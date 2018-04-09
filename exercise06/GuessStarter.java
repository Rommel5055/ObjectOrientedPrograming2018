package exercise6;

import java.util.Random;
import java.util.Scanner;

public class GuessStarter {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Random random= new Random();
		int randomNumber = random.nextInt(100) + 1;
		
		System.out.print("I'm thinking of a number between 1 and 100\n(including both). Can you guesswhat it is?\n");
		System.out.print("Type a number: ");
		int userGuess = reader.nextInt();
		System.out.print("Your guess is: " + userGuess + "\n");
		System.out.print("The number I was thinking was: " + randomNumber + "\n");
		if (userGuess == randomNumber){
			System.out.print("Congratulations: You have won!");
		}
		else{
			int difference = randomNumber - userGuess;
			if (difference < 0){
				difference = - difference;
			} 
			System.out.println("You were off by: " + difference);
		}
		reader.close();
	}

}
