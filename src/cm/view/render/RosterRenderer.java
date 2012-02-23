package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import cm.model.Combatant;
import cm.model.Encounter;

/**
 * Defines a JTable cell renderer for the encounter roster.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class RosterRenderer extends JLabel implements TableCellRenderer {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -1329542472734129791L;

	/**
	 * The ongoing {@link Encounter}.
	 */
	private Encounter _fight;

	/**
	 * Returns the ongoing {@link Encounter}.
	 * 
	 * @return the ongoing {@link Encounter}
	 */
	public Encounter getFight() {
		return _fight;
	}

	/**
	 * Sets the ongoing {@link Encounter}.
	 * 
	 * @param fight
	 *            the ongoing {@link Encounter}
	 */
	public void setFight(Encounter fight) {
		_fight = fight;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int col) {
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
