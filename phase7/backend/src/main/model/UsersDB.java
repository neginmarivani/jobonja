import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsersDB {

	private static UsersDB singlton_instance = null;

	public static UsersDB getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new UsersDB();
		}
		return singlton_instance;
	}

//	private ArrayList<User> jobonjaUsers = new ArrayList<User>();
//	private ArrayList<String> jobonjaSkills = new ArrayList<String>();

	Tools tools = Tools.getInstance();

	public UsersDB() {
		getUsersFromJsonFile();
		try {
			getSkillsFromServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


//	public int getSize() {
//		return jobonjaUsers.size();
//	}


//	public ArrayList<String> getJobonjaSkills() {
//		return jobonjaSkills;
//	}

//	public void setJobonjaSkills(ArrayList<String> jobonjaSkills) {
//		this.jobonjaSkills = jobonjaSkills;
//	}


	public User getUser(int id) {

		return getUserFromDB(id);
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> users=new ArrayList<User>();
        Connection conn = ConnectDB.getConnetion();
        User u = null;
        try{
            Statement st =conn.createStatement();
            ResultSet usersTable=st.executeQuery("SELECT * FROM USER ");
            while(usersTable.next()){
                u = convertTableToUser(usersTable);
                users.add(u);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

//	public void add(User x) {
//		jobonjaUsers.add(x);
//	}

	public void addUser	(JSONObject jObject) {
		int id = jObject.getInt("ID");
		String userName = jObject.getString("first name");
		String userFamilyName = jObject.getString("family name");
		JSONArray skillsTemp = jObject.getJSONArray("skills");
		ArrayList<Skill> skills = tools.addSkill(skillsTemp);
		String jobTitle = jObject.getString("position");
		String imageUrl = jObject.getString("imgURL");
		String bio = jObject.getString("bio");

		User u = new User(id, bio, jobTitle, userName, userFamilyName, skills, imageUrl);
//		jobonjaUsers.add(u);
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
	public ArrayList<Skill> getUserSkill(int id){
		ArrayList<Skill> out =new ArrayList<Skill>();
		Connection conn = ConnectDB.getConnetion();
		try{
			Statement st =conn.createStatement();
			ResultSet skillTable=st.executeQuery("SELECT * FROM UserSkill WHERE userId="+ Integer.toString(id));
			while(skillTable.next()){
				String name=skillTable.getString("name");
				int point=skillTable.getInt("point");
				out.add(new Skill(name,point));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return out;
	}
	public  User getUserFromDB(int id) {
		Connection conn = ConnectDB.getConnetion();
        User u = null;
		try{
			Statement st =conn.createStatement();
			ResultSet userTable=st.executeQuery("SELECT * FROM USER WHERE id="+ Integer.toString(id));
            u = convertTableToUser(userTable);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return u;
	}
	private User convertTableToUser(ResultSet userTable){
        User u = null;
        try {
            ArrayList<Skill> selectedSkill = getUserSkill(userTable.getInt("id"));
            u = new User(userTable.getInt("id"), userTable.getString("bio"),
                    userTable.getString("jobTitle"), "Ali"
                    , userTable.getString("userFamilyName"), selectedSkill,
                    userTable.getString("ImageURL"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return u;
    }
	public void addSkillToDB(String skill) {
		Connection conn = ConnectDB.getConnetion();
		try {
		PreparedStatement st = conn.prepareStatement("INSERT INTO SystemSkill VALUES (?)");
		st.setString(1,skill);
		st.executeUpdate();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	}
	public  void addUserToDB(User u) {

		Connection conn = ConnectDB.getConnetion();
		try {

				for(int i=0;i<u.getSkills().size();i++){
					PreparedStatement st = conn.prepareStatement("INSERT INTO UserSkill VALUES (?,?,?)");
					st.setString(1,u.getSkills().get(i).getName());
					st.setInt(2,u.getSkills().get(i).getPoint());
					st.setInt(3,u.getId());
					st.executeUpdate();
				}


				PreparedStatement st = conn.prepareStatement("INSERT INTO USER VALUES (?,?,?,?,?,?)");
				st.setInt(1, u.getId());
				st.setString(2, u.getBio());
				st.setString(3, u.getJobTitle());
				st.setString(4, u.getUserFamilyName());
				st.setString(5, u.getImageUrl());
				st.setInt(6, 0);
				st.executeUpdate();
//				this.getUserFromDB(1);


		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}