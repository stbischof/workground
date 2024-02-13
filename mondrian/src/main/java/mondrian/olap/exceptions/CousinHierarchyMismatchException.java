/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena - initial
 *   Stefan Bischof (bipolis.org) - initial
 */
package mondrian.olap.exceptions;

import mondrian.olap.MondrianException;

import java.text.MessageFormat;

public class CousinHierarchyMismatchException extends MondrianException {

    public final static String cousinHierarchyMismatch = """
        The member arguments to the Cousin function must be from the same hierarchy. The members are ''{0}'' and ''{1}''.
        """;

    public CousinHierarchyMismatchException(String name1, String name2) {
        super(MessageFormat.format(cousinHierarchyMismatch, name1, name2));
    }
}
