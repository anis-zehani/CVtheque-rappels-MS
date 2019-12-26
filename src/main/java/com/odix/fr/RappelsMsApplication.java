package com.odix.fr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RappelsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RappelsMsApplication.class, args);
	}

}
