package pokemon;
import java.util.HashMap;

public class Item {
	String name;
	int amount;
	int cost;
	String description;
	
	Item(String name, int amount, int cost){
		this.name = name;
		this.amount = amount;
		this.cost = cost;
	}


	public void addDescription(String description) {
		this.description = description;
	}
	
}
class Potion extends Item{
	double HPGiven;
	
	Potion(String name, int amount, int HP, int cost) {
		super(name, amount, cost);
		HPGiven = HP;
	}
}
class Band extends Item{
	String stat;
	Band(String name, int amount, String statName, int cost){
		super(name, amount, cost);
		stat = statName;
	}
}
class Berries extends Item{
	String stat;
	Berries(String name, int amount, String statName, int cost){
		super(name, amount, cost);
		stat = statName;
	}
}
class Ball extends Item{
	double accuracy;
	Ball(String name, int amount, double accuracy, int cost){
		super(name, amount, cost);
		this.accuracy = accuracy;
	}
}
class Vitamin extends Item{
	String stat;
	Vitamin(String name, int amount, String stat, int cost){
		super(name, amount, cost);
		this.stat = stat;
	}
}
class Candy extends Item{
	int changelvl;
	Candy(String name, int amount, int changelvl, int cost){
		super(name, amount, cost);
		this.changelvl = changelvl;
	}
}


class Backpack{
	private HashMap<String, Item> items;
	public Backpack() {
		items = new HashMap<>();
	}
	public void addItem(String name, Item item) {
		if(items.containsKey(name)) {
			addExistingItem(name, item);
		} else {
			addItem(name, item);
			items.get(name).amount++;
		}
	}
	public void addExistingItem(String name, Item item) {
		items.get(name).amount++;
	}
}

class ItemShopDatabase{
	private HashMap<String, Item> items;
	public ItemShopDatabase() {
		items = new HashMap<>();
		//add Balls to Shop
		Ball pokeBall = new Ball("PokeBall", 0, 1, 200);
		pokeBall.addDescription("The basic PokeBall variant, providing a 1x chance to catch a pokemon.");
		addItem(pokeBall.name, pokeBall);
		Ball greatBall = new Ball("GreatBall", 0, 1.5, 600);
		greatBall.addDescription("The GreatBall is a variant of the PokeBall, providing a 1.5x chance to catch a pokemon.");
		addItem(greatBall.name, greatBall);
		Ball ultraBall = new Ball("UltraBall", 0, 2, 1200);
		ultraBall.addDescription("The UltraBall is a variant of the PokeBall, providing a 2.0x chance to catch a pokemon.");
		addItem(ultraBall.name, ultraBall);
		Ball masterBall = new Ball("MasterBall", 0, 255, 5000);
		masterBall.addDescription("The MasterBall is a variant of the PokeBall, that guarantees the capture of a pokemon.");
		addItem(masterBall.name, masterBall);
		
		//add Potions to shop
		Potion normalPotion = new Potion("Normal Potion", 0, 20, 300);
		normalPotion.addDescription("The basic Potion variant, providing 20 health points when given to a pokemon.");
		addItem(normalPotion.name, normalPotion);
		Potion superPotion = new Potion("Super Potion", 0, 50, 700);
		superPotion.addDescription("The Super Potion is a potion variant, providing 50 health points when given to a pokemon.");
		addItem(superPotion.name, superPotion);
		Potion hyperPotion = new Potion("Hyper Potion", 0, 200, 1500);
		hyperPotion.addDescription("The Hyper Potion is a potion variant, providing 200 health points when given to a pokemon.");
		addItem(hyperPotion.name, hyperPotion);
		Potion maxPotion = new Potion("MAX Potion", 0, 10000, 2500);
		maxPotion.addDescription("The MAX Potion is a potion variant, putting the pokemon given this item at their MAX health points");
		addItem(maxPotion.name, maxPotion);
		
		//add vitamins to shop
		Vitamin protein = new Vitamin("Protein", 0, "attack", 9800);
		protein.addDescription("The Protein Vitamin provides the given pokemon additional Attack stat points.");
		addItem(protein.name, protein);
		Vitamin iron = new Vitamin("Iron", 0, "Defense", 9800);
		iron.addDescription("The Iron Vitamin provides the given pokemon additional Defense stat points.");
		addItem(iron.name, iron);
		Vitamin calcium = new Vitamin("Calcium", 0, "spAttack", 9800);
		calcium.addDescription("The Calcium Vitamin provides the given pokemon additional sp.Attack stat points.");
		addItem(calcium.name, calcium);
		Vitamin zinc = new Vitamin("Zinc", 0, "spDefense", 9800);
		zinc.addDescription("The Zinc Vitamin provides the given pokemon additional sp.Defense stat points.");
		addItem(zinc.name, zinc);
		Vitamin carbos = new Vitamin("Carbos", 0, "speed", 9800);
		carbos.addDescription("The Carbos Vitamin provides the given pokemon additional Speed stat points.");
		addItem(carbos.name, carbos);
		
		//add bands to shop
		Band anklet = new Band("Anklet", 0, "speed", 10000);
		anklet.addDescription("The Anklet Band provides the given pokemon a boost to their Speed stat gains.");
		addItem(anklet.name, anklet);
		Band armor = new Band("Armor", 0, "spDefense", 10000);
		armor.addDescription("The Armor Band provides the given pokemon a boost to their sp.Defense stat gains.");
		addItem(armor.name, armor);
		Band belt = new Band("Belt", 0, "defense", 10000);
		belt.addDescription("The Belt Band provides the given pokemon a boost to their Defense stat gains.");
		addItem(belt.name, belt);
		Band bracer = new Band("Bracer", 0, "attack", 10000);
		bracer.addDescription("The Bracer Band provides the given pokemon a boost to their Attack stat gains.");
		addItem(bracer.name, bracer);
		Band lens = new Band("Lens", 0, "spAttack", 10000);
		lens.addDescription("The Lens Band provides the given pokemon a boost to their sp.Attack stat gains.");
		addItem(lens.name, lens);
		Band weight = new Band("Weight", 0, "hp", 10000);
		weight.addDescription("The Weight Band provides the given pokemon a boost to their HP stat gains.");
		addItem(weight.name, weight);
	}
	public void addItem(String name, Item item) {
		items.put(name, item);
	}
	public String getItemDescription(String name) {
		return items.get(name).description;
	}
	public boolean hasItem(String name) {
		return items.containsKey(name);
	}	
}