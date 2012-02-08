package cm.model;

public class CheckableItem {
	private Object _o;
	private Boolean _selected;
	private String _text;
	
	/**
	 * Creates a new checkable item with the given object.
	 * @param o the object
	 */
	public CheckableItem(Object o) {
		setObject(o);
		setSelected(false);
	}

	/**
	 * Sets an indicator of this item being selected (checked).
	 * @param selected true, if this item is selected
	 */
	public void setSelected(Boolean selected) {
		_selected = selected;
	}
	
	/**
	 * Returns an indicator of this item being selected.
	 * @return true, if this item is selected
	 */
	public Boolean isSelected() {
		return _selected;
	}
	
	/**
	 * Returns this item's description.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (getText() != null) {
			return getObject().toString() + " " + getText();
		} else {
			return getObject().toString();
		}
	}

	/**
	 * Returns the item's associated object.
	 * @return the object
	 */
	public Object getObject() {
		return _o;
	}

	/**
	 * Sets the item's associated object.
	 * @param o the object
	 */
	private void setObject(Object o) {
		_o = o;
	}
	
	/**
	 * Returns the supplementary text for this item.
	 * @return the text
	 */
	private String getText() {
		return _text;
	}

	/**
	 * Sets the supplementary text to be displayed after the object's string value.
	 * @param text the text
	 */
	public void setText(String text) {
		_text = text;
	}
}
