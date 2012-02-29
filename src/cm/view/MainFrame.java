package cm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

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
import cm.util.music.PlayerListener;
import cm.view.render.EffectDetailsCellRenderer;
import cm.view.render.OffTurnPowerRenderer;
import cm.view.render.PlaylistCellRenderer;
import cm.view.render.PowerCellRenderer;
import cm.view.render.RosterRenderer;

/**
 * Displays the main interface for the application.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
// VS4E -- DO NOT REMOVE THIS LINE!
public class MainFrame extends JFrame {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -5968785836556756134L;

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

	private JButton jButtonAdd;
	private JButton jButtonAddTemp;
	private JButton jButtonBackUp;
	private JButton jButtonChange;
	private JButton jButtonDamage;
	private JButton jButtonDelay;
	private JButton jButtonFailDeath;
	private JButton jButtonFive;
	private JButton jButtonHalve;
	private JButton jButtonHeal;
	private JButton jButtonMax;
	private JButton jButtonMinusOne;
	private JButton jButtonMoveToTop;
	private JButton jButtonNextTurn;
	private JButton jButtonPlusFive;
	private JButton jButtonPlusOne;
	private JButton jButtonPowerPointsMinusFour;
	private JButton jButtonPowerPointsMinusSix;
	private JButton jButtonPowerPointsMinusTwo;
	private JButton jButtonReady;
	private JButton jButtonRegainAll;
	private JButton jButtonRemove;
	private JButton jButtonRemoveFighter;
	private JButton jButtonReserve;
	private JButton jButtonRollInitiative;
	private JButton jButtonSurge;
	private JButton jButtonUndoDeath;
	private JCheckBox jCheckBoxSpent;
	private JComboBox jComboBoxPlaylists;
	private JEditorPane jEditorPaneCompendium;
	private JEditorPane jEditorPaneStatblock;
	private JLabel jLabelInitRoll;
	private JLabel jLabelName;
	private JList jListEffects;
	private JList jListOffTurnPowers;
	private JList jListPowerList;
	private JMenuItem jMenuItemDelay;
	private JMenuItem jMenuItemLogOA;
	private JMenuItem jMenuItemMarkUntilEOE;
	private JMenuItem jMenuItemMarkUntilEONT;
	private JMenuItem jMenuItemMoveToTop;
	private JMenuItem jMenuItemReady;
	private JMenuItem jMenuItemToggleVisibility;
	private JPanel jPanelActionPoints;
	private JPanel jPanelBottomCenter;
	private JPanel jPanelCrits;
	private JPanel jPanelDamageHealing;
	private JPanel jPanelEffectButtons;
	private JPanel jPanelEffects;
	private JPanel jPanelHealth;
	private JPanel jPanelInitiative;
	private JPanel jPanelMisc;
	private JPanel jPanelMusic;
	private JPanel jPanelPoints;
	private JPanel jPanelPowerPoints;
	private JPanel jPanelSurges;
	private JPanel jPanelTopCenter;
	private JPopupMenu jPopupMenuRoster;
	private JScrollPane jScrollPaneCompendium;
	private JScrollPane jScrollPaneEffects;
	private JScrollPane jScrollPaneNotes;
	private JScrollPane jScrollPaneNowPlaying;
	private JScrollPane jScrollPaneOffTurnPowers;
	private JScrollPane jScrollPanePowerList;
	private JScrollPane jScrollPaneRoster;
	private JScrollPane jScrollPaneStatblock;
	private JSeparator jSeparator0;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JSeparator jSeparator3;
	private JSeparator jSeparator4;
	private JSpinner jSpinnerActionPoints;
	private JSpinner jSpinnerDamageHealAmount;
	private JSpinner jSpinnerInitRoll;
	private JSpinner jSpinnerPowerPoints;
	private JSplitPane jSplitPaneCenter;
	private JSplitPane jSplitPaneLeft;
	private JSplitPane jSplitPaneMain;
	private JSplitPane jSplitPaneRight;
	private JSplitPane jSplitPaneSub;
	private JTabbedPane jTabbedPaneControls;
	private JTabbedPane jTabbedPaneUtils;
	private JTable jTableRoster;
	private JTextArea jTextAreaNotes;
	private JTextArea jTextAreaNowPlaying;
	private JTextField jTextFieldName;
	private JTextField jTextFieldNumber;
	private JTextField jTextFieldSurges;
	private JToggleButton jToggleButtonCritHit;
	private JToggleButton jToggleButtonCritMiss;
	private JToggleButton jToggleButtonMiscDaily;
	private JToggleButton jToggleButtonMiscVictory;
	private JToggleButton jToggleButtonPlay;
	private JMenuBar menuBarMain;
	private JMenu menuEncounter;
	private JMenuItem menuEncounterEnd;
	private JMenuItem menuEncounterInitiative;
	private JMenuItem menuEncounterRemoveMonsters;
	private JMenu menuFile;
	private JMenuItem menuFileExit;
	private JMenuItem menuFileImport;
	private JMenuItem menuFileNew;
	private JMenuItem menuFileOpen;
	private JMenuItem menuFileSave;
	private JMenu menuHelp;
	private JMenuItem menuHelpAbout;
	private JMenu menuLibrary;
	private JMenuItem menuLibraryOpen;
	private JMenu menuParty;
	private JMenuItem menuPartyExtendedRest;
	private JMenuItem menuPartyRemove;
	private JMenuItem menuPartyShortRest;
	private JMenuItem menuPartyShortRestMilestone;
	private JMenu menuWindows;
	private JCheckBoxMenuItem menuWindowsFullInitDisplay;
	private JCheckBoxMenuItem menuWindowsMinimalInitDisplay;
	private JMenuItem menuWindowsOptions;
	private JRadioButtonMenuItem jRadioButtonMenuItemSmall;
	private JRadioButtonMenuItem jRadioButtonMenuItemMedium;
	private JRadioButtonMenuItem jRadioButtonMenuItemLarge;
	private JRadioButtonMenuItem jRadioButtonMenuItemExtraLarge;
	private JRadioButtonMenuItem jRadioButtonMenuItemExtraExtraLarge;
	private JMenu menuWindowsFontSize;
	private ButtonGroup fontSizeButtonGroup;
	private JLabel jLabelPreviousRest;

	/**
	 * Displays the interface.
	 */
	public MainFrame() {
		initComponents();
		if (Settings.getFontSize().contentEquals("small")) {
			getJRadioButtonMenuItemSmall().setSelected(true);
		} else if (Settings.getFontSize().contentEquals("medium")) {
			getJRadioButtonMenuItemMedium().setSelected(true);
		} else if (Settings.getFontSize().contentEquals("large")) {
			getJRadioButtonMenuItemLarge().setSelected(true);
		} else if (Settings.getFontSize().contentEquals("x-large")) {
			getJRadioButtonMenuItemExtraLarge().setSelected(true);
		} else if (Settings.getFontSize().contentEquals("xx-large")) {
			getJRadioButtonMenuItemExtraExtraLarge().setSelected(true);
		}
		
		Player.setListener(new PlayerListener() {

			@Override
			public void playbackStarted() {
				if (Player.getFile() != null) {
					getJTextAreaNowPlaying().setText(Player.getFile().getName());
				}
			}

			@Override
			public void playbackStopped() {
				getJTextAreaNowPlaying().setText("(nothing)");
			}
		});
	}

	/**
	 * Event: Window resized.
	 * 
	 * @param event
	 */
	private void componentComponentResized(ComponentEvent event) {
		ColumnsAutoSizer.sizeColumnsToFit(getJTableRoster(), 15);
	}

	/**
	 * Enables/disables effect change and remove buttons based on list
	 * selection.
	 */
	private void effectButtonUpdate() {
		if (getJListEffects().getSelectedIndices().length > 0) {
			getJButtonChange().setEnabled(true);
			getJButtonRemove().setEnabled(true);
		} else {
			getJButtonChange().setEnabled(false);
			getJButtonRemove().setEnabled(false);
		}
	}

	/**
	 * Loads active effects for the selected {@link Combatant}.
	 */
	private void effectLoad() {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			getJListEffects().setEnabled(true);

			getJListEffects().setCellRenderer(new EffectDetailsCellRenderer());
			DefaultListModel model = (DefaultListModel) getJListEffects().getModel();
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
					getMenuWindowsMinimalInitDisplay().setSelected(false);
					getMenuWindowsFullInitDisplay().setSelected(false);
				}
			});
		}
		return _initDisplay;
	}

	private JButton getJButtonAdd() {
		if (jButtonAdd == null) {
			jButtonAdd = new JButton();
			jButtonAdd.setText("Add");
			jButtonAdd.setMargin(new Insets(0, 0, 0, 0));
			jButtonAdd.setEnabled(false);
			jButtonAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonAddActionActionPerformed(event);
				}
			});
		}
		return jButtonAdd;
	}

	private JButton getJButtonAddTemp() {
		if (jButtonAddTemp == null) {
			jButtonAddTemp = new JButton();
			jButtonAddTemp.setText("<html>Add Temp</html>");
			jButtonAddTemp.setMargin(new Insets(0, 0, 0, 0));
			jButtonAddTemp.setEnabled(false);
			jButtonAddTemp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonAddTempActionActionPerformed(event);
				}
			});
		}
		return jButtonAddTemp;
	}

	private JButton getJButtonBackUp() {
		if (jButtonBackUp == null) {
			jButtonBackUp = new JButton();
			jButtonBackUp.setText("<html>Back Up</html>");
			jButtonBackUp.setMargin(new Insets(0, 0, 0, 0));
			jButtonBackUp.setEnabled(false);
			jButtonBackUp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonBackUpActionActionPerformed(event);
				}
			});
		}
		return jButtonBackUp;
	}

	private JButton getJButtonChange() {
		if (jButtonChange == null) {
			jButtonChange = new JButton();
			jButtonChange.setText("Change");
			jButtonChange.setMargin(new Insets(0, 0, 0, 0));
			jButtonChange.setEnabled(false);
			jButtonChange.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonChangeActionActionPerformed(event);
				}
			});
		}
		return jButtonChange;
	}

	private JButton getJButtonDamage() {
		if (jButtonDamage == null) {
			jButtonDamage = new JButton();
			jButtonDamage.setText("<html>Damage</html>");
			jButtonDamage.setMargin(new Insets(0, 0, 0, 0));
			jButtonDamage.setEnabled(false);
			jButtonDamage.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonDamageActionActionPerformed(event);
				}
			});
		}
		return jButtonDamage;
	}

	private JButton getJButtonDelay() {
		if (jButtonDelay == null) {
			jButtonDelay = new JButton();
			jButtonDelay.setText("<html>Delay</html>");
			jButtonDelay.setMargin(new Insets(0, 0, 0, 0));
			jButtonDelay.setEnabled(false);
			jButtonDelay.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonDelayActionActionPerformed(event);
				}
			});
		}
		return jButtonDelay;
	}

	private JButton getJButtonFailDeath() {
		if (jButtonFailDeath == null) {
			jButtonFailDeath = new JButton();
			jButtonFailDeath.setText("<html>Fail Death</html>");
			jButtonFailDeath.setMargin(new Insets(0, 0, 0, 0));
			jButtonFailDeath.setEnabled(false);
			jButtonFailDeath.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonFailDeathActionActionPerformed(event);
				}
			});
		}
		return jButtonFailDeath;
	}

	private JButton getJButtonFive() {
		if (jButtonFive == null) {
			jButtonFive = new JButton();
			jButtonFive.setText("<html>5</html>");
			jButtonFive.setMargin(new Insets(0, 0, 0, 0));
			jButtonFive.setEnabled(false);
			jButtonFive.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonFiveActionActionPerformed(event);
				}
			});
		}
		return jButtonFive;
	}

	private JButton getJButtonHalve() {
		if (jButtonHalve == null) {
			jButtonHalve = new JButton();
			jButtonHalve.setText("<html>Halve</html>");
			jButtonHalve.setMargin(new Insets(0, 0, 0, 0));
			jButtonHalve.setEnabled(false);
			jButtonHalve.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonHalveActionActionPerformed(event);
				}
			});
		}
		return jButtonHalve;
	}

	private JButton getJButtonHeal() {
		if (jButtonHeal == null) {
			jButtonHeal = new JButton();
			jButtonHeal.setText("<html>Heal</html>");
			jButtonHeal.setMargin(new Insets(0, 0, 0, 0));
			jButtonHeal.setEnabled(false);
			jButtonHeal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonHealActionActionPerformed(event);
				}
			});
		}
		return jButtonHeal;
	}

	private JButton getJButtonMax() {
		if (jButtonMax == null) {
			jButtonMax = new JButton();
			jButtonMax.setText("<html>Max</html>");
			jButtonMax.setEnabled(false);
			jButtonMax.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonMaxActionActionPerformed(event);
				}
			});
		}
		return jButtonMax;
	}

	private JButton getJButtonMinusOne() {
		if (jButtonMinusOne == null) {
			jButtonMinusOne = new JButton();
			jButtonMinusOne.setText("<html>-1</html>");
			jButtonMinusOne.setMargin(new Insets(0, 0, 0, 0));
			jButtonMinusOne.setEnabled(false);
			jButtonMinusOne.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonMinusOneActionActionPerformed(event);
				}
			});
		}
		return jButtonMinusOne;
	}

	private JButton getJButtonMoveToTop() {
		if (jButtonMoveToTop == null) {
			jButtonMoveToTop = new JButton();
			jButtonMoveToTop.setText("<html><center>Move to<br>Top</center></html>");
			jButtonMoveToTop.setMargin(new Insets(0, 0, 0, 0));
			jButtonMoveToTop.setEnabled(false);
			jButtonMoveToTop.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonMoveToTopActionActionPerformed(event);
				}
			});
		}
		return jButtonMoveToTop;
	}

	private JButton getJButtonNextTurn() {
		if (jButtonNextTurn == null) {
			jButtonNextTurn = new JButton();
			jButtonNextTurn.setText("<html>Next Turn</html>");
			jButtonNextTurn.setMargin(new Insets(0, 0, 0, 0));
			jButtonNextTurn.setEnabled(false);
			jButtonNextTurn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonNextTurnActionActionPerformed(event);
				}
			});
		}
		return jButtonNextTurn;
	}

	private JButton getJButtonPlusFive() {
		if (jButtonPlusFive == null) {
			jButtonPlusFive = new JButton();
			jButtonPlusFive.setText("<html>+5</html>");
			jButtonPlusFive.setMargin(new Insets(0, 0, 0, 0));
			jButtonPlusFive.setEnabled(false);
			jButtonPlusFive.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonPlusFiveActionActionPerformed(event);
				}
			});
		}
		return jButtonPlusFive;
	}

	private JButton getJButtonPlusOne() {
		if (jButtonPlusOne == null) {
			jButtonPlusOne = new JButton();
			jButtonPlusOne.setText("<html>+1</html>");
			jButtonPlusOne.setMargin(new Insets(0, 0, 0, 0));
			jButtonPlusOne.setEnabled(false);
			jButtonPlusOne.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonPlusOneActionActionPerformed(event);
				}
			});
		}
		return jButtonPlusOne;
	}

	private JButton getJButtonPowerPointsMinusFour() {
		if (jButtonPowerPointsMinusFour == null) {
			jButtonPowerPointsMinusFour = new JButton();
			jButtonPowerPointsMinusFour.setText("-4");
			jButtonPowerPointsMinusFour.setMargin(new Insets(0, 0, 0, 0));
			jButtonPowerPointsMinusFour.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonPowerPointsMinusFourActionActionPerformed(event);
				}
			});
		}
		return jButtonPowerPointsMinusFour;
	}

	private JButton getJButtonPowerPointsMinusSix() {
		if (jButtonPowerPointsMinusSix == null) {
			jButtonPowerPointsMinusSix = new JButton();
			jButtonPowerPointsMinusSix.setText("-6");
			jButtonPowerPointsMinusSix.setMargin(new Insets(0, 0, 0, 0));
			jButtonPowerPointsMinusSix.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonPowerPointsMinusSixActionActionPerformed(event);
				}
			});
		}
		return jButtonPowerPointsMinusSix;
	}

	private JButton getJButtonPowerPointsMinusTwo() {
		if (jButtonPowerPointsMinusTwo == null) {
			jButtonPowerPointsMinusTwo = new JButton();
			jButtonPowerPointsMinusTwo.setText("-2");
			jButtonPowerPointsMinusTwo.setMargin(new Insets(0, 0, 0, 0));
			jButtonPowerPointsMinusTwo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonPowerPointsMinusTwoActionActionPerformed(event);
				}
			});
		}
		return jButtonPowerPointsMinusTwo;
	}

	private JButton getJButtonReady() {
		if (jButtonReady == null) {
			jButtonReady = new JButton();
			jButtonReady.setText("<html>Ready</html>");
			jButtonReady.setMargin(new Insets(0, 0, 0, 0));
			jButtonReady.setEnabled(false);
			jButtonReady.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonReadyActionActionPerformed(event);
				}
			});
		}
		return jButtonReady;
	}

	private JButton getJButtonRegainAll() {
		if (jButtonRegainAll == null) {
			jButtonRegainAll = new JButton();
			jButtonRegainAll.setText("<html><center>Regain<br>All</center></html>");
			jButtonRegainAll.setMargin(new Insets(0, 0, 0, 0));
			jButtonRegainAll.setEnabled(false);
			jButtonRegainAll.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonRegainAllActionActionPerformed(event);
				}
			});
		}
		return jButtonRegainAll;
	}

	private JButton getJButtonRemove() {
		if (jButtonRemove == null) {
			jButtonRemove = new JButton();
			jButtonRemove.setText("Remove");
			jButtonRemove.setMargin(new Insets(0, 0, 0, 0));
			jButtonRemove.setEnabled(false);
			jButtonRemove.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonRemoveActionActionPerformed(event);
				}
			});
		}
		return jButtonRemove;
	}

	private JButton getJButtonRemoveFighter() {
		if (jButtonRemoveFighter == null) {
			jButtonRemoveFighter = new JButton();
			jButtonRemoveFighter.setText("<html><center>Remove<br>Fighter</center></html>");
			jButtonRemoveFighter.setMargin(new Insets(0, 0, 0, 0));
			jButtonRemoveFighter.setEnabled(false);
			jButtonRemoveFighter.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonRemoveFighterActionActionPerformed(event);
				}
			});
		}
		return jButtonRemoveFighter;
	}

	private JButton getJButtonReserve() {
		if (jButtonReserve == null) {
			jButtonReserve = new JButton();
			jButtonReserve.setText("<html>Reserve</html>");
			jButtonReserve.setMargin(new Insets(0, 0, 0, 0));
			jButtonReserve.setEnabled(false);
			jButtonReserve.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonReserveActionActionPerformed(event);
				}
			});
		}
		return jButtonReserve;
	}

	private JButton getJButtonRollInitiative() {
		if (jButtonRollInitiative == null) {
			jButtonRollInitiative = new JButton();
			jButtonRollInitiative.setText("<html><center>Roll<br>Initiative</center></html>");
			jButtonRollInitiative.setMargin(new Insets(0, 0, 0, 0));
			jButtonRollInitiative.setEnabled(false);
			jButtonRollInitiative.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonRollInitiativeActionActionPerformed(event);
				}
			});
		}
		return jButtonRollInitiative;
	}

	private JButton getJButtonSurge() {
		if (jButtonSurge == null) {
			jButtonSurge = new JButton();
			jButtonSurge.setText("<html>Surge</html>");
			jButtonSurge.setMargin(new Insets(0, 0, 0, 0));
			jButtonSurge.setEnabled(false);
			jButtonSurge.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonSurgeActionActionPerformed(event);
				}
			});
		}
		return jButtonSurge;
	}

	private JButton getJButtonUndoDeath() {
		if (jButtonUndoDeath == null) {
			jButtonUndoDeath = new JButton();
			jButtonUndoDeath.setText("<html>Undo Death</html>");
			jButtonUndoDeath.setMargin(new Insets(0, 0, 0, 0));
			jButtonUndoDeath.setEnabled(false);
			jButtonUndoDeath.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonUndoDeathActionActionPerformed(event);
				}
			});
		}
		return jButtonUndoDeath;
	}

	private JCheckBox getJCheckBoxSpent() {
		if (jCheckBoxSpent == null) {
			jCheckBoxSpent = new JCheckBox();
			jCheckBoxSpent.setText("Spent");
			jCheckBoxSpent.setHorizontalAlignment(SwingConstants.CENTER);
			jCheckBoxSpent.setFocusPainted(false);
			jCheckBoxSpent.setEnabled(false);
			jCheckBoxSpent.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent event) {
					jCheckBoxSpentChangeStateChanged(event);
				}
			});
		}
		return jCheckBoxSpent;
	}

	private JComboBox getJComboBoxPlaylists() {
		if (jComboBoxPlaylists == null) {
			jComboBoxPlaylists = new JComboBox();
			jComboBoxPlaylists.setDoubleBuffered(false);
			jComboBoxPlaylists.setBorder(null);
		}
		return jComboBoxPlaylists;
	}

	private JEditorPane getJEditorPaneCompendium() {
		if (jEditorPaneCompendium == null) {
			jEditorPaneCompendium = new JEditorPane();
			jEditorPaneCompendium.setContentType("text/html");
			jEditorPaneCompendium.setEditable(false);
			StyleSheet ss = ((HTMLEditorKit) jEditorPaneCompendium.getEditorKit()).getStyleSheet();
			ss.addRule("h1.atwillpower { font-size: 12pt; line-height: 2; padding-left: 15px; margin: 0pt; color: rgb(255, 255, 255); background-color: rgb(97, 152, 105) }");
			ss.addRule("h1.dailypower { font-size: 12pt; line-height: 2; padding-left: 15px; margin: 0pt; color: rgb(255, 255, 255); background-color: rgb(77, 77, 79) }");
			ss.addRule("h1.encounterpower { font-size: 12pt; line-height: 2; padding-left: 15px; margin: 0pt; color: rgb(255, 255, 255); background-color: rgb(150, 19, 52) }");
			ss.addRule(".flavor { padding: 2px 15px; margin: 0pt; background-color: rgb(214, 214, 194); font-size: 12pt }");
			ss.addRule(".level { padding-right: 15px; margin-top: 0pt; text-align: right; float: right }");
			ss.addRule(".powerstat { padding-left: 15px; margin: 0pt; background-color: rgb(255, 255, 255); font-size: 12pt }");
		}
		return jEditorPaneCompendium;
	}

	private JEditorPane getJEditorPaneStatblock() {
		if (jEditorPaneStatblock == null) {
			jEditorPaneStatblock = new JEditorPane();
			jEditorPaneStatblock.setContentType("text/html");
			jEditorPaneStatblock.setEditable(false);
			jEditorPaneStatblock.addHyperlinkListener(new HyperlinkListener() {

				@Override
				public void hyperlinkUpdate(HyperlinkEvent event) {
					jEditorPaneStatblockHyperlinkHyperlinkUpdate(event);
				}
			});
		}
		return jEditorPaneStatblock;
	}

	private JLabel getJLabelInitRoll() {
		if (jLabelInitRoll == null) {
			jLabelInitRoll = new JLabel();
			jLabelInitRoll.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelInitRoll.setText("Init Roll");
		}
		return jLabelInitRoll;
	}

	private JLabel getJLabelName() {
		if (jLabelName == null) {
			jLabelName = new JLabel();
			jLabelName.setText("Name");
		}
		return jLabelName;
	}

	private JList getJListEffects() {
		if (jListEffects == null) {
			jListEffects = new JList();
			DefaultListModel listModel = new DefaultListModel();
			jListEffects.setModel(listModel);
			jListEffects.setEnabled(false);
			jListEffects.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					jListEffectsMouseMouseClicked(event);
				}
			});
			jListEffects.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent event) {
					jListEffectsListSelectionValueChanged(event);
				}
			});
		}
		return jListEffects;
	}

	private JList getJListOffTurnPowers() {
		if (jListOffTurnPowers == null) {
			jListOffTurnPowers = new JList();
			DefaultListModel listModel = new DefaultListModel();
			jListOffTurnPowers.setModel(listModel);
			jListOffTurnPowers.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					jListOffTurnPowersListSelectionValueChanged(e);
				}
			});
		}
		return jListOffTurnPowers;
	}

	private JList getJListPowerList() {
		if (jListPowerList == null) {
			jListPowerList = new JList();
			DefaultListModel listModel = new DefaultListModel();
			jListPowerList.setModel(listModel);
			jListPowerList.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					jListPowerListMouseMouseClicked(event);
				}
			});
			jListPowerList.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent event) {
					jListPowerListListSelectionValueChanged(event);
				}
			});
		}
		return jListPowerList;
	}

	private JMenuItem getJMenuItemDelay() {
		if (jMenuItemDelay == null) {
			jMenuItemDelay = new JMenuItem();
			jMenuItemDelay.setText("Delay");
			jMenuItemDelay.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jMenuItemDelayActionActionPerformed(event);
				}
			});
		}
		return jMenuItemDelay;
	}

	private JMenuItem getJMenuItemLogOA() {
		if (jMenuItemLogOA == null) {
			jMenuItemLogOA = new JMenuItem();
			jMenuItemLogOA.setText("Log OA");
			jMenuItemLogOA.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jMenuItemLogOAActionActionPerformed(event);
				}
			});
		}
		return jMenuItemLogOA;
	}

	private JMenuItem getJMenuItemMarkUntilEOE() {
		if (jMenuItemMarkUntilEOE == null) {
			jMenuItemMarkUntilEOE = new JMenuItem();
			jMenuItemMarkUntilEOE.setText("Mark until EOE");
			jMenuItemMarkUntilEOE.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jMenuItemMarkUntilEOEActionActionPerformed(event);
				}
			});
		}
		return jMenuItemMarkUntilEOE;
	}

	private JMenuItem getJMenuItemMarkUntilEONT() {
		if (jMenuItemMarkUntilEONT == null) {
			jMenuItemMarkUntilEONT = new JMenuItem();
			jMenuItemMarkUntilEONT.setText("Mark until EONT");
			jMenuItemMarkUntilEONT.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jMenuItemMarkUntilEONTActionActionPerformed(event);
				}
			});
		}
		return jMenuItemMarkUntilEONT;
	}

	private JMenuItem getJMenuItemMoveToTop() {
		if (jMenuItemMoveToTop == null) {
			jMenuItemMoveToTop = new JMenuItem();
			jMenuItemMoveToTop.setText("Move to Top");
			jMenuItemMoveToTop.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jMenuItemMoveToStopActionActionPerformed(event);
				}
			});
		}
		return jMenuItemMoveToTop;
	}

	private JMenuItem getJMenuItemReady() {
		if (jMenuItemReady == null) {
			jMenuItemReady = new JMenuItem();
			jMenuItemReady.setText("Ready");
			jMenuItemReady.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jMenuItemReadyActionActionPerformed(event);
				}
			});
		}
		return jMenuItemReady;
	}

	private JMenuItem getJMenuItemToggleVisibility() {
		if (jMenuItemToggleVisibility == null) {
			jMenuItemToggleVisibility = new JMenuItem();
			jMenuItemToggleVisibility.setText("Toggle Visibility");
			jMenuItemToggleVisibility.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jMenuItemToggleVisibilityActionActionPerformed(event);
				}
			});
		}
		return jMenuItemToggleVisibility;
	}

	private JPanel getJPanelActionPoints() {
		if (jPanelActionPoints == null) {
			jPanelActionPoints = new JPanel();
			jPanelActionPoints.setBorder(BorderFactory.createTitledBorder(null, "Action Points", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
			jPanelActionPoints.setLayout(new GroupLayout());
			jPanelActionPoints.add(getJSpinnerActionPoints(),
					new Constraints(new Bilateral(12, 12, 27), new Leading(0, 41, 10, 10)));
			jPanelActionPoints.add(getJCheckBoxSpent(), new Constraints(new Bilateral(8, 12, 58), new Trailing(0, 34, 45)));
		}
		return jPanelActionPoints;
	}

	private JPanel getJPanelBottomCenter() {
		if (jPanelBottomCenter == null) {
			jPanelBottomCenter = new JPanel();
			jPanelBottomCenter.setBorder(BorderFactory.createTitledBorder(null, "Powers", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 10), new Color(51, 51, 51)));
			jPanelBottomCenter.setLayout(new BorderLayout());
			jPanelBottomCenter.add(getJScrollPanePowerList(), BorderLayout.CENTER);
		}
		return jPanelBottomCenter;
	}

	private JPanel getJPanelCrits() {
		if (jPanelCrits == null) {
			jPanelCrits = new JPanel();
			jPanelCrits.setBorder(BorderFactory.createTitledBorder(null, "Crits", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelCrits.setLayout(new GroupLayout());
			jPanelCrits.add(getJToggleButtonCritHit(), new Constraints(new Bilateral(0, 0, 49), new Leading(0, 12, 12)));
			jPanelCrits.add(getJToggleButtonCritMiss(), new Constraints(new Bilateral(0, 0, 61), new Leading(32, 12, 12)));
		}
		return jPanelCrits;
	}

	private JPanel getJPanelDamageHealing() {
		if (jPanelDamageHealing == null) {
			jPanelDamageHealing = new JPanel();
			jPanelDamageHealing.setLayout(new GroupLayout());
			jPanelDamageHealing.add(getJPanelHealth(), new Constraints(new Leading(0, 211, 12, 12), new Leading(0, 112, 12, 12)));
			jPanelDamageHealing.add(getJPanelSurges(), new Constraints(new Leading(210, 73, 10, 10), new Leading(0, 112, 12, 12)));
		}
		return jPanelDamageHealing;
	}

	private JPanel getJPanelEffectButtons() {
		if (jPanelEffectButtons == null) {
			jPanelEffectButtons = new JPanel();
			jPanelEffectButtons.add(getJButtonAdd());
			jPanelEffectButtons.add(getJButtonChange());
			jPanelEffectButtons.add(getJButtonRemove());
		}
		return jPanelEffectButtons;
	}

	private JPanel getJPanelEffects() {
		if (jPanelEffects == null) {
			jPanelEffects = new JPanel();
			jPanelEffects.setBorder(BorderFactory.createTitledBorder(null, "Effects", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 10), new Color(51, 51, 51)));
			jPanelEffects.setLayout(new BorderLayout());
			jPanelEffects.add(getJScrollPaneEffects(), BorderLayout.CENTER);
			jPanelEffects.add(getJPanelEffectButtons(), BorderLayout.SOUTH);
		}
		return jPanelEffects;
	}

	private JPanel getJPanelHealth() {
		if (jPanelHealth == null) {
			jPanelHealth = new JPanel();
			jPanelHealth.setBorder(BorderFactory.createTitledBorder(null, "Health", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 10), new Color(51, 51, 51)));
			jPanelHealth.setLayout(new GroupLayout());
			jPanelHealth.add(getJSpinnerDamageHealAmount(), new Constraints(new Leading(0, 62, 12, 12), new Leading(-5, 10, 10)));
			jPanelHealth.add(getJButtonFive(), new Constraints(new Leading(0, 26, 12, 12), new Leading(20, 22, 12, 12)));
			jPanelHealth.add(getJButtonPlusFive(), new Constraints(new Leading(36, 26, 12, 12), new Leading(20, 22, 12, 12)));
			jPanelHealth.add(getJButtonSurge(), new Constraints(new Leading(0, 62, 12, 12), new Leading(43, 22, 12, 12)));
			jPanelHealth.add(getJButtonHalve(), new Constraints(new Leading(0, 62, 12, 12), new Leading(66, 22, 12, 12)));
			jPanelHealth.add(getJButtonDamage(), new Constraints(new Leading(66, 57, 12, 12), new Leading(-5, 47, 12, 12)));
			jPanelHealth.add(getJButtonHeal(), new Constraints(new Leading(66, 57, 12, 12), new Leading(43, 45, 12, 12)));
			jPanelHealth.add(getJButtonAddTemp(), new Constraints(new Leading(127, 74, 12, 12), new Leading(-5, 24, 12, 12)));
			jPanelHealth.add(getJButtonMax(), new Constraints(new Leading(127, 74, 12, 12), new Leading(20, 22, 12, 12)));
			jPanelHealth.add(getJButtonFailDeath(), new Constraints(new Leading(127, 74, 12, 12), new Leading(43, 22, 12, 12)));
			jPanelHealth.add(getJButtonUndoDeath(), new Constraints(new Leading(127, 74, 12, 12), new Leading(66, 22, 12, 12)));
		}
		return jPanelHealth;
	}

	private JPanel getJPanelInitiative() {
		if (jPanelInitiative == null) {
			jPanelInitiative = new JPanel();
			jPanelInitiative.setLayout(new GroupLayout());
			jPanelInitiative.add(getJLabelInitRoll(), new Constraints(new Leading(227, 50, 12, 12), new Leading(12, 12, 12)));
			jPanelInitiative.add(getJSpinnerInitRoll(), new Constraints(new Leading(230, 47, 12, 12), new Leading(32, 41, 12, 12)));
			jPanelInitiative
					.add(getJButtonRemoveFighter(), new Constraints(new Leading(3, 72, 12, 12), new Leading(5, 38, 12, 12)));
			jPanelInitiative.add(getJButtonRollInitiative(), new Constraints(new Leading(76, 72, 12, 12),
					new Leading(5, 38, 12, 12)));
			jPanelInitiative.add(getJButtonMoveToTop(), new Constraints(new Leading(150, 72, 12, 12), new Leading(5, 38, 12, 12)));
			jPanelInitiative.add(getJButtonReserve(), new Constraints(new Leading(3, 72, 12, 12), new Leading(44, 38, 12, 12)));
			jPanelInitiative.add(getJButtonDelay(), new Constraints(new Leading(76, 72, 12, 12), new Leading(44, 38, 12, 12)));
			jPanelInitiative.add(getJButtonReady(), new Constraints(new Leading(150, 72, 12, 12), new Leading(44, 38, 12, 12)));
			jPanelInitiative.add(getJLabelPreviousRest(), new Constraints(new Leading(3, 274, 12, 12), new Leading(88, 12, 12)));
		}
		return jPanelInitiative;
	}

	private JPanel getJPanelMisc() {
		if (jPanelMisc == null) {
			jPanelMisc = new JPanel();
			jPanelMisc.setBorder(BorderFactory.createTitledBorder(null, "Misc", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelMisc.setLayout(new GroupLayout());
			jPanelMisc.add(getJToggleButtonMiscVictory(), new Constraints(new Bilateral(0, 0, 61), new Leading(0, 12, 12)));
			jPanelMisc.add(getJToggleButtonMiscDaily(), new Constraints(new Bilateral(0, 0, 61), new Leading(32, 12, 12)));
		}
		return jPanelMisc;
	}

	private JPanel getJPanelMusic() {
		if (jPanelMusic == null) {
			jPanelMusic = new JPanel();
			jPanelMusic.setBorder(BorderFactory.createTitledBorder(null, "Music Player", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelMusic.setLayout(new GroupLayout());
			jPanelMusic.add(getJToggleButtonPlay(), new Constraints(new Leading(0, 165, 165), new Trailing(29, 84, 12, 12)));
			jPanelMusic.add(getJPanelCrits(), new Constraints(new Bilateral(64, 88, 71), new Trailing(29, 84, 12, 12)));
			jPanelMusic.add(getJPanelMisc(), new Constraints(new Trailing(0, 10, 141), new Trailing(29, 84, 12, 12)));
			jPanelMusic.add(getJScrollPaneNowPlaying(), new Constraints(new Bilateral(0, 0, 22), new Bilateral(0, 119, 22)));
			jPanelMusic.add(getJComboBoxPlaylists(), new Constraints(new Bilateral(0, 0, 60), new Trailing(0, 170, 165)));
		}
		return jPanelMusic;
	}

	private JPanel getJPanelPoints() {
		if (jPanelPoints == null) {
			jPanelPoints = new JPanel();
			jPanelPoints.setLayout(new GroupLayout());
			jPanelPoints.add(getJPanelActionPoints(), new Constraints(new Leading(12, 130, 10, 10), new Bilateral(0, 12, 0)));
			jPanelPoints.add(getJPanelPowerPoints(), new Constraints(new Bilateral(160, 12, 0), new Bilateral(-1, 12, 0)));
		}
		return jPanelPoints;
	}

	private JPanel getJPanelPowerPoints() {
		if (jPanelPowerPoints == null) {
			jPanelPowerPoints = new JPanel();
			jPanelPowerPoints.setBorder(BorderFactory.createTitledBorder(null, "Power Points", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
			jPanelPowerPoints.setLayout(new GroupLayout());
			jPanelPowerPoints.add(getJSpinnerPowerPoints(), new Constraints(new Bilateral(12, 12, 27), new Leading(0, 41, 10, 10)));
			jPanelPowerPoints.add(getJButtonPowerPointsMinusSix(), new Constraints(new Trailing(12, 53, 53), new Leading(47, 12,
					12)));
			jPanelPowerPoints.add(getJButtonPowerPointsMinusFour(), new Constraints(new Bilateral(42, 42, 17), new Leading(47, 12,
					12)));
			jPanelPowerPoints.add(getJButtonPowerPointsMinusTwo(),
					new Constraints(new Leading(12, 65, 65), new Leading(47, 12, 12)));
		}
		return jPanelPowerPoints;
	}

	private JPanel getJPanelSurges() {
		if (jPanelSurges == null) {
			jPanelSurges = new JPanel();
			jPanelSurges.setBorder(BorderFactory.createTitledBorder(null, "Surges", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 10), new Color(51, 51, 51)));
			jPanelSurges.setLayout(new GroupLayout());
			jPanelSurges.add(getJTextFieldSurges(), new Constraints(new Leading(0, 63, 12, 12), new Leading(-5, 24, 12, 12)));
			jPanelSurges.add(getJButtonPlusOne(), new Constraints(new Leading(0, 26, 12, 12), new Leading(20, 22, 12, 12)));
			jPanelSurges.add(getJButtonMinusOne(), new Constraints(new Leading(37, 26, 12, 12), new Leading(20, 22, 12, 12)));
			jPanelSurges.add(getJButtonRegainAll(), new Constraints(new Leading(0, 63, 12, 12), new Leading(43, 45, 12, 12)));
		}
		return jPanelSurges;
	}

	private JPanel getJPanelTopCenter() {
		if (jPanelTopCenter == null) {
			jPanelTopCenter = new JPanel();
			jPanelTopCenter.setLayout(new GroupLayout());
			jPanelTopCenter.add(getJLabelName(), new Constraints(new Leading(4, 10, 10), new Leading(6, 12, 12)));
			jPanelTopCenter.add(getJTabbedPaneControls(),
					new Constraints(new Leading(4, 288, 12, 12), new Leading(26, 139, 12, 12)));
			jPanelTopCenter.add(getJButtonNextTurn(), new Constraints(new Leading(4, 211, 12, 12), new Leading(171, 12, 12)));
			jPanelTopCenter.add(getJTextFieldNumber(), new Constraints(new Leading(254, 38, 12, 12), new Leading(3, 12, 12)));
			jPanelTopCenter.add(getJTextFieldName(), new Constraints(new Leading(43, 205, 10, 10), new Leading(3, 12, 12)));
			jPanelTopCenter.add(getJButtonBackUp(), new Constraints(new Leading(221, 71, 12, 12), new Leading(171, 12, 12)));
			jPanelTopCenter.add(getJPanelEffects(), new Constraints(new Leading(4, 288, 12, 12), new Bilateral(193, 12, 78)));
		}
		return jPanelTopCenter;
	}

	private JPopupMenu getJPopupMenuRoster() {
		if (jPopupMenuRoster == null) {
			jPopupMenuRoster = new JPopupMenu();
			jPopupMenuRoster.add(getJMenuItemMoveToTop());
			jPopupMenuRoster.add(getJMenuItemDelay());
			jPopupMenuRoster.add(getJMenuItemReady());
			jPopupMenuRoster.addSeparator();
			jPopupMenuRoster.add(getJMenuItemLogOA());
			jPopupMenuRoster.addSeparator();
			jPopupMenuRoster.add(getJMenuItemMarkUntilEONT());
			jPopupMenuRoster.add(getJMenuItemMarkUntilEOE());
			jPopupMenuRoster.addSeparator();
			jPopupMenuRoster.add(getJMenuItemToggleVisibility());
			jPopupMenuRoster.addPopupMenuListener(new PopupMenuListener() {

				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
					// do nothing
				}

				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					// do nothing
				}

				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					jPopupMenuRosterWillBecomeVisible(e);
				}
			});
		}
		return jPopupMenuRoster;
	}

	private JScrollPane getJScrollPaneCompendium() {
		if (jScrollPaneCompendium == null) {
			jScrollPaneCompendium = new JScrollPane();
			jScrollPaneCompendium.setViewportView(getJEditorPaneCompendium());
		}
		return jScrollPaneCompendium;
	}

	private JScrollPane getJScrollPaneEffects() {
		if (jScrollPaneEffects == null) {
			jScrollPaneEffects = new JScrollPane();
			jScrollPaneEffects.setViewportView(getJListEffects());
		}
		return jScrollPaneEffects;
	}

	private JScrollPane getJScrollPaneNotes() {
		if (jScrollPaneNotes == null) {
			jScrollPaneNotes = new JScrollPane();
			jScrollPaneNotes.setViewportView(getJTextAreaNotes());
		}
		return jScrollPaneNotes;
	}

	private JScrollPane getJScrollPaneNowPlaying() {
		if (jScrollPaneNowPlaying == null) {
			jScrollPaneNowPlaying = new JScrollPane();
			jScrollPaneNowPlaying.setBorder(BorderFactory.createTitledBorder(null, "Now Playing", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 10), new Color(51, 51, 51)));
			jScrollPaneNowPlaying.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPaneNowPlaying.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			jScrollPaneNowPlaying.setViewportView(getJTextAreaNowPlaying());
		}
		return jScrollPaneNowPlaying;
	}

	private JScrollPane getJScrollPaneOffTurnPowers() {
		if (jScrollPaneOffTurnPowers == null) {
			jScrollPaneOffTurnPowers = new JScrollPane();
			jScrollPaneOffTurnPowers.setVisible(false);
			jScrollPaneOffTurnPowers.setViewportView(getJListOffTurnPowers());
		}
		return jScrollPaneOffTurnPowers;
	}

	private JScrollPane getJScrollPanePowerList() {
		if (jScrollPanePowerList == null) {
			jScrollPanePowerList = new JScrollPane();
			jScrollPanePowerList.setViewportView(getJListPowerList());
		}
		return jScrollPanePowerList;
	}

	private JScrollPane getJScrollPaneRoster() {
		if (jScrollPaneRoster == null) {
			jScrollPaneRoster = new JScrollPane();
			jScrollPaneRoster.setViewportView(getJTableRoster());
		}
		return jScrollPaneRoster;
	}

	private JScrollPane getJScrollPaneStatblock() {
		if (jScrollPaneStatblock == null) {
			jScrollPaneStatblock = new JScrollPane();
			jScrollPaneStatblock.setViewportView(getJEditorPaneStatblock());
		}
		return jScrollPaneStatblock;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	private JSeparator getJSeparator2() {
		if (jSeparator2 == null) {
			jSeparator2 = new JSeparator();
		}
		return jSeparator2;
	}

	private JSeparator getJSeparator3() {
		if (jSeparator3 == null) {
			jSeparator3 = new JSeparator();
		}
		return jSeparator3;
	}

	private JSeparator getJSeparator4() {
		if (jSeparator4 == null) {
			jSeparator4 = new JSeparator();
		}
		return jSeparator4;
	}

	private JSpinner getJSpinnerActionPoints() {
		if (jSpinnerActionPoints == null) {
			jSpinnerActionPoints = new JSpinner();
			jSpinnerActionPoints.setFont(new Font("Dialog", Font.BOLD, 20));
			jSpinnerActionPoints.setModel(new SpinnerNumberModel(0, 0, null, 1));
			jSpinnerActionPoints.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent event) {
					jSpinnerActionPointsKeyKeyPressed(event);
				}
			});
			jSpinnerActionPoints.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent event) {
					jSpinnerActionPointsChangeStateChanged(event);
				}
			});
		}
		return jSpinnerActionPoints;
	}

	private JSpinner getJSpinnerDamageHealAmount() {
		if (jSpinnerDamageHealAmount == null) {
			jSpinnerDamageHealAmount = new JSpinner();
			jSpinnerDamageHealAmount.setFont(new Font("Dialog", Font.BOLD, 14));
			jSpinnerDamageHealAmount.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
			jSpinnerDamageHealAmount.setEditor(new JSpinner.NumberEditor(jSpinnerDamageHealAmount));
			jSpinnerDamageHealAmount.setEnabled(false);
			jSpinnerDamageHealAmount.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent event) {
					jSpinnerDamageHealAmountChangeStateChanged(event);
				}
			});
		}
		return jSpinnerDamageHealAmount;
	}

	private JSpinner getJSpinnerInitRoll() {
		if (jSpinnerInitRoll == null) {
			jSpinnerInitRoll = new JSpinner();
			jSpinnerInitRoll.setFont(new Font("Dialog", Font.PLAIN, 14));
			jSpinnerInitRoll.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
			jSpinnerInitRoll.setEditor(new JSpinner.NumberEditor(jSpinnerInitRoll));
			jSpinnerInitRoll.setEnabled(false);
			jSpinnerInitRoll.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					jSpinnerInitRollChangeStateChanged(e);
				}
			});
			jSpinnerInitRoll.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent event) {
					jSpinnerInitRollKeyKeyPressed(event);
				}
			});
		}
		return jSpinnerInitRoll;
	}

	private JSpinner getJSpinnerPowerPoints() {
		if (jSpinnerPowerPoints == null) {
			jSpinnerPowerPoints = new JSpinner();
			jSpinnerPowerPoints.setFont(new Font("Dialog", Font.BOLD, 20));
			jSpinnerPowerPoints.setModel(new SpinnerNumberModel(0, 0, null, 1));
			jSpinnerPowerPoints.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent event) {
					jSpinnerPowerPointsKeyKeyPressed(event);
				}
			});
			jSpinnerPowerPoints.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent event) {
					jSpinnerPowerPointsChangeStateChanged(event);
				}
			});
		}
		return jSpinnerPowerPoints;
	}

	private JSplitPane getJSplitPaneCenter() {
		if (jSplitPaneCenter == null) {
			jSplitPaneCenter = new JSplitPane();
			jSplitPaneCenter.setDividerLocation(325);
			jSplitPaneCenter.setResizeWeight(0.25);
			jSplitPaneCenter.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPaneCenter.setTopComponent(getJPanelTopCenter());
			jSplitPaneCenter.setBottomComponent(getJPanelBottomCenter());
		}
		return jSplitPaneCenter;
	}

	private JSplitPane getJSplitPaneLeft() {
		if (jSplitPaneLeft == null) {
			jSplitPaneLeft = new JSplitPane();
			jSplitPaneLeft.setDividerLocation(400);
			jSplitPaneLeft.setDividerSize(0);
			jSplitPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPaneLeft.setResizeWeight(1.0);
			jSplitPaneLeft.setTopComponent(getJScrollPaneRoster());
			jSplitPaneLeft.setBottomComponent(getJTabbedPaneUtils());
		}
		return jSplitPaneLeft;
	}

	private JSplitPane getJSplitPaneMain() {
		if (jSplitPaneMain == null) {
			jSplitPaneMain = new JSplitPane();
			jSplitPaneMain.setDividerLocation(250);
			jSplitPaneMain.setResizeWeight(0.25);
			jSplitPaneMain.setLeftComponent(getJSplitPaneLeft());
			jSplitPaneMain.setRightComponent(getJSplitPaneSub());
		}
		return jSplitPaneMain;
	}

	private JSplitPane getJSplitPaneRight() {
		if (jSplitPaneRight == null) {
			jSplitPaneRight = new JSplitPane();
			jSplitPaneRight.setDividerLocation(350);
			jSplitPaneRight.setResizeWeight(1.0);
			jSplitPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPaneRight.setTopComponent(getJScrollPaneStatblock());
			jSplitPaneRight.setBottomComponent(getJScrollPaneCompendium());
		}
		return jSplitPaneRight;
	}

	private JSplitPane getJSplitPaneSub() {
		if (jSplitPaneSub == null) {
			jSplitPaneSub = new JSplitPane();
			jSplitPaneSub.setDividerLocation(300);
			jSplitPaneSub.setDividerSize(0);
			jSplitPaneSub.setLeftComponent(getJSplitPaneCenter());
			jSplitPaneSub.setRightComponent(getJSplitPaneRight());
		}
		return jSplitPaneSub;
	}

	private JTabbedPane getJTabbedPaneControls() {
		if (jTabbedPaneControls == null) {
			jTabbedPaneControls = new JTabbedPane();
			jTabbedPaneControls.addTab("Initiative", getJPanelInitiative());
			jTabbedPaneControls.addTab("Damage/Healing", getJPanelDamageHealing());
			jTabbedPaneControls.addTab("Points", getJPanelPoints());
		}
		return jTabbedPaneControls;
	}

	private JTabbedPane getJTabbedPaneUtils() {
		if (jTabbedPaneUtils == null) {
			jTabbedPaneUtils = new JTabbedPane();
			jTabbedPaneUtils.addTab("Notes", getJScrollPaneNotes());
			jTabbedPaneUtils.addTab("Off-Turn Powers", getJScrollPaneOffTurnPowers());
			jTabbedPaneUtils.addTab("Music", getJPanelMusic());
		}
		return jTabbedPaneUtils;
	}

	private JTable getJTableRoster() {
		if (jTableRoster == null) {
			jTableRoster = new JTable();
			jTableRoster.setComponentPopupMenu(getJPopupMenuRoster());
			jTableRoster.setModel(new ReadOnlyTableModel(null, new String[] { "V", "R", "Name", "Status", "AC", "F", "R", "W" }));
			jTableRoster.setDefaultRenderer(Object.class, getRosterRenderer());
			jTableRoster.getTableHeader().getColumnModel().getColumn(0).setMinWidth(15);
			jTableRoster.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(15);
			jTableRoster.getTableHeader().getColumnModel().getColumn(0).setResizable(false);
			jTableRoster.getTableHeader().getColumnModel().getColumn(1).setMinWidth(15);
			jTableRoster.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(15);
			jTableRoster.getTableHeader().getColumnModel().getColumn(1).setResizable(false);
			jTableRoster.getTableHeader().getColumnModel().getColumn(4).setMinWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(4).setResizable(false);
			jTableRoster.getTableHeader().getColumnModel().getColumn(5).setMinWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(5).setResizable(false);
			jTableRoster.getTableHeader().getColumnModel().getColumn(6).setMinWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(6).setResizable(false);
			jTableRoster.getTableHeader().getColumnModel().getColumn(7).setMinWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(30);
			jTableRoster.getTableHeader().getColumnModel().getColumn(7).setResizable(false);
			jTableRoster.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableRoster.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent event) {
					jTableRosterSelectionValueChanged(event);
				}
			});
		}
		return jTableRoster;
	}

	private JTextArea getJTextAreaNotes() {
		if (jTextAreaNotes == null) {
			jTextAreaNotes = new JTextArea();
			jTextAreaNotes.setLineWrap(true);
			jTextAreaNotes.setWrapStyleWord(true);
			jTextAreaNotes.getDocument().addDocumentListener(new DocumentListener() {

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
		}
		return jTextAreaNotes;
	}

	private JTextArea getJTextAreaNowPlaying() {
		if (jTextAreaNowPlaying == null) {
			jTextAreaNowPlaying = new JTextArea();
			jTextAreaNowPlaying.setFont(new Font("Dialog", Font.BOLD, 12));
			jTextAreaNowPlaying.setLineWrap(true);
			jTextAreaNowPlaying.setText("(nothing)");
			jTextAreaNowPlaying.setWrapStyleWord(true);
		}
		return jTextAreaNowPlaying;
	}

	private JTextField getJTextFieldName() {
		if (jTextFieldName == null) {
			jTextFieldName = new JTextField();
			jTextFieldName.setEnabled(false);
			jTextFieldName.addVetoableChangeListener(new VetoableChangeListener() {

				@Override
				public void vetoableChange(PropertyChangeEvent event) {
					jTextFieldNameVetoableChangeVetoableChange(event);
				}
			});
		}
		return jTextFieldName;
	}

	private JTextField getJTextFieldNumber() {
		if (jTextFieldNumber == null) {
			jTextFieldNumber = new JTextField();
			jTextFieldNumber.setEnabled(false);
		}
		return jTextFieldNumber;
	}

	private JTextField getJTextFieldSurges() {
		if (jTextFieldSurges == null) {
			jTextFieldSurges = new JTextField();
			jTextFieldSurges.setFont(new Font("Dialog", Font.BOLD, 14));
			jTextFieldSurges.setHorizontalAlignment(SwingConstants.CENTER);
			jTextFieldSurges.setText("0/0");
			jTextFieldSurges.setEditable(false);
			jTextFieldSurges.setEnabled(false);
		}
		return jTextFieldSurges;
	}

	private JToggleButton getJToggleButtonCritHit() {
		if (jToggleButtonCritHit == null) {
			jToggleButtonCritHit = new JToggleButton();
			jToggleButtonCritHit.setText("Hit");
			jToggleButtonCritHit.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent event) {
					jToggleButtonCritHitItemItemStateChanged(event);
				}
			});
		}
		return jToggleButtonCritHit;
	}

	private JToggleButton getJToggleButtonCritMiss() {
		if (jToggleButtonCritMiss == null) {
			jToggleButtonCritMiss = new JToggleButton();
			jToggleButtonCritMiss.setText("Miss");
			jToggleButtonCritMiss.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent event) {
					jToggleButtonCritMissItemItemStateChanged(event);
				}
			});
		}
		return jToggleButtonCritMiss;
	}

	private JToggleButton getJToggleButtonMiscDaily() {
		if (jToggleButtonMiscDaily == null) {
			jToggleButtonMiscDaily = new JToggleButton();
			jToggleButtonMiscDaily.setText("Daily");
			jToggleButtonMiscDaily.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent event) {
					jToggleButtonMiscDailyItemItemStateChanged(event);

				}
			});
		}
		return jToggleButtonMiscDaily;
	}

	private JToggleButton getJToggleButtonMiscVictory() {
		if (jToggleButtonMiscVictory == null) {
			jToggleButtonMiscVictory = new JToggleButton();
			jToggleButtonMiscVictory.setText("Victory");
			jToggleButtonMiscVictory.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent event) {
					jToggleButtonMiscVictoryItemItemStateChanged(event);
				}
			});
		}
		return jToggleButtonMiscVictory;
	}

	private JToggleButton getJToggleButtonPlay() {
		if (jToggleButtonPlay == null) {
			jToggleButtonPlay = new JToggleButton();
			jToggleButtonPlay.setText("Play");
			jToggleButtonPlay.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent event) {
					jToggleButtonPlayItemItemStateChanged(event);
				}
			});
		}
		return jToggleButtonPlay;
	}

	/**
	 * Returns the selected {@link Combatant}.
	 * 
	 * @return the Combatant
	 */
	private Combatant getListSelectedFighter() {
		return getFight().getSelectedFighter();
	}

	private JMenuBar getMenuBarMain() {
		if (menuBarMain == null) {
			menuBarMain = new JMenuBar();
			menuBarMain.add(getMenuFile());
			menuBarMain.add(getMenuEncounter());
			menuBarMain.add(getMenuParty());
			menuBarMain.add(getMenuWindows());
			menuBarMain.add(getMenuLibrary());
			menuBarMain.add(getMenuHelp());
		}
		return menuBarMain;
	}

	private JMenu getMenuEncounter() {
		if (menuEncounter == null) {
			menuEncounter = new JMenu();
			menuEncounter.setText("Encounter");
			menuEncounter.setMnemonic(KeyEvent.VK_E);
			menuEncounter.setOpaque(false);
			menuEncounter.add(getMenuEncounterInitiative());
			menuEncounter.add(getMenuEncounterEnd());
			menuEncounter.add(getMenuEncounterRemoveMonsters());
		}
		return menuEncounter;
	}

	private JMenuItem getMenuEncounterEnd() {
		if (menuEncounterEnd == null) {
			menuEncounterEnd = new JMenuItem();
			menuEncounterEnd.setText("End Encounter");
			menuEncounterEnd.setAccelerator(KeyStroke.getKeyStroke("control shift pressed I"));
			menuEncounterEnd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuEncounterEndActionActionPerformed(event);
				}
			});
		}
		return menuEncounterEnd;
	}

	private JMenuItem getMenuEncounterInitiative() {
		if (menuEncounterInitiative == null) {
			menuEncounterInitiative = new JMenuItem();
			menuEncounterInitiative.setText("Roll Initiative");
			menuEncounterInitiative.setAccelerator(KeyStroke.getKeyStroke("control pressed I"));
			menuEncounterInitiative.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuEncounterInitiativeActionActionPerformed(event);
				}
			});
		}
		return menuEncounterInitiative;
	}

	private JMenuItem getMenuEncounterRemoveMonsters() {
		if (menuEncounterRemoveMonsters == null) {
			menuEncounterRemoveMonsters = new JMenuItem();
			menuEncounterRemoveMonsters.setText("Remove All Monsters");
			menuEncounterRemoveMonsters.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuEncounterRemoveMonstersActionActionPerformed(event);
				}
			});
		}
		return menuEncounterRemoveMonsters;
	}

	private JMenu getMenuFile() {
		if (menuFile == null) {
			menuFile = new JMenu();
			menuFile.setText("File");
			menuFile.setMnemonic(KeyEvent.VK_F);
			menuFile.setOpaque(false);
			menuFile.add(getMenuFileNew());
			menuFile.add(getJSeparator2());
			menuFile.add(getMenuFileImport());
			menuFile.add(getJSeparator1());
			menuFile.add(getMenuFileOpen());
			menuFile.add(getMenuFileSave());
			menuFile.add(getJSeparator0());
			menuFile.add(getMenuFileExit());
		}
		return menuFile;
	}

	private JMenuItem getMenuFileExit() {
		if (menuFileExit == null) {
			menuFileExit = new JMenuItem();
			menuFileExit.setText("Exit");
			menuFileExit.setAccelerator(KeyStroke.getKeyStroke("alt pressed F4"));
			menuFileExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuFileExitActionActionPerformed(event);
				}
			});
		}
		return menuFileExit;
	}

	private JMenuItem getMenuFileImport() {
		if (menuFileImport == null) {
			menuFileImport = new JMenuItem();
			menuFileImport.setText("Import");
			menuFileImport.setAccelerator(KeyStroke.getKeyStroke("control shift pressed O"));
			menuFileImport.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuFileImportActionActionPerformed(event);
				}
			});
		}
		return menuFileImport;
	}

	private JMenuItem getMenuFileNew() {
		if (menuFileNew == null) {
			menuFileNew = new JMenuItem();
			menuFileNew.setText("New Encounter");
			menuFileNew.setAccelerator(KeyStroke.getKeyStroke("control pressed N"));
			menuFileNew.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuFileNewActionActionPerformed(event);
				}
			});
		}
		return menuFileNew;
	}

	private JMenuItem getMenuFileOpen() {
		if (menuFileOpen == null) {
			menuFileOpen = new JMenuItem();
			menuFileOpen.setText("Open Encounter");
			menuFileOpen.setAccelerator(KeyStroke.getKeyStroke("control pressed O"));
			menuFileOpen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuFileOpenActionActionPerformed(event);
				}
			});
		}
		return menuFileOpen;
	}

	private JMenuItem getMenuFileSave() {
		if (menuFileSave == null) {
			menuFileSave = new JMenuItem();
			menuFileSave.setText("Save Encounter");
			menuFileSave.setAccelerator(KeyStroke.getKeyStroke("control pressed S"));
			menuFileSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuFileSaveActionActionPerformed(event);
				}
			});
		}
		return menuFileSave;
	}

	private JMenu getMenuHelp() {
		if (menuHelp == null) {
			menuHelp = new JMenu();
			menuHelp.setText("Help");
			menuHelp.setMnemonic(KeyEvent.VK_H);
			menuHelp.setBorderPainted(true);
			menuHelp.setOpaque(false);
			menuHelp.setRolloverEnabled(false);
			menuHelp.add(getMenuHelpAbout());
		}
		return menuHelp;
	}

	private JMenuItem getMenuHelpAbout() {
		if (menuHelpAbout == null) {
			menuHelpAbout = new JMenuItem();
			menuHelpAbout.setText("About");
			menuHelpAbout.setAccelerator(KeyStroke.getKeyStroke("pressed F1"));
			menuHelpAbout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					menuHelpAboutActionActionPerformed(e);
				}
			});
		}
		return menuHelpAbout;
	}

	private JMenu getMenuLibrary() {
		if (menuLibrary == null) {
			menuLibrary = new JMenu();
			menuLibrary.setText("Library");
			menuLibrary.setMnemonic(KeyEvent.VK_L);
			menuLibrary.setOpaque(false);
			menuLibrary.add(getMenuLibraryOpen());
		}
		return menuLibrary;
	}

	private JMenuItem getMenuLibraryOpen() {
		if (menuLibraryOpen == null) {
			menuLibraryOpen = new JMenuItem();
			menuLibraryOpen.setText("Open");
			menuLibraryOpen.setAccelerator(KeyStroke.getKeyStroke("control pressed L"));
			menuLibraryOpen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuLibraryOpenActionActionPerformed(event);
				}
			});
		}
		return menuLibraryOpen;
	}

	private JMenu getMenuParty() {
		if (menuParty == null) {
			menuParty = new JMenu();
			menuParty.setText("Party");
			menuParty.setMnemonic(KeyEvent.VK_P);
			menuParty.setOpaque(false);
			menuParty.add(getMenuPartyShortRest());
			menuParty.add(getMenuPartyShortRestMilestone());
			menuParty.add(getMenuPartyExtendedRest());
			menuParty.add(getJSeparator3());
			menuParty.add(getMenuPartyRemove());
		}
		return menuParty;
	}

	private JMenuItem getMenuPartyExtendedRest() {
		if (menuPartyExtendedRest == null) {
			menuPartyExtendedRest = new JMenuItem();
			menuPartyExtendedRest.setText("Take Extended Rest");
			menuPartyExtendedRest.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuPartyExtendedRestActionActionPerformed(event);
				}
			});
		}
		return menuPartyExtendedRest;
	}

	private JMenuItem getMenuPartyRemove() {
		if (menuPartyRemove == null) {
			menuPartyRemove = new JMenuItem();
			menuPartyRemove.setText("Remove Party from Roster");
			menuPartyRemove.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuPartyRemoveActionActionPerformed(event);
				}
			});
		}
		return menuPartyRemove;
	}

	private JMenuItem getMenuPartyShortRest() {
		if (menuPartyShortRest == null) {
			menuPartyShortRest = new JMenuItem();
			menuPartyShortRest.setText("Take Short Rest");
			menuPartyShortRest.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuPartyShortRestActionActionPerformed(event);
				}
			});
		}
		return menuPartyShortRest;
	}

	private JMenuItem getMenuPartyShortRestMilestone() {
		if (menuPartyShortRestMilestone == null) {
			menuPartyShortRestMilestone = new JMenuItem();
			menuPartyShortRestMilestone.setText("Take Short Rest with Milestone");
			menuPartyShortRestMilestone.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					menuPartyShortRestMilestoneActionActionPerformed(event);
				}
			});
		}
		return menuPartyShortRestMilestone;
	}

	private JMenu getMenuWindows() {
		if (menuWindows == null) {
			menuWindows = new JMenu();
			menuWindows.setMnemonic('W');
			menuWindows.setText("Windows");
			menuWindows.setOpaque(false);
			menuWindows.add(getMenuWindowsMinimalInitDisplay());
			menuWindows.add(getMenuWindowsFullInitDisplay());
			menuWindows.add(getJSeparator4());
			menuWindows.add(getMenuWindowsOptions());
			menuWindows.add(getMenuWindowsFontSize());
		}
		return menuWindows;
	}

	private JMenu getMenuWindowsFontSize() {
		if (menuWindowsFontSize == null) {
			menuWindowsFontSize = new JMenu();
			menuWindowsFontSize.setText("Init Display Font Size");
			menuWindowsFontSize.setOpaque(false);
			menuWindowsFontSize.add(getJRadioButtonMenuItemSmall());
			menuWindowsFontSize.add(getJRadioButtonMenuItemMedium());
			menuWindowsFontSize.add(getJRadioButtonMenuItemLarge());
			menuWindowsFontSize.add(getJRadioButtonMenuItemExtraLarge());
			menuWindowsFontSize.add(getJRadioButtonMenuItemExtraExtraLarge());
		}
		return menuWindowsFontSize;
	}

	private JCheckBoxMenuItem getMenuWindowsFullInitDisplay() {
		if (menuWindowsFullInitDisplay == null) {
			menuWindowsFullInitDisplay = new JCheckBoxMenuItem();
			menuWindowsFullInitDisplay.setText("Show Full Init Display");
			menuWindowsFullInitDisplay.setAccelerator(KeyStroke.getKeyStroke("control pressed F"));
			menuWindowsFullInitDisplay.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					menuWindowsFullInitDisplayStateChanged(e);
				}
			});
		}
		return menuWindowsFullInitDisplay;
	}

	private JCheckBoxMenuItem getMenuWindowsMinimalInitDisplay() {
		if (menuWindowsMinimalInitDisplay == null) {
			menuWindowsMinimalInitDisplay = new JCheckBoxMenuItem();
			menuWindowsMinimalInitDisplay.setText("Minimal Init Display");
			menuWindowsMinimalInitDisplay.setAccelerator(KeyStroke.getKeyStroke("control pressed M"));
			menuWindowsMinimalInitDisplay.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent event) {
					menuWindowsMinimalInitDisplayStateChanged(event);
				}
			});
		}
		return menuWindowsMinimalInitDisplay;
	}

	private JMenuItem getMenuWindowsOptions() {
		if (menuWindowsOptions == null) {
			menuWindowsOptions = new JMenuItem("Options");
			menuWindowsOptions.setAccelerator(KeyStroke.getKeyStroke("alt pressed O"));
			menuWindowsOptions.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					menuWindowsOptionsActionActionPerformed(e);
				}
			});
		}
		return menuWindowsOptions;
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

	private void initComponents() {
		setTitle("DnD 4e Combat Manager");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		add(getJSplitPaneMain(), BorderLayout.CENTER);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent event) {
				componentComponentResized(event);
			}
		});
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent event) {
				windowWindowClosing(event);
			}

			@Override
			public void windowOpened(WindowEvent event) {
				windowWindowOpened(event);
			}
		});
		setJMenuBar(getMenuBarMain());
		initFontSizeButtonGroup();
		pack();
	}

	private JLabel getJLabelPreviousRest() {
		if (jLabelPreviousRest == null) {
			jLabelPreviousRest = new JLabel();
			jLabelPreviousRest.setText("Previous Rest: ");
		}
		return jLabelPreviousRest;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItemExtraExtraLarge() {
		if (jRadioButtonMenuItemExtraExtraLarge == null) {
			jRadioButtonMenuItemExtraExtraLarge = new JRadioButtonMenuItem();
			jRadioButtonMenuItemExtraExtraLarge.setText("Extra Extra Large");
			jRadioButtonMenuItemExtraExtraLarge.setActionCommand("xx-large");
			jRadioButtonMenuItemExtraExtraLarge.addItemListener(new ItemListener() {
	
				public void itemStateChanged(ItemEvent event) {
					fontSizeSelectionChanged(event);
				}
			});
		}
		return jRadioButtonMenuItemExtraExtraLarge;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItemExtraLarge() {
		if (jRadioButtonMenuItemExtraLarge == null) {
			jRadioButtonMenuItemExtraLarge = new JRadioButtonMenuItem();
			jRadioButtonMenuItemExtraLarge.setText("Extra Large");
			jRadioButtonMenuItemExtraLarge.setActionCommand("x-large");
			jRadioButtonMenuItemExtraLarge.addItemListener(new ItemListener() {
	
				public void itemStateChanged(ItemEvent event) {
					fontSizeSelectionChanged(event);
				}
			});
		}
		return jRadioButtonMenuItemExtraLarge;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItemLarge() {
		if (jRadioButtonMenuItemLarge == null) {
			jRadioButtonMenuItemLarge = new JRadioButtonMenuItem();
			jRadioButtonMenuItemLarge.setText("Large");
			jRadioButtonMenuItemLarge.setActionCommand("large");
			jRadioButtonMenuItemLarge.addItemListener(new ItemListener() {
	
				public void itemStateChanged(ItemEvent event) {
					fontSizeSelectionChanged(event);
				}
			});
		}
		return jRadioButtonMenuItemLarge;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItemMedium() {
		if (jRadioButtonMenuItemMedium == null) {
			jRadioButtonMenuItemMedium = new JRadioButtonMenuItem();
			jRadioButtonMenuItemMedium.setText("Medium");
			jRadioButtonMenuItemMedium.setActionCommand("medium");
			jRadioButtonMenuItemMedium.addItemListener(new ItemListener() {
	
				public void itemStateChanged(ItemEvent event) {
					fontSizeSelectionChanged(event);
				}
			});
		}
		return jRadioButtonMenuItemMedium;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItemSmall() {
		if (jRadioButtonMenuItemSmall == null) {
			jRadioButtonMenuItemSmall = new JRadioButtonMenuItem();
			jRadioButtonMenuItemSmall.setSelected(true);
			jRadioButtonMenuItemSmall.setText("Small");
			jRadioButtonMenuItemSmall.setActionCommand("small");
			jRadioButtonMenuItemSmall.addItemListener(new ItemListener() {
	
				public void itemStateChanged(ItemEvent event) {
					fontSizeSelectionChanged(event);
				}
			});
		}
		return jRadioButtonMenuItemSmall;
	}

	private void initFontSizeButtonGroup() {
		fontSizeButtonGroup = new ButtonGroup();
		fontSizeButtonGroup.add(getJRadioButtonMenuItemSmall());
		fontSizeButtonGroup.add(getJRadioButtonMenuItemMedium());
		fontSizeButtonGroup.add(getJRadioButtonMenuItemLarge());
		fontSizeButtonGroup.add(getJRadioButtonMenuItemExtraLarge());
		fontSizeButtonGroup.add(getJRadioButtonMenuItemExtraExtraLarge());
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
			fighter.addTempHP((Integer) getJSpinnerDamageHealAmount().getValue());
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
		getJTableRoster().getSelectionModel().setSelectionInterval(0, 0);
	}

	/**
	 * Event: Change clicked.
	 * 
	 * @param event
	 */
	private void jButtonChangeActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			if (getJListEffects().getSelectedIndices().length > 0) {
				for (int i : getJListEffects().getSelectedIndices()) {
					Effect eff = (Effect) getJListEffects().getModel().getElementAt(i);
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
			fighter.damage((Integer) getJSpinnerDamageHealAmount().getValue());
			updateFromClass();
			getJSpinnerDamageHealAmount().requestFocusInWindow();

			if (!getFight().getCurrentFighterHandle().isEmpty()) {
				StatLogger.logDamage(getFight().getCurrentRound(), getFight().getCurrentFighter().getCombatHandle(),
						fighter.getCombatHandle(), (Integer) getJSpinnerDamageHealAmount().getValue());
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
		getJSpinnerDamageHealAmount().setValue(5);
		getJSpinnerDamageHealAmount().requestFocusInWindow();
	}

	/**
	 * Event: Halve clicked.
	 * 
	 * @param event
	 */
	private void jButtonHalveActionActionPerformed(ActionEvent event) {
		getJSpinnerDamageHealAmount().setValue((Integer) getJSpinnerDamageHealAmount().getValue() / 2);
	}

	/**
	 * Event: Heal clicked.
	 * 
	 * @param event
	 */
	private void jButtonHealActionActionPerformed(ActionEvent event) {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			fighter.heal((Integer) getJSpinnerDamageHealAmount().getValue());
			updateFromClass();
			getJSpinnerDamageHealAmount().requestFocusInWindow();

			if (!getFight().getCurrentFighterHandle().isEmpty()) {
				StatLogger.logDamage(getFight().getCurrentRound(), getFight().getCurrentFighter().getCombatHandle(),
						fighter.getCombatHandle(), -(Integer) getJSpinnerDamageHealAmount().getValue());
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
			getJSpinnerDamageHealAmount().setValue(fighter.getMaxHP());
			getJSpinnerDamageHealAmount().requestFocusInWindow();
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
			getJTextFieldSurges().setText(fighter.getSurgeView());
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
		getJTableRoster().getSelectionModel().setSelectionInterval(0, 0);
	}

	/**
	 * Event: +5 clicked.
	 * 
	 * @param event
	 */
	private void jButtonPlusFiveActionActionPerformed(ActionEvent event) {
		getJSpinnerDamageHealAmount().setValue((Integer) getJSpinnerDamageHealAmount().getValue() + 5);
		getJSpinnerDamageHealAmount().requestFocusInWindow();
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
			getJTextFieldSurges().setText(fighter.getSurgeView());
		}
	}

	/**
	 * Event: Power Points -4 pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerPointsMinusFourActionActionPerformed(ActionEvent event) {
		Integer oldVal = (Integer) getJSpinnerPowerPoints().getValue();
		Integer newVal = oldVal - 4;
		getJSpinnerPowerPoints().setValue((newVal < 0 ? 0 : newVal));
	}

	/**
	 * Event: Power Points -6 pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerPointsMinusSixActionActionPerformed(ActionEvent event) {
		Integer oldVal = (Integer) getJSpinnerPowerPoints().getValue();
		Integer newVal = oldVal - 6;
		getJSpinnerPowerPoints().setValue((newVal < 0 ? 0 : newVal));
	}

	/**
	 * Event: Power Points -2 pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerPointsMinusTwoActionActionPerformed(ActionEvent event) {
		Integer oldVal = (Integer) getJSpinnerPowerPoints().getValue();
		Integer newVal = oldVal - 2;
		getJSpinnerPowerPoints().setValue((newVal < 0 ? 0 : newVal));
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
			getJTextFieldSurges().setText(fighter.getSurgeView());
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
			if (getJListEffects().getSelectedIndices().length > 0) {
				for (int i : getJListEffects().getSelectedIndices()) {
					Effect eff = (Effect) getJListEffects().getModel().getElementAt(i);
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
			getJSpinnerDamageHealAmount().setValue(fighter.getSurgeValue());
			getJSpinnerDamageHealAmount().requestFocusInWindow();
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
			fighter.setActionPointSpent(getJCheckBoxSpent().isSelected());
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
				getJSpinnerDamageHealAmount().setValue(Integer.valueOf(result.substring(result.indexOf("=") + 1).trim()));
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
			getJButtonChange().doClick();
		}
	}

	/**
	 * Event: Off-turn powers list selection changed.
	 * 
	 * @param event
	 */
	private void jListOffTurnPowersListSelectionValueChanged(ListSelectionEvent event) {
		if (getJListOffTurnPowers().getSelectedIndex() >= 0) {
			FighterPower fp = (FighterPower) getJListOffTurnPowers().getSelectedValue();
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
			if (fighter != null && !getJListPowerList().isSelectionEmpty()) {
				DefaultListModel model = (DefaultListModel) getJListPowerList().getModel();
				Power pow = (Power) model.getElementAt(getJListPowerList().getSelectedIndex());
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
		getJButtonDelay().doClick();
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
	private void jMenuItemMoveToStopActionActionPerformed(ActionEvent event) {
		getJButtonMoveToTop().doClick();
	}

	/**
	 * Event: Roster, popup menu, Ready clicked.
	 * 
	 * @param event
	 */
	private void jMenuItemReadyActionActionPerformed(ActionEvent event) {
		getJButtonReady().doClick();
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
		if (getJTableRoster().getSelectedRow() == -1) {
			getJPopupMenuRoster().setVisible(false);
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
			fighter.setActionPointsRemaining((Integer) getJSpinnerActionPoints().getValue());
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
		if ((Integer) getJSpinnerDamageHealAmount().getValue() > 0) {
			getJButtonDamage().setEnabled(true);
			getJButtonHeal().setEnabled(true);
			getJButtonAddTemp().setEnabled(true);
			getJButtonHalve().setEnabled(true);
		} else {
			getJButtonDamage().setEnabled(false);
			getJButtonHeal().setEnabled(false);
			getJButtonAddTemp().setEnabled(false);
			getJButtonHalve().setEnabled(false);
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
			if ((Integer) getJSpinnerInitRoll().getValue() != fighter.getInitRoll()) {
				if (fighter.getInitStatus().contentEquals("Reserve")) {
					getFight().rollOneInit(fighter);
				}
				getFight().fighterInitRollUpdate(fighter.getCombatHandle(), (Integer) getJSpinnerInitRoll().getValue());
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
			fighter.setPowerPointsRemaining((Integer) getJSpinnerPowerPoints().getValue());
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
		ColumnsAutoSizer.sizeColumnsToFit(getJTableRoster(), 15);
		if (getJTableRoster().getSelectedRow() >= 0) {
			Combatant fighterSelected = getFight().getSelectedFighter();
			String tableSelected = (String) getJTableRoster().getValueAt(getJTableRoster().getSelectedRow(), 2);

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
		String newValue = getJTextFieldName().getText().trim();

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

	private void jToggleButtonCritHitItemItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (Settings.getCriticalHitSong() != null) {
				getJToggleButtonCritMiss().setSelected(false);
				getJToggleButtonMiscDaily().setSelected(false);
				getJToggleButtonMiscVictory().setSelected(false);
				getJToggleButtonPlay().setSelected(false);
				Player.playOnce(Settings.getCriticalHitSong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						getJToggleButtonCritHit().setSelected(false);
						getJToggleButtonPlay().setSelected(true);
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
				getJToggleButtonCritHit().setSelected(false);
				getJToggleButtonMiscDaily().setSelected(false);
				getJToggleButtonMiscVictory().setSelected(false);
				getJToggleButtonPlay().setSelected(false);
				Player.playOnce(Settings.getCriticalMissSong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						getJToggleButtonCritMiss().setSelected(false);
						getJToggleButtonPlay().setSelected(true);
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
				getJToggleButtonCritHit().setSelected(false);
				getJToggleButtonCritMiss().setSelected(false);
				getJToggleButtonMiscVictory().setSelected(false);
				getJToggleButtonPlay().setSelected(false);
				Player.playOnce(Settings.getDailySong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						getJToggleButtonMiscDaily().setSelected(false);
						getJToggleButtonPlay().setSelected(true);
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
				getJToggleButtonCritHit().setSelected(false);
				getJToggleButtonCritMiss().setSelected(false);
				getJToggleButtonMiscDaily().setSelected(false);
				getJToggleButtonPlay().setSelected(false);
				Player.playOnce(Settings.getVictorySong(), new PlaybackListener() {
					@Override
					public void playbackFinished(PlaybackEvent event) {
						Player.setCompletedOnce(true);
						getJToggleButtonMiscVictory().setSelected(false);
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
			Player.setDir((File) getJComboBoxPlaylists().getSelectedItem());
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
		getJComboBoxPlaylists().setRenderer(new PlaylistCellRenderer());
		DefaultComboBoxModel model = (DefaultComboBoxModel) getJComboBoxPlaylists().getModel();
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
			getJTableRoster().clearSelection();
			getFight().resetEncounter(false);
			getJTabbedPaneUtils().setSelectedIndex(0);
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
		getJTabbedPaneUtils().setSelectedIndex(1);
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
			getJTableRoster().clearSelection();
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
			getJTableRoster().clearSelection();
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
			getJTableRoster().clearSelection();
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
			getMenuWindowsMinimalInitDisplay().setSelected(false);
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
			getMenuWindowsFullInitDisplay().setSelected(false);
			getInitDisplay().dispose();
			setFullInit(false);
			GraphicsEnvironment gc = GraphicsEnvironment.getLocalGraphicsEnvironment();
			if (gc.getScreenDevices().length > 1) {
				getInitDisplay().setUndecorated(true);
				gc.getScreenDevices()[1].setFullScreenWindow(getInitDisplay());
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

	private void menuWindowsOptionsActionActionPerformed(ActionEvent event) {
		Options options = new Options();
		options.setLocationRelativeTo(this);
		options.setVisible(true);
		options.dispose();

		((DefaultComboBoxModel) getJComboBoxPlaylists().getModel()).removeAllElements();
		loadPlaylists(Settings.getMusicDirectory());
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
		if (getJListPowerList().getSelectedIndex() >= 0) {
			DefaultListModel model = (DefaultListModel) getJListPowerList().getModel();
			Power selected = (Power) model.get(getJListPowerList().getSelectedIndex());
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
								getJEditorPaneCompendium().setText(content);
								getJEditorPaneCompendium().setCaretPosition(0);
							} catch (MalformedURLException e) {
								getJEditorPaneCompendium().setText(
										"<html><body><h1>Failed to load URL</h1><pre>" + e + "</pre></body></html>");
							} catch (IOException e) {
								getJEditorPaneCompendium().setText(
										"<html><body><h1>Failed to load URL</h1><pre>" + e + "</pre></body></html>");
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
			getJListPowerList().setCellRenderer(new PowerCellRenderer(fighter));
			DefaultListModel model = (DefaultListModel) getJListPowerList().getModel();
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
		getJTableRoster().setDefaultRenderer(Object.class, getRosterRenderer());
		DefaultTableModel model = (DefaultTableModel) getJTableRoster().getModel();
		while (getFight().size() < getJTableRoster().getRowCount()) {
			model.removeRow(getJTableRoster().getRowCount() - 1);
		}

		Integer index = 0;
		while (index < getFight().size()) {
			Combatant fighter = getFight().getFighterByIndex(index);

			if (fighter == null) {
				break;
			}

			if (index < getJTableRoster().getRowCount()) {
				getJTableRoster().setValueAt(fighter.isShown().toString().substring(0, 1).toUpperCase(), index, 0);
				getJTableRoster().setValueAt(fighter.getRound(), index, 1);
				getJTableRoster().setValueAt(fighter.getCombatHandle(), index, 2);
				getJTableRoster().setValueAt(fighter.getStatusLine(), index, 3);
				getJTableRoster().setValueAt(fighter.getAC(), index, 4);
				getJTableRoster().setValueAt(fighter.getFort(), index, 5);
				getJTableRoster().setValueAt(fighter.getRef(), index, 6);
				getJTableRoster().setValueAt(fighter.getWill(), index, 7);
			} else {
				model.addRow(new Object[] { fighter.isShown().toString().substring(0, 1).toUpperCase(), fighter.getRound(),
						fighter.getCombatHandle(), fighter.getStatusLine(), fighter.getAC(), fighter.getFort(), fighter.getRef(),
						fighter.getWill() });
			}

			if (fighter.getInitStatus().contentEquals("Rolled")) {
				// set group to 0
				if (fighter.getRound() == 0) {
					getJTableRoster().setValueAt("S", index, 1);
				} else {
					getJTableRoster().setValueAt(fighter.getRound(), index, 1);
				}
			} else if (fighter.getInitStatus().contentEquals("Delay")) {
				// set group to 1
				getJTableRoster().setValueAt("D", index, 1);
			} else if (fighter.getInitStatus().contentEquals("Ready")) {
				// set group to 2
				getJTableRoster().setValueAt("R", index, 1);
			} else if (fighter.getInitStatus().contentEquals("Inactive")) {
				// set group to 3
				getJTableRoster().setValueAt("I", index, 1);
			} else if (fighter.getInitStatus().contentEquals("Rolling")) {
				// set group to 4
				getJTableRoster().setValueAt("x", index, 1);
			} else {
				// set group to 5
				getJTableRoster().setValueAt("", index, 1);
			}

			index++;
		}
		ColumnsAutoSizer.sizeColumnsToFit(getJTableRoster(), 15);

		updateOffTurnPowers();
		updateInitDisplay();
		updateEnabledControls();
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
				return f.getName().endsWith(".xml");
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
			getFight().setGlobalNotes(getJTextAreaNotes().getText());
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
		getJEditorPaneStatblock().setText("");
		((DefaultListModel) getJListPowerList().getModel()).clear();
		((DefaultListModel) getJListEffects().getModel()).clear();
		getJTextFieldName().setText("");
		getJTextFieldNumber().setText("");
		getJSpinnerInitRoll().setValue(0);
		getJSpinnerActionPoints().setValue(0);
		getJSpinnerPowerPoints().setValue(0);
		getJCheckBoxSpent().setSelected(false);
		getJTextFieldSurges().setText("");
		getFight().clearSelectedFighter();
		statDataDisable();
	}

	private void statDataDisable() {
		getJTextFieldName().setEnabled(false);
		getJSpinnerInitRoll().setEnabled(false);
		getJCheckBoxSpent().setEnabled(false);
		getJButtonBackUp().setEnabled(false);
		getJButtonDelay().setEnabled(false);
		getJButtonMoveToTop().setEnabled(false);
		getJButtonReady().setEnabled(false);
		getJButtonReserve().setEnabled(false);
		getJButtonRollInitiative().setEnabled(false);
		getJSpinnerDamageHealAmount().setEnabled(false);
		getJButtonDamage().setEnabled(false);
		getJButtonFailDeath().setEnabled(false);
		getJButtonFive().setEnabled(false);
		getJButtonPlusFive().setEnabled(false);
		getJButtonSurge().setEnabled(false);
		getJButtonMax().setEnabled(false);
		getJTextFieldSurges().setEnabled(false);
		getJButtonPlusOne().setEnabled(false);
		getJButtonMinusOne().setEnabled(false);
		getJButtonRegainAll().setEnabled(false);
		getJButtonHeal().setEnabled(false);
		getJButtonUndoDeath().setEnabled(false);
		getJButtonAddTemp().setEnabled(false);
		getJButtonRemoveFighter().setEnabled(false);
		getJListPowerList().setEnabled(false);
		getJListEffects().setEnabled(false);
		getJButtonAdd().setEnabled(false);
		getJButtonChange().setEnabled(false);
		getJButtonRemove().setEnabled(false);
		getJPopupMenuRoster().setEnabled(false);
	}

	private void statDataEnable(Combatant fighter) {
		if (fighter != null) {
			getJTextFieldName().setEnabled(true);
			getJSpinnerInitRoll().setEnabled(true);
			getJListPowerList().setEnabled(true);
			getJListEffects().setEnabled(true);
			getJButtonBackUp().setEnabled(false);
			getJButtonDelay().setEnabled(false);
			getJButtonMoveToTop().setEnabled(false);
			getJButtonReady().setEnabled(false);
			getJButtonReserve().setEnabled(false);
			getJButtonRollInitiative().setEnabled(false);
			getJButtonFailDeath().setEnabled(false);
			getJButtonUndoDeath().setEnabled(false);
			getJSpinnerDamageHealAmount().setEnabled(false);
			getJButtonDamage().setEnabled(false);
			getJButtonHeal().setEnabled(false);
			getJButtonAddTemp().setEnabled(false);
			getJButtonFive().setEnabled(false);
			getJButtonPlusFive().setEnabled(false);
			getJButtonSurge().setEnabled(false);
			getJButtonMax().setEnabled(false);
			getJTextFieldSurges().setEnabled(false);
			getJButtonPlusOne().setEnabled(false);
			getJButtonMinusOne().setEnabled(false);
			getJButtonRemoveFighter().setEnabled(true);
			getJCheckBoxSpent().setEnabled(true);

			if (fighter.getInitStatus().contentEquals("Rolled")) {
				getJButtonBackUp().setEnabled(true);
				getJButtonDelay().setEnabled(true);
				getJButtonMoveToTop().setEnabled(true);
				getJButtonReady().setEnabled(true);
				getJButtonReserve().setEnabled(true);
			} else if (fighter.getInitStatus().contentEquals("Ready")) {
				getJButtonDelay().setEnabled(true);
				getJButtonMoveToTop().setEnabled(true);
				getJButtonReserve().setEnabled(true);
			} else if (fighter.getInitStatus().contentEquals("Delay")) {
				getJButtonMoveToTop().setEnabled(true);
				getJButtonReady().setEnabled(true);
				getJButtonReserve().setEnabled(true);
			} else if (fighter.getInitStatus().contentEquals("Reserve")) {
				getJButtonRollInitiative().setEnabled(true);
			} else {
				getJButtonReserve().setEnabled(true);
			}

			if (getFight().getCurrentFighter() != null && getJButtonMoveToTop().isEnabled()
					&& getFight().getCurrentFighter().getCombatHandle().contentEquals(fighter.getCombatHandle())) {
				getJButtonMoveToTop().setEnabled(false);
			}

			if (!fighter.getInitStatus().contentEquals("Reserve") || fighter.isPC()) {
				getJSpinnerInitRoll().setEnabled(true);
				getJSpinnerDamageHealAmount().setEnabled(true);
				getJButtonFive().setEnabled(true);
				getJButtonPlusFive().setEnabled(true);
				getJButtonSurge().setEnabled(true);
				getJButtonMax().setEnabled(true);
				getJTextFieldSurges().setEnabled(true);
				getJButtonAdd().setEnabled(true);
				getJButtonRemove().setEnabled(true);
				if (fighter.isPC()) {
					getJButtonPlusOne().setEnabled(true);
					getJButtonMinusOne().setEnabled(true);
					getJButtonRegainAll().setEnabled(true);
					if (!fighter.isActive()) {
						if (fighter.isAlive()) {
							getJButtonFailDeath().setEnabled(true);
						}
						if (fighter.isDyingOrDead()) {
							getJButtonUndoDeath().setEnabled(true);
						}
					}
				}
				if (Integer.valueOf(getJSpinnerDamageHealAmount().getValue().toString().replace(",", "")) > 0) {
					getJButtonDamage().setEnabled(true);
					getJButtonHeal().setEnabled(true);
					if (fighter.isAlive()) {
						getJButtonAddTemp().setEnabled(true);
					} else {
						getJButtonAddTemp().setEnabled(false);
					}
				} else {
					getJButtonDamage().setEnabled(false);
					getJButtonHeal().setEnabled(false);
					getJButtonAddTemp().setEnabled(false);
				}
			}
			powerInfoUpdate();
			effectButtonUpdate();
			getJPopupMenuRoster().setEnabled(true);
			getJMenuItemDelay().setEnabled(getJButtonDelay().isEnabled());
			getJMenuItemMoveToTop().setEnabled(getJButtonMoveToTop().isEnabled());
			getJMenuItemReady().setEnabled(getJButtonReady().isEnabled());
			getJMenuItemMarkUntilEONT().setEnabled(true);
			getJMenuItemMarkUntilEOE().setEnabled(true);
		}
	}

	/**
	 * Load fighter statistics.
	 */
	private void statDataLoad() {
		Combatant fighter = getListSelectedFighter();

		if (fighter != null) {
			getJTextFieldName().setText(fighter.getName());
			if (fighter.getFighterNumber() > 0) {
				getJTextFieldNumber().setText(fighter.getFighterNumber().toString());
			} else {
				getJTextFieldNumber().setText("PC");
			}
			getJSpinnerInitRoll().setValue(fighter.getInitRoll());
			getJSpinnerActionPoints().setValue(fighter.getActionPointsRemaining());
			getJSpinnerPowerPoints().setValue(fighter.getPowerPointsRemaining());
			getJCheckBoxSpent().setSelected(fighter.isActionPointSpent());

			getJEditorPaneStatblock().setText(fighter.getStatsHTML());
			getJEditorPaneStatblock().setCaretPosition(0);
			getJTextFieldSurges().setText(fighter.getSurgeView());

			powerLoad();
			effectLoad();

			statDataEnable(fighter);
		}
	}

	/**
	 * Updates the enabled controls based on encounter state.
	 */
	private void updateEnabledControls() {
		getMenuFileSave().setEnabled(false);
		getMenuEncounterEnd().setEnabled(false);
		getMenuEncounterInitiative().setEnabled(false);
		getMenuEncounterRemoveMonsters().setEnabled(false);
		getMenuWindowsMinimalInitDisplay().setEnabled(false);
		getMenuWindowsFullInitDisplay().setEnabled(false);
		getMenuPartyShortRest().setEnabled(false);
		getMenuPartyShortRestMilestone().setEnabled(false);
		getMenuPartyExtendedRest().setEnabled(false);
		getMenuPartyRemove().setEnabled(false);

		getJButtonNextTurn().setEnabled(false);

		if (getFight().size() > 0) {
			getMenuFileSave().setEnabled(true);

			if (getFight().getReserveList().size() > 0) {
				getMenuEncounterInitiative().setEnabled(true);
			}

			if (!getFight().isOngoingFight()) {
				getMenuEncounterRemoveMonsters().setEnabled(true);
				getMenuPartyRemove().setEnabled(true);
				getMenuPartyShortRest().setEnabled(true);
				getMenuPartyShortRestMilestone().setEnabled(true);
				getMenuPartyExtendedRest().setEnabled(true);
			} else {
				if (getFight().getRolledList().size() > 0) {
					getMenuEncounterEnd().setEnabled(true);
					getMenuWindowsMinimalInitDisplay().setEnabled(true);
					getMenuWindowsFullInitDisplay().setEnabled(true);
					getJButtonNextTurn().setEnabled(true);
				}
			}
		}
	}

	/**
	 * Updates display from class information.
	 */
	private void updateFromClass() {
		getJTextAreaNotes().setText(getFight().getGlobalNotes());
		getJLabelPreviousRest().setText("Previous Rest: " + Settings.getRest());
		reloadListFromClass();

		if (getFight().hasSelectedFighter()) {
			Combatant fighter = getFight().getSelectedFighter();
			for (int i = 0; i < getJTableRoster().getRowCount(); i++) {
				String handle = (String) getJTableRoster().getValueAt(i, 2);
				if (handle.contentEquals(fighter.getCombatHandle())) {
					getJTableRoster().getSelectionModel().setSelectionInterval(i, i);
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
				+ "table { width: 100%; border-width: 1px; border-style: solid; border-color: black }\n"
				+ "th { font-size: " + Settings.getFontSize() + "; border-width: 1px; border-style: solid; border-color: black }\n"
				+ "td { font-size: " + Settings.getFontSize() + "; border-width: 1px; border-style: solid; border-color: gray }\n"
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
					text += "<td style='width: 105px'><div style='height: 1em; border-width: 1px; border-style: solid; border-color: white; "
							+ "width: 100px'><div style='border-width: 0px; width: "
							+ healthPercent
							+ "px; background-color: "
							+ hpBarColor + "'></div></div></td>";
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
		getJListOffTurnPowers().setCellRenderer(new OffTurnPowerRenderer(getFight()));
		DefaultListModel model = (DefaultListModel) getJListOffTurnPowers().getModel();
		model.clear();
		for (String handle : getFight().getRolledList().values()) {
			Combatant fighter = getFight().getFighterByHandle(handle);
			for (Power pow : fighter.getPowerList()) {
				if (!fighter.isPowerUsed(pow.getName())
						&& !fighter.isPC()
						&& (pow.getAction().contains("immediate") || pow.getAction().contains("opportunity;")
								|| pow.getAction().contains("triggered;") || pow.getAction().contains("free;")
								|| pow.getAction().contains("no;") || pow.isAura())) {
					model.addElement(new FighterPower(fighter, pow));
				}
			}
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
		((DefaultComboBoxModel) getJComboBoxPlaylists().getModel()).removeAllElements();
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
}