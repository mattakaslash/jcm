package cm.model;

import cm.view.EffectWin;
import cm.view.RechargeWin;

/**
 * Implements a checkable item. Used in {@link EffectWin} and
 * {@link RechargeWin}.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class CheckableItem {
	/**
	 * The object associated with this item.
	 */
	private Object _o;

	/**
	 * A flag indicating if the item is selected/checked.
	 */
	private Boolean _selected;

	/**
	 * Display text for the item in the UI.
	 */
	private String _text;

	/**
	 * Creates a new checkable item with the given object.
	 * 
	 * @param o
	 *            the object
	 */
	public CheckableItem(Object o) {
		setObject(o);
		setSelected(false);
	}

	/**
	 * Returns the item's associated object.
	 * 
	 * @return the object
	 */
	public Object getObject() {
		return _o;
	}

	/**
	 * Sets the item's associated object.
	 * 
	 * @param o
	 *            the object
	 */
	private void setObject(Object o) {
		_o = o;
	}

	/**
	 * Returns an indicator of this item being selected.
	 * 
	 * @return true, if this item is selected
	 */
	public Boolean isSelected() {
		return _selected;
	}

	/**
	 * Sets an indicator of this item being selected (checked).
	 * 
	 * @param selected
	 *            true, if this item is selected
	 */
	public void setSelected(Boolean selected) {
		_selected = selected;
	}

	/**
	 * Returns the supplementary text for this item.
	 * 
	 * @return the text
	 */
	public String getText() {
		return _text;
	}

	/**
	 * Sets the supplementary text to be displayed after the object's string
	 * value.
	 * 
	 * @param text
	 *            the text
	 */
	public void setText(String text) {
		_text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (getText() != null) {
			return getObject().toString() + " " + getText();
		} else {
			return getObject().toString();
		}
	}
}
