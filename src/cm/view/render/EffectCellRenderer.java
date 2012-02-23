package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import cm.model.Effect;
import cm.util.Colors;

/**
 * Defines a JTable cell renderer for an {@link Effect}.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class EffectCellRenderer extends JLabel implements TableCellRenderer {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 4820997545557879271L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		Effect eff = (Effect) value;
		Boolean saved = ((JCheckBox) table.getValueAt(row, 2)).isSelected();

		setForeground(Color.WHITE);
		setOpaque(true);
		setText(eff.getDesc());

		if (saved) {
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
