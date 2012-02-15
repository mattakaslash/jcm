package cm.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

//VS4E -- DO NOT REMOVE THIS LINE!
public class InitDisplay extends JDialog {

	private static final long serialVersionUID = 1L;
	private JEditorPane jEditorPaneInitDisplay;
	private JScrollPane jScrollPaneInitDisplay;
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

	public void setHTML(String text) {
		getJEditorPaneInitDisplay().setText(text);
		getJEditorPaneInitDisplay().setCaretPosition(0);
	}
}
