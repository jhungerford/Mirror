package dev.mirror.precondition;

import com.google.common.base.Strings;

import java.util.Collection;

public class Preconditions {

    /**
     * Throws a PreconditionFailedException if the given thing is null, or returns the non-null thing.
     *
     * @param thing Thing to check
     * @param name Name of the thing to include in the error message
     * @throws PreconditionFailedException if the object is null
     * @return Non-null thing
     */
    public static <T> T checkNotNull(T thing, String name) {
        if (thing == null) {
            throw new PreconditionFailedException(name + " cannot be null");
        }

        return thing;
    }

    /**
     * Throws a PreconditionFailedException if the given collection is null or empty.  Returns the collection
     * if it has at least one element
     *
     * @param collection Collection to check
     * @param name Name of the collection to include in the error message.
     * @throws PreconditionFailedException if the collection is null or empty
     * @return Collection with at least one element
     */
    public static <T, C extends Collection<T>> C checkNotEmpty(C collection, String name) {
        if (collection == null || collection.isEmpty()) {
            throw new PreconditionFailedException(name + " cannot be empty");
        }

        return collection;
    }

    /**
     * Throws a PreconditionFailedException if the given string is not null or empty.
     * @param string String to check
     * @param name Name of the string to include in the error message
     * @throws PreconditionFailedException if the string is null or empty
     * @return Non-empty string
     */
    public static String checkNotEmpty(String string, String name) {
        if (Strings.isNullOrEmpty(string)) {
            throw new PreconditionFailedException(name + " cannot be empty");
        }

        return string;
    }
}
