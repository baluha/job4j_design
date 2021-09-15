package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;


    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (first == null && size == 0) {
            first = newNode;
        } else {
            last.prev.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> nodeRsl;
        if (index == 0) {
            return first.item;
        } else if (index == size - 1) {
            return last.item;
        } else {
            nodeRsl = first;
            for (int i = 0; i < size; i++) {
                nodeRsl = nodeRsl.next;
                if (i == index) {
                    return nodeRsl.item;
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {


        return new Iterator<>() {

            private int count = 0;
            final int sizeOfList = size;
            private Node<E> cursor = first;
            final int exp = modCount;

            @Override
            public boolean hasNext() {
                if (exp != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < sizeOfList;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = cursor.item;
                cursor = cursor.next;
                count++;
                return rsl;
            }
        };
    }


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }
}
