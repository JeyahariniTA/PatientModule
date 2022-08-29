package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.example.config.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@SpringBootApplication
@RefreshScope
@EnableConfigurationProperties(Credentials.class)
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "User Role Module API", version = "2.0"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class Sample1Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(Sample1Application.class);

	@Autowired
	private Config config;

	@Autowired
	private Credentials credentials;

	public Sample1Application(Credentials credentials) {
		this.credentials = credentials;
	}

	public static void main(String[] args) {
		SpringApplication.run(Sample1Application.class, args);
//		System.out.println("message: " + message);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("config: " + config.getSpringCloudConfigServerGitUri());
		logger.info("username: " + credentials.getUsername());
		logger.info("password: " + credentials.getPassword());
	}

}
