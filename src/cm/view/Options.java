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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import cm.model.Settings;

/**
 * Defines a program options window.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 2.0
 */
//VS4E -- DO NOT REMOVE THIS LINE!
public class Options extends JDialog {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 1948071096317227461L;
	private JButton jButtonBrowseRoot;
	private JButton jButtonCancel;
	private JButton jButtonBrowseCriticalHit;
	private JButton jButtonBrowseCriticalMiss;
	private JButton jButtonBrowseDaily;
	private JButton jButtonOK;
	private JButton jButtonBrowseVictory;
	private JCheckBox jCheckBoxGroupSimilar;
	private JCheckBox jCheckBoxOngoingPopup;
	private JCheckBox jCheckBoxPowerRecharges;
	private JCheckBox jCheckBoxSavingThrows;
	private JLabel jLabelCriticalHit;
	private JLabel jLabelCriticalMiss;
	private JLabel jLabelDaily;
	private JLabel jLabelRootDirectory;
	private JLabel jLabelVictory;
	private JPanel jPanelAutomaticRolls;
	private JPanel jPanelMusicLocation;
	private JTextField jTextFieldCriticalHit;
	private JTextField jTextFieldCriticalMiss;
	private JTextField jTextFieldDaily;
	private JTextField jTextFieldRootDirectory;
	private JTextField jTextFieldVictory;

	/**
	 * Defines the options window and loads settings into their respective controls.
	 */
	public Options() {
		initComponents();
		getJCheckBoxGroupSimilar().setSelected(Settings.doGroupSimilar());
		getJCheckBoxOngoingPopup().setSelected(Settings.doPopupForOngoingDamage());
		getJCheckBoxPowerRecharges().setSelected(Settings.doPowerRecharge());
		getJCheckBoxSavingThrows().setSelected(Settings.doSavingThrows());
		if (Settings.getMusicDirectory() != null) {
			getJTextFieldRootDirectory().setText(Settings.getMusicDirectory().getAbsolutePath());
		}
		if (Settings.getCriticalHitSong() != null) {
			getJTextFieldCriticalHit().setText(Settings.getCriticalHitSong().getAbsolutePath());
		}
		if (Settings.getCriticalMissSong() != null) {
			getJTextFieldCriticalMiss().setText(Settings.getCriticalMissSong().getAbsolutePath());
		}
		if (Settings.getVictorySong() != null) {
			getJTextFieldVictory().setText(Settings.getVictorySong().getAbsolutePath());
		}
		if (Settings.getDailySong() != null) {
			getJTextFieldDaily().setText(Settings.getDailySong().getAbsolutePath());
		}
	}

