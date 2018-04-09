package exercise8;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class DateFormat {
	
	public static void printAmerican(String dayOfWeek, String month, int dd, int yy){
		System.out.printf("American Format: \n%s, %s %d, %d \n", dayOfWeek, month, dd, yy);
	}
	
	public static void printEuropean(String dayOfWeek, String month, int dd, int yy){
		System.out.printf("European Format: \n%s %d %s, %d \n", dayOfWeek, dd, month, yy);
	}
	public static String getMonth(int mm){
		List<String> months = new ArrayList<String>();
		months.add("January");
		months.add("February");
		months.add("March");
		months.add("April");
		months.add("May");
		months.add("June");
		months.add("July");
		months.add("August");
		months.add("September");
		months.add("October");
		months.add("Novemver");
		months.add("December");
		
		String month = (String)months.get(mm - 1);
		return month;
	}
	
	public static String getDayOfWeek(int dw){
		List<String> daysOfWeek = new ArrayList<String>();
		daysOfWeek.add("Monday");
		daysOfWeek.add("Tuesday");
		daysOfWeek.add("Wednesday");
		daysOfWeek.add("Thursday");
		daysOfWeek.add("Friday");
		daysOfWeek.add("Saturday");
		daysOfWeek.add("Sunday");
		
		String dayOfWeek = (String)daysOfWeek.get(dw - 1);
		return dayOfWeek;
	}

	public static void main(String[] args) {
		int dw = Integer.parseInt(args[0]);
		int dd = Integer.parseInt(args[1]);
		int mm = Integer.parseInt(args[2]);
		int yy = Integer.parseInt(args[3]);

		Scanner reader = new Scanner(System.in);
		
		String month = getMonth(mm);
		
		String dayOfWeek = getDayOfWeek(dw);
		
		printAmerican( dayOfWeek,  month,  dd, yy);
		
		printEuropean( dayOfWeek,  month,  dd, yy);
		
		System.out.printf("\n0 for American Format\n1 for European Format\n");
		
		int userInput = reader.nextInt();
		
		if (userInput == 0){
			printAmerican( dayOfWeek,  month,  dd, yy);
		}
		else if (userInput == 1){
			printEuropean( dayOfWeek,  month,  dd, yy);
		}
		reader.close();
	}

}

