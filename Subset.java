import edu.princeton.cs.algs4.StdIn;

/**
 * Created by zhanlgu on 16/9/18.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
//        In in = new In(args[0]);
//        int k = in.readInt();
        RandomizedQueue<String> randomQ = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
//        while (!in.isEmpty()) {
            String item = StdIn.readString();
//            String item = in.readString();
            randomQ.enqueue(item);
        }
        if (k < 0 || k > randomQ.size())
            return;
        for (int i = 0; i < k; i++) {
            String s = randomQ.dequeue();
            System.out.println(s);
        }
    }
}
