/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */

package mondrian.calc.impl;

import mondrian.calc.Calc;
import mondrian.calc.MemberCalc;
import mondrian.olap.Evaluator;
import mondrian.olap.type.MemberType;
import mondrian.olap.type.Type;

/**
 * Abstract implementation of the {@link mondrian.calc.MemberCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateMember(mondrian.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian.olap.Evaluator)} method will call it.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public abstract class AbstractMemberCalc
extends AbstractCalc
implements MemberCalc
{
    /**
     * Creates an AbstractMemberCalc.
     *
     * @param exp Source expression
     * @param calcs Child compiled expressions
     */
    protected AbstractMemberCalc(String name, Type type, Calc[] calcs) {
        super(name,type, calcs);
        assert getType() instanceof MemberType;
    }

    @Override
    public Object evaluate(Evaluator evaluator) {
        return evaluateMember(evaluator);
    }
}
