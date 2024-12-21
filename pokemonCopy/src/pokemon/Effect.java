package pokemon;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Random;
import pokemon.Map;
import pokemon.Map.chunk;
import pokemon.Poke;
import pokemon.Player;
import pokemon.Collection;
import pokemon.Puzzle;
import pokemon.AI;
import java.util.HashMap;
import java.util.function.Function;


public class Effect {
	
	public void assignDebuff(Poke curr, Poke other, Attack move, ArrayList<ArrayList<chunk>> chunks) {
		if(move.effect != "") {
			if(!((move.effect.equals("Daze") || move.effect.equals("Confuse")) && curr.status.equals("Echolocation"))){
				if(move.attackType.equals("Physical") && curr.impact.equals("")) {
					if(move.effect.equals("Extra Turn")) {
						other.impact = move.effect;
						System.out.println(other.name + " has been assigned " + other.impact);
					} else {
						curr.impact = move.effect;
						Battle.mapDialogue(chunks, curr.name + " has been inflicted with " + move.effect.toLowerCase() + "                                                                                               > continue                                                                    ");
					}
				} else if(move.attackType.equals("Special") && curr.debuff.equals("")) {
					curr.debuff = move.effect;
					Battle.mapDialogue(chunks, curr.name + " has been inflicted with " + move.effect.toLowerCase() + "                                                                                               > continue                                                                    ");
				} else if(move.attackType.equals("Status") && curr.status.equals("")) {
					curr.status = move.effect;
					Battle.mapDialogue(chunks, curr.name + " has been inflicted with " + move.effect.toLowerCase() + "                                                                                               > continue                                                                    ");
				} else {
					return;
				}
				if(move.attackType.equals("Physical")) {
					curr.impacttimer = move.timer;
				} else if(move.attackType.equals("Special")) {
					curr.debufftimer = move.timer;
				} else if(move.attackType.equals("Status")) {
					curr.statustimer = move.timer;
				}
				
//				if(move.attackType.equals("Physical")) {
//					if(move.effect.equals("Extra Turn")) {
//						curr.impacttimer = 1;
//					} else {
//						Random ran = new Random();
//						curr.impacttimer = ran.nextInt(1,3);
//					}
//				} else if(move.attackType.equals("Special")) {
//					if(curr.debuff.equals("Drown") || curr.debuff.equals("Shock") || curr.debuff.equals("Photosynthesis") || curr.debuff.equals("Pollution")) {
//						curr.debufftimer = 3;
//					} else if(curr.debuff.equals("Burn") || curr.debuff.equals("Poison") || curr.debuff.equals("Mud") || curr.debuff.equals("Melting Point") || curr.debuff.equals("Malleable")) {
//						curr.debufftimer = 3;
//					}
//				} else if(move.attackType.equals("Status")) {
//					if(curr.status.equals("heal")) {
//						curr.statustimer = 1;
//					} else if(curr.status.equals("evade")) {
//						curr.statustimer = 2;
//					} else if(curr.status.equals("Thorns") || curr.status.equals("Blossom")) {
//						curr.statustimer = 3;
//					} else if(curr.status.equals("Rock Shield") || curr.status.equals("Ice Shield") || curr.status.equals("Reflect")) {
//						curr.statustimer = 4;
//					}
//				}
			}
		}
	}

