package pokemon;
import java.util.ArrayList;
import java.util.HashMap;
import pokemon.Map;
import pokemon.Map.chunk;
import pokemon.game;
import pokemon.Poke;
import pokemon.AttackDatabase;
import pokemon.Item;


public class Collection {
	
	private HashMap<String, basePoke> pokemonCollection;
	
	public Collection(AttackDatabase attacks) {
		//add all pokemon into a pokemon library made up of all pokemon
		
		//hp, attack, spattack, defense, spdefense, speed
		pokemonCollection = new HashMap<>();
		//         name       types     hp  a   sa  d   sd   spe
		//addPokemon("Fizard", "Fire", "",45, 50, 80, 40, 80 , 60);
		addPokemon("Fizard", "Fire", "",120, 45, 55, 65, 30, 45);
		//world 1
		
		addPokemon("test", "", "", 1, 1, 1, 1, 1,1 );
		
		addPokemon("Stickly", "Grass", "", 35, 55, 35, 45, 30, 70);
		pokemonCollection.get("Stickly").setMoves(attacks, "Leaf Kick", "Stick Puncture", "Dirty Hook", "Dirty Slap", "Branch Throw", "Leaf Blow", null, null);
		addPokemon("GrassJumper", "Grass", "", 35, 60, 30, 40, 30, 100);
		pokemonCollection.get("GrassJumper").setMoves(attacks, "Shallow Grass Cut", "Branch Throw", "Dirty Shot", "Leaf Blow", "Leaf Kick", "Dirty Slap", null, null);
		addPokemon("Raccadox", "Grass", "", 50, 55, 80, 40, 70, 60);
		pokemonCollection.get("Raccadox").setMoves(attacks, "Herb Clash", "Weeping Willow", "Dirty Slap", "Dirty Hook", "Leaf Kick", "Flower Dance", null, null);
		addPokemon("Crow-Bow", "Grass", "", 45, 75, 40, 70, 40, 90);
		pokemonCollection.get("Crow-Bow").setMoves(attacks, "Lawn Mower", "Tangled Vines", "Dirty Slap", "Stick Puncture", "Dirty Hook", "Hack-a-Tree", null, null);
		addPokemon("ButterBird", "Grass", "", 60, 30, 70, 75, 50, 60);
		pokemonCollection.get("ButterBird").setMoves(attacks, "Dirty Shot", "Leaf Blow", "HayMaker", "Herb Clash", "Tangled Vines", "Flora Wallop", null, null);
		addPokemon("Goosepor", "Grass", "Water", 60, 40, 90, 40, 50, 55);
		addPokemon("VineTurtle", "Grass", "Water", 70, 60, 30, 80, 50, 40);
		addPokemon("ToxRat", "Grass", "Poison", 25, 20, 80, 40, 40, 90);
		addPokemon("Banopotamus", "Grass", "Poison", 80, 30, 90, 55, 50, 50);
		addPokemon("Bark-Widow", "Grass", "Poison", 50, 40, 65, 35, 40, 70);
		addPokemon("Thunder-Bunny", "Grass", "Electric", 40, 75, 35, 30, 75, 100);
		addPokemon("Lumin-Fox", "Grass", "Electric", 60, 40, 35, 55, 60, 60);
		addPokemon("EnerSnail", "Grass", "Electric", 30, 30, 70, 30, 50, 120);
		addPokemon("Scorponium", "Grass", "Steel", 100, 65, 40, 70, 35, 50);
		addPokemon("BoulderMash", "Rock", "", 120, 45, 55, 65, 30, 45);
		
		
	}
	
	public boolean hasPokemon(String name) {
		return pokemonCollection.containsKey(name);
	}
	
	public void printPokemon() {
		if(pokemonCollection.isEmpty()) {
			System.out.println("No pokemon");
		} else {
			for(String name : pokemonCollection.keySet()) {
				System.out.print(pokemonCollection.get(name).name);
			}
		}
	}	
	public basePoke get(String name) {
		return pokemonCollection.get(name);
	}
	
	public void addPokemon(String name, String type1, String type2, int bhp, int battack, int bspattack, int bdefense, int bspdefense, int bspeed) {
		basePoke pokemon = new basePoke(name, type1, type2, bhp, battack, bspattack, bdefense, bspdefense, bspeed);
		pokemonCollection.put(pokemon.name, pokemon);
		System.out.println("Added: " + pokemonCollection.get(pokemon.name).name);
	}

}


class PokeDex{
	
	HashMap<String, Poke> pokedex;
	
	PokeDex(){
		pokedex = new HashMap<>();
	}
	
	public void addPoke(Poke pokemon) {
		String id = String.valueOf(pokemon.ID);
		pokedex.put(id, pokemon);
	}
	public Poke retrievePoke(int ID) {
		String id = String.valueOf(ID);
		return pokedex.get(id);
	}
}

class basePoke{
	String name;
	String type[] = {"", ""};
	ArrayList<Attack> moves;
	int bhp; int battack; int bspattack; int bdefense; int bspdefense; int bspeed;
	basePoke(String name, String type1, String type2, int bhp, int battack, int bspattack, int bdefense, int bspdefense, int bspeed){
		this.name = name; 
		this.type[0] = type1; 
		this.type[1] = type2; 
		this.bhp = bhp; 
		this.battack = battack; 
		this.bspattack = bspattack; 
		this.bdefense = bdefense; 
		this.bspdefense = bspdefense; 
		this.bspeed = bspeed;
		moves = new ArrayList<>();
	}
	public void setMoves(AttackDatabase attacks, String move1, String move2, String move3, String move4, String move5, String move6, String move7, String move8) {
		System.out.println(this.name);
		if(move1 != null){
			moves.add(attacks.retrieveMove(move1));
			System.out.print(moves.get(0).name + " ");
		} 
		if(move2 != null){
			moves.add(attacks.retrieveMove(move2));
			System.out.print(moves.get(1).name + " ");
		}
		if(move3 != null){
			moves.add(attacks.retrieveMove(move3));
			System.out.print(moves.get(2).name + " ");
		}
		if(move4 != null){
			moves.add(attacks.retrieveMove(move4));
			System.out.print(moves.get(3).name + " ");
		}
		if(move5 != null){
			moves.add(attacks.retrieveMove(move5));
			System.out.print(moves.get(4).name + " ");
		}
		if(move6 != null){
			moves.add(attacks.retrieveMove(move6));
			System.out.print(moves.get(5).name + " ");
		}
		if(move7 != null){
			moves.add(attacks.retrieveMove(move7));
			System.out.print(moves.get(6).name + " ");
		}
		if(move8 != null){
			moves.add(attacks.retrieveMove(move8));
			System.out.print(moves.get(7).name);
		}
		System.out.println();
		
		
		
	}
	
}




