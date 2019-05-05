
public class ConnectionPool {

	private final static String dbURL = "jdbc:sqlite:C:/Users/tehrani/Desktop/jobonja/src/jobonja.db\n";
	private static SQLiteBasicDBConnectionPool instance;

	public static SQLiteBasicDBConnectionPool getInstance() {
		if (instance == null) {
			instance = new SQLiteBasicDBConnectionPool(1, 5, dbURL);
		}
		return instance;
	}
}
