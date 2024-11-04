package pokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import pokemon.game;
import pokemon.Item;
import pokemon.Map.chunk;

import pokemon.Backpack;

class Player{
	private HashMap<String, Poke> roster;
	ArrayList<Poke> rosterlist;
	Backpack backpack;
	String[][] symbols;
	String[][] symbols2;
	Collection collection;
	String[] symbolsInterior;
	String[] symbolsInterior2;
	static int gold = 100000000;
	private static int numRoster= 0;
	int execute = 0;
	Player(){
		rosterlist = new ArrayList<Poke>(6);
		backpack = new Backpack();
		roster = new HashMap<>();
		collection = new Collection();
		symbols = new String[3][4];
		symbols2 = new String[3][4];
		symbolsInterior = new String[10];
		symbolsInterior2 = new String[10];
	}
	
	public void addPokemonToRoster(Poke pokemon) {
		if(numRoster != 6) {
			roster.put(pokemon.name, pokemon);
			rosterlist.add(pokemon);
			numRoster++;
		}
	}
	public void addToPokeDex(Poke pokemon) {
		if(!(collection.hasPokemon(pokemon.name))) {
			collection.addPokemon(pokemon.name, pokemon);
		}
	}
	
	public void healAllPokemon() {
		for(String key : roster.keySet()) {
			roster.get(key).temphp = roster.get(key).hp;
		}	
	}
	
	
	public void customize() {
		String input = " ";
		Scanner scan = new Scanner(System.in);
		String[][] menu = new String[19][50];
		int posRow = 1;
		int posCol = 1;
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 49; j++) {
				menu[i][j] = " ";
				if(i == 0 || i == 18) {
					menu[i][j] = "_";
				}
				if(i != 0 && (j == 0 || j == 48)) {
					menu[i][j] = "|";
				}
			}
		}
		for(int i = 1; i < 48; i++) {
			menu[6][i] = "=";
			menu[12][i] = "=";
		}
		for(int i = 1; i < 18; i++) {
			menu[i][16] = "|";
			menu[i][32] = "|";
		}
		
		while(true) {
			game.spaces();
			menu = fillCell(menu, posRow, posCol);
			menu = charCells(menu);
			System.out.println("     Choose a custom character! \n\n\n");
			for(int i = 0; i < 19; i++) {
				if(i == 3) {
					System.out.print("   Up      - (w)    ");
				} else if(i == 5) {
					System.out.print("   Down    - (s)    ");
				} else if (i == 7) {
					System.out.print("   Left    - (a)    ");
				} else if(i == 9){
					System.out.print("   Right   - (d)    ");
				} else if(i == 1) {
					System.out.print("   Confirm - (c)    ");
				} else if(i == 11) {
					System.out.print("   Exit    - (o)    ");
				}else {
					System.out.print("                    ");
				}
				for(int j = 0; j < 49; j++) {
					System.out.print(menu[i][j]);
				}
				System.out.println("");
			}
				
			menu = emptyCell(menu, posRow, posCol);
			input = scan.next();
			
			if(input.equals("o")) {
				break;
			} else if(input.equals("w")) {
				if(posRow != 1) {
					posRow -= 6;
				}
			} else if(input.equals("s")) {
				if(posRow != 13) {
					posRow += 6;
				}
			} else if(input.equals("a")) {
				if(posCol != 1) {
					posCol -= 16;
				}
			} else if(input.equals("d")) {
				if(posCol != 33) {
					posCol += 16;
				}
			} else if(input.equals("c")) {
				if(posRow == 1) {
					if(posCol == 1) {
						symbols2[0][0] = " "; symbols2[0][1] = ","; symbols2[0][2] = ","; symbols2[0][3] = ","; 
						symbols2[1][0] = "|"; symbols2[1][1] = "'"; symbols2[1][2] = "'"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " ";
						symbolsInterior2[0] = "  ...//...//...// ";
						symbolsInterior2[1] = " |              | ";
						symbolsInterior2[2] = " |              | ";
						symbolsInterior2[3] = " |    |    |    | ";
						symbolsInterior2[4] = " |              | ";
						symbolsInterior2[5] = " |___        ___| ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | |  | |     ";
						symbolsInterior2[9] = "     |_|  |_|     ";
						
					} else if(posCol == 17) {
						symbols2[0][0] = "."; symbols2[0][1] = "."; symbols2[0][2] = "."; symbols2[0][3] = "."; 
						symbols2[1][0] = "|"; symbols2[1][1] = "'"; symbols2[1][2] = "'"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " ";
						symbolsInterior2[0] = "  *...*....*...*  ";
						symbolsInterior2[1] = " |              | ";
						symbolsInterior2[2] = " |              | ";
						symbolsInterior2[3] = " |    |    |    | ";
						symbolsInterior2[4] = " |              | ";
						symbolsInterior2[5] = " |___        ___| ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | |  | |     ";
						symbolsInterior2[9] = "     |_|  |_|     ";
					} else if(posCol == 33) {
						symbols2[0][0] = "."; symbols2[0][1] = "/"; symbols2[0][2] = "\\"; symbols2[0][3] = "."; 
						symbols2[1][0] = "|"; symbols2[1][1] = "^"; symbols2[1][2] = "^"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "/"; symbols2[2][2] = "\\"; symbols2[2][3] = " "; 
						symbolsInterior2[0] = "       /  \\       ";
						symbolsInterior2[1] = "   ../______\\..   ";
						symbolsInterior2[2] = "  |            |  ";
						symbolsInterior2[3] = "  |   /\\  /\\   |  ";
						symbolsInterior2[4] = "  |            |  ";
						symbolsInterior2[5] = "   \\ ________ /   ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     /      \\     ";
						symbolsInterior2[8] = "    /        \\    ";
						symbolsInterior2[9] = "   /          \\   ";
					}
				} else if(posRow ==7) {
					if(posCol == 1) {
						symbols2[0][0] = "_"; symbols2[0][1] = "\\"; symbols2[0][2] = "/"; symbols2[0][3] = "_"; 
						symbols2[1][0] = "|"; symbols2[1][1] = "\\"; symbols2[1][2] = "/"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " "; 
						symbolsInterior2[0] = "      \\    /      ";
						symbolsInterior2[1] = "  _____\\==/_____  ";
						symbolsInterior2[2] = "  |     \\/     |  ";
						symbolsInterior2[3] = "  |   \\    /   |  ";
						symbolsInterior2[4] = "  |    \\  /    |  ";
						symbolsInterior2[5] = "  |     \\/     |  ";
						symbolsInterior2[6] = "   \\___    ___/   ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | /  \\ |     ";
						symbolsInterior2[9] = "     | |  | |     ";
					} else if(posCol == 17) {
						symbols2[0][0] = "="; symbols2[0][1] = "="; symbols2[0][2] = "="; symbols2[0][3] = "="; 
						symbols2[1][0] = "|"; symbols2[1][1] = "'"; symbols2[1][2] = "'"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " "; 
						symbolsInterior2[0] = " ---------------- ";
						symbolsInterior2[1] = " ---------------- ";
						symbolsInterior2[2] = " |              | ";
						symbolsInterior2[3] = " |    |    |    | ";
						symbolsInterior2[4] = " |              | ";
						symbolsInterior2[5] = " |___        ___| ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | |  | |     ";
						symbolsInterior2[9] = "     |_|  |_|     ";
					} else if(posCol == 33) {
						symbols2[0][0] = "_"; symbols2[0][1] = "."; symbols2[0][2] = "."; symbols2[0][3] = "_"; 
						symbols2[1][0] = "|"; symbols2[1][1] = "-"; symbols2[1][2] = "-"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " "; 
						symbolsInterior2[0] = "_______*__*_______";
						symbolsInterior2[1] = "  /            \\  ";
						symbolsInterior2[2] = " |              | ";
						symbolsInterior2[3] = " |   __    __   | ";
						symbolsInterior2[4] = " |              | ";
						symbolsInterior2[5] = "  \\__        __/  ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | |  | |     ";
						symbolsInterior2[9] = "     |_|  |_|     ";
					}
				} else if(posRow == 13) {
					if(posCol == 1) {
						symbols2[0][0] = "_"; symbols2[0][1] = "["; symbols2[0][2] = "]"; symbols2[0][3] = "_"; 
						symbols2[1][0] = "|"; symbols2[1][1] = "."; symbols2[1][2] = "."; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " "; 
						symbolsInterior2[0] = "_______[  ]_______";
						symbolsInterior2[1] = "  /            \\  ";
						symbolsInterior2[2] = " |              | ";
						symbolsInterior2[3] = " |    *    *    | ";
						symbolsInterior2[4] = " |              | ";
						symbolsInterior2[5] = "  \\__        __/  ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | |  | |     ";
						symbolsInterior2[9] = "     |_|  |_|     ";
					} else if(posCol == 17) {
						symbols2[0][0] = "^"; symbols2[0][1] = "_"; symbols2[0][2] = "_"; symbols2[0][3] = "^"; 
						symbols2[1][0] = "|"; symbols2[1][1] = "0"; symbols2[1][2] = "0"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " "; 
						symbolsInterior2[0] = "     /\\    /\\     ";
						symbolsInterior2[1] = "  __/  \\__/  \\__  ";
						symbolsInterior2[2] = " |              | ";
						symbolsInterior2[3] = " |   (/)  (/)   | ";
						symbolsInterior2[4] = " |     _/\\_     | ";
						symbolsInterior2[5] = " |___        ___| ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | |  | |     ";
						symbolsInterior2[9] = "     |_|  |_|     ";
					} else if(posCol == 33) {
						symbols2[0][0] = ";"; symbols2[0][1] = "-"; symbols2[0][2] = "-"; symbols2[0][3] = ";"; 
						symbols2[1][0] = "|"; symbols2[1][1] = "'"; symbols2[1][2] = "'"; symbols2[1][3] = "|"; 
						symbols2[2][0] = " "; symbols2[2][1] = "|"; symbols2[2][2] = "|"; symbols2[2][3] = " "; 
						symbolsInterior2[0] = " *______________* ";
						symbolsInterior2[1] = " \"              \" ";
						symbolsInterior2[2] = " |              | ";
						symbolsInterior2[3] = " |    |    |    | ";
						symbolsInterior2[4] = " |              | ";
						symbolsInterior2[5] = " |___        ___| ";
						symbolsInterior2[6] = "     |      |     ";
						symbolsInterior2[7] = "     |      |     ";
						symbolsInterior2[8] = "     | |  | |     ";
						symbolsInterior2[9] = "     |_|  |_|     ";
					}
				}
				for (int i = 0; i < symbols2.length; i++) {
				    System.arraycopy(symbols2[i], 0, symbols[i], 0, symbols2[i].length);
				}
				System.arraycopy(symbolsInterior2, 0, symbolsInterior, 0, symbolsInterior2.length);
				break;
			}
				
		}
	}
	
	public String[][] fillCell(String menu[][], int row, int col) {
		for(int i = row; i < row+5; i++) {
			for(int j = col; j < col+15; j++) {
				if((i == row || i == row+4)) {
					menu[i][j] = "~";
				}
				if((i != row && i != row+4) && (j == col || j == col+14)) {
					menu[i][j] = "|";
				}
			}	
		}
		menu[row][col] = "/";
		menu[row][col+14] = "\\";
		menu[row+4][col] = "\\";
		menu[row+4][col+14] = "/";
		return menu;
	}
	public String[][] emptyCell(String menu[][], int row, int col){
		for(int i = row; i < row+5; i++) {
			for(int j = col; j < col +15; j++) {
				if(i == row || i == row+4) {
					menu[i][j] = " ";
				}
				if((i != row && i != row+4) && (j == col || j == col+14)) {
					menu[i][j] = " ";
				}	
			}
		}
		return menu;
	}
	public String[][] charCells(String menu[][]){
		//character slot 1
		menu[2][6] = " "; menu[2][7] = ","; menu[2][8] = ","; menu[2][9] = ","; 
		menu[3][6] = "|"; menu[3][7] = "'"; menu[3][8] = "'"; menu[3][9] = "|"; 
		menu[4][6] = " "; menu[4][7] = "|"; menu[4][8] = "|"; menu[4][9] = " ";
		//character slot 2
		menu[8][6] = "_"; menu[8][7] = "\\"; menu[8][8] = "/"; menu[8][9] = "_"; 
		menu[9][6] = "|"; menu[9][7] = "\\"; menu[9][8] = "/"; menu[9][9] = "|"; 
		menu[10][6] = " "; menu[10][7] = "|"; menu[10][8] = "|"; menu[10][9] = " ";
		//character slot 3
		menu[14][6] = "_"; menu[14][7] = "["; menu[14][8] = "]"; menu[14][9] = "_"; 
		menu[15][6] = "|"; menu[15][7] = "."; menu[15][8] = "."; menu[15][9] = "|"; 
		menu[16][6] = " "; menu[16][7] = "|"; menu[16][8] = "|"; menu[16][9] = " ";
		//character slot 4
		menu[2][22] = "."; menu[2][23] = "."; menu[2][24] = "."; menu[2][25] = "."; 
		menu[3][22] = "|"; menu[3][23] = "'"; menu[3][24] = "'"; menu[3][25] = "|"; 
		menu[4][22] = " "; menu[4][23] = "|"; menu[4][24] = "|"; menu[4][25] = " ";
		//character slot 5
		menu[8][22] = "="; menu[8][23] = "="; menu[8][24] = "="; menu[8][25] = "="; 
		menu[9][22] = "|"; menu[9][23] = "'"; menu[9][24] = "'"; menu[9][25] = "|"; 
		menu[10][22] = " "; menu[10][23] = "|"; menu[10][24] = "|"; menu[10][25] = " ";
		//character slot 6
		menu[14][22] = "^"; menu[14][23] = "_"; menu[14][24] = "_"; menu[14][25] = "^"; 
		menu[15][22] = "|"; menu[15][23] = "0"; menu[15][24] = "0"; menu[15][25] = "|"; 
		menu[16][22] = " "; menu[16][23] = "|"; menu[16][24] = "|"; menu[16][25] = " ";
		//character slot 7
		menu[2][38] = "."; menu[2][39] = "/"; menu[2][40] = "\\"; menu[2][41] = "."; 
		menu[3][38] = "|"; menu[3][39] = "^"; menu[3][40] = "^"; menu[3][41] = "|"; 
		menu[4][38] = " "; menu[4][39] = "/"; menu[4][40] = "\\"; menu[4][41] = " ";
		//character slot 8
		menu[8][38] = "_"; menu[8][39] = "."; menu[8][40] = "."; menu[8][41] = "_"; 
		menu[9][38] = "|"; menu[9][39] = "-"; menu[9][40] = "-"; menu[9][41] = "|"; 
		menu[10][38] = " "; menu[10][39] = "|"; menu[10][40] = "|"; menu[10][41] = " ";
		//character slot 9
		menu[14][38] = ";"; menu[14][39] = "-"; menu[14][40] = "-"; menu[14][41] = ";"; 
		menu[15][38] = "|"; menu[15][39] = "'"; menu[15][40] = "'"; menu[15][41] = "|"; 
		menu[16][38] = " "; menu[16][39] = "|"; menu[16][40] = "|"; menu[16][41] = " ";
		return menu;
	}
	public void openMenu(Player user, String area, String size, ArrayList<ArrayList<chunk>> chunks) {
		Scanner scan = new Scanner(System.in);
		int menurow = 3;
		int termkey = 0;
		String positions[] = {" ", " ", " "};
		String bagpositions[] = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",};
		int rosterrow = 2; int rostercol = 4;
		int bagpos = 0;
		int pos = 0;
		String input = "";
		int flag = 0;
		while(termkey == 0) {
			positions[pos] = ">";
			bagpositions[bagpos] = ">";
			loadMenuChunks(menurow, positions, bagpositions, chunks, flag);
			chunks.get(1).get(menurow).grid[1] = "~~~~~~~~~~~~~~~~~~";
			chunks.get(1).get(menurow).grid[2] = "|" + chunks.get(1).get(menurow).grid[2].substring(1, 17)  + "|";
			chunks.get(1).get(menurow).grid[3] = "~~~~~~~~~~~~~~~~~~";
			if(menurow == 7) {
				chunks.get(rosterrow).get(rostercol).grid[1] = "~~~~~~~~~~~~~~~~~~";
				chunks.get(rosterrow).get(rostercol-1).grid[2]= "                 /";chunks.get(rosterrow).get(rostercol).grid[2]   = chunks.get(rosterrow).get(rostercol).grid[2].substring(0,16) + "/ ";
				chunks.get(rosterrow).get(rostercol-1).grid[3]= "                / ";chunks.get(rosterrow).get(rostercol).grid[3]   = chunks.get(rosterrow).get(rostercol).grid[3].substring(0, 15) + "/  ";
				chunks.get(rosterrow).get(rostercol-1).grid[4]= "               /  ";chunks.get(rosterrow).get(rostercol).grid[4]   = chunks.get(rosterrow).get(rostercol).grid[4].substring(0, 14) + "/   ";
				chunks.get(rosterrow+1).get(rostercol-1).grid[0] = "               ~~~";chunks.get(rosterrow+1).get(rostercol).grid[0] = "~~~~~~~~~~~~~~    ";
			}
			
			game.spaces();
			for(int i = 0; i < 8; i++) {
				for(int gridIndex = 0; gridIndex < 5; gridIndex++) {
					System.out.print("|");
					for(int j = 0; j < 11; j++) {
						System.out.print(chunks.get(i).get(j).grid[gridIndex]);
					}
					System.out.println("|");
				}
			}
			input = scan.next();
			chunks.get(1).get(menurow).grid[1] = "                  ";
			chunks.get(1).get(menurow).grid[2] = " " + chunks.get(1).get(menurow).grid[2].substring(1, 17)  + " ";
			chunks.get(1).get(menurow).grid[3] = "                  ";
			if(input.equals("q") && menurow != 3 && flag != 1) {
				menurow -= 2;
				positions[pos] = " ";
				bagpositions[bagpos] = " ";
				pos = 0;bagpos = 0;
			} 
			if(input.equals("r") && menurow != 7 && flag != 1) {
				menurow += 2;
				positions[pos] = " ";
				bagpositions[bagpos] = " ";
				pos = 0;bagpos = 0;
				
			}
			if(menurow == 3) {
				if(input.equals("w") && pos != 0 && flag != 1) {
					positions[pos] = " ";
					positions[pos] = " ";
					bagpositions[bagpos] = " ";
					pos--;
				}
				if(input.equals("s") && pos != 2 && flag != 1) {
					positions[pos] = " ";
					positions[pos] = " ";
					bagpositions[bagpos] = " ";
					pos++;
				}
				if(input.equals("c")) {
					if(pos == 0) return;
					if(pos == 1) {
						if(flag == 1) {
							flag = 0;
						} else {
							flag = 1;
						}
					}
					if(pos == 2) {
						user.execute = 1;
						return;
					}
				}
			}
			if(menurow == 5) {
				if(user.backpack.numItems > 0) {
					if(input.equals("w") && bagpos != 0) {
						bagpositions[bagpos] = " ";
						bagpos--;
					} else if(input.equals("w") && bagpos == 0) {
						bagpositions[bagpos] = " ";
						bagpos = user.backpack.numItems - 1;
					}
					if(input.equals("s") && bagpos != user.backpack.numItems-1) {
						bagpositions[bagpos] = " ";
						bagpos++;
					} else if(input.equals("s") && bagpos == user.backpack.numItems-1) {
						bagpositions[bagpos] = " ";
						bagpos = 0;
					}
				}
			}
			if(menurow == 7) {
				if(numRoster != 0) {
					if(input.equals("w") && rosterrow != 2)rosterrow--;
					if(input.equals("s") && rosterrow != 4)rosterrow++;
					if(input.equals("a") && rostercol != 4)rostercol -= 2;
					if(input.equals("d") && rostercol != 6)rostercol += 2;
					if(input.equals("c")) {
						if(rosterrow == 2 && rostercol == 4 && 0 < numRoster) {
							loadPokemonStats(chunks, 0);
						}
						if(rosterrow == 3 && rostercol == 4 && 1 < numRoster) {
							loadPokemonStats(chunks, 1);
						}
						if(rosterrow == 4 && rostercol == 4 && 2 < numRoster) {
							loadPokemonStats(chunks, 2);
						}
						if(rosterrow == 2 && rostercol == 6 && 3 < numRoster) {
							loadPokemonStats(chunks, 3);
						}
						if(rosterrow == 3 && rostercol == 6 && 4 < numRoster) {
							loadPokemonStats(chunks, 4);
						}
						if(rosterrow == 4 && rostercol == 6 && 5 < numRoster) {
							loadPokemonStats(chunks, 5);
						}
					}
					
				}
			}
			
			if(input.equals("t") || input.equals("o")) {
				return;
			}
		}
	}
	
	public void loadMenuChunks(int tab, String positions[], String bagpositions[], ArrayList<ArrayList<chunk>> chunks, int flag) {
		if(tab == 3) {
		chunks.get(1).get(2).grid[0] =" _________________";chunks.get(1).get(3).grid[0] ="__________________";chunks.get(1).get(4).grid[0] ="__________________";chunks.get(1).get(5).grid[0] ="__________________";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="__________________";chunks.get(1).get(8).grid[0] ="_________________ ";
		chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                 |";
		chunks.get(1).get(2).grid[2] ="|                 ";chunks.get(1).get(3).grid[2] ="    M  E  N  U    ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="    B   A    G    ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="    T  E  A  M    ";chunks.get(1).get(8).grid[2] ="                 |";
		chunks.get(1).get(2).grid[3] ="|                 ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                 |";
		chunks.get(1).get(2).grid[4] ="|_________________";chunks.get(1).get(3).grid[4] ="__________________";chunks.get(1).get(4).grid[4] ="__________________";chunks.get(1).get(5).grid[4] ="__________________";chunks.get(1).get(6).grid[4] ="__________________";chunks.get(1).get(7).grid[4] ="__________________";chunks.get(1).get(8).grid[4] ="_________________|";
		chunks.get(2).get(2).grid[0] ="|                 ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                 |";
		chunks.get(2).get(2).grid[1] ="|                 ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                 |";
		chunks.get(2).get(2).grid[2] ="|                 ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                 |";
		chunks.get(2).get(2).grid[3] ="|                 ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                " + positions[0] + " ";chunks.get(2).get(5).grid[3] =" R  e  s  u  m  e ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                 |";
		chunks.get(2).get(2).grid[4] ="|                 ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                 |";
		chunks.get(3).get(2).grid[0] ="|                 ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                 |";
		chunks.get(3).get(2).grid[1] ="|                 ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                 |";
		chunks.get(3).get(2).grid[2] ="|                 ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                 |";
		chunks.get(3).get(2).grid[3] ="|                 ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                 |";
		chunks.get(3).get(2).grid[4] ="|                 ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                 " + positions[1];chunks.get(3).get(5).grid[4] ="  C o n t r o l s ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                 |";
		chunks.get(4).get(2).grid[0] ="|                 ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                 |";
		chunks.get(4).get(2).grid[1] ="|                 ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                 |";
		chunks.get(4).get(2).grid[2] ="|                 ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                 |";
		chunks.get(4).get(2).grid[3] ="|                 ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                 |";
		chunks.get(4).get(2).grid[4] ="|                 ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                 |";
		chunks.get(5).get(2).grid[0] ="|                 ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                 " + positions[2];chunks.get(5).get(5).grid[0] ="    Q  u  i  t    ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                 |";
		chunks.get(5).get(2).grid[1] ="|                 ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                 |";
		chunks.get(5).get(2).grid[2] ="|                 ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                 |";
		chunks.get(5).get(2).grid[3] ="|                 ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                 |";
		chunks.get(5).get(2).grid[4] ="|                 ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                 |";
		chunks.get(6).get(2).grid[0] ="|                 ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                 |";
		chunks.get(6).get(2).grid[1] ="|                 ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                 |";
		chunks.get(6).get(2).grid[2] ="|                 ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                 |";
		chunks.get(6).get(2).grid[3] ="|                 ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                 |";
		chunks.get(6).get(2).grid[4] ="|_________________";chunks.get(6).get(3).grid[4] ="__________________";chunks.get(6).get(4).grid[4] ="__________________";chunks.get(6).get(5).grid[4] ="__________________";chunks.get(6).get(6).grid[4] ="__________________";chunks.get(6).get(7).grid[4] ="__________________";chunks.get(6).get(8).grid[4] ="_________________|";	
			if(flag == 1) {
				chunks.get(1).get(2).grid[0] =" _________________";chunks.get(1).get(3).grid[0] ="__________________";chunks.get(1).get(4).grid[0] ="__________________";chunks.get(1).get(5).grid[0] ="__________________";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="__________________";chunks.get(1).get(8).grid[0] ="_________________ ";
				chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                 |";
				chunks.get(1).get(2).grid[2] ="|                 ";chunks.get(1).get(3).grid[2] ="    M  E  N  U    ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="    B   A    G    ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="    T  E  A  M    ";chunks.get(1).get(8).grid[2] ="                 |";
				chunks.get(1).get(2).grid[3] ="|                 ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                 |";
				chunks.get(1).get(2).grid[4] ="|_________________";chunks.get(1).get(3).grid[4] ="__________________";chunks.get(1).get(4).grid[4] ="__________________";chunks.get(1).get(5).grid[4] ="__________________";chunks.get(1).get(6).grid[4] ="__________________";chunks.get(1).get(7).grid[4] ="__________________";chunks.get(1).get(8).grid[4] ="_________________|";
				chunks.get(2).get(2).grid[0] ="|                 ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                 |";
				chunks.get(2).get(2).grid[1] ="|                 ";chunks.get(2).get(3).grid[1] ="  > back          ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                 |";
				chunks.get(2).get(2).grid[2] ="|                 ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                 |";
				chunks.get(2).get(2).grid[3] ="|                 ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                 |";
				chunks.get(2).get(2).grid[4] ="|                 ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                 |";
				chunks.get(3).get(2).grid[0] ="|                 ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                 |";
				chunks.get(3).get(2).grid[1] ="|                 ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                 |";
				chunks.get(3).get(2).grid[2] ="|                 ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                 |";
				chunks.get(3).get(2).grid[3] ="|                 ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                 |";
				chunks.get(3).get(2).grid[4] ="|                 ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                 |";
				chunks.get(4).get(2).grid[0] ="|                 ";chunks.get(4).get(3).grid[0] ="           _______";chunks.get(4).get(4).grid[0] ="___               ";chunks.get(4).get(5).grid[0] ="              ____";chunks.get(4).get(6).grid[0] ="______            ";chunks.get(4).get(7).grid[0] ="           ^  - up";chunks.get(4).get(8).grid[0] ="      (w)        |";
				chunks.get(4).get(2).grid[1] ="|                 ";chunks.get(4).get(3).grid[1] ="          [_______";chunks.get(4).get(4).grid[1] ="___]______________";chunks.get(4).get(5).grid[1] ="_____________[____";chunks.get(4).get(6).grid[1] ="______]           ";chunks.get(4).get(7).grid[1] ="           v  - do";chunks.get(4).get(8).grid[1] ="wn    (s)        |";
				chunks.get(4).get(2).grid[2] ="|                 ";chunks.get(4).get(3).grid[2] ="         /        ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="       \\          ";chunks.get(4).get(7).grid[2] ="           <  - le";chunks.get(4).get(8).grid[2] ="ft    (a)        |";
				chunks.get(4).get(2).grid[3] ="|                 ";chunks.get(4).get(3).grid[3] ="        /     ^   ";chunks.get(4).get(4).grid[3] ="     |            ";chunks.get(4).get(5).grid[3] ="          ||      ";chunks.get(4).get(6).grid[3] =" /\\     \\         ";chunks.get(4).get(7).grid[3] ="           >  - ri";chunks.get(4).get(8).grid[3] ="ght   (d)        |";
				chunks.get(4).get(2).grid[4] ="|                 ";chunks.get(4).get(3).grid[4] ="       /   <     >";chunks.get(4).get(4).grid[4] ="     ____         ";chunks.get(4).get(5).grid[4] ="       ____     []";chunks.get(4).get(6).grid[4] ="    ()   \\        ";chunks.get(4).get(7).grid[4] ="           >< - co";chunks.get(4).get(8).grid[4] ="nfirm (c)        |";
				chunks.get(5).get(2).grid[0] ="|                 ";chunks.get(5).get(3).grid[0] ="      /       v   ";chunks.get(5).get(4).grid[0] ="    |    |        ";chunks.get(5).get(5).grid[0] ="      |    |      ";chunks.get(5).get(6).grid[0] =" ><       \\       ";chunks.get(5).get(7).grid[0] ="           () - ba";chunks.get(5).get(8).grid[0] ="ck    (o)        |";
				chunks.get(5).get(2).grid[1] ="|                 ";chunks.get(5).get(3).grid[1] ="     /            ";chunks.get(5).get(4).grid[1] ="    |____|        ";chunks.get(5).get(5).grid[1] ="      |____|      ";chunks.get(5).get(6).grid[1] ="           \\      ";chunks.get(5).get(7).grid[1] ="           || - me";chunks.get(5).get(8).grid[1] ="nu    (t)        |";
				chunks.get(5).get(2).grid[2] ="|                 ";chunks.get(5).get(3).grid[2] ="    /             ";chunks.get(5).get(4).grid[2] ="__________________";chunks.get(5).get(5).grid[2] ="_________________ ";chunks.get(5).get(6).grid[2] ="            \\     ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                 |";
				chunks.get(5).get(2).grid[3] ="|                 ";chunks.get(5).get(3).grid[3] ="   /             /";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                 \\";chunks.get(5).get(6).grid[3] ="             \\    ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                 |";
				chunks.get(5).get(2).grid[4] ="|                 ";chunks.get(5).get(3).grid[4] ="  /             / ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="\\             \\   ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                 |";
				chunks.get(6).get(2).grid[0] ="|                 ";chunks.get(6).get(3).grid[0] =" /             /  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] =" \\             \\  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                 |";
				chunks.get(6).get(2).grid[1] ="|                 ";chunks.get(6).get(3).grid[1] ="/_____________/   ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="  \\_____________\\ ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                 |";
				chunks.get(6).get(2).grid[2] ="|                 ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                 |";
				chunks.get(6).get(2).grid[3] ="|                 ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                 |";
				chunks.get(6).get(2).grid[4] ="|_________________";chunks.get(6).get(3).grid[4] ="__________________";chunks.get(6).get(4).grid[4] ="__________________";chunks.get(6).get(5).grid[4] ="__________________";chunks.get(6).get(6).grid[4] ="__________________";chunks.get(6).get(7).grid[4] ="__________________";chunks.get(6).get(8).grid[4] ="_________________|";	
				
			}
		} 
		if(tab == 5) {
			chunks.get(1).get(2).grid[0] =" _________________";chunks.get(1).get(3).grid[0] ="__________________";chunks.get(1).get(4).grid[0] ="__________________";chunks.get(1).get(5).grid[0] ="__________________";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="__________________";chunks.get(1).get(8).grid[0] ="_________________ ";
			chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                 |";
			chunks.get(1).get(2).grid[2] ="|                 ";chunks.get(1).get(3).grid[2] ="    M  E  N  U    ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="    B   A    G    ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="    T  E  A  M    ";chunks.get(1).get(8).grid[2] ="                 |";
			chunks.get(1).get(2).grid[3] ="|                 ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                 |";
			chunks.get(1).get(2).grid[4] ="|_________________";chunks.get(1).get(3).grid[4] ="__________________";chunks.get(1).get(4).grid[4] ="__________________";chunks.get(1).get(5).grid[4] ="__________________";chunks.get(1).get(6).grid[4] ="__________________";chunks.get(1).get(7).grid[4] ="__________________";chunks.get(1).get(8).grid[4] ="_________________|";
			chunks.get(2).get(2).grid[0] ="|                 ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                 |";
			chunks.get(2).get(2).grid[1] ="|                 ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                 |";
			chunks.get(2).get(2).grid[2] ="|                 ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                 |";
			chunks.get(2).get(2).grid[3] ="|          Gold: $";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                 |";
			chunks.get(2).get(2).grid[4] ="|                 ";chunks.get(2).get(3).grid[4] ="~Item~        ~Amo";chunks.get(2).get(4).grid[4] ="unt~              ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                 |";
			chunks.get(3).get(2).grid[0] ="|              " + bagpositions[0] + "  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                 |";
			chunks.get(3).get(2).grid[1] ="|              " + bagpositions[1] + "  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                 |";
			chunks.get(3).get(2).grid[2] ="|              " + bagpositions[2] + "  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                 |";
			chunks.get(3).get(2).grid[3] ="|              " + bagpositions[3] + "  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                 |";
			chunks.get(3).get(2).grid[4] ="|              " + bagpositions[4] + "  ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                 |";
			chunks.get(4).get(2).grid[0] ="|              " + bagpositions[5] + "  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                 |";
			chunks.get(4).get(2).grid[1] ="|              " + bagpositions[6] + "  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                 |";
			chunks.get(4).get(2).grid[2] ="|              " + bagpositions[7] + "  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                 |";
			chunks.get(4).get(2).grid[3] ="|              " + bagpositions[8] + "  ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                 |";
			chunks.get(4).get(2).grid[4] ="|              " + bagpositions[9] + "  ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                 |";
			chunks.get(5).get(2).grid[0] ="|              " + bagpositions[10] + "  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                 |";
			chunks.get(5).get(2).grid[1] ="|              " + bagpositions[11] + "  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                 |";
			chunks.get(5).get(2).grid[2] ="|              " + bagpositions[12] + "  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                 |";
			chunks.get(5).get(2).grid[3] ="|              " + bagpositions[13] + "  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                 |";
			chunks.get(5).get(2).grid[4] ="|              " + bagpositions[14] + "  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                 |";
			chunks.get(6).get(2).grid[0] ="|              " + bagpositions[15] + "  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                 |";
			chunks.get(6).get(2).grid[1] ="|              " + bagpositions[16] + "  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                 |";
			chunks.get(6).get(2).grid[2] ="|              " + bagpositions[17] + "  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                 |";
			chunks.get(6).get(2).grid[3] ="|              " + bagpositions[18] + "  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                 |";
			chunks.get(6).get(2).grid[4] ="|_________________";chunks.get(6).get(3).grid[4] ="__________________";chunks.get(6).get(4).grid[4] ="__________________";chunks.get(6).get(5).grid[4] ="__________________";chunks.get(6).get(6).grid[4] ="__________________";chunks.get(6).get(7).grid[4] ="__________________";chunks.get(6).get(8).grid[4] ="_________________|";	
			
			String fragmented = String.valueOf(Player.gold);
			String fragmented2;
			chunks.get(2).get(3).grid[3] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(2).get(3).grid[3] += " ";
			}
			int row = 3; int gridIndex = 0;
			for(String key : backpack.items.keySet()) {
				fragmented = backpack.items.get(key).name;
				fragmented2 = String.valueOf(backpack.items.get(key).amount);
				chunks.get(row).get(3).grid[gridIndex] = fragmented;
				chunks.get(row).get(4).grid[gridIndex] = fragmented2;
				for(int i = fragmented.length(); i < 18; i++) {
					chunks.get(row).get(3).grid[gridIndex] += " ";
				}
				for(int i = fragmented2.length(); i < 18; i++) {
					chunks.get(row).get(4).grid[gridIndex] += " ";
				}
				gridIndex++;
				if(gridIndex == 5) {
					gridIndex = 0;
					row++;
				}
			}
			
		}
		if (tab == 7) {
			chunks.get(1).get(2).grid[0] =" _________________";chunks.get(1).get(3).grid[0] ="__________________";chunks.get(1).get(4).grid[0] ="__________________";chunks.get(1).get(5).grid[0] ="__________________";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="__________________";chunks.get(1).get(8).grid[0] ="_________________ ";
			chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                 |";
			chunks.get(1).get(2).grid[2] ="|                 ";chunks.get(1).get(3).grid[2] ="    M  E  N  U    ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="    B   A    G    ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="    T  E  A  M    ";chunks.get(1).get(8).grid[2] ="                 |";
			chunks.get(1).get(2).grid[3] ="|                 ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                 |";
			chunks.get(1).get(2).grid[4] ="|_________________";chunks.get(1).get(3).grid[4] ="__________________";chunks.get(1).get(4).grid[4] ="__________________";chunks.get(1).get(5).grid[4] ="__________________";chunks.get(1).get(6).grid[4] ="__________________";chunks.get(1).get(7).grid[4] ="__________________";chunks.get(1).get(8).grid[4] ="_________________|";
			chunks.get(2).get(2).grid[0] ="|                 ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                 |";
			chunks.get(2).get(2).grid[1] ="|                 ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                 |";
			chunks.get(2).get(2).grid[2] ="|                 ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                 |";
			chunks.get(2).get(2).grid[3] ="|                 ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                 |";
			chunks.get(2).get(2).grid[4] ="|                 ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                 |";
			chunks.get(3).get(2).grid[0] ="|                 ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                 |";
			chunks.get(3).get(2).grid[1] ="|                 ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                 |";
			chunks.get(3).get(2).grid[2] ="|                 ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                 |";
			chunks.get(3).get(2).grid[3] ="|                 ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                 |";
			chunks.get(3).get(2).grid[4] ="|                 ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                 |";
			chunks.get(4).get(2).grid[0] ="|                 ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                 |";
			chunks.get(4).get(2).grid[1] ="|                 ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                 |";
			chunks.get(4).get(2).grid[2] ="|                 ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                 |";
			chunks.get(4).get(2).grid[3] ="|                 ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                 |";
			chunks.get(4).get(2).grid[4] ="|                 ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                 |";
			chunks.get(5).get(2).grid[0] ="|                 ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                 |";
			chunks.get(5).get(2).grid[1] ="|                 ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                 |";
			chunks.get(5).get(2).grid[2] ="|                 ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                 |";
			chunks.get(5).get(2).grid[3] ="|                 ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                 |";
			chunks.get(5).get(2).grid[4] ="|                 ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                 |";
			chunks.get(6).get(2).grid[0] ="|                 ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                 |";
			chunks.get(6).get(2).grid[1] ="|                 ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                 |";
			chunks.get(6).get(2).grid[2] ="|                 ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                 |";
			chunks.get(6).get(2).grid[3] ="|                 ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                 |";
			chunks.get(6).get(2).grid[4] ="|_________________";chunks.get(6).get(3).grid[4] ="__________________";chunks.get(6).get(4).grid[4] ="__________________";chunks.get(6).get(5).grid[4] ="__________________";chunks.get(6).get(6).grid[4] ="__________________";chunks.get(6).get(7).grid[4] ="__________________";chunks.get(6).get(8).grid[4] ="_________________|";	
			String fragmented;
			String fragmented2;
			String fragmented3;
				int row = 2; int col = 4; int gridIndex = 2;
				for(int i = 0; i < numRoster; i++) {
					fragmented = rosterlist.get(i).name;
					fragmented2 = "    Level:" + rosterlist.get(i).level;
					fragmented3 = "    HP: " + rosterlist.get(i).temphp + "/" + rosterlist.get(i).hp;
					chunks.get(row).get(col).grid[gridIndex] = fragmented;
					chunks.get(row).get(col).grid[gridIndex+1] = fragmented2;
					chunks.get(row).get(col).grid[gridIndex+2] = fragmented3;
					for(int j = fragmented.length(); j < 18; j++) {
						chunks.get(row).get(col).grid[gridIndex] += " ";
					}
					for(int j = fragmented2.length(); j < 18; j++) {
						chunks.get(row).get(col).grid[gridIndex+1] += " "; 
					}
					for(int j = fragmented3.length(); j < 18; j++) {
						chunks.get(row).get(col).grid[gridIndex+2] += " "; 
					}
					row++;
					if(row == 5) {
						row = 2; col = 6;
					}
			}
		}
	}
	
	public void loadPokemonStats(ArrayList<ArrayList<chunk>> chunks, int pindex) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		String positions[] = {" ", " "};
		int pos = 0;
		while(true) {
			positions[pos] = ">";
			chunks.get(1).get(2).grid[0] =" _________________";chunks.get(1).get(3).grid[0] ="__________________";chunks.get(1).get(4).grid[0] ="__________________";chunks.get(1).get(5).grid[0] ="__________________";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="__________________";chunks.get(1).get(8).grid[0] ="_________________ ";
			chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="~~~~~~~~~~~~~~~~~~";chunks.get(1).get(8).grid[1] ="                 |";
			chunks.get(1).get(2).grid[2] ="|                 ";chunks.get(1).get(3).grid[2] ="    M  E  N  U    ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="    B   A    G    ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="|   T  E  A  M   |";chunks.get(1).get(8).grid[2] ="                 |";
			chunks.get(1).get(2).grid[3] ="|                 ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="~~~~~~~~~~~~~~~~~~";chunks.get(1).get(8).grid[3] ="                 |";
			chunks.get(1).get(2).grid[4] ="|_________________";chunks.get(1).get(3).grid[4] ="__________________";chunks.get(1).get(4).grid[4] ="__________________";chunks.get(1).get(5).grid[4] ="__________________";chunks.get(1).get(6).grid[4] ="__________________";chunks.get(1).get(7).grid[4] ="__________________";chunks.get(1).get(8).grid[4] ="_________________|";
			chunks.get(2).get(2).grid[0] ="|                 ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                 |";
			chunks.get(2).get(2).grid[1] ="|                 ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                 |";
			chunks.get(2).get(2).grid[2] ="|                 ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                 |";
			chunks.get(2).get(2).grid[3] ="|                 ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                 |";
			chunks.get(2).get(2).grid[4] ="|                 ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                 |";
			chunks.get(3).get(2).grid[0] ="|                 ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                 |";
			chunks.get(3).get(2).grid[1] ="|                 ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                 |";
			chunks.get(3).get(2).grid[2] ="|                 ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                 |";
			chunks.get(3).get(2).grid[3] ="|                 ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                 |";
			chunks.get(3).get(2).grid[4] ="|                 ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                 |";
			chunks.get(4).get(2).grid[0] ="|                 ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                 |";
			chunks.get(4).get(2).grid[1] ="|                 ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                 |";
			chunks.get(4).get(2).grid[2] ="|                 ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                 |";
			chunks.get(4).get(2).grid[3] ="|                 ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                 |";
			chunks.get(4).get(2).grid[4] ="|                 ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                 |";
			chunks.get(5).get(2).grid[0] ="|                 ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                 |";
			chunks.get(5).get(2).grid[1] ="|                 ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                 |";
			chunks.get(5).get(2).grid[2] ="|                 ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                 |";
			chunks.get(5).get(2).grid[3] ="|                 ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                 |";
			chunks.get(5).get(2).grid[4] ="|                 ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                 |";
			chunks.get(6).get(2).grid[0] ="|                 ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                 |";
			chunks.get(6).get(2).grid[1] ="|                 ";chunks.get(6).get(3).grid[1] ="             " + positions[0] + "Back";chunks.get(6).get(4).grid[1] ="            " + positions[1] + "Moves";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                 |";
			chunks.get(6).get(2).grid[2] ="|                 ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                 |";
			chunks.get(6).get(2).grid[3] ="|                 ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                 |";
			chunks.get(6).get(2).grid[4] ="|_________________";chunks.get(6).get(3).grid[4] ="__________________";chunks.get(6).get(4).grid[4] ="__________________";chunks.get(6).get(5).grid[4] ="__________________";chunks.get(6).get(6).grid[4] ="__________________";chunks.get(6).get(7).grid[4] ="__________________";chunks.get(6).get(8).grid[4] ="_________________|";	
			
			String fragmented = rosterlist.get(pindex).name;
			chunks.get(2).get(3).grid[4] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(2).get(3).grid[4] += " ";
			}
			fragmented = "Level: " + String.valueOf(rosterlist.get(pindex).level);
			chunks.get(2).get(4).grid[4] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(2).get(4).grid[4] += " ";
			}
			
			fragmented = rosterlist.get(pindex).type[0] + "  " + rosterlist.get(pindex).type[1];
			chunks.get(3).get(3).grid[1] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(3).get(3).grid[1] += " ";
			}
			
			
			
			fragmented = "       HP: " + String.valueOf(rosterlist.get(pindex).hp);
			chunks.get(4).get(4).grid[2] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(4).get(4).grid[2] += " ";
			}
			fragmented = "   Attack: " + String.valueOf(rosterlist.get(pindex).attack);
			chunks.get(4).get(4).grid[4] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(4).get(4).grid[4] += " ";
			}
			
			fragmented = " SpAttack: " + String.valueOf(rosterlist.get(pindex).spAttack);
			chunks.get(5).get(4).grid[1] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(5).get(4).grid[1] += " ";
			}
			fragmented = "    Speed: " + String.valueOf(rosterlist.get(pindex).speed);
			chunks.get(4).get(5).grid[2] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(4).get(5).grid[2] += " ";
			}
			fragmented = "  Defense: " + String.valueOf(rosterlist.get(pindex).defense);
			chunks.get(4).get(5).grid[4] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(4).get(5).grid[4] += " ";
			}
			fragmented = "SpDefense: " + String.valueOf(rosterlist.get(pindex).spDefense);
			chunks.get(5).get(5).grid[1] = fragmented;
			for(int i = fragmented.length(); i < 18; i++) {
				chunks.get(5).get(5).grid[1] += " ";
			}
			game.spaces();
			for(int i = 0; i < 8; i++) {
				for(int gridIndex = 0; gridIndex < 5; gridIndex++) {
					System.out.print("|");
					for(int j = 0; j < 11; j++) {
						System.out.print(chunks.get(i).get(j).grid[gridIndex]);
					}
					System.out.println("|");
				}
			}
			input = scan.next();
			if(input.equals("a") && pos != 0) {
				positions[pos] = " ";
				pos--;
			}
			if(input.equals("d") && pos != 1) {
				positions[pos] = " ";
				pos++;
			}
			if(input.equals("c")) {
				if(pos == 0) {
					break;
				}
				if(pos == 1) {
					while(true) {
						chunks.get(1).get(2).grid[0] =" _________________";chunks.get(1).get(3).grid[0] ="__________________";chunks.get(1).get(4).grid[0] ="__________________";chunks.get(1).get(5).grid[0] ="__________________";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="__________________";chunks.get(1).get(8).grid[0] ="_________________ ";
						chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="~~~~~~~~~~~~~~~~~~";chunks.get(1).get(8).grid[1] ="                 |";
						chunks.get(1).get(2).grid[2] ="|                 ";chunks.get(1).get(3).grid[2] ="    M  E  N  U    ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="    B   A    G    ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="|   T  E  A  M   |";chunks.get(1).get(8).grid[2] ="                 |";
						chunks.get(1).get(2).grid[3] ="|                 ";chunks.get(1).get(3).grid[3] ="                  ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="~~~~~~~~~~~~~~~~~~";chunks.get(1).get(8).grid[3] ="                 |";
						chunks.get(1).get(2).grid[4] ="|_________________";chunks.get(1).get(3).grid[4] ="__________________";chunks.get(1).get(4).grid[4] ="__________________";chunks.get(1).get(5).grid[4] ="__________________";chunks.get(1).get(6).grid[4] ="__________________";chunks.get(1).get(7).grid[4] ="__________________";chunks.get(1).get(8).grid[4] ="_________________|";
						chunks.get(2).get(2).grid[0] ="|                 ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                 |";
						chunks.get(2).get(2).grid[1] ="|                 ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                 |";
						chunks.get(2).get(2).grid[2] ="|                 ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                 |";
						chunks.get(2).get(2).grid[3] ="|                 ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                 |";
						chunks.get(2).get(2).grid[4] ="|                 ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                 |";
						chunks.get(3).get(2).grid[0] ="|                 ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                 |";
						chunks.get(3).get(2).grid[1] ="|                 ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                 |";
						chunks.get(3).get(2).grid[2] ="|                 ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                 |";
						chunks.get(3).get(2).grid[3] ="|                 ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                 |";
						chunks.get(3).get(2).grid[4] ="|                 ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                 |";
						chunks.get(4).get(2).grid[0] ="|                 ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                 |";
						chunks.get(4).get(2).grid[1] ="|                 ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                 |";
						chunks.get(4).get(2).grid[2] ="|                 ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                 |";
						chunks.get(4).get(2).grid[3] ="|                 ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                 |";
						chunks.get(4).get(2).grid[4] ="|                 ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                 |";
						chunks.get(5).get(2).grid[0] ="|                 ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                 |";
						chunks.get(5).get(2).grid[1] ="|                 ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                 |";
						chunks.get(5).get(2).grid[2] ="|                 ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                 |";
						chunks.get(5).get(2).grid[3] ="|                 ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                 |";
						chunks.get(5).get(2).grid[4] ="|                 ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                 |";
						chunks.get(6).get(2).grid[0] ="|                 ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                 |";
						chunks.get(6).get(2).grid[1] ="|                 ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                 |";
						chunks.get(6).get(2).grid[2] ="|                 ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                 |";
						chunks.get(6).get(2).grid[3] ="|                 ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                 |";
						chunks.get(6).get(2).grid[4] ="|_________________";chunks.get(6).get(3).grid[4] ="__________________";chunks.get(6).get(4).grid[4] ="__________________";chunks.get(6).get(5).grid[4] ="__________________";chunks.get(6).get(6).grid[4] ="__________________";chunks.get(6).get(7).grid[4] ="__________________";chunks.get(6).get(8).grid[4] ="_________________|";	
						
						game.spaces();
						for(int i = 0; i < 8; i++) {
							for(int gridIndex = 0; gridIndex < 5; gridIndex++) {
								System.out.print("|");
								for(int j = 0; j < 11; j++) {
									System.out.print(chunks.get(i).get(j).grid[gridIndex]);
								}
								System.out.println("|");
							}
						}
						input = scan.next();
						if(input.equals("o")) break;
					}
				}
			}
			if(input.equals("o")) break;
		}
		
	}
	
	
}