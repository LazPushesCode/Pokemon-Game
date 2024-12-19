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
	int level; int xp;
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
	String[] model;
	
	int EV[];
	int IV[];
	Item itemHeld;
	String type[] = {"",""};
	String impact;
	String debuff;
	String status;
	int impacttimer, debufftimer, statustimer;
	Poke(String name, Collection collection, int level){
		model = new String[10];
		for(int i = 0; i < 10; i++) {
			model[i] = collection.get(name).model[i];
		}
		movesets = new ArrayList<>();
		this.level = level; this.xp = 0;
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

		initializeStats();
		double temp;
		//calculate stats
		this.hp = ((((2 * collection.get(name).bhp + IV[0] + (EV[0]/4))/100)*level) + level + 10 )* 2;
		temp = (((2 * (double)collection.get(name).battack + (double)IV[1] + (EV[1]/4))/100));
		temp = (temp * level) + 5;
		this.attack = (int)temp;
		temp = (((2 * (double)collection.get(name).bspattack + (double)IV[2] + (EV[2]/4))/100));
		temp = (temp * level) + 5;
		this.spAttack = (int)temp;
		temp = (((2 * (double)collection.get(name).bdefense + (double)IV[3] + (EV[3]/4))/100));
		temp = (temp * level) + 5;
		this.defense = (int)temp;
		temp = (((2 * (double)collection.get(name).bspdefense + (double)IV[4] + (EV[4]/4))/100));
		temp = (temp * level) + 5;
		this.spDefense = (int)temp;
		temp = (((2 * (double)collection.get(name).bspeed + (double)IV[5] + (EV[5]/4))/100));
		temp = (temp * level) + 5;
		this.speed = (int)temp;
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
		this.hp = ((((2 * collection.get(name).bhp + IV[0] + (EV[0]/4))/100)*level) + level + 10 ) * 2;
		this.attack = (((2 * collection.get(name).battack + IV[1] + (EV[1]/4))/100)*level) + 5;
		this.spAttack = (((2 * collection.get(name).bspattack + IV[2] + (EV[2]/4))/100)*level) + 5;
		this.defense = (((2 * collection.get(name).bdefense + IV[3] + (EV[3]/4))/100)*level) + 5;
		this.spDefense = (((2 * collection.get(name).bspdefense + IV[4] + (EV[4]/4))/100)*level) + 5;
		this.speed = (((2 * collection.get(name).bspeed + IV[5] + (EV[5]/4))/100)*level) + 5;
	}
	public int checkLevel(Collection collection) {
		if(xp >= (20 + ((level-5) *5))) {
			updateStats(collection);
			if(level != 100)level++;
			xp = xp - (20+((level-5)*5));
			temphp = hp;
			return 1;
		}
		return 0;
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
	int timer;
	Attack() {
		this.name = "NONE";
		this.type = "NONE";
		this.accuracy = 0;
		this.effect = "NONE";
	}
	Attack(String name, String type, String attackType, int accuracy, String effect, int power, int pp, int timer){
		this.name = name;
		this.type = type;
		this.accuracy = accuracy;
		this.effect = effect;
		this.power = power;
		this.attackType = attackType;
		this.pp = pp;
		this.timer = timer;
	}
}

class Physical extends Attack{
	int power;
	String attackType = "Physical";
	Physical(String name, String type, int accuracy, String effect, int power, int pp, int timer){
		super(name, type, "Physical", accuracy, effect, power, pp, timer);
		this.power = power;
	}
}

class Special extends Attack{
	int dps;
	int power;
	String attackType = "Special";
	Special(String name, String type, int accuracy, String effect, int power, int dps, int pp, int timer){
		super(name, type, "Special", accuracy, effect, power, pp, timer);
		this.power = power;
		this.dps = dps;
	}
}

