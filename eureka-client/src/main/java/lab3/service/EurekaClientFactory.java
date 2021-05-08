package lab3.service;

import com.netflix.appinfo.*;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.sun.jersey.api.client.filter.ClientFilter;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.http.conn.ssl.NoopHostnameVerifier;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class EurekaClientFactory {
    
    private final String healthcheckEndpoint;
    private final String statusEndpoint;

    public EurekaClientFactory(String healthcheckEndpoint, String statusEndpoint) {
        this.healthcheckEndpoint = healthcheckEndpoint;
        this.statusEndpoint = statusEndpoint;
    }
    
    public EurekaClient create() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String eurekaHost = System.getenv("EUREKA_HOST");
        String eurekaPort = System.getenv("EUREKA_PORT");
        int leaseDuration = Integer.parseInt(System.getenv("EUREKA_LEASE_DURATION"));

        String serviceName = System.getenv("SERVICE_NAME");
        String appGroup = System.getenv("APP_GROUP");
        String host = System.getenv("HOST");
        int port = Integer.parseInt(System.getenv("PORT"));

        AbstractConfiguration config = ConfigurationManager.getConfigInstance();
        config.setProperty("eureka.region", "default");
        config.setProperty("eureka.serviceUrl.default", "https://" + eurekaHost + ":" + eurekaPort + "/eureka");

        NetLocation serviceLocation = new NetLocation(host, port);

        InstanceInfo instanceInfo =
                InstanceInfo.Builder.newBuilder()
                        .setAppName(serviceName)
                        .setIPAddr(System.getenv("IP"))
                        .setAppGroupName(appGroup)
                        .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                        .setHostName(host)
                        .setInstanceId(host + ":" + serviceName + ":" + port)
                        .setSecurePort(port)
//                        .setSecureVIPAddress(serviceName)
                        .setHealthCheckUrls(null, null, serviceLocation.url(healthcheckEndpoint))
                        .setStatusPageUrl(null, serviceLocation.url(statusEndpoint))
                        .setLeaseInfo(LeaseInfo.Builder.newBuilder().setDurationInSecs(leaseDuration).build())
                        .build();

        EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig(serviceName);

        ApplicationInfoManager infoManager = new ApplicationInfoManager(instanceConfig, instanceInfo);

        AbstractDiscoveryClientOptionalArgs<ClientFilter> clientArgs = new DiscoveryClient.DiscoveryClientOptionalArgs();
        clientArgs.setSSLContext(new TruststoreSslContextFactory().create());
        clientArgs.setHostnameVerifier(NoopHostnameVerifier.INSTANCE);

        EurekaClient eurekaClient = new DiscoveryClient(infoManager, new DefaultEurekaClientConfig(), clientArgs);
        eurekaClient.registerHealthCheck(status -> InstanceInfo.InstanceStatus.UP);
        return eurekaClient;
    }
}