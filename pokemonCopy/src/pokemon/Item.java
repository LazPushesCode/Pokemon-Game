package pokemon;
import java.util.HashMap;
import java.util.Scanner;
import pokemon.Map;
import pokemon.Map.chunk;
import java.util.ArrayList;
import java.lang.reflect.Method;

public abstract class Item {
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
	
	public void applyItem(Poke curr, Item item, ArrayList<ArrayList<chunk>> chunks, Player user) {
		if(item instanceof Potion) {
			applyPotion(curr, (Potion) item, chunks);
		} else if(item instanceof Vitamin) {
			applyVitamin(curr, (Vitamin) item, chunks);
		} else if(item instanceof Band) {
			applyBand(curr, (Band) item, chunks);
		}
		item.decrementItem(user);
	}
	
	public void applyPotion(Poke curr, Potion item, ArrayList<ArrayList<chunk>> chunks) {
		curr.temphp += item.HPGiven;
		if(curr.temphp > curr.hp) curr.temphp = curr.hp;
	}
	
	public void applyVitamin(Poke curr, Vitamin item, ArrayList<ArrayList<chunk>> chunks) {
		curr.EV[item.stat] += 10;
	}
	
	public void applyBand(Poke curr, Band item, ArrayList<ArrayList<chunk>> chunks) {
		curr.itemHeld = item;
	}
	public void decrementItem(Player user) {
		amount--;
		if(amount == 0) user.backpack.items.remove(name);
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
	int stat;
	Band(String name, int amount, int stat, int cost){
		super(name, amount, cost);
		this.stat = stat;
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
	int stat;
	Vitamin(String name, int amount, int stat, int cost){
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
	HashMap<String, Item> items;
	int numItems = 0;
	public Backpack() {
		items = new HashMap<>();
	}
	public void addItem(String name, Item item) {
		if(numItems != 19) {
			if(items.containsKey(name)) {
				addExistingItem(name, item);
			} else {
				items.put(name, item);
				items.get(name).amount++;
				numItems++;
			}
		}
	}
	public void addExistingItem(String name, Item item) {
		items.get(name).amount++;
	}
	public int getAmount(String key) {
		return items.get(key).amount;
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
		Vitamin protein = new Vitamin("Protein", 0, 1, 9800);
		protein.addDescription("The Protein Vitamin provides the given pokemon additional Attack stat points.");
		addItem(protein.name, protein);
		Vitamin iron = new Vitamin("Iron", 0, 3, 9800);
		iron.addDescription("The Iron Vitamin provides the given pokemon additional Defense stat points.");
		addItem(iron.name, iron);
		Vitamin calcium = new Vitamin("Calcium", 0, 2, 9800);
		calcium.addDescription("The Calcium Vitamin provides the given pokemon additional sp.Attack stat points.");
		addItem(calcium.name, calcium);
		Vitamin zinc = new Vitamin("Zinc", 0, 4, 9800);
		zinc.addDescription("The Zinc Vitamin provides the given pokemon additional sp.Defense stat points.");
		addItem(zinc.name, zinc);
		Vitamin carbos = new Vitamin("Carbos", 0, 5, 9800);
		carbos.addDescription("The Carbos Vitamin provides the given pokemon additional Speed stat points.");
		addItem(carbos.name, carbos);
		
		//add bands to shop
		Band anklet = new Band("Anklet", 0, 5, 10000);
		anklet.addDescription("The Anklet Band provides the given pokemon a boost to their Speed stat gains.");
		addItem(anklet.name, anklet);
		Band armor = new Band("Armor", 0, 4, 10000);
		armor.addDescription("The Armor Band provides the given pokemon a boost to their sp.Defense stat gains.");
		addItem(armor.name, armor);
		Band belt = new Band("Belt", 0, 3, 10000);
		belt.addDescription("The Belt Band provides the given pokemon a boost to their Defense stat gains.");
		addItem(belt.name, belt);
		Band bracer = new Band("Bracer", 0, 1, 10000);
		bracer.addDescription("The Bracer Band provides the given pokemon a boost to their Attack stat gains.");
		addItem(bracer.name, bracer);
		Band lens = new Band("Lens", 0, 2, 10000);
		lens.addDescription("The Lens Band provides the given pokemon a boost to their sp.Attack stat gains.");
		addItem(lens.name, lens);
		Band weight = new Band("Weight", 0, 0, 10000);
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
	public Item returnItem(String key) {
		return items.get(key);
	}
	
	
	public void pokeStopMenu(Player user, String area, ItemShopDatabase items, ArrayList<ArrayList<chunk>> chunks, Map map) {
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
		char positions[] = {' ', ' ', ' ', ' ', ' ', ' '};
		int pos = 0;
		while(true) {
			if(input.equals("c")) {
				if(pos == 0) {
					game.spaces();
					map.mapDialogue("Alright! Hand me your PokeBalls and I'll get them ready for healing!        ");
					map.printSimpleMap();
					scan.next();
					game.spaces();
					user.healAllPokemon();
					map.mapDialogue("Inserting PokeBalls... Preparing healing machine... Healing aaaaaand... DONE!");
					map.printSimpleMap();
					scan.next();
					game.spaces();
					map.mapDialogue("Your team is all healed!               ");
					map.printSimpleMap();
					scan.next();
					input = "";
				}
				if (pos == 1){
					input = "";
					positions[pos] = ' ';
					pos = 0;
					positions[pos] = '>';
					map.mapDialogue("");
					int bar = 5;
																		chunks.get(0).get(5).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(6).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(7).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(8).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(9).grid[0] = "~~~~~~~~~~~~~~~~~~";chunks.get(0).get(10).grid[0] = "~~~~~~~~~~~~~~~~~~";
					chunks.get(0).get(4).grid[1] = "                {|";chunks.get(0).get(5).grid[1] = "                  ";chunks.get(0).get(6).grid[1] = "                  ";chunks.get(0).get(7).grid[1] = "                  ";chunks.get(4).get(8).grid[0] = "                  ";chunks.get(0).get(9).grid[1] = "                  ";chunks.get(0).get(10).grid[1] = "                  ";
					chunks.get(0).get(4).grid[2] = "                {|";chunks.get(0).get(5).grid[2] = "    Poke-Balls    ";chunks.get(0).get(6).grid[2] = "     Potions      ";chunks.get(0).get(7).grid[2] = "     Vitamins     ";chunks.get(0).get(8).grid[2] = "      Bands       ";chunks.get(0).get(9).grid[2] = "     Berries      ";chunks.get(0).get(10).grid[2] = "      Other       ";
					chunks.get(0).get(4).grid[3] = "                {|";chunks.get(0).get(5).grid[3] = "                  ";chunks.get(0).get(6).grid[3] = "                  ";chunks.get(0).get(7).grid[3] = "                  ";chunks.get(0).get(8).grid[3] = "                  ";chunks.get(0).get(9).grid[3] = "                  ";chunks.get(0).get(10).grid[3] = "                  ";
					chunks.get(0).get(4).grid[4] = "                {|";chunks.get(0).get(5).grid[4] = "                  ";chunks.get(0).get(6).grid[4] = "                  ";chunks.get(0).get(7).grid[4] = "                  ";chunks.get(0).get(8).grid[4] = "                  ";chunks.get(0).get(9).grid[4] = "                  ";chunks.get(0).get(10).grid[4] = "                  ";
					chunks.get(1).get(4).grid[0] = "                {|";chunks.get(1).get(5).grid[0] = "                  ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                  ";chunks.get(1).get(8).grid[0] = "                  ";chunks.get(1).get(9).grid[0] = "                  ";chunks.get(1).get(10).grid[0] = "                  ";
					chunks.get(1).get(4).grid[1] = "                {|";chunks.get(1).get(5).grid[1] = "                  ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                  ";chunks.get(1).get(8).grid[1] = "                  ";chunks.get(1).get(9).grid[1] = "                  ";chunks.get(1).get(10).grid[1] = "                  ";
					chunks.get(1).get(4).grid[2] = "                {|";chunks.get(1).get(5).grid[2] = "                  ";chunks.get(1).get(6).grid[2] = "                  ";chunks.get(1).get(7).grid[2] = "                  ";chunks.get(1).get(8).grid[2] = "                  ";chunks.get(1).get(9).grid[2] = "                  ";chunks.get(1).get(10).grid[2] = "                  ";
					chunks.get(1).get(4).grid[3] = "                {|";chunks.get(1).get(5).grid[3] = "                  ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                  ";chunks.get(1).get(8).grid[3] = "                  ";chunks.get(1).get(9).grid[3] = "                  ";chunks.get(1).get(10).grid[3] = "                  ";
					chunks.get(1).get(4).grid[4] = "                {|";chunks.get(1).get(5).grid[4] = "                  ";chunks.get(1).get(6).grid[4] = "                  ";chunks.get(1).get(7).grid[4] = "                  ";chunks.get(1).get(8).grid[4] = "                  ";chunks.get(1).get(9).grid[4] = "                  ";chunks.get(1).get(10).grid[4] = "                  ";
					chunks.get(2).get(4).grid[0] = "                {|";chunks.get(2).get(5).grid[0] = "                  ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                  ";chunks.get(2).get(8).grid[0] = "                  ";chunks.get(2).get(9).grid[0] = "                  ";chunks.get(2).get(10).grid[0] = "                  ";
					chunks.get(2).get(4).grid[1] = "                {|";chunks.get(2).get(5).grid[1] = "                  ";chunks.get(2).get(6).grid[1] = "                  ";chunks.get(2).get(7).grid[1] = "                  ";chunks.get(2).get(8).grid[1] = "                  ";chunks.get(2).get(9).grid[1] = "                  ";chunks.get(2).get(10).grid[1] = "                  ";
					chunks.get(2).get(4).grid[2] = "                {|";chunks.get(2).get(5).grid[2] = "                  ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                  ";chunks.get(2).get(8).grid[2] = "                  ";chunks.get(2).get(9).grid[2] = "                  ";chunks.get(2).get(10).grid[2] = "                  ";
					chunks.get(2).get(4).grid[3] = "                {|";chunks.get(2).get(5).grid[3] = "                  ";chunks.get(2).get(6).grid[3] = "                  ";chunks.get(2).get(7).grid[3] = "                  ";chunks.get(2).get(8).grid[3] = "                  ";chunks.get(2).get(9).grid[3] = "                  ";chunks.get(2).get(10).grid[3] = "                  ";
					chunks.get(2).get(4).grid[4] = "                {|";chunks.get(2).get(5).grid[4] = "                  ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                  ";chunks.get(2).get(8).grid[4] = "                  ";chunks.get(2).get(9).grid[4] = "                  ";chunks.get(2).get(10).grid[4] = "                  ";
					chunks.get(3).get(4).grid[0] = "                {|";chunks.get(3).get(5).grid[0] = "                  ";chunks.get(3).get(6).grid[0] = "                  ";chunks.get(3).get(7).grid[0] = "                  ";chunks.get(3).get(8).grid[0] = "                  ";chunks.get(3).get(9).grid[0] = "                  ";chunks.get(3).get(10).grid[0] = "                  ";
					chunks.get(3).get(4).grid[1] = "                {|";chunks.get(3).get(5).grid[1] = "                  ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                  ";chunks.get(3).get(8).grid[1] = "                  ";chunks.get(3).get(9).grid[1] = "                  ";chunks.get(3).get(10).grid[1] = "                  ";
					chunks.get(3).get(4).grid[2] = "                {|";chunks.get(3).get(5).grid[2] = "                  ";chunks.get(3).get(6).grid[2] = "                  ";chunks.get(3).get(7).grid[2] = "                  ";chunks.get(3).get(8).grid[2] = "                  ";chunks.get(3).get(9).grid[2] = "                  ";chunks.get(3).get(10).grid[2] = "                  ";
					chunks.get(3).get(4).grid[3] = "                {|";chunks.get(3).get(5).grid[3] = "                  ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                  ";chunks.get(3).get(8).grid[3] = "                  ";chunks.get(3).get(9).grid[3] = "                  ";chunks.get(3).get(10).grid[3] = "                  ";
					chunks.get(3).get(4).grid[4] = "                {|";chunks.get(3).get(5).grid[4] = "                  ";chunks.get(3).get(6).grid[4] = "                  ";chunks.get(3).get(7).grid[4] = "                  ";chunks.get(3).get(8).grid[4] = "                  ";chunks.get(3).get(9).grid[4] = "                  ";chunks.get(3).get(10).grid[4] = "                  ";
					chunks.get(4).get(4).grid[0] = "                {|";chunks.get(4).get(5).grid[0] = "                  ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                  ";chunks.get(4).get(8).grid[0] = "                  ";chunks.get(4).get(9).grid[0] = "                  ";chunks.get(4).get(10).grid[0] = "                  ";
					chunks.get(4).get(4).grid[1] = "                {|";chunks.get(4).get(5).grid[1] = "                  ";chunks.get(4).get(6).grid[1] = "                  ";chunks.get(4).get(7).grid[1] = "                  ";chunks.get(4).get(8).grid[1] = "                  ";chunks.get(4).get(9).grid[1] = "                  ";chunks.get(4).get(10).grid[1] = "                  ";
					                                                    chunks.get(4).get(5).grid[2] = "~~~~~~~~~~~~~~~~~~";chunks.get(4).get(6).grid[2] = "~~~~~~~~~~~~~~~~~~";chunks.get(4).get(7).grid[2] = "~~~~~~~~~~~~~~~~~~";chunks.get(4).get(8).grid[2] = "~~~~~~~~~~~~~~~~~~";chunks.get(4).get(9).grid[2] = "~~~~~~~~~~~~~~~~~~";chunks.get(4).get(10).grid[2] = "~~~~~~~~~~~~~~~~~~";
					                                                
					while(true) {
						String itemName = " ";
						if(bar == 5) {
							if(input.equals("w") && pos != 0) {
								positions[pos] = ' ';
								pos--;
								positions[pos] = '>';
							} 
							if(input.equals("s") && pos != 3) {
								positions[pos] = ' ';
								pos++;
								positions[pos] = '>';
							}
							if(input.equals("c")) {
								if(user.backpack.numItems != 19) {
									if(pos == 0 && Player.gold >= 200) {
										user.backpack.addItem("PokeBall", items.returnItem("PokeBall")); Player.gold -= 200; itemName = "PokeBall";
									}
									if(pos == 1 && Player.gold >= 600) {
										user.backpack.addItem("GreatBall", items.returnItem("GreatBall")); itemName = "GreatBall";
										Player.gold -= 600;
									}
									if(pos == 2 && Player.gold >= 1200) {
										user.backpack.addItem("UltraBall", items.returnItem("UltraBall")); itemName = "UltraBall";
										Player.gold -= 1200;
									}
									if(pos == 3 && Player.gold >= 5000) {
										user.backpack.addItem("MasterBall", items.returnItem("MasterBall")); itemName = "MasterBall";
										Player.gold -= 5000;
									}
									if(itemName.equals(" ") != true) {
										map.mapDialogue(itemName + " has been added to your backpack!                        ");
									} else {
										map.mapDialogue("Insufficient funds!                  ");
									}
								}
							}
							chunks.get(1).get(4).grid[0] = "                {|";chunks.get(1).get(5).grid[0] = "                  ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                  ";chunks.get(1).get(8).grid[0] = "                  ";chunks.get(1).get(9).grid[0] = "                  ";chunks.get(1).get(10).grid[0] = "                  ";
							chunks.get(1).get(4).grid[1] = "                {|";chunks.get(1).get(5).grid[1] = "                  ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                  ";chunks.get(1).get(8).grid[1] = "                  ";chunks.get(1).get(9).grid[1] = "                  ";chunks.get(1).get(10).grid[1] = "                  ";
							chunks.get(1).get(4).grid[2] = "                {|";chunks.get(1).get(5).grid[2] = positions[0] + "  PokeBall       ";chunks.get(1).get(6).grid[2] = "                  ";chunks.get(1).get(7).grid[2] = "   $200           ";chunks.get(1).get(8).grid[2] = "                  ";chunks.get(1).get(9).grid[2] = "                  ";chunks.get(1).get(10).grid[2] = "                  ";
							chunks.get(1).get(4).grid[3] = "                {|";chunks.get(1).get(5).grid[3] = "                  ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                  ";chunks.get(1).get(8).grid[3] = "                  ";chunks.get(1).get(9).grid[3] = "                  ";chunks.get(1).get(10).grid[3] = "                  ";
							chunks.get(1).get(4).grid[4] = "                {|";chunks.get(1).get(5).grid[4] = "                  ";chunks.get(1).get(6).grid[4] = "                  ";chunks.get(1).get(7).grid[4] = "                  ";chunks.get(1).get(8).grid[4] = "                  ";chunks.get(1).get(9).grid[4] = "                  ";chunks.get(1).get(10).grid[4] = "                  ";
							chunks.get(2).get(4).grid[0] = "                {|";chunks.get(2).get(5).grid[0] = "                  ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                  ";chunks.get(2).get(8).grid[0] = "                  ";chunks.get(2).get(9).grid[0] = "                  ";chunks.get(2).get(10).grid[0] = "                  ";
							chunks.get(2).get(4).grid[1] = "                {|";chunks.get(2).get(5).grid[1] = positions[1] + "  GreatBall      ";chunks.get(2).get(6).grid[1] = "                  ";chunks.get(2).get(7).grid[1] = "   $600           ";chunks.get(2).get(8).grid[1] = "                  ";chunks.get(2).get(9).grid[1] = "                  ";chunks.get(2).get(10).grid[1] = "                  ";
							chunks.get(2).get(4).grid[2] = "                {|";chunks.get(2).get(5).grid[2] = "                  ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                  ";chunks.get(2).get(8).grid[2] = "                  ";chunks.get(2).get(9).grid[2] = "                  ";chunks.get(2).get(10).grid[2] = "                  ";
							chunks.get(2).get(4).grid[3] = "                {|";chunks.get(2).get(5).grid[3] = "                  ";chunks.get(2).get(6).grid[3] = "                  ";chunks.get(2).get(7).grid[3] = "                  ";chunks.get(2).get(8).grid[3] = "                  ";chunks.get(2).get(9).grid[3] = "                  ";chunks.get(2).get(10).grid[3] = "                  ";
							chunks.get(2).get(4).grid[4] = "                {|";chunks.get(2).get(5).grid[4] = "                  ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                  ";chunks.get(2).get(8).grid[4] = "                  ";chunks.get(2).get(9).grid[4] = "                  ";chunks.get(2).get(10).grid[4] = "                  ";
							chunks.get(3).get(4).grid[0] = "                {|";chunks.get(3).get(5).grid[0] = positions[2] + "  UltraBall      ";chunks.get(3).get(6).grid[0] = "                  ";chunks.get(3).get(7).grid[0] = "   $1200          ";chunks.get(3).get(8).grid[0] = "                  ";chunks.get(3).get(9).grid[0] = "                  ";chunks.get(3).get(10).grid[0] = "                  ";
							chunks.get(3).get(4).grid[1] = "                {|";chunks.get(3).get(5).grid[1] = "                  ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                  ";chunks.get(3).get(8).grid[1] = "                  ";chunks.get(3).get(9).grid[1] = "                  ";chunks.get(3).get(10).grid[1] = "                  ";
							chunks.get(3).get(4).grid[2] = "                {|";chunks.get(3).get(5).grid[2] = "                  ";chunks.get(3).get(6).grid[2] = "                  ";chunks.get(3).get(7).grid[2] = "                  ";chunks.get(3).get(8).grid[2] = "                  ";chunks.get(3).get(9).grid[2] = "                  ";chunks.get(3).get(10).grid[2] = "                  ";
							chunks.get(3).get(4).grid[3] = "                {|";chunks.get(3).get(5).grid[3] = "                  ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                  ";chunks.get(3).get(8).grid[3] = "                  ";chunks.get(3).get(9).grid[3] = "                  ";chunks.get(3).get(10).grid[3] = "                  ";
							chunks.get(3).get(4).grid[4] = "                {|";chunks.get(3).get(5).grid[4] = positions[3] + "  MasterBall     ";chunks.get(3).get(6).grid[4] = "                  ";chunks.get(3).get(7).grid[4] = "   $5000          ";chunks.get(3).get(8).grid[4] = "                  ";chunks.get(3).get(9).grid[4] = "                  ";chunks.get(3).get(10).grid[4] = "                  ";
							chunks.get(4).get(4).grid[0] = "                {|";chunks.get(4).get(5).grid[0] = "                  ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                  ";chunks.get(4).get(8).grid[0] = "                  ";chunks.get(4).get(9).grid[0] = "                  ";chunks.get(4).get(10).grid[0] = "                  ";
							chunks.get(4).get(4).grid[1] = "                {|";chunks.get(4).get(5).grid[1] = "                  ";chunks.get(4).get(6).grid[1] = "                  ";chunks.get(4).get(7).grid[1] = "                  ";chunks.get(4).get(8).grid[1] = "                  ";chunks.get(4).get(9).grid[1] = "                  ";chunks.get(4).get(10).grid[1] = "                  ";
						} 
						if(bar == 6) {
							if(input.equals("w") && pos != 0) {
								positions[pos] = ' ';
								pos--;
								positions[pos] = '>';
							} 
							if(input.equals("s") && pos != 3) {
								positions[pos] = ' ';
								pos++;
								positions[pos] = '>';
							}
							if(input.equals("c")) {
								if(user.backpack.numItems != 19) {
									if(pos == 0 && Player.gold >= 300) {
										user.backpack.addItem("Normal Potion", items.returnItem("Normal Potion")); Player.gold -= 300; itemName = "Normal Potion";
									}
									if(pos == 1 && Player.gold >= 700) {
										user.backpack.addItem("Super Potion", items.returnItem("Super Potion")); Player.gold -= 700; itemName = "Super Potion";
									}
									if(pos == 2 && Player.gold >= 1500) {
										user.backpack.addItem("Hyper Potion", items.returnItem("Hyper Potion")); Player.gold -= 1500; itemName = "Hyper Potion";
									}
									if(pos == 3 && Player.gold >= 25000) {
										user.backpack.addItem("MAX Potion", items.returnItem("MAX Potion")); Player.gold -= 2500; itemName = "MAX Potion";
									}
									if(itemName.equals(" ") != true) {
										map.mapDialogue(itemName + " has been added to your backpack!                      ");
									} else {
										map.mapDialogue("Insufficient funds!                  ");
									}
								}
							}
							chunks.get(1).get(4).grid[0] = "                {|";chunks.get(1).get(5).grid[0] = "                  ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                  ";chunks.get(1).get(8).grid[0] = "                  ";chunks.get(1).get(9).grid[0] = "                  ";chunks.get(1).get(10).grid[0] = "                  ";
							chunks.get(1).get(4).grid[1] = "                {|";chunks.get(1).get(5).grid[1] = "                  ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                  ";chunks.get(1).get(8).grid[1] = "                  ";chunks.get(1).get(9).grid[1] = "                  ";chunks.get(1).get(10).grid[1] = "                  ";
							chunks.get(1).get(4).grid[2] = "                {|";chunks.get(1).get(5).grid[2] = positions[0] + "  Normal Potion  ";chunks.get(1).get(6).grid[2] = "                  ";chunks.get(1).get(7).grid[2] = "   $300           ";chunks.get(1).get(8).grid[2] = "                  ";chunks.get(1).get(9).grid[2] = "                  ";chunks.get(1).get(10).grid[2] = "                  ";
							chunks.get(1).get(4).grid[3] = "                {|";chunks.get(1).get(5).grid[3] = "                  ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                  ";chunks.get(1).get(8).grid[3] = "                  ";chunks.get(1).get(9).grid[3] = "                  ";chunks.get(1).get(10).grid[3] = "                  ";
							chunks.get(1).get(4).grid[4] = "                {|";chunks.get(1).get(5).grid[4] = "                  ";chunks.get(1).get(6).grid[4] = "                  ";chunks.get(1).get(7).grid[4] = "                  ";chunks.get(1).get(8).grid[4] = "                  ";chunks.get(1).get(9).grid[4] = "                  ";chunks.get(1).get(10).grid[4] = "                  ";
							chunks.get(2).get(4).grid[0] = "                {|";chunks.get(2).get(5).grid[0] = "                  ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                  ";chunks.get(2).get(8).grid[0] = "                  ";chunks.get(2).get(9).grid[0] = "                  ";chunks.get(2).get(10).grid[0] = "                  ";
							chunks.get(2).get(4).grid[1] = "                {|";chunks.get(2).get(5).grid[1] = positions[1] + "  Super Potion   ";chunks.get(2).get(6).grid[1] = "                  ";chunks.get(2).get(7).grid[1] = "   $700           ";chunks.get(2).get(8).grid[1] = "                  ";chunks.get(2).get(9).grid[1] = "                  ";chunks.get(2).get(10).grid[1] = "                  ";
							chunks.get(2).get(4).grid[2] = "                {|";chunks.get(2).get(5).grid[2] = "                  ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                  ";chunks.get(2).get(8).grid[2] = "                  ";chunks.get(2).get(9).grid[2] = "                  ";chunks.get(2).get(10).grid[2] = "                  ";
							chunks.get(2).get(4).grid[3] = "                {|";chunks.get(2).get(5).grid[3] = "                  ";chunks.get(2).get(6).grid[3] = "                  ";chunks.get(2).get(7).grid[3] = "                  ";chunks.get(2).get(8).grid[3] = "                  ";chunks.get(2).get(9).grid[3] = "                  ";chunks.get(2).get(10).grid[3] = "                  ";
							chunks.get(2).get(4).grid[4] = "                {|";chunks.get(2).get(5).grid[4] = "                  ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                  ";chunks.get(2).get(8).grid[4] = "                  ";chunks.get(2).get(9).grid[4] = "                  ";chunks.get(2).get(10).grid[4] = "                  ";
							chunks.get(3).get(4).grid[0] = "                {|";chunks.get(3).get(5).grid[0] = positions[2] + "  Hyper Potion   ";chunks.get(3).get(6).grid[0] = "                  ";chunks.get(3).get(7).grid[0] = "   $1500          ";chunks.get(3).get(8).grid[0] = "                  ";chunks.get(3).get(9).grid[0] = "                  ";chunks.get(3).get(10).grid[0] = "                  ";
							chunks.get(3).get(4).grid[1] = "                {|";chunks.get(3).get(5).grid[1] = "                  ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                  ";chunks.get(3).get(8).grid[1] = "                  ";chunks.get(3).get(9).grid[1] = "                  ";chunks.get(3).get(10).grid[1] = "                  ";
							chunks.get(3).get(4).grid[2] = "                {|";chunks.get(3).get(5).grid[2] = "                  ";chunks.get(3).get(6).grid[2] = "                  ";chunks.get(3).get(7).grid[2] = "                  ";chunks.get(3).get(8).grid[2] = "                  ";chunks.get(3).get(9).grid[2] = "                  ";chunks.get(3).get(10).grid[2] = "                  ";
							chunks.get(3).get(4).grid[3] = "                {|";chunks.get(3).get(5).grid[3] = "                  ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                  ";chunks.get(3).get(8).grid[3] = "                  ";chunks.get(3).get(9).grid[3] = "                  ";chunks.get(3).get(10).grid[3] = "                  ";
							chunks.get(3).get(4).grid[4] = "                {|";chunks.get(3).get(5).grid[4] = positions[3] + "  MAX Potion     ";chunks.get(3).get(6).grid[4] = "                  ";chunks.get(3).get(7).grid[4] = "   $2500          ";chunks.get(3).get(8).grid[4] = "                  ";chunks.get(3).get(9).grid[4] = "                  ";chunks.get(3).get(10).grid[4] = "                  ";
							chunks.get(4).get(4).grid[0] = "                {|";chunks.get(4).get(5).grid[0] = "                  ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                  ";chunks.get(4).get(8).grid[0] = "                  ";chunks.get(4).get(9).grid[0] = "                  ";chunks.get(4).get(10).grid[0] = "                  ";
							chunks.get(4).get(4).grid[1] = "                {|";chunks.get(4).get(5).grid[1] = "                  ";chunks.get(4).get(6).grid[1] = "                  ";chunks.get(4).get(7).grid[1] = "                  ";chunks.get(4).get(8).grid[1] = "                  ";chunks.get(4).get(9).grid[1] = "                  ";chunks.get(4).get(10).grid[1] = "                  ";
						}	
						if(bar == 7 ) {
							if(input.equals("w") && pos != 0) {
								positions[pos] = ' ';
								pos--;
								positions[pos] = '>';
							} 
							if(input.equals("s") && pos != 5) {
								positions[pos] = ' ';
								pos++;
								positions[pos] = '>';
							}
							if(input.equals("c")) {
								if(user.backpack.numItems != 19) {
									if(pos == 0 && Player.gold >= 9800) {
										user.backpack.addItem("Protein", items.returnItem("Protein")); Player.gold -= 9800; itemName = "Protein";
									}
									if(pos == 1 && Player.gold >= 9800) {
										user.backpack.addItem("Iron", items.returnItem("Iron")); Player.gold -= 9800; itemName = "Iron";
									}
									if(pos == 2 && Player.gold >= 9800) {
										user.backpack.addItem("Calcium", items.returnItem("Calcium")); Player.gold -= 9800; itemName = "Calcium";
									}
									if(pos == 3 && Player.gold >= 9800) {
										user.backpack.addItem("Zinc", items.returnItem("Zinc")); Player.gold -= 9800; itemName = "Zinc";
									}
									if(pos == 4 && Player.gold >= 9800) {
										user.backpack.addItem("Carbos", items.returnItem("Carbos")); Player.gold -= 9800; itemName = "Carbos";
									}
									if(pos == 5);
									if(itemName.equals(" ") != true) {
										map.mapDialogue(itemName + " has been added to your backpack!                       ");
									} else {
										map.mapDialogue("Insufficient funds!                  ");
									}
								}
							}
							chunks.get(1).get(4).grid[0] = "                {|";chunks.get(1).get(5).grid[0] = "                  ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                  ";chunks.get(1).get(8).grid[0] = "                  ";chunks.get(1).get(9).grid[0] = "                  ";chunks.get(1).get(10).grid[0] = "                  ";
							chunks.get(1).get(4).grid[1] = "                {|";chunks.get(1).get(5).grid[1] = "                  ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                  ";chunks.get(1).get(8).grid[1] = "                  ";chunks.get(1).get(9).grid[1] = "                  ";chunks.get(1).get(10).grid[1] = "                  ";
							chunks.get(1).get(4).grid[2] = "                {|";chunks.get(1).get(5).grid[2] = positions[0] + "  Protein        ";chunks.get(1).get(6).grid[2] = "                  ";chunks.get(1).get(7).grid[2] = "   $9800          ";chunks.get(1).get(8).grid[2] = "                  ";chunks.get(1).get(9).grid[2] = "                  ";chunks.get(1).get(10).grid[2] = "                  ";
							chunks.get(1).get(4).grid[3] = "                {|";chunks.get(1).get(5).grid[3] = "                  ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                  ";chunks.get(1).get(8).grid[3] = "                  ";chunks.get(1).get(9).grid[3] = "                  ";chunks.get(1).get(10).grid[3] = "                  ";
							chunks.get(1).get(4).grid[4] = "                {|";chunks.get(1).get(5).grid[4] = positions[1] + "  Iron           ";chunks.get(1).get(6).grid[4] = "                  ";chunks.get(1).get(7).grid[4] = "   $9800          ";chunks.get(1).get(8).grid[4] = "                  ";chunks.get(1).get(9).grid[4] = "                  ";chunks.get(1).get(10).grid[4] = "                  ";
							chunks.get(2).get(4).grid[0] = "                {|";chunks.get(2).get(5).grid[0] = "                  ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                  ";chunks.get(2).get(8).grid[0] = "                  ";chunks.get(2).get(9).grid[0] = "                  ";chunks.get(2).get(10).grid[0] = "                  ";
							chunks.get(2).get(4).grid[1] = "                {|";chunks.get(2).get(5).grid[1] = positions[2] + "  Calcium        ";chunks.get(2).get(6).grid[1] = "                  ";chunks.get(2).get(7).grid[1] = "   $9800          ";chunks.get(2).get(8).grid[1] = "                  ";chunks.get(2).get(9).grid[1] = "                  ";chunks.get(2).get(10).grid[1] = "                  ";
							chunks.get(2).get(4).grid[2] = "                {|";chunks.get(2).get(5).grid[2] = "                  ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                  ";chunks.get(2).get(8).grid[2] = "                  ";chunks.get(2).get(9).grid[2] = "                  ";chunks.get(2).get(10).grid[2] = "                  ";
							chunks.get(2).get(4).grid[3] = "                {|";chunks.get(2).get(5).grid[3] = positions[3] + "  Zinc           ";chunks.get(2).get(6).grid[3] = "                  ";chunks.get(2).get(7).grid[3] = "   $9800          ";chunks.get(2).get(8).grid[3] = "                  ";chunks.get(2).get(9).grid[3] = "                  ";chunks.get(2).get(10).grid[3] = "                  ";
							chunks.get(2).get(4).grid[4] = "                {|";chunks.get(2).get(5).grid[4] = "                  ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                  ";chunks.get(2).get(8).grid[4] = "                  ";chunks.get(2).get(9).grid[4] = "                  ";chunks.get(2).get(10).grid[4] = "                  ";
							chunks.get(3).get(4).grid[0] = "                {|";chunks.get(3).get(5).grid[0] = positions[4] + "  Carbos         ";chunks.get(3).get(6).grid[0] = "                  ";chunks.get(3).get(7).grid[0] = "   $9800          ";chunks.get(3).get(8).grid[0] = "                  ";chunks.get(3).get(9).grid[0] = "                  ";chunks.get(3).get(10).grid[0] = "                  ";
							chunks.get(3).get(4).grid[1] = "                {|";chunks.get(3).get(5).grid[1] = "                  ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                  ";chunks.get(3).get(8).grid[1] = "                  ";chunks.get(3).get(9).grid[1] = "                  ";chunks.get(3).get(10).grid[1] = "                  ";
							chunks.get(3).get(4).grid[2] = "                {|";chunks.get(3).get(5).grid[2] = positions[5] + "                 ";chunks.get(3).get(6).grid[2] = "                  ";chunks.get(3).get(7).grid[2] = "   $9800          ";chunks.get(3).get(8).grid[2] = "                  ";chunks.get(3).get(9).grid[2] = "                  ";chunks.get(3).get(10).grid[2] = "                  ";
							chunks.get(3).get(4).grid[3] = "                {|";chunks.get(3).get(5).grid[3] = "                  ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                  ";chunks.get(3).get(8).grid[3] = "                  ";chunks.get(3).get(9).grid[3] = "                  ";chunks.get(3).get(10).grid[3] = "                  ";
							chunks.get(3).get(4).grid[4] = "                {|";chunks.get(3).get(5).grid[4] = "                  ";chunks.get(3).get(6).grid[4] = "                  ";chunks.get(3).get(7).grid[4] = "                  ";chunks.get(3).get(8).grid[4] = "                  ";chunks.get(3).get(9).grid[4] = "                  ";chunks.get(3).get(10).grid[4] = "                  ";
							chunks.get(4).get(4).grid[0] = "                {|";chunks.get(4).get(5).grid[0] = "                  ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                  ";chunks.get(4).get(8).grid[0] = "                  ";chunks.get(4).get(9).grid[0] = "                  ";chunks.get(4).get(10).grid[0] = "                  ";
							chunks.get(4).get(4).grid[1] = "                {|";chunks.get(4).get(5).grid[1] = "                  ";chunks.get(4).get(6).grid[1] = "                  ";chunks.get(4).get(7).grid[1] = "                  ";chunks.get(4).get(8).grid[1] = "                  ";chunks.get(4).get(9).grid[1] = "                  ";chunks.get(4).get(10).grid[1] = "                  ";
						}
						if(bar == 8) {
							if(input.equals("w") && pos != 0) {
								positions[pos] = ' ';
								pos--;
								positions[pos] = '>';
							} 
							if(input.equals("s") && pos != 5) {
								positions[pos] = ' ';
								pos++;
								positions[pos] = '>';
							}
							if(input.equals("c")) {
								if(user.backpack.numItems != 19) {
									if(pos == 0 && Player.gold >= 10000) {
										user.backpack.addItem("Anklet", items.returnItem("Anklet")); Player.gold -= 10000; itemName = "Anklet";
									}
									if(pos == 1 && Player.gold >= 10000) {
										user.backpack.addItem("Armor", items.returnItem("Armor")); Player.gold -= 10000; itemName = "Armor";
									}
									if(pos == 2 && Player.gold >= 10000) {
										user.backpack.addItem("Belt", items.returnItem("Belt")); Player.gold -= 10000; itemName = "Belt";
									}
									if(pos == 3 && Player.gold >= 10000) {
										user.backpack.addItem("Bracer", items.returnItem("Bracer")); Player.gold -= 10000; itemName = "Bracer";
									}
									if(pos == 4 && Player.gold >= 10000) {
										user.backpack.addItem("Lens", items.returnItem("Lens")); Player.gold -= 10000; itemName = "Lens";
									}
									if(pos == 5 && Player.gold >= 10000) {
										user.backpack.addItem("Weight", items.returnItem("Weight")); Player.gold -= 10000; itemName = "Weight";
									}
									if(itemName.equals(" ") != true) {
										map.mapDialogue(itemName + " has been added to your backpack!                     ");
									} else {
										map.mapDialogue("Insufficient funds!                  ");
									}
								}
							}
							chunks.get(1).get(4).grid[0] = "                {|";chunks.get(1).get(5).grid[0] = "                  ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                  ";chunks.get(1).get(8).grid[0] = "                  ";chunks.get(1).get(9).grid[0] = "                  ";chunks.get(1).get(10).grid[0] = "                  ";
							chunks.get(1).get(4).grid[1] = "                {|";chunks.get(1).get(5).grid[1] = "                  ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                  ";chunks.get(1).get(8).grid[1] = "                  ";chunks.get(1).get(9).grid[1] = "                  ";chunks.get(1).get(10).grid[1] = "                  ";
							chunks.get(1).get(4).grid[2] = "                {|";chunks.get(1).get(5).grid[2] = positions[0] + "  Anklet         ";chunks.get(1).get(6).grid[2] = "   $10000         ";chunks.get(1).get(7).grid[2] = "                  ";chunks.get(1).get(8).grid[2] = "                  ";chunks.get(1).get(9).grid[2] = "                  ";chunks.get(1).get(10).grid[2] = "                  ";
							chunks.get(1).get(4).grid[3] = "                {|";chunks.get(1).get(5).grid[3] = "                  ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                  ";chunks.get(1).get(8).grid[3] = "                  ";chunks.get(1).get(9).grid[3] = "                  ";chunks.get(1).get(10).grid[3] = "                  ";
							chunks.get(1).get(4).grid[4] = "                {|";chunks.get(1).get(5).grid[4] = positions[1] + "  Armor          ";chunks.get(1).get(6).grid[4] = "   $10000         ";chunks.get(1).get(7).grid[4] = "                  ";chunks.get(1).get(8).grid[4] = "                  ";chunks.get(1).get(9).grid[4] = "                  ";chunks.get(1).get(10).grid[4] = "                  ";
							chunks.get(2).get(4).grid[0] = "                {|";chunks.get(2).get(5).grid[0] = "                  ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                  ";chunks.get(2).get(8).grid[0] = "                  ";chunks.get(2).get(9).grid[0] = "                  ";chunks.get(2).get(10).grid[0] = "                  ";
							chunks.get(2).get(4).grid[1] = "                {|";chunks.get(2).get(5).grid[1] = positions[2] + "  Belt           ";chunks.get(2).get(6).grid[1] = "   $10000         ";chunks.get(2).get(7).grid[1] = "                  ";chunks.get(2).get(8).grid[1] = "                  ";chunks.get(2).get(9).grid[1] = "                  ";chunks.get(2).get(10).grid[1] = "                  ";
							chunks.get(2).get(4).grid[2] = "                {|";chunks.get(2).get(5).grid[2] = "                  ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                  ";chunks.get(2).get(8).grid[2] = "                  ";chunks.get(2).get(9).grid[2] = "                  ";chunks.get(2).get(10).grid[2] = "                  ";
							chunks.get(2).get(4).grid[3] = "                {|";chunks.get(2).get(5).grid[3] = positions[3] + "  Bracer         ";chunks.get(2).get(6).grid[3] = "   $10000         ";chunks.get(2).get(7).grid[3] = "                  ";chunks.get(2).get(8).grid[3] = "                  ";chunks.get(2).get(9).grid[3] = "                  ";chunks.get(2).get(10).grid[3] = "                  ";
							chunks.get(2).get(4).grid[4] = "                {|";chunks.get(2).get(5).grid[4] = "                  ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                  ";chunks.get(2).get(8).grid[4] = "                  ";chunks.get(2).get(9).grid[4] = "                  ";chunks.get(2).get(10).grid[4] = "                  ";
							chunks.get(3).get(4).grid[0] = "                {|";chunks.get(3).get(5).grid[0] = positions[4] + "  Lens           ";chunks.get(3).get(6).grid[0] = "   $10000         ";chunks.get(3).get(7).grid[0] = "                  ";chunks.get(3).get(8).grid[0] = "                  ";chunks.get(3).get(9).grid[0] = "                  ";chunks.get(3).get(10).grid[0] = "                  ";
							chunks.get(3).get(4).grid[1] = "                {|";chunks.get(3).get(5).grid[1] = "                  ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                  ";chunks.get(3).get(8).grid[1] = "                  ";chunks.get(3).get(9).grid[1] = "                  ";chunks.get(3).get(10).grid[1] = "                  ";
							chunks.get(3).get(4).grid[2] = "                {|";chunks.get(3).get(5).grid[2] = positions[5] + "  Weight         ";chunks.get(3).get(6).grid[2] = "   $10000         ";chunks.get(3).get(7).grid[2] = "                  ";chunks.get(3).get(8).grid[2] = "                  ";chunks.get(3).get(9).grid[2] = "                  ";chunks.get(3).get(10).grid[2] = "                  ";
							chunks.get(3).get(4).grid[3] = "                {|";chunks.get(3).get(5).grid[3] = "                  ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                  ";chunks.get(3).get(8).grid[3] = "                  ";chunks.get(3).get(9).grid[3] = "                  ";chunks.get(3).get(10).grid[3] = "                  ";
							chunks.get(3).get(4).grid[4] = "                {|";chunks.get(3).get(5).grid[4] = "                  ";chunks.get(3).get(6).grid[4] = "                  ";chunks.get(3).get(7).grid[4] = "                  ";chunks.get(3).get(8).grid[4] = "                  ";chunks.get(3).get(9).grid[4] = "                  ";chunks.get(3).get(10).grid[4] = "                  ";
							chunks.get(4).get(4).grid[0] = "                {|";chunks.get(4).get(5).grid[0] = "                  ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                  ";chunks.get(4).get(8).grid[0] = "                  ";chunks.get(4).get(9).grid[0] = "                  ";chunks.get(4).get(10).grid[0] = "                  ";
							chunks.get(4).get(4).grid[1] = "                {|";chunks.get(4).get(5).grid[1] = "                  ";chunks.get(4).get(6).grid[1] = "                  ";chunks.get(4).get(7).grid[1] = "                  ";chunks.get(4).get(8).grid[1] = "                  ";chunks.get(4).get(9).grid[1] = "                  ";chunks.get(4).get(10).grid[1] = "                  ";
						}
						chunks.get(0).get(bar).grid[1] = "~~~~~~~~~~~~~~~~~~";
						chunks.get(0).get(bar).grid[2] = "|" + chunks.get(0).get(bar).grid[2].substring(1, 17) + "|";
						chunks.get(0).get(bar).grid[3] = "~~~~~~~~~~~~~~~~~~";
						System.out.println(pos);
						map.printSimpleMap();
						input = scan.next();
						
						if(input.equals("q") && bar != 5) {
							chunks.get(0).get(bar).grid[1] = "                  ";
							chunks.get(0).get(bar).grid[2] = " " + chunks.get(0).get(bar).grid[2].substring(1, 17) + " ";
							chunks.get(0).get(bar).grid[3] = "                  ";
							positions[pos] = ' ';
							pos = 0;
							positions[pos] = '>';
							bar--;
						}
						if(input.equals("r") && bar != 10) {
							chunks.get(0).get(bar).grid[1] = "                  ";
							chunks.get(0).get(bar).grid[2] = " " + chunks.get(0).get(bar).grid[2].substring(1, 17) + " ";
							chunks.get(0).get(bar).grid[3] = "                  ";
							positions[pos] = ' ';
							pos = 0;
							positions[pos] = '>';
							bar++;
						}
						if(input.equals("o")) {
							input = "";
							positions[pos] = ' ';
							pos = 0;
							break;
						}
					}
				} else if(pos == 2){
					return;
				}
			
				
			} else if(input.equals("o")) {
				break;
			}else {
				chunks.get(0).get(4).grid[0] = "                  ";chunks.get(0).get(5).grid[0] = "                  ";chunks.get(0).get(6).grid[0] = "                  ";chunks.get(0).get(7).grid[0] = "                  ";chunks.get(0).get(8).grid[0] = "                  ";chunks.get(0).get(9).grid[0] = "                  ";chunks.get(0).get(10).grid[0] = "                  ";
				chunks.get(0).get(4).grid[1] = "                  ";chunks.get(0).get(5).grid[1] = "                  ";chunks.get(0).get(6).grid[1] = "                  ";chunks.get(0).get(7).grid[1] = "                  ";chunks.get(0).get(8).grid[1] = "                  ";chunks.get(0).get(9).grid[1] = "                  ";chunks.get(0).get(10).grid[1] = "                  ";
				chunks.get(0).get(4).grid[2] = "                  ";chunks.get(0).get(5).grid[2] = "                  ";chunks.get(0).get(6).grid[2] = "                  ";chunks.get(0).get(7).grid[2] = "                  ";chunks.get(0).get(8).grid[2] = "                  ";chunks.get(0).get(9).grid[2] = "                  ";chunks.get(0).get(10).grid[2] = "                  ";
				chunks.get(0).get(4).grid[3] = "                  ";chunks.get(0).get(5).grid[3] = "                  ";chunks.get(0).get(6).grid[3] = "                  ";chunks.get(0).get(7).grid[3] = "                  ";chunks.get(0).get(8).grid[3] = "                  ";chunks.get(0).get(9).grid[3] = "                  ";chunks.get(0).get(10).grid[3] = "                  ";
				chunks.get(0).get(4).grid[4] = "                  ";chunks.get(0).get(5).grid[4] = "                  ";chunks.get(0).get(6).grid[4] = "                  ";chunks.get(0).get(7).grid[4] = "                  ";chunks.get(0).get(8).grid[4] = "                  ";chunks.get(0).get(9).grid[4] = "                  ";chunks.get(0).get(10).grid[4] = "                  ";
				chunks.get(1).get(4).grid[0] = "                  ";chunks.get(1).get(5).grid[0] = "                  ";chunks.get(1).get(6).grid[0] = "                  ";chunks.get(1).get(7).grid[0] = "                  ";chunks.get(1).get(8).grid[0] = "                  ";chunks.get(1).get(9).grid[0] = "                  ";chunks.get(1).get(10).grid[0] = "                  ";
				chunks.get(1).get(4).grid[1] = "                  ";chunks.get(1).get(5).grid[1] = "                  ";chunks.get(1).get(6).grid[1] = "                  ";chunks.get(1).get(7).grid[1] = "                  ";chunks.get(1).get(8).grid[1] = "                  ";chunks.get(1).get(9).grid[1] = "                  ";chunks.get(1).get(10).grid[1] = "                  ";
				chunks.get(1).get(4).grid[2] = "                  ";chunks.get(1).get(5).grid[2] = "                  ";chunks.get(1).get(6).grid[2] = "                  ";chunks.get(1).get(7).grid[2] = "                  ";chunks.get(1).get(8).grid[2] = "                  ";chunks.get(1).get(9).grid[2] = "                  ";chunks.get(1).get(10).grid[2] = "                  ";
				chunks.get(1).get(4).grid[3] = "                  ";chunks.get(1).get(5).grid[3] = "                  ";chunks.get(1).get(6).grid[3] = "                  ";chunks.get(1).get(7).grid[3] = "                  ";chunks.get(1).get(8).grid[3] = "                  ";chunks.get(1).get(9).grid[3] = "                  ";chunks.get(1).get(10).grid[3] = "                  ";
				chunks.get(1).get(4).grid[4] = "                  ";chunks.get(1).get(5).grid[4] = "                  ";chunks.get(1).get(6).grid[4] = "                  ";chunks.get(1).get(7).grid[4] = "                  ";chunks.get(1).get(8).grid[4] = "                  ";chunks.get(1).get(9).grid[4] = "                  ";chunks.get(1).get(10).grid[4] = "                  ";
				chunks.get(2).get(4).grid[0] = "                  ";chunks.get(2).get(5).grid[0] = "                  ";chunks.get(2).get(6).grid[0] = "                  ";chunks.get(2).get(7).grid[0] = "                  ";chunks.get(2).get(8).grid[0] = "                  ";chunks.get(2).get(9).grid[0] = "                  ";chunks.get(2).get(10).grid[0] = "                  ";
				chunks.get(2).get(4).grid[1] = "                  ";chunks.get(2).get(5).grid[1] = "                  ";chunks.get(2).get(6).grid[1] = "                  ";chunks.get(2).get(7).grid[1] = "                  ";chunks.get(2).get(8).grid[1] = "                  ";chunks.get(2).get(9).grid[1] = "                  ";chunks.get(2).get(10).grid[1] = "                  ";
				chunks.get(2).get(4).grid[2] = "                  ";chunks.get(2).get(5).grid[2] = "                  ";chunks.get(2).get(6).grid[2] = "                  ";chunks.get(2).get(7).grid[2] = "                  ";chunks.get(2).get(8).grid[2] = "                  ";chunks.get(2).get(9).grid[2] = "                  ";chunks.get(2).get(10).grid[2] = "                  ";
				chunks.get(2).get(4).grid[3] = "                  ";chunks.get(2).get(5).grid[3] = "                  ";chunks.get(2).get(6).grid[3] = "                  ";chunks.get(2).get(7).grid[3] = "                  ";chunks.get(2).get(8).grid[3] = "                  ";chunks.get(2).get(9).grid[3] = "                  ";chunks.get(2).get(10).grid[3] = "                  ";
				chunks.get(2).get(4).grid[4] = "                  ";chunks.get(2).get(5).grid[4] = "                  ";chunks.get(2).get(6).grid[4] = "                  ";chunks.get(2).get(7).grid[4] = "                  ";chunks.get(2).get(8).grid[4] = "                  ";chunks.get(2).get(9).grid[4] = "                  ";chunks.get(2).get(10).grid[4] = "                  ";
				chunks.get(3).get(4).grid[0] = "                  ";chunks.get(3).get(5).grid[0] = "                  ";chunks.get(3).get(6).grid[0] = "                  ";chunks.get(3).get(7).grid[0] = "                  ";chunks.get(3).get(8).grid[0] = "                  ";chunks.get(3).get(9).grid[0] = "                  ";chunks.get(3).get(10).grid[0] = "                  ";
				chunks.get(3).get(4).grid[1] = "                  ";chunks.get(3).get(5).grid[1] = "                  ";chunks.get(3).get(6).grid[1] = "                  ";chunks.get(3).get(7).grid[1] = "                  ";chunks.get(3).get(8).grid[1] = "                  ";chunks.get(3).get(9).grid[1] = "                  ";chunks.get(3).get(10).grid[1] = "                  ";
				chunks.get(3).get(4).grid[2] = "                  ";chunks.get(3).get(5).grid[2] = "                  ";chunks.get(3).get(6).grid[2] = "                  ";chunks.get(3).get(7).grid[2] = "                  ";chunks.get(3).get(8).grid[2] = "                  ";chunks.get(3).get(9).grid[2] = "                  ";chunks.get(3).get(10).grid[2] = "                  ";
				chunks.get(3).get(4).grid[3] = "                  ";chunks.get(3).get(5).grid[3] = "                  ";chunks.get(3).get(6).grid[3] = "                  ";chunks.get(3).get(7).grid[3] = "                  ";chunks.get(3).get(8).grid[3] = "                  ";chunks.get(3).get(9).grid[3] = "                  ";chunks.get(3).get(10).grid[3] = "                  ";
				chunks.get(3).get(4).grid[4] = "                  ";chunks.get(3).get(5).grid[4] = "                  ";chunks.get(3).get(6).grid[4] = "                  ";chunks.get(3).get(7).grid[4] = "                  ";chunks.get(3).get(8).grid[4] = "                  ";chunks.get(3).get(9).grid[4] = "                  ";chunks.get(3).get(10).grid[4] = "                  ";
				chunks.get(4).get(4).grid[0] = "                  ";chunks.get(4).get(5).grid[0] = "                  ";chunks.get(4).get(6).grid[0] = "                  ";chunks.get(4).get(7).grid[0] = "                  ";chunks.get(4).get(8).grid[0] = "                  ";chunks.get(4).get(9).grid[0] = "                  ";chunks.get(4).get(10).grid[0] = "                  ";
				chunks.get(4).get(4).grid[1] = "                  ";chunks.get(4).get(5).grid[1] = "                  ";chunks.get(4).get(6).grid[1] = "                  ";chunks.get(4).get(7).grid[1] = "                  ";chunks.get(4).get(8).grid[1] = "                  ";chunks.get(4).get(9).grid[1] = "                  ";chunks.get(4).get(10).grid[1] = "                  ";
				chunks.get(4).get(4).grid[2] = "                  ";chunks.get(4).get(5).grid[2] = "                  ";chunks.get(4).get(6).grid[2] = "                  ";chunks.get(4).get(7).grid[2] = "                  ";chunks.get(4).get(8).grid[2] = "                  ";chunks.get(4).get(9).grid[2] = "                  ";chunks.get(4).get(10).grid[2] = "                  ";
				
				positions[pos] = '>';
				map.mapDialogue("Welcome to Route 1's PokeStop! What would you like to do today?                                               " + positions[0] + " Heal Pokemon     " + positions[1] + " Purchase Items     " + positions[2] + " Exit                ");
				map.printSimpleMap();
				input = scan.next();
			}
			if(input.equals("a") && pos != 0) {
				positions[pos] = ' '; pos--; positions[pos] = '>';
			} else if(input.equals("d") && pos != 2) {
				positions[pos] = ' '; pos++; positions[pos] = '>';
			}
				
		} 
	}
	
}

