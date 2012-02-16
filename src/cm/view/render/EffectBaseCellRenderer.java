package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import cm.model.EffectBase;
import cm.util.Colors;

public class EffectBaseCellRenderer extends JTextArea implements ListCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EffectBaseCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		EffectBase eff = (EffectBase) value;
		setText(eff.getName() + "\n" 
				+ "    Duration: " + eff.getDurationCode().getDesc());
		setForeground(Color.WHITE);
		if (eff.isBeneficial()) {
			setBackground(Colors.ATWILL);
		} else {
			setBackground(Colors.ENCOUNTER);
		}
		
		if (isSelected) {
			setBackground(Color.BLACK);
		}
		
		return this;
	}

}
