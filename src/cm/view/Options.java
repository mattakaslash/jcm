/**
 * 
 */
package cm.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import cm.model.Settings;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Options extends JDialog {

	private static final long serialVersionUID = 1L;
	private JCheckBox jCheckBoxGroupSimilar;
	private JCheckBox jCheckBoxOngoingPopup;
	private JPanel jPanelAutomaticRolls;
	private JCheckBox jCheckBoxSavingThrows;
	private JCheckBox jCheckBoxPowerRecharges;
	private JPanel jPanelMusicLocation;
	private JTextField jTextFieldMusicLocation;
	private JButton jButtonBrowse;
	private JButton jButtonOK;
	private JButton jButtonCancel;

	public Options() {
		initComponents();
		if (Settings.getMusicDirectory() != null) {
			getJTextFieldMusicLocation().setText(Settings.getMusicDirectory().getAbsolutePath());
		}
	}

	private void initComponents() {
		setTitle("Options");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setModal(true);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJCheckBoxGroupSimilar(), new Constraints(new Bilateral(8, 8, 299), new Leading(8, 8, 8)));
		add(getJCheckBoxOngoingPopup(), new Constraints(new Bilateral(8, 8, 217), new Leading(32, 8, 8)));
		add(getJPanelAutomaticRolls(), new Constraints(new Bilateral(8, 8, 0), new Leading(58, 53, 10, 10)));
		add(getJPanelMusicLocation(), new Constraints(new Bilateral(8, 8, 0), new Leading(117, 51, 10, 10)));
		add(getJButtonOK(), new Constraints(new Trailing(8, 12, 12), new Leading(174, 12, 12)));
		add(getJButtonCancel(), new Constraints(new Trailing(65, 12, 12), new Leading(174, 12, 12)));
		pack();
	}

	private JCheckBox getJCheckBoxGroupSimilar() {
		if (jCheckBoxGroupSimilar == null) {
			jCheckBoxGroupSimilar = new JCheckBox();
			jCheckBoxGroupSimilar.setSelected(true);
			jCheckBoxGroupSimilar.setText("Group Similar Combatants when Rolling Initiative");
		}
		return jCheckBoxGroupSimilar;
	}

	private JButton getJButtonCancel() {
		if (jButtonCancel == null) {
			jButtonCancel = new JButton();
			jButtonCancel.setText("Cancel");
			jButtonCancel.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonCancelActionActionPerformed(event);
				}
			});
		}
		return jButtonCancel;
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

	private JButton getJButtonBrowse() {
		if (jButtonBrowse == null) {
			jButtonBrowse = new JButton();
			jButtonBrowse.setText("Browse...");
			jButtonBrowse.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonBrowseActionActionPerformed(event);
				}
			});
		}
		return jButtonBrowse;
	}

	private JTextField getJTextFieldMusicLocation() {
		if (jTextFieldMusicLocation == null) {
			jTextFieldMusicLocation = new JTextField();
		}
		return jTextFieldMusicLocation;
	}

	private JPanel getJPanelMusicLocation() {
		if (jPanelMusicLocation == null) {
			jPanelMusicLocation = new JPanel();
			jPanelMusicLocation.setBorder(BorderFactory.createTitledBorder(null, "Music Location", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelMusicLocation.setLayout(new GroupLayout());
			jPanelMusicLocation.add(getJTextFieldMusicLocation(), new Constraints(new Bilateral(0, 99, 4), new Leading(0, 12, 12)));
			jPanelMusicLocation.add(getJButtonBrowse(), new Constraints(new Trailing(0, 93, 109, 168), new Leading(-3, 12, 12)));
		}
		return jPanelMusicLocation;
	}

	private JCheckBox getJCheckBoxPowerRecharges() {
		if (jCheckBoxPowerRecharges == null) {
			jCheckBoxPowerRecharges = new JCheckBox();
			jCheckBoxPowerRecharges.setSelected(true);
			jCheckBoxPowerRecharges.setText("Power Recharges");
		}
		return jCheckBoxPowerRecharges;
	}

	private JCheckBox getJCheckBoxSavingThrows() {
		if (jCheckBoxSavingThrows == null) {
			jCheckBoxSavingThrows = new JCheckBox();
			jCheckBoxSavingThrows.setSelected(true);
			jCheckBoxSavingThrows.setText("Saving Throws");
		}
		return jCheckBoxSavingThrows;
	}

	private JPanel getJPanelAutomaticRolls() {
		if (jPanelAutomaticRolls == null) {
			jPanelAutomaticRolls = new JPanel();
			jPanelAutomaticRolls.setBorder(BorderFactory.createTitledBorder(null, "Automatic Rolls", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelAutomaticRolls.setLayout(new GroupLayout());
			jPanelAutomaticRolls.add(getJCheckBoxSavingThrows(), new Constraints(new Leading(8, 8, 8), new Leading(0, 8, 8)));
			jPanelAutomaticRolls.add(getJCheckBoxPowerRecharges(), new Constraints(new Leading(121, 8, 8), new Leading(0, 8, 8)));
		}
		return jPanelAutomaticRolls;
	}

	private JCheckBox getJCheckBoxOngoingPopup() {
		if (jCheckBoxOngoingPopup == null) {
			jCheckBoxOngoingPopup = new JCheckBox();
			jCheckBoxOngoingPopup.setSelected(true);
			jCheckBoxOngoingPopup.setText("Display Popup for Ongoing Effects");
		}
		return jCheckBoxOngoingPopup;
	}

	private void jButtonBrowseActionActionPerformed(ActionEvent event) {
		JFileChooser jc = new JFileChooser();
		jc.setDialogTitle("Select Music Root");
		jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jc.setAcceptAllFileFilterUsed(false);
		if (Settings.getMusicDirectory().exists()) {
			jc.setCurrentDirectory(Settings.getMusicDirectory());
		}
		jc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Root Directory";
			}
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}
		});
		
		if (jc.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
			getJTextFieldMusicLocation().setText(jc.getSelectedFile().getAbsolutePath());
		}
	}

	private void jButtonCancelActionActionPerformed(ActionEvent event) {
		this.dispose();
	}

	private void jButtonOKActionActionPerformed(ActionEvent event) {
		File f = new File(getJTextFieldMusicLocation().getText());
		
		if (!f.exists() || !f.isDirectory()) {
			JOptionPane.showMessageDialog(this, "Music directory does not exist or is not a directory.", "Invalid Music Directory", JOptionPane.ERROR_MESSAGE);
		} else {
			Settings.setDoPowerRecharge(getJCheckBoxPowerRecharges().isSelected());
			Settings.setDoSavingThrows(getJCheckBoxSavingThrows().isSelected());
			Settings.setGroupSimilar(getJCheckBoxGroupSimilar().isSelected());
			Settings.setPopupForOngoingDamage(getJCheckBoxOngoingPopup().isSelected());
			Settings.setMusicDirectory(f);
			this.setVisible(false);
		}
	}
}
