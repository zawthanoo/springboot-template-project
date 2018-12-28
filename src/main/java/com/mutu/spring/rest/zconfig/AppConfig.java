package com.mutu.spring.rest.zconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("appconfig.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    public String getClientId() {
        return env.getProperty("CLIENT_ID");
    }
    
    public String getClientIdPassword() {
        return env.getProperty("CLIENT_ID_PASSWORD");
    }
}