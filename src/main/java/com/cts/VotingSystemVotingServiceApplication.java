package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
@EnableDiscoveryClient
@EnableFeignClients("com.cts")
public class VotingSystemVotingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemVotingServiceApplication.class, args);
		System.out.println("server started at 8200");
	}

}
