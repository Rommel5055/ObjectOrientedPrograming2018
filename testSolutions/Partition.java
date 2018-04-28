package testSolutions;

public class Partition {

	public static void recursivePartition(int n, int max, String str){
		if (n == 0){
			System.out.println(str);
			return;
		}
		else{
			for (int i = Math.min(n, max); i >= 1; i--){
				recursivePartition(n-i, i, str + " " + i);
			}
		}
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		recursivePartition(n, n, " ");
	}
}