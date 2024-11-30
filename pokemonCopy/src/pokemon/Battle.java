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
		int userchoice = choosePokemon(user, wildpoke, chunks, map);
		int totalDead = 0; int numDead = 0;
		for(int i = 0; i < Player.numRoster; i++) {
			if(user.rosterlist.get(i).temphp == 0) {
				totalDead++;
			}
		}
		int result;
		//start overview battle rotation
		while(true) {
			if(wildpoke.temphp == 0) {
				//initiate battle end script
				break;
			}
			
			if(user.rosterlist.get(userchoice).temphp == 0) {
				if((Player.numRoster - totalDead) == numDead) {
					//initiate player loss script
					break;
				}
				choosePokemon(user,wildpoke,chunks, map);
			}
			
			result = simulateBattle(user,wildpoke,chunks,userchoice, 1, map);
			if(result == 3) {
				//ran from right script
				return;
			} else if(result == 0) {
				//player won
			} else if(result == 1) {
				//pokemon won
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
		if(enemy.speed > user.rosterlist.get(userchoice).speed) {
			//enemy attacks first!
		}
		displayMainBattleMenu(chunks);
		int choicerow = 5; int choicecol = 1;
		Scanner scan = new Scanner(System.in);
		String input = "";
		int round = 1; int termkey;
		while(true) {
			//player chooses between selecting attack, bag, Pokemon, or run (only if Pokemon is wild)
			termkey = 0;
			while(termkey == 0) {
				game.spaces();
				displayMainBattleMenu(chunks);
				if(!(user.rosterlist.get(userchoice).impact.equals("")))inflictImpact(user.rosterlist.get(userchoice), enemy, chunks);
				if(!(user.rosterlist.get(userchoice).debuff.equals("")))inflictDebuff(user.rosterlist.get(userchoice), enemy, chunks);
				if(user.rosterlist.get(userchoice).impact.equals("Paralyze")) {
					
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
									int moveChosen = displayFightMenu(user, enemy, chunks, userchoice);
									if(moveChosen != 5) {
										if(!(user.rosterlist.get(userchoice).movesets.get(moveChosen).attackType.equals("Status"))){
											if(!(enemy.status.equals("Evade"))){
												double dmgcalculated = calculateDamage(user, enemy, userchoice, moveChosen, map);
												int dmg = (int)dmgcalculated;
												//if the speed formula impacts the game, just change it and test please   -past Laz
												int Accuracy = (user.rosterlist.get(userchoice).movesets.get(moveChosen).accuracy + (user.rosterlist.get(userchoice).tempstats[0]/enemy.tempstats[0]));
												Random ran = new Random();
												if(Accuracy >= ran.nextInt(0, 100)) {
													inflictDamage(user.rosterlist.get(userchoice), enemy, dmg, chunks);
													if(!(user.rosterlist.get(userchoice).movesets.get(moveChosen).effect.equals(""))) {
														assignDebuff(enemy, user.rosterlist.get(userchoice).movesets.get(moveChosen), chunks);
													}	
												} else {
													mapDialogue(chunks, user.rosterlist.get(userchoice).name +"'s attack missed!                                                                                                                > continue                                    ");
												}
											} else {
												mapDialogue(chunks, enemy.name + "evaded the attack!                                                                                              > continue                                    ");
											}
										} else {
											//is a status
											assignDebuff(user.rosterlist.get(userchoice), user.rosterlist.get(userchoice).movesets.get(moveChosen), chunks);
											user.rosterlist.get(userchoice).movesets.get(moveChosen).pp--;
										}
										displayMainBattleMenu(chunks);
										break;
									}
									displayMainBattleMenu(chunks);
							} else if(choice == 1) {
									//roster
									
									displayMainBattleMenu(chunks);
							} else if (choice == 2) {
									if(choice == 2) {
										result = displayBagMenu(user, enemy, chunks, userchoice);
									} else { 
										result = 0;
									}
									displayMainBattleMenu(chunks);
									if(result == 1) {
										termkey = 1;
										break; 
									}
							} else if(choice == 3) {
									if(wildflag == 1 && choice == 3) {
										//run script
										return 3;
									}	
							}
						}
				}
				//enemy attacks here if they are not PARALYZED!
			}
			if(!(user.rosterlist.get(userchoice).status.equals(""))) {
				inflictStatus(user.rosterlist.get(userchoice), chunks);
			}
			
			if(!(enemy.impact.equals(""))) {
				inflictImpact(enemy, user.rosterlist.get(userchoice), chunks);
			}
			if(!(enemy.debuff.equals(""))) {
				inflictDebuff(enemy, user.rosterlist.get(userchoice), chunks);
			}
			if(!(enemy.status.equals(""))) {
				inflictStatus(enemy, chunks);
			}
			if(enemy.impact.equals("Paralyze")) {
				
			} else {
				
			}
		}
	}
	
	public double calculateDamage(Player user, Poke enemy, int pokechoice, int movechoice, Map map) {
		double damage = 0;
		int crit = 1;
		Random ran = new Random();
		Poke curr = user.rosterlist.get(pokechoice);
		double STAB;
		double strength = determineStrength(user.rosterlist.get(pokechoice).movesets.get(movechoice).type, enemy);
		if(curr.type.equals(curr.movesets.get(movechoice).type)) {
			STAB = 1.5;
		} else {
			STAB = 1;
		}
		if(curr.movesets.get(movechoice).attackType.equals("Physical")) {
			damage = ((((((curr.level * 2)/5)+5) * curr.movesets.get(movechoice).power * (curr.tempstats[1]/enemy.tempstats[3]))/50)+2) * crit * (ran.nextDouble(.85, 1)) * STAB * strength;
		} else if(curr.movesets.get(movechoice).attackType.equals("Special")) {
			damage = ((((((curr.level * 2)/5)+5) * curr.movesets.get(movechoice).power * (curr.tempstats[2]/enemy.tempstats[4]))/50)+2) * crit * (ran.nextDouble(.85, 1)) * STAB * strength;
		}
		return damage;
	}
	public double determineStrength(String type, Poke Defender) {
		double strength = 1;
		String arr[] = new String[] {"Fire", "Grass", "Rock", "Electric", "Metal", "Poison", "Water"};
		int attackerelement = 0; int defenderelement = 0;
		for(int i = 0; i < 7; i++) { 
			if(arr[i].equals(type)) attackerelement = i;
			if(arr[i].equals(Defender.type[0])) defenderelement= i;
		}
		if((attackerelement + 1) == defenderelement) {
			strength = 2;
		}else if(attackerelement == defenderelement) {
			return 0.5;
		}
		if(!(Defender.type[1].equals(""))) {
			for(int i = 0; i < 7; i++) { 
				if(arr[i].equals(type)) attackerelement = i;
				if(arr[i].equals(Defender.type[1])) defenderelement= i;
			}
			if((attackerelement + 1) == defenderelement) {
				strength = 2;
			}else if(attackerelement == defenderelement) {
				return 0.5;
			}
		}
		return strength;
	}
	public void inflictDamage(Poke attacker, Poke defender, int damage, ArrayList<ArrayList<chunk>> chunks) {
		defender.temphp -= damage;
		mapDialogue(chunks,"It deals " + damage +" damage to " + defender.name + "'s hp!                                                                                  > continue                                    ");
		if(defender.temphp < 0) defender.temphp = 0;
	}
	
	public int displayFightMenu(Player user, Poke enemy, ArrayList<ArrayList<chunk>> chunks, int userchoice) {
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
		String fragmented; String fragmented2;
		int itemrow = 2; int itemcol = 2; int itemgrid = 0; int item = 0;
		for(String key : user.backpack.items.keySet()) {
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
		int pos = 0; bagpositions[pos] = ">";
		int rate = 0;
		int rosterpos = 0;
		int userrosterrow= 0; int userrostergrid = 0;
		while(true) {
			pos = (useritemrow - 2) + (useritemgrid + rate);
			bagpositions[pos] = ">";
			chunks.get(useritemrow).get(2).grid[useritemgrid] = "                " + bagpositions[pos] + " ";
			System.out.println(pos + " " + useritemrow + "," + useritemgrid + " rate: " + rate);
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
							input = scan.next();
							confirmpositions[pos] = " ";
							if(input.equals("a") && pos != 0) {
								pos--;
							} else if(input.equals("d") && pos != 1) {
								pos++;
							} else if(input.equals("c")) {
								if(pos == 0) return 1;
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
	
	public void displayMainBattleMenu(ArrayList<ArrayList<chunk>> chunks) {
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
		
	}
	
	public void assignDebuff(Poke curr, Attack move, ArrayList<ArrayList<chunk>> chunks) {
		if(move.effect != "NONE") {
			if(move.attackType.equals("Physical") && curr.impact.equals("")) {
				curr.impact = move.effect;
			} else if(move.attackType.equals("Special") && curr.debuff.equals("")) {
				curr.debuff = move.effect;
			} else if(move.attackType.equals("Status") && curr.status.equals("")) {
				curr.status = move.effect;
			} else {
				return;
			}
			mapDialogue(chunks, curr.name + " has been inflicted with " + move.effect.toLowerCase() + "                                                                                               > continue");
			if(move.attackType.equals("Physical")) {
				if(move.effect.equals("Extra Turn")) {
					curr.impacttimer = 2;
				} else {
					Random ran = new Random();
					curr.impacttimer = ran.nextInt(1,4);
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
			} catch(NoSuchMethodException e) {
				try {
					Method method = Battle.class.getMethod(key,  Poke.class, Poke.class,  ArrayList.class);
					method.invoke(this, curr, attacker, chunks);
				} catch(Exception d) {
					System.out.println("Still could not find a method");
				}
				if(key.equals("Photosynthesis")) {
					Photosynthesis(curr, attacker, chunks);
				}
			}catch(Exception e) {
				e.printStackTrace();
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
				if(key.equals("Photosynthesis")) {
					Photosynthesis(curr, attacker, chunks);
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
		mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " burn damage!                                                                                                  > continue                                                      ");
		if(curr.temphp < 0) curr.temphp = 0;
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.debuff = "";
		}
	}
	public void Poison(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//inflicts 15% dps
		double dmg = curr.temphp * .15;
		curr.hp -= (int)dmg;
		mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " poison damage!                                                                                                > continue                                                     ");
		if(curr.temphp < 0) curr.temphp = 0;
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.debuff = "";
		}
	}
	public void Drown(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//inflict 5% dps
		double dmg = curr.temphp * .05;
		curr.hp -= (int)dmg;
		mapDialogue(chunks, curr.name + " has been hit with " + (int)dmg + " drown damage!                                                                                                 > continue                                                     ");
		if(curr.temphp < 0) curr.temphp = 0;
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.debuff = "";
		}
	}
	public void Heal(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//heals the pokemon by 50% of its hp
		double healing = curr.temphp * .5;
		curr.hp += (int)healing;
		mapDialogue(chunks, curr.name + " has been healed by " + (int)healing + " points!                                                                                                  > continue                                                     ");
		if(curr.temphp > curr.hp) curr.temphp = curr.hp;
	}
	public void Evade(Poke curr, Map map) {
		//provides current pokemon the ability to dodge every turn until its gone
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.status = "";
		}
	}
	public void Mud(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//causes current pokemon to lose control of what move they use, reduces POW of move by 45%, and removes the debuff if special
		
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.debuff = "";
		}
	}
	public void Rock_Shield(Poke curr,ArrayList<ArrayList<chunk>> chunks) {
		//provides current pokemon a 50% shield and resistance to bleed
		
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.status = "";
		}
	}
	public void Ice_Shield(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provides current pokemon a 20% shield and resistance to burning and poison
		
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.status = "";
		}
	}
	public void Shock(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//slows the current pokemon down and 25% chance to miss a move
		
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.debuff = "";
		}
	}
	public void Haste(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//should increase move accuracy, reduce damage taken, increase chance to dodge attack by a significant amount
		curr.tempstats[0] = (int)(curr.speed * 0.3) + curr.speed;
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.status = "";
		}
	}
	public void Reflect(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//reflects 40% of damage taken back onto enemy and reduces damage taken by 20%
		
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.status = "";
		}
	}
	public void Extra_Turn(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provides pokemon an extra turn after performing their turn in battle
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.status = "";
		}
	}
	public void Photosynthesis(Poke curr, Poke Attacker, ArrayList<ArrayList<chunk>> chunks) {
		//provides the current pokemon 
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.debuff = "";
		}
	}
	public void Thorns(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//thorn status effect grants current pokemon chance to damage enemy pokemon whenever it attacks it and a low chance to inflict poison
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.status = "";
		}
	}
	public void Paralyze(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//prevents current pokemon from using its turn in battle temporarily
		curr.impacttimer--;
	}
	public void Confuse(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//causes current pokemon to have a chance at attacking itself or teamates
		curr.impacttimer--;
	}
	public void Daze(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//lowers the accuracy of current pokemon by 65%
		curr.impacttimer--;
	}
	public void MeltingPoint(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//lowers all stats of the current pokemon by 15%
		for(int i = 0; i < 5; i++) {
			double stat = curr.tempstats[i] * .15;
			curr.tempstats[i] -= (int)stat;
		}
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.tempstats[0] = curr.speed;curr.tempstats[1] = curr.attack; curr.tempstats[2] = curr.spAttack;
			curr.tempstats[3] = curr.defense;curr.tempstats[4] = curr.spDefense;
		}
	}
	public void Malleable(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//lowers defense and special defense of pokemon by 20%
		double stat = curr.tempstats[3] * .20;
		curr.tempstats[3] -= (int)stat;
		stat = curr.tempstats[4] * .20;
		curr.tempstats[4] -= (int)stat;
		curr.debufftimer--;
		if(curr.debufftimer == 0) {
			curr.tempstats[3] = curr.defense;
			curr.tempstats[4] = curr.spDefense;
		}
	}
	public void Pollution(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//will deal 5% damage to current pokemon team each time current pokemon is damaged in battle
		curr.debufftimer--;
	}
	public void Blossom(Poke curr, ArrayList<ArrayList<chunk>> chunks) {
		//provide current pokemon a 2x multiplier to their attack and special attack stat
		curr.tempstats[1] *= 2;
		curr.tempstats[2] *= 2;
		curr.statustimer--;
		if(curr.statustimer == 0) {
			curr.tempstats[1] = curr.attack;
			curr.tempstats[2] = curr.spAttack;
		}
	}
		
	public void printMethod(ArrayList<ArrayList<chunk>> chunks) {
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
		printMethod(chunks);
		String input = "";
		Scanner scan = new Scanner(System.in);
		input = scan.next();
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












