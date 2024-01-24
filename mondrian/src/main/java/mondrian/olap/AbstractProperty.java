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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractProperty<T> {
    protected MondrianProperties mondrianProperties;
    protected String key;
    protected String aDefault;
    private final TriggerList triggerList = new TriggerList();

    protected AbstractProperty(MondrianProperties mondrianProperties, String key, String aDefault) {
        this.mondrianProperties = mondrianProperties;
        this.key = key;
        this.aDefault = aDefault;
    }

    public abstract T get();

    public String getPath()
    {
        return key;
    }

    public String getDefaultValue() {
        return aDefault;
    }

    public String stringValue() {
        return this.mondrianProperties.getProperty(key, aDefault);
    }

    public synchronized void addTrigger(Trigger trigger)
    {
        triggerList.add(trigger);
    }

    public abstract void set(T value);

    /**
     * A trigger list a list of triggers associated with a given property.
     *
     * <p>A trigger list is associated with a property key, and contains zero
     * or more {@link Trigger} objects.</p>
     *
     * <p>Each {@link Trigger} is stored in a {@link WeakReference} so that
     * when the Trigger is only reachable via weak references the Trigger will
     * be be collected and the contents of the WeakReference will be set to
     * null.</p>
     */
    private static class TriggerList
        extends ArrayList
    {
        /**
         * Adds a Trigger, wrapping it in a WeakReference.
         *
         * @param trigger
         */
        void add(final Trigger trigger)
        {
            // this is the object to add to list
            Object o =
                (trigger.isPersistent()) ? trigger
                    : (Object) new WeakReference /*<Trigger>*/(trigger);

            // Add a Trigger in the correct group of phases in the list
            for (ListIterator /*<Object>*/ it = listIterator(); it.hasNext();) {
                Trigger t = convert(it.next());

                if (t == null) {
                    it.remove();
                } else if (trigger.phase() < t.phase()) {
                    // add it before
                    it.hasPrevious();
                    it.add(o);
                    return;
                } else if (trigger.phase() == t.phase()) {
                    // add it after
                    it.add(o);
                    return;
                }
            }
            super.add(o);
        }

        /**
         * Removes the given Trigger.
         *
         * <p/>In addition, removes any {@link WeakReference} that is empty.
         *
         * @param trigger
         */
        void remove(final Trigger trigger)
        {
            for (Iterator it = iterator(); it.hasNext();) {
                Trigger t = convert(it.next());

                if (t == null) {
                    it.remove();
                } else if (t.equals(trigger)) {
                    it.remove();
                }
            }
        }

        /**
         * Executes every {@link Trigger} in this {@link TriggerList}, passing
         * in the property key whose change was the casue.
         *
         * <p/>In addition, removes any {@link WeakReference} that is empty.
         *
         * <p>Synchronizes on {@code property} while modifying the trigger list.
         *
         * @param property The property whose change caused this property to
         * fire
         */
        void execute(AbstractProperty property, String value)
            throws Trigger.VetoRT
        {
            // Make a copy so that if during the execution of a trigger a
            // Trigger is added or removed, we do not get a concurrent
            // modification exception. We do an explicit copy (rather than
            // a clone) so that we can remove any WeakReference whose
            // content has become null. Synchronize, per the locking strategy,
            // while the copy is being made.
            List /*<Trigger>*/ l = new ArrayList /*<Trigger>*/();
            synchronized (property) {
                for (Iterator /*<Object>*/ it = iterator(); it.hasNext();) {
                    Trigger t = convert(it.next());
                    if (t == null) {
                        it.remove();
                    } else {
                        l.add(t);
                    }
                }
            }

            for (int i = 0; i < l.size(); i++) {
                Trigger t = (Trigger) l.get(i);
                t.execute(property, value);
            }
        }

        /**
         * Converts a trigger or a weak reference to a trigger into a trigger.
         * The result may be null.
         */
        private Trigger convert(Object o)
        {
            if (o instanceof WeakReference) {
                o = ((WeakReference) o).get();
            }
            return (Trigger) o;
        }
    }
}


