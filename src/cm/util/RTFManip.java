package cm.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Provides functions for the manipulation of RTF text.
 * @author matthew.rinehart
 */
public class RTFManip {
	/**
	 * Adds bold formatting directives to the provided input.
	 * @param input a string
	 * @return an RTF-embolded string
	 */
	public static String bold(String input) {
		return "\b " + input + "\b0 ";
	}

	/**
	 * Reformats provided RTF
	 * @param orig RTF
	 * @return reformatted RTF
	 */
	public static String importFast(String orig) {
		String str = new String(orig);
		str = str.replaceAll("\\cell", "\\row")
				.replaceAll("\\line", "\\row")
				.replaceAll("\\fs27", "\\fs20")
				.replaceAll("\\fs24", "\\fs16")
				.replaceAll("\\pard\\ltrpar\\qj\\par\r\n", "")
				.replaceAll("\\f1 ", "\\par\\f1 ")
				.replaceAll("\\pard\\ltrpar\\b", "\\par\\pard\\ltrpar\\b")
				.replaceAll("\\par\r\n\\b Dex", "\\tab \\b Dex")
				.replaceAll("\\par\r\n\\b Wis", "\\tab \\b Wis")
				.replaceAll("\\par\r\n\\b Int", "\\tab \\b Int")
				.replaceAll("\\par\r\n\\b Cha", "\\tab \\b Cha")
				.replaceAll("\\par\\pard\\ltrpar\\b Con", "\\pard\\ltrpar\\b Con")
				.replaceAll("Georgia", "Arial")
				.replaceAll("\\par\r\nLevel", " \\tab Level")
				.replaceAll("\\par\r\nXP", " \\tab XP")
				.replaceAll("\\pard\\ltrpar\\qj\\fs16\\par", "\\pard\\ltrpar\\qj\\fs16")
				.replaceAll("\r\n\\pard\\ltrpar\\par\r\n", "");
		str = str.substring(0, str.indexOf("\\pard\\ltrpar\\'a9")) + "}";
		return str;
	}
	
	/**
	 * Imports RTF stats from clibpoard.
	 * @return Reformatted string.
	 * @see #importFast(String)
	 */
	public static String importFromClipboard() {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		String value = "";
		if (cb.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
			try {
				value = importFast((String) cb.getData(DataFlavor.stringFlavor));
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * Re-formats provided RTF
	 * @param input RTF
	 * @return reformatted RTF
	 */
	public static String importToDetails(String input) {
		String builder = new String(input);
		Boolean flag = false;
		builder.replaceAll("{\\fs18 ", "")
				.replaceAll("\\fs18", "")
				.replaceAll("\\cf1", "")
				.replaceAll("  ", " ")
		        .replaceAll("\\f3 }", "\\ltrch }")
		        .replaceAll("};", ";")
		        .replaceAll("\\loch", "")
		        .replaceAll("\\f3 6", "6")
		        .replaceAll("\\f3 5 6", "5")
		        .replaceAll("\\f3 4 5 6", "4")
		        .replaceAll("\\f3 3 4 5 6", "3")
		        .replaceAll("\\f3 2 3 4 5 6", "2")
		        .replaceAll("\\bullet ", "*")
		        .replaceAll("\\endash ", "-")
		        .replaceAll("\\emdash ", "-")
		        .replaceAll("\\rquote ", "'")
		        .replaceAll("\\b0 ", "")
		        .replaceAll("\\b ", "")
		        .replaceAll("}", " ")
		        .replaceAll("{", " ")
		        .replaceAll("\\", "\r\n\\")
		        .replaceAll("    ", " ")
		        .replaceAll("   ", " ")
		        .replaceAll("  ", " ")
		        .replaceAll(" ,", ",")
		        .replaceAll(" ; ", "; ")
		        .replaceAll("\\~", "\\ltrch ");
		
		String[] strArray = builder.split("\n");
		builder = "";
		Integer num = 0;
		for (String str2 : strArray) {
			if (str2.trim().startsWith("\\ltrch") && !str2.trim().contentEquals("\\ltrch")) {
				if (num == 1) {
					builder += "@B " + str2.trim() + "\r\n";
					num = 0;
				} else {
					builder += str2.trim() + "\r\n";
				}
			} else if (str2.trim().contentEquals("\\clcbpat5")) {
				num = 1;
			} else if (str2.trim().contentEquals("\\clcbpat3")) {
				num = 0;
			} else if (str2.trim().startsWith("\\i ") && !str2.trim().contentEquals("\\i")) {
				builder += str2.substring(3).trim() + "\r\n";
			} else if (str2.trim().startsWith("\\tab") && !str2.trim().contentEquals("\\tab")) {
				if (!str2.trim().startsWith("\\tab Senses ")) {
					builder += "##\r\n";
				}
				builder += str2.trim() + "\r\n";
			} else if (str2.trim().startsWith("\\line") && !str2.trim().contentEquals("\\line")) {
				builder += str2.trim() + "\r\n";
			} else if (str2.trim().startsWith("\\f3") && !str2.trim().contentEquals("\\f3")) {
				builder += str2.trim() + "\r\n";
			} else if (str2.trim().startsWith("\\fi180")) {
				builder += "##\r\n";
			} else if (str2.trim().startsWith("\\fldrslt")) {
				builder += str2.trim() + "\r\n";
			} else if (flag) {
				builder += str2.trim() + "\r\n";
			}
		}
		builder = builder.replaceAll("; Resist ", "\r\nResist ")
				.replaceAll("; Vulnerability ", "\r\nVulnerability ")
				.replaceAll("; Action ", "\r\nAction ")
				.replaceAll("\r\n\\fldrslt", "").replaceAll("\r\n##", "#");
		if (!flag) {
			builder = builder.replaceAll("\\ltrch ", "")
					.replaceAll("\\tab ", "")
					.replaceAll("\\line ", "")
					.replaceAll("\\f3 ", "@")
					.replaceAll("  ", " ");
		}
		builder = "@4eSB001\r\n" + builder;
		return builder;
	}

	/**
	 * Formats the provided integer to include sign, regardless of value.
	 * @param input the integer
	 * @return the signed-integer
	 */
	public static String integerFormatForPlus(Integer input) {
		if (input < 0) {
			return input.toString();
		}
		return "+" + input;
	}
	
	/** Formats the integer in the provided string to include sign, regardless of value.
	 * @param input the string
	 * @return the signed-integer
	 */
	public static String integerFormatForPlus(String input) {
		return integerFormatForPlus(Integer.valueOf(input));
	}

	/**
	 * Formats the provided integer as a DnD 4e stat with bonus.
	 * @param input the stat
	 * @return the stat with stat bonus included
	 */
	public static String statBonus(Integer input) {
		Integer num = DnD4eRules.calcStatBonus(input);
		return input + " (" + integerFormatForPlus(num) + ")";
	}
	
	/**
	 * Reformats the input to use the DnD symbol font.
	 * @param input the input
	 * @return the formatted input
	 */
	public static String symbol(String input) {
		return "\\f1 " + input + "\\f0 ";
	}
}