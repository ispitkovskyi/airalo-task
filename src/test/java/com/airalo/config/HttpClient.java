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

    public String getApiUrl(){
        return this.apiUrl;
    }

    public String getClientId(){
        return this.clientId;
    }

    public String getClientSecret(){
        return this.clientSecret;
    }

    public String getAccessToken(){
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private HttpClientConnectionManager initConnectionManager() {
        BasicHttpClientConnectionManager simple_cm;
        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;

        try {
            socketFactoryRegistry = createConnectionSocketFactory();
            simple_cm = new BasicHttpClientConnectionManager(socketFactoryRegistry);
        }catch (Exception e){
            throw new Error ("Could not initialize registry of connection socket factory. HTTPS connection will not work or trust self-signed (any) certificates: " + e.getCause());
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
                new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" },
                null,
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });

        return sslsf;
    }

/*    private static HttpsClient initClient(String protocol, String host, String port, String user, String password) {
        try {
            return  new HttpsClient()
            if(ConfigurationProperties.instance().getPoolSize() == null || ConfigurationProperties.instance().getPoolSize() < 2){
                return new BasicClient(protocol, host, port, user, password);
            }else{
                return new PoolingClient(protocol, host, port, user, password);
            }
        } catch (Exception e) {
            TestLogger.logInfo("Cannot initialize client instance: " + e.getMessage());
            throw e;
        }
    }*/

/*    public static HttpsClient getInstance(String protocol, String host, String port, String user, String password) throws Exception {
        if (host == null || port == null || user == null) {
            throw new Exception("There is no client initialized and required credentials [host, port, user, password] have not been provided to initialize it");
        }

        String clientName = getClientNameByThreadName(Thread.currentThread().getName());
        ServiceClient alreadyExisting = (ServiceClient) GlobalContext.Get(clientName);

        if (alreadyExisting != null) {
            if (alreadyExisting.getHost() == host && alreadyExisting.getPort() == port && alreadyExisting.getUser() == user && alreadyExisting.getPassword() == password) {
                TestLogger.logInfo("SdaServiceClient initialized with same credentials and for same execution thread already exists. Skip creating duplicate...");
                return alreadyExisting;
            }
        }

        ServiceClient sc = initClient(protocol, host, port, user, password);
        GlobalContext.Put(clientName, sc);
        TestLogger.logInfo(String.format("New instance of SdaServiceClient created for thread=%s with host=%s, port=%s, user=%s, password=%s", Thread.currentThread().getName(), host, port, user, password));
        return sc;
    }*/

/*    public static ServiceClient getInstance(String host, String port, String user, String password) throws Exception {
        return getInstance(null, host, port, user, password);
    }*/
    
/*    public static HttpsClient getInstance(String userName, String password) throws Exception {
    	return getInstance(getSdaHost(), getSdaPort(), userName, password);
    }*/

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

/*    public static ServiceClient getInstance() throws Exception {
        ServiceClient client = null;

        if (GlobalContext.getRunSuitesParallel()) {
            client = (ServiceClient) GlobalContext.Get(getClientNameByThreadName(Thread.currentThread().getName()));
        }

        if (client == null) {
            if (GlobalContext.getRunSuitesParallel())
                TestLogger.logInfo("HTTP client associated with current thread (" + Thread.currentThread().getName() + ") was not found. Will try getting default HTTP client.");
            client = (ServiceClient) GlobalContext.Get(getClientNameByThreadName(THREAD_MAIN));
        }

        if (client == null) {
            throw new Exception("Could not find in the GlobalContext HTTP client neither for specific nor for main thread. Probably none HTTP clients have been initialized.");
        }

        return client;
    }*/


/*    private static String getClientNameByThreadName(String threadName) {
        return THREAD_PREFIX + threadName + "_httpclient";
    }

    private static String getSdaHost() {
        return ConfigurationProperties.instance().getHost();
    }
    
    private static String getSdaPort() {
        return ConfigurationProperties.instance().getPort();
    }    

    public boolean equals(ServiceClient client) {
        if (client.getHost() == host && client.getPort() == port && client.getUser() == user && client.getPassword() == pwd) {
            return true;
        } else {
            return false;
        }
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return pwd;
    }

    public String getProtocol(){return protocol;}*/


/*    public void setHeaders(HttpRequestBase req, HashMap<String, String> headers){
        headers.entrySet().stream().forEach(entry -> setHeader(req, entry.getKey(), entry.getValue()));
    }

    public void setHeader(HttpRequestBase req, String headerName, String headerValue){
        req.setHeader(new BasicHeader(headerName, headerValue));
    }*/

    //Generating Authorization header and attaching it as header to request
/*    protected void setBasicAuthHeader(HttpRequestBase req) {
        //Authorization
        byte[] encodedPassword = (this.user + ":" + this.pwd).getBytes();

        String USER_AUTH = DatatypeConverter.printBase64Binary(encodedPassword);
        setHeader(req, "Authorization", "Basic " + USER_AUTH);
    }*/

    //Getting SSO token and attaching it as header to request
/*    protected void setALFSSOAuthNTokenHeader(HttpRequestBase req) throws Exception {
        ssoHelper = com.rlc.rest.http.clients.SSO.getInstance(this.user, this.pwd);
        String ssoToken = ssoHelper.getSSOTokenBase64();
        setHeader(req,"ALFSSOAuthNToken", ssoToken);
    }*/

    //Generating Authorization header and attaching it as header to request
/*    protected void setAuthorizationHeaders(HttpRequestBase req) throws Exception {
        setBasicAuthHeader(req);
        if(ConfigurationProperties.instance().isSSO()){
            setALFSSOAuthNTokenHeader(req);
        }
    }*/

/*    public HttpResponse sendRequest(HttpRequestBase request) throws Exception {
        return sendRequest(request, true);
    }*/
    //Trying to send request and receive response from server
    public HttpResponse sendRequest(HttpRequestBase request) throws Exception {
        //getRequest.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
//        if(needAuth)
//            setAuthorizationHeaders(request);

//        int statusCode = 0;
//        String responseBody = "";

        CloseableHttpResponse response = client.execute(request);
//        response.close();
//        try {
////            HttpEntity entity = response.getEntity();
////            statusCode = response.getStatusLine().getStatusCode();
////            responseBody = entity == null ? "" : EntityUtils.toString(entity, StandardCharsets.UTF_8);
//            response = client.execute(request);
//        } finally {
//            response.close();
//        }
          return response;
////        return new Response(statusCode, responseBody);
    }

    public void close() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
