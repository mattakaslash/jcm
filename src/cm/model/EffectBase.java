package cm.model;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * Defines the basic attributes of an effect (e.g., dazed, blinded, etc.) in D&D
 * 4e.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class EffectBase implements Comparable<EffectBase> {
	/**
	 * The name of the effect.
	 */
	private String _name = "";

	/**
	 * If true, the effect provides a benefit as opposed to a hindrance.
	 */
	private Boolean _beneficial = false;

	/**
	 * The {@link Duration} of the effect.
	 */
	private Duration _durationCode = Duration.None;

	/**
	 * Defines possible effect durations in D&D 4e.
	 * 
	 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
	 * @since 1.0
	 */
	public enum Duration {
		/**
		 * No duration.
		 */
		None("None", "None"),
		/**
		 * Ends with a successful saving throw.
		 */
		SaveEnds("Save Ends", "SE"),
		/**
		 * Ends at the start of the recipient's next turn.
		 */
		TargetStart("Start of Target's Next Turn", "SOT Target"),
		/**
		 * Ends at the end of the recipient's next turn.
		 */
		TargetEnd("End of Target's Next Turn", "EOT Target"),
		/**
		 * Ends at the start of the originator's next turn.
		 */
		SourceStart("Start of Source's Next Turn", "SOT Source"),
		/**
		 * Ends at the end of the originator's next turn.
		 */
		SourceEnd("End of Source's Next Turn", "EOT Source"),
		/**
		 * Ends at the end of the current turn.
		 */
		TurnEnd("End of the Current Turn", "EOT"),
		/**
		 * Ends at the end of the encounter.
		 */
		Encounter("End of the Encounter", "EOE"),
		/**
		 * The source uses an action to sustain this power.
		 */
		Sustained("Sustained", "Sus"),
		/**
		 * A catch-all for any not-covered duration.
		 */
		Special("Special", "Spec");

		private final String _abbr;
		private final String _desc;
		/**
		 * Creates a new duration.
		 * 
		 * @param desc
		 *            the description of the duration
		 * @param abbr
		 *            an abbreviation of the duration
		 */
		private Duration(String desc, String abbr) {
			this._desc = desc;
			this._abbr = abbr;
		}

		/**
		 * Returns the abbreviation of this duration.
		 * 
		 * @return the abbreviation
		 */
		public String getAbbr() {
			return this._abbr;
		}

		/**
		 * Returns the description of this duration.
		 * 
		 * @return the description
		 */
		public String getDesc() {
			return this._desc;
		}
	}

	/**
	 * An empty EffectBase.
	 */
	public EffectBase() {
		clearAll();
	}

	/**
	 * Create a new EffectBase that is a copy of an existing effect.
	 * 
	 * @param effect
	 *            the effect to copy
	 */
	public EffectBase(EffectBase effect) {
		clearAll();
		setName(effect.getName());
		setBeneficial(effect.isBeneficial());
		setDurationCode(effect.getDurationCode());
	}

	/**
	 * Create a new EffectBase with the specified properties.
	 * 
	 * @param name
	 *            the name of the effect
	 * @param duration
	 *            the {@link Duration} of the effect
	 * @param beneficial
	 *            true if this effect is beneficial to the target
	 */
	public EffectBase(String name, Duration duration, Boolean beneficial) {
		clearAll();
		setName(name);
		setBeneficial(beneficial);
		setDurationCode(duration);
	}

	/**
	 * Indicates if this effect is beneficial to the target.
	 * 
	 * @return true if beneficial
	 */
	public Boolean isBeneficial() {
		return _beneficial;
	}

	/**
	 * Set the indicator of this effect being beneficial to the target.
	 * 
	 * @param beneficial
	 *            true if beneficial
	 */
	public void setBeneficial(Boolean beneficial) {
		_beneficial = beneficial;
	}

	/**
	 * Returns a {@link Duration} indicating the effect's duration.
	 * 
	 * @return the {@link Duration}
	 */
	public Duration getDurationCode() {
		return _durationCode;
	}

	/**
	 * Sets the duration of this effect.
	 * 
	 * @param durationCode
	 *            the {@link Duration} to set
	 */
	public void setDurationCode(Duration durationCode) {
		_durationCode = durationCode;
	}

	/**
	 * Returns a concatenated string of effect properties.
	 * 
	 * @return {@link #getName()} + {@link #getDurationCode()} +
	 *         {@link #isBeneficial()}
	 */
	public String getEffectBaseID() {
		return getName() + getDurationCode() + isBeneficial();
	}

	/**
	 * Indicates whether this status effect marks the target.
	 * 
	 * @return true if this effect marks the target
	 */
	protected Boolean isMark() {
		return getName().toLowerCase().startsWith("marked");
	}

	/**
	 * Returns the name of this effect.
	 * 
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the name of this effect.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * Indicates if the effect is valid.
	 * 
	 * @return true if the effect has a defined name and duration
	 */
	public Boolean isValid() {
		return !(getName().contentEquals("") || getName() == null || getDurationCode() == Duration.None);
	}

	/**
	 * Resets all properties to default (empty) values.
	 */
	protected void clearAll() {
		setName("");
		setDurationCode(Duration.None);
		setBeneficial(false);
	}

	/**
	 * Writes the effect base properties to an XML stream.
	 * 
	 * @param writer
	 *            the stream to which to write the effect
	 * @throws XMLStreamException
	 *             from the writer
	 */
	public void exportXML(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("effectbase");

		writer.writeStartElement("name");
		writer.writeCharacters(getName());
		writer.writeEndElement();

		writer.writeStartElement("beni");
		writer.writeCharacters(isBeneficial().toString());
		writer.writeEndElement();

		writer.writeStartElement("durcode");
		writer.writeCharacters(getDurationCode().toString());
		writer.writeEndElement();

		writer.writeEndElement();
	}

	/**
	 * Sets the properties of this effect from the contents of the XML stream.
	 * 
	 * @param reader
	 *            the stream from which to read the effect
	 * @return true on success
	 * @throws XMLStreamException
	 *             from the reader
	 */
	public Boolean importXML(XMLStreamReader reader) throws XMLStreamException {
		String elementName = "";

		if (reader.isStartElement() && reader.getName().toString().contentEquals("effectbase")) {
			clearAll();

			while (reader.hasNext()) {
				reader.next();
				if (reader.isStartElement()) {
					elementName = reader.getName().toString();
				} else if (reader.isCharacters()) {
					if (elementName.contentEquals("name")) {
						setName(reader.getText());
					} else if (elementName.contentEquals("beni")) {
						setBeneficial(Boolean.valueOf(reader.getText()));
					} else if (elementName.contentEquals("durcode")) {
						setDurationCode(Duration.valueOf(reader.getText()));
					}
				} else if (reader.isEndElement()) {
					elementName = "";
					if (reader.getName().toString().contentEquals("effectbase")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(EffectBase o) {
		return getName().compareTo(o.getName());
	}
}
