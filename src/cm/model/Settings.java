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
 * @author matthew.rinehart
 */
public class Settings {
	private static Properties SETTINGS = new Properties();
	private static String FILENAME = "cm.properties";
	
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

	private static void loadDefaults() {
		SETTINGS.setProperty("doOngoingPopup", Boolean.TRUE.toString());
		SETTINGS.setProperty("doPowerRecharge", Boolean.TRUE.toString());
		SETTINGS.setProperty("doSavingThrows", Boolean.TRUE.toString());
		SETTINGS.setProperty("useModRoles", Boolean.FALSE.toString());
		SETTINGS.setProperty("workingDirectory", System.getProperty("user.dir"));
	}

	public static Boolean useModRoles() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("useModRoles"));
	}
	
	public static boolean doPopupForOngoingDamage() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("doOngoingPopup"));
	}

	public static boolean doPowerRecharge() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("doPowerRecharge"));
	}

	public static boolean doSavingThrows() {
		init();
		return Boolean.valueOf(SETTINGS.getProperty("doSavingThrows"));
	}
	
	public static File getWorkingDirectory() {
		init();
		return new File(SETTINGS.getProperty("workingDirectory"));
	}

	public static void setPopupForOngoingDamage(Boolean value) {
		SETTINGS.setProperty("doOngoingPopup", value.toString());
	}

	public static void setUsePowerRecharge(Boolean value) {
		SETTINGS.setProperty("doPowerRecharge", value.toString());
	}

	public static void setUseSavingThrows(Boolean value) {
		SETTINGS.setProperty("doSavingThrows", value.toString());
	}
	
	public static void setWorkingDirectory(File directory) {
		SETTINGS.setProperty("workingDirectory", directory.getAbsolutePath());
	}
}
