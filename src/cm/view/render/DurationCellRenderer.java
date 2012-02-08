package cm.view.render;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.EffectBase.Duration;

public class DurationCellRenderer extends JLabel implements ListCellRenderer {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(((Duration)value).toString());
		return this;
	}

}
