import java.sql.Timestamp;
import java.util.ArrayList;

public class Project {
    private String title;
    private String id;
    private String description;
    private String imageUrl;
    private Timestamp deadline ;
    private ArrayList <Skill> prerequisites = new ArrayList<Skill>();
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

    public Timestamp getDeadline() {
        return deadline;
    }

    public Project(String title, String id, String description, String imageUrl, Timestamp deadline, ArrayList<Skill> prerequisites, int budget) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.deadline = deadline;
        this.prerequisites = prerequisites;
        this.budget = budget;
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

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", deadline=" + deadline +
                ", prerequisites=" + prerequisites +
                ", budget=" + budget +
                '}';
    }
    public String toHtml(){

        String list ="";
        list += "<li>" ;
        list += "id :"+ id +"<br/>";
        list += "title :"+ title +"<br/>";
        String skills="<ul>";

        for (int j=0;j<prerequisites.size() ; j++){
            skills += "<li>";
            skills += prerequisites.get(j).getName()+"<br/>";
            skills += prerequisites.get(j).getPoint()+"<br/>";
            skills += "</li>";
        }
        list += skills+"</ul>";
        list += "description : "+ description+"<br/>";
        list += "budget : "+ budget+"<br/>";
        list += "deadline : " + deadline+"<br/>";
        list += "<img src="+ imageUrl+" alt="+title+">";
        list += "</li>";


        return list;
    }
}
