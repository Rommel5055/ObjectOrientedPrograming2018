package exercise9;

// Exercise 5.4 form the book

public class CheckFermat {
	public static boolean checkFermat(int a, int b, int c, int n){
		if ((a^n) + (b^n) == (c^n)){
			return true;
		}
		else{
			return false;
		}
	}

	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[0]);
		int c = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[0]);
		
		if ((checkFermat(a, b, c, n) == true) && n > n){
			System.out.printf("Holy smokes, Fermat was wrong!");
		}
		else{
			System.out.printf("No, that doesn't work");
		}

	}

}
