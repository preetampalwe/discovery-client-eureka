package com.mycompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class DiscoveryClientEurekaApplication {

	private static Logger logger = LoggerFactory.getLogger(DiscoveryClientEurekaApplication.class);

	public static void main(String[] args) {
		logger.debug("\n\n\nAll Environment Variables - " + System.getenv());
		logger.debug("\n\n\nAll System Properties - " + System.getProperties());

		SpringApplication.run(DiscoveryClientEurekaApplication.class, args);
	}

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "salutation", defaultValue = "Hello") String salutation,
			@RequestParam(value = "name", defaultValue = "Bob") String name) {
		logger.debug("Received -- " + salutation + " and " + name);
		return new Greeting(salutation, name);
	}

	public static class Greeting {
		private static final String template = "%s, %s!";
		private String message;

		public Greeting(String salutation, String name) {
			this.message = String.format(Greeting.template, salutation, name);
		}

		public String getMessage() {
			return this.message;
		}
	}
}
