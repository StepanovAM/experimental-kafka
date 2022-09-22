package com.experimental.kafka.producer.repository;


import com.experimental.kafka.producer.domain.DnEventType;
import org.springframework.data.repository.CrudRepository;

public interface DnEventTypeRepository extends CrudRepository<DnEventType, String> {
}
