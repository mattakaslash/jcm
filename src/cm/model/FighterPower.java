package cm.model;

/**
 * Pairs a fighter and the fighter's power.
 * 
 * @author Matthew Rinehart &lt;gomamon2k at yahoo.com&gt;
 * @since 1.0
 */
public class FighterPower {
	/**
	 * The fighter.
	 */
	private Combatant _fighter;
	
	/**
	 * The power. 
	 */
	private Power _power;
	
	/**
	 * Creates a pairing between fighter and power.
	 * @param fighter the fighter
	 * @param power the power
	 */
	public FighterPower(Combatant fighter, Power power) {
		setFighter(fighter);
		setPower(power);
	}

	/**
	 * Returns the fighter.
	 * @return the fighter
	 */
	public Combatant getFighter() {
		return _fighter;
	}
	
	/**
	 * Sets the fighter.
	 * @param fighter the fighter
	 */
	private void setFighter(Combatant fighter) {
		_fighter = fighter;
	}
	
	/**
	 * Returns the power.
	 * @return the power
	 */
	public Power getPower() {
		return _power;
	}
	
	/**
	 * Sets the power.
	 * @param power the power
	 */
	private void setPower(Power power) {
		_power = power;
	}
}
