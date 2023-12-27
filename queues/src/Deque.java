/*
/
 Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack
 and a queue that supports adding and removing items from either the front or the back of
 the data structure. Create a generic data type Deque that implements the following API:
 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

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
	public int getSize() {
		return size;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if(item == null) throw new IllegalArgumentException();
		Node oldHead = head;
		head = new Node();
		head.item = item;
		head.next = oldHead;
		size++;
	}
	
	// add the item to the back
	public void addLast(Item item) {
		if(item == null) throw new IllegalArgumentException();
		Node newTail = new Node();
		newTail.item = item;
		newTail.next = null;
		tail = newTail;
		size++;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if(isEmpty()) throw new NoSuchElementException();
		Item removedHead = head.item;
		head = head.next;
		size--;
		return removedHead;
	}
	
	// remove and return the item from the back
	public Item removeLast() {
		if(isEmpty()) throw new NoSuchElementException();
		Iterator<Item> removalIterate = iterator();
		for(int i = 0; i<size-1; i++) {
			removalIterate.next();
		}
		Node lastVisited = ((ListIterator)	removalIterate).getLastVisited();
		lastVisited.next = null;
	  size--;
		return lastVisited.item;
	}
	
	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current = head;
		private Node lastVisited = null;
		public boolean hasNext() {
			return current != null;
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			lastVisited = current;
			current = current.next;
			return item;
		}
		
		public Node getLastVisited() { return lastVisited; }
	}
	
	// unit testing (required)
	public static void main(String[] args) {

		
	}
}
