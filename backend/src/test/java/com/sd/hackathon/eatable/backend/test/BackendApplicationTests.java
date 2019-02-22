package com.sd.hackathon.eatable.backend.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sd.hackathon.eatable.backend.document.UrlCode;
import com.sd.hackathon.eatable.backend.service.urlconverter.UrlConverterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @MockBean
    private UrlConverterService urlConverterService;

    private MockMvc mockMvc;

    private UrlCode tempUrlCode;

    // initializing mocking
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        when(urlConverterService.saveUrl(ArgumentMatchers.any(UrlCode.class))).thenAnswer(
                (invocation) -> {
                    tempUrlCode = invocation.getArgument(0);
                    tempUrlCode.setHitCount(0L);
                    tempUrlCode.setCode("ABC");
                    return tempUrlCode;
                }
        );
        when(urlConverterService.getUrl(ArgumentMatchers.anyString())).thenAnswer(
                (invocation) -> {
                    if(invocation.getArgument(0).equals("ABC"))
                        return tempUrlCode;
                    else
                        return null;
                }
        );
        when(urlConverterService.updateUrl(ArgumentMatchers.any(UrlCode.class))).thenAnswer(
                (invocation) -> {
                    if(((UrlCode)invocation.getArgument(0)).getCode().equals("ABC")){
                        tempUrlCode = invocation.getArgument(0);
                        return tempUrlCode;
                    } else
                        return null;
                }
        );
    }
    /*
     Mocked test for backend
     Testing saving a data unit and redirection for that unit
      */
    @Test
    public void urlTest() throws Exception {
        String mockRedirectionAddress = "http://0.com";
        UrlCode urlCode = new UrlCode();
        urlCode.setUrl(mockRedirectionAddress);

        urlCode = objectMapper.readValue(this.mockMvc.perform(post(apiRoot + "/save")
                .content(objectMapper.writeValueAsString(urlCode))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.url", is(mockRedirectionAddress)))
                .andExpect(jsonPath("$.code", notNullValue()))
                .andReturn().getResponse().getContentAsString(), UrlCode.class);
        this.mockMvc.perform(get("/" + urlCode.getCode()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(mockRedirectionAddress))
                .andReturn();
    }

    // Testing  redirection for invalid unit
    @Test
    public void invalidCodeRedirectionTest() throws Exception {
        this.mockMvc.perform(get("/0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andReturn();
    }

}