	private JButton getJButtonBrowseRoot() {
		if (jButtonBrowseRoot == null) {
			jButtonBrowseRoot = new JButton();
			jButtonBrowseRoot.setText("Browse...");
			jButtonBrowseRoot.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonBrowseRootActionActionPerformed(event);
				}
			});
		}
		return jButtonBrowseRoot;
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

	private JButton getJButtonBrowseCriticalHit() {
		if (jButtonBrowseCriticalHit == null) {
			jButtonBrowseCriticalHit = new JButton();
			jButtonBrowseCriticalHit.setText("Browse...");
			jButtonBrowseCriticalHit.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonBrowseCriticalHitActionActionPerformed(event);
				}
			});
		}
		return jButtonBrowseCriticalHit;
	}

	private JButton getJButtonBrowseCriticalMiss() {
		if (jButtonBrowseCriticalMiss == null) {
			jButtonBrowseCriticalMiss = new JButton();
			jButtonBrowseCriticalMiss.setText("Browse...");
			jButtonBrowseCriticalMiss.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonBrowseCriticalMissActionActionPerformed(event);
				}
			});
		}
		return jButtonBrowseCriticalMiss;
	}

	private JButton getJButtonBrowseDaily() {
		if (jButtonBrowseDaily == null) {
			jButtonBrowseDaily = new JButton();
			jButtonBrowseDaily.setText("Browse...");
			jButtonBrowseDaily.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonBrowseDailyActionActionPerformed(event);
				}
			});
		}
		return jButtonBrowseDaily;
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

	private JButton getJButtonBrowseVictory() {
		if (jButtonBrowseVictory == null) {
			jButtonBrowseVictory = new JButton();
			jButtonBrowseVictory.setText("Browse...");
			jButtonBrowseVictory.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonBrowseVictoryActionActionPerformed(event);
				}
			});
		}
		return jButtonBrowseVictory;
	}

	private JCheckBox getJCheckBoxGroupSimilar() {
		if (jCheckBoxGroupSimilar == null) {
			jCheckBoxGroupSimilar = new JCheckBox();
			jCheckBoxGroupSimilar.setSelected(true);
			jCheckBoxGroupSimilar.setText("Group Similar Combatants when Rolling Initiative");
		}
		return jCheckBoxGroupSimilar;
	}

	private JCheckBox getJCheckBoxOngoingPopup() {
		if (jCheckBoxOngoingPopup == null) {
			jCheckBoxOngoingPopup = new JCheckBox();
			jCheckBoxOngoingPopup.setSelected(true);
			jCheckBoxOngoingPopup.setText("Display Popup for Ongoing Effects");
		}
		return jCheckBoxOngoingPopup;
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

	private JLabel getJLabelCriticalHit() {
		if (jLabelCriticalHit == null) {
			jLabelCriticalHit = new JLabel();
			jLabelCriticalHit.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelCriticalHit.setText("Critical Hit");
		}
		return jLabelCriticalHit;
	}

	private JLabel getJLabelCriticalMiss() {
		if (jLabelCriticalMiss == null) {
			jLabelCriticalMiss = new JLabel();
			jLabelCriticalMiss.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelCriticalMiss.setText("Critical Miss");
		}
		return jLabelCriticalMiss;
	}

	private JLabel getJLabelDaily() {
		if (jLabelDaily == null) {
			jLabelDaily = new JLabel();
			jLabelDaily.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelDaily.setText("Daily");
		}
		return jLabelDaily;
	}

	private JLabel getJLabelRootDirectory() {
		if (jLabelRootDirectory == null) {
			jLabelRootDirectory = new JLabel();
			jLabelRootDirectory.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelRootDirectory.setText("Root Directory");
		}
		return jLabelRootDirectory;
	}

	private JLabel getJLabelVictory() {
		if (jLabelVictory == null) {
			jLabelVictory = new JLabel();
			jLabelVictory.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelVictory.setText("Victory");
		}
		return jLabelVictory;
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

	private JPanel getJPanelMusicLocation() {
		if (jPanelMusicLocation == null) {
			jPanelMusicLocation = new JPanel();
			jPanelMusicLocation.setBorder(BorderFactory.createTitledBorder(null, "Music Locations", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
					"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelMusicLocation.setLayout(new GroupLayout());
			jPanelMusicLocation.add(getJButtonBrowseRoot(), new Constraints(new Trailing(0, 93, 109, 168), new Leading(-3, 12, 12)));
			jPanelMusicLocation.add(getJButtonBrowseCriticalHit(), new Constraints(new Trailing(1, 92, 12, 12), new Leading(26, 12, 12)));
			jPanelMusicLocation.add(getJButtonBrowseCriticalMiss(), new Constraints(new Trailing(1, 92, 12, 12), new Leading(55, 12, 12)));
			jPanelMusicLocation.add(getJButtonBrowseVictory(), new Constraints(new Trailing(1, 92, 12, 12), new Leading(84, 12, 12)));
			jPanelMusicLocation.add(getJButtonBrowseDaily(), new Constraints(new Trailing(1, 92, 12, 12), new Leading(113, 12, 12)));
			jPanelMusicLocation.add(getJLabelRootDirectory(), new Constraints(new Leading(0, 102, 10, 10), new Leading(1, 12, 12)));
			jPanelMusicLocation.add(getJLabelCriticalHit(), new Constraints(new Leading(0, 102, 10, 10), new Leading(30, 12, 12)));
			jPanelMusicLocation.add(getJLabelCriticalMiss(), new Constraints(new Leading(0, 102, 10, 10), new Leading(59, 12, 12)));
			jPanelMusicLocation.add(getJLabelVictory(), new Constraints(new Leading(0, 102, 10, 10), new Leading(88, 12, 12)));
			jPanelMusicLocation.add(getJLabelDaily(), new Constraints(new Leading(0, 102, 10, 10), new Leading(117, 12, 12)));
			jPanelMusicLocation.add(getJTextFieldRootDirectory(), new Constraints(new Bilateral(114, 99, 4), new Leading(0, 12, 12)));
			jPanelMusicLocation.add(getJTextFieldCriticalHit(), new Constraints(new Bilateral(114, 99, 4), new Leading(29, 12, 12)));
			jPanelMusicLocation.add(getJTextFieldCriticalMiss(), new Constraints(new Bilateral(114, 99, 4), new Leading(58, 12, 12)));
			jPanelMusicLocation.add(getJTextFieldVictory(), new Constraints(new Bilateral(114, 99, 4), new Leading(87, 12, 12)));
			jPanelMusicLocation.add(getJTextFieldDaily(), new Constraints(new Bilateral(114, 99, 4), new Leading(116, 12, 12)));
		}
		return jPanelMusicLocation;
	}

	private JTextField getJTextFieldCriticalHit() {
		if (jTextFieldCriticalHit == null) {
			jTextFieldCriticalHit = new JTextField();
		}
		return jTextFieldCriticalHit;
	}

	private JTextField getJTextFieldCriticalMiss() {
		if (jTextFieldCriticalMiss == null) {
			jTextFieldCriticalMiss = new JTextField();
		}
		return jTextFieldCriticalMiss;
	}

	private JTextField getJTextFieldDaily() {
		if (jTextFieldDaily == null) {
			jTextFieldDaily = new JTextField();
		}
		return jTextFieldDaily;
	}

	private JTextField getJTextFieldRootDirectory() {
		if (jTextFieldRootDirectory == null) {
			jTextFieldRootDirectory = new JTextField();
		}
		return jTextFieldRootDirectory;
	}

	private JTextField getJTextFieldVictory() {
		if (jTextFieldVictory == null) {
			jTextFieldVictory = new JTextField();
		}
		return jTextFieldVictory;
	}

	private void initComponents() {
		setTitle("Options");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setModal(true);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJCheckBoxGroupSimilar(), new Constraints(new Leading(8, 10, 10), new Leading(8, 8, 8)));
		add(getJCheckBoxOngoingPopup(), new Constraints(new Leading(8, 298, 8, 8), new Leading(32, 8, 8)));
		add(getJPanelAutomaticRolls(), new Constraints(new Leading(308, 317, 12, 12), new Leading(8, 53, 12, 12)));
		add(getJPanelMusicLocation(), new Constraints(new Leading(8, 617, 12, 12), new Leading(58, 167, 10, 10)));
		add(getJButtonCancel(), new Constraints(new Leading(494, 10, 10), new Leading(231, 12, 12)));
		add(getJButtonOK(), new Constraints(new Leading(574, 12, 12), new Leading(231, 12, 12)));
		pack();
	}

	private void jButtonBrowseCriticalHitActionActionPerformed(ActionEvent event) {
		JFileChooser jc = new JFileChooser();
		jc.setDialogTitle("Select Critical Hit Song");
		if (Settings.getCriticalHitSong() != null && Settings.getCriticalHitSong().canRead()) {
			jc.setCurrentDirectory(Settings.getCriticalHitSong());
		}
		jc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".mp3");
			}
			
			@Override
			public String getDescription() {
				return "MP3s";
			}
		});
		
		if (jc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			getJTextFieldCriticalHit().setText(jc.getSelectedFile().getAbsolutePath());
		}
	}

	private void jButtonBrowseCriticalMissActionActionPerformed(ActionEvent event) {
		JFileChooser jc = new JFileChooser();
		jc.setDialogTitle("Select Critical Miss Song");
		if (Settings.getCriticalMissSong() != null && Settings.getCriticalMissSong().canRead()) {
			jc.setCurrentDirectory(Settings.getCriticalMissSong());
		}
		jc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".mp3");
			}
			
			@Override
			public String getDescription() {
				return "MP3s";
			}
		});
		
		if (jc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			getJTextFieldCriticalMiss().setText(jc.getSelectedFile().getAbsolutePath());
		}
	}

	private void jButtonBrowseDailyActionActionPerformed(ActionEvent event) {
		JFileChooser jc = new JFileChooser();
		jc.setDialogTitle("Select Daily Song");
		if (Settings.getDailySong() != null && Settings.getDailySong().canRead()) {
			jc.setCurrentDirectory(Settings.getDailySong());
		}
		jc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".mp3");
			}
			
			@Override
			public String getDescription() {
				return "MP3s";
			}
		});
		
		if (jc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			getJTextFieldDaily().setText(jc.getSelectedFile().getAbsolutePath());
		}
	}

	private void jButtonBrowseVictoryActionActionPerformed(ActionEvent event) {
		JFileChooser jc = new JFileChooser();
		jc.setDialogTitle("Select Victory Song");
		if (Settings.getVictorySong() != null && Settings.getVictorySong().canRead()) {
			jc.setCurrentDirectory(Settings.getVictorySong());
		}
		jc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".mp3");
			}
			
			@Override
			public String getDescription() {
				return "MP3s";
			}
		});
		
		if (jc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			getJTextFieldVictory().setText(jc.getSelectedFile().getAbsolutePath());
		}
	}

	private void jButtonBrowseRootActionActionPerformed(ActionEvent event) {
		JFileChooser jc = new JFileChooser();
		jc.setDialogTitle("Select Music Root");
		jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jc.setAcceptAllFileFilterUsed(false);
		if (Settings.getMusicDirectory() != null && Settings.getMusicDirectory().exists()) {
			jc.setCurrentDirectory(Settings.getMusicDirectory());
		}
		jc.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}
			
			@Override
			public String getDescription() {
				return "Root Directory";
			}
		});
		
		if (jc.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
			getJTextFieldRootDirectory().setText(jc.getSelectedFile().getAbsolutePath());
		}
	}

	private void jButtonCancelActionActionPerformed(ActionEvent event) {
		this.dispose();
	}

	private void jButtonOKActionActionPerformed(ActionEvent event) {
		File rootDirectory = new File(getJTextFieldRootDirectory().getText());
		File criticalHit = new File(getJTextFieldCriticalHit().getText());
		File criticalMiss = new File(getJTextFieldCriticalMiss().getText());
		File victory = new File(getJTextFieldVictory().getText());
		File daily = new File(getJTextFieldDaily().getText());
		
		if (!rootDirectory.getPath().isEmpty() && !(rootDirectory.exists() && rootDirectory.isDirectory())) {
			JOptionPane.showMessageDialog(this, "Music directory does not exist or is not a directory.", "Invalid Music Directory", JOptionPane.ERROR_MESSAGE);
		} else if (!criticalHit.getPath().isEmpty() && !(criticalHit.exists() && criticalHit.canRead())) {
			JOptionPane.showMessageDialog(this, "Critical Hit song does not exist or is not readable.", "Invalid Critical Hit", JOptionPane.ERROR_MESSAGE);
		} else if (!criticalMiss.getPath().isEmpty() && !(criticalMiss.exists() && criticalMiss.canRead())) {
			JOptionPane.showMessageDialog(this, "Critical Miss song does not exist or is not readable.", "Invalid Critical Miss", JOptionPane.ERROR_MESSAGE);
		} else if (!victory.getPath().isEmpty() && !(victory.exists() && victory.canRead())) {
			JOptionPane.showMessageDialog(this, "Victory song does not exist or is not readable.", "Invalid Victory", JOptionPane.ERROR_MESSAGE);
		} else if (!daily.getPath().isEmpty() && !(daily.exists() && daily.canRead())) {
			JOptionPane.showMessageDialog(this, "Daily song does not exist or is not readable.", "Invalid Daily", JOptionPane.ERROR_MESSAGE);
		} else {
			Settings.setDoPowerRecharge(getJCheckBoxPowerRecharges().isSelected());
			Settings.setDoSavingThrows(getJCheckBoxSavingThrows().isSelected());
			Settings.setGroupSimilar(getJCheckBoxGroupSimilar().isSelected());
			Settings.setPopupForOngoingDamage(getJCheckBoxOngoingPopup().isSelected());
			Settings.setMusicDirectory(rootDirectory);
			Settings.setCriticalHitSong(criticalHit);
			Settings.setCriticalMissSong(criticalMiss);
			Settings.setVictorySong(victory);
			Settings.setDailySong(daily);
			
			this.setVisible(false);
		}
	}
}
