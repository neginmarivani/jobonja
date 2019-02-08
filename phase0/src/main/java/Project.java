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
    public Skill getSkill(String name ){
        for(int i=0;i<prerequisites.size();i++){
            if(prerequisites.get(i).getName().equals(name))
                return prerequisites.get(i);
        }
        return new Skill();
    }

    public Project() {
    }

    public User findBestBid(){

        int max = 0;
        User selected = new User();
        for(int i=0;i<bids.size() ;i++){

            ArrayList<Skill> userSkill = bids.get(i).getUser().getSkills();
            int score =0;
            for(int j=0;j<userSkill.size() ;j++){
                score += 1000*Math.pow(userSkill.get(j).getPoint() - this.getSkill(userSkill.get(j).getName()).getPoint(),2) ;
                score += bids.get(i).getBidAmount() - budget;
            }
            if (score > max ){
                max = score;
                selected = bids.get(i).getUser();
            }

        }
        return selected;
    }
}
