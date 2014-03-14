package kalenderSystem;


import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
	
		/**
		 * Handels connection to database server.
		 * 
		 * 
		 */

		private static Connection connection = null;
		private static String connectionURL = "jdbc:mysql://mysql.stud.ntnu.no:3306/christdj_fp";
		@SuppressWarnings("unused")
		private static Statement stat = null;
		private static String user = "christdj";
		private static String pw = "gruppe41";
		@SuppressWarnings("unused")
		private static String database = "christdj_fp";

		/**
		 * Logger på databasen.
		 * 
		 * @throws Exception
		 */
		// Logger inn i databasen
		public DBConnection() {
			try { 
				connection = DriverManager.getConnection(connectionURL, user, pw);
				stat = connection.createStatement();
			} catch (Exception ex) {
				System.out.println("Tilkobling til databaseserver feilet: "+ ex.getMessage());
				System.out.println("For å koble til databaseserveren må du være på NTNU-nettet. Bruk VPN hvis du er utenfor NTNU-nettet.");
			}
		}

		/**
		 * Stenger tilkoblingen mot databasen
		 * 
		 * @throws SQLException
		 */
		public void close() {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * SQL Query that do not alter the database. Eg. SELECT queries
		 * 
		 * @param sql
		 * @return ResultSet
		 * @throws SQLException
		 */
		public ResultSet readQuery(String sql) {
			Statement s;
			try {
				s = connection.createStatement();
				ResultSet rs;
				rs = s.executeQuery(sql);
				return rs;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * SQL Query that alters the database. Eg. CREATE TABLE, INSERT, UPDATE,
		 * DELETE querys.
		 * 
		 * @param sql
		 * @throws SQLException
		 */

		public void updateQuery(String sql) {
			Statement s;
			try {
				s = connection.createStatement();
				s.executeUpdate(sql);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * SQL Query that INSERT in the DB and return the keys
		 * 
		 * @param sql
		 * @return keylist
		 * @throws SQLException
		 */
		public ArrayList<Integer> insertAndGetKeysQuery(String sql) {
			Statement s;
			try {
				s = connection.createStatement();
				s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				ResultSet keys = s.getGeneratedKeys();
				ArrayList<Integer> keyList = new ArrayList<Integer>();
				int i = 1;
				while (keys.next()) {
					keyList.add(keys.getInt(i));
					i++;
				}
				return keyList;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public Connection getConnection() {
			return connection;
		}

}
