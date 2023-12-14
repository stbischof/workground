package org.eclipse.daanse.olap.impl;

import java.util.List;
import java.util.Map;

public interface NamedList<E> extends List<E> {
    /**
     * Retrieves a member by name.
     *
     * @param name name of the element to return
     *
     * @see #get(int)
     *
     * @return the element of the list with the specified name, or null if
     * there is no such element
     */
    E get(String name);

    /**
     * Returns the position where a member of a given name is found, or -1
     * if the member is not present.
     *
     * @param name name of the element to return
     *
     * @return the index of element of the list with the specified name, or -1
     * if there is no such element
     *
     * @see #indexOf(Object)
     */
    int indexOfName(String name);

    /**
     * Returns the name of a given element.
     *
     * @param element Element
     * @return Name of element
     */
    String getName(Object element);

    /**
     * Returns a view of this named list as a {@link Map} whose key is the name
     * of each element.
     *
     * @return A view of this named list as a map
     */
    Map<String, E> asMap();
}
