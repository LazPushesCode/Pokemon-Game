package pokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import pokemon.game;
import pokemon.Item;
import pokemon.Map.chunk;
import java.util.Random;
import pokemon.Backpack;

public class Poke {
	int level;
	String name;
	int ID;
	
	int hp;
	int speed;
	int attack;
	int spAttack;
	int defense;
	int spDefense;
	int EVcount;
	int temphp;
	int tempstats[];
	ArrayList<Attack> movesets;
	
	int EV[];
	int IV[];
	Item itemHeld;
	String type[] = {"",""};
	String impact;
	String debuff;
	String status;
	int impacttimer, debufftimer, statustimer;
	Poke(String name, Collection collection, String flag){
		Random random = new Random();
		movesets = new ArrayList<>();
		if(flag.equals("starter")) {
			level = 5;
		} else if(flag.equals("random5-15")) {
			level = random.nextInt(5, 16);
		} else if(flag.equals("random20-30")) {
			level = random.nextInt(20,31);
		} else {
			level = 1;
		}
		
		movesets.add(collection.get(name).moves.get(0));
		movesets.add(collection.get(name).moves.get(1));
		if(level >= 7) movesets.add(collection.get(name).moves.get(2));
		if(level >= 10) movesets.add(collection.get(name).moves.get(3));
		debuff = ""; status = ""; impact = "";
		
		//0 - hp, 1 - attack, 2 - spAttack, 3 - defense, 4 - spDefense, 5 - speed
		this.name = name;
		this.type[0] = collection.get(name).type[0];
		if(collection.get(name).type[1] != null) {
			this.type[1] = collection.get(name).type[1];
		}
		EV = new int[6];
		IV = new int[6];
		System.out.println("BASE HP: " + collection.get(name).bhp);
		System.out.println("BASE Attack: " + collection.get(name).battack);
		System.out.println("BASE SpAttack: " + collection.get(name).bspattack);
		System.out.println("BASE Defense: " + collection.get(name).bdefense);
		System.out.println("BASE SpDefense: " + collection.get(name).bspdefense);
		System.out.println("BASE Speed: " + collection.get(name).bspeed);
		initializeStats();
		for(int i = 0; i < 6; i++) {
			if(i == 0) System.out.print("IV HP");
			if(i == 1) System.out.print("IV Attack");
			if(i == 2) System.out.print("IV SpAttack");
			if(i == 3) System.out.print("IV Defense");
			if(i == 4) System.out.print("IV SpDefense");
			if(i == 5) System.out.print("IV Speed");
			System.out.println(": "+IV[i]);
		}
		double temp;
		//calculate stats
		this.hp = (((2 * collection.get(name).bhp + IV[0] + (EV[0]/4))/100)*level) + level + 10;
		
		System.out.println("RESULT hp: " +this.hp);
		temp = (((2 * (double)collection.get(name).battack + (double)IV[1] + (EV[1]/4))/100));
		temp = (temp * level) + 5;
		this.attack = (int)temp;
		System.out.println("RESULT attack: " +this.attack);
		temp = (((2 * (double)collection.get(name).bspattack + (double)IV[2] + (EV[2]/4))/100));
		temp = (temp * level) + 5;
		this.spAttack = (int)temp;
		System.out.println("RESULT spAttack: " +this.spAttack);
		temp = (((2 * (double)collection.get(name).bdefense + (double)IV[3] + (EV[3]/4))/100));
		temp = (temp * level) + 5;
		this.defense = (int)temp;
		System.out.println("RESULT defense: " +this.defense);
		temp = (((2 * (double)collection.get(name).bspdefense + (double)IV[4] + (EV[4]/4))/100));
		temp = (temp * level) + 5;
		this.spDefense = (int)temp;
		System.out.println("RESULT spDefense: " +this.spDefense);
		temp = (((2 * (double)collection.get(name).bspeed + (double)IV[5] + (EV[5]/4))/100));
		temp = (temp * level) + 5;
		this.speed = (int)temp;
		System.out.println("RESULT speed: " +this.speed);
		temphp = this.hp;
		tempstats = new int[5];
		this.tempstats[0] = this.speed;this.tempstats[1] = this.attack; this.tempstats[2] = this.spAttack;
		this.tempstats[3] = this.defense;this.tempstats[4] = this.spDefense;
		
	}
	public void initializeStats() {
		Random ran = new Random();
		for(int i = 0; i < 6; i++) {
			IV[i] = ran.nextInt(0, 32);
			EV[i] = 1;
		}
	}
	
