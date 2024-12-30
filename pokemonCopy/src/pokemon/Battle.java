package pokemon;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Random;
import pokemon.Map;
import pokemon.Effect;
import pokemon.Map.chunk;
import pokemon.Poke;
import pokemon.Player;
import pokemon.Collection;
import pokemon.Puzzle;
import pokemon.AI;
import java.util.HashMap;
import java.util.function.Function;


public class Battle {
	
	public void OverviewBattleTrainer(Player user, Trainer enemy, ArrayList<ArrayList<chunk>> chunks, Map map) {
		int trainerchoice = 0;
		int totalDead = 0; int numDead = 0;
		for(int i = 0; i < Player.numRoster; i++) {
			if(user.rosterlist.get(i).temphp == 0) {
				totalDead++;
			}
		}
		//tell user the trainer's pokemon choice
		int userchoice = choosePokemon(user, enemy.rosterList.get(trainerchoice), chunks, map);
		
		//start overview battle rotation
		while(true) {
			if(enemy.rosterList.get(trainerchoice).temphp == 0) {
				if(trainerchoice != enemy.numRoster-1) {
					trainerchoice++;
				} else {
					//initiate battle end script
					break;
				}
			}
			if(user.rosterlist.get(userchoice).temphp == 0) {
				
				if((Player.numRoster - totalDead) == numDead) {
					//initiate player loss script
					break;
				}
				choosePokemon(user,enemy.rosterList.get(trainerchoice),chunks, map);
			}
			simulateBattle(user, enemy.rosterList.get(trainerchoice), chunks, userchoice, 0, map);	
		}	
	}
	
