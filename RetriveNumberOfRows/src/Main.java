import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = 
			"jdbc:mysql://localhost/explorecalifornia";

	public static void main(String[] args) throws SQLException {	
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("SELECT * FROM states");
			
			rs.last();
			System.out.println("Number of rows: " + rs.getRow());
			
			//System.out.println("Connected!");
		} catch(SQLException e) {
			System.err.print(e);
		} finally {
			if (rs != null)
			{
				rs.close();	
			}
			if (st != null)
			{
				st.close();	
			}
			if (conn != null)
			{
				conn.close();	
			}
		}
	}

}