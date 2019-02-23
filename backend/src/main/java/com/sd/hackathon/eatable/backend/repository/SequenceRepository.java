package com.sd.hackathon.eatable.backend.repository;

import com.sd.hackathon.eatable.backend.document.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, String> {
}
