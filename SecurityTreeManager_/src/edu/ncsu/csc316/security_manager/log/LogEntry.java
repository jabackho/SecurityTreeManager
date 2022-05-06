package edu.ncsu.csc316.security_manager.log;

import edu.ncsu.csc316.security_manager.date.Date;

/**
 * Represents a log entry from the activity log.
 * @author jakebackhouse
 *
 */
public class LogEntry {
	
	/** The date of the log entry */
	private Date date;
	
	/** The user in the log entry */
	private String user;
	
	/** The description of the log entry */
	private String description;
	
	/**
	 * Constructs a log entry given its date, user, and description.
	 * @param date The date of the log entry.
	 * @param user The user of the log entry.
	 * @param description The description of the log entry.
	 */
	public LogEntry(Date date, String user, String description) {
		this.date = date;
		this.user = user;
		this.description = description;
	}
	
	/**
	 * Gets the date of the log entry.
	 * @return the date of the log entry.
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Gets the string format of a log entry.
	 * @return the string format of a log entry.
	 */
	@Override
	public String toString() {
		return "LogEntry[timestamp=" + date.toString() + ", user=" + user + ", description=" + description + "]";
	}

}
