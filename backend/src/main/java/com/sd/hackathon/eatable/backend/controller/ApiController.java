package com.sd.hackathon.eatable.backend.controller;

import com.sd.hackathon.eatable.backend.document.UrlCode;
import com.sd.hackathon.eatable.backend.service.urlconverter.UrlConverterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping("${application.api.root}")
@CrossOrigin
public class ApiController {

    @Value("${application.api.root}")
    private String apiRoot;

    @Autowired
    private UrlConverterService urlConverterService;

    @PostMapping("/save")
    ResponseEntity<UrlCode> getShortUrl(@RequestBody UrlCode urlCode){
        // check for malformed urls
        try {
            new URL(urlCode.getUrl()).toURI();
            int temp = urlCode.getUrl().split("://").length;
            if(temp != 2) throw new Exception("Invalid protocol syntax");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(urlCode);
        }

        // checking for duplicates
        UrlCode existing = urlConverterService.getCode(urlCode.getUrl());
        if(existing != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(existing);
        }

        urlCode = urlConverterService.saveUrl(urlCode);
        return ResponseEntity.created(URI.create(apiRoot + "/" + urlCode.getCode()))
                .body(urlCode);

    }

    @GetMapping("/page")
    Slice<UrlCode> getShortUrlsPage(@RequestParam(required = false) String code,
                                    @RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size){
        String pageCode = (code == null) ? "" : code;
        int pageNo = (page == null || page < 0) ? 0 : page;
        int pageSize = (size == null || size < 0) ? 20 : size;
        return urlConverterService.pageUrlsByCode(pageCode, PageRequest.of(pageNo, pageSize));
    }
}
