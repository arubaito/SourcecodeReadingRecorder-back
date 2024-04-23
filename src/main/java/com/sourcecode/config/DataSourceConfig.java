package com.sourcecode.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	// Logger
	org.slf4j.Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Bean
	public DataSource dataSource() {
		logger.info("CREATE DATA SOURCE");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
		dataSource.setUrl("jdbc:postgresql://localhost/sourcecode_reading_recorder");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		logger.info("CREATE JdbcTemplate");
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
