package com.experimental.kafka.producer.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAsync
@EnableTransactionManagement
public class KafkaProducerConfiguration {

    private final KafkaProperties kafkaProperties;

    public KafkaProducerConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> producerConfigs = new HashMap<>(kafkaProperties.buildProducerProperties());
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 500);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 60000);
//        producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory<>(producerConfigs);
//        producerFactory.setTransactionIdPrefix(kafkaProperties.getProducer().getTransactionIdPrefix());
        producerFactory.setTransactionIdPrefix("ONE-TRANSACTION");
        return producerFactory;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory2() {
        DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
//        producerFactory.setTransactionIdPrefix("TWO-TRANSACTION");
        return producerFactory;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic("experimental-topic");
        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate2(ProducerFactory<String, String> producerFactory2) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory2);
        kafkaTemplate.setDefaultTopic("quickstart-events");
        return kafkaTemplate;
    }

//    @Bean
//    public KafkaTransactionManager experimentalKafkaTransactionManager(ProducerFactory<String, String> producerFactory) {
//        return new KafkaTransactionManager<>(producerFactory);
//    }

//    @Bean
//    @Primary
//    public PlatformTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}