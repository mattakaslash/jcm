package cm.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import cm.model.Combatant;
import cm.model.Effect;
import cm.model.EffectBase;
import cm.model.EffectBase.Duration;
import cm.model.Encounter;
import cm.util.AutoCompletion;
import cm.view.render.DurationCellRenderer;
import cm.view.render.EffectBaseCellRenderer;

//VS4E -- DO NOT REMOVE THIS LINE!
public class EffectWin extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelEffect;
	private JLabel jLabelName;
	private JComboBox jComboBoxName;
	private JLabel jLabelDuration;
	private JCheckBox jCheckBoxBeneficial;
	private JCheckBox jCheckBoxHidden;
	private JComboBox jComboBoxDuration;
	private JComboBox jComboBoxSource;
	private JComboBox jComboBoxTarget;
	private JLabel jLabelSource;
	private JLabel jLabelTarget;
	private JButton jButtonCancel;
	private JButton jButtonOK;
	private JList jListPresets;
	private JScrollPane jScrollPanePresets;
	public EffectWin() {
		initComponents();
	}

	/**
	 * Creates a new effect window for the given encounter.
	 * @param fight the encounter
	 * @param c the parent component
	 */
	public EffectWin(Encounter fight, Frame c) {
		super(c);
		initComponents();
		
		setFight(fight);
		setModEffect(new Effect());
		loadCombos();
		setDefaults();
	}
	/**
	 * Creates a new effect window for the given encounter, with an effect to modify.
	 * @param fight the encounter
	 * @param eff the effect to modify
	 * @param c the parent component
	 */
	public EffectWin(Encounter fight, Effect eff, Frame c) {
		super(c);
		initComponents();
		
		setFight(fight);
		setModEffect(eff);
		setOrigID(eff.getEffectID());
		setOrigRound(eff.getRoundTill());
		loadCombos();
		setDefaults();
		//loadHistory();
		moveClassToFields(eff);
	}

	private void initComponents() {
		setTitle("Effect");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setModal(true);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanelEffect(), new Constraints(new Bilateral(12, 12, 0), new Leading(12, 136, 10, 10)));
		add(getJButtonCancel(), new Constraints(new Trailing(12, 12, 12), new Leading(154, 12, 12)));
		add(getJButtonOK(), new Constraints(new Trailing(91, 12, 12), new Leading(154, 12, 12)));
		add(getJScrollPanePresets(), new Constraints(new Leading(12, 379, 12, 12), new Bilateral(185, 12, 22)));
		pack();
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

	private JCheckBox getJCheckBoxBeneficial() {
		if (jCheckBoxBeneficial == null) {
			jCheckBoxBeneficial = new JCheckBox();
			jCheckBoxBeneficial.setFont(new Font("Dialog", Font.PLAIN, 12));
			jCheckBoxBeneficial.setText("Beneficial");
		}
		return jCheckBoxBeneficial;
	}

	private JCheckBox getJCheckBoxHidden() {
		if (jCheckBoxHidden == null) {
			jCheckBoxHidden = new JCheckBox();
			jCheckBoxHidden.setFont(new Font("Dialog", Font.PLAIN, 12));
			jCheckBoxHidden.setText("Hidden");
		}
		return jCheckBoxHidden;
	}

	private JComboBox getJComboBoxDuration() {
		if (jComboBoxDuration == null) {
			jComboBoxDuration = new JComboBox();
			jComboBoxDuration.setFont(new Font("Dialog", Font.PLAIN, 12));
			jComboBoxDuration.setModel(new DefaultComboBoxModel(new Object[] { Duration.None, Duration.SourceEnd, Duration.TargetEnd, Duration.TurnEnd, Duration.Encounter, Duration.SaveEnds, Duration.Special, Duration.SourceStart, Duration.TargetStart, Duration.Sustained }));
			jComboBoxDuration.setRenderer(new DurationCellRenderer());
			jComboBoxDuration.setDoubleBuffered(false);
			jComboBoxDuration.setBorder(null);
			jComboBoxDuration.setRequestFocusEnabled(false);
		}
		return jComboBoxDuration;
	}

	private JComboBox getJComboBoxName() {
		if (jComboBoxName == null) {
			jComboBoxName = new JComboBox();
			jComboBoxName.setEditable(true);
			jComboBoxName.setFont(new Font("Dialog", Font.PLAIN, 12));
			jComboBoxName.setModel(new DefaultComboBoxModel(new Object[] { "", "Attack Penalty", "Blinded", "Dazed", "Deafened", "Defense Penalty", "Dominated",
					"Full Defense (+2 all def)", "Granting Combat Advantage", "Immobilized", "Marked", "Ongoing Damage", "Petrified", "Prone", "Regeneration",
					"Resist", "Restrained", "Second Wind (+2 all def)", "Slowed", "Stunned", "Surprised", "Unconscious", "Vulnerability", "Weakened" }));
			jComboBoxName.setDoubleBuffered(false);
			jComboBoxName.setBorder(null);
			jComboBoxName.setRequestFocusEnabled(false);
			AutoCompletion.enable(jComboBoxName);
		}
		return jComboBoxName;
	}

	private JComboBox getJComboBoxSource() {
		if (jComboBoxSource == null) {
			jComboBoxSource = new JComboBox();
			jComboBoxSource.setFont(new Font("Dialog", Font.PLAIN, 12));
			jComboBoxSource.setModel(new DefaultComboBoxModel(new Object[] {}));
			jComboBoxSource.setDoubleBuffered(false);
			jComboBoxSource.setBorder(null);
			jComboBoxSource.setRequestFocusEnabled(false);
			jComboBoxSource.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jComboBoxSourceActionActionPerformed(event);
				}
			});
		}
		return jComboBoxSource;
	}

	private JComboBox getJComboBoxTarget() {
		if (jComboBoxTarget == null) {
			jComboBoxTarget = new JComboBox();
			jComboBoxTarget.setFont(new Font("Dialog", Font.PLAIN, 12));
			jComboBoxTarget.setModel(new DefaultComboBoxModel(new Object[] {}));
			jComboBoxTarget.setDoubleBuffered(false);
			jComboBoxTarget.setBorder(null);
			jComboBoxTarget.setRequestFocusEnabled(false);
			jComboBoxTarget.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jComboBoxTargetActionActionPerformed(event);
				}
			});
		}
		return jComboBoxTarget;
	}

	private JLabel getJLabelDuration() {
		if (jLabelDuration == null) {
			jLabelDuration = new JLabel();
			jLabelDuration.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabelDuration.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelDuration.setText("Duration");
		}
		return jLabelDuration;
	}

	private JLabel getJLabelName() {
		if (jLabelName == null) {
			jLabelName = new JLabel();
			jLabelName.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabelName.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelName.setText("Name");
		}
		return jLabelName;
	}

	private JLabel getJLabelSource() {
		if (jLabelSource == null) {
			jLabelSource = new JLabel();
			jLabelSource.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabelSource.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelSource.setText("Source");
		}
		return jLabelSource;
	}

	private JLabel getJLabelTarget() {
		if (jLabelTarget == null) {
			jLabelTarget = new JLabel();
			jLabelTarget.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabelTarget.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabelTarget.setText("Target");
		}
		return jLabelTarget;
	}

	private JList getJListPresets() {
		if (jListPresets == null) {
			jListPresets = new JList();
			DefaultListModel listModel = new DefaultListModel();
			jListPresets.setModel(listModel);
			jListPresets.setCellRenderer(new EffectBaseCellRenderer());
			jListPresets.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jListPresetsMouseMouseClicked(event);
				}
			});
			jListPresets.addListSelectionListener(new ListSelectionListener() {
	
				public void valueChanged(ListSelectionEvent event) {
					jListPresetsListSelectionValueChanged(event);
				}
			});
		}
		return jListPresets;
	}

	private JPanel getJPanelEffect() {
		if (jPanelEffect == null) {
			jPanelEffect = new JPanel();
			jPanelEffect.setBorder(BorderFactory.createTitledBorder(null, "Effect", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.PLAIN, 12), new Color(51, 51, 51)));
			jPanelEffect.setLayout(new GroupLayout());
			jPanelEffect.add(getJLabelName(), new Constraints(new Leading(12, 57, 10, 10), new Leading(0, 12, 12)));
			jPanelEffect.add(getJLabelDuration(), new Constraints(new Leading(12, 57, 12, 12), new Leading(27, 12, 12)));
			jPanelEffect.add(getJCheckBoxBeneficial(), new Constraints(new Trailing(8, 174, 174), new Leading(-4, 8, 8)));
			jPanelEffect.add(getJCheckBoxHidden(), new Constraints(new Trailing(8, 79, 73, 83), new Leading(24, 8, 8)));
			jPanelEffect.add(getJComboBoxName(), new Constraints(new Bilateral(81, 89, 184), new Leading(-4, 12, 12)));
			jPanelEffect.add(getJComboBoxDuration(), new Constraints(new Bilateral(81, 89, 60), new Leading(23, 12, 12)));
			jPanelEffect.add(getJLabelSource(), new Constraints(new Leading(12, 57, 132, 132), new Leading(58, 12, 12)));
			jPanelEffect.add(getJLabelTarget(), new Constraints(new Leading(12, 57, 132, 132), new Leading(85, 12, 12)));
			jPanelEffect.add(getJComboBoxSource(), new Constraints(new Bilateral(81, 8, 31), new Leading(54, 12, 12)));
			jPanelEffect.add(getJComboBoxTarget(), new Constraints(new Bilateral(81, 8, 31), new Leading(82, 10, 10)));
		}
		return jPanelEffect;
	}

	private JScrollPane getJScrollPanePresets() {
		if (jScrollPanePresets == null) {
			jScrollPanePresets = new JScrollPane();
			jScrollPanePresets.setBorder(BorderFactory.createTitledBorder(null, "Prior/Preset Effects from Source", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
			jScrollPanePresets.setViewportView(getJListPresets());
		}
		return jScrollPanePresets;
	}

	/**
	 * Event: Cancel clicked.
	 * @param event
	 */
	private void jButtonCancelActionActionPerformed(ActionEvent event) {
		setModEffect(null);
		this.setVisible(false);
	}

	/**
	 * Event: OK clicked.
	 * @param event
	 */
	private void jButtonOKActionActionPerformed(ActionEvent event) {
		String name = (String) getJComboBoxName().getSelectedItem();
		String source = (String) getJComboBoxSource().getSelectedItem();
		String target = (String) getJComboBoxTarget().getSelectedItem();
		Duration duration = (Duration) getJComboBoxDuration().getSelectedItem();
		
		if (name == null || name.isEmpty() || source == null
				|| source.isEmpty() || target == null || target.isEmpty()
				|| duration == null || duration.toString().contentEquals("None")) {
			JOptionPane.showMessageDialog(this, "Please assign a Name, Source, Target, and Duration to this Effect.  It cannot be added until these values are assigned.");
		} else {
			setModEffect(new Effect());
			moveFieldsToClass(getModEffect());
			setVisible(false);
		}
	}

	/**
	 * Event: Source combo box changed.
	 * @param event
	 */
	private void jComboBoxSourceActionActionPerformed(ActionEvent event) {
		loadHistory();
	}

	/**
	 * Event: Target combo box changed.
	 * @param event
	 */
	private void jComboBoxTargetActionActionPerformed(ActionEvent event) {
		loadHistory();
	}

	/**
	 * Event: Effect list clicked.
	 * @param event
	 */
	private void jListPresetsMouseMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			getJButtonOK().doClick();
		}
	}

	private Encounter _fight;
	private Effect _modEffect;
	private Integer _origID = 0;
	private Integer _origRound = 0;
	private Hashtable<String, EffectBase> _presetEffects = new Hashtable<String, EffectBase>();
	
	/**
	 * Returns the modified/new effect.
	 * @return the effect
	 */
	public Effect getEffect() {
		return getModEffect();
	}

	/**
	 * Returns the encounter.
	 * @return the encounter
	 */
	private Encounter getFight() {
		return _fight;
	}

	/**
	 * Sets the reference encounter.
	 * @param fight the encounter
	 */
	private void setFight(Encounter fight) {
		_fight = fight;
	}

	/**
	 * Returns the effect that being created/modified.
	 * @return the effect
	 */
	private Effect getModEffect() {
		return _modEffect;
	}

	/**
	 * Sets the effect to be modified.
	 * @param modEffect the effect
	 */
	private void setModEffect(Effect modEffect) {
		_modEffect = modEffect;
	}

	/**
	 * Returns the original effect ID.
	 * @return the effect ID
	 */
	private Integer getOrigID() {
		return _origID;
	}
	
	/**
	 * Sets the original effect ID.
	 * @param id the effect ID
	 */
	private void setOrigID(Integer id) {
		_origID = id;
	}

	/**
	 * Returns the original round of combat.
	 * @return the round
	 */
	private Integer getOrigRound() {
		return _origRound;
	}
	
	/**
	 * Sets the original round of combat.
	 * @param origRound the round
	 */
	private void setOrigRound(Integer origRound) {
		_origRound = origRound;
	}

	/**
	 * Returns the set of preset effects.
	 * @return the preset effects
	 */
	private Hashtable<String, EffectBase> getPresetEffects() {
		return _presetEffects;
	}

	/**
	 * Loads combo boxes with lists of fighters.
	 */
	private void loadCombos() {
		if (getFight() != null) {
			for (Combatant fighter : getFight().getFullList()) {
				getJComboBoxSource().addItem(fighter.getCombatHandle());
				getJComboBoxTarget().addItem(fighter.getCombatHandle());
			}
		}
	}

	/**
	 * Loads default values into the form fields.
	 */
	private void setDefaults() {
		if (getFight().getCurrentFighterHandle().isEmpty()) {
			getJComboBoxSource().setSelectedItem(getFight().getSelectedFighterHandle());
		} else {
			getJComboBoxSource().setSelectedItem(getFight().getCurrentFighterHandle());
		}
		getJComboBoxTarget().setSelectedItem(getFight().getSelectedFighterHandle());
		getJComboBoxDuration().setSelectedIndex(0);
		getJCheckBoxBeneficial().setSelected(false);
	}

	/**
	 * Loads effect history into the effect list.
	 */
	private void loadHistory() {
		if (!((String) getJComboBoxSource().getSelectedItem()).isEmpty()) {
			((DefaultListModel)getJListPresets().getModel()).clear();
			getPresetEffects().clear();

			String sourceHandle = (String) getJComboBoxSource()
					.getSelectedItem();
			String targetHandle = (String) getJComboBoxTarget()
					.getSelectedItem();
			Combatant fighter = getFight().getFighterByHandle(sourceHandle);
			if (fighter != null && sourceHandle != null) {
				for (EffectBase eff : fighter.getPresetEffects()) {
					presetEffectAdd(eff);
				}

				for (EffectBase eff : getFight()
						.getEffectsUniqueHistoryBySource(sourceHandle)) {
					presetEffectAdd(eff);
				}
			}

			if (sourceHandle != null && targetHandle != null
					&& sourceHandle.contentEquals(targetHandle)) {
				presetEffectAdd(new EffectBase("Full Defense (+2 all Def)",
						Duration.SourceStart, true));
				if (fighter != null && fighter.isPC()) {
					presetEffectAdd(new EffectBase("Second Wind (+2 all Def)",
							Duration.SourceStart, true));
				}
			}

			DefaultListModel model = (DefaultListModel) getJListPresets()
					.getModel();
			for (EffectBase eff : getPresetEffects().values()) {
				model.addElement(eff);
			}
		}
	}
	
	/**
	 * Loads data from the effect into the form fields.
	 * @param eff
	 */
	private void moveClassToFields(Effect eff) {
		getJComboBoxName().setSelectedItem(eff.getName());
		getJComboBoxSource().setSelectedItem(eff.getSourceHandle());
		getJComboBoxTarget().setSelectedItem(eff.getTargetHandle());
		getJComboBoxDuration().setSelectedItem(eff.getDurationCode());
		getJCheckBoxBeneficial().setSelected(eff.isBeneficial());
		getJCheckBoxHidden().setSelected(eff.isHidden());
	}

	/**
	 * Writes field values to the effect.
	 * @param eff the effect
	 */
	private void moveFieldsToClass(Effect eff) {
		eff.setName((String)getJComboBoxName().getSelectedItem());
		eff.setSourceHandle((String)getJComboBoxSource().getSelectedItem());
		eff.setTargetHandle((String)getJComboBoxTarget().getSelectedItem());
		eff.setDurationCode((Duration)getJComboBoxDuration().getSelectedItem());
		eff.setBeneficial(getJCheckBoxBeneficial().isSelected());
		eff.setHidden(getJCheckBoxHidden().isSelected());
		eff.setEffectID(getOrigID());
		eff.setRoundTill(getOrigRound());
	}

	/**
	 * Adds the given effect to the preset effects list.
	 * @param eff the effect
	 */
	private void presetEffectAdd(EffectBase eff) {
		if (eff.isValid() && !getPresetEffects().contains(eff.getEffectBaseID())) {
			getPresetEffects().put(eff.getEffectBaseID(), eff);
		}
	}

	/**
	 * Event: Prior/preset effects list selection changed. 
	 * @param event
	 */
	private void jListPresetsListSelectionValueChanged(ListSelectionEvent event) {
		if (getJListPresets().getSelectedIndex() >= 0) {
			EffectBase eff = (EffectBase) getJListPresets().getSelectedValue();
			if (eff != null) {
				getJComboBoxName().setSelectedItem(eff.getName());
				getJComboBoxDuration().setSelectedItem(eff.getDurationCode());
				getJCheckBoxBeneficial().setSelected(eff.isBeneficial());
			}
		}
	}
}