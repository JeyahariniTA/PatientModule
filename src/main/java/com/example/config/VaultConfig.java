package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.VaultCredentials;

@Configuration
public class VaultConfig {

	@Autowired
	VaultCredentials vaultCredentials;

	@Bean
	public DataSource getDataSource() {
		@SuppressWarnings("rawtypes")
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/dbtest");
		dataSourceBuilder.username(vaultCredentials.getDbusername());
		dataSourceBuilder.password(vaultCredentials.getDbpassword());
		return dataSourceBuilder.build();
	}

}
