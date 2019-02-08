import java.util.ArrayList;

public class Bid {

    Project  project;
    User user;
    int bidAmount ;

    public Bid(Project project, User user, int bidAmount) {
        this.project = project;
        this.user = user;
        this.bidAmount = bidAmount;
    }
    public boolean isValid(){

        if(bidAmount > project.getBudget())
            return false;
        ArrayList<Skill> prerequisites = project.getPrerequisites();
        ArrayList<Skill> userSkills = user.getSkills();
        boolean flag=true;
        for(int i =0;i<prerequisites.size() ; i++){
            if(!flag)
                break;
            for(int j=0;j<userSkills.size(); j++){
                if(prerequisites.get(i).getName().equals(userSkills.get(j).getName())){
                    if (prerequisites.get(i).getPoint() <= userSkills.get(j).getPoint() )
                        flag=true;
                    else{
                        flag=false;
                    }

                }
                else{
                    flag=false;
                }
            }
        }
        if(flag)
            return true;
        else
            return false;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public int getBidAmount() {
        return bidAmount;
    }
}
