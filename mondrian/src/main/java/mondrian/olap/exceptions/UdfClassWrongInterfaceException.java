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

public class UdfClassWrongInterfaceException extends MondrianException {

    public final static String message = """
        Failed to load user-defined function ''{0}'': class ''{1}'' does not implement the required interface ''{2}''
        """;

    public UdfClassWrongInterfaceException(String functionName, String className, String name) {
        super(MessageFormat.format(message, functionName, className, name));
    }
}
