package cm.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

/**
 * Creates an HTML display of combat details for the players.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
// VS4E -- DO NOT REMOVE THIS LINE!
public class InitDisplay extends JDialog {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -5128386667160663019L;

	private JEditorPane jEditorPaneInitDisplay;
	private JScrollPane jScrollPaneInitDisplay;

	/**
	 * Creates the initiative display.
	 */
	public InitDisplay() {
		initComponents();
	}

	private void initComponents() {
		setAlwaysOnTop(true);
		add(getJScrollPaneInitDisplay(), BorderLayout.CENTER);
		setSize(800, 600);
	}

	private JScrollPane getJScrollPaneInitDisplay() {
		if (jScrollPaneInitDisplay == null) {
			jScrollPaneInitDisplay = new JScrollPane();
			jScrollPaneInitDisplay.setViewportView(getJEditorPaneInitDisplay());
		}
		return jScrollPaneInitDisplay;
	}

	private JEditorPane getJEditorPaneInitDisplay() {
		if (jEditorPaneInitDisplay == null) {
			jEditorPaneInitDisplay = new JEditorPane();
			jEditorPaneInitDisplay.setContentType("text/html");
			jEditorPaneInitDisplay.setEditable(false);
		}
		return jEditorPaneInitDisplay;
	}

	/**
	 * Sets the HTML content of the display.
	 * 
	 * @param text
	 *            the HTML content
	 */
	public void setHTML(String text) {
		getJEditorPaneInitDisplay().setText(text);
		getJEditorPaneInitDisplay().setCaretPosition(0);
	}
}
