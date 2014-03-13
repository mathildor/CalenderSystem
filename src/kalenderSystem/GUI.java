package kalenderSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class GUI {
	
	Ansatt bruker;
	
	public void help(){
		System.out.println("visKalender			her kan du se kalendern");
		System.out.println("opprettAvtale       her kan du lage en ny hendelse");
		System.out.println("settPåVarsel        sett opp varsel på en avtale");
		System.out.println("seValgtBrukersAvtaler    sneek peek i andres liv");
		this.kommandoer();
	}
	
	public void loggInn(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("epost: ");
		String inpute = scan.nextLine();
		Database db = new Database();
		ArrayList<String> eposter = db.getEposter();
		if (eposter.contains(inpute)){
			System.out.println("passord: ");
			String inputp = scan.nextLine();
			while (!(db.getPassord(inpute).equals(inputp))){
				System.out.println("feil passord, prøv på nytt");
				inputp = scan.nextLine();
			}
			System.out.println("Du er nå logget inn");
			this.bruker = new Ansatt(inpute);
		} else {
			System.out.println("Epost eksisterer ikke bruriiii!!");
			this.kommandoer();
		}
	}
	
	public void loggUt() {
		this.bruker = null;
		this.kommandoer();
	}

	public void opprettNyBruker() {
		
	}
	
	public void kommandoer(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Hva vil du gjøre?");
		System.out.println("1. Logg inn");
		System.out.println("2. Opprett ny bruker");
		System.out.println("3. Liste over gyldige kommandoer etter du er logget inn");
		//føkk GIT
		String input = sc.next();
		switch (input) {
			case "1": this.loggInn();
				break;
			case "2": this.opprettNyBruker();
				break;
			case "3": this.help();
				break;
			default: System.out.println("Dette er ikke en gyldig kommando");
				this.kommandoer();
				break;
		}
	}
	


}
