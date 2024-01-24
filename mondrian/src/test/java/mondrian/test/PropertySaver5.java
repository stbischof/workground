/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2003-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara
// All Rights Reserved.
//
// jhyde, Feb 21, 2003
*/

package mondrian.test;

import java.util.HashMap;
import java.util.Map;

import mondrian.olap.BooleanProperty;
import mondrian.olap.DoubleProperty;
import mondrian.olap.IntegerProperty;
import mondrian.olap.StringProperty;
import org.apache.logging.log4j.Level;
import org.slf4j.Logger;

import mondrian.olap.MondrianProperties;
import mondrian.rolap.RolapUtil;

/**
 * Sets properties and logging levels, and remembers the original values so they
 * can be reverted at the end of the test.
 *
 * @author jhyde
 * @since Oct 28, 2008
 */
public class PropertySaver5 {

    public final MondrianProperties properties =
        MondrianProperties.instance();

    private final Map<String, String> originalValues =
        new HashMap<>();

    private final Map<Logger, Level> originalLoggerLevels =
        new HashMap<>();

    // wacky initializer to prevent compiler from internalizing the
    // string (we don't want it to be == other occurrences of "NOT_SET")
    private static final String NOT_SET =
        new StringBuffer("NOT_" + "SET").toString();

    /**
     * Sets a boolean property and remembers its previous value.
     *
     * @param property Property
     * @param value New value
     */
    public void set(BooleanProperty property, boolean value) {
        if (!originalValues.containsKey(property.getPath())) {
            final String originalValue =
                properties.containsKey(property.getPath())
                    ? properties.getProperty(property.getPath())
                    : NOT_SET;
            originalValues.put(
                property.getPath(),
                originalValue);
        }
        property.set(value);
    }

    /**
     * Sets an integer property and remembers its previous value.
     *
     * @param property Property
     * @param value New value
     */
    public void set(IntegerProperty property, int value) {
        if (!originalValues.containsKey(property.getPath())) {
            final String originalValue =
                properties.containsKey(property.getPath())
                    ? properties.getProperty(property.getPath())
                    : NOT_SET;
            originalValues.put(
                property.getPath(),
                originalValue);
        }
        property.set(value);
    }

    /**
     * Sets a string property and remembers its previous value.
     *
     * @param property Property
     * @param value New value
     */
    public void set(StringProperty property, String value) {
        if (!originalValues.containsKey(property.getPath())) {
            final String originalValue =
                properties.containsKey(property.getPath())
                    ? properties.getProperty(property.getPath())
                    : NOT_SET;
            originalValues.put(
                property.getPath(),
                originalValue);
        }
        property.set(value);
        if (property.getPath().equals(MondrianProperties.instance().NullMemberRepresentation.getPath()))
        {
            RolapUtil.reloadNullLiteral();
        }
    }

    /**
     * Sets a double property and remembers its previous value.
     *
     * @param property Property
     * @param value New value
     */
    public void set(DoubleProperty property, Double value) {
        if (!originalValues.containsKey(property.getPath())) {
            final String originalValue =
                properties.containsKey(property.getPath())
                    ? properties.getProperty(property.getPath())
                    : NOT_SET;
            originalValues.put(
                property.getPath(),
                originalValue);
        }
        property.set(value);
    }

    /**
     * Sets all properties back to their original values.
     */
    public void reset() {
        for (Map.Entry<String, String> entry : originalValues.entrySet()) {
            final String value = entry.getValue();
            //noinspection StringEquality
            if (value == NOT_SET) {
                properties.remove(entry.getKey());
            } else {
                properties.setProperty(entry.getKey(), value);
            }
            if (entry.getKey().equals(MondrianProperties.instance().NullMemberRepresentation.getPath()))
            {
                RolapUtil.reloadNullLiteral();
            }
        }

    }


}
