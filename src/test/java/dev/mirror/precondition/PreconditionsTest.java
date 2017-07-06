package dev.mirror.precondition;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionsTest {

    @Test(expected = PreconditionFailedException.class)
    public void nullCollectionIsEmpty() {
        try {
            Collection<String> collection = null;
            Preconditions.checkNotEmpty(collection, "names");
        } catch (PreconditionFailedException ex) {
            assertThat(ex.getMessage()).contains("names");
            throw ex;
        }
    }

    @Test(expected = PreconditionFailedException.class)
    public void emptyCollectionIsEmpty() {
        try {
            Preconditions.checkNotEmpty(Collections.emptyList(), "names");
        } catch (PreconditionFailedException ex) {
            assertThat(ex.getMessage()).contains("names");
            throw ex;
        }
    }

    @Test
    public void collectionWithOneElementIsEmpty() {
        ImmutableList<String> list = Preconditions.checkNotEmpty(ImmutableList.of("apple"), "names");
        assertThat(list).containsExactly("apple");
    }

    @Test(expected = PreconditionFailedException.class)
    public void nullObjectIsNull() {
        try {
            Preconditions.checkNotNull(null, "name");
        } catch (PreconditionFailedException ex) {
            assertThat(ex.getMessage()).contains("name");
            throw ex;
        }
    }

    @Test
    public void emptyStringIsNotNull() {
        assertThat(Preconditions.checkNotNull("", "name")).isEqualTo("");
    }

    @Test
    public void stringIsNotNull() {
        assertThat(Preconditions.checkNotNull("apple", "name")).isEqualTo("apple");
    }

    @Test(expected = PreconditionFailedException.class)
    public void emptyStringIsNotEmpty() {
        try {
            Preconditions.checkNotEmpty("", "name");
        } catch (PreconditionFailedException ex) {
            assertThat(ex.getMessage()).contains("name");
            throw ex;
        }
    }

    @Test(expected = PreconditionFailedException.class)
    public void nullStringIsNotEmpty() {
        try {
            String string = null;
            Preconditions.checkNotEmpty(string, "name");
        } catch (PreconditionFailedException ex) {
            assertThat(ex.getMessage()).contains("name");
            throw ex;
        }
    }

    @Test
    public void nonEmptyStringIsNotEmpty() {
        assertThat(Preconditions.checkNotEmpty("apple", "name")).isEqualTo("apple");
    }
}