
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class ProjectsRefreshManager implements ServletContextListener {

	private ScheduledExecutorService schedular;

	public void contextDestroyed(ServletContextEvent sce) {
		schedular.shutdown();
	}

	public void contextInitialized(ServletContextEvent sce) {

		Long initialTime = new Long(0);
		Long period = new Long(5);

		schedular = Executors.newSingleThreadScheduledExecutor();
		schedular.scheduleAtFixedRate(new Refresher(), initialTime, period, TimeUnit.MINUTES);

	}

}
