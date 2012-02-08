package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.CheckableItem;

public class CheckListRenderer extends JCheckBox implements ListCellRenderer {
	public static final int EFFECT_TYPE = 0;
	public static final int POWER_TYPE = 1;
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	private int _type;
	
	public CheckListRenderer(int type) {
		_type = type;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		CheckableItem item = (CheckableItem) value;
		
		setOpaque(true);
		setSelected(item.isSelected());
		setText(item.toString());

		if (getType() == EFFECT_TYPE) {
			setForeground(Color.WHITE);

			if (item.isSelected()) {
				setBackground(Color.GREEN);
			} else {
				setBackground(Color.RED);
			}
		} else if (getType() == POWER_TYPE) {
			
			if (item.isSelected()) {
				setForeground(Color.BLACK);
				setBackground(Color.ORANGE);
			} else {
				setForeground(Color.GRAY);
				setBackground(Color.WHITE);
			}
		}

		return this;
	}

	private int getType() {
		return _type;
	}

}
