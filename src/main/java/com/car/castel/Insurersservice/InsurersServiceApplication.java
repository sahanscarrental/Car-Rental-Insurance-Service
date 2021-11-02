package com.car.castel.Insurersservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class InsurersServiceApplication {

	@Autowired
	private LicenceNoService licenceNoService;

	public static void main(String[] args) {
		SpringApplication.run(InsurersServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			System.out.println("inserting Sample fraud insurance claimed License No");
			this.licenceNoService.createSampleData();
		};
	}

}
