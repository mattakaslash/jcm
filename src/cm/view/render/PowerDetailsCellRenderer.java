package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import cm.model.Power;

public class PowerDetailsCellRenderer extends JTextArea implements ListCellRenderer {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Power pow = (Power) value;
		String display = pow.getName() + "\n";
		
		if (pow.isAura()) {
			if (pow.getKeywords().isEmpty()) {
				display += "   " + pow.getType();
			} else {
				display += "   " + pow.getType() + " (" + pow.getKeywords() + ")";
			}
		} else {
			if (!pow.getType().isEmpty() && !pow.getAction().isEmpty()) {
				display += "   " + pow.getType() + " (" + pow.getAction() + ")";
			} else if (!pow.getAction().isEmpty()) {
				display += "   (" + pow.getAction() + ")";
			} else if (!pow.getType().isEmpty()) {
				display += "   " + pow.getType();
			} else {
				display += "   (constant)";
			}
		}
		
		setText(display);
		
		if (isSelected) {
			setBackground(Color.DARK_GRAY);
			setForeground(Color.WHITE);
		} else {
			setBackground(pow.getBackColor());
			setForeground(pow.getForeColor());
		}

		return this;
	}

}
