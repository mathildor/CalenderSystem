package kalenderSystem;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//Avtaleobjektet inneholder ingenting, alle felt er tomme. Må kalle på metodene i avtaleklassen for å fylle feltene i avtalobjektet
public class Appointment {

	Date date;
	boolean checkDate;
	String EPOST;
	String startDate, endDate, place, meetingRoom;
	String startTime="";
	String endTime="";
	String description="";
	int week;
	String meetingLeader; //epost til møtesjef
	Database db;
	Ansatt user;
	int avtaleID = 0;



	public static void main(String[] args) {
		//Database db = new Database();
		//System.out.println(db.ledigeMoterom(5));
		Appointment aev= new Appointment();
		//aev.editAppointment();
		//aev.createAppointment(user);
	}

	public Appointment(){
		db=new Database();
	}

	public void editAppointment(){

		Scanner scan = new Scanner(System.in);

		System.out.println("Skriv inn avtaleID'n til den avtalen du vil endre: ");
		ArrayList<Integer>avtaler=db.getAvtaler();
		for(int avtale : avtaler){
			if(db.getMoteLeder(avtale) == user.EPOST){
				System.out.println(avtale);
			}
		}
		avtaleID = scan.nextInt();


		System.out.println("Hva vil du gjøre?");
		System.out.println("1. Endre starttid");
		System.out.println("2. Endre sluttid");
		System.out.println("3. Endre startdato");
		System.out.println("4. Endre sluttdato");
		System.out.println("5. Endre beskrivelse");
		System.out.println("6. Endre møterom");
		System.out.println("7. Endre sted");
		System.out.println("8. Gå tilbake til hovedmeny");
		System.out.println("Skriv inn tall på endring du ønsker å gjøre: ");

		int input =scan.nextInt();
		switch (input){
		case 1: this.editStartTime();
		break;
		case 2: this.editEndTime();
		break;
		case 3: this.editStartDate();
		break;
		case 4: this.editEndDate();
		break;
		case 5: this.editDescription();
		break;
		case 6: this.editMeetingRoom();
		break;
		case 7: this.editPlace();
		break;
		case 8: return;

		default: System.out.println("Dette er ikke en gyldig kommando");
		this.editAppointment();
		break;
		}
	}


	public void editStartTime(){

		checkDate=false;
		Scanner scan = new Scanner(System.in); 
		while(checkDate==false){
			System.out.println("Angi ny starttid i TT:MM: ");
			startTime = scan.nextLine();
			testInputDate("HH:MM", startTime);
		}	
		System.out.println("Starttid ble endret");
		//TODO: Endre avtalen som skal endres utifra avtaleID, så må lage en database-metode som heter endre avtale
	}

	public void editEndTime(){
		checkDate=false;
		Scanner scan = new Scanner(System.in);
		while(checkDate==false){
			System.out.println("Angi ny sluttid i TT:MM ");
			endTime = scan.nextLine();
			testInputDate("HH:MM", endTime);
		}
		System.out.println("slutt-tid ble endret");
		//TODO: Endre avtalen som skal endres utifra avtaleID, så må lage en database-metode som heter endre avtale
	}


	public void editStartDate(){
		checkDate=false;
		Scanner scan = new Scanner(System.in);
		while(checkDate==false){
			System.out.println("Angi ny startdato YYYY-MM-DD: ");
			startDate= scan.nextLine(); 
			testInputDate("yyyy-MM-dd", startDate);
		}
		System.out.println("Startdato ble endret");
		//TODO: Endre avtalen som skal endres utifra avtaleID, så må lage en database-metode som heter endre avtale
	}
	public void editEndDate(){
		checkDate=false;
		Scanner scan = new Scanner(System.in);
		while(checkDate==false){
			System.out.println("Angi ny sluttdato i YYYY-MM-DD: ");
			endDate= scan.nextLine();
			testInputDate("yyyy-MM-dd", endDate);
		}
		System.out.println("Sluttdato ble endret");
		//TODO: Endre avtalen som skal endres utifra avtaleID, så må lage en database-metode som heter endre avtale
	}

	public void editDescription(){
		checkDate=false;
		Scanner scan = new Scanner(System.in);
		while(checkDate==false){
			System.out.println("Angi ny beskrivelse på en linje: ");
			description= scan.nextLine(); 
		}
		System.out.println("Beskrivelse ble endret til: "+description);
		//TODO: Endre avtalen som skal endres utifra avtaleID, så må lage en database-metode som heter endre avtale
	}

	public void editMeetingRoom(){
		checkDate=false;
		Scanner scan = new Scanner(System.in);
		while(checkDate==false){
			System.out.println("Angi nytt møterom: ");
			meetingRoom= scan.nextLine(); 
		}
		System.out.println("Møterom ble endret");
		//TODO: Endre avtalen som skal endres utifra avtaleID, så må lage en database-metode som heter endre avtale
	}


	public void editPlace(){
		checkDate=false;
		Scanner scan = new Scanner(System.in);
		while(checkDate==false){
			System.out.println("Angi ny sted: ");
			place= scan.nextLine(); 
		}
		System.out.println("Sted ble endret");
		//TODO: Endre avtalen som skal endres utifra avtaleID, så må lage en database-metode som heter endre avtale
	}


