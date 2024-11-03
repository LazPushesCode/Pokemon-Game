package pokemon;
import pokemon.game;
import java.util.Random;

public class Poke {
	int level;
	String name;
	
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
	int base[];
	Item itemHeld;
	String type[] = {"",""};
	
	Poke(String name, String type1, String type2, int hp, int attack, int specialAttack, int defense, int specialDefense, int speed, String flag){
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
		this.type[0] = type1;
		this.type[1] = type2;
		base = new int[6];
		EV = new int[6];
		IV = new int[6];
		base[0] = hp;
		base[1] = attack;
		base[2] = specialAttack;
		base[3] = defense;
		base[4] = specialDefense;
		base[5] = speed;
		initializeStats();
		//calculate stats
		this.hp = (((2 * base[0] + IV[0] + (EV[0]/4))/100)*level) + level + 10;
		this.attack = (((2 * base[1] + IV[1] + (EV[1]/4))/100)*level) + 5;
		this.spAttack = (((2 * base[2] + IV[2] + (EV[2]/4))/100)*level) + 5;
		this.defense = (((2 * base[3] + IV[3] + (EV[3]/4))/100)*level) + 5;
		this.spDefense = (((2 * base[4] + IV[4] + (EV[4]/4))/100)*level) + 5;
		this.speed = (((2 * base[5] + IV[5] + (EV[5]/4))/100)*level) + 5;
		temphp = this.hp;
	}
	public void initializeStats() {
		Random ran = new Random();
		for(int i = 0; i < 6; i++) {
			IV[i] = ran.nextInt(0, 32);
			EV[i] = 1;
		}
	}
	
	public void updateStats() {
		this.hp = (((2 * base[0] + IV[0] + (EV[0]/4))/100)*level) + level + 10;
		this.attack = (((2 * base[1] + IV[1] + (EV[1]/4))/100)*level) + 5;
		this.spAttack = (((2 * base[2] + IV[2] + (EV[2]/4))/100)*level) + 5;
		this.defense = (((2 * base[3] + IV[3] + (EV[3]/4))/100)*level) + 5;
		this.spDefense = (((2 * base[4] + IV[4] + (EV[4]/4))/100)*level) + 5;
		this.speed = (((2 * base[5] + IV[5] + (EV[5]/4))/100)*level) + 5;
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
