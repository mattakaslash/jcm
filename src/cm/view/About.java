package cm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

//VS4E -- DO NOT REMOVE THIS LINE!
public class About extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton jButtonOK;
	private JTextArea jTextAreaAbout;

	public About() {
		initComponents();
	}

	private void initComponents() {
		setTitle("About DnD 4e Combat Manager");
		setModal(true);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(new Color(223, 223, 223));
		setForeground(Color.black);
		add(getJButtonOK(), BorderLayout.SOUTH);
		add(getJTextAreaAbout(), BorderLayout.CENTER);
		pack();
	}

	private JButton getJButtonOK() {
		if (jButtonOK == null) {
			jButtonOK = new JButton();
			jButtonOK.setText("OK");
			jButtonOK.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonOKActionActionPerformed(event);
				}
			});
		}
		return jButtonOK;
	}

	private JTextArea getJTextAreaAbout() {
		if (jTextAreaAbout == null) {
			jTextAreaAbout = new JTextArea();
			jTextAreaAbout.setEditable(false);
			jTextAreaAbout
					.setText("DnD 4e Combat Manager is based on the program of the same name hosted at\n" +
							"http://www.dragonpro.com/index.php?option=com_jdownloads&view=viewcategory&catid=2&Itemid=53.\n" +
							"This is a Java port of an early source release, with some features from the original added and\n" +
							"other unique features implemented also.");
		}
		return jTextAreaAbout;
	}

	private void jButtonOKActionActionPerformed(ActionEvent event) {
		this.setVisible(false);
	}
}
