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

public class CurrentMemberWithCompoundSlicerException extends MondrianException {
    private final static String currentMemberWithCompoundSlicer =
        "The MDX function CURRENTMEMBER failed because the coordinate for the ''{0}'' hierarchy contains a set";

    public CurrentMemberWithCompoundSlicerException(String h) {
        super(MessageFormat.format(currentMemberWithCompoundSlicer, h));
    }
}
