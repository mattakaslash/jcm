package cm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import cm.model.CheckableItem;
import cm.model.Effect;
import cm.util.DiceBag;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

/**
 * Displays a window listing effects that require a save.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
// VS4E -- DO NOT REMOVE THIS LINE!
public class SavingThrows extends JDialog {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -3276944884619671356L;

	/**
	 * A table of effects.
	 */
	private Hashtable<Integer, Effect> _effects = new Hashtable<Integer, Effect>();

	/**
	 * A list of effects that have been saved against.
	 */
	private List<Integer> _successfulSaves = new ArrayList<Integer>();

	private JFormattedTextField _textFieldSaveBonus;
	private JList _listEffects;

	/**
	 * Displays a default (empty) saving throw window.
	 */
	public SavingThrows() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				windowWindowOpened(e);
			}
		});
		initComponents();
	}

	/**
	 * Creates a new Saving Throws window for the given effects.
	 * 
	 * @param effectList
	 *            a list of effects to roll saves for
	 * @param bonus
	 *            the save bonus
	 * @param parent
	 *            the parent frame
	 */
	public SavingThrows(List<Effect> effectList, Integer bonus, Frame parent) {
		super(parent);
		initComponents();

		_textFieldSaveBonus.setText(bonus.toString());

		for (Effect eff : effectList) {
			eff.setActive();
			addEffect(eff.getEffectID(), eff);
		}
	}

	/**
	 * Adds an effect to the list of effects.
	 * 
	 * @param effectID
	 *            the effect ID
	 * @param eff
	 *            the effect
	 */
	private void addEffect(Integer effectID, Effect eff) {
		getEffects().put(effectID, eff);
	}

	/**
	 * Adds a successful save to the list.
	 * 
	 * @param effectID
	 *            the effect ID of the saved effect
	 */
	private void addSuccessfulSave(Integer effectID) {
		getSuccessfulSaves().add(effectID);
	}

	/**
	 * Returns the list of effects.
	 * 
	 * @return the effects
	 */
	private Hashtable<Integer, Effect> getEffects() {
		return _effects;
	}

	/**
	 * Returns the list of successful saves.
	 * 
	 * @return the list
	 */
	public List<Integer> getSuccessfulSaves() {
		return _successfulSaves;
	}

	private void initComponents() {
		setTitle("Saving Throws");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setModal(true);
		setForeground(Color.black);

		_listEffects = new JList();
		_listEffects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jListEffectsMouseMouseClicked(e);
			}
		});
		getContentPane().add(_listEffects, BorderLayout.CENTER);

		JPanel panelControls = new JPanel();
		getContentPane().add(panelControls, BorderLayout.SOUTH);
		GridBagLayout gbl_panelControls = new GridBagLayout();
		gbl_panelControls.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelControls.rowHeights = new int[] { 0, 0 };
		gbl_panelControls.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelControls.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelControls.setLayout(gbl_panelControls);

		JLabel lblSaveBonus = new JLabel("Save Bonus:");
		GridBagConstraints gbc_lblSaveBonus = new GridBagConstraints();
		gbc_lblSaveBonus.anchor = GridBagConstraints.EAST;
		gbc_lblSaveBonus.insets = new Insets(0, 0, 0, 5);
		gbc_lblSaveBonus.gridx = 0;
		gbc_lblSaveBonus.gridy = 0;
		panelControls.add(lblSaveBonus, gbc_lblSaveBonus);

		_textFieldSaveBonus = new JFormattedTextField(NumberFormat.getInstance());
		_textFieldSaveBonus.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_textFieldSaveBonus = new GridBagConstraints();
		gbc_textFieldSaveBonus.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldSaveBonus.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSaveBonus.gridx = 1;
		gbc_textFieldSaveBonus.gridy = 0;
		panelControls.add(_textFieldSaveBonus, gbc_textFieldSaveBonus);
		_textFieldSaveBonus.setColumns(2);

		JButton btnRerollAll = new JButton("Reroll All");
		btnRerollAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRerollAllActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnRerollAll = new GridBagConstraints();
		gbc_btnRerollAll.insets = new Insets(0, 0, 0, 5);
		gbc_btnRerollAll.gridx = 2;
		gbc_btnRerollAll.gridy = 0;
		panelControls.add(btnRerollAll, gbc_btnRerollAll);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonSaveActionActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 0;
		panelControls.add(btnSave, gbc_btnSave);
		pack();
	}

	/**
	 * Event: Reroll All pressed.
	 * 
	 * @param event
	 */
	private void jButtonRerollAllActionActionPerformed(ActionEvent event) {
		rerollAllSaves();
	}

	/**
	 * Event: Save pressed.
	 * 
	 * @param event
	 */
	private void jButtonSaveActionActionPerformed(ActionEvent event) {
		DefaultListModel model = (DefaultListModel) _listEffects.getModel();

		for (int i = 0; i < model.getSize(); i++) {
			CheckableItem item = (CheckableItem) model.getElementAt(i);

			if (item.isSelected()) {
				addSuccessfulSave(((Effect) item.getObject()).getEffectID());
			}
		}

		this.setVisible(false);
	}

	/**
	 * Event: Effect list, mouse clicked.
	 * 
	 * @param event
	 */
	private void jListEffectsMouseMouseClicked(MouseEvent event) {
		JList list = _listEffects;
		CheckableItem item = (CheckableItem) list.getModel().getElementAt(list.locationToIndex(event.getPoint()));
		item.setSelected(!item.isSelected());
		item.setText(null);
		list.repaint();
	}

	/**
	 * Loads the list of effects to the UI.
	 */
	private void loadEffectsToList() {
		DefaultListModel model = ((DefaultListModel) _listEffects.getModel());
		model.clear();

		for (Effect eff : getEffects().values()) {
			model.addElement(new CheckableItem(eff));
		}

		rerollAllSaves();
	}

	/**
	 * Re-rolls all saving throws in the list.
	 */
	private void rerollAllSaves() {
		DefaultListModel model = ((DefaultListModel) _listEffects.getModel());

		for (int i = 0; i < model.getSize(); i++) {
			CheckableItem item = (CheckableItem) model.getElementAt(i);
			Integer roll = DiceBag.roll(20) + Integer.valueOf(_textFieldSaveBonus.getText());
			item.setSelected(roll >= 10);
			item.setText("(roll=" + roll + ")");
		}

		_listEffects.repaint();
	}

	/**
	 * Event: Window opened.
	 * 
	 * @param event
	 */
	private void windowWindowOpened(WindowEvent event) {
		loadEffectsToList();
	}
}
