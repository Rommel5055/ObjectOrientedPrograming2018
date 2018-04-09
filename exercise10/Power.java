package exercise10;

import java.util.Scanner;

public class Power {

	public static double power(double x, int n){
		double value = 0;
		if (n == 0){
			value = 1.0;
		}
		else{
			value = x * power(x, n-1);
		}
		return value;
	}
	
	public static double optimizedPower(double x, int n){
		double value = 0;
		if (n == 0){
			value = 1.0;
		}
		else{
			if((n-1)%2 == 0 && (n-1)!=0){
				value = x * optimizedPower(optimizedPower(x, ((n-1)/2)), 2);
			}
			else{
				value = x * optimizedPower(x, n-1);
			}
		}
		return value;
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Insert number X: ");
		double x = reader.nextDouble();
		System.out.print("Insert number n: ");
		int n = reader.nextInt();
		System.out.print(power(x,n) + "\n");
		System.out.print(optimizedPower(x,n)+"\n");
		reader.close();
	}

}
