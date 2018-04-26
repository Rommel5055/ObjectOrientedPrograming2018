package exercise14;

import java.math.BigInteger;

public class Big {
	public static int factorial(int n){
		int result = 1;
		for (int i = n; i > 0; i--){
			result *= i; 
		}
		return result;
	}
	
	public static BigInteger bigFactorial(int n){
		BigInteger result = BigInteger.valueOf(1);
		for (int i = n; i > 0; i--){
			BigInteger multiplier = BigInteger.valueOf(i);
			result = result.multiply(multiplier); 
		}
		return result;
	}
	
	public static void main(String[] args){
		int x = 30;
		for (int i = 0; i < x; i++){
			int result = factorial(i);
			System.out.println("El resultado de " + i + "! es: " + result);
			} 
		//The answers are not right after a certain point because the number is bigger than what int can handle
		
		System.out.println("");
		
		for (int i = 0; i < x; i++){
			BigInteger result = bigFactorial(i);
			System.out.println("El resultado de " + i + "! es: " + result);
		}
		//BigInteger is not mutable, but there is a MutableBigInteger class and it is mutable
	}
}
