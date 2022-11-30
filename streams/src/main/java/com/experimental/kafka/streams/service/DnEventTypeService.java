package com.experimental.kafka.streams.service;

import com.experimental.kafka.streams.repository.DnEventTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DnEventTypeService {

    private final DnEventTypeRepository repository;

    public DnEventTypeService(DnEventTypeRepository repository) {
        this.repository = repository;
    }

//    @Transactional
    public void save(String id, String desc) {
        log.info("mockedPhone id: {}, desc: {}", id, desc);
//        repository.save(new DnEventType(id, desc));
//        log.info("Saved");
    }
}
