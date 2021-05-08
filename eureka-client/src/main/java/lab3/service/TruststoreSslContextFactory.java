package lab3.service;

import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class TruststoreSslContextFactory {

    private final String truststoreLocation = System.getenv("KS_LOCATION");
    private final String truststorePass = System.getenv("KS_PASS");

    public SSLContext create() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        return SSLContexts
                .custom()
                .loadTrustMaterial(new File(truststoreLocation), truststorePass.toCharArray())
                .build();
    }
}