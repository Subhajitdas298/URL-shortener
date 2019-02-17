package com.sd.hackathon.eatable.backend.controller;

import com.sd.hackathon.eatable.backend.document.UrlCode;
import com.sd.hackathon.eatable.backend.service.urlconverter.UrlConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.api.root}")
@CrossOrigin
public class ApiController {

    @Autowired
    private UrlConverterService urlConverterService;

    @PostMapping("/save")
    UrlCode getShortUrl(@RequestBody UrlCode URLCode){
        return urlConverterService.saveUrl(URLCode);
    }

    @GetMapping("/page")
    Slice<UrlCode> getShortUrlsPage(@RequestParam String code,
                                    @RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size){
        int pageNo = (page == null || page < 0) ? 0 : page;
        int pageSize = (size == null || size < 0) ? 20 : size;
        return urlConverterService.pageUrlsByCode(code, PageRequest.of(pageNo, pageSize));
    }
}
