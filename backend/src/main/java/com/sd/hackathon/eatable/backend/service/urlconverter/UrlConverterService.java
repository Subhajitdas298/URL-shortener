package com.sd.hackathon.eatable.backend.service.urlconverter;

import com.sd.hackathon.eatable.backend.document.UrlCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UrlConverterService {
    UrlCode saveUrl(UrlCode URLCode);
    UrlCode updateUrl(UrlCode URLCode);
    UrlCode getUrl(String code);
    UrlCode getCode(String url);
    Slice<UrlCode> pageUrlsByCode(String code, Pageable pageable);
}
