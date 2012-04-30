package cm.model;

import java.awt.Color;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cm.util.Colors;

/**
 * Defines a D&D 4e creature power.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class Power {
	/**
	 * The name of the power.
	 */
	private String _name = "";

	/**
	 * A single-character type code derived from usage; e.g., melee basic is
	 * 'm'.
	 */
	private String _typeCode = "";

	/**
	 * The power's action, e.g., standard; at-will
	 */
	private String _action = "";

	/**
	 * The power's keywords.
	 */
	private String _keywords = "";

	/**
	 * A coded form of the power's description.
	 */
	private String _descCoded = "";

	/**
	 * The compendium URL for the power.
	 */
	private String _url = "";

	/**
	 * The size of the power's aura.
	 */
	private Integer _aura = 0;

	/**
	 * A blank power.
	 */
	public Power() {
		clearAll();
	}

	/**
	 * A new power with the provided properties.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the full type
	 * @param action
	 *            the action
	 * @param keywords
	 *            the keywords
	 * @param desc
	 *            the description
	 * @param aura
	 *            the size of the aura
	 */
	public Power(String name, String type, String action, String keywords, String desc, Integer aura) {
		setName(name);
		setType(type);
		setAction(action);
		setKeywords(keywords);
		setDesc(desc);
		setAura(aura);
	}

	/**
	 * A power created as a copy of another power.
	 * 
	 * @param pow
	 *            the source power
	 */
	public Power(Power pow) {
		copy(pow);
	}

	/**
	 * Returns the power's action.
	 * 
	 * @return the action
	 */
	public String getAction() {
		return _action;
	}

	/**
	 * Sets the power's action.
	 * 
	 * @param action
	 *            the action
	 */
	public void setAction(String action) {
		_action = action;
	}

	/**
	 * Returns recharge dice in the DnD4Attack font.
	 * 
	 * @return recharge dice
	 */
	private String getActionDiceHTML() {
		String out = "";
		out += getAction();
		out = out.replace("recharge 6", "recharge <font face='DnD4Attack'>6</font>");
		out = out.replace("recharge 5", "recharge <font face='DnD4Attack'>5 6</font>");
		out = out.replace("recharge 4", "recharge <font face='DnD4Attack'>4 5 6</font>");
		out = out.replace("recharge 3", "recharge <font face='DnD4Attack'>3 4 5 6</font>");
		out = out.replace("recharge 2", "recharge <font face='DnD4Attack'>2 3 4 5 6</font>");
		return out;
	}

	/**
	 * Returns an action line, indicating the power usage frequency or
	 * aura-ness.
	 * 
	 * @return the action line
	 */
	public String getActionLine() {
		String line = "";

		if (isAura()) {
			line += "aura " + getAura().toString();
		} else if (getAction().isEmpty()) {
			return "  ";
		} else if (getAction().toLowerCase().contains("recharges")) {
			line += "recharge *";
		} else if (_action.toLowerCase().contains("recharge ")) {
			line += "recharge ";
			String recharge = getAction().substring(getAction().indexOf("recharge") + 9).replaceAll("[^0-9]*", "");
			if (!recharge.isEmpty() && Integer.valueOf(recharge) > 0) {
				line += getAction().substring(getAction().indexOf("recharge") + 9);
			} else {
				line += "*";
			}
		} else if (getAction().toLowerCase().contains("encounter")) {
			line += "encounter";
		} else if (getAction().toLowerCase().contains("at-will")) {
			line += "at-will";
		} else if (getAction().toLowerCase().contains("daily")) {
			line += "daily";
		} else if (getAction().toLowerCase().contains("item")) {
			line += "item";
		} else {
			line += "special";
		}

		return " (" + line + ")";
	}

	/**
	 * Indicates if the power is an action point place-holder.
	 * 
	 * @return true if the power's name contains "action point"
	 */
	public Boolean isActionPoint() {
		return (getName().toLowerCase().contains("action point"));
	}

	/**
	 * Indicates if the power is an aura.
	 * 
	 * @return true if there is a defined aura for this power
	 */
	public Boolean isAura() {
		return (getAura() > 0);
	}

	/**
	 * Returns the size of the power's aura.
	 * 
	 * @return the aura size
	 */
	public Integer getAura() {
		return _aura;
	}

	/**
	 * Sets the size of the power's aura.
	 * 
	 * @param aura
	 *            the aura size
	 */
	public void setAura(Integer aura) {
		_aura = aura;
	}

	/**
	 * Returns the background {@link Color}.
	 * 
	 * @return the background {@link Color}
	 */
	public Color getBackColor() {
		if (isAura()) {
			return Color.BLUE;
		}
		
		if (isItem()) {
			return Colors.ITEM;
		}

		if (getAction().toLowerCase().contains("recharge")) {
			return Color.ORANGE;
		} else if (getAction().toLowerCase().contains("encounter")) {
			return Colors.ENCOUNTER;
		} else if (getAction().toLowerCase().contains("daily")) {
			return Colors.DAILY;
		} else if (getAction().toLowerCase().contains("at-will")) {
			return Colors.ATWILL;
		} else {
			return Color.GRAY;
		}
	}

	/**
	 * Returns the description of the power.
	 * 
	 * @return the power description
	 */
	public String getDesc() {
		return getDescCoded().replace("###", "\n");
	}

	/**
	 * Sets the power's description.
	 * 
	 * @param value
	 *            the description
	 */
	public void setDesc(String value) {
		setDescCoded(value.replace("\n", "###"));
	}

	/**
	 * Returns the coded description.
	 * 
	 * @return the coded description
	 */
	private String getDescCoded() {
		return _descCoded;
	}

	/**
	 * Sets the coded description.
	 * 
	 * @param descCoded
	 *            the coded description
	 */
	private void setDescCoded(String descCoded) {
		_descCoded = descCoded;
	}

	/**
	 * Returns an HTML version of the power description with dice expressions
	 * hyperlinked.
	 * 
	 * @return an HTML-description
	 */
	public String getDescHTML() {
		String out = getDescCoded();
		return out.replaceAll("###", "<br>").replaceAll("(([0-9]+)d([0-9]+))? *?[+] *?([0-9]+)", "<a href=\"#$0\">$0</a>");
	}

	/**
	 * Indicates if the power is a daily usage frequency.
	 * 
	 * @return true if the power's action contains "daily"
	 */
	public Boolean isDaily() {
		return (getAction().toLowerCase().contains("daily"));
	}

	/**
	 * Indicates if the power is an item power.
	 * 
	 * @return true if the power's action contains "item"
	 */
	public Boolean isItem() {
		return (getAction().toLowerCase().contains("item"));
	}

	/**
	 * Returns the foreground {@link Color}.
	 * 
	 * @return the foreground {@link Color}
	 */
	public Color getForeColor() {
		if (isAura()) {
			return Color.WHITE;
		}

		if (getAction().toLowerCase().contains("recharge")) {
			return Color.WHITE;
		} else if (getAction().toLowerCase().contains("encounter")) {
			return Color.WHITE;
		} else if (getAction().toLowerCase().contains("daily")) {
			return Color.WHITE;
		} else if (getAction().toLowerCase().contains("at-will")) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}

	/**
	 * Returns the power keywords.
	 * 
	 * @return the keywords
	 */
	public String getKeywords() {
		return _keywords;
	}

	/**
	 * Sets the power's keywords.
	 * 
	 * @param keywords
	 *            the keywords
	 */
	public void setKeywords(String keywords) {
		_keywords = keywords;
	}

	/**
	 * Returns the power name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the power's name.
	 * 
	 * @param name
	 *            the name
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * Returns HTML-formatted power details.
	 * 
	 * @return HTML power details
	 */
	public String getPowerHTML() {
		String out = "";

		if (getAura() > 0) {
			out += "<div class='ggmed'><b>" + _name + "</b>";
			if (!getKeywords().isEmpty()) {
				out += " (" + getKeywords() + ")";
			}
			out += " Aura " + getAura().toString();
			out += "</div>";
			out += "<div class='gglt'><div class='ggindent'>";
			out += getDescHTML();
			out += "</div></div>";
		} else {
			out += "<div class='ggmed'>";
			if (!getTypeCode().isEmpty()) {
				out += "<font face='DnD4Attack'>" + getTypeCode() + "</font> ";
			}
			out += "<b>" + getName().replace("*", "&bull;") + "</b>";
			if (!getAction().isEmpty()) {
				out += " (" + getActionDiceHTML() + ")";
			}
			if (!getKeywords().isEmpty()) {
				out += " &bull;  " + getKeywords();
			}
			out += "</div>";
			if (!getDescCoded().isEmpty()) {
				out += "<div class='gglt'><div class='ggindent'>";
				out += getDescHTML();
				out += "</div></div>";
			}
		}
		return out.replaceAll("\t", "&nbsp;&nbsp;&nbsp;");
	}

	/**
	 * Returns the required d6 result to recharge this power.
	 * 
	 * @return the recharge value
	 */
	public Integer getRechargeVal() {
		if (getAction() == null || getAction().contentEquals("")) {
			return 0;
		} else if (getAction().toLowerCase().contains("recharge 2")) {
			return 2;
		} else if (getAction().toLowerCase().contains("recharge 3")) {
			return 3;
		} else if (getAction().toLowerCase().contains("recharge 4")) {
			return 4;
		} else if (getAction().toLowerCase().contains("recharge 5")) {
			return 5;
		} else if (getAction().toLowerCase().contains("recharge 6")) {
			return 6;
		} else {
			return 0;
		}
	}

	/**
	 * Returns the power type, e.g. "Basic Melee", "Close", etc.
	 * 
	 * @return the power type
	 */
	public String getType() {
		if (isAura()) {
			return "Aura " + _aura.toString();
		} else if (getTypeCode().contentEquals("m")) {
			return "Basic Melee";
		} else if (getTypeCode().contentEquals("M")) {
			return "Melee";
		} else if (getTypeCode().contentEquals("r")) {
			return "Basic Ranged";
		} else if (getTypeCode().contentEquals("R")) {
			return "Ranged";
		} else if (getTypeCode().contentEquals("A")) {
			return "Area";
		} else if (getTypeCode().contentEquals("C")) {
			return "Close";
		} else if (getTypeCode().isEmpty()) {
			return "(no icon)";
		} else {
			return "???";
		}
	}

	/**
	 * Sets the type of the power.
	 * 
	 * @param value
	 *            the type
	 */
	public void setType(String value) {
		String type = value.trim().toLowerCase();

		if (type.startsWith("aura")) {
			setAura(Integer.valueOf(type.replace("aura", "").trim()));
			setTypeCode("");
		} else if (type.contentEquals("basic melee")) {
			setTypeCode("m");
		} else if (type.contentEquals("melee")) {
			setTypeCode("M");
		} else if (type.contentEquals("basic ranged")) {
			setTypeCode("r");
		} else if (type.contentEquals("ranged")) {
			setTypeCode("R");
		} else if (type.contentEquals("area")) {
			setTypeCode("A");
		} else if (type.contentEquals("close")) {
			setTypeCode("C");
		} else {
			setTypeCode("");
		}
	}

	/**
	 * Returns the single-letter type code for the power. For example, a basic
	 * ranged attack has code 'r'.
	 * 
	 * @return the type code
	 */
	private String getTypeCode() {
		return _typeCode;
	}

	/**
	 * Sets the type code of the power.
	 * 
	 * @param typeCode
	 *            the type code
	 */
	private void setTypeCode(String typeCode) {
		_typeCode = typeCode;
	}

	/**
	 * Returns the power's information URL.
	 * 
	 * @return the URL
	 */
	public String getURL() {
		return _url;
	}

	/**
	 * Sets the power's URL.
	 * 
	 * @param url
	 *            the URL
	 */
	public void setURL(String url) {
		_url = url;
	}

	/**
	 * Resets all power properties to default values (blank).
	 */
	private void clearAll() {
		setName("(unnamed power)");
		setTypeCode("");
		setAction("");
		setKeywords("");
		setDesc("(no description)");
		setAura(0);
	}

	/**
	 * Sets this power's properties from another power.
	 * 
	 * @param pow
	 *            the other power
	 */
	private void copy(Power pow) {
		setName(pow.getName());
		setTypeCode(pow.getTypeCode());
		setAction(pow.getAction());
		setKeywords(pow.getKeywords());
		setDescCoded(pow.getDescCoded());
		setURL(pow.getURL());
		setAura(pow.getAura());
	}

	/**
	 * Writes the power to an XML stream.
	 * 
	 * @param writer
	 *            the XML stream
	 * @throws XMLStreamException
	 *             from the writer
	 */
	public void exportXML(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("power");

		writer.writeStartElement("name");
		writer.writeCharacters(getName());
		writer.writeEndElement();

		writer.writeStartElement("type");
		writer.writeCharacters(getType());
		writer.writeEndElement();

		writer.writeStartElement("act");
		writer.writeCharacters(getAction());
		writer.writeEndElement();

		writer.writeStartElement("key");
		writer.writeCharacters(getKeywords());
		writer.writeEndElement();

		writer.writeStartElement("desc");
		writer.writeCharacters(getDescCoded());
		writer.writeEndElement();

		writer.writeStartElement("url");
		writer.writeCharacters(getURL());
		writer.writeEndElement();

		writer.writeEndElement();
	}

	/**
	 * Sets this power's properties from an XML stream.
	 * 
	 * @param reader
	 *            the XML stream
	 * @return true on success
	 * @throws XMLStreamException
	 *             from the reader
	 */
	public Boolean importXML(XMLStreamReader reader) throws XMLStreamException {
		String elementName = "";

		if (reader.isStartElement() && reader.getName().toString().contentEquals("power")) {
			clearAll();

			while (reader.hasNext()) {
				reader.next();

				if (reader.isStartElement()) {
					elementName = reader.getName().toString();
				} else if (reader.isCharacters()) {
					if (elementName.contentEquals("name")) {
						setName(reader.getText());
					} else if (elementName.contentEquals("type")) {
						setType(reader.getText());
					} else if (elementName.contentEquals("act")) {
						setAction(reader.getText());
					} else if (elementName.contentEquals("key")) {
						setKeywords(reader.getText());
					} else if (elementName.contentEquals("desc")) {
						setDescCoded(reader.getText());
					} else if (elementName.contentEquals("url")) {
						setURL(reader.getText());
					}
				} else if (reader.isEndElement()) {
					elementName = "";
					if (reader.getName().toString().contentEquals("power")) {
						return true;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getName();
	}
}
