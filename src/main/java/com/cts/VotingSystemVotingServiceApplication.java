package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = "com")
@EnableDiscoveryClient
@EnableFeignClients("com.cts")
@Slf4j
public class VotingSystemVotingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemVotingServiceApplication.class, args);
		log.info("server started at 8200");
	}

}
