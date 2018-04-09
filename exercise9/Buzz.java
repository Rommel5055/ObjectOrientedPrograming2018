package exercise9;

//Exercise 5.6 from the book

public class Buzz {
	public static void baffle(String blimp) {			//07
		System.out.println(blimp);						//08
		zippo("ping", -5);								//09
		}
	
	public static void zippo(String quince, int flag) { //02,10
		if (flag < 0) {									//03,11
			System.out.println(quince + " zoop");		//12
		} 
		else {											//04
			System.out.println("ik");					//05
			baffle(quince);								//06
			System.out.println("boo-wa-ha-ha");			//12
			}
		}
	
	public static void main(String[] args) { 			//00
		zippo("rattle", 13);							//01
		}

}

//03.- The value of blimp when baffle() gets involved is "rattle"
//04.- the output of the program is (without the "/*" and "*/")
/*
ik
rattle
ping zoop
boo-wa-ha-ha
*/
