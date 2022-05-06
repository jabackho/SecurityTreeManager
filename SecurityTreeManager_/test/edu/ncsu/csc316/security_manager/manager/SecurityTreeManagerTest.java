package edu.ncsu.csc316.security_manager.manager;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.security_manager.manager.SecurityTreeManager;

/**
 * Tests the SecurityTreeManager class.
 * @author jakebackhouse
 *
 */
public class SecurityTreeManagerTest {
	
	private SecurityTreeManager s1, s2, s3, s4, s5, s6;
	
	private String preOrderFile1 = "input/ddos-preorder.txt";
	private String postOrderFile1 = "input/ddos-postorder.txt";
	private String logFile1 = "input/sample-log.txt";
	private String logFile2 = "input/sample-log2.txt";
	private String logFile3 = "input/sample-log3.txt";
	private String invalidFile = "input/klajdlkfs.txt";
	

	private String date1 = "09-13-2015";
	private String date2 = "07-17-2015";
	private String date3 = "12-12-2016";
	
	/**
	 * Setup before each test.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		s1 = new SecurityTreeManager(preOrderFile1, postOrderFile1);
		s2 = new SecurityTreeManager(logFile1);
		s3 = new SecurityTreeManager(logFile2);
		s4 = new SecurityTreeManager(invalidFile);
		s5 = new SecurityTreeManager(logFile3);
		s6 = new SecurityTreeManager(preOrderFile1, invalidFile);
		
	}

	/**
	 * Tests the generation of an attack tree level order.
	 */
	@Test
	public void testAttackTreeLevelOrder() {
		
		assertEquals("LevelOrder[\n   GOAL Step[Use DDoS Attack to Disrupt All Users, C=21557.12, P=0.878, I=8.00]\n   OR Step[Attack Servers, C=14099.02, P=0.692, I=7.48]\n   OR Step[Attack Comm Infrastructure, C=32857.14, P=0.559, I=8.00]\n   OR Step[Attack All Clients, C=10000.00, P=0.100, I=5.00]\n   OR Step[Direct BOTNET against key Servers, C=5580.65, P=0.560, I=7.48]\n   OR Step[Infect Servers with Worm/Virus, C=30000.00, P=0.300, I=7.00]\n   OR Step[Attack Switches, C=30000.00, P=0.300, I=7.00]\n   OR Step[Attack Routers, C=30000.00, P=0.300, I=7.00]\n   OR Step[Attack DNS, C=50000.00, P=0.100, I=8.00]\n   OR Step[Infect Clients with Worm/Virus, C=10000.00, P=0.100, I=5.00]\n   OR Step[\"Rent\" Existing BOTNET, C=5000.00, P=0.500, I=6.00]\n   OR Step[Build a BOTNET, C=8000.00, P=0.120, I=7.48]\n   AND Step[Find Vulnerable Computers, C=2000.00, P=0.500, I=3.00]\n   AND Step[Infect Computer with BOT, C=5000.00, P=0.400, I=4.00]\n   AND Step[Remain Undetected, C=1000.00, P=0.600, I=4.00]\n]", s1.getAttackTreeLevelOrder());
		
	}
	
	/**
	 * Tests the propagation of values and the get root functionality.
	 */
	@Test
	public void testGetRoot() {
		assertEquals("GOAL Step[Use DDoS Attack to Disrupt All Users, C=21557.12, P=0.878, I=8.00]", s1.getRoot());
	}
	
	/**
	 * Tests the generation of log entries with the given date.
	 */
	@Test 
	public void testGetLogEntriesByDate() {
		assertEquals("LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]\n", s2.getLogEntriesForDate(date1));
		assertEquals("LogEntry[timestamp=2015/07/17 15:55:25, user=user8, description=save immunizations]\n", s2.getLogEntriesForDate(date2));
		assertEquals("LogEntry[timestamp=2016/12/12 06:28:13, user=user6, description=edit patient representative list]\n", s3.getLogEntriesForDate(date3));
		assertEquals("LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]\nLogEntry[timestamp=2015/09/13 02:58:49, user=user3, description=save patient list]\nLogEntry[timestamp=2015/09/13 02:58:49, user=user4, description=save patient list]\nLogEntry[timestamp=2015/09/13 02:58:49, user=user5, description=save patient list]\n", s5.getLogEntriesForDate(date1));
	}
	
	/**
	 * Tests the generation of the level order of log entries.
	 */
	@Test
	public void testLogEntryLevelOrders() {
		assertEquals("LevelOrder[\n   LogEntry[timestamp=2015/09/13 02:58:49, user=user2, description=save patient list]\n   LogEntry[timestamp=2012/12/18 16:25:58, user=user18, description=view diagnoses]\n   LogEntry[timestamp=2016/12/12 06:28:13, user=user6, description=edit patient representative list]\n   LogEntry[timestamp=2013/07/19 22:49:10, user=user1, description=edit email reminders]\n   LogEntry[timestamp=2015/07/10 00:26:58, user=user15, description=add patient representative list]\n   LogEntry[timestamp=2015/08/01 03:54:17, user=user10, description=update appointment requests]\n]", s3.getLogTreeLevelOrder());
	}
	
	/**
	 * Tests the handling of an invalid file.
	 */
	@Test
	public void testInvalidFile() {
		assertEquals("LevelOrder[\n]", s4.getLogTreeLevelOrder());
		assertEquals("LevelOrder[\n]", s6.getAttackTreeLevelOrder());
	}

}
