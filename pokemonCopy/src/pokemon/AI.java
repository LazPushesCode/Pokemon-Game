package pokemon;
import pokemon.Battle;

public class AI {
	String debuff;
	String secondarydebuff;
	String status;
	String physical;
	String special;
	String impact;
	int pindex;int spindex;int impactindex;int debuffindex;int statusindex; int secondaryindex;
	int pdmg; int spdmg; int impactdmg; int debuffdmg;
	int impacttimer; int debufftimer;int statustimer; int secondarytimer;
	String arr[]; int movetypes[]; int enemytype1; int enemytype2;
	AI(Poke ai, Poke enemy){
		this.physical = "";
		this.special = "";
		this.debuff = ""; 
		this.status = "";
		this.impact = "";
		this.secondarydebuff = "";
		pindex = -1; spindex = -1; impactindex = -1; debuffindex = -1; statusindex = -1; secondaryindex = -1;
		pdmg = 0; spdmg = 0; impactdmg = 0; debuffdmg = 0;
		impacttimer = 0; debufftimer = 0; statustimer = 0; secondarytimer = 0;
		arr = new String[] {"Fire", "Grass", "Rock", "Electric", "Metal", "Poison", "Water", " "};
		movetypes = new int[7];
		enemytype1 = 7; enemytype2 = 7;
		physical = ""; special = ""; debuff = ""; status = "";
		for(int i = 0; i < 7; i++) {
			try {
				if(ai.movesets.get(0).type.equals(arr[i])) movetypes[0] = i;
				if(ai.movesets.get(1).type.equals(arr[i])) movetypes[1] = i;
				if(ai.movesets.get(2).type.equals(arr[i])) movetypes[2] = i;
				if(ai.movesets.get(3).type.equals(arr[i])) movetypes[3] = i;
			}catch(Exception e) {
			}
			if(arr[i].equals(enemy.type[0])) enemytype1 = i;
			if(!(enemy.type[1].equals(""))) {
				if(enemy.type[1].equals(arr[i])) enemytype2 = i;
			}
		}
	}
	public void memorize(Poke ai, Poke enemy) {
		int pmax = 0;
		int smax = 0;
		int impactmax = 0; 
		int debuffmax = 0;	
		for(int i = 0; i < 4; i++) {
			try {
				//separate the moves sorting into Effects and non Effects
				if(!(ai.movesets.get(i).effect.equals(""))) {
					if(ai.movesets.get(i).attackType.equals("Physical")) {
						impactmax = setMove(ai, impactindex, impactmax, i, movetypes, enemytype1, enemytype2, "impact");	
					} else if(ai.movesets.get(i).attackType.equals("Special")) {
						debuffmax = setMove(ai, debuffindex, debuffmax, i, movetypes, enemytype1, enemytype2, "debuff");	
					} else if(ai.movesets.get(i).attackType.equals("Status") && status.equals("")) {
						status = ai.movesets.get(i).name; statusindex = i;
					}
				} else {
					if(ai.movesets.get(i).attackType.equals("Physical")){
						pmax = setMove(ai, pindex, pmax, i, movetypes, enemytype1, enemytype2, "physical");	
					} else if(ai.movesets.get(i).attackType.equals("Special")) {
						smax = setMove(ai, spindex, smax, i, movetypes, enemytype1, enemytype2, "special");	
					}
				}
			} catch(Exception e) {
				break;
			}
		}
		if(!debuff.equals("")) {
			for(int i = 0; i < 4; i++) {
				if(ai.movesets.get(i).attackType.equals("Special") && !ai.movesets.get(i).effect.equals(ai.movesets.get(debuffindex).effect)) {
					secondarydebuff = ai.movesets.get(i).name; secondaryindex = i;
				}
			}
		}
		if(pindex != -1) {
			physical = ai.movesets.get(pindex).name; pdmg = pmax;
		}
		if(spindex != -1) {
			special = ai.movesets.get(spindex).name; spdmg = smax;
		} 
		if(impactindex != -1) {
			impact = ai.movesets.get(impactindex).name; impactdmg = impactmax;
		}
		if(debuffindex != -1) {
			debuff = ai.movesets.get(debuffindex).name; debuffdmg = debuffmax;
		}
		if(statusindex != -1) status = ai.movesets.get(statusindex).name;
		for(int i = 0; i < 4; i++) {
			try {
				System.out.println(ai.movesets.get(i).name);
			}catch(Exception e) {
				break;
			}
		}
	}
	public int setMove(Poke ai, int index, int max, int i, int[] movetypes, int enemytype1, int enemytype2, String type) {
		if(index == -1) {
			index = i; max = ai.movesets.get(i).power;
		} else {
			if((movetypes[i] + 1 == enemytype1 || movetypes[i] + 1 == enemytype2) && ai.movesets.get(i).power > max) {
				max = ai.movesets.get(i).power; index = i;
			} else if((movetypes[i] + 1 == enemytype1 || movetypes[i] + 1 == enemytype2) && (movetypes[index] + 1 != enemytype1 || movetypes[index] + 1 != enemytype2)){
				max = ai.movesets.get(i).power; index = i;
			} else if((movetypes[i] + 1 != enemytype1 && movetypes[i] + 1 != enemytype2) && (movetypes[index] + 1 != enemytype1 && movetypes[index] + 1 != enemytype2)){
				if((movetypes[index] == enemytype1 || movetypes[index] == enemytype2) && (movetypes[i] != enemytype1 && movetypes[i] != enemytype2)) {
					max = ai.movesets.get(i).power; index = i;
				} else if(ai.movesets.get(i).power > max) {
					max = ai.movesets.get(i).power; index = i;
				}
			}
		}
		if(type.equals("physical"))pindex = index;
		if(type.equals("special"))spindex = index;
		if(type.equals("impact"))impactindex = index;
		if(type.equals("debuff"))debuffindex = index;
		
		return max;
	}
	
	public int decideMove(Poke ai, Poke enemy, Map map) {
		if(!status.equals("") && statustimer == 0 && ai.temphp <= ai.hp - (ai.hp * 0.25)) {
			statustimer = ai.movesets.get(statusindex).timer+1;
			return statusindex;
		}
		if(!debuff.equals("") && debufftimer == 0) {
			debufftimer = ai.movesets.get(debuffindex).timer+1;
			return debuffindex;
		} else if(!secondarydebuff.equals("") && secondarytimer == 0) {
			secondarytimer = ai.movesets.get(secondaryindex).timer+1;
			return secondaryindex;
		}else if(!impact.equals("") && impacttimer == 0) {
			impacttimer = ai.movesets.get(impactindex).timer+1;
			return impactindex;
		} else {
			if(!physical.equals("") && !special.equals("")) {
				double physicaldmg = Battle.calculateDamage(ai, enemy, pindex, map);
				double specialdmg = Battle.calculateDamage(ai, enemy, spindex, map);
				if(physicaldmg >= specialdmg) {
					return pindex;
				} else {
					return spindex;
				}
			} else if(!physical.equals("") && special.equals("")) {
				return pindex;
			} else if(!special.equals("") && physical.equals("")) {
				return spindex;
			} else {
				if(!status.equals("")) {
					statustimer = ai.movesets.get(statusindex).timer+1;
					return statusindex;
				}else if(!debuff.equals("")) {
					debufftimer = ai.movesets.get(debuffindex).timer+1;
					return debuffindex;
				}else if(!impact.equals("")) {
					impacttimer = ai.movesets.get(impactindex).timer+1;
					return impactindex;
				}
			}
				
		}
		return 0;
	}
}
