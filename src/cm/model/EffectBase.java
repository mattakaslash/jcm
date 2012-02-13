package cm.model;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * Defines the basic attributes of an effect (e.g., dazed, blinded, etc.) in D&D
 * 4e.
 * 
 * @author matthew.rinehart
 * 
 */
public class EffectBase {
	private String _name;
	private Boolean _beneficial;
	private Duration _durationCode;

	/**
	 * Defines possible effect durations in D&D 4e.
	 * 
	 * @author matthew.rinehart
	 * 
	 */
	public enum Duration {
		None("None", null),
		SaveEnds("Save Ends", "SE"),
		TargetStart("Start of Target's Next Turn", "SOT Target"),
		TargetEnd("End of Target's Next Turn", "EOT Target"),
		SourceStart("Start of Source's Next Turn", "SOT Source"),
		SourceEnd("End of Source's Next Turn", "EOT Source"),
		TurnEnd("End of the Current Turn", "EOT"),
		Encounter("End of Encounter", "EOE"),
		Sustained("Sustained", null), 
		Special("Special", null);

		private final String _desc;
		private final String _abbr;

		/**
		 * Creates a new Duration.
		 * 
		 * @param desc
		 * @param abbr
		 */
		private Duration(String desc, String abbr) {
			this._desc = desc;
			this._abbr = abbr;
		}

		/**
		 * Returns the description of this Duration.
		 * 
		 * @see java.lang.Enum#toString()
		 */
		public String toString() {
			return this._desc;
		}

		/**
		 * Returns the abbreviation of this Duration.
		 * 
		 * @return the abbreviation
		 */
		public String getAbbr() {
			return this._abbr;
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
	 * Indicates whether this status effect marks the target.
	 * 
	 * @return true if this effect marks the target
	 */
	protected Boolean isMark() {
		return getName().toLowerCase().startsWith("marked");
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
	 * Returns a concatenated string of effect properties.
	 * 
	 * @return {@link #getName()} + {@link #getDurationCode()} +
	 *         {@link #isBeneficial()}
	 */
	public String getEffectBaseID() {
		return getName() + getDurationCode() + isBeneficial();
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

		writer.writeEndElement(); // effectbase
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

		if (reader.isStartElement()
				&& reader.getName().toString().contentEquals("effectbase")) {
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
						if (reader.getText().length() == 1) {
							// TODO: supports original; remove when no longer needed
							switch (Integer.valueOf(reader.getText())) {
							default:
							case 0:
								setDurationCode(Duration.None);
								break;
							case 1:
								setDurationCode(Duration.SaveEnds);
								break;
							case 2:
								setDurationCode(Duration.TargetStart);
								break;
							case 3:
								setDurationCode(Duration.TargetEnd);
								break;
							case 4:
								setDurationCode(Duration.SourceStart);
								break;
							case 5:
								setDurationCode(Duration.SourceEnd);
								break;
							case 6:
								setDurationCode(Duration.TurnEnd);
								break;
							case 7:
								setDurationCode(Duration.Encounter);
								break;
							case 8:
								setDurationCode(Duration.Sustained);
								break;
							case 9:
								setDurationCode(Duration.Special);
								break;
							}
						} else {
							setDurationCode(Duration.valueOf(reader.getText()));
						}
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
}
