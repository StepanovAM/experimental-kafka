package com.experimental.kafka.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

    @KafkaListener(id = "listener1", topics = "experimental-topic", groupId = "experimental-second-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("partition: " + record.partition() + " " + record.key() + " " + record.value());
    }
}
