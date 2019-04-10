package com.ashwinmulky.security.config;

import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.ashwinmulky.security.repository")
public class DatabaseConfiguration {

    //This is to set Mongock bean - for initial DB setup

    @Bean
    public SpringBootMongock mongock(ApplicationContext springContext, MongoClient mongoClient) {
        return (SpringBootMongock) new SpringBootMongockBuilder(mongoClient, "spring_security_jwt", "com.ashwinmulky.security.config.dbmigrations")
                .setApplicationContext(springContext)
                .setLockQuickConfig()
                .build();
    }
}