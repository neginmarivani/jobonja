import java.util.ArrayList;

public class ProjectsDB {
    private ArrayList<Project> jabonjaProjects = new ArrayList<Project>();
    public Project getProject(String proName){

        for (int i = 0 ;i<jabonjaProjects .size() ;i++){
            if(jabonjaProjects .get(i).getTitle().equals(proName)){
                return jabonjaProjects .get(i);
            }
        }
        return new Project();
    }
    public void add(Project x){
        jabonjaProjects.add(x);
    }
}