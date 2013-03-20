package cm.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.util.Hashtable;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;

import cm.model.Combatant;
import cm.model.Effect;
import cm.model.EffectBase;
import cm.model.Encounter;
import cm.model.EffectBase.Duration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;

/**
 * Displays a list of prior/preset effects and provides an interface to add new
 * ones.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class EffectWin extends JDialog {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -7381308473910493347L;

	/**
	 * The {@link Encounter} the effects are occurring in.
	 */
	private Encounter _fight;

	/**
	 * The modified/added {@link Effect}.
	 */
	private Effect _modEffect;

	/**
	 * The modified {@link Effect}'s original identifier.
	 */
	private Integer _origID = 0;

	/**
	 * The modified {@link Effect}'s original end round.
	 */
	private Integer _origRound = 0;

	/**
	 * A table of prior/preset {@link EffectBase}s for the selected source
	 * {@link Combatant}.
	 */
	private Hashtable<String, EffectBase> _presetEffects = new Hashtable<String, EffectBase>();

	private JPanel _panelEffect;
	private JLabel _lblName;
	private JComboBox _comboBoxName;
	private JLabel _lblDuration;
	private JComboBox _comboBoxDuration;
	private JLabel _lblSource;
	private JComboBox _comboBoxSource;
	private JLabel _lblTarget;
	private JComboBox _comboBoxTarget;
	private JPanel _panelButtons;
	private JButton _buttonOK;
	private JButton _buttonCancel;
	private JPanel _panelPresets;
	private JList _listPresets;
	private JCheckBox _chckbxHidden;
	private JCheckBox _chckbxBeneficial;

	/**
	 * Creates a default effect window.
	 */
	public EffectWin() {
		initComponents();
	}

	/**
	 * Creates a new effect window for the given encounter, with an effect to
	 * modify.
	 * 
	 * @param fight
	 *            the encounter
	 * @param eff
	 *            the effect to modify
	 * @param c
	 *            the parent component
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
		// loadHistory();
		moveClassToFields(eff);
	}

	/**
	 * Creates a new effect window for the given encounter.
	 * 
	 * @param fight
	 *            the encounter
	 * @param c
	 *            the parent component
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
	 * Returns the modified/new effect.
	 * 
	 * @return the effect
	 */
	public Effect getEffect() {
		return getModEffect();
	}

	/**
	 * Returns the encounter.
	 * 
	 * @return the encounter
	 */
	private Encounter getFight() {
		return _fight;
	}

	/**
	 * Returns the effect that being created/modified.
	 * 
	 * @return the effect
	 */
	private Effect getModEffect() {
		return _modEffect;
	}

	/**
	 * Returns the original effect ID.
	 * 
	 * @return the effect ID
	 */
	private Integer getOrigID() {
		return _origID;
	}

	/**
	 * Returns the original round of combat.
	 * 
	 * @return the round
	 */
	private Integer getOrigRound() {
		return _origRound;
	}

	/**
	 * Returns the set of preset effects.
	 * 
	 * @return the preset effects
	 */
	private Hashtable<String, EffectBase> getPresetEffects() {
		return _presetEffects;
	}

	/**
	 * Event: Cancel clicked.
	 * 
	 * @param event
	 */
	private void jButtonCancelActionActionPerformed(ActionEvent event) {
		setModEffect(null);
		this.setVisible(false);
	}

	/**
	 * Event: OK clicked.
	 * 
	 * @param event
	 */
	private void jButtonOKActionActionPerformed(ActionEvent event) {
		String name = (String) _comboBoxName.getSelectedItem();
		String source = (String) _comboBoxSource.getSelectedItem();
		String target = (String) _comboBoxTarget.getSelectedItem();
		Duration duration = (Duration) _comboBoxDuration.getSelectedItem();

		if (name == null || name.isEmpty() || source == null || source.isEmpty() || target == null || target.isEmpty()
				|| duration == null || duration.toString().contentEquals("None")) {
			JOptionPane
					.showMessageDialog(this,
							"Please assign a Name, Source, Target, and Duration to this Effect.  It cannot be added until these values are assigned.");
		} else {
			setModEffect(new Effect());
			moveFieldsToClass(getModEffect());
			setVisible(false);
		}
	}

	/**
	 * Event: Source combo box changed.
	 * 
	 * @param event
	 */
	private void jComboBoxSourceActionActionPerformed(ActionEvent event) {
		loadHistory();
	}

	/**
	 * Event: Target combo box changed.
	 * 
	 * @param event
	 */
	private void jComboBoxTargetActionActionPerformed(ActionEvent event) {
		loadHistory();
	}

	/**
	 * Event: Prior/preset effects list selection changed.
	 * 
	 * @param event
	 */
	private void jListPresetsListSelectionValueChanged(ListSelectionEvent event) {
		if (_listPresets.getSelectedIndex() >= 0) {
			EffectBase eff = (EffectBase) _listPresets.getSelectedValue();
			if (eff != null) {
				_comboBoxName.setSelectedItem(eff.getName());
				_comboBoxDuration.setSelectedItem(eff.getDurationCode());
				_chckbxBeneficial.setSelected(eff.isBeneficial());
			}
		}
	}

	/**
	 * Event: Effect list clicked.
	 * 
	 * @param event
	 */
	private void jListPresetsMouseMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			_buttonOK.doClick();
		}
	}

	/**
	 * Loads combo boxes with lists of fighters.
	 */
	private void loadCombos() {
		if (getFight() != null) {
			for (Combatant fighter : getFight().getFullList()) {
				_comboBoxSource.addItem(fighter.getCombatHandle());
				_comboBoxTarget.addItem(fighter.getCombatHandle());
			}
		}
	}

	/**
	 * Loads effect history into the effect list.
	 */
	private void loadHistory() {
		if (!((String) _comboBoxSource.getSelectedItem()).isEmpty()) {
			((DefaultListModel) _listPresets.getModel()).clear();
			getPresetEffects().clear();

			String sourceHandle = (String) _comboBoxSource.getSelectedItem();
			String targetHandle = (String) _comboBoxTarget.getSelectedItem();
			Combatant fighter = getFight().getFighterByHandle(sourceHandle);
			if (fighter != null && sourceHandle != null) {
				for (EffectBase eff : fighter.getPresetEffects()) {
					presetEffectAdd(eff);
				}

				for (EffectBase eff : getFight().getEffectsUniqueHistoryBySource(sourceHandle)) {
					presetEffectAdd(eff);
				}
			}

			if (sourceHandle != null && targetHandle != null && sourceHandle.contentEquals(targetHandle)) {
				presetEffectAdd(new EffectBase("Full Defense (+2 all Def)", Duration.SourceStart, true));
				if (fighter != null && fighter.isPC()) {
					presetEffectAdd(new EffectBase("Second Wind (+2 all Def)", Duration.SourceStart, true));
				}
			}

			DefaultListModel model = (DefaultListModel) _listPresets.getModel();
			for (EffectBase eff : getPresetEffects().values()) {
				model.addElement(eff);
			}
		}
	}

	/**
	 * Loads data from the effect into the form fields.
	 * 
	 * @param eff
	 */
	private void moveClassToFields(Effect eff) {
		_comboBoxName.setSelectedItem(eff.getName());
		_comboBoxSource.setSelectedItem(eff.getSourceHandle());
		_comboBoxTarget.setSelectedItem(eff.getTargetHandle());
		_comboBoxDuration.setSelectedItem(eff.getDurationCode());
		_chckbxBeneficial.setSelected(eff.isBeneficial());
		_chckbxHidden.setSelected(eff.isHidden());
	}

	/**
	 * Writes field values to the effect.
	 * 
	 * @param eff
	 *            the effect
	 */
	private void moveFieldsToClass(Effect eff) {
		eff.setName((String) _comboBoxName.getSelectedItem());
		eff.setSourceHandle((String) _comboBoxSource.getSelectedItem());
		eff.setTargetHandle((String) _comboBoxTarget.getSelectedItem());
		eff.setDurationCode((Duration) _comboBoxDuration.getSelectedItem());
		eff.setBeneficial(_chckbxBeneficial.isSelected());
		eff.setHidden(_chckbxHidden.isSelected());
		eff.setEffectID(getOrigID());
		eff.setRoundTill(getOrigRound());
	}

	/**
	 * Adds the given effect to the preset effects list.
	 * 
	 * @param eff
	 *            the effect
	 */
	private void presetEffectAdd(EffectBase eff) {
		if (eff.isValid() && !getPresetEffects().contains(eff.getEffectBaseID())) {
			getPresetEffects().put(eff.getEffectBaseID(), eff);
		}
	}

	/**
	 * Loads default values into the form fields.
	 */
	private void setDefaults() {
		if (getFight().getCurrentFighterHandle().isEmpty()) {
			_comboBoxSource.setSelectedItem(getFight().getSelectedFighterHandle());
		} else {
			_comboBoxSource.setSelectedItem(getFight().getCurrentFighterHandle());
		}
		_comboBoxTarget.setSelectedItem(getFight().getSelectedFighterHandle());
		_comboBoxDuration.setSelectedIndex(0);
		_chckbxBeneficial.setSelected(false);
		_chckbxHidden.setSelected(false);
	}

	/**
	 * Sets the reference encounter.
	 * 
	 * @param fight
	 *            the encounter
	 */
	private void setFight(Encounter fight) {
		_fight = fight;
	}

	/**
	 * Sets the effect to be modified.
	 * 
	 * @param modEffect
	 *            the effect
	 */
	private void setModEffect(Effect modEffect) {
		_modEffect = modEffect;
	}

	/**
	 * Sets the original effect ID.
	 * 
	 * @param id
	 *            the effect ID
	 */
	private void setOrigID(Integer id) {
		_origID = id;
	}

	/**
	 * Sets the original round of combat.
	 * 
	 * @param origRound
	 *            the round
	 */
	private void setOrigRound(Integer origRound) {
		_origRound = origRound;
	}

	/**
	 * Create the dialog.
	 */
	private void initComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		_panelEffect = new JPanel();
		_panelEffect.setBorder(new TitledBorder(null, "Effect", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelEffect = new GridBagConstraints();
		gbc_panelEffect.insets = new Insets(0, 0, 5, 0);
		gbc_panelEffect.fill = GridBagConstraints.BOTH;
		gbc_panelEffect.gridx = 0;
		gbc_panelEffect.gridy = 0;
		getContentPane().add(_panelEffect, gbc_panelEffect);
		GridBagLayout gbl_panelEffect = new GridBagLayout();
		gbl_panelEffect.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelEffect.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		_panelEffect.setLayout(gbl_panelEffect);

		_lblName = new JLabel("Name");
		_lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc__lblName = new GridBagConstraints();
		gbc__lblName.anchor = GridBagConstraints.EAST;
		gbc__lblName.insets = new Insets(0, 0, 5, 5);
		gbc__lblName.gridx = 0;
		gbc__lblName.gridy = 0;
		_panelEffect.add(_lblName, gbc__lblName);

		_comboBoxName = new JComboBox();
		GridBagConstraints gbc_comboBoxName = new GridBagConstraints();
		gbc_comboBoxName.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxName.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxName.gridx = 1;
		gbc_comboBoxName.gridy = 0;
		_panelEffect.add(_comboBoxName, gbc_comboBoxName);

		_lblDuration = new JLabel("Duration");
		_lblDuration.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc__lblDuration = new GridBagConstraints();
		gbc__lblDuration.anchor = GridBagConstraints.EAST;
		gbc__lblDuration.insets = new Insets(0, 0, 5, 5);
		gbc__lblDuration.gridx = 0;
		gbc__lblDuration.gridy = 1;
		_panelEffect.add(_lblDuration, gbc__lblDuration);

		_comboBoxDuration = new JComboBox();
		GridBagConstraints gbc_comboBoxDuration = new GridBagConstraints();
		gbc_comboBoxDuration.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDuration.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDuration.gridx = 1;
		gbc_comboBoxDuration.gridy = 1;
		_panelEffect.add(_comboBoxDuration, gbc_comboBoxDuration);

		_chckbxHidden = new JCheckBox("Hidden");
		GridBagConstraints gbc__chckbxHidden = new GridBagConstraints();
		gbc__chckbxHidden.fill = GridBagConstraints.HORIZONTAL;
		gbc__chckbxHidden.insets = new Insets(0, 0, 5, 0);
		gbc__chckbxHidden.gridx = 2;
		gbc__chckbxHidden.gridy = 1;
		_panelEffect.add(_chckbxHidden, gbc__chckbxHidden);

		_lblSource = new JLabel("Source");
		_lblSource.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc__lblSource = new GridBagConstraints();
		gbc__lblSource.anchor = GridBagConstraints.EAST;
		gbc__lblSource.insets = new Insets(0, 0, 5, 5);
		gbc__lblSource.gridx = 0;
		gbc__lblSource.gridy = 2;
		_panelEffect.add(_lblSource, gbc__lblSource);

		_comboBoxSource = new JComboBox();
		_comboBoxSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jComboBoxSourceActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_comboBoxSource = new GridBagConstraints();
		gbc_comboBoxSource.gridwidth = 2;
		gbc_comboBoxSource.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSource.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSource.gridx = 1;
		gbc_comboBoxSource.gridy = 2;
		_panelEffect.add(_comboBoxSource, gbc_comboBoxSource);

		_lblTarget = new JLabel("Target");
		_lblTarget.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc__lblTarget = new GridBagConstraints();
		gbc__lblTarget.anchor = GridBagConstraints.EAST;
		gbc__lblTarget.insets = new Insets(0, 0, 5, 5);
		gbc__lblTarget.gridx = 0;
		gbc__lblTarget.gridy = 3;
		_panelEffect.add(_lblTarget, gbc__lblTarget);

		_comboBoxTarget = new JComboBox();
		_comboBoxTarget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jComboBoxTargetActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_comboBoxTarget = new GridBagConstraints();
		gbc_comboBoxTarget.gridwidth = 2;
		gbc_comboBoxTarget.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTarget.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTarget.gridx = 1;
		gbc_comboBoxTarget.gridy = 3;
		_panelEffect.add(_comboBoxTarget, gbc_comboBoxTarget);

		_chckbxBeneficial = new JCheckBox("Beneficial");
		GridBagConstraints gbc__chckbxBeneficial = new GridBagConstraints();
		gbc__chckbxBeneficial.fill = GridBagConstraints.HORIZONTAL;
		gbc__chckbxBeneficial.insets = new Insets(0, 0, 5, 0);
		gbc__chckbxBeneficial.gridx = 2;
		gbc__chckbxBeneficial.gridy = 0;
		_panelEffect.add(_chckbxBeneficial, gbc__chckbxBeneficial);

		_panelButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) _panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.gridx = 0;
		gbc_panelButtons.gridy = 1;
		getContentPane().add(_panelButtons, gbc_panelButtons);

		_buttonOK = new JButton("OK");
		_buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonOKActionActionPerformed(e);
			}
		});
		_panelButtons.add(_buttonOK);

		_buttonCancel = new JButton("Cancel");
		_buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCancelActionActionPerformed(e);
			}
		});
		_panelButtons.add(_buttonCancel);

		_panelPresets = new JPanel();
		GridBagConstraints gbc_panelPresets = new GridBagConstraints();
		gbc_panelPresets.fill = GridBagConstraints.BOTH;
		gbc_panelPresets.gridx = 0;
		gbc_panelPresets.gridy = 2;
		getContentPane().add(_panelPresets, gbc_panelPresets);
		_panelPresets.setLayout(new BorderLayout(0, 0));

		_listPresets = new JList();
		_listPresets.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jListPresetsMouseMouseClicked(e);
			}
		});
		_listPresets.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jListPresetsListSelectionValueChanged(e);
			}
		});
		_panelPresets.add(_listPresets, BorderLayout.CENTER);

		pack();
	}
}
