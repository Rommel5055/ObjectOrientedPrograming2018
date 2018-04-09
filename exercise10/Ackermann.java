package exercise10;

import java.util.Scanner;

public class Ackermann {

	public static int ack(int m, int n){
		int k = 0;
		if (m == 0){
			k = n+1;
		}
		else if (m > 0 && n == 0){
			k = ack(m-1, 1);
			return k;
		}		
		else if (m>0 && n>0){
			k = ack(m-1, ack(m, n-1));
		}
		return k;
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Insert number m: \n");
		int m = reader.nextInt();
		System.out.print("Insert number m: \n");
		int n = reader.nextInt();
		System.out.print(ack(m,n));
		reader.close();
	}

}
