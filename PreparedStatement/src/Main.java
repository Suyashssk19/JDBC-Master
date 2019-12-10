import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = 
			"jdbc:mysql://localhost/explorecalifornia";
	private static final String SQL = "SELECT * FROM tours WHERE price <= ?";
	
	public static String getInput(String prompt) {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(prompt);
			System.out.flush();		
			
			try {
				return stdin.readLine();
			} catch (IOException e) {
				return e.getMessage();
			}
	}
	
	public static double getDouble(String prompt) throws NumberFormatException {
		
		String input = getInput(prompt);
		return Double.parseDouble(input);
		
	}

	public static void main(String[] args) throws SQLException {
		
		double maxPrice ;
		try {
			maxPrice = getDouble("Enter a maximum price: ");
		}
		catch(NumberFormatException e) {
			System.err.println("Error: Invalid Number!");
			return;
		}
		ResultSet rs = null;
		try (
				Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				PreparedStatement st = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				//PreparedStatement st = conn.prepareStatement(sql, resultSetType, resultSetConcurrency)
				
				){
					//st.setDouble(parameterIndex, x);
					st.setDouble(1, maxPrice);
					rs = st.executeQuery();
					
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
		} finally {
			if (rs != null)
				rs.close();
		}
	}
}