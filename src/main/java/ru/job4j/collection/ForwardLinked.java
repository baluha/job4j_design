package ru.job4j.collection;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public void addFirst(T value) {
        Node<T> newNode = new Node(value, null);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> newNode = head.next;
        head.next = null;
        T oldVal = head.value;
        head = newNode;
        size--;
        return oldVal;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean revert() {
        int count = 1;
        if (size <= 1) {
            return false;
        }
        Node<T> a = head;
        Node<T> b = head.next;
        Node<T> c;
        for (int i = count; i < size; i++) {
            c = head;
            head = b;
            b = b.next;
            head.next = c;
        }
        a.next = null;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}