package pokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import pokemon.game;


public class Map {
	static int userrow;
	static int usercol;
	int prevrow;
	int prevcol;
	int area;
	String size;
	public ArrayList<ArrayList<chunk>> chunks;
	static String mapGen = "World1";
	static String mapSize = "small";
	private static HashMap<String, String> enterableChunks;
	private static HashMap<String, String> mapType;
	//maybe create the hashmap HERE
	//make a hashmap of the chunks here and a retrieve system here 
	Map() {
		area = 0;
		userrow = 6;
		usercol = 0;
		size = "small";
		chunks = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			ArrayList<chunk> row = new ArrayList<>();
			for(int j = 0; j < 11; j++) {
				chunk newChunk = new chunk(i, j);
				row.add(newChunk);
			}
			chunks.add(row);
		}
		
	}
	Map(String flag){
		size = "big";
		chunks = new ArrayList<>();
		for(int i = 0 ; i < 24; i++) {
			ArrayList<chunk> row = new ArrayList<>();
			for(int j = 0; j < 33; j++) {
				chunk newChunk = new chunk(i,j);
				row.add(newChunk);
			}
			chunks.add(row);
		}
	}
	
	public static void InitializeHashMaps() {
		enterableChunks = new HashMap<>();
		enterableChunks.put("World1"+2+""+6, "75");
		enterableChunks.put("World1"+2+""+3, "75");
		enterableChunks.put("World1"+6+""+0, "75");
		enterableChunks.put("World1"+6+""+9, "75");
		enterableChunks.put("PokeStop1"+7+""+5, "26");
		enterableChunks.put("PokeLab1"+7+""+5, "60");
		enterableChunks.put("Gym1"+7+""+5, "23");
		enterableChunks.put("House"+7+""+5, "69");	
		
		mapType = new HashMap<>();
		mapType.put("PokeStop1", "small");
		mapType.put("PokeStop2", "small");
		mapType.put("PokeStop3", "small");
		mapType.put("House", "small");
		mapType.put("World1", "small");
		mapType.put("PokeLab1", "big");
		mapType.put("Gym1", "big");
		
	}
	
	//run print out function to print chunks onto the display
	public void printMap(Player user, String area, String size) {
			chunk playerChunk = new chunk();
			chunk playerChunk2 = new chunk();
			if(area.equals("World1")) {
				playerChunk = modifyChunk(chunks.get(userrow).get(usercol), user, area, 1);
			}
			if(area.equals("Gym1") || area.equals("PokeStop1") || area.equals("House") || area.equals("PokeLab1")) {
				playerChunk = modifyChunk(chunks.get(userrow).get(usercol), user, area, 1);
				playerChunk2 = modifyChunk(chunks.get(userrow).get(usercol), user, area, 2);
			}
			if(size.equals("small")) {
			System.out.println("Area: " + area + " " + chunks.get(userrow).get(usercol).enterable);
			System.out.println("_".repeat(200));
			for(int j = 0; j < 8; j++) {
				//System.out.print(j );
				for(int i = 0; i < 5; i++) {
					System.out.print("|");
					
					for(int col = 0; col < 11; col++) {
						if(area.equals("World1")) {
							if(userrow == j && usercol == col) {
								System.out.print(playerChunk.grid[i]);
							} else {
								System.out.print(chunks.get(j).get(col).grid[i]);
							}
						} else if (area.equals("PokeStop1") || area.equals("House")) {
							if(userrow == j && usercol == col) {
								System.out.print(playerChunk2.grid[i]);
							} else if(userrow == j+1 && usercol == col) {
								System.out.print(playerChunk.grid[i]);
							} else {
								System.out.print(chunks.get(j).get(col).grid[i]);
							}
						}
					}
				if(i != 7) {
					System.out.println("|");
				}
			}
		}
			System.out.println("|" + "_".repeat(198) + "|");
	} else if(size.equals("big")){
		System.out.println("_".repeat(200));
		for(int i = userrow-3; i < userrow+4; i++) {
			for(int gridIndex = 0; gridIndex < 5; gridIndex++) {
				System.out.print("|");
				for(int j = usercol-5; j < usercol+6; j++) {
					if(userrow == i && usercol == j) {
						System.out.print(playerChunk2.grid[gridIndex]);
					} else if(userrow == i+1 && usercol ==j) {
						System.out.print(playerChunk.grid[gridIndex]);
					} else {
						System.out.print(chunks.get(i).get(j).grid[gridIndex]);
					}
				}
				System.out.println("|");
				
			}
		}
		System.out.println("|" + "_".repeat(198) + "|");
	}
}		
	public void printSimpleMap() {
		System.out.println("_".repeat(200));
		for(int i = 0; i < 8; i++) {
			for(int index = 0; index < 5; index++) {
				System.out.print("|");
				for(int j = 0; j < 11; j++) {
					System.out.print(chunks.get(i).get(j).grid[index]);
				}
				System.out.println("|");
			}
		}
		System.out.println("|" + "_".repeat(198) + "|");
	}
	
	//process the user's input to move the character or do actions
	public void takeInput(String input, Player user, String area) {
		prevrow = userrow;
		prevcol = usercol;
		if(input.equals("a")) {
			user.symbols[1][1] = user.symbols2[1][1];
			user.symbols[1][2] =  " ";
			if(mapSize.equals("small")) {
				if(usercol != 0 && chunks.get(userrow).get(usercol-1).right == 1) {
					usercol -= 1;
				}
			} else if(mapSize.equals("big")) {
				if(usercol-5 <= 0) return;
				usercol -=1;
			}
		} else if (input.equals("d")) {
			user.symbols[1][2] = user.symbols2[1][2];
			user.symbols[1][1] =  " ";
			if(mapSize.equals("small")) {
				if(usercol != 10 && chunks.get(userrow).get(usercol+1).left == 1) {
					usercol += 1;
				}
			} else if(mapSize.equals("big")) {
				if(usercol+5 >= 32) return;
				usercol += 1;
			}
		} else if(input.equals("w")) {
			user.symbols[1][1] = " ";
			user.symbols[1][2] = " ";
			if(mapSize.equals("small")) {
				if(userrow != 0 && chunks.get(userrow-1).get(usercol).down == 1) {
					userrow -= 1;
				}
			} else if(mapSize.equals("big")) {
				if(userrow-3 <= 0) return;
				userrow -= 1;
			}
		} else if(input.equals("s")) {
			user.symbols[1][1] = user.symbols2[1][1];
			user.symbols[1][2] = user.symbols2[1][2];
			if(mapSize.equals("small")) {
				if(userrow != 7 && chunks.get(userrow+1).get(usercol).up == 1) {
					userrow += 1;
				}
			} else if(mapSize.equals("big")) {
				if(userrow+3 >= 23) return;
				userrow += 1;
			}
		} else if(input.equals("t")) {
			openMenu(user);
		} else if(input.equals("c")) {
			if(chunks.get(userrow).get(usercol).enterable == 1){
				if(enterableChunks.get(mapGen+""+userrow+""+usercol) == null) return;
				String combinedRowCol = enterableChunks.get(mapGen+""+userrow+""+usercol);
				char[] pos = combinedRowCol.toCharArray();
				int parsedRow = Integer.parseInt(String.valueOf(pos[0])); int parsedCol = Integer.parseInt(String.valueOf(pos[1]));
				mapGen = chunks.get(userrow).get(usercol).enterArea;
				mapSize = mapType.get(mapGen);
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 11; j++) {
						chunks.get(i).get(j).enterable = 0;
						chunks.get(i).get(j).down = 1;chunks.get(i).get(j).up = 1;chunks.get(i).get(j).left = 1;chunks.get(i).get(j).right = 1;
					}
				}
				if(mapGen == "World1") {
					allChunksNonEnterableResetCollision();
				}
				userrow = parsedRow;usercol = parsedCol;
				setChunkValues(mapGen);
				return;
			}
			if(chunks.get(userrow).get(usercol).interactable == 1) {
				if((mapGen.equals("PokeStop1") || mapGen.equals("PokeStop2") || mapGen.equals("PokeStop3") )&& userrow == 3 && usercol == 5)  {
					pokeStopMenu(user, area);
				}
				if(mapGen.equals("House") && ((userrow == 2 && usercol == 1) || (userrow == 2 && usercol == 2))){
					user.customize();
				}
			

			}
		}
}
	
