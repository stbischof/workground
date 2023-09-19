/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2000-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package mondrian.olap;

import java.io.PrintWriter;

import org.eclipse.daanse.olap.api.query.component.MemberProperty;

/**
 * Member property or solve order specification.
 *
 * @author jhyde, 1 March, 2000
 */
public class MemberPropertyImpl extends AbstractQueryPart implements MemberProperty {

    private final String name;
    private Exp exp;

    public MemberPropertyImpl(String name, Exp exp) {
        this.name = name;
        this.exp = exp;
    }

    public MemberPropertyImpl(MemberProperty memberProperty) {
        this(memberProperty.getName(), memberProperty.getExp().cloneExp());
    }

    static MemberProperty[] cloneArray(MemberProperty[] x) {
        MemberProperty[] x2 = new MemberProperty[x.length];
        for (int i = 0; i < x.length; i++) {
            x2[i] = new MemberPropertyImpl(x[i]);
        }
        return x2;
    }

    @Override
    public void resolve(Validator validator) {
        exp = validator.validate(exp, false);
    }

    @Override
    public Exp getExp() {
        return exp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
	public Object[] getChildren() {
        return new Exp[] {exp};
    }

    @Override
	public void unparse(PrintWriter pw) {
        pw.print(new StringBuilder(name).append(" = ").toString());
        exp.unparse(pw);
    }

    /**
     * Retrieves a property by name from an array.
     */
    static Exp get(MemberProperty[] a, String name, boolean caseSensitive) {
        // TODO: Linear search may be a performance problem.
        for (int i = 0; i < a.length; i++) {
            if (Util.equalName(a[i].getName(), name, caseSensitive)) {
                return a[i].getExp();
            }
        }
        return null;
    }
}


// End MemberProperty.java
