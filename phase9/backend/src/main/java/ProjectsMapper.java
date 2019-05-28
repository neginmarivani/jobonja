
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProjectsMapper extends Mapper<Project , Integer>{
	private Tools tools = Tools.getInstance();

	private static ProjectsMapper singlton_instance = null;

	public static ProjectsMapper getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new ProjectsMapper();
		}
		return singlton_instance;
	}

	public ProjectsMapper() {

	}
	@Override
	protected String getFindStatement(){
		return "SELECT * FROM Project ";
	}
	@Override
	protected Project convertResultSetToDomainModel(ResultSet rs){
		return convertTableToProject(rs);
	}

	public Project getProject(String name, boolean byTitle) {
		return getProjectFromDB(name, byTitle);
	}

	public Project getProjectFromDB(String id, boolean byTitle) {
		Connection conn = ConnectDB.getConnetion();
		Project p = null;
		try {
			Statement st = conn.createStatement();
			ResultSet projectTable;
			if (byTitle)
				projectTable = st.executeQuery("SELECT * FROM Project WHERE title='" + id + "'");
			else {
				projectTable = st.executeQuery("SELECT * FROM Project WHERE id='" + id + "'");

			}
			p = convertTableToProject(projectTable);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return p;
	}

	public void addProject(JSONObject jObject) {

		JSONArray skillsTemp = jObject.getJSONArray("skills");
		ArrayList<Skill> projectSkillsTemp = tools.addSkill(skillsTemp);
		String imageUrl = jObject.getString("imageUrl");
		String description = jObject.getString("description");
		String id = jObject.getString("id");
		String titleTemp = jObject.getString("title");
		int timestamp = jObject.getInt("deadline");
		int creationDate = jObject.getInt("creationDate");

		int budgetTemp = jObject.getInt("budget");
		Project p = new Project(titleTemp, id, description, imageUrl, timestamp, creationDate, projectSkillsTemp,
				budgetTemp);
		addProjectToDB(p);
	}

	public void addProjectToDB(Project p) {
		Connection conn = ConnectDB.getConnetion();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO Project VALUES (?,?,?,?,?,?,?)");
			st.setString(1, p.getTitle());
			st.setString(2, p.getId());
			st.setString(3, p.getDescription());
			st.setString(4, p.getImageUrl());
			st.setInt(5, p.getDeadline());
			st.setInt(6, p.getBudget());
			st.setInt(7, p.getCreationDate());
			st.executeUpdate();

			for (int i = 0; i < p.getPrerequisites().size(); i++) {

				PreparedStatement st2 = conn.prepareStatement("INSERT INTO ProjectSkill VALUES (?,?,?)");
				st2.setString(1, p.getPrerequisites().get(i).getName());
				st2.setInt(2, p.getPrerequisites().get(i).getPoint());
				st2.setString(3, p.getId());
				st2.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

	public void getProjectFromServer(

	) throws java.io.IOException {

		String json = tools.getJsonStr("http://142.93.134.194:8000/joboonja/project");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);

			this.addProject(jsonObject);
		}
	}

	public ArrayList<Project> getProjectsFromDB(int n) {
		ArrayList<Project> projects = new ArrayList<Project>();
		Connection conn = ConnectDB.getConnetion();
		Project p = null;
		try {
			Statement st = conn.createStatement();
			ResultSet projectsTable;
			if( n <0){
				projectsTable= st.executeQuery("SELECT * FROM Project ORDER BY creationDate DESC ");
			}
			else {
				projectsTable = st.executeQuery("SELECT * FROM Project ORDER BY creationDate DESC LIMIT " + n);
			}
			while (projectsTable.next()) {
				p = convertTableToProject(projectsTable);
				projects.add(p);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return projects;
	}

	private Project convertTableToProject(ResultSet projectsTable) {
		Project p = null;
		try {
			ArrayList<Skill> prerequisites = getProjectSkill(projectsTable.getString("id"));
			p = new Project(projectsTable.getString("title"), projectsTable.getString("id"),
					projectsTable.getString("description"), projectsTable.getString("imageUrl"),
					projectsTable.getInt("deadline"), projectsTable.getInt("creationDate"), prerequisites,
					projectsTable.getInt("budget"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return p;
	}

	public ArrayList<Skill> getProjectSkill(String id) {
		ArrayList<Skill> out = new ArrayList<Skill>();
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet skillTable = st.executeQuery("SELECT * FROM ProjectSkill WHERE projectId= '" + id + "'");
			while (skillTable.next()) {
				String name = skillTable.getString("name");
				int point = skillTable.getInt("point");
				out.add(new Skill(name, point));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return out;
	}

	public ArrayList<Project> projectsForThisUser(User user) {

		ArrayList<Project> out = new ArrayList<Project>();

		//faze badi yadet bashe avaz koni
		ArrayList<Project> projects = getProjectsFromDB(-5);
		for (int i = 0; i < projects.size(); i++) {
			Project p = projects.get(i);
			if (GoodEnough(p, user))
				out.add(p);
		}
		return out;
	}

	public boolean GoodEnough(Project p, User user) {
		ArrayList<Skill> prerequisites = p.getPrerequisites();
		ArrayList<Skill> userSkills = user.getSkills();
		boolean flag = false;
		for (int i = 0; i < prerequisites.size(); i++) {
			if (!flag && i != 0)
				break;
			flag = false;
			for (int j = 0; j < userSkills.size(); j++) {
				if (flag)
					break;
				if (prerequisites.get(i).getName().equals(userSkills.get(j).getName())) {
					if (prerequisites.get(i).getPoint() <= userSkills.get(j).getPoint())
						flag = true;
				}
			}
		}
		return flag;

	}

	public ArrayList<Project> searchProject(String query) {

		ArrayList<Project> out = new ArrayList<Project>();
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			// st.executeUpdate("ALTER TABLE Project ADD FULLTEXT(title)");
			// st.executeUpdate("ALTER TABLE Project ADD FULLTEXT(description)");

			ResultSet rs = st.executeQuery(" SELECT * FROM Project WHERE title LIKE '%" + query + "%' ORDER BY creationDate DESC");

			while (rs.next()) {
				out.add(convertTableToProject(rs));
			}
			ResultSet rs2 = st.executeQuery(" SELECT * FROM Project WHERE description LIKE '%" + query + "%' ORDER BY creationDate DESC");

			while (rs2.next()) {
				out.add(convertTableToProject(rs2));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return out;
	}
	public void checkProjectsForBestBid(AuctionMapper adb){
		Connection conn = ConnectDB.getConnetion();
		int currentTime = (int) (new Date().getTime()/1000);
		System.out.println("current date " +currentTime);
		try {
			Statement st = conn.createStatement();
			ResultSet skillTable = st.executeQuery("SELECT * FROM Project WHERE deadline <=" + currentTime );
			while (skillTable.next()) {
				String pId = skillTable.getString("id");
				User wonUser=adb.findBestBid(pId);
				if(wonUser !=null)
					addWonBidToDB(pId,wonUser.getId());
				else
					addWonBidToDB(pId,0);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


	}
	public void addWonBidToDB(String pId,int uId){
		Connection conn = ConnectDB.getConnetion();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO Auction VALUES (?,?)");
			st.setString(1,pId);
			st.setInt(2, uId);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


	}
}