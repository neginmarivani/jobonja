
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        ArrayList<User> jabonjaUsers = new ArrayList<User>();
        ArrayList<Project> jabonjaProjects = new ArrayList<Project>();
        Scanner scanner = new Scanner(System.in);
        boolean notFinished = true;
        while (notFinished) {
            String cmdName = scanner.next();
            String jsonData = scanner.next();

            if (cmdName.equals("register")) {
                JSONObject jObject = new JSONObject(jsonData);
                String usrNameTemp = jObject.getString("username");
                JSONArray skillsTemp = jObject.getJSONArray("skills");
                ArrayList<Skill> usrSkillTemp = new ArrayList<Skill>();
                for (int i = 0; i < skillsTemp.length(); i++) {
                    JSONObject jo = skillsTemp.getJSONObject(i);
                    String name = jo.getString("name");
                    int points = jo.getInt("points");
                    usrSkillTemp.add(new Skill(name, points));

                }
                jabonjaUsers.add(new User(usrNameTemp, usrSkillTemp));

            }
            else if(cmdName.equals("addProject")){
                JSONObject jObject = new JSONObject(jsonData);
                String titleTemp = jObject.getString("title");
                JSONArray skillsTemp = jObject.getJSONArray("skills");
                ArrayList<Skill> projectSkillTemp = new ArrayList<Skill>();
                for (int i = 0; i < skillsTemp.length(); i++) {
                    JSONObject jo = skillsTemp.getJSONObject(i);
                    String name = jo.getString("name");
                    int points = jo.getInt("points");
                    projectSkillTemp.add(new Skill(name, points));

                }
                int budgetTemp = jObject.getInt("budget");
                jabonjaProjects.add(new Project(titleTemp,projectSkillTemp,budgetTemp));
            }
            else if(cmdName.equals("bid")){
                JSONObject jObject = new JSONObject(jsonData);
                String usrName = jObject.getString("biddingUser");
                User usrTemp = getUser(jabonjaUsers ,usrName);
                String proName = jObject.getString("projectTitle");
                Project proTemp = getProject(jabonjaProjects , proName);
                int budgetTemp = jObject.getInt("bidAmount");
                Bid bidTemp = new Bid(proTemp ,usrTemp ,budgetTemp);
                if(bidTemp.isValid())
                    proTemp.addBid(bidTemp);

            }
            else if (cmdName.equals("auction")){
                //find the best bid
                JSONObject jObject = new JSONObject(jsonData);
                String project = jObject.getString("projectTitle");
                Project proTemp = getProject(jabonjaProjects , project);
                User selected =proTemp.findBestBid();
                System.out.println("best bid is from :" +selected.getUserName());
                notFinished = false;
            }
        }
    }

    public static Project getProject(ArrayList<Project> list , String proName){

        for (int i = 0 ;i<list.size() ;i++){
            if(list.get(i).getTitle().equals(proName)){
                return list.get(i);
            }
        }
        return new Project();
    }
    public static User getUser (ArrayList<User> list ,String usrName ) throws NullPointerException{

        for (int i = 0 ;i<list.size() ;i++) {
            if (list.get(i).getUserName().equals(usrName)) {
                return list.get(i);
            }
        }
        return new User();
    }
}
