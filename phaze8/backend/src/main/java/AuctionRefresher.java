import java.io.IOException;

public class AuctionRefresher implements Runnable {

    private ProjectsMapper pdb = ProjectsMapper.getInstance();
    private AuctionMapper adb = AuctionMapper.getInstance();
    @Override
    public void run() {
            pdb.checkProjectsForBestBid(adb);

    }

}