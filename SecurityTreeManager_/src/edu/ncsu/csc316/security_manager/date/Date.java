package edu.ncsu.csc316.security_manager.date;

/**
 * Date object containing the day, month, year, and timestamp.
 * @author jakebackhouse
 *
 */
public class Date implements Comparable<Date> {
	
	/** The month of the date */
	private int month;
	
	/** The day of the date */
	private int day;
	
	/** The year of the date */
	private int year;
	
	/** The hour of the timestamp */
	private int hour;
	
	/** The minute of the timestamp */
	private int minute;
	
	/** The second of the timestamp */
	private int second;
	
	/** Holds the split input line */
	private String[] lineSplit;
	
	/** Holds the split date line */
	private String[] dateSplit;
	
	/** Holds the split time line */
	private String[] timeSplit;
	
	/**
	 * Constructs a date object given an input line from the input file.
	 * @param date The string containing the input line.
	 */
	public Date(String date) {
		lineSplit = date.split(" ");
		dateSplit = lineSplit[0].split("-");
		if(lineSplit.length > 1) {
			timeSplit = lineSplit[1].split(":");
			this.hour = Integer.parseInt(timeSplit[0]);
			this.minute = Integer.parseInt(timeSplit[1]);
			this.second = Integer.parseInt(timeSplit[2]);
		}
		this.month = Integer.parseInt(dateSplit[0]);
		this.day = Integer.parseInt(dateSplit[1]);
		this.year = Integer.parseInt(dateSplit[2]);
	}
	
	/**
	 * Gets the hour of the date.
	 * @return the hour of the date.
	 */
	public int getHour() {
		return this.hour;
	}
	
	/**
	 * Gets the minute of the date.
	 * @return the minute of the date.
	 */
	public int getMinute() {
		return this.minute;
	}
	
	/**
	 * Gets the second of the date.
	 * @return the second of the date.
	 */
	public int getSecond() {
		return this.second;
	}
	
	/**
	 * Gets the month of the date.
	 * @return the month of the date.
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * Gets the day of the date.
	 * @return the day of the date.
	 */
	public int getDay() {
		return this.day;
	}
	
	/**
	 * Gets the year of the date.
	 * @return the year of the date.
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Gets the string format of the date.
	 * @return the string format of the date.
	 */
	@Override
	public String toString() {
		return "" + year + "/" + String.format("%02d", month) + "/" + String.format("%02d", day) + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
	}

	/**
	 * Compares two date objects. Compares the year, month, and day.
	 * @return 0 if they are equal, -1 if the one calling compareTo is less than, and 1 if the 
	 * one calling the compareTo is greater than.
	 */
	@Override
	public int compareTo(Date o) {
		if(this.getMonth() == o.getMonth() && this.getDay() == o.getDay() && this.getYear() == o.getYear()) {
			return 0;
		} else if(this.getYear() < o.getYear()){
			return -1;
		} else if(this.getYear() == o.getYear() && this.getMonth() < o.getMonth()) {
			return -1;
		} else if(this.getYear() == o.getYear() && this.getMonth() == o.getMonth() && this.getDay() < o.getDay()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	/**
	 * Compares two date objects. Compares the year, month, day, hour, minute, and second.
	 * @param o The date to be compared to.
	 * @return 0 if they are equal, -1 if the one calling compareTo is less than, and 1 if the 
	 * one calling the compareTo is greater than.
	 */
	public int compareToWithTime(Date o) {
		if(this.getMonth() == o.getMonth() && this.getDay() == o.getDay() && this.getYear() == o.getYear() && this.getHour() == o.getHour() && this.getMinute() == o.getMinute() && this.getSecond() == o.getSecond() ) {
			return 0;
		} else if(this.getYear() < o.getYear()){
			return -1;
		} else if(this.getYear() == o.getYear() && this.getMonth() < o.getMonth()) {
			return -1;
		} else if(this.getYear() == o.getYear() && this.getMonth() == o.getMonth() && this.getDay() < o.getDay()) {
			return -1;
		} else if(this.getYear() == o.getYear() && this.getMonth() == o.getMonth() && this.getDay() == o.getDay() && this.getHour() < o.getHour()){
			return -1;
		} else if(this.getYear() == o.getYear() && this.getMonth() == o.getMonth() && this.getDay() == o.getDay() && this.getHour() == o.getHour() && this.getMinute() < o.getMinute()) {
			return -1;
		} else if(this.getYear() == o.getYear() && this.getMonth() == o.getMonth() && this.getDay() == o.getDay() && this.getHour() == o.getHour() && this.getMinute() == o.getMinute() && this.getSecond() < o.getSecond()) {
			return -1;
		} else {
			return 1;
		}
	}

}
