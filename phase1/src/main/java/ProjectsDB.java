import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ProjectsDB {
    private AuctionDB auctionDB;
    private Tools tools;

    public ProjectsDB(AuctionDB auctionDB , Tools tools) {
        this.auctionDB = auctionDB;
        this.tools = tools;
    }

    private ArrayList<Project> jabonjaProjects = new ArrayList<Project>();

    public Project getProject(String Name ,boolean type){

        for (int i = 0 ;i<jabonjaProjects .size() ;i++){
            if(type) {
                if (jabonjaProjects.get(i).getTitle().equals(Name)) {
                    return jabonjaProjects.get(i);
                }
            }
            else {
                if (jabonjaProjects.get(i).getId().equals(Name)) {
                    return jabonjaProjects.get(i);
                }
            }
        }
        return new Project();
    }
    public void add(Project x){
        jabonjaProjects.add(x);
    }
    public void addProject(JSONObject jObject ){

        JSONArray skillsTemp = jObject.getJSONArray("skills");
        ArrayList<Skill> projectSkillsTemp = tools.addSkill(skillsTemp);
        String imageUrl = jObject.getString("imageUrl");
        String description = jObject.getString("description");
        String id = jObject.getString("id");
        String titleTemp = jObject.getString("title");
        Timestamp timestamp = new Timestamp(jObject.getInt("deadline") );

        int budgetTemp = jObject.getInt("budget");
        Project p = new Project(titleTemp,id,description,imageUrl, timestamp, projectSkillsTemp, budgetTemp);

        jabonjaProjects.add(p) ;
        auctionDB.addProject(titleTemp);
        //debug
//        System.out.println(p.toString());

    }
    public void getProjectFromServer()throws java.io.IOException{

        String json = tools.getJsonStr("http://142.93.134.194:8000/joboonja/project");
        JSONArray jsonarray = new JSONArray(json);
        for (int i=0;i<jsonarray.length();i++){
            JSONObject jsonObject = jsonarray.getJSONObject(i);

            this.addProject(jsonObject);
        }
    }
    public ArrayList<Project> projectsForThisUser(User user) {
        ArrayList<Project> out = new ArrayList<Project>();

        for (int i = 0; i < jabonjaProjects.size(); i++) {
            Project p = jabonjaProjects.get(i);
            if(GoodEnough(p , user) )
                out.add(p);
        }
        return out;
    }
    public boolean findIn(ArrayList<Project> list , Project p){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(p.getId())){
                return true;
            }
        }
        return false;
    }

    public boolean GoodEnough(Project p , User user){
        ArrayList<Skill> prerequisites = p.getPrerequisites();

        ArrayList<Skill> userSkills = user.getSkills();
        boolean flag=false;
        for(int i =0;i<prerequisites.size() ; i++){
            if(!flag && i !=0)
                break;
            flag = false;
            for(int j=0;j<userSkills.size(); j++){
                if(flag)
                    break;
                if(prerequisites.get(i).getName().equals(userSkills.get(j).getName())) {
                    if (prerequisites.get(i).getPoint() <= userSkills.get(j).getPoint())
                        flag = true;
                }
            }
        }
        return flag;

    }
}