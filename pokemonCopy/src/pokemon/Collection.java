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
		//Starters:
		addPokemon("Fizard", "Fire", "",60, 50, 80, 40, 80 , 70);
		pokemonCollection.get("Fizard").setMoves(attacks, "Tail Whip", "Hide n Lizard", "Flaming Tongue", "Swelling Inferno", "ASHES OF POMPEII", "Magma Bound", null, null);
		pokemonCollection.get("Fizard").model[0] = "          ___/\\_                    ";
		pokemonCollection.get("Fizard").model[1] = "        .*. \\ \\ \\___                   ";
		pokemonCollection.get("Fizard").model[2] = "         '   \\ \\ \\ .*.               ";
		pokemonCollection.get("Fizard").model[3] = "              \\ \\ \\ '                  ";
		pokemonCollection.get("Fizard").model[4] = "              / /  /                  ";
		pokemonCollection.get("Fizard").model[5] = "           __/ /  /_                    ";
		pokemonCollection.get("Fizard").model[6] = "          /  | | /  \\                  ";
		pokemonCollection.get("Fizard").model[7] = "       .*.    \\==\\  .*.                 ";
		pokemonCollection.get("Fizard").model[8] = "        '     (. .)  '                ";
		pokemonCollection.get("Fizard").model[9] = "               \\ /                  ";
		addPokemon("Aqualard", "Water", "", 70, 70, 40, 50, 50, 40);
		pokemonCollection.get("Aqualard").setMoves(attacks, "Flipper Wack", "Splash", "10 Foot Wave", "Aquatic Dash", "Cannonball", "Davy Jones Locker", null, null);
		pokemonCollection.get("Aqualard").model[0] = "                                    ";
		pokemonCollection.get("Aqualard").model[1] = "                  (  )              ";
		pokemonCollection.get("Aqualard").model[2] = "                 __||__             ";
		pokemonCollection.get("Aqualard").model[3] = "               /        \\           ";
		pokemonCollection.get("Aqualard").model[4] = "             /            \\         ";
		pokemonCollection.get("Aqualard").model[5] = "            |    |    |    |         ";
		pokemonCollection.get("Aqualard").model[6] = "            |      ()      |        ";
		pokemonCollection.get("Aqualard").model[7] = "     _ _ __/      ____     |         ";
		pokemonCollection.get("Aqualard").model[8] = "    /            /    \\   /          ";
		pokemonCollection.get("Aqualard").model[9] = "   |____________________/           ";
		addPokemon("TreeKat", "Grass", "", 50, 60, 60, 40, 70, 65);
		pokemonCollection.get("TreeKat").setMoves(attacks, "Dirty Hook", "Stick Puncture", "Dirty Shot", "HayMaker", "Uprooted", "Flower Dance", null, null);
		pokemonCollection.get("TreeKat").model[0] =  "            ^______^                ";
		pokemonCollection.get("TreeKat").model[1] =  "           |  .  .  |               ";
		pokemonCollection.get("TreeKat").model[2] =  "            \\      /                ";
		pokemonCollection.get("TreeKat").model[3] =  "           __\\=/\\=/__               ";
		pokemonCollection.get("TreeKat").model[4] =  "          /          \\              ";
		pokemonCollection.get("TreeKat").model[5] =  "        / |* * __ * *| \\            ";
		pokemonCollection.get("TreeKat").model[6] =  "       |  |   /  \\   |  |           ";
		pokemonCollection.get("TreeKat").model[7] =  "        \\ \\  /    \\  / /            ";
		pokemonCollection.get("TreeKat").model[8] =  "        | /  \\    /  \\ |            ";
		pokemonCollection.get("TreeKat").model[9] =  "      ./ .\\  /.  .\\  /. \\.          ";
		//world 1
		addPokemon("Stickly", "Grass", "", 35, 55, 35, 45, 30, 50);
		pokemonCollection.get("Stickly").setMoves(attacks, "Leaf Kick", "Stick Puncture", "Dirty Hook", "Dirty Slap", "Branch Throw", "Leaf Blow", null, null);
		pokemonCollection.get("Stickly").model[0] = "              |    |                ";
		pokemonCollection.get("Stickly").model[1] = "              |    |                ";
		pokemonCollection.get("Stickly").model[2] = "              |    |                ";
		pokemonCollection.get("Stickly").model[3] = "              |    | \\/_            ";
		pokemonCollection.get("Stickly").model[4] = "              | :D | /              ";
		pokemonCollection.get("Stickly").model[5] = "          _\\/ |    |/               ";
		pokemonCollection.get("Stickly").model[6] = "            \\ |    |                ";
		pokemonCollection.get("Stickly").model[7] = "             \\|    |                ";
		pokemonCollection.get("Stickly").model[8] = "              |    |                ";
		pokemonCollection.get("Stickly").model[9] = "              |    |                ";
		addPokemon("GrassJumper", "Grass", "", 35, 60, 30, 40, 30, 60);
		pokemonCollection.get("GrassJumper").setMoves(attacks, "Shallow Grass Cut", "Branch Throw", "Dirty Shot", "Leaf Blow", "Leaf Kick", "Dirty Slap", null, null);
		pokemonCollection.get("GrassJumper").model[0] = "                                      ";
		pokemonCollection.get("GrassJumper").model[1] = "                                      ";
		pokemonCollection.get("GrassJumper").model[2] = "  |    |                   _          ";
		pokemonCollection.get("GrassJumper").model[3] = "   \\ __/                  / \\         ";
		pokemonCollection.get("GrassJumper").model[4] = "    / 0 \\ ______         / /\\\\        ";
		pokemonCollection.get("GrassJumper").model[5] = "    |  /\\|      \\______/ /___\\\\      ";
		pokemonCollection.get("GrassJumper").model[6] = "    \\ /   \\__       _/  /    /\\\\      ";
		pokemonCollection.get("GrassJumper").model[7] = "         //  \\_\\_/___/_____/  \\\\     ";
		pokemonCollection.get("GrassJumper").model[8] = "       _//_    _\\\\_            _\\\\_   ";
		pokemonCollection.get("GrassJumper").model[9] = "                                      ";
		addPokemon("Raccadox", "Grass", "", 50, 55, 80, 40, 70, 60);
		pokemonCollection.get("Raccadox").setMoves(attacks, "Herb Clash", "Weeping Willow", "Dirty Slap", "Dirty Hook", "Leaf Kick", "Flower Dance", null, null);
		pokemonCollection.get("Raccadox").model[0] = "                                    ";
		pokemonCollection.get("Raccadox").model[1] = "                                    ";
		pokemonCollection.get("Raccadox").model[2] = "                /'\\___/'\\                   ";
		pokemonCollection.get("Raccadox").model[3] = "               /  _   _  \\           ";
		pokemonCollection.get("Raccadox").model[4] = "              (  /.\\_/.\\  )          ";
		pokemonCollection.get("Raccadox").model[5] = "               \\/  /*\\  \\/             ";
		pokemonCollection.get("Raccadox").model[6] = "                \\_/[-]\\_/           ";
		pokemonCollection.get("Raccadox").model[7] = "     _______   /        \\           ";
		pokemonCollection.get("Raccadox").model[8] = "    (|_|_|  \\ | ' '   '  |            ";
		pokemonCollection.get("Raccadox").model[9] = "          \\__\\|   '  ' ' |          ";
		addPokemon("Crow-Bow", "Grass", "", 45, 75, 40, 70, 40, 70);
		pokemonCollection.get("Crow-Bow").setMoves(attacks, "Lawn Mower", "Tangled Vines", "Dirty Slap", "Stick Puncture", "Dirty Hook", "Hack-a-Tree", null, null);
		pokemonCollection.get("Crow-Bow").model[0] = "                                     ";
		pokemonCollection.get("Crow-Bow").model[1] = "                                     ";
		pokemonCollection.get("Crow-Bow").model[2] = "                     _______         ";
		pokemonCollection.get("Crow-Bow").model[3] = "    /|             _/   . __\\__      ";
		pokemonCollection.get("Crow-Bow").model[4] = "   / |      ______/      /_____\\     ";
		pokemonCollection.get("Crow-Bow").model[5] = "  /  |  ___/ |      |   _/           ";
		pokemonCollection.get("Crow-Bow").model[6] = " |   |_/        |      |             ";
		pokemonCollection.get("Crow-Bow").model[7] = "  \\  |_  | |      |  _/              ";
		pokemonCollection.get("Crow-Bow").model[8] = "   \\ | \\____________/                ";
		pokemonCollection.get("Crow-Bow").model[9] = "    \\|     _\\_  _\\_                  ";
		addPokemon("ButterBird", "Grass", "", 60, 30, 70, 75, 50, 60);
		pokemonCollection.get("ButterBird").setMoves(attacks, "Dirty Shot", "Leaf Blow", "HayMaker", "Herb Clash", "Tangled Vines", "Flora Wallop", null, null);
		pokemonCollection.get("ButterBird").model[0] = "                      _______        ";
		pokemonCollection.get("ButterBird").model[1] = "                   __(       )       ";
		pokemonCollection.get("ButterBird").model[2] = "                __(_      . /        ";
		pokemonCollection.get("ButterBird").model[3] = "               (    \\....   >        ";
		pokemonCollection.get("ButterBird").model[4] = "              /     /  '   \\          ";
		pokemonCollection.get("ButterBird").model[5] = "           __/     / '  ' |         ";
		pokemonCollection.get("ButterBird").model[6] = "         /________/  '    |          ";
		pokemonCollection.get("ButterBird").model[7] = "      _ /       ____   ' /            ";
		pokemonCollection.get("ButterBird").model[8] = "     // / _____/    \\___/              ";
		pokemonCollection.get("ButterBird").model[9] = "      / /     _\\_    _\\_             ";
		addPokemon("Goosepor", "Grass", "Water", 60, 40, 90, 40, 50, 55);
		pokemonCollection.get("Goosepor").setMoves(attacks, "Stick Puncture", "Wet Slap", "Hazy Mist", "10 Foot Wave", "Swan Lake", "Davy Jones Locker", null, null);
		pokemonCollection.get("Goosepor").model[0] = "                                     ";
		pokemonCollection.get("Goosepor").model[1] = "                  _____              ";
		pokemonCollection.get("Goosepor").model[2] = "                 /   . \\\\...           ";
		pokemonCollection.get("Goosepor").model[3] = "                 |     ||...',        ";
		pokemonCollection.get("Goosepor").model[4] = "                 |    /              ";
		pokemonCollection.get("Goosepor").model[5] = "   |\\___________/    |               ";
		pokemonCollection.get("Goosepor").model[6] = "   |                 /               ";
		pokemonCollection.get("Goosepor").model[7] = "  (                 |               ";
		pokemonCollection.get("Goosepor").model[8] = "   \\_______________/                    ";
		pokemonCollection.get("Goosepor").model[9] = "        |_    |_                     ";
		addPokemon("VineTurtle", "Grass", "Water", 70, 60, 30, 80, 50, 40);
		pokemonCollection.get("VineTurtle").setMoves(attacks, "Wet Slap", "Flipper Wack", "Hazy Mist", "Aquatic Dash", "Cannonball", "Explosive Cannonball", null, null);
		pokemonCollection.get("VineTurtle").model[0] = "                                    ";
		pokemonCollection.get("VineTurtle").model[1] = "                                    ";
		pokemonCollection.get("VineTurtle").model[2] = "                                    ";
		pokemonCollection.get("VineTurtle").model[3] = "                                    ";
		pokemonCollection.get("VineTurtle").model[4] = "        __________     ____         ";
		pokemonCollection.get("VineTurtle").model[5] = "      _/  \\__/  \\_\\_  /  . \\        ";
		pokemonCollection.get("VineTurtle").model[6] = "     / \\__/  \\__/  \\\\/  _../        ";
		pokemonCollection.get("VineTurtle").model[7] = "    |  /__\\__/__\\__/ |_/             ";
		pokemonCollection.get("VineTurtle").model[8] = "     []            []                 ";
		pokemonCollection.get("VineTurtle").model[9] = "                                    ";
		addPokemon("ToxRat", "Grass", "Poison", 25, 20, 80, 40, 40, 80);
		pokemonCollection.get("ToxRat").setMoves(attacks, "Bad Breath", "Erosive Wack", "Toxic Spit", "Poison Arrow", null, null, null, null);
		pokemonCollection.get("ToxRat").model[0] = "                                     ";
		pokemonCollection.get("ToxRat").model[1] = "    ___________                       ";
		pokemonCollection.get("ToxRat").model[2] = "   / __________||>                   ";
		pokemonCollection.get("ToxRat").model[3] = "  / /                                ";
		pokemonCollection.get("ToxRat").model[4] = " | /       _________(U)()              ";
		pokemonCollection.get("ToxRat").model[5] = " | |   ___/    '     '  \\__           ";
		pokemonCollection.get("ToxRat").model[6] = " | |  /   '       '     *  \\         ";
		pokemonCollection.get("ToxRat").model[7] = " \\ \\_/    '       '  |   '  \\         ";
		pokemonCollection.get("ToxRat").model[8] = "  \\_|____'______'___/ '---o--*        ";
		pokemonCollection.get("ToxRat").model[9] = "     \\\\_.      \\\\_.                  ";
		addPokemon("Banopotamus", "Grass", "Poison", 80, 90, 30, 55, 50, 40);
		pokemonCollection.get("Banopotamus").setMoves(attacks, "Bad Breath", "Purple Splash", "Poison Arrow", "Toxin Mask", "Twisted Thicket", null, null, null);
		pokemonCollection.get("Banopotamus").model[0] = "                                    ";
		pokemonCollection.get("Banopotamus").model[1] = "                                    ";
		pokemonCollection.get("Banopotamus").model[2] = "                                    ";
		pokemonCollection.get("Banopotamus").model[3] = "                                    ";
		pokemonCollection.get("Banopotamus").model[4] = "     ___________________ ^ _ ^      ";
		pokemonCollection.get("Banopotamus").model[5] = "    /                   /.    .\\    ";
		pokemonCollection.get("Banopotamus").model[6] = "  /|                    ||    ||    ";
		pokemonCollection.get("Banopotamus").model[7] = " * |                    ||/,,\\||    ";
		pokemonCollection.get("Banopotamus").model[8] = "    \\      _____      / /______\\    ";
		pokemonCollection.get("Banopotamus").model[9] = "     |    |     |    |  {      }    ";
		addPokemon("Bark-Widow", "Grass", "Poison", 50, 40, 65, 35, 40, 70);
		pokemonCollection.get("Bark-Widow").setMoves(attacks, "Erosive Wack", "Lethal Sting", "Venomous Regurgitation", "Swamp of DECAY", null, null, null, null);
		pokemonCollection.get("Bark-Widow").model[0] = "        /\\  ______  /\\               ";
		pokemonCollection.get("Bark-Widow").model[1] = "       /  \\|   |  |/  \\              ";
		pokemonCollection.get("Bark-Widow").model[2] = "       /\\  | |  | |  /\\              ";
		pokemonCollection.get("Bark-Widow").model[3] = "      /  \\ |   |  | /  \\             ";
		pokemonCollection.get("Bark-Widow").model[4] = "     /  /\\\\|  |   |//\\  \\            ";
		pokemonCollection.get("Bark-Widow").model[5] = "       /  \\|    | |/  \\               ";
		pokemonCollection.get("Bark-Widow").model[6] = "      /    | | |  |    \\             ";
		pokemonCollection.get("Bark-Widow").model[7] = "         /\\|______|/\\                ";
		pokemonCollection.get("Bark-Widow").model[8] = "        /  (_*,,*_)   \\               ";
		pokemonCollection.get("Bark-Widow").model[9] = "                                    ";
		addPokemon("Thunder-Bunny", "Grass", "Electric", 30, 75, 35, 30, 75, 110);
		pokemonCollection.get("Thunder-Bunny").setMoves(attacks, "Quick Attack", "Static Beat", "Zappify", "Thunder Clap", null, null, null, null);
		pokemonCollection.get("Thunder-Bunny").model[0] = "                                     ";
		pokemonCollection.get("Thunder-Bunny").model[1] = "                                     ";
		pokemonCollection.get("Thunder-Bunny").model[2] = "                                     ";
		pokemonCollection.get("Thunder-Bunny").model[3] = "         | \\   / |                   ";
		pokemonCollection.get("Thunder-Bunny").model[4] = "          \\|...|/                    ";
		pokemonCollection.get("Thunder-Bunny").model[5] = "          / * * \\                     ";
		pokemonCollection.get("Thunder-Bunny").model[6] = "        _/(     )\\_                    ";
		pokemonCollection.get("Thunder-Bunny").model[7] = "       (  ,  '  ,   )                   ";
		pokemonCollection.get("Thunder-Bunny").model[8] = "       \\   , , ,   /                   ";
		pokemonCollection.get("Thunder-Bunny").model[9] = "      (__/ || || \\__)                ";
		addPokemon("Lumin-Fox", "Grass", "Electric", 30, 40, 35, 55, 60, 100); 
		pokemonCollection.get("Lumin-Fox").setMoves(attacks, "Quick Attack", "Static Beat", "Volt Smack", "Electrocute", "Voltaic Blast", "Electric Boogaloo", null, null);
		pokemonCollection.get("Lumin-Fox").model[0] = "                                          ";
		pokemonCollection.get("Lumin-Fox").model[1] = "                                           ";
		pokemonCollection.get("Lumin-Fox").model[2] = "                                            ";
		pokemonCollection.get("Lumin-Fox").model[3] = "            ^.:.:.^   .                        ";
		pokemonCollection.get("Lumin-Fox").model[4] = "            \\     /  / \\                 ";
		pokemonCollection.get("Lumin-Fox").model[5] = "             \\. ./   \\ \\\\             ";
		pokemonCollection.get("Lumin-Fox").model[6] = "            /|\\ /|\\  / //             ";
		pokemonCollection.get("Lumin-Fox").model[7] = "           / / * \\ \\/ //             ";
		pokemonCollection.get("Lumin-Fox").model[8] = "          | |:| |:| |//                 ";
		pokemonCollection.get("Lumin-Fox").model[9] = "          | |:| |:| |/              ";
		addPokemon("EnerSnail", "Grass", "Electric", 20, 30, 70, 30, 50, 120);
		pokemonCollection.get("EnerSnail").setMoves(attacks, "Quick Attack", "1000 Watts", "Zappify", "Short Circuit", "Ballroom Blitz", null, null, null);
		pokemonCollection.get("EnerSnail").model[0] = "                                    ";
		pokemonCollection.get("EnerSnail").model[1] = "                                    ";
		pokemonCollection.get("EnerSnail").model[2] = "      ________          \\___\\_      ";
		pokemonCollection.get("EnerSnail").model[3] = "    / ........ \\        /_[ ]_\\     ";
		pokemonCollection.get("EnerSnail").model[4] = "  /  ' .....  '  \\      ( )-( )      ";
		pokemonCollection.get("EnerSnail").model[5] = " |  . '     .  .  |     | '--'|     ";
		pokemonCollection.get("EnerSnail").model[6] = " |  . '  '...  .  |     |\\   /|     ";
		pokemonCollection.get("EnerSnail").model[7] = " |  .  '.......   |_____| \\./ |     ";
		pokemonCollection.get("EnerSnail").model[8] = "  \\  .........'  /________|_| |     ";
		pokemonCollection.get("EnerSnail").model[9] = "    \\__________/_____________/      ";
		addPokemon("Scorponium", "Grass", "Steel", 100, 65, 40, 70, 35, 50);
