package pokemon;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import pokemon.Battle;
import pokemon.Item;
import java.util.function.Function;
import java.util.Comparator;

public class PC {
	
	PC(){
		
	}
	
	
	public void displayPC(Player user, ArrayList<ArrayList<chunk>> chunks) {
		
		Collections.sort(user.pokedex, new Comparator<Poke>() {
			@Override
			public int compare(Poke curr, Poke other) {
				return Integer.compare(curr.ID, other.ID);
			}
		});
			
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 11; j++) {
					if(j != 9 && j != 10 && j != 8) {
						chunks.get(i).get(j).grid[0] = "------------------";
						chunks.get(i).get(j).grid[1] = "------------------";
						chunks.get(i).get(j).grid[2] = "------------------";
						chunks.get(i).get(j).grid[3] = "------------------";
						chunks.get(i).get(j).grid[4] = "------------------";
					} else if(j == 8){
						chunks.get(i).get(j).grid[0] = "-----------------|";
						chunks.get(i).get(j).grid[1] = "-----------------|";
						chunks.get(i).get(j).grid[2] = "-----------------|";
						chunks.get(i).get(j).grid[3] = "-----------------|";
						chunks.get(i).get(j).grid[4] = "-----------------|";
					} else {	
						chunks.get(i).get(j).blankify();
					}
				}
		}	
		
		chunks.get(0).get(9).grid[0] = "________ID________";chunks.get(0).get(10).grid[0] = "_______NAME_______";
		chunks.get(0).get(9).grid[1] = "                  ";chunks.get(0).get(10).grid[1] = "                  ";
		chunks.get(0).get(9).grid[2] = "                  ";chunks.get(0).get(10).grid[2] = "                  ";
		chunks.get(0).get(9).grid[3] = "                  ";chunks.get(0).get(10).grid[3] = "                  ";
		chunks.get(0).get(9).grid[4] = "                  ";chunks.get(0).get(10).grid[4] = "                  ";
		int start = 0; int end = 9;
		int pos = 0;
		int printrow = 0;
		String input = "";
		Scanner scan = new Scanner(System.in);
		while(!user.pokedex.isEmpty()) {
			printrow= 0;
			chunks.get(pos).get(9).grid[1] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(10).grid[1] = "~~~~~~~~~~~~~~~~~~";
			chunks.get(pos).get(9).grid[3] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(10).grid[3] = "~~~~~~~~~~~~~~~~~~";
			
			for(int i = start; i < end; i++) {
				try {
					chunks.get(printrow).get(9).grid[2] = "        " + user.pokedex.get(i).ID;chunks.get(printrow).get(10).grid[2] = " " + user.pokedex.get(i).name;
					printrow++;
				} catch(Exception e) {
					end = i;
					break;
				}
			}
			for(int i = 0; i < end; i++) {
				for(int j = 0; j < 18; j++) {
					if(j >= chunks.get(i).get(9).grid[2].length()) chunks.get(i).get(9).grid[2] += " ";
					if(j >= chunks.get(i).get(10).grid[2].length()) chunks.get(i).get(10).grid[2] += " ";
				}
			}	
		printInformation(user.pokedex.get(pos),chunks);
		printMethod(chunks);
		chunks.get(pos).get(9).grid[1] = "                  ";chunks.get(pos).get(10).grid[1] = "                  ";
		chunks.get(pos).get(9).grid[3] = "                  ";chunks.get(pos).get(10).grid[3] = "                  ";
		
		input = scan.next();
		if(input.equals("o")) {
			break;
		} else if(input.equals("w")) {
			if(pos == 0 && start != 0) {
				start--; end--;
			} else if(pos != 0) {
				pos--;
			}
		} else if(input.equals("s") && pos != user.pokedex.size()-1) {
			if(pos == 7 && end != user.pokedex.size()-1) {
				start++; end++;
			} else if(pos != 7){
				pos++;
			}
		} else if(input.equals("c")) {
			inspectMoves(user, user.pokedex.get(pos), chunks);
		}
		
		
		}	
	}
	
	public void inspectMoves(Player user, Poke p, ArrayList<ArrayList<chunk>> chunks) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		
		int pos = 2;
		while(true) {
			
			chunks.get(pos).get(4).grid[1] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(5).grid[1] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(6).grid[1] = "~~~~~~~~~~~~~~~~~~";
			if(pos == p.movesets.size()+2) {
				chunks.get(pos).get(4).grid[3] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(5).grid[3] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(6).grid[3] = "~~~~~~~~~~~~~~~~~~";
			} else {
				chunks.get(pos).get(4).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(5).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(pos).get(6).grid[4] = "~~~~~~~~~~~~~~~~~~";
			}
			
			printMethod(chunks);
			
			chunks.get(pos).get(4).grid[1] = "                  ";chunks.get(pos).get(5).grid[1] = "                  ";chunks.get(pos).get(6).grid[1] = "                  ";
			
			if(pos == p.movesets.size()+2) {
				chunks.get(pos).get(4).grid[3] = "                  ";chunks.get(pos).get(5).grid[3] = "                  ";chunks.get(pos).get(6).grid[3] = "                  ";
			} else {
				chunks.get(pos).get(4).grid[4] = "                  ";chunks.get(pos).get(5).grid[4] = "                  ";chunks.get(pos).get(6).grid[4] = "                  ";
			}
			
			
			input = scan.next();
			if(input.equals("o")) {
				return;
			} else if(input.equals("c")) {
				if(pos == p.movesets.size()+2) {
					addPokemonToRoster(user, p, chunks);
					return;
				} else {
					while(true) {
						displayMoveData(p, pos-2, chunks);
						printMethod(chunks);
						input = scan.next();
						if(input.equals("c") || input.equals("o")) {
							printInformation(p, chunks);
							break;
						}
					}
				}
			} else if(input.equals("w") && pos != 2) {
				pos--;
			} else if(input.equals("s") && pos != p.movesets.size()+2) {
				pos++;
			}
		
		
		}
	}
	
	public void displayMoveData(Poke p, int movechosen, ArrayList<ArrayList<chunk>> chunks) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				for(int g = 0; g < 5; g++) {
					chunks.get(i).get(j).grid[g] = "------------------";
				}
			}
		}
		for(int i = 1; i < 7; i++) {
			for(int j = 1; j < 7; j++) {
				chunks.get(i).get(j).blankify();
			}
		}
		for(int i = 0; i < 5; i++) {
			chunks.get(1).get(0).grid[i] = "-----------------|";
			chunks.get(2).get(0).grid[i] = "-----------------|";
			chunks.get(3).get(0).grid[i] = "-----------------|";
			chunks.get(4).get(0).grid[i] = "-----------------|";
			chunks.get(5).get(0).grid[i] = "-----------------|";
			chunks.get(6).get(0).grid[i] = "-----------------|";
			
			chunks.get(1).get(7).grid[i] = "|-----------------";
			chunks.get(2).get(7).grid[i] = "|-----------------";
			chunks.get(3).get(7).grid[i] = "|-----------------";
			chunks.get(4).get(7).grid[i] = "|-----------------";
			chunks.get(5).get(7).grid[i] = "|-----------------";
			chunks.get(6).get(7).grid[i] = "|-----------------";
			
			chunks.get(4).get(1).grid[i] = "                 {";
			chunks.get(5).get(1).grid[i] = "                 {";
			chunks.get(4).get(6).grid[i] = "}                 ";
			chunks.get(5).get(6).grid[i] = "}                 ";
		}
		chunks.get(3).get(2).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(3).get(3).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(3).get(4).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(3).get(5).grid[4] = "~~~~~~~~~~~~~~~~~~";
		chunks.get(6).get(2).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(6).get(3).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(6).get(4).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(6).get(5).grid[0] = "~~~~~~~~~~~~~~~~~~";
		
		chunks.get(0).get(1).grid[4] = "__________________";chunks.get(0).get(2).grid[4] = "__________________";chunks.get(0).get(3).grid[4] = "__________________";chunks.get(0).get(4).grid[4] = "__________________";chunks.get(0).get(5).grid[4] = "__________________";chunks.get(0).get(6).grid[4] = "__________________";
		chunks.get(6).get(1).grid[4] = "__________________";chunks.get(6).get(2).grid[4] = "__________________";chunks.get(6).get(3).grid[4] = "__________________";chunks.get(6).get(4).grid[4] = "__________________";chunks.get(6).get(5).grid[4] = "__________________";chunks.get(6).get(6).grid[4] = "__________________";
		
		chunks.get(1).get(2).grid[2] =p.movesets.get(movechosen).name; ;chunks.get(1).get(4).grid[2] = p.movesets.get(movechosen).type;chunks.get(1).get(6).grid[2] = "   " + p.movesets.get(movechosen).attackType;
		chunks.get(1).get(1).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(1).get(2).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(1).get(3).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(1).get(4).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(1).get(5).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(1).get(6).grid[4] = "~~~~~~~~~~~~~~~~~~";
		chunks.get(2).get(2).grid[1] = "   POW: " + p.movesets.get(movechosen).power; chunks.get(3).get(2).grid[1] = "   ACC: " + p.movesets.get(movechosen).accuracy; chunks.get(2).get(5).grid[1] = "PP: " +p.movesets.get(movechosen).pp; chunks.get(3).get(5).grid[1] = p.movesets.get(movechosen).effect;
		
	
		for(int i = 0; i < 18; i++) {
			if(i >= chunks.get(1).get(2).grid[2].length()) chunks.get(1).get(2).grid[2] += " ";
			if(i >= chunks.get(1).get(4).grid[2].length()) chunks.get(1).get(4).grid[2] += " ";
			if(i >= chunks.get(1).get(6).grid[2].length()) chunks.get(1).get(6).grid[2] += " ";
			if(i >= chunks.get(2).get(2).grid[1].length()) chunks.get(2).get(2).grid[1] += " ";
			if(i >= chunks.get(3).get(2).grid[1].length()) chunks.get(3).get(2).grid[1] += " ";
			if(i >= chunks.get(2).get(5).grid[1].length()) chunks.get(2).get(5).grid[1] += " ";
			if(i >= chunks.get(3).get(5).grid[1].length()) chunks.get(3).get(5).grid[1] += " ";
		}
		
		
	}
	
	public static void printInformation(Poke p,ArrayList<ArrayList<chunk>> chunks) {
		chunks.get(0).get(1).grid[0] = "-------...........";chunks.get(0).get(2).grid[0] = "...........-------";
		chunks.get(0).get(1).grid[1] = "----../~~~~~~~~~~~";chunks.get(0).get(2).grid[1] = "~~~~~~~~~~\\..-----";
		chunks.get(0).get(1).grid[2] = "--./~~~~~~~~~~~~~~";chunks.get(0).get(2).grid[2] = "~~~~~~~~~~~~~~\\.--";
		chunks.get(0).get(1).grid[3] = "-/~~~~~~~~~~~~~...";chunks.get(0).get(2).grid[3] = "...~~~~~~~~~~~~~\\-";
		chunks.get(0).get(1).grid[4] = "/_____________.__[";chunks.get(0).get(2).grid[4] = "]__._____________\\";
		
		chunks.get(3).get(1).grid[0] = "__________________"; chunks.get(3).get(2).grid[0] = "__________________";
		chunks.get(3).get(1).grid[1] = "\\                 "; chunks.get(3).get(2).grid[1] = "                 /";
		chunks.get(3).get(1).grid[2] = "--.               "; chunks.get(3).get(2).grid[2] = "               .--";
		chunks.get(3).get(1).grid[3] = "-----............."; chunks.get(3).get(2).grid[3] = "..............----";
		chunks.get(3).get(1).grid[4] = "__________________"; chunks.get(3).get(2).grid[4] = "__________________";
		
		for(int i = 0; i < 5; i++) {
			chunks.get(1).get(0).grid[i] = "-----------------|";
			chunks.get(2).get(0).grid[i] = "-----------------|";
			chunks.get(1).get(3).grid[i] = "|----------------|";
			chunks.get(2).get(3).grid[i] = "|----------------|";
			chunks.get(1).get(1).grid[i] = p.model[i].substring(0,18);
			chunks.get(1).get(2).grid[i] = p.model[i].substring(18,36);
			
			chunks.get(2).get(1).grid[i] = p.model[i+5].substring(0,18);
			chunks.get(2).get(2).grid[i] = p.model[i+5].substring(18,36);
		}

		
		for(int i = 0; i < 5; i++) {
			chunks.get(4).get(0).grid[i] = "-----------------|";
			chunks.get(6).get(1).grid[i] = "------------------";
			chunks.get(6).get(2).grid[i] = "------------------";
			chunks.get(3).get(0).grid[i] = "------------------";
			chunks.get(4).get(3).grid[i] = "|-----------------";
			chunks.get(5).get(0).grid[i] = "-----------------|";
			chunks.get(5).get(3).grid[i] = "|-----------------";
			chunks.get(6).get(3).grid[i] = "-----------------|";
			chunks.get(6).get(8).grid[i] = "-----------------|";
			chunks.get(3).get(3).grid[i] = "-----------------|";
			chunks.get(4).get(3).grid[i] = "|----------------|";
			chunks.get(6).get(0).grid[i] = "------------------";
			chunks.get(5).get(3).grid[i]  = "|----------------|";
			chunks.get(5).get(7).grid[i]  = "|-----------------";
			chunks.get(6).get(7).grid[i]  = "|-----------------";
			chunks.get(1).get(7).grid[i] = "|-----------------";
			chunks.get(2).get(7).grid[i] = "|-----------------";
			chunks.get(3).get(7).grid[i] = "|-----------------";
			chunks.get(4).get(7).grid[i] = "|-----------------";
		
		}
		for(int i = 1; i < 7; i++) {
			for(int j = 4; j < 7; j++) {
					chunks.get(i).get(j).blankify();
			}
		}
		chunks.get(1).get(5).grid[1] = "     Movesets     ";
		chunks.get(1).get(4).grid[2] = "******************";chunks.get(1).get(5).grid[2] = "******************";chunks.get(1).get(6).grid[2] = "******************";
		for(int i = 4; i < 6; i++) {
			for(int j = 1; j < 3; j++) {
				chunks.get(i).get(j).blankify();
			}
		}		
		chunks.get(4).get(1).grid[1] = "  " + p.name; chunks.get(4).get(2).grid[1] = "  " + p.type[0] + "  " + p.type[1];
		chunks.get(4).get(1).grid[2] = "******************";chunks.get(4).get(2).grid[2] = "******************";
		chunks.get(4).get(1).grid[4] = " HP:  " + p.temphp + "/" + p.hp; chunks.get(4).get(2).grid[4] = " Sp:    " + p.speed;
		chunks.get(5).get(1).grid[1] = " Att: " + p.attack; chunks.get(5).get(2).grid[1] = " SpAtt: " + p.spAttack;
		chunks.get(5).get(1).grid[3] = " Def: " + p.defense; chunks.get(5).get(2).grid[3] = " SpDef: " + p.spDefense;

		int row = 2;
		for(int i = 0; i < 4; i++) {
			try {
				chunks.get(row).get(4).grid[2] = " " + p.movesets.get(i).name; chunks.get(row).get(5).grid[2] = p.movesets.get(i).type; chunks.get(row).get(6).grid[2] = "POW: " + p.movesets.get(i).power;
				chunks.get(row).get(4).grid[3] = " " + p.movesets.get(i).attackType;
				row++;
			} catch(Exception e) {
				break;
			}
		}
		chunks.get(row).get(4).grid[2] = " [       Assign po"; chunks.get(row).get(5).grid[2] = "kemon to your team"; chunks.get(row).get(6).grid[2] = "'s roster       ] ";
		for(int i = 0; i < 18; i++) {
			if(i >= chunks.get(4).get(1).grid[1].length()) chunks.get(4).get(1).grid[1] += " ";
			if(i >= chunks.get(4).get(2).grid[1].length()) chunks.get(4).get(2).grid[1] += " ";
			if(i >= chunks.get(4).get(1).grid[4].length()) chunks.get(4).get(1).grid[4] += " ";
			if(i >= chunks.get(4).get(2).grid[4].length()) chunks.get(4).get(2).grid[4] += " ";
			if(i >= chunks.get(5).get(1).grid[1].length()) chunks.get(5).get(1).grid[1] += " ";
			if(i >= chunks.get(5).get(2).grid[1].length()) chunks.get(5).get(2).grid[1] += " ";
			if(i >= chunks.get(5).get(1).grid[3].length()) chunks.get(5).get(1).grid[3] += " ";
			if(i >= chunks.get(5).get(2).grid[3].length()) chunks.get(5).get(2).grid[3] += " ";
			
			if(i >= chunks.get(2).get(4).grid[2].length()) chunks.get(2).get(4).grid[2] += " ";
			if(i >= chunks.get(2).get(5).grid[2].length()) chunks.get(2).get(5).grid[2] += " ";
			if(i >= chunks.get(2).get(6).grid[2].length()) chunks.get(2).get(6).grid[2] += " ";
			if(i >= chunks.get(2).get(4).grid[3].length()) chunks.get(2).get(4).grid[3] += " "; 
			
			
			if(i >= chunks.get(3).get(4).grid[2].length()) chunks.get(3).get(4).grid[2] += " ";
			if(i >= chunks.get(3).get(5).grid[2].length()) chunks.get(3).get(5).grid[2] += " ";
			if(i >= chunks.get(3).get(6).grid[2].length()) chunks.get(3).get(6).grid[2] += " ";
			if(i >= chunks.get(3).get(4).grid[3].length()) chunks.get(3).get(4).grid[3] += " "; 
			
			
			if(i >= chunks.get(4).get(4).grid[2].length()) chunks.get(4).get(4).grid[2] += " ";
			if(i >= chunks.get(4).get(5).grid[2].length()) chunks.get(4).get(5).grid[2] += " ";
			if(i >= chunks.get(4).get(6).grid[2].length()) chunks.get(4).get(6).grid[2] += " ";
			if(i >= chunks.get(4).get(4).grid[3].length()) chunks.get(4).get(4).grid[3] += " "; 
			
			if(i >= chunks.get(5).get(4).grid[2].length()) chunks.get(5).get(4).grid[2] += " ";
			if(i >= chunks.get(5).get(5).grid[2].length()) chunks.get(5).get(5).grid[2] += " ";
			if(i >= chunks.get(5).get(6).grid[2].length()) chunks.get(5).get(6).grid[2] += " ";
			if(i >= chunks.get(5).get(4).grid[3].length()) chunks.get(5).get(4).grid[3] += " "; 
			
			if(i >= chunks.get(6).get(4).grid[2].length()) chunks.get(6).get(4).grid[2] += " ";
			if(i >= chunks.get(6).get(5).grid[2].length()) chunks.get(6).get(5).grid[2] += " ";
			if(i >= chunks.get(6).get(6).grid[2].length()) chunks.get(6).get(6).grid[2] += " ";
		}
		
		
		
		chunks.get(5).get(1).grid[4] = "__________________";chunks.get(5).get(2).grid[4] = "__________________";
		chunks.get(0).get(4).grid[4] = "__________________";	chunks.get(0).get(5).grid[4] = "__________________";	chunks.get(0).get(6).grid[4] = "__________________";
		chunks.get(6).get(4).grid[4] = "__________________";chunks.get(6).get(5).grid[4] = "__________________";chunks.get(6).get(6).grid[4] = "__________________";
		
	
	}
	
	public void addPokemonToRoster(Player user, Poke p, ArrayList<ArrayList<chunk>> chunks) {
		if(Player.numRoster != 6) {
			user.addPokemonToRoster(p);
			user.pokedex.remove(p);
		} else {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					for(int g = 0; g < 5; g++) {
						chunks.get(i).get(j).grid[g] = "------------------";
					}
				}
			}
			for(int i = 1; i < 7; i++) {
				for(int j = 2; j < 7; j++) {
					chunks.get(i).get(j).blankify();
				}
			}
			chunks.get(0).get(2).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(3).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(4).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(5).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(6).grid[4] = "~~~~~~~~~~~~~~~~~~";
			chunks.get(7).get(2).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(7).get(3).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(7).get(4).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(7).get(5).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(7).get(6).grid[0] = "~~~~~~~~~~~~~~~~~~";
			for(int i = 0; i < 5; i++) {
				chunks.get(1).get(1).grid[i] = "-----------------|";
				chunks.get(2).get(1).grid[i] = "-----------------|";
				chunks.get(3).get(1).grid[i] = "-----------------|";
				chunks.get(4).get(1).grid[i] = "-----------------|";
				chunks.get(5).get(1).grid[i] = "-----------------|";
				chunks.get(6).get(1).grid[i] = "-----------------|";
				
				chunks.get(1).get(7).grid[i] = "|-----------------";
				chunks.get(2).get(7).grid[i] = "|-----------------";
				chunks.get(3).get(7).grid[i] = "|-----------------";
				chunks.get(4).get(7).grid[i] = "|-----------------";
				chunks.get(5).get(7).grid[i] = "|-----------------";
				chunks.get(6).get(7).grid[i] = "|-----------------";
			}
			int row = 3; int col = 3; 
			for(int i = 0; i < 6; i++) {
				try {
					chunks.get(row).get(col-1).grid[1] = "  " + i+".";chunks.get(row).get(col).grid[1] = user.rosterlist.get(i).name;
					chunks.get(row).get(col).grid[2] = "  Level: " +user.rosterlist.get(i).level;
					chunks.get(row).get(col).grid[3] = "  HP: " + user.rosterlist.get(i).temphp +"/"+user.rosterlist.get(i).hp;
					row++;
					if(row >5) {
						row = 3; col = 6;
					}
				} catch(Exception e) {
					break;
				}
			}
			for(int i = 0; i < 18; i++) {
				if(i >= chunks.get(3).get(2).grid[1].length()) chunks.get(3).get(2).grid[1] += " ";
				if(i >= chunks.get(3).get(3).grid[1].length()) chunks.get(3).get(3).grid[1] += " ";
				if(i >= chunks.get(3).get(3).grid[2].length()) chunks.get(3).get(3).grid[2] += " ";
				if(i >= chunks.get(3).get(3).grid[3].length()) chunks.get(3).get(3).grid[3] += " ";
				
				if(i >= chunks.get(4).get(2).grid[1].length()) chunks.get(4).get(2).grid[1] += " ";
				if(i >= chunks.get(4).get(3).grid[1].length()) chunks.get(4).get(3).grid[1] += " ";
				if(i >= chunks.get(4).get(3).grid[2].length()) chunks.get(4).get(3).grid[2] += " ";
				if(i >= chunks.get(4).get(3).grid[3].length()) chunks.get(4).get(3).grid[3] += " ";
				
				if(i >= chunks.get(5).get(2).grid[1].length()) chunks.get(5).get(2).grid[1] += " ";
				if(i >= chunks.get(5).get(3).grid[1].length()) chunks.get(5).get(3).grid[1] += " ";
				if(i >= chunks.get(5).get(3).grid[2].length()) chunks.get(5).get(3).grid[2] += " ";
				if(i >= chunks.get(5).get(3).grid[3].length()) chunks.get(5).get(3).grid[3] += " ";
				
				if(i >= chunks.get(3).get(5).grid[1].length()) chunks.get(3).get(5).grid[1] += " ";
				if(i >= chunks.get(3).get(6).grid[1].length()) chunks.get(3).get(6).grid[1] += " ";
				if(i >= chunks.get(3).get(6).grid[2].length()) chunks.get(3).get(6).grid[2] += " ";
				if(i >= chunks.get(3).get(6).grid[3].length()) chunks.get(3).get(6).grid[3] += " ";
				
				if(i >= chunks.get(4).get(5).grid[1].length()) chunks.get(4).get(5).grid[1] += " ";
				if(i >= chunks.get(4).get(6).grid[1].length()) chunks.get(4).get(6).grid[1] += " ";
				if(i >= chunks.get(4).get(6).grid[2].length()) chunks.get(4).get(6).grid[2] += " ";
				if(i >= chunks.get(4).get(6).grid[3].length()) chunks.get(4).get(6).grid[3] += " ";
				
				if(i >= chunks.get(5).get(5).grid[1].length()) chunks.get(5).get(5).grid[1] += " ";
				if(i >= chunks.get(5).get(6).grid[1].length()) chunks.get(5).get(6).grid[1] += " ";
				if(i >= chunks.get(5).get(6).grid[2].length()) chunks.get(5).get(6).grid[2] += " ";
				if(i >= chunks.get(5).get(6).grid[3].length()) chunks.get(5).get(6).grid[3] += " ";
			}
			for(int i = 3; i < 6; i++) {
				for(int g = 0; g < 5; g++) {
					chunks.get(i).get(4).grid[g] = "        ||        ";
				}
			}
			chunks.get(2).get(2).grid[2] = " There is no more "; chunks.get(2).get(3).grid[2] = " on your team. Sel"; chunks.get(2).get(4).grid[2] = "ect a slot to plac"; chunks.get(2).get(5).grid[2] = "e your pokemon or "; chunks.get(2).get(6).grid[2]  = "press o to cancel ";
			for(int i = 2; i < 7; i++) {
				chunks.get(2).get(i).grid[4] = "^^^^^^^^^^^^^^^^^^";
				chunks.get(6).get(i).grid[0] = "vvvvvvvvvvvvvvvvvv";
			}
			Scanner scan = new Scanner(System.in);
			String input = "";
			int posrow = 3; int poscol = 2;
			while(true) {
				chunks.get(posrow).get(poscol).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(posrow).get(poscol+1).grid[0] = "~~~~~~~~~~~~~~~~~~";
				chunks.get(posrow).get(poscol).grid[4] = "~~~~~~~~~~~~~~~~~~";chunks.get(posrow).get(poscol+1).grid[4] = "~~~~~~~~~~~~~~~~~~";
				if(poscol == 2) {
					chunks.get(posrow).get(4).grid[0] = "~~~~~~~~||        ";
					chunks.get(posrow).get(4).grid[4] = "~~~~~~~~||        ";
				} else {
					chunks.get(posrow).get(4).grid[0] = "        ||~~~~~~~~";
					chunks.get(posrow).get(4).grid[4] = "        ||~~~~~~~~";
				}
				
				
				printMethod(chunks);
				chunks.get(posrow).get(poscol).grid[0] = "                  ";chunks.get(posrow).get(poscol+1).grid[0] = "                  ";
				chunks.get(posrow).get(poscol).grid[4] = "                  ";chunks.get(posrow).get(poscol+1).grid[4] = "                  ";
				for(int i = 0; i < 5; i++) {
					chunks.get(posrow).get(4).grid[i] = "        ||        ";
				}
				input = scan.next();
				if(input.equals("o")){
					break;
				} else if(input.equals("c")) { 
					int position = Integer.parseInt(chunks.get(posrow).get(poscol).grid[1].substring(2,3));
					user.pokedex.add(user.rosterlist.get(position));
					user.removePokemonFromRoster(user.rosterlist.get(position));
					System.out.println(Player.numRoster);
					user.addPokemonToRoster(p);
					user.pokedex.remove(p);
					for(int i = 0; i < 6; i++) {
						try {
							System.out.println(user.rosterlist.get(i).name);
						} catch(Exception e) {
							break;
						}
					}
					return;
					
				} else if(input.equals("w") && posrow != 3) {
					posrow--;
				} else if(input.equals("s") && posrow != 5) {
					posrow++;
				} else if(input.equals("a") && poscol != 2) {
					poscol -= 3;;
				} else if(input.equals("d") && poscol != 5) {
					poscol += 3;;
				}
				
			}
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
	
	
	
	
	
	
}
