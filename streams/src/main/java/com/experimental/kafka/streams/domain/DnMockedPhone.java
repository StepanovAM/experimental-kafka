package com.experimental.kafka.streams.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DnMockedPhone {

    @Id
    private String phoneNumber;
    private String mockedCode;
}
