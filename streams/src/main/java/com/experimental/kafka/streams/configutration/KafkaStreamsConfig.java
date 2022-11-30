package com.experimental.kafka.streams.configutration;

import com.experimental.kafka.streams.service.DnEventTypeService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableKafkaStreams
//@EnableKafka
public class KafkaStreamsConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaStreamsConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public KafkaStreamsConfiguration experimentalKafkaStreamsConfiguration() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildStreamsProperties());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(StreamsConfig.POLL_MS_CONFIG, 1000);
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public StreamsBuilderFactoryBean experimentalStreamsBuilder(KafkaStreamsConfiguration experimentalKafkaStreamsConfiguration) {
        return new StreamsBuilderFactoryBean(experimentalKafkaStreamsConfiguration);
    }

    @Bean
    public KStream<String, String> experimentalStream(StreamsBuilder experimentalStreamsBuilder, DnEventTypeService service) {
        KStream<String, String> stream = experimentalStreamsBuilder.stream("experimental-topic");
        stream.foreach(service::save);
        return stream;
    }
}
