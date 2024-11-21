package pokemon;
import java.util.Scanner;
import java.util.Random;


class test{
	public static void main(String[] args) {
		Random ran = new Random();
		int goal = ran.nextInt(1, 4);
		Node one = new Node(goal);
		Node two = new Node(goal);
		Node three = new Node(goal);
		Node four = new Node(goal);
		one.updateNode(two);
		two.updateNode(three);
		three.updateNode(four);
		four.updateNode(one);
		goal = ran.nextInt(6, 11);
		int randomPick1, randomPick2;
		int flag1= 0, flag2 = 0;
		for(int i = 0; i < goal;) {
			if(i == goal-1) {
				int flagzero = 0; int flagfour = 0;
				Node curr = one;
				for(int j = 0; j < 4; j++) {
					if(curr.position == 0) flagzero = 1;
					if(curr.position == 4) flagfour = 1;
					curr = curr.next;
				}
				if(flagzero != 1 || flagfour != 1) {
					i = 0;
				}
				if(one.position == two.position && two.position == three.position && three.position == four.position && four.position == one.position) {
					i = 0;
				}
			}
			randomPick1 = ran.nextInt(1,5);
			randomPick2 = ran.nextInt(1,5);
			if(randomPick1 == 1 && one.position != 0) {
				flag1 = 1;
			} else if(randomPick1 == 2 && two.position != 0) {
				flag1 = 1;
			} else if(randomPick1 == 3 && three.position != 0) {
				flag1 = 1;
			} else if(randomPick1 == 4 && four.position != 0) {
				flag1 = 1;
			}
			
			if(randomPick2 == 1 && one.position != 4) {
				flag2 = 1;
			} else if(randomPick2 == 2 && two.position != 4) {
				flag2 = 1;
			} else if(randomPick2 == 3 && three.position != 4) {
				flag2 = 1;
			} else if(randomPick2 == 4 && four.position != 4) {
				flag2 = 1;
			}
			
			if(flag1 == 1 && flag2 == 1) {
				if(randomPick1 == 1) one.position--;
				if(randomPick1 == 2) two.position--;
				if(randomPick1 == 3) three.position--;
				if(randomPick1 == 4) four.position--;

				if(randomPick2 == 1) one.position++;
				if(randomPick2 == 2) two.position++;
				if(randomPick2 == 3) three.position++;
				if(randomPick2 == 4) four.position++;
				i++;
			}
			flag1 = 0; flag2 = 0;
		}
		
//		one = new Node(two, 1);
//		two = new Node(three, 4);
//		three = new Node(three, 0);
//		four = new Node(one, 3);
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(true) {
			if(one.position == two.position && two.position == three.position && three.position == four.position && four.position == one.position) break;
			System.out.println(four.position + "   " + one.position);
			System.out.println("\n\n\n");
			System.out.println(two.position + "   " + three.position);
			
			
			input = scan.next();
			if(input.equals("o")) break;
			if(input.equals("1") && one.position != 0 && one.next.position != 4) {
				one.position--; one.next.position++;
			} 
			if(input.equals("2") && two.position != 0 && two.next.position != 4) {
				two.position--; two.next.position++;
			} 
			if(input.equals("3") && three.position != 0 && three.next.position != 4) {
				three.position--; three.next.position++;	
			} 
			if(input.equals("4") && four.position != 0 && four.next.position != 4) {
				four.position--; four.next.position++;
			} 
		}
		System.out.println("GG's!");
		scan.close();
	}
}

class Node{
	int position;
	Node next;
	Node(int position){
		this.position = position;
		this.next = null;
	}
	void updateNode(Node next) {
		this.next = next;
	}
}