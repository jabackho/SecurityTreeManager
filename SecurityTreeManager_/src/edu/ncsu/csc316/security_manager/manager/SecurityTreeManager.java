package edu.ncsu.csc316.security_manager.manager;

import edu.ncsu.csc316.security_manager.date.Date;
import edu.ncsu.csc316.security_manager.io.AttackFileReader;
import edu.ncsu.csc316.security_manager.io.LogFileReader;
import edu.ncsu.csc316.security_manager.tree.TreeNode;
import edu.ncsu.csc316.security_manager.tree.LogTree;

/**
 * The SecurityTreeManager class is able to build an attack tree
 * given pre and post order traversal files or generate log entries
 * in a binary search tree format.
 * @author jakebackhouse
 *
 */
public class SecurityTreeManager {
	
	/** Used to read in attack steps into the attack tree */
	private AttackFileReader attackReader;
	
	/** Used to read in log entries into the binary search tree */
	private LogFileReader logReader;
	
	/** Root node of the Attack Tree */
	private TreeNode root;
	
	/** Binary Search Tree of log entries */
	private LogTree logEntries;

	/**
	 * Constructs a new SecurityTreeManager object with the given paths 
	 * to the preOrder and postOrder traversal files.
	 * @param preOrder - the path to the preOrder traversal file
	 * @param postOrder - the path to the postOrder traversal file
	 */
	public SecurityTreeManager(String preOrder, String postOrder)
	{
		this.attackReader = new AttackFileReader(preOrder.trim(), postOrder.trim());

		this.root = attackReader.getRoot();
		
		this.propagateValues();
	}
	
	/**
	 * Constructs a new SecurityTreeManager object with the given path
	 * to the file that contains the log entries.
	 * @param filePath - the path to the log entry file
	 */
	public SecurityTreeManager(String filePath)
	{
		this.logReader = new LogFileReader(filePath);
		this.logEntries = logReader.getTree();
	}
	
	/**
	 * Returns the level order traversal of the Attack Tree
	 * as a string in the format (where each "node" is indented 3 spaces):
	 * 
	 * LevelOrder[
	 *    GOAL Step[Use DDoS Attack to Disrupt All Users, C=0.00, P=0.000, I=0.00]
	 *    OR Step[Attack Servers, C=0.00, P=0.000, I=0.00]
	 *    OR Step[Attack Comm Infrastructure, C=0.00, P=0.000, I=0.00]
	 * ]
	 *
	 * THE LEVEL ORDER TRAVERSAL MUST NOT RETURN ANY OF THE PROPAGATED VALUES!
	 * Why? So you can earn credit for having a correct traversal,
	 * even if you have incorrect functions for propagating values.
	 * 
	 * @return the level order traversal (as a string) of the attack tree
	 */
	public String getAttackTreeLevelOrder()
	{
		StringBuffer buff = new StringBuffer();
		buff.append("LevelOrder[");
		if(root == null) {
			buff.append("\n]");
			return buff.toString();
		} else {
			int h = root.height(root);
			for(int i = 1; i <= h; i++) {
				buff.append(getLevel(root, i));
			}
			buff.append("\n]");
			return buff.toString();
		}
	}
	
	/**
	 * Gets all nodes and the given level in String format.
	 * @param root The root of the tree.
	 * @param level The desired level of nodes.
	 * @return A string of all nodes in the given level.
	 */
	public String getLevel(TreeNode root, int level) {
		
		StringBuffer buff = new StringBuffer();
		
		/** Base Case */
		if(root == null) {
			return null;
		}
		
		if(level == 1) {
			buff.append("\n   ");
			buff.append(root.data.toString());
			return buff.toString();
		} else if (level > 1) {
			/** Recursive call with the children nodes */
			for(int i = 0; i < root.numChildren; i++) {
				buff.append(getLevel(root.children[i], level - 1));
			}
		}
		return buff.toString();
	}
	
	/**
	 * Return the level order traversal of the Log Tree
	 * as a string in the format (where each "node" is indented 3 spaces):
	 * 
	 * LevelOrder[
	 *    LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]
	 *    LogEntry[timestamp=2012/12/18 16:25:58, user=user18, description=view diagnoses]
	 *    LogEntry[timestamp=2016/12/12 06:28:13, user=user6, description=edit patient representative list]
	 * ]
	 * 
	 * @return the level order traversal (as a string) of the log tree
	 */
	public String getLogTreeLevelOrder()
	{
		StringBuffer buff = new StringBuffer();
		buff.append("LevelOrder[\n");
		for(int i = 0; i < logEntries.getSize(); i++) {
			if(logEntries.getByIndex(i) != null) {
				buff.append("   " + logEntries.getByIndex(i).toString() + "\n");
			}
		}
		buff.append("]");
		
		return buff.toString();
	}
	
	/**
	 * Returns the values (as a string) propagated to the root node
	 * using the formulas from the project writeup.
	 * For example:
	 * GOAL Step[Use DDoS Attack to Disrupt All Users, C=21557.12, P=0.878, I=8.00]
	 * 
	 * @return the metric values (as a string) that are propagated to the root node
	 */
	public String propagateValues()
	{
		
		attackReader.calculateValues(this.root);
		
		if(root == null) {
			return "";
		} else {
			return this.root.getData().toString();
		}
	}
	
	/**
	 * Gets the string format of the root node.
	 * @return the string format of the root node.
	 */
	public String getRoot() {
		return root.getData().toString();
	}
	
	/**
	 * Returns (as a string, sorted in increasing order) the log entries 
	 * for the given date in the format:
	 * 
	 * LogEntry[timestamp=2015/07/17 15:49:38, user=user4, description=print calendar]
	 * LogEntry[timestamp=2015/07/17 15:55:25, user=user8, description=save immunizations]
	 * 
	 * @param date - the date, in the format MM-DD-YYYY
	 * @return the string representation of the log entries for the specified date
	 */
	public String getLogEntriesForDate(String date)
	{
		Date temp = new Date(date);
		return logEntries.search(temp, 0);
	}
	
}


