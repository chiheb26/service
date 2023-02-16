package com.minotore.data.config;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GeoIPConfig {

    @Value("${spring.maxmind.database-path}") // This is the path to the GeoLite2 database
   // @Value("src/main/resources/db/GeoLite2City.mmdb")
    private String databasePath;
    @Autowired
    private ResourceLoader resourceLoader;
    @Bean
    public DatabaseReader databaseReader() throws IOException {
    //       File database = new File(databasePath);


         Resource resource = resourceLoader.getResource(databasePath);

       // InputStream inputStream = resource.getInputStream();
      //  File database = new File(resource.getFile().toURI());

        // ClassPathResourceLoader resourceLoader = new ClassPathResourceLoader();
      //  Resource resource = resourceLoader.getResource("classpath:file.txt");
       // InputStream inputStream = resource.getInputStream();

     //   ClassPathResource resource = new ClassPathResource(databasePath);
        //InputStream inputStream = resource.getInputStream();
      //  ClassPathResource resource = new ClassPathResource(databasePath);
       // InputStream database = resource.getInputStream();
      // return new DatabaseReader.Builder(database).build();
        return new DatabaseReader.Builder(resource.getInputStream()).build();
    }



}