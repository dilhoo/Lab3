package lab3.component.service;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Service
public class RestTemplateFactory {

    public RestTemplate create() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, CertificateException, IOException {
        String truststoreLocation = System.getenv("KS_LOCATION");
        String truststorePass = System.getenv("KS_PASS");
        SSLContext sslContext =
                SSLContexts
                        .custom()
                        .loadTrustMaterial(new File(truststoreLocation), truststorePass.toCharArray())
                        .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpClient =
                HttpClients.custom()
                        .setSSLSocketFactory(csf)
                        .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        return new RestTemplate(requestFactory);
    }
}