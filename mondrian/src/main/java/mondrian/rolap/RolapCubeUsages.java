/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2001-2005 Julian Hyde
// Copyright (C) 2005-2017 Hitachi Vantara and others
// All Rights Reserved.
*/

package mondrian.rolap;

import java.util.List;

import org.eclipse.daanse.rolap.mapping.api.model.CubeConnectorMapping;

/**
 * Provides the base cubes that a virtual cube uses and
 * specifies if unrelated dimensions to measures from these cubes should be
 * ignored.
 *
 * @author ajoglekar
 * @since Nov 22 2007
 */
public class RolapCubeUsages {
    private List<? extends CubeConnectorMapping> cubeUsages;

    public RolapCubeUsages(List<? extends CubeConnectorMapping> cubeUsage) {
        this.cubeUsages = cubeUsage;
    }

    public boolean shouldIgnoreUnrelatedDimensions(String baseCubeName) {
        if (cubeUsages == null) {
            return false;
        }
        for (CubeConnectorMapping usage : cubeUsages) {
            if (usage.getCube().getName().equals(baseCubeName)
                && Boolean.TRUE.equals(usage.isIgnoreUnrelatedDimensions()))
            {
                return true;
            }
        }
        return false;
    }
}
