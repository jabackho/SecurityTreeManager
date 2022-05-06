package edu.ncsu.csc316.security_manager.tree;

import edu.ncsu.csc316.security_manager.date.Date;
import edu.ncsu.csc316.security_manager.log.LogEntry;

/**
 * Array implementation of a binary search tree for
 * log entries.
 * @author jakebackhouse
 *
 */
public class LogTree {
	
	/** The array of log entries */
	LogEntry[] tree;
	
	/** The initial capacity of the array */
	private static final int INITIALCAPACITY = 10;
	
	/** The current capacity of the array */
	private int capacity;
	
	/**
	 * Constructs an empty log tree at an initial
	 * capacity of 50.
	 */
	public LogTree() {
		this.capacity = INITIALCAPACITY;
		tree = new LogEntry[capacity];
	}
	
	/**
	 * Inserts the given log entry into the correct
	 * location in the tree.
	 * @param data The log entry to be inserted.
	 * @param index The current index in the array 
	 * where the data will be attempted to be inserted.
	 */
	public void insert(LogEntry data, int index) {
		if(index >= capacity) {
			tree = reSize(index);
		}
		if(tree[index] == null) {
			tree[index] = data;
		} else if(data.getDate().compareToWithTime(tree[index].getDate()) == -1 || data.getDate().compareToWithTime(tree[index].getDate()) == 0){
			insert(data, (2 * index) + 1);
		} else if(data.getDate().compareToWithTime(tree[index].getDate()) == 1) {
			insert(data, (2 * index) + 2);
		}
	}
	
	/**
	 * Gets the current size of the array.
	 * @return The current size of the array.
	 */
	public int getSize() {
		return tree.length;
	}
	
	/**
	 * Gets the data at the given index.
	 * @param index The index of the desired data.
	 * @return The log entry at the given index.
	 */
	public LogEntry getByIndex(int index) {
		return tree[index];
	}
	
	/**
	 * Binary search for given date.
	 * @param date The date being searched for.
	 * @param index The current index in the tree.
	 * @return The string format of the log entry with matching date.
	 */
	public String search(Date date, int index) {
		StringBuffer buff = new StringBuffer();
		if(index >= tree.length) {
			return buff.toString();
		}
		if(tree[index] == null) {
			return buff.toString();
			
		} else if(date.compareTo(tree[index].getDate()) == 0) {
			if(date.compareToWithTime(tree[index].getDate()) == -1) {
				buff.append(search(date, (index * 2) + 1));
			} else {
				buff.append(search(date, (index * 2) + 2));
			}
			buff.append(tree[index].toString());
			buff.append("\n");
		} else if(date.compareToWithTime(tree[index].getDate()) == -1) {
			buff.append(search(date, ((index * 2) + 1)));
		} else if(date.compareToWithTime(tree[index].getDate()) == 1) {
			buff.append(search(date, ((index * 2) + 2)));
		}
		return buff.toString();
	}
		
	/**
	 * Resizes the array if it is at capacity.
	 * Doubles the capacity.
	 * @param index The requested index of the array.
	 * @return The resized array.
	 */
	public LogEntry[] reSize(int index) {
		
		while(index >= capacity) {
			capacity *= 2;
		}
		
		//capacity *= 2;
		LogEntry[] newArray = new LogEntry[capacity];
		System.arraycopy(tree, 0, newArray, 0, tree.length);
		return newArray;
		
	}

}
