
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsersMapper extends Mapper<User , Integer>{


	private static UsersMapper singlton_instance = null;
	private SQLiteBasicDBConnectionPool dbConnection = ConnectionPool.getInstance();
	@Override
	protected String getFindStatement(){
		return "SELECT * FROM USER ";
	}
	@Override
	protected User convertResultSetToDomainModel(ResultSet rs){
		return convertTableToUser(rs);
	}
	public static UsersMapper getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new UsersMapper();
		}
		return singlton_instance;
	}

	Tools tools = Tools.getInstance();

	public UsersMapper() {
		getUsersFromJsonFile();

		try {
			getSkillsFromServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User getUser(int id) {

		return getUserFromDB(id);
	}

	public void addEndorsement(int endorsedId, String skillName, int endoreserId) {
		Connection conn = dbConnection.get();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO Endorsement VALUES (?,?,?)");
			st.setInt(1, endoreserId);
			st.setInt(2, endorsedId);
			st.setString(3, skillName);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);

	}

	public boolean EndorseSkill(int endorsedId, String skillName, int endorserId) {

		if (!UserEndorsedThisSkillBefore(endorsedId, skillName, endorserId)) {

			addEndorsement(endorsedId, skillName, endorserId);
			return true;
		}
		return false;

	}

	private boolean UserEndorsedThisSkillBefore(int endorsedId, String skillName, int endorserId) {
		Connection conn = dbConnection.get();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT COUNT(*) AS total FROM Endorsement WHERE endorsedId=" + Integer.toString(endorsedId)
							+ " AND endorserId=" + Integer.toString(endorserId) + " AND skillName='" + skillName + "'");
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

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		Connection conn = dbConnection.get();
		User u = null;
		try {
			Statement st = conn.createStatement();
			ResultSet usersTable = st.executeQuery("SELECT * FROM USER ");
			while (usersTable.next()) {
				u = convertTableToUser(usersTable);
				users.add(u);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
		return users;

	}

	public void getSkillsFromServer() throws IOException {
		String json = tools.getJsonStr("http://142.93.134.194:8000/joboonja/skill");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);

			addSkillToDB(jsonObject.getString("name"));
		}
	}

	public void addUser(JSONObject jObject) {
		int id = jObject.getInt("ID");
		String userName = jObject.getString("first name");
		String userFamilyName = jObject.getString("family name");
		JSONArray skillsTemp = jObject.getJSONArray("skills");
		ArrayList<Skill> skills = tools.addSkill(skillsTemp);
		String jobTitle = jObject.getString("position");
		String imageUrl = jObject.getString("imgURL");
		String bio = jObject.getString("bio");

		User u = new User(id, bio, jobTitle, userName, userFamilyName, skills, imageUrl);
		// jobonjaUsers.add(u);
		addUserToDB(u);

	}

	public static String readFile(String filename) {
		String result = "";
		try {
			Reader reader = new InputStreamReader(new FileInputStream(filename), "utf-8");
			BufferedReader br = new BufferedReader(reader);

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getUsersFromJsonFile() {
		String json = readFile("C:\\Users\\tehrani\\Desktop\\jobonja\\src\\main\\users.json");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			addUser(jsonobject);

		}

	}

	public boolean deleteSkill(String skillName, int userId) {
		Connection conn = dbConnection.get();
		try {
			Statement st = conn.createStatement();
			int num = st.executeUpdate(
					"DELETE FROM UserSkill WHERE userId=" + Integer.toString(userId) + " AND name='" + skillName + "'");
			if (num == 0) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
		return false;
	}

	public boolean addSkill(String skillName, int userId) {
		Connection conn = dbConnection.get();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM UserSkill WHERE userId="
					+ Integer.toString(userId) + " AND name='" + skillName + "'");
			int num = rs.getInt("total");
			System.out.println(num);
			if (num == 0) {
				addUserSkillToDB(userId, new Skill(skillName, 0));
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
		return false;
	}

	public ArrayList<Skill> getUserSkill(int id) {
		ArrayList<Skill> out = new ArrayList<Skill>();
		Connection conn = dbConnection.get();
		try {
			Statement st = conn.createStatement();
			ResultSet skillTable = st.executeQuery("SELECT * FROM UserSkill WHERE userId=" + Integer.toString(id));
			while (skillTable.next()) {
				String name = skillTable.getString("name");
				int point = skillTable.getInt("point");
				out.add(new Skill(name, point));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
		return out;
	}

	public User getUserFromDB(int id) {
		Connection conn = dbConnection.get();
		User u = null;
		try {
			Statement st = conn.createStatement();
			ResultSet userTable = st.executeQuery("SELECT * FROM USER WHERE id=" + Integer.toString(id));
			u = convertTableToUser(userTable);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
		return u;
	}

	public ArrayList<User> getUserFromDB(String query) {
		String arr[] = query.split(" ");
		String name = arr[0];
		String familyName = arr[1];
		ArrayList<User> out = new ArrayList<User>();
		Connection conn = dbConnection.get();
		try {
			Statement st = conn.createStatement();
			ResultSet userTable = st.executeQuery(
			" SELECT * FROM USER WHERE userFamilyName LIKE '%" + familyName + "%' OR userName LIKE '%" + name + "%'");
			while (userTable.next()) {
				out.add(convertTableToUser(userTable));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
		return out;
	}

	private User convertTableToUser(ResultSet userTable) {
		User u = null;
		try {
			ArrayList<Skill> selectedSkill = getUserSkill(userTable.getInt("id"));
			u = new User(userTable.getInt("id"), userTable.getString("bio"), userTable.getString("jobTitle"),
					userTable.getString("userName"), userTable.getString("userFamilyName"), selectedSkill,
					userTable.getString("ImageURL"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return u;
	}

	public void addSkillToDB(String skill) {
		Connection conn = dbConnection.get();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO SystemSkill VALUES (?)");
			st.setString(1, skill);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);

	}

	public void addUserSkillToDB(int userID, Skill skill) {

		Connection conn = dbConnection.get();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO UserSkill VALUES (?,?,?)");
			st.setString(1, skill.getName());
			st.setInt(2, skill.getPoint());
			st.setInt(3, userID);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
	}

	public void addUserToDB(User u) {

		Connection conn = dbConnection.get();
		try {

			for (int i = 0; i < u.getSkills().size(); i++) {
				addUserSkillToDB(u.getId(), u.getSkills().get(i));
			}

			PreparedStatement st = conn.prepareStatement("INSERT INTO USER VALUES (?,?,?,?,?,?,?)");
			st.setInt(1, u.getId());
			st.setString(2, u.getBio());
			st.setString(3, u.getJobTitle());
			st.setString(4, u.getUserFamilyName());
			st.setString(5, u.getImageUrl());
			st.setInt(6, 0);
			st.setString(7, u.getUserName());
			st.executeUpdate();
			// this.getUserFromDB(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		dbConnection.release(conn);
	}

	public ArrayList<User> searchUser(String query) {
		// TODO Auto-generated method stub

		return getUserFromDB(query);
	}
}