package com.experimental.kafka.streams.repository;

import com.experimental.kafka.streams.domain.DnMockedPhone;
import org.springframework.data.repository.CrudRepository;

public interface DnEventTypeRepository extends CrudRepository<DnMockedPhone, String> {
}
