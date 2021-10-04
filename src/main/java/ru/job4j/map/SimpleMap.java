package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private final float loadFactor = 0.75f;

    private static int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * loadFactor) {
            expand();
        }
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return  hashCode ^ (hashCode >>> 4);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] resizeTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> tmp = table[i];
                resizeTable[indexFor(hash(tmp.key.hashCode()))] = tmp;
            }
        }
        modCount++;
        table = resizeTable;
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> map = table[indexFor(hash(key.hashCode()))];
        V rsl = null;
        if (map != null && Objects.equals(map.key, key)) {
            rsl = map.value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        MapEntry<K, V> map = table[indexFor(hash(key.hashCode()))];
        boolean rsl = map != null && Objects.equals(map.key, key);
        if (map != null && Objects.equals(map.key, key)) {
            modCount++;
            table[indexFor(hash(key.hashCode()))] = null;
        }
        return rsl;
    }



    @Override
    public Iterator<K> iterator() {
        final int exp = modCount;

        Iterator<K> iterator = new Iterator<K>() {
            int cursor;
            @Override
            public boolean hasNext() {
                if (exp != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = cursor; i < table.length; i++, cursor++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
        return iterator;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key)
                    && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

}