
import java.sql.*;


public class ConnectDB {
        static Connection conn = null;
        public static Connection getConnetion(){
            if(conn!=null){
                return conn;
            }
            else{
                return connect();
            }
        }
        public  static Connection connect() {
            try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:C:/Users/tehrani/Desktop/jobonja/src/jobonja.db\n";
                conn = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");
                Statement st =conn.createStatement();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

//            finally {
//                try {
//                    if (conn != null) {
//                        conn.close();
//                    }
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
            return conn;
        }

}
