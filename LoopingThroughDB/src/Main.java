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
		
		try (
				Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery("SELECT * FROM tours");
				
				){
			Tours.displayData(rs);
			
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
			
		} catch(SQLException e) {
			System.err.print(e);
		} finally {
		/*	if (rs != null)
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
			}*/
		}
	}

}