package cm.view.render;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.Combatant;
import cm.model.FighterPower;
import cm.model.Power;

public class OffTurnPowerRenderer extends JLabel implements ListCellRenderer {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Combatant fighter = ((FighterPower) value).getFighter();
		Power power = ((FighterPower) value).getPower();
		
		setOpaque(true);
		setText(fighter.getName() + ": " + power.getName() + power.getActionLine());

		return this;
	}

}
