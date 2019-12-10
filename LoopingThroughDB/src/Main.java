import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";
	private static final String SQL = "SELECT * FROM tours";
	

	public static void main(String[] args) throws SQLException {
		
		try (
				Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD); 
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery(SQL);
				)
		{
			rs.last();
			if (rs.getRow() == 0)
				System.out.println("There are no results!");
			else
				System.out.println("Tours: " + rs.getRow());
			
			if(rs.isBeforeFirst() != true)
				rs.beforeFirst();
			
			while  ( rs.next() ) {
				StringBuffer buffer = new StringBuffer();
				buffer.append( "Tour " +  rs.getInt("tourId") + ":");
				buffer.append(rs.getString("tourName"));
				System.out.println(buffer.toString());
			}
		}	
		catch(Exception e) {
			System.err.println(e.getMessage());
		}		
/*		
		try (
				Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery(SQL);
				){
					
					rs.last();
					if (rs.getRow() == 0)
						System.out.println("No rows were found!");
					else
						System.out.println("Number of tours: " + rs.getRow() + "\n");
						rs.beforeFirst();
					
					while(rs.next()) {
						StringBuffer buffer = new StringBuffer();
						buffer.append("Tour " + rs.getInt("tourId") + ": ");
						buffer.append(rs.getString("tourName"));
						buffer.append("($" + rs.getDouble("price") + ")");
						System.out.println(buffer.toString());
			}
			
		} catch(SQLException e) {
			System.err.print(e);
		}
	*/
	}
}