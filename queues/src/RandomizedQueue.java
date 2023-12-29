import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/*
	A randomized queue is similar to a stack or queue, except that the item removed is
	chosen uniformly at random among items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	Node head;
	Node tail;
	int size;
	
	private class Node {
		Item item;
		Node next;
		Node prev;
	}
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// is the randomized queue empty?
	public boolean isEmpty() {
		return head == null;
	}
	
	// return the number of items on the randomized queue
	public int size() {
		return size;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new IllegalArgumentException();
		if (isEmpty()) {
			head = new Node();
			head.item = item;
			head.next = null;
//			tail = head;
		} else {
			if (tail == null) {
				head.next = new Node();
				tail = head.next;
				tail.prev = head;
			} else {
				Node oldTail = tail;
				tail = new Node();
				tail.prev = oldTail;
				oldTail.next = tail;
			}
			tail.item = item;
			tail.next = null;
		}
		size++;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		Iterator<Item> removeIter = iterator();
		Item toBeRemoved = removeIter.next();
		Node lastVisited = ((RandomIterator) removeIter).getLastVisited();
		
		if (head != lastVisited) {
			lastVisited.prev.next = lastVisited.next;
			if (lastVisited.next != null) lastVisited.next.prev = lastVisited.prev;
			
		} else {
			
			Node oldHeadNext = null;
			if (size > 1) oldHeadNext = head.next;
			head = oldHeadNext;
		}
		size--;
		
		return toBeRemoved;
		
	}
	
	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException();
		Iterator<Item> sampleIter = iterator();
		return sampleIter.next();
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomIterator();
	}
	
	private class RandomIterator implements Iterator<Item> {
		Random rng = new Random();
		private Node current = head;
		private Node lastVisited = null;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			int randomNum = rng.nextInt(size);
			for (int i = 0; i < randomNum; i++) {
				current = current.next;
			}
			
			Item item = current.item;
			lastVisited = current;
			return item;
		}
		
		public Node getLastVisited() {
			return lastVisited;
		}
	}
	
	// unit testing (required)
	public static void main(String[] args) {
		RandomizedQueue<String> test2 = new RandomizedQueue<>();
//		test2.enqueue("0");
		System.out.println(test2.size());
		test2.enqueue("a");
		test2.enqueue("b");
		test2.enqueue("c");
		
		System.out.println(test2.size());
		int originalSize = test2.size();
		for (int i = 0; i < originalSize; i++) {
			System.out.println("Loop iteration i = " + i + " removed item: " + test2.dequeue());
			System.out.println("size after removal: " + test2.size());
		}
//		System.out.println(test2.head.item);
//		System.out.println("Loop iteration 1,  removed item: " + test2.dequeue());
//		System.out.println("size after removal: " + test2.size());
//		System.out.println("Loop iteration 2,  removed item: " + test2.dequeue());
//		System.out.println("size after removal: " + test2.size());
	}
	
}

