package cm.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

import cm.model.Effect;
import cm.model.ReadOnlyTableModel;
import cm.util.DiceBag;
import cm.view.render.EffectCellRenderer;
import cm.view.render.JCheckBoxCellRenderer;

//VS4E -- DO NOT REMOVE THIS LINE!
public class SavingThrows extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable jTableRolls;
	private JScrollPane jScrollPaneRolls;
	private JToolBar jToolBarActions;
	private JButton jButtonRerollAll;
	private JLabel jLabelSaveBonus;
	private JFormattedTextField jFormattedTextFieldSaveBonus;
	private JButton jButtonSaveResults;
	
	public SavingThrows() {
		initComponents();
	}

	public SavingThrows(List<Effect> list, Integer saveBonus,
			DiceBag encounterDice) {
		initComponents();
		
	    setDice(encounterDice);
	    getJFormattedTextFieldSaveBonus().setText(saveBonus.toString());

	    if (list == null) {
	    	setEffects(new Hashtable<Integer, Effect>());
	    } else {
	    	for (Effect eff : list) {
	    		eff.setActive();
	    		getEffects().put(eff.getEffectID(), eff);
	    	}
	    }
	}

	private void initComponents() {
		setTitle("Saving Throw Results");
		setModal(true);
		add(getJScrollPaneRolls(), BorderLayout.CENTER);
		add(getJToolBarActions(), BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
	
			public void windowClosed(WindowEvent event) {
				windowWindowClosed(event);
			}
	
			public void windowOpened(WindowEvent event) {
				windowWindowOpened(event);
			}
		});
		setSize(380, 160);
	}

	private JButton getJButtonSaveResults() {
		if (jButtonSaveResults == null) {
			jButtonSaveResults = new JButton();
			jButtonSaveResults.setText("Save Results");
			jButtonSaveResults.setBorder(BorderFactory.createCompoundBorder(null, null));
			jButtonSaveResults.setDefaultCapable(false);
			jButtonSaveResults.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonSaveResultsActionActionPerformed(event);
				}
			});
		}
		return jButtonSaveResults;
	}

	private JFormattedTextField getJFormattedTextFieldSaveBonus() {
		if (jFormattedTextFieldSaveBonus == null) {
			jFormattedTextFieldSaveBonus = new JFormattedTextField(NumberFormat.getInstance());
			jFormattedTextFieldSaveBonus.setText("0");
		}
		return jFormattedTextFieldSaveBonus;
	}

	private JLabel getJLabelSaveBonus() {
		if (jLabelSaveBonus == null) {
			jLabelSaveBonus = new JLabel();
			jLabelSaveBonus.setText("Save Bonus: ");
		}
		return jLabelSaveBonus;
	}

	private JToolBar getJToolBarActions() {
		if (jToolBarActions == null) {
			jToolBarActions = new JToolBar();
			jToolBarActions.setFloatable(false);
			jToolBarActions.add(getJButtonRerollAll());
			jToolBarActions.add(getJLabelSaveBonus());
			jToolBarActions.add(getJFormattedTextFieldSaveBonus());
			jToolBarActions.add(getJButtonSaveResults());
		}
		return jToolBarActions;
	}

	private JButton getJButtonRerollAll() {
		if (jButtonRerollAll == null) {
			jButtonRerollAll = new JButton();
			jButtonRerollAll.setText("Reroll All");
			jButtonRerollAll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jButtonRerollAll.setDefaultCapable(false);
			jButtonRerollAll.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButtonRerollAllActionActionPerformed(event);
				}
			});
		}
		return jButtonRerollAll;
	}

	private JScrollPane getJScrollPaneRolls() {
		if (jScrollPaneRolls == null) {
			jScrollPaneRolls = new JScrollPane();
			jScrollPaneRolls.setViewportView(getJTableRolls());
		}
		return jScrollPaneRolls;
	}

	private JTable getJTableRolls() {
		if (jTableRolls == null) {
			jTableRolls = new JTable();
			String[] columns = { "Effect", "Save Result", "Saved" };
			jTableRolls.setModel(new ReadOnlyTableModel(null, columns));
			jTableRolls.getColumn(columns[0]).setCellRenderer(new EffectCellRenderer());
			jTableRolls.getColumn(columns[2]).setCellRenderer(new JCheckBoxCellRenderer());
		}
		return jTableRolls;
	}
	
	private Hashtable<Integer, Effect> _effects = new Hashtable<Integer, Effect>();
	private List<Integer> _successfulSaves = new ArrayList<Integer>();
	private DiceBag _dice = new DiceBag();

	/**
	 * @return the effects
	 */
	private Hashtable<Integer, Effect> getEffects() {
		return _effects;
	}

	/**
	 * @param effects the effects to set
	 */
	private void setEffects(Hashtable<Integer, Effect> effects) {
		_effects = effects;
	}

	/**
	 * @return the successfulSaves
	 */
	public List<Integer> getSuccessfulSaves() {
		return _successfulSaves;
	}

	/**
	 * @param successfulSaves the successfulSaves to set
	 */
	private void setSuccessfulSaves(List<Integer> successfulSaves) {
		_successfulSaves = successfulSaves;
	}

	/**
	 * @return the dice
	 */
	private DiceBag getDice() {
		return _dice;
	}

	/**
	 * @param dice the _dice to set
	 */
	private void setDice(DiceBag dice) {
		_dice = dice;
	}

	/**
	 * Event: Window opened.
	 * @param event
	 */
	private void windowWindowOpened(WindowEvent event) {
		loadEffectsToList();
	}

	/**
	 * Event: Window closed.
	 * @param event
	 */
	private void windowWindowClosed(WindowEvent event) {
		/* TODO:
    For Each listitem As Windows.Forms.ListViewItem In lbChecklist.Items
        If listitem.Checked Then
            SuccessfulSaves.Add(listitem.Tag)
        End If
    Next
		 */
	}

	/**
	 * Event: Reroll All pressed.
	 * @param event
	 */
	private void jButtonRerollAllActionActionPerformed(ActionEvent event) {
		rerollAllSaves();
	}

	/**
	 * Event: Save Results pressed.
	 * @param event
	 */
	private void jButtonSaveResultsActionActionPerformed(ActionEvent event) {
		this.setVisible(false);
	}
	
	/**
	 * Loads the effects list to the UI table.
	 */
	private void loadEffectsToList() {
		ReadOnlyTableModel model = (ReadOnlyTableModel) getJTableRolls().getModel();
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
		
		for (Effect eff : getEffects().values()) {
			model.addRow(new Object[] { eff, 1, new JCheckBox("", false) });
		}
		
		rerollAllSaves();
	}
	
	/**
	 * Re-rolls all saving throws.
	 */
	private void rerollAllSaves() {
		ReadOnlyTableModel model = (ReadOnlyTableModel) getJTableRolls().getModel();
		
		for (int row = 0; row < model.getRowCount(); row++) {
			Integer roll = getDice().roll(20) + Integer.valueOf(getJFormattedTextFieldSaveBonus().getText());
			JCheckBox cb = (JCheckBox) model.getValueAt(row, 2);
			if (roll >= 10) {
				cb.setSelected(true);
			} else {
				cb.setSelected(false);
			}
			model.setValueAt(roll, row, 1);
			model.setValueAt(cb, row, 2);
		}
	}
}
/*
Private Sub lbChecklist_ItemChecked(ByVal sender As Object, ByVal e As System.Windows.Forms.ItemCheckedEventArgs) Handles lbChecklist.ItemChecked
    Dim listitem As Windows.Forms.ListViewItem = e.Item

    If listitem.SubItems.Count > 1 Then
        If listitem.Checked Then
            listitem.SubItems(1).Text = "Successful"
            listitem.BackColor = Color.DarkGreen
        ElseIf Not listitem.Checked Then
            listitem.SubItems(1).Text = "Failed"
            listitem.BackColor = Color.DarkRed
        End If
    End If
End Sub

End Class
*/