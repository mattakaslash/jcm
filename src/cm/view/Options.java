/**
 * 
 */
package cm.view;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import cm.model.Settings;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

/**
 * Defines a program options window.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 2.0
 */
// VS4E -- DO NOT REMOVE THIS LINE!
public class Options extends JDialog {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 1948071096317227461L;
	private JTextField _textFieldRootDirectory;
	private JTextField _textFieldCriticalHit;
	private JTextField _textFieldCriticalMiss;
	private JTextField _textFieldVictory;
	private JTextField _textFieldDaily;
	private JCheckBox _chckbxGroupSimilar;
	private JCheckBox _chckbxSavingThrows;
	private JCheckBox _chckbxPowerRecharges;
	private JCheckBox _chckbxPopup;

	/**
	 * Defines the options window and loads settings into their respective
	 * controls.
	 */
	public Options() {
		setTitle("Options");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		_chckbxGroupSimilar = new JCheckBox("Group Similar Combatants when Rolling Initiative");
		_chckbxGroupSimilar.setSelected(true);
		GridBagConstraints gbc__chckbxGroupSimilar = new GridBagConstraints();
		gbc__chckbxGroupSimilar.anchor = GridBagConstraints.WEST;
		gbc__chckbxGroupSimilar.insets = new Insets(0, 0, 5, 5);
		gbc__chckbxGroupSimilar.gridx = 0;
		gbc__chckbxGroupSimilar.gridy = 0;
		getContentPane().add(_chckbxGroupSimilar, gbc__chckbxGroupSimilar);

		JPanel panelRolls = new JPanel();
		panelRolls.setBorder(new TitledBorder(null, "Automatic Rolls", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelRolls = new GridBagConstraints();
		gbc_panelRolls.gridheight = 2;
		gbc_panelRolls.insets = new Insets(0, 0, 5, 0);
		gbc_panelRolls.fill = GridBagConstraints.BOTH;
		gbc_panelRolls.gridx = 1;
		gbc_panelRolls.gridy = 0;
		getContentPane().add(panelRolls, gbc_panelRolls);
		GridBagLayout gbl_panelRolls = new GridBagLayout();
		gbl_panelRolls.columnWidths = new int[] { 95, 109, 0 };
		gbl_panelRolls.rowHeights = new int[] { 23, 0 };
		gbl_panelRolls.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelRolls.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelRolls.setLayout(gbl_panelRolls);

		_chckbxSavingThrows = new JCheckBox("Saving Throws");
		_chckbxSavingThrows.setSelected(true);
		GridBagConstraints gbc__chckbxSavingThrows = new GridBagConstraints();
		gbc__chckbxSavingThrows.anchor = GridBagConstraints.NORTHWEST;
		gbc__chckbxSavingThrows.insets = new Insets(0, 0, 0, 5);
		gbc__chckbxSavingThrows.gridx = 0;
		gbc__chckbxSavingThrows.gridy = 0;
		panelRolls.add(_chckbxSavingThrows, gbc__chckbxSavingThrows);

		_chckbxPowerRecharges = new JCheckBox("Power Recharges");
		_chckbxPowerRecharges.setSelected(true);
		GridBagConstraints gbc__chckbxPowerRecharges = new GridBagConstraints();
		gbc__chckbxPowerRecharges.anchor = GridBagConstraints.NORTHWEST;
		gbc__chckbxPowerRecharges.gridx = 1;
		gbc__chckbxPowerRecharges.gridy = 0;
		panelRolls.add(_chckbxPowerRecharges, gbc__chckbxPowerRecharges);

		_chckbxPopup = new JCheckBox("Display Popup for Ongoing Effects");
		_chckbxPopup.setSelected(true);
		GridBagConstraints gbc__chckbxPopup = new GridBagConstraints();
		gbc__chckbxPopup.anchor = GridBagConstraints.WEST;
		gbc__chckbxPopup.insets = new Insets(0, 0, 5, 5);
		gbc__chckbxPopup.gridx = 0;
		gbc__chckbxPopup.gridy = 1;
		getContentPane().add(_chckbxPopup, gbc__chckbxPopup);

		JPanel panelMusicLocations = new JPanel();
		panelMusicLocations
				.setBorder(new TitledBorder(null, "Music Locations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelMusicLocations = new GridBagConstraints();
		gbc_panelMusicLocations.insets = new Insets(0, 0, 5, 0);
		gbc_panelMusicLocations.gridwidth = 2;
		gbc_panelMusicLocations.fill = GridBagConstraints.BOTH;
		gbc_panelMusicLocations.gridx = 0;
		gbc_panelMusicLocations.gridy = 2;
		getContentPane().add(panelMusicLocations, gbc_panelMusicLocations);
		GridBagLayout gbl_panelMusicLocations = new GridBagLayout();
		gbl_panelMusicLocations.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panelMusicLocations.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		panelMusicLocations.setLayout(gbl_panelMusicLocations);

		JLabel lblRootDirectory = new JLabel("Root Directory");
		GridBagConstraints gbc_lblRootDirectory = new GridBagConstraints();
		gbc_lblRootDirectory.anchor = GridBagConstraints.EAST;
		gbc_lblRootDirectory.insets = new Insets(0, 0, 5, 5);
		gbc_lblRootDirectory.gridx = 0;
		gbc_lblRootDirectory.gridy = 0;
		panelMusicLocations.add(lblRootDirectory, gbc_lblRootDirectory);

		_textFieldRootDirectory = new JTextField();
		GridBagConstraints gbc_textFieldRootDirectory = new GridBagConstraints();
		gbc_textFieldRootDirectory.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRootDirectory.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRootDirectory.gridx = 1;
		gbc_textFieldRootDirectory.gridy = 0;
		panelMusicLocations.add(_textFieldRootDirectory, gbc_textFieldRootDirectory);
		_textFieldRootDirectory.setColumns(10);

		JLabel lblCriticalHit = new JLabel("Critical Hit");
		GridBagConstraints gbc_lblCriticalHit = new GridBagConstraints();
		gbc_lblCriticalHit.anchor = GridBagConstraints.EAST;
		gbc_lblCriticalHit.insets = new Insets(0, 0, 5, 5);
		gbc_lblCriticalHit.gridx = 0;
		gbc_lblCriticalHit.gridy = 1;
		panelMusicLocations.add(lblCriticalHit, gbc_lblCriticalHit);

		_textFieldCriticalHit = new JTextField();
		GridBagConstraints gbc_textFieldCriticalHit = new GridBagConstraints();
		gbc_textFieldCriticalHit.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCriticalHit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCriticalHit.gridx = 1;
		gbc_textFieldCriticalHit.gridy = 1;
		panelMusicLocations.add(_textFieldCriticalHit, gbc_textFieldCriticalHit);
		_textFieldCriticalHit.setColumns(10);

		JButton btnCriticalHit = new JButton("Browse...");
		btnCriticalHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBrowseCriticalHitActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnCriticalHit = new GridBagConstraints();
		gbc_btnCriticalHit.insets = new Insets(0, 0, 5, 0);
		gbc_btnCriticalHit.gridx = 2;
		gbc_btnCriticalHit.gridy = 1;
		panelMusicLocations.add(btnCriticalHit, gbc_btnCriticalHit);

		JLabel lblCriticalMiss = new JLabel("Critical Miss");
		GridBagConstraints gbc_lblCriticalMiss = new GridBagConstraints();
		gbc_lblCriticalMiss.anchor = GridBagConstraints.EAST;
		gbc_lblCriticalMiss.insets = new Insets(0, 0, 5, 5);
		gbc_lblCriticalMiss.gridx = 0;
		gbc_lblCriticalMiss.gridy = 2;
		panelMusicLocations.add(lblCriticalMiss, gbc_lblCriticalMiss);

		_textFieldCriticalMiss = new JTextField();
		GridBagConstraints gbc_textFieldCriticalMiss = new GridBagConstraints();
		gbc_textFieldCriticalMiss.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCriticalMiss.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCriticalMiss.gridx = 1;
		gbc_textFieldCriticalMiss.gridy = 2;
		panelMusicLocations.add(_textFieldCriticalMiss, gbc_textFieldCriticalMiss);
		_textFieldCriticalMiss.setColumns(10);

		JButton btnCriticalMiss = new JButton("Browse...");
		btnCriticalMiss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBrowseCriticalMissActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnCriticalMiss = new GridBagConstraints();
		gbc_btnCriticalMiss.insets = new Insets(0, 0, 5, 0);
		gbc_btnCriticalMiss.gridx = 2;
		gbc_btnCriticalMiss.gridy = 2;
		panelMusicLocations.add(btnCriticalMiss, gbc_btnCriticalMiss);

		JLabel lblVictory = new JLabel("Victory");
		GridBagConstraints gbc_lblVictory = new GridBagConstraints();
		gbc_lblVictory.anchor = GridBagConstraints.EAST;
		gbc_lblVictory.insets = new Insets(0, 0, 5, 5);
		gbc_lblVictory.gridx = 0;
		gbc_lblVictory.gridy = 3;
		panelMusicLocations.add(lblVictory, gbc_lblVictory);

		_textFieldVictory = new JTextField();
		GridBagConstraints gbc_textFieldVictory = new GridBagConstraints();
		gbc_textFieldVictory.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVictory.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVictory.gridx = 1;
		gbc_textFieldVictory.gridy = 3;
		panelMusicLocations.add(_textFieldVictory, gbc_textFieldVictory);
		_textFieldVictory.setColumns(10);

		JButton btnVictory = new JButton("Browse...");
		btnVictory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBrowseVictoryActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnVictory = new GridBagConstraints();
		gbc_btnVictory.insets = new Insets(0, 0, 5, 0);
		gbc_btnVictory.gridx = 2;
		gbc_btnVictory.gridy = 3;
		panelMusicLocations.add(btnVictory, gbc_btnVictory);

		JLabel lblDaily = new JLabel("Daily");
		GridBagConstraints gbc_lblDaily = new GridBagConstraints();
		gbc_lblDaily.anchor = GridBagConstraints.EAST;
		gbc_lblDaily.insets = new Insets(0, 0, 0, 5);
		gbc_lblDaily.gridx = 0;
		gbc_lblDaily.gridy = 4;
		panelMusicLocations.add(lblDaily, gbc_lblDaily);

		_textFieldDaily = new JTextField();
		GridBagConstraints gbc_textFieldDaily = new GridBagConstraints();
		gbc_textFieldDaily.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldDaily.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDaily.gridx = 1;
		gbc_textFieldDaily.gridy = 4;
		panelMusicLocations.add(_textFieldDaily, gbc_textFieldDaily);
		_textFieldDaily.setColumns(10);

		JButton btnRootDirectory = new JButton("Browse...");
		btnRootDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBrowseRootActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnRootDirectory = new GridBagConstraints();
		gbc_btnRootDirectory.insets = new Insets(0, 0, 5, 0);
		gbc_btnRootDirectory.gridx = 2;
		gbc_btnRootDirectory.gridy = 0;
		panelMusicLocations.add(btnRootDirectory, gbc_btnRootDirectory);

		JButton btnDaily = new JButton("Browse...");
		btnDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBrowseDailyActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnDaily = new GridBagConstraints();
		gbc_btnDaily.gridx = 2;
		gbc_btnDaily.gridy = 4;
		panelMusicLocations.add(btnDaily, gbc_btnDaily);

		JPanel panelCancelOK = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCancelOK.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panelCancelOK = new GridBagConstraints();
		gbc_panelCancelOK.gridwidth = 2;
		gbc_panelCancelOK.fill = GridBagConstraints.BOTH;
		gbc_panelCancelOK.gridx = 0;
		gbc_panelCancelOK.gridy = 3;
		getContentPane().add(panelCancelOK, gbc_panelCancelOK);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCancelActionActionPerformed(e);
			}
		});
		panelCancelOK.add(btnCancel);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonOKActionActionPerformed(e);
			}
		});
		panelCancelOK.add(btnOk);
		pack();
		_chckbxGroupSimilar.setSelected(Settings.doGroupSimilar());
		_chckbxPopup.setSelected(Settings.doPopupForOngoingDamage());
		_chckbxPowerRecharges.setSelected(Settings.doPowerRecharge());
		_chckbxSavingThrows.setSelected(Settings.doSavingThrows());

		if (Settings.getMusicDirectory() != null) {
			_textFieldRootDirectory.setText(Settings.getMusicDirectory().getAbsolutePath());
		}
		if (Settings.getCriticalHitSong() != null) {
			_textFieldCriticalHit.setText(Settings.getCriticalHitSong().getAbsolutePath());
		}
		if (Settings.getCriticalMissSong() != null) {
			_textFieldCriticalMiss.setText(Settings.getCriticalMissSong().getAbsolutePath());
		}
		if (Settings.getVictorySong() != null) {
			_textFieldVictory.setText(Settings.getVictorySong().getAbsolutePath());
		}
		if (Settings.getDailySong() != null) {
			_textFieldDaily.setText(Settings.getDailySong().getAbsolutePath());
		}
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
			_textFieldCriticalHit.setText(jc.getSelectedFile().getAbsolutePath());
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
			_textFieldCriticalMiss.setText(jc.getSelectedFile().getAbsolutePath());
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
			_textFieldDaily.setText(jc.getSelectedFile().getAbsolutePath());
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
			_textFieldVictory.setText(jc.getSelectedFile().getAbsolutePath());
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
			_textFieldRootDirectory.setText(jc.getSelectedFile().getAbsolutePath());
		}
	}

	private void jButtonCancelActionActionPerformed(ActionEvent event) {
		this.dispose();
	}

	private void jButtonOKActionActionPerformed(ActionEvent event) {
		File rootDirectory = new File(_textFieldRootDirectory.getText());
		File criticalHit = new File(_textFieldCriticalHit.getText());
		File criticalMiss = new File(_textFieldCriticalMiss.getText());
		File victory = new File(_textFieldVictory.getText());
		File daily = new File(_textFieldDaily.getText());

		if (!rootDirectory.getPath().isEmpty() && !(rootDirectory.exists() && rootDirectory.isDirectory())) {
			JOptionPane.showMessageDialog(this, "Music directory does not exist or is not a directory.", "Invalid Music Directory",
					JOptionPane.ERROR_MESSAGE);
		} else if (!criticalHit.getPath().isEmpty() && !(criticalHit.exists() && criticalHit.canRead())) {
			JOptionPane.showMessageDialog(this, "Critical Hit song does not exist or is not readable.", "Invalid Critical Hit",
					JOptionPane.ERROR_MESSAGE);
		} else if (!criticalMiss.getPath().isEmpty() && !(criticalMiss.exists() && criticalMiss.canRead())) {
			JOptionPane.showMessageDialog(this, "Critical Miss song does not exist or is not readable.", "Invalid Critical Miss",
					JOptionPane.ERROR_MESSAGE);
		} else if (!victory.getPath().isEmpty() && !(victory.exists() && victory.canRead())) {
			JOptionPane.showMessageDialog(this, "Victory song does not exist or is not readable.", "Invalid Victory",
					JOptionPane.ERROR_MESSAGE);
		} else if (!daily.getPath().isEmpty() && !(daily.exists() && daily.canRead())) {
			JOptionPane.showMessageDialog(this, "Daily song does not exist or is not readable.", "Invalid Daily",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Settings.setDoPowerRecharge(_chckbxPowerRecharges.isSelected());
			Settings.setDoSavingThrows(_chckbxSavingThrows.isSelected());
			Settings.setGroupSimilar(_chckbxGroupSimilar.isSelected());
			Settings.setPopupForOngoingDamage(_chckbxPopup.isSelected());
			Settings.setMusicDirectory(rootDirectory);
			Settings.setCriticalHitSong(criticalHit);
			Settings.setCriticalMissSong(criticalMiss);
			Settings.setVictorySong(victory);
			Settings.setDailySong(daily);

			this.setVisible(false);
		}
	}
}