	public void OverviewBattleWild(Player user, Poke wildpoke, ArrayList<ArrayList<chunk>> chunks, Map map) {
		int userchoice = 0;
		ArrayList<Poke> involvedPokemon = new ArrayList<>();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 11; j++) {
				chunks.get(i).get(j).blankify();
			}
		}
		chunks.get(4).get(0).grid[4]="__________________";chunks.get(4).get(1).grid[4]="__________________";chunks.get(4).get(2).grid[4] ="__________________";chunks.get(4).get(3).grid[4] ="__________________";chunks.get(4).get(4).grid[4] ="__________________";chunks.get(4).get(5).grid[4] ="__________________";chunks.get(4).get(6).grid[4] ="__________________";chunks.get(4).get(7).grid[4] ="__________________";chunks.get(4).get(8).grid[4] ="__________________";chunks.get(4).get(9).grid[4] ="__________________";chunks.get(4).get(10).grid[4] ="__________________";
		displayPokemon(wildpoke, chunks);
		mapDialogue(chunks, "A wild " + wildpoke.name + " appears!                                                ");
		while(true) {
			while(true) {
				userchoice = choosePokemon(user, wildpoke, chunks, map);
				try {
					user.rosterlist.get(userchoice);
					break;
				} catch(Exception e) {}
			}
			if(!involvedPokemon.contains(user.rosterlist.get(userchoice))) involvedPokemon.add(user.rosterlist.get(userchoice));
			if(Player.numRoster > userchoice) {
				break;
			}
		}
		int result;
		//start overview battle rotation
		while(true) {
			if(wildpoke.temphp == 0) break;
			user.updateDead();
			if(user.rosterlist.get(userchoice).temphp == 0) {
				if((Player.numRoster - user.numDead) == 0) {
					//initiate player loss script
					Player.encountercooldown = 10;
					mapDialogue(chunks, "All of your pokemon have been defeated... The area is far too dangerous to stay.                                               ");
					break;
				}
				while(true) {
					userchoice = choosePokemon(user,wildpoke,chunks, map);
					try {
						user.rosterlist.get(userchoice);
						break;
					} catch(Exception e) {
						
					}
				}
			}
			
			result = simulateBattle(user,wildpoke,chunks,userchoice, 1, map);
			if(result == 3 || result == 4) {
				Player.encountercooldown = 3;
				return;
			} else if(result == 0) {
				//player won
				Player.encountercooldown = 5;
				mapDialogue(chunks, "You've successfully beaten the wild pokemon!                                           ");
				int num = distributeXP(user, involvedPokemon, wildpoke, map);
				Player.gold += num*3;
				mapDialogue(chunks, "You've been awarded "+num*3+" gold!                                                  ");
				
			} else if(result == 1) {
				//pokemon won
				user.numDead++;
			}
		}
		
	}
	
	public int distributeXP(Player user, ArrayList<Poke> list, Poke enemy, Map map) {
		int temp = 0;
		if(enemy.percent == 1) {
			temp = 1;
		} else {
			temp = (1%enemy.percent);
		}
		int exp = (temp * enemy.level) * 5;
		exp = exp/list.size();
		for(int i = 0; i < list.size(); i++) {
			list.get(i).xp += exp;
			mapDialogue(map.chunks, list.get(i).name + " has obtained " + exp + " experience!                                            ");
		}		
		return exp;
	}
	
	
	public int choosePokemon(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, Map map) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 11; j++) {
				chunks.get(i).get(j).blankify();
			}
		}
		chunks.get(4).get(0).grid[4]="__________________";chunks.get(4).get(1).grid[4]="__________________";chunks.get(4).get(2).grid[4] ="__________________";chunks.get(4).get(3).grid[4] ="__________________";chunks.get(4).get(4).grid[4] ="__________________";chunks.get(4).get(5).grid[4] ="__________________";chunks.get(4).get(6).grid[4] ="__________________";chunks.get(4).get(7).grid[4] ="__________________";chunks.get(4).get(8).grid[4] ="__________________";chunks.get(4).get(9).grid[4] ="__________________";chunks.get(4).get(10).grid[4] ="__________________";
		String fragmented;
		int row = 5; int col = 2;
		for(int i = 0; i < Player.numRoster; i++) {
				fragmented = user.rosterlist.get(i).name + "  Lvl: " + user.rosterlist.get(i).level;
				chunks.get(row).get(col).grid[2] = fragmented;
				for(int j = fragmented.length(); j < 18; j++) {
					chunks.get(row).get(col).grid[2] += " ";
				}
				fragmented = "Hp: " + user.rosterlist.get(i).temphp + "/" + user.rosterlist.get(i).hp;
				chunks.get(row).get(col).grid[1] = fragmented;
				for(int j = fragmented.length(); j < 18; j++) {
					chunks.get(row).get(col).grid[1] += " ";
				}
				col += 3;
				if(col >8) {
					col = 2;
					row = 6;
				}
		}
		int choicerow = 5; int choicecol = 2; int choice = 0;
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(true) {
			game.spaces();
			choice = ((choicerow-5)*3) + ((choicecol-2)/3);
			try {
				displayPokemon(user.rosterlist.get(choice), chunks);
			} catch (Exception e) {
				for(int i = 0; i < 5; i++) {
					for(int j = 1; j < 9; j++) {
						chunks.get(i).get(j).blankify();
					}
				}
				chunks.get(4).get(0).grid[4]="__________________";chunks.get(4).get(1).grid[4]="__________________";chunks.get(4).get(2).grid[4] ="__________________";chunks.get(4).get(3).grid[4] ="__________________";chunks.get(4).get(4).grid[4] ="__________________";chunks.get(4).get(5).grid[4] ="__________________";chunks.get(4).get(6).grid[4] ="__________________";chunks.get(4).get(7).grid[4] ="__________________";chunks.get(4).get(8).grid[4] ="__________________";chunks.get(4).get(9).grid[4] ="__________________";chunks.get(4).get(10).grid[4] ="__________________";
			
			}
			chunks.get(choicerow).get(choicecol).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(choicerow).get(choicecol+1).grid[0] = "~~~               ";
			chunks.get(choicerow).get(choicecol-1).grid[1] = "                 /";chunks.get(choicerow).get(choicecol+1).grid[1] = "  /               ";
			chunks.get(choicerow).get(choicecol-1).grid[2] = "                / ";chunks.get(choicerow).get(choicecol+1).grid[2] = " /                ";
			chunks.get(choicerow).get(choicecol-1).grid[3] = "               /  ";chunks.get(choicerow).get(choicecol+1).grid[3] = "/                 ";
			chunks.get(choicerow).get(choicecol-1).grid[4] = "               ~~~";chunks.get(choicerow).get(choicecol).grid[4] = "~~~~~~~~~~~~~~~~~~";
				
			System.out.println("_".repeat(200));
			for(int r = 0; r < 8; r++) {
				for(int i = 0; i < 5; i++) {
					System.out.print("|");
					
					for(int c = 0; c < 11; c++) {
						System.out.print(chunks.get(r).get(c).grid[i]);
					}
				if(i != 7) {
					System.out.println("|");
				}
				}
			}	
			chunks.get(choicerow).get(choicecol).grid[0] = "                  ";chunks.get(choicerow).get(choicecol+1).grid[0] = "                  ";
			chunks.get(choicerow).get(choicecol-1).grid[1] = "                  ";chunks.get(choicerow).get(choicecol+1).grid[1] = "                  ";
			chunks.get(choicerow).get(choicecol-1).grid[2] = "                  ";chunks.get(choicerow).get(choicecol+1).grid[2] = "                  ";
			chunks.get(choicerow).get(choicecol-1).grid[3] = "                  ";chunks.get(choicerow).get(choicecol+1).grid[3] = "                  ";
			chunks.get(choicerow).get(choicecol-1).grid[4] = "                  ";chunks.get(choicerow).get(choicecol).grid[4] = "                  ";
			input = scan.next();
			if(input.equals("w") && choicerow != 5) {
				choicerow--;
			} else if(input.equals("d") && choicecol != 8) {
				choicecol += 3;
			} else if(input.equals("a") && choicecol != 2) {
				choicecol -= 3;
			} else if(input.equals("s") && choicerow != 6) {
				choicerow++;
			} else if(input.equals("c")) {
				return choice;
				
			} else if(input.equals("o")) {
				break;
			}
			
		}
		return 0;	
	}
	
	public int simulateBattle(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, int userchoice, int wildflag, Map map) {
		Effect effect = new Effect();
		AI ai = new AI(enemy, user.rosterlist.get(userchoice));
		ai.memorize(enemy, user.rosterlist.get(userchoice));
		System.out.println("Impact: " + ai.impact + " Debuff: " + ai.debuff + " physical: " + ai.physical + " special: " + ai.special + " Status: " + ai.status);
		if(enemy.speed > user.rosterlist.get(userchoice).speed) {
			//enemy attacks first!
			displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
			int key = initiateTurn(user, enemy, userchoice, ai, map, effect, 1);
		}
		displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
		int choicerow = 5; int choicecol = 1;
		Scanner scan = new Scanner(System.in);
		String input = "";
		int round = 1; int termkey;
		while(true) {
			if(enemy.temphp < 0) enemy.temphp = 0;
			if(enemy.temphp == 0) {
				mapDialogue(chunks, enemy.name + " has fainted!                                                                                                                  > continue                            ");
				return 0;
			}
			checkTimers(enemy, user.rosterlist.get(userchoice), chunks, effect); //decrement timers for the enemy pokemon, then decrement for ai 
			if(ai.impacttimer != 0)ai.impacttimer--;
			if(ai.statustimer != 0)ai.statustimer--;
			if(ai.debufftimer != 0)ai.debufftimer--;
			if(!(user.rosterlist.get(userchoice).impact.equals("")))effect.inflictImpact(user.rosterlist.get(userchoice), enemy, chunks);
			if(!(user.rosterlist.get(userchoice).debuff.equals(""))) {
				if(!user.rosterlist.get(userchoice).debuff.equals("Photosynthesis")) {
					effect.inflictDebuff(user.rosterlist.get(userchoice),enemy, chunks);
				} else {
					Random ran = new Random();
					effect.Photosynthesis(user.rosterlist.get(userchoice),enemy,chunks, ran);
				}
			}
			displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
			termkey = 0;
			while(termkey == 0) {
				game.spaces();
				System.out.println("sc: " + user.rosterlist.get(userchoice).statuscooldown + " dc: " + user.rosterlist.get(userchoice).debuffcooldown + " ic: " + user.rosterlist.get(userchoice).impactcooldown);
				displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
				if(user.rosterlist.get(userchoice).temphp == 0) {
					mapDialogue(chunks, user.rosterlist.get(userchoice).name + " has fainted!                                                                                                                  > continue                            ");
					return 1;
				}
				if(user.rosterlist.get(userchoice).impact.equals("Paralyze")) {
					mapDialogue(chunks, user.rosterlist.get(userchoice).name +" is paralyzed!                                                                                      > continue                          ");
					break;
				} else {
					chunks.get(choicerow).get(choicecol).grid[0] = "~~~~~~~~~~~~~~~~~~";
					chunks.get(choicerow).get(choicecol-1).grid[1] = "                 {";chunks.get(choicerow).get(choicecol+1).grid[1] = "}                 ";
					chunks.get(choicerow).get(choicecol-1).grid[2] = "                 {";chunks.get(choicerow).get(choicecol+1).grid[2] = "}                 ";
					chunks.get(choicerow).get(choicecol-1).grid[3] = "                 {";chunks.get(choicerow).get(choicecol+1).grid[3] = "}                 ";
					chunks.get(choicerow).get(choicecol).grid[4] = "~~~~~~~~~~~~~~~~~~";
					System.out.println("_".repeat(200));
					printMethod(chunks);
					chunks.get(choicerow).get(choicecol).grid[0] = "                  ";
					chunks.get(choicerow).get(choicecol-1).grid[1] = "                  ";chunks.get(choicerow).get(choicecol+1).grid[1] = "                  ";
					chunks.get(choicerow).get(choicecol-1).grid[2] = "                  ";chunks.get(choicerow).get(choicecol+1).grid[2] = "                  ";
					chunks.get(choicerow).get(choicecol-1).grid[3] = "                  ";chunks.get(choicerow).get(choicecol+1).grid[3] = "                  ";
					chunks.get(choicerow).get(choicecol).grid[4] = "                  ";
					input = scan.next();
					int choice; int result;
						if(input.equals("w") && choicerow != 5) {
							choicerow--;
						} else if(input.equals("s") && choicerow != 6) {
							choicerow++;
						} else if(input.equals("a") && choicecol != 1) {
							choicecol -= 2;
						} else if(input.equals("d") && choicecol != 3) {
							choicecol += 2;
						} else if(input.equals("c")) {
							choice = (choicerow-5) + (choicecol - 1);
							if(choice == 0) {
									//fight
								int key = initiateTurn(user, enemy, userchoice, ai, map, effect, 0); //user attacks
								if(user.rosterlist.get(userchoice).impact.equals("Extra Turn")) {
									key = initiateTurn(user,enemy,userchoice,ai,map, effect, 0);
									checkTimers(user.rosterlist.get(userchoice), enemy, chunks, effect);
								}
								if(key == 1) break;
							} else if(choice == 1) {
									//rosterz
									
								
								
									displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
							} else if (choice == 2) {
									if(choice == 2) {
										result = displayBagMenu(user, enemy, chunks, userchoice);
										if(result == 2) return 4;
									} else { 
										result = 0;
									}
									displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
									if(result == 1) {
										termkey = 1;
										break; 
									}
							} else if(choice == 3) {
									if(wildflag == 1 && choice == 3) {
										mapDialogue(chunks, "You've ran from the fight!                                                                                              > continue                                    ");
										return 3;
									}	
							}
						}
				}
			}
			checkTimers(user.rosterlist.get(userchoice), enemy, chunks, effect); //decrement timers for the user pokemon
			System.out.println("Debuff: " + user.rosterlist.get(userchoice).debuff);
			if(enemy.temphp == 0) {
				mapDialogue(chunks, enemy.name + " has fainted!                                                                                                                  > continue                            ");
				return 0;
			}
			if(enemy.impact.equals("Paralyze")) {
				mapDialogue(chunks, enemy.name + "is paralyzed!                                                                                                      > continue                                    ");
			} else {
				int key = initiateTurn(user, enemy, userchoice, ai, map, effect, 1); //ai attacks
				if(enemy.impact.equals("Extra Turn")) {
					key = initiateTurn(user,enemy,userchoice,ai,map,effect,1);
					checkTimers(enemy, user.rosterlist.get(userchoice), chunks, effect);
				}
			}
			if(!(user.rosterlist.get(userchoice).status.equals(""))) {
				effect.inflictStatus(user.rosterlist.get(userchoice), chunks);
			}
			if(!(enemy.impact.equals("")))effect.inflictImpact(enemy, user.rosterlist.get(userchoice), chunks);
			if(!(enemy.debuff.equals(""))) {
				if(!enemy.debuff.equals("Photosynthesis")) {
					effect.inflictDebuff(enemy, user.rosterlist.get(userchoice), chunks);
				} else {
					Random ran = new Random();
					effect.Photosynthesis(enemy, user.rosterlist.get(userchoice),chunks, ran);
				}
			}
			if(!(enemy.status.equals(""))) {
				effect.inflictStatus(enemy, chunks);
			}
		}
	}
	
	public int initiateTurn(Player user, Poke enemy, int userchoice, AI ai, Map map, Effect effect, int key) {
		int moveChosen;
		Poke attacker; Poke defender;
		if(key == 0) {
			attacker = user.rosterlist.get(userchoice); defender = enemy;
			if(attacker.debuff.equals("Mud")) {
				moveChosen = effect.Mud(attacker, map.chunks);
			} else {
				moveChosen = displayFightMenu(user, defender, map.chunks, userchoice);
			}
		} else {
			attacker = enemy; defender = user.rosterlist.get(userchoice);
			if(attacker.debuff.equals("Mud")) {
				moveChosen = effect.Mud(attacker, map.chunks);
			} else {
				moveChosen = ai.decideMove(attacker, user.rosterlist.get(userchoice), map);
			}
		}	
		if(moveChosen != 5) {
			mapDialogue(map.chunks, attacker.name + " used " + attacker.movesets.get(moveChosen).name + "!                                                           > continue                                    ");
			if(!(attacker.movesets.get(moveChosen).attackType.equals("Status"))){
				if(!defender.status.equals("Evade") && !defender.debuff.equals("Camoflauge")){
						double dmgcalculated = calculateDamage(attacker, defender, moveChosen, map);
						int dmg = (int)dmgcalculated;
						//if the speed formula impacts the game, just change it and test please   -past Laz
						double Accuracy = calculateAccuracy(attacker, defender, moveChosen);
						Random ran = new Random();
						if(Accuracy >= ran.nextInt(0, 100)) {
							inflictDamage(attacker, defender, dmg, effect, map.chunks, user.rosterlist.get(userchoice), enemy);
							if(!(attacker.movesets.get(moveChosen).effect.equals(""))) {
								effect.assignDebuff(defender, attacker, attacker.movesets.get(moveChosen), map.chunks);
							}	
					} else {
						mapDialogue(map.chunks, attacker.name +"'s attack missed!                                                                                                                > continue                                    ");
					}
				} else if(defender.status.equals("Evade") || defender.debuff.equals("Camoflauge")){
					mapDialogue(map.chunks, defender.name + " evaded the attack!                                                                                              > continue                                    ");
				}
			} else {
				//is a status
				effect.assignDebuff(attacker, defender, attacker.movesets.get(moveChosen), map.chunks);
				attacker.movesets.get(moveChosen).pp--;
				displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, map.chunks);
			}
			return 1;
		}
		displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, map.chunks);
		return 0;
	}
	
	public void checkTimers(Poke p, Poke o, ArrayList<ArrayList<chunk>> chunks,Effect effect) {
		
		if(p.impactcooldown > 0) p.impactcooldown--;
		if(p.debuffcooldown > 0) p.debuffcooldown--;
		if(p.statuscooldown > 0) p.statuscooldown--;
		
		if(p.impacttimer != 0)p.impacttimer--;
		if(p.impacttimer == 0 && !p.impact.equals("")) {
			effect.inflictImpact(p,o, chunks);
			p.impact = "";
			p.impactcooldown = 2;
		}
		if(p.statustimer != 0)p.statustimer--;
		if(p.statustimer == 0 && !p.status.equals("")) {
			effect.inflictStatus(p, chunks);
			p.status = "";
			p.statuscooldown = 3;
		}
		if(p.debufftimer != 0)p.debufftimer--;
		System.out.println("Timer: " +p.debufftimer);
		if(p.debufftimer == 0 && !p.debuff.equals("")) {
			System.out.println("Uh oh");
			if(p.debuff.equals("Photosynthesis")) {
				
			} else {
				effect.inflictDebuff(p,o, chunks);
			}
			p.debuff = "";
			p.debuffcooldown = 2;
		}
	}
	public static double calculateAccuracy(Poke attacker, Poke defender, int movechoice) {
		double fraction = (double) attacker.tempstats[0]/defender.tempstats[0];
		double Accuracy = (attacker.movesets.get(movechoice).accuracy) * fraction;	
		return Accuracy;
	}
	
	public static double calculateDamage(Poke attacker, Poke defender, int movechoice, Map map) {
		double damage = 0;
		int crit = 1;
		Random ran = new Random();
		double STAB;
		double strength = determineStrength(attacker.movesets.get(movechoice).type, defender);
		if(attacker.type[0].equals(attacker.movesets.get(movechoice).type) || attacker.type[1].equals(attacker.movesets.get(movechoice).type)) {
			STAB = 1.5;
		} else {
			STAB = 1;
		}
		if(attacker.movesets.get(movechoice).attackType.equals("Physical")) {
			damage = ((((((attacker.level * 2)/5)+5) * attacker.movesets.get(movechoice).power * (attacker.tempstats[1]/defender.tempstats[3]))/50)+2) * crit * (ran.nextDouble(.85, 1)) * STAB * strength;
		} else if(attacker.movesets.get(movechoice).attackType.equals("Special")) {
			damage = ((((((attacker.level * 2)/5)+5) * attacker.movesets.get(movechoice).power * (attacker.tempstats[2]/defender.tempstats[4]))/50)+2) * crit * (ran.nextDouble(.85, 1)) * STAB * strength;
		}
		return damage;
	}
	public static double determineStrength(String type, Poke Defender) {
		double strength = 1;
		String arr[] = new String[] {"Fire", "Grass", "Rock", "Electric", "Metal", "Poison", "Water"};
		int attackerelement = 0; int defenderelement = 0;
		for(int i = 0; i < 7; i++) { 
			if(arr[i].equals(type)) attackerelement = i;
			if(arr[i].equals(Defender.type[0])) defenderelement= i;
		}
		if(((attackerelement + 1) == defenderelement) || (attackerelement-6) == defenderelement) {
			strength = 2;
		}else if(attackerelement == defenderelement) {
			return 0.5;
		}
		if(!(Defender.type[1].equals(""))) {
			for(int i = 0; i < 7; i++) { 
				if(arr[i].equals(type)) attackerelement = i;
				if(arr[i].equals(Defender.type[1])) defenderelement= i;
			}
			if((attackerelement + 1) == defenderelement || (attackerelement-6) == defenderelement) {
				strength = 2;
			}else if(attackerelement == defenderelement) {
				return 0.5;
			}
		}
		return strength;
	}
	public void inflictDamage(Poke attacker, Poke defender, int damage, Effect effect, ArrayList<ArrayList<chunk>> chunks, Poke user, Poke enemy) {
		if(defender.status.equals("Reflect")) {
			effect.Reflect(defender, attacker, damage, chunks);
			mapDialogue(chunks, defender.name + " reflects the move onto " + attacker.name + "!                                                                               > continue                                           ");
			mapDialogue(chunks,"It deals " + damage +" damage to " + attacker.name + "'s hp!                                                                                  > continue                                    ");
		} else {
			defender.temphp -= damage;
			if(defender.status.equals("Thorns")) effect.Thorns(enemy, chunks);
			if(defender.temphp < 0) defender.temphp = 0;
			printInformation(user, enemy,chunks);
			mapDialogue(chunks,"It deals " + damage +" damage to " + defender.name + "'s hp!                                                                                  > continue                                    ");
		}
	}
	
	public int displayFightMenu(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, int userchoice) {
		chunks.get(4).get(0).grid[4]="__________________";chunks.get(4).get(1).grid[4]="__________________";chunks.get(4).get(2).grid[4] ="__________________";chunks.get(4).get(3).grid[4] ="__________________";chunks.get(4).get(4).grid[4] ="__________________";chunks.get(4).get(5).grid[4] ="__________________";chunks.get(4).get(6).grid[4] ="__________________";chunks.get(4).get(7).grid[4] ="__________________";chunks.get(4).get(8).grid[4] ="__________________";chunks.get(4).get(9).grid[4] ="__________________";chunks.get(4).get(10).grid[4] ="__________________";
		
		chunks.get(5).get(0).grid[0]="==================";chunks.get(5).get(1).grid[0]="==================";chunks.get(5).get(2).grid[0] ="==================";chunks.get(5).get(3).grid[0] ="==================";chunks.get(5).get(4).grid[0] ="==================";chunks.get(5).get(5).grid[0] ="==================";chunks.get(5).get(6).grid[0] ="|                 ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="                  ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="                 |";chunks.get(5).get(3).grid[1] ="|                 ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="|                 ";chunks.get(5).get(7).grid[1] ="    ==============";chunks.get(5).get(8).grid[1] ="==================";chunks.get(5).get(9).grid[1] ="==============    ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="                  ";chunks.get(5).get(2).grid[2] ="                 |";chunks.get(5).get(3).grid[2] ="|                 ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="|                 ";chunks.get(5).get(7).grid[2] ="  //              ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="              \\\\  ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                 |";chunks.get(5).get(3).grid[3] ="|                 ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="|                 ";chunks.get(5).get(7).grid[3] =" ||               ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="               || ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                 |";chunks.get(5).get(3).grid[4] ="|                 ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="|                 ";chunks.get(5).get(7).grid[4] =" ||               ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="               || ";chunks.get(5).get(10).grid[4] ="                  ";
	 
		chunks.get(6).get(0).grid[0]="==================";chunks.get(6).get(1).grid[0]="==================";chunks.get(6).get(2).grid[0] ="==================";chunks.get(6).get(3).grid[0] ="==================";chunks.get(6).get(4).grid[0] ="==================";chunks.get(6).get(5).grid[0] ="==================";chunks.get(6).get(6).grid[0] ="|                 ";chunks.get(6).get(7).grid[0] =" ||               ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="               || ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                 |";chunks.get(6).get(3).grid[1] ="|                 ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="|                 ";chunks.get(6).get(7).grid[1] =" ||               ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="               || ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="                  ";chunks.get(6).get(2).grid[2] ="                 |";chunks.get(6).get(3).grid[2] ="|                 ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="|                 ";chunks.get(6).get(7).grid[2] =" ||               ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="               || ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                 |";chunks.get(6).get(3).grid[3] ="|                 ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="|                 ";chunks.get(6).get(7).grid[3] ="  \\\\              ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="              //  ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                 |";chunks.get(6).get(3).grid[4] ="|                 ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="                  ";chunks.get(6).get(6).grid[4] ="|                 ";chunks.get(6).get(7).grid[4] ="    ==============";chunks.get(6).get(8).grid[4] ="==================";chunks.get(6).get(9).grid[4] ="==============    ";chunks.get(6).get(10).grid[4] ="                  ";
		
		chunks.get(7).get(0).grid[0]="==================";chunks.get(7).get(1).grid[0]="==================";chunks.get(7).get(2).grid[0] ="==================";chunks.get(7).get(3).grid[0] ="==================";chunks.get(7).get(4).grid[0] ="==================";chunks.get(7).get(5).grid[0] ="==================";chunks.get(7).get(6).grid[0] ="|                 ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="//////////////////";chunks.get(7).get(1).grid[1]="//////////////////";chunks.get(7).get(2).grid[1] ="//////////////////";chunks.get(7).get(3).grid[1] ="//////////////////";chunks.get(7).get(4).grid[1] ="//////////////////";chunks.get(7).get(5).grid[1] ="//////////////////";chunks.get(7).get(6).grid[1] ="|                 ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="//////////////////";chunks.get(7).get(1).grid[2]="//////////////////";chunks.get(7).get(2).grid[2] ="//////////////////";chunks.get(7).get(3).grid[2] ="//////////////////";chunks.get(7).get(4).grid[2] ="//////////////////";chunks.get(7).get(5).grid[2] ="//////////////////";chunks.get(7).get(6).grid[2] ="|                 ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="//////////////////";chunks.get(7).get(1).grid[3]="//////////////////";chunks.get(7).get(2).grid[3] ="//////////////////";chunks.get(7).get(3).grid[3] ="//////////////////";chunks.get(7).get(4).grid[3] ="//////////////////";chunks.get(7).get(5).grid[3] ="//////////////////";chunks.get(7).get(6).grid[3] ="|                 ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="//////////////////";chunks.get(7).get(1).grid[4]="//////////////////";chunks.get(7).get(2).grid[4] ="//////////////////";chunks.get(7).get(3).grid[4] ="//////////////////";chunks.get(7).get(4).grid[4] ="//////////////////";chunks.get(7).get(5).grid[4] ="//////////////////";chunks.get(7).get(6).grid[4] ="|                 ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
		int row = 5; int col = 1; 
		for(int i = 0; i < 4; i++) {
			try {
				chunks.get(row).get(col).grid[1] = " " + user.rosterlist.get(userchoice).movesets.get(i).name;chunks.get(row).get(col+1).grid[1] = " " + user.rosterlist.get(userchoice).movesets.get(i).type;
				chunks.get(row).get(col).grid[2] = "   PP: " + user.rosterlist.get(userchoice).movesets.get(i).pp;
				chunks.get(row).get(col).grid[3] = "   POW " + user.rosterlist.get(userchoice).movesets.get(i).power; chunks.get(row).get(col+1).grid[3] = "   ACC " + user.rosterlist.get(userchoice).movesets.get(i).accuracy;
				chunks.get(row).get(col).grid[4] = "   " + user.rosterlist.get(userchoice).movesets.get(i).attackType; chunks.get(row).get(col+1).grid[4] = "  " + user.rosterlist.get(userchoice).movesets.get(i).effect; 
				col += 3;
				if(col > 4) {
					row = 6; col = 1;
				}
			}catch(Exception e) {
				
			}
		}
		for(int i = 0; i < 18; i++) {
			//names
			if(chunks.get(5).get(1).grid[1].length() <= i) chunks.get(5).get(1).grid[1] += " ";
			if(chunks.get(5).get(4).grid[1].length() <= i) chunks.get(5).get(4).grid[1] += " ";
			if(chunks.get(6).get(1).grid[1].length() <= i) chunks.get(6).get(1).grid[1] += " ";
			if(chunks.get(6).get(4).grid[1].length() <= i) chunks.get(6).get(4).grid[1] += " ";
			//type
			if(chunks.get(5).get(2).grid[1].length() <= i) chunks.get(5).get(2).grid[1] += " ";
			if(chunks.get(5).get(5).grid[1].length() <= i) chunks.get(5).get(5).grid[1] += " ";
			if(chunks.get(6).get(2).grid[1].length() <= i) chunks.get(6).get(2).grid[1] += " ";
			if(chunks.get(6).get(5).grid[1].length() <= i) chunks.get(6).get(5).grid[1] += " ";
			//power
			if(chunks.get(5).get(1).grid[3].length() <= i) chunks.get(5).get(1).grid[3] += " ";
			if(chunks.get(5).get(4).grid[3].length() <= i) chunks.get(5).get(4).grid[3] += " ";
			if(chunks.get(6).get(1).grid[3].length() <= i) chunks.get(6).get(1).grid[3] += " ";
			if(chunks.get(6).get(4).grid[3].length() <= i) chunks.get(6).get(4).grid[3] += " ";
			//acc
			if(chunks.get(5).get(2).grid[3].length() <= i) chunks.get(5).get(2).grid[3] += " ";
			if(chunks.get(5).get(5).grid[3].length() <= i) chunks.get(5).get(5).grid[3] += " ";
			if(chunks.get(6).get(2).grid[3].length() <= i) chunks.get(6).get(2).grid[3] += " ";
			if(chunks.get(6).get(5).grid[3].length() <= i) chunks.get(6).get(5).grid[3] += " ";
			//attackType
			if(chunks.get(5).get(1).grid[4].length() <= i) chunks.get(5).get(1).grid[4] += " ";
			if(chunks.get(5).get(4).grid[4].length() <= i) chunks.get(5).get(4).grid[4] += " ";
			if(chunks.get(6).get(1).grid[4].length() <= i) chunks.get(6).get(1).grid[4] += " ";
			if(chunks.get(6).get(4).grid[4].length() <= i) chunks.get(6).get(4).grid[4] += " ";
			//effect
			if(chunks.get(5).get(2).grid[4].length() <= i) chunks.get(5).get(2).grid[4] += " ";
			if(chunks.get(5).get(5).grid[4].length() <= i) chunks.get(5).get(5).grid[4] += " ";
			if(chunks.get(6).get(2).grid[4].length() <= i) chunks.get(6).get(2).grid[4] += " ";
			if(chunks.get(6).get(5).grid[4].length() <= i) chunks.get(6).get(5).grid[4] += " ";
			//pp
			if(chunks.get(5).get(1).grid[2].length() <= i) chunks.get(5).get(1).grid[2] += " ";
			if(chunks.get(5).get(4).grid[2].length() <= i) chunks.get(5).get(4).grid[2] += " ";
			if(chunks.get(6).get(1).grid[2].length() <= i) chunks.get(6).get(1).grid[2] += " ";
			if(chunks.get(6).get(4).grid[2].length() <= i) chunks.get(6).get(4).grid[2] += " ";
		}
		Scanner scan = new Scanner(System.in);
		String input = "";
		row = 5; col = 1;
		
		while(true) {
			game.spaces();	
			for(int i = 1; i < 5; i++) {
				StringBuilder sb1 = new StringBuilder(chunks.get(row).get(col-1).grid[i]);
				sb1.replace(0, 3, "|>>");
				chunks.get(row).get(col-1).grid[i] = sb1.toString();
				StringBuilder sb2 = new StringBuilder(chunks.get(row).get(col+1).grid[i]);
				sb2.replace(15, 18, "<<|");
				chunks.get(row).get(col+1).grid[i] = sb2.toString();
			}
			printMethod(chunks);
			input = scan.next();
			for(int i = 1; i < 5; i++) {
				StringBuilder sb1 = new StringBuilder(chunks.get(row).get(col-1).grid[i]);
				sb1.replace(0, 3, "|  ");
				chunks.get(row).get(col-1).grid[i] = sb1.toString();
				StringBuilder sb2 = new StringBuilder(chunks.get(row).get(col+1).grid[i]);
				sb2.replace(15, 18, "  |");
				chunks.get(row).get(col+1).grid[i] = sb2.toString();
			}
			if(input.equals("o")) {
				return 5;
			} else if(input.equals("w")&&row!=5) {
				row--;
			} else if(input.equals("s")&&row!=6) {
				row++;
			} else if(input.equals("a")&&col!=1) {
				col -= 3;;
			} else if(input.equals("d")&&col!=4) {
				col += 3;
			} else if(input.equals("c")) {
				int choice = 0;
				if((row-5) > 0) choice +=2;
				if((col-1) > 0) choice += 1;
				try {
					if((user.rosterlist.get(userchoice).movesets.get(choice)) != null && user.rosterlist.get(userchoice).movesets.get(choice).pp != 0) {
						return choice;
					}
				} catch(Exception e) {
				}	
			}	
		}	
	}
	
	public int displayBagMenu(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, int userchoice) {
		if(user.backpack.numItems != 0) {
		chunks.get(0).get(0).grid[0]="                  ";chunks.get(0).get(1).grid[0]="                  ";chunks.get(0).get(2).grid[0] ="                  ";chunks.get(0).get(3).grid[0] ="                  ";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="                  ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
		chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="                  ";chunks.get(0).get(2).grid[1] ="                  ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
		chunks.get(0).get(0).grid[2]="                  ";chunks.get(0).get(1).grid[2]="                  ";chunks.get(0).get(2).grid[2] ="==================";chunks.get(0).get(3).grid[2] ="==================";chunks.get(0).get(4).grid[2] ="                  ";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] ="                  ";chunks.get(0).get(7).grid[2] ="==================";chunks.get(0).get(8).grid[2] ="==================";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="                  ";
		chunks.get(0).get(0).grid[3]="                  ";chunks.get(0).get(1).grid[3]="                 |";chunks.get(0).get(2).grid[3] ="            ~~~~~~";chunks.get(0).get(3).grid[3] ="~~~~~~            ";chunks.get(0).get(4).grid[3] ="|                 ";chunks.get(0).get(5).grid[3] ="                  ";chunks.get(0).get(6).grid[3] ="                 |";chunks.get(0).get(7).grid[3] ="           ~~~~~~~";chunks.get(0).get(8).grid[3] ="~~~~~~~           ";chunks.get(0).get(9).grid[3] ="|                 ";chunks.get(0).get(10).grid[3] ="                  ";
		chunks.get(0).get(0).grid[4]="                  ";chunks.get(0).get(1).grid[4]="                 |";chunks.get(0).get(2).grid[4] ="           {  I T ";chunks.get(0).get(3).grid[4] ="E M S }           ";chunks.get(0).get(4).grid[4] ="|                 ";chunks.get(0).get(5).grid[4] ="                  ";chunks.get(0).get(6).grid[4] ="                 |";chunks.get(0).get(7).grid[4] ="          { R O S ";chunks.get(0).get(8).grid[4] ="T E R  }          ";chunks.get(0).get(9).grid[4] ="|                 ";chunks.get(0).get(10).grid[4] ="                  ";
		chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="                 |";chunks.get(1).get(2).grid[0] ="            ~~~~~~";chunks.get(1).get(3).grid[0] ="~~~~~~            ";chunks.get(1).get(4).grid[0] ="|                 ";chunks.get(1).get(5).grid[0] ="==================";chunks.get(1).get(6).grid[0] ="                 |";chunks.get(1).get(7).grid[0] ="           ~~~~~~~";chunks.get(1).get(8).grid[0] ="~~~~~~~           ";chunks.get(1).get(9).grid[0] ="|                 ";chunks.get(1).get(10).grid[0] ="                  ";
		chunks.get(1).get(0).grid[1]="                  ";chunks.get(1).get(1).grid[1]="                 |";chunks.get(1).get(2).grid[1] ="==================";chunks.get(1).get(3).grid[1] ="==================";chunks.get(1).get(4).grid[1] ="|                |";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="|                |";chunks.get(1).get(7).grid[1] ="==================";chunks.get(1).get(8).grid[1] ="==================";chunks.get(1).get(9).grid[1] ="|                 ";chunks.get(1).get(10).grid[1] ="                  ";
		chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="                 |";chunks.get(1).get(2).grid[2] ="                  ";chunks.get(1).get(3).grid[2] ="                  ";chunks.get(1).get(4).grid[2] ="|                |";chunks.get(1).get(5).grid[2] ="  S E L E C T E D ";chunks.get(1).get(6).grid[2] ="|                |";chunks.get(1).get(7).grid[2] ="                  ";chunks.get(1).get(8).grid[2] ="                  ";chunks.get(1).get(9).grid[2] ="|                 ";chunks.get(1).get(10).grid[2] ="                  ";
		chunks.get(1).get(0).grid[3]="                  ";chunks.get(1).get(1).grid[3]="                 |";chunks.get(1).get(2).grid[3] ="                  ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="|                |";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="|                |";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="|                 ";chunks.get(1).get(10).grid[3] ="                  ";
		chunks.get(1).get(0).grid[4]="                  ";chunks.get(1).get(1).grid[4]="                 |";chunks.get(1).get(2).grid[4] ="                  ";chunks.get(1).get(3).grid[4] ="                  ";chunks.get(1).get(4).grid[4] ="|                |";chunks.get(1).get(5).grid[4] ="==================";chunks.get(1).get(6).grid[4] ="|                |";chunks.get(1).get(7).grid[4] ="                  ";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="|                 ";chunks.get(1).get(10).grid[4] ="                  ";
		chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="                 |";chunks.get(2).get(2).grid[0] ="                  ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="|                |";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="|                |";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="|                 ";chunks.get(2).get(10).grid[0] ="                  ";
		chunks.get(2).get(0).grid[1]="                  ";chunks.get(2).get(1).grid[1]="                 |";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="|                |";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="|                |";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="|                 ";chunks.get(2).get(10).grid[1] ="                  ";
		chunks.get(2).get(0).grid[2]="                  ";chunks.get(2).get(1).grid[2]="                 |";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="|                |";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="|                |";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="|                 ";chunks.get(2).get(10).grid[2] ="                  ";
		chunks.get(2).get(0).grid[3]="                  ";chunks.get(2).get(1).grid[3]="                 |";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="|                |";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="|                |";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="|                 ";chunks.get(2).get(10).grid[3] ="                  ";
		chunks.get(2).get(0).grid[4]="                  ";chunks.get(2).get(1).grid[4]="                 |";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="|                |";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="|                |";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="|                 ";chunks.get(2).get(10).grid[4] ="                  ";
		chunks.get(3).get(0).grid[0]="                  ";chunks.get(3).get(1).grid[0]="                 |";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="|                 ";chunks.get(3).get(5).grid[0] ="==================";chunks.get(3).get(6).grid[0] ="                 |";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="|                 ";chunks.get(3).get(10).grid[0] ="                  ";
		chunks.get(3).get(0).grid[1]="                  ";chunks.get(3).get(1).grid[1]="                 |";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="|                 ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                 |";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="|                 ";chunks.get(3).get(10).grid[1] ="                  ";
		chunks.get(3).get(0).grid[2]="                  ";chunks.get(3).get(1).grid[2]="                 |";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="|                 ";chunks.get(3).get(5).grid[2] ="        | |       ";chunks.get(3).get(6).grid[2] ="                 |";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="|                 ";chunks.get(3).get(10).grid[2] ="                  ";
		chunks.get(3).get(0).grid[3]="                  ";chunks.get(3).get(1).grid[3]="                 |";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="|                 ";chunks.get(3).get(5).grid[3] ="       \\   /      ";chunks.get(3).get(6).grid[3] ="                 |";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="|                 ";chunks.get(3).get(10).grid[3] ="                  ";
		chunks.get(3).get(0).grid[4]="                  ";chunks.get(3).get(1).grid[4]="                 |";chunks.get(3).get(2).grid[4] ="                  ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="|                 ";chunks.get(3).get(5).grid[4] ="        \\ /       ";chunks.get(3).get(6).grid[4] ="                 |";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="|                 ";chunks.get(3).get(10).grid[4] ="                  ";
		chunks.get(4).get(0).grid[0]="                  ";chunks.get(4).get(1).grid[0]="                 |";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="|                 ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                 |";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="|                 ";chunks.get(4).get(10).grid[0] ="                  ";
		chunks.get(4).get(0).grid[1]="                  ";chunks.get(4).get(1).grid[1]="                 |";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="|                 ";chunks.get(4).get(5).grid[1] ="==================";chunks.get(4).get(6).grid[1] ="                 |";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="|                 ";chunks.get(4).get(10).grid[1] ="                  ";
		chunks.get(4).get(0).grid[2]="                  ";chunks.get(4).get(1).grid[2]="                 |";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="|                |";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="|                |";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="|                 ";chunks.get(4).get(10).grid[2] ="                  ";
		chunks.get(4).get(0).grid[3]="                  ";chunks.get(4).get(1).grid[3]="                 |";chunks.get(4).get(2).grid[3] ="                  ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="|                |";chunks.get(4).get(5).grid[3] ="   P O K E M O N  ";chunks.get(4).get(6).grid[3] ="|                |";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="|                 ";chunks.get(4).get(10).grid[3] ="                  ";
		chunks.get(4).get(0).grid[4]="                  ";chunks.get(4).get(1).grid[4]="                 |";chunks.get(4).get(2).grid[4] ="                  ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="|                |";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="|                |";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                  ";chunks.get(4).get(9).grid[4] ="|                 ";chunks.get(4).get(10).grid[4] ="                  ";
		chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="                 |";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="|                |";chunks.get(5).get(5).grid[0] ="==================";chunks.get(5).get(6).grid[0] ="|                |";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="|                 ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                 |";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="|                |";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="|                |";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="|                 ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="                 |";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="|                |";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="|                |";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="|                 ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                 |";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="|                |";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="|                |";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="|                 ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                 |";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="|                |";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="|                |";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="|                 ";chunks.get(5).get(10).grid[4] ="                  ";
		chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                 |";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="|                |";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="|                |";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="|                 ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                 |";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="|                 ";chunks.get(6).get(5).grid[1] ="==================";chunks.get(6).get(6).grid[1] ="                 |";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="|                 ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="                 |";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="|                 ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                 |";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="|                 ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                 |";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="|                 ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                 |";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="|                 ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                 |";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="|                 ";chunks.get(6).get(5).grid[4] ="~~~~~~~~~~~~~~~~~~";chunks.get(6).get(6).grid[4] ="                 |";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="|                 ";chunks.get(6).get(10).grid[4] ="                  ";
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                 |";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="|                |";chunks.get(7).get(5).grid[0] ="     Confirm?     ";chunks.get(7).get(6).grid[0] ="|                |";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="|                 ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                 |";chunks.get(7).get(2).grid[1] ="__________________";chunks.get(7).get(3).grid[1] ="__________________";chunks.get(7).get(4).grid[1] ="|                |";chunks.get(7).get(5).grid[1] ="   yes       no   ";chunks.get(7).get(6).grid[1] ="|                |";chunks.get(7).get(7).grid[1] =" _________________";chunks.get(7).get(8).grid[1] ="__________________";chunks.get(7).get(9).grid[1] ="|                 ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                 |";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="|                 ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="~~~~~~~~~~~~~~~~~~";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="                  ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
		
		String bagpositions[] = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",};
		String fragmented; String fragmented2; ArrayList<Item> items = new ArrayList<>();
		int itemrow = 2; int itemcol = 2; int itemgrid = 0; int item = 0;
		for(String key : user.backpack.items.keySet()) {
			items.add(user.backpack.items.get(key));
			chunks.get(itemrow).get(2).grid[itemgrid] = "                " + bagpositions[item] + " ";
			fragmented = user.backpack.items.get(key).name + " " + String.valueOf(user.backpack.items.get(key).amount);
			chunks.get(itemrow).get(3).grid[itemgrid] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(itemrow).get(3).grid[itemgrid] += " ";
			}
			itemgrid++;
			item++;
			if(itemgrid == 5) {
				itemgrid = 0;
				itemrow++;
			}
		}
		int rosterrow = 1; int rostercol = 7; int rostergrid = 3;
		for(int i = 0; i < Player.numRoster; i++) {
			fragmented = "   "+user.rosterlist.get(i).name;
			fragmented2 = "Lvl:" + user.rosterlist.get(i).level + " HP:" + user.rosterlist.get(i).temphp + "/" + user.rosterlist.get(i).hp;;
			chunks.get(rosterrow).get(rostercol).grid[rostergrid] = fragmented;
			chunks.get(rosterrow).get(rostercol+1).grid[rostergrid] = fragmented2;
			for(int j = fragmented.length(); j < 18; j++) {
				chunks.get(rosterrow).get(rostercol).grid[rostergrid] += " ";
			}
			for(int j = fragmented2.length(); j < 18; j++) {
				chunks.get(rosterrow).get(rostercol+1).grid[rostergrid] += " "; 
			}
			rosterrow++;
		}
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		int useritemrow = 2; int useritemgrid = 0;
		int pos = 0; bagpositions[pos] = ">"; int itemchosen;
		int rate = 0;
		int rosterpos = 0;
		int userrosterrow= 0; int userrostergrid = 0;
		while(true) {
			pos = (useritemrow - 2) + (useritemgrid + rate);
			bagpositions[pos] = ">";
			chunks.get(useritemrow).get(2).grid[useritemgrid] = "                " + bagpositions[pos] + " ";
			System.out.println(pos + " " + useritemrow + "," + useritemgrid + " rate: " + rate);
			System.out.println("_".repeat(200));
			printMethod(chunks);
		input = scan.next();
		bagpositions[pos] = " ";
		chunks.get(useritemrow).get(2).grid[useritemgrid] = "                " + bagpositions[pos] + " ";
		if(user.backpack.numItems > 0) {
			if(input.equals("w") && pos != 0) {
				bagpositions[pos] = " ";
				useritemgrid--;
			} else if(input.equals("w") && pos == 0) {
				bagpositions[pos] = " ";
				pos = user.backpack.numItems - 1;
			}
			if(input.equals("s") && pos != user.backpack.numItems-1) {
				bagpositions[pos] = " ";
				useritemgrid++;
			} else if(input.equals("s") && pos == user.backpack.numItems-1) {
				bagpositions[pos] = " ";
				pos = 0;
			} 
			if(input.equals("o")) {
				return 0;
			}
			if((useritemrow - 2) + (useritemgrid + rate) != user.backpack.numItems+1) {
				if(useritemgrid < 0 && useritemrow != 2) {
					rate -= 4;
					useritemgrid = 4;
					useritemrow--;
				}
				if(useritemgrid > 4) {
					rate += 4;
					useritemgrid = 0;
					useritemrow++;
				}
			}
			if(input.equals("c")) {
				itemchosen = pos;
				String stringchunk = chunks.get(useritemrow).get(3).grid[useritemgrid];
				for(int i = stringchunk.length(); i > 0; i--) {
					System.out.println(stringchunk.substring(0,i));
					if(user.backpack.items.containsKey(stringchunk.substring(0, i-1))) {
						stringchunk = user.backpack.items.get(stringchunk.substring(0,i-1)).name;
						break;
					}
				}
				chunks.get(2).get(5).grid[2] = "  " + stringchunk;
				for(int i = stringchunk.length(); i < 16; i++) {
					chunks.get(2).get(5).grid[2] += " ";
				}
				while(true) {
					chunks.get(rosterpos+1).get(7).grid[2] = "~~~~~~~~~~~~~~~~~~";chunks.get(rosterpos+1).get(8).grid[2] = "~~~~~~~~~~~~~~~~~~";
					chunks.get(rosterpos+1).get(7).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(rosterpos+1).get(8).grid[4] = "~~~~~~~~~~~~~~~~~~";
					printMethod(chunks);
					chunks.get(rosterpos+1).get(7).grid[2] = "                  ";chunks.get(rosterpos+1).get(8).grid[2] = "                  ";
					chunks.get(rosterpos+1).get(7).grid[4] = "                  ";chunks.get(rosterpos+1).get(8).grid[4] = "                  ";
					input = scan.next();
					if(input.equals("w") && rosterpos != 0) {
						rosterpos--;
					} else if(input.equals("s") && rosterpos != Player.numRoster-1) {
						rosterpos++;
					} else if(input.equals("c")) {
						chunks.get(5).get(5).grid[3] = " "+user.rosterlist.get(rosterpos).name;
						for(int i = 1 + user.rosterlist.get(rosterpos).name.length(); i < 18; i++) {
							chunks.get(5).get(5).grid[3] += " ";
						}
						pos = 0;
						String confirmpositions[] = {" ", " "};						
						while(true) {
							confirmpositions[pos] = ">";
							chunks.get(7).get(5).grid[1] = "  "+confirmpositions[0]+"yes      " + confirmpositions[1] +"no   ";
							printMethod(chunks);
							input = scan.next();
							confirmpositions[pos] = " ";
							if(input.equals("a") && pos != 0) {
								pos--;
							} else if(input.equals("d") && pos != 1) {
								pos++;
							} else if(input.equals("c")) {
								if(pos == 0) {
									try {
									if(items.get(itemchosen) instanceof Ball) {
										int res = capturePokemon(enemy, chunks, (Ball) items.get(itemchosen), user);
										items.get(itemchosen).decrementItem(user);
										if(res == 1) {
											return 2;
										} else {
											return 1;
										}
									}
									items.get(itemchosen).applyItem(user.rosterlist.get(rosterpos), items.get(itemchosen), chunks, user);
									return 1;
									}catch(Exception e) {
										return 0;
									}
								}
								if(pos == 1) return 0;
							} else if(input.equals("o")) {
								chunks.get(5).get(5).grid[3] = "                  ";
								confirmpositions[pos] = " ";
								break;
							}
						}
					} else if(input.equals("o")) {
						chunks.get(2).get(5).grid[2] = "                  ";
						break;
					}
				}
			}
		} else {
			return 0;
		}			
		}
		}
		return 0;
	}
	
	public int capturePokemon(Poke victim, ArrayList<ArrayList<chunk>> chunks, Ball item, Player user) {
		double chance = (((3*victim.hp - (2 * victim.temphp))%(3*victim.hp)) * item.accuracy);
		Random ran = new Random();
		if(ran.nextInt(1, 255) <= chance) {
			//initiate capture script
			try {
				user.pokedex.add(victim);
				mapDialogue(chunks, victim.name + " has been successfully caught! Added pokemon to your pokedex!                                                                              ");
				return 1;
			} catch(Exception e) {
				mapDialogue(chunks, "Error, failed to add pokemon to pokedex                                                                                                                ");
				return 0;
			}                                                                                              
			
		} else {
			mapDialogue(chunks, "The " + item.name + " missed!                                                               ");
			return 0;
		}
	}
	
	public void displayRosterMenu(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, int userchoice) {
		chunks.get(0).get(0).grid[0]="                  ";chunks.get(0).get(1).grid[0]="                  ";chunks.get(0).get(2).grid[0] ="                  ";chunks.get(0).get(3).grid[0] ="                  ";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="                  ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
		chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="               ___";chunks.get(0).get(2).grid[1] ="__________________";chunks.get(0).get(3).grid[1] ="___               ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="__________________";chunks.get(0).get(9).grid[1] ="__________________";chunks.get(0).get(10).grid[1] ="                  ";
		chunks.get(0).get(0).grid[2]="                  ";chunks.get(0).get(1).grid[2]="              {   ";chunks.get(0).get(2).grid[2] ="                  ";chunks.get(0).get(3).grid[2] ="   }              ";chunks.get(0).get(4).grid[2] ="                  ";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] ="                  ";chunks.get(0).get(7).grid[2] ="                 {";chunks.get(0).get(8).grid[2] ="                  ";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="}                 ";
		chunks.get(0).get(0).grid[3]="                  ";chunks.get(0).get(1).grid[3]="              {   ";chunks.get(0).get(2).grid[3] ="   R O S T E R    ";chunks.get(0).get(3).grid[3] ="   }              ";chunks.get(0).get(4).grid[3] ="                  ";chunks.get(0).get(5).grid[3] ="                  ";chunks.get(0).get(6).grid[3] ="~~~~~~~~~~~~~~~~~~";chunks.get(0).get(7).grid[3] ="                 {";chunks.get(0).get(8).grid[3] ="              S T ";chunks.get(0).get(9).grid[3] ="A T S             ";chunks.get(0).get(10).grid[3] ="}                 ";
		chunks.get(0).get(0).grid[4]="                  ";chunks.get(0).get(1).grid[4]="              {___";chunks.get(0).get(2).grid[4] ="__________________";chunks.get(0).get(3).grid[4] ="___}              ";chunks.get(0).get(4).grid[4] ="                  ";chunks.get(0).get(5).grid[4] ="                []";chunks.get(0).get(6).grid[4] ="~~~~~~~~~~~~~~~~~~";chunks.get(0).get(7).grid[4] ="[]               {";chunks.get(0).get(8).grid[4] ="__________________";chunks.get(0).get(9).grid[4] ="__________________";chunks.get(0).get(10).grid[4] ="}                 ";
		chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="              |   ";chunks.get(1).get(2).grid[0] ="                  ";chunks.get(1).get(3).grid[0] ="   |              ";chunks.get(1).get(4).grid[0] ="                  ";chunks.get(1).get(5).grid[0] ="                ||";chunks.get(1).get(6).grid[0] ="                  ";chunks.get(1).get(7).grid[0] ="||               |";chunks.get(1).get(8).grid[0] ="                  ";chunks.get(1).get(9).grid[0] ="                  ";chunks.get(1).get(10).grid[0] ="|                 ";
		chunks.get(1).get(0).grid[1]="                  ";chunks.get(1).get(1).grid[1]="              |   ";chunks.get(1).get(2).grid[1] ="                  ";chunks.get(1).get(3).grid[1] ="   |              ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                ||";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="||               |";chunks.get(1).get(8).grid[1] ="                  ";chunks.get(1).get(9).grid[1] ="                  ";chunks.get(1).get(10).grid[1] ="|                 ";
		chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="              |   ";chunks.get(1).get(2).grid[2] ="                  ";chunks.get(1).get(3).grid[2] ="   |              ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="                ||";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="||               |";chunks.get(1).get(8).grid[2] ="  *               ";chunks.get(1).get(9).grid[2] ="   *              ";chunks.get(1).get(10).grid[2] ="|                 ";
		chunks.get(1).get(0).grid[3]="                  ";chunks.get(1).get(1).grid[3]="              |   ";chunks.get(1).get(2).grid[3] ="                  ";chunks.get(1).get(3).grid[3] ="   |              ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                ||";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="||               |";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="                  ";chunks.get(1).get(10).grid[3] ="|                 ";
		chunks.get(1).get(0).grid[4]="                  ";chunks.get(1).get(1).grid[4]="              |   ";chunks.get(1).get(2).grid[4] ="                  ";chunks.get(1).get(3).grid[4] ="   |              ";chunks.get(1).get(4).grid[4] ="                  ";chunks.get(1).get(5).grid[4] ="                ||";chunks.get(1).get(6).grid[4] ="                  ";chunks.get(1).get(7).grid[4] ="||               |";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="                  ";chunks.get(1).get(10).grid[4] ="|                 ";
		chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="              |   ";chunks.get(2).get(2).grid[0] ="                  ";chunks.get(2).get(3).grid[0] ="   |              ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                []";chunks.get(2).get(6).grid[0] ="~~~~~~~~~~~~~~~~~~";chunks.get(2).get(7).grid[0] ="[]               |";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="|                 ";
		chunks.get(2).get(0).grid[1]="                  ";chunks.get(2).get(1).grid[1]="              |   ";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="   |              ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="~~~~~~~~~~~~~~~~~~";chunks.get(2).get(7).grid[1] ="                 |";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="|                 ";
		chunks.get(2).get(0).grid[2]="                  ";chunks.get(2).get(1).grid[2]="              |   ";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="   |              ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                 |";chunks.get(2).get(8).grid[2] ="  *               ";chunks.get(2).get(9).grid[2] ="   *              ";chunks.get(2).get(10).grid[2] ="|                 ";
		chunks.get(2).get(0).grid[3]="                  ";chunks.get(2).get(1).grid[3]="              |   ";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="   |              ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                 |";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="                  ";chunks.get(2).get(10).grid[3] ="|                 ";
		chunks.get(2).get(0).grid[4]="                  ";chunks.get(2).get(1).grid[4]="              |   ";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="   |              ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                 |";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="                  ";chunks.get(2).get(10).grid[4] ="|                 ";
		chunks.get(3).get(0).grid[0]="                  ";chunks.get(3).get(1).grid[0]="              |   ";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="   |              ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                 |";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="                  ";chunks.get(3).get(10).grid[0] ="|                 ";
		chunks.get(3).get(0).grid[1]="                  ";chunks.get(3).get(1).grid[1]="              |   ";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="   |              ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                 |";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="                  ";chunks.get(3).get(10).grid[1] ="|                 ";
		chunks.get(3).get(0).grid[2]="                  ";chunks.get(3).get(1).grid[2]="              |   ";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="   |              ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                 |";chunks.get(3).get(8).grid[2] ="  *               ";chunks.get(3).get(9).grid[2] ="   *              ";chunks.get(3).get(10).grid[2] ="|                 ";
		chunks.get(3).get(0).grid[3]="                  ";chunks.get(3).get(1).grid[3]="              |   ";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="   |              ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                 |";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="                  ";chunks.get(3).get(10).grid[3] ="|                 ";
		chunks.get(3).get(0).grid[4]="                  ";chunks.get(3).get(1).grid[4]="              |   ";chunks.get(3).get(2).grid[4] ="                  ";chunks.get(3).get(3).grid[4] ="   |              ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                 |";chunks.get(3).get(8).grid[4] ="__________________";chunks.get(3).get(9).grid[4] ="__________________";chunks.get(3).get(10).grid[4] ="                  ";
		chunks.get(4).get(0).grid[0]="                  ";chunks.get(4).get(1).grid[0]="              |   ";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="   |              ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="                  ";chunks.get(4).get(10).grid[0] ="                  ";
		chunks.get(4).get(0).grid[1]="                  ";chunks.get(4).get(1).grid[1]="              |   ";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="   |              ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="                  ";chunks.get(4).get(10).grid[1] ="                  ";
		chunks.get(4).get(0).grid[2]="                  ";chunks.get(4).get(1).grid[2]="              |   ";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="   |              ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="                  ";chunks.get(4).get(10).grid[2] ="                  ";
		chunks.get(4).get(0).grid[3]="                  ";chunks.get(4).get(1).grid[3]="              |   ";chunks.get(4).get(2).grid[3] ="                  ";chunks.get(4).get(3).grid[3] ="   |              ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="                  ";chunks.get(4).get(10).grid[3] ="                  ";
		chunks.get(4).get(0).grid[4]="                  ";chunks.get(4).get(1).grid[4]="              |   ";chunks.get(4).get(2).grid[4] ="                  ";chunks.get(4).get(3).grid[4] ="   |              ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                  ";chunks.get(4).get(9).grid[4] ="                  ";chunks.get(4).get(10).grid[4] ="                  ";
		chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="              |   ";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="   |              ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="                  ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="              |   ";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="   |              ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="                  ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="              |   ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="   |              ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="                  ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="              |   ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="   |              ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="                  ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="              |   ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="   |              ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="                  ";chunks.get(5).get(10).grid[4] ="                  ";
		chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="              |   ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="   |              ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="                  ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="              |   ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="   |              ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="                  ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="              |   ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="   |              ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="                  ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="              |   ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="   |              ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="                  ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="              |   ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="   |              ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="                  ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="                  ";chunks.get(6).get(10).grid[4] ="                  ";
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="              |   ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="   |              ";chunks.get(7).get(4).grid[0] ="                  ";chunks.get(7).get(5).grid[0] ="                  ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="              |   ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="   |              ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="                  ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="              |___";chunks.get(7).get(2).grid[2] ="__________________";chunks.get(7).get(3).grid[2] ="___|              ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="                  ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="                  ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
				
	}
	
	public void displayMainBattleMenu(Poke curr, Poke enemy,ArrayList<ArrayList<chunk>> chunks) {
		chunks.get(0).get(0).grid[0]="    ______________";chunks.get(0).get(1).grid[0]="__________________";chunks.get(0).get(2).grid[0] ="__________________";chunks.get(0).get(3).grid[0] ="__________________";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="__________________";chunks.get(0).get(8).grid[0] ="__________________";chunks.get(0).get(9).grid[0] ="__________________";chunks.get(0).get(10).grid[0] ="______________    ";
		chunks.get(0).get(0).grid[1]="   /              ";chunks.get(0).get(1).grid[1]="                  ";chunks.get(0).get(2).grid[1] ="                  ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="\\.                ";chunks.get(0).get(5).grid[1] ="  ______________  ";chunks.get(0).get(6).grid[1] ="                ./";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="              \\   ";
		chunks.get(0).get(0).grid[2]="  /               ";chunks.get(0).get(1).grid[2]="                  ";chunks.get(0).get(2).grid[2] ="                  ";chunks.get(0).get(3).grid[2] ="                  ";chunks.get(0).get(4).grid[2] ="  \\.              ";chunks.get(0).get(5).grid[2] =" /     ....     \\ ";chunks.get(0).get(6).grid[2] ="              ./  ";chunks.get(0).get(7).grid[2] ="                  ";chunks.get(0).get(8).grid[2] ="                  ";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="               \\  ";
		chunks.get(0).get(0).grid[3]=" /                ";chunks.get(0).get(1).grid[3]="                  ";chunks.get(0).get(2).grid[3] ="                  ";chunks.get(0).get(3).grid[3] ="                  ";chunks.get(0).get(4).grid[3] ="    \\_____________";chunks.get(0).get(5).grid[3] ="/_____/____\\_____\\";chunks.get(0).get(6).grid[3] ="_____________/    ";chunks.get(0).get(7).grid[3] ="                  ";chunks.get(0).get(8).grid[3] ="                  ";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                \\ ";
		chunks.get(0).get(0).grid[4]="|                 ";chunks.get(0).get(1).grid[4]="                  ";chunks.get(0).get(2).grid[4] ="                  ";chunks.get(0).get(3).grid[4] ="                  ";chunks.get(0).get(4).grid[4] ="                  ";chunks.get(0).get(5).grid[4] ="                  ";chunks.get(0).get(6).grid[4] ="                  ";chunks.get(0).get(7).grid[4] ="                  ";chunks.get(0).get(8).grid[4] ="                  ";chunks.get(0).get(9).grid[4] ="                  ";chunks.get(0).get(10).grid[4] ="                 |";
		
		chunks.get(1).get(0).grid[0]="|                 ";chunks.get(1).get(1).grid[0]="                  ";chunks.get(1).get(2).grid[0] ="                  ";chunks.get(1).get(3).grid[0] ="                  ";chunks.get(1).get(4).grid[0] ="                  ";chunks.get(1).get(5).grid[0] ="                  ";chunks.get(1).get(6).grid[0] ="                  ";chunks.get(1).get(7).grid[0] ="                  ";chunks.get(1).get(8).grid[0] ="                  ";chunks.get(1).get(9).grid[0] ="                  ";chunks.get(1).get(10).grid[0] ="                 |";
		chunks.get(1).get(0).grid[1]="|                 ";chunks.get(1).get(1).grid[1]="                  ";chunks.get(1).get(2).grid[1] ="                  ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                  ";chunks.get(1).get(9).grid[1] ="                  ";chunks.get(1).get(10).grid[1] ="                 |";
		chunks.get(1).get(0).grid[2]="|                 ";chunks.get(1).get(1).grid[2]="                  ";chunks.get(1).get(2).grid[2] ="                  ";chunks.get(1).get(3).grid[2] ="                  ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="                  ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="                  ";chunks.get(1).get(8).grid[2] ="                  ";chunks.get(1).get(9).grid[2] ="                  ";chunks.get(1).get(10).grid[2] ="                 |";
		chunks.get(1).get(0).grid[3]="|                 ";chunks.get(1).get(1).grid[3]="                  ";chunks.get(1).get(2).grid[3] ="                  ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="                  ";chunks.get(1).get(10).grid[3] ="                 |";
		chunks.get(1).get(0).grid[4]="|                 ";chunks.get(1).get(1).grid[4]="                  ";chunks.get(1).get(2).grid[4] ="                  ";chunks.get(1).get(3).grid[4] ="                  ";chunks.get(1).get(4).grid[4] ="                  ";chunks.get(1).get(5).grid[4] ="                  ";chunks.get(1).get(6).grid[4] ="                  ";chunks.get(1).get(7).grid[4] ="                  ";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="                  ";chunks.get(1).get(10).grid[4] ="                 |";
		
		chunks.get(2).get(0).grid[0]="|                 ";chunks.get(2).get(1).grid[0]="                  ";chunks.get(2).get(2).grid[0] ="                  ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="                 |";
		chunks.get(2).get(0).grid[1]="|                 ";chunks.get(2).get(1).grid[1]="                  ";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="                 |";
		chunks.get(2).get(0).grid[2]="|                 ";chunks.get(2).get(1).grid[2]="                  ";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="                  ";chunks.get(2).get(10).grid[2] ="                 |";
		chunks.get(2).get(0).grid[3]="|                 ";chunks.get(2).get(1).grid[3]="                  ";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="                  ";chunks.get(2).get(10).grid[3] ="                 |";
		chunks.get(2).get(0).grid[4]="|                 ";chunks.get(2).get(1).grid[4]="                  ";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="                  ";chunks.get(2).get(10).grid[4] ="                 |";
		
		chunks.get(3).get(0).grid[0]="|                 ";chunks.get(3).get(1).grid[0]="                  ";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="                  ";chunks.get(3).get(10).grid[0] ="                 |";
		chunks.get(3).get(0).grid[1]="|                 ";chunks.get(3).get(1).grid[1]="                  ";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="                  ";chunks.get(3).get(10).grid[1] ="                 |";
		chunks.get(3).get(0).grid[2]="|                 ";chunks.get(3).get(1).grid[2]="                  ";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="                  ";chunks.get(3).get(10).grid[2] ="                 |";
		chunks.get(3).get(0).grid[3]="|                 ";chunks.get(3).get(1).grid[3]="                  ";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="                  ";chunks.get(3).get(10).grid[3] ="                 |";
		chunks.get(3).get(0).grid[4]="|                 ";chunks.get(3).get(1).grid[4]="                  ";chunks.get(3).get(2).grid[4] ="                  ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="                  ";chunks.get(3).get(10).grid[4] ="                 |";
		
		chunks.get(4).get(0).grid[0]="|                 ";chunks.get(4).get(1).grid[0]="                  ";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="                  ";chunks.get(4).get(10).grid[0] ="                 |";
		chunks.get(4).get(0).grid[1]="|                 ";chunks.get(4).get(1).grid[1]="                  ";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="                  ";chunks.get(4).get(10).grid[1] ="                 |";
		chunks.get(4).get(0).grid[2]="|_________________";chunks.get(4).get(1).grid[2]="__________________";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="                  ";chunks.get(4).get(10).grid[2] ="                 |";
		chunks.get(4).get(0).grid[3]="                  ";chunks.get(4).get(1).grid[3]="                  ";chunks.get(4).get(2).grid[3] ="                  ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="                  ";chunks.get(4).get(10).grid[3] ="                 |";
		chunks.get(4).get(0).grid[4]="__________________";chunks.get(4).get(1).grid[4]="__________________";chunks.get(4).get(2).grid[4] ="__________________";chunks.get(4).get(3).grid[4] ="__________________";chunks.get(4).get(4).grid[4] ="__________________";chunks.get(4).get(5).grid[4] ="__________________";chunks.get(4).get(6).grid[4] ="__________________";chunks.get(4).get(7).grid[4] ="__________________";chunks.get(4).get(8).grid[4] ="__________________";chunks.get(4).get(9).grid[4] ="__________________";chunks.get(4).get(10).grid[4] ="_________________|";
		
		chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="                  ";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="|                 ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="                  ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="|                 ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="    ==============";chunks.get(5).get(8).grid[1] ="==================";chunks.get(5).get(9).grid[1] ="==============    ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="   F  I  G  H  T  ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="      B  A  G     ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="|                 ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="  //              ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="              \\\\  ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="|                 ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] =" ||               ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="               || ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="|                 ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] =" ||               ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="               || ";chunks.get(5).get(10).grid[4] ="                  ";
		
		chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="|                 ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] =" ||               ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="               || ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="|                 ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] =" ||               ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="               || ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="   P O K E M O N  ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="      R  U  N     ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="|                 ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] =" ||               ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="               || ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="|                 ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="  \\\\              ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="              //  ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="|                 ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="    ==============";chunks.get(6).get(8).grid[4] ="==================";chunks.get(6).get(9).grid[4] ="==============    ";chunks.get(6).get(10).grid[4] ="                  ";
		
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="                  ";chunks.get(7).get(5).grid[0] ="|                 ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="|                 ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="|                 ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="|                 ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="|                 ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
		
		
		chunks.get(0).get(2).grid[4] = "------------------";chunks.get(0).get(3).grid[4] = "------------------";
		chunks.get(0).get(7).grid[4] = "------------------";chunks.get(0).get(8).grid[4] = "------------------";
		printInformation(curr, enemy, chunks);
	}
	public void printInformation(Poke curr, Poke enemy, ArrayList<ArrayList<chunk>> chunks) {
		for(int i = 0; i < 5; i++) {
			chunks.get(1).get(1).grid[i] = "                 I";chunks.get(1).get(6).grid[i] = "                 I";
			chunks.get(2).get(1).grid[i] = "                 I";chunks.get(2).get(6).grid[i] = "                 I";
			chunks.get(1).get(4).grid[i] = "I                 ";chunks.get(1).get(9).grid[i] = "I                 ";
			chunks.get(2).get(4).grid[i] = "I                 ";chunks.get(2).get(9).grid[i] = "I                 ";
		}
		
		chunks.get(3).get(2).grid[0] = "------------------";chunks.get(3).get(3).grid[0] = "------------------";
		chunks.get(3).get(7).grid[0] = "------------------";chunks.get(3).get(8).grid[0] = "------------------";
		int row = 1; int col = 2;
		for(int i = 0; i < 10; i++) {
			try {
				if(i < 5) {
					StringBuilder sb1 = new StringBuilder(curr.model[i].substring(0,18));
					StringBuilder sb2 = new StringBuilder(curr.model[i].substring(18,36));
					chunks.get(row).get(col).grid[i] = sb1.toString();
					chunks.get(row).get(col+1).grid[i] = sb2.toString();
					StringBuilder sb3 = new StringBuilder(enemy.model[i].substring(0,18));
					StringBuilder sb4 = new StringBuilder(enemy.model[i].substring(18,36));
					chunks.get(row).get(col+5).grid[i] = sb3.toString();
					chunks.get(row).get(col+6).grid[i] = sb4.toString();
				} else {
					StringBuilder sb1 = new StringBuilder(curr.model[i].substring(0,18));
					StringBuilder sb2 = new StringBuilder(curr.model[i].substring(18,36));
					chunks.get(row+1).get(col).grid[i-5] = sb1.toString();
					chunks.get(row+1).get(col+1).grid[i-5] = sb2.toString();
					StringBuilder sb3 = new StringBuilder(enemy.model[i].substring(0,18));
					StringBuilder sb4 = new StringBuilder(enemy.model[i].substring(18,36));
					chunks.get(row+1).get(col+5).grid[i-5] = sb3.toString();
					chunks.get(row+1).get(col+6).grid[i-5] = sb4.toString();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		chunks.get(0).get(2).grid[2] = curr.name;
		chunks.get(0).get(7).grid[2] = enemy.name;
		chunks.get(0).get(3).grid[2] = "Lvl: " + curr.level;
		chunks.get(0).get(8).grid[2] = "  Lvl: " + enemy.level;
		
		chunks.get(0).get(2).grid[3] = "  " + curr.type[0] + "  " + curr.type[1];
		chunks.get(0).get(7).grid[3] = "  " + enemy.type[0] + "  " + enemy.type[1];
		chunks.get(3).get(3).grid[4] = "__________________";chunks.get(3).get(8).grid[4] = "__________________";
		chunks.get(4).get(3).grid[0] = "Imp. " + curr.impact;
		chunks.get(4).get(3).grid[1] = "De.  " + curr.debuff;
		chunks.get(4).get(3).grid[2] = "St.  " + curr.status;
		
		chunks.get(4).get(2).grid[0] = "                 /";chunks.get(4).get(7).grid[0] = "                 /";
		chunks.get(4).get(2).grid[1] = "                | ";chunks.get(4).get(7).grid[1] = "                | ";
		chunks.get(4).get(2).grid[2] = "________________| ";chunks.get(4).get(7).grid[2] = "________________| ";
		
		chunks.get(4).get(8).grid[0] = "Imp. " + enemy.impact;
		chunks.get(4).get(8).grid[1] = "De.  " + enemy.debuff;
		chunks.get(4).get(8).grid[2] = "St.  " + enemy.status;
		
		
 		
		chunks.get(3).get(2).grid[1] = "__________________";
		chunks.get(3).get(1).grid[2] = "                 |";chunks.get(3).get(3).grid[2] = ")                 ";
		chunks.get(3).get(1).grid[3] = "                 |";chunks.get(3).get(2).grid[3] = "_________________/";
		int filled = (curr.temphp * 18)/curr.hp;
		chunks.get(3).get(2).grid[2] = "";
		for(int i = 0; i < 18; i++) {
			if(i <= filled) chunks.get(3).get(2).grid[2] += "/"; 
			if(i > filled) chunks.get(3).get(2).grid[2] += " "; 
		}
		chunks.get(3).get(3).grid[2] = "  HP: " + curr.temphp +"/" + curr.hp;
		
		chunks.get(3).get(7).grid[1] = "__________________";
		chunks.get(3).get(6).grid[2] = "                 |";chunks.get(3).get(8).grid[2] = ")                 ";
		chunks.get(3).get(6).grid[3] = "                 |";chunks.get(3).get(7).grid[3] = "_________________/";
		
		
		 filled = (enemy.temphp * 18)/enemy.hp;
		chunks.get(3).get(7).grid[2] = "";
		for(int i = 0; i < 18; i++) {
			if(i <= filled) chunks.get(3).get(7).grid[2] += "/"; 
			if(i > filled) chunks.get(3).get(7).grid[2] += " "; 
		}
		chunks.get(3).get(8).grid[2] = "  HP: " + enemy.temphp +"/" + enemy.hp;
		
		for(int i = 0; i < 18; i++) {
			chunks.get(0).get(2).increaseSpace(i, 2);
			chunks.get(0).get(3).increaseSpace(i, 2);
			chunks.get(0).get(7).increaseSpace(i, 2);
			chunks.get(0).get(8).increaseSpace(i, 2);
			chunks.get(0).get(2).increaseSpace(i, 3);
			chunks.get(0).get(7).increaseSpace(i, 3);
			chunks.get(3).get(3).increaseSpace(i, 2);
			chunks.get(3).get(8).increaseSpace(i, 2);
			chunks.get(4).get(3).increaseSpace(i, 0);
			chunks.get(4).get(3).increaseSpace(i, 1);
			chunks.get(4).get(3).increaseSpace(i, 2);
			chunks.get(4).get(8).increaseSpace(i, 0);
			chunks.get(4).get(8).increaseSpace(i, 1);
			chunks.get(4).get(8).increaseSpace(i, 2);
		}
		
	}
	
		
	public static void printMethod(ArrayList<ArrayList<chunk>> chunks) {
		game.spaces();
		System.out.println("_".repeat(200));
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 5; i++) {
				System.out.print("|");
				
				for(int col = 0; col < 11; col++) {
					System.out.print(chunks.get(j).get(col).grid[i]);
				}
			if(i != 7) {
				System.out.println("|");
			}
			}
		}
	}
	public static void mapDialogue(ArrayList<ArrayList<chunk>> chunks,String sentence) {
		// 3, 4, 5, 6, 7, 8
		chunks.get(5).get(2).grid[0] = " ~~~~~~~~~~~~~~~~~";
		chunks.get(5).get(2).grid[1] = "|                 ";
		chunks.get(5).get(2).grid[2] = "|                 ";
		chunks.get(5).get(2).grid[3] = "|                 ";
		chunks.get(5).get(2).grid[4] = "|                 ";
		chunks.get(6).get(2).grid[0] = "|                 ";
		chunks.get(6).get(2).grid[1] = "|                 ";
		chunks.get(6).get(2).grid[2] = "|                 ";
		chunks.get(6).get(2).grid[3] = "|                 ";
		chunks.get(6).get(2).grid[4] = " ~~~~~~~~~~~~~~~~~";
		
		chunks.get(5).get(9).grid[0] = "~~~~~~~~~~~~~~~~~ ";
		chunks.get(5).get(9).grid[1] = "                 |";
		chunks.get(5).get(9).grid[2] = "                 |";
		chunks.get(5).get(9).grid[3] = "                 |";
		chunks.get(5).get(9).grid[4] = "                 |";
		chunks.get(6).get(9).grid[0] = "                 |";
		chunks.get(6).get(9).grid[1] = "                 |";
		chunks.get(6).get(9).grid[2] = "                 |";
		chunks.get(6).get(9).grid[3] = "                 |";
		chunks.get(6).get(9).grid[4] = "~~~~~~~~~~~~~~~~~ ";
		for(int i = 3; i < 9; i++) {
			chunks.get(5).get(i).grid[0] = "~~~~~~~~~~~~~~~~~~";
			chunks.get(5).get(i).grid[1] = "                  ";
			chunks.get(5).get(i).grid[2] = "                  ";
			chunks.get(5).get(i).grid[3] = "                  ";
			chunks.get(5).get(i).grid[4] = "                  ";
			chunks.get(6).get(i).grid[0] = "                  ";
			chunks.get(6).get(i).grid[1] = "                  ";
			chunks.get(6).get(i).grid[2] = "                  ";
			chunks.get(6).get(i).grid[3] = "                  ";
			chunks.get(6).get(i).grid[4] = "~~~~~~~~~~~~~~~~~~";
		}
		String fragmented = sentence;
		int end = 18;
		int chunkCol = 3; int gridRow = 2; int chunkRow = 5;
		int i = 0;
		try {
		for(i = 0; i < sentence.length(); i += 18) {
			chunks.get(chunkRow).get(chunkCol).grid[gridRow] = sentence.substring(i, end);
			end += 18;
			if(end > sentence.length()) {
				end = sentence.length();
			}
			if(chunkCol == 8) {
				gridRow += 2;
				if(gridRow == 6) {
					gridRow = 1;
					chunkRow++;
				}
				chunkCol = 3;
			} else {
				chunkCol++;
			}
			if(end == sentence.length()) break;
		}
			for(int start = i; start <  19; start++) {
				chunks.get(chunkRow).get(chunkCol).grid[gridRow].concat(" ");
			}	
		String input = "";
		Scanner scan = new Scanner(System.in);
		while(true) {
			printMethod(chunks);
			input = scan.next();
			if(input.equals("c"))break;
		}
		} catch(Exception e) {
			System.out.println("Could not print dialogue...");

		}	
	}

public void displayPokemon(Poke p, ArrayList<ArrayList<chunk>> chunks) {
	for(int i = 0; i < 10; i++) {
			if(i < 5) {
				StringBuilder sb3 = new StringBuilder(p.model[i].substring(0,18));
				StringBuilder sb4 = new StringBuilder(p.model[i].substring(18,36));
				chunks.get(2).get(4).grid[i] = sb3.toString();
				chunks.get(2).get(5).grid[i] = sb4.toString();
			} else {
				StringBuilder sb3 = new StringBuilder(p.model[i].substring(0,18));
				StringBuilder sb4 = new StringBuilder(p.model[i].substring(18,36));
				chunks.get(3).get(4).grid[i-5] = sb3.toString();
				chunks.get(3).get(5).grid[i-5] = sb4.toString();
			}
	}
	
	if(p.name.equals("Fizard") || p.name.equals("Bark-Widow")) {
		for(int i = 0; i < 5; i++) {
			for(int g = 0; g < 5; g++) {
				if(i == 4 && g == 4) {
					chunks.get(i).get(3).grid[g] = "_________________|";
					chunks.get(i).get(6).grid[g] = "|_________________";
				} else {
					chunks.get(i).get(3).grid[g] = "                 |";
					chunks.get(i).get(6).grid[g] = "|                 ";
				}
			}
		}
		chunks.get(4).get(6).grid[0] = "|              |__";chunks.get(4).get(7).grid[0] =     "__________________";chunks.get(4).get(8).grid[0] = "_|                ";
		chunks.get(1).get(6).grid[4] = "|               __";chunks.get(1).get(7).grid[4] =     "__________________";chunks.get(1).get(8).grid[4] =  "_                 ";
	} else {
		chunks.get(4).get(6).grid[0] = "               |__";chunks.get(4).get(7).grid[0] =     "__________________";chunks.get(4).get(8).grid[0] = "_|                ";
		chunks.get(4).get(4).grid[0] = "  ________________";chunks.get(4).get(5).grid[0] = "________________  ";
		chunks.get(4).get(4).grid[1] = "_                 ";chunks.get(4).get(5).grid[1] = "                 _";
		chunks.get(4).get(3).grid[2] = "                 _";chunks.get(4).get(6).grid[2] = "_                 ";
		chunks.get(4).get(3).grid[3] = "                _ ";chunks.get(4).get(6).grid[3] = " _                ";
		chunks.get(1).get(6).grid[4] = "                __";chunks.get(1).get(7).grid[4] =     "__________________";chunks.get(1).get(8).grid[4] =  "_                 ";
	}
	
	for(int i = 0; i < 5; i++) {
		if(p.name.equals("Fizard") || p.name.equals("Bark-Widow")) {
			chunks.get(2).get(6).grid[i] = "|              |: ";
			chunks.get(3).get(6).grid[i] = "|              |: ";
		} else {
			chunks.get(2).get(6).grid[i] = "               |: ";
			chunks.get(3).get(6).grid[i] = "               |: ";
		}
		chunks.get(2).get(8).grid[i] = ":|                ";
		chunks.get(3).get(8).grid[i] = ":|                ";
	}
		chunks.get(2).get(7).grid[1] = p.name;
		chunks.get(2).get(7).grid[3] = p.type[0] + " " + p.type[1];
		chunks.get(3).get(7).grid[0] = " Lvl: " + p.level;
		if(p.defense < p.spDefense) chunks.get(3).get(7).grid[2] = " Weak: Physical";
		if(p.spDefense < p.defense) chunks.get(3).get(7).grid[2] = " Weak: Special";
	
	for(int i = 0; i < 18; i++) {
		chunks.get(2).get(7).increaseSpace(i, 1);
		chunks.get(2).get(7).increaseSpace(i, 3);
		chunks.get(3).get(7).increaseSpace(i, 0);
		chunks.get(3).get(7).increaseSpace(i, 2);
	}
	
	
}


public static int mapQuestion(ArrayList<ArrayList<chunk>> chunks,String sentence) {
	// 3, 4, 5, 6, 7, 8
	chunks.get(5).get(2).grid[0] = " ~~~~~~~~~~~~~~~~~";
	chunks.get(5).get(2).grid[1] = "|                 ";
	chunks.get(5).get(2).grid[2] = "|                 ";
	chunks.get(5).get(2).grid[3] = "|                 ";
	chunks.get(5).get(2).grid[4] = "|                 ";
	chunks.get(6).get(2).grid[0] = "|                 ";
	chunks.get(6).get(2).grid[1] = "|                 ";
	chunks.get(6).get(2).grid[2] = "|                 ";
	chunks.get(6).get(2).grid[3] = "|                 ";
	chunks.get(6).get(2).grid[4] = " ~~~~~~~~~~~~~~~~~";
	
	chunks.get(5).get(9).grid[0] = "~~~~~~~~~~~~~~~~~ ";
	chunks.get(5).get(9).grid[1] = "                 |";
	chunks.get(5).get(9).grid[2] = "                 |";
	chunks.get(5).get(9).grid[3] = "                 |";
	chunks.get(5).get(9).grid[4] = "                 |";
	chunks.get(6).get(9).grid[0] = "                 |";
	chunks.get(6).get(9).grid[1] = "                 |";
	chunks.get(6).get(9).grid[2] = "                 |";
	chunks.get(6).get(9).grid[3] = "                 |";
	chunks.get(6).get(9).grid[4] = "~~~~~~~~~~~~~~~~~ ";
	for(int i = 3; i < 9; i++) {
		chunks.get(5).get(i).grid[0] = "~~~~~~~~~~~~~~~~~~";
		chunks.get(5).get(i).grid[1] = "                  ";
		chunks.get(5).get(i).grid[2] = "                  ";
		chunks.get(5).get(i).grid[3] = "                  ";
		chunks.get(5).get(i).grid[4] = "                  ";
		chunks.get(6).get(i).grid[0] = "                  ";
		chunks.get(6).get(i).grid[1] = "                  ";
		chunks.get(6).get(i).grid[2] = "                  ";
		chunks.get(6).get(i).grid[3] = "                  ";
		chunks.get(6).get(i).grid[4] = "~~~~~~~~~~~~~~~~~~";
	}
	String fragmented = sentence;
	int end = 18;
	int chunkCol = 3; int gridRow = 2; int chunkRow = 5;
	int i = 0;
	try {
	for(i = 0; i < sentence.length(); i += 18) {
		chunks.get(chunkRow).get(chunkCol).grid[gridRow] = sentence.substring(i, end);
		end += 18;
		if(end > sentence.length()) {
			end = sentence.length();
		}
		if(chunkCol == 8) {
			gridRow += 2;
			if(gridRow == 6) {
				gridRow = 1;
				chunkRow++;
			}
			chunkCol = 3;
		} else {
			chunkCol++;
		}
		if(end == sentence.length()) break;
	}
		for(int start = i; start <  19; start++) {
			chunks.get(chunkRow).get(chunkCol).grid[gridRow].concat(" ");
		}	
	String arr[] = {" ", " "};
	String input = "";
	int pos = 0;
	Scanner scan = new Scanner(System.in);
	while(true) {
		arr[pos] = ">";
		chunks.get(chunkRow).get(3).grid[3] = arr[0] + " Yes   " + arr[1] + " No      ";
		printMethod(chunks);
		input = scan.next();
		arr[pos] = " ";
		if(input.equals("c")) {
			if(pos == 0) return 1;
			if(pos == 1) return 0;
		} else if(input.equals("a") && pos == 1) {
			pos--;
		} else if(input.equals("d") && pos == 0) {
			pos++;
		}
	}
	} catch(Exception e) {
		System.out.println("Could not print dialogue...");
		return 0;
	}	
}

}




