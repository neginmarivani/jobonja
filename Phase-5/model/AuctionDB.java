import java.util.ArrayList;
import java.util.HashMap;

public class AuctionDB {

	private static AuctionDB singlton_instance = null;

	public static AuctionDB getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new AuctionDB();
		}
		return singlton_instance;
	}

	private HashMap<String, ArrayList<Bid>> auctions = new HashMap<String, ArrayList<Bid>>();

	public void addBid(String projectName, Bid bid) {

		ArrayList<Bid> tmp = auctions.get(projectName);
		tmp.add(bid);
	}

	public boolean UserHasBidForProject(String projectName, User u) {
		for (int i = 0; i < auctions.get(projectName).size(); i++) {
			if (auctions.get(projectName).get(i).getUser().getId() == u.getId()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Bid> getBid(String name) {
		return auctions.get(name);
	}

	public void addProject(String name) {
		auctions.put(name, new ArrayList<Bid>());
	}

	public User findBestBid(String project) {

		int max = 0;
		User selected = new User();
		ArrayList<Bid> bids = auctions.get(project);

		for (int i = 0; i < bids.size(); i++) {

			ArrayList<Skill> skill = bids.get(i).getProject().getPrerequisites();
			int score = 0;
			for (int j = 0; j < skill.size(); j++) {
				score += 10000 * Math.pow(
						bids.get(i).getUser().getSkill(skill.get(j).getName()).getPoint() - skill.get(j).getPoint(), 2);

			}
			score += bids.get(i).getProject().getBudget() - bids.get(i).getBidAmount();
			if (score > max) {
				max = score;
				selected = bids.get(i).getUser();
			}

		}
		return selected;
	}

}
