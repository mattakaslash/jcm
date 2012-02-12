package cm.model;

public class FighterPower {
	private Combatant _fighter;
	private Power _power;
	
	public Combatant getFighter() {
		return _fighter;
	}
	private void setFighter(Combatant fighter) {
		_fighter = fighter;
	}
	public Power getPower() {
		return _power;
	}
	private void setPower(Power power) {
		_power = power;
	}
	
	public FighterPower(Combatant fighter, Power power) {
		setFighter(fighter);
		setPower(power);
	}
}
