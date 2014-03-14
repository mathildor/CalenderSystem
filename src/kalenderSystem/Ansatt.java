package kalenderSystem;

//Kommentar
public class Ansatt {
	
	String EPOST;
	String NAVN;
	Database db;


	public Ansatt(String EPOST){
		this.EPOST = EPOST;
		db = new Database();
		this.NAVN = db.getNavn(this.EPOST);
	}
	
	public void byttPassord(String nyttPO){
		
	}

}
