import java.sql.*;

class jdbcDB2Sample {
	public static void main(String argv[]) {
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
			System.out.println("Selecting sixteens_sequence from Produces");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT sixteens_sequence FROM Produces");

			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