class Status extends Attack{
	String attackType = "Status";
	Status(String name, String type, int accuracy, String effect, int power, int pp, int timer){
		super(name, type, "Status", accuracy, effect, power, pp, timer);
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
		//attacktype, name, type, acc, effect, POW, dps, pp, timer
		
		//physical grass moves
		addMove("Physical", "Leaf Kick", "Grass", 80, "", 30, 0, 30, 0);
		addMove("Physical", "Stick Puncture", "Grass", 80, "", 50, 0, 30, 0);
		addMove("Physical", "Dirty Hook", "Grass", 80, "Daze", 40, 0, 20, 2);
		addMove("Physical", "Dirty Slap", "Grass", 80, "", 50, 0, 30, 0);
		addMove("Physical", "Lawn Mower", "Grass", 80, "", 80, 0, 20, 0);
		addMove("Physical", "Weed Wacker", "Grass", 80, "", 75, 0, 20, 0);
		addMove("Physical", "Tangled Vines", "Grass", 80, "Paralyze", 0, 0, 20, 2);
		addMove("Physical", "Flora Wallop", "Grass", 80, "Daze", 70, 0, 20, 2);
		addMove("Physical", "Forest Junction", "Grass", 80, "Paralyze", 120, 0 ,10, 2);
		addMove("Physical", "Hack-a-Tree", "Grass", 80, "", 150, 0, 10, 0);
		//special grass moves
		addMove("Special", "Shallow Grass Cut", "Grass", 70, "Bleed", 40, 0, 20, 3);
		addMove("Special", "Branch Throw", "Grass", 80, "", 50, 0, 30, 0);
		addMove("Special", "Dirty Shot", "Grass", 60, "Photosynthesis", 60, 0, 20, 2);
		addMove("Special", "Leaf Blow", "Grass", 80, "", 50, 0, 30, 0);
		addMove("Special", "Vine Lash", "Grass", 70, "Bleed", 80, 0, 10, 3);
		addMove("Special", "Herb Clash", "Grass", 80, "", 75, 0, 20, 0);
		addMove("Special", "HayMaker", "Grass", 80, "", 90, 0, 20, 0);
		addMove("Special", "Weeping Willow", "Grass", 80, "", 70, 0, 20, 0);
		addMove("Special", "Jungle Junction", "Grass", 70, "Photosynthesis", 120, 0, 10, 1);
		addMove("Special", "Uprooted", "Grass", 100, "", 150, 0, 10, 0);
		//status grass moves (temp)
		addMove("Status", "Flower Dance", "Grass", 100, "", 0, 0, 5, 0);
		
		//physical water moves
		
		//special water moves
		
		//status water moves
		
		//physical Fire moves
		addMove("Physical", "Flame Kick", "Fire", 80, "", 40, 0, 30, 0);
		addMove("Physical", "Searing Punch", "Fire", 80, "", 30, 0, 30, 0);
		addMove("Physical", "Light Flicker", "Fire", 80, "", 30, 0, 30, 0);
		addMove("Physical", "Bonfire Rush", "Fire", 80, "", 50, 0, 30, 0);
		addMove("Physical", "Fiery Uppercut", "Fire", 80, "", 70, 0, 20, 0);
		addMove("Physical", "Enflamed Glove", "Fire", 80, "", 75, 0, 20, 0);
		addMove("Physical", "1000 Deg Blaze", "Fire", 80, "Daze", 60, 0, 20, 3);
		addMove("Physical", "Smoldering Entombment", "Fire", 80, "", 90, 0, 20, 0);
		addMove("Physical", "MALEVOLENT KITCHEN", "Fire", 110, "Extra Turn", 0, 0, 10, 1);
		addMove("Physical", "ASHES OF POMPEII", "Fire", 100, "Confuse", 0, 0, 10, 2);
		//special fire moves
		addMove("Special", "Fire Dart", "Fire", 60, "", 40, 0, 30, 0);
		addMove("Special", "Boulder of Ash", "Fire", 60, "", 50, 0, 30, 0);
		addMove("Special", "Flame Ignition", "Fire", 80, "Burn", 30, 0, 20, 3);
		addMove("Special", "Flame Pebbles", "Fire", 80, "", 40, 0, 30, 0);
		addMove("Special", "Meteor Shower", "Fire", 60, "Burn", 80, 0, 20, 3);
		addMove("Special", "Swelling Inferno", "Fire", 70, "Melting Point", 40, 0, 20, 3);
		addMove("Special", "Hot Cross Buns", "Fire", 100, "", 90, 0, 20, 0);
		addMove("Special", "Volcanic Disruption", "Fire", 100, "", 100, 0, 20, 0);
		addMove("Special", "Magma Bound", "Fire", 100, "Melting Point", 140, 0, 10, 3);
		addMove("Special", "Lava Flood", "Fire", 100, "Burn", 130, 0, 10, 3);
		
		//status fire moves
		addMove("Status", "Rising Phoenix", "Fire", 100, "Heal", 0, 0, 5, 1);
		
		
		//physical water moves
		addMove("Physical", "Wet Slap", "Water", 80, "", 20, 0, 30, 0);
		addMove("Physical", "Flipper Wack", "Water", 80, "", 30, 0, 30, 0);
		addMove("Physical", "Hazy Mist", "Water", 80, "Daze", 20, 0, 20, 0);
		addMove("Physical", "Freeze", "Water", 60, "Paralyze", 0, 0, 20, 0);
		addMove("Physical", "Aquatic Dash", "Water", 100, "", 50, 0, 30, 0);
		addMove("Physical", "Cannonball", "Water", 80, "", 70, 0, 30, 0);
		addMove("Physical", "Dolphin Dive", "Water", 80, "", 50, 0, 30, 0);
		addMove("Physical", "Royal Flush", "Water", 80, "Daze", 60, 0, 20, 0);
		addMove("Physical", "Aquatic Uppercut", "Water", 80, "", 100, 0, 30, 0);
		addMove("Physical", "Explosive Cannonball", "Water", 80, "Confuse", 90, 0, 10, 0);
		
		//special water moves
		addMove("Special", "Water Pisol", "Water", 80, "", 30, 0, 30, 0);
		addMove("Special", "Droplet Dart", "Water", 80, "", 20, 0, 30, 0);
		addMove("Special", "Splash", "Water", 80, "", 40, 0, 30, 0);
		addMove("Special", "Snow Throw", "Water", 60, "Freeze", 30, 0, 10, 0);
		addMove("Special", "10 Foot Wave", "Water", 70, "Drown", 40, 0, 20, 0);
		addMove("Special", "50 Leagues Under", "Water", 80, "Drown", 40, 0, 20, 0);
		addMove("Special", "Starfish Scuffle", "Water", 70, "", 90, 0, 30, 0);
		addMove("Special", "Swan Lake", "Water", 70, "Freeze", 60, 0, 10, 0);
		//gives the pokemon a 10% chance to instant kill the opponent. 
		addMove("Special", "Aqueous Bullet Hell", "Water", 1000, "", 20, 0, 5, 0);
		//kills the pokemon, but provides its entire team immunity to all debuffs for the rest of the fight
		addMove("Special", "Davy Jones Locker", "Water", 100, "", 0, 0, 3, 0);
		
		//status water moves
		addMove("Status", "Rain Shower", "Water", 100, "Cleanse", 0, 0, 5, 0);
		addMove("Status", "Iceberg", "Water", 100, "Ice Shield", 0, 0, 5, 3);
		
		//physical electric moves
		addMove("Physical", "Quick Attack", "Electric", 100, "Extra Turn", 20, 0, 20, 1);
		addMove("Physical", "Static Beat", "Electric", 100, "", 30, 0, 30, 0);
		addMove("Physical", "Volt Smack", "Electric", 100, "Daze", 30, 0, 30, 0);
		addMove("Physical", "Charged Punch", "Electric", 100, "", 60, 0, 30, 0);
		addMove("Physical", "Thunder Clap", "Electric", 100, "Daze", 50, 0, 20, 0);
		addMove("Physical", "Blackout", "Electric", 100, "Paralyze", 20, 0, 20, 0);
		addMove("Physical", "Atom Collider", "Electric", 100, "", 80, 0, 30, 0);
		addMove("Physical", "Watt Conductor", "Electric", 100, "", 70, 0, 30, 0);
		
		addMove("Physical", "BLITZED", "Electric", 100, "Extra Turn", 50, 0, 10, 3);
		//entire enemy team hit with paralysis
		addMove("Physical", "Total Blackout", "Electric", 100, "Paralyze", 20, 0, 5, 0);
		
		//special electric moves
		addMove("Special", "Zappify", "Electric", 100, "Shock", 20, 0, 20, 0);
		addMove("Special", "1000 Watts", "Electric", 100, "", 40, 0, 30, 0);
		addMove("Special", "Electrocute", "Electric", 100, "", 50, 0, 30, 0);
		addMove("Special", "Voltaic Blast", "Electric", 100, "Shock", 50, 0, 30, 0);
		addMove("Special", "Thunder Storm", "Electric", 100, "", 70, 0, 30, 0);
		addMove("Special", "Short Circuit", "Electric", 100, "Shock", 30, 0, 20, 0);
		addMove("Special", "Bolt of Light", "Electric", 100, "", 70, 0, 30, 0);
		addMove("Special", "Aftershock", "Electric", 100, "Shock", 40, 0, 10, 0);
		
		
		addMove("Special", "THOR'S WRATH", "Electric", 100, "", 0, 0, 30, 0);
		//Reverts pokemon back to its normal state at the cost of speed
		addMove("Special", "Electric Boogaloo", "Electric", 100, "", 0, 0, 10, 0);
		
		//status electric moves
		addMove("Status", "Ballroom Blitz", "Electric", 100, "Haste", 0, 0, 5, 3);
		
		
		//physical poison moves
		addMove("Physical", "Bad Breath", "Poison", 80, "", 40, 0, 30, 1);
		addMove("Physical", "Erosive Wack", "Poison", 80, "", 50, 0, 30, 1);
		addMove("Physical", "Lethal Sting", "Poison", 80, "Daze", 60, 0, 30, 1);
		addMove("Physical", "Purple Splash", "Poison", 60, "", 70, 0, 30, 1);
		addMove("Physical", "Fistful of Thorns", "Poison", 60, "Confuse", 70, 0, 30, 1);
		
		//special poison moves
		addMove("Special", "Poison Arrow", "Poison", 70, "Poison", 20, 0, 30, 1);
		addMove("Special", "Toxic Spit", "Poison", 70, "", 40, 0, 30, 1);
		addMove("Special", "Poison Cloud", "Poison", 70, "Poison", 50, 0, 30, 1);
		addMove("Special", "Toxin Mask", "Poison", 70, "Erode", 70, 0, 30, 1);
		addMove("Special", "Venomous Regurgitation", "Poison", 80, "", 60, 0, 30, 1);
		addMove("Special", "Swamp of DECAY", "Poison", 100, "Pollution", 50, 0, 30, 1);
		addMove("Special", "Disastrous Consequences", "Poison", 100, "Pollution", 100, 0, 30, 1);
		
		//status poison moves
		addMove("Status", "Twisted Thicket", "Poison", 100, "Thorns", 0, 0, 5, 3);
		
		
		
		
	}
	
	public void addMove(String attackType, String name, String type, int accuracy, String effect, int power, int dps, int pp, int timer) {
		
		if(attackType.equals("Physical")) {
			Physical move = new Physical(name, type, accuracy, effect, power, pp, timer);
			physicalMoves.put(move.name, move);
		} else if(attackType.equals("Special")) {
			Special move = new Special(name, type, accuracy, effect, power, dps, pp, timer);
			specialMoves.put(move.name, move);
		} else if(attackType.equals("Status")) {
			Status move = new Status(name, type, accuracy, effect, power, pp, timer);
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




