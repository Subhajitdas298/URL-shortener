package com.sd.hackathon.eatable.backend.repository;

import com.sd.hackathon.eatable.backend.document.UrlCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlCodeRepository extends MongoRepository<UrlCode, String> {
    UrlCode findByUrl(String url);
    Slice<UrlCode> findAllByCodeStartingWith(String code, Pageable pageable);
}
