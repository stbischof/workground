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

package mondrian.olap;

import java.util.List;

import mondrian.olap.exceptions.FailedToParseQueryException;
import org.eclipse.daanse.olap.api.Connection;
import org.eclipse.daanse.olap.api.Statement;
import org.eclipse.daanse.olap.api.element.Schema;
import org.eclipse.daanse.olap.api.function.FunctionTable;
import org.eclipse.daanse.olap.api.query.component.QueryComponent;
import org.eclipse.daanse.olap.impl.StatementImpl;
import org.slf4j.Logger;

import mondrian.parser.JavaccParserValidatorImpl;
import mondrian.parser.MdxParserValidator;

/**
 * <code>ConnectionBase</code> implements some of the methods in
 * {@link Connection}.
 *
 * @author jhyde
 * @since 6 August, 2001
 */
public abstract class ConnectionBase implements Connection {

    protected ConnectionBase() {
    }

    protected abstract Logger getLogger();


    @Override
	public QueryImpl parseQuery(String query) {
        return (QueryImpl) parseStatement(query);
    }

    /**
     * Parses a query, with specified function table and the mode for strict
     * validation(if true then invalid members are not ignored).
     *
     * <p>This method is only used in testing and by clients that need to
     * support customized parser behavior. That is why this method is not part
     * of the Connection interface.
     *
     * <p>See test case mondrian.olap.CustomizedParserTest.
     *
     * @param statement Evaluation context
     * @param query MDX query that requires special parsing
     * @param funTable Customized function table to use in parsing
     * @param strictValidation If true, do not ignore invalid members
     * @return Query the corresponding Query object if parsing is successful
     * @throws MondrianException if parsing fails
     */
    public QueryComponent parseStatement(
        Statement statement,
        String query,
        FunctionTable funTable,
        boolean strictValidation)
    {
        MdxParserValidator parser = createParser();
        boolean debug = false;

        if (getLogger().isDebugEnabled()) {
            String s = new StringBuilder().append(Util.NL).append(query.replaceAll("[\n\r]", "_")).toString();
            getLogger().debug(s);
        }

        try {
            return
                parser.parseInternal(
                    statement, query, debug, funTable, strictValidation);
        } catch (Exception e) {
            throw new FailedToParseQueryException(query, e);
        }
    }

    protected MdxParserValidator createParser() {
        return new JavaccParserValidatorImpl();
    }

    @Override
    public List<Schema> getSchemas() {
        return List.of(getSchema());
    }

    public  QueryComponent parseStatementN(StatementImpl statement, String query,
                                           FunctionTable funTable,
                                           boolean strictValidation) {
        MdxParserValidator parser = createParser();
        boolean debug = false;

        if (getLogger().isDebugEnabled()) {
            String s = new StringBuilder().append(Util.NL).append(query.replaceAll("[\n\r]", "_")).toString();
            getLogger().debug(s);
        }

        try {
            return
                parser.parseInternal(
                    statement, query, debug, funTable, strictValidation);
        } catch (Exception e) {
            throw new FailedToParseQueryException(query, e);
        }
    }
}
