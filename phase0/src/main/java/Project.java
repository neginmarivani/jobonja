import java.util.ArrayList;

public class Project {
    private String title;
    private ArrayList <Skill> prerequisites = new ArrayList<Skill>();
    private int budget;

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

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", prerequisites=" + prerequisites +
                ", budget=" + budget +
                '}';
    }

}
