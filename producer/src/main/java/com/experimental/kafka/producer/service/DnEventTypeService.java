package com.experimental.kafka.producer.service;

import com.experimental.kafka.producer.domain.DnEventType;
import com.experimental.kafka.producer.repository.DnEventTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DnEventTypeService {

    private final DnEventTypeRepository repository;
    private final KafkaTemplate kafkaTemplate;

    public DnEventTypeService(DnEventTypeRepository repository, KafkaTemplate kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public void save(String id, String desc) {
        log.info("id: {}, desc: {}", id, desc);
        repository.save(new DnEventType(id, desc));

        kafkaTemplate.sendDefault(id, desc);

        boolean b = true;
        if (b) {
            throw new RuntimeException();
        }

        log.info("Saved");
    }
}
