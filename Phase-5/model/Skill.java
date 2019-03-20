import java.util.ArrayList;

public class Skill {
	String name;
	int point;
	private ArrayList<Integer> usersEndorsedThisSkill = new ArrayList<Integer>();

	public Skill(String name, int point) {
		this.name = name;
		this.point = point;
	}

	public Skill() {
	}

	@Override
	public String toString() {
		return "Skill{" + "name='" + name + '\'' + ", point=" + point + '}';
	}

	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}

	public void addPoint(int userId) {
		usersEndorsedThisSkill.add(userId);
		point++;
	}

	public boolean alreadyEndorsed(int userId) {
		for (int i = 0; i < usersEndorsedThisSkill.size(); i++) {
			if (usersEndorsedThisSkill.get(i) == userId) {
				return true;
			}
		}
		return false;
	}

}