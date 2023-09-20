/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap;

import java.util.Map;

import mondrian.olap.StringLiteralImpl;
import org.eclipse.daanse.db.dialect.api.Datatype;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.Expression;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.MeasureDataTypeEnum;

import mondrian.olap.Property;
import mondrian.resource.MondrianResource;
import mondrian.spi.CellFormatter;

/**
 * Measure which is computed from a SQL column (or expression) and which is
 * defined in a non-virtual cube.
 *
 * @see RolapVirtualCubeMeasure
 *
 * @author jhyde
 * @since 24 August, 2006
 */
public class RolapBaseCubeMeasure
    extends RolapMemberBase
    implements RolapStoredMeasure
{
    static enum DataType {
        Integer,
        Numeric,
        String
    }

    /**
     * For SQL generator. Column which holds the value of the measure.
     */
    private final Expression expression;

    /**
     * For SQL generator. Has values "SUM", "COUNT", etc.
     */
    private final RolapAggregator aggregator;

    private final RolapCube cube;
    private final Map<String, Object> metadata;

    /**
     * Holds the {@link mondrian.rolap.RolapStar.Measure} from which this
     * member is computed. Untyped, because another implementation might store
     * it somewhere else.
     */
    private Object starMeasure;

    private RolapResult.ValueFormatter formatter;

    /**
     * Creates a RolapBaseCubeMeasure.
     *
     * @param cube Cube
     * @param parentMember Parent member
     * @param level Level this member belongs to
     * @param name Name of this member
     * @param caption Caption
     * @param description Description
     * @param formatString Format string
     * @param expression Expression
     * @param aggregatorName Aggregator
     * @param datatype Data type
     * @param metadata metadata
     */
    RolapBaseCubeMeasure(
        RolapCube cube,
        RolapMember parentMember,
        RolapLevel level,
        String name,
        String caption,
        String description,
        String formatString,
        Expression expression,
        String aggregatorName,
        MeasureDataTypeEnum datatype,
        Map<String, Object> metadata)
    {
        super(parentMember, level, name, null, MemberType.MEASURE, cube.getContext().getConfig().caseSensitive());
        assert metadata != null;
        this.cube = cube;
        this.metadata = metadata;
        this.caption = caption;
        this.expression = expression;
        boolean caseSensitive = cube.getContext().getConfig().caseSensitive();
        if (description != null) {
            setProperty(
                Property.DESCRIPTION_PROPERTY.name,
                description, caseSensitive);
        }
        if (formatString == null) {
            formatString = "";
        } else {
            setProperty(
                Property.FORMAT_STRING.name,
                formatString, caseSensitive);
        }
        setProperty(
            Property.FORMAT_EXP_PARSED.name,
            StringLiteralImpl.create(formatString), caseSensitive);
        setProperty(
            Property.FORMAT_EXP.name,
            formatString, caseSensitive);

        // Validate aggregator.
        this.aggregator =
            RolapAggregator.enumeration.getValue(aggregatorName, false);
        if (this.aggregator == null) {
            StringBuilder buf = new StringBuilder();
            for (String aggName : RolapAggregator.enumeration.getNames()) {
                if (buf.length() > 0) {
                    buf.append(", ");
                }
                buf.append('\'');
                buf.append(aggName);
                buf.append('\'');
            }
            throw MondrianResource.instance().UnknownAggregator.ex(
                aggregatorName,
                buf.toString());
        }

        setProperty(Property.AGGREGATION_TYPE.name, aggregator, caseSensitive);
        if (datatype == null) {
            if (aggregator == RolapAggregator.Count
                || aggregator == RolapAggregator.DistinctCount)
            {
                datatype = MeasureDataTypeEnum.INTEGER;
            } else {
                datatype = MeasureDataTypeEnum.NUMERIC;
            }
        }
        if (RolapBaseCubeMeasure.DataType.valueOf(datatype.getValue()) == null) {
            throw MondrianResource.instance().CastInvalidType.ex(datatype.getValue());
        }
        setProperty(Property.DATATYPE.name, datatype.getValue(), caseSensitive);
    }

    @Override
	public Expression getMondrianDefExpression() {
        return expression;
    }

    @Override
	public RolapAggregator getAggregator() {
        return aggregator;
    }

    @Override
	public RolapCube getCube() {
        return cube;
    }

    @Override
	public RolapResult.ValueFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(CellFormatter cellFormatter) {
        this.formatter =
            new RolapResult.CellFormatterValueFormatter(cellFormatter);
    }

    @Override
	public Object getStarMeasure() {
        return starMeasure;
    }

    void setStarMeasure(Object starMeasure) {
        this.starMeasure = starMeasure;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public Datatype getDatatype() {
        Object datatype = getPropertyValue(Property.DATATYPE.name, getCube().getContext().getConfig().caseSensitive());
        try {
            return Datatype.fromValue((String) datatype);
        } catch (ClassCastException e) {
            return Datatype.STRING;
        } catch (IllegalArgumentException e) {
            return Datatype.STRING;
        }
    }
}
