import edu.princeton.cs.algs4.StdIn;

import static edu.princeton.cs.algs4.StdOut.println;

public class Permutation {

	public static void main(String[] args) {
		String cmdArg = args[0];
		
		if(cmdArg == null) {
			throw new IllegalArgumentException("'k' arg can't be 'null'");
		}
		
		int k = Integer.parseInt(cmdArg);
		
		if(k <= 0) {
			throw new IllegalArgumentException("'k' can't be <= 0");
		}
		
		println("Enter values");
		
		String[] userInput = StdIn.readAllStrings();
		
		
	}

}
