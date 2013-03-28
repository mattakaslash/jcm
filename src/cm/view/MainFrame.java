package cm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;

import cm.model.Combatant;
import cm.model.Effect;
import cm.model.EffectBase;
import cm.model.Encounter;
import cm.model.FighterPower;
import cm.model.Power;
import cm.model.ReadOnlyTableModel;
import cm.model.Settings;
import cm.model.StatLibrary;
import cm.model.Stats;
import cm.util.DiceBag;
import cm.util.StatLogger;
import cm.util.external.ColumnsAutoSizer;
import cm.util.music.Player;
import cm.view.render.EffectDetailsCellRenderer;
import cm.view.render.OffTurnPowerRenderer;
import cm.view.render.PlaylistCellRenderer;
import cm.view.render.PowerCellRenderer;
import cm.view.render.RosterRenderer;

import javax.swing.JTabbedPane;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextPane;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import javax.swing.JToggleButton;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyAdapter;
import java.beans.VetoableChangeListener;
import java.awt.event.ItemListener;

/**
 * Displays the main interface for the application.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class MainFrame extends JFrame {

	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 4468998349419197734L;

	/**
	 * The active encounter.
	 */
	private Encounter _fight;

	/**
	 * If true, full information is displayed on the initiative display.
	 */
	private Boolean _fullInit = false;

	/**
	 * The initiative display.
	 */
	private InitDisplay _initDisplay;

	/**
	 * The table renderer.
	 */
	private RosterRenderer _rosterRenderer = new RosterRenderer();

	/**
	 * The statistics library.
	 */
	private StatLibrary _statlib;

	/**
	 * The filename to save the statistics library to.
	 */
	private String _statlibFilename = "statlibrary.dnd4";

	private JPanel _contentPane;
	private JTable _tableRoster;
	private JTabbedPane _tabbedPaneBottomLeft;
	private JSplitPane _splitPaneMain;
	private JSplitPane _splitPaneMiddleRight;
	private JPanel _panelLeft;
	private JPanel _panelNotes;
	private JPanel _panelOffTurnPowers;
	private JPanel _panelMusic;
	private JTextField _textFieldXP;
	private JSplitPane _splitPaneMiddle;
	private JPanel _panelControls;
	private JPanel _panelPowers;
	private JLabel _labelName;
	private JTextField _textFieldName;
	private JTextField _textFieldNumber;
	private JTabbedPane _tabbedPaneControls;
	private JPanel _panelInitiative;
	private JPanel _panelDamageHealing;
	private JPanel _panelPoints;
	private JButton _buttonRemoveFighter;
	private JButton _buttonRollInitiative;
	private JButton _buttonMoveToTop;
	private JButton _buttonReserve;
	private JButton _buttonDelay;
	private JButton _buttonReady;
	private JLabel _labelPreviousRest;
	private JLabel _labelInitRoll;
	private JSpinner _spinnerInitRoll;
	private JPanel _panelNameLevel;
	private JButton _buttonNextTurn;
	private JButton _buttonBackUp;
	private JPanel _panelHealth;
	private JPanel _panelSurges;
	private JSpinner _spinnerAmount;
	private JButton _button5;
	private JButton _buttonPlus5;
	private JButton _buttonSurge;
	private JButton _buttonHalve;
	private JButton _buttonDamage;
	private JButton _buttonHeal;
	private JButton _buttonAddTemp;
	private JButton _buttonMax;
	private JButton _buttonFailDeath;
	private JButton _buttonUndoDeath;
	private JTextField _textFieldSurges;
	private JButton _buttonPlus1;
	private JButton _buttonMinus1;
	private JButton _buttonRegainAll;
	private JPanel _panelEffects;
	private JList _listEffects;
	private JPanel _panelEffectsButtons;
	private JButton _buttonAdd;
	private JButton _buttonChange;
	private JButton _buttonRemove;
	private JList _listPowers;
	private JSplitPane _splitPaneRight;
	private JTextPane _textPaneStatblock;
	private JTextPane _textPanePowerBrowser;
	private JPanel _panelActionPoints;
	private JPanel _panelPowerPoints;
	private JSpinner _spinnerActionPoints;
	private JCheckBox _chckbxSpent;
	private JSpinner _spinnerPowerPoints;
	private JButton _buttonMinus2;
	private JButton _buttonMinus4;
	private JButton _buttonMinus6;
	private JPanel _panelPowerPointsButtons;
	private JTextArea _textAreaNotes;
	private JList _listOffTurnPowers;
	private JPanel _panelNowPlaying;
	private JTextArea _textAreaNowPlaying;
	private JToggleButton _toggleButtonPlay;
	private JButton _buttonNext;
	private JPanel _panelCrits;
	private JToggleButton _toggleButtonHit;
	private JToggleButton _toggleButtonMiss;
	private JPanel _panelMisc;
	private JToggleButton _toggleButtonVictory;
	private JToggleButton _toggleButtonDaily;
	private JComboBox _comboBoxPlaylist;
	private JMenuBar _menuBar;
	private JMenu _menuFile;
	private JMenu _menuEncounter;
	private JMenu _menuParty;
	private JMenu _menuWindows;
	private JMenu _menuLibrary;
	private JMenu _menuHelp;
	private Component _horizontalGlue;
	private JMenuItem _menuItemNew;
	private JSeparator _separator;
	private JMenuItem _menuItemImport;
	private JSeparator _separator_1;
	private JMenuItem _menuItemOpen;
	private JMenuItem _menuItemSave;
	private JMenuItem _menuItemExit;
	private JSeparator _separator_2;
	private JMenuItem _menuItemRollInit;
	private JMenuItem _menuItemEnd;
	private JMenuItem _menuItemRemoveNPCs;
	private JMenuItem _menuItemShortRest;
	private JMenuItem _menuItemMilestone;
	private JSeparator _separator_3;
	private JMenuItem _menuItemRemovePCs;
	private JCheckBoxMenuItem _checkBoxMenuItemMinimalInitDisplay;
	private JMenuItem _menuItemExtendedRest;
	private JCheckBoxMenuItem _checkBoxMenuItemFullInitDisplay;
	private JSeparator _separator_4;
	private JMenuItem _menuItemOptions;
	private JMenu _menuInitDisplayFontSize;
	private JRadioButtonMenuItem _radioButtonMenuItemSmall;
	private JRadioButtonMenuItem _radioButtonMenuItemMedium;
	private JRadioButtonMenuItem _radioButtonMenuItemLarge;
	private JRadioButtonMenuItem _radioButtonMenuItemXLarge;
	private JRadioButtonMenuItem _radioButtonMenuItemXXLarge;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSeparator _separator_5;
	private JMenuItem _menuItemMoveInitDisplay;
	private JMenuItem _menuItemOpenLibrary;
	private JMenuItem _menuItemAbout;
	private JPopupMenu _popupMenuRoster;
	private JMenuItem _menuItemMoveToTop;
	private JMenuItem _menuItemDelay;
	private JMenuItem _menuItemReady;
	private JSeparator _separator_6;
	private JMenuItem _menuItemLogOA;
	private JSeparator _separator_7;
	private JMenuItem _menuItemMarkEONT;
	private JMenuItem _menuItemMarkEOE;
	private JSeparator _separator_8;
	private JMenuItem _menuItemToggleVisibility;
	private int _secondDisplayIndex = 1;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				windowWindowClosing(e);
			}

			@Override
			public void windowOpened(WindowEvent e) {
				windowWindowOpened(e);
			}
		});
		setTitle("DnD 4e Combat Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 543);

		_menuBar = new JMenuBar();
		setJMenuBar(_menuBar);

		_menuFile = new JMenu("File");
		_menuFile.setMnemonic('F');
		_menuBar.add(_menuFile);

		_menuItemNew = new JMenuItem("New Encounter");
		_menuItemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFileNewActionActionPerformed(e);
			}
		});
		_menuItemNew.setMnemonic('N');
		_menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		_menuFile.add(_menuItemNew);

		_separator = new JSeparator();
		_menuFile.add(_separator);

		_menuItemImport = new JMenuItem("Import");
		_menuItemImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFileImportActionActionPerformed(e);
			}
		});
		_menuItemImport.setMnemonic('I');
		_menuItemImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		_menuFile.add(_menuItemImport);

		_separator_1 = new JSeparator();
		_menuFile.add(_separator_1);

		_menuItemOpen = new JMenuItem("Open Encounter");
		_menuItemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFileOpenActionActionPerformed(e);
			}
		});
		_menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		_menuItemOpen.setMnemonic('O');
		_menuFile.add(_menuItemOpen);

		_menuItemSave = new JMenuItem("Save Encounter");
		_menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFileSaveActionActionPerformed(e);
			}
		});
		_menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		_menuItemSave.setMnemonic('S');
		_menuFile.add(_menuItemSave);

		_separator_2 = new JSeparator();
		_menuFile.add(_separator_2);

		_menuItemExit = new JMenuItem("Exit");
		_menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFileExitActionActionPerformed(e);
			}
		});
		_menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		_menuItemExit.setMnemonic('x');
		_menuFile.add(_menuItemExit);

		_menuEncounter = new JMenu("Encounter");
		_menuEncounter.setMnemonic('E');
		_menuBar.add(_menuEncounter);

		_menuItemRollInit = new JMenuItem("Roll Initiative");
		_menuItemRollInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEncounterInitiativeActionActionPerformed(e);
			}
		});
		_menuItemRollInit.setMnemonic('I');
		_menuItemRollInit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		_menuEncounter.add(_menuItemRollInit);

		_menuItemEnd = new JMenuItem("End Encounter");
		_menuItemEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEncounterEndActionActionPerformed(e);
			}
		});
		_menuItemEnd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		_menuItemEnd.setMnemonic('E');
		_menuEncounter.add(_menuItemEnd);

		_menuItemRemoveNPCs = new JMenuItem("Remove All Monsters");
		_menuItemRemoveNPCs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEncounterRemoveMonstersActionActionPerformed(e);
			}
		});
		_menuItemRemoveNPCs.setMnemonic('R');
		_menuItemRemoveNPCs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		_menuEncounter.add(_menuItemRemoveNPCs);

		_menuParty = new JMenu("Party");
		_menuParty.setMnemonic('P');
		_menuBar.add(_menuParty);

		_menuItemShortRest = new JMenuItem("Take Short Rest");
		_menuItemShortRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPartyShortRestActionActionPerformed(e);
			}
		});
		_menuItemShortRest.setMnemonic('R');
		_menuParty.add(_menuItemShortRest);

		_menuItemMilestone = new JMenuItem("Take Short Rest with Milestone");
		_menuItemMilestone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPartyShortRestMilestoneActionActionPerformed(e);
			}
		});
		_menuItemMilestone.setMnemonic('M');
		_menuParty.add(_menuItemMilestone);

		_menuItemExtendedRest = new JMenuItem("Take Extended Rest");
		_menuItemExtendedRest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPartyExtendedRestActionActionPerformed(e);
			}
		});
		_menuItemExtendedRest.setMnemonic('E');
		_menuParty.add(_menuItemExtendedRest);

		_separator_3 = new JSeparator();
		_menuParty.add(_separator_3);

		_menuItemRemovePCs = new JMenuItem("Remove Party from Roster");
		_menuItemRemovePCs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPartyRemoveActionActionPerformed(e);
			}
		});
		_menuItemRemovePCs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		_menuItemRemovePCs.setMnemonic('P');
		_menuParty.add(_menuItemRemovePCs);

		_menuWindows = new JMenu("Windows");
		_menuWindows.setMnemonic('W');
		_menuBar.add(_menuWindows);

		_checkBoxMenuItemMinimalInitDisplay = new JCheckBoxMenuItem("Minimal Init Display");
		_checkBoxMenuItemMinimalInitDisplay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				menuWindowsMinimalInitDisplayStateChanged(e);
			}
		});
		_checkBoxMenuItemMinimalInitDisplay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		_checkBoxMenuItemMinimalInitDisplay.setMnemonic('M');
		_menuWindows.add(_checkBoxMenuItemMinimalInitDisplay);

		_checkBoxMenuItemFullInitDisplay = new JCheckBoxMenuItem("Full Init Display");
		_checkBoxMenuItemFullInitDisplay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				menuWindowsFullInitDisplayStateChanged(e);
			}
		});
		_checkBoxMenuItemFullInitDisplay.setMnemonic('F');
		_checkBoxMenuItemFullInitDisplay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		_menuWindows.add(_checkBoxMenuItemFullInitDisplay);

		_separator_4 = new JSeparator();
		_menuWindows.add(_separator_4);

		_menuItemOptions = new JMenuItem("Options");
		_menuItemOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuWindowsOptionsActionActionPerformed(e);
			}
		});
		_menuItemOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
		_menuItemOptions.setMnemonic('O');
		_menuWindows.add(_menuItemOptions);

		_menuInitDisplayFontSize = new JMenu("Init Display Font Size");
		_menuWindows.add(_menuInitDisplayFontSize);

		_radioButtonMenuItemSmall = new JRadioButtonMenuItem("Small");
		_radioButtonMenuItemSmall.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fontSizeSelectionChanged(e);
			}
		});
		buttonGroup.add(_radioButtonMenuItemSmall);
		_menuInitDisplayFontSize.add(_radioButtonMenuItemSmall);

		_radioButtonMenuItemMedium = new JRadioButtonMenuItem("Medium");
		buttonGroup.add(_radioButtonMenuItemMedium);
		_radioButtonMenuItemMedium.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fontSizeSelectionChanged(e);
			}
		});
		_menuInitDisplayFontSize.add(_radioButtonMenuItemMedium);

		_radioButtonMenuItemLarge = new JRadioButtonMenuItem("Large");
		buttonGroup.add(_radioButtonMenuItemLarge);
		_radioButtonMenuItemLarge.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fontSizeSelectionChanged(e);
			}
		});
		_menuInitDisplayFontSize.add(_radioButtonMenuItemLarge);

		_radioButtonMenuItemXLarge = new JRadioButtonMenuItem("Extra Large");
		buttonGroup.add(_radioButtonMenuItemXLarge);
		_radioButtonMenuItemXLarge.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fontSizeSelectionChanged(e);
			}
		});
		_menuInitDisplayFontSize.add(_radioButtonMenuItemXLarge);

		_radioButtonMenuItemXXLarge = new JRadioButtonMenuItem("Extra Extra Large");
		buttonGroup.add(_radioButtonMenuItemXXLarge);
		_radioButtonMenuItemXXLarge.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				fontSizeSelectionChanged(e);
			}
		});
		_menuInitDisplayFontSize.add(_radioButtonMenuItemXXLarge);

		_menuItemMoveInitDisplay = new JMenuItem("Move Init Display");
		_menuItemMoveInitDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuWindowsMoveInitDisplayActionActionPerformed(e);
			}
		});
		_menuItemMoveInitDisplay
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		_menuWindows.add(_menuItemMoveInitDisplay);

		_separator_5 = new JSeparator();
		_menuWindows.add(_separator_5);

		_menuLibrary = new JMenu("Library");
		_menuLibrary.setMnemonic('L');
		_menuBar.add(_menuLibrary);

		_menuItemOpenLibrary = new JMenuItem("Open");
		_menuItemOpenLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuLibraryOpenActionActionPerformed(e);
			}
		});
		_menuItemOpenLibrary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		_menuLibrary.add(_menuItemOpenLibrary);

		_horizontalGlue = Box.createHorizontalGlue();
		_menuBar.add(_horizontalGlue);

		_menuHelp = new JMenu("Help");
		_menuHelp.setMnemonic('H');
		_menuHelp.setHorizontalAlignment(SwingConstants.LEFT);
		_menuBar.add(_menuHelp);

		_menuItemAbout = new JMenuItem("About");
		_menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuHelpAboutActionActionPerformed(e);
			}
		});
		_menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		_menuHelp.add(_menuItemAbout);
		_contentPane = new JPanel();
		_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(_contentPane);

		_splitPaneMain = new JSplitPane();
		_contentPane.add(_splitPaneMain, BorderLayout.CENTER);

		_splitPaneMiddleRight = new JSplitPane();
		_splitPaneMain.setRightComponent(_splitPaneMiddleRight);

		_splitPaneMiddle = new JSplitPane();
		_splitPaneMiddle.setOrientation(JSplitPane.VERTICAL_SPLIT);
		_splitPaneMiddleRight.setLeftComponent(_splitPaneMiddle);

		_panelControls = new JPanel();
		_splitPaneMiddle.setLeftComponent(_panelControls);
		GridBagLayout gbl_panelControls = new GridBagLayout();
		gbl_panelControls.columnWeights = new double[] { 1.0, 0.0 };
		gbl_panelControls.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0 };
		_panelControls.setLayout(gbl_panelControls);

		_tabbedPaneControls = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPaneControls = new GridBagConstraints();
		gbc_tabbedPaneControls.gridwidth = 2;
		gbc_tabbedPaneControls.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPaneControls.fill = GridBagConstraints.BOTH;
		gbc_tabbedPaneControls.gridx = 0;
		gbc_tabbedPaneControls.gridy = 1;
		_panelControls.add(_tabbedPaneControls, gbc_tabbedPaneControls);

		_panelInitiative = new JPanel();
		_tabbedPaneControls.addTab("Initiative", null, _panelInitiative, null);
		GridBagLayout gbl_panelInitiative = new GridBagLayout();
		gbl_panelInitiative.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		gbl_panelInitiative.rowWeights = new double[] { 1.0, 1.0, 1.0 };
		_panelInitiative.setLayout(gbl_panelInitiative);

		_buttonRemoveFighter = new JButton("Remove Fighter");
		_buttonRemoveFighter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRemoveFighterActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonRemoveFighter = new GridBagConstraints();
		gbc_buttonRemoveFighter.fill = GridBagConstraints.BOTH;
		gbc_buttonRemoveFighter.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRemoveFighter.gridx = 0;
		gbc_buttonRemoveFighter.gridy = 0;
		_panelInitiative.add(_buttonRemoveFighter, gbc_buttonRemoveFighter);

		_buttonRollInitiative = new JButton("Roll Initiative");
		_buttonRollInitiative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRollInitiativeActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonRollInitiative = new GridBagConstraints();
		gbc_buttonRollInitiative.fill = GridBagConstraints.BOTH;
		gbc_buttonRollInitiative.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRollInitiative.gridx = 1;
		gbc_buttonRollInitiative.gridy = 0;
		_panelInitiative.add(_buttonRollInitiative, gbc_buttonRollInitiative);

		_buttonMoveToTop = new JButton("Move to Top");
		_buttonMoveToTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonMoveToTopActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonMoveToTop = new GridBagConstraints();
		gbc_buttonMoveToTop.fill = GridBagConstraints.BOTH;
		gbc_buttonMoveToTop.insets = new Insets(0, 0, 5, 5);
		gbc_buttonMoveToTop.gridx = 2;
		gbc_buttonMoveToTop.gridy = 0;
		_panelInitiative.add(_buttonMoveToTop, gbc_buttonMoveToTop);

		_buttonReserve = new JButton("Reserve");
		_buttonReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonReserveActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonReserve = new GridBagConstraints();
		gbc_buttonReserve.fill = GridBagConstraints.BOTH;
		gbc_buttonReserve.insets = new Insets(0, 0, 5, 5);
		gbc_buttonReserve.gridx = 0;
		gbc_buttonReserve.gridy = 1;
		_panelInitiative.add(_buttonReserve, gbc_buttonReserve);

		_buttonDelay = new JButton("Delay");
		_buttonDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonDelayActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_Delay = new GridBagConstraints();
		gbc_Delay.fill = GridBagConstraints.BOTH;
		gbc_Delay.insets = new Insets(0, 0, 5, 5);
		gbc_Delay.gridx = 1;
		gbc_Delay.gridy = 1;
		_panelInitiative.add(_buttonDelay, gbc_Delay);

		_buttonReady = new JButton("Ready");
		_buttonReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonReadyActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonReady = new GridBagConstraints();
		gbc_buttonReady.insets = new Insets(0, 0, 5, 5);
		gbc_buttonReady.fill = GridBagConstraints.BOTH;
		gbc_buttonReady.gridx = 2;
		gbc_buttonReady.gridy = 1;
		_panelInitiative.add(_buttonReady, gbc_buttonReady);

		_spinnerInitRoll = new JSpinner();
		_spinnerInitRoll.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				jSpinnerInitRollKeyKeyPressed(e);
			}
		});
		GridBagConstraints gbc_spinnerInitRoll = new GridBagConstraints();
		gbc_spinnerInitRoll.fill = GridBagConstraints.BOTH;
		gbc_spinnerInitRoll.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerInitRoll.gridx = 3;
		gbc_spinnerInitRoll.gridy = 1;
		_panelInitiative.add(_spinnerInitRoll, gbc_spinnerInitRoll);

		_labelPreviousRest = new JLabel("Previous Rest: (none)");
		_labelPreviousRest.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_labelPreviousRest = new GridBagConstraints();
		gbc_labelPreviousRest.fill = GridBagConstraints.VERTICAL;
		gbc_labelPreviousRest.anchor = GridBagConstraints.WEST;
		gbc_labelPreviousRest.gridwidth = 3;
		gbc_labelPreviousRest.insets = new Insets(0, 0, 0, 5);
		gbc_labelPreviousRest.gridx = 0;
		gbc_labelPreviousRest.gridy = 2;
		_panelInitiative.add(_labelPreviousRest, gbc_labelPreviousRest);

		_labelInitRoll = new JLabel("Init Roll");
		GridBagConstraints gbc_labelInitRoll = new GridBagConstraints();
		gbc_labelInitRoll.fill = GridBagConstraints.VERTICAL;
		gbc_labelInitRoll.insets = new Insets(0, 0, 5, 0);
		gbc_labelInitRoll.gridx = 3;
		gbc_labelInitRoll.gridy = 0;
		_panelInitiative.add(_labelInitRoll, gbc_labelInitRoll);

		_panelDamageHealing = new JPanel();
		_tabbedPaneControls.addTab("Damage/Healing", null, _panelDamageHealing, null);
		GridBagLayout gbl_panelDamageHealing = new GridBagLayout();
		gbl_panelDamageHealing.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelDamageHealing.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelDamageHealing.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDamageHealing.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		_panelDamageHealing.setLayout(gbl_panelDamageHealing);

		_panelHealth = new JPanel();
		_panelHealth.setBorder(new TitledBorder(null, "Health", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelHealth = new GridBagConstraints();
		gbc_panelHealth.insets = new Insets(0, 0, 5, 0);
		gbc_panelHealth.fill = GridBagConstraints.BOTH;
		gbc_panelHealth.gridx = 0;
		gbc_panelHealth.gridy = 0;
		_panelDamageHealing.add(_panelHealth, gbc_panelHealth);
		GridBagLayout gbl_panelHealth = new GridBagLayout();
		gbl_panelHealth.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelHealth.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelHealth.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gbl_panelHealth.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		_panelHealth.setLayout(gbl_panelHealth);

		_spinnerAmount = new JSpinner();
		_spinnerAmount.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				jSpinnerDamageHealAmountChangeStateChanged(e);
			}
		});
		GridBagConstraints gbc_spinnerAmount = new GridBagConstraints();
		gbc_spinnerAmount.fill = GridBagConstraints.BOTH;
		gbc_spinnerAmount.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerAmount.gridwidth = 2;
		gbc_spinnerAmount.gridx = 0;
		gbc_spinnerAmount.gridy = 0;
		_panelHealth.add(_spinnerAmount, gbc_spinnerAmount);

		_button5 = new JButton("5");
		_button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonFiveActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_button5 = new GridBagConstraints();
		gbc_button5.fill = GridBagConstraints.BOTH;
		gbc_button5.insets = new Insets(0, 0, 5, 5);
		gbc_button5.gridx = 0;
		gbc_button5.gridy = 1;
		_panelHealth.add(_button5, gbc_button5);

		_buttonPlus5 = new JButton("+5");
		_buttonPlus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPlusFiveActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonPlus5 = new GridBagConstraints();
		gbc_buttonPlus5.fill = GridBagConstraints.BOTH;
		gbc_buttonPlus5.insets = new Insets(0, 0, 5, 5);
		gbc_buttonPlus5.gridx = 1;
		gbc_buttonPlus5.gridy = 1;
		_panelHealth.add(_buttonPlus5, gbc_buttonPlus5);

		_buttonMax = new JButton("Max");
		_buttonMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonMaxActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonMax = new GridBagConstraints();
		gbc_buttonMax.fill = GridBagConstraints.BOTH;
		gbc_buttonMax.insets = new Insets(0, 0, 5, 0);
		gbc_buttonMax.gridx = 3;
		gbc_buttonMax.gridy = 1;
		_panelHealth.add(_buttonMax, gbc_buttonMax);

		_buttonSurge = new JButton("Surge");
		_buttonSurge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSurgeActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonSurge = new GridBagConstraints();
		gbc_buttonSurge.insets = new Insets(0, 0, 5, 5);
		gbc_buttonSurge.fill = GridBagConstraints.BOTH;
		gbc_buttonSurge.gridwidth = 2;
		gbc_buttonSurge.gridx = 0;
		gbc_buttonSurge.gridy = 2;
		_panelHealth.add(_buttonSurge, gbc_buttonSurge);

		_buttonHeal = new JButton("Heal");
		_buttonHeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonHealActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonHeal = new GridBagConstraints();
		gbc_buttonHeal.fill = GridBagConstraints.BOTH;
		gbc_buttonHeal.gridheight = 2;
		gbc_buttonHeal.insets = new Insets(0, 0, 0, 5);
		gbc_buttonHeal.gridx = 2;
		gbc_buttonHeal.gridy = 2;
		_panelHealth.add(_buttonHeal, gbc_buttonHeal);

		_buttonFailDeath = new JButton("Fail Death");
		_buttonFailDeath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonFailDeathActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonFailDeath = new GridBagConstraints();
		gbc_buttonFailDeath.fill = GridBagConstraints.BOTH;
		gbc_buttonFailDeath.insets = new Insets(0, 0, 5, 0);
		gbc_buttonFailDeath.gridx = 3;
		gbc_buttonFailDeath.gridy = 2;
		_panelHealth.add(_buttonFailDeath, gbc_buttonFailDeath);

		_buttonHalve = new JButton("Halve");
		_buttonHalve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonHalveActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonHalve = new GridBagConstraints();
		gbc_buttonHalve.insets = new Insets(0, 0, 0, 5);
		gbc_buttonHalve.fill = GridBagConstraints.BOTH;
		gbc_buttonHalve.gridwidth = 2;
		gbc_buttonHalve.gridx = 0;
		gbc_buttonHalve.gridy = 3;
		_panelHealth.add(_buttonHalve, gbc_buttonHalve);

		_buttonDamage = new JButton("Damage");
		_buttonDamage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonDamageActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonDamage = new GridBagConstraints();
		gbc_buttonDamage.insets = new Insets(0, 0, 5, 5);
		gbc_buttonDamage.fill = GridBagConstraints.BOTH;
		gbc_buttonDamage.gridheight = 2;
		gbc_buttonDamage.gridx = 2;
		gbc_buttonDamage.gridy = 0;
		_panelHealth.add(_buttonDamage, gbc_buttonDamage);

		_buttonAddTemp = new JButton("Add Temp");
		_buttonAddTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAddTempActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonAddTemp = new GridBagConstraints();
		gbc_buttonAddTemp.insets = new Insets(0, 0, 5, 0);
		gbc_buttonAddTemp.fill = GridBagConstraints.BOTH;
		gbc_buttonAddTemp.gridx = 3;
		gbc_buttonAddTemp.gridy = 0;
		_panelHealth.add(_buttonAddTemp, gbc_buttonAddTemp);

		_buttonUndoDeath = new JButton("Undo Death");
		_buttonUndoDeath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonUndoDeathActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonUndoDeath = new GridBagConstraints();
		gbc_buttonUndoDeath.fill = GridBagConstraints.BOTH;
		gbc_buttonUndoDeath.gridx = 3;
		gbc_buttonUndoDeath.gridy = 3;
		_panelHealth.add(_buttonUndoDeath, gbc_buttonUndoDeath);

		_panelSurges = new JPanel();
		_panelSurges.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Surges", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 70, 213)));
		GridBagConstraints gbc_panelSurges = new GridBagConstraints();
		gbc_panelSurges.fill = GridBagConstraints.BOTH;
		gbc_panelSurges.gridx = 1;
		gbc_panelSurges.gridy = 0;
		_panelDamageHealing.add(_panelSurges, gbc_panelSurges);
		GridBagLayout gbl_panelSurges = new GridBagLayout();
		gbl_panelSurges.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelSurges.columnWidths = new int[] { 0, 0 };
		gbl_panelSurges.columnWeights = new double[] { 0.0, 0.0 };
		gbl_panelSurges.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		_panelSurges.setLayout(gbl_panelSurges);

		_textFieldSurges = new JTextField();
		_textFieldSurges.setEditable(false);
		_textFieldSurges.setHorizontalAlignment(SwingConstants.CENTER);
		_textFieldSurges.setFont(new Font("Tahoma", Font.BOLD, 11));
		_textFieldSurges.setText("0/0");
		GridBagConstraints gbc_textFieldSurges = new GridBagConstraints();
		gbc_textFieldSurges.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSurges.gridwidth = 2;
		gbc_textFieldSurges.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSurges.gridx = 0;
		gbc_textFieldSurges.gridy = 0;
		_panelSurges.add(_textFieldSurges, gbc_textFieldSurges);
		_textFieldSurges.setColumns(10);

		_buttonPlus1 = new JButton("+1");
		_buttonPlus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPlusOneActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonPlus1 = new GridBagConstraints();
		gbc_buttonPlus1.insets = new Insets(0, 0, 5, 5);
		gbc_buttonPlus1.fill = GridBagConstraints.BOTH;
		gbc_buttonPlus1.gridx = 0;
		gbc_buttonPlus1.gridy = 1;
		_panelSurges.add(_buttonPlus1, gbc_buttonPlus1);

		_buttonMinus1 = new JButton("-1");
		_buttonMinus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonMinusOneActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonMinus1 = new GridBagConstraints();
		gbc_buttonMinus1.insets = new Insets(0, 0, 5, 0);
		gbc_buttonMinus1.fill = GridBagConstraints.BOTH;
		gbc_buttonMinus1.gridx = 1;
		gbc_buttonMinus1.gridy = 1;
		_panelSurges.add(_buttonMinus1, gbc_buttonMinus1);

		_buttonRegainAll = new JButton("Regain All");
		_buttonRegainAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRegainAllActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonRegainAll = new GridBagConstraints();
		gbc_buttonRegainAll.gridwidth = 2;
		gbc_buttonRegainAll.gridheight = 2;
		gbc_buttonRegainAll.fill = GridBagConstraints.BOTH;
		gbc_buttonRegainAll.insets = new Insets(0, 0, 0, 5);
		gbc_buttonRegainAll.gridx = 0;
		gbc_buttonRegainAll.gridy = 2;
		_panelSurges.add(_buttonRegainAll, gbc_buttonRegainAll);

		_panelPoints = new JPanel();
		_tabbedPaneControls.addTab("Points", null, _panelPoints, null);
		_panelPoints.setLayout(new GridLayout(1, 0, 0, 0));

		_panelActionPoints = new JPanel();
		_panelActionPoints.setBorder(new TitledBorder(null, "Action Points", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		_panelPoints.add(_panelActionPoints);
		_panelActionPoints.setLayout(new BorderLayout(0, 0));

		_spinnerActionPoints = new JSpinner();
		_spinnerActionPoints.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				jSpinnerActionPointsKeyKeyPressed(e);
			}
		});
		_spinnerActionPoints.setFont(new Font("Tahoma", Font.PLAIN, 18));
		_panelActionPoints.add(_spinnerActionPoints, BorderLayout.CENTER);

		_chckbxSpent = new JCheckBox("Spent");
		_chckbxSpent.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				jCheckBoxSpentChangeStateChanged(e);
			}
		});
		_chckbxSpent.setHorizontalAlignment(SwingConstants.CENTER);
		_panelActionPoints.add(_chckbxSpent, BorderLayout.SOUTH);

		_panelPowerPoints = new JPanel();
		_panelPowerPoints.setBorder(new TitledBorder(null, "Power Points", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		_panelPoints.add(_panelPowerPoints);
		_panelPowerPoints.setLayout(new BorderLayout(0, 0));

		_spinnerPowerPoints = new JSpinner();
		_spinnerPowerPoints.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				jSpinnerPowerPointsKeyKeyPressed(e);
			}
		});
		_spinnerPowerPoints.setFont(new Font("Tahoma", Font.PLAIN, 18));
		_panelPowerPoints.add(_spinnerPowerPoints);

		_panelPowerPointsButtons = new JPanel();
		_panelPowerPoints.add(_panelPowerPointsButtons, BorderLayout.SOUTH);

		_buttonMinus2 = new JButton("-2");
		_buttonMinus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPowerPointsMinusTwoActionActionPerformed(e);
			}
		});
		_panelPowerPointsButtons.add(_buttonMinus2);

		_buttonMinus4 = new JButton("-4");
		_buttonMinus4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPowerPointsMinusFourActionActionPerformed(e);
			}
		});
		_panelPowerPointsButtons.add(_buttonMinus4);

		_buttonMinus6 = new JButton("-6");
		_buttonMinus6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPowerPointsMinusSixActionActionPerformed(e);
			}
		});
		_panelPowerPointsButtons.add(_buttonMinus6);

		_panelNameLevel = new JPanel();
		GridBagConstraints gbc_panelNameLevel = new GridBagConstraints();
		gbc_panelNameLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNameLevel.gridwidth = 2;
		gbc_panelNameLevel.insets = new Insets(0, 0, 5, 0);
		gbc_panelNameLevel.gridx = 0;
		gbc_panelNameLevel.gridy = 0;
		_panelControls.add(_panelNameLevel, gbc_panelNameLevel);
		GridBagLayout gbl_panelNameLevel = new GridBagLayout();
		gbl_panelNameLevel.columnWidths = new int[] { 97, 27, 86, 0 };
		gbl_panelNameLevel.rowHeights = new int[] { 20, 0 };
		gbl_panelNameLevel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelNameLevel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		_panelNameLevel.setLayout(gbl_panelNameLevel);

		_labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.fill = GridBagConstraints.VERTICAL;
		gbc_labelName.insets = new Insets(0, 0, 0, 5);
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		_panelNameLevel.add(_labelName, gbc_labelName);

		_textFieldName = new JTextField();
		_textFieldName.addVetoableChangeListener(new VetoableChangeListener() {
			public void vetoableChange(PropertyChangeEvent e) {
				jTextFieldNameVetoableChangeVetoableChange(e);
			}
		});
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.fill = GridBagConstraints.BOTH;
		gbc_textFieldName.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		_panelNameLevel.add(_textFieldName, gbc_textFieldName);
		_textFieldName.setColumns(10);

		_textFieldNumber = new JTextField();
		GridBagConstraints gbc_textFieldNumber = new GridBagConstraints();
		gbc_textFieldNumber.fill = GridBagConstraints.BOTH;
		gbc_textFieldNumber.gridx = 2;
		gbc_textFieldNumber.gridy = 0;
		_panelNameLevel.add(_textFieldNumber, gbc_textFieldNumber);
		_textFieldNumber.setEditable(false);
		_textFieldNumber.setColumns(3);

		_buttonNextTurn = new JButton("Next Turn");
		_buttonNextTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonNextTurnActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonNextTurn = new GridBagConstraints();
		gbc_buttonNextTurn.fill = GridBagConstraints.BOTH;
		gbc_buttonNextTurn.insets = new Insets(0, 0, 5, 5);
		gbc_buttonNextTurn.gridx = 0;
		gbc_buttonNextTurn.gridy = 2;
		_panelControls.add(_buttonNextTurn, gbc_buttonNextTurn);

		_buttonBackUp = new JButton("Back Up");
		_buttonBackUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBackUpActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonBackUp = new GridBagConstraints();
		gbc_buttonBackUp.insets = new Insets(0, 0, 5, 0);
		gbc_buttonBackUp.fill = GridBagConstraints.BOTH;
		gbc_buttonBackUp.gridx = 1;
		gbc_buttonBackUp.gridy = 2;
		_panelControls.add(_buttonBackUp, gbc_buttonBackUp);

		_panelEffects = new JPanel();
		_panelEffects.setBorder(new TitledBorder(null, "Effects", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelEffects = new GridBagConstraints();
		gbc_panelEffects.gridwidth = 2;
		gbc_panelEffects.insets = new Insets(0, 0, 0, 5);
		gbc_panelEffects.fill = GridBagConstraints.BOTH;
		gbc_panelEffects.gridx = 0;
		gbc_panelEffects.gridy = 3;
		_panelControls.add(_panelEffects, gbc_panelEffects);
		_panelEffects.setLayout(new BorderLayout(0, 0));

		_listEffects = new JList();
		_listEffects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jListEffectsMouseMouseClicked(e);
			}
		});
		_listEffects.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jListEffectsListSelectionValueChanged(e);
			}
		});
		_panelEffects.add(_listEffects, BorderLayout.CENTER);

		_panelEffectsButtons = new JPanel();
		_panelEffects.add(_panelEffectsButtons, BorderLayout.SOUTH);

		_buttonAdd = new JButton("Add");
		_buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAddActionActionPerformed(e);
			}
		});
		_panelEffectsButtons.add(_buttonAdd);

		_buttonChange = new JButton("Change");
		_buttonChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonChangeActionActionPerformed(e);
			}
		});
		_panelEffectsButtons.add(_buttonChange);

		_buttonRemove = new JButton("Remove");
		_buttonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRemoveActionActionPerformed(e);
			}
		});
		_panelEffectsButtons.add(_buttonRemove);

		_panelPowers = new JPanel();
		_panelPowers.setBorder(new TitledBorder(null, "Powers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		_splitPaneMiddle.setRightComponent(_panelPowers);
		_panelPowers.setLayout(new BorderLayout(0, 0));

		_listPowers = new JList();
		_listPowers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jListPowerListMouseMouseClicked(e);
			}
		});
		_listPowers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jListPowerListListSelectionValueChanged(e);
			}
		});
		_panelPowers.add(_listPowers);

		_splitPaneRight = new JSplitPane();
		_splitPaneRight.setResizeWeight(1.0);
		_splitPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
		_splitPaneMiddleRight.setRightComponent(_splitPaneRight);

		_textPaneStatblock = new JTextPane();
		_textPaneStatblock.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				jEditorPaneStatblockHyperlinkHyperlinkUpdate(e);
			}
		});
		_splitPaneRight.setLeftComponent(_textPaneStatblock);

		_textPanePowerBrowser = new JTextPane();
		_splitPaneRight.setRightComponent(_textPanePowerBrowser);

		_panelLeft = new JPanel();
		_splitPaneMain.setLeftComponent(_panelLeft);
		GridBagLayout gbl__panelLeft = new GridBagLayout();
		gbl__panelLeft.columnWidths = new int[] { 0, 0 };
		gbl__panelLeft.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl__panelLeft.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl__panelLeft.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		_panelLeft.setLayout(gbl__panelLeft);

		_tableRoster = new JTable();
		_tableRoster.setModel(new ReadOnlyTableModel(new Object[][] {}, new String[] { "V", "R", "Name", "Status", "AC", "F", "R",
				"W" }));
		_tableRoster.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				jTableRosterSelectionValueChanged(e);
			}
		});
		_popupMenuRoster = new JPopupMenu();
		_popupMenuRoster.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent event) {
				jPopupMenuRosterWillBecomeVisible(event);
			}
		});
		addPopup(_tableRoster, _popupMenuRoster);

		_menuItemMoveToTop = new JMenuItem("Move to Top");
		_menuItemMoveToTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuItemMoveToTopActionActionPerformed(e);
			}
		});
		_popupMenuRoster.add(_menuItemMoveToTop);

		_menuItemDelay = new JMenuItem("Delay");
		_menuItemDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuItemDelayActionActionPerformed(e);
			}
		});
		_popupMenuRoster.add(_menuItemDelay);

		_menuItemReady = new JMenuItem("Ready");
		_menuItemReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuItemReadyActionActionPerformed(e);
			}
		});
		_popupMenuRoster.add(_menuItemReady);

		_separator_6 = new JSeparator();
		_popupMenuRoster.add(_separator_6);

		_menuItemLogOA = new JMenuItem("Log OA");
		_menuItemLogOA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuItemLogOAActionActionPerformed(e);
			}
		});
		_popupMenuRoster.add(_menuItemLogOA);

		_separator_7 = new JSeparator();
		_popupMenuRoster.add(_separator_7);

		_menuItemMarkEONT = new JMenuItem("Mark until EONT");
		_menuItemMarkEONT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuItemMarkUntilEONTActionActionPerformed(e);
			}
		});
		_popupMenuRoster.add(_menuItemMarkEONT);

		_menuItemMarkEOE = new JMenuItem("Mark until EOE");
		_menuItemMarkEOE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuItemMarkUntilEOEActionActionPerformed(e);
			}
		});
		_popupMenuRoster.add(_menuItemMarkEOE);

		_menuItemToggleVisibility = new JMenuItem("Toggle Visibility");
		_menuItemToggleVisibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jMenuItemToggleVisibilityActionActionPerformed(e);
			}
		});
		_popupMenuRoster.add(_menuItemToggleVisibility);

		_separator_8 = new JSeparator();
		_popupMenuRoster.add(_separator_8);
		GridBagConstraints gbc_tableRoster = new GridBagConstraints();
		gbc_tableRoster.insets = new Insets(0, 0, 5, 0);
		gbc_tableRoster.fill = GridBagConstraints.BOTH;
		gbc_tableRoster.gridx = 0;
		gbc_tableRoster.gridy = 0;
		_panelLeft.add(_tableRoster, gbc_tableRoster);

		_tabbedPaneBottomLeft = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc__tabbedPaneBottomLeft = new GridBagConstraints();
		gbc__tabbedPaneBottomLeft.insets = new Insets(0, 0, 5, 0);
		gbc__tabbedPaneBottomLeft.fill = GridBagConstraints.BOTH;
		gbc__tabbedPaneBottomLeft.gridx = 0;
		gbc__tabbedPaneBottomLeft.gridy = 1;
		_panelLeft.add(_tabbedPaneBottomLeft, gbc__tabbedPaneBottomLeft);

		_panelNotes = new JPanel();
		_tabbedPaneBottomLeft.addTab("Notes", null, _panelNotes, null);
		_panelNotes.setLayout(new BorderLayout(0, 0));

		_textAreaNotes = new JTextArea();
		_textAreaNotes.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				saveGlobalNotes();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				saveGlobalNotes();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				saveGlobalNotes();
			}

		});
		_panelNotes.add(_textAreaNotes);

		_panelOffTurnPowers = new JPanel();
		_tabbedPaneBottomLeft.addTab("Off-Turn Powers", null, _panelOffTurnPowers, null);
		_panelOffTurnPowers.setLayout(new BorderLayout(0, 0));

		_listOffTurnPowers = new JList();
		_listOffTurnPowers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jListOffTurnPowersListSelectionValueChanged(e);
			}
		});
		_panelOffTurnPowers.add(_listOffTurnPowers);

		_panelMusic = new JPanel();
		_tabbedPaneBottomLeft.addTab("Music", null, _panelMusic, null);
		GridBagLayout gbl_panelMusic = new GridBagLayout();
		gbl_panelMusic.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelMusic.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelMusic.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelMusic.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		_panelMusic.setLayout(gbl_panelMusic);

		_panelNowPlaying = new JPanel();
		_panelNowPlaying.setBorder(new TitledBorder(null, "Now Playing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelNowPlaying = new GridBagConstraints();
		gbc_panelNowPlaying.gridwidth = 3;
		gbc_panelNowPlaying.insets = new Insets(0, 0, 5, 0);
		gbc_panelNowPlaying.fill = GridBagConstraints.BOTH;
		gbc_panelNowPlaying.gridx = 0;
		gbc_panelNowPlaying.gridy = 0;
		_panelMusic.add(_panelNowPlaying, gbc_panelNowPlaying);
		_panelNowPlaying.setLayout(new BorderLayout(0, 0));

		_textAreaNowPlaying = new JTextArea();
		_textAreaNowPlaying.setFont(new Font("Tahoma", Font.BOLD, 13));
		_textAreaNowPlaying.setWrapStyleWord(true);
		_textAreaNowPlaying.setText("(nothing)");
		_textAreaNowPlaying.setLineWrap(true);
		_textAreaNowPlaying.setEditable(false);
		_panelNowPlaying.add(_textAreaNowPlaying, BorderLayout.NORTH);

		_toggleButtonPlay = new JToggleButton("Play");
		_toggleButtonPlay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				jToggleButtonPlayItemItemStateChanged(e);
			}
		});
		GridBagConstraints gbc_toggleButtonPlay = new GridBagConstraints();
		gbc_toggleButtonPlay.fill = GridBagConstraints.BOTH;
		gbc_toggleButtonPlay.insets = new Insets(0, 0, 5, 5);
		gbc_toggleButtonPlay.gridx = 0;
		gbc_toggleButtonPlay.gridy = 1;
		_panelMusic.add(_toggleButtonPlay, gbc_toggleButtonPlay);

		_buttonNext = new JButton(">|");
		_buttonNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPlayNextActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_buttonNext = new GridBagConstraints();
		gbc_buttonNext.insets = new Insets(0, 0, 5, 5);
		gbc_buttonNext.fill = GridBagConstraints.BOTH;
		gbc_buttonNext.gridx = 0;
		gbc_buttonNext.gridy = 2;
		_panelMusic.add(_buttonNext, gbc_buttonNext);

		_panelCrits = new JPanel();
		_panelCrits.setBorder(new TitledBorder(null, "Crits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelCrits = new GridBagConstraints();
		gbc_panelCrits.gridheight = 2;
		gbc_panelCrits.insets = new Insets(0, 0, 5, 5);
		gbc_panelCrits.fill = GridBagConstraints.BOTH;
		gbc_panelCrits.gridx = 1;
		gbc_panelCrits.gridy = 1;
		_panelMusic.add(_panelCrits, gbc_panelCrits);
		_panelCrits.setLayout(new GridLayout(0, 1, 0, 0));

		_toggleButtonHit = new JToggleButton("Hit");
		_toggleButtonHit.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				jToggleButtonCritHitItemItemStateChanged(e);
			}
		});
		_panelCrits.add(_toggleButtonHit);

		_toggleButtonMiss = new JToggleButton("Miss");
		_toggleButtonMiss.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				jToggleButtonCritMissItemItemStateChanged(e);
			}
		});
		_panelCrits.add(_toggleButtonMiss);

		_panelMisc = new JPanel();
		_panelMisc.setBorder(new TitledBorder(null, "Misc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelMisc = new GridBagConstraints();
		gbc_panelMisc.insets = new Insets(0, 0, 5, 0);
		gbc_panelMisc.gridheight = 2;
		gbc_panelMisc.fill = GridBagConstraints.BOTH;
		gbc_panelMisc.gridx = 2;
		gbc_panelMisc.gridy = 1;
		_panelMusic.add(_panelMisc, gbc_panelMisc);
		_panelMisc.setLayout(new GridLayout(0, 1, 0, 0));

		_toggleButtonVictory = new JToggleButton("Victory");
		_toggleButtonVictory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				jToggleButtonMiscVictoryItemItemStateChanged(e);
			}
		});
		_panelMisc.add(_toggleButtonVictory);

		_toggleButtonDaily = new JToggleButton("Daily");
		_toggleButtonDaily.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				jToggleButtonMiscDailyItemItemStateChanged(e);
			}
		});
		_panelMisc.add(_toggleButtonDaily);

		_comboBoxPlaylist = new JComboBox();
		GridBagConstraints gbc_comboBoxPlaylist = new GridBagConstraints();
		gbc_comboBoxPlaylist.gridwidth = 3;
		gbc_comboBoxPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxPlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPlaylist.gridx = 0;
		gbc_comboBoxPlaylist.gridy = 3;
		_panelMusic.add(_comboBoxPlaylist, gbc_comboBoxPlaylist);

		_textFieldXP = new JTextField();
		_textFieldXP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTextFieldXPMouseMouseClicked(e);
			}
		});
		_textFieldXP.setText("Total XP: 0");
		_textFieldXP.setEnabled(false);
		_textFieldXP.setEditable(false);
		GridBagConstraints gbc_textFieldXP = new GridBagConstraints();
		gbc_textFieldXP.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldXP.gridx = 0;
		gbc_textFieldXP.gridy = 2;
		_panelLeft.add(_textFieldXP, gbc_textFieldXP);
		_textFieldXP.setColumns(10);
	}

	/**
	 * Enables/disables effect change and remove buttons based on list
	 * selection.
	 */
	private void effectButtonUpdate() {
		if (_listEffects.getSelectedIndices().length > 0) {
			_buttonChange.setEnabled(true);
			_buttonRemove.setEnabled(true);
		} else {
			_buttonChange.setEnabled(false);
			_buttonRemove.setEnabled(false);
		}
	}

	/**
	 * Loads active effects for the selected {@link Combatant}.
	 */
	private void effectLoad() {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			_listEffects.setEnabled(true);

			_listEffects.setCellRenderer(new EffectDetailsCellRenderer());
			DefaultListModel model = (DefaultListModel) _listEffects.getModel();
			model.clear();

			for (Effect eff : getFight().getEffectsByTarget(fighter.getCombatHandle())) {
				model.addElement(eff);
			}
			effectButtonUpdate();
		}
	}

	/**
	 * Closes the application.
	 */
	private void exitEncounter() {
		Settings.save();
		getStatlib().saveToFile(getStatlibFilename());
		this.dispose();
	}

	/**
	 * Returns the tracker's encounter.
	 * 
	 * @return the encounter
	 */
	private Encounter getFight() {
		return _fight;
	}

	/**
	 * Returns the {@link InitDisplay}.
	 * 
	 * @return the {@link InitDisplay}
	 */
	private InitDisplay getInitDisplay() {
		if (_initDisplay == null) {
			_initDisplay = new InitDisplay();
			_initDisplay.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					_checkBoxMenuItemMinimalInitDisplay.setSelected(false);
					_checkBoxMenuItemFullInitDisplay.setSelected(false);
				}
			});
		}
		return _initDisplay;
	}

	/**
	 * Returns the selected {@link Combatant}.
	 * 
	 * @return the Combatant
	 */
	private Combatant getListSelectedFighter() {
		return getFight().getSelectedFighter();
	}

	/**
	 * Returns the roster cell renderer.
	 * 
	 * @return the renderer
	 */
	private TableCellRenderer getRosterRenderer() {
		_rosterRenderer.setFight(getFight());
		return _rosterRenderer;
	}

	/**
	 * Returns the stat library
	 * 
	 * @return the stat library
	 */
	private StatLibrary getStatlib() {
		return _statlib;
	}

	/**
	 * Returns the filename of the stat library.
	 * 
	 * @return the filename
	 */
	private String getStatlibFilename() {
		return _statlibFilename;
	}

	/**
	 * Indicates if full initiative information is to be displayed.
	 * 
	 * @return true, if full initiative information should be displayed
	 */
	private Boolean isFullInit() {
		return _fullInit;
	}

	/**
	 * Event: Add clicked.
	 * 
	 * @param event
	 */
	private void jButtonAddActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			EffectWin effectWin = new EffectWin(getFight(), this);
			effectWin.setVisible(true);

			if (effectWin.getEffect() != null) {
				getFight().effectAdd(effectWin.getEffect());
				effectLoad();
			}

			effectWin.dispose();
			updateInitDisplay();
		}
	}

	/**
	 * Event: Add Temp clicked.
	 * 
	 * @param event
	 */
	private void jButtonAddTempActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.addTempHP((Integer) _spinnerAmount.getValue());
			updateFromClass();
		}
	}

	/**
	 * Event: Back Up clicked.
	 * 
	 * @param event
	 */
	private void jButtonBackUpActionActionPerformed(ActionEvent event) {
		getFight().fighterUndoTurn(getFight().getPriorFighterHandle());
		updateFromClass();
		_tableRoster.getSelectionModel().setSelectionInterval(0, 0);
	}

	/**
	 * Event: Change clicked.
	 * 
	 * @param event
	 */
	private void jButtonChangeActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			if (_listEffects.getSelectedIndices().length > 0) {
				for (int i : _listEffects.getSelectedIndices()) {
					Effect eff = (Effect) _listEffects.getModel().getElementAt(i);
					EffectWin effectWin = new EffectWin(getFight(), getFight().getActiveEffect(eff.getEffectID()), this);
					effectWin.setVisible(true);

					if (effectWin.getEffect() != null) {
						getFight().effectChange(effectWin.getEffect());
					}

					effectWin.dispose();
				}
			}

			effectLoad();
			updateInitDisplay();
		}
	}

	/**
	 * Event: Dmg, clicked.
	 * 
	 * @param event
	 */
	private void jButtonDamageActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.damage((Integer) _spinnerAmount.getValue());
			updateFromClass();
			_spinnerAmount.requestFocusInWindow();

			if (!getFight().getCurrentFighterHandle().isEmpty()) {
				StatLogger.logDamage(getFight().getCurrentRound(), getFight().getCurrentFighter().getCombatHandle(),
						fighter.getCombatHandle(), (Integer) _spinnerAmount.getValue());
				if (!fighter.isPC() && fighter.isDyingOrDead()) {
					StatLogger.logDeath(getFight().getCurrentRound(), getFight().getCurrentFighter().getCombatHandle(),
							fighter.getCombatHandle());
				}
			}
		}
	}

	/**
	 * Event: Delay clicked.
	 * 
	 * @param event
	 */
	private void jButtonDelayActionActionPerformed(ActionEvent event) {
		getFight().setInitStatus(getListSelectedFighter(), "Delay");
		updateFromClass();
	}

	/**
	 * Event: Fail Death clicked.
	 * 
	 * @param event
	 */
	private void jButtonFailDeathActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.failDeathSave();
			updateFromClass();
		}
	}

	/**
	 * Event: 5 clicked.
	 * 
	 * @param event
	 */
	private void jButtonFiveActionActionPerformed(ActionEvent event) {
		_spinnerAmount.setValue(5);
		_spinnerAmount.requestFocusInWindow();
	}

	/**
	 * Event: Halve clicked.
	 * 
	 * @param event
	 */
	private void jButtonHalveActionActionPerformed(ActionEvent event) {
		_spinnerAmount.setValue((Integer) _spinnerAmount.getValue() / 2);
	}

	/**
	 * Event: Heal clicked.
	 * 
	 * @param event
	 */
	private void jButtonHealActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.heal((Integer) _spinnerAmount.getValue());
			updateFromClass();
			_spinnerAmount.requestFocusInWindow();

			if (!getFight().getCurrentFighterHandle().isEmpty()) {
				StatLogger.logDamage(getFight().getCurrentRound(), getFight().getCurrentFighter().getCombatHandle(),
						fighter.getCombatHandle(), -(Integer) _spinnerAmount.getValue());
			}
		}
	}

	/**
	 * Event: Max clicked.
	 * 
	 * @param event
	 */
	private void jButtonMaxActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			_spinnerAmount.setValue(fighter.getMaxHP());
			_spinnerAmount.requestFocusInWindow();
		}
	}

	/**
	 * Event: -1 clicked.
	 * 
	 * @param event
	 */
	private void jButtonMinusOneActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();
		if (fighter != null) {
			fighter.modSurges(-1);
			_textFieldSurges.setText(fighter.getSurgeView());
		}
	}

	/**
	 * Event: Move to Top clicked.
	 * 
	 * @param event
	 */
	private void jButtonMoveToTopActionActionPerformed(ActionEvent event) {
		getFight().moveToTop(getListSelectedFighter());
		updateFromClass();
	}

	/**
	 * Event: Next Turn clicked.
	 * 
	 * @param event
	 */
	private void jButtonNextTurnActionActionPerformed(ActionEvent event) {
		getFight().finishCurrentTurn();
		updateFromClass();
		_tableRoster.getSelectionModel().setSelectionInterval(0, 0);
	}

	/**
	 * Event: Play next clicked.
	 * 
	 * @param event
	 */
	private void jButtonPlayNextActionActionPerformed(ActionEvent event) {
		_toggleButtonPlay.setSelected(false);
		_toggleButtonPlay.setSelected(true);
	}

	/**
	 * Event: +5 clicked.
	 * 
	 * @param event
	 */
	private void jButtonPlusFiveActionActionPerformed(ActionEvent event) {
		_spinnerAmount.setValue((Integer) _spinnerAmount.getValue() + 5);
		_spinnerAmount.requestFocusInWindow();
	}

	/**
	 * Event: +1 clicked.
	 * 
	 * @param event
	 */
	private void jButtonPlusOneActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();
		if (fighter != null) {
			fighter.modSurges(1);
			_textFieldSurges.setText(fighter.getSurgeView());
		}
	}

	/**
	 * Event: Power Points -4 pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerPointsMinusFourActionActionPerformed(ActionEvent event) {
		Integer oldVal = (Integer) _spinnerPowerPoints.getValue();
		Integer newVal = oldVal - 4;
		_spinnerPowerPoints.setValue((newVal < 0 ? 0 : newVal));
	}

	/**
	 * Event: Power Points -6 pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerPointsMinusSixActionActionPerformed(ActionEvent event) {
		Integer oldVal = (Integer) _spinnerPowerPoints.getValue();
		Integer newVal = oldVal - 6;
		_spinnerPowerPoints.setValue((newVal < 0 ? 0 : newVal));
	}

	/**
	 * Event: Power Points -2 pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerPointsMinusTwoActionActionPerformed(ActionEvent event) {
		Integer oldVal = (Integer) _spinnerPowerPoints.getValue();
		Integer newVal = oldVal - 2;
		_spinnerPowerPoints.setValue((newVal < 0 ? 0 : newVal));
	}

	/**
	 * Event: Ready clicked.
	 * 
	 * @param event
	 */
	private void jButtonReadyActionActionPerformed(ActionEvent event) {
		getFight().setInitStatus(getListSelectedFighter(), "Ready");
		updateFromClass();
	}

	/**
	 * Event: Regain All clicked.
	 * 
	 * @param event
	 */
	private void jButtonRegainAllActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();
		if (fighter != null) {
			fighter.modSurges(fighter.getSurges() - fighter.getSurgesLeft());
			_textFieldSurges.setText(fighter.getSurgeView());
		}
	}

	/**
	 * Event: Remove clicked.
	 * 
	 * @param event
	 */
	private void jButtonRemoveActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			if (_listEffects.getSelectedIndices().length > 0) {
				for (int i : _listEffects.getSelectedIndices()) {
					Effect eff = (Effect) _listEffects.getModel().getElementAt(i);
					getFight().effectRemove(eff.getEffectID());
				}
			}

			effectLoad();
			updateInitDisplay();
		}
	}

	/**
	 * Event: Remove Fighter clicked.
	 * 
	 * @param event
	 */
	private void jButtonRemoveFighterActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			int n = JOptionPane.showOptionDialog(this, "Are you sure you want to remove\n" + fighter.getCombatHandle()
					+ " from the battle?", "Remove Fighter?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
					null);
			if (n == JOptionPane.YES_OPTION) {
				getFight().remove(fighter.getCombatHandle());
				getFight().clearSelectedFighter();
				updateFromClass();
			}
		}
	}

	/**
	 * Event: Reserve clicked.
	 * 
	 * @param event
	 */
	private void jButtonReserveActionActionPerformed(ActionEvent event) {
		getFight().setInitStatus(getListSelectedFighter(), "Reserve");
		updateFromClass();
	}

	/**
	 * Event: Roll Initiative clicked.
	 * 
	 * @param event
	 */
	private void jButtonRollInitiativeActionActionPerformed(ActionEvent event) {
		getFight().rollOneInit(getListSelectedFighter());
		updateFromClass();
	}

	/**
	 * Event: Surge clicked.
	 * 
	 * @param event
	 */
	private void jButtonSurgeActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null && fighter.getSurgeValue() != 0) {
			_spinnerAmount.setValue(fighter.getSurgeValue());
			_spinnerAmount.requestFocusInWindow();
		}
	}

	/**
	 * Event: Unfail Death clicked.
	 * 
	 * @param event
	 */
	private void jButtonUndoDeathActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.unfailDeathSave();
			updateFromClass();
		}
	}

	/**
	 * Event: Action Points spent changed.
	 * 
	 * @param event
	 */
	private void jCheckBoxSpentChangeStateChanged(ChangeEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.setActionPointSpent(_chckbxSpent.isSelected());
			updateFromClass();
		}
	}

	/**
	 * Event: Statblock display, hyperlink updated.
	 * 
	 * @param event
	 */
	private void jEditorPaneStatblockHyperlinkHyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			String dice = event.getDescription().substring(1).trim();
			if (dice.startsWith("+")) {
				JOptionPane.showMessageDialog(this, DiceBag.roll("1d20" + dice, 9));
			} else {
				String result = DiceBag.roll(dice);
				_spinnerAmount.setValue(Integer.valueOf(result.substring(result.indexOf("=") + 1).trim()));
				JOptionPane.showMessageDialog(this, result);
			}
		}
	}

	/**
	 * Event: Effects, selection changed.
	 * 
	 * @param event
	 */
	private void jListEffectsListSelectionValueChanged(ListSelectionEvent event) {
		effectButtonUpdate();
	}

	/**
	 * Event: Effects, mouse clicked.
	 * 
	 * @param event
	 */
	private void jListEffectsMouseMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2 && !event.isConsumed()) {
			event.consume();
			_buttonChange.doClick();
		}
	}

	/**
	 * Event: Off-turn powers list selection changed.
	 * 
	 * @param event
	 */
	private void jListOffTurnPowersListSelectionValueChanged(ListSelectionEvent event) {
		if (_listOffTurnPowers.getSelectedIndex() >= 0) {
			FighterPower fp = (FighterPower) _listOffTurnPowers.getSelectedValue();
			getFight().setSelectedFighter(fp.getFighter());
			updateFromClass();
		}
	}

	/**
	 * Event: Power List selection changed.
	 * 
	 * @param event
	 */
	private void jListPowerListListSelectionValueChanged(ListSelectionEvent event) {
		powerInfoUpdate();
	}

	/**
	 * Event: Power List clicked.
	 * 
	 * @param event
	 */
	private void jListPowerListMouseMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2 && !event.isConsumed()) {
			event.consume();
			Combatant fighter = getListSelectedFighter();
			if (fighter != null && !_listPowers.isSelectionEmpty()) {
				DefaultListModel model = (DefaultListModel) _listPowers.getModel();
				Power pow = (Power) model.getElementAt(_listPowers.getSelectedIndex());
				fighter.setPowerUsed(pow.getName(), !fighter.isPowerUsed(pow.getName()));
				powerLoad();
			}
		}
	}

	/**
	 * Event: Roster, popup menu, Delay clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemDelayActionActionPerformed(ActionEvent event) {
		_buttonDelay.doClick();
	}

	/**
	 * Event: Roster, popup menu, Log OA clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemLogOAActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			StatLogger.logOA(fighter.getCombatHandle());
		}
	}

	/**
	 * Event: Roster popup, Mark until EOE clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemMarkUntilEOEActionActionPerformed(ActionEvent event) {
		getFight().effectAdd("Marked (" + getFight().getCurrentFighter().getCombatHandle() + ")",
				getFight().getCurrentFighter().getCombatHandle(), getFight().getSelectedFighter().getCombatHandle(),
				EffectBase.Duration.Encounter, false);
		updateInitDisplay();
	}

	/**
	 * Event: Roster popup, Mark until EONT clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemMarkUntilEONTActionActionPerformed(ActionEvent event) {
		getFight().effectAdd("Marked (" + getFight().getCurrentFighter().getCombatHandle() + ")",
				getFight().getCurrentFighter().getCombatHandle(), getFight().getSelectedFighter().getCombatHandle(),
				EffectBase.Duration.SourceEnd, false);
		updateInitDisplay();
	}

	/**
	 * Event: Roster, popup menu, Move to Top clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemMoveToTopActionActionPerformed(ActionEvent event) {
		_buttonMoveToTop.doClick();
	}

	/**
	 * Event: Roster, popup menu, Ready clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemReadyActionActionPerformed(ActionEvent event) {
		_buttonReady.doClick();
	}

	/**
	 * Event: Roster popup, Toggle Visibility clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemToggleVisibilityActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();
		if (fighter != null) {
			fighter.setShown(!fighter.isShown());
			reloadListFromClass();
		}
	}

	/**
	 * Event: Roster popup will become visible.
	 * 
	 * @param event
	 */
	private void jPopupMenuRosterWillBecomeVisible(PopupMenuEvent event) {
		if (_tableRoster.getSelectedRow() == -1) {
			_popupMenuRoster.setVisible(false);
		}
	}

	/**
	 * Event: Action Points spinner changed.
	 * 
	 * @param event
	 */
	private void jSpinnerActionPointsChangeStateChanged(ChangeEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.setActionPointsRemaining((Integer) _spinnerActionPoints.getValue());
			updateFromClass();
		}
	}

	/**
	 * Event: Action Points key pressed.
	 * 
	 * @param event
	 */
	private void jSpinnerActionPointsKeyKeyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			jSpinnerActionPointsChangeStateChanged(null);
		}
	}

	/**
	 * Event: Damage/heal state changed.
	 * 
	 * @param event
	 */
	private void jSpinnerDamageHealAmountChangeStateChanged(ChangeEvent event) {
		if ((Integer) _spinnerAmount.getValue() > 0) {
			_buttonDamage.setEnabled(true);
			_buttonHeal.setEnabled(true);
			_buttonAddTemp.setEnabled(true);
			_buttonHalve.setEnabled(true);
		} else {
			_buttonDamage.setEnabled(false);
			_buttonHeal.setEnabled(false);
			_buttonAddTemp.setEnabled(false);
			_buttonHalve.setEnabled(false);
		}
		statDataEnable(getListSelectedFighter());
	}

	/**
	 * Event: Init roll, value changed.
	 * 
	 * @param event
	 */
	private void jSpinnerInitRollChangeStateChanged(ChangeEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			if ((Integer) _spinnerInitRoll.getValue() != fighter.getInitRoll()) {
				if (fighter.getInitStatus().contentEquals("Reserve")) {
					getFight().rollOneInit(fighter);
				}
				getFight().fighterInitRollUpdate(fighter.getCombatHandle(), (Integer) _spinnerInitRoll.getValue());
				updateFromClass();
			}
		}
	}

	/**
	 * Event: Init roll, key pressed.
	 * 
	 * @param event
	 */
	private void jSpinnerInitRollKeyKeyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			jSpinnerInitRollChangeStateChanged(null);
			event.consume();
		}
	}

	/**
	 * Event: Power Points spinner changed.
	 * 
	 * @param event
	 */
	private void jSpinnerPowerPointsChangeStateChanged(ChangeEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.setPowerPointsRemaining((Integer) _spinnerPowerPoints.getValue());
			updateFromClass();
		}
	}

	/**
	 * Event: Power Points spinner key pressed.
	 * 
	 * @param event
	 */
	private void jSpinnerPowerPointsKeyKeyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			jSpinnerPowerPointsChangeStateChanged(null);
		}
	}

	/**
	 * Event: Roster, selection changed.
	 * 
	 * @param event
	 */
	private void jTableRosterSelectionValueChanged(ListSelectionEvent event) {
		ColumnsAutoSizer.sizeColumnsToFit(_tableRoster, 15);
		if (_tableRoster.getSelectedRow() >= 0) {
			Combatant fighterSelected = getFight().getSelectedFighter();
			String tableSelected = (String) _tableRoster.getValueAt(_tableRoster.getSelectedRow(), 2);

			if (fighterSelected == null || !fighterSelected.getCombatHandle().contentEquals(tableSelected)) {
				getFight().setSelectedFighterHandle(tableSelected);
				statDataLoad();
			}
		} else {
			getFight().clearSelectedFighter();
			statDataClear();
		}
	}

	/**
	 * Event: Name, changed.
	 * 
	 * @param event
	 */
	private void jTextFieldNameVetoableChangeVetoableChange(PropertyChangeEvent event) {
		String newValue = _textFieldName.getText().trim();

		if (!newValue.isEmpty()) {
			Combatant fighter = getListSelectedFighter();

			if (fighter != null) {
				if (!newValue.contentEquals(fighter.getName())) {
					getFight().remove(fighter.getCombatHandle());
					fighter.setName(newValue);
					getFight().add(fighter, false, true);
					getFight().setSelectedFighter(fighter);
					updateFromClass();
				}
			}
		}
	}

	/**
	 * Event: XP, clicked.
	 * 
	 * @param event
	 */
	private void jTextFieldXPMouseMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			String XP = _textFieldXP.getText().replace("Total XP: ", "");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(XP), null);
		}
	}

	private void jToggleButtonCritHitItemItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (Settings.getCriticalHitSong() != null) {
				_toggleButtonMiss.setSelected(false);
				_toggleButtonDaily.setSelected(false);
				_toggleButtonVictory.setSelected(false);
				_toggleButtonPlay.setSelected(false);
				Player.playOnce(Settings.getCriticalHitSong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						_toggleButtonHit.setSelected(false);
						_toggleButtonPlay.setSelected(true);
					}
				});
			}
		} else if (!Player.isCompletedOnce()) {
			Player.stopOnce();
		}
	}

	private void jToggleButtonCritMissItemItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (Settings.getCriticalMissSong() != null) {
				_toggleButtonHit.setSelected(false);
				_toggleButtonDaily.setSelected(false);
				_toggleButtonVictory.setSelected(false);
				_toggleButtonPlay.setSelected(false);
				Player.playOnce(Settings.getCriticalMissSong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						_toggleButtonMiss.setSelected(false);
						_toggleButtonPlay.setSelected(true);
					}
				});
			}
		} else if (!Player.isCompletedOnce()) {
			Player.stopOnce();
		}
	}

	private void jToggleButtonMiscDailyItemItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (Settings.getDailySong() != null) {
				_toggleButtonHit.setSelected(false);
				_toggleButtonMiss.setSelected(false);
				_toggleButtonVictory.setSelected(false);
				_toggleButtonPlay.setSelected(false);
				Player.playOnce(Settings.getDailySong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						_toggleButtonDaily.setSelected(false);
						_toggleButtonPlay.setSelected(true);
					}
				});
			}
		} else if (!Player.isCompletedOnce()) {
			Player.stopOnce();
		}
	}

	private void jToggleButtonMiscVictoryItemItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (Settings.getVictorySong() != null) {
				_toggleButtonHit.setSelected(false);
				_toggleButtonMiss.setSelected(false);
				_toggleButtonDaily.setSelected(false);
				_toggleButtonPlay.setSelected(false);
				Player.playOnce(Settings.getVictorySong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						_toggleButtonVictory.setSelected(false);
					}
				});
			}
		} else if (!Player.isCompletedOnce()) {
			Player.stopOnce();
		}
	}

	/**
	 * Toggles playback of the music player.
	 * 
	 * @param event
	 */
	private void jToggleButtonPlayItemItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Player.setDir((File) _comboBoxPlaylist.getSelectedItem());
			Player.play();
		} else {
			Player.stop();
		}
	}

	/**
	 * Loads or imports an encounter.
	 * 
	 * @param clearFirst
	 *            if true, the encounter is cleared first
	 */
	private void loadEncounter(Boolean clearFirst) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Load Encounter File(s)");
		fc.setMultiSelectionEnabled(true);
		fc.setCurrentDirectory(Settings.getWorkingDirectory());
		fc.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return (f.isDirectory() || f.getName().endsWith(".xml"));
			}

			@Override
			public String getDescription() {
				return "Encounter files (*.xml)";
			}
		});

		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			Settings.setWorkingDirectory(fc.getCurrentDirectory());
			if (clearFirst) {
				getFight().clearAll();
			}
			for (File f : fc.getSelectedFiles()) {
				getFight().loadFromFile(f.getAbsolutePath(), false);
			}
			getFight().clearSelectedFighter();
			updateFromClass();
		}
	}

	/**
	 * Recursively populates the playlist combo box with available music
	 * directories.
	 * 
	 * @param dir
	 *            the directory to search
	 */
	private void loadPlaylists(File dir) {
		if (dir == null || !dir.isDirectory()) {
			return;
		}
		_comboBoxPlaylist.setRenderer(new PlaylistCellRenderer());
		DefaultComboBoxModel model = (DefaultComboBoxModel) _comboBoxPlaylist.getModel();
		java.io.FileFilter dirFilter = new java.io.FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}
		};

		SortedSet<File> files = new TreeSet<File>();

		for (File f : dir.listFiles(dirFilter)) {
			files.add(f);
		}

		for (File f : files.toArray(new File[0])) {
			String[] mp3s = f.list(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".mp3");
				}
			});

			if (mp3s.length > 0) {
				model.addElement(f);
			}
			loadPlaylists(f);
		}
	}

	/**
	 * Event: Menu, Encounter, End Encounter.
	 * 
	 * @param event
	 */
	private void menuEncounterEndActionActionPerformed(ActionEvent event) {
		int n = JOptionPane.showOptionDialog(this, "Are you sure you want to end the battle?\n" + "This will:\n"
				+ "    -Reset monster health and powers\n" + "    -End all ongoing effects", "Are you sure?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == JOptionPane.YES_OPTION) {
			_checkBoxMenuItemMinimalInitDisplay.setSelected(false);
			_checkBoxMenuItemFullInitDisplay.setSelected(false);
			_tableRoster.clearSelection();
			getFight().resetEncounter(false);
			_tabbedPaneBottomLeft.setSelectedIndex(0);
			updateFromClass();
		}
	}

	/**
	 * Event: Menu, Encounter, Roll Initiative.
	 * 
	 * @param event
	 */
	private void menuEncounterInitiativeActionActionPerformed(ActionEvent event) {
		getFight().startFight(Settings.doGroupSimilar());
		_tabbedPaneBottomLeft.setSelectedIndex(1);
		updateFromClass();
	}

	/**
	 * Event: Menu, Encounter, Remove Monsters
	 * 
	 * @param event
	 */
	private void menuEncounterRemoveMonstersActionActionPerformed(ActionEvent event) {
		getFight().clearNPCs();
		updateFromClass();
	}

	/**
	 * Event: Menu, File, Exit.
	 * 
	 * @param event
	 */
	private void menuFileExitActionActionPerformed(ActionEvent event) {
		exitEncounter();
	}

	/**
	 * Event: Menu, File, Import.
	 * 
	 * @param event
	 */
	private void menuFileImportActionActionPerformed(ActionEvent event) {
		loadEncounter(false);
	}

	/**
	 * Event: Menu, File, New Encounter.
	 * 
	 * @param event
	 */
	private void menuFileNewActionActionPerformed(ActionEvent event) {
		newEncounter();
	}

	/**
	 * Event: Menu, File, Open Encounter.
	 * 
	 * @param event
	 */
	private void menuFileOpenActionActionPerformed(ActionEvent event) {
		loadEncounter(true);
	}

	/**
	 * Event: Menu, File, Save Encounter.
	 * 
	 * @param event
	 */
	private void menuFileSaveActionActionPerformed(ActionEvent event) {
		saveEncounter();
	}

	/**
	 * Event: Menu, Help, About.
	 * 
	 * @param event
	 */
	private void menuHelpAboutActionActionPerformed(ActionEvent event) {
		About about = new About();
		about.setLocationRelativeTo(this);
		about.setVisible(true);
		about.dispose();
	}

	/**
	 * Event: Menu, Library, Open.
	 * 
	 * @param event
	 */
	private void menuLibraryOpenActionActionPerformed(ActionEvent event) {
		Library statlibWin = new Library(getStatlib(), this);
		statlibWin.setVisible(true);
		getStatlib().saveToFile(getStatlibFilename());

		if (statlibWin.getStatsToAdd().size() > 0) {
			for (Stats fighter : statlibWin.getStatsToAdd()) {
				getFight().add(fighter, false);
			}
		}

		statlibWin.dispose();
		getFight().updateAllStats(!getFight().isOngoingFight());
		updateFromClass();
	}

	/**
	 * Event: Menu, Party, Take Extended Rest.
	 * 
	 * @param event
	 */
	private void menuPartyExtendedRestActionActionPerformed(ActionEvent event) {
		int n = JOptionPane.showOptionDialog(this, "Are you sure you want to take an extended rest?\n" + "This will:\n"
				+ "    -Clear all monsters from the encounter\n" + "    -Refresh all PC powers\n"
				+ "    -Restore the party to full health", "Take Extended Rest?", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == JOptionPane.YES_OPTION) {
			Settings.setRest("Extended Rest");
			_tableRoster.clearSelection();
			getFight().takeExtendedRest();
			updateFromClass();
		}
	}

	/**
	 * Event: Menu, Party, Remove Party from Roster.
	 * 
	 * @param event
	 */
	private void menuPartyRemoveActionActionPerformed(ActionEvent event) {
		getFight().clearPCs();
		updateFromClass();
	}

	/**
	 * Event: Menu, Party, Take Short Rest.
	 * 
	 * @param event
	 */
	private void menuPartyShortRestActionActionPerformed(ActionEvent event) {
		int n = JOptionPane.showOptionDialog(this, "Are you sure you want to take a short rest?\n" + "This will:\n"
				+ "    -Clear all monsters from the encounter\n" + "    -Refresh all non-daily PC powers\n"
				+ "    -Clear all temporary hit points", "Take Short Rest?", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == JOptionPane.YES_OPTION) {
			Settings.setRest("Short Rest");
			_tableRoster.clearSelection();
			getFight().takeShortRest();
			updateFromClass();
		}
	}

	/**
	 * Event: Menu, Party, Take Short Rest with Milestone clicked.
	 * 
	 * @param event
	 */
	private void menuPartyShortRestMilestoneActionActionPerformed(ActionEvent event) {
		int n = JOptionPane.showOptionDialog(this, "Are you sure you want to take a short rest with milestone?\n" + "This will:\n"
				+ "    -Clear all monsters from the encounter\n" + "    -Refresh all non-daily PC powers\n"
				+ "    -Clear all temporary hit points\n" + "    -Refresh PC action points", "Take Short Rest with Milestone?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == JOptionPane.YES_OPTION) {
			Settings.setRest("Short Rest with Milestone");
			_tableRoster.clearSelection();
			getFight().takeShortRestWithMilestone();
			updateFromClass();
		}
	}

	/**
	 * Event: Menu, Options, Show Full Init Display (un)checked.
	 * 
	 * @param event
	 */
	private void menuWindowsFullInitDisplayStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			_checkBoxMenuItemMinimalInitDisplay.setSelected(false);
			getInitDisplay().dispose();
			setFullInit(true);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			if (ge.getScreenDevices().length > 1) {
				getInitDisplay().setUndecorated(true);
				ge.getScreenDevices()[1].setFullScreenWindow(getInitDisplay());
			} else {
				getInitDisplay().setUndecorated(false);
				getInitDisplay().setBounds(0, 0, 1024, 768);
			}
			getInitDisplay().setVisible(true);
			updateInitDisplay();
		} else {
			getInitDisplay().setVisible(false);
		}
	}

	/**
	 * Event: Menu, Options, Show Minimal Init Display clicked.
	 * 
	 * @param event
	 */
	private void menuWindowsMinimalInitDisplayStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			_checkBoxMenuItemFullInitDisplay.setSelected(false);
			getInitDisplay().dispose();
			setFullInit(false);
			GraphicsEnvironment gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
			if (gc.getScreenDevices().length > 1) {
				getInitDisplay().setUndecorated(true);
				gc.getScreenDevices()[getSecondDisplayIndex()].setFullScreenWindow(getInitDisplay());
			} else {
				getInitDisplay().setBounds(0, 0, 1024, 768);
				getInitDisplay().setUndecorated(false);
			}
			getInitDisplay().setVisible(true);
			updateInitDisplay();
		} else {
			getInitDisplay().setVisible(false);
		}
	}

	private int getSecondDisplayIndex() {
		return _secondDisplayIndex;
	}

	private void setSecondDisplayIndex(int index) {
		_secondDisplayIndex = index;
	}

	private void menuWindowsOptionsActionActionPerformed(ActionEvent event) {
		Options options = new Options();
		options.setLocationRelativeTo(this);
		options.setVisible(true);
		options.dispose();

		((DefaultComboBoxModel) _comboBoxPlaylist.getModel()).removeAllElements();
		loadPlaylists(Settings.getMusicDirectory());
	}

	private void menuWindowsMoveInitDisplayActionActionPerformed(ActionEvent event) {
		GraphicsEnvironment gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
		int numScreens = gc.getScreenDevices().length;
		if (numScreens > 1) {
			setSecondDisplayIndex(getSecondDisplayIndex() + 1);
			if (getSecondDisplayIndex() >= numScreens) {
				setSecondDisplayIndex(0);
			}
			gc.getScreenDevices()[getSecondDisplayIndex()].setFullScreenWindow(getInitDisplay());
		}
	}

	/**
	 * Re-initializes the system.
	 */
	private void newEncounter() {
		getFight().clearAll();
		updateFromClass();
	}

	/**
	 * Update power information.
	 */
	private void powerInfoUpdate() {
		if (_listPowers.getSelectedIndex() >= 0) {
			DefaultListModel model = (DefaultListModel) _listPowers.getModel();
			Power selected = (Power) model.get(_listPowers.getSelectedIndex());
			if (getListSelectedFighter() != null) {
				for (Power pow : getListSelectedFighter().getPowerList()) {
					if (selected.getName().contentEquals(pow.getName())) {
						if (pow.getURL().startsWith("http")) {
							try {
								URL url = new URL(pow.getURL());
								BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
								String inputLine;
								String content = "";

								while ((inputLine = reader.readLine()) != null) {
									if (!inputLine.matches(".*<link.*")) {
										content += inputLine.replaceAll("<img[^>]*>", "").replaceAll(
												"<span class=\"level\">([^<]*)</span>", "$0: ");
									}
								}
								_textPanePowerBrowser.setText(content);
								_textPanePowerBrowser.setCaretPosition(0);
							} catch (MalformedURLException e) {
								_textPanePowerBrowser.setText("<html><body><h1>Failed to load URL</h1><pre>" + e
										+ "</pre></body></html>");
							} catch (IOException e) {
								_textPanePowerBrowser.setText("<html><body><h1>Failed to load URL</h1><pre>" + e
										+ "</pre></body></html>");
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Load active {@link Combatant} {@link Power}s to power list.
	 */
	private void powerLoad() {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			_listPowers.setCellRenderer(new PowerCellRenderer(fighter));
			DefaultListModel model = (DefaultListModel) _listPowers.getModel();
			model.clear();

			for (Power pow : fighter.getPowerList()) {
				model.addElement(pow);
			}
			powerInfoUpdate();
		}
	}

	/**
	 * Reloads the roster from class information.
	 */
	private void reloadListFromClass() {
		_tableRoster.setDefaultRenderer(Object.class, getRosterRenderer());
		DefaultTableModel model = (DefaultTableModel) _tableRoster.getModel();
		while (getFight().size() < _tableRoster.getRowCount()) {
			model.removeRow(_tableRoster.getRowCount() - 1);
		}

		Integer index = 0;
		while (index < getFight().size()) {
			Combatant fighter = getFight().getFighterByIndex(index);

			if (fighter == null) {
				break;
			}

			if (index < _tableRoster.getRowCount()) {
				_tableRoster.setValueAt(fighter.isShown().toString().substring(0, 1).toUpperCase(), index, 0);
				_tableRoster.setValueAt(fighter.getRound(), index, 1);
				_tableRoster.setValueAt(fighter.getCombatHandle(), index, 2);
				_tableRoster.setValueAt(fighter.getStatusLine(), index, 3);
				_tableRoster.setValueAt(fighter.getAC(), index, 4);
				_tableRoster.setValueAt(fighter.getFort(), index, 5);
				_tableRoster.setValueAt(fighter.getRef(), index, 6);
				_tableRoster.setValueAt(fighter.getWill(), index, 7);
			} else {
				model.addRow(new Object[] { fighter.isShown().toString().substring(0, 1).toUpperCase(), fighter.getRound(),
						fighter.getCombatHandle(), fighter.getStatusLine(), fighter.getAC(), fighter.getFort(), fighter.getRef(),
						fighter.getWill() });
			}

			if (fighter.getInitStatus().contentEquals("Rolled")) {
				// set group to 0
				if (fighter.getRound() == 0) {
					_tableRoster.setValueAt("S", index, 1);
				} else {
					_tableRoster.setValueAt(fighter.getRound(), index, 1);
				}
			} else if (fighter.getInitStatus().contentEquals("Delay")) {
				// set group to 1
				_tableRoster.setValueAt("D", index, 1);
			} else if (fighter.getInitStatus().contentEquals("Ready")) {
				// set group to 2
				_tableRoster.setValueAt("R", index, 1);
			} else if (fighter.getInitStatus().contentEquals("Inactive")) {
				// set group to 3
				_tableRoster.setValueAt("I", index, 1);
			} else if (fighter.getInitStatus().contentEquals("Rolling")) {
				// set group to 4
				_tableRoster.setValueAt("x", index, 1);
			} else {
				// set group to 5
				_tableRoster.setValueAt("", index, 1);
			}

			index++;
		}
		ColumnsAutoSizer.sizeColumnsToFit(_tableRoster, 15);

		updateOffTurnPowers();
		updateInitDisplay();
		updateEnabledControls();
		updateEncounterXP();
	}

	/**
	 * Saves the current encounter to a file.
	 */
	private void saveEncounter() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save Encounter File");
		fc.setCurrentDirectory(Settings.getWorkingDirectory());
		fc.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".xml") || f.isDirectory();
			}

			@Override
			public String getDescription() {
				return "Encounter files (*.xml)";
			}
		});

		if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			Settings.setWorkingDirectory(fc.getCurrentDirectory());
			File f = fc.getSelectedFile();
			String name = f.getName();
			if (name.indexOf(".") < 0) {
				name += ".xml";
			}
			getFight().saveToFile(f.getParent() + File.separator + name);
		}
	}

	/**
	 * Saves the text in the global notes text area to the class.
	 */
	private void saveGlobalNotes() {
		if (getFight() != null) {
			getFight().setGlobalNotes(_textAreaNotes.getText());
		}
	}

	/**
	 * Sets the encounter used by the tracker.
	 * 
	 * @param fight
	 *            the encounter
	 */
	private void setFight(Encounter fight) {
		_fight = fight;
	}

	/**
	 * Sets an indicator of full initiative information display.
	 * 
	 * @param fullInit
	 *            true, if full initiative information should be displayed
	 */
	private void setFullInit(Boolean fullInit) {
		_fullInit = fullInit;
	}

	/**
	 * Sets the stat library.
	 * 
	 * @param statlib
	 *            the stat library
	 */
	private void setStatlib(StatLibrary statlib) {
		_statlib = statlib;
	}

	/**
	 * Clear fighter statistics.
	 */
	private void statDataClear() {
		_textPaneStatblock.setText("");
		((DefaultListModel) _listPowers.getModel()).clear();
		((DefaultListModel) _listEffects.getModel()).clear();
		_textFieldName.setText("");
		_textFieldNumber.setText("");
		_spinnerInitRoll.setValue(0);
		_spinnerActionPoints.setValue(0);
		_spinnerPowerPoints.setValue(0);
		_chckbxSpent.setSelected(false);
		_textFieldSurges.setText("");
		getFight().clearSelectedFighter();
		statDataDisable();
	}

	private void statDataDisable() {
		_textFieldName.setEnabled(false);
		_spinnerInitRoll.setEnabled(false);
		_chckbxSpent.setEnabled(false);
		_buttonBackUp.setEnabled(false);
		_buttonDelay.setEnabled(false);
		_buttonMoveToTop.setEnabled(false);
		_buttonReady.setEnabled(false);
		_buttonReserve.setEnabled(false);
		_buttonRollInitiative.setEnabled(false);
		_spinnerAmount.setEnabled(false);
		_buttonDamage.setEnabled(false);
		_buttonFailDeath.setEnabled(false);
		_button5.setEnabled(false);
		_buttonPlus5.setEnabled(false);
		_buttonSurge.setEnabled(false);
		_buttonMax.setEnabled(false);
		_textFieldSurges.setEnabled(false);
		_buttonPlus1.setEnabled(false);
		_buttonMinus1.setEnabled(false);
		_buttonRegainAll.setEnabled(false);
		_buttonHeal.setEnabled(false);
		_buttonUndoDeath.setEnabled(false);
		_buttonAddTemp.setEnabled(false);
		_buttonRemoveFighter.setEnabled(false);
		_listPowers.setEnabled(false);
		_listEffects.setEnabled(false);
		_buttonAdd.setEnabled(false);
		_buttonChange.setEnabled(false);
		_buttonRemove.setEnabled(false);
		_popupMenuRoster.setEnabled(false);
	}

	private void statDataEnable(Combatant fighter) {
		if (fighter != null) {
			_textFieldName.setEnabled(true);
			_spinnerInitRoll.setEnabled(true);
			_listPowers.setEnabled(true);
			_listEffects.setEnabled(true);
			_buttonBackUp.setEnabled(false);
			_buttonDelay.setEnabled(false);
			_buttonMoveToTop.setEnabled(false);
			_buttonReady.setEnabled(false);
			_buttonReserve.setEnabled(false);
			_buttonRollInitiative.setEnabled(false);
			_buttonFailDeath.setEnabled(false);
			_buttonUndoDeath.setEnabled(false);
			_spinnerAmount.setEnabled(false);
			_buttonDamage.setEnabled(false);
			_buttonHeal.setEnabled(false);
			_buttonAddTemp.setEnabled(false);
			_button5.setEnabled(false);
			_buttonPlus5.setEnabled(false);
			_buttonSurge.setEnabled(false);
			_buttonMax.setEnabled(false);
			_textFieldSurges.setEnabled(false);
			_buttonPlus1.setEnabled(false);
			_buttonMinus1.setEnabled(false);
			_buttonRemoveFighter.setEnabled(true);
			_chckbxSpent.setEnabled(true);

			if (fighter.getInitStatus().contentEquals("Rolled")) {
				_buttonBackUp.setEnabled(true);
				_buttonDelay.setEnabled(true);
				_buttonMoveToTop.setEnabled(true);
				_buttonReady.setEnabled(true);
				_buttonReserve.setEnabled(true);
			} else if (fighter.getInitStatus().contentEquals("Ready")) {
				_buttonDelay.setEnabled(true);
				_buttonMoveToTop.setEnabled(true);
				_buttonReserve.setEnabled(true);
			} else if (fighter.getInitStatus().contentEquals("Delay")) {
				_buttonMoveToTop.setEnabled(true);
				_buttonReady.setEnabled(true);
				_buttonReserve.setEnabled(true);
			} else if (fighter.getInitStatus().contentEquals("Reserve")) {
				_buttonRollInitiative.setEnabled(true);
			} else {
				_buttonReserve.setEnabled(true);
			}

			if (getFight().getCurrentFighter() != null && _buttonMoveToTop.isEnabled()
					&& getFight().getCurrentFighter().getCombatHandle().contentEquals(fighter.getCombatHandle())) {
				_buttonMoveToTop.setEnabled(false);
			}

			if (!fighter.getInitStatus().contentEquals("Reserve") || fighter.isPC()) {
				_spinnerInitRoll.setEnabled(true);
				_spinnerAmount.setEnabled(true);
				_button5.setEnabled(true);
				_buttonPlus5.setEnabled(true);
				_buttonSurge.setEnabled(true);
				_buttonMax.setEnabled(true);
				_textFieldSurges.setEnabled(true);
				_buttonAdd.setEnabled(true);
				_buttonRemove.setEnabled(true);
				if (fighter.isPC()) {
					_buttonPlus1.setEnabled(true);
					_buttonMinus1.setEnabled(true);
					_buttonRegainAll.setEnabled(true);
					if (!fighter.isActive()) {
						if (fighter.isAlive()) {
							_buttonFailDeath.setEnabled(true);
						}
						if (fighter.isDyingOrDead()) {
							_buttonUndoDeath.setEnabled(true);
						}
					}
				}
				if (Integer.valueOf(_spinnerAmount.getValue().toString().replace(",", "")) > 0) {
					_buttonDamage.setEnabled(true);
					_buttonHeal.setEnabled(true);
					if (fighter.isAlive()) {
						_buttonAddTemp.setEnabled(true);
					} else {
						_buttonAddTemp.setEnabled(false);
					}
				} else {
					_buttonDamage.setEnabled(false);
					_buttonHeal.setEnabled(false);
					_buttonAddTemp.setEnabled(false);
				}
			}
			powerInfoUpdate();
			effectButtonUpdate();
			_popupMenuRoster.setEnabled(true);
			_menuItemDelay.setEnabled(_buttonDelay.isEnabled());
			_menuItemMoveToTop.setEnabled(_buttonMoveToTop.isEnabled());
			_menuItemReady.setEnabled(_buttonReady.isEnabled());
			_menuItemMarkEONT.setEnabled(true);
			_menuItemMarkEOE.setEnabled(true);
		}
	}

	/**
	 * Load fighter statistics.
	 */
	private void statDataLoad() {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			_textFieldName.setText(fighter.getName());
			if (fighter.getFighterNumber() > 0) {
				_textFieldNumber.setText(fighter.getFighterNumber().toString());
			} else {
				_textFieldNumber.setText("PC");
			}
			_spinnerInitRoll.setValue(fighter.getInitRoll());
			_spinnerActionPoints.setValue(fighter.getActionPointsRemaining());
			_spinnerPowerPoints.setValue(fighter.getPowerPointsRemaining());
			_chckbxSpent.setSelected(fighter.isActionPointSpent());

			_textPaneStatblock.setText(fighter.getStatsHTML());
			_textPaneStatblock.setCaretPosition(0);
			_textFieldSurges.setText(fighter.getSurgeView());

			powerLoad();
			effectLoad();

			statDataEnable(fighter);
		}
	}

	/**
	 * Updates the enabled controls based on encounter state.
	 */
	private void updateEnabledControls() {
		_menuItemSave.setEnabled(false);
		_menuItemEnd.setEnabled(false);
		_menuItemRollInit.setEnabled(false);
		_menuItemRemoveNPCs.setEnabled(false);
		_checkBoxMenuItemMinimalInitDisplay.setEnabled(false);
		_checkBoxMenuItemFullInitDisplay.setEnabled(false);
		_menuItemShortRest.setEnabled(false);
		_menuItemMilestone.setEnabled(false);
		_menuItemExtendedRest.setEnabled(false);
		_menuItemRemovePCs.setEnabled(false);

		_buttonNextTurn.setEnabled(false);

		if (getFight().size() > 0) {
			_menuItemSave.setEnabled(true);

			if (getFight().getReserveList().size() > 0) {
				_menuItemRollInit.setEnabled(true);
			}

			if (!getFight().isOngoingFight()) {
				_menuItemRemoveNPCs.setEnabled(true);
				_menuItemRemovePCs.setEnabled(true);
				_menuItemShortRest.setEnabled(true);
				_menuItemMilestone.setEnabled(true);
				_menuItemExtendedRest.setEnabled(true);
			} else {
				if (getFight().getRolledList().size() > 0) {
					_menuItemEnd.setEnabled(true);
					_checkBoxMenuItemMinimalInitDisplay.setEnabled(true);
					_checkBoxMenuItemFullInitDisplay.setEnabled(true);
					_buttonNextTurn.setEnabled(true);
				}
			}
		}
	}

	/**
	 * Updates display of encounter experience total.
	 */
	private void updateEncounterXP() {
		int index = 0;
		int xp = 0;

		while (index < getFight().size()) {
			Combatant fighter = getFight().getFighterByIndex(index);

			if (!fighter.isPC()) {
				xp += fighter.getStats().getXP();
			}

			index++;
		}

		_textFieldXP.setText("Total XP: " + xp);
	}

	/**
	 * Updates display from class information.
	 */
	private void updateFromClass() {
		_textAreaNotes.setText(getFight().getGlobalNotes());
		_labelPreviousRest.setText("Previous Rest: " + Settings.getRest());
		reloadListFromClass();

		if (getFight().hasSelectedFighter()) {
			Combatant fighter = getFight().getSelectedFighter();
			for (int i = 0; i < _tableRoster.getRowCount(); i++) {
				String handle = (String) _tableRoster.getValueAt(i, 2);
				if (handle.contentEquals(fighter.getCombatHandle())) {
					_tableRoster.getSelectionModel().setSelectionInterval(i, i);
				}
			}
			statDataLoad();
		} else {
			statDataClear();
		}
	}

	/**
	 * Regenerates the initiative display from current information.
	 */
	private void updateInitDisplay() {
		Integer index = 0;
		Integer max = 0;
		Integer min = 999;
		Integer currentRound = -1;
		Integer round = -1;
		String text;

		text = "<html><head><style type='text/css'>\n" + "body { margin: 0ex; }\n"
				+ "table { width: 100%; border-width: 1px; border-style: solid; border-color: black }\n" + "th { font-size: "
				+ Settings.getFontSize() + "; border-width: 1px; border-style: solid; border-color: black }\n" + "td { font-size: "
				+ Settings.getFontSize() + "; border-width: 1px; border-style: solid; border-color: gray }\n"
				+ "</style></head><body><table><tr><th style='width: 12ex'>Combatant</th>"
				+ "<th style='width: 105px'>HP</th><th style='width: 3ex'>A</th>\n"
				+ "<th style='width: 3ex'>F</th><th style='width: 3ex'>R</th>\n"
				+ "<th style='width: 3ex'>W</th><th>Status Effects</th></tr>\n";

		while (index < getFight().size()) {
			Combatant fighter = getFight().getFighterByIndex(index);

			if (!fighter.isShown()) {
				index++;
				continue;
			}

			String name = fighter.getName();
			String hpBarColor;

			if (!fighter.isPC()) {
				name += " " + fighter.getFighterNumber();
			}

			if (!name.isEmpty() && !fighter.isDyingOrDead() || fighter.isPC()) {
				if (fighter.isBloody() && (isFullInit() || fighter.isPC())) {
					hpBarColor = "#aa0000";
				} else {
					hpBarColor = "#00aa00";
				}

				if (currentRound < 0) {
					currentRound = fighter.getRound();
				}
				if (round < 0) {
					round = fighter.getRound();
				}
				if (round < fighter.getRound()) {
					round = fighter.getRound();
					text += "<tr><th colspan='7'>Round " + round + "</th></tr>";
				}

				text += "<tr><th style='text-align: left; color: ";
				if (fighter.isPC()) {
					text += "#00aa00";
				} else {
					text += "#aa0000";
					if (fighter.getAC() > max) {
						max = fighter.getAC();
					}
					if (fighter.getFort() > max) {
						max = fighter.getFort();
					}
					if (fighter.getRef() > max) {
						max = fighter.getRef();
					}
					if (fighter.getWill() > max) {
						max = fighter.getWill();
					}
					if (fighter.getAC() < min) {
						min = fighter.getAC();
					}
					if (fighter.getFort() < min) {
						min = fighter.getFort();
					}
					if (fighter.getRef() < min) {
						min = fighter.getRef();
					}
					if (fighter.getWill() < min) {
						min = fighter.getWill();
					}
				}
				text += "'>" + name + "</th>";

				if (fighter.isPC() && fighter.getCurrHP() <= 0) {
					text += "<td>Dying: " + fighter.getDeathStatus() + "</td>";
				} else if ((fighter.isPC() || isFullInit()) && fighter.getMaxHP() > 0) {
					int healthPercent = (int) (((double) fighter.getCurrHP() / (double) fighter.getMaxHP()) * 100);
					text += "<td style='width: 105px'>";
					if (fighter.isPC()) {
						text += "<div style='color: " + hpBarColor + "; text-align: center; width: 100px'>" + fighter.getCurrHP()
								+ "/" + fighter.getMaxHP();
						if (fighter.getTempHP() > 0) {
							text += "<span style='color: blue'>+" + fighter.getTempHP() + "</span>";
						}
						text += "</div>";
					} else {
						text += "<div style='height: 1em; border-width: 1px; border-style: solid; border-color: white; "
								+ "width: 100px'><div style='border-width: 0px; width: " + healthPercent + "px; background-color: "
								+ hpBarColor + "'></div></div>";
					}
					text += "</td>";
				} else if (fighter.isBloody()) {
					text += "<td><span style='color: red'>bloody</span></td>";
				} else {
					text += "<td>&nbsp;</td>";
				}

				if (isFullInit() || fighter.isPC()) {
					text += "<td style='text-align: center'>" + fighter.getAC() + "</td>" + "<td style='text-align: center'>"
							+ fighter.getFort() + "</td>" + "<td style='text-align: center'>" + fighter.getRef() + "</td>"
							+ "<td style='text-align: center'>" + fighter.getWill() + "</td>";
				} else {
					text += "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
				}

				text += "<td>";
				for (Effect eff : getFight().getEffectsByTarget(fighter.getCombatHandle())) {
					if (eff.isBeneficial()) {
						text += "<span style='color: #00aa00'>";
					} else {
						text += "<span style='color: #aa0000'>";
					}

					String dur = eff.getDurationCode().getDesc();
					dur = dur.replace("Start of Source's Next Turn", "SOT " + eff.getSourceHandle())
							.replace("Start of Target's Next Turn", "SOT " + eff.getTargetHandle())
							.replace("End of Source's Next Turn", "EOT " + eff.getSourceHandle())
							.replace("End of Target's Next Turn", "EOT " + eff.getTargetHandle())
							.replace("End of the Encounter", "EOE").replace("Save Ends", "SE");

					text += eff.getName().replaceFirst("[(].*?[)] ", "");
					text += " (" + dur + ")</span><br>";
				}
				text += "</td></tr>\n";
			}
			index++;
		}

		text = "<h1>Round " + currentRound + ". Defenses: " + min + "-" + max + "</h1><br>" + text + "</table></body></html>";

		getInitDisplay().setHTML(text);
	}

	/**
	 * Updates the list of off-turn powers.
	 */
	private void updateOffTurnPowers() {
		_listOffTurnPowers.setCellRenderer(new OffTurnPowerRenderer(getFight()));
		SortedMap<String, FighterPower> powers = new TreeMap<String, FighterPower>();
		DefaultListModel model = (DefaultListModel) _listOffTurnPowers.getModel();
		model.clear();
		for (String handle : getFight().getRolledList().values()) {
			Combatant fighter = getFight().getFighterByHandle(handle);
			for (Power pow : fighter.getPowerList()) {
				if (!fighter.isPowerUsed(pow.getName())
						&& !fighter.isPC()
						&& (pow.getAction().contains("immediate") || pow.getAction().contains("opportunity;")
								|| pow.getAction().contains("triggered;") || pow.getAction().contains("free;")
								|| pow.getAction().contains("no;") || pow.isAura())) {
					powers.put(fighter.getStats().getHandle() + pow.getName(), new FighterPower(fighter, pow));
				}
			}
		}

		for (String key : powers.keySet()) {
			model.addElement(powers.get(key));
		}
	}

	/**
	 * Event: window closing.
	 * 
	 * @param event
	 */
	private void windowWindowClosing(WindowEvent event) {
		exitEncounter();
	}

	/**
	 * Event: window open.
	 * 
	 * @param event
	 */
	private void windowWindowOpened(WindowEvent event) {
		setStatlib(new StatLibrary());
		File stats = new File(getStatlibFilename());
		if (stats.exists()) {
			getStatlib().loadFromFile(getStatlibFilename(), true);
		}

		setFight(new Encounter(getStatlib(), this));

		updateEnabledControls();

		// populate music playlists
		((DefaultComboBoxModel) _comboBoxPlaylist.getModel()).removeAllElements();
		loadPlaylists(Settings.getMusicDirectory());
	}

	/**
	 * Event: Font Size selection changed.
	 * 
	 * @param event
	 */
	private void fontSizeSelectionChanged(ItemEvent event) {
		JRadioButtonMenuItem source = (JRadioButtonMenuItem) event.getSource();
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Settings.setFontSize(source.getActionCommand());
			if (getFight() != null) {
				updateInitDisplay();
			}
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
