package cm.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Application settings.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class Settings {
	/**
	 * The properties object which stores the application settings.
	 */
	private static Properties SETTINGS = new Properties();

	/**
	 * The file to write the properties to.
	 */
	private static String FILENAME = "cm.properties";

	/**
	 * If no settings have been loaded, initializes settings from defaults and
	 * the file.
	 */
	private static void init() {
		if (SETTINGS.isEmpty()) {
			loadDefaults();
			File f = new File(FILENAME);
			if (f.exists()) {
				try {
					InputStream in = new FileInputStream(f);
					SETTINGS.load(new FileInputStream(f));
					in.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			save();
		}
	}

	/**
	 * Saves the settings to the settings file.
	 */
	public static void save() {
		try {
			OutputStream out = new FileOutputStream(new File(FILENAME));
			SETTINGS.store(out, "DnD 4e Combat Manager settings");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads default values for the application settings.
	 */
	private static void loadDefaults() {
		SETTINGS.setProperty("criticalHitSong", "");
		SETTINGS.setProperty("criticalMissSong", "");
		SETTINGS.setProperty("dailySong", "");
		SETTINGS.setProperty("doGroupSimilar", Boolean.TRUE.toString());
		SETTINGS.setProperty("doOngoingPopup", Boolean.TRUE.toString());
		SETTINGS.setProperty("doPowerRecharge", Boolean.TRUE.toString());
		SETTINGS.setProperty("doSavingThrows", Boolean.TRUE.toString());
		SETTINGS.setProperty("musicDirectory", "");
		SETTINGS.setProperty("victorySong", "");
		SETTINGS.setProperty("workingDirectory", System.getProperty("user.dir"));
	}

	/**
	 * Returns the critical hit song.
	 * 
	 * @return the critical hit song file
	 */
	public static File getCriticalHitSong() {
		init();
		File f = new File(SETTINGS.getProperty("criticalHitSong"));
		if (f.exists() && f.canRead()) {
			return f;
		} else {
			return null;
		}
	}

	/**
	 * Sets the critical hit song.
	 * 
	 * @param file
	 *            the critical hit song file
	 */
	public static void setCriticalHitSong(File file) {
		if (file.getPath().isEmpty()) {
			SETTINGS.setProperty("criticalHitSong", "");
		} else {
			SETTINGS.setProperty("criticalHitSong", file.getAbsolutePath());
		}
	}

	/**
	 * Returns the critical miss song.
	 * 
	 * @return the critical miss song file
	 */
	public static File getCriticalMissSong() {
		init();
		File f = new File(SETTINGS.getProperty("criticalMissSong"));
		if (f.exists() && f.canRead()) {
			return f;
		} else {
			return null;
		}
	}

	/**
	 * Sets the critical miss song.
	 * 
	 * @param file
	 *            the critical miss song file
	 */
	public static void setCriticalMissSong(File file) {
		if (file.getPath().isEmpty()) {
			SETTINGS.setProperty("criticalMissSong", "");
		} else {
			SETTINGS.setProperty("criticalMissSong", file.getAbsolutePath());
		}
	}

	/**
	 * Returns the daily song.
	 * 
	 * @return the daily song file
	 */
	public static File getDailySong() {
		init();
		File f = new File(SETTINGS.getProperty("dailySong"));
		if (f.exists() && f.canRead()) {
			return f;
		} else {
			return null;
		}
	}

	/**
	 * Sets the daily song.
	 * 
	 * @param file
	 *            the daily song file
	 */
	public static void setDailySong(File file) {
		if (file.getPath().isEmpty()) {
			SETTINGS.setProperty("dailySong", "");
		} else {
			SETTINGS.setProperty("dailySong", file.getAbsolutePath());
		}
	}

	/**
	 * Returns an indicator of whether the application should group similar
	 * Combatants when rolling initiative.
	 * 
	 * @return true, if similar Combatants should be grouped
	 */
	public static Boolean doGroupSimilar() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("doGroupSimilar"));
	}

	/**
	 * Sets an indicator of whether the application should group similar
	 * Combatants when rolling initiative.
	 * 
	 * @param value
	 *            true, if similar Combatants should be grouped
	 */
	public static void setGroupSimilar(Boolean value) {
		SETTINGS.setProperty("doGroupSimilar", value.toString());
	}

	/**
	 * Returns the root directory of the music for the music player.
	 * 
	 * @return the directory, or null if the setting is invalid
	 */
	public static File getMusicDirectory() {
		init();
		File f = new File(SETTINGS.getProperty("musicDirectory"));
		if (f.exists() && f.isDirectory()) {
			return f;
		} else {
			return null;
		}
	}

	/**
	 * Sets the root directory of the music for the music player.
	 * 
	 * @param directory
	 *            the directory
	 */
	public static void setMusicDirectory(File directory) {
		if (directory.getPath().isEmpty()) {
			SETTINGS.setProperty("musicDirectory", "");
		} else {
			SETTINGS.setProperty("musicDirectory", directory.getAbsolutePath());
		}
	}

	/**
	 * Returns an indicator of whether the application should display a pop-up
	 * reminder for ongoing damage effects.
	 * 
	 * @return true, if a pop-up should be displayed
	 */
	public static boolean doPopupForOngoingDamage() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("doOngoingPopup"));
	}

	/**
	 * Sets an indicator of whether the application should display a pop-up
	 * reminder for ongoing damage effects.
	 * 
	 * @param value
	 *            true, if a pop-up should be displayed
	 */
	public static void setPopupForOngoingDamage(Boolean value) {
		SETTINGS.setProperty("doOngoingPopup", value.toString());
	}

	/**
	 * Returns an indicator of whether the application should roll power
	 * recharges automatically.
	 * 
	 * @return true, if the application should roll to recharge powers
	 *         automatically when a creature starts its turn
	 */
	public static boolean doPowerRecharge() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("doPowerRecharge"));
	}

	/**
	 * Sets an indicator of whether the application should roll power recharges
	 * automatically.
	 * 
	 * @param value
	 *            true, if the application should roll to recharge powers
	 *            automatically when a creature starts its turn
	 */
	public static void setDoPowerRecharge(Boolean value) {
		SETTINGS.setProperty("doPowerRecharge", value.toString());
	}

	/**
	 * Returns an indicator of whether the application should roll saving throws
	 * automatically.
	 * 
	 * @return true, if the application should roll saving throws automatically
	 *         at the end of a creature's turn
	 */
	public static boolean doSavingThrows() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("doSavingThrows"));
	}

	/**
	 * Sets an indicator of whether the application should roll saving throws
	 * automatically.
	 * 
	 * @param value
	 *            true, if the application should roll saving throws
	 *            automatically at the end of a creature's turn
	 */
	public static void setDoSavingThrows(Boolean value) {
		SETTINGS.setProperty("doSavingThrows", value.toString());
	}

	/**
	 * Returns the victory song.
	 * 
	 * @return the victory song file
	 */
	public static File getVictorySong() {
		init();
		File f = new File(SETTINGS.getProperty("victorySong"));
		if (f.exists() && f.canRead()) {
			return f;
		} else {
			return null;
		}
	}

	/**
	 * Sets the victory song.
	 * 
	 * @param file
	 *            the victory song file
	 */
	public static void setVictorySong(File file) {
		if (file.getPath().isEmpty()) {
			SETTINGS.setProperty("victorySong", "");
		} else {
			SETTINGS.setProperty("victorySong", file.getAbsolutePath());
		}
	}

	/**
	 * Returns the last-remembered working directory for a file load/save
	 * operation.
	 * 
	 * @return the working directory
	 */
	public static File getWorkingDirectory() {
		init();
		return new File(SETTINGS.getProperty("workingDirectory"));
	}

	/**
	 * Store the last-used working directory from a file load/save operation.
	 * 
	 * @param directory
	 *            the working directory
	 */
	public static void setWorkingDirectory(File directory) {
		SETTINGS.setProperty("workingDirectory", directory.getAbsolutePath());
	}
}
