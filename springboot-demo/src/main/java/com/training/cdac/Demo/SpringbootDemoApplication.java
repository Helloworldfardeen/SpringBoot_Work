package com.training.cdac.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Indicates that this class is the main entry point of the Spring Boot application.
//It also enables auto-configuration and component scanning features of Spring Boot.
public class SpringbootDemoApplication {

	public static void main(String[] args) {
	      // SpringApplication.run() method bootstraps the Spring application
        // by creating and configuring the Spring ApplicationContext.
        // It starts the embedded Tomcat server (by default) and deploys the application.
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
