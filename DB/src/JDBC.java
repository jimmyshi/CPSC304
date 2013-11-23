import java.sql.*;

import javax.swing.JOptionPane;

class JDBC {
	
	Connection con = null;
	
	public JDBC()
	{
		String userid = "ora_n2d8";
		String passwd = "a43221118";
		String url = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.exit(-1);
		}

		try {
			con = DriverManager.getConnection(url, userid, passwd);
		} catch (Exception e) {
			System.out.println("Connection failed\n" + e);
		}
	}
	
	public ResultSet GetAllTableNames(){
		ResultSet rs = null;
		try{
			String query = "select table_name from user_tables";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException ex){
			System.out.println(ex);
		}
		return rs;
	}
	
	public ResultSet SelectData(String table, String where)
	{
		ResultSet rs = null;
		
		try {
			if(where.isEmpty())
			{
				String query = "SELECT * FROM " + table;
				System.out.println(query);
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			String query = "SELECT * FROM " + table + " WHERE " + where ; 
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return rs;
	}
	
	public ResultSet InsertData2(String table, String value1, String value2)
	{
		ResultSet rs = null;
		
		try {
			String query = "INSERT INTO " + table + " VALUES (" + "'" + value1 + "'" + "," + value2 + ")"; 
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null,
					ex, "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
		return rs;
	}
	
	public ResultSet DeleteData(String table, String column, String value)
	{
		ResultSet rs = null;
		
		try {
			String query = "DELETE FROM " + table + " WHERE " + column +"=" + "'" + value + "'"; 
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null,
					ex, "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
		return rs;
	}
	
	public ResultSet JoinData(boolean duplicate, String select, String from, String where)
	{
		ResultSet rs = null;
		
		try {
			if(where.isEmpty() && duplicate)
			{
				String query = "DISTINCT SELECT " + select + " FROM " + from; 
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			if(where.isEmpty())
			{
				String query = "SELECT " + select + " FROM " + from; 
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			if(duplicate)
			{
				String query = "DISTINCT SELECT " + select + " FROM " + from + " WHERE " + where; 
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			String query = "SELECT " + select + " FROM " + from + " WHERE " + where; 
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return rs;
	}
	
	public ResultSet ViewData(boolean duplicate, String name, String select, String from, String where)
	{
		ResultSet rs = null;
		
		try {
			if(where.isEmpty() && duplicate)
			{
				String query = "CREATE VIEW " + name + " AS " +  " DISTICT SELECT " + select + " FROM " + from;
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			if(where.isEmpty())
			{
				String query = "CREATE VIEW " + name + " AS " +  " SELECT " + select + " FROM " + from;
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			if(duplicate)
			{
				String query = "CREATE VIEW " + name + " AS " +  " DISTICT SELECT " + select + " FROM " + from + " WHERE " + where;
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			String query = "CREATE VIEW " + name + " AS " +  " SELECT " + select + " FROM " + from + " WHERE " + where;
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return rs;
	}
	
	public ResultSet GroupData(boolean duplicate, String select, String from, String where, String group, String having)
	{
		ResultSet rs = null;
		
		try {
			if(having.isEmpty() && duplicate)
			{
				String query =  "DISTICT SELECT " + select + " FROM " + from + " WHERE " + where + " GROUP BY " + group;
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			if(having.isEmpty())
			{
				String query =  "SELECT " + select + " FROM " + from + " WHERE " + where + " GROUP BY " + group;
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			if(duplicate)
			{
				String query =  "DISTINCT SELECT " + select + " FROM " + from + " WHERE " + where + " GROUP BY " + group + " HAVING " + having;
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				return rs;
			}
			
			String query =  "SELECT " + select + " FROM " + from + " WHERE " + where + " GROUP BY " + group + " HAVING " + having;
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return rs;
	}

	
	/*public static void main(String argv[]) {
		Connection con = null;

		String userid = "ora_n2d8";
		String passwd = "a43221118";

		String url = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";

		try {
			System.out.println("Loading driver ...");

			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println("Driver loaded.");
		} catch (Exception e) {
			System.out.println("Unable to load driver\n" + e);
			System.exit(-1);
		}

		try {
			System.out.println("Connecting to NetDB2 ...");

			con = DriverManager.getConnection(url, userid, passwd);

			System.out.println("Connection successful.");

		} catch (Exception e) {
			System.out.println("Connection failed\n" + e);
		}

		try {
			System.out.println("");
			System.out.println("Selecting sixteens_sequence from Produces");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT sixteens_sequence FROM Produces");

			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}*/
}
