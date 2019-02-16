package com.sd.hackathon.eatable.backend.repository;

import com.sd.hackathon.eatable.backend.document.SequenceNumber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceNumberRepository extends MongoRepository<SequenceNumber, String> {
    SequenceNumber findBySequenceName(String sequenceName);
}
