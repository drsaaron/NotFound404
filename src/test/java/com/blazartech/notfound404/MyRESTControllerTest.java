/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.blazartech.notfound404;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import tools.jackson.databind.ObjectMapper;

/**
 *
 * @author aar1069
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(MyRESTController.class)
@Slf4j
public class MyRESTControllerTest {

    @TestConfiguration
    public static class MyRESTControllerTestConfiguration {

    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public MyRESTControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getPersons method, of class MyRESTController.
     */
    @Test
    public void testGetPersons_found() throws Exception {
        log.info("getPerson_found");

        int id = 1;

        MvcResult result = mockMvc
                .perform(
                        get("/v1/person/{id}", id)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        Person p = objectMapper.readValue(responseJson, Person.class);

        assertNotNull(p);
        assertEquals("Scott", p.getName());

    }

    @Test
    public void testGetPersons_notFound() throws Exception {
        log.info("getPerson_notFound");

        int id = 5;

        MvcResult result = mockMvc
                .perform(
                        get("/v1/person/{id}", id)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        assertNotNull(responseJson);
        assertEquals(0, responseJson.length());

    }
}