	public void updateStats(Collection collection) {
		this.hp = (((2 * collection.get(name).bhp + IV[0] + (EV[0]/4))/100)*level) + level + 10;
		this.attack = (((2 * collection.get(name).battack + IV[1] + (EV[1]/4))/100)*level) + 5;
		this.spAttack = (((2 * collection.get(name).bspattack + IV[2] + (EV[2]/4))/100)*level) + 5;
		this.defense = (((2 * collection.get(name).bdefense + IV[3] + (EV[3]/4))/100)*level) + 5;
		this.spDefense = (((2 * collection.get(name).bspdefense + IV[4] + (EV[4]/4))/100)*level) + 5;
		this.speed = (((2 * collection.get(name).bspeed + IV[5] + (EV[5]/4))/100)*level) + 5;
	}
}	

class Attack{
	String name;
	String type;
	int accuracy;
	String effect;
	int power;
	String attackType;
	int pp;
	Attack() {
		this.name = "NONE";
		this.type = "NONE";
		this.accuracy = 0;
		this.effect = "NONE";
	}
	Attack(String name, String type, String attackType, int accuracy, String effect, int power, int pp){
		this.name = name;
		this.type = type;
		this.accuracy = accuracy;
		this.effect = effect;
		this.power = power;
		this.attackType = attackType;
		this.pp = pp;
	}
}

class Physical extends Attack{
	int power;
	String attackType = "Physical";
	Physical(String name, String type, int accuracy, String effect, int power, int pp){
		super(name, type, "Physical", accuracy, effect, power, pp);
		this.power = power;
	}
}

class Special extends Attack{
	int dps;
	int power;
	String attackType = "Special";
	Special(String name, String type, int accuracy, String effect, int power, int dps, int pp){
		super(name, type, "Special", accuracy, effect, power, pp);
		this.power = power;
		this.dps = dps;
	}
}

class Status extends Attack{
	String attackType = "Status";
	Status(String name, String type, int accuracy, String effect, int power, int pp){
		super(name, type, "Status", accuracy, effect, power, pp);
		this.effect = effect;
	}
	//specific effect i guess
}

class AttackDatabase{
	
	private HashMap<String, Physical> physicalMoves;
	private HashMap<String, Special> specialMoves;
	private HashMap<String, Status> statusMoves;
	
