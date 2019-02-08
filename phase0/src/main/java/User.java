import java.util.ArrayList;

public class User {

    String userName;
    ArrayList <Skill> skills;

    public User(String userName, ArrayList<Skill> skills) {
        this.userName = userName;
        this.skills = skills;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }
}
