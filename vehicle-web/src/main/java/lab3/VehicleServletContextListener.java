package lab3;

import com.netflix.discovery.EurekaClient;
import lab3.service.EurekaClientFactory;
import lab3.service.EurekaService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class VehicleServletContextListener implements ServletContextListener {

    private volatile EurekaClient eurekaClient;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        while (true) {
            try {
                eurekaClient = new EurekaClientFactory(EurekaService.HEARTBEAT_ENDPOINT, EurekaService.STATUS_ENDPOINT)
                        .create();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        eurekaClient.shutdown();
    }
}