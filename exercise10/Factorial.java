package exercise10;

import java.util.Scanner;

public class Factorial {
	public static int factorial(int n){
		int result = 1;
		for (int i = n; i > 0; i--){
			result *= i; 
		}
		return result;
	}
	
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		System.out.print("Insert number X: ");
		int x = reader.nextInt();
		int result = factorial(x);
		System.out.println("El resultado es: " + result);
		reader.close();
	}
}
