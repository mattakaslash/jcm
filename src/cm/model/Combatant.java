package cm.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import cm.util.DiceBag;

/**
 * Defines a Combatant in a D&D 4e encounter.
 * @author matthew.rinehart
 *
 */
/**
 * @author matthew.rinehart
 *
 */
public class Combatant implements Comparable<Combatant> {
	// Saved data
	private Stats _stats = new Stats();
	private String _customName = "";
	private String _mod = "";
	private String _combatNotesCoded = "";

	// Encounter-specific data
	private Integer _currHP = 0, _deathSaveFailed = 0, _tempHP = 0,
			_surgesRemaining = 0, _round = 0, _initRoll = 0, _random3 = 0,
			_fighterNumber = 0;
	private String _roundStatus = "";
	private Boolean _ready = false;
	private Boolean _shown = true;
	private List<String> _powersUsed = new ArrayList<String>();

	/**
	 * Blank Combatant
	 */
	public Combatant() {
		clearAll();
		setStats(new Stats());
		setRoleMod("");
	}

	/**
	 * Combatant with provided stats
	 * 
	 * @param stats
	 *            A Stats object for the combatant
	 */
	public Combatant(Stats stats) {
		clearAll();
		setStats(stats);
		setRoleMod("");
		resetInit();
		resetHealth();
	}

	/**
	 * Combatant with provided stats and role
	 * 
	 * @param stats
	 *            A Stats object for the combatant
	 * @param rolemod
	 *            The role mod for the combatant
	 */
	public Combatant(Stats stats, String rolemod) {
		clearAll();
		setStats(stats);
		setRoleMod(rolemod);
		resetInit();
		resetHealth();
	}

	/**
	 * Indicates if the Combatant is active.
	 * @return {@link #getMaxHP()} < 1 || {@link #getCurrHP()} > 0
	 */
	public Boolean isActive() {
		return (getMaxHP() < 1 || getCurrHP() > 0);
	}

