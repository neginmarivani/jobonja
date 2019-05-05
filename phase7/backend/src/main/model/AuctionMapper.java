
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuctionMapper extends Mapper<Bid , Integer>{

	private ProjectsMapper pdb = ProjectsMapper.getInstance();
	private UsersMapper udb = UsersMapper.getInstance();
	private static AuctionMapper singlton_instance = null;

	private SQLiteBasicDBConnectionPool dbConnection = ConnectionPool.getInstance();

	public static AuctionMapper getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new AuctionMapper();
		}
		return singlton_instance;
	}
	@Override
	protected String getFindStatement(){
		return "SELECT * FROM Bid ";
	}
	@Override
	protected Bid convertResultSetToDomainModel(ResultSet rs){
		int bidAmount=0;
		User user =null;
		Project p=null;

		try{
		int userId = rs.getInt("userId");
		user = udb.getUser(userId);
		String projectId = rs.getString("projectId");
		p = pdb.getProject(projectId,false);
		bidAmount = rs.getInt("bidAmount");

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
		return new Bid(p,user,bidAmount);
	}

	public void addBid(String projectId, int UserId, int amount) {
		Connection conn = dbConnection.get();
		try {

			PreparedStatement st = conn.prepareStatement("INSERT INTO Bid VALUES (?,?,?)");
			st.setString(1, projectId);
			st.setInt(2, UserId);
			st.setInt(3, amount);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
	}

	public boolean UserHasBidForProject(String projectID, int UserID) {
		Connection conn = dbConnection.get();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT COUNT(*) AS total FROM Bid WHERE projectId='" + projectID + "' AND userId=" + UserID);
			int num = rs.getInt("total");
			if (num == 0) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
		return true;
	}

	public User findBestBid(String projectId) {

		int max = 0;
		Connection conn = dbConnection.get();
		Project project = pdb.getProject(projectId, false);
		User selected = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Bid WHERE projectId=" + projectId);
			while (rs.next()) {

				int userId = rs.getInt("userId");
				User user = udb.getUser(userId);

				int bidAmount = rs.getInt("bidAmount");

				ArrayList<Skill> skill = project.getPrerequisites();
				int score = 0;
				for (int j = 0; j < skill.size(); j++) {
					score += 10000
							* Math.pow(user.getSkill(skill.get(j).getName()).getPoint() - skill.get(j).getPoint(), 2);

				}
				score += project.getBudget() - bidAmount;
				if (score > max) {
					max = score;
					selected = user;
				}

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);

		return selected;
	}

}
