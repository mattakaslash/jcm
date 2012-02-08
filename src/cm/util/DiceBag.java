package cm.util;

import java.util.Random;

public class DiceBag {
	private Random numGen;
	
	public DiceBag() {
		numGen = new Random();
	}
	
	public Integer roll(Integer dieType) {
		if (dieType < 2) {
			return 0;
		}
		
		return numGen.nextInt(dieType) + 1;
	}
	
	public Integer roll(Integer dieNum, Integer dieType) {
		Integer total = 0;
		
		if (dieNum < 1) {
			return 0;
		}
		
		for (int i = 0; i < dieNum; i++) {
			total += roll(dieType);
		}
		
		return total;
	}
	
	public Integer roll(Integer dieNum, Integer dieType, String desc) {
		Integer total = 0;
		Integer result;
		
		desc = dieNum + "d" + dieType + " (";
		
		if (dieNum < 1) {
			return 0;
		}
		
		for (int i = 0; i < dieNum; i++) {
			result = roll(dieType);
			total += result;
			desc += result;
			if (i != dieNum - 1) {
				desc += ",";
			}
		}
		
		desc += ")";
		
		return total;
	}
}