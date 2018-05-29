package com.sk.hw_1.message;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Hw1Application.class)
@WebAppConfiguration
public class MessagesControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testDigestmessage() throws Exception {
        mockMvc.perform(post("/messages")
                .content("{\"message\":\"foo\"}")
                .contentType(contentType))
                .andExpect(content().json(
                        "{\"digest\":\"2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae\"}"));
    }

    @Test
    public void messageFound() throws Exception {
        postMessage();
        mockMvc.perform(get("/messages/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae")
                .contentType(contentType))
                .andExpect(content().json(
                        "{\"message\":\"foo\"}"));
    }

    @Test
    public void messageNotFound() throws Exception {
        mockMvc.perform(get("/messages/abc")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }


    @Test public void postMessage() throws Exception {
        Message msg = new Message("foo");
        mockMvc.perform(post("/messages/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(msg)))
                .andExpect(status().is2xxSuccessful());
    }
}
