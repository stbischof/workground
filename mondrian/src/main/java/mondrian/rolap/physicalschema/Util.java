package mondrian.rolap.physicalschema;

import mondrian.resource.MondrianResource;

public class Util {

    /**
     * Computes a hash code from an existing hash code and one or more objects
     * (any of which may be null).
     *
     * @param h Existing hash code
     * @param a Array of zero or more arguments
     * @return Hash code of h and each object in array
     */
    public static int hashV(int h, Object... a) {
        return hashArray(h, a);
    }

    /**
     * Computes a hash code from an existing hash code and an array of objects
     * (which may be null).
     *
     * @param h Existing hash code
     * @param a Array of arguments
     * @return Hash code of h and each object in array
     */
    public static int hashArray(int h, Object[] a) {
        // The hashcode for a null array and an empty array should be different
        // than h, so use magic numbers.
        if (a == null) {
            return hash(h, 19690429);
        }
        if (a.length == 0) {
            return hash(h, 19690721);
        }
        for (Object anA : a) {
            h = hash(h, anA);
        }
        return h;
    }

    /**
     * Computes a hash code from an existing hash code and an object (which
     * may be null).
     */
    public static int hash(int h, Object o) {
        int k = (o == null) ? 0 : o.hashCode();
        return ((h << 4) | h) ^ k;
    }

    /**
     * Combines two integers into a hash code.
     */
    public static int hash(int i, int j) {
        return (i << 4) ^ j;
    }

    /**
     * Insert a call to this method if you want to flag a piece of
     * undesirable code.
     *
     * @deprecated
     */
    public static <T> T deprecated(T reason, boolean fail) {
        if (fail) {
            throw new UnsupportedOperationException(String.valueOf(reason));
        } else {
            return reason;
        }
    }

    /**
     * Returns an exception indicating that we didn't expect to find this value
     * here.
     *
     * @param value Value
     */
    public static RuntimeException unexpected(Enum value) {
        return mondrian.olap.Util.newInternal(
            "Was not expecting value '" + value
                + "' for enumeration '" + value.getClass().getName()
                + "' in this context");
    }

    /**
     * Computes <a href="http://en.wikipedia.org/wiki/Julian_day">Julian Day
     * Number</a>.</p>
     *
     * @param year Year
     * @param month Month
     * @param day Date
     * @return Julian Day Number (JDN)
     */
    public static long julian(long year, long month, long day) {
        long a = (14 - month) / 12;
        long y = year + 4800 - a;
        long m = month + 12 * a - 3;
        return day
            + (153 * m + 2) / 5
            + 365 * y
            + y / 4
            - y / 100
            + y / 400
            - 32045;
    }

    /**
     * Returns true if two objects are equal, or are both null.
     *
     * @param s First object
     * @param t Second object
     * @return Whether objects are equal or both null
     */
    public static boolean equals(Object s, Object t) {
        if (s == t) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.equals(t);
    }

    /**
     * Creates a non-internal error. Currently implemented in terms of
     * internal errors, but later we will create resourced messages.
     */
    public static RuntimeException newError(String message) {
        return newInternal(message);
    }

    /**
     * Creates an internal error with a given message.
     */
    public static RuntimeException newInternal(String message) {
        return MondrianResource.instance().Internal.ex(message);
    }

    /**
     * Looks up an enumeration by name, returning null if null or not valid.
     *
     * @param clazz Enumerated type
     * @param name Name of constant
     */
    public static <E extends Enum<E>> E lookup(Class<E> clazz, String name) {
        return lookup(clazz, name, null);
    }

    /**
     * Looks up an enumeration by name, returning a given default value if null
     * or not valid.
     *
     * @param clazz Enumerated type
     * @param name Name of constant
     * @param defaultValue Default value if constant is not found
     * @return Value, or null if name is null or value does not exist
     */
    public static <E extends Enum<E>> E lookup(
        Class<E> clazz,
        String name,
        E defaultValue)
    {
        if (name == null) {
            return defaultValue;
        }
        try {
            return Enum.valueOf(clazz, name);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    /**
     * Function that takes zero arguments and returns {@code RT}.
     *
     * @param <RT> Return type
     */
    public static interface Function0<RT> {
        RT apply();
    }

    /**
     * Function that takes one argument ({@code PT}) and returns {@code RT}.
     *
     * @param <RT> Return type
     * @param <PT> Parameter type
     */
    public static interface Function1<PT, RT> {
        RT apply(PT param);
    }

    /**
     * Returns the first argument that is not null.
     *
     * <p>You can use this method to provide defaults, e.g.
     * {@code first(foo.name, "anonymous")}.</p>
     *
     * @param s0 Argument one
     * @param s1 Argument two
     * @param <T> Type of arguments and result
     * @return First argument that is not null.
     */
    public static <T> T first(T s0, T s1) {
        if (s0 != null) {
            return s0;
        }
        return s1;
    }
}
