import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

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

	private ArrayList<User> jobonjaUsers = new ArrayList<User>();
	private ArrayList<String> jobonjaSkills = new ArrayList<String>();
	private User currentUser;
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

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(int id) {
		for (int i = 0; i < jobonjaUsers.size(); i++) {
			if (jobonjaUsers.get(i).getId() == id) {
				this.currentUser = jobonjaUsers.get(i);
			}
		}
	}

	public int getSize() {
		return jobonjaUsers.size();
	}

	public User getUser(String usrName) throws NullPointerException {

		for (int i = 0; i < this.jobonjaUsers.size(); i++) {
			if (this.jobonjaUsers.get(i).getUserName().equals(usrName)) {
				return this.jobonjaUsers.get(i);
			}
		}
		return new User();
	}

	public ArrayList<String> getJobonjaSkills() {
		return jobonjaSkills;
	}

	public void setJobonjaSkills(ArrayList<String> jobonjaSkills) {
		this.jobonjaSkills = jobonjaSkills;
	}

	public void removeUser(int id) {
		for (int i = 0; i < jobonjaUsers.size(); i++) {
			if (jobonjaUsers.get(i).getId() == id) {
				jobonjaUsers.get(i).hide();
			}
		}
	}

	public User getUser(int id) {
		for (int i = 0; i < this.jobonjaUsers.size(); i++) {
			if (this.jobonjaUsers.get(i).getId() == id) {
				// System.out.println(jobonjaUsers.get(i).getUserName());
				return this.jobonjaUsers.get(i);
			}
		}
		return new User();
	}

	public ArrayList<User> getUsers() {
		return jobonjaUsers;
	}

	public void getSkillsFromServer() throws IOException {
		String json = tools.getJsonStr("http://142.93.134.194:8000/joboonja/skill");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);

			jobonjaSkills.add(jsonObject.getString("name"));
		}
	}

	public void add(User x) {
		jobonjaUsers.add(x);
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
		jobonjaUsers.add(u);

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
		String json = readFile("C:\\Users\\asus\\Documents\\workspace\\Phase-2\\src\\users.json");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			addUser(jsonobject);
		}

	}
}