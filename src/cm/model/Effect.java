package cm.model;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * Defines an effect in place on a combatant during an encounter.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class Effect extends EffectBase {
	/**
	 * The combat handle for the effect's source.
	 */
	private String _sourceHandle = "";

	/**
	 * The combat handle for the effect's target.
	 */
	private String _targetHandle = "";

	/**
	 * An identifier for the effect within the encounter.
	 */
	private Integer _effectID = 0;

	/**
	 * The initiative sequence at which this effect ends.
	 */
	private Integer _endInitSeq = 0;

	/**
	 * The round in which this effect ends.
	 */
	private Integer _roundTill = 0;

	/**
	 * If true, indicates the effect should be hidden from the initiative
	 * display.
	 */
	private Boolean _hidden = false;

	/**
	 * A blank effect.
	 */
	public Effect() {
		clearAll();
	}

	/**
	 * Creates a new effect with the specified properties.
	 * 
	 * @param name
	 *            the name of the effect
	 * @param ID
	 *            an identifier for the effect (used in Hashtables, etc.)
	 * @param source
	 *            the {@link Combatant#getCombatHandle()} of the effect's source
	 * @param target
	 *            the {@link Combatant#getCombatHandle()} of the effect's target
	 * @param roundTill
	 *            the round the effect expires
	 * @param dur
	 *            the {@link Duration} of the effect
	 * @param beneficial
	 *            true if the effect is beneficial to its target
	 */
	public Effect(String name, Integer ID, String source, String target, Integer roundTill, Duration dur, Boolean beneficial) {
		clearAll();
		setName(name);
		setEffectID(ID);
		setSourceHandle(source);
		setTargetHandle(target);
		setDurationCode(dur);
		setRoundTill(roundTill);
		setBeneficial(beneficial);
	}

	/**
	 * Indicates if the effect is active for the specified init sequence.
	 * 
	 * @param currentSeq
	 *            the initiative sequence of combat
	 * @return true, if 0 < {@link #getEndInitSeq()} <= currentSeq
	 */
	public Boolean isActive(Integer currentSeq) {
		return !(getEndInitSeq() > 0 && getEndInitSeq() <= currentSeq);
	}

	/**
	 * Sets the effect as active.
	 */
	public void setActive() {
		setEndInitSeq(0);
	}

	/**
	 * Sets the effect inactive as of the specified init sequence.
	 * 
	 * @param currentSeq
	 *            the initiative sequence
	 */
	public void setInactive(Integer currentSeq) {
		setEndInitSeq(currentSeq);
	}

	/**
	 * Returns {@link EffectBase#isBeneficial()}.
	 * 
	 * @return {@link EffectBase#isBeneficial()}
	 */
	@Override
	public Boolean isBeneficial() {
		return super.isBeneficial();
	}

	/**
	 * Returns a textual description of the effect.
	 * 
	 * @return the description
	 */
	public String getDesc() {
		String value = new String("");
		switch (getDurationCode()) {
		case SaveEnds:
			value += "Save Ends";
			break;
		case TargetStart:
			value += "Until start of Target's round " + getRoundTill();
			break;
		case TargetEnd:
			value += "Until end of Target's round " + getRoundTill();
			break;
		case SourceStart:
			value += "Until start of Source's round " + getRoundTill();
			break;
		case SourceEnd:
			value += "Until end of Source's round " + getRoundTill();
			break;
		case TurnEnd:
			value += "Until end of Source's ";
			if (getRoundTill() > 0) {
				value += "round " + getRoundTill();
			} else {
				value += "surprise round";
			}
			break;
		case Encounter:
			value += "Until the end of Encounter";
			break;
		case Sustained:
			value += "Sustained";
			break;
		case Special:
			value += "Special";
			break;
		default:
			value += "Duration unknown";
			break;
		}
		return value;
	}

	/**
	 * Returns the effect's numeric ID.
	 * 
	 * @return the effect ID
	 */
	public Integer getEffectID() {
		return _effectID;
	}

	/**
	 * Sets the effect's unique ID.
	 * 
	 * @param id
	 *            the ID
	 */
	public void setEffectID(Integer id) {
		_effectID = id;
	}

	/**
	 * Returns the initiative sequence at which this effect expires.
	 * 
	 * @return the init sequence
	 */
	public Integer getEndInitSeq() {
		return _endInitSeq;
	}

	/**
	 * Sets the initiative sequence at which this effect expires.
	 * 
	 * @param seq
	 *            the init sequence
	 */
	public void setEndInitSeq(Integer seq) {
		_endInitSeq = seq;
	}

	/**
	 * Indicates if the effect should be hidden from the init display.
	 * 
	 * @return true, if the effect should be hidden
	 */
	public Boolean isHidden() {
		return _hidden;
	}

	/**
	 * Sets the indicator of hiding the status from the init display.
	 * 
	 * @param hidden
	 *            true, if the effect should be hidden
	 */
	public void setHidden(Boolean hidden) {
		_hidden = hidden;
	}

	/**
	 * Gets the round in which the effect expires.
	 * 
	 * @return the round of combat
	 */
	public Integer getRoundTill() {
		return _roundTill;
	}

	/**
	 * Sets the round in which the effect expires.
	 * 
	 * @param roundTill
	 *            the round of combat
	 */
	public void setRoundTill(Integer roundTill) {
		_roundTill = roundTill;
	}

	/**
	 * Returns the handle of the effect's source.
	 * 
	 * @return the source handle
	 */
	public String getSourceHandle() {
		return _sourceHandle;
	}

	/**
	 * Sets the source for this effect.
	 * 
	 * @param source
	 *            the source's {@link Combatant#getCombatHandle()}
	 */
	public void setSourceHandle(String source) {
		_sourceHandle = source;
	}

	/**
	 * Returns the target's combat handle.
	 * 
	 * @return the target handle
	 */
	public String getTargetHandle() {
		return _targetHandle;
	}

	/**
	 * Sets the target for this effect.
	 * 
	 * @param target
	 *            the target's {@link Combatant#getCombatHandle()}
	 */
	public void setTargetHandle(String target) {
		_targetHandle = target;
	}

	/**
	 * Indicates if the effect is valid.
	 * 
	 * @return true, if the effect base is valid and the source, target, and
	 *         effect ID are set
	 * @see cm.model.EffectBase#isValid()
	 */
	@Override
	public Boolean isValid() {
		return (super.isValid() && !getSourceHandle().contentEquals("") && !getTargetHandle().contentEquals("") && getEffectID() != 0);
	}

	/**
	 * Resets all effect properties to defaults (empty).
	 * 
	 * @see cm.model.EffectBase#clearAll()
	 */
	@Override
	protected void clearAll() {
		super.clearAll();
		setSourceHandle("");
		setTargetHandle("");
		setRoundTill(0);
		setEffectID(0);
		setEndInitSeq(0);
	}

	/**
	 * Writes the effect to an XML stream.
	 * 
	 * @param writer
	 *            the stream to which to write
	 * @throws XMLStreamException
	 *             from the writer
	 * @see cm.model.EffectBase#exportXML(javax.xml.stream.XMLStreamWriter)
	 */
	@Override
	public void exportXML(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("effect");

		super.exportXML(writer);

		writer.writeStartElement("sTarget");
		writer.writeCharacters(getTargetHandle());
		writer.writeEndElement();

		writer.writeStartElement("sSource");
		writer.writeCharacters(getSourceHandle());
		writer.writeEndElement();

		writer.writeStartElement("nRoundTill");
		writer.writeCharacters(getRoundTill().toString());
		writer.writeEndElement();

		writer.writeStartElement("nEffectID");
		writer.writeCharacters(getEffectID().toString());
		writer.writeEndElement();

		writer.writeStartElement("nEndInitSeq");
		writer.writeCharacters(getEndInitSeq().toString());
		writer.writeEndElement();

		writer.writeEndElement();
	}

	/**
	 * Sets the properties of this effect from an XML stream.
	 * 
	 * @param reader
	 *            the reader from which to read the object
	 * @returns true on success
	 * @throws XMLStreamException
	 *             from the reader
	 * @see cm.model.EffectBase#importXML(javax.xml.stream.XMLStreamReader)
	 */
	@Override
	public Boolean importXML(XMLStreamReader reader) throws XMLStreamException {
		String elementName = "";

		if (reader.isStartElement() && reader.getName().toString().contentEquals("effect")) {
			clearAll();

			while (reader.hasNext()) {
				reader.next();
				if (reader.isStartElement()) {
					elementName = reader.getName().toString();
				} else if (reader.isCharacters()) {
					if (elementName.contentEquals("effectbase")) {
						super.importXML(reader);
					} else if (elementName.contentEquals("sTarget")) {
						setTargetHandle(reader.getText());
					} else if (elementName.contentEquals("sSource")) {
						setSourceHandle(reader.getText());
					} else if (elementName.contentEquals("nRoundTill")) {
						setRoundTill(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nEffectID")) {
						setEffectID(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nEndInitSeq")) {
						setEndInitSeq(Integer.valueOf(reader.getText()));
					}
				} else if (reader.isEndElement() && reader.getName().toString().contentEquals("effectbase")) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}
}
