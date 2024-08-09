/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian.rolap;

import org.eclipse.daanse.rolap.mapping.api.model.SchemaMapping;

import mondrian.olap.Util;
import mondrian.util.ByteString;
import mondrian.util.StringKey;

/**
 * Globally unique identifier for the metadata content of a schema.
 *
 * <p>Two schemas have the same content if they have same schema XML.
 * But schemas are also deemed to have the same content if they are read from
 * the same URL (subject to rules about how often the contents of a URL change)
 * or if their content has the same MD5 hash.</p>
 *
 * @see SchemaKey
 *
 * @author jhyde
 */
public class SchemaContentKey extends StringKey {
    private SchemaContentKey(String s) {
        super(s);
    }

	static SchemaContentKey create(SchemaMapping schemaMapping) {

		int hash = System.identityHashCode(schemaMapping);
		return new SchemaContentKey(new ByteString(Util.digestSHA(hash + "")).toString());
	}
}
