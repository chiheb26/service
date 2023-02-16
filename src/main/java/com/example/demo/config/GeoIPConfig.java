package com.example.demo.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class GeoIPConfig {

    @Value("${spring.maxmind.database-path}") // This is the path to the GeoLite2 database
   // @Value("src/main/resources/db/GeoLite2-City.mmdb")
    private String databasePath;

    @Bean
    public DatabaseReader databaseReader() throws IOException {
        File database = new File(databasePath);
        return new DatabaseReader.Builder(database).build();
    }
}