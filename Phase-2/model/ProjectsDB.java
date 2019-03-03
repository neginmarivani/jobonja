import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProjectsDB {
	private AuctionDB auctionDB = AuctionDB.getInstance();
	private Tools tools = Tools.getInstance();

	private static ProjectsDB singlton_instance = null;

	public static ProjectsDB getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new ProjectsDB();
		}
		return singlton_instance;
	}

	public ProjectsDB() {

	}

	private ArrayList<Project> jobonjaProjects = new ArrayList<Project>();

	public Project getProject(String Name, boolean byTitle) {

		for (int i = 0; i < jobonjaProjects.size(); i++) {
			if (byTitle) {
				if (jobonjaProjects.get(i).getTitle().equals(Name)) {
					return jobonjaProjects.get(i);
				}
			} else {
				if (jobonjaProjects.get(i).getId().equals(Name)) {
					return jobonjaProjects.get(i);
				}
			}
		}
		return new Project();
	}

	public void add(Project x) {
		jobonjaProjects.add(x);
	}

	public void addProject(JSONObject jObject) {

		JSONArray skillsTemp = jObject.getJSONArray("skills");
		ArrayList<Skill> projectSkillsTemp = tools.addSkill(skillsTemp);
		String imageUrl = jObject.getString("imageUrl");
		String description = jObject.getString("description");
		String id = jObject.getString("id");
		String titleTemp = jObject.getString("title");
		Timestamp timestamp = new Timestamp(jObject.getInt("deadline"));

		int budgetTemp = jObject.getInt("budget");
		Project p = new Project(titleTemp, id, description, imageUrl, timestamp, projectSkillsTemp, budgetTemp);

		jobonjaProjects.add(p);
		auctionDB.addProject(titleTemp);
		// debug
		// System.out.println(p.toString());

	}

	public void getProjectFromServer() throws java.io.IOException {

		String json = tools.getJsonStr("http://142.93.134.194:8000/joboonja/project");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);

			this.addProject(jsonObject);
		}
	}

	public ArrayList<Project> getjobonjaProjects() {
		return jobonjaProjects;
	}

	public void setjobonjaProjects(ArrayList<Project> jobonjaProjects) {
		this.jobonjaProjects = jobonjaProjects;
	}

	public ArrayList<Project> projectsForThisUser(User user) {
		ArrayList<Project> out = new ArrayList<Project>();

		for (int i = 0; i < jobonjaProjects.size(); i++) {
			Project p = jobonjaProjects.get(i);
			if (GoodEnough(p, user))
				out.add(p);
		}
		return out;
	}

	public boolean findIn(ArrayList<Project> list, Project p) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(p.getId())) {
				return true;
			}
		}
		return false;
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
}