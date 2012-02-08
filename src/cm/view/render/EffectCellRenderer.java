package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import cm.model.Effect;

public class EffectCellRenderer extends JLabel implements TableCellRenderer {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Effect eff = (Effect) value;
		Boolean saved = ((JCheckBox) table.getValueAt(row, 2)).isSelected();
		
		setForeground(Color.WHITE);
		setOpaque(true);
		setText(eff.getDesc());
		
		if (saved) {
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
