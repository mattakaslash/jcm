package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import cm.model.EffectBase;
import cm.util.Colors;

/**
 * Defines a JList cell renderer for an {@link EffectBase}.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class EffectBaseCellRenderer extends JTextArea implements ListCellRenderer {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = -2213778407853623902L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		EffectBase eff = (EffectBase) value;
		setOpaque(true);
		setText(eff.getName() + "\n" + "    Duration: " + eff.getDurationCode().getDesc());
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
