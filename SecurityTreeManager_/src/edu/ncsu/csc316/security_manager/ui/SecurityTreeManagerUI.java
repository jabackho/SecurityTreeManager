package edu.ncsu.csc316.security_manager.ui;

import java.util.Scanner;

import edu.ncsu.csc316.security_manager.manager.SecurityTreeManager;

/**
 * Manages the user input functionalilty of the SecurityTreeManager.
 * @author jakebackhouse
 *
 */
public class SecurityTreeManagerUI {
	
	/** The singleton instance of the SecurityTreeManager */
	public static SecurityTreeManager manager;
	
	/** The file name for the log entries */
	public static String logEntriesFile;
	
	/** The file name for the pre order traversal */
	public static String preOrderTreeFile;
	
	/** The file name for the post order traversal */
	public static String postOrderTreeFile;
	
	/**
	 * Program starting point. Handles all user interactions
	 * and responds to user commands. 
	 * @param args Command line arguments.
	 */
	public static void main(String [] args) {
		
		Scanner input = new Scanner(System.in);
		
		boolean exit = false;
		boolean loop = false;
		String command;
		
		System.out.println("Choose command: AttackTree, LogFiles, Exit");
		
		while(!exit) {
			
			command = input.nextLine();
			
			if(command.equals("AttackTree")) {
				
				do {
					loop = false;
				
					System.out.println("Enter Preorder File");
					preOrderTreeFile = input.nextLine();
				
					System.out.println("Enter Postorder File");
					postOrderTreeFile = input.nextLine();
				
					//try{
					manager = new SecurityTreeManager(preOrderTreeFile, postOrderTreeFile);
					/**
					} catch(FileNotFoundException e) {
						System.out.println("One of the files does not exist. Please enter new files.");
						loop = true;
					}
					*/
				} while(loop);
				
				while(!exit) {
					System.out.println("Choose command: GoalSummary, LevelOrder, Exit");
					command = input.nextLine();
					if(command.equals("GoalSummary")) {
						System.out.println(manager.getRoot());
					} else if(command.equals("LevelOrder")) {
						System.out.println(manager.getAttackTreeLevelOrder());
					} else if(command.equals("Exit")) {
						exit = true;
					} else {
						System.out.println("Invalid command.");
					}
				}
				
			} else if(command.equals("LogFiles")) {
				
				do{
					
					loop = false;
				
					System.out.println("Enter LogEntries File");
				
					logEntriesFile = input.nextLine();
				
					//try{
						manager = new SecurityTreeManager(logEntriesFile);
					//} catch(FileNotFoundException e) {
						//System.out.println("Invalid File. Please enter new file.");
						//loop = true;
					//}
					
				} while(loop);
				boolean valid = false;
				while(!exit) {
					System.out.println("Choose command: GetLogByDate, LevelOrder, Exit");
					command = input.nextLine();
						if(command.equals("GetLogByDate")) {
							while(!valid) {
								System.out.println("Enter date in the format: MM-DD-YYYY");
								command = input.nextLine();
								String[] commandSplit = command.split("-");
								if(commandSplit.length != 3) {
									System.out.println("Invalid date. Please enter date in the format: MM-DD-YYY");
								} else {
									System.out.println(manager.getLogEntriesForDate(command));
									valid = true;
								}
							}
					} else if(command.equals("LevelOrder")) {
						System.out.println(manager.getLogTreeLevelOrder());
					} else if(command.equals("Exit")) {
						exit = true;
					} else {
						System.out.println("Invalid Command.");
					}
				}
				
			} else if(command.equals("Exit")) {
				exit = true;
			} else {
				System.out.println("Invalid Command");
			}
			
		}
		
		input.close();
		
	}

}
