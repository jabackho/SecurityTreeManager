package edu.ncsu.csc316.security_manager.list;

import edu.ncsu.csc316.security_manager.attack.AttackStep;

/**
 * Queue for holding attack steps in their pre and post
 * order traversals.
 * @author jakebackhouse
 * 
 */
public class Queue {
	
	/** Head node of the queue */
	private Node head;
	
	/** Tail node of the queue */
	private Node tail;
	
	/** Size of the queue */
	private int size;
	
	/**
	 * Inner Node class for the queue.
	 * Each node has data and a next node.
	 * @author jakebackhouse
	 *
	 */
	public class Node {
		
		/** A node's data */
		private AttackStep data;
		
		/** The next node */
		private Node next;
		
		/**
		 * Constructs a node given its data and 
		 * next node.
		 * @param data The node's data.
		 * @param next The next node.
		 */
		public Node(AttackStep data, Node next) {
			
			this.data = data;
			this.next = next;
			
		}
		
	}
	
	/**
	 * Constructs an empty queue. 
	 */
	public Queue() {
		
		this.head = null;
		this.tail = null;
		this.size = 0;
		
	}
	
	/**
	 * Adds the given data to the end of the queue.
	 * @param data The data to be added.
	 */
	public void add(AttackStep data) {
		
		/** Temporary node for the data to be added */
		Node temp = new Node(data, null);
		
		/** If head is null add it to the front */
		if(head == null) {
			
			head = temp;
			tail = head;
			size++;
			return;
			
		} else {
			
			tail.next = temp;
			tail = temp;
			size++;
			return;
			
		}
		
	}
	
	/**
	 * Gets the data at the front of the queue and
	 * then removes it.
	 * @return The data at the front of the queue.
	 */
	public AttackStep pop() {
		if(head == null) {
			return null;
		}
		Node temp = head;
		head = head.next;
		size--;
		return temp.data;
	}
	
	/**
	 * Checks the data at the front of the queue but
	 * does not remove it.
	 * @return The data at the front of the queue.
	 */
	public AttackStep peek() {
		if(head == null) {
			return null;
		}
		return head.data;
	}
	
	/**
	 * Gets the size of the queue.
	 * @return The size of the queue.
	 */
	public int getSize() {
		return this.size;
	}

}
