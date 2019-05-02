import java.io.IOException;

public class Refresher implements Runnable {

	private ProjectsDB pdb = ProjectsDB.getInstance();

	@Override
	public void run() {

		try {
			pdb.getProjectFromServer();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}