	//METODE FOR Å OPPRETTE AVTALER
	//TODO:SIKRE SLUTT SENERE ENN START HVIS NOK TID
	//Nederst kaller den på addAppointment i db-klassen så avtalen blir lagret i databasen

	public void createAppointment(Ansatt user){
		//meetingLeader=ansatt.EPOST;
		meetingLeader= user.EPOST;
		Scanner scan = new Scanner(System.in);


		this.checkDate=false;

		while(checkDate==false){
			System.out.println("Angi starttid i TT:MM ");
			this.startTime=scan.nextLine();
			testInputDate("HH:MM", startTime);
		}
		this.checkDate=false;

		while(checkDate==false){
			System.out.println("Angi startdato i YYYY-MM-DD: ");
			this.startDate=scan.nextLine();
			testInputDate("yyyy-MM-dd", startDate);
		}
		Date startDateAndTime= parseDate("HH:mm yyyy-MM-dd", startTime+" "+startDate);


		this.checkDate=false;

		while(checkDate==false){
			System.out.println("Angi slutt-tid i TT:MM ");
			this.endTime=scan.nextLine();
			testInputDate("HH:MM", endTime);
		}

		this.checkDate=false;
		while(checkDate==false){
			System.out.println("Angi slutt dato i YYYY-MM-DD: ");
			this.endDate=scan.nextLine();
			testInputDate("yyyy-MM-dd", endDate);
		}


		Date endDateAndTime= parseDate("HH:mm yyyy-MM-dd", endTime+" "+endDate);
		Calendar cal=Calendar.getInstance();
		cal.setTime(startDateAndTime);
		this.week = cal.get(Calendar.WEEK_OF_YEAR);

		System.out.println(startDateAndTime +", uke: "+week);
		System.out.println(endDateAndTime);


		System.out.println("Angi beskrivelse for avtalen i en linje: ");
		description+=scan.nextLine();

		System.out.println("Angi sted for møtet: ");
		place=scan.nextLine();

		System.out.println("Angi møterom du ønsker fra denne lista: ");
		//print liste over ledige møterom
		ArrayList<String>meetingRoomList= db.getMoteromListe();
		System.out.println(meetingRoomList);
		this.meetingRoom=scan.nextLine();

		//TODO: SJEKK OM RIKTIG FORMAT I INPUT

		db.addAvtale(startTime+":00", endTime+":00", description, place, meetingRoom, meetingLeader);

	}

	public void testInputDate(String format, String strDate){

		SimpleDateFormat dateFormater = new SimpleDateFormat (format);

		try {
			dateFormater.parse(strDate);
			this.checkDate=true;;

		} catch (ParseException e){
			this.checkDate=false;
		}

	}


	private Date parseDate(String format, String strDate) {


		SimpleDateFormat dateFormater = new SimpleDateFormat (format);
		Date newDate = null;
		try { 
			newDate = dateFormater.parse(strDate); 

		} catch (ParseException e) { 

			System.out.println(strDate + " er unparseable using " + format); 
		}
		return newDate;
	}


	public void showCalendar(){
		Scanner scan = new Scanner(System.in);

		System.out.println("Din kalender: ");
		ArrayList<Integer>avtaler=db.getAvtaler();
		ArrayList<String>participants=db.getParticipants();
		for(int avtale : avtaler){
			if(participants.contains(user.EPOST)){
				System.out.println(avtale); //TODO: print calendar istede
			}
		}
		avtaleID = scan.nextInt();
	}

	public void showUserCalendar(){
		Scanner scan=new Scanner(System.in);
		boolean check=false;
		while(!check){
			System.out.println("Skriv inn eposten til brukeren du vil se kalendere til: ");
			String userEmail=scan.nextLine();
			if(db.getEposter().contains(userEmail)){
				check=true;
				ArrayList<Integer>avtaler=db.getAvtaler();
				ArrayList<String>participants=db.getParticipants();
				for(int avtale : avtaler){
					if(participants.contains(userEmail)){
						System.out.println(avtale);//TODO: print calendar istede
					}
				}
			}
			else
				check=false;
		}
	}


	public void addAlarm(){
		//Skal kunne legge til alarm til alle avtaler som bruker er med på
		//Må sjekke om bruker er participant
		// lager så alarm ved å sende mail?? få opp beskjedi kalender? hmmm.. dånåå

	}

	public void sendAlarm(){
		//Skal sende alarm til alle deltakere om at møtet er eksempel slettet. Sjekk krav for hva mer
	}


	public void deleteAppointment(){
		db.deleteAppointment(int avtaleID);

	}

	public void addUser(){
		boolean check=false;

		while(!check){
			Scanner scan=new Scanner(System.in);
			System.out.println("Skriv inn din epost: ");
			String email=scan.nextLine();
			System.out.println("Skriv inn passord: ");
			String password=scan.nextLine();
			System.out.println("Skriv inn ditt navn: ");
			String name=scan.nextLine();

			try {
				db.addPerson(email, name, password);
				check=true;
			} catch (SQLException e) {
				check=false;
				e.printStackTrace();
			}
		}
	}
}

//TODO: lage metodene i run i GUI?
//Legge til andre i avtale
//slette brukere
//