	AttackDatabase(){
		physicalMoves = new HashMap<>();
		specialMoves = new HashMap<>();
		statusMoves = new HashMap<>();
		//attacktype, name, type, acc, effect, POW, dps, pp
		
		//physical grass moves
		addMove("Physical", "Leaf Kick", "Grass", 100, "", 30, 0, 30);
		addMove("Physical", "Stick Puncture", "Grass", 100, "", 40, 0, 30);
		addMove("Physical", "Dirty Hook", "Grass", 100, "Daze", 50, 0, 30);
		addMove("Physical", "Dirty Slap", "Grass", 100, "", 50, 0, 30);
		addMove("Physical", "Lawn Mower", "Grass", 100, "", 80, 0, 20);
		addMove("Physical", "Weed Wacker", "Grass", 100, "", 75, 0, 20);
		addMove("Physical", "Tangled Vines", "Grass", 100, "Paralyze", 0, 0, 20);
		addMove("Physical", "Flora Wallop", "Grass", 100, "Daze", 70, 0, 20);
		addMove("Physical", "Forest Junction", "Grass", 100, "Paralyze", 120, 0 ,10);
		addMove("Physical", "Hack-a-Tree", "Grass", 100, "", 150, 0, 10);
		//special grass moves
		addMove("Special", "Shallow Grass Cut", "Grass", 100, "Bleed", 40, 0, 30);
		addMove("Special", "Branch Throw", "Grass", 100, "", 50, 0, 30);
		addMove("Special", "Dirty Shot", "Grass", 100, "Photosynthesis", 60, 0, 30);
		addMove("Special", "Leaf Blow", "Grass", 100, "", 50, 0, 30);
		addMove("Special", "Vine Lash", "Grass", 100, "Bleed", 80, 0, 20);
		addMove("Special", "Herb Clash", "Grass", 100, "", 75, 0, 20);
		addMove("Special", "HayMaker", "Grass", 100, "", 90, 0, 20);
		addMove("Special", "Weeping Willow", "Grass", 100, "", 70, 0, 20);
		addMove("Special", "Jungle Junction", "Grass", 100, "Photosynthesis", 120, 0, 10);
		addMove("Special", "Uprooted", "Grass", 100, "", 150, 0, 10);
		//status grass moves (temp)
		addMove("Status", "Flower Dance", "Grass", 100, "", 0, 0, 5);
		
		//physical water moves
		
		//special water moves
		
		//status water moves
		
		//physical Fire moves
		addMove("Physical", "Flame Kick", "Fire", 80, "", 30, 0, 30);
		addMove("Physical", "Searing Punch", "Fire", 80, "", 40, 0, 30);
		addMove("Physical", "Light Flicker", "Fire", 100, "", 30, 0, 30);
		addMove("Physical", "Bonfire Rush", "Fire", 100, "", 60, 0, 30);
		addMove("Physical", "Fiery Uppercut", "Fire", 100, "", 70, 0, 20);
		addMove("Physical", "Enflamed Glove", "Fire", 100, "", 75, 0, 20);
		addMove("Physical", "1000 Deg Blaze", "Fire", 100, "Daze", 60, 0, 20);
		addMove("Physical", "Smoldering Entombment", "Fire", 100, "", 90, 0, 20);
		addMove("Physical", "MALEVOLENT KITCHEN", "Fire", 110, "Extra Turn", 0, 0, 10);
		addMove("Physical", "ASHES OF POMPEII", "Fire", 100, "Confuse", 0, 0, 10);
		//special fire moves
		addMove("Special", "Fire Dart", "Fire", 100, "", 40, 0, 30);
		addMove("Special", "Boulder of Ash", "Fire", 100, "", 50, 0, 30);
		addMove("Special", "Flame Ignition", "Fire", 100, "Burn", 30, 0, 30);
		addMove("Special", "Flame Pebbles", "Fire", 100, "", 40, 0, 30);
		addMove("Special", "Meteor Shower", "Fire", 100, "Burn", 80, 0, 20);
		addMove("Special", "Swelling Inferno", "Fire", 100, "Melting Point", 40, 0, 20);
		addMove("Special", "Hot Cross Buns", "Fire", 100, "", 90, 0, 20);
		addMove("Special", "Volcanic Disruption", "Fire", 100, "", 100, 0, 20);
		addMove("Special", "Magma Bound", "Fire", 100, "Melting Point", 140, 0, 10);
		addMove("Special", "Lava Flood", "Fire", 100, "Burn", 130, 0, 10);
		
		//status fire moves
		addMove("Status", "Rising Phoenix", "Fire", 0, "Heal", 0, 0, 5);
	}
	
	public void addMove(String attackType, String name, String type, int accuracy, String effect, int power, int dps, int pp) {
		
		if(attackType.equals("Physical")) {
			Physical move = new Physical(name, type, accuracy, effect, power, pp);
			physicalMoves.put(move.name, move);
		} else if(attackType.equals("Special")) {
			Special move = new Special(name, type, accuracy, effect, power, dps, pp);
			specialMoves.put(move.name, move);
		} else if(attackType.equals("Status")) {
			Status move = new Status(name, type, accuracy, effect, power, pp);
			statusMoves.put(move.name, move);
		}
		
	}
	public Attack retrieveMove(String key) {
		if(physicalMoves.containsKey(key)) {
			return physicalMoves.get(key);
		} else if(specialMoves.containsKey(key)) {
			return specialMoves.get(key);
		} else if(statusMoves.containsKey(key)) {
			return statusMoves.get(key);
		}
		return null;
	}
	
	public Attack findMoveInformation(Attack move) {
		String key = move.getClass().toString();
		if(key.equals("Physical")) {
			return physicalMoves.get(move.name);
		} else if(key.equals("Special")) {
			return specialMoves.get(move.name);
		} else if(key.equals("Status")) {
			return statusMoves.get(move.name);
		} else {
			return null;
		}
	}
}




