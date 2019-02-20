
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UsersDB usersDB = new UsersDB();
        ProjectsDB projectsDB = new ProjectsDB();
        AuctionDB auctionDB = new AuctionDB();

        Scanner scanner = new Scanner(System.in);
        boolean notFinished = true;
        while (notFinished) {
            String cmdName = scanner.next();
            String jsonData = scanner.next();

            if (cmdName.equals("register")) {
                JSONObject jObject = new JSONObject(jsonData);
                String usrNameTemp = jObject.getString("username");
                JSONArray skillsTemp = jObject.getJSONArray("skills");
                ArrayList<Skill> usrSkillTemp = addSkill(skillsTemp);
                usersDB.add(new User(usrNameTemp, usrSkillTemp));

            }
            else if(cmdName.equals("addProject")){
                JSONObject jObject = new JSONObject(jsonData);
                String titleTemp = jObject.getString("title");
                JSONArray skillsTemp = jObject.getJSONArray("skills");
                ArrayList<Skill> projectSkillTemp = addSkill(skillsTemp);
                int budgetTemp = jObject.getInt("budget");
                projectsDB.add(new Project(titleTemp,projectSkillTemp,budgetTemp));
                auctionDB.addProject(titleTemp);
            }
            else if(cmdName.equals("bid")){

                JSONObject jObject = new JSONObject(jsonData);
                String usrName = jObject.getString("biddingUser");
                User usrTemp = usersDB.getUser(usrName);
                String proName = jObject.getString("projectTitle");
                Project proTemp = projectsDB.getProject( proName);
                int budgetTemp = jObject.getInt("bidAmount");
                Bid bidTemp = new Bid(proTemp ,usrTemp ,budgetTemp);
                if(bidTemp.isValid()){
                    auctionDB.addBid(proName ,bidTemp);
                }
            }
            else if (cmdName.equals("auction")){

                JSONObject jObject = new JSONObject(jsonData);
                String project = jObject.getString("projectTitle");
                User selected =auctionDB.findBestBid(project);
                System.out.println("best bid is from :" +selected.getUserName());
                notFinished = false;
            }
        }
    }
    private static ArrayList<Skill> addSkill(JSONArray skillsTemp){
        ArrayList<Skill> SkillTemp = new ArrayList<Skill>();
        for (int i = 0; i < skillsTemp.length(); i++) {
            JSONObject jo = skillsTemp.getJSONObject(i);
            String name = jo.getString("name");
            int points = jo.getInt("points");
            SkillTemp.add(new Skill(name, points));

        }
        return SkillTemp;
    }

}
