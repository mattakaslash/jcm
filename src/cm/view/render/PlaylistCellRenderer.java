package cm.view.render;

import java.awt.Color;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import cm.model.Settings;

/**
 * Renders a cell of the playlist dropdown.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 2.0
 */
public class PlaylistCellRenderer implements ListCellRenderer {

	@Override
	public JLabel getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel text = new JLabel();
		text.setOpaque(true);
		
		File dir = (File) value;
		String root = "";
		
		if (Settings.getMusicDirectory() != null) {
			root = Settings.getMusicDirectory().getAbsolutePath();
		}
		
		if (dir != null) {
			text.setText(dir.getAbsolutePath().replace(root, ""));
		}
		
		if (isSelected) {
			text.setBackground(Color.DARK_GRAY);
			text.setForeground(Color.WHITE);
		}
		
		return text;
	}

}
