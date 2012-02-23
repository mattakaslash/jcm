package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.Combatant;
import cm.model.Power;
import cm.util.Colors;

/**
 * Defines a JList cell renderer for a {@link Power}.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class PowerCellRenderer extends JLabel implements ListCellRenderer {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 13349837299893871L;

	/**
	 * The {@link Combatant} whose power is to be rendered.
	 */
	private Combatant _fighter;

	/**
	 * Creates a new cell renderer.
	 * 
	 * @param fighter
	 *            the {@link Combatant} whose powers these are
	 */
	public PowerCellRenderer(Combatant fighter) {
		setFighter(fighter);
		setOpaque(true);
	}

	/**
	 * Returns the fighter.
	 * 
	 * @return the fighter
	 */
	private Combatant getFighter() {
		return _fighter;
	}

	/**
	 * Sets the fighter.
	 * 
	 * @param fighter
	 *            the fighter to set
	 */
	private void setFighter(Combatant fighter) {
		_fighter = fighter;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasValue) {
		Power pow = (Power) value;
		setText(pow.getName() + pow.getActionLine());

		if (getFighter().isPowerUsed(pow.getName())) {
			setForeground(Colors.DAILY);
			setBackground(Color.WHITE);
			setText("x  " + getText());
		} else {
			setForeground(pow.getForeColor());
			setBackground(pow.getBackColor());
		}

		if (isSelected) {
			setBackground(Color.BLACK);
		}

		return this;
	}
}
