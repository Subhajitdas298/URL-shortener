package com.sd.hackathon.eatable.backend.service.urlconverter;

import com.sd.hackathon.eatable.backend.document.SequenceNumber;
import com.sd.hackathon.eatable.backend.document.UrlCode;
import com.sd.hackathon.eatable.backend.repository.SequenceNumberRepository;
import com.sd.hackathon.eatable.backend.repository.UrlCodeRepository;
import com.sd.hackathon.eatable.backend.service.transcoder.NumberEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class UrlConverterServiceImpl implements UrlConverterService {

    @Value("${eatable.encoder.sequenceStoreName}")
    private String sequenceStoreName;

    @Autowired
    private UrlCodeRepository urlRepository;

    @Autowired
    private SequenceNumberRepository numberRepository;

    @Autowired
    private NumberEncoder encoder;

    @Override
    public UrlCode saveUrl(UrlCode urlCode) {
        SequenceNumber sequenceStore = numberRepository.findBySequenceName(sequenceStoreName);
        long nextNo = sequenceStore.getNextNumber();

        sequenceStore.setNextNumber(nextNo + 1);
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
        return urlRepository.findByCode(code);
    }

    @Override
    public Slice<UrlCode> pageUrlsByCode(String code, Pageable pageable) {
        return urlRepository.findAllByCodeStartingWith(code, pageable);
    }
}
