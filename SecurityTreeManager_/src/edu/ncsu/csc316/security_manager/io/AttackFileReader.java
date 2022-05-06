package edu.ncsu.csc316.security_manager.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.security_manager.attack.AttackStep;
import edu.ncsu.csc316.security_manager.list.Queue;
import edu.ncsu.csc316.security_manager.tree.TreeNode;

/**
 * The AttackFileReader class is in charge of 
 * taking the input pre order and post order
 * traversal files and generating the correct Attack
 * Tree from them.
 * @author jakebackhouse
 *
 */
public class AttackFileReader {
	
	/** Queue for temporarily storing AttackSteps from the pre order traversal */
	private Queue preQueue = new Queue();
	
	/** Queue for temporarily storing AttackSteps from the post order traversal */
	private Queue postQueue = new Queue();
	
	/** Root node of the Attack Tree to be generated */
	private TreeNode root;
	
	/**
	 * Constructor for the AttackFileReader.
	 * Will create scanners for the pre and post order traversals and then
	 * read Attack Steps into the correct Queues.
	 * @param preOrderFile The file of the pre order traversal.
	 * @param postOrderFile The file of the post order traversal.
	 */
	public AttackFileReader(String preOrderFile, String postOrderFile) {
		
		/** Current line being read in */
		String line;
		
		/** Holds the split of the current line */
		String[] lineSplit;
		
		try(Scanner preScan = new Scanner(new FileInputStream(preOrderFile))){
			
			while(preScan.hasNextLine()) {
				StringBuffer temp = new StringBuffer();
				
				line = preScan.nextLine();
				lineSplit = line.split(" ");

				if(lineSplit[0].equals("GOAL")) {
					for(int j = 1; j < lineSplit.length; j++) {
						
						temp.append(" " + lineSplit[j]);
						
					}
					AttackStep tempAttackStep = new AttackStep(lineSplit[0], temp.toString());
					preQueue.add(tempAttackStep);
					
				} else {
					
					try{
						StringBuffer temp2 = new StringBuffer();
						for(int j = 4; j < lineSplit.length; j++) {
							temp2.append(" " + lineSplit[j]);
						}
						preQueue.add(new AttackStep(lineSplit[0], Double.parseDouble(lineSplit[1]), Double.parseDouble(lineSplit[2]), Double.parseDouble(lineSplit[3]), temp2.toString()));
					} catch (NumberFormatException e) {
						
						for(int j = 1; j < lineSplit.length; j++) {
							
							temp.append(" " + lineSplit[j]);
							
						}
											
						preQueue.add(new AttackStep(lineSplit[0], temp.toString()));
						
					}
					
				}
				
			}
		} catch (FileNotFoundException e) {
			AttackStep temp = new AttackStep();
			root = new TreeNode(temp);
		}

		try(Scanner postScan = new Scanner(new FileInputStream(postOrderFile))){
			while(postScan.hasNextLine()) {
				
				StringBuffer temp = new StringBuffer();

					
				temp = new StringBuffer();
					
				line = postScan.nextLine();
				lineSplit = line.split(" ");
					
				if(lineSplit[0].equals("GOAL")) {
					for(int j = 1; j < lineSplit.length; j++) {
							
						temp.append(" " + lineSplit[j]);
							
					}
					AttackStep tempAttackStep = new AttackStep(lineSplit[0], temp.toString());
					postQueue.add(tempAttackStep);
						
				} else {
						
					try{
						StringBuffer temp2 = new StringBuffer();
						for(int j = 4; j < lineSplit.length; j++) {
							temp2.append(" " + lineSplit[j]);
						}
						postQueue.add(new AttackStep(lineSplit[0], Double.parseDouble(lineSplit[1]), Double.parseDouble(lineSplit[2]), Double.parseDouble(lineSplit[3]), temp2.toString()));
					} catch (NumberFormatException e) {
						
						for(int j = 1; j < lineSplit.length; j++) {
								
							temp.append(" " + lineSplit[j]);
								
						}
												
						postQueue.add(new AttackStep(lineSplit[0], temp.toString()));
							
					}
						
				}
					
			}
		} catch (FileNotFoundException e) {
			AttackStep temp = new AttackStep();
			root = new TreeNode(temp);
		}
					
		root = buildAttackTree(preQueue, postQueue);
		
	}
	
