package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class Config {

//	@Value("${spring.datasource.url}")
//	private String url;
//
//	@Value("${spring.datasource.username}")
//	private String username;
//
//	@Value("${spring.datasource.password}")
//	private String password;
//
//	@Value("${server.error.whitelabel.enabled}")
//	private boolean enabled;
//
//	@Value("${spring.jpa.hibernate.ddl-auto}")
//	private String ddlauto;
//
//	@Value("${spring.jpa.show-sql}")
//	private boolean showsql;
//
//	@Value("${spring.jpa.generate-ddl}")
//	private boolean generateddl;
//
//	@Value("${spring.jpa.database-platform}")
//	private String databasePlatform;

	@Value("${spring.cloud.config.server.git.uri}")
	private String springCloudConfigServerGitUri;

	@Value("${spring.cloud.config.server.git.cloneOnStart}")
	private boolean springCloudConfigServerGitCloneOnStart;

	@Value("${management.security.enabled}")
	private boolean managementSecurityEnabled;

	@Value("$spring.cloud.vault.token}")
	private String springCloudVaultToken;

	@Value("${spring.cloud.vault.scheme}")
	private String springCloudVaultScheme;

	@Value("${spring.cloud.vault.kv.enabled}")
	private boolean springCloudVaultKvEnabled;

	@Value("${spring.application.name}")
	private String springApplicationName;

	@Value("${spring.cloud.vault.port}")
	private String springApplicationPort;

	@Value("${spring.cloud.vault.host}")
	private String springApplicationHost;

	@Value("${spring.config.import}")
	private String springConfigImport;

	@Value("${springdoc.api-docs.path}")
	private String springDocApiDocsPath;

	public String getSpringDocApiDocsPath() {
		return springDocApiDocsPath;
	}

	public void setSpringDocApiDocsPath(String springDocApiDocsPath) {
		this.springDocApiDocsPath = springDocApiDocsPath;
	}

	public String getSpringConfigImport() {
		return springConfigImport;
	}

	public void setSpringConfigImport(String springConfigImport) {
		this.springConfigImport = springConfigImport;
	}

	public String getSpringApplicationPort() {
		return springApplicationPort;
	}

	public void setSpringApplicationPort(String springApplicationPort) {
		this.springApplicationPort = springApplicationPort;
	}

	public String getSpringApplicationHost() {
		return springApplicationHost;
	}

	public void setSpringApplicationHost(String springApplicationHost) {
		this.springApplicationHost = springApplicationHost;
	}

	public String getSpringCloudVaultToken() {
		return springCloudVaultToken;
	}

	public void setSpringCloudVaultToken(String springCloudVaultToken) {
		this.springCloudVaultToken = springCloudVaultToken;
	}

	public String getSpringCloudVaultScheme() {
		return springCloudVaultScheme;
	}

	public void setSpringCloudVaultScheme(String springCloudVaultScheme) {
		this.springCloudVaultScheme = springCloudVaultScheme;
	}

	public boolean isSpringCloudVaultKvEnabled() {
		return springCloudVaultKvEnabled;
	}

	public void setSpringCloudVaultKvEnabled(boolean springCloudVaultKvEnabled) {
		this.springCloudVaultKvEnabled = springCloudVaultKvEnabled;
	}

//	@Value("${msg}")
//	private String msg;

//	public String getMsg() {
//		return msg;
//	}
//
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}

	public String getSpringCloudConfigServerGitUri() {
		return springCloudConfigServerGitUri;
	}

	public void setSpringCloudConfigServerGitUri(String springCloudConfigServerGitUri) {
		this.springCloudConfigServerGitUri = springCloudConfigServerGitUri;
	}

	public boolean isSpringCloudConfigServerGitCloneOnStart() {
		return springCloudConfigServerGitCloneOnStart;
	}

	public void setSpringCloudConfigServerGitCloneOnStart(boolean springCloudConfigServerGitCloneOnStart) {
		this.springCloudConfigServerGitCloneOnStart = springCloudConfigServerGitCloneOnStart;
	}

	public boolean isManagementSecurityEnabled() {
		return managementSecurityEnabled;
	}

	public void setManagementSecurityEnabled(boolean managementSecurityEnabled) {
		this.managementSecurityEnabled = managementSecurityEnabled;
	}

	public String getSpringApplicationName() {
		return springApplicationName;
	}

	public void setSpringApplicationName(String springApplicationName) {
		this.springApplicationName = springApplicationName;
	}

//	public String getDatabasePlatform() {
//		return databasePlatform;
//	}
//
//	public void setDatabasePlatform(String databasePlatform) {
//		this.databasePlatform = databasePlatform;
//	}
//
//	public String getDdlauto() {
//		return ddlauto;
//	}
//
//	public void setDdlauto(String ddlauto) {
//		this.ddlauto = ddlauto;
//	}
//
//	public boolean isShowsql() {
//		return showsql;
//	}
//
//	public void setShowsql(boolean showsql) {
//		this.showsql = showsql;
//	}
//
//	public boolean isGenerateddl() {
//		return generateddl;
//	}
//
//	public void setGenerateddl(boolean generateddl) {
//		this.generateddl = generateddl;
//	}
//
//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String toString() {
//		return "{" + url + " , " + username + "}";
//	}

}
