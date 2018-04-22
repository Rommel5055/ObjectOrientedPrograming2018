package exercise12;

public class MaximunInArray {
	public static double maxInRange(double[] array, int lowIndex, int highIndex){
		if (lowIndex == highIndex){
			return array[lowIndex];
		}
		else{
			int maxLeft = ((highIndex - lowIndex)/2) + lowIndex;
			int minRight = highIndex - ((highIndex - lowIndex)/2);
			
			double maxA = maxInRange(array, lowIndex, maxLeft);
			double maxB = maxInRange(array, minRight, highIndex);
			
			if (maxA > maxB){
				return maxA;
			}
			else{return maxB;}
		}
	}

	public static double max(double[] array){
		return maxInRange(array, 0, array.length - 1);
	}
	
	public static void main(String args[]){
		double array[] = new double[8];
		array[0] = 24;
		array[1] = 54;
		array[2] = 23;
		array[3] = 76;
		array[4] = 56;
		array[5] = 34;
		array[6] = 53;
		array[7] = 64;
		double max = max(array);
		System.out.println(max);
	}
	
}