	/**
	 * Indicates if the Combatant is alive.
	 * @return true if Combatant is alive
	 */
	public Boolean isAlive() {
		if (getMaxHP() < 1) {
			return true;
		}
	
		if (!isPC()) {
			return isActive();
		}
	
		if (getCurrHP() > (-1 * getBloodyHP()) && getDeathSaveFailed() < 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Indicates if the Combatant is bloody.
	 * @return true if Combatant is bloody, but conscious
	 */
	public Boolean isBloody() {
		return (getCurrHP() <= getBloodyHP() && isAlive());
	}

	/**
	 * @return {@link Stats#getBloodyHP()

	 */
	private Integer getBloodyHP() {
		return getStats().getBloodyHP();
	}

	/**
	 * Gives a combat handle in the format name-#
	 * 
	 * @return {@link #getName()}-{@link #getFighterNumber()}
	 */
	public String getCombatHandle() {
		if (getFighterNumber() > 0) {
			return getName().concat("-").concat(getFighterNumber().toString());
		} else {
			return getName();
		}
	}

	/**
	 * @return stored combat notes
	 */
	private String getCombatNotes() {
		return getCombatNotesCoded().replace("###", "\n");
	}

	/**
	 * Stores the provided value as combat notes for this Combatant.
	 * @param combatNotes the combat notes
	 */
	private void setCombatNotes(String combatNotes) {
		setCombatNotesCoded(combatNotes.replace("\n", "###"));
	}

	/**
	 * @return current HP
	 */
	public Integer getCurrHP() {
		return _currHP;
	}

	/**
	 * Sets the current HP of the Combatant. During an encounter,
	 * {@link #damage(Integer)} and {@link #heal(Integer)} should be used to
	 * adjust HP instead.
	 * 
	 * @param HP
	 */
	private void setCurrHP(Integer HP) {
		_currHP = HP;
	}

	/**
	 * Returns the custom name of this Combatant
	 * 
	 * @return the custom name
	 */
	private String getCustomName() {
		return _customName;
	}

	/**
	 * Sets the custom name of this Combatant.
	 * 
	 * @param customName
	 *            the new custom name
	 */
	private void setCustomName(String customName) {
		_customName = customName;
	}

	/**
	 * @return count of failed death saves
	 */
	private Integer getDeathSaveFailed() {
		return _deathSaveFailed;
	}

	/**
	 * Sets the count of failed death saves. During an encounter,
	 * {@link #failDeathSave()} and {@link #unfailDeathSave()} should be used
	 * instead.
	 * 
	 * @param count
	 *            the new count of failed death saves
	 */
	private void setDeathSaveFailed(Integer count) {
		_deathSaveFailed = count;
	}

	/**
	 * If the player is bloodied, returns "(dying #/3 strikes)"
	 * 
	 * @return a string indicating death status
	 */
	public String getDeathStatus() {
		if (isBloody()) {
			if (getDeathSaveFailed() < 3) {
				return "(dying " + getDeathSaveFailed() + "/3 strikes)";
			}
		}
		return "";
	}

	/**
	 * Indicates if the Combatant is dying or dead.
	 * @return {@link #getDeathSaveFailed()} > 0
	 */
	public Boolean isDyingOrDead() {
		return (getDeathSaveFailed() > 0);
	}

	/**
	 * Returns the fighter's number.
	 * 
	 * @return fighter number
	 */
	public Integer getFighterNumber() {
		return _fighterNumber;
	}

	/**
	 * Sets the fighter number.
	 * @param fighterNumber the new number
	 */
	public void setFighterNumber(int fighterNumber) {
		_fighterNumber = fighterNumber;
	}

	/**
	 * Returns the handle from the stats.
	 * 
	 * @return {@link Stats#getHandle()}
	 */
	public String getHandle() {
		return getStats().getHandle();
	}

	/**
	 * Current HP, formatted as either 'Minion' or 'x/y+z' or 'Dead (x/y)'.
	 * 
	 * @return a string indicating current HP
	 */
	private String getHPStatus() {
		String value = "";
		if (isAlive()) {
			if (getMaxHP() == 1) {
				value = "Minion";
			} else {
				value = getCurrHP() + "/" + getMaxHP();
			}
			if (_tempHP > 0) {
				value = value.concat("+" + getTempHP());
			}
		} else {
			value = "Dead (" + _currHP + "/" + getMaxHP() + ")";
		}
		return value;
	}

	/**
	 * Returns the initiative roll.
	 * 
	 * @return initiative roll
	 */
	public Integer getInitRoll() {
		return _initRoll;
	}

	/**
	 * Sets the initiative roll result.
	 * @param initRoll the new initiative roll result
	 */
	public void setInitRoll(Integer initRoll) {
		_initRoll = initRoll;
	}

	/**
	 * Returns a value calculated from {@link #getRound()},
	 * {@link #getInitRoll()}, and {@link #getRandom3()}. This value is used to
	 * help sort Combatants by initiatve.
	 * 
	 * @return an integer designed to help sort Combatants by initiative
	 */
	public Integer getInitSequence() {
		if (getRandom3() == 0) {
			return 0;
		} else {
			return (getRound() * 10000000) + ((95 - getInitRoll()) * 100000)
					+ getRandom3();
		}
	}

	/**
	 * Returns a string by which the Combatant is sorted in the initiative list.
	 * 
	 * @return a prefix attached to {@link #getCombatHandle()}
	 */
	public String getInitSort() {
		String prefix = "";

		if (getInitStatus().contentEquals("Rolled")) {
			return String.format("%010d", getInitSequence());
		} else if (getInitStatus().contentEquals("Delay")) {
			prefix = "95";
		} else if (getInitStatus().contentEquals("Ready")) {
			prefix = "96";
		} else if (getInitStatus().contentEquals("Inactive")) {
			prefix = "97";
		} else if (getInitStatus().contentEquals("Rolling")) {
			prefix = "98";
		} else {
			prefix = "99";
		}

		if (isPC()) {
			prefix = prefix.concat("0");
		} else {
			prefix = prefix.concat("1");
		}

		return prefix.concat(getCombatHandle());
	}

	/**
	 * Returns a string indicating initiative status.
	 * 
	 * @return one of 'Inactive', 'Ready', 'Delay', 'Rolling', 'Rolled', or
	 *         'Reserve'
	 */
	public String getInitStatus() {
		if (!isActive() && !isPC()) {
			return "Inactive";
		} else if (getRoundStatus().contentEquals("Ready")
				|| getRoundStatus().contentEquals("Delay")
				|| getRoundStatus().contentEquals("Rolling")) {
			return getRoundStatus();
		} else if (getInitSequence() > 0) {
			return "Rolled";
		} else {
			return "Reserve";
		}
	}

	/**
	 * Sets initiative status to the specified value.
	 * 
	 * @param value
	 *            the initiative status
	 */
	public void setInitStatus(String value) {
		if (value.contentEquals("Ready") || value.contentEquals("Delay")
				|| value.contentEquals("Rolling")) {
			setRoundStatus(value);
		} else if (getInitSequence() > 0) {
			setRoundStatus("Rolled");
		} else {
			setRoundStatus("Reserve");
		}
	}

	/**
	 * Returns the Combatant's max HP, modified by role mod. Calls
	 * {@link Stats#getMaxHP()}.
	 * 
	 * @return max HP
	 */
	public Integer getMaxHP() {
		if (getMod().contentEquals("Demi")) {
			return getStats().getMaxHP() / 2;
		} else if (getMod().contentEquals("Semi")) {
			return getStats().getMaxHP() / 4;
		} else if (getMod().contentEquals("Minion")) {
			return 1;
		} else {
			return getStats().getMaxHP();
		}
	}

	/**
	 * Indicates if the Combatant is a minion.
	 * 
	 * @return {@link Stats#isMinion()}
	 */
	private Boolean isMinion() {
		return getStats().isMinion();
	}

	/**
	 * Returns the bare modifier for the Combatant.
	 * 
	 * @return mod, e.g., "Demi", "Semi", "Minion", etc.
	 */
	private String getMod() {
		return _mod;
	}

	/**
	 * Sets the modifier (demi, semi, minion, etc.) for this Combatant.
	 * 
	 * @param mod
	 *            the new modifier
	 */
	private void setMod(String mod) {
		_mod = mod;
	}

	/**
	 * Returns the name of this Combatant
	 * 
	 * @return a custom name, if set; otherwise, {@link Stats#getName()}
	 */
	public String getName() {
		if (getCustomName().isEmpty()) {
			return getStats().getName();
		} else {
			return getCustomName();
		}
	}

	/**
	 * Sets the name of this Combatant.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		if (name.trim().contentEquals(getStats().getName())) {
			setCustomName("");
		} else {
			setCustomName(name.trim());
		}
	}

	/**
	 * Indicates if the Combatant is a player character.
	 * 
	 * @return {@link Stats#isPC()}
	 */
	public Boolean isPC() {
		return getStats().isPC();
	}

	/**
	 * Returns the list of used powers.
	 * 
	 * @return list of used powers
	 */
	private List<String> getPowersUsed() {
		return _powersUsed;
	}

	private void setPowersUsed(List<String> powersUsed) {
		_powersUsed = powersUsed;
	}

	/**
	 * Indicates if the named power has been used by the Combatant in this encounter.
	 * @param powName the name of the power
	 * @return true if the power has been used in this encounter
	 */
	public Boolean isPowerUsed(String powName) {
		return (getPowersUsed().contains(powName));
	}

	/**
	 * Sets a Combatant's power usage.
	 * @param powName the name of the power to be marked
	 * @param isUsed true if the power has been used, false otherwise
	 */
	public void setPowerUsed(String powName, Boolean isUsed) {
		if (isUsed) {
			for (Power pow : getStats().getPowerList()) {
				if (pow.getName().contentEquals(powName)
						&& !getPowersUsed().contains(pow.getName())) {
					getPowersUsed().add(pow.getName());
				}
			}
		} else {
			getPowersUsed().remove(powName);
		}
	}

	/**
	 * Returns the random3 used for this Combatant's initiative.
	 * 
	 * @return the random3 value
	 */
	public Integer getRandom3() {
		return _random3;
	}

	/**
	 * Sets an externally-determined random3 value.
	 * @param random3 the new random3 value
	 */
	public void setRandom3(Integer random3) {
		_random3 = random3;
	}

	/**
	 * Indicates if the Combatant is ready for combat (rolled initiative, etc.).
	 * 
	 * @return a boolean indicator of readiness
	 */
	private Boolean isReady() {
		return _ready;
	}

	/**
	 * Sets the indicator of Combatant readiness.
	 * @param ready true if Combatant is ready; false otherwise
	 */
	public void setReady(boolean ready) {
		_ready = ready;
	}

	/**
	 * Returns the role mod, based on if Combatant {@link #isMinion()} or
	 * {@link #isPC()}.
	 * 
	 * @return One of "Normal", "Minion", "PC", or ""
	 */
	private String getRoleMod() {
		if (getMod().isEmpty()) {
			return "";
		} else if (isMinion()) {
			return "Minion";
		} else if (isPC()) {
			return "PC";
		} else {
			return "Normal";
		}
	}

	/**
	 * Changes role for the Combatant, adjusting max HP if necessary.
	 * 
	 * @param value
	 *            The new role modifier.
	 */
	public void setRoleMod(String value) {
		String newMod = _mod;

		if (isMinion() || isPC()) {
			newMod = "";
		} else {
			if (value.toLowerCase().contentEquals("demi")) {
				newMod = "Demi";
			} else if (value.toLowerCase().contentEquals("semi")) {
				newMod = "Semi";
			} else if (value.toLowerCase().contentEquals("minion")) {
				newMod = "Minion";
			} else {
				newMod = "";
			}

			if (!newMod.contentEquals(_mod)) {
				Integer oldMaxHP = getMaxHP();
				_mod = newMod;
				if (oldMaxHP > getMaxHP()) {
					damage(oldMaxHP - getMaxHP());
				} else if (oldMaxHP < getMaxHP()) {
					heal(getMaxHP() - oldMaxHP);
				}
			}
		}
	}

	/**
	 * Returns the current round of combat for this Combatant.
	 * 
	 * @return the combat round
	 */
	public Integer getRound() {
		return _round;
	}

	/**
	 * Sets the Combatant's current round in the encounter.
	 * @param round the round
	 */
	public void setRound(Integer round) {
		_round = round;
	}

	/**
	 * Returns the round status string.
	 * @return round status
	 */
	private String getRoundStatus() {
		return _roundStatus;
	}

	/**
	 * Sets round status.
	 * 
	 * @param value
	 *            the round status, e.g. "Rolling", "Reserve", etc.
	 */
	private void setRoundStatus(String value) {
		_roundStatus = value;
	}

	/**
	 * Indicates if the Combatant should be visible in the initiative display.
	 * @return true if the Combatant should be visible
	 */
	public Boolean isShown() {
		return _shown;
	}

	/**
	 * Sets the visibility of this Combatant in the initiative display.
	 * 
	 * @param shown
	 *            true if Combatant should be visible in the initiative display
	 *            (default)
	 */
	public void setShown(Boolean shown) {
		_shown = shown;
	}

	/**
	 * Returns the {@link Stats} object for this Combatant.
	 * 
	 * @return this Combatant's {@link Stats}
	 */
	public Stats getStats() {
		return _stats;
	}

	/**
	 * Sets the Combatants {@link Stats} the provided stats. HP is adjusted if
	 * necessary, and power usage is re-evaluated.
	 * 
	 * @param newStats
	 *            the new stats
	 */
	private void setStats(Stats newStats) {
		Integer oldMaxHP = getMaxHP();
		_stats = newStats;

		if (oldMaxHP > getMaxHP()) {
			damage(oldMaxHP - getMaxHP());
		} else if (oldMaxHP < getMaxHP()) {
			heal(getMaxHP() - oldMaxHP);
		}

		List<String> newPowersUsed = new ArrayList<String>();

		for (String powused : getPowersUsed()) {
			for (Power pow : getStats().getPowerList()) {
				if (pow.getName().contentEquals(powused)) {
					newPowersUsed.add(powused);
				}
			}
		}
		setPowersUsed(newPowersUsed);
	}

	/**
	 * Returns a status string of information, such as bloodied, unconscious, or
	 * dying, and whether the Combatant has readied an action.
	 * 
	 * @return {@link #getHPStatus()}.concat(status)
	 */
	public String getStatusLine() {
		String status = "";

		if (getMaxHP() < 1) {
			return "(no HP)";
		}

		if (isBloody()) {
			if (isActive()) {
				status = " (bloodied)";
			} else if (_deathSaveFailed == 0) {
				status = " (unconscious)";
			} else if (_deathSaveFailed < 3) {
				status = " (dying " + getDeathSaveFailed() + "/3)";
			}
		}

		if (isActive() && isReady()) {
			status = " *Rdy* " + status;
		}

		return getHPStatus().concat(status);
	}

	/**
	 * Returns the number of remaining healing surges for this Combatant.
	 * 
	 * @return surges remaining for this Combatant
	 */
	public Integer getSurgesLeft() {
		return _surgesRemaining;
	}

	private void setSurgesLeft(Integer surgesLeft) {
		_surgesRemaining = surgesLeft;
	}

	/**
	 * Returns the healing surge value for this Combatant.
	 * 
	 * @return {@link Stats#getSurgeValue()}
	 */
	public Integer getSurgeValue() {
		return getStats().getSurgeValue();
	}

	/**
	 * Returns a string of surges spent vs. total suitable for display in a UI.
	 * 
	 * @return (surges remaining)/(total surges)
	 */
	public String getSurgeView() {
		return getSurgesLeft() + "/" + getStats().getSurges();
	}

	/**
	 * Returns the temporary HP of the Combatant.
	 * 
	 * @return amount of temporary HP
	 */
	private Integer getTempHP() {
		return _tempHP;
	}

	/**
	 * Sets the temporary HP of the Combatant. During an encounter,
	 * {@link #addTempHP(Integer)} should be used instead.
	 * 
	 * @param tempHP
	 *            the new temporary HP value
	 */
	private void setTempHP(Integer tempHP) {
		_tempHP = tempHP;
	}

	/**
	 * Returns the experience point worth of this Combatant.
	 * 
	 * @return {@link Stats#getXP()} modified by role
	 */
	private Integer getXP() {
		if (isMinion() || isPC()) {
			return getStats().getXP();
		} else {
			if (getMod().contentEquals("Demi")) {
				return getStats().getXP() * 2 / 3;
			} else if (_mod.contentEquals("Semi")) {
				return getStats().getXP() / 3;
			} else if (_mod.contentEquals("Minion")) {
				return getStats().getXP() / 4;
			} else {
				return getStats().getXP();
			}
		}
	}

	/**
	 * Sets the temp HP to the amount specified, if greater than current.
	 * 
	 * @param amt
	 *            new temp HP amount
	 */
	public void addTempHP(Integer amt) {
		if (amt <= 0 || !isAlive()) {
			return;
		}
		if (_tempHP < amt) {
			_tempHP = amt;
		}
	}

	/**
	 * Reset all class fields to defaults.
	 */
	private void clearAll() {
		setStats(new Stats());
		setCustomName("");
		setMod("");
		setFighterNumber(0);
		setCombatNotes("");
		setRoundStatus("");
		setCurrHP(0);
		setDeathSaveFailed(0);
		setTempHP(0);
		setInitRoll(0);
		setRound(0);
		setRandom3(0);
		setReady(false);
		setShown(true);
		getPowersUsed().clear();
	}

	/**
	 * Apply damage to the Combatant.
	 * 
	 * @param amt
	 *            damage amount as a positive integer
	 */
	public void damage(Integer amt) {
		if (amt <= 0) {
			return;
		}

		setTempHP(getTempHP() - amt);
		if (getTempHP() < 0) {
			setCurrHP(getCurrHP() + getTempHP());
			setTempHP(0);
		}

		updateStatus();
	}

	/**
	 * Heal the Combatant by the specified amount, to a maximum of {@link #getMaxHP()}.
	 * @param amt the amount of healing as a postive integer
	 */
	public void heal(Integer amt) {
		if (amt <= 0) {
			return;
		}
	
		if (getCurrHP() <= 0) {
			setCurrHP(0);
			setDeathSaveFailed(0);
		}
	
		setCurrHP(getCurrHP() + amt);
		if (getCurrHP() > getMaxHP()) {
			setCurrHP(getMaxHP());
		}
	
		updateStatus();
	}

	/**
	 * Modify the amount of remaining healing surges by the specified amount.
	 * No action is taken if the modification would result in a surge amount
	 * outside the range 0 - {@link Stats#getSurges()}.
	 * @param amt the amount to adjust the surge count by
	 */
	public void modSurges(Integer amt) {
		if (amt + getSurgesLeft() <= getStats().getSurges()
				&& amt + getSurgesLeft() >= 0) {
			setSurgesLeft(getSurgesLeft() + amt);
		}
	}

	/**
	 * Restores the Combatant's health and healing surge count to full.
	 */
	public void resetHealth() {
		setCurrHP(getMaxHP());
		setDeathSaveFailed(0);
		resetTempHP();
		setSurgesLeft(getStats().getSurges());
	}

	/**
	 * Calls {@link #setTempHP(Integer)} with a parameter of 0.
	 */
	public void resetTempHP() {
		setTempHP(0);
	}

	/**
	 * Kills the Combatant.
	 */
	private void slay() {
		if (getCurrHP() > 0) {
			setCurrHP(0);
		}
		setTempHP(0);
		setDeathSaveFailed(3);
		setReady(false);
	}

	/**
	 * Kills dead combatants and inactive enemies. Sets readiness if inactive PCs to false.
	 */
	private void updateStatus() {
		if (!isAlive()) {
			slay();
		} else if (!isActive()) {
			if (!isPC()) {
				slay();
			} else {
				setReady(false);
			}
		}
	}

	/**
	 * Record a failed death save for the Combatant, to a maximum of 3.
	 */
	public void failDeathSave() {
		if (getDeathSaveFailed() < 3) {
			setDeathSaveFailed(getDeathSaveFailed() + 1);
		} else {
			setDeathSaveFailed(3);
		}
	}

	/**
	 * Reduces failed death save count by one, to a minimum of 0.
	 */
	public void unfailDeathSave() {
		setDeathSaveFailed(getDeathSaveFailed() - 1);
		if (getDeathSaveFailed() < 0) {
			setDeathSaveFailed(0);
		}
	}

	/**
	 * Clear initiative-related fields.
	 */
	public void initClear() {
		setRound(0);
		setInitRoll(0);
		setRandom3(0);
		setReady(false);
	}

	/**
	 * Generates a new random3 value.
	 * 
	 * @param die
	 *            the die to use for rolls
	 */
	private void newInitMod() {
		setRandom3(DiceBag.roll(500) + 200);
		setRandom3(getRandom3() + ((90 - getStats().getInit()) * 1000));
	}

	/**
	 * Resets initiative.
	 */
	public void resetInit() {
		setRoundStatus("");
		setReady(false);
		initClear();
	}

	/**
	 * Roll initiative for this Combatant. Gets initiative modifier from
	 * {@link Stats#getInit()} and calls
	 * {@link #rollInitiative(Integer, DiceBag)}.
	 * 
	 * @return the rolled initiative
	 */
	public Integer rollInitiative() {
		return rollInitiative(DiceBag.roll(20) + getStats().getInit());
	}

	/**
	 * Sets Combatant's initiative roll to the value and generates a new random3
	 * value with the provided die.
	 * 
	 * @param rollValue
	 *            the Combatant's initiative result, probably from
	 *            {@link #rollInitiative(DiceBag)}
	 * @return the rolled initiative
	 */
	private Integer rollInitiative(Integer rollValue) {
		setInitRoll(rollValue);
		newInitMod();
		setRoundStatus("Rolling");
		return getInitRoll();
	}

	/**
	 * Resets power usage for the Combatant.
	 * @param resetDaily if true, marks all powers as unused and resets action points to 1
	 * @param resetAction if true, only action points are handled
	 */
	public void resetPowersUsage(Boolean resetDaily, Boolean resetAction) {
		if (resetDaily) {
			getPowersUsed().clear();
			getStats().setActionPoints(1);
		} else {
			for (Power pow : getStats().getPowerList()) {
				if (isPowerUsed(pow.getName()) && pow.isActionPoint()) {
					getStats().setActionPoints(getStats().getActionPoints() - 1);
				}
				if (isPowerUsed(pow.getName()) && !pow.isDaily()
						&& !pow.isItem() && !resetAction) {
					setPowerUsed(pow.getName(), false);
				}
			}
		}
	}

	/**
	 * Refreshes stats from/to library.
	 * @param lib the stat library
	 * @param updateFromLibrary if true, the stats are refreshed from the library; otherwise, the current stats are written to the library
	 */
	public void updateLibrary(StatLibrary lib, Boolean updateFromLibrary) {
		if (lib.contains(getStats().getHandle())) {
			if (updateFromLibrary) {
				setStats(lib.get(getStats().getHandle()));
			}
		} else {
			if (getStats().isValid()) {
				lib.add(getStats(), true);
			}
		}
	}

	/**
	 * Write Combatant details to the XML stream; calls
	 * {@link Stats#exportXML(XMLStreamWriter)}.
	 * 
	 * @param writer
	 *            the XMLStreamWriter to write to
	 * @param ongoing
	 *            indicates an ongoing encounter; writes additional information
	 *            when true
	 * @throws XMLStreamException from the writer
	 */
	public void exportXML(XMLStreamWriter writer, Boolean ongoing)
			throws XMLStreamException {
		writer.writeStartElement("combatant");

		writer.writeStartElement("customname");
		writer.writeCharacters(getCustomName());
		writer.writeEndElement();

		writer.writeStartElement("rolemod");
		writer.writeCharacters(getMod());
		writer.writeEndElement();

		writer.writeStartElement("notes");
		writer.writeCharacters(getCombatNotesCoded());
		writer.writeEndElement();

		_stats.exportXML(writer);

		if (ongoing) {
			writer.writeStartElement("nRound");
			writer.writeCharacters(getRound().toString());
			writer.writeEndElement();

			writer.writeStartElement("nInitRoll");
			writer.writeCharacters(getInitRoll().toString());
			writer.writeEndElement();

			writer.writeStartElement("nRandom3");
			writer.writeCharacters(getRandom3().toString());
			writer.writeEndElement();

			writer.writeStartElement("nFighterNumber");
			writer.writeCharacters(getFighterNumber().toString());
			writer.writeEndElement();

			writer.writeStartElement("sRoundStatus");
			writer.writeCharacters(getRoundStatus());
			writer.writeEndElement();

			writer.writeStartElement("bReady");
			writer.writeCharacters(isReady().toString());
			writer.writeEndElement();

			writer.writeStartElement("bShown");
			writer.writeCharacters(isShown().toString());
			writer.writeEndElement();
		}

		if (isPC() || ongoing) {
			writer.writeStartElement("nCurrHP");
			writer.writeCharacters(getCurrHP().toString());
			writer.writeEndElement();

			writer.writeStartElement("nDeathSaveFailed");
			writer.writeCharacters(getDeathSaveFailed().toString());
			writer.writeEndElement();

			writer.writeStartElement("nTempHP");
			writer.writeCharacters(getTempHP().toString());
			writer.writeEndElement();

			writer.writeStartElement("nSurgesRemaining");
			writer.writeCharacters(getSurgesLeft().toString());
			writer.writeEndElement();

			for (String pow : getPowersUsed()) {
				writer.writeStartElement("PowerUsed");
				writer.writeCharacters(pow);
				writer.writeEndElement();
			}
		}
		writer.writeEndElement();
	}

	/**
	 * Imports from an XML stream into the class; calls {@link Stats#importXML(XMLStreamReader)}.
	 * @param reader the XMLStreamReader from which to read the data
	 * @return true if successful, false on error
	 * @throws NumberFormatException from {@link Integer#valueOf(String)}
	 * @throws XMLStreamException from the reader
	 */
	public Boolean importXML(XMLStreamReader reader)
			throws NumberFormatException, XMLStreamException {
		String elementName = "";

		if (reader.isStartElement()
				&& reader.getName().toString().contentEquals("combatant")) {
			clearAll();

			while (reader.hasNext()) {
				reader.next();
				if (reader.isStartElement()) {
					elementName = reader.getName().toString();
					if (elementName.contentEquals("statblock")) {
						setStats(new Stats());
						getStats().importXML(reader);
						resetInit();
						resetHealth();
					}
				} else if (reader.isCharacters()) {
					if (elementName.contentEquals("customname")) {
						setCustomName(reader.getText());
					} else if (elementName.contentEquals("rolemod")) {
						setMod(reader.getText());
					} else if (elementName.contentEquals("nRound")) {
						setRound(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nInitRoll")) {
						setInitRoll(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nRandom3")) {
						setRandom3(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nFighterNumber")) {
						setFighterNumber(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("sRoundStatus")) {
						setRoundStatus(reader.getText());
					} else if (elementName.contentEquals("bReady")) {
						setReady(Boolean.valueOf(reader.getText()));
					} else if (elementName.contentEquals("bShown")) {
						setShown(Boolean.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nCurrHP")) {
						setCurrHP(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nDeathSaveFailed")) {
						setDeathSaveFailed(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nTempHP")) {
						setTempHP(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("nSurgesRemaining")) {
						setSurgesLeft(Integer.valueOf(reader.getText()));
					} else if (elementName.contentEquals("PowerUsed")) {
						getPowersUsed().add(reader.getText());
					}
				} else if (reader.isEndElement()) {
					elementName = "";
					if (reader.getName().toString().contentEquals("combatant")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns the armor class defense.
	 * @return {@link Stats#getAC()}
	 */
	public Integer getAC() {
		return getStats().getAC();
	}

	/**
	 * Returns the fortitude defense.
	 * @return {@link Stats#getFort()}
	 */
	public Integer getFort() {
		return getStats().getFort();
	}

	/**
	 * Returns the reflex defense.
	 * @return {@link Stats#getRef()}
	 */
	public Integer getRef() {
		return getStats().getRef();
	}

	/**
	 * Returns the will defense.
	 * @return {@link Stats#getWill()}
	 */
	public Integer getWill() {
		return getStats().getWill();
	}
	
	/**
	 * Returns {@link Color#WHITE}.
	 * @return {@link Color#WHITE}
	 */
	public Color getDisplayForeColor() {
		return Color.WHITE;
	}
	
	/**
	 * Returns a background color corresponding to the Combatant's state.
	 * @return black, if dead; gray, if inactive; green, if PC; red, if enemy
	 */
	public Color getDisplayBackColor() {
		if (!isAlive()) {
			return Color.BLACK;
		}
		if (!isActive()) {
			return Color.GRAY;
		}
		if (isPC()) {
			return Color.GREEN;
		} else {
			return Color.RED;
		}
	}

	/**
	 * Returns {@link Stats#getStatsHTML()} 
	 * @return {@link Stats#getStatsHTML()}
	 */
	public String getStatsHTML() {
		return getStats().getStatsHTML();
	}

	/**
	 * Returns {@link Stats#getPowerList()}.
	 * @return {@link Stats#getPowerList()}
	 */
	public List<Power> getPowerList() {
		return getStats().getPowerList();
	}

	/**
	 * Returns {@link Stats#getSurges()}.
	 * @return {@link Stats#getSurges()}
	 */
	public Integer getSurges() {
		return getStats().getSurges();
	}

	/**
	 * Returns a collection of preset {@link EffectBase}s for this fighter.
	 * @return the collection
	 */
	public Collection<EffectBase> getPresetEffects() {
		return getStats().getPresetEffects().values();
	}

	/**
	 * @param combatNotesCoded the combatNotesCoded to set
	 */
	private void setCombatNotesCoded(String combatNotesCoded) {
		_combatNotesCoded = combatNotesCoded;
	}

	/**
	 * @return the combatNotesCoded
	 */
	private String getCombatNotesCoded() {
		return _combatNotesCoded;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Combatant y) {
		return getInitSequence().compareTo(y.getInitSequence());
	}
}