//       
/*
 
 MAIN BATTLE MENU
 
 		chunks.get(0).get(0).grid[0]="                  ";chunks.get(0).get(1).grid[0]="                  ";chunks.get(0).get(2).grid[0] ="                  ";chunks.get(0).get(3).grid[0] ="                  ";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="                  ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
		chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="                  ";chunks.get(0).get(2).grid[1] ="                  ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
		chunks.get(0).get(0).grid[2]="                  ";chunks.get(0).get(1).grid[2]="                  ";chunks.get(0).get(2).grid[2] ="                  ";chunks.get(0).get(3).grid[2] ="                  ";chunks.get(0).get(4).grid[2] ="                  ";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] ="                  ";chunks.get(0).get(7).grid[2] ="                  ";chunks.get(0).get(8).grid[2] ="                  ";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="                  ";
		chunks.get(0).get(0).grid[3]="                  ";chunks.get(0).get(1).grid[3]="                  ";chunks.get(0).get(2).grid[3] ="                  ";chunks.get(0).get(3).grid[3] ="                  ";chunks.get(0).get(4).grid[3] ="                  ";chunks.get(0).get(5).grid[3] ="                  ";chunks.get(0).get(6).grid[3] ="                  ";chunks.get(0).get(7).grid[3] ="                  ";chunks.get(0).get(8).grid[3] ="                  ";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                  ";
		chunks.get(0).get(0).grid[4]="                  ";chunks.get(0).get(1).grid[4]="                  ";chunks.get(0).get(2).grid[4] ="                  ";chunks.get(0).get(3).grid[4] ="                  ";chunks.get(0).get(4).grid[4] ="                  ";chunks.get(0).get(5).grid[4] ="                  ";chunks.get(0).get(6).grid[4] ="                  ";chunks.get(0).get(7).grid[4] ="                  ";chunks.get(0).get(8).grid[4] ="                  ";chunks.get(0).get(9).grid[4] ="                  ";chunks.get(0).get(10).grid[4] ="                  ";
		
		chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="                  ";chunks.get(1).get(2).grid[0] ="                  ";chunks.get(1).get(3).grid[0] ="                  ";chunks.get(1).get(4).grid[0] ="                  ";chunks.get(1).get(5).grid[0] ="                  ";chunks.get(1).get(6).grid[0] ="                  ";chunks.get(1).get(7).grid[0] ="                  ";chunks.get(1).get(8).grid[0] ="                  ";chunks.get(1).get(9).grid[0] ="                  ";chunks.get(1).get(10).grid[0] ="                  ";
		chunks.get(1).get(0).grid[1]="                  ";chunks.get(1).get(1).grid[1]="                  ";chunks.get(1).get(2).grid[1] ="                  ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                  ";chunks.get(1).get(9).grid[1] ="                  ";chunks.get(1).get(10).grid[1] ="                  ";
		chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="                  ";chunks.get(1).get(2).grid[2] ="                  ";chunks.get(1).get(3).grid[2] ="                  ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="                  ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="                  ";chunks.get(1).get(8).grid[2] ="                  ";chunks.get(1).get(9).grid[2] ="                  ";chunks.get(1).get(10).grid[2] ="                  ";
		chunks.get(1).get(0).grid[3]="                  ";chunks.get(1).get(1).grid[3]="                  ";chunks.get(1).get(2).grid[3] ="                  ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="                  ";chunks.get(1).get(10).grid[3] ="                  ";
		chunks.get(1).get(0).grid[4]="                  ";chunks.get(1).get(1).grid[4]="                  ";chunks.get(1).get(2).grid[4] ="                  ";chunks.get(1).get(3).grid[4] ="                  ";chunks.get(1).get(4).grid[4] ="                  ";chunks.get(1).get(5).grid[4] ="                  ";chunks.get(1).get(6).grid[4] ="                  ";chunks.get(1).get(7).grid[4] ="                  ";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="                  ";chunks.get(1).get(10).grid[4] ="                  ";
		
		chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="                  ";chunks.get(2).get(2).grid[0] ="                  ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="                  ";
		chunks.get(2).get(0).grid[1]="                  ";chunks.get(2).get(1).grid[1]="                  ";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="                  ";
		chunks.get(2).get(0).grid[2]="                  ";chunks.get(2).get(1).grid[2]="                  ";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="                  ";chunks.get(2).get(10).grid[2] ="                  ";
		chunks.get(2).get(0).grid[3]="                  ";chunks.get(2).get(1).grid[3]="                  ";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="                  ";chunks.get(2).get(10).grid[3] ="                  ";
		chunks.get(2).get(0).grid[4]="                  ";chunks.get(2).get(1).grid[4]="                  ";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="                  ";chunks.get(2).get(10).grid[4] ="                  ";
		
		chunks.get(3).get(0).grid[0]="                  ";chunks.get(3).get(1).grid[0]="                  ";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="                  ";chunks.get(3).get(10).grid[0] ="                  ";
		chunks.get(3).get(0).grid[1]="                  ";chunks.get(3).get(1).grid[1]="                  ";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="                  ";chunks.get(3).get(10).grid[1] ="                  ";
		chunks.get(3).get(0).grid[2]="                  ";chunks.get(3).get(1).grid[2]="                  ";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="                  ";chunks.get(3).get(10).grid[2] ="                  ";
		chunks.get(3).get(0).grid[3]="                  ";chunks.get(3).get(1).grid[3]="                  ";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="                  ";chunks.get(3).get(10).grid[3] ="                  ";
		chunks.get(3).get(0).grid[4]="                  ";chunks.get(3).get(1).grid[4]="                  ";chunks.get(3).get(2).grid[4] ="                  ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="                  ";chunks.get(3).get(10).grid[4] ="                  ";
		
		chunks.get(4).get(0).grid[0]="                  ";chunks.get(4).get(1).grid[0]="                  ";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="                  ";chunks.get(4).get(10).grid[0] ="                  ";
		chunks.get(4).get(0).grid[1]="                  ";chunks.get(4).get(1).grid[1]="                  ";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="                  ";chunks.get(4).get(10).grid[1] ="                  ";
		chunks.get(4).get(0).grid[2]="                  ";chunks.get(4).get(1).grid[2]="                  ";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="                  ";chunks.get(4).get(10).grid[2] ="                  ";
		chunks.get(4).get(0).grid[3]="                  ";chunks.get(4).get(1).grid[3]="                  ";chunks.get(4).get(2).grid[3] ="                  ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="                  ";chunks.get(4).get(10).grid[3] ="                  ";
		chunks.get(4).get(0).grid[4]="__________________";chunks.get(4).get(1).grid[4]="__________________";chunks.get(4).get(2).grid[4] ="__________________";chunks.get(4).get(3).grid[4] ="__________________";chunks.get(4).get(4).grid[4] ="__________________";chunks.get(4).get(5).grid[4] ="__________________";chunks.get(4).get(6).grid[4] ="__________________";chunks.get(4).get(7).grid[4] ="__________________";chunks.get(4).get(8).grid[4] ="__________________";chunks.get(4).get(9).grid[4] ="__________________";chunks.get(4).get(10).grid[4] ="__________________";
		
		chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="                  ";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="|                 ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="                  ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="|                 ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="    ==============";chunks.get(5).get(8).grid[1] ="==================";chunks.get(5).get(9).grid[1] ="==============    ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="   F  I  G  H  T  ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="      B  A  G     ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="|                 ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="  //              ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="              \\\\  ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="|                 ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] =" ||               ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="               || ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="|                 ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] =" ||               ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="               || ";chunks.get(5).get(10).grid[4] ="                  ";
		
		chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="|                 ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] =" ||               ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="               || ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="|                 ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] =" ||               ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="               || ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="   P O K E M O N  ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="      R  U  N     ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="|                 ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] =" ||               ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="               || ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="|                 ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="  \\\\              ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="              //  ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="|                 ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="    ==============";chunks.get(6).get(8).grid[4] ="==================";chunks.get(6).get(9).grid[4] ="==============    ";chunks.get(6).get(10).grid[4] ="                  ";
		
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="                  ";chunks.get(7).get(5).grid[0] ="|                 ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="|                 ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="|                 ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="|                 ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="|                 ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
		
 
 
 
 
 		BAG MENU
 
 		chunks.get(0).get(0).grid[0]="                  ";chunks.get(0).get(1).grid[0]="                  ";chunks.get(0).get(2).grid[0] ="                  ";chunks.get(0).get(3).grid[0] ="                  ";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="                  ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
		chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="                  ";chunks.get(0).get(2).grid[1] ="                  ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
		chunks.get(0).get(0).grid[2]="                  ";chunks.get(0).get(1).grid[2]="                  ";chunks.get(0).get(2).grid[2] ="==================";chunks.get(0).get(3).grid[2] ="==================";chunks.get(0).get(4).grid[2] ="                  ";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] ="                  ";chunks.get(0).get(7).grid[2] ="==================";chunks.get(0).get(8).grid[2] ="==================";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="                  ";
		chunks.get(0).get(0).grid[3]="                  ";chunks.get(0).get(1).grid[3]="                 |";chunks.get(0).get(2).grid[3] ="            ~~~~~~";chunks.get(0).get(3).grid[3] ="~~~~~~            ";chunks.get(0).get(4).grid[3] ="|                 ";chunks.get(0).get(5).grid[3] ="                  ";chunks.get(0).get(6).grid[3] ="                 |";chunks.get(0).get(7).grid[3] ="            ~~~~~~";chunks.get(0).get(8).grid[3] ="~~~~~~            ";chunks.get(0).get(9).grid[3] ="|                 ";chunks.get(0).get(10).grid[3] ="                  ";
		chunks.get(0).get(0).grid[4]="                  ";chunks.get(0).get(1).grid[4]="                 |";chunks.get(0).get(2).grid[4] ="           {  I T ";chunks.get(0).get(3).grid[4] ="E M S }           ";chunks.get(0).get(4).grid[4] ="|                 ";chunks.get(0).get(5).grid[4] ="                  ";chunks.get(0).get(6).grid[4] ="                 |";chunks.get(0).get(7).grid[4] ="           { R O S";chunks.get(0).get(8).grid[4] ="T E R }           ";chunks.get(0).get(9).grid[4] ="|                 ";chunks.get(0).get(10).grid[4] ="                  ";
		chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="                 |";chunks.get(1).get(2).grid[0] ="            ~~~~~~";chunks.get(1).get(3).grid[0] ="~~~~~~            ";chunks.get(1).get(4).grid[0] ="|                 ";chunks.get(1).get(5).grid[0] ="==================";chunks.get(1).get(6).grid[0] ="                 |";chunks.get(1).get(7).grid[0] ="            ~~~~~~";chunks.get(1).get(8).grid[0] ="~~~~~~            ";chunks.get(1).get(9).grid[0] ="|                 ";chunks.get(1).get(10).grid[0] ="                  ";
		chunks.get(1).get(0).grid[1]="                  ";chunks.get(1).get(1).grid[1]="                 |";chunks.get(1).get(2).grid[1] ="==================";chunks.get(1).get(3).grid[1] ="==================";chunks.get(1).get(4).grid[1] ="|                |";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="|                |";chunks.get(1).get(7).grid[1] ="==================";chunks.get(1).get(8).grid[1] ="                  ";chunks.get(1).get(9).grid[1] ="|                 ";chunks.get(1).get(10).grid[1] ="                  ";
		chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="                 |";chunks.get(1).get(2).grid[2] ="                  ";chunks.get(1).get(3).grid[2] ="                  ";chunks.get(1).get(4).grid[2] ="|                |";chunks.get(1).get(5).grid[2] ="  S E L E C T E D ";chunks.get(1).get(6).grid[2] ="|                |";chunks.get(1).get(7).grid[2] ="                  ";chunks.get(1).get(8).grid[2] ="==================";chunks.get(1).get(9).grid[2] ="|                 ";chunks.get(1).get(10).grid[2] ="                  ";
		chunks.get(1).get(0).grid[3]="                  ";chunks.get(1).get(1).grid[3]="                 |";chunks.get(1).get(2).grid[3] ="                  ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="|                |";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="|                |";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="|                 ";chunks.get(1).get(10).grid[3] ="                  ";
		chunks.get(1).get(0).grid[4]="                  ";chunks.get(1).get(1).grid[4]="                 |";chunks.get(1).get(2).grid[4] ="                  ";chunks.get(1).get(3).grid[4] ="                  ";chunks.get(1).get(4).grid[4] ="|                |";chunks.get(1).get(5).grid[4] ="==================";chunks.get(1).get(6).grid[4] ="|                |";chunks.get(1).get(7).grid[4] ="                  ";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="|                 ";chunks.get(1).get(10).grid[4] ="                  ";
		chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="                 |";chunks.get(2).get(2).grid[0] ="                  ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="|                |";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="|                |";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="|                 ";chunks.get(2).get(10).grid[0] ="                  ";
		chunks.get(2).get(0).grid[1]="                  ";chunks.get(2).get(1).grid[1]="                 |";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="|                |";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="|                |";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="|                 ";chunks.get(2).get(10).grid[1] ="                  ";
		chunks.get(2).get(0).grid[2]="                  ";chunks.get(2).get(1).grid[2]="                 |";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="|                |";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="|                |";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="|                 ";chunks.get(2).get(10).grid[2] ="                  ";
		chunks.get(2).get(0).grid[3]="                  ";chunks.get(2).get(1).grid[3]="                 |";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="|                |";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="|                |";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="|                 ";chunks.get(2).get(10).grid[3] ="                  ";
		chunks.get(2).get(0).grid[4]="                  ";chunks.get(2).get(1).grid[4]="                 |";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="|                |";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="|                |";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="|                 ";chunks.get(2).get(10).grid[4] ="                  ";
		chunks.get(3).get(0).grid[0]="                  ";chunks.get(3).get(1).grid[0]="                 |";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="|                 ";chunks.get(3).get(5).grid[0] ="==================";chunks.get(3).get(6).grid[0] ="                 |";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="|                 ";chunks.get(3).get(10).grid[0] ="                  ";
		chunks.get(3).get(0).grid[1]="                  ";chunks.get(3).get(1).grid[1]="                 |";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="|                 ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                 |";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="|                 ";chunks.get(3).get(10).grid[1] ="                  ";
		chunks.get(3).get(0).grid[2]="                  ";chunks.get(3).get(1).grid[2]="                 |";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="|                 ";chunks.get(3).get(5).grid[2] ="        | |       ";chunks.get(3).get(6).grid[2] ="                 |";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="|                 ";chunks.get(3).get(10).grid[2] ="                  ";
		chunks.get(3).get(0).grid[3]="                  ";chunks.get(3).get(1).grid[3]="                 |";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="|                 ";chunks.get(3).get(5).grid[3] ="       \   /      ";chunks.get(3).get(6).grid[3] ="                 |";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="|                 ";chunks.get(3).get(10).grid[3] ="                  ";
		chunks.get(3).get(0).grid[4]="                  ";chunks.get(3).get(1).grid[4]="                 |";chunks.get(3).get(2).grid[4] ="                  ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="|                 ";chunks.get(3).get(5).grid[4] ="        \ /       ";chunks.get(3).get(6).grid[4] ="                 |";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="|                 ";chunks.get(3).get(10).grid[4] ="                  ";
		chunks.get(4).get(0).grid[0]="                  ";chunks.get(4).get(1).grid[0]="                 |";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="|                 ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                 |";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="|                 ";chunks.get(4).get(10).grid[0] ="                  ";
		chunks.get(4).get(0).grid[1]="                  ";chunks.get(4).get(1).grid[1]="                 |";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="|                 ";chunks.get(4).get(5).grid[1] ="==================";chunks.get(4).get(6).grid[1] ="                 |";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="|                 ";chunks.get(4).get(10).grid[1] ="                  ";
		chunks.get(4).get(0).grid[2]="                  ";chunks.get(4).get(1).grid[2]="                 |";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="|                |";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="|                |";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="|                 ";chunks.get(4).get(10).grid[2] ="                  ";
		chunks.get(4).get(0).grid[3]="                  ";chunks.get(4).get(1).grid[3]="                 |";chunks.get(4).get(2).grid[3] ="                  ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="|                |";chunks.get(4).get(5).grid[3] ="   P O K E M O N  ";chunks.get(4).get(6).grid[3] ="|                |";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="|                 ";chunks.get(4).get(10).grid[3] ="                  ";
		chunks.get(4).get(0).grid[4]="                  ";chunks.get(4).get(1).grid[4]="                 |";chunks.get(4).get(2).grid[4] ="                  ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="|                |";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="|                |";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                  ";chunks.get(4).get(9).grid[4] ="|                 ";chunks.get(4).get(10).grid[4] ="                  ";
		chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="                 |";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="|                |";chunks.get(5).get(5).grid[0] ="==================";chunks.get(5).get(6).grid[0] ="|                |";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="|                 ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                 |";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="|                |";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="|                |";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="|                 ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="                 |";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="|                |";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="|                |";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="|                 ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                 |";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="|                |";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="|                |";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="|                 ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                 |";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="|                |";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="|                |";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="|                 ";chunks.get(5).get(10).grid[4] ="                  ";
		chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                 |";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="|                |";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="|                |";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="|                 ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                 |";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="|                 ";chunks.get(6).get(5).grid[1] ="==================";chunks.get(6).get(6).grid[1] ="                 |";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="|                 ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="                 |";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="|                 ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                 |";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="|                 ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                 |";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="|                 ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                 |";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="|                 ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                 |";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="|                 ";chunks.get(6).get(5).grid[4] ="~~~~~~~~~~~~~~~~~~";chunks.get(6).get(6).grid[4] ="                 |";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="|                 ";chunks.get(6).get(10).grid[4] ="                  ";
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                 |";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="|                |";chunks.get(7).get(5).grid[0] ="     Confirm?     ";chunks.get(7).get(6).grid[0] ="|                |";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="|                 ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                 |";chunks.get(7).get(2).grid[1] ="__________________";chunks.get(7).get(3).grid[1] ="__________________";chunks.get(7).get(4).grid[1] ="|                |";chunks.get(7).get(5).grid[1] ="   yes       no   ";chunks.get(7).get(6).grid[1] ="|                |";chunks.get(7).get(7).grid[1] =" _________________";chunks.get(7).get(8).grid[1] ="__________________";chunks.get(7).get(9).grid[1] ="|                 ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                 |";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="|                 ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="~~~~~~~~~~~~~~~~~~";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="                  ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
 
 
 		ROSTER MENU
 		
 		chunks.get(0).get(0).grid[0]="                  ";chunks.get(0).get(1).grid[0]="==================";chunks.get(0).get(2).grid[0] ="                  ";chunks.get(0).get(3).grid[0] ="                  ";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="                  ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
		chunks.get(0).get(0).grid[1]="                 |";chunks.get(0).get(1).grid[1]="                  ";chunks.get(0).get(2).grid[1] ="|                 ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
		chunks.get(0).get(0).grid[2]="                 |";chunks.get(0).get(1).grid[2]="   R O S T E R    ";chunks.get(0).get(2).grid[2] ="|                 ";chunks.get(0).get(3).grid[2] ="                  ";chunks.get(0).get(4).grid[2] ="                  ";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] ="                  ";chunks.get(0).get(7).grid[2] ="                  ";chunks.get(0).get(8).grid[2] ="                  ";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="                  ";
		chunks.get(0).get(0).grid[3]="                 |";chunks.get(0).get(1).grid[3]="                  ";chunks.get(0).get(2).grid[3] ="|                 ";chunks.get(0).get(3).grid[3] ="                  ";chunks.get(0).get(4).grid[3] ="                  ";chunks.get(0).get(5).grid[3] ="                  ";chunks.get(0).get(6).grid[3] ="                  ";chunks.get(0).get(7).grid[3] ="                  ";chunks.get(0).get(8).grid[3] ="                  ";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                  ";
		chunks.get(0).get(0).grid[4]="                 |";chunks.get(0).get(1).grid[4]="==================";chunks.get(0).get(2).grid[4] ="|                 ";chunks.get(0).get(3).grid[4] ="                  ";chunks.get(0).get(4).grid[4] ="                  ";chunks.get(0).get(5).grid[4] ="~~~~~~~~~~~~~~~~~~";chunks.get(0).get(6).grid[4] ="                  ";chunks.get(0).get(7).grid[4] ="                {.";chunks.get(0).get(8).grid[4] ="..................";chunks.get(0).get(9).grid[4] =".}                ";chunks.get(0).get(10).grid[4] ="                  ";
		chunks.get(1).get(0).grid[0]="                 |";chunks.get(1).get(1).grid[0]="                  ";chunks.get(1).get(2).grid[0] ="|                 ";chunks.get(1).get(3).grid[0] ="                  ";chunks.get(1).get(4).grid[0] ="                 /";chunks.get(1).get(5).grid[0] ="                  ";chunks.get(1).get(6).grid[0] ="\                 ";chunks.get(1).get(7).grid[0] ="                {.";chunks.get(1).get(8).grid[0] ="..................";chunks.get(1).get(9).grid[0] =".}                ";chunks.get(1).get(10).grid[0] ="                  ";
		chunks.get(1).get(0).grid[1]="                 |";chunks.get(1).get(1).grid[1]="                  ";chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                 /";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="\                 ";chunks.get(1).get(7).grid[1] ="                 |";chunks.get(1).get(8).grid[1] =                   ";chunks.get(1).get(9).grid[1] ="|                 ";chunks.get(1).get(10).grid[1] ="                  ";
		chunks.get(1).get(0).grid[2]="                 |";chunks.get(1).get(1).grid[2]="                  ";chunks.get(1).get(2).grid[2] ="|                 ";chunks.get(1).get(3).grid[2] ="                  ";chunks.get(1).get(4).grid[2] ="                 |";chunks.get(1).get(5).grid[2] ="                  ";chunks.get(1).get(6).grid[2] ="|                 ";chunks.get(1).get(7).grid[2] ="                 |";chunks.get(1).get(8).grid[2] ="  S  T  A  T  S   ";chunks.get(1).get(9).grid[2] ="|                 ";chunks.get(1).get(10).grid[2] ="                  ";
		chunks.get(1).get(0).grid[3]="                 |";chunks.get(1).get(1).grid[3]="                  ";chunks.get(1).get(2).grid[3] ="|                 ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                 \";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="/                 ";chunks.get(1).get(7).grid[3] ="                 |";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="|                 ";chunks.get(1).get(10).grid[3] ="                  ";
		chunks.get(1).get(0).grid[4]="                 |";chunks.get(1).get(1).grid[4]="------------------";chunks.get(1).get(2).grid[4] ="|                 ";chunks.get(1).get(3).grid[4] ="                  ";chunks.get(1).get(4).grid[4] ="                 \";chunks.get(1).get(5).grid[4] ="                  ";chunks.get(1).get(6).grid[4] ="/                 ";chunks.get(1).get(7).grid[4] ="                 |";chunks.get(1).get(8).grid[4] ="..................";chunks.get(1).get(9).grid[4] ="|                 ";chunks.get(1).get(10).grid[4] ="                  ";
		chunks.get(2).get(0).grid[0]="                 |";chunks.get(2).get(1).grid[0]="                  ";chunks.get(2).get(2).grid[0] ="|                 ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="~~~~~~~~~~~~~~~~~~";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                 |";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="|                 ";chunks.get(2).get(10).grid[0] ="                  ";
		chunks.get(2).get(0).grid[1]="                 |";chunks.get(2).get(1).grid[1]="                  ";chunks.get(2).get(2).grid[1] ="|                 ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                 |";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="|                 ";chunks.get(2).get(10).grid[1] ="                  ";
		chunks.get(2).get(0).grid[2]="                 |";chunks.get(2).get(1).grid[2]="                  ";chunks.get(2).get(2).grid[2] ="|                 ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                 |";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="|                 ";chunks.get(2).get(10).grid[2] ="                  ";
		chunks.get(2).get(0).grid[3]="                 |";chunks.get(2).get(1).grid[3]="                  ";chunks.get(2).get(2).grid[3] ="|                 ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                 |";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="|                 ";chunks.get(2).get(10).grid[3] ="                  ";
		chunks.get(2).get(0).grid[4]="                 |";chunks.get(2).get(1).grid[4]="------------------";chunks.get(2).get(2).grid[4] ="|                 ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                 |";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="|                 ";chunks.get(2).get(10).grid[4] ="                  ";
		chunks.get(3).get(0).grid[0]="                 |";chunks.get(3).get(1).grid[0]="                  ";chunks.get(3).get(2).grid[0] ="|                 ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                 |";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="|                 ";chunks.get(3).get(10).grid[0] ="                  ";
		chunks.get(3).get(0).grid[1]="                 |";chunks.get(3).get(1).grid[1]="                  ";chunks.get(3).get(2).grid[1] ="|                 ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                 |";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="|                 ";chunks.get(3).get(10).grid[1] ="                  ";
		chunks.get(3).get(0).grid[2]="                 |";chunks.get(3).get(1).grid[2]="                  ";chunks.get(3).get(2).grid[2] ="|                 ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                 |";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="|                 ";chunks.get(3).get(10).grid[2] ="                  ";
		chunks.get(3).get(0).grid[3]="                 |";chunks.get(3).get(1).grid[3]="                  ";chunks.get(3).get(2).grid[3] ="|                 ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                 |";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="|                 ";chunks.get(3).get(10).grid[3] ="                  ";
		chunks.get(3).get(0).grid[4]="                 |";chunks.get(3).get(1).grid[4]="------------------";chunks.get(3).get(2).grid[4] ="|                 ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="[][][][][][][][][]";chunks.get(3).get(5).grid[4] ="[][][][][][][][][]";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                 |";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="|                 ";chunks.get(3).get(10).grid[4] ="                  ";
		chunks.get(4).get(0).grid[0]="                 |";chunks.get(4).get(1).grid[0]="                  ";chunks.get(4).get(2).grid[0] ="|                 ";chunks.get(4).get(3).grid[0] ="                 |";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="|                 ";chunks.get(4).get(7).grid[0] ="                 |";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="|                 ";chunks.get(4).get(10).grid[0] ="                  ";
		chunks.get(4).get(0).grid[1]="                 |";chunks.get(4).get(1).grid[1]="                  ";chunks.get(4).get(2).grid[1] ="|                 ";chunks.get(4).get(3).grid[1] ="                 |";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="|                 ";chunks.get(4).get(7).grid[1] ="                 |";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="|                 ";chunks.get(4).get(10).grid[1] ="                  ";
		chunks.get(4).get(0).grid[2]="                 |";chunks.get(4).get(1).grid[2]="                  ";chunks.get(4).get(2).grid[2] ="|                 ";chunks.get(4).get(3).grid[2] ="                 |";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="|                 ";chunks.get(4).get(7).grid[2] ="                 |";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="|                 ";chunks.get(4).get(10).grid[2] ="                  ";
		chunks.get(4).get(0).grid[3]="                 |";chunks.get(4).get(1).grid[3]="                  ";chunks.get(4).get(2).grid[3] ="|                 ";chunks.get(4).get(3).grid[3] ="                 |";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="|                 ";chunks.get(4).get(7).grid[3] ="                 |";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="|                 ";chunks.get(4).get(10).grid[3] ="                  ";
		chunks.get(4).get(0).grid[4]="                 |";chunks.get(4).get(1).grid[4]="------------------";chunks.get(4).get(2).grid[4] ="|                 ";chunks.get(4).get(3).grid[4] ="                 |";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="|                 ";chunks.get(4).get(7).grid[4] ="                 |";chunks.get(4).get(8).grid[4] ="                  ";chunks.get(4).get(9).grid[4] ="|                 ";chunks.get(4).get(10).grid[4] ="                  ";
		chunks.get(5).get(0).grid[0]="                 |";chunks.get(5).get(1).grid[0]="                  ";chunks.get(5).get(2).grid[0] ="|                 ";chunks.get(5).get(3).grid[0] ="                 |";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="|                 ";chunks.get(5).get(7).grid[0] ="                 |";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="|                 ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                 |";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="|                 ";chunks.get(5).get(3).grid[1] ="                 |";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="|                 ";chunks.get(5).get(7).grid[1] ="                 |";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="|                 ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                 |";chunks.get(5).get(1).grid[2]="                  ";chunks.get(5).get(2).grid[2] ="|                 ";chunks.get(5).get(3).grid[2] ="                 |";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="|                 ";chunks.get(5).get(7).grid[2] ="                 |";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="|                 ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                 |";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="|                 ";chunks.get(5).get(3).grid[3] ="                 |";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="|                 ";chunks.get(5).get(7).grid[3] ="                 |";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="|                 ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                 |";chunks.get(5).get(1).grid[4]="------------------";chunks.get(5).get(2).grid[4] ="|                 ";chunks.get(5).get(3).grid[4] ="                 |";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="|                 ";chunks.get(5).get(7).grid[4] ="                 |";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="|                 ";chunks.get(5).get(10).grid[4] ="                  ";
		chunks.get(6).get(0).grid[0]="                 |";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="|                 ";chunks.get(6).get(3).grid[0] ="                 |";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="|                 ";chunks.get(6).get(7).grid[0] ="                {.";chunks.get(6).get(8).grid[0] ="..................";chunks.get(6).get(9).grid[0] =".}                ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                 |";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="|                 ";chunks.get(6).get(3).grid[1] ="                 |";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="|                 ";chunks.get(6).get(7).grid[1] ="                {.";chunks.get(6).get(8).grid[1] ="..................";chunks.get(6).get(9).grid[1] =".}                ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                 |";chunks.get(6).get(1).grid[2]="                  ";chunks.get(6).get(2).grid[2] ="|                 ";chunks.get(6).get(3).grid[2] ="                 |";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="|                 ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="                  ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                 |";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="|                 ";chunks.get(6).get(3).grid[3] ="                 |";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="|                 ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="                  ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="==================";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                 |";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="                  ";chunks.get(6).get(6).grid[4] ="|                 ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="                  ";chunks.get(6).get(10).grid[4] ="                  ";
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="[][][][][][][][][]";chunks.get(7).get(5).grid[0] ="[][][][][][][][][]";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="                  ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="                  ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="                  ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
		

 
 
 
 
        DEFAULT MENU
       
        chunks.get(0).get(0).grid[0]="                  ";chunks.get(0).get(1).grid[0]="                  ";chunks.get(0).get(2).grid[0] ="                  ";chunks.get(0).get(3).grid[0] ="                  ";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="                  ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
		chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="                  ";chunks.get(0).get(2).grid[1] ="                  ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
		chunks.get(0).get(0).grid[2]="                  ";chunks.get(0).get(1).grid[2]="                  ";chunks.get(0).get(2).grid[2] ="                  ";chunks.get(0).get(3).grid[2] ="                  ";chunks.get(0).get(4).grid[2] ="                  ";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] ="                  ";chunks.get(0).get(7).grid[2] ="                  ";chunks.get(0).get(8).grid[2] ="                  ";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="                  ";
		chunks.get(0).get(0).grid[3]="                  ";chunks.get(0).get(1).grid[3]="                  ";chunks.get(0).get(2).grid[3] ="                  ";chunks.get(0).get(3).grid[3] ="                  ";chunks.get(0).get(4).grid[3] ="                  ";chunks.get(0).get(5).grid[3] ="                  ";chunks.get(0).get(6).grid[3] ="                  ";chunks.get(0).get(7).grid[3] ="                  ";chunks.get(0).get(8).grid[3] ="                  ";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                  ";
		chunks.get(0).get(0).grid[4]="                  ";chunks.get(0).get(1).grid[4]="                  ";chunks.get(0).get(2).grid[4] ="                  ";chunks.get(0).get(3).grid[4] ="                  ";chunks.get(0).get(4).grid[4] ="                  ";chunks.get(0).get(5).grid[4] ="                  ";chunks.get(0).get(6).grid[4] ="                  ";chunks.get(0).get(7).grid[4] ="                  ";chunks.get(0).get(8).grid[4] ="                  ";chunks.get(0).get(9).grid[4] ="                  ";chunks.get(0).get(10).grid[4] ="                  ";
		
		chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="                  ";chunks.get(1).get(2).grid[0] ="                  ";chunks.get(1).get(3).grid[0] ="                  ";chunks.get(1).get(4).grid[0] ="                  ";chunks.get(1).get(5).grid[0] ="                  ";chunks.get(1).get(6).grid[0] ="                  ";chunks.get(1).get(7).grid[0] ="                  ";chunks.get(1).get(8).grid[0] ="                  ";chunks.get(1).get(9).grid[0] ="                  ";chunks.get(1).get(10).grid[0] ="                  ";
		chunks.get(1).get(0).grid[1]="                  ";chunks.get(1).get(1).grid[1]="                  ";chunks.get(1).get(2).grid[1] ="                  ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                  ";chunks.get(1).get(9).grid[1] ="                  ";chunks.get(1).get(10).grid[1] ="                  ";
		chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="                  ";chunks.get(1).get(2).grid[2] ="                  ";chunks.get(1).get(3).grid[2] ="                  ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="                  ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="                  ";chunks.get(1).get(8).grid[2] ="                  ";chunks.get(1).get(9).grid[2] ="                  ";chunks.get(1).get(10).grid[2] ="                  ";
		chunks.get(1).get(0).grid[3]="                  ";chunks.get(1).get(1).grid[3]="                  ";chunks.get(1).get(2).grid[3] ="                  ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="                  ";chunks.get(1).get(10).grid[3] ="                  ";
		chunks.get(1).get(0).grid[4]="                  ";chunks.get(1).get(1).grid[4]="                  ";chunks.get(1).get(2).grid[4] ="                  ";chunks.get(1).get(3).grid[4] ="                  ";chunks.get(1).get(4).grid[4] ="                  ";chunks.get(1).get(5).grid[4] ="                  ";chunks.get(1).get(6).grid[4] ="                  ";chunks.get(1).get(7).grid[4] ="                  ";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="                  ";chunks.get(1).get(10).grid[4] ="                  ";
		
		chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="                  ";chunks.get(2).get(2).grid[0] ="                  ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="                  ";
		chunks.get(2).get(0).grid[1]="                  ";chunks.get(2).get(1).grid[1]="                  ";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="                  ";
		chunks.get(2).get(0).grid[2]="                  ";chunks.get(2).get(1).grid[2]="                  ";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="                  ";chunks.get(2).get(10).grid[2] ="                  ";
		chunks.get(2).get(0).grid[3]="                  ";chunks.get(2).get(1).grid[3]="                  ";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="                  ";chunks.get(2).get(10).grid[3] ="                  ";
		chunks.get(2).get(0).grid[4]="                  ";chunks.get(2).get(1).grid[4]="                  ";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="                  ";chunks.get(2).get(10).grid[4] ="                  ";
		
		chunks.get(3).get(0).grid[0]="                  ";chunks.get(3).get(1).grid[0]="                  ";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="                  ";chunks.get(3).get(10).grid[0] ="                  ";
		chunks.get(3).get(0).grid[1]="                  ";chunks.get(3).get(1).grid[1]="                  ";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="                  ";chunks.get(3).get(10).grid[1] ="                  ";
		chunks.get(3).get(0).grid[2]="                  ";chunks.get(3).get(1).grid[2]="                  ";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="                  ";chunks.get(3).get(10).grid[2] ="                  ";
		chunks.get(3).get(0).grid[3]="                  ";chunks.get(3).get(1).grid[3]="                  ";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="                  ";chunks.get(3).get(10).grid[3] ="                  ";
		chunks.get(3).get(0).grid[4]="                  ";chunks.get(3).get(1).grid[4]="                  ";chunks.get(3).get(2).grid[4] ="                  ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="                  ";chunks.get(3).get(10).grid[4] ="                  ";
		
		chunks.get(4).get(0).grid[0]="                  ";chunks.get(4).get(1).grid[0]="                  ";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="                  ";chunks.get(4).get(10).grid[0] ="                  ";
		chunks.get(4).get(0).grid[1]="                  ";chunks.get(4).get(1).grid[1]="                  ";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="                  ";chunks.get(4).get(10).grid[1] ="                  ";
		chunks.get(4).get(0).grid[2]="                  ";chunks.get(4).get(1).grid[2]="                  ";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="                  ";chunks.get(4).get(10).grid[2] ="                  ";
		chunks.get(4).get(0).grid[3]="                  ";chunks.get(4).get(1).grid[3]="                  ";chunks.get(4).get(2).grid[3] ="                  ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="                  ";chunks.get(4).get(10).grid[3] ="                  ";
		chunks.get(4).get(0).grid[4]="                  ";chunks.get(4).get(1).grid[4]="                  ";chunks.get(4).get(2).grid[4] ="                  ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                  ";chunks.get(4).get(9).grid[4] ="                  ";chunks.get(4).get(10).grid[4] ="                  ";
		
		chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="                  ";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="|                 ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="                  ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="|                 ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="                  ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="                  ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="|                 ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="                  ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="|                 ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="                  ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="|                 ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="                  ";chunks.get(5).get(10).grid[4] ="                  ";
		
		chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="|                 ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="                  ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="|                 ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="                  ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="                  ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="|                 ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="                  ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="|                 ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="                  ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="|                 ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="                  ";chunks.get(6).get(10).grid[4] ="                  ";
		
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="                  ";chunks.get(7).get(5).grid[0] ="|                 ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="|                 ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="|                 ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="|                 ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="|                 ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
		
 */












