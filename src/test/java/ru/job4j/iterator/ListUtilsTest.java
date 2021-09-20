package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfterAtTheBeginning() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1, 0));
        ListUtils.addAfter(input, 0, 999);
        assertThat(Arrays.asList(5, 999, 4, 3, 2, 1, 0), Is.is(input));
    }

    @Test
    public void whenRemoving() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 1));
        Predicate<Integer> predicate = i -> i == 1;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(2, 3), Is.is(input));
    }

    @Test
    public void whenAnotherRemoving() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1));
        Predicate<Integer> predicate = i -> i == 1;
        ListUtils.removeIf(input, predicate);
        assertThat(Collections.emptyList(), Is.is(input));
    }

    @Test
    public void whenReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 1, 4, 1));
        Predicate<Integer> predicate = i -> i == 1;
        ListUtils.replaceIf(input, predicate, 100);
        assertThat(Arrays.asList(100, 2, 100, 4, 100), Is.is(input));
    }

    @Test
    public void whenReplaceAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 1, 4, 1, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(4, 5), Is.is(input));
    }
}