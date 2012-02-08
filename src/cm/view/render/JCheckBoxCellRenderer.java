package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JCheckBoxCellRenderer extends JCheckBox implements
		TableCellRenderer {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JCheckBox cb = (JCheckBox) value;
		
		if (cb.isSelected()) {
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
