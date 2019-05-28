
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AuctionRefreshManager implements ServletContextListener {

    private ScheduledExecutorService schedular;

    public void contextDestroyed(ServletContextEvent sce) {
        schedular.shutdown();
    }

    public void contextInitialized(ServletContextEvent sce) {

        Long initialTime = new Long(0);
        Long period = new Long(1);

        schedular = Executors.newSingleThreadScheduledExecutor();
        schedular.scheduleAtFixedRate(new AuctionRefresher(), initialTime, period, TimeUnit.MINUTES);

    }

}