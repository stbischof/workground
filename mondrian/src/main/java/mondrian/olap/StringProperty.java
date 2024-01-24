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
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package mondrian.olap;

public class StringProperty extends AbstractProperty<String> {

    public StringProperty(MondrianProperties mondrianProperties, String key, String aDefault) {
        super(mondrianProperties, key, aDefault);
    }

    public String get() {
        return mondrianProperties.getProperty(key, aDefault);
    }

    @Override
    public void set(String value) {

    }
}
