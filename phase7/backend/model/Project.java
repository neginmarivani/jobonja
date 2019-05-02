import java.util.ArrayList;

public class Project {
	private String title;
	private String id;
	private String description;
	private String imageUrl;
	private int deadline;
	private int creationDate;
	private ArrayList<Skill> prerequisites = new ArrayList<Skill>();
	private int budget;

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public int getDeadline() {
		return deadline;
	}

	public int getCreationDate() {
		return creationDate;
	}

	public Project(String title, String id, String description, String imageUrl, int deadline, int creationDate,
			ArrayList<Skill> prerequisites, int budget) {
		this.title = title;
		this.id = id;
		this.description = description;
		this.imageUrl = imageUrl;
		this.deadline = deadline;
		this.prerequisites = prerequisites;
		this.budget = budget;
		this.creationDate = creationDate;

	}

	public Project(String title, ArrayList<Skill> prerequisites, int budget) {
		this.title = title;
		this.prerequisites = prerequisites;
		this.budget = budget;
	}

	public String getTitle() {
		return title;
	}

	public int getBudget() {
		return budget;
	}

	public ArrayList<Skill> getPrerequisites() {
		return prerequisites;
	}

	public Project() {
	}

}
