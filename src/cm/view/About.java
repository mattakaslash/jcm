package cm.view;

import javax.swing.JDialog;

import java.awt.Desktop;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import cm.DnD4eCombatManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JTextPane;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Help -> About.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class About extends JDialog {
	/**
	 * Creates the About dialog box.
	 */
	public About() {
		setTitle("About DnD 4e Combat Manager");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 615, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);
		_lblBlurb.setBackground(_lblProgTitle.getBackground());

		_lblProgTitle = new JLabel("DnD 4e Combat Manager");
		_lblProgTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		_lblProgTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblProgTitle = new GridBagConstraints();
		gbc_lblProgTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblProgTitle.gridx = 0;
		gbc_lblProgTitle.gridy = 0;
		getContentPane().add(_lblProgTitle, gbc_lblProgTitle);

		_lblVersion = new JLabel("Version " + DnD4eCombatManager.VERSION);
		_lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		_lblVersion.setFont(new Font("Tahoma", Font.BOLD, 24));
		GridBagConstraints gbc_lblVersion = new GridBagConstraints();
		gbc_lblVersion.insets = new Insets(0, 0, 5, 0);
		gbc_lblVersion.gridx = 0;
		gbc_lblVersion.gridy = 1;
		getContentPane().add(_lblVersion, gbc_lblVersion);

		_lblBlurb = new JTextPane();
		_lblBlurb.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						URI uri = new URI(event.getDescription());
						if (Desktop.isDesktopSupported()) {
							Desktop.getDesktop().browse(uri);
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Unable to launch your default browser: " + e.getLocalizedMessage(),
								"Error Launching Browser", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		});
		_lblBlurb.setEditable(false);
		_lblBlurb.setContentType("text/html");
		_lblBlurb
				.setText("<html>DnD 4e Combat Manager is based on the program of the same name hosted at<br><a href=\\\"http://www.dragonpro.com/index.php?option=com_jdownloads&view=viewcategory&catid=2&Itemid=53\\\">http://www.dragonpro.com/index.php?option=com_jdownloads&view=viewcategory&catid=2&Itemid=53</a>.<br>This is a Java port of an early source release, with some features from the original added and<br>other unique features implemented also.</html>");
		GridBagConstraints gbc_lblBlurb = new GridBagConstraints();
		gbc_lblBlurb.fill = GridBagConstraints.BOTH;
		gbc_lblBlurb.insets = new Insets(0, 0, 5, 0);
		gbc_lblBlurb.gridx = 0;
		gbc_lblBlurb.gridy = 2;
		getContentPane().add(_lblBlurb, gbc_lblBlurb);

		_btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.fill = GridBagConstraints.BOTH;
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 3;
		getContentPane().add(_btnOk, gbc_btnOk);
		pack();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -278991946798522826L;
	private JLabel _lblProgTitle;
	private JLabel _lblVersion;
	private JTextPane _lblBlurb;
	private JButton _btnOk;

}
