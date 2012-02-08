package cm.view.render;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CenteredTableCellRenderer extends DefaultTableCellRenderer
		implements TableCellRenderer {
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public CenteredTableCellRenderer() {
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}
