package kalenderSystem;


import java.util.ArrayList;
import java.util.Scanner;

public class GUI {

	Ansatt user;
	Appointment appointment;
	boolean checkEpost;
	Database db;

	public GUI(){
		db=new Database();
		checkEpost=false;
		appointment=new Appointment();
	}

	public static void main(String[] args) {
		GUI gui=new GUI();
		//gui.help();
		gui.startMenu();
		if(gui.logIn()){
			gui.run();
		}
	}

	public void startMenu(){
		System.out.println("1. Logg inn: ");
		System.out.println("2. Oprett nye bruker: ");
		Scanner scan=new Scanner(System.in);
		int input=scan.nextInt();
		switch(input){
		case 1: this.logIn();
		case 2: appointment.addUser();

		}

	}
	public void run(){
		System.out.println("YOU MADE IT!!!");
		Scanner scan=new Scanner(System.in);
		String input = "";
		while(!input.equals("avslutt")){
			help();
			input=scan.nextLine();
			if(input.equals("endre")){//TODO: && (user.EPOST==avtaleID.meetingLeader.epost)){
				System.out.println("du er i endre");
				appointment.editAppointment();
			}
			else if(input.equals("vis")){
				appointment.showCalendar();
				break;
			}

			else if(input.equals("opprett")){
				appointment.createAppointment(user);
			}

			else if(input.equals("visBrukerKal")){
				appointment.showUserCalendar();
				break;
			}

			else if(input.equals("varsel")){
				appointment.addAlarm();
				break;
			}
			else if(input.equals("slette")){//TODO: && (user.EPOST==avtaleID.meetingLeader.epost)){
				appointment.deleteAppointment();;
				break;
			}
		}
	}


	public void help(){
		System.out.println("Her er dine tilgjengelige valg: \n");
		System.out.println("KOMMANDO:	BESKRIVELSE AV KOMMANDO:");
		System.out.println("opprett       	Oppretter nye avtale");
		System.out.println("endre		Endre opprettede avtaler");
		System.out.println("vis		Viser egen kalender");
		System.out.println("visBrukerKal	Viser en annens bruker kalender");
		System.out.println("varsel        	Setter varsel på en avtale");
		System.out.println("slette		Slette avtale");
		System.out.println("avslutt		Avslutt og logg ut av programmet");
		System.out.println("   ");

	}

	public boolean logIn(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("epost: ");
		String epost = scan.nextLine();

		while(!isValidEpost(epost)){
			System.out.println("Feil epost, prøv igjen: ");
			epost=scan.nextLine();
		}
		System.out.println("passord: ");
		String password=scan.nextLine();
		while(!isValidPassword(password, epost)){
			System.out.println("Feil passord, prøv igjen: ");
			password=scan.nextLine();
		}
		System.out.println("Du er nå logget inn");
		this.user = new Ansatt(epost);
		return true;
	} 


	public boolean isValidEpost(String epost){
		ArrayList<String> eposter = db.getEposter();
		if (eposter.contains(epost)){
			return true;
		}
		else
			return false;

	}

	public boolean isValidPassword(String password, String epost){
		if(db.getPassord(epost).equals(password)){
			return true;
		}
		else
			return false;
	}
	//TODO: trenger vi denne? eller skal vi bare avslutte alt istede for å logge ut?
	public void loggUt(){
		this.user = null;
	}
}