public void pokeStopMenu(Player user, String area) {
	//start poke stop menu
	chunks.get(0).get(0).grid[0]="                  ";chunks.get(0).get(1).grid[0]="                  ";chunks.get(0).get(2).grid[0] ="                  ";chunks.get(0).get(3).grid[0] ="                  ";chunks.get(0).get(4).grid[0] ="                  ";chunks.get(0).get(5).grid[0] ="                  ";chunks.get(0).get(6).grid[0] ="                  ";chunks.get(0).get(7).grid[0] ="                  ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
	chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="                  ";chunks.get(0).get(2).grid[1] ="                  ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
	chunks.get(0).get(0).grid[2]="                  ";chunks.get(0).get(1).grid[2]="                  ";chunks.get(0).get(2).grid[2] ="                  ";chunks.get(0).get(3).grid[2] ="                  ";chunks.get(0).get(4).grid[2] ="                  ";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] ="                  ";chunks.get(0).get(7).grid[2] ="                  ";chunks.get(0).get(8).grid[2] ="                  ";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="                  ";
	chunks.get(0).get(0).grid[3]="                  ";chunks.get(0).get(1).grid[3]="                  ";chunks.get(0).get(2).grid[3] ="                  ";chunks.get(0).get(3).grid[3] ="                  ";chunks.get(0).get(4).grid[3] ="                  ";chunks.get(0).get(5).grid[3] ="                  ";chunks.get(0).get(6).grid[3] ="                  ";chunks.get(0).get(7).grid[3] ="                  ";chunks.get(0).get(8).grid[3] ="                  ";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                  ";
	chunks.get(0).get(0).grid[4]="                  ";chunks.get(0).get(1).grid[4]="                  ";chunks.get(0).get(2).grid[4] ="                  ";chunks.get(0).get(3).grid[4] ="                  ";chunks.get(0).get(4).grid[4] ="                  ";chunks.get(0).get(5).grid[4] ="                  ";chunks.get(0).get(6).grid[4] ="                  ";chunks.get(0).get(7).grid[4] ="                  ";chunks.get(0).get(8).grid[4] ="                  ";chunks.get(0).get(9).grid[4] ="                  ";chunks.get(0).get(10).grid[4] ="                  ";
	
	chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="                  ";chunks.get(1).get(2).grid[0] ="          ________";chunks.get(1).get(3).grid[0] ="________          ";chunks.get(1).get(4).grid[0] ="                  ";chunks.get(1).get(5).grid[0] ="                  ";chunks.get(1).get(6).grid[0] ="                  ";chunks.get(1).get(7).grid[0] ="                  ";chunks.get(1).get(8).grid[0] ="                  ";chunks.get(1).get(9).grid[0] ="                  ";chunks.get(1).get(10).grid[0] ="                  ";
	chunks.get(1).get(0).grid[1]="                  ";chunks.get(1).get(1).grid[1]="                  ";chunks.get(1).get(2).grid[1] ="         \\        ";chunks.get(1).get(3).grid[1] ="        /         ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                  ";chunks.get(1).get(9).grid[1] ="                  ";chunks.get(1).get(10).grid[1] ="                  ";
	chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="                  ";chunks.get(1).get(2).grid[2] ="          \\______(";chunks.get(1).get(3).grid[2] =")______/          ";chunks.get(1).get(4).grid[2] ="                  ";chunks.get(1).get(5).grid[2] ="                  ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="                  ";chunks.get(1).get(8).grid[2] ="                  ";chunks.get(1).get(9).grid[2] ="                  ";chunks.get(1).get(10).grid[2] ="                  ";
	chunks.get(1).get(0).grid[3]="                  ";chunks.get(1).get(1).grid[3]="                  ";chunks.get(1).get(2).grid[3] ="        / ________";chunks.get(1).get(3).grid[3] ="____     \\        ";chunks.get(1).get(4).grid[3] ="                  ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="                  ";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3] ="                  ";chunks.get(1).get(10).grid[3] ="                  ";
	chunks.get(1).get(0).grid[4]="                  ";chunks.get(1).get(1).grid[4]="                  ";chunks.get(1).get(2).grid[4] ="       / /        ";chunks.get(1).get(3).grid[4] ="    \\  /\\ \\       ";chunks.get(1).get(4).grid[4] ="                  ";chunks.get(1).get(5).grid[4] ="                  ";chunks.get(1).get(6).grid[4] ="                  ";chunks.get(1).get(7).grid[4] ="                  ";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="                  ";chunks.get(1).get(10).grid[4] ="                  ";
	chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="                  ";chunks.get(2).get(2).grid[0] ="      | |         ";chunks.get(2).get(3).grid[0] ="     \\/  | |      ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="                  ";
	chunks.get(2).get(0).grid[1]="                  ";chunks.get(2).get(1).grid[1]="                  ";chunks.get(2).get(2).grid[1] ="      | |      |  ";chunks.get(2).get(3).grid[1] ="  |      | |      ";chunks.get(2).get(4).grid[1] ="                  ";chunks.get(2).get(5).grid[1] ="                  ";chunks.get(2).get(6).grid[1] ="                  ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="                  ";
	chunks.get(2).get(0).grid[2]="                  ";chunks.get(2).get(1).grid[2]="                  ";chunks.get(2).get(2).grid[2] ="      | |      |  ";chunks.get(2).get(3).grid[2] ="  |      | |      ";chunks.get(2).get(4).grid[2] ="                  ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                  ";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="                  ";chunks.get(2).get(10).grid[2] ="                  ";
	chunks.get(2).get(0).grid[3]="                  ";chunks.get(2).get(1).grid[3]="                  ";chunks.get(2).get(2).grid[3] ="      | |         ";chunks.get(2).get(3).grid[3] ="         | |      ";chunks.get(2).get(4).grid[3] ="                  ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                  ";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="                  ";chunks.get(2).get(10).grid[3] ="                  ";
	chunks.get(2).get(0).grid[4]="                  ";chunks.get(2).get(1).grid[4]="                  ";chunks.get(2).get(2).grid[4] ="      | |     .___";chunks.get(2).get(3).grid[4] ="___.     | |      ";chunks.get(2).get(4).grid[4] ="                  ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                  ";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="                  ";chunks.get(2).get(10).grid[4] ="                  ";
	chunks.get(3).get(0).grid[0]="                  ";chunks.get(3).get(1).grid[0]="                  ";chunks.get(3).get(2).grid[0] ="    _/   \\        ";chunks.get(3).get(3).grid[0] ="        /   \\_    ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="                  ";chunks.get(3).get(10).grid[0] ="                  ";
	chunks.get(3).get(0).grid[1]="                  ";chunks.get(3).get(1).grid[1]="                  ";chunks.get(3).get(2).grid[1] ="   (__)___\\____   ";chunks.get(3).get(3).grid[1] ="   ____/___(__)   ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="                  ";chunks.get(3).get(10).grid[1] ="                  ";
	chunks.get(3).get(0).grid[2]="                  ";chunks.get(3).get(1).grid[2]="                  ";chunks.get(3).get(2).grid[2] ="             __|  ";chunks.get(3).get(3).grid[2] ="  |__             ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="                  ";chunks.get(3).get(10).grid[2] ="                  ";
	chunks.get(3).get(0).grid[3]="                  ";chunks.get(3).get(1).grid[3]="                  ";chunks.get(3).get(2).grid[3] ="            ////\\-";chunks.get(3).get(3).grid[3] ="-////\\            ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="                  ";chunks.get(3).get(10).grid[3] ="                  ";
	chunks.get(3).get(0).grid[4]="                  ";chunks.get(3).get(1).grid[4]="                  ";chunks.get(3).get(2).grid[4] ="           //////\\";chunks.get(3).get(3).grid[4] ="//////\\           ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="                  ";chunks.get(3).get(10).grid[4] ="                  ";
	
	chunks.get(4).get(0).grid[0]="                  ";chunks.get(4).get(1).grid[0]="                  ";chunks.get(4).get(2).grid[0] ="          |/////|-";chunks.get(4).get(3).grid[0] ="-|/////|          ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] ="                  ";chunks.get(4).get(10).grid[0] ="                  ";
	chunks.get(4).get(0).grid[1]="                  ";chunks.get(4).get(1).grid[1]="                  ";chunks.get(4).get(2).grid[1] ="          |/////|-";chunks.get(4).get(3).grid[1] ="-|/////|          ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="                  ";chunks.get(4).get(10).grid[1] ="                  ";
	chunks.get(4).get(0).grid[2]="                  ";chunks.get(4).get(1).grid[2]="                  ";chunks.get(4).get(2).grid[2] ="          |/////|-";chunks.get(4).get(3).grid[2] ="-|/////|          ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="                  ";chunks.get(4).get(10).grid[2] ="                  ";
	chunks.get(4).get(0).grid[3]="__________________";chunks.get(4).get(1).grid[3]="__________________";chunks.get(4).get(2).grid[3] ="__________________";chunks.get(4).get(3).grid[3] ="__________________";chunks.get(4).get(4).grid[3] ="__________________";chunks.get(4).get(5).grid[3] ="__________________";chunks.get(4).get(6).grid[3] ="__________________";chunks.get(4).get(7).grid[3] ="__________________";chunks.get(4).get(8).grid[3] ="__________________";chunks.get(4).get(9).grid[3] ="__________________";chunks.get(4).get(10).grid[3] ="__________________";
	chunks.get(4).get(0).grid[4]="..................";chunks.get(4).get(1).grid[4]="..................";chunks.get(4).get(2).grid[4] ="..................";chunks.get(4).get(3).grid[4] ="..................";chunks.get(4).get(4).grid[4] ="..................";chunks.get(4).get(5).grid[4] ="..................";chunks.get(4).get(6).grid[4] ="..................";chunks.get(4).get(7).grid[4] ="..................";chunks.get(4).get(8).grid[4] ="..................";chunks.get(4).get(9).grid[4] ="..................";chunks.get(4).get(10).grid[4] ="..................";
	
	chunks.get(5).get(0).grid[0]=".                 ";chunks.get(5).get(1).grid[0]="                  ";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="                  ";chunks.get(5).get(10).grid[0] ="                 .";
	chunks.get(5).get(0).grid[1]=".                 ";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="                  ";chunks.get(5).get(10).grid[1] ="                 .";
	chunks.get(5).get(0).grid[2]=".                 ";chunks.get(5).get(1).grid[2]="                  ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="                  ";chunks.get(5).get(10).grid[2] ="                 .";
	chunks.get(5).get(0).grid[3]=".                 ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="                  ";chunks.get(5).get(10).grid[3] ="                 .";
	chunks.get(5).get(0).grid[4]=".                 ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="                  ";chunks.get(5).get(10).grid[4] ="                 .";
	
	chunks.get(6).get(0).grid[0]=".                 ";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="                  ";chunks.get(6).get(10).grid[0] ="                 .";
	chunks.get(6).get(0).grid[1]=".                 ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="                  ";chunks.get(6).get(10).grid[1] ="                 .";
	chunks.get(6).get(0).grid[2]=".                 ";chunks.get(6).get(1).grid[2]="                  ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="                  ";chunks.get(6).get(10).grid[2] ="                 .";
	chunks.get(6).get(0).grid[3]=".                 ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="                  ";chunks.get(6).get(10).grid[3] ="                 .";
	chunks.get(6).get(0).grid[4]=".                 ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="                  ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="                  ";chunks.get(6).get(10).grid[4] ="                 .";
	
	chunks.get(7).get(0).grid[0]=".                 ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="                  ";chunks.get(7).get(5).grid[0] ="                  ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                 .";
	chunks.get(7).get(0).grid[1]=".                 ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="                  ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                 .";
	chunks.get(7).get(0).grid[2]=".                 ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                 .";
	chunks.get(7).get(0).grid[3]=".                 ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="                  ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                 .";
	chunks.get(7).get(0).grid[4]="..................";chunks.get(7).get(1).grid[4]="..................";chunks.get(7).get(2).grid[4] ="..................";chunks.get(7).get(3).grid[4] ="..................";chunks.get(7).get(4).grid[4] ="..................";chunks.get(7).get(5).grid[4] ="..................";chunks.get(7).get(6).grid[4] ="..................";chunks.get(7).get(7).grid[4] ="..................";chunks.get(7).get(8).grid[4] ="..................";chunks.get(7).get(9).grid[4] ="..................";chunks.get(7).get(10).grid[4] ="..................";
	Scanner scan = new Scanner(System.in);
	String input = "";
	String menu = "main";
	char positions[] = {' ', ' ', ' '};
	int pos = 0;
	while(true) {
		if(input.equals("c")) {
			if(pos == 0) {
				mapDialogue("Alright! Hand me your PokeBalls and I'll get them ready for healing!        ");
				printSimpleMap();
				scan.next();
				//implement healing loop
				mapDialogue("Inserting PokeBalls... Preparing healing machine... Healing aaaaaand... DONE!");
				printSimpleMap();
				scan.next();
				mapDialogue("Your team is all healed!               ");
				printSimpleMap();
				scan.next();
				input = "";
			}
			if (pos == 1){
				mapDialogue("");
				int buypos = 0;
				char buypositions[] = {'>', ' ', ' ', ' ', ' '};
				chunks.get(0).get(5).grid[0] = "  ~~~~~~~~~~~~~~~~";chunks.get(0).get(6).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(7).grid[0] = "~~~~~~~~~~~~~~~~  ";
				chunks.get(0).get(5).grid[1] = "{|                ";chunks.get(0).get(6).grid[1] = "                  ";chunks.get(0).get(7).grid[1] = "                |}";
				chunks.get(0).get(5).grid[2] = "{|                ";chunks.get(0).get(6).grid[2] = "     S H O P      ";chunks.get(0).get(7).grid[2] = "                |}";
				chunks.get(0).get(5).grid[3] = "{|________________";chunks.get(0).get(6).grid[3] = "__________________";chunks.get(0).get(7).grid[3] = "________________|}";
				chunks.get(0).get(5).grid[4] = "{|                ";chunks.get(0).get(6).grid[4] = "                  ";chunks.get(0).get(7).grid[4] = "                |}";
				chunks.get(1).get(5).grid[0] = "{|                ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                |}";
				chunks.get(1).get(5).grid[1] = "{|                ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                |}";
				chunks.get(1).get(5).grid[2] = "{|      " + buypositions[0] + "         ";chunks.get(1).get(6).grid[2] = "    Bandages      ";chunks.get(1).get(7).grid[2] = "                |}";
				chunks.get(1).get(5).grid[3] = "{|                ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                |}";
				chunks.get(1).get(5).grid[4] = "{|                ";chunks.get(1).get(6).grid[4] = "                  ";chunks.get(1).get(7).grid[4] = "                |}";
				chunks.get(2).get(5).grid[0] = "{|                ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                |}";
				chunks.get(2).get(5).grid[1] = "{|      " + buypositions[1] + "         ";chunks.get(2).get(6).grid[1] = "    PokeBalls     ";chunks.get(2).get(7).grid[1] = "                |}";
				chunks.get(2).get(5).grid[2] = "{|                ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                |}";
				chunks.get(2).get(5).grid[3] = "{|                ";chunks.get(2).get(6).grid[3] = "                  ";chunks.get(2).get(7).grid[3] = "                |}";
				chunks.get(2).get(5).grid[4] = "{|                ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                |}";
				chunks.get(3).get(5).grid[0] = "{|      " + buypositions[2] + "         ";chunks.get(3).get(6).grid[0] = "    Repellent     ";chunks.get(3).get(7).grid[0] = "                |}";
				chunks.get(3).get(5).grid[1] = "{|                ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                |}";
				chunks.get(3).get(5).grid[2] = "{|                ";chunks.get(3).get(6).grid[2] = "                  ";chunks.get(3).get(7).grid[2] = "                |}";
				chunks.get(3).get(5).grid[3] = "{|                ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                |}";
				chunks.get(3).get(5).grid[4] = "{|      " + buypositions[3] + "         ";chunks.get(3).get(6).grid[4] = "    Revives       ";chunks.get(3).get(7).grid[4] = "                |}";
				chunks.get(4).get(5).grid[0] = "{|                ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                |}";
				chunks.get(4).get(5).grid[1] = "{|      " + buypositions[4] + "         ";chunks.get(4).get(6).grid[1] = "    Exit          ";chunks.get(4).get(7).grid[1] = "                |}";
				chunks.get(4).get(5).grid[2] = "  ~~~~~~~~~~~~~~~~";chunks.get(4).get(6).grid[2] = "~~~~~~~~~~~~~~~~~~";chunks.get(4).get(7).grid[2] = "~~~~~~~~~~~~~~~~  ";

			
				while(true) {
					chunks.get(1).get(5).grid[2] = "{|      " + buypositions[0] + "         ";chunks.get(1).get(6).grid[2] = "    Bandages      ";chunks.get(1).get(7).grid[2] = "                |}";
					chunks.get(2).get(5).grid[1] = "{|      " + buypositions[1] + "         ";chunks.get(2).get(6).grid[1] = "    PokeBalls     ";chunks.get(2).get(7).grid[1] = "                |}";
					chunks.get(3).get(5).grid[0] = "{|      " + buypositions[2] + "         ";chunks.get(3).get(6).grid[0] = "    Repellent     ";chunks.get(3).get(7).grid[0] = "                |}";
					chunks.get(3).get(5).grid[4] = "{|      " + buypositions[3] + "         ";chunks.get(3).get(6).grid[4] = "    Revives       ";chunks.get(3).get(7).grid[4] = "                |}";
					chunks.get(4).get(5).grid[1] = "{|      " + buypositions[4] + "         ";chunks.get(4).get(6).grid[1] = "    Exit          ";chunks.get(4).get(7).grid[1] = "                |}";
					printSimpleMap();
					input = scan.next();
					if(input.equals("w") && buypos != 0) {
						System.out.println("here1");
						buypositions[buypos] = ' ';
						buypos -= 1;
						buypositions[buypos] = '>';
						System.out.println(buypos);
					} else if(input.equals("s") && buypos != 4) {
						System.out.println("here2");
						buypositions[buypos] = ' ';
						buypos += 1; 
						buypositions[buypos] = '>';
						System.out.println(buypos);
					} else if(input.equals("c")) {
						if(buypos == 4) {
							break;
						}
					} 
				}
				input = "";
			} else if(pos == 2){
				System.out.println("here");
				return;
			}
		
			
		}  else {
			chunks.get(0).get(5).grid[0] = "                  ";chunks.get(0).get(6).grid[0] = "                  ";chunks.get(0).get(7).grid[0] = "                  ";
			chunks.get(0).get(5).grid[1] = "                  ";chunks.get(0).get(6).grid[1] = "                  ";chunks.get(0).get(7).grid[1] = "                  ";
			chunks.get(0).get(5).grid[2] = "                  ";chunks.get(0).get(6).grid[2] = "                  ";chunks.get(0).get(7).grid[2] = "                  ";
			chunks.get(0).get(5).grid[3] = "                  ";chunks.get(0).get(6).grid[3] = "                  ";chunks.get(0).get(7).grid[3] = "                  ";
			chunks.get(0).get(5).grid[4] = "                  ";chunks.get(0).get(6).grid[4] = "                  ";chunks.get(0).get(7).grid[4] = "                  ";
			chunks.get(1).get(5).grid[0] = "                  ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                  ";
			chunks.get(1).get(5).grid[1] = "                  ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                  ";
			chunks.get(1).get(5).grid[2] = "                  ";chunks.get(1).get(6).grid[2] = "                  ";chunks.get(1).get(7).grid[2] = "                  ";
			chunks.get(1).get(5).grid[3] = "                  ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                  ";
			chunks.get(1).get(5).grid[4] = "                  ";chunks.get(1).get(6).grid[4] = "                  ";chunks.get(1).get(7).grid[4] = "                  ";
			chunks.get(2).get(5).grid[0] = "                  ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                  ";
			chunks.get(2).get(5).grid[1] = "                  ";chunks.get(2).get(6).grid[1] = "                  ";chunks.get(2).get(7).grid[1] = "                  ";
			chunks.get(2).get(5).grid[2] = "                  ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                  ";
			chunks.get(2).get(5).grid[3] = "                  ";chunks.get(2).get(6).grid[3] = "                  ";chunks.get(2).get(7).grid[3] = "                  ";
			chunks.get(2).get(5).grid[4] = "                  ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                  ";
			chunks.get(3).get(5).grid[0] = "                  ";chunks.get(3).get(6).grid[0] = "                  ";chunks.get(3).get(7).grid[0] = "                  ";
			chunks.get(3).get(5).grid[1] = "                  ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                  ";
			chunks.get(3).get(5).grid[2] = "                  ";chunks.get(3).get(6).grid[2] = "                  ";chunks.get(3).get(7).grid[2] = "                  ";
			chunks.get(3).get(5).grid[3] = "                  ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                  ";
			chunks.get(3).get(5).grid[4] = "                  ";chunks.get(3).get(6).grid[4] = "                  ";chunks.get(3).get(7).grid[4] = "                  ";
			chunks.get(4).get(5).grid[0] = "                  ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                  ";
			chunks.get(4).get(5).grid[1] = "                  ";chunks.get(4).get(6).grid[1] = "                  ";chunks.get(4).get(7).grid[1] = "                  ";
			chunks.get(4).get(5).grid[2] = "                  ";chunks.get(4).get(6).grid[2] = "                  ";chunks.get(4).get(7).grid[2] = "                  ";
			input = "";
			positions[pos] = '>';
			mapDialogue("Welcome to Route 1's PokeStop! What would you like to do today?                                               " + positions[0] + " Heal Pokemon     " + positions[1] + " Purchase Items     " + positions[2] + " Exit                ");
			printSimpleMap();
			input = scan.next();
		}
		if(input.equals("a") && pos != 0) {
			positions[pos] = ' '; pos--; positions[pos] = '>';
		} else if(input.equals("d") && pos != 2) {
			positions[pos] = ' '; pos++; positions[pos] = '>';
		}
			
	} 
	
}

	
public void loadChunks(String mapType) {
		if(mapType.equals("World1")){
			chunks.get(0).get(0).grid[0]="|-----------------";chunks.get(0).get(1).grid[0]="-----------------|";chunks.get(0).get(2).grid[0] ="////|-----________";chunks.get(0).get(3).grid[0] ="-----________-----";chunks.get(0).get(4).grid[0] ="________----------";chunks.get(0).get(5).grid[0] ="------------------";chunks.get(0).get(6).grid[0] ="------------------";chunks.get(0).get(7).grid[0] ="-------|\\\\\\|------";chunks.get(0).get(8).grid[0] ="-----------------|";chunks.get(0).get(9).grid[0] ="                  ";chunks.get(0).get(10).grid[0] ="                  ";
			chunks.get(0).get(0).grid[1]="|-----------------";chunks.get(0).get(1).grid[1]="-----------------|";chunks.get(0).get(2).grid[1] ="////    _/ ______ ";chunks.get(0).get(3).grid[1] ="\\___/\\======/\\___/";chunks.get(0).get(4).grid[1] =" ______ \\_        ";chunks.get(0).get(5).grid[1] ="                  ";chunks.get(0).get(6).grid[1] ="      _____       ";chunks.get(0).get(7).grid[1] ="        \\\\\\|------";chunks.get(0).get(8).grid[1] ="-----------------|";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
			chunks.get(0).get(0).grid[2]="|-----------------";chunks.get(0).get(1).grid[2]="-----------------|";chunks.get(0).get(2).grid[2] ="/// ___/__/     |\\";chunks.get(0).get(3).grid[2] ="______\\ [] /______";chunks.get(0).get(4).grid[2] ="/|     \\__\\___    ";chunks.get(0).get(5).grid[2] ="                __";chunks.get(0).get(6).grid[2] ="_____/_( )_\\______";chunks.get(0).get(7).grid[2] ="__       \\\\|------";chunks.get(0).get(8).grid[2] ="-----------------|";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="                  ";
			chunks.get(0).get(0).grid[3]="|-----------------";chunks.get(0).get(1).grid[3]="-----------------|";chunks.get(0).get(2).grid[3] ="// /\\____/\\ ' ' | ";chunks.get(0).get(3).grid[3] ="|      \\__/      |";chunks.get(0).get(4).grid[3] =" |' '  /\\____/\\   ";chunks.get(0).get(5).grid[3] ="               (__";chunks.get(0).get(6).grid[3] ="__________________";chunks.get(0).get(7).grid[3] ="__)       \\|------";chunks.get(0).get(8).grid[3] ="-----------------|";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                  ";
			chunks.get(0).get(0).grid[4]="|-----------------";chunks.get(0).get(1).grid[4]="-----------------|";chunks.get(0).get(2).grid[4] ="/ /_/====\\_\\ ' '| ";chunks.get(0).get(3).grid[4] ="|________________|";chunks.get(0).get(4).grid[4] =" | ' '/_/====\\_\\  ";chunks.get(0).get(5).grid[4] ="              __[_";chunks.get(0).get(6).grid[4] ="____|_______|_____";chunks.get(0).get(7).grid[4] ="_]__        ______";chunks.get(0).get(8).grid[4] ="__________________";chunks.get(0).get(9).grid[4] ="                  ";chunks.get(0).get(10).grid[4] ="                  ";
			
			chunks.get(1).get(0).grid[0]="  ________________";chunks.get(1).get(1).grid[0]="_________________ ";chunks.get(1).get(2).grid[0] ="  | |  ' | | '  | ";chunks.get(1).get(3).grid[0] ="|=====/----\\=====|";chunks.get(1).get(4).grid[0] =" | '  | |  ' | |  ";chunks.get(1).get(5).grid[0] =",    ,    ,  (____";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="____) ,  , |******";chunks.get(1).get(8).grid[0] ="*****************|";chunks.get(1).get(9).grid[0] ="                  ";chunks.get(1).get(10).grid[0] ="                  ";
			chunks.get(1).get(0).grid[1]=" |****************";chunks.get(1).get(1).grid[1]="*****************|";chunks.get(1).get(2).grid[1] ="  | | ' '| |' ' | ";chunks.get(1).get(3).grid[1] ="|    ________    |";chunks.get(1).get(4).grid[1] =" |' ' | | ' '| |  ";chunks.get(1).get(5).grid[1] ="    ,   ,    ||||[";chunks.get(1).get(6).grid[1] ="POKE] ______ [STOP";chunks.get(1).get(7).grid[1] ="]||||   ,  |******";chunks.get(1).get(8).grid[1] ="*****************|";chunks.get(1).get(9).grid[1] ="                  ";chunks.get(1).get(10).grid[1] ="                  ";
			chunks.get(1).get(0).grid[2]=" |****************";chunks.get(1).get(1).grid[2]="*****************|";chunks.get(1).get(2).grid[2] ="  | |  ' | |____| ";chunks.get(1).get(3).grid[2] ="| . |\\      /| . |";chunks.get(1).get(4).grid[2] =" |____| |  ' | |  ";chunks.get(1).get(5).grid[2] =" ,    ,    , ||||/";chunks.get(1).get(6).grid[2] =" ' '//  ||  \\\\' ' ";chunks.get(1).get(7).grid[2] ="\\||||  , , |******";chunks.get(1).get(8).grid[2] ="*****************|";chunks.get(1).get(9).grid[2] ="                  ";chunks.get(1).get(10).grid[2] ="                  ";
			chunks.get(1).get(0).grid[3]=" |****************";chunks.get(1).get(1).grid[3]="*****************|";chunks.get(1).get(2).grid[3] ="  |\\|____|/|____| ";chunks.get(1).get(3).grid[3] ="| . | \\ () / | . |";chunks.get(1).get(4).grid[3] =" |____|\\|____|/|  ";chunks.get(1).get(5).grid[3] =" ,      ,    |||||";chunks.get(1).get(6).grid[3] ="' ' ||  ||  || ' '";chunks.get(1).get(7).grid[3] ="||||| ,    |******";chunks.get(1).get(8).grid[3] ="*****************|";chunks.get(1).get(9).grid[3] ="                  ";chunks.get(1).get(10).grid[3] ="                  ";
			chunks.get(1).get(0).grid[4]=" |****************";chunks.get(1).get(1).grid[4]="*****************|";chunks.get(1).get(2).grid[4] ="   \\|____|/      \\";chunks.get(1).get(3).grid[4] ="|___|_/____\\_|___|";chunks.get(1).get(4).grid[4] ="/      \\|____|/   ";chunks.get(1).get(5).grid[4] ="   ,  ,   ,  [||||";chunks.get(1).get(6).grid[4] ="____|| (==) ||____";chunks.get(1).get(7).grid[4] ="||||]    , |******";chunks.get(1).get(8).grid[4] ="*****************|";chunks.get(1).get(9).grid[4] ="                  ";chunks.get(1).get(10).grid[4] ="                  ";
		
			chunks.get(2).get(0).grid[0]=" |****************";chunks.get(2).get(1).grid[0]="*****************|";chunks.get(2).get(2).grid[0] ="  ,   ,      ,    ";chunks.get(2).get(3).grid[0] ="(                )";chunks.get(2).get(4).grid[0] ="  ,   ,      ,   ,";chunks.get(2).get(5).grid[0] ="  ,   ,      ,    ";chunks.get(2).get(6).grid[0] ="    \\|__||__|/  __";chunks.get(2).get(7).grid[0] ="___________|******";chunks.get(2).get(8).grid[0] ="*****************|";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="                  ";
			chunks.get(2).get(0).grid[1]=" |****************";chunks.get(2).get(1).grid[1]="*****************|";chunks.get(2).get(2).grid[1] ="    ,    ,        ";chunks.get(2).get(3).grid[1] =" \\              / ";chunks.get(2).get(4).grid[1] ="    ,    ,        ";chunks.get(2).get(5).grid[1] ="    ,    ,        ";chunks.get(2).get(6).grid[1] ="  |            ||*";chunks.get(2).get(7).grid[1] ="******************";chunks.get(2).get(8).grid[1] ="*****************|";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="                  ";
			chunks.get(2).get(0).grid[2]=" |****************";chunks.get(2).get(1).grid[2]="*****************|";chunks.get(2).get(2).grid[2] =" ,        ,    ,  ";chunks.get(2).get(3).grid[2] ="  |            |  ";chunks.get(2).get(4).grid[2] =" ,        ,    ,  ";chunks.get(2).get(5).grid[2] =" ,        ,    ,  ";chunks.get(2).get(6).grid[2] ="  |            ||*";chunks.get(2).get(7).grid[2] ="******************";chunks.get(2).get(8).grid[2] ="*****************|";chunks.get(2).get(9).grid[2]  ="                  ";chunks.get(2).get(10).grid[2] ="                  ";
			chunks.get(2).get(0).grid[3]=" |****************";chunks.get(2).get(1).grid[3]="*****************|";chunks.get(2).get(2).grid[3] ="   ,  ,     ,     ";chunks.get(2).get(3).grid[3] ="  |            |  ";chunks.get(2).get(4).grid[3] ="   ,  ,     ,     ";chunks.get(2).get(5).grid[3] ="   ,  ,     ,     ";chunks.get(2).get(6).grid[3] ="  |            ||*";chunks.get(2).get(7).grid[3] ="******************";chunks.get(2).get(8).grid[3] ="*****************|";chunks.get(2).get(9).grid[3]  ="                  ";chunks.get(2).get(10).grid[3] ="                  ";
			chunks.get(2).get(0).grid[4]=" |****************";chunks.get(2).get(1).grid[4]="*****************|";chunks.get(2).get(2).grid[4] =" ,      ,      ,  ";chunks.get(2).get(3).grid[4] ="  |            |  ";chunks.get(2).get(4).grid[4] =" ,      ,      ,  ";chunks.get(2).get(5).grid[4] =" ,      ,      ,  ";chunks.get(2).get(6).grid[4] ="  |            ||*";chunks.get(2).get(7).grid[4] ="******************";chunks.get(2).get(8).grid[4] ="*****************|";chunks.get(2).get(9).grid[4]  ="                  ";chunks.get(2).get(10).grid[4] ="                  ";
			
			chunks.get(3).get(0).grid[0]=" |****************";chunks.get(3).get(1).grid[0]="*****************|";chunks.get(3).get(2).grid[0] ="__________________";chunks.get(3).get(3).grid[0] ="_/              \\_";chunks.get(3).get(4).grid[0] ="__________________";chunks.get(3).get(5).grid[0] ="__________________";chunks.get(3).get(6).grid[0] ="_/              \\\\";chunks.get(3).get(7).grid[0] ="******************";chunks.get(3).get(8).grid[0] ="*****************|";chunks.get(3).get(9).grid[0]="                  ";chunks.get(3).get(10).grid[0] ="                  ";
			chunks.get(3).get(0).grid[1]=" |****************";chunks.get(3).get(1).grid[1]="***************** ";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                 |";chunks.get(3).get(7).grid[1] ="|*****************";chunks.get(3).get(8).grid[1] ="*****************|";chunks.get(3).get(9).grid[1]  ="                  ";chunks.get(3).get(10).grid[1] ="                  ";
			chunks.get(3).get(0).grid[2]=" |****************";chunks.get(3).get(1).grid[2]="***************** ";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                 |";chunks.get(3).get(7).grid[2] ="|*****************";chunks.get(3).get(8).grid[2] ="*****************|";chunks.get(3).get(9).grid[2]  ="                  ";chunks.get(3).get(10).grid[2] ="                  ";
			chunks.get(3).get(0).grid[3]=" |****************";chunks.get(3).get(1).grid[3]="***************** ";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                 |";chunks.get(3).get(7).grid[3] ="|*****************";chunks.get(3).get(8).grid[3] ="*****************|";chunks.get(3).get(9).grid[3]  ="                  ";chunks.get(3).get(10).grid[3] ="                  ";
			chunks.get(3).get(0).grid[4]=" |****************";chunks.get(3).get(1).grid[4]="***************** ";chunks.get(3).get(2).grid[4] ="__________________";chunks.get(3).get(3).grid[4] ="__________________";chunks.get(3).get(4).grid[4] ="__________________";chunks.get(3).get(5).grid[4] ="__________________";chunks.get(3).get(6).grid[4] ="                 |";chunks.get(3).get(7).grid[4] ="|*****************";chunks.get(3).get(8).grid[4] ="*****************|";chunks.get(3).get(9).grid[4]  ="                  ";chunks.get(3).get(10).grid[4] ="                  ";
		
			chunks.get(4).get(0).grid[0]="|-----------------";chunks.get(4).get(1).grid[0]="-----------------|";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="\\                \\";chunks.get(4).get(7).grid[0] ="|*****************";chunks.get(4).get(8).grid[0] ="*****************|";chunks.get(4).get(9).grid[0]="      -*=         ";chunks.get(4).get(10).grid[0] ="                  ";
			chunks.get(4).get(0).grid[1]="    -*=           ";chunks.get(4).get(1).grid[1]="           __     ";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="|                 ";chunks.get(4).get(7).grid[1] ="******************";chunks.get(4).get(8).grid[1] ="*****************|";chunks.get(4).get(9).grid[1] ="   ____|______    ";chunks.get(4).get(10).grid[1] ="                  ";
			chunks.get(4).get(0).grid[2]="  ___|____________";chunks.get(4).get(1).grid[2]="__________|-||__  ";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="|                 ";chunks.get(4).get(7).grid[2] ="******************";chunks.get(4).get(8).grid[2] ="*****************|";chunks.get(4).get(9).grid[2] ="  /...........\\\\__";chunks.get(4).get(10).grid[2] ="______________    ";
			chunks.get(4).get(0).grid[3]=" /________________";chunks.get(4).get(1).grid[3]="__________|_||_/| ";chunks.get(4).get(2).grid[3] ="     ********     ";chunks.get(4).get(3).grid[3] ="     ********     ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="|                 ";chunks.get(4).get(7).grid[3] ="******************";chunks.get(4).get(8).grid[3] ="*****************|";chunks.get(4).get(9).grid[3]  =" /.............\\\\\\";chunks.get(4).get(10).grid[3] ="\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\  ";
			chunks.get(4).get(0).grid[4]="|                 ";chunks.get(4).get(1).grid[4]="    ________   || ";chunks.get(4).get(2).grid[4] ="    **********    ";chunks.get(4).get(3).grid[4] ="    **********    ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="  ________________";chunks.get(4).get(6).grid[4] ="\\_________________";chunks.get(4).get(7).grid[4] ="******************";chunks.get(4).get(8).grid[4] ="*****************|";chunks.get(4).get(9).grid[4]="/_______________\\\\";chunks.get(4).get(10).grid[4] ="________________| ";
			
			chunks.get(5).get(0).grid[0]="|=================";chunks.get(5).get(1).grid[0]="==./       /.==|| ";chunks.get(5).get(2).grid[0] ="    **********    ";chunks.get(5).get(3).grid[0] ="    **********    ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] =" |****************";chunks.get(5).get(6).grid[0] ="******************";chunks.get(5).get(7).grid[0] ="******************";chunks.get(5).get(8).grid[0] ="*****************|";chunks.get(5).get(9).grid[0]  ="|~~~~~~~~~~~~~~~|/";chunks.get(5).get(10).grid[0] ="|~~~~~~~~~~~~~~|| ";
			chunks.get(5).get(0).grid[1]="| []:::::::::::[] ";chunks.get(5).get(1).grid[1]=" ./  POKE /.   || ";chunks.get(5).get(2).grid[1] ="     ********     ";chunks.get(5).get(3).grid[1] ="     ********     ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] =" |****************";chunks.get(5).get(6).grid[1] ="******************";chunks.get(5).get(7).grid[1] ="******************";chunks.get(5).get(8).grid[1] ="*****************|";chunks.get(5).get(9).grid[1]  ="| __   ___   __ | ";chunks.get(5).get(10).grid[1] ="|   [ ' '  ]   || ";
			chunks.get(5).get(0).grid[2]="| [] ____ ____ [] ";chunks.get(5).get(1).grid[2]=" .\\  LAB  \\.   || ";chunks.get(5).get(2).grid[2] ="       |  |       ";chunks.get(5).get(3).grid[2] ="       |  |       ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] =" |****************";chunks.get(5).get(6).grid[2] ="******************";chunks.get(5).get(7).grid[2] ="******************";chunks.get(5).get(8).grid[2] ="*****************|";chunks.get(5).get(9).grid[2]  ="|[' ] |   | [' ]| ";chunks.get(5).get(10).grid[2] ="|   [__'_'_]   || ";
			chunks.get(5).get(0).grid[3]="| []|  \\ | /  |[] ";chunks.get(5).get(1).grid[3]="  .\\_______\\.  || ";chunks.get(5).get(2).grid[3] ="       |  |       ";chunks.get(5).get(3).grid[3] ="       |  |       ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] =" |****************";chunks.get(5).get(6).grid[3] ="******************";chunks.get(5).get(7).grid[3] ="******************";chunks.get(5).get(8).grid[3] ="*****************|";chunks.get(5).get(9).grid[3]  ="|[_'] |.  | [_']| ";chunks.get(5).get(10).grid[3] ="|______________|| ";
			chunks.get(5).get(0).grid[4]="| []|   {:}   |[] ";chunks.get(5).get(1).grid[4]="               || ";chunks.get(5).get(2).grid[4] ="       |  |       ";chunks.get(5).get(3).grid[4] ="       |  |       ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] =" |****************";chunks.get(5).get(6).grid[4] ="******************";chunks.get(5).get(7).grid[4] ="******************";chunks.get(5).get(8).grid[4] ="*****************|";chunks.get(5).get(9).grid[4]  ="|_____|___|_____|/";chunks.get(5).get(10).grid[4] ="              |   ";
			
			chunks.get(6).get(0).grid[0]="|_[]|__/_|_\\__|[]_";chunks.get(6).get(1).grid[0]="_______________|| ";chunks.get(6).get(2).grid[0] ="      /    \\      ";chunks.get(6).get(3).grid[0] ="      /    \\      ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] =" |****************";chunks.get(6).get(6).grid[0] ="******************";chunks.get(6).get(7).grid[0] ="******************";chunks.get(6).get(8).grid[0] ="*****************|";chunks.get(6).get(9).grid[0]=" |                ";chunks.get(6).get(10).grid[0] ="              |   ";
			chunks.get(6).get(0).grid[1]=" \\              / ";chunks.get(6).get(1).grid[1]="                 |";chunks.get(6).get(2).grid[1] ="  ,   ,  ,   ,   ,";chunks.get(6).get(3).grid[1] ="  ,   ,  ,   ,   ,";chunks.get(6).get(4).grid[1] ="  ,   ,  ,   ,   ,";chunks.get(6).get(5).grid[1] =" |***************_";chunks.get(6).get(6).grid[1] ="__________________";chunks.get(6).get(7).grid[1] ="__________________";chunks.get(6).get(8).grid[1] ="_________________|";chunks.get(6).get(9).grid[1] =" |                ";chunks.get(6).get(10).grid[1] ="              |   ";
			chunks.get(6).get(0).grid[2]="  |            |  ";chunks.get(6).get(1).grid[2]=" {*} (*) (*) (*) |";chunks.get(6).get(2).grid[2] =",   ,    ,  ,  ,  ";chunks.get(6).get(3).grid[2] =",   ,    ,  ,  ,  ";chunks.get(6).get(4).grid[2] =",   ,    ,  ,  ,  ";chunks.get(6).get(5).grid[2] =" |**************| ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2]  =" |                ";chunks.get(6).get(10).grid[2] ="              |   ";
			chunks.get(6).get(0).grid[3]="  |            |  ";chunks.get(6).get(1).grid[3]="  |   |   |   |  |";chunks.get(6).get(2).grid[3] =" ,    ,   ,  , ,  ";chunks.get(6).get(3).grid[3] =" ,    ,   ,  , ,  ";chunks.get(6).get(4).grid[3] =" ,    ,   ,  , ,  ";chunks.get(6).get(5).grid[3] =" |**************| ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3]  =" |                ";chunks.get(6).get(10).grid[3] ="              |   ";
			chunks.get(6).get(0).grid[4]="  |            |  ";chunks.get(6).get(1).grid[4]="                 |";chunks.get(6).get(2).grid[4] ="------------------";chunks.get(6).get(3).grid[4] ="------------------";chunks.get(6).get(4).grid[4] ="------------------";chunks.get(6).get(5).grid[4] ="-|**************| ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4]  =" |               _";chunks.get(6).get(10).grid[4] ="_____________/    ";
			
			chunks.get(7).get(0).grid[0]="  |            |__";chunks.get(7).get(1).grid[0]="__________________";chunks.get(7).get(2).grid[0] ="__________________";chunks.get(7).get(3).grid[0] ="__________________";chunks.get(7).get(4).grid[0]="__________________";chunks.get(7).get(5).grid[0] ="_/              \\_";chunks.get(7).get(6).grid[0] ="__________________";chunks.get(7).get(7).grid[0]="__________________";chunks.get(7).get(8).grid[0] ="__________________";chunks.get(7).get(9).grid[0] ="_|              |_";chunks.get(7).get(10).grid[0] ="__________________";
			chunks.get(7).get(0).grid[1]="  |               ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1]="                  ";chunks.get(7).get(5).grid[1] ="                  ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1]="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
			chunks.get(7).get(0).grid[2]="  |               ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2]="                  ";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2]="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
			chunks.get(7).get(0).grid[3]="  |               ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3]="                  ";chunks.get(7).get(5).grid[3] ="                  ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3]="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
			chunks.get(7).get(0).grid[4]="  |_______________";chunks.get(7).get(1).grid[4]="__________________";chunks.get(7).get(2).grid[4] ="__________________";chunks.get(7).get(3).grid[4] ="__________________";chunks.get(7).get(4).grid[4]="__________________";chunks.get(7).get(5).grid[4] ="__________________";chunks.get(7).get(6).grid[4] ="__________________";chunks.get(7).get(7).grid[4]="__________________";chunks.get(7).get(8).grid[4] ="__________________";chunks.get(7).get(9).grid[4] ="__________________";chunks.get(7).get(10).grid[4] ="__________________";
		}
		if(mapType.equals("Gym1")) {
			loadBigMapChunks(mapType);
		}
		if(mapType.equals("PokeStop1")) {
			chunks.get(7).get(5).enterable = 1;
			chunks.get(0).get(0).grid[0]=" *     *     *    ";chunks.get(0).get(1).grid[0]=" ________________ ";chunks.get(0).get(2).grid[0] =" ________________ ";chunks.get(0).get(3).grid[0] ="|\\       \\   *    ";chunks.get(0).get(4).grid[0] =" *     *     *    ";chunks.get(0).get(5).grid[0] =  " *     *     *    ";chunks.get(0).get(6).grid[0] =" *     *     *    ";chunks.get(0).get(7).grid[0] =" *     */       /|";chunks.get(0).get(8).grid[0] =" *     *     *    ";chunks.get(0).get(9).grid[0]  =" *     *     *    ";chunks.get(0).get(10).grid[0] =" *     *     *    ";
			chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="|________________|";chunks.get(0).get(2).grid[1] ="|________________|";chunks.get(0).get(3).grid[1] ="|\\\\       \\       ";chunks.get(0).get(4).grid[1] ="                  ";chunks.get(0).get(5).grid[1] = "       ____       ";chunks.get(0).get(6).grid[1] ="                  ";chunks.get(0).get(7).grid[1] ="       /       //|";chunks.get(0).get(8).grid[1] ="                  ";chunks.get(0).get(9).grid[1] ="                  ";chunks.get(0).get(10).grid[1] ="                  ";
			chunks.get(0).get(0).grid[2]="    *     *     * ";chunks.get(0).get(1).grid[2]="|________________|";chunks.get(0).get(2).grid[2] ="|________________|";chunks.get(0).get(3).grid[2] ="||\\\\       \\    * ";chunks.get(0).get(4).grid[2] ="          *     * ";chunks.get(0).get(5).grid[2] = "    * \\____/    * ";chunks.get(0).get(6).grid[2] ="    *     *     * ";chunks.get(0).get(7).grid[2] ="    * /       //||";chunks.get(0).get(8).grid[2] ="    *     *     * ";chunks.get(0).get(9).grid[2] ="    *     *     * ";chunks.get(0).get(10).grid[2] ="    *     *     * ";
			chunks.get(0).get(0).grid[3]="                  ";chunks.get(0).get(1).grid[3]="|________________|";chunks.get(0).get(2).grid[3] ="|________________|";chunks.get(0).get(3).grid[3] ="|||\\\\       \\     ";chunks.get(0).get(4).grid[3] =" __________       ";chunks.get(0).get(5).grid[3] = "     /____  \\     ";chunks.get(0).get(6).grid[3] ="                  ";chunks.get(0).get(7).grid[3] ="     /       //|||";chunks.get(0).get(8).grid[3] ="                  ";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                  ";
			chunks.get(0).get(0).grid[4]="__________________";chunks.get(0).get(1).grid[4]="|________________|";chunks.get(0).get(2).grid[4] ="|________________|";chunks.get(0).get(3).grid[4] ="||||\\\\       \\____";chunks.get(0).get(4).grid[4] ="/==========\\______";chunks.get(0).get(5).grid[4] ="___ ||    \\/||____";chunks.get(0).get(6).grid[4] ="__________________";chunks.get(0).get(7).grid[4] ="____/       //||||";chunks.get(0).get(8).grid[4] ="__________________";chunks.get(0).get(9).grid[4] ="__________________";chunks.get(0).get(10).grid[4] ="__________________";
			chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="|________________|";chunks.get(1).get(2).grid[0] ="|________________|";chunks.get(1).get(3).grid[0] =" \\|||\\\\       \\  /";chunks.get(1).get(4).grid[0] ="|[   ][   ]|\\     ";chunks.get(1).get(5).grid[0]="    || |  | ||    ";chunks.get(1).get(6).grid[0] ="                  ";chunks.get(1).get(7).grid[0] ="   /       //|||/ ";chunks.get(1).get(8).grid[0] ="                  ";chunks.get(1).get(9).grid[0]="                  ";chunks.get(1).get(10).grid[0] ="                  ";
			chunks.get(1).get(0).grid[1]="                  ";chunks.get(1).get(1).grid[1]="                  ";chunks.get(1).get(2).grid[1] ="                  ";chunks.get(1).get(3).grid[1] ="  \\|||\\\\       \\ |";chunks.get(1).get(4).grid[1] ="|[   ][   ]||     ";chunks.get(1).get(5).grid[1] ="   (_\\ .__. /_)   ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="  /       //|||/  ";chunks.get(1).get(8).grid[1] ="                  ";chunks.get(1).get(9).grid[1]="                  ";chunks.get(1).get(10).grid[1] ="                  ";
			chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="                  ";chunks.get(1).get(2).grid[2] ="                  ";chunks.get(1).get(3).grid[2] ="   \\|||\\\\       \\|";chunks.get(1).get(4).grid[2] ="|[   ][   ]||     ";chunks.get(1).get(5).grid[2] ="     _|____|_     ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] =" /       //|||/   ";chunks.get(1).get(8).grid[2] ="                  ";chunks.get(1).get(9).grid[2]="                  ";chunks.get(1).get(10).grid[2] ="                  ";
			chunks.get(1).get(0).grid[3]="                  ";chunks.get(1).get(1).grid[3]="                  ";chunks.get(1).get(2).grid[3] ="                  ";chunks.get(1).get(3).grid[3] ="    \\|||\\\\       \\";chunks.get(1).get(4).grid[3] ="|__________||_____";chunks.get(1).get(5).grid[3] ="____/////////\\____";chunks.get(1).get(6).grid[3] ="__________________";chunks.get(1).get(7).grid[3] ="/       //|||/    ";chunks.get(1).get(8).grid[3] ="                  ";chunks.get(1).get(9).grid[3]="                  ";chunks.get(1).get(10).grid[3] ="                  ";
			chunks.get(1).get(0).grid[4]="                  ";chunks.get(1).get(1).grid[4]="                  ";chunks.get(1).get(2).grid[4] ="                  ";chunks.get(1).get(3).grid[4] ="     \\|||\\\\       ";chunks.get(1).get(4).grid[4] ="                  ";chunks.get(1).get(5).grid[4] ="                  ";chunks.get(1).get(6).grid[4] ="                  ";chunks.get(1).get(7).grid[4] ="       //|||/     ";chunks.get(1).get(8).grid[4] ="                  ";chunks.get(1).get(9).grid[4] ="                  ";chunks.get(1).get(10).grid[4] ="                  ";
		
			chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="                  ";chunks.get(2).get(2).grid[0] ="                  ";chunks.get(2).get(3).grid[0] ="      \\|||\\\\      ";chunks.get(2).get(4).grid[0] ="                  ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="                  ";chunks.get(2).get(7).grid[0] ="      //|||/      ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="                  ";
			chunks.get(2).get(0).grid[1]="      _______     ";chunks.get(2).get(1).grid[1]="      _______     ";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="       \\|||\\\\_____";chunks.get(2).get(4).grid[1] ="__________________";chunks.get(2).get(5).grid[1] ="__________________";chunks.get(2).get(6).grid[1] ="__________________";chunks.get(2).get(7).grid[1] ="_____//|||/       ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="                  ";
			chunks.get(2).get(0).grid[2]="     |       |    ";chunks.get(2).get(1).grid[2]="     |       |    ";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="        \\|||||||||";chunks.get(2).get(4).grid[2] ="||||||||||||||||||";chunks.get(2).get(5).grid[2] ="||||||||||||||||||";chunks.get(2).get(6).grid[2] ="||||||||||||||||||";chunks.get(2).get(7).grid[2] ="|||||||||/        ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2]="                  ";chunks.get(2).get(10).grid[2] ="                  ";
			chunks.get(2).get(0).grid[3]="     |  [ ]  |    ";chunks.get(2).get(1).grid[3]="     |  [ ]  |    ";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="         \\||||||||";chunks.get(2).get(4).grid[3] ="||||||||||||||||||";chunks.get(2).get(5).grid[3] ="||||||||||||||||||";chunks.get(2).get(6).grid[3] ="||||||||||||||||||";chunks.get(2).get(7).grid[3] ="||||||||/         ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3]="                  ";chunks.get(2).get(10).grid[3] ="                  ";
			chunks.get(2).get(0).grid[4]="     |_______|    ";chunks.get(2).get(1).grid[4]="     |_______|    ";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="          \\|||||||";chunks.get(2).get(4).grid[4] ="||||||||||||||||||";chunks.get(2).get(5).grid[4] ="||||||||||||||||||";chunks.get(2).get(6).grid[4] ="||||||||||||||||||";chunks.get(2).get(7).grid[4] ="|||||||/          ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4]="                  ";chunks.get(2).get(10).grid[4] ="                  ";
			
			chunks.get(3).get(0).grid[0]="      -------     ";chunks.get(3).get(1).grid[0]="      -------     ";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="                  ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                  ";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0]="                  ";chunks.get(3).get(10).grid[0] ="    _____________ ";
			chunks.get(3).get(0).grid[1]="   _______________";chunks.get(3).get(1).grid[1]="_______________   ";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="                  ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                  ";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1]="                  ";chunks.get(3).get(10).grid[1] ="    \\_\\__________|";
			chunks.get(3).get(0).grid[2]="  |               ";chunks.get(3).get(1).grid[2]="               |  ";chunks.get(3).get(2).grid[2] ="_______           ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="                  ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                  ";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2]="                  ";chunks.get(3).get(10).grid[2] ="    |-|          |";
			chunks.get(3).get(0).grid[3]="  |               ";chunks.get(3).get(1).grid[3]="               | |";chunks.get(3).get(2).grid[3] ="       |          ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="                  ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                  ";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3]="                  ";chunks.get(3).get(10).grid[3] ="    |-|          |";
			chunks.get(3).get(0).grid[4]="  |               ";chunks.get(3).get(1).grid[4]="               | |";chunks.get(3).get(2).grid[4] ="  [ ]  |          ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="                  ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                  ";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4]="                  ";chunks.get(3).get(10).grid[4] ="    |-|          |";
			chunks.get(4).get(0).grid[0]="  |               ";chunks.get(4).get(1).grid[0]="               | |";chunks.get(4).get(2).grid[0] ="_______|          ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="                  ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                  ";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0]="                  ";chunks.get(4).get(10).grid[0] =" __/_/           |";
			chunks.get(4).get(0).grid[1]="  |               ";chunks.get(4).get(1).grid[1]="               |  ";chunks.get(4).get(2).grid[1] ="-------           ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="                  ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                  ";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1]="                  ";chunks.get(4).get(10).grid[1] ="|  |             |";
			chunks.get(4).get(0).grid[2]="  |               ";chunks.get(4).get(1).grid[2]="               |  ";chunks.get(4).get(2).grid[2] ="_______           ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="                  ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                  ";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2]="                  ";chunks.get(4).get(10).grid[2] ="|  |             |";
			chunks.get(4).get(0).grid[3]="  |               ";chunks.get(4).get(1).grid[3]="               | |";chunks.get(4).get(2).grid[3] ="       |          ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="                  ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                  ";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3]="                  ";chunks.get(4).get(10).grid[3] ="|  |             |";
			chunks.get(4).get(0).grid[4]="  |               ";chunks.get(4).get(1).grid[4]="               | |";chunks.get(4).get(2).grid[4] ="  [ ]  |          ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="                  ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                  ";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                  ";chunks.get(4).get(9).grid[4]="                  ";chunks.get(4).get(10).grid[4] ="|__|_____________|";
		
			chunks.get(5).get(0).grid[0]="  |_______________";chunks.get(5).get(1).grid[0]="_______________| |";chunks.get(5).get(2).grid[0] ="       |          ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="                  ";chunks.get(5).get(5).grid[0] ="                  ";chunks.get(5).get(6).grid[0] ="                  ";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0]="                  ";chunks.get(5).get(10).grid[0] ="                  ";
			chunks.get(5).get(0).grid[1]="  ||              ";chunks.get(5).get(1).grid[1]="              ||  ";chunks.get(5).get(2).grid[1] ="-------           ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1]="                  ";chunks.get(5).get(10).grid[1] ="                  ";
			chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="                  ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2]="                  ";chunks.get(5).get(10).grid[2] ="                  ";
			chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3]="                  ";chunks.get(5).get(10).grid[3] ="                  ";
			chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4]="                  ";chunks.get(5).get(10).grid[4] ="                  ";
			
			chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0]="                  ";chunks.get(6).get(10).grid[0] ="                  ";
			chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1]="                  ";chunks.get(6).get(10).grid[1] ="                  ";
			chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="                  ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2]="                  ";chunks.get(6).get(10).grid[2] ="                  ";
			chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3]="                  ";chunks.get(6).get(10).grid[3] ="                  ";
			chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="                  ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4]="                  ";chunks.get(6).get(10).grid[4] ="                  ";
			
			chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0]="                  ";chunks.get(7).get(5).grid[0] ="                  ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0]="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="                  ";chunks.get(7).get(10).grid[0] ="                  ";
			chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1]="                  ";chunks.get(7).get(5).grid[1] =" ~~~~~~~~~~~~~~~~ ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1]="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="                  ";chunks.get(7).get(10).grid[1] ="                  ";
			chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2]="                  ";chunks.get(7).get(5).grid[2] ="|////////////////|";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2]="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="                  ";chunks.get(7).get(10).grid[2] ="                  ";
			chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3]="                  ";chunks.get(7).get(5).grid[3] ="|////////////////|";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3]="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="                  ";chunks.get(7).get(10).grid[3] ="                  ";
			chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4]="                  ";chunks.get(7).get(5).grid[4] ="|////////////////|";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4]="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="                  ";chunks.get(7).get(10).grid[4] ="                  ";
			
		}
		if(mapType.equals("PokeLab1")) {
			chunks.get(7).get(5).enterable = 1;
			chunks.get(7).get(5).enterArea = "World1";
			loadBigMapChunks(mapType);
		}
		if(mapType.equals("House")) {
			chunks.get(0).get(0).grid[0]="             |    ";chunks.get(0).get(1).grid[0]="     ____________ ";chunks.get(0).get(2).grid[0] =" ____________     ";chunks.get(0).get(3).grid[0] =" |           |    ";chunks.get(0).get(4).grid[0] ="               (__";chunks.get(0).get(5).grid[0] ="__________________";chunks.get(0).get(6).grid[0] ="__)               ";chunks.get(0).get(7).grid[0] ="             |    ";chunks.get(0).get(8).grid[0] ="                  ";chunks.get(0).get(9).grid[0] ="       |          ";chunks.get(0).get(10).grid[0] ="  |               ";
			chunks.get(0).get(0).grid[1]="                  ";chunks.get(0).get(1).grid[1]="    |            |";chunks.get(0).get(2).grid[1] ="|            |    ";chunks.get(0).get(3).grid[1] ="                  ";chunks.get(0).get(4).grid[1] ="                | ";chunks.get(0).get(5).grid[1] =" -          -     ";chunks.get(0).get(6).grid[1] ="-|                ";chunks.get(0).get(7).grid[1] ="                  ";chunks.get(0).get(8).grid[1] ="             |    ";chunks.get(0).get(9).grid[1] ="             |    ";chunks.get(0).get(10).grid[1] ="                  ";
			chunks.get(0).get(0).grid[2]="       |          ";chunks.get(0).get(1).grid[2]="    |            |";chunks.get(0).get(2).grid[2] ="|            |    ";chunks.get(0).get(3).grid[2] ="   |              ";chunks.get(0).get(4).grid[2] ="                |-";chunks.get(0).get(5).grid[2] ="                  ";chunks.get(0).get(6).grid[2] =" |                ";chunks.get(0).get(7).grid[2] ="                  ";chunks.get(0).get(8).grid[2] ="       |          ";chunks.get(0).get(9).grid[2] ="                  ";chunks.get(0).get(10).grid[2] ="             |    ";
			chunks.get(0).get(0).grid[3]=" |                ";chunks.get(0).get(1).grid[3]="    |         || |";chunks.get(0).get(2).grid[3] ="| ||         |    ";chunks.get(0).get(3).grid[3] ="                  ";chunks.get(0).get(4).grid[3] ="                | ";chunks.get(0).get(5).grid[3] ="        -       - ";chunks.get(0).get(6).grid[3] =" |                ";chunks.get(0).get(7).grid[3] ="   |              ";chunks.get(0).get(8).grid[3] =" |                ";chunks.get(0).get(9).grid[3] ="                  ";chunks.get(0).get(10).grid[3] ="                  ";
			chunks.get(0).get(0).grid[4]="                  ";chunks.get(0).get(1).grid[4]="    |         || |";chunks.get(0).get(2).grid[4] ="| ||         |    ";chunks.get(0).get(3).grid[4] ="                  ";chunks.get(0).get(4).grid[4] =" _______________|_";chunks.get(0).get(5).grid[4] ="__________________";chunks.get(0).get(6).grid[4] ="_|_______________ ";chunks.get(0).get(7).grid[4] ="                  ";chunks.get(0).get(8).grid[4] ="                  ";chunks.get(0).get(9).grid[4] ="                  ";chunks.get(0).get(10).grid[4] ="                  ";
			chunks.get(1).get(0).grid[0]="                  ";chunks.get(1).get(1).grid[0]="    |         || |";chunks.get(1).get(2).grid[0] ="| ||         |    ";chunks.get(1).get(3).grid[0] ="     |            ";chunks.get(1).get(4).grid[0] ="|______([])______|";chunks.get(1).get(5).grid[0] ="|______([])______|";chunks.get(1).get(6).grid[0] ="|______([])______|";chunks.get(1).get(7).grid[0] ="                  ";chunks.get(1).get(8).grid[0] ="                  ";chunks.get(1).get(9).grid[0] ="                  ";chunks.get(1).get(10).grid[0] ="                  ";
			chunks.get(1).get(0).grid[1]="             |    ";chunks.get(1).get(1).grid[1]="    |         ]] |";chunks.get(1).get(2).grid[1] ="| [[         |    ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="|/////////////////";chunks.get(1).get(5).grid[1] ="//////////////////";chunks.get(1).get(6).grid[1] ="////////         |";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] =" |           |    ";chunks.get(1).get(9).grid[1] ="         |        ";chunks.get(1).get(10).grid[1] ="   |              ";
			chunks.get(1).get(0).grid[2]="                  ";chunks.get(1).get(1).grid[2]="    |____________|";chunks.get(1).get(2).grid[2] ="|____________|    ";chunks.get(1).get(3).grid[2] ="                  ";chunks.get(1).get(4).grid[2] ="|/////////////////";chunks.get(1).get(5).grid[2] ="//////////        ";chunks.get(1).get(6).grid[2] ="                 |";chunks.get(1).get(7).grid[2] ="       |          ";chunks.get(1).get(8).grid[2] ="                  ";chunks.get(1).get(9).grid[2] ="                  ";chunks.get(1).get(10).grid[2] ="       |          ";
			chunks.get(1).get(0).grid[3]="       |          ";chunks.get(1).get(1).grid[3]="    |         .  |";chunks.get(1).get(2).grid[3] ="|  .         |    ";chunks.get(1).get(3).grid[3] =" |           |    ";chunks.get(1).get(4).grid[3] ="|//////////////   ";chunks.get(1).get(5).grid[3] ="                  ";chunks.get(1).get(6).grid[3] ="                 |";chunks.get(1).get(7).grid[3] =" |           |    ";chunks.get(1).get(8).grid[3] ="       |          ";chunks.get(1).get(9).grid[3] ="   |              ";chunks.get(1).get(10).grid[3] =" |                ";
			chunks.get(1).get(0).grid[4]="__________________";chunks.get(1).get(1).grid[4]="    |____________|";chunks.get(1).get(2).grid[4] ="|____________|    ";chunks.get(1).get(3).grid[4] ="__________________";chunks.get(1).get(4).grid[4] ="| ________________";chunks.get(1).get(5).grid[4] ="__________________";chunks.get(1).get(6).grid[4] ="________________ |";chunks.get(1).get(7).grid[4] ="__________________";chunks.get(1).get(8).grid[4] ="__________________";chunks.get(1).get(9).grid[4] ="__________________";chunks.get(1).get(10).grid[4] ="__________________";
			chunks.get(2).get(0).grid[0]="                  ";chunks.get(2).get(1).grid[0]="    [____________]";chunks.get(2).get(2).grid[0] ="[____________]    ";chunks.get(2).get(3).grid[0] ="                  ";chunks.get(2).get(4).grid[0] ="|_|               ";chunks.get(2).get(5).grid[0] ="                  ";chunks.get(2).get(6).grid[0] ="               |_|";chunks.get(2).get(7).grid[0] ="                  ";chunks.get(2).get(8).grid[0] ="                  ";chunks.get(2).get(9).grid[0] ="                  ";chunks.get(2).get(10).grid[0] ="                  ";
			chunks.get(2).get(0).grid[1]="                  ";chunks.get(2).get(1).grid[1]="                  ";chunks.get(2).get(2).grid[1] ="                  ";chunks.get(2).get(3).grid[1] ="                  ";chunks.get(2).get(4).grid[1] =" _________________";chunks.get(2).get(5).grid[1] ="__________________";chunks.get(2).get(6).grid[1] ="_________________ ";chunks.get(2).get(7).grid[1] ="                  ";chunks.get(2).get(8).grid[1] ="                  ";chunks.get(2).get(9).grid[1] ="                  ";chunks.get(2).get(10).grid[1] ="                  ";
			chunks.get(2).get(0).grid[2]="                  ";chunks.get(2).get(1).grid[2]="                  ";chunks.get(2).get(2).grid[2] ="                  ";chunks.get(2).get(3).grid[2] ="                  ";chunks.get(2).get(4).grid[2] ="/                 ";chunks.get(2).get(5).grid[2] ="                  ";chunks.get(2).get(6).grid[2] ="                 \\";chunks.get(2).get(7).grid[2] ="                  ";chunks.get(2).get(8).grid[2] ="                  ";chunks.get(2).get(9).grid[2] ="                  ";chunks.get(2).get(10).grid[2] ="                  ";
			chunks.get(2).get(0).grid[3]="                  ";chunks.get(2).get(1).grid[3]="                  ";chunks.get(2).get(2).grid[3] ="                  ";chunks.get(2).get(3).grid[3] ="                  ";chunks.get(2).get(4).grid[3] ="\\                 ";chunks.get(2).get(5).grid[3] ="                  ";chunks.get(2).get(6).grid[3] ="                 /";chunks.get(2).get(7).grid[3] ="                  ";chunks.get(2).get(8).grid[3] ="                  ";chunks.get(2).get(9).grid[3] ="                  ";chunks.get(2).get(10).grid[3] ="                  ";
			chunks.get(2).get(0).grid[4]="                  ";chunks.get(2).get(1).grid[4]="                  ";chunks.get(2).get(2).grid[4] ="                  ";chunks.get(2).get(3).grid[4] ="                  ";chunks.get(2).get(4).grid[4] ="/                 ";chunks.get(2).get(5).grid[4] ="                  ";chunks.get(2).get(6).grid[4] ="                 \\";chunks.get(2).get(7).grid[4] ="                  ";chunks.get(2).get(8).grid[4] ="                  ";chunks.get(2).get(9).grid[4] ="                  ";chunks.get(2).get(10).grid[4] ="                  ";
			chunks.get(3).get(0).grid[0]="                  ";chunks.get(3).get(1).grid[0]="                  ";chunks.get(3).get(2).grid[0] ="                  ";chunks.get(3).get(3).grid[0] ="                  ";chunks.get(3).get(4).grid[0] ="\\                 ";chunks.get(3).get(5).grid[0] ="                  ";chunks.get(3).get(6).grid[0] ="                 /";chunks.get(3).get(7).grid[0] ="                  ";chunks.get(3).get(8).grid[0] ="                  ";chunks.get(3).get(9).grid[0] ="                  ";chunks.get(3).get(10).grid[0] ="                  ";
			chunks.get(3).get(0).grid[1]="                  ";chunks.get(3).get(1).grid[1]="                  ";chunks.get(3).get(2).grid[1] ="                  ";chunks.get(3).get(3).grid[1] ="                  ";chunks.get(3).get(4).grid[1] ="/                 ";chunks.get(3).get(5).grid[1] ="                  ";chunks.get(3).get(6).grid[1] ="                 \\";chunks.get(3).get(7).grid[1] ="                  ";chunks.get(3).get(8).grid[1] ="                  ";chunks.get(3).get(9).grid[1] ="                  ";chunks.get(3).get(10).grid[1] ="                  ";
			chunks.get(3).get(0).grid[2]="                  ";chunks.get(3).get(1).grid[2]="                  ";chunks.get(3).get(2).grid[2] ="                  ";chunks.get(3).get(3).grid[2] ="                  ";chunks.get(3).get(4).grid[2] ="\\                 ";chunks.get(3).get(5).grid[2] ="                  ";chunks.get(3).get(6).grid[2] ="                 /";chunks.get(3).get(7).grid[2] ="                  ";chunks.get(3).get(8).grid[2] ="                  ";chunks.get(3).get(9).grid[2] ="                  ";chunks.get(3).get(10).grid[2] ="                  ";
			chunks.get(3).get(0).grid[3]="                  ";chunks.get(3).get(1).grid[3]="                  ";chunks.get(3).get(2).grid[3] ="                  ";chunks.get(3).get(3).grid[3] ="                  ";chunks.get(3).get(4).grid[3] ="/                 ";chunks.get(3).get(5).grid[3] ="                  ";chunks.get(3).get(6).grid[3] ="                 \\";chunks.get(3).get(7).grid[3] ="                  ";chunks.get(3).get(8).grid[3] ="                  ";chunks.get(3).get(9).grid[3] ="                  ";chunks.get(3).get(10).grid[3] ="                  ";
			chunks.get(3).get(0).grid[4]="                  ";chunks.get(3).get(1).grid[4]="                  ";chunks.get(3).get(2).grid[4] ="                  ";chunks.get(3).get(3).grid[4] ="                  ";chunks.get(3).get(4).grid[4] ="\\                 ";chunks.get(3).get(5).grid[4] ="                  ";chunks.get(3).get(6).grid[4] ="                 /";chunks.get(3).get(7).grid[4] ="                  ";chunks.get(3).get(8).grid[4] ="                  ";chunks.get(3).get(9).grid[4] ="                  ";chunks.get(3).get(10).grid[4] ="                  ";
			chunks.get(4).get(0).grid[0]="                  ";chunks.get(4).get(1).grid[0]="                  ";chunks.get(4).get(2).grid[0] ="                  ";chunks.get(4).get(3).grid[0] ="                  ";chunks.get(4).get(4).grid[0] ="/                 ";chunks.get(4).get(5).grid[0] ="                  ";chunks.get(4).get(6).grid[0] ="                 \\";chunks.get(4).get(7).grid[0] ="                  ";chunks.get(4).get(8).grid[0] ="                  ";chunks.get(4).get(9).grid[0] =" _________________";chunks.get(4).get(10).grid[0] ="_________________ ";
			chunks.get(4).get(0).grid[1]="                  ";chunks.get(4).get(1).grid[1]="                  ";chunks.get(4).get(2).grid[1] ="                  ";chunks.get(4).get(3).grid[1] ="                  ";chunks.get(4).get(4).grid[1] ="\\                 ";chunks.get(4).get(5).grid[1] ="                  ";chunks.get(4).get(6).grid[1] ="                 /";chunks.get(4).get(7).grid[1] ="                  ";chunks.get(4).get(8).grid[1] ="                  ";chunks.get(4).get(9).grid[1] ="|                 ";chunks.get(4).get(10).grid[1] ="                 |";
			chunks.get(4).get(0).grid[2]="                  ";chunks.get(4).get(1).grid[2]="                  ";chunks.get(4).get(2).grid[2] ="                  ";chunks.get(4).get(3).grid[2] ="                  ";chunks.get(4).get(4).grid[2] ="/                 ";chunks.get(4).get(5).grid[2] ="                  ";chunks.get(4).get(6).grid[2] ="                 \\";chunks.get(4).get(7).grid[2] ="                  ";chunks.get(4).get(8).grid[2] ="                  ";chunks.get(4).get(9).grid[2] ="|  _____________  ";chunks.get(4).get(10).grid[2] ="  _____________  |";
			chunks.get(4).get(0).grid[3]="                  ";chunks.get(4).get(1).grid[3]="                  ";chunks.get(4).get(2).grid[3] ="                  ";chunks.get(4).get(3).grid[3] ="                  ";chunks.get(4).get(4).grid[3] ="\\                 ";chunks.get(4).get(5).grid[3] ="                  ";chunks.get(4).get(6).grid[3] ="                 /";chunks.get(4).get(7).grid[3] ="                  ";chunks.get(4).get(8).grid[3] ="                  ";chunks.get(4).get(9).grid[3] ="| (             ) ";chunks.get(4).get(10).grid[3] =" (             ) |";
			chunks.get(4).get(0).grid[4]="                  ";chunks.get(4).get(1).grid[4]="                  ";chunks.get(4).get(2).grid[4] ="                  ";chunks.get(4).get(3).grid[4] ="                  ";chunks.get(4).get(4).grid[4] ="/                 ";chunks.get(4).get(5).grid[4] ="                  ";chunks.get(4).get(6).grid[4] ="                 \\";chunks.get(4).get(7).grid[4] ="                  ";chunks.get(4).get(8).grid[4] ="                  ";chunks.get(4).get(9).grid[4] ="| (_____________) ";chunks.get(4).get(10).grid[4] =" (_____________) |";
			
			chunks.get(5).get(0).grid[0]="                  ";chunks.get(5).get(1).grid[0]="                  ";chunks.get(5).get(2).grid[0] ="                  ";chunks.get(5).get(3).grid[0] ="                  ";chunks.get(5).get(4).grid[0] ="\\_________________";chunks.get(5).get(5).grid[0] ="__________________";chunks.get(5).get(6).grid[0] ="_________________/";chunks.get(5).get(7).grid[0] ="                  ";chunks.get(5).get(8).grid[0] ="                  ";chunks.get(5).get(9).grid[0] ="|                 ";chunks.get(5).get(10).grid[0] ="                 |";
			chunks.get(5).get(0).grid[1]="                  ";chunks.get(5).get(1).grid[1]="                  ";chunks.get(5).get(2).grid[1] ="                  ";chunks.get(5).get(3).grid[1] ="                  ";chunks.get(5).get(4).grid[1] ="                  ";chunks.get(5).get(5).grid[1] ="                  ";chunks.get(5).get(6).grid[1] ="                  ";chunks.get(5).get(7).grid[1] ="                  ";chunks.get(5).get(8).grid[1] ="                  ";chunks.get(5).get(9).grid[1] ="|_________________";chunks.get(5).get(10).grid[1] ="_________________|";
			chunks.get(5).get(0).grid[2]="                  ";chunks.get(5).get(1).grid[2]="                  ";chunks.get(5).get(2).grid[2] ="                  ";chunks.get(5).get(3).grid[2] ="                  ";chunks.get(5).get(4).grid[2] ="                  ";chunks.get(5).get(5).grid[2] ="                  ";chunks.get(5).get(6).grid[2] ="                  ";chunks.get(5).get(7).grid[2] ="                  ";chunks.get(5).get(8).grid[2] ="                  ";chunks.get(5).get(9).grid[2] ="|                 ";chunks.get(5).get(10).grid[2] ="                 |";
			chunks.get(5).get(0).grid[3]="                  ";chunks.get(5).get(1).grid[3]="                  ";chunks.get(5).get(2).grid[3] ="                  ";chunks.get(5).get(3).grid[3] ="                  ";chunks.get(5).get(4).grid[3] ="                  ";chunks.get(5).get(5).grid[3] ="                  ";chunks.get(5).get(6).grid[3] ="                  ";chunks.get(5).get(7).grid[3] ="                  ";chunks.get(5).get(8).grid[3] ="                  ";chunks.get(5).get(9).grid[3] ="|                 ";chunks.get(5).get(10).grid[3] ="                 |";
			chunks.get(5).get(0).grid[4]="                  ";chunks.get(5).get(1).grid[4]="                  ";chunks.get(5).get(2).grid[4] ="                  ";chunks.get(5).get(3).grid[4] ="                  ";chunks.get(5).get(4).grid[4] ="                  ";chunks.get(5).get(5).grid[4] ="                  ";chunks.get(5).get(6).grid[4] ="                  ";chunks.get(5).get(7).grid[4] ="                  ";chunks.get(5).get(8).grid[4] ="                  ";chunks.get(5).get(9).grid[4] ="|                 ";chunks.get(5).get(10).grid[4] ="                 |";
			
			chunks.get(6).get(0).grid[0]="                  ";chunks.get(6).get(1).grid[0]="                  ";chunks.get(6).get(2).grid[0] ="                  ";chunks.get(6).get(3).grid[0] ="                  ";chunks.get(6).get(4).grid[0] ="                  ";chunks.get(6).get(5).grid[0] ="                  ";chunks.get(6).get(6).grid[0] ="                  ";chunks.get(6).get(7).grid[0] ="                  ";chunks.get(6).get(8).grid[0] ="                  ";chunks.get(6).get(9).grid[0] ="|                 ";chunks.get(6).get(10).grid[0] ="                 |";
			chunks.get(6).get(0).grid[1]="                  ";chunks.get(6).get(1).grid[1]="                  ";chunks.get(6).get(2).grid[1] ="                  ";chunks.get(6).get(3).grid[1] ="                  ";chunks.get(6).get(4).grid[1] ="                  ";chunks.get(6).get(5).grid[1] ="                  ";chunks.get(6).get(6).grid[1] ="                  ";chunks.get(6).get(7).grid[1] ="                  ";chunks.get(6).get(8).grid[1] ="                  ";chunks.get(6).get(9).grid[1] ="|                 ";chunks.get(6).get(10).grid[1] ="                 |";
			chunks.get(6).get(0).grid[2]="                  ";chunks.get(6).get(1).grid[2]="                  ";chunks.get(6).get(2).grid[2] ="                  ";chunks.get(6).get(3).grid[2] ="                  ";chunks.get(6).get(4).grid[2] ="                  ";chunks.get(6).get(5).grid[2] ="                  ";chunks.get(6).get(6).grid[2] ="                  ";chunks.get(6).get(7).grid[2] ="                  ";chunks.get(6).get(8).grid[2] ="                  ";chunks.get(6).get(9).grid[2] ="|                 ";chunks.get(6).get(10).grid[2] ="                 |";
			chunks.get(6).get(0).grid[3]="                  ";chunks.get(6).get(1).grid[3]="                  ";chunks.get(6).get(2).grid[3] ="                  ";chunks.get(6).get(3).grid[3] ="                  ";chunks.get(6).get(4).grid[3] ="                  ";chunks.get(6).get(5).grid[3] ="                  ";chunks.get(6).get(6).grid[3] ="                  ";chunks.get(6).get(7).grid[3] ="                  ";chunks.get(6).get(8).grid[3] ="                  ";chunks.get(6).get(9).grid[3] ="|                 ";chunks.get(6).get(10).grid[3] ="                 |";
			chunks.get(6).get(0).grid[4]="                  ";chunks.get(6).get(1).grid[4]="                  ";chunks.get(6).get(2).grid[4] ="                  ";chunks.get(6).get(3).grid[4] ="                  ";chunks.get(6).get(4).grid[4] ="                  ";chunks.get(6).get(5).grid[4] ="                  ";chunks.get(6).get(6).grid[4] ="                  ";chunks.get(6).get(7).grid[4] ="                  ";chunks.get(6).get(8).grid[4] ="                  ";chunks.get(6).get(9).grid[4] ="|                 ";chunks.get(6).get(10).grid[4] ="                 |";
			
			chunks.get(7).get(0).grid[0]="                  ";chunks.get(7).get(1).grid[0]="                  ";chunks.get(7).get(2).grid[0] ="                  ";chunks.get(7).get(3).grid[0] ="                  ";chunks.get(7).get(4).grid[0] ="                  ";chunks.get(7).get(5).grid[0] ="                  ";chunks.get(7).get(6).grid[0] ="                  ";chunks.get(7).get(7).grid[0] ="                  ";chunks.get(7).get(8).grid[0] ="                  ";chunks.get(7).get(9).grid[0] ="|                 ";chunks.get(7).get(10).grid[0] ="                 |";
			chunks.get(7).get(0).grid[1]="                  ";chunks.get(7).get(1).grid[1]="                  ";chunks.get(7).get(2).grid[1] ="                  ";chunks.get(7).get(3).grid[1] ="                  ";chunks.get(7).get(4).grid[1] ="                  ";chunks.get(7).get(5).grid[1] ="                  ";chunks.get(7).get(6).grid[1] ="                  ";chunks.get(7).get(7).grid[1] ="                  ";chunks.get(7).get(8).grid[1] ="                  ";chunks.get(7).get(9).grid[1] ="|                 ";chunks.get(7).get(10).grid[1] ="                 |";
			chunks.get(7).get(0).grid[2]="                  ";chunks.get(7).get(1).grid[2]="                  ";chunks.get(7).get(2).grid[2] ="                  ";chunks.get(7).get(3).grid[2] ="                  ";chunks.get(7).get(4).grid[2] ="                  ";chunks.get(7).get(5).grid[2] ="                  ";chunks.get(7).get(6).grid[2] ="                  ";chunks.get(7).get(7).grid[2] ="                  ";chunks.get(7).get(8).grid[2] ="                  ";chunks.get(7).get(9).grid[2] ="|                 ";chunks.get(7).get(10).grid[2] ="                 |";
			chunks.get(7).get(0).grid[3]="                  ";chunks.get(7).get(1).grid[3]="                  ";chunks.get(7).get(2).grid[3] ="                  ";chunks.get(7).get(3).grid[3] ="                  ";chunks.get(7).get(4).grid[3] ="                  ";chunks.get(7).get(5).grid[3] ="                  ";chunks.get(7).get(6).grid[3] ="                  ";chunks.get(7).get(7).grid[3] ="                  ";chunks.get(7).get(8).grid[3] ="                  ";chunks.get(7).get(9).grid[3] ="|_________________";chunks.get(7).get(10).grid[3] ="_________________|";
			chunks.get(7).get(0).grid[4]="                  ";chunks.get(7).get(1).grid[4]="                  ";chunks.get(7).get(2).grid[4] ="                  ";chunks.get(7).get(3).grid[4] ="                  ";chunks.get(7).get(4).grid[4] ="                  ";chunks.get(7).get(5).grid[4] ="                  ";chunks.get(7).get(6).grid[4] ="                  ";chunks.get(7).get(7).grid[4] ="                  ";chunks.get(7).get(8).grid[4] ="                  ";chunks.get(7).get(9).grid[4] ="||                ";chunks.get(7).get(10).grid[4] ="                ||";
			
			chunks.get(7).get(5).enterable = 1;
			chunks.get(7).get(5).enterArea = "World1";
		}
}

