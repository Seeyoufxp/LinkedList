package love.seeyoufxp.singlelinkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

public class SinglyLinkedList implements Iterable<Integer> {
    private Node head = new Node(666, null);

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value) throws IllegalAccessException {
        insert(0, value);
    }

    public void loop(Consumer<Integer> consumer) {
        Node p = head.next;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    public void loop3(Consumer<Integer> before, Consumer<Integer> after) {
        recursion(head.next, before, after);
    }

    private void recursion(Node curr,
                           Consumer<Integer> before, Consumer<Integer> after) {
        if (curr == null) {
            return;
        }
        before.accept(curr.value);
        recursion(curr.next, before, after);
        after.accept(curr.value);
    }

    private Node findLast() {
        Node p;
        for (p = head; p.next != null; p = p.next) {
        }
        ;
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();
        last.next = new Node(value, null);
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public int get(int index) throws IllegalAccessException {
        Node node = findNode(index);
        if (node == null) {
            throw illegalIndex(index);
        }
        return node.value;
    }

    public void insert(int index, int value) throws IllegalAccessException {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        prev.next = new Node(value, prev.next);
    }

    private static IllegalAccessException illegalIndex(int index) {
        return new IllegalAccessException(
                String.format("index [%d] 不合法", index));
    }

    public void removeFirst() throws IllegalAccessException {
        remove(0);
    }

    public void remove(int index) throws IllegalAccessException {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node removed = prev.next;
        if (removed == null) {
            throw illegalIndex(index);
        }
        prev.next = removed.next;
    }
}




