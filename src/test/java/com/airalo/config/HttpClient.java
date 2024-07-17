package com.airalo.config;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClient {

    public static HttpClient serviceClient;
    private String apiUrl, clientId, clientSecret, token;
    private CloseableHttpClient client;

    HttpClientConnectionManager connMgr;

    private HttpClient(String apiUrl, String clientId, String clientSecret) {
        this.apiUrl = apiUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        connMgr = initConnectionManager();

        client = HttpClients.custom()
                .setConnectionManager(connMgr)
                .build();
    }

    public String getApiUrl() {
        return this.apiUrl;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String getAccessToken() {
        return this.token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    private HttpClientConnectionManager initConnectionManager() {
        BasicHttpClientConnectionManager simple_cm;
        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;

        try {
            socketFactoryRegistry = createConnectionSocketFactory();
            simple_cm = new BasicHttpClientConnectionManager(socketFactoryRegistry);
        } catch (Exception e) {
            throw new Error("Could not initialize registry of connection socket factory. HTTPS connection will not work or trust self-signed (any) certificates: " + e.getCause());
        }

        return simple_cm;
    }

    private Registry<ConnectionSocketFactory> createConnectionSocketFactory() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        ConnectionSocketFactory socketFactory = createSSLConnectionSocketFactory();
        registryBuilder.register("https", socketFactory);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = registryBuilder.build();

        return socketFactoryRegistry;
    }

    private SSLConnectionSocketFactory createSSLConnectionSocketFactory() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContextBuilder builder = SSLContexts.custom();

        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                return true;
            }
        });

        SSLContext sslContext = builder.build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"},
                null,
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });

        return sslsf;
    }

    public static HttpClient getInstance() {
        if (serviceClient == null) {
            serviceClient = new HttpClient(
                    EnvironmentProperties.get().getApiUrl(),
                    EnvironmentProperties.get().getClientId(),
                    EnvironmentProperties.get().getClientSecret()
            );
        }
        return serviceClient;
    }

    public HttpResponse sendRequest(HttpRequestBase request) throws Exception {
        CloseableHttpResponse response = client.execute(request);
        return response;
    }
}
