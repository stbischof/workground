/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.net.MalformedURLException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.olap4j.OlapConnection;
import org.olap4j.metadata.Catalog;
import org.olap4j.metadata.NamedList;
import org.opencube.junit5.ContextSource;
import org.opencube.junit5.context.TestContextWrapper;
import org.opencube.junit5.dataloader.FastFoodmardDataLoader;
import org.opencube.junit5.propupdator.AppandFoodMartCatalogAsFile;

import mondrian.olap.MondrianServer;
import mondrian.olap.Util;
import mondrian.olap4j.MondrianOlap4jDriver;
import mondrian.rolap.RolapConnectionProperties;
import mondrian.server.StringRepositoryContentFinder;
import mondrian.server.UrlRepositoryContentFinder;
import mondrian.xmla.test.XmlaTestContext;

/**
 * Test suite for server functionality in {@link MondrianServer}.
 *
 * @author jhyde
 * @since 2010/11/22
 */
class MondrianServerTest {
    /**
     * Tests an embedded server.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class)
    void testEmbedded(TestContextWrapper context) {
        final MondrianServer server =
            MondrianServer.forConnection(context.createConnection());
        final int id = server.getId();
        assertNotSame(0, id);
        server.shutdown();
    }

    /**
     * Tests a server with its own repository.
     */
    @Test
    void testStringRepository() throws MalformedURLException {
        final MondrianServer server =
            MondrianServer.createWithRepository(
                new StringRepositoryContentFinder("foo bar"),
                null);
        final int id = server.getId();
        assertNotSame(0, id);
        server.shutdown();
    }

    /**
     * Tests a server that reads its repository from a file URL.
     */

    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class )
    void testRepository(TestContextWrapper context) throws MalformedURLException, SQLException {
        final XmlaTestContext xmlaTestContext = new XmlaTestContext();
        final MondrianServer server =
            MondrianServer.createWithRepository(
                new UrlRepositoryContentFinder(
                    "inline:" + xmlaTestContext.getDataSourcesString(context)),
                null);
        final int id = server.getId();
        assertNotSame(0, id);
        java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver(context.getContext()));
        OlapConnection connection =
            server.getConnection("FoodMart", "FoodMart", null);
        final NamedList<Catalog> catalogs =
            connection.getOlapCatalogs();
        assertEquals(1, catalogs.size());
        assertEquals("FoodMart", catalogs.get(0).getName());
        server.shutdown();
    }

    /**
     * Tests a server that reads its repository from a file URL.
     */
    @ParameterizedTest
    @ContextSource(propertyUpdater = AppandFoodMartCatalogAsFile.class, dataloader = FastFoodmardDataLoader.class )
    void testRepositoryWithBadCatalog(TestContextWrapper context) throws Exception {
        final XmlaTestContext xmlaTestContext = new XmlaTestContext() {

            String catalogName = context.createConnection().getCatalogName();
            public String getDataSourcesString() {
                return super.getDataSourcesString(context)
                    .replace(
                        "</Catalog>",
                        "</Catalog>\n"
                        + "<Catalog name='__1'>\n"
                        + "<DataSourceInfo>Provider=mondrian;Jdbc='jdbc:derby:non-existing-db'</DataSourceInfo>\n"
                        + "<Definition>" + catalogName + "</Definition>\n"
                        + "</Catalog>\n");
            }
        };
        final MondrianServer server =
            MondrianServer.createWithRepository(
                new UrlRepositoryContentFinder(
                    "inline:" + xmlaTestContext.getDataSourcesString(context)),
                null);
        final int id = server.getId();
        assertNotSame(0, id);
        java.sql.DriverManager.registerDriver(new MondrianOlap4jDriver(context.getContext()));
        OlapConnection connection =
            server.getConnection("FoodMart", "FoodMart", null);
        final NamedList<Catalog> catalogs =
            connection.getOlapCatalogs();
        assertEquals(1, catalogs.size());
        assertEquals("FoodMart", catalogs.get(0).getName());
        server.shutdown();
    }
}
