package pokemon;
import pokemon.Poke;
import pokemon.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.HashMap;
import pokemon.Collection;

public class Generate {
	HashMap<String, String> world1_area1;
	HashMap<String, String> world1_area2;
	HashMap<String, String> cave1;
	HashMap<String, String> world2;
	HashMap<String, String> cave2;
	HashMap<String, String> world3;

	HashMap<String, Integer> levels;
	
	HashMap<String, HashMap<String, String>> pool;
	
	Generate(Collection collection){
		
		pool = new HashMap<>();
		
		world1_area1 = new HashMap<>();
		world1_area2 = new HashMap<>();
		cave1 = new HashMap<>();
		world2 = new HashMap<>();
		cave2 = new HashMap<>();
		world3 = new HashMap<>();
		
		
		pool.put("World1_area1", world1_area1);
		pool.put("World2", world2);
		pool.put("World3", world3);
		pool.put("Cave1", cave1);
		pool.put("Cave2", cave2);
		
		
		world1_area1.put("1 15", "Stickly");   
		world1_area1.put("17 28", "GrassJumper");
		world1_area1.put("29 37","Raccadox");         world1_area2.put("1 14","Raccadox");
		world1_area1.put("38 47","Crow-Bow");         world1_area2.put("15 27","Crow-Bow");
		world1_area1.put("48 57","ButterBird");       world1_area2.put("28 39","ButterBird");
		world1_area1.put("58 64","Goosepor");         world1_area2.put("40 48","Goosepor");
		world1_area1.put("65 71","VineTurtle");       world1_area2.put("49 57","VineTurtle");
		world1_area1.put("72 73","ToxRat");           world1_area2.put("58 61","ToxRat");
		world1_area1.put("74 75","Banopotamus");      world1_area2.put("62 66","Banopotamus");
		world1_area1.put("76 79","Bark-Widow");       world1_area2.put("67 72","Bark-Widow");
		world1_area1.put("80 81","Thunder-Bunny");    world1_area2.put("73 76","Thunder-Bunny");
		world1_area1.put("82 86","Lumin-Fox");        world1_area2.put("77 83","Lumin-Fox");
		world1_area1.put("87 91","EnerSnail");        world1_area2.put("84 89","EnerSnail");
		world1_area1.put("92 94","Scorponium");       world1_area2.put("90 93","Scorponium");
		world1_area1.put("95 100","BoulderMash");     world1_area2.put("94 100","BoulderMash");
		
		
		levels = new HashMap<>();
		levels.put("1 15", 5);
		levels.put("16 30", 6);
		levels.put("31 50", 7);
		levels.put("51 63", 8);
		levels.put("64 73", 9);
		levels.put("74 78", 10);
		levels.put("79 83", 11);
		levels.put("84 88", 12);
		levels.put("89 93", 13);
		levels.put("94 97", 14);
		levels.put("98 100", 15);

	}
	
	public Poke generatePoke(String area, Collection collection){
		Poke newPoke = locateArea(area, collection);
		return newPoke;
	}
	
	public Poke locateArea(String area, Collection collection) {
		while(true) {
			Poke pokemon;
			Random ran = new Random();
			int chance = ran.nextInt(1, 101);
			int lvl = 0; String name = ""; int level = 0;
			lvl = ran.nextInt(1,101);
			chance = ran.nextInt(1, 101);
			String tester; StringTokenizer st; StringTokenizer st1;
			Integer value[] = new Integer[2];
			
			
			for(String key : pool.get(area).keySet()) {
				st = new StringTokenizer(key);
				int index = 0;
				while(st.hasMoreTokens()) {
					value[index] = Integer.parseInt(st.nextToken());
					index++;
				}
				if(chance >= value[0] && chance <= value[1]) {
					name = world1_area1.get(key);
					break;
				}
			}
			for(String key : levels.keySet()) {
				st = new StringTokenizer(key);
				int index = 0;
				while(st.hasMoreTokens()) {
					value[index] = Integer.parseInt(st.nextToken());
					index++;
				}
				if(lvl >= value[0] && lvl <= value[1]) {
					level = levels.get(key);
					break;
				}
			}
			try {
				pokemon = new Poke(name, collection, level);
				return pokemon;
			}catch(Exception e) {
				
			}
		}
	}
	
	
	
}
