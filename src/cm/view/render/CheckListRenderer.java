package cm.view.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.CheckableItem;
import cm.model.Effect;
import cm.model.Power;

/**
 * Defines a renderer for a JList cell that includes a checkbox.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class CheckListRenderer extends JCheckBox implements ListCellRenderer {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 3854158611515234828L;

	/**
	 * Indicates that the item in the list is an {@link Effect}.
	 */
	public static final int EFFECT_TYPE = 0;

	/**
	 * Indicates that the item in the list is a {@link Power}.
	 */
	public static final int POWER_TYPE = 1;

	/**
	 * Stores the type of this renderer.
	 */
	private int _type;

	/**
	 * Creates a new renderer.
	 * 
	 * @param type
	 *            {@link CheckListRenderer#EFFECT_TYPE}|
	 *            {@link CheckListRenderer#POWER_TYPE}
	 */
	public CheckListRenderer(int type) {
		_type = type;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
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

	/**
	 * Returns the stored type of the renderer.
	 * 
	 * @return the stored type
	 */
	private int getType() {
		return _type;
	}

}
