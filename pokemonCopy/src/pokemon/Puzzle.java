package pokemon;

import java.util.Random;

public class Puzzle {

	
}

class GrassPuzzle{
	Vine one;
	Vine two;
	Vine three;
	Vine four;
	int goal;
	GrassPuzzle(){
		one = new Vine();
		two = new Vine();
		three = new Vine();
		four = new Vine();
		one.updateVine(two);
		two.updateVine(three);
		three.updateVine(four);
		four.updateVine(one);
	}
	void updateVines() {
		Random ran = new Random();
		goal = ran.nextInt(2,4);
		one.position = goal; two.position = goal; three.position = goal; four.position = goal;
		int ran1; int ran2;
		int flag1 = 0; int flag2 = 0;
		goal = ran.nextInt(6, 11);
		for(int i = 0; i < goal;) {
			if(i == goal-1) {
				int zeroflag = 0; int fourflag = 0;
				Vine curr = one;
				for(int j = 0; j < 4; j++) {
					if(curr.position == 0) zeroflag = 1;
					if(curr.position == 4) fourflag = 1;
					curr = curr.next;
				}
				if(zeroflag != 1 || fourflag != 1) {
					i = 0;
				}
				if(one.position == two.position && two.position == three.position && three.position == four.position && four.position == one.position) {
					i = 0;
				}
			}
			ran1 = ran.nextInt(1,5);
			ran2 = ran.nextInt(1,5);
			if(ran1 == 1 && one.position != 0) {
				flag1 = 1;
			} else if(ran1 == 2 && two.position != 0) {
				flag1 = 1;
			} else if(ran1 == 3 && three.position != 0) {
				flag1 = 1;
			} else if(ran1 == 4 && four.position != 0) {
				flag1 = 1;
			}
			
			if(ran2 == 1 && one.position != 4) {
				flag2 = 1;
			} else if(ran2 == 2 && two.position != 4) {
				flag2 = 1;
			} else if(ran2 == 3 && three.position != 4) {
				flag2 = 1;
			} else if(ran2 == 4 && four.position != 4) {
				flag2 = 1;
			}
			
			if(flag1 == 1 && flag2 == 1) {
				if(ran1 == 1) one.position--;
				if(ran1 == 2) two.position--;
				if(ran1 == 3) three.position--;
				if(ran1 == 4) four.position--;

				if(ran2 == 1) one.position++;
				if(ran2 == 2) two.position++;
				if(ran2 == 3) three.position++;
				if(ran2 == 4) four.position++;
				i++;
			}
			flag1 = 0; flag2 = 0;
		}		
	}
}

class Vine{
	int position;
	Vine next;
	Vine(int position){
		this.position = position;
		this.next = null;
	}
	Vine(){
		this.position = 0;
		this.next = null;
	}
	void updateVine(Vine next) {
		this.next = next;
	}
	void changeVinePosition(Vine current) {
		if(current.position != 0 && current.next.position != 4) {
			current.position--;
			current.next.position++;
		}
	}
}
