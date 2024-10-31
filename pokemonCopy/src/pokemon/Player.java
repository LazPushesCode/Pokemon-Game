package pokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import pokemon.game;

class Item {
	String name;
	int amount;
	
	Item(String name, int amount){
		this.name = name;
		this.amount = amount;
	}
}

class Player{
	ArrayList<Item> items;
	ArrayList<Poke> pokemon;
	String[][] symbols;
	String[][] symbols2;
	Collection collection;
	String[] symbolsInterior;
	String[] symbolsInterior2;
	
	private static int numPokemon = 0;
	
	
	Player(){
		items = new ArrayList<>();
		pokemon = new ArrayList<>();
		Item bandage = new Item("Bandage", 3);
		Item pokeball = new Item("PokeBall", 8);
		Item revives = new Item("Revives", 1);
		Item candy = new Item("Candy", 4);
		Item repelent = new Item("Repelent", 1);
		items.add(bandage);
		items.add(pokeball);
		items.add(revives);
		items.add(repelent);
		items.add(candy);
		symbols = new String[3][4];
		symbols2 = new String[3][4];
		symbolsInterior = new String[10];
		symbolsInterior2 = new String[10];
	}
	
	public static int getNumPokemon() {
		return numPokemon;
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
	//add Pokemon to the pokedex
	public void addPoke(String name, String element, String element2, int attack, int specialAttack, int defense, int specialDefense, int speed) {
		if(collection == null) {
			Poke p = new Poke(name, element, element2, attack, specialAttack, defense, specialDefense, speed);
			collection = new Collection(name, p);
		} else {
			Poke p = new Poke(name, element, element2, attack, specialAttack, defense, specialDefense, speed);
			collection.addPokemon(name, p);
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
}