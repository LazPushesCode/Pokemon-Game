package pokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import pokemon.game;
import pokemon.Item;
import pokemon.Map.chunk;

import pokemon.Backpack;

public class Trainer {
	ArrayList<Poke> rosterList;
	int numRoster;
	Trainer(){
		rosterList = new ArrayList<Poke>();
		numRoster = 0;
	}
}

 class TutorialTrainer extends Trainer{
	String name;
	
	TutorialTrainer(){
		super();
		name = "Test Trainer";
		
		
	}
}






