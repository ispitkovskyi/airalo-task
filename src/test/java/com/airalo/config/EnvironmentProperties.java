package com.airalo.config;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("environment.properties")
public class EnvironmentProperties {
    private static EnvironmentProperties environmentProperties;
    private EnvironmentProperties() {
        PropertyLoader.populate(this);
    }

    public static EnvironmentProperties get() {
        if (environmentProperties == null) {
            environmentProperties = new EnvironmentProperties();
        }
        return environmentProperties;
    }

    @Property("browser")
    public String browser;

    @Property("homeUrl")
    public String homeUrl;

    @Property("apiUrl")
    public String apiUrl;
    @Property("clientId")
    public String clientId;
    @Property("clientSecret")
    public String clientSecret;

    public String getBrowser(){return browser;}
    public String getHomeUrl(){return homeUrl;}
    public String getApiUrl(){return apiUrl;}
    public String getClientId(){return clientId;}
    public String getClientSecret(){return clientSecret;}
}
