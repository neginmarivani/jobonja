import java.util.ArrayList;

public class UsersDB {

    private ArrayList<User> jabonjaUsers = new ArrayList<User>();
    public User getUser (String usrName ) throws NullPointerException{

        for (int i = 0 ;i<this.jabonjaUsers.size() ;i++) {
            if (this.jabonjaUsers.get(i).getUserName().equals(usrName)) {
                return this.jabonjaUsers.get(i);
            }
        }
        return new User();
    }
    public void add(User x){
        jabonjaUsers.add(x);

    }
}