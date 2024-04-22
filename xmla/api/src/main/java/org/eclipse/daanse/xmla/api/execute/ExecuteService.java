/*
* Copyright (c) 2023 Contributors to the Eclipse Foundation.
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
package org.eclipse.daanse.xmla.api.execute;

import org.eclipse.daanse.xmla.api.RequestMetaData;
import org.eclipse.daanse.xmla.api.UserPrincipal;
import org.eclipse.daanse.xmla.api.execute.alter.AlterRequest;
import org.eclipse.daanse.xmla.api.execute.alter.AlterResponse;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelRequest;
import org.eclipse.daanse.xmla.api.execute.cancel.CancelResponse;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheRequest;
import org.eclipse.daanse.xmla.api.execute.clearcache.ClearCacheResponse;
import org.eclipse.daanse.xmla.api.execute.statement.StatementRequest;
import org.eclipse.daanse.xmla.api.execute.statement.StatementResponse;

public interface ExecuteService {

	/*
	 * The method is used to alter an object that already exists on a server.
	 */
	AlterResponse alter(AlterRequest statementRequest, RequestMetaData metaData, UserPrincipal userPrincipal);

	/*
	 * The method cancels the currently running command on the specified
	 * connection.
	 */
	CancelResponse cancel(CancelRequest capture, RequestMetaData metaData, UserPrincipal userPrincipal);

	/*
	 * The method clears the in-memory cache of the specified object.
	 */
	ClearCacheResponse clearCache(ClearCacheRequest clearCacheRequest, RequestMetaData metaData, UserPrincipal userPrincipal);

	/*
	 * The method consists of a string. This MUST be a valid string in a
	 * language that is understood by the server, such as MDX, DMX, or SQL.
	 */
	StatementResponse statement(StatementRequest statementRequest, RequestMetaData metaData, UserPrincipal userPrincipal);
}
