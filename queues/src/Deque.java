/*
/
 Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack
 and a queue that supports adding and removing items from either the front or the back of
 the data structure. Create a generic data type Deque that implements the following API:
 
 */

public class Deque<Item> implements Iterable<Item> {
	Node head;
	Node tail;
	int size;
	
	private class Node {
		Item item;
		Node next;
	}
	
	// construct an empty deque
	public Deque() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return head == null;
	}
	
	// return the number of items on the deque
	public int size() {
		return size;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		// Throw an IllegalArgumentException if the client calls either
		// addFirst() or addLast() with a null argument.
		Node oldHead = head;
		head = new Node();
		head.item = item;
		head.next = oldHead;
		size++;
		
	}
	
	// add the item to the back
	public void addLast(Item item) {
		// Throw an IllegalArgumentException if the client calls either
		// addFirst() or addLast() with a null argument.
		Node newTail = new Node();
		newTail.item = item;
		newTail.next = null;
		tail = newTail;
		size++;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		//	 Throw a java.util.NoSuchElementException if the client calls either
		//	 removeFirst() or removeLast when the deque is empty.
		Item removedHead = head.item;
		head = head.next;
		size--;
		return removedHead;
	}
	
	// remove and return the item from the back
	public Item removeLast() {
		//	 Throw a java.util.NoSuchElementException if the client calls either
		//	 removeFirst() or removeLast when the deque is empty.
	}
	
	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
	
	}
	
	// unit testing (required)
	public static void main(String[] args) {
	
	}
}
