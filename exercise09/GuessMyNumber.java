package exercise9;

import java.util.Random;
import java.util.Scanner;

public class GuessMyNumber {
	public static void checkMyAnswer(Scanner reader, int randomNumber){
		int userGuess = reader.nextInt();
		System.out.print("Your guess is: " + userGuess + "\n");
		if (userGuess == randomNumber){
			System.out.print("Congratulations: You have won!\n");
			System.out.print("The number I was thinking was: " + randomNumber + "\n");
			return;
		}
		else{
			if (userGuess > randomNumber){
				System.out.print("Too high! ");
			}
			else{
				System.out.print("Too low! ");
			}
			System.out.print("Try again!\n");
			checkMyAnswer(reader, randomNumber);
		}
		
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Random random= new Random();
		int randomNumber = random.nextInt(100) + 1;
		
		System.out.print("I'm thinking of a number between 1 and 100\n(including both). Can you guesswhat it is?\n");
		System.out.print("Type a number: ");
		checkMyAnswer(reader, randomNumber);
		
		reader.close();
	}
}
