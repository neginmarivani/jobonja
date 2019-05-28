
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	static Connection conn = null;

	public static Connection getConnetion() {
		if (conn != null) {
			return conn;
		} else {
			return connect();
		}
	}

	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql:C:\\Users\\asus\\IdeaProjects\\jobonja\\src\\jobonja.db\n";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

}
