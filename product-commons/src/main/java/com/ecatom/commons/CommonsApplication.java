package com.ecatom.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class CommonsApplication {
    //CREATING A Product DEPENDENCY TO ALL PROJECTS TO PREVENT CODE DUPLICATION//////
    //Removed main to use commons as dependency to other microservices
    //pom.xml edited, maven build removed
    //Added @EnableAutoConfiguration and excluded DataSourceAutoConfiguration to prevent JPA errors in every microservice that does not use database and uses Commons
    //Compiled jar
    //Changed all microservices imports to add the new dependency
    //Removed duplicate dependencies in the rest of the microservices
    //Added @EntityScan to the main classes in the changed microservice, allowing Spring to discover packages out of it's default scope

}
