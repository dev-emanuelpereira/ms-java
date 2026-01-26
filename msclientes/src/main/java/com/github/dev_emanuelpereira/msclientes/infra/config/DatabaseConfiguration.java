package com.github.dev_emanuelpereira.msclientes.infra.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Bean
    public DataSource db() {
        HikariConfig configDB = new HikariConfig();

        configDB.setJdbcUrl(url);
        configDB.setUsername(username);
        configDB.setPassword(password);
        configDB.setDriverClassName(driver);

        configDB.setMaxLifetime(1000);
        configDB.setMinimumIdle(1);
        configDB.setMaximumPoolSize(10);
        configDB.setConnectionTimeout(100000);

        return new HikariDataSource(configDB);
    }
}
