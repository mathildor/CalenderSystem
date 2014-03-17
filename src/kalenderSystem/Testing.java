package kalenderSystem;

import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		Appointment app=new Appointment();
		
//		String startTime="12:01";
//		String startTimeValue = startTime.substring(0, 2) + startTime.substring(3,5);
//		System.out.println(startTimeValue);
//		int startInt=Integer.parseInt(startTimeValue);
//		System.out.println(startInt);
//		System.out.println(startInt+100);
//		
//		String startDate= "2014-01-22";
//		String startYear=startDate.substring(0,4);
//		String startMonth=startDate.substring(5,7);
//		String startDay=startDate.substring(8,10);
//		System.out.println("year: "+startYear+" month: "+ startMonth+ " day: " +startDay);
//		
//		boolean check=true;
//		while(check){
//			System.out.println("while");
//			Scanner scan=new Scanner(System.in);
//			String input=scan.nextLine();
//			if(input.equals("2014")){
//				break;
//			}
//			else
//				System.out.println("else");
//				check=false;
//			
//		}
//		
		app.createAppointment();
	}
}
