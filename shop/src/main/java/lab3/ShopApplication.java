package lab3;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.DiscoveryClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public AbstractDiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, KeyManagementException {
        File keystoreFile = new File(System.getenv("KS_LOCATION"));
        char[] keystorePass = System.getenv("KS_PASS").toCharArray();
        SSLContext sslContext =
                SSLContexts
                        .custom()
                        .loadTrustMaterial(keystoreFile, keystorePass)
                        .loadKeyMaterial(keystoreFile, keystorePass, keystorePass)
                        .build();

        DiscoveryClient.DiscoveryClientOptionalArgs clientArgs = new DiscoveryClient.DiscoveryClientOptionalArgs();
        clientArgs.setSSLContext(sslContext);
        clientArgs.setHostnameVerifier(NoopHostnameVerifier.INSTANCE);
        return clientArgs;
    }
}