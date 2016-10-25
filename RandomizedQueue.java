import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by zhanlgu on 16/9/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arrayQ;
    private int n;

    public RandomizedQueue() {
        n = 0;
        arrayQ = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = arrayQ[i];
        arrayQ = copy;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (n == arrayQ.length)
            resize(2 * arrayQ.length);
        arrayQ[n++] = item;
    }

    public Item dequeue() {
        if (n == 0)
            throw new java.util.NoSuchElementException();
        int i = StdRandom.uniform(n);
        Item item = arrayQ[i];
        if (i != (n -1)) {
            arrayQ[i] = arrayQ[--n];
            arrayQ[n] = null;
        } else
            arrayQ[--n] = null;
        if (n > 0 && n == arrayQ.length / 4)
            resize(arrayQ.length / 2);
        return item;
    }

    public Item sample() {
        if (n == 0)
            throw new java.util.NoSuchElementException();
        return arrayQ[StdRandom.uniform(n)];
    }

    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
        private int iterN;
        private Item[] copyRandomQ = (Item[]) new Object[n];

        public ListIterator(){
            iterN = n;
            for(int i = 0; i < n; i++)
                copyRandomQ[i] = arrayQ[i];
        }

        public boolean hasNext() {
            return iterN > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            // see if it is wrong
            int i = StdRandom.uniform(0, iterN);
            Item item = copyRandomQ[i];
            if(i != iterN - 1)
                copyRandomQ[i] = copyRandomQ[iterN -1];
            copyRandomQ[iterN - 1] = null;
            iterN--;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomQ = new RandomizedQueue<String>();
//        randomQ.enqueue(82);
//        if(randomQ.isEmpty()){
//            System.out.println("is empty.");
//        }
        randomQ.enqueue("A");
        randomQ.enqueue("B");
        randomQ.enqueue("C");
        randomQ.enqueue("D");
        for (String i : randomQ)
            System.out.println(i);
        System.out.println("-------");
        System.out.println("n = " + randomQ.size());
        String de = randomQ.dequeue();
        for (String i : randomQ)
            System.out.println(i);
        System.out.println("-------");
        System.out.println(de);
    }
}