public void allChunksNonEnterableReset() {
	for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 11; j++) {
			chunks.get(i).get(j).enterable = 0;
			chunks.get(i).get(j).down = 1;chunks.get(i).get(j).up = 1;chunks.get(i).get(j).left = 1;chunks.get(i).get(j).right = 1;
		}
	}
}

public void allChunksNonEnterableResetCollision() {
	for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 11; j++) {
			chunks.get(i).get(j).enterable = 0;
			chunks.get(i).get(j).down = 0;chunks.get(i).get(j).up = 0;chunks.get(i).get(j).left = 0;chunks.get(i).get(j).right = 0;
		}
	}
}

public void setAllChunksEmpty() {
	for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 11; j++) {
			chunks.get(i).get(j).grid[0] = "                  ";
			chunks.get(i).get(j).grid[1] = "                  ";
			chunks.get(i).get(j).grid[2] = "                  ";
			chunks.get(i).get(j).grid[3] = "                  ";
			chunks.get(i).get(j).grid[4] = "                  ";
		}
	}
}

	
public void setChunkValues(String area) {
	if(area.equals("World1")) {
		setChunkDirections(1, 0, 0, 1, 0, 1);setChunkDirections(1, 1, 0, 1, 1, 0);setChunkDirections(1, 8, 0, 1, 0, 0);
		setChunkDirections(2, 0, 1, 1, 0, 1);setChunkDirections(2, 1, 1, 1, 1, 0);setChunkDirections(2, 3, 0, 1, 0, 0);setChunkDirections(2, 6, 0, 1, 0, 0);setChunkDirections(2, 7, 0, 1, 0, 1);setChunkDirections(2, 8, 1, 1, 1, 0);
		setChunkDirections(3, 0, 1, 0, 0, 1);setChunkDirections(3, 1, 1, 0, 1, 1);setChunkDirections(3, 2, 0, 0, 1, 1);setChunkDirections(3, 3, 1, 0, 1, 1);setChunkDirections(3, 4, 0, 0, 1, 1);setChunkDirections(3, 5, 0, 0, 1, 1);setChunkDirections(3, 6, 1, 1, 1, 0);setChunkDirections(3, 7, 1, 1, 0, 1);setChunkDirections(3, 8, 1, 1, 1, 0);
		setChunkDirections(4, 6, 1, 0, 0, 1);setChunkDirections(4, 7, 1, 1, 1, 1);setChunkDirections(4, 8, 1, 1, 1, 0);
		setChunkDirections(5, 5, 0, 1, 0, 1);setChunkDirections(5, 6, 0, 0, 1, 1);setChunkDirections(5, 7, 1, 0, 1, 1);setChunkDirections(5, 8, 1, 0, 1, 0);
		setChunkDirections(6, 0, 0, 1, 0, 0);setChunkDirections(6, 5, 1, 1, 0, 0);setChunkDirections(6, 9, 0, 1, 0, 1);setChunkDirections(6, 10, 0, 0, 1, 0);
		setChunkDirections(7, 0, 1, 0, 0, 1);setChunkDirections(7, 1, 0, 0, 1, 1);setChunkDirections(7, 2, 0, 0, 1, 1);setChunkDirections(7, 3, 0, 0, 1, 1);setChunkDirections(7, 4, 0, 0, 1, 1);setChunkDirections(7, 5, 1, 0, 1, 1);setChunkDirections(7, 6, 0, 0, 1, 1);setChunkDirections(7, 7, 0, 0, 1, 1);setChunkDirections(7, 8, 0, 0, 1, 1);setChunkDirections(7, 9, 1, 0, 1, 1);setChunkDirections(7, 10, 0, 0, 1, 1);
		//chunks.get(6).get(0).enterable=1;
		chunks.get(6).get(9).enterable=1;
		//chunks.get(2).get(3).enterable=1;
		chunks.get(2).get(6).enterable=1;
		setChunksNonInteractable();
		chunks.get(6).get(0).enterArea = "PokeLab1";chunks.get(6).get(9).enterArea = "House";chunks.get(2).get(3).enterArea = "Gym1";chunks.get(2).get(6).enterArea = "PokeStop1";
	}
	if(area.equals("PokeStop1")) {
		for(int i = 0; i < 11; i++) {
			setChunkDirections(0, i, 0, 0, 0, 0);
		}
		setChunksNonInteractable();
		setChunkDirections(1, 3, 0, 0, 0, 0);setChunkDirections(1, 7, 0, 0, 0, 0);
		setChunkDirections(2, 3, 0, 0, 0, 0);setChunkDirections(2, 4, 0, 0, 0, 0);setChunkDirections(2, 5, 0, 0, 0, 0);setChunkDirections(2, 6, 0, 0, 0, 0);setChunkDirections(2, 7, 0, 0, 0, 0);
		setChunkDirections(3, 10, 0, 0, 0, 0);setChunkDirections(4, 10, 0, 0, 0, 0);setChunkDirections(5, 10, 0, 0, 0, 0);
		setChunkDirections(3, 0, 0, 0, 0, 0);setChunkDirections(3, 1, 0, 0, 0, 0);setChunkDirections(3, 2, 0, 0, 0, 0);
		setChunkDirections(4, 0, 0, 0, 0, 0);setChunkDirections(4, 1, 0, 0, 0, 0);setChunkDirections(4, 2, 0, 0, 0, 0);
		setChunkDirections(5, 0, 0, 0, 0, 0);setChunkDirections(5, 1, 0, 0, 0, 0);setChunkDirections(5, 2, 0, 0, 0, 0);
		chunks.get(7).get(5).enterArea = "World1";	
		chunks.get(3).get(5).interactable = 1; chunks.get(4).get(9).interactable = 1;
	}
	if(area.equals("House")) {
		setChunksNonInteractable();
		System.out.println("here");
		for(int i = 0; i < 11; i++) {
			setChunkDirections(0, i, 0, 0, 0, 0);
			setChunkDirections(1, i, 0, 0, 0, 0);
		}
		setChunkDirections(4,9, 0, 0,0 ,0);setChunkDirections(4,10, 0, 0,0 ,0);
		setChunkDirections(5,9, 0, 0,0 ,0);setChunkDirections(5,10, 0, 0,0 ,0);
		setChunkDirections(6,9, 0, 0,0 ,0);setChunkDirections(6,10, 0, 0,0 ,0);
		setChunkDirections(7,9, 0, 0,0 ,0);setChunkDirections(7,10, 0, 0,0 ,0);
		chunks.get(2).get(1).interactable = 1; chunks.get(2).get(2).interactable = 1;
		chunks.get(7).get(5).enterArea = "World1";
	}
	if(area.equals("PokeLab1")) {
		
	}
}

