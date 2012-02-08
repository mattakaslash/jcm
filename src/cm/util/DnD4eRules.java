package cm.util;

/**
 * Codifies several rules of Dungeons & Dragons 4th Edition
 * @author matthew.rinehart
 */
public class DnD4eRules {
	/**
	 * Calculates the stat bonus from a given stat
	 * @param input the ability score
	 * @return the stat bonus
	 */
	public static Integer calcStatBonus(Integer input) {
		Integer output = input - 10;
		
		if (output < 0) {
			output = (output - 1) / 2;
		} else {
			output = output / 2;
		}
		
		return output;
	}
	
	/**
	 * Calculates the encounter level based on party size and experience value
	 * @param partySize party size
	 * @param xpBudget experience value
	 * @return encounter level
	 */
	public static Integer encounterLevel(Integer partySize, Integer xpBudget) {
		if (partySize == 0) {
			return 0;
		}

		Integer perPC = xpBudget / partySize;
		if (perPC <= 112) {
			return 1;
		} else if (perPC <= 137) {
			return 2;
		} else if (perPC <= 162) {
			return 3;
		} else if (perPC <= 187) {
			return 4;
		} else if (perPC <= 225) {
			return 5;
		} else

		if (perPC <= 275) {
			return 6;
		} else if (perPC <= 325) {
			return 7;
		} else if (perPC <= 375) {
			return 8;
		} else if (perPC <= 450) {
			return 9;
		} else if (perPC <= 550) {
			return 10;
		} else

		if (perPC <= 650) {
			return 11;
		} else if (perPC <= 750) {
			return 12;
		} else if (perPC <= 900) {
			return 13;
		} else if (perPC <= 1100) {
			return 14;
		} else if (perPC <= 1300) {
			return 15;
		} else

		if (perPC <= 1500) {
			return 16;
		} else if (perPC <= 1800) {
			return 17;
		} else if (perPC <= 2200) {
			return 18;
		} else if (perPC <= 2600) {
			return 19;
		} else if (perPC <= 3000) {
			return 20;
		} else

		if (perPC <= 3675) {
			return 21;
		} else if (perPC <= 4625) {
			return 22;
		} else if (perPC <= 5575) {
			return 23;
		} else if (perPC <= 6525) {
			return 24;
		} else if (perPC <= 8000) {
			return 25;
		} else

		if (perPC <= 10000) {
			return 26;
		} else if (perPC <= 12000) {
			return 27;
		} else if (perPC <= 14000) {
			return 28;
		} else if (perPC <= 17000) {
			return 29;
		} else if (perPC <= 21000) {
			return 30;
		} else

		if (perPC <= 25000) {
			return 31;
		} else if (perPC <= 29000) {
			return 32;
		} else if (perPC <= 35000) {
			return 33;
		} else if (perPC <= 43000) {
			return 34;
		} else if (perPC <= 51000) {
			return 35;
		} else

		if (perPC <= 59000) {
			return 36;
		} else if (perPC <= 71000) {
			return 37;
		} else if (perPC <= 87000) {
			return 38;
		} else if (perPC <= 103000) {
			return 39;
		} else {
			return 40;
		}
	}
}
