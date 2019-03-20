import java.util.ArrayList;

public class Bid {

	private Project project;
	private User user;
	private int bidAmount;

	public Bid(Project project, User user, int bidAmount) {
		this.project = project;
		this.user = user;
		this.bidAmount = bidAmount;
	}

	public boolean isValid() {

		if (bidAmount > project.getBudget())
			return false;
		ArrayList<Skill> prerequisites = project.getPrerequisites();
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

	@Override
	public String toString() {
		return "Bid{" + "project=" + project.getTitle() + ", user=" + user.getUserName() + ", bidAmount=" + bidAmount
				+ '}';
	}

	public Project getProject() {
		return project;
	}

	public User getUser() {
		return user;
	}

	public int getBidAmount() {
		return bidAmount;
	}
}
