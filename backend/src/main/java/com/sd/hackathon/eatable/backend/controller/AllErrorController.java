package com.sd.hackathon.eatable.backend.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@CrossOrigin
public class AllErrorController implements ErrorController {

    @Value("${application.frontend.errorPageAddress}")
    private String errorPageAddress;

    @RequestMapping("/error")
    public RedirectView handleError() {
        LoggerFactory.getLogger(this.getClass()).error("Error encountered");

        String redirectUrlString = errorPageAddress;
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        return redirectView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
