import edu.princeton.cs.algs4.StdIn;
/*
Write a client program Permutation.java that takes an integer k as a command-line argument;
reads a sequence of strings from standard input using StdIn.readString(); and
prints exactly k of them, uniformly at random. Print each item from the sequence at most once.
 */
public class Permutation {
	public static void main(String[] args) {
		// check for 1 int, e.g len 1.
		if(args.length != 1) throw new IllegalArgumentException();
		
		// convert string to int.
		int permNum = Integer.parseInt(args[0]);
		
		RandomizedQueue<String> rndStringQueue = new RandomizedQueue<>();
		
		while(!StdIn.isEmpty()) {
			String temp1 = StdIn.readString();
			rndStringQueue.enqueue(temp1);
			}
		
		for(int i = 0; i<permNum; i++) {
			System.out.println(rndStringQueue.dequeue());
		}
		
		
	}
}
