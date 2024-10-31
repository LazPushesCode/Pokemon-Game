package pokemon;
import java.util.HashMap;
import pokemon.Map;
import pokemon.Map.chunk;
import pokemon.game;
import pokemon.Poke;


public class Collection {
	
	private HashMap<String, Poke> pokemonCollection;
	private HashMap<String, Attack> attackCollection;
	
	public Collection(String name, Poke pokemon) {
		pokemonCollection = new HashMap<>();
		pokemonCollection.put(name, pokemon);
	}
	
	public Collection() {
		pokemonCollection = new HashMap<>();
		Poke pokemon1 = new Poke("Poke1", "default", "default", 0, 0, 0, 0, 0);
		Poke pokemon2 = new Poke("Poke2", "default", "default", 0, 0, 0, 0, 0);
		pokemonCollection.put(pokemon1.name, pokemon1);
		pokemonCollection.put(pokemon2.name, pokemon2);
	}
	
	public Collection(String name, Attack attack) {
		attackCollection = new HashMap<>();
		attackCollection.put(name, attack);
	
	}
	
	public void addPokemon(String name, Poke pokemon) {
		pokemonCollection.put(name,  pokemon);
	}
	
	public Poke getPoke(String name) {
		return pokemonCollection.get(name);
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
}



