package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.example.config.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
@EnableConfigurationProperties(VaultCredentials.class)
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Patient Module API", version = "2.0"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class PatientModuleApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(PatientModuleApplication.class);

	@Autowired
	private Config config;

	@Autowired
	private VaultCredentials credentials;

	public PatientModuleApplication(VaultCredentials credentials) {
		this.credentials = credentials;
	}

	public static void main(String[] args) {
		SpringApplication.run(PatientModuleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("config: " + config.getSpringCloudConfigServerGitUri());
		logger.info("dbusername: " + credentials.getDbusername());
	}

}
