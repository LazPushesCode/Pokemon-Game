package pokemon;
import java.util.HashMap;
import pokemon.Map;
import pokemon.Map.chunk;
import pokemon.game;
import pokemon.Poke;
import pokemon.Item;


public class Collection {
	
	private HashMap<String, basePoke> pokemonCollection;
	
	public Collection() {
		//add all pokemon into a pokemon library made up of all pokemon
		pokemonCollection = new HashMap<>();
		addPokemon("Fizard", "Fire", "",35, 1, 1, 1, 1 ,1);
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
		System.out.println("Added: " + pokemonCollection.get(pokemon.name));
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
		System.out.println(this.type[0] + " " + this.type[1]);
	}
	
}




