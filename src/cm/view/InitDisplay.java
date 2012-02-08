package cm.view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

//VS4E -- DO NOT REMOVE THIS LINE!
public class InitDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JEditorPane jEditorPaneInitDisplay;
	private JScrollPane jScrollPaneInitDisplay;
	public InitDisplay() {
		initComponents();
	}

	private void initComponents() {
		setUndecorated(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
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
		}
		return jEditorPaneInitDisplay;
	}

	public void setHTML(String text) {
		getJEditorPaneInitDisplay().setText(text);
	}
}
