package cm.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.CheckableItem;

public class CheckListRenderer extends JCheckBox implements ListCellRenderer {
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		CheckableItem item = (CheckableItem) value;
		
		setForeground(Color.WHITE);
		setOpaque(true);
		setText(item.toString());
		
		if (item.isSelected()) {
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.RED);
		}
		return this;
	}

}
