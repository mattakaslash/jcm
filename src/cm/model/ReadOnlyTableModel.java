package cm.model;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ReadOnlyTableModel extends DefaultTableModel implements TableModel {
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public ReadOnlyTableModel(Object[][] object, String[] strings) {
		super(object, strings);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
