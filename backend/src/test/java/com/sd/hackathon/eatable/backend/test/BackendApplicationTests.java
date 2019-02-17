package com.sd.hackathon.eatable.backend.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sd.hackathon.eatable.backend.document.UrlCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test the API and auto generate docs with Spring REST Doc (MockMVC)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests {

    @Value("${application.api.root}")
    private String apiRoot;

    @Value("${application.frontend.errorPageAddress}")
    private String errorPageAddress;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    /*
     MVC tests.
     Note: Database initiation required
      */

    /*
     End to end test for backend
     Testing saving a data unit and redirection for that unit
      */
    @Test
    public void urlTest() throws Exception{
        String mockRedirectionAddress = "http://dummy.com";
        UrlCode urlCode = new UrlCode();
        urlCode.setUrl(mockRedirectionAddress);

        urlCode = objectMapper.readValue(this.mockMvc.perform(post(apiRoot + "/save")
                .content(objectMapper.writeValueAsString(urlCode))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.url", is(mockRedirectionAddress)))
                .andExpect(jsonPath("$.code", notNullValue(UrlCode.class)))
                .andReturn().getResponse().getContentAsString(), UrlCode.class);
        this.mockMvc.perform(get( "/" + urlCode.getCode()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(mockRedirectionAddress))
                .andReturn();
    }

    // Testing  redirection for invalid unit
    @Test
    public void invalidCodeRedirectionTest() throws Exception{
        this.mockMvc.perform(get( "/0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andReturn();
    }

}