	public void inflictDebuff(Poke curr, Poke attacker, ArrayList<ArrayList<chunk>> chunks) {
		if(curr.status.equals("Moisturize")) {
			Random ran = new Random();
			if(ran.nextInt(1,10) < 6) return;
		}
		if(!(curr.debuff.equals(""))) {
			String key = "";
			for(int i = 0; i < curr.debuff.length(); i++) {
				if(curr.debuff.substring(i, i+1).equals(" ")) {
					key += "_";
				} else {
					key += curr.debuff.substring(i, i+1);
				}
			}
		    try {
		            Method method = Battle.class.getMethod(key, Poke.class, ArrayList.class);
		            method.invoke(this, curr, chunks);
		            System.out.println("We found it ");
			}catch(Exception e) {
				System.out.println("Something failed...");
				e.printStackTrace();
				return;
			}
		}
	}	
	public void inflictImpact(Poke curr, Poke attacker, ArrayList<ArrayList<chunk>> chunks) {
		if(!(curr.impact.equals(""))) {
			String key = "";
			for(int i = 0; i < curr.impact.length(); i++) {
				if(curr.impact.substring(i, i+1).equals(" ")) {
					key += "_";
				} else {
					key += curr.impact.substring(i, i+1);
				}
			}
		    try {
		    		key.trim();
		            Method method = Battle.class.getMethod(key, Poke.class,  ArrayList.class);
		            method.invoke(this, curr, chunks);
			} catch(NoSuchMethodException e) {
				try {
					Method method = Battle.class.getMethod(key,  Poke.class, Poke.class,  ArrayList.class);
					method.invoke(this, curr, attacker, chunks);
				} catch(Exception d) {
					System.out.println("Still could not find a method");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void inflictStatus(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		if(!(curr.status.equals(""))) {
			String key = "";
			for(int i = 0; i < curr.status.length(); i++) {
				if(curr.status.substring(i, i+1).equals(" ")) {
					key += "_";
				} else {
					key += curr.status.substring(i, i+1);
				}
			}
		    try {
		    		key.trim();
		            Method method = Battle.class.getMethod(key, Poke.class,  ArrayList.class);
		            method.invoke(this, curr, chunks);
			} catch(NoSuchMethodException e) {
				//type methods that have multiple parameters (it shouldnt?)
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Burn(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//inflicts 10% dps
		double dmg = curr.hp * .10;
		curr.temphp -= (int)dmg;
		Battle.mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " burn damage!                                                                                                               > continue                                                      ");
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Poison(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//inflicts 15% dps
		double dmg = curr.temphp * .15;
		curr.temphp -= (int)dmg;
		Battle.mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " poison damage!                                                                                                             > continue                                                     ");
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Drown(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//inflict 5% dps
		double dmg = curr.temphp * .05;
		curr.temphp -= (int)dmg;
		Battle.mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " drown damage!                                                                                                              > continue                                                     ");
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Heal(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//heals the pokemon by 50% of its hp
		double healing = curr.temphp * .5;
		curr.temphp += (int)healing;
		Battle.mapDialogue(chunks, curr.name + " has been healed by " + (int)healing + " points!                                                                                                               > continue                                                     ");
		if(curr.temphp > curr.hp) curr.temphp = curr.hp;
	}
	public void Rock_Shield(Poke curr,ArrayList<ArrayList<chunk>> chunks) {
		//provides current pokemon a 50% shield
		curr.shield = (int)(curr.hp * .5);
		if(curr.statustimer==0) curr.shield = 0;
	}
	public void Ice_Shield(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provides current pokemon a 20% shield
		curr.shield = (int)(curr.hp * .2);
		if(curr.statustimer==0) curr.shield = 0;
	}
	public void Shock(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//slows the current pokemon down and 25% chance to miss a move
		curr.tempstats[0] = curr.speed  - (int)(curr.speed * .40);
		curr.tempstats[1] = curr.attack - (int)(curr.attack * .05);
		double dmg = curr.hp * .05;
		curr.temphp -= (int)dmg;
		Battle.mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " static damage!                                                                                                             > continue                                                     ");
		if(curr.debufftimer == 0) {
			curr.tempstats[0] = curr.speed;
			curr.tempstats[1] = curr.attack;
		}
		if(curr.temphp > curr.hp) curr.temphp = curr.hp;
	}
	public void Haste(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//should increase move accuracy, reduce damage taken, increase chance to dodge attack by a significant amount
		curr.tempstats[0] = (int)(curr.speed * 0.3) + curr.speed;
		if(curr.statustimer == 0) {
			curr.tempstats[0] = curr.speed;
		}
	}
	public void Photosynthesis(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks, Random ran) {
		//provides the current pokemon 
		double dmg = (int)(curr.hp * .1);
		Attacker.temphp += (int)dmg;
		curr.temphp -= (int)dmg;
		Battle.mapDialogue(chunks, Attacker.name + " syphens " + (int)dmg +" hp from " + curr.name + "                                                                                                           > continue                                                     ");
		if(Attacker.temphp > Attacker.hp) Attacker.temphp = Attacker.hp;
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Daze(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//lowers the accuracy of current pokemon by 35%
		curr.tempstats[0] = curr.speed - (int)( curr.speed * .20);
		if(curr.impacttimer == 0) {
			curr.tempstats[0] = curr.speed;
		}
	}
	public void MeltingPoint(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//lowers all stats of the current pokemon by 5% overtime
		for(int i = 0; i < 5; i++) {
			double stat = curr.tempstats[i] * .05;
			curr.tempstats[i] -= (int)stat;
		}
		if(curr.debufftimer == 0) {
			curr.tempstats[0] = curr.speed;curr.tempstats[1] = curr.attack; curr.tempstats[2] = curr.spAttack;
			curr.tempstats[3] = curr.defense;curr.tempstats[4] = curr.spDefense;
		}
	}
	public void Malleable(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//lowers defense and special defense of pokemon by 20%
		curr.tempstats[3] = curr.defense - (int)(curr.defense * .20);
		curr.tempstats[4] = curr.spDefense - (int)(curr.spDefense * .20);
		if(curr.debufftimer == 0) {
			curr.tempstats[3] = curr.defense;
			curr.tempstats[4] = curr.spDefense;
		}
	}
	public void Blossom(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provide current pokemon a 2x multiplier to their attack and special attack stat
		curr.tempstats[1] = curr.attack * 2;
		curr.tempstats[2] = curr.spAttack * 2;
		if(curr.statustimer == 0) {
			curr.tempstats[1] = curr.attack;
			curr.tempstats[2] = curr.spAttack;
		}
	}
	public void Bleed(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		curr.temphp -= (int) (curr.hp*.05);
		curr.tempstats[0] = curr.speed - (int)(curr.speed * .1);
		if(curr.debufftimer == 0) {
			curr.tempstats[0] = curr.speed;
		}
	}
	public void Nourishment(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		curr.temphp += (int)(curr.hp * .4);
		if(curr.temphp > curr.hp) curr.temphp = curr.hp;
	}
	public void Cleanse(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		curr.debuff = ""; curr.impact = "";
		curr.impacttimer = 0; curr.debufftimer = 0;
	}
	public void Attraction(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		if(curr.debuff.equals("Shock")){
			Attacker.tempstats[1] = curr.attack * 2;
		}
		if(Attacker.statustimer == 0) {
			curr.tempstats[1] = curr.attack;
		}
	}
	public void Charge_Up(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		if(curr.tempstats[0] > Attacker.tempstats[0]) Attacker.tempstats[0] = curr.tempstats[0];
		if(curr.statustimer == 0) {
			Attacker.tempstats[0] = Attacker.speed;
		}
	}	
	public void Anticipation(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		if(!curr.status.equals("")) {
			curr.status = "";
			curr.debuff = ""; curr.debufftimer = 0;
			curr.debuff = "Shock"; curr.debufftimer = 3;
		}
	}
	public void Outpace(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		curr.tempstats[0] = curr.speed - (int) (curr.speed * curr.speed*.1);
		if(curr.statustimer == 0) {
			curr.tempstats[0] = curr.speed;
		}
	}
	public void Copycat(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		if(!curr.status.equals("")) Attacker.status = curr.status;
	}
	public void Flame_On(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		curr.tempstats[2] = curr.spAttack + (int) (curr.spAttack * .35);
		if(curr.statustimer == 0) {
			curr.tempstats[2] = curr.spAttack;
		}
	}
	public void Scorched_Earth(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		curr.temphp += (int) curr.hp * .25;
	}
	public void Ignition(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		if(!curr.status.equals("")) {
			curr.status = ""; curr.statustimer = 0;
		}
	}
	public void Evaporation(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		if((curr.type[0].equals("Water")|| curr.type[1].equals("Water") )|| (curr.type[0].equals("Poison") || curr.type[1].equals("Poison"))) {
			curr.tempstats[3] = curr.defense - (int) (curr.defense * .20);
			curr.tempstats[4] = curr.spDefense - (int) (curr.spDefense * .20);
		}
		if(curr.debufftimer == 0) {
			curr.tempstats[3] = curr.defense;
			curr.tempstats[4] = curr.spDefense;
		}
	}

	
	
	//UNIQUE INTERACTIONS EFFECTS
	public void Moisturize(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//applies a special liquid on the pokemon that reduces chance of debuff infliction on it by 50% (IMPLEMENTED)
	}
	public void Web(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		//traps the pokemon, applies paralysis, 50% it wears off?
	}
	public void Deliquesce(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		//if the enemy has a shield, then remove it
		if(curr.shield != 0) curr.shield = 0;
	}
	public void Echolocation(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provides pokemon immunity to daze and confusion (IMPLEMENTED)
	}
	public void Pollution(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//will deal 5% damage to current pokemon team each time current pokemon is damaged in battle 
	}
	public void Confuse(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//causes current pokemon to have a chance at attacking itself or teamates
	}
	public void Thorns(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//thorn status effect grants current pokemon chance to damage enemy pokemon whenever it attacks it and a low chance to inflict poison (IMPLEMENTED)
		Random ran = new Random();
		curr.temphp -= (int) curr.hp * .10;
		if(ran.nextInt(1,11) <= 4) {
			curr.debuff = ""; curr.debufftimer = 0;
			curr.debuff = "Poison"; curr.debufftimer = 3;
		}
	}
	public void Reflect(Poke curr, Poke Attacker, int damage, ArrayList<ArrayList<chunk>> chunks) {
		//reflects 50% of damage taken back onto enemy
		curr.temphp -= (int) (damage * .5);
	}
	public int Mud(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//causes current pokemon to lose control of what move they use, reduces POW of move by 15%, and removes the debuff if special
		Random ran = new Random();
		return ran.nextInt(0,curr.movesets.size());
	}
	
	
	
	//UNIQUE MOVES
	public void Davy_Jones_Locker() {
		
	}
	public void Aqueous_Bullet_Hell() {
		
	}
	public void Cornered() {
		
	}
	
	
}
