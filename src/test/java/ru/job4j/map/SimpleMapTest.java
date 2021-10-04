package ru.job4j.map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    @Test
    public void testPut() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("abcd", "abc"));
        assertFalse(simpleMap.put("abcd", "abc"));
        assertTrue(simpleMap.put("a", "ac"));
        assertTrue(simpleMap.put("b", "ac"));
        assertFalse(simpleMap.put("a", "123"));
        assertFalse(simpleMap.put("b", "123"));
    }

    @Test
    public void testPut2() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        String a = "abc";
        for (int i = 0; i < 60; i++) {
            assertTrue(simpleMap.put(i, a));
        }
        for (int i = 0; i < 60; i++) {
            assertFalse(simpleMap.put(i, a));
        }
    }

    @Test
    public void testGet() {
        SimpleMap<String, String> simpleMap = new SimpleMap<>();
        simpleMap.put("12", "123");
        simpleMap.put("a", "345");
        assertThat(simpleMap.get("a"), is("345"));
        assertThat(simpleMap.get("12"), is("123"));
        assertNull(simpleMap.get("b"));
    }

    @Test
    public void testGet2() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(0, "abc");
        simpleMap.put(1, "aaa");
        simpleMap.put(0, "bbb");
        assertNull(simpleMap.get(2));
        assertThat(simpleMap.get(0), is("abc"));
    }

    @Test
    public void testRemove() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(0, "abc");
        simpleMap.put(1, "abc");
        assertTrue(simpleMap.remove(0));
        assertFalse(simpleMap.remove(0));
    }

    @Test
    public void testRemove2() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(10, "abc");
        assertFalse(simpleMap.remove(0));
    }

    @Test
    public void testIterator() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(0, "123");
        simpleMap.put(1, "223");
        Iterator<Integer> iterator = simpleMap.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(0));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIterator2() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Iterator<Integer> iterator = simpleMap.iterator();
        simpleMap.put(0, "123");
        simpleMap.put(1, "223");
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(0));
        simpleMap.put(2, "223");
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        simpleMap.put(7, "123");
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(7));
        simpleMap.put(3, "123");
        assertTrue(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIterator3() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Iterator<Integer> iterator = simpleMap.iterator();
        iterator.next();
    }
}