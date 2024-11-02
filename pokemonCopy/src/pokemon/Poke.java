package pokemon;
import pokemon.game;


public class Poke {
	double level;
	String name;
	int hp;
	int speed;
	int attack;
	int spAttack;
	int defense;
	int spDefense;
	int EVcount;
	int EV[];
	Item itemHeld;
	String type[] = {"",""};
	
	Poke(String name, String type1, String type2, int attack, int specialAttack, int defense, int specialDefense, int speed){
		level = 0;
		this.name = name;
		this.type[0] = type1;
		this.type[1] = type2;
		this.attack = attack;
		this.spAttack = specialAttack;
		this.defense = defense;
		this.spDefense = specialDefense;
		this.speed = speed;
		this.EVcount = 0;
		EV = new int[6];
		this.hp = 0;
	}
}	

class Attack{
	String name;
	String type;
	double accuracy;
	Attack() {
		this.name = "NONE";
		this.type = "NONE";
		this.accuracy = 0;
	}
	Attack(String name, String type, double accuracy){
		this.name = name;
		this.type = type;
		this.accuracy = accuracy;
	}
}

class Normal extends Attack{
	int power;
	Normal(String name, String type, double accuracy, int power){
		super(name, type, accuracy);
		this.power = power;
	}
}

class Special extends Attack{
	String debuff;
	int dps;
	int power;
	Special(String name, String type, double accuracy, int power, int dps, String debuff){
		super(name, type, accuracy);
		this.power = power;
		this.dps = dps;
		this.debuff = debuff;
	}
}

class Status extends Attack{
	//specific effect i guess
}
