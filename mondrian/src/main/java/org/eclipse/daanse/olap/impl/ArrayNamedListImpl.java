package org.eclipse.daanse.olap.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public abstract class ArrayNamedListImpl<T>
    extends ArrayList<T>
    implements NamedList<T>
{
    /**
     * Creates an empty list with the specified initial capacity.
     *
     * @param   initialCapacity   the initial capacity of the list
     * @exception IllegalArgumentException if the specified initial capacity
     *            is negative
     */
    public ArrayNamedListImpl(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates an empty list.
     */
    public ArrayNamedListImpl() {
        super();
    }

    /**
     * Creates a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public ArrayNamedListImpl(Collection<? extends T> c) {
        super(c);
    }

    public T get(String name) {
        for (T t : this) {
            if (getName(t).equals(name)) {
                return t;
            }
        }
        return null;
    }

    public int indexOfName(String name) {
        for (int i = 0; i < size(); ++i) {
            T t = get(i);
            if (getName(t).equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Map<String, T> asMap() {
        return new NamedListMap<T>(this);
    }
}
