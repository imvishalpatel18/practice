/**
 * 
 */
package com.flights.application;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.flights.service.FlightMstService;

/**		
 * @author Vishal
 */
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@RestController
@EnableTransactionManagement
@PropertySource("classpath:serverDB.properties")
@ComponentScan({ "com.flights" })
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(
        		Application.class);
        sa.run(args);
    }
	
	@Autowired
	private DataSource dataSource;

	@Resource
	public Environment environment;
	
	@Autowired
	FlightMstService flightService;

	private Logger logger = Logger.getLogger(Application.class);

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.flights" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getProperty("mysql.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("mysql.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("mysql.hbm2ddl.auto"));
		return properties;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("mysql.driverClassName"));
		dataSource.setUrl(environment.getProperty("mysql.url"));
		dataSource.setUsername(environment.getProperty("mysql.username"));
		dataSource.setPassword(environment.getProperty("mysql.password"));

		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		logger.info("starting app, setting up data");
		flightService.loadData();
		logger.info("load data done");
	}
}
