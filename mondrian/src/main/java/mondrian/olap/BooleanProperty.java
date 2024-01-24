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

public class BooleanProperty extends AbstractProperty<Boolean> {

    public BooleanProperty(MondrianProperties mondrianProperties, String key, Boolean aDefault) {
        super(mondrianProperties, key, aDefault ? "true" : "false");
    }

    @Override
    public Boolean get() {
        String value = mondrianProperties.getProperty(key, aDefault);
        if (value == null) {
            return false;
        }
        return toBoolean(value);
    }

    @Override
    public void set(Boolean value) {

    }

    private boolean toBoolean(final String value)
    {
        String trimmedLowerValue = value.toLowerCase().trim();
        return trimmedLowerValue.equals("1")
            || trimmedLowerValue.equals("true")
            || trimmedLowerValue.equals("yes");
    }
}
