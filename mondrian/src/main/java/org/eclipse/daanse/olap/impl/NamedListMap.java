package org.eclipse.daanse.olap.impl;

import mondrian.util.Pair;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

class NamedListMap<T> extends AbstractMap<String, T> {
    private final NamedList<T> namedList;

    /**
     * Creates a NamedListMap.
     *
     * @param namedList Named list
     */
    public NamedListMap(NamedList<T> namedList) {
        this.namedList = namedList;
    }

    public Set<Entry<String, T>> entrySet() {
        return new AbstractSet<Entry<String, T>>() {
            public Iterator<Entry<String, T>> iterator() {
                final Iterator<T> iterator = namedList.iterator();
                return new Iterator<Entry<String, T>>() {
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    public Entry<String, T> next() {
                        T x = iterator.next();
                        String name = namedList.getName(x);
                        return new Pair<String, T>(name, x);
                    }

                    public void remove() {
                        iterator.remove();
                    }
                };
            }

            public int size() {
                return namedList.size();
            }
        };
    }
}