//		pokemonCollection.get("Scorponium").setMoves(attacks, null, null, null, null, null, null, null, null);
		addPokemon("BoulderMash", "Rock", "", 120, 45, 55, 65, 30, 45);
//		pokemonCollection.get("BoulderMash").setMoves(attacks, null, null, null, null, null, null, null, null);
		
	}
	
	public boolean hasPokemon(String name) {
		return pokemonCollection.containsKey(name);
	}
	

	public basePoke get(String name) {
		return pokemonCollection.get(name);
	}
	
	public void addPokemon(String name, String type1, String type2, int bhp, int battack, int bspattack, int bdefense, int bspdefense, int bspeed) {
		basePoke pokemon = new basePoke(name, type1, type2, bhp, battack, bspattack, bdefense, bspdefense, bspeed);
		pokemonCollection.put(pokemon.name, pokemon);
	}

}


//class PokeDex{
//	
//	HashMap<String, Poke> pokedex;
//	
//	PokeDex(){
//		pokedex = new HashMap<>();
//	}
//	
//	public void addPoke(Poke pokemon) {
//		String id = String.valueOf(pokemon.ID);
//		pokedex.put(id, pokemon);
//	}
//	public Poke retrievePoke(int ID) {
//		String id = String.valueOf(ID);
//		return pokedex.get(id);
//	}
//}

class basePoke{
	String name;
	String type[] = {"", ""};
	ArrayList<Attack> moves;
	int bhp; int battack; int bspattack; int bdefense; int bspdefense; int bspeed;
	String model[];
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
		model = new String[10];
		for(int i = 0; i < 10; i++) {
			model[i] = "                                    ";
		}
		moves = new ArrayList<>();
	}
	public void setMoves(AttackDatabase attacks, String move1, String move2, String move3, String move4, String move5, String move6, String move7, String move8) {
		if(move1 != null){
			moves.add(attacks.retrieveMove(move1));
		} 
		if(move2 != null){
			moves.add(attacks.retrieveMove(move2));
		}
		if(move3 != null){
			moves.add(attacks.retrieveMove(move3));
		}
		if(move4 != null){
			moves.add(attacks.retrieveMove(move4));
		}
		if(move5 != null){
			moves.add(attacks.retrieveMove(move5));
		}
		if(move6 != null){
			moves.add(attacks.retrieveMove(move6));
		}
		if(move7 != null){
			moves.add(attacks.retrieveMove(move7));
		}
		if(move8 != null){
			moves.add(attacks.retrieveMove(move8));
		}		
	}
	
}




