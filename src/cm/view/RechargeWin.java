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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cm.model.CheckableItem;
import cm.model.Power;
import cm.util.DiceBag;
import cm.view.render.CheckListRenderer;

/**
 * Displays a window listing powers up for recharging.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
// VS4E -- DO NOT REMOVE THIS LINE!
public class RechargeWin extends JDialog {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -6686134276284813896L;

	/**
	 * A table of powers up for recharging.
	 */
	private Hashtable<String, Power> _powers = new Hashtable<String, Power>();

	/**
	 * A list of power names that have been recharged.
	 */
	private List<String> _recharged = new ArrayList<String>();

	private JButton jButtonRerollAll;
	private JButton jButtonSave;
	private JList jListPowers;
	private JPanel jPanelBottom;
	private JScrollPane jScrollPanePowers;

	/**
	 * Creates a default (empty) recharge window.
	 */
	public RechargeWin() {
		initComponents();
	}

	/**
	 * Creates a new power recharge window for the provided combatant.
	 * 
	 * @param combatHandle
	 *            fighter's combat handle
	 * @param powerList
	 *            list of used powers
	 * @param parent
	 *            the parent frame
	 */
	public RechargeWin(String combatHandle, List<Power> powerList, Frame parent) {
		super(parent);
		initComponents();

		setTitle("Power Recharge for " + combatHandle);

		for (Power pow : powerList) {
			getPowers().put(pow.getName(), pow);
		}
	}

	private void initComponents() {
		setTitle("Recharge Powers");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setModal(true);
		add(getJPanelBottom(), BorderLayout.SOUTH);
		add(getJScrollPanePowers(), BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent event) {
				windowWindowOpened(event);
			}
		});
		pack();
	}

	private JButton getJButtonRerollAll() {
		if (jButtonRerollAll == null) {
			jButtonRerollAll = new JButton();
			jButtonRerollAll.setText("Reroll All");
			jButtonRerollAll.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonRerollAllActionActionPerformed(event);
				}
			});
		}
		return jButtonRerollAll;
	}

	private JButton getJButtonSave() {
		if (jButtonSave == null) {
			jButtonSave = new JButton();
			jButtonSave.setText("Save");
			jButtonSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					jButtonSaveActionActionPerformed(event);
				}
			});
		}
		return jButtonSave;
	}

	private JList getJListPowers() {
		if (jListPowers == null) {
			jListPowers = new JList();
			DefaultListModel listModel = new DefaultListModel();
			jListPowers.setModel(listModel);
			jListPowers.setCellRenderer(new CheckListRenderer(CheckListRenderer.POWER_TYPE));
			jListPowers.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent event) {
					jListPowersMouseMouseClicked(event);
				}
			});
		}
		return jListPowers;
	}

	private JPanel getJPanelBottom() {
		if (jPanelBottom == null) {
			jPanelBottom = new JPanel();
			jPanelBottom.setPreferredSize(new Dimension(40, 40));
			jPanelBottom.add(getJButtonRerollAll());
			jPanelBottom.add(getJButtonSave());
		}
		return jPanelBottom;
	}

	private JScrollPane getJScrollPanePowers() {
		if (jScrollPanePowers == null) {
			jScrollPanePowers = new JScrollPane();
			jScrollPanePowers.setViewportView(getJListPowers());
		}
		return jScrollPanePowers;
	}

	/**
	 * Returns the table of used powers.
	 * 
	 * @return the table
	 */
	private Hashtable<String, Power> getPowers() {
		return _powers;
	}

	/**
	 * Returns the list of recharged powers, by name.
	 * 
	 * @return the list
	 */
	public List<String> getRecharged() {
		return _recharged;
	}

	/**
	 * Event: Reroll All pressed.
	 * 
	 * @param event
	 */
	private void jButtonRerollAllActionActionPerformed(ActionEvent event) {
		rerollAllRecharge();
	}

	/**
	 * Event: Save pressed.
	 * 
	 * @param event
	 */
	private void jButtonSaveActionActionPerformed(ActionEvent event) {
		DefaultListModel model = (DefaultListModel) getJListPowers().getModel();

		for (int i = 0; i < model.getSize(); i++) {
			CheckableItem item = (CheckableItem) model.get(i);
			if (item.isSelected()) {
				Power pow = (Power) item.getObject();
				getRecharged().add(pow.getName());
			}
		}

		this.setVisible(false);
	}

	/**
	 * Event: Power list, mouse clicked.
	 * 
	 * @param event
	 */
	private void jListPowersMouseMouseClicked(MouseEvent event) {
		JList list = getJListPowers();
		CheckableItem item = (CheckableItem) list.getModel().getElementAt(list.locationToIndex(event.getPoint()));
		Power pow = (Power) item.getObject();
		item.setSelected(!item.isSelected());
		item.setText("(recharge=" + pow.getRechargeVal() + ")");
		list.repaint();
	}

	/**
	 * Loads powers from the table into the UI.
	 */
	private void loadPowersToList() {
		DefaultListModel model = (DefaultListModel) getJListPowers().getModel();
		model.clear();

		for (Power pow : getPowers().values()) {
			CheckableItem item = new CheckableItem(pow);
			item.setText("(recharge " + pow.getRechargeVal() + ")");
			model.addElement(new CheckableItem(pow));
		}

		rerollAllRecharge();
	}

	/**
	 * Rerolls all recharge powers in the list.
	 */
	private void rerollAllRecharge() {
		DefaultListModel model = (DefaultListModel) getJListPowers().getModel();

		for (int i = 0; i < model.getSize(); i++) {
			CheckableItem item = (CheckableItem) model.get(i);
			Power pow = (Power) item.getObject();
			Integer roll = DiceBag.roll(6);
			item.setSelected(roll >= pow.getRechargeVal());
			item.setText("(recharge=" + pow.getRechargeVal() + ") (roll=" + roll + ")");
		}

		getJListPowers().repaint();
	}

	/**
	 * Event: Window opened.
	 * 
	 * @param event
	 */
	private void windowWindowOpened(WindowEvent event) {
		loadPowersToList();
	}
}
