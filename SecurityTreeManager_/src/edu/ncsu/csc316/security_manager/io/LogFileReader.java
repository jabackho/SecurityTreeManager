package edu.ncsu.csc316.security_manager.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.security_manager.date.Date;
import edu.ncsu.csc316.security_manager.log.LogEntry;
import edu.ncsu.csc316.security_manager.tree.LogTree;

/**
 * The LogFileReader class is in charge of 
 * taking the input log entries file and generating
 * a binary search tree from it.
 * @author jakebackhouse
 *
 */
public class LogFileReader {
	
	/** The binary search tree that will contain the log entries */
	private LogTree tree;
	
	/**
	 * Constructor for the LogFileReader.
	 * Creates a scanner for the input log entry file and inserts them into
	 * the LogTree.
	 * @param logEntries The file name of the log entries.
	 */
	public LogFileReader(String logEntries) {
		
		try(Scanner logScanner = new Scanner(new FileInputStream(logEntries))){
			tree = new LogTree();
			String line;
			
			String[] lineSplit;
			
			LogEntry log;
			
			Date date;
			
			while(logScanner.hasNext()) {
				line = logScanner.nextLine();
				lineSplit = line.split(",");
				date = new Date(lineSplit[0]);
				log = new LogEntry(date, lineSplit[1], lineSplit[2]);
				tree.insert(log, 0);
			}
		} catch(FileNotFoundException e) {
			tree = new LogTree();
			e.printStackTrace();
		}		
		
	}
	
	/**
	 * Gets the tree of the log entries.
	 * @return the tree of the log entries.
	 */
	public LogTree getTree() {
		return tree;
	}

}
