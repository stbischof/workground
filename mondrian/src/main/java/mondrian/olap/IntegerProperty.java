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

public class IntegerProperty extends AbstractProperty<Integer> {

    protected IntegerProperty(MondrianProperties mondrianProperties, String key, Integer aDefault) {
        super(mondrianProperties, key, aDefault.toString());
    }

    @Override
    public Integer get() {
        String value = mondrianProperties.getProperty(key, aDefault);
        if (value == null) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    @Override
    public void set(Integer value) {

    }
}
