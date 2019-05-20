import java.io.IOException;

//import DataAccessLayer.ProjectsMapper;

public class Refresher implements Runnable {

	private ProjectsMapper pdb = ProjectsMapper.getInstance();

	@Override
	public void run() {

		try {
			pdb.getProjectFromServer();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}