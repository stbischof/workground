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
package org.eclipse.daanse.mdx.parser.cccx;

public class MdxParserUtil {
	private MdxParserUtil() {
	}

	public static String stripQuotes(String s, String prefix, String suffix, String quoted) {
		if (s.startsWith(prefix) && s.endsWith(suffix)) {
			s = s.substring(prefix.length(), s.length() - suffix.length());
			s = s.replace(quoted, suffix);
		}
		return s;
	}
}
