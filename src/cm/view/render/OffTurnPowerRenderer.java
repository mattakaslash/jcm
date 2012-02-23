package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.Combatant;
import cm.model.Encounter;
import cm.model.FighterPower;
import cm.model.Power;
import cm.util.Colors;

/**
 * Defines a JList cell renderer for a {@link Power}. It's used to format the
 * list of off-turn powers.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class OffTurnPowerRenderer extends JTextArea implements ListCellRenderer {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -8888786031071706235L;

	/**
	 * The ongoing {@link Encounter}.
	 */
	private Encounter _fight;

	/**
	 * Creates a new renderer, storing the ongoing encounter for later
	 * reference.
	 * 
	 * @param fight
	 *            the ongoing encounter
	 */
	public OffTurnPowerRenderer(Encounter fight) {
		setFight(fight);
	}

	/**
	 * Returns the ongoing encounter.
	 * 
	 * @return the ongoing encounter
	 */
	private Encounter getFight() {
		return _fight;
	}

	/**
	 * Sets the ongoing encounter.
	 * 
	 * @param fight
	 *            the ongoing encounter
	 */
	private void setFight(Encounter fight) {
		_fight = fight;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Combatant fighter = ((FighterPower) value).getFighter();
		Power power = ((FighterPower) value).getPower();

		setOpaque(true);
		String text = fighter.getName() + ": " + power.getName() + power.getActionLine() + "\n" + "   "
				+ power.getDesc().replaceAll("\n", "\n   ");
		setText(text);

		if (getFight() != null && fighter == getFight().getSelectedFighter()) {
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
		} else {
			setBackground(power.getBackColor());
			setForeground(power.getForeColor());
		}

		return this;
	}
}
