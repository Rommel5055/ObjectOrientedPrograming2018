package exercise11;

import java.util.Scanner;

public class Power {
	public static double power(double x, int n){
		double result = 1.0;
		for (int i = 0; i < n; i++){
			result *= x;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Insert number X: ");
		double x = reader.nextDouble();
		System.out.print("Insert number n: ");
		int n = reader.nextInt();
		double result = power(x,n);
		System.out.println("El resultado es: " + result);
		reader.close();
	}
}