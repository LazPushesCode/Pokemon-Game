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
		while(true) {
			userchoice = choosePokemon(user, wildpoke, chunks, map);
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
				userchoice = choosePokemon(user,wildpoke,chunks, map);
			}
			
			result = simulateBattle(user,wildpoke,chunks,userchoice, 1, map);
			if(result == 3 || result == 4) {
				Player.encountercooldown = 3;
				return;
			} else if(result == 0) {
				//player won
				Player.encountercooldown = 5;
				user.rosterlist.get(userchoice).xp += 20;
				mapDialogue(chunks, "You've successfully beaten the wild pokemon!                                           ");
			} else if(result == 1) {
				//pokemon won
				user.numDead++;
			}
		}
		
	}
	
	
	public int choosePokemon(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, Map map) {
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
		
		chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="                  ";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="                  ";chunks.get(5).get(10).grid[0] ="                  ";
		chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="                  ";chunks.get(5).get(10).grid[1] ="                  ";
		chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="                  ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="                  ";chunks.get(5).get(10).grid[2] ="                  ";
		chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="                  ";chunks.get(5).get(10).grid[3] ="                  ";
		chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="                  ";chunks.get(5).get(10).grid[4] ="                  ";
		
		chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="                  ";chunks.get(6).get(10).grid[0] ="                  ";
		chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="                  ";chunks.get(6).get(10).grid[1] ="                  ";
		chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="                  ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="                  ";chunks.get(6).get(10).grid[2] ="                  ";
		chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="                  ";chunks.get(6).get(10).grid[3] ="                  ";
		chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="                  ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="                  ";chunks.get(6).get(10).grid[4] ="                  ";
		
		chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="                  ";chunks.get(7).get(5).grid[0] ="                  ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
		chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="                  ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
		chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
		chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="                  ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
		chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="                  ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
		
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
		int choicerow = 5; int choicecol = 2;
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(true) {
			game.spaces();
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
				System.out.println(((choicerow-5)*3) + ((choicecol-2)/3));
				return ((choicerow-5)*3) + ((choicecol-2)/3);
				
			} else if(input.equals("o")) {
				break;
			}
			
		}
		return 0;	
	}
	
	public int simulateBattle(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, int userchoice, int wildflag, Map map) {
		AI ai = new AI(enemy, user.rosterlist.get(userchoice));
		ai.memorize(enemy, user.rosterlist.get(userchoice));
		System.out.println("Impact: " + ai.impact + " Debuff: " + ai.debuff + " physical: " + ai.physical + " special: " + ai.special + " Status: " + ai.status);
		if(enemy.speed > user.rosterlist.get(userchoice).speed) {
			//enemy attacks first!
			displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
			int key = initiateTurn(user, enemy, userchoice, ai, map, 1);
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
			checkTimers(enemy, user.rosterlist.get(userchoice), chunks); //decrement timers for the enemy pokemon, then decrement for ai 
			if(ai.impacttimer != 0)ai.impacttimer--;
			if(ai.statustimer != 0)ai.statustimer--;
			if(ai.debufftimer != 0)ai.debufftimer--;
			if(!(user.rosterlist.get(userchoice).impact.equals("")))inflictImpact(user.rosterlist.get(userchoice), enemy, chunks);
			if(!(user.rosterlist.get(userchoice).debuff.equals("")))inflictDebuff(user.rosterlist.get(userchoice), enemy, chunks);
			displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, chunks);
			termkey = 0;
			while(termkey == 0) {
				game.spaces();
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
								int key = initiateTurn(user, enemy, userchoice, ai, map, 0); //user attacks
								if(user.rosterlist.get(userchoice).impact.equals("Extra Turn")) {
									key = initiateTurn(user,enemy,userchoice,ai,map,0);
									checkTimers(user.rosterlist.get(userchoice), enemy, chunks);
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
			checkTimers(user.rosterlist.get(userchoice), enemy, chunks); //decrement timers for the user pokemon
			if(enemy.impact.equals("Paralyze")) {
				mapDialogue(chunks, enemy.name + "is paralyzed!                                                                                                      > continue                                    ");
			} else {
				int key = initiateTurn(user, enemy, userchoice, ai, map, 1); //ai attacks
				if(enemy.impact.equals("Extra Turn")) {
					key = initiateTurn(user,enemy,userchoice,ai,map,1);
					checkTimers(enemy, user.rosterlist.get(userchoice), chunks);
				}
			}
			if(!(user.rosterlist.get(userchoice).status.equals(""))) {
				inflictStatus(user.rosterlist.get(userchoice), chunks);
			}
			if(!(enemy.impact.equals(""))) {
				inflictImpact(enemy, user.rosterlist.get(userchoice), chunks);
			}
			if(!(enemy.debuff.equals(""))) {
				if(!enemy.debuff.equals("Photosynthesis")) {
					inflictDebuff(enemy, user.rosterlist.get(userchoice), chunks);
				} else {
					Random ran = new Random();
					Photosynthesis(enemy, user.rosterlist.get(userchoice),chunks, ran);
				}
			}
			if(!(enemy.status.equals(""))) {
				inflictStatus(enemy, chunks);
			}
		}
	}
	
	public int initiateTurn(Player user, Poke enemy, int userchoice, AI ai, Map map, int key) {
		int moveChosen;
		Poke attacker; Poke defender;
		if(key == 0) {
			attacker = user.rosterlist.get(userchoice); defender = enemy;
			moveChosen = displayFightMenu(user, defender, map.chunks, userchoice);
		} else {
			attacker = enemy; defender = user.rosterlist.get(userchoice);
			moveChosen = ai.decideMove(attacker, user.rosterlist.get(userchoice), map);
			System.out.println(ai.impacttimer);
		}	
		if(moveChosen != 5) {
			mapDialogue(map.chunks, attacker.name + " used " + attacker.movesets.get(moveChosen).name + "!                                                           > continue                                    ");
			if(!(attacker.movesets.get(moveChosen).attackType.equals("Status"))){
				if(!(defender.status.equals("Evade"))){
						double dmgcalculated = calculateDamage(attacker, defender, moveChosen, map);
						int dmg = (int)dmgcalculated;
						//if the speed formula impacts the game, just change it and test please   -past Laz
						double Accuracy = calculateAccuracy(attacker, defender, moveChosen);
						Random ran = new Random();
						if(Accuracy >= ran.nextInt(0, 100)) {
							inflictDamage(attacker, defender, dmg, map.chunks, user.rosterlist.get(userchoice), enemy);
							if(!(attacker.movesets.get(moveChosen).effect.equals(""))) {
								assignDebuff(defender, attacker, attacker.movesets.get(moveChosen), map.chunks);
							}	
					} else {
						mapDialogue(map.chunks, attacker.name +"'s attack missed!                                                                                                                > continue                                    ");
					}
				} else {
					mapDialogue(map.chunks, defender.name + "evaded the attack!                                                                                              > continue                                    ");
				}
			} else {
				//is a status
				assignDebuff(attacker, defender, attacker.movesets.get(moveChosen), map.chunks);
				attacker.movesets.get(moveChosen).pp--;
				displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, map.chunks);
			}
			return 1;
		}
		displayMainBattleMenu(user.rosterlist.get(userchoice), enemy, map.chunks);
		return 0;
	}
	
	public void checkTimers(Poke p, Poke o, ArrayList<ArrayList<chunk>> chunks) {
		if(p.impacttimer != 0)p.impacttimer--;
		if(p.impacttimer == 0) {
			inflictImpact(p,o, chunks);
			p.impact = "";
		}
		if(p.statustimer != 0)p.statustimer--;
		if(p.statustimer == 0) {
			inflictStatus(p, chunks);
			p.status = "";
		}
		if(p.debufftimer != 0)p.debufftimer--;
		if(p.debufftimer == 0) {
			if(p.debuff.equals("Photosynthesis")) {
				
			} else {
				inflictDebuff(p,o, chunks);
			}
			p.debuff = "";
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
	public void inflictDamage(Poke attacker, Poke defender, int damage, ArrayList<ArrayList<chunk>> chunks, Poke user, Poke enemy) {
		defender.temphp -= damage;
		if(defender.temphp < 0) defender.temphp = 0;
		printInformation(user, enemy,chunks);
		mapDialogue(chunks,"It deals " + damage +" damage to " + defender.name + "'s hp!                                                                                  > continue                                    ");
	}
	
	public void assignDebuff(Poke curr, Poke other, Attack move, ArrayList<ArrayList<chunk>> chunks) {
		if(move.effect != "NONE") {
			if(move.attackType.equals("Physical") && curr.impact.equals("")) {
				if(move.effect.equals("Extra Turn")) {
					other.impact = move.effect;
					System.out.println(other.name + " has been assigned " + other.impact);
				} else {
					curr.impact = move.effect;
					mapDialogue(chunks, curr.name + " has been inflicted with " + move.effect.toLowerCase() + "                                                                                               > continue                                                                    ");
				}
			} else if(move.attackType.equals("Special") && curr.debuff.equals("")) {
				curr.debuff = move.effect;
				mapDialogue(chunks, curr.name + " has been inflicted with " + move.effect.toLowerCase() + "                                                                                               > continue                                                                    ");
			} else if(move.attackType.equals("Status") && curr.status.equals("")) {
				curr.status = move.effect;
				mapDialogue(chunks, curr.name + " has been inflicted with " + move.effect.toLowerCase() + "                                                                                               > continue                                                                    ");
			} else {
				return;
			}
			if(move.attackType.equals("Physical")) {
				if(move.effect.equals("Extra Turn")) {
					curr.impacttimer = 1;
				} else {
					Random ran = new Random();
					curr.impacttimer = ran.nextInt(1,3);
				}
			} else if(move.attackType.equals("Special")) {
				if(curr.debuff.equals("Drown") || curr.debuff.equals("Shock") || curr.debuff.equals("Photosynthesis") || curr.debuff.equals("Pollution")) {
					curr.debufftimer = 3;
				} else if(curr.debuff.equals("Burn") || curr.debuff.equals("Poison") || curr.debuff.equals("Mud") || curr.debuff.equals("Melting Point") || curr.debuff.equals("Malleable")) {
					curr.debufftimer = 3;
				}
			} else if(move.attackType.equals("Status")) {
				if(curr.status.equals("heal")) {
					curr.statustimer = 1;
				} else if(curr.status.equals("evade")) {
					curr.statustimer = 2;
				} else if(curr.status.equals("Thorns") || curr.status.equals("Blossom")) {
					curr.statustimer = 3;
				} else if(curr.status.equals("Rock Shield") || curr.status.equals("Ice Shield") || curr.status.equals("Reflect")) {
					curr.statustimer = 4;
				}
			}
		}
	}

	public  void inflictDebuff(Poke curr, Poke attacker, ArrayList<ArrayList<chunk>> chunks) {
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
		mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " burn damage!                                                                                                               > continue                                                      ");
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Poison(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//inflicts 15% dps
		double dmg = curr.temphp * .15;
		curr.temphp -= (int)dmg;
		mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " poison damage!                                                                                                             > continue                                                     ");
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Drown(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//inflict 5% dps
		double dmg = curr.temphp * .05;
		curr.temphp -= (int)dmg;
		mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " drown damage!                                                                                                              > continue                                                     ");
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Heal(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//heals the pokemon by 50% of its hp
		double healing = curr.temphp * .5;
		curr.temphp += (int)healing;
		mapDialogue(chunks, curr.name + " has been healed by " + (int)healing + " points!                                                                                                               > continue                                                     ");
		if(curr.temphp > curr.hp) curr.temphp = curr.hp;
	}
	public void Evade(Poke curr, Map map) {
		//provides current pokemon the ability to dodge every turn until its gone
	}
	public void Mud(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//causes current pokemon to lose control of what move they use, reduces POW of move by 15%, and removes the debuff if special
	}
	public void Rock_Shield(Poke curr,ArrayList<ArrayList<chunk>> chunks) {
		//provides current pokemon a 50% shield
	}
	public void Ice_Shield(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provides current pokemon a 20% shield and resistance to burning and poison
		if(curr.statustimer == 0) {

		}
	}
	public void Shock(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//slows the current pokemon down and 25% chance to miss a move
		curr.tempstats[0] = curr.speed  - (int)(curr.speed * .40);
		curr.tempstats[1] = curr.attack - (int)(curr.attack * .05);
		double dmg = curr.hp * .05;
		curr.temphp -= (int)dmg;
		mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " static damage!                                                                                                             > continue                                                     ");
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
	public void Reflect(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//reflects 40% of damage taken back onto enemy and reduces damage taken by 20%
		if(curr.statustimer == 0) {
		
		}
	}
	public void Extra_Turn(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provides pokemon an extra turn after performing their turn in battle
	}
	public void Photosynthesis(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks, Random ran) {
		//provides the current pokemon 
		double dmg = (int)(curr.hp * .1);
		Attacker.temphp += (int)dmg;
		curr.temphp -= (int)dmg;
		mapDialogue(chunks, Attacker.name + " syphens " + (int)dmg +" hp from " + curr.name + "                                                                                                           > continue                                                     ");
		if(Attacker.temphp > Attacker.hp) Attacker.temphp = Attacker.hp;
		if(curr.temphp < 0) curr.temphp = 0;
	}
	public void Thorns(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//thorn status effect grants current pokemon chance to damage enemy pokemon whenever it attacks it and a low chance to inflict poison
	}
	public void Paralyze(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//prevents current pokemon from using its turn in battle temporarily
	}
	public void Confuse(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//causes current pokemon to have a chance at attacking itself or teamates
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
	public void Pollution(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//will deal 5% damage to current pokemon team each time current pokemon is damaged in battle
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
		System.out.println(chance);
		Random ran = new Random();
		if(ran.nextInt(1, 255) <= chance) {
			//initiate capture script
			try {
				user.pokedex.addPoke(victim);
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
				} else {
					StringBuilder sb1 = new StringBuilder(curr.model[i-5].substring(0,18));
					StringBuilder sb2 = new StringBuilder(curr.model[i-5].substring(18,36));
					chunks.get(row+1).get(col).grid[i-5] = sb1.toString();
					chunks.get(row+1).get(col+1).grid[i-5] = sb2.toString();
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
			if(i >= chunks.get(0).get(2).grid[2].length()) chunks.get(0).get(2).grid[2] += " ";
			if(i >= chunks.get(0).get(3).grid[2].length()) chunks.get(0).get(3).grid[2] += " ";
			if(i >= chunks.get(0).get(7).grid[2].length()) chunks.get(0).get(7).grid[2] += " ";
			if(i >= chunks.get(0).get(8).grid[2].length()) chunks.get(0).get(8).grid[2] += " ";
			if(i >= chunks.get(0).get(2).grid[3].length()) chunks.get(0).get(2).grid[3] += " ";
			if(i >= chunks.get(0).get(7).grid[3].length()) chunks.get(0).get(7).grid[3] += " ";
			if(i >= chunks.get(3).get(3).grid[2].length()) chunks.get(3).get(3).grid[2] += " ";
			if(i >= chunks.get(3).get(8).grid[2].length()) chunks.get(3).get(8).grid[2] += " ";
			if(i >= chunks.get(4).get(3).grid[0].length()) chunks.get(4).get(3).grid[0] += " ";
			if(i >= chunks.get(4).get(3).grid[1].length()) chunks.get(4).get(3).grid[1] += " ";
			if(i >= chunks.get(4).get(3).grid[2].length()) chunks.get(4).get(3).grid[2] += " ";
			if(i >= chunks.get(4).get(8).grid[0].length()) chunks.get(4).get(8).grid[0] += " ";
			if(i >= chunks.get(4).get(8).grid[1].length()) chunks.get(4).get(8).grid[1] += " ";
			if(i >= chunks.get(4).get(8).grid[2].length()) chunks.get(4).get(8).grid[2] += " ";
		}
		
	}
	
		
	public void printMethod(ArrayList<ArrayList<chunk>> chunks) {
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
	public void mapDialogue(ArrayList<ArrayList<chunk>> chunks,String sentence) {
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












