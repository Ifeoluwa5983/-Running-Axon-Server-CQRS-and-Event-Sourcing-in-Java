package com.example.Restaurant;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = {"com.example.Restaurant.*"})
@EntityScan(basePackages = {"com.example.Restaurant.*"})
@EnableMongoRepositories(basePackages = {"com.example.Restaurant.*"})
@Configuration
public class DataConfig {
}
