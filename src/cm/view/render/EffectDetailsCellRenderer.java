package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.Effect;

public class EffectDetailsCellRenderer extends JTextArea implements ListCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EffectDetailsCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Effect eff = (Effect) value;
		setText(eff.getName() + "\n" 
				+ "    Source: " + eff.getSourceHandle() + "\n"
				+ "    Duration: " + eff.getDesc());
		setForeground(Color.WHITE);
		if (eff.isBeneficial()) {
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.RED);
		}
		
		if (isSelected) {
			setBackground(Color.DARK_GRAY);
		}
		
		return this;
	}

}
