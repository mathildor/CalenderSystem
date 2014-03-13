package kalenderSystem;

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Avtale {

	Date date;
	boolean checkDate;
	String EPOST;
	String meetingLeader;
	
	
	Ansatt ansatt=new Ansatt(EPOST);
	
	public Avtale(){
		
	}

	public void editAppointment(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Hva vil du gjøre?");
		System.out.println("1. Endre starttid");
		System.out.println("2. Endre sluttid");
		System.out.println("3. Endre startdato");
		System.out.println("4. Endre sluttdato");
		System.out.println("5. Endre beskrivelse");
		System.out.println("Skriv inn tall endring du ønsker å gjøre");
		
		int input =scan.nextInt();
		switch (input){
		case 1: this.editStart();
		break;
		case 2: this.editSlutt();
		break;
		case 3: this.editMenu();
		break;
		case 4: this.editDate();
		break;
		case 5: this.editBeskrivelse();
		break;
		
		default: System.out.println("Dette er ikke en gyldig kommando");
		this.editAppointment();
		break;
		}
	}



	
	public void editMenu(){
		System.out.println("Angi meny: ");
		Scanner scan = new Scanner(System.in); 
		String id = scan.nextLine();


	}
	public void editDate(){
		System.out.println("ny tid: ");
		Scanner scan = new Scanner(System.in); 
		String id = scan.nextLine(); 

	}

	public void editMoterom(){
		System.out.println("Angi nytt møterom: ");
		Scanner scan=new Scanner(System.in);
		String id=scan.nextLine();
	}


	public void editSted(){
		System.out.println("nytt sted: ");
		Scanner scan = new Scanner(System.in); 
		String id = scan.nextLine(); 


	}
	public void editBeskrivelse(){
		System.out.println("ny beskrivelse: ");
		Scanner scan = new Scanner(System.in); 
		String id = scan.nextLine(); 

	}
	public void editSlutt(){
		System.out.println("ny sluttid: ");
		Scanner scan = new Scanner(System.in); 
		String id = scan.nextLine(); 

	}
	public void editStart(){
		System.out.println("ny starttid: ");
		Scanner scan = new Scanner(System.in); 
		String id = scan.nextLine(); 

	}
	
	
	
	//METODE FOR Å OPPRETTE AVTALER
	//SIKRE SLUTT SENERE ENN START HVIS NOK TID
	public void createAvtale(){
		meetingLeader=ansatt.EPOST;
		Scanner scan = new Scanner(System.in);

		checkDate=false;
		String startTime="";
		while(checkDate==false){
			System.out.println("Angi starttid i TT:MM ");
			startTime=scan.next();
			testInputDate("HH:MM", startTime);
		}
		checkDate=false;
		String startDate="";
		while(checkDate==false){
			System.out.println("Angi startdato i YYYY-MM-DD: ");
			startDate=scan.next();
			testInputDate("yyyy-MM-dd", startDate);
		}
		Date startDateAndTime= parseDate("HH:mm yyyy-MM-dd", startTime+" "+startDate);


		checkDate=false;
		String endTime="";
		while(checkDate==false){
			System.out.println("Angi slutt-tid i TT:MM ");
			endTime=scan.next();
			testInputDate("HH:MM", endTime);
		}

		checkDate=false;
		String endDate="";
		while(checkDate==false){
			System.out.println("Angi slutt dato i YYYY-MM-DD: ");
			endDate=scan.next();
			testInputDate("yyyy-MM-dd", endDate);
		}

		Date endDateAndTime= parseDate("HH:mm yyyy-MM-dd", endTime+" "+endDate);


		System.out.println(startDateAndTime);
		System.out.println(endDateAndTime);

	}

	public void testInputDate(String format, String strDate){

		SimpleDateFormat dateFormater = new SimpleDateFormat (format);

		try {
			dateFormater.parse(strDate);
			checkDate=true;;

		} catch (ParseException e){
			checkDate=false;
		}

	}


	private Date parseDate(String format, String strDate) {


		SimpleDateFormat dateFomater = new SimpleDateFormat (format);
		Date newDate = null;
		try { 
			newDate = dateFomater.parse(strDate); 
			checkDate=true;

		} catch (ParseException e) { 

			System.out.println(strDate + " er unparseable using " + format); 
			checkDate=false;

		}

		return newDate;

	}

	// to do: create avtale
	// if setniner så kun møteleder kan endre
	// deleateAvtale?
	// Oversikt over avtaler per uke
	// kunne hente ut alle avtaler man er med i 
	// liste over de som er med i møtet
	// kunne melde avbud

	// addAvtale: 
	// slutt og start tid: YYYY-MM-DD TT:MM:SS
	// beskrivelse av møte String, kan null
	// sted String, kan null
	// møterom må finnes i liste, navn: moteromListe
	// møtesjef String
	// sette møtesjef = bruker
	// møtesjef navn = anstatt navn for å kunne gjøre endringer

}

