package exercise10;

public class Exercise65 {
	public static void main(String[] args) {
		boolean flag1 = isHoopy(202);
		boolean flag2 = isFrabjuous(202);
		System.out.println(flag1);
		System.out.println(flag2);
		if (flag1 && flag2) {
			System.out.println("ping!");
		}
		if (flag1 || flag2) {
			System.out.println("pong!");
		}
	}
	
	public static boolean isHoopy(int x) {
		boolean hoopyFlag;
		if (x % 2 == 0) {
			hoopyFlag = true;
		} else {
			hoopyFlag = false;
		}
		return hoopyFlag;
	}
	
	public static boolean isFrabjuous(int x) {
		boolean frabjuousFlag;
		if (x > 0) {
			frabjuousFlag = true;
		} else {
			frabjuousFlag = false;
		}
		return frabjuousFlag;
	}

	
}

/*
 * print value of flag1, which is true
 * print value of flag2, which is also true
 * flag1 AND flag2 are both true, so print "ping!"
 * being both flag1 and flag2 true, flag1 or fag2 is also true, so print "pong!"
 * final output:
true
true
ping!
pong!
*/