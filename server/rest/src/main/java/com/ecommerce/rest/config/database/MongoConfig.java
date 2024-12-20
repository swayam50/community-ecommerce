package com.ecommerce.rest.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.ecommerce.rest.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.url}")
    private String url;

    @Override
    public String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    @Qualifier(value = "mongoClient")
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(url);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                                                                     .applyConnectionString(connectionString)
                                                                     .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    @Primary
    @Qualifier(value = "mongoDbFactory")
    public MongoDatabaseFactory fetchMongoDbFactory(@Autowired MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, database);
    }

    @Bean
    @Qualifier(value = "mongoTemplate")
    public MongoTemplate fetchMongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Bean
    @Qualifier(value = "gridFsTemplate")
    public GridFsTemplate fetchGridFsTemplate(
        @Autowired
        MongoClient mongoClient,
        @Autowired
        MongoConverter mongoConverter
    ) throws Exception {
        return new GridFsTemplate(fetchMongoDbFactory(mongoClient), mongoConverter);
    }

}
