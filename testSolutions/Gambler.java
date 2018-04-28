package testSolutions;

import java.util.Random;

public class Gambler {

	public static boolean Trials(int stake, int goal, Random rand){
		int cash = stake;
		while (cash > 0 && cash < goal){
			int coin = rand.nextInt(2);
			if (coin == 0){
				cash--;
			} 
			else if (coin == 1){
				cash++;
			}
		}
		if (cash == 0){
			return false;
		}
		else if (cash == goal){
			return true;
		}
		System.out.println("This souldn't be printed, yet the following return statement must exist...");
		return false;
	}
	
	public static void main(String[] args) {
		int stake = Integer.parseInt(args[0]);
		int goal = Integer.parseInt(args[1]);
		int trials = Integer.parseInt(args[2]);
		boolean win;
		
		Random rand = new Random();
		
		int wins = 0;
		int loses = 0;
		for (int i = 0; i < trials; i++){
			win = Trials(stake, goal, rand);
			if (win){
				wins++;
			}
			else{
				loses++;
			}
		}
		System.out.printf("Wins: %d Loses: %d Trials: %d", wins, loses, trials);
	}
}