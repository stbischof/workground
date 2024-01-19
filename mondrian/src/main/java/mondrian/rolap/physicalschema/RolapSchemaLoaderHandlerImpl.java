/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2010-2013 Pentaho and others
// All Rights Reserved.
*/
package mondrian.rolap.physicalschema;

import mondrian.olap.MondrianException;
import org.eigenbase.xom.Location;
import org.eigenbase.xom.NodeDef;

import java.util.List;

/**
 * Implementation of {@link Handler}.
 *
 * <p>Derived class must implement {@link #getWarningList()}.</p>
 *
 * @author jhyde
 */
abstract class RolapSchemaLoaderHandlerImpl
    implements Handler
{
    private int errorCount;

    /**
     * Creates a HandlerImpl.
     */
    public RolapSchemaLoaderHandlerImpl() {
    }

    public void warning(
        String message)
    {
        final RuntimeException ex =
            new RuntimeException(message);
        final List<Exception> warningList =
            getWarningList();
        if (warningList != null) {
            warningList.add(ex);
        } else {
            throw ex;
        }
    }

    /**
     * Returns list where warnings are to be stored, or null if
     * warnings are to be escalated to errors and thrown immediately.
     *
     * @return Warning list
     */
    protected abstract List<Exception>  getWarningList();


    public void error(
        String message)
    {
        final RuntimeException ex =
            new RuntimeException(message);
        final List<Exception> warningList =
            getWarningList();
        if (warningList != null) {
            ++errorCount;
            warningList.add(ex);
        } else {
            throw ex;
        }
    }


    public Exception fatal(String message)
    {
        return new Exception(
            message);
    }

    public void check() {
        if (errorCount > 0) {
            throw new MondrianMultipleSchemaException(
                "There were schema errors",
                getWarningList());
        }
    }
}

// End RolapSchemaLoaderHandlerImpl.java