public void setChunkDirections(int row, int col, int up, int down, int left, int right) {
	chunks.get(row).get(col).up = up; chunks.get(row).get(col).down = down; chunks.get(row).get(col).right = right; chunks.get(row).get(col).left = left;
}

public void setChunksNonInteractable() {
	for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 11; j++) {
			chunks.get(i).get(j).interactable=0;
		}
	}
}

public void openMenu(Player user) {
	Scanner scan = new Scanner(System.in);
	int menurow = 0;
	int termkey = 0;
	int menuTab = 0;
	String input = "";
	while(termkey == 0) {
		loadMenuChunks(menuTab);
		input = scan.next();
		if(input.equals("t")) break;
	}
}

public void loadMenuChunks(int tab) {
	if(tab == 0) {
	chunks.get(1).get(2).grid[0] =" _________________";chunks.get(1).get(3).grid[0] ="__________________";chunks.get(1).get(4).grid[0] ="__________________";chunks.get(1).get(5).grid[0] ="__________________";chunks.get(1).get(6).grid[0] ="__________________";chunks.get(1).get(7).grid[0] ="__________________";chunks.get(1).get(8).grid[0] ="_________________ ";
	chunks.get(1).get(2).grid[1] ="|                 ";chunks.get(1).get(3).grid[1] ="                  ";chunks.get(1).get(4).grid[1] ="                  ";chunks.get(1).get(5).grid[1] ="                  ";chunks.get(1).get(6).grid[1] ="                  ";chunks.get(1).get(7).grid[1] ="                  ";chunks.get(1).get(8).grid[1] ="                 |";
	chunks.get(1).get(2).grid[2] ="|   /\\    /\\  //__";chunks.get(1).get(3).grid[2] ="_\\ ||\\  | ||    | ";chunks.get(1).get(4).grid[2] ="            |   ))";chunks.get(1).get(5).grid[2] ="  /\\\\   /    \\\\   ";chunks.get(1).get(6).grid[2] ="                  ";chunks.get(1).get(7).grid[2] ="         ||  \\ // ";chunks.get(1).get(8).grid[2] =" \\   || / //___\\ |";
	chunks.get(1).get(2).grid[3] ="|  // \\  // \\ ||  ";chunks.get(1).get(3).grid[3] ="   || \\ | ||    | ";chunks.get(1).get(4).grid[3] ="            |   \\\\";chunks.get(1).get(5).grid[3] =" /__\\\\  |  ___    ";chunks.get(1).get(6).grid[3] ="                  ";chunks.get(1).get(7).grid[3] ="        ||__/ /|  ";chunks.get(1).get(8).grid[3] = "  |  ||/  ||     |";
	chunks.get(1).get(2).grid[4] ="| //   \\//   \\ \\__";chunks.get(1).get(3).grid[4] ="_  ||  \\|  \\\\__/  ";chunks.get(1).get(4).grid[4] ="            |   //";chunks.get(1).get(5).grid[4] ="/    \\\\ |____//   ";chunks.get(1).get(6).grid[4] ="                  ";chunks.get(1).get(7).grid[4] ="         ||     \\_";chunks.get(1).get(8).grid[4] ="_/   ||\\   \\___  |";
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
	System.out.println("_".repeat(200));
	for(int j = 0; j < 8; j++) {
		//System.out.print(j );
		for(int i = 0; i < 5; i++) {
			System.out.print("|");
				System.out.print(chunks.get(j).get(0).grid[i]);
				System.out.print(chunks.get(j).get(1).grid[i]);
				System.out.print(chunks.get(j).get(2).grid[i]);
				System.out.print(chunks.get(j).get(3).grid[i]);
				System.out.print(chunks.get(j).get(4).grid[i]);
				System.out.print(chunks.get(j).get(5).grid[i]);
				System.out.print(chunks.get(j).get(6).grid[i]);
				System.out.print(chunks.get(j).get(7).grid[i]);
				System.out.print(chunks.get(j).get(8).grid[i]);
				System.out.print(chunks.get(j).get(9).grid[i]);
				System.out.print(chunks.get(j).get(10).grid[i]);
			if(i != 7) {
				System.out.println();
			}
		}
	}
	System.out.println("|" + "_".repeat(198) + "|");
	}
}

