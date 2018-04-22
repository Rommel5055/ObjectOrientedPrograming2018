package exercise13;

import java.util.Scanner;

public class Encapsulation {

	public static int counter(String s, char a, char b){
		int count = 0;
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (c == a){
				count++;
			}
			else if (c == b){
				count--;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String s = reader.next();
		String a = reader.next();
		String b = reader.next();
		System.out.println(counter(s, a.charAt(0), b.charAt(0)));
		reader.close();
	}

}
