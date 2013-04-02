package cm.view;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.SortedSet;

import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cm.model.EffectBase;
import cm.model.Power;
import cm.model.Settings;
import cm.model.Stats;
import cm.model.EffectBase.Duration;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.InputSource;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import javax.swing.event.ListSelectionListener;

/**
 * Displays a window allowing for creation/editing of {@link Stats}.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class Statblock extends JDialog {
	/**
	 * generated
	 */
	private static final long serialVersionUID = -7828665815825335204L;
	private JTextField _textFieldName;
	private JTextField _textFieldTypeKeywords;
	private JFormattedTextField _textFieldXP;
	private JTextField _textFieldSenses;
	private JTextField _textFieldImmune;
	private JFormattedTextField _formattedTextFieldSaveBonus;
	private JTextField _textFieldResist;
	private JTextField _textFieldVulnerable;
	private JTextField _textFieldRegen;
	private JTextField _textFieldSpeed;
	private JTextField _textFieldAlignment;
	private JTextField _textFieldSkills;
	private JTextField _textFieldFeats;
	private JTextField _textFieldLanguages;
	private JTextField _textFieldEquipment;
	private JTextField _textFieldSource;
	private JTextField _textFieldPowerName;
	private JTextField _textFieldPowerAction;
	private JTextField _textFieldPowerKeywords;
	private JTextField _textFieldPowerURL;
	private JComboBox _comboBoxEffect;
	private JComboBox _comboBoxDuration;
	private JCheckBox _chckbxBeneficial;
	private JCheckBox _chckbxHidden;
	private SortedSet<EffectBase> _presetEffects;
	private Stats _stat;
	private Boolean _powerChanged;
	private JList _listPowers;
	private JList _listEffects;
	private JCheckBox _chckbxPc;
	private JComboBox _comboBoxRole;
	private JComboBox _comboBoxRole2;
	private JCheckBox _chckbxLeader;
	private JComboBox _comboBoxPowerIcon;
	private JCheckBox _chckbxPowerAura;
	private JFormattedTextField _formattedTextFieldPowerAuraSize;
	private JTextArea _textAreaPowerDescription;
	private JButton _btnEffectEdit;
	private JButton _btnEffectDelete;
	private JButton _btnPowerDelete;
	private JButton _btnPowerUp;
	private JButton _btnPowerDown;
	private JSpinner _spinnerLevel;
	private JFormattedTextField _formattedTextFieldInit;
	private JFormattedTextField _formattedTextFieldActionPts;
	private JFormattedTextField _formattedTextFieldPowerPts;
	private JFormattedTextField _formattedTextFieldAC;
	private JFormattedTextField _formattedTextFieldFort;
	private JFormattedTextField _formattedTextFieldRef;
	private JFormattedTextField _formattedTextFieldWill;
	private JFormattedTextField _formattedTextFieldStr;
	private JFormattedTextField _formattedTextFieldDex;
	private JFormattedTextField _formattedTextFieldWis;
	private JFormattedTextField _formattedTextFieldCon;
	private JFormattedTextField _formattedTextFieldInt;
	private JFormattedTextField _formattedTextFieldCha;
	private JFormattedTextField _formattedTextFieldMax;
	private JFormattedTextField _formattedTextFieldSurges;
	private JTextPane _textPaneNotes;

	/**
	 * Create the dialog.
	 */
	public Statblock() {
		setTitle("Statblock Viewer");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new EmptyBorder(5, 5, 0, 5));
		GridBagConstraints gbc_panelLeft = new GridBagConstraints();
		gbc_panelLeft.insets = new Insets(0, 0, 0, 5);
		gbc_panelLeft.fill = GridBagConstraints.BOTH;
		gbc_panelLeft.gridx = 0;
		gbc_panelLeft.gridy = 0;
		getContentPane().add(panelLeft, gbc_panelLeft);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelLeft.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelLeft.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelLeft.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panelLeft.setLayout(gbl_panelLeft);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panelLeft.add(lblName, gbc_lblName);

		_textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 3;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		panelLeft.add(_textFieldName, gbc_textFieldName);
		_textFieldName.setColumns(10);

		JPanel panelDescription = new JPanel();
		panelDescription.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDescription = new GridBagConstraints();
		gbc_panelDescription.insets = new Insets(0, 0, 5, 0);
		gbc_panelDescription.gridwidth = 4;
		gbc_panelDescription.fill = GridBagConstraints.BOTH;
		gbc_panelDescription.gridx = 0;
		gbc_panelDescription.gridy = 1;
		panelLeft.add(panelDescription, gbc_panelDescription);
		GridBagLayout gbl_panelDescription = new GridBagLayout();
		gbl_panelDescription.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDescription.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelDescription.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDescription.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelDescription.setLayout(gbl_panelDescription);

		JLabel lblLevel = new JLabel("Level");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.anchor = GridBagConstraints.EAST;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 0;
		panelDescription.add(lblLevel, gbc_lblLevel);

		_spinnerLevel = new JSpinner();
		GridBagConstraints gbc__spinnerLevel = new GridBagConstraints();
		gbc__spinnerLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc__spinnerLevel.insets = new Insets(0, 0, 5, 5);
		gbc__spinnerLevel.gridx = 1;
		gbc__spinnerLevel.gridy = 0;
		panelDescription.add(_spinnerLevel, gbc__spinnerLevel);

		JLabel lblTypekeywords = new JLabel("Type/Keywords");
		GridBagConstraints gbc_lblTypekeywords = new GridBagConstraints();
		gbc_lblTypekeywords.anchor = GridBagConstraints.EAST;
		gbc_lblTypekeywords.gridwidth = 2;
		gbc_lblTypekeywords.insets = new Insets(0, 0, 5, 5);
		gbc_lblTypekeywords.gridx = 2;
		gbc_lblTypekeywords.gridy = 0;
		panelDescription.add(lblTypekeywords, gbc_lblTypekeywords);

		_textFieldTypeKeywords = new JTextField();
		GridBagConstraints gbc_textFieldTypeKeywords = new GridBagConstraints();
		gbc_textFieldTypeKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTypeKeywords.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTypeKeywords.gridx = 4;
		gbc_textFieldTypeKeywords.gridy = 0;
		panelDescription.add(_textFieldTypeKeywords, gbc_textFieldTypeKeywords);
		_textFieldTypeKeywords.setColumns(10);

		JLabel lblRole = new JLabel("Role");
		GridBagConstraints gbc_lblRole = new GridBagConstraints();
		gbc_lblRole.anchor = GridBagConstraints.EAST;
		gbc_lblRole.insets = new Insets(0, 0, 0, 5);
		gbc_lblRole.gridx = 0;
		gbc_lblRole.gridy = 1;
		panelDescription.add(lblRole, gbc_lblRole);

		_comboBoxRole2 = new JComboBox();
		GridBagConstraints gbc_comboBoxRole2 = new GridBagConstraints();
		gbc_comboBoxRole2.gridwidth = 2;
		gbc_comboBoxRole2.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxRole2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRole2.gridx = 1;
		gbc_comboBoxRole2.gridy = 1;
		panelDescription.add(_comboBoxRole2, gbc_comboBoxRole2);

		_comboBoxRole = new JComboBox();
		_comboBoxRole.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				jComboBoxRoleItemItemStateChanged(e);
			}
		});
		GridBagConstraints gbc__comboBoxRole = new GridBagConstraints();
		gbc__comboBoxRole.gridwidth = 2;
		gbc__comboBoxRole.insets = new Insets(0, 0, 0, 5);
		gbc__comboBoxRole.fill = GridBagConstraints.HORIZONTAL;
		gbc__comboBoxRole.gridx = 3;
		gbc__comboBoxRole.gridy = 1;
		panelDescription.add(_comboBoxRole, gbc__comboBoxRole);

		_chckbxLeader = new JCheckBox("Leader");
		GridBagConstraints gbc__chckbxLeader = new GridBagConstraints();
		gbc__chckbxLeader.anchor = GridBagConstraints.WEST;
		gbc__chckbxLeader.insets = new Insets(0, 0, 5, 0);
		gbc__chckbxLeader.gridx = 5;
		gbc__chckbxLeader.gridy = 0;
		panelDescription.add(_chckbxLeader, gbc__chckbxLeader);

		_chckbxPc = new JCheckBox("PC");
		_chckbxPc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				jCheckBoxPCChangeStateChanged(e);
			}
		});
		GridBagConstraints gbc__chckbxPc = new GridBagConstraints();
		gbc__chckbxPc.anchor = GridBagConstraints.WEST;
		gbc__chckbxPc.gridx = 5;
		gbc__chckbxPc.gridy = 1;
		panelDescription.add(_chckbxPc, gbc__chckbxPc);

		JPanel panelDefenses = new JPanel();
		panelDefenses.setBorder(new TitledBorder(null, "Defenses", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDefenses = new GridBagConstraints();
		gbc_panelDefenses.gridwidth = 2;
		gbc_panelDefenses.gridheight = 2;
		gbc_panelDefenses.insets = new Insets(0, 0, 5, 5);
		gbc_panelDefenses.fill = GridBagConstraints.BOTH;
		gbc_panelDefenses.gridx = 0;
		gbc_panelDefenses.gridy = 2;
		panelLeft.add(panelDefenses, gbc_panelDefenses);
		GridBagLayout gbl_panelDefenses = new GridBagLayout();
		gbl_panelDefenses.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panelDefenses.rowWeights = new double[] { Double.MIN_VALUE, 0.0, 0.0, 0.0 };
		panelDefenses.setLayout(gbl_panelDefenses);

		JLabel lblAc = new JLabel("AC");
		GridBagConstraints gbc_lblAc = new GridBagConstraints();
		gbc_lblAc.anchor = GridBagConstraints.EAST;
		gbc_lblAc.insets = new Insets(0, 0, 5, 5);
		gbc_lblAc.gridx = 0;
		gbc_lblAc.gridy = 0;
		panelDefenses.add(lblAc, gbc_lblAc);

		_formattedTextFieldAC = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldAC.setColumns(3);
		_formattedTextFieldAC.setText("0");
		GridBagConstraints gbc__formattedTextFieldAC = new GridBagConstraints();
		gbc__formattedTextFieldAC.insets = new Insets(0, 0, 5, 0);
		gbc__formattedTextFieldAC.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldAC.gridx = 1;
		gbc__formattedTextFieldAC.gridy = 0;
		panelDefenses.add(_formattedTextFieldAC, gbc__formattedTextFieldAC);

		JLabel lblFortitude = new JLabel("Fortitude");
		GridBagConstraints gbc_lblFortitude = new GridBagConstraints();
		gbc_lblFortitude.anchor = GridBagConstraints.EAST;
		gbc_lblFortitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblFortitude.gridx = 0;
		gbc_lblFortitude.gridy = 1;
		panelDefenses.add(lblFortitude, gbc_lblFortitude);

		_formattedTextFieldFort = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldFort.setColumns(3);
		_formattedTextFieldFort.setText("0");
		GridBagConstraints gbc__formattedTextFieldFort = new GridBagConstraints();
		gbc__formattedTextFieldFort.insets = new Insets(0, 0, 5, 0);
		gbc__formattedTextFieldFort.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldFort.gridx = 1;
		gbc__formattedTextFieldFort.gridy = 1;
		panelDefenses.add(_formattedTextFieldFort, gbc__formattedTextFieldFort);

		JLabel lblReflex = new JLabel("Reflex");
		GridBagConstraints gbc_lblReflex = new GridBagConstraints();
		gbc_lblReflex.anchor = GridBagConstraints.EAST;
		gbc_lblReflex.insets = new Insets(0, 0, 5, 5);
		gbc_lblReflex.gridx = 0;
		gbc_lblReflex.gridy = 2;
		panelDefenses.add(lblReflex, gbc_lblReflex);

		_formattedTextFieldRef = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldRef.setColumns(3);
		_formattedTextFieldRef.setText("0");
		GridBagConstraints gbc__formattedTextFieldRef = new GridBagConstraints();
		gbc__formattedTextFieldRef.insets = new Insets(0, 0, 5, 0);
		gbc__formattedTextFieldRef.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldRef.gridx = 1;
		gbc__formattedTextFieldRef.gridy = 2;
		panelDefenses.add(_formattedTextFieldRef, gbc__formattedTextFieldRef);

		_formattedTextFieldWill = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldWill.setColumns(3);
		_formattedTextFieldWill.setText("0");
		GridBagConstraints gbc__formattedTextFieldWill = new GridBagConstraints();
		gbc__formattedTextFieldWill.insets = new Insets(0, 0, 5, 0);
		gbc__formattedTextFieldWill.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldWill.gridx = 1;
		gbc__formattedTextFieldWill.gridy = 3;
		panelDefenses.add(_formattedTextFieldWill, gbc__formattedTextFieldWill);

		JLabel lblWill = new JLabel("Will");
		GridBagConstraints gbc_lblWill = new GridBagConstraints();
		gbc_lblWill.anchor = GridBagConstraints.EAST;
		gbc_lblWill.insets = new Insets(0, 0, 0, 5);
		gbc_lblWill.gridx = 0;
		gbc_lblWill.gridy = 3;
		panelDefenses.add(lblWill, gbc_lblWill);

		JPanel panelAttributes = new JPanel();
		panelAttributes.setBorder(new TitledBorder(null, "Attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelAttributes = new GridBagConstraints();
		gbc_panelAttributes.gridheight = 2;
		gbc_panelAttributes.insets = new Insets(0, 0, 5, 5);
		gbc_panelAttributes.fill = GridBagConstraints.BOTH;
		gbc_panelAttributes.gridx = 2;
		gbc_panelAttributes.gridy = 2;
		panelLeft.add(panelAttributes, gbc_panelAttributes);
		GridBagLayout gbl_panelAttributes = new GridBagLayout();
		gbl_panelAttributes.columnWidths = new int[] { 19, 0, 0, 0, 0 };
		gbl_panelAttributes.rowHeights = new int[] { 14, 0, 0, 0 };
		gbl_panelAttributes.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelAttributes.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelAttributes.setLayout(gbl_panelAttributes);

		JLabel lblStr = new JLabel("STR");
		GridBagConstraints gbc_lblStr = new GridBagConstraints();
		gbc_lblStr.anchor = GridBagConstraints.EAST;
		gbc_lblStr.insets = new Insets(0, 0, 5, 5);
		gbc_lblStr.gridx = 0;
		gbc_lblStr.gridy = 0;
		panelAttributes.add(lblStr, gbc_lblStr);

		_formattedTextFieldStr = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldStr.setColumns(3);
		_formattedTextFieldStr.setText("0");
		GridBagConstraints gbc__formattedTextFieldStr = new GridBagConstraints();
		gbc__formattedTextFieldStr.insets = new Insets(0, 0, 5, 5);
		gbc__formattedTextFieldStr.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldStr.gridx = 1;
		gbc__formattedTextFieldStr.gridy = 0;
		panelAttributes.add(_formattedTextFieldStr, gbc__formattedTextFieldStr);

		JLabel lblCon = new JLabel("CON");
		GridBagConstraints gbc_lblCon = new GridBagConstraints();
		gbc_lblCon.anchor = GridBagConstraints.EAST;
		gbc_lblCon.insets = new Insets(0, 0, 5, 5);
		gbc_lblCon.gridx = 2;
		gbc_lblCon.gridy = 0;
		panelAttributes.add(lblCon, gbc_lblCon);

		_formattedTextFieldCon = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldCon.setColumns(3);
		_formattedTextFieldCon.setText("0");
		GridBagConstraints gbc__formattedTextFieldCon = new GridBagConstraints();
		gbc__formattedTextFieldCon.insets = new Insets(0, 0, 5, 0);
		gbc__formattedTextFieldCon.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldCon.gridx = 3;
		gbc__formattedTextFieldCon.gridy = 0;
		panelAttributes.add(_formattedTextFieldCon, gbc__formattedTextFieldCon);

		JLabel lblDex = new JLabel("DEX");
		GridBagConstraints gbc_lblDex = new GridBagConstraints();
		gbc_lblDex.anchor = GridBagConstraints.EAST;
		gbc_lblDex.insets = new Insets(0, 0, 5, 5);
		gbc_lblDex.gridx = 0;
		gbc_lblDex.gridy = 1;
		panelAttributes.add(lblDex, gbc_lblDex);

		_formattedTextFieldDex = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldDex.setColumns(3);
		_formattedTextFieldDex.setText("0");
		GridBagConstraints gbc_formattedTextFieldDex = new GridBagConstraints();
		gbc_formattedTextFieldDex.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextFieldDex.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldDex.gridx = 1;
		gbc_formattedTextFieldDex.gridy = 1;
		panelAttributes.add(_formattedTextFieldDex, gbc_formattedTextFieldDex);

		JLabel lblInt = new JLabel("INT");
		GridBagConstraints gbc_lblInt = new GridBagConstraints();
		gbc_lblInt.anchor = GridBagConstraints.EAST;
		gbc_lblInt.insets = new Insets(0, 0, 5, 5);
		gbc_lblInt.gridx = 2;
		gbc_lblInt.gridy = 1;
		panelAttributes.add(lblInt, gbc_lblInt);

		_formattedTextFieldInt = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldInt.setColumns(3);
		_formattedTextFieldInt.setText("0");
		GridBagConstraints gbc_formattedTextFieldInt = new GridBagConstraints();
		gbc_formattedTextFieldInt.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldInt.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldInt.gridx = 3;
		gbc_formattedTextFieldInt.gridy = 1;
		panelAttributes.add(_formattedTextFieldInt, gbc_formattedTextFieldInt);

		JLabel lblWis = new JLabel("WIS");
		GridBagConstraints gbc_lblWis = new GridBagConstraints();
		gbc_lblWis.anchor = GridBagConstraints.EAST;
		gbc_lblWis.insets = new Insets(0, 0, 0, 5);
		gbc_lblWis.gridx = 0;
		gbc_lblWis.gridy = 2;
		panelAttributes.add(lblWis, gbc_lblWis);

		_formattedTextFieldWis = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldWis.setColumns(3);
		_formattedTextFieldWis.setText("0");
		GridBagConstraints gbc_formattedTextFieldWis = new GridBagConstraints();
		gbc_formattedTextFieldWis.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextFieldWis.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldWis.gridx = 1;
		gbc_formattedTextFieldWis.gridy = 2;
		panelAttributes.add(_formattedTextFieldWis, gbc_formattedTextFieldWis);

		JLabel lblCha = new JLabel("CHA");
		GridBagConstraints gbc_lblCha = new GridBagConstraints();
		gbc_lblCha.insets = new Insets(0, 0, 0, 5);
		gbc_lblCha.anchor = GridBagConstraints.EAST;
		gbc_lblCha.gridx = 2;
		gbc_lblCha.gridy = 2;
		panelAttributes.add(lblCha, gbc_lblCha);

		_formattedTextFieldCha = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldCha.setColumns(3);
		_formattedTextFieldCha.setText("0");
		GridBagConstraints gbc_formattedTextFieldCha = new GridBagConstraints();
		gbc_formattedTextFieldCha.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldCha.gridx = 3;
		gbc_formattedTextFieldCha.gridy = 2;
		panelAttributes.add(_formattedTextFieldCha, gbc_formattedTextFieldCha);

		JPanel panelXPValue = new JPanel();
		panelXPValue.setBorder(new TitledBorder(null, "XP Value", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelXPValue = new GridBagConstraints();
		gbc_panelXPValue.insets = new Insets(0, 0, 5, 0);
		gbc_panelXPValue.fill = GridBagConstraints.BOTH;
		gbc_panelXPValue.gridx = 3;
		gbc_panelXPValue.gridy = 2;
		panelLeft.add(panelXPValue, gbc_panelXPValue);
		panelXPValue.setLayout(new BorderLayout(0, 0));

		_textFieldXP = new JFormattedTextField(NumberFormat.getInstance());
		_textFieldXP.setText("0");
		panelXPValue.add(_textFieldXP);
		_textFieldXP.setColumns(10);

		JPanel panelHitPoints = new JPanel();
		panelHitPoints.setBorder(new TitledBorder(null, "Hit Points", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelHitPoints = new GridBagConstraints();
		gbc_panelHitPoints.insets = new Insets(0, 0, 5, 0);
		gbc_panelHitPoints.fill = GridBagConstraints.BOTH;
		gbc_panelHitPoints.gridx = 3;
		gbc_panelHitPoints.gridy = 3;
		panelLeft.add(panelHitPoints, gbc_panelHitPoints);
		GridBagLayout gbl_panelHitPoints = new GridBagLayout();
		gbl_panelHitPoints.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelHitPoints.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelHitPoints.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelHitPoints.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelHitPoints.setLayout(gbl_panelHitPoints);

		JLabel lblMax = new JLabel("Max");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.anchor = GridBagConstraints.EAST;
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 0;
		gbc_lblMax.gridy = 0;
		panelHitPoints.add(lblMax, gbc_lblMax);

		_formattedTextFieldMax = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldMax.setText("0");
		GridBagConstraints gbc__formattedTextFieldMax = new GridBagConstraints();
		gbc__formattedTextFieldMax.insets = new Insets(0, 0, 5, 0);
		gbc__formattedTextFieldMax.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldMax.gridx = 1;
		gbc__formattedTextFieldMax.gridy = 0;
		panelHitPoints.add(_formattedTextFieldMax, gbc__formattedTextFieldMax);

		JLabel lblSurges = new JLabel("Surges");
		GridBagConstraints gbc_lblSurges = new GridBagConstraints();
		gbc_lblSurges.insets = new Insets(0, 0, 0, 5);
		gbc_lblSurges.anchor = GridBagConstraints.EAST;
		gbc_lblSurges.gridx = 0;
		gbc_lblSurges.gridy = 1;
		panelHitPoints.add(lblSurges, gbc_lblSurges);

		_formattedTextFieldSurges = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldSurges.setText("0");
		GridBagConstraints gbc__formattedTextFieldSurges = new GridBagConstraints();
		gbc__formattedTextFieldSurges.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldSurges.gridx = 1;
		gbc__formattedTextFieldSurges.gridy = 1;
		panelHitPoints.add(_formattedTextFieldSurges, gbc__formattedTextFieldSurges);

		JTabbedPane tabbedPaneMisc = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPaneMisc = new GridBagConstraints();
		gbc_tabbedPaneMisc.gridwidth = 4;
		gbc_tabbedPaneMisc.fill = GridBagConstraints.BOTH;
		gbc_tabbedPaneMisc.gridx = 0;
		gbc_tabbedPaneMisc.gridy = 4;
		panelLeft.add(tabbedPaneMisc, gbc_tabbedPaneMisc);

		JPanel panelTraits = new JPanel();
		tabbedPaneMisc.addTab("Traits", null, panelTraits, null);
		GridBagLayout gbl_panelTraits = new GridBagLayout();
		gbl_panelTraits.columnWidths = new int[] { 155, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelTraits.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelTraits.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelTraits.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelTraits.setLayout(gbl_panelTraits);

		JLabel lblSenses = new JLabel("Senses");
		GridBagConstraints gbc_lblSenses = new GridBagConstraints();
		gbc_lblSenses.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenses.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSenses.gridx = 0;
		gbc_lblSenses.gridy = 0;
		panelTraits.add(lblSenses, gbc_lblSenses);

		_textFieldSenses = new JTextField();
		GridBagConstraints gbc_textFieldSenses = new GridBagConstraints();
		gbc_textFieldSenses.gridwidth = 7;
		gbc_textFieldSenses.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSenses.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSenses.gridx = 1;
		gbc_textFieldSenses.gridy = 0;
		panelTraits.add(_textFieldSenses, gbc_textFieldSenses);
		_textFieldSenses.setColumns(10);

		_textFieldImmune = new JTextField();
		GridBagConstraints gbc_textFieldImmune = new GridBagConstraints();
		gbc_textFieldImmune.gridwidth = 7;
		gbc_textFieldImmune.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldImmune.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldImmune.gridx = 1;
		gbc_textFieldImmune.gridy = 1;
		panelTraits.add(_textFieldImmune, gbc_textFieldImmune);
		_textFieldImmune.setColumns(10);

		_textFieldResist = new JTextField();
		GridBagConstraints gbc_textFieldResist = new GridBagConstraints();
		gbc_textFieldResist.gridwidth = 7;
		gbc_textFieldResist.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldResist.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldResist.gridx = 1;
		gbc_textFieldResist.gridy = 2;
		panelTraits.add(_textFieldResist, gbc_textFieldResist);
		_textFieldResist.setColumns(10);

		_textFieldVulnerable = new JTextField();
		GridBagConstraints gbc_textFieldVulnerable = new GridBagConstraints();
		gbc_textFieldVulnerable.gridwidth = 7;
		gbc_textFieldVulnerable.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVulnerable.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVulnerable.gridx = 1;
		gbc_textFieldVulnerable.gridy = 3;
		panelTraits.add(_textFieldVulnerable, gbc_textFieldVulnerable);
		_textFieldVulnerable.setColumns(10);

		_textFieldRegen = new JTextField();
		GridBagConstraints gbc_textFieldRegen = new GridBagConstraints();
		gbc_textFieldRegen.gridwidth = 7;
		gbc_textFieldRegen.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRegen.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRegen.gridx = 1;
		gbc_textFieldRegen.gridy = 4;
		panelTraits.add(_textFieldRegen, gbc_textFieldRegen);
		_textFieldRegen.setColumns(10);

		_textFieldSpeed = new JTextField();
		GridBagConstraints gbc_textFieldSpeed = new GridBagConstraints();
		gbc_textFieldSpeed.gridwidth = 7;
		gbc_textFieldSpeed.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSpeed.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSpeed.gridx = 1;
		gbc_textFieldSpeed.gridy = 5;
		panelTraits.add(_textFieldSpeed, gbc_textFieldSpeed);
		_textFieldSpeed.setColumns(10);

		_formattedTextFieldInit = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldInit.setText("0");
		_formattedTextFieldInit.setColumns(3);
		GridBagConstraints gbc__formattedTextFieldInit = new GridBagConstraints();
		gbc__formattedTextFieldInit.insets = new Insets(0, 0, 5, 5);
		gbc__formattedTextFieldInit.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldInit.gridx = 1;
		gbc__formattedTextFieldInit.gridy = 6;
		panelTraits.add(_formattedTextFieldInit, gbc__formattedTextFieldInit);

		_formattedTextFieldActionPts = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldActionPts.setText("0");
		_formattedTextFieldActionPts.setColumns(3);
		GridBagConstraints gbc__formattedTextFieldActionPts = new GridBagConstraints();
		gbc__formattedTextFieldActionPts.insets = new Insets(0, 0, 5, 5);
		gbc__formattedTextFieldActionPts.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldActionPts.gridx = 3;
		gbc__formattedTextFieldActionPts.gridy = 6;
		panelTraits.add(_formattedTextFieldActionPts, gbc__formattedTextFieldActionPts);

		_formattedTextFieldPowerPts = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldPowerPts.setText("0");
		_formattedTextFieldPowerPts.setColumns(3);
		GridBagConstraints gbc__formattedTextFieldPowerPts = new GridBagConstraints();
		gbc__formattedTextFieldPowerPts.insets = new Insets(0, 0, 5, 5);
		gbc__formattedTextFieldPowerPts.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldPowerPts.gridx = 5;
		gbc__formattedTextFieldPowerPts.gridy = 6;
		panelTraits.add(_formattedTextFieldPowerPts, gbc__formattedTextFieldPowerPts);

		_formattedTextFieldSaveBonus = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldSaveBonus.setText("0");
		GridBagConstraints gbc_textFieldSaveBonus = new GridBagConstraints();
		gbc_textFieldSaveBonus.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSaveBonus.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSaveBonus.gridx = 7;
		gbc_textFieldSaveBonus.gridy = 6;
		panelTraits.add(_formattedTextFieldSaveBonus, gbc_textFieldSaveBonus);
		_formattedTextFieldSaveBonus.setColumns(3);

		JLabel lblAlignment = new JLabel("Alignment");
		GridBagConstraints gbc_lblAlignment = new GridBagConstraints();
		gbc_lblAlignment.anchor = GridBagConstraints.EAST;
		gbc_lblAlignment.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlignment.gridx = 0;
		gbc_lblAlignment.gridy = 7;
		panelTraits.add(lblAlignment, gbc_lblAlignment);

		_textFieldAlignment = new JTextField();
		GridBagConstraints gbc_textFieldAlignment = new GridBagConstraints();
		gbc_textFieldAlignment.gridwidth = 7;
		gbc_textFieldAlignment.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAlignment.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAlignment.gridx = 1;
		gbc_textFieldAlignment.gridy = 7;
		panelTraits.add(_textFieldAlignment, gbc_textFieldAlignment);
		_textFieldAlignment.setColumns(10);

		JLabel lblSkills = new JLabel("Skills");
		GridBagConstraints gbc_lblSkills = new GridBagConstraints();
		gbc_lblSkills.anchor = GridBagConstraints.EAST;
		gbc_lblSkills.insets = new Insets(0, 0, 5, 5);
		gbc_lblSkills.gridx = 0;
		gbc_lblSkills.gridy = 8;
		panelTraits.add(lblSkills, gbc_lblSkills);

		_textFieldSkills = new JTextField();
		GridBagConstraints gbc_textFieldSkills = new GridBagConstraints();
		gbc_textFieldSkills.gridwidth = 7;
		gbc_textFieldSkills.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSkills.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSkills.gridx = 1;
		gbc_textFieldSkills.gridy = 8;
		panelTraits.add(_textFieldSkills, gbc_textFieldSkills);
		_textFieldSkills.setColumns(10);

		JLabel lblFeats = new JLabel("Feats");
		GridBagConstraints gbc_lblFeats = new GridBagConstraints();
		gbc_lblFeats.anchor = GridBagConstraints.EAST;
		gbc_lblFeats.insets = new Insets(0, 0, 5, 5);
		gbc_lblFeats.gridx = 0;
		gbc_lblFeats.gridy = 9;
		panelTraits.add(lblFeats, gbc_lblFeats);

		_textFieldFeats = new JTextField();
		GridBagConstraints gbc_textFieldFeats = new GridBagConstraints();
		gbc_textFieldFeats.gridwidth = 7;
		gbc_textFieldFeats.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFeats.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFeats.gridx = 1;
		gbc_textFieldFeats.gridy = 9;
		panelTraits.add(_textFieldFeats, gbc_textFieldFeats);
		_textFieldFeats.setColumns(10);

		JLabel lblLanguages = new JLabel("Languages");
		GridBagConstraints gbc_lblLanguages = new GridBagConstraints();
		gbc_lblLanguages.anchor = GridBagConstraints.EAST;
		gbc_lblLanguages.insets = new Insets(0, 0, 5, 5);
		gbc_lblLanguages.gridx = 0;
		gbc_lblLanguages.gridy = 10;
		panelTraits.add(lblLanguages, gbc_lblLanguages);

		_textFieldLanguages = new JTextField();
		GridBagConstraints gbc_textFieldLanguages = new GridBagConstraints();
		gbc_textFieldLanguages.gridwidth = 7;
		gbc_textFieldLanguages.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLanguages.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLanguages.gridx = 1;
		gbc_textFieldLanguages.gridy = 10;
		panelTraits.add(_textFieldLanguages, gbc_textFieldLanguages);
		_textFieldLanguages.setColumns(10);

		JLabel lblEquipment = new JLabel("Equipment");
		GridBagConstraints gbc_lblEquipment = new GridBagConstraints();
		gbc_lblEquipment.anchor = GridBagConstraints.EAST;
		gbc_lblEquipment.insets = new Insets(0, 0, 5, 5);
		gbc_lblEquipment.gridx = 0;
		gbc_lblEquipment.gridy = 11;
		panelTraits.add(lblEquipment, gbc_lblEquipment);

		_textFieldEquipment = new JTextField();
		GridBagConstraints gbc_textFieldEquipment = new GridBagConstraints();
		gbc_textFieldEquipment.gridwidth = 7;
		gbc_textFieldEquipment.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEquipment.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEquipment.gridx = 1;
		gbc_textFieldEquipment.gridy = 11;
		panelTraits.add(_textFieldEquipment, gbc_textFieldEquipment);
		_textFieldEquipment.setColumns(10);

		JLabel lblSource = new JLabel("Source");
		GridBagConstraints gbc_lblSource = new GridBagConstraints();
		gbc_lblSource.anchor = GridBagConstraints.EAST;
		gbc_lblSource.insets = new Insets(0, 0, 0, 5);
		gbc_lblSource.gridx = 0;
		gbc_lblSource.gridy = 12;
		panelTraits.add(lblSource, gbc_lblSource);

		JLabel lblImmune = new JLabel("Immune");
		GridBagConstraints gbc_lblImmune = new GridBagConstraints();
		gbc_lblImmune.anchor = GridBagConstraints.EAST;
		gbc_lblImmune.insets = new Insets(0, 0, 5, 5);
		gbc_lblImmune.gridx = 0;
		gbc_lblImmune.gridy = 1;
		panelTraits.add(lblImmune, gbc_lblImmune);

		JLabel lblResist = new JLabel("Resist");
		GridBagConstraints gbc_lblResist = new GridBagConstraints();
		gbc_lblResist.anchor = GridBagConstraints.EAST;
		gbc_lblResist.insets = new Insets(0, 0, 5, 5);
		gbc_lblResist.gridx = 0;
		gbc_lblResist.gridy = 2;
		panelTraits.add(lblResist, gbc_lblResist);

		JLabel lblVulnerable = new JLabel("Vulnerable");
		GridBagConstraints gbc_lblVulnerable = new GridBagConstraints();
		gbc_lblVulnerable.anchor = GridBagConstraints.EAST;
		gbc_lblVulnerable.insets = new Insets(0, 0, 5, 5);
		gbc_lblVulnerable.gridx = 0;
		gbc_lblVulnerable.gridy = 3;
		panelTraits.add(lblVulnerable, gbc_lblVulnerable);

		JLabel lblRegen = new JLabel("Regen");
		GridBagConstraints gbc_lblRegen = new GridBagConstraints();
		gbc_lblRegen.anchor = GridBagConstraints.EAST;
		gbc_lblRegen.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegen.gridx = 0;
		gbc_lblRegen.gridy = 4;
		panelTraits.add(lblRegen, gbc_lblRegen);

		JLabel lblSpeed = new JLabel("Speed");
		GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
		gbc_lblSpeed.anchor = GridBagConstraints.EAST;
		gbc_lblSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeed.gridx = 0;
		gbc_lblSpeed.gridy = 5;
		panelTraits.add(lblSpeed, gbc_lblSpeed);

		JLabel lblSaveBonus = new JLabel("Save Bonus");
		GridBagConstraints gbc_lblSaveBonus = new GridBagConstraints();
		gbc_lblSaveBonus.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaveBonus.gridx = 6;
		gbc_lblSaveBonus.gridy = 6;
		panelTraits.add(lblSaveBonus, gbc_lblSaveBonus);

		JLabel lblInitiative = new JLabel("Initiative");
		GridBagConstraints gbc_lblInitiative = new GridBagConstraints();
		gbc_lblInitiative.anchor = GridBagConstraints.EAST;
		gbc_lblInitiative.insets = new Insets(0, 0, 5, 5);
		gbc_lblInitiative.gridx = 0;
		gbc_lblInitiative.gridy = 6;
		panelTraits.add(lblInitiative, gbc_lblInitiative);

		JLabel lblActionPoints = new JLabel("Action Points");
		GridBagConstraints gbc_lblActionPoints = new GridBagConstraints();
		gbc_lblActionPoints.anchor = GridBagConstraints.EAST;
		gbc_lblActionPoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblActionPoints.gridx = 2;
		gbc_lblActionPoints.gridy = 6;
		panelTraits.add(lblActionPoints, gbc_lblActionPoints);

		JLabel lblPowerPoints = new JLabel("Power Points");
		GridBagConstraints gbc_lblPowerPoints = new GridBagConstraints();
		gbc_lblPowerPoints.anchor = GridBagConstraints.EAST;
		gbc_lblPowerPoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblPowerPoints.gridx = 4;
		gbc_lblPowerPoints.gridy = 6;
		panelTraits.add(lblPowerPoints, gbc_lblPowerPoints);

		_textFieldSource = new JTextField();
		GridBagConstraints gbc_textFieldSource = new GridBagConstraints();
		gbc_textFieldSource.gridwidth = 7;
		gbc_textFieldSource.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldSource.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSource.gridx = 1;
		gbc_textFieldSource.gridy = 12;
		panelTraits.add(_textFieldSource, gbc_textFieldSource);
		_textFieldSource.setColumns(10);

		JPanel panelGeneratedEffects = new JPanel();
		panelGeneratedEffects.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPaneMisc.addTab("Generated Effects", null, panelGeneratedEffects, null);
		GridBagLayout gbl_panelGeneratedEffects = new GridBagLayout();
		gbl_panelGeneratedEffects.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelGeneratedEffects.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelGeneratedEffects.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelGeneratedEffects.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelGeneratedEffects.setLayout(gbl_panelGeneratedEffects);

		_listEffects = new JList();
		_listEffects.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jListEffectsListSelectionValueChanged(e);
			}
		});
		_listEffects.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc__listEffects = new GridBagConstraints();
		gbc__listEffects.gridheight = 5;
		gbc__listEffects.gridwidth = 3;
		gbc__listEffects.insets = new Insets(0, 0, 5, 5);
		gbc__listEffects.fill = GridBagConstraints.BOTH;
		gbc__listEffects.gridx = 2;
		gbc__listEffects.gridy = 0;
		panelGeneratedEffects.add(_listEffects, gbc__listEffects);

		_btnEffectEdit = new JButton("Edit");
		_btnEffectEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonEditActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnEffectEdit = new GridBagConstraints();
		gbc_btnEffectEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEffectEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEffectEdit.gridx = 5;
		gbc_btnEffectEdit.gridy = 0;
		panelGeneratedEffects.add(_btnEffectEdit, gbc_btnEffectEdit);

		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonExportActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExport.insets = new Insets(0, 0, 5, 5);
		gbc_btnExport.gridx = 5;
		gbc_btnExport.gridy = 1;
		panelGeneratedEffects.add(btnExport, gbc_btnExport);

		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonImportActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnImport = new GridBagConstraints();
		gbc_btnImport.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnImport.insets = new Insets(0, 0, 5, 5);
		gbc_btnImport.gridx = 5;
		gbc_btnImport.gridy = 2;
		panelGeneratedEffects.add(btnImport, gbc_btnImport);

		_btnEffectDelete = new JButton("Delete");
		_btnEffectDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonDeleteActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnEffectDelete = new GridBagConstraints();
		gbc_btnEffectDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEffectDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnEffectDelete.gridx = 5;
		gbc_btnEffectDelete.gridy = 3;
		panelGeneratedEffects.add(_btnEffectDelete, gbc_btnEffectDelete);

		JLabel lblEffect = new JLabel("Effect");
		GridBagConstraints gbc_lblEffect = new GridBagConstraints();
		gbc_lblEffect.gridwidth = 2;
		gbc_lblEffect.anchor = GridBagConstraints.EAST;
		gbc_lblEffect.insets = new Insets(0, 0, 5, 5);
		gbc_lblEffect.gridx = 0;
		gbc_lblEffect.gridy = 5;
		panelGeneratedEffects.add(lblEffect, gbc_lblEffect);

		_comboBoxEffect = new JComboBox();
		GridBagConstraints gbc__comboBoxEffect = new GridBagConstraints();
		gbc__comboBoxEffect.fill = GridBagConstraints.HORIZONTAL;
		gbc__comboBoxEffect.gridwidth = 3;
		gbc__comboBoxEffect.insets = new Insets(0, 0, 5, 5);
		gbc__comboBoxEffect.gridx = 2;
		gbc__comboBoxEffect.gridy = 5;
		panelGeneratedEffects.add(_comboBoxEffect, gbc__comboBoxEffect);

		JLabel lblDuration = new JLabel("Duration");
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.gridwidth = 2;
		gbc_lblDuration.anchor = GridBagConstraints.EAST;
		gbc_lblDuration.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuration.gridx = 0;
		gbc_lblDuration.gridy = 6;
		panelGeneratedEffects.add(lblDuration, gbc_lblDuration);

		_comboBoxDuration = new JComboBox();
		GridBagConstraints gbc__comboBoxDuration = new GridBagConstraints();
		gbc__comboBoxDuration.fill = GridBagConstraints.HORIZONTAL;
		gbc__comboBoxDuration.gridwidth = 3;
		gbc__comboBoxDuration.insets = new Insets(0, 0, 5, 5);
		gbc__comboBoxDuration.gridx = 2;
		gbc__comboBoxDuration.gridy = 6;
		panelGeneratedEffects.add(_comboBoxDuration, gbc__comboBoxDuration);

		_chckbxBeneficial = new JCheckBox("Beneficial");
		GridBagConstraints gbc__chckbxBeneficial = new GridBagConstraints();
		gbc__chckbxBeneficial.fill = GridBagConstraints.HORIZONTAL;
		gbc__chckbxBeneficial.insets = new Insets(0, 0, 0, 5);
		gbc__chckbxBeneficial.gridx = 2;
		gbc__chckbxBeneficial.gridy = 7;
		panelGeneratedEffects.add(_chckbxBeneficial, gbc__chckbxBeneficial);

		_chckbxHidden = new JCheckBox("Hidden");
		GridBagConstraints gbc__chckbxHidden = new GridBagConstraints();
		gbc__chckbxHidden.anchor = GridBagConstraints.WEST;
		gbc__chckbxHidden.insets = new Insets(0, 0, 0, 5);
		gbc__chckbxHidden.gridx = 3;
		gbc__chckbxHidden.gridy = 7;
		panelGeneratedEffects.add(_chckbxHidden, gbc__chckbxHidden);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAddActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 4;
		gbc_btnAdd.gridy = 7;
		panelGeneratedEffects.add(btnAdd, gbc_btnAdd);

		JPanel panelNotes = new JPanel();
		panelNotes.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneMisc.addTab("Notes", null, panelNotes, null);
		panelNotes.setLayout(new BorderLayout(0, 0));

		_textPaneNotes = new JTextPane();
		panelNotes.add(_textPaneNotes);

		JPanel panelRight = new JPanel();
		panelRight.setBorder(new EmptyBorder(5, 0, 5, 5));
		GridBagConstraints gbc_panelRight = new GridBagConstraints();
		gbc_panelRight.fill = GridBagConstraints.BOTH;
		gbc_panelRight.gridx = 1;
		gbc_panelRight.gridy = 0;
		getContentPane().add(panelRight, gbc_panelRight);
		GridBagLayout gbl_panelRight = new GridBagLayout();
		gbl_panelRight.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelRight.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelRight.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelRight.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panelRight.setLayout(gbl_panelRight);

		JPanel panelPowers = new JPanel();
		panelPowers.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Powers", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 70, 213)));
		GridBagConstraints gbc_panelPowers = new GridBagConstraints();
		gbc_panelPowers.gridwidth = 6;
		gbc_panelPowers.insets = new Insets(0, 0, 5, 0);
		gbc_panelPowers.fill = GridBagConstraints.BOTH;
		gbc_panelPowers.gridx = 0;
		gbc_panelPowers.gridy = 0;
		panelRight.add(panelPowers, gbc_panelPowers);
		GridBagLayout gbl_panelPowers = new GridBagLayout();
		gbl_panelPowers.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelPowers.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelPowers.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelPowers.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelPowers.setLayout(gbl_panelPowers);

		_listPowers = new JList();
		_listPowers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jListPowersListSelectionValueChanged(e);
			}
		});
		_listPowers.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc__listPowers = new GridBagConstraints();
		gbc__listPowers.gridwidth = 5;
		gbc__listPowers.gridheight = 5;
		gbc__listPowers.insets = new Insets(0, 0, 5, 5);
		gbc__listPowers.fill = GridBagConstraints.BOTH;
		gbc__listPowers.gridx = 0;
		gbc__listPowers.gridy = 0;
		panelPowers.add(_listPowers, gbc__listPowers);

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPowerNewActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNew.insets = new Insets(0, 0, 5, 0);
		gbc_btnNew.gridx = 5;
		gbc_btnNew.gridy = 0;
		panelPowers.add(btnNew, gbc_btnNew);

		_btnPowerDelete = new JButton("Delete");
		_btnPowerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPowerDeleteActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc__btnDelete = new GridBagConstraints();
		gbc__btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc__btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc__btnDelete.gridx = 5;
		gbc__btnDelete.gridy = 1;
		panelPowers.add(_btnPowerDelete, gbc__btnDelete);

		Box verticalBox = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox = new GridBagConstraints();
		gbc_verticalBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_verticalBox.insets = new Insets(0, 0, 5, 0);
		gbc_verticalBox.gridx = 5;
		gbc_verticalBox.gridy = 2;
		panelPowers.add(verticalBox, gbc_verticalBox);

		_btnPowerUp = new JButton("Up");
		_btnPowerUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPowerUpActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc__btnPowerUp = new GridBagConstraints();
		gbc__btnPowerUp.fill = GridBagConstraints.HORIZONTAL;
		gbc__btnPowerUp.insets = new Insets(0, 0, 5, 0);
		gbc__btnPowerUp.gridx = 5;
		gbc__btnPowerUp.gridy = 3;
		panelPowers.add(_btnPowerUp, gbc__btnPowerUp);

		_btnPowerDown = new JButton("Down");
		_btnPowerDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonPowerDownActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc__btnPowerDown = new GridBagConstraints();
		gbc__btnPowerDown.fill = GridBagConstraints.HORIZONTAL;
		gbc__btnPowerDown.insets = new Insets(0, 0, 5, 0);
		gbc__btnPowerDown.gridx = 5;
		gbc__btnPowerDown.gridy = 4;
		panelPowers.add(_btnPowerDown, gbc__btnPowerDown);

		JLabel lblName_1 = new JLabel("Name");
		GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
		gbc_lblName_1.anchor = GridBagConstraints.EAST;
		gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_1.gridx = 0;
		gbc_lblName_1.gridy = 5;
		panelPowers.add(lblName_1, gbc_lblName_1);

		_textFieldPowerName = new JTextField();
		_textFieldPowerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTextFieldPowerNameKeyKeyReleased(e);
			}
		});
		GridBagConstraints gbc_textFieldPowerName = new GridBagConstraints();
		gbc_textFieldPowerName.gridwidth = 5;
		gbc_textFieldPowerName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPowerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPowerName.gridx = 1;
		gbc_textFieldPowerName.gridy = 5;
		panelPowers.add(_textFieldPowerName, gbc_textFieldPowerName);
		_textFieldPowerName.setColumns(10);

		JLabel lblAction = new JLabel("Action");
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.anchor = GridBagConstraints.EAST;
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 0;
		gbc_lblAction.gridy = 6;
		panelPowers.add(lblAction, gbc_lblAction);

		_textFieldPowerAction = new JTextField();
		_textFieldPowerAction.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTextFieldPowerActionKeyKeyReleased(e);
			}
		});
		GridBagConstraints gbc_textFieldPowerAction = new GridBagConstraints();
		gbc_textFieldPowerAction.gridwidth = 5;
		gbc_textFieldPowerAction.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPowerAction.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPowerAction.gridx = 1;
		gbc_textFieldPowerAction.gridy = 6;
		panelPowers.add(_textFieldPowerAction, gbc_textFieldPowerAction);
		_textFieldPowerAction.setColumns(10);

		JLabel lblKeywords = new JLabel("Keywords");
		GridBagConstraints gbc_lblKeywords = new GridBagConstraints();
		gbc_lblKeywords.anchor = GridBagConstraints.EAST;
		gbc_lblKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeywords.gridx = 0;
		gbc_lblKeywords.gridy = 7;
		panelPowers.add(lblKeywords, gbc_lblKeywords);

		_textFieldPowerKeywords = new JTextField();
		_textFieldPowerKeywords.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTextFieldPowerKeywordsKeyKeyReleased(e);
			}
		});
		GridBagConstraints gbc_textFieldPowerKeywords = new GridBagConstraints();
		gbc_textFieldPowerKeywords.gridwidth = 5;
		gbc_textFieldPowerKeywords.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPowerKeywords.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPowerKeywords.gridx = 1;
		gbc_textFieldPowerKeywords.gridy = 7;
		panelPowers.add(_textFieldPowerKeywords, gbc_textFieldPowerKeywords);
		_textFieldPowerKeywords.setColumns(10);

		JLabel lblUrl = new JLabel("URL");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.anchor = GridBagConstraints.EAST;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 0;
		gbc_lblUrl.gridy = 8;
		panelPowers.add(lblUrl, gbc_lblUrl);

		_textFieldPowerURL = new JTextField();
		GridBagConstraints gbc_textFieldPowerURL = new GridBagConstraints();
		gbc_textFieldPowerURL.gridwidth = 5;
		gbc_textFieldPowerURL.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPowerURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPowerURL.gridx = 1;
		gbc_textFieldPowerURL.gridy = 8;
		panelPowers.add(_textFieldPowerURL, gbc_textFieldPowerURL);
		_textFieldPowerURL.setColumns(10);

		JLabel lblIcon = new JLabel("Icon");
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.anchor = GridBagConstraints.EAST;
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 0;
		gbc_lblIcon.gridy = 9;
		panelPowers.add(lblIcon, gbc_lblIcon);

		_comboBoxPowerIcon = new JComboBox();
		_comboBoxPowerIcon.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				jComboBoxPowerIconItemItemStateChanged(e);
			}
		});
		GridBagConstraints gbc__comboBoxIcon = new GridBagConstraints();
		gbc__comboBoxIcon.insets = new Insets(0, 0, 5, 5);
		gbc__comboBoxIcon.fill = GridBagConstraints.HORIZONTAL;
		gbc__comboBoxIcon.gridx = 1;
		gbc__comboBoxIcon.gridy = 9;
		panelPowers.add(_comboBoxPowerIcon, gbc__comboBoxIcon);

		_chckbxPowerAura = new JCheckBox("Aura");
		_chckbxPowerAura.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				jCheckBoxPowerAuraChangeStateChanged(e);
			}
		});
		GridBagConstraints gbc__chckbxAura = new GridBagConstraints();
		gbc__chckbxAura.insets = new Insets(0, 0, 5, 5);
		gbc__chckbxAura.gridx = 2;
		gbc__chckbxAura.gridy = 9;
		panelPowers.add(_chckbxPowerAura, gbc__chckbxAura);

		_formattedTextFieldPowerAuraSize = new JFormattedTextField(NumberFormat.getInstance());
		_formattedTextFieldPowerAuraSize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jFormattedTextFieldAuraSizeKeyKeyReleased(e);
			}
		});
		_formattedTextFieldPowerAuraSize.setColumns(3);
		_formattedTextFieldPowerAuraSize.setText("0");
		GridBagConstraints gbc__formattedTextFieldAura = new GridBagConstraints();
		gbc__formattedTextFieldAura.insets = new Insets(0, 0, 5, 5);
		gbc__formattedTextFieldAura.fill = GridBagConstraints.HORIZONTAL;
		gbc__formattedTextFieldAura.gridx = 3;
		gbc__formattedTextFieldAura.gridy = 9;
		panelPowers.add(_formattedTextFieldPowerAuraSize, gbc__formattedTextFieldAura);

		Box horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalBox.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalBox.gridx = 4;
		gbc_horizontalBox.gridy = 9;
		panelPowers.add(horizontalBox, gbc_horizontalBox);

		JPanel panelPowerDescription = new JPanel();
		panelPowerDescription.setBorder(new TitledBorder(null, "Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelPowerDescription = new GridBagConstraints();
		gbc_panelPowerDescription.gridwidth = 6;
		gbc_panelPowerDescription.insets = new Insets(0, 0, 0, 5);
		gbc_panelPowerDescription.fill = GridBagConstraints.BOTH;
		gbc_panelPowerDescription.gridx = 0;
		gbc_panelPowerDescription.gridy = 10;
		panelPowers.add(panelPowerDescription, gbc_panelPowerDescription);
		panelPowerDescription.setLayout(new BorderLayout(0, 0));

		_textAreaPowerDescription = new JTextArea();
		_textAreaPowerDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTextAreaPowerDescriptionKeyKeyReleased(e);
			}
		});
		panelPowerDescription.add(_textAreaPowerDescription);

		JButton btnAtLoad = new JButton("AT Load");
		btnAtLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonATLoadActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAtLoad = new GridBagConstraints();
		gbc_btnAtLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnAtLoad.gridx = 0;
		gbc_btnAtLoad.gridy = 1;
		panelRight.add(btnAtLoad, gbc_btnAtLoad);

		JButton btnCbLoad = new JButton("CB Load");
		btnCbLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCBLoadActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnCbLoad = new GridBagConstraints();
		gbc_btnCbLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnCbLoad.gridx = 1;
		gbc_btnCbLoad.gridy = 1;
		panelRight.add(btnCbLoad, gbc_btnCbLoad);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 2;
		gbc_horizontalStrut.gridy = 1;
		panelRight.add(horizontalStrut, gbc_horizontalStrut);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCancelActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 1;
		panelRight.add(btnCancel, gbc_btnCancel);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonOKActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 5;
		gbc_btnOk.gridy = 1;
		panelRight.add(btnOk, gbc_btnOk);
		pack();
	}

	/**
	 * Creates a Statblock viewer for the given stats and the given library
	 * 
	 * @param stat
	 *            the stats to load
	 * @param c
	 *            the dialog to display relative to
	 */
	public Statblock(Stats stat, JDialog c) {
		this();
		setStat(stat);
		setLocationRelativeTo(c);
	}

	/**
	 * Clears effect fields.
	 */
	private void clearEffect() {
		_comboBoxEffect.setSelectedIndex(0);
		_comboBoxDuration.setSelectedIndex(0);
		_chckbxBeneficial.setSelected(false);
		_chckbxHidden.setSelected(false);
	}

	/**
	 * Returns the preset effects stored in the form.
	 * 
	 * @return the preset effects
	 */
	private SortedSet<EffectBase> getPresetEffects() {
		return _presetEffects;
	}

	/**
	 * Returns the statblock for this form.
	 * 
	 * @return the statblock
	 */
	public Stats getStat() {
		return _stat;
	}

	/**
	 * Returns an indicator of if the power information changed.
	 * 
	 * @return true, if the power information changed
	 */
	private Boolean isPowerChanged() {
		return _powerChanged;
	}

	/**
	 * Returns an indication of the power data being valid.
	 * 
	 * @return true, if the power data is valid
	 */
	private Boolean isPowerDataValid() {
		if (((DefaultListModel) _listPowers.getModel()).size() < 1) {
			powDataDisable();
			powDataClear();
			return false;
		}

		return true;
	}

	/**
	 * Event: Effect Add clicked.
	 * 
	 * @param event
	 */
	private void jButtonAddActionActionPerformed(ActionEvent event) {
		EffectBase eff = new EffectBase((String) _comboBoxEffect.getSelectedItem(), (Duration) _comboBoxDuration.getSelectedItem(),
				_chckbxBeneficial.isSelected());
		if (eff.isValid()) {
			presetEffectAdd(eff);
			clearEffect();
			resetEffectListFromArray();
		} else {
			JOptionPane.showMessageDialog(this,
					"Please assign a name and duration to the effect. It cannot be added until these values are assigned.",
					"Missing Effect Info", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Event: AT Load pressed.
	 * 
	 * @param event
	 */
	private void jButtonATLoadActionActionPerformed(ActionEvent event) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Load from Adventure Tools");
		fc.setMultiSelectionEnabled(false);
		fc.setCurrentDirectory(Settings.getWorkingDirectory());
		fc.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return (f.isDirectory() || f.getName().endsWith(".monster"));
			}

			@Override
			public String getDescription() {
				return "Adventure Tools monster files (*.monster)";
			}
		});

		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			Settings.setWorkingDirectory(fc.getCurrentDirectory());
			File f = fc.getSelectedFile();
			setStat(new Stats());
			if (getStat().loadFromMonsterFile(f.getAbsolutePath())) {
				if (getStat().isValid()) {
					moveClassToFields(getStat());
				}
			}
		}
	}

	/**
	 * Event: Cancel pressed.
	 * 
	 * @param event
	 */
	private void jButtonCancelActionActionPerformed(ActionEvent event) {
		setStat(null);
		setVisible(false);
	}

	/**
	 * Event: CB Load pressed.
	 * 
	 * @param event
	 */
	private void jButtonCBLoadActionActionPerformed(ActionEvent event) {
		setStat(new Stats());

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Load Character File");
		fc.setCurrentDirectory(Settings.getWorkingDirectory());
		fc.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return (f.isDirectory() || f.getName().endsWith(".dnd4e"));
			}

			@Override
			public String getDescription() {
				return "Character files (*.dnd4e)";
			}
		});

		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			Settings.setWorkingDirectory(fc.getCurrentDirectory());
			if (getStat().loadFromCBFile(fc.getSelectedFile().getAbsolutePath())) {
				if (getStat().isValid()) {
					moveClassToFields(getStat());
				}
			}
		}
	}

	/**
	 * Event: Delete pressed.
	 * 
	 * @param event
	 */
	private void jButtonDeleteActionActionPerformed(ActionEvent event) {
		if (_listEffects.getSelectedIndex() >= 0) {
			getPresetEffects().remove(_listEffects.getSelectedValue());
			resetEffectListFromArray();
		}
	}

	/**
	 * Event: Effect edit button pressed.
	 * 
	 * @param event
	 */
	private void jButtonEditActionActionPerformed(ActionEvent event) {
		if (_listEffects.getSelectedValue() != null) {
			EffectBase eff = (EffectBase) _listEffects.getSelectedValue();
			_comboBoxEffect.setSelectedItem(eff.getName());
			_comboBoxDuration.setSelectedItem(eff.getDurationCode());
			getPresetEffects().remove(_listEffects.getSelectedValue());
			resetEffectListFromArray();
		}
	}

	/**
	 * Event: Effect Export pressed.
	 * 
	 * @param event
	 */
	private void jButtonExportActionActionPerformed(ActionEvent event) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Export to XML File");
		fc.setCurrentDirectory(Settings.getWorkingDirectory());
		fc.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return (f.isDirectory() || f.getName().endsWith(".xml"));
			}

			@Override
			public String getDescription() {
				return "XML files (*.xml)";
			}
		});

		if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			Settings.setWorkingDirectory(fc.getCurrentDirectory());
			try {
				File out = fc.getSelectedFile();
				if (out.exists()) {
					out.delete();
				}
				out.createNewFile();
				XMLStreamWriter writer = XMLStreamWriterFactory.create(new FileOutputStream(out));
				writer.writeStartElement("effects");
				for (EffectBase eff : getPresetEffects().toArray(new EffectBase[0])) {
					eff.exportXML(writer);
				}
				writer.writeEndElement();
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Event: Effect Import pressed.
	 * 
	 * @param event
	 */
	private void jButtonImportActionActionPerformed(ActionEvent event) {
		getPresetEffects().clear();

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Load XML File");
		fc.setCurrentDirectory(Settings.getWorkingDirectory());
		fc.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return (f.isDirectory() || f.getName().endsWith(".xml"));
			}

			@Override
			public String getDescription() {
				return "XML files (*.xml)";
			}
		});

		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			Settings.setWorkingDirectory(fc.getCurrentDirectory());
			InputSource input;
			try {
				input = new InputSource(new FileInputStream(fc.getSelectedFile()));
				XMLStreamReader reader = XMLStreamReaderFactory.create(input, false);
				while (reader.hasNext()) {
					reader.next();
					EffectBase eff = new EffectBase();
					eff.importXML(reader);
					presetEffectAdd(eff);
				}
				reader.close();
				resetEffectListFromArray();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Event: OK pressed.
	 * 
	 * @param event
	 */
	private void jButtonOKActionActionPerformed(ActionEvent event) {
		setStat(new Stats());
		moveFieldsToClass(getStat());

		if (!getStat().isValid()) {
			JOptionPane.showMessageDialog(this, "Please assign a name, level, and role to this statblock.\n"
					+ "It cannot be saved until these values are assigned.", "Missing Information", JOptionPane.ERROR_MESSAGE);
			setStat(null);
		} else {
			this.setVisible(false);
		}
	}

	/**
	 * Event: Power Delete pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerDeleteActionActionPerformed(ActionEvent event) {
		if (isPowerDataValid()) {
			((DefaultListModel) _listPowers.getModel()).removeElementAt(_listPowers.getSelectedIndex());
			resetPowerListFromArray();
			_listPowers.setSelectedIndex(-1);
		}
	}

	/**
	 * Event: Power Down pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerDownActionActionPerformed(ActionEvent event) {
		if (isPowerDataValid()) {
			Integer index = _listPowers.getSelectedIndex();
			DefaultListModel model = (DefaultListModel) _listPowers.getModel();
			Power tempPow = (Power) model.get(index);
			model.set(index, model.get(index + 1));
			index++;
			model.set(index, tempPow);
			resetPowerListFromArray();
			_listPowers.setSelectedValue(tempPow, true);
		}
	}

	/**
	 * Event: Power New pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerNewActionActionPerformed(ActionEvent event) {
		Power pow = new Power();
		((DefaultListModel) _listPowers.getModel()).addElement(pow);
		resetPowerListFromArray();
		_listPowers.setSelectedValue(pow, true);
	}

	/**
	 * Event: Power Up pressed.
	 * 
	 * @param event
	 */
	private void jButtonPowerUpActionActionPerformed(ActionEvent event) {
		if (isPowerDataValid()) {
			Integer index = _listPowers.getSelectedIndex();
			DefaultListModel model = (DefaultListModel) _listPowers.getModel();
			if (index > 0) {
				Power tempPow = (Power) model.get(index);
				model.set(index, model.get(index - 1));
				index--;
				model.set(index, tempPow);
				resetPowerListFromArray();
				_listPowers.setSelectedValue(tempPow, true);
			}
		}
	}

	/**
	 * Event: PC checked/unchecked.
	 * 
	 * @param event
	 */
	private void jCheckBoxPCChangeStateChanged(ChangeEvent event) {
		if (_chckbxPc.isSelected()) {
			_comboBoxRole.setSelectedItem("Hero");
			_comboBoxRole2.setSelectedItem("");
			_chckbxLeader.setSelected(false);

			_comboBoxRole.setEnabled(false);
			_comboBoxRole2.setEnabled(false);
			_chckbxLeader.setEnabled(false);
		} else {
			_comboBoxRole.setEnabled(true);
			_comboBoxRole2.setEnabled(true);
			_chckbxLeader.setEnabled(true);
			if (((String) _comboBoxRole.getSelectedItem()).contentEquals("Hero")) {
				_comboBoxRole.setSelectedItem("");
			}
		}
	}

	/**
	 * Event: Aura checked/unchecked.
	 * 
	 * @param e
	 */
	private void jCheckBoxPowerAuraChangeStateChanged(ChangeEvent e) {
		if (_chckbxPowerAura.isSelected()) {
			_comboBoxPowerIcon.setEnabled(false);
			_formattedTextFieldPowerAuraSize.setEnabled(true);
			if (Integer.valueOf(_formattedTextFieldPowerAuraSize.getText()) < 1) {
				_formattedTextFieldPowerAuraSize.setText("1");
				_formattedTextFieldPowerAuraSize.requestFocusInWindow();
			}
			Power pow = (Power) _listPowers.getSelectedValue();
			pow.setAura(Integer.valueOf(_formattedTextFieldPowerAuraSize.getText()));
			_listPowers.repaint();
		} else {
			_comboBoxPowerIcon.setEnabled(true);
			_formattedTextFieldPowerAuraSize.setEnabled(false);
			_formattedTextFieldPowerAuraSize.setText("0");
			if (isPowerDataValid()) {
				Power pow = (Power) _listPowers.getSelectedValue();
				pow.setAura(0);
				_listPowers.repaint();
			}
		}
		setPowerChanged(true);
	}

	/**
	 * Event: Power Icon focus lost.
	 * 
	 * @param event
	 */
	private void jComboBoxPowerIconItemItemStateChanged(ItemEvent event) {
		if (isPowerDataValid()) {
			Power pow = (Power) _listPowers.getSelectedValue();
			pow.setType((String) _comboBoxPowerIcon.getSelectedItem());
			setPowerChanged(true);
			_listPowers.repaint();
		}
	}

	/**
	 * Event: Role changed.
	 * 
	 * @param event
	 */
	private void jComboBoxRoleItemItemStateChanged(ItemEvent event) {
		if (((String) _comboBoxRole2.getSelectedItem()).contentEquals("Hero") && !_chckbxPc.isSelected()) {
			_chckbxPc.doClick();
		} else if (!((String) _comboBoxRole2.getSelectedItem()).contentEquals("Hero") && _chckbxPc.isSelected()) {
			_chckbxPc.doClick();
		}
	}

	/**
	 * Event: Aura size focus lost.
	 * 
	 * @param event
	 */
	private void jFormattedTextFieldAuraSizeKeyKeyReleased(KeyEvent event) {
		if (isPowerDataValid()) {
			Power pow = (Power) _listPowers.getSelectedValue();
			if (!_formattedTextFieldPowerAuraSize.getText().isEmpty()) {
				pow.setAura(Integer.valueOf(_formattedTextFieldPowerAuraSize.getText()));
			}
			setPowerChanged(true);
			_listPowers.repaint();
		}
	}

	/**
	 * Event: Effect list selection changed.
	 * 
	 * @param event
	 */
	private void jListEffectsListSelectionValueChanged(ListSelectionEvent event) {
		if (_listEffects.getSelectedIndex() >= 0) {
			_btnEffectDelete.setEnabled(true);
			_btnEffectEdit.setEnabled(true);
		} else {
			_btnEffectDelete.setEnabled(false);
			_btnEffectEdit.setEnabled(false);
		}
	}

	/**
	 * Event: Powers list, selection changed.
	 * 
	 * @param event
	 */
	private void jListPowersListSelectionValueChanged(ListSelectionEvent event) {
		DefaultListModel model = (DefaultListModel) _listPowers.getModel();
		if (_listPowers.getSelectedIndex() >= 0) {
			Power pow = (Power) _listPowers.getSelectedValue();
			powDataEnable();
			powDataLoad(pow);
			_btnPowerDelete.setEnabled(true);

			if (model.size() > 1 && _listPowers.getSelectedIndex() > 0) {
				_btnPowerUp.setEnabled(true);
			} else {
				_btnPowerUp.setEnabled(false);
			}
			if (model.size() > 1 && _listPowers.getSelectedIndex() < model.size() - 1) {
				_btnPowerDown.setEnabled(true);
			} else {
				_btnPowerDown.setEnabled(false);
			}
		} else {
			powDataDisable();
			powDataClear();
			_btnPowerDelete.setEnabled(false);
			_btnPowerUp.setEnabled(false);
			_btnPowerDown.setEnabled(false);
			if (isPowerChanged()) {
				resetPowerListFromArray();
			}
		}
	}

	/**
	 * Event: Power Description focus lost.
	 * 
	 * @param event
	 */
	private void jTextAreaPowerDescriptionKeyKeyReleased(KeyEvent event) {
		if (isPowerDataValid()) {
			Power pow = (Power) _listPowers.getSelectedValue();
			pow.setDesc(_textAreaPowerDescription.getText());
			setPowerChanged(true);
			_listPowers.repaint();
		}
	}

	/**
	 * Event: Power Action focus lost.
	 * 
	 * @param event
	 */
	private void jTextFieldPowerActionKeyKeyReleased(KeyEvent event) {
		if (isPowerDataValid()) {
			Power pow = (Power) _listPowers.getSelectedValue();
			pow.setAction(_textFieldPowerAction.getText());
			setPowerChanged(true);
			_listPowers.repaint();
		}
	}

	/**
	 * Event: Power Keywords focus lost.
	 * 
	 * @param event
	 */
	private void jTextFieldPowerKeywordsKeyKeyReleased(KeyEvent event) {
		if (isPowerDataValid()) {
			Power pow = (Power) _listPowers.getSelectedValue();
			pow.setKeywords(_textFieldPowerKeywords.getText());
			setPowerChanged(true);
			_listPowers.repaint();
		}
	}

	/**
	 * Event: Power Name focus lost.
	 * 
	 * @param event
	 */
	private void jTextFieldPowerNameKeyKeyReleased(KeyEvent event) {
		if (isPowerDataValid()) {
			Power pow = (Power) _listPowers.getSelectedValue();
			if (_textFieldPowerName.getText().isEmpty()) {
				_textFieldPowerName.setText("(no name)");
			}
			pow.setName(_textFieldPowerName.getText());
			setPowerChanged(true);
			_listPowers.repaint();
		}
	}

	/**
	 * Loads the statblock data into form fields.
	 * 
	 * @param stat
	 *            the statblock
	 */
	private void moveClassToFields(Stats stat) {
		_textFieldName.setText(stat.getName());
		_spinnerLevel.setValue(stat.getLevel());
		_comboBoxRole.setSelectedItem(stat.getRole());
		_comboBoxRole2.setSelectedItem(stat.getRole2());
		_chckbxLeader.setSelected(stat.isLeader());
		_chckbxPc.setSelected(stat.isPC());

		_textFieldTypeKeywords.setText(stat.getType());
		_textFieldXP.setText(stat.getXP().toString());

		_textFieldSenses.setText(stat.getSenses());
		_textFieldSpeed.setText(stat.getSpeed());

		_textFieldImmune.setText(stat.getImmunity());
		_textFieldResist.setText(stat.getResistance());
		_textFieldVulnerable.setText(stat.getVulnerability());
		_textFieldRegen.setText(stat.getRegen());

		_formattedTextFieldInit.setText(stat.getInit().toString());
		_formattedTextFieldSaveBonus.setText(stat.getSaveBonus().toString());
		_formattedTextFieldActionPts.setText(stat.getActionPoints().toString());
		_formattedTextFieldPowerPts.setText(stat.getPowerPoints().toString());
		_formattedTextFieldMax.setText(stat.getMaxHP().toString());
		_formattedTextFieldSurges.setText(stat.getSurges().toString());

		_formattedTextFieldAC.setText(stat.getAC().toString());
		_formattedTextFieldFort.setText(stat.getFort().toString());
		_formattedTextFieldRef.setText(stat.getRef().toString());
		_formattedTextFieldWill.setText(stat.getWill().toString());

		_formattedTextFieldStr.setText(stat.getStr().toString());
		_formattedTextFieldCon.setText(stat.getCon().toString());
		_formattedTextFieldDex.setText(stat.getDex().toString());
		_formattedTextFieldInt.setText(stat.getInt().toString());
		_formattedTextFieldWis.setText(stat.getWis().toString());
		_formattedTextFieldCha.setText(stat.getCha().toString());

		_textFieldAlignment.setText(stat.getAlignment());
		_textFieldSkills.setText(stat.getSkills());
		_textFieldFeats.setText(stat.getFeats());
		_textFieldLanguages.setText(stat.getLanguages());
		_textFieldEquipment.setText(stat.getEquipment());
		_textFieldSource.setText(stat.getSource());
		_textPaneNotes.setText(stat.getNotes());

		DefaultListModel model = (DefaultListModel) _listPowers.getModel();
		model.clear();
		for (Power pow : stat.getPowerList()) {
			model.addElement(pow);
		}
		resetPowerListFromArray();

		for (EffectBase eff : stat.getPresetEffects().values()) {
			presetEffectAdd(new EffectBase(eff));
		}
		resetEffectListFromArray();
	}

	/**
	 * Loads field data into Stats class.
	 * 
	 * @param stat
	 *            the stats
	 */
	private void moveFieldsToClass(Stats stat) {
		stat.setName(_textFieldName.getText());
		stat.setLevel((Integer) _spinnerLevel.getValue());
		stat.setRole((String) _comboBoxRole.getSelectedItem());
		stat.setRole2((String) _comboBoxRole2.getSelectedItem());
		stat.setLeader(_chckbxLeader.isSelected());
		stat.setType(_textFieldTypeKeywords.getText());
		stat.setXP(Integer.valueOf(_textFieldXP.getText().replace(",", "")));

		stat.setSenses(_textFieldSenses.getText());
		stat.setSpeed(_textFieldSpeed.getText());

		stat.setImmunity(_textFieldImmune.getText());
		stat.setResistance(_textFieldResist.getText());
		stat.setVulnerability(_textFieldVulnerable.getText());
		stat.setRegen(_textFieldRegen.getText());

		stat.setInit(Integer.valueOf(_formattedTextFieldInit.getText().replace(",", "")));
		stat.setSaveBonus(Integer.valueOf(_formattedTextFieldSaveBonus.getText().replace(",", "")));
		stat.setActionPoints(Integer.valueOf(_formattedTextFieldActionPts.getText()));
		stat.setPowerPoints(Integer.valueOf(_formattedTextFieldPowerPts.getText()));
		stat.setMaxHP(Integer.valueOf(_formattedTextFieldMax.getText().replace(",", "")));
		stat.setSurges(Integer.valueOf(_formattedTextFieldSurges.getText().replace(",", "")));

		stat.setAC(Integer.valueOf(_formattedTextFieldAC.getText().replace(",", "")));
		stat.setFort(Integer.valueOf(_formattedTextFieldFort.getText().replace(",", "")));
		stat.setRef(Integer.valueOf(_formattedTextFieldRef.getText().replace(",", "")));
		stat.setWill(Integer.valueOf(_formattedTextFieldWill.getText().replace(",", "")));

		stat.setStr(Integer.valueOf(_formattedTextFieldStr.getText().replace(",", "")));
		stat.setCon(Integer.valueOf(_formattedTextFieldCon.getText().replace(",", "")));
		stat.setDex(Integer.valueOf(_formattedTextFieldDex.getText().replace(",", "")));
		stat.setInt(Integer.valueOf(_formattedTextFieldInt.getText().replace(",", "")));
		stat.setWis(Integer.valueOf(_formattedTextFieldWis.getText().replace(",", "")));
		stat.setCha(Integer.valueOf(_formattedTextFieldCha.getText().replace(",", "")));

		stat.setAlignment(_textFieldAlignment.getText());
		stat.setSkills(_textFieldSkills.getText());
		stat.setFeats(_textFieldFeats.getText());
		stat.setLanguages(_textFieldLanguages.getText());
		stat.setEquipment(_textFieldEquipment.getText());
		stat.setSource(_textFieldSource.getText());
		stat.setNotes(_textPaneNotes.getText());

		stat.getPowerList().clear();
		DefaultListModel model = (DefaultListModel) _listPowers.getModel();
		for (int i = 0; i < model.getSize(); i++) {
			Power pow = (Power) model.get(i);
			if (!pow.getName().isEmpty()) {
				stat.getPowerList().add(new Power(pow));
			}
		}
		stat.getPresetEffects().clear();
		for (EffectBase eff : getPresetEffects().toArray(new EffectBase[0])) {
			stat.presetEffectAdd(new EffectBase(eff));
		}
	}

	/**
	 * Clears power data fields.
	 */
	private void powDataClear() {
		_textFieldPowerName.setText("");
		_comboBoxPowerIcon.setSelectedItem(null);
		_textFieldPowerKeywords.setText("");
		_textFieldPowerAction.setText("");
		_formattedTextFieldPowerAuraSize.setText("0");
		_chckbxPowerAura.setSelected(false);
		_comboBoxPowerIcon.setEnabled(false);
		_formattedTextFieldPowerAuraSize.setEnabled(false);
		_textAreaPowerDescription.setText("");
	}

	/**
	 * Disables power data fields.
	 */
	private void powDataDisable() {
		_textFieldPowerName.setEnabled(false);
		_textFieldPowerKeywords.setEnabled(false);
		_textFieldPowerAction.setEnabled(false);
		_textAreaPowerDescription.setEnabled(false);
		_chckbxPowerAura.setEnabled(false);
		_formattedTextFieldPowerAuraSize.setEnabled(false);
		_comboBoxPowerIcon.setEnabled(false);
	}

	/**
	 * Enables power data fields.
	 */
	private void powDataEnable() {
		_textFieldPowerName.setEnabled(true);
		_textFieldPowerKeywords.setEnabled(true);
		_textFieldPowerAction.setEnabled(true);
		_textAreaPowerDescription.setEnabled(true);
		_chckbxPowerAura.setEnabled(true);
		if (_chckbxPowerAura.isSelected()) {
			_formattedTextFieldPowerAuraSize.setEnabled(true);
			_comboBoxPowerIcon.setEnabled(false);
		} else {
			_formattedTextFieldPowerAuraSize.setEnabled(false);
			_comboBoxPowerIcon.setEnabled(true);
		}
	}

	/**
	 * Loads power data into data fields.
	 * 
	 * @param pow
	 *            the power for which data should be loaded
	 */
	private void powDataLoad(Power pow) {
		_textFieldPowerName.setText(pow.getName());
		_textFieldPowerKeywords.setText(pow.getKeywords());
		_textFieldPowerAction.setText(pow.getAction());
		_textFieldPowerURL.setText(pow.getURL());
		_textAreaPowerDescription.setText(pow.getDesc());
		if (pow.isAura()) {
			_comboBoxPowerIcon.setSelectedIndex(0);
			_comboBoxPowerIcon.setEnabled(false);
			_chckbxPowerAura.setSelected(true);
			_formattedTextFieldPowerAuraSize.setEnabled(true);
			_formattedTextFieldPowerAuraSize.setText(pow.getAura().toString());
		} else {
			_comboBoxPowerIcon.setSelectedItem(pow.getType());
			_comboBoxPowerIcon.setEnabled(true);
			_chckbxPowerAura.setSelected(false);
			_formattedTextFieldPowerAuraSize.setEnabled(false);
			_formattedTextFieldPowerAuraSize.setText("0");
		}
		setPowerChanged(false);
	}

	/**
	 * Adds a preset effect to the list.
	 * 
	 * @param eff
	 */
	private void presetEffectAdd(EffectBase eff) {
		if (eff.isValid() && !getPresetEffects().contains(eff)) {
			getPresetEffects().add(eff);
		}
	}

	/**
	 * Reloads the effect list from the array in the class.
	 */
	private void resetEffectListFromArray() {
		DefaultListModel model = (DefaultListModel) _listEffects.getModel();
		model.clear();

		if (model.isEmpty()) {
			_btnEffectDelete.setEnabled(false);

			for (EffectBase eff : getPresetEffects().toArray(new EffectBase[0])) {
				model.addElement(eff);
			}
		}
	}

	/**
	 * Reloads the power list UI from the list of powers in the class.
	 */
	private void resetPowerListFromArray() {
		setPowerChanged(false);
	}

	/**
	 * Sets an indicator of if a power was changed.
	 * 
	 * @param powerChanged
	 *            true, if a power was changed
	 */
	private void setPowerChanged(Boolean powerChanged) {
		_powerChanged = powerChanged;
	}

	/**
	 * Sets the statblock for this form.
	 * 
	 * @param stat
	 *            the statblock
	 */
	private void setStat(Stats stat) {
		_stat = stat;
	}
}
