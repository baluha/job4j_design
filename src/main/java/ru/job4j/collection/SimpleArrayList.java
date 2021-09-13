package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;
import java.util.function.BiPredicate;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow(container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        if (Objects.checkIndex(index, size) == -1) {
            throw new IndexOutOfBoundsException();
        }
        T val = container[index];
        container[index] = newValue;
        return val;
    }


    @Override
    public T remove(int index) {
        if (Objects.checkIndex(index, size) == -1) {
            throw new IndexOutOfBoundsException();
        }
        T val = container[index];
            System.arraycopy(container, index + 1,
                    container, index, size - index - 1);
            container[size - 1] = null;
        size--;
        modCount++;
        return val;
    }

    @Override
    public T get(int index) {
        if (Objects.checkIndex(index, size) == -1) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void grow(int newCapacity) {
        T[] newContainer = (T[]) new Object[newCapacity];
        System.arraycopy(container, 0, newContainer, 0, size);
        container = newContainer;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            final int exp = modCount;
            int position;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public T next() {
                if (modCount != exp) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[position++];
            }
        };
    }
}