package com.sd.hackathon.eatable.backend.service.urlconverter;

import com.sd.hackathon.eatable.backend.document.Sequence;
import com.sd.hackathon.eatable.backend.document.UrlCode;
import com.sd.hackathon.eatable.backend.repository.SequenceRepository;
import com.sd.hackathon.eatable.backend.repository.UrlCodeRepository;
import com.sd.hackathon.eatable.backend.service.transcoder.NumberEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class UrlConverterServiceImpl implements UrlConverterService {

    @Value("${application.encoder.sequenceStoreName}")
    private String sequenceStoreName;

    @Autowired
    private UrlCodeRepository urlRepository;

    @Autowired
    private SequenceRepository numberRepository;

    @Autowired
    private NumberEncoder encoder;

    @Override
    public UrlCode saveUrl(UrlCode urlCode) {
        Sequence sequenceStore = numberRepository.findById(sequenceStoreName).get();
        long nextNo = sequenceStore.getNext();

        sequenceStore.setNext(nextNo + 1);
        numberRepository.save(sequenceStore);

        urlCode.setCode(encoder.encode(nextNo));
        urlCode.setHitCount(0L);
        return urlRepository.save(urlCode);
    }

    @Override
    public UrlCode updateUrl(UrlCode urlCode) {
        return urlRepository.save(urlCode);
    }

    @Override
    public UrlCode getUrl(String code) {
        return urlRepository.findById(code).get();
    }

    @Override
    public UrlCode getCode(String url) {
        return urlRepository.findByUrl(url);
    }

    @Override
    public Slice<UrlCode> pageUrlsByCode(String code, Pageable pageable) {
        return urlRepository.findAllByCodeStartingWith(code, pageable);
    }
}