public void mapDialogue(String sentence) {
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
}


class chunk{
	String[]grid;
	int up, down, left, right;
	int enterable;
	int interactable;
	String enterArea;
	int row, col;
	chunk(){
		grid = new String[5];
		for(int i = 0; i < 5; i++) {
			grid[i] = "                  ";
		}
		up = 0;
		down = 0;
		left = 0;
		right = 0;
		enterable = 0;
		interactable = 0;
	}	
	chunk(int row, int col){
		grid = new String[5];
		for(int i = 0; i < 5; i++) {
			grid[i] = "                  ";
		}
		up = 0;
		down = 0;
		left = 0;
		right = 0;
		enterable = 0;
		interactable = 1;
		this.row = row; this.col = col;
	}	
}
//replace: 7 8 9 10
public chunk modifyChunk(chunk c, Player user, String area, int chunkNum){
	chunk temp = new chunk();
 	if(area.equals("World1")) { 
		temp = c;
		StringBuilder sb1 = new StringBuilder(c.grid[1]);
		StringBuilder sb2 = new StringBuilder(c.grid[2]);
		StringBuilder sb3 = new StringBuilder(c.grid[3]);
		String row0 = user.symbols[0][0] + user.symbols[0][1] + user.symbols[0][2] + user.symbols[0][3];
		String row1 = user.symbols[1][0] + user.symbols[1][1] + user.symbols[1][2] + user.symbols[1][3];
		String row2 = user.symbols[2][0] + user.symbols[2][1] + user.symbols[2][2] + user.symbols[2][3];
		sb1.replace(7, 11, row0);
		sb2.replace(7, 11, row1);
		sb3.replace(7, 11, row2);
		c.grid[1] = sb1.toString();
		c.grid[2] = sb2.toString();
		c.grid[3] = sb3.toString();
	}
	if(area.equals("Gym1") || area.equals("PokeStop1") || area.equals("House") || area.equals("PokeLab1")) {
		if(chunkNum == 1) {
			temp = c;
			for(int i = 0; i < 5; i++) {
				temp.grid[i] = user.symbolsInterior[i];
			}
		} else if(chunkNum == 2) {
			for(int i = 0; i < 5; i++) {
				temp.grid[i] = user.symbolsInterior[i+5];
			}
		}
	}
	return temp;
}


public void loadBigMapChunks(String area) {
	if(area.equals("Gym1")) {
		for(int i = 0; i < 24; i++) {
			for(int j = 0; j < 33; j++) {
				chunks.get(i).get(j).grid[0] = "                  ";
				chunks.get(i).get(j).grid[1] = "                  ";
				chunks.get(i).get(j).grid[2] = "                  ";
				chunks.get(i).get(j).grid[3] = "                  ";
				chunks.get(i).get(j).grid[4] = "                  ";
			}
		}
	} 
	if(area.equals("PokeLab1")) {
		for(int i = 0; i < 24; i++) {
			for(int j = 0; j < 33; j++) {
				chunks.get(i).get(j).grid[0] = "                  ";
				chunks.get(i).get(j).grid[1] = "                  ";
				chunks.get(i).get(j).grid[2] = "                  ";
				chunks.get(i).get(j).grid[3] = "                  ";
				chunks.get(i).get(j).grid[4] = "                  ";
			}
		}
		 chunks.get(10).get(11).grid[3] = " ~~~~~~~~~~~~~~~~~";
	}
	if(area.equals("Transition1")) {
		
	}
}

}
