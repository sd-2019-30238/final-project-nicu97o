package com.shoppinglist.configuration;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("googleApi.properties")
public class GoogleApiConfiguration {
    private Environment environment;

    @Autowired
    public GoogleApiConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public GeoApiContext getGeoApiContext() {
        GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey(environment.getProperty("googleApi.key")).build();
        return geoApiContext;
    }
}