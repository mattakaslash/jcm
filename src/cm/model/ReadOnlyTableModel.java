package cm.model;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Defines a table model with editing disabled.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class ReadOnlyTableModel extends DefaultTableModel implements TableModel {
	/**
	 * a generate serial ID
	 */
	private static final long serialVersionUID = 2008567134774809488L;

	/**
	 * Creates the table model.
	 * 
	 * @param object
	 *            the data
	 * @param strings
	 *            the headers
	 */
	public ReadOnlyTableModel(Object[][] object, String[] strings) {
		super(object, strings);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
