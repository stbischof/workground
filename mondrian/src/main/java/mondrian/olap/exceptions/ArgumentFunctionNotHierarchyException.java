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

public class ArgumentFunctionNotHierarchyException extends MondrianException {

    private final static String message = "Argument ''{0,number}'' of function ''{1}'' must be a hierarchy";

    public ArgumentFunctionNotHierarchyException(Integer i, String o) {
        super(MessageFormat.format(message, i, o));
    }
}
