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
package mondrian.olap.api;

import mondrian.olap.IdImpl;

import java.util.ArrayList;
import java.util.List;

public interface Segment {

    Quoting getQuoting();

    List<NameSegment> getKeyParts();

    boolean matches(String name, boolean caseSensitive);

    void toString(StringBuilder buf);

    /**
     * Converts an array of names to a list of segments.
     *
     * @param nameParts Array of names
     * @return List of segments
     */
    static List<Segment> toList(String... nameParts) {
        final List<Segment> segments =
            new ArrayList<>(nameParts.length);
        for (String namePart : nameParts) {
            segments.add(new IdImpl.NameSegmentImpl(namePart));
        }
        return segments;
    }
}
