package exercise13;

public class Recurse {
	public static char first(String s) {
		return s.charAt(0);
	}

	public static String rest(String s) {
		return s.substring(1);
	}
	
	public static String middle(String s) {
		return s.substring(1, s.length() - 1);
	}
	
	public static int length(String s) {
		return s.length();
	}
	
	public static void printString(String s){
		if (length(s) == 1){
			System.out.println(first(s));
		}
		else{
			System.out.println(first(s));
			printString(rest(s));
		}
	}
	
	public static void printBackwards(String s){
		if (length(s) > 0){
			printBackwards(rest(s));
			System.out.println(first(s));
		}
	}
	
	public static String reverseString(String s){
		if (length(s) > 0){
			return reverseString(rest(s)) + first(s);
		}
		return "";
	}
	
	public static boolean isPalindrome(String s){
		String reverse = reverseString(s);
		if (reverse.equals(s)){
			return true;
		}
		else{return false;}
	}
	
	public static void main(String args[]){
		String s = "banana";
		System.out.println(first(s));
		System.out.println(rest(s));
		System.out.println(middle(s));
		System.out.println(length(s));
		System.out.println("");
		printString(s);
		printBackwards(s);
		String backwardsString = reverseString(s);
		System.out.println(backwardsString);
		System.out.println(isPalindrome(s));
	}
}
