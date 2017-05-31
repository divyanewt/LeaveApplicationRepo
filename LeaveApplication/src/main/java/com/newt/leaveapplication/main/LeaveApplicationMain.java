package com.newt.leaveapplication.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EnableSwagger2
@EnableJpaRepositories("com.newt.leaveapplication.dao")
@EntityScan("com.newt.leaveapplication.model")
@ComponentScan(basePackages={"com"})


public class LeaveApplicationMain {
	public static void main(String args[]){
		SpringApplication.run(LeaveApplicationMain.class, args);
		
	}

}
