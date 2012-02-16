package cm;

import javax.swing.JFrame;

import cm.view.MainFrame;

public class DnD4eCombatManager {
	/**
	 * Application version.
	 */
	public static String VERSION = "1.0pre3";

	/**
	 * Invokes the main frame.
	 * @param args unused
	 */
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
