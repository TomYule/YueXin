package com.yuexin;

import com.yuexin.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class YueXinApplication {

	public static void main(String[] args) {
		SpringApplication.run(YueXinApplication.class, args);
	}

}
