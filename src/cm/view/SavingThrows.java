package cm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import cm.model.CheckableItem;
import cm.model.Effect;
import cm.util.DiceBag;
import cm.view.render.CheckListRenderer;

//VS4E -- DO NOT REMOVE THIS LINE!
public class SavingThrows extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelBottom;
	private JLabel jLabelSaveBonus;
	private JFormattedTextField jFormattedTextFieldBonus;
	private JList jListEffects;
	private JScrollPane jScrollPaneList;
	private JButton jButtonRerollAll;
	private JButton jButtonSave;
	
	public SavingThrows() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Saving Throws");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setModal(true);
		setForeground(Color.black);
		add(getJPanelBottom(), BorderLayout.SOUTH);
		add(getJScrollPaneList(), BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
	
			public void windowOpened(WindowEvent event) {
				windowWindowOpened(event);
			}
		});
		setSize(309, 253);
	}

	private JButton getJButtonSave() {
		if (jButtonSave == null) {
			jButtonSave = new JButton();
			jButtonSave.setText("Save");
			jButtonSave.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonSaveActionActionPerformed(event);
				}
			});
		}
		return jButtonSave;
	}

	private JButton getJButtonRerollAll() {
		if (jButtonRerollAll == null) {
			jButtonRerollAll = new JButton();
			jButtonRerollAll.setText("Reroll All");
			jButtonRerollAll.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonRerollAllActionActionPerformed(event);
				}
			});
		}
		return jButtonRerollAll;
	}

	private JScrollPane getJScrollPaneList() {
		if (jScrollPaneList == null) {
			jScrollPaneList = new JScrollPane();
			jScrollPaneList.setViewportView(getJListEffects());
		}
		return jScrollPaneList;
	}

	private JList getJListEffects() {
		if (jListEffects == null) {
			jListEffects = new JList();
			jListEffects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			DefaultListModel listModel = new DefaultListModel();
			jListEffects.setModel(listModel);
			jListEffects.setCellRenderer(new CheckListRenderer(CheckListRenderer.EFFECT_TYPE));
			jListEffects.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jListEffectsMouseMouseClicked(event);
				}
			});
		}
		return jListEffects;
	}

	private JFormattedTextField getJFormattedTextFieldBonus() {
		if (jFormattedTextFieldBonus == null) {
			jFormattedTextFieldBonus = new JFormattedTextField(NumberFormat.getInstance());
			jFormattedTextFieldBonus.setText("0");
		}
		return jFormattedTextFieldBonus;
	}

	private JLabel getJLabelSaveBonus() {
		if (jLabelSaveBonus == null) {
			jLabelSaveBonus = new JLabel();
			jLabelSaveBonus.setText("Save Bonus: ");
		}
		return jLabelSaveBonus;
	}

	private JPanel getJPanelBottom() {
		if (jPanelBottom == null) {
			jPanelBottom = new JPanel();
			jPanelBottom.setPreferredSize(new Dimension(40, 40));
			jPanelBottom.setLayout(new GroupLayout());
			jPanelBottom.add(getJLabelSaveBonus(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
			jPanelBottom.add(getJFormattedTextFieldBonus(), new Constraints(new Leading(97, 42, 10, 10), new Leading(10, 12, 12)));
			jPanelBottom.add(getJButtonRerollAll(), new Constraints(new Leading(145, 12, 12), new Leading(7, 12, 12)));
			jPanelBottom.add(getJButtonSave(), new Constraints(new Leading(235, 12, 12), new Leading(7, 12, 12)));
		}
		return jPanelBottom;
	}

	private Hashtable<Integer, Effect> _effects = new Hashtable<Integer, Effect>();
	private List<Integer> _successfulSaves = new ArrayList<Integer>();
	/**
	 * Creates a new Saving Throws window for the given effects.
	 * @param effectList a list of effects to roll saves for
	 * @param bonus the save bonus
	 * @param parent the parent frame
	 */
	public SavingThrows(List<Effect> effectList, Integer bonus, Frame parent) {
		super(parent);
		initComponents();
		
		getJFormattedTextFieldBonus().setText(bonus.toString());
		
		for (Effect eff : effectList) {
			eff.setActive();
			addEffect(eff.getEffectID(), eff);
		}
	}

	/**
	 * Adds an effect to the list of effects.
	 * @param effectID the effect ID
	 * @param eff the effect
	 */
	private void addEffect(Integer effectID, Effect eff) {
		getEffects().put(effectID, eff);		
	}

	/**
	 * Returns the list of effects.
	 * @return the effects
	 */
	private Hashtable<Integer, Effect> getEffects() {
		return _effects;
	}

	/**
	 * Event: Window opened.
	 * @param event
	 */
	private void windowWindowOpened(WindowEvent event) {
		loadEffectsToList();
	}

	/**
	 * Adds a successful save to the list.
	 * @param effectID the effect ID of the saved effect
	 */
	private void addSuccessfulSave(Integer effectID) {
		getSuccessfulSaves().add(effectID);
	}

	/**
	 * Returns the list of successful saves.
	 * @return the list
	 */
	public List<Integer> getSuccessfulSaves() {
		return _successfulSaves;
	}

	/**
	 * Event: Reroll All pressed.
	 * @param event
	 */
	private void jButtonRerollAllActionActionPerformed(ActionEvent event) {
		rerollAllSaves();
	}

	/**
	 * Event: Save pressed.
	 * @param event
	 */
	private void jButtonSaveActionActionPerformed(ActionEvent event) {
		DefaultListModel model = (DefaultListModel) getJListEffects().getModel();
		
		for (int i = 0; i < model.getSize(); i++) {
			CheckableItem item = (CheckableItem) model.getElementAt(i);
			
			if (item.isSelected()) {
				addSuccessfulSave(((Effect) item.getObject()).getEffectID());
			}
		}

		this.setVisible(false);
	}
	
	/**
	 * Loads the list of effects to the UI.
	 */
	private void loadEffectsToList() {
		DefaultListModel model = ((DefaultListModel) getJListEffects().getModel());
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
		DefaultListModel model = ((DefaultListModel) getJListEffects().getModel());
		
		for (int i = 0; i < model.getSize(); i++) {
			CheckableItem item = (CheckableItem) model.getElementAt(i);
			Integer roll = DiceBag.roll(20) + Integer.valueOf(getJFormattedTextFieldBonus().getText());
			item.setSelected(roll >= 10);
			item.setText("(roll=" + roll + ")");
		}
		
		getJListEffects().repaint();
	}

	/**
	 * Event: Effect list, mouse clicked.
	 * @param event
	 */
	private void jListEffectsMouseMouseClicked(MouseEvent event) {
		JList list = getJListEffects();
		CheckableItem item = (CheckableItem) list.getModel().getElementAt(list.locationToIndex(event.getPoint()));
		item.setSelected(!item.isSelected());
		item.setText(null);
		list.repaint();
	}
}
