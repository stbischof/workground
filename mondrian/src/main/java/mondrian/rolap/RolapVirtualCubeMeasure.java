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

import org.eclipse.daanse.rolap.mapping.api.model.SQLExpressionMapping;

/**
 * Measure which is defined in a virtual cube, and based on a stored measure
 * in one of the virtual cube's base cubes.
 *
 * @author jhyde
 * @since Aug 18, 2006
 */
public class RolapVirtualCubeMeasure
    extends RolapMemberBase
    implements RolapStoredMeasure
{
    /**
     * The measure in the underlying cube.
     */
    private final RolapStoredMeasure cubeMeasure;
    private final Map<String, Object> metaData;

    public RolapVirtualCubeMeasure(
        RolapMember parentMember,
        RolapLevel level,
        RolapStoredMeasure cubeMeasure,
        Map<String, Object> metaData)
    {
        super(parentMember, level, cubeMeasure.getName());
        this.cubeMeasure = cubeMeasure;
        this.metaData = metaData;
    }

    @Override
	public Object getPropertyValue(String propertyName, boolean matchCase) {
        // Look first in this member (against the virtual cube), then
        // fallback on the base measure.
        // This allows, for instance, a measure to be invisible in a virtual
        // cube but visible in its base cube.
        Object value = super.getPropertyValue(propertyName, matchCase);
        if (value == null) {
            value = cubeMeasure.getPropertyValue(propertyName, matchCase);
        }
        return value;
    }

    @Override
	public RolapCube getCube() {
        return cubeMeasure.getCube();
    }

    @Override
	public Object getStarMeasure() {
        return cubeMeasure.getStarMeasure();
    }

    @Override
	public SQLExpressionMapping getMondrianDefExpression() {
        return cubeMeasure.getMondrianDefExpression();
    }

    @Override
	public RolapAggregator getAggregator() {
        return cubeMeasure.getAggregator();
    }

    @Override
	public RolapResult.ValueFormatter getFormatter() {
        return cubeMeasure.getFormatter();
    }

    @Override
	public Map<String, Object> getMetadata() {
        return metaData;
    }
}
