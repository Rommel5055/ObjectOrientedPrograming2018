package exercise12;

public class Sieve {

	public static boolean[] sieve(int n){
		boolean[] finalArray = new boolean[n];
		for (int i = 0; i < n; i++){
			boolean control = true;
			for (int j = 2; j < i; j++){
				if (i % j == 0){
					control = false;
					break;
				}
			finalArray[i] = control;
			}
		}
		return finalArray;
	}
	
	public static void main(String[] args) {
		int n = 1787;
		boolean[] values = new boolean[n];
		values = sieve(n);
		for (int i = 0; i < values.length; i++)
			System.out.println(values[i]);
	}

}
