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
	
	int EV[];
	int IV[];
	Item itemHeld;
	String type[] = {"",""};
	String debuff;
	String status;
	Poke(String name, Collection collection, String flag){
		Random random = new Random();
		if(flag.equals("starter")) {
			level = 5;
		} else if(flag.equals("random5-15")) {
			level = random.nextInt(5, 16);
		} else if(flag.equals("random20-30")) {
			level = random.nextInt(20,31);
		} else {
			level = 1;
		}
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
	double accuracy;
	String effect;
	Attack() {
		this.name = "NONE";
		this.type = "NONE";
		this.accuracy = 0;
		this.effect = "NONE";
	}
	Attack(String name, String type, double accuracy, String effect){
		this.name = name;
		this.type = type;
		this.accuracy = accuracy;
		this.effect = effect;
	}
}

class Physical extends Attack{
	int power;
	Physical(String name, String type, double accuracy, String effect, int power){
		super(name, type, accuracy, effect);
		this.power = power;
	}
}

class Special extends Attack{
	int dps;
	int power;
	Special(String name, String type, double accuracy, String effect, int power, int dps){
		super(name, type, accuracy, effect);
		this.power = power;
		this.dps = dps;
	}
}

class Status extends Attack{
	Status(String name, String type, double accuracy, String effect){
		super(name, type, accuracy, effect);
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
		//attacktype, name, type, acc, effect, POW, dps
		
		//physical grass moves
		addMove("Physical", "Leaf Kick", "Grass", 100, "", 30, 0);
		addMove("Physical", "Stick Puncture", "Grass", 100, "", 40, 0);
		addMove("Physical", "Dirty Hook", "Grass", 100, "Daze", 50, 0);
		addMove("Physical", "Dirty Slap", "Grass", 100, "", 50, 0);
		addMove("Physical", "Lawn Mower", "Grass", 100, "", 80, 0);
		addMove("Physical", "Weed Wacker", "Grass", 100, "", 75, 0);
		addMove("Physical", "Tangled Vines", "Grass", 100, "Paralyze", 0, 0);
		addMove("Physical", "Flora Wallop", "Grass", 100, "Daze", 70, 0);
		addMove("Physical", "Forest Junction", "Grass", 100, "Paralyze", 120, 0);
		addMove("Physical", "Hack-a-Tree", "Grass", 100, "", 150, 0);
		//special grass moves
		addMove("Special", "Shallow Grass Cut", "Grass", 100, "Bleed", 40, 0);
		addMove("Special", "Branch Throw", "Grass", 100, "", 50, 0);
		addMove("Special", "Dirty Shot", "Grass", 100, "Photosynthesis", 60, 0);
		addMove("Special", "Leaf Blow", "Grass", 100, "", 50, 0);
		addMove("Special", "Vine Lash", "Grass", 100, "Bleed", 80, 0);
		addMove("Special", "Herb Clash", "Grass", 100, "", 75, 0);
		addMove("Special", "HayMaker", "Grass", 100, "", 90, 0);
		addMove("Special", "Weeping Willow", "Grass", 100, "", 70, 0);
		addMove("Special", "Jungle Junction", "Grass", 100, "Photosynthesis", 120, 0);
		addMove("Special", "Uprooted", "Grass", 100, "", 150, 0);
		//status grass moves (temp)
		addMove("Status", "Flower Dance", "Grass", 100, "", 0, 0);
		
		//physical water moves
		
		//special water moves
	}
	
	public void addMove(String attackType, String name, String type, double accuracy, String effect, int power, int dps) {
		
		if(attackType.equals("Physical")) {
			Physical move = new Physical(name, type, accuracy, effect, power);
			physicalMoves.put(move.name, move);
		} else if(attackType.equals("Special")) {
			Special move = new Special(name, type, accuracy, effect, power, dps);
			specialMoves.put(move.name, move);
		} else if(attackType.equals("Status")) {
			Status move = new Status(name, type, accuracy, effect);
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




