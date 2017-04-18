package io.spring.cloud.samples.fortuneteller.ui;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
	@LoadBalanced
	@Bean
	public RestTemplate loadBalanced() {
		return new RestTemplate();
	}

}
