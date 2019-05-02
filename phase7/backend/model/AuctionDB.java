import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuctionDB {

	private ProjectsDB pdb = ProjectsDB.getInstance();
	private UsersDB udb = UsersDB.getInstance();
	private static AuctionDB singlton_instance = null;

	public static AuctionDB getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new AuctionDB();
		}
		return singlton_instance;
	}

	public void addBid(String projectId, int UserId, int amount) {
		Connection conn = ConnectDB.getConnetion();
		try {

			PreparedStatement st = conn.prepareStatement("INSERT INTO Bid VALUES (?,?,?)");
			st.setString(1, projectId);
			st.setInt(2, UserId);
			st.setInt(3, amount);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean UserHasBidForProject(String projectID, int UserID) {
		Connection conn = ConnectDB.getConnetion();
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
		return true;
	}

	public User findBestBid(String projectId) {

		int max = 0;
		Connection conn = ConnectDB.getConnetion();
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

		return selected;
	}

}
