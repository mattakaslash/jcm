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
import cm.utils.Colors;

public class OffTurnPowerRenderer extends JTextArea implements ListCellRenderer {
	
	private Encounter _fight;

	public OffTurnPowerRenderer(Encounter fight) {
		setFight(fight);
	}
	
	private Encounter getFight() {
		return _fight;
	}

	private void setFight(Encounter fight) {
		_fight = fight;
	}

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
		String text = fighter.getName() + ": " + power.getName() + power.getActionLine() + "\n" +
				"   " + power.getDesc().replaceAll("\n", "\n   ");
		setText(text);
		
		if (getFight() != null && fighter == getFight().getSelectedFighter()) {
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
		} else {
			if (power.getAction().contains("at-will")) {
				setBackground(Colors.ATWILL);
				setForeground(Color.WHITE);
			} else if (power.getAction().contains("encounter")) {
				setBackground(Colors.ATWILL);
				setForeground(Color.WHITE);
			} else if (power.getAction().contains("daily")) {
				setBackground(Colors.ATWILL);
				setForeground(Color.WHITE);
			} else {
				setBackground(Color.WHITE);
				setForeground(Color.BLACK);
			}
		}

		return this;
	}

}
