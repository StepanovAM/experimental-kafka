package com.experimental.kafka.streams.service;

import com.experimental.kafka.streams.domain.DnEventType;
import com.experimental.kafka.streams.repository.DnEventTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DnEventTypeService {

    private final DnEventTypeRepository repository;

    public DnEventTypeService(DnEventTypeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(String id, String desc) {
        log.info("id: {}, desc: {}", id, desc);
        repository.save(new DnEventType(id, desc));
        log.info("Saved");
    }
}
