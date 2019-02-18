package com.sd.hackathon.eatable.backend.controller;

import com.sd.hackathon.eatable.backend.document.UrlCode;
import com.sd.hackathon.eatable.backend.service.urlconverter.UrlConverterService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@CrossOrigin
public class RedirectionController {

    @Autowired
    private UrlConverterService urlConverterService;

    @GetMapping("/{code}")
    public RedirectView redirectUrl(@PathVariable String code) {
        UrlCode urlCode = urlConverterService.getUrl(code);
        String redirectUrlString;
        // if invalid code is found, redirect to error page
        if(urlCode != null){
            redirectUrlString = urlCode.getUrl();
            urlCode.setHitCount(urlCode.getHitCount() + 1);
            urlConverterService.updateUrl(urlCode);
        }else{
            redirectUrlString = "/error";
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        LoggerFactory.getLogger(this.getClass()).info("Redirecting to : " + redirectUrlString);
        return redirectView;
    }
}

