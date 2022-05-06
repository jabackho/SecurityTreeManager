package edu.ncsu.csc316.security_manager.tree;

import edu.ncsu.csc316.security_manager.attack.AttackStep;

/**
 * Tree of nodes containing attack step representing 
 * an attack tree.
 * @author jakebackhouse
 *
 */
//public class LinkedTree {

	
	/**
	 * Inner TreeNode class of the LinkedTree. 
	 * @author jakebackhouse
	 *
	 */
	public class TreeNode {
		
		/** A node's data */
		public AttackStep data;
		
		/** A node's parent node */
		public TreeNode parent;
		
		/** THe initial capacity for number of children */
		private static final int CHILDREN_CAP = 2;
		
		/** The current capacity for the number of children */
		private int capacity;
		
		/** A node's list of children nodes */
		public TreeNode[] children;
		
		/** The number of children in a node's list of children */
		public int numChildren;
		
		/**
		 * Constructs a TreeNode given it's data.
		 * @param data The node's data.
		 */
		public TreeNode(AttackStep data) {
			
			this.data = data;
			this.numChildren = 0;
			this.capacity = CHILDREN_CAP;
			children = new TreeNode[capacity];
			
		}
		
		/**
		 * Adds a child to the list of children a node has.
		 * @param child The child node to be added to the list.
		 */
		public void addChild(TreeNode child) {
			
			if(child == null) {
				return;
			}
			
			if(numChildren == capacity) {
				children = reSize();
			}
			this.children[numChildren] = child;
			this.numChildren++;
			
		}
		
		/**
		 * Called by a child node, the child node sets the given
		 * parent node as their parent.
		 * @param parent The parent node of the child to be set.
		 */
		public void assignParent(TreeNode parent) {

			parent.addChild(this);
			this.parent = parent;
			
		}
		
		/**
		 * Assigns the given child to the current node.
		 * @param child The child node to be assigned.
		 */
		public void assignChild(TreeNode child) {
			
			child.assignParent(this);
			
		}
		
		/**
		 * Gets a node's data.
		 * @return The node's data.
		 */
		public AttackStep getData() {
			
			return this.data;
			
		}
		
		/**
		 * Resizes the array of children nodes once
		 * it has reached capacity.
		 * @return The resized children array.
		 */
		public TreeNode[] reSize() {
			
			capacity *= 2;
			TreeNode[] newArray = new TreeNode[capacity];
			System.arraycopy(children, 0, newArray, 0, children.length);
			return newArray;
			
		}
		
		/**
		 * Determines the height of the attack tree.
		 * @param root The current root node.
		 * @return the height of the attack tree.
		 */
		public int height(TreeNode root) {
			int max = 0;
			if(root == null) {
				return 0;
			}
			int[] childHeights = new int[root.numChildren];
			for(int i = 0; i < root.numChildren; i++) {
				childHeights[i] = height(root.children[i]);
			}
			for(int i = 0; i < root.numChildren; i++) {
				if(childHeights[i] > max) {
					max = childHeights[i];
				}
			}
			return max + 1;
		}
		
	}

//}
