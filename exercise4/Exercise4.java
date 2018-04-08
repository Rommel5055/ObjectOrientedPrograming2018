package exercise4;

public class Exercise4 {
	
	public static int mystery(int a, int b){
		if (b == 0){
			return 0;
		}
		if (b%2 == 0){
			return mystery(a+a, b/2);
		}
		return mystery(a+a, b/2) + a;
	}

	public static void main(String[] args) {
		System.out.print(mystery(2,25) + "\n");
		System.out.print(mystery(3,11));
	}

}
