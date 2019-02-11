import java.util.ArrayList;

public class Project {
    String title;
    ArrayList <Skill> prerequisites = new ArrayList<Skill>();
    int budget;
    ArrayList<Bid> bids = new ArrayList<Bid>();

    public Project(String title, ArrayList<Skill> prerequisites, int budget) {
        this.title = title;
        this.prerequisites = prerequisites;
        this.budget = budget;
    }
    public void addBid(Bid bid ){
        bids.add(bid);
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
                ", prerequisites=" + prerequisites +
                ", budget=" + budget +
                ", bids=" + bids +
                '}';
    }

    public User findBestBid(){

        int max = 0;
        User selected = new User();
        for(int i=0;i<bids.size() ;i++){

            ArrayList<Skill> skill = bids.get(i).getProject().getPrerequisites();
            int score =0;
            for(int j=0;j<skill.size() ;j++){
                score += 10000*Math.pow( bids.get(i).getUser().getSkill(skill.get(j).getName()).getPoint() - skill.get(j).getPoint(),2) ;

            }
            score += budget - bids.get(i).getBidAmount() ;
            if (score > max ){
                max = score;
                selected = bids.get(i).getUser();
            }

        }
        return selected;
    }
}
