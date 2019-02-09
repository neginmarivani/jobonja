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

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", skills=" + skills +
                '}';
    }
}
