/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2012-2013 Pentaho
// All Rights Reserved.
*/
package mondrian.rolap.physicalschema;

import mondrian.util.Format;
import org.eclipse.daanse.db.dialect.api.Datatype;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Enumeration of the types of column that can be in a date dimension table.
 *
 * @author jhyde
 */
enum TimeColumnRole {
    JULIAN("time_id", Datatype.INTEGER), // e.g. 2454115
    YYMMDD("yymmdd", Datatype.INTEGER), // e.g. 121231
    YYYYMMDD("yyyymmdd", Datatype.INTEGER), // e.g. 20121231
    DATE("the_date", Datatype.DATE), // e.g. '2012-12-31'
    DAY_OF_WEEK("day_of_week", Datatype.INTEGER), // e.g. 0 (= Sunday)
    DAY_OF_WEEK_IN_MONTH("day_of_week_in_month", Datatype.INTEGER),
    DAY_OF_WEEK_NAME("the_day", Datatype.STRING), // e.g. 'Friday'
    MONTH_NAME("the_month", Datatype.STRING), // e.g. 'December'
    YEAR("the_year", Datatype.INTEGER), // e.g. 2012
    DAY_OF_MONTH("day_of_month", Datatype.INTEGER), // e.g. 31
    WEEK_OF_YEAR("week_of_year", Datatype.INTEGER), // e.g. 53
    MONTH("month_of_year", Datatype.INTEGER), // e.g. 12
    QUARTER("quarter", Datatype.STRING); // e.g. 'Q4'

    final String columnName;
    final Datatype defaultDatatype;

    public static final Map<String, TimeColumnRole> mapNameToRole;

    private TimeColumnRole(String columnName, Datatype datatype) {
        this.columnName = columnName;
        this.defaultDatatype = datatype;
    }

    private static String[] quarters = {
        "Q1", "Q1", "Q1",
        "Q2", "Q2", "Q2",
        "Q3", "Q3", "Q3",
        "Q4", "Q4", "Q4",
    };

    static {
        Map<String, TimeColumnRole> map =
            new HashMap<String, TimeColumnRole>();
        for (TimeColumnRole value : values()) {
            TimeColumnRole put = map.put(value.columnName.toUpperCase(), value);
            assert put == null : "duplicate column";
        }
        mapNameToRole = Collections.unmodifiableMap(map);
    }

    public static class Struct {
        public final TimeColumnRole role;
        public final Date epoch;

        public Struct(TimeColumnRole role, Date epoch) {
            this.role = role;
            this.epoch = epoch;
            assert role != null;
        }

        /**
         * Creates state, if any is needed, that can be used for multiple calls
         * to {@link #bind}.
         *
         * @param locale Locale in which to generate strings
         * @return Any needed state, or null
         */
        Object initialize(Locale locale) {
            switch (role) {
                case DAY_OF_WEEK_NAME:
                    return new Format("dddd", locale);
                case MONTH_NAME:
                    return new Format("mmmm", locale);
                case JULIAN:
                    if (epoch != null) {
                        return Util.julian(
                            epoch.getYear() + 1900,
                            epoch.getMonth() + 1,
                            epoch.getDate());
                    } else {
                        return 0L;
                    }
                default:
                    return null;
            }
        }

        void bind(
            Object[] states,
            int ordinal,
            Instant instant,
            PreparedStatement pstmt) throws SQLException
        {
            switch (role) {
                case JULIAN:
                    long julian =
                        Util.julian(
                            instant.get(ChronoField.YEAR),
                            instant.get(ChronoField.MONTH_OF_YEAR),
                            instant.get(ChronoField.DAY_OF_MONTH))
                            - ((Long) states[ordinal]);
                    pstmt.setLong(ordinal, julian);
                    return;
                case YYMMDD:
                    pstmt.setLong(
                        ordinal,
                        (instant.get(ChronoField.YEAR) % 100) * 10000
                            + instant.get(ChronoField.MONTH_OF_YEAR) * 100
                            + instant.get(ChronoField.MONTH_OF_YEAR));
                    return;
                case YYYYMMDD:
                    pstmt.setLong(
                        ordinal,
                        instant.get(ChronoField.YEAR) * 10000
                            + (instant.get(ChronoField.MONTH_OF_YEAR)) * 100
                            + instant.get(ChronoField.MONTH_OF_YEAR));
                    return;
                case DATE:
                    pstmt.setDate(
                        ordinal,
                        new java.sql.Date(instant.toEpochMilli()));
                    return;
                case DAY_OF_MONTH:
                    pstmt.setInt(
                        ordinal,
                        instant.get(ChronoField.DAY_OF_MONTH));
                    return;
                case MONTH_NAME:
                case DAY_OF_WEEK_NAME:
                    pstmt.setString(
                        ordinal, ((Format) states[ordinal]).format(instant));
                    return;
                case DAY_OF_WEEK:
                    pstmt.setInt(ordinal, instant.get(ChronoField.DAY_OF_WEEK));
                    return;
                case DAY_OF_WEEK_IN_MONTH:
                    pstmt.setInt(
                        ordinal, instant.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                    return;
                case WEEK_OF_YEAR:
                    pstmt.setInt(ordinal, instant.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
                    return;
                case MONTH:
                    pstmt.setInt(ordinal, instant.get(ChronoField.MONTH_OF_YEAR));
                    return;
                case QUARTER:
                    pstmt.setString(
                        ordinal, quarters[instant.get(ChronoField.MONTH_OF_YEAR) - 1]);
                    return;
                case YEAR:
                    pstmt.setInt(
                        ordinal, instant.get(ChronoField.YEAR));
                    return;
                default:
                    throw Util.unexpected(role);
            }
        }
    }
}

// End TimeColumnRole.java