	/**
	 * Returns the root of the generated Attack Tree.
	 * @return the root of the Attack Tree.
	 */
	public TreeNode getRoot() {
		return this.root;
	}
	
	/**
	 * Builds the attack tree given the pre and post queue of
	 * Attack Steps.
	 * @param pre The queue containing the pre order traversal.
	 * @param post The queue containing the post order traversal.
	 * @return The root of the generated Attack Tree.
	 */
	public TreeNode buildAttackTree(Queue pre, Queue post) {
		
		/** Base case */
		if(post.getSize() < 1) {
			return null;
			
		}
		
		/** Current root is the next Attack Step in the pre order traversal */
		TreeNode currentRoot = new TreeNode(pre.pop());

		/** Queue for holding the sub queue of post */
		Queue postNew = new Queue();
		
		/** Current size of post queue */
		int postSize = post.getSize();
		
		while(post.getSize() > 1 && pre.getSize() != 0) {
			
			
			for(int i = 0; i < postSize; i++) {
				
				/** If the next post Attack Step is equal to the next pre Attack Step */
				if((pre.peek().compareTo(post.peek()) == 0) ) {
					
					/** Remove from post and add to the sub post queue */
					postNew.add(post.pop());

					/** Since they are equal we have reached the end of the sub queue of post */
					break;
				
				/** If the next post Attack Step and the next pre Attack Step aren't equal */
				} else {
					
					/** Remove from post and add to the sub post queue */
					postNew.add(post.pop());
				
				}
			
			}
			
			/** Recursive call using the same pre queue and the sub post queue */
			currentRoot.assignChild(buildAttackTree(pre, postNew));
			
			/** Removes the front of the sub post queue */
			postNew.pop();
		
		}
		
		/** Return the current root as it has no more children to be added */
		return currentRoot;
		
	}
	
	/**
	 * Traverses the entire tree and calculates the probability, cost, and impact 
	 * of each node.
	 * @param root The current node whose values are being calculated.
	 */
	public void calculateValues(TreeNode root) {
		if(root == null) {
			return;
		}
		if(root.numChildren == 0) {
			return;
		}
		String[] types = new String[root.numChildren];
		double[] probabilities = new double[root.numChildren];
		double[] costs = new double[root.numChildren];
		double[] impacts = new double[root.numChildren];
		for(int i = 0; i < root.numChildren; i++) {
			calculateValues(root.children[i]);
			types[i] = root.children[i].getData().getType();
			probabilities[i] = root.children[i].getData().getProbability();
			costs[i] = root.children[i].getData().getCost();
			impacts[i] = root.children[i].getData().getImpact();
		}
		double rootProbability = 1;
		double rootCost = 0;
		double rootImpact = 1;
		double probabilitiesSum = 0;
		double maxImpact = 0;
		for(int i = 0; i < root.numChildren; i++) {
			if(types[i].equals("AND")) {
				rootProbability *= probabilities[i];
				rootCost += costs[i];
				rootImpact *= (10 - impacts[i]);
			} else {
				rootProbability *= (1 - probabilities[i]);
				rootCost += (probabilities[i] * costs[i]);
				probabilitiesSum += probabilities[i];
				if(impacts[i] > maxImpact) {
					maxImpact = impacts[i];
				}
			}
		}
		if(types[0].equals("AND")) {
			rootImpact = (Math.pow(10, root.numChildren) - rootImpact) / (Math.pow(10, root.numChildren - 1));
		} else {
			rootProbability = 1 - rootProbability;
			rootCost = rootCost / probabilitiesSum;
			rootImpact = maxImpact;
		}
		root.getData().setProbability(rootProbability);
		root.getData().setCost(rootCost);
		root.getData().setImpact(rootImpact);
	}
	
}
