import java.util.ArrayList;

public class User {

	private int id;
	private String bio;
	private String jobTitle;
	private String userName;
	private String userFamilyName;
	private ArrayList<Skill> skills;
	private String ImageUrl;
	private boolean behidden;

	public User(int id, String bio, String jobTitle, String userName, String userFamilyName, ArrayList<Skill> skills,
			String imageUrl) {
		this.id = id;
		this.bio = bio;
		this.jobTitle = jobTitle;
		this.userName = userName;
		this.userFamilyName = userFamilyName;
		this.skills = skills;
		this.ImageUrl = imageUrl;
		this.behidden = false;
	}

	public User(String userName, ArrayList<Skill> skills) {
		this.userName = userName;
		this.skills = skills;
	}

	public User() {
	}

	public boolean ifHidden() {
		return this.behidden;
	}

	public void hide() {
		this.behidden = true;
	}

	public Skill getSkill(String name) {
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getName().equals(name))
				return skills.get(i);
		}
		return new Skill();
	}

	public String getUserName() {
		return userName;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public boolean updateUserSkill(String skillName, int userId) {
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getName().equals(skillName)) {
				if (!skills.get(i).alreadyEndorsed(userId)) {
					skills.get(i).addPoint(userId);
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public boolean deleteSkill(String skillName) {
		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getName().equals(skillName)) {
				skills.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean addSkill(String skillName) {

		for (int i = 0; i < skills.size(); i++) {
			if (skills.get(i).getName().equals(skillName)) {
				return false;
			}
		}

		return skills.add(new Skill(skillName, 0));
	}

	@Override
	public String toString() {
		return "User{" + "userName='" + userName + '\'' + ", skills=" + skills + '}';
	}

	public int getId() {
		return id;
	}

	public String getBio() {
		return bio;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getUserFamilyName() {
		return userFamilyName;
	}

	public String toHtml() {
		String list = "";
		list += "<li>";
		list += "id :" + id + "<br/>";
		list += "bio :" + bio + "<br/>";
		list += "jobTitle :" + jobTitle + "<br/>";
		list += "userName : " + userName + "<br/>";
		list += "userFamilyName :" + userFamilyName + "<br/>";
		String s = "<ul>";

		for (int j = 0; j < skills.size(); j++) {
			s += "<li>";
			s += skills.get(j).getName() + "<br/>";
			s += skills.get(j).getPoint() + "<br/>";
			s += "</li>";
		}
		list += s + "</ul>";
		list += "</li>";

		return list;
	}
}
