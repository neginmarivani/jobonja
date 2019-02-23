import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class UsersDB {

    private ArrayList<User> jabonjaUsers = new ArrayList<User>();
    private ArrayList<String>  jobonjaSkills = new ArrayList<String>();
    private Tools tools;

    public UsersDB(Tools tools) {

        this.tools = tools;
        ArrayList<Skill> tmpL = new ArrayList<Skill>();
        tmpL.add(new Skill("HTML",5));
        tmpL.add(new Skill("Javascript",4));
        tmpL.add(new Skill("C++",2));
        tmpL.add(new Skill("Java",3));
        User tmp = new User(1, "روی سنگ قبرم بنویسید: خدا بیامرز میخواست خیلی کارا بکنه ولی نشد","برنامه نویس وب", "علی",
                "شریف زاده", tmpL);
        jabonjaUsers.add(tmp);

    }
    public int getSize(){
        return jabonjaUsers.size();
    }
    public User getUser (String usrName ) throws NullPointerException{

        for (int i = 0 ;i<this.jabonjaUsers.size() ;i++) {
            if (this.jabonjaUsers.get(i).getUserName().equals(usrName)) {
                return this.jabonjaUsers.get(i);
            }
        }
        return new User();
    }
    public User getUser(int id){
        for (int i = 0 ;i<this.jabonjaUsers.size() ;i++) {
            if (this.jabonjaUsers.get(i).getId() == id) {
//                System.out.println(jabonjaUsers.get(i).getUserName());
                return this.jabonjaUsers.get(i);
            }
        }
        return new User();
    }
    public void getSkillsFromServer()throws IOException {
        String json = tools.getJsonStr("http://142.93.134.194:8000/joboonja/skill");
        JSONArray jsonarray = new JSONArray(json);
        for (int i=0;i<jsonarray.length();i++){
            JSONObject jsonObject = jsonarray.getJSONObject(i);

            jobonjaSkills.add( jsonObject.getString("name"));
        }
    }
    public void add(User x){
        jabonjaUsers.add(x);
    }

}