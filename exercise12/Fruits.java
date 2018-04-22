package exercise12;

public class Fruits {
	public static int banana(int[] a) {
		int kiwi = 1;
		int i = 0;
		while (i < a.length) {
			kiwi = kiwi * a[i];
			i++;
		}
		return kiwi;
	}
	//Returns the value of the multiplication of every elemnt in the array a

	public static int grapefruit(int[] a, int grape) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == grape) {
				return i;
			}
		}
		return -1;
	}
	//It searches for a value in the array. If found it returns where it is, else it returns -1
	
	public static int pineapple(int[] a, int apple) {
		int pear = 0;
		for (int pine: a) {
			if (pine == apple) {
				pear++;
			}
		}
		return pear;
	}
	//counts the number of times the element apple is in the array


}

