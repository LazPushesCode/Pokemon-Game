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
		initializeStats();
		//calculate stats
		this.hp = (((2 * collection.get(name).bhp + IV[0] + (EV[0]/4))/100)*level) + level + 10;
		this.attack = (((2 * collection.get(name).battack + IV[1] + (EV[1]/4))/100)*level) + 5;
		this.spAttack = (((2 * collection.get(name).bspattack + IV[2] + (EV[2]/4))/100)*level) + 5;
		this.defense = (((2 * collection.get(name).bdefense + IV[3] + (EV[3]/4))/100)*level) + 5;
		this.spDefense = (((2 * collection.get(name).bspdefense + IV[4] + (EV[4]/4))/100)*level) + 5;
		this.speed = (((2 * collection.get(name).bspeed + IV[5] + (EV[5]/4))/100)*level) + 5;
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
	Status(String name, String type, int accuracy, String effect){
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




