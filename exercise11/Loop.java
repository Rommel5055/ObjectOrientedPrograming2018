package exercise11;

public class Loop {
	public static void main(String[] args) {
		loop(999999999);
		}
	public static void loop(int n) {
		int i = n;
		while (i > 1) {
			System.out.println(i);
			if (i % 2 == 0) {
					i = i / 2;
			} else {
				i = i + 1;
			}
		}
	}
}