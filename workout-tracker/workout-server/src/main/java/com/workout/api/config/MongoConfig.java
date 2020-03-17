package com.workout.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	private static final String MONGO_DB_URL = "localhost";
	private static final String MONGO_DB_NAME = "embeded_db";

//	@Bean
//	public MongoTemplate mongoTemplate() throws IOException {
//		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
//		mongo.setBindIp(MONGO_DB_URL);
//		MongoClient mongoClient = mongo.getObject();
//		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
//		return mongoTemplate;
//	}

	@Override
	public MongoClient mongoClient() {
		return new MongoClient("localhost", 27017);
	}

	@Override
	protected String getDatabaseName() {
		return "test";
	}

	@Bean
	TransactionTemplate transactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(platformTransactionManager());
		return transactionTemplate;
	}

	@Bean
	PlatformTransactionManager platformTransactionManager() {
		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		TransactionManagerImple transactionManagerImple = new TransactionManagerImple();
		jtaTransactionManager.setTransactionManager(transactionManagerImple);
		return jtaTransactionManager;
	}
}
