/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// Copyright (C) 2021 Sergei Semenkov
// All Rights Reserved.
*/
package mondrian.rolap;

import java.util.Collections;
import java.util.Map;

import org.eclipse.daanse.olap.api.query.component.Formula;

import mondrian.olap.Exp;
import mondrian.olap.FormulaImpl;
import mondrian.olap.Property;
import mondrian.olap.Util;

/**
 * A <code>RolapCalculatedMember</code> is a member based upon a
 * {@link FormulaImpl}.
 *
 * <p>It is created before the formula has been resolved; the formula is
 * responsible for setting the "format_string" property.
 *
 * @author jhyde
 * @since 26 August, 2001
 */
public class RolapCalculatedMember extends RolapMemberBase {
    private final Formula formula;
    private Map<String, Object> metadata;
    // source cube for a virtual member
    private RolapCube baseCube;

    /**
     * Creates a RolapCalculatedMember.
     *
     * @param parentMember Parent member
     * @param level Level
     * @param name Name
     * @param formula Formula
     */
    RolapCalculatedMember(
        RolapMember parentMember,
        RolapLevel level,
        String name,
        Formula formula, boolean caseSensitive)
    {
        // A calculated measure has MemberType.FORMULA because FORMULA
        // overrides MEASURE.
        super(parentMember, level, name, null, MemberType.FORMULA, caseSensitive);
        this.formula = formula;
        this.metadata = Collections.emptyMap();
    }

    // override RolapMember
    @Override
	public int getSolveOrder() {
        final Number solveOrder = formula.getSolveOrder(getBaseCube().getContext().getConfig().caseSensitive());
        return solveOrder == null ? 0 : solveOrder.intValue();
    }

    @Override
	public Object getPropertyValue(String propertyName, boolean matchCase) {
        if (Util.equalWithMatchCaseOption(propertyName, Property.FORMULA.name, matchCase)) {
            return formula;
        } else if (Util.equalWithMatchCaseOption(
                propertyName, Property.CHILDREN_CARDINALITY.name, matchCase))
        {
            // Looking up children is unnecessary for calculated member.
            // If do that, SQLException will be thrown.
            return 0;
        } else {
            return super.getPropertyValue(propertyName, matchCase);
        }
    }

    @Override
	protected boolean computeCalculated(final MemberType memberType) {
        return true;
    }

    @Override
	public boolean isCalculatedInQuery() {
        final String memberScope =
            (String) getPropertyValue(Property.MEMBER_SCOPE.name, getBaseCube().getContext().getConfig().caseSensitive());
        return memberScope == null
            || memberScope.equals("QUERY");
    }

    @Override
	public Exp getExpression() {
        return formula.getExpression();
    }

    public Formula getFormula() {
        return formula;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    void setMetadata(Map<String, Object> metadata) {
        assert metadata != null;
        this.metadata = metadata;
    }

    public RolapCube getBaseCube() {
        return baseCube;
    }

    public void setBaseCube(RolapCube baseCube) {
        this.baseCube = baseCube;
    }
}
