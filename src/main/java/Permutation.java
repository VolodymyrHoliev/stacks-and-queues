import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

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

        if (k == 0) {
            return;
        }

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        int n = 0;

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();

            n++;

            if (queue.size() < k) {
                queue.enqueue(input);
            } else {
                double probability = (double) k / n;

                boolean addItem = StdRandom.bernoulli(probability);

                if (addItem) {
                    queue.dequeue();

                    queue.enqueue(input);
                }
            }
        }

        Iterator<String> iterator = queue.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();

            System.out.println(next);
        }
    }

}
