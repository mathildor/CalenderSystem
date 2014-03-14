package kalenderSystem;

import java.sql.*;
import java.util.ArrayList;

public class Database {

	DBConnection db;

	//TODO: hente ut liste over alle navn/brukere/eposter som er med i en avtale. Kall listen for participants. 
	//TODO lag getParticipants();
	
	public Database(){
		db = new DBConnection();
	}
	
	//TODO: 
	public ArrayList<String> getParticipants(){
		
	}
	public void deleteAppointment(){
		
	}
	
	public ArrayList<String> getEposter() {
		ArrayList<String> EPOSTER = new ArrayList<String>();
		String sql = "select EPOST from Ansatt;";
		ResultSet rs = db.readQuery(sql);
		try {
			while (rs.next()) {
				EPOSTER.add(rs.getString("EPOST"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return EPOSTER;
	}

	public void addPerson(String Epost, String Navn, String Passord) throws SQLException {
		ArrayList<String> EPOSTER = getEposter();
		if (EPOSTER.contains(Epost)){
			System.out.println("Brukeren finnes allerede, velg en annen epost");
		} else {
			String sql = "insert into Ansatt values('"+Epost+"', '"+Navn+"', '"+Passord+"') on duplicate key update EPOST = EPOST;";
			db.updateQuery(sql);
		}
	}

	public void removePerson(String Epost) throws SQLException{
		String sql = "delete from Ansatt where EPOST = '"+Epost+"';";
		db.updateQuery(sql);
	}

	public String getPassord(String EPOST) {
		String PASSORD = null;
		String sql = "select PASSORD from Ansatt where EPOST = '"+EPOST+"';";
		ResultSet rs = db.readQuery(sql);
		try {
			if (rs.next()) {
				PASSORD = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return PASSORD;
	}

	public ArrayList<String> getAlleNavn(){
		ArrayList<String> NAVN = new ArrayList<String>();
		String sql = "select NAVN from Ansatt;";
		ResultSet rs = db.readQuery(sql);
		try {
			while (rs.next()) {
				NAVN.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return NAVN;
	}

	public String getNavn(String EPOST){
		String Navn = null;
		String sql = "select NAVN from Ansatt where EPOST = '"+EPOST+"';";
		ResultSet rs = db.readQuery(sql);
		try {
			if (rs.next()) {
				Navn = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return Navn;
	}

	public ArrayList<Integer> getAvtaler(){
		ArrayList<Integer> AVTALER = new ArrayList<Integer>();
		String sql = "select AVTALEID from Avtale";
		ResultSet rs = db.readQuery(sql);
		try {
			while (rs.next()) {
				AVTALER.add(rs.getInt("AVTALEID"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return AVTALER;
	}

	public String getVarighet(int AVTALEID) {
		String sql1 = "select STARTTID from Avtale where AVTALEID = "+AVTALEID;
		String sql2 = "select SLUTTTID from Avtale where AVTALEID = "+AVTALEID;
		String sql3 = "select TIMEDIFF (("+sql2+"), (" +sql1+"));";
		ResultSet rs = db.readQuery(sql3);
		String varighet = null;
		try {
			if (rs.next()){
				varighet = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return "Møte "+Integer.toString(AVTALEID)+" varer i "+varighet+"(TT:MM:SS)";
	}

	public String getMoterom(int AVTALEID){
		String moterom = null;
		String sql = "select MOTEROM from Avtale where AVTALEID =" +AVTALEID+ ";";
		ResultSet rs = db.readQuery(sql);
		try {
			if (rs.next()){
				moterom = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return moterom;
	}

	public ArrayList<String> getMoteromListe(){
		ArrayList<String> moteromListe = new ArrayList<String>();
		String sql = "select ROMNAVN from Moterom;";
		ResultSet rs = db.readQuery(sql);
		try {
			while (rs.next()) {
				moteromListe.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return moteromListe;
	}

	public int getStorrelse(String moterom){
		int storrelse = 0;
		String sql = "select STORRELSE from Moterom where ROMANVN =" +moterom+ ";";
		ResultSet rs = db.readQuery(sql);
		try {
			if (rs.next()){
				moterom = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return storrelse;
	}

	public String getMoteLeder(int ID){
		return "yo";
	}
//LEGGE TIL SLUTT- OG STARTDATO OGSÅ??
	public void addAvtale(String starttid, String slutttid, String beskrivelse, String sted, String moterom, String motesjef) {
		//gjør om til Date
		String sql = "insert into Avtale (STARTTID, SLUTTTID, BESKRIVELSE, STED, MOTEROM, MOTESJEF) values('"+starttid+"', '"+slutttid+"', '"+beskrivelse+"', '"+sted+"', '"+moterom+"', '"+motesjef+"');";
		if (moterom == null && sted == null) {
			sql = "insert into Avtale (STARTTID, SLUTTTID, BESKRIVELSE, STED, MOTEROM, MOTESJEF) values('"+starttid+"', '"+slutttid+"', '"+beskrivelse+"', "+"null"+", "+"null"+", '"+motesjef+"');";
		} else if (moterom == null) {
			sql = "insert into Avtale (STARTTID, SLUTTTID, BESKRIVELSE, STED, MOTEROM, MOTESJEF) values('"+starttid+"', '"+slutttid+"', '"+beskrivelse+"', '"+sted+"', "+"null"+", '"+motesjef+"');";
		} else if (sted == null) {
			sql = "insert into Avtale (STARTTID, SLUTTTID, BESKRIVELSE, STED, MOTEROM, MOTESJEF) values('"+starttid+"', '"+slutttid+"', '"+beskrivelse+"', "+"null"+", '"+moterom+"', '"+motesjef+"');";
		} else {
			System.out.println("Feil med addAvtale");
		}
		db.updateQuery(sql);
	}

	public ArrayList<String> ledigeMoterom(int ID){
		ArrayList<String> ledigeMoterom = new ArrayList<String>();
		ArrayList<String> opptatteMoterom = new ArrayList<String>();
		ArrayList<String> alleMoterom = new ArrayList<String>();
		String start = getStartTid(ID);
		String end = getSluttTid(ID);
		String sql1 = "SELECT Moterom FROM Avtale WHERE NOT (STARTTID < '"+start+"' and";
		String sql2 = " SLUTTTID < '"+start+"' OR STARTTID > '"+end+"' and SLUTTTID > '"+end+"');";
		ResultSet rs = db.readQuery(sql1+sql2);
		try {
			while (rs.next()){
				opptatteMoterom.add(rs.getString(1));
			}
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
		alleMoterom = this.getMoteromListe();
		for (int i = 0; i<alleMoterom.size();i++){

			if (opptatteMoterom.contains(alleMoterom.get(i))){
			} else {
				ledigeMoterom.add(alleMoterom.get(i));
			}
		}
		return ledigeMoterom;
	}

	public String getStartTid(int ID){
		String startTid = null;
		String sql = "select STARTTID from Avtale where AVTALEID = "+ID+";";
		ResultSet rs = db.readQuery(sql);
		try {
			if (rs.next()){				
				startTid = rs.getString(1);
			}
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
		return startTid;
	}
	public String getSluttTid(int ID){
		String sluttTid = null;
		String sql = "select SLUTTTID from Avtale where AVTALEID = "+ID+";";
		ResultSet rs = db.readQuery(sql);
		try {
			if (rs.next()){
				sluttTid = rs.getString(1);				
			}
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
		return sluttTid;
	}
}