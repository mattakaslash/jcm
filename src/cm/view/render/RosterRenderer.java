package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import cm.model.Combatant;
import cm.model.Encounter;

public class RosterRenderer extends JLabel implements TableCellRenderer {
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	private Encounter _fight;
	
	public Encounter getFight() {
		return _fight;
	}

	public void setFight(Encounter fight) {
		_fight = fight;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		String handle = (String) table.getValueAt(row, 2);
		
		setOpaque(true);
		setText((value == null ? "" : value.toString()));

		if (col != 2 && col != 3) {
			setHorizontalAlignment(SwingConstants.CENTER);
		} else {
			setHorizontalAlignment(SwingConstants.LEADING);
		}
		
		if (handle != null && getFight() != null) {
			Combatant fighter = getFight().getFighterByHandle(handle);

			if (fighter != null) {
				setBackground(fighter.getDisplayBackColor());
				setForeground(fighter.getDisplayForeColor());
			}
		}
		
		if (isSelected) {
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
		}
		return this;
	}
}
