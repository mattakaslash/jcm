package cm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StatLogger {
	/**
	 * Output log.
	 */
	private static FileOutputStream LOG = null;

	/**
	 * Initialize the output stream, if necessary.
	 */
	private static void init() {
		if (LOG == null) {
			try {
				File logFile = new File("stats.csv");
				LOG = new FileOutputStream(logFile);
				if (!logFile.exists()) {
					logFile.createNewFile();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Write stats to the log: round,source,target,damage_amount.
	 * 
	 * @param round
	 *            round of combat
	 * @param source
	 *            source handle
	 * @param target
	 *            target handle
	 * @param amount
	 *            amount of damage (negative values indicate healing)
	 */
	public static void logDamage(Integer round, String source, String target,
			Integer amount) {
		init();
		String message = round + "," + source + "," + target + "," + amount
				+ "\r\n";
		try {
			LOG.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write stats to the log: round,source,target,DEAD.
	 * 
	 * @param round
	 *            round of combat
	 * @param source
	 *            source handle
	 * @param target
	 *            target handle
	 */
	public static void logDeath(Integer round, String source, String target) {
		init();
		String message = round + "," + source + "," + target + ",DEAD\r\n";
		try {
			LOG.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write informational message to log, indicating previous message was an
	 * opportunity attack by a different fighter.
	 * 
	 * @param fighter
	 *            the fighter's handle
	 */
	public static void logOA(String fighter) {
		init();
		String message = ",Above was OA by " + fighter + "\r\n";
		try {
			LOG.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}