import java.sql.ResultSet;
import java.sql.SQLException;

public class Tours {
	
	public static void displayData(ResultSet rs) throws SQLException {
		while(rs.next()) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Tour " + rs.getInt("tourId") + ": ");
			buffer.append(rs.getString("tourName"));			
			
			System.out.println(buffer.toString());
		}
	}

}
