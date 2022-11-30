package com.experimental.kafka.producer.controller;

import com.experimental.kafka.producer.service.DnEventTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@RestController
@Slf4j
public class Endpoint {

    @Autowired
    KafkaTemplate kafkaTemplate;

//    @Autowired
//    KafkaTemplate kafkaTemplate2;

    @Autowired
    DnEventTypeService service;


    @GetMapping("/executeInTransaction")
    public void executeInTransaction(String key, String msg) {
        kafkaTemplate.sendDefault(key, msg);
//        service.save(key, msg);
//        sendInTransactionToKafka(key, msg);
//        sendInTransactionToKafka2(key, msg);
    }

    //    @Transactional(value = "experimentalKafkaTransactionManager", propagation = REQUIRES_NEW)
//    @Async
//    public void sendInTransactionToKafka(String key, String msg) {
//        try {
//            kafkaTemplate.executeInTransaction(operations -> {
//                for (int i = 0; i < 200; i++) {
//                    kafkaTemplate.sendDefault(String.valueOf(i), "FIRST TX" + i);
//                    if (i == 100) {
//                        throw new RuntimeException();
//                    }
//                }
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    throw new RuntimeException(e);
////                }
//                return null;
//            });
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//    @Async
//    public void sendInTransactionToKafka2(String key, String msg) {
//        try {
////            kafkaTemplate2.executeInTransaction(operations -> {
//                for (int i = 0; i < 200; i++) {
//                    kafkaTemplate2.sendDefault(String.valueOf(i), "second kafka template: " + i);
//                    if (i == 100) {
//                        throw new RuntimeException();
//                    }
//                }
////                return null;
////            });
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
}
