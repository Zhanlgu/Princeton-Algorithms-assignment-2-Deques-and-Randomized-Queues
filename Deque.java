import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by zhanlgu on 16/9/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    private class Node {
        private Item item;
        private Node previous = null;
        private Node next = null;
    }

    public boolean isEmpty() {
        // where I make mistakes
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (n == 0)
            last = first;
        else
            oldFirst.previous = first;
        n++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        if (n == 0)
            first = last;
        else
            oldLast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        Node oldFirst = first;
        first = first.next;
        oldFirst.previous = null;
        oldFirst.next = null;
        n--;
        if (isEmpty())
            last = null;
        else
            first.previous = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        Node oldLast = last;
        last = last.previous;
        // it is important to set the last.next to null for iterator
        oldLast.previous = null;
        oldLast.next = null;
        n--;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            // see if it is wrong
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        for (Integer i : deque)
            System.out.println(i);
        System.out.println("-----");
        deque.removeLast();
//        deque.addFirst("bb");
//        deque.addFirst("aa");
//        deque.addLast("cc");
//        deque.addLast("dd");
//        for (String i : deque)
//            System.out.println(i);
//        System.out.println("-----");
//        // check the independence of iterator
//        for (String i : deque)
//            for (String j : deque)
//                System.out.println(i + " " + j);
//        System.out.println("-----");
//        deque.removeFirst();
//        for (String i : deque)
//            System.out.println(i);
//        System.out.println("-----");
//        deque.removeLast();
//        for (String i : deque)
//                System.out.println(i);
//        System.out.println("-----");
//        System.out.println(deque.size());
//        deque.removeFirst();
//        deque.removeLast();
//        System.out.println(deque.size());
    }
}


