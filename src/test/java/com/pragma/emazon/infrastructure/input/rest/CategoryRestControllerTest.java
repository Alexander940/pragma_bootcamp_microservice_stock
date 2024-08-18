package com.pragma.emazon.infrastructure.input.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.infrastructure.input.rest.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoryRestControllerTest {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void when_creates_category_returns_200() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("name", "description");

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtil.mapToJson(categoryRequest)))
                .andReturn();

        assertEquals(201, mockMvcResult.getResponse().getStatus());
    }

    @Test
    void when_creates_category_and_name_is_too_long_returns_400() throws Exception {
        String name = "a".repeat(51);
        CategoryRequest categoryRequest = new CategoryRequest(name, "description");

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtil.mapToJson(categoryRequest)))
                .andReturn();

        assertEquals(400, mockMvcResult.getResponse().getStatus());
    }
}