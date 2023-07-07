import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        String cmdArg = args[0];

        if (cmdArg == null) {
            throw new IllegalArgumentException("'k' arg can't be 'null'");
        }

        int k = Integer.parseInt(cmdArg);

        if (k < 0) {
            throw new IllegalArgumentException("'k' can't be < 0");
        }

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String userInput = StdIn.readString();

            queue.enqueue(userInput);
        }

        Iterator<String> iterator = queue.iterator();

        int poppedItemsCount = 0;

        while (iterator.hasNext() && poppedItemsCount < k) {
            String nextRandomItem = iterator.next();

            StdOut.println(nextRandomItem);

            poppedItemsCount++;
        }
    }

}
