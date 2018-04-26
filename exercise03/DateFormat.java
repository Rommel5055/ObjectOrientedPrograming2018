package exercise03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DateFormat {
	
	public static List<String> getMonths(){
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
		
		return months;
	}
	
	public static List<String> getDays(){
		List<String> daysOfWeek = new ArrayList<String>();
		daysOfWeek.add("Monday");
		daysOfWeek.add("Tuesday");
		daysOfWeek.add("Wednesday");
		daysOfWeek.add("Thursday");
		daysOfWeek.add("Friday");
		daysOfWeek.add("Saturday");
		daysOfWeek.add("Sunday");
		
		return daysOfWeek;
	}
	
	public static void main(String[] args) {
		int dw = Integer.parseInt(args[0]);
		int dd = Integer.parseInt(args[1]);
		int mm = Integer.parseInt(args[2]);
		int yy = Integer.parseInt(args[3]);

		Scanner reader = new Scanner(System.in);
		
		List<String> months = getMonths();
		
		String month = (String)months.get(mm - 1);
		
		List<String> daysOfWeek = getDays();
		
		String dayOfWeek = (String)daysOfWeek.get(dw - 1);
		
		System.out.printf("American Format: \n%s, %s %d, %d \n", dayOfWeek, month, dd, yy);
		System.out.printf("European Format: \n%s %d %s, %d \n", dayOfWeek, dd, month, yy);
		
		System.out.printf("\n0 for American Format\n1 for European Format\n");
		
		int userInput = reader.nextInt();
		
		if (userInput == 0){
			System.out.printf("American Format: \n%s, %s %d, %d \n", dayOfWeek, month, dd, yy);
		}
		else if (userInput == 1){
			System.out.printf("European Format: \n%s %d %s, %d \n", dayOfWeek, dd, month, yy);
		}
		
		System.out.println("Loop to get all days after the day received");
		int ldm;
		
		mm--;
		//Meses con 31 dias
		if ((mm < 7 && mm % 2 == 0) || (mm >= 7 && mm % 2 == 1)){
			ldm = 31;
		}
		
		//Febrero
		else if (mm == 1){
			if ((yy/100 == 0 && yy/400 == 0) || (yy/100 != 0 && yy/4 == 0)){ // Año bisiesto
				ldm = 29;
			}
			else{
				ldm = 28;
			}
		}
		
		//Meses con 30 dias que no son Febrero
		else{
			ldm = 30;
		}
		
		//Mostrar dias restantes
		for (int cdd = dd + 1; cdd <= ldm; cdd++){
			if (userInput == 0){
				System.out.printf("American Format: \n%s, %s %d, %d \n", dayOfWeek, month, cdd, yy);
			}
			else if (userInput == 1){
				System.out.printf("European Format: \n%s %d %s, %d \n", dayOfWeek, cdd, month, yy);
			}
		}
		reader.close();
	}
}