package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import cm.model.Power;

/**
 * Defines a JList cell renderer to display {@link Power} details.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class PowerDetailsCellRenderer extends JTextArea implements ListCellRenderer {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 5180473278771647432L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
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
