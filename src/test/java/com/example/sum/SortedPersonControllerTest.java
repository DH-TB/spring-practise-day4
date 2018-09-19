package com.example.sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class SortedPersonControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(new SortPersonController()).build();
    }

    @Test
    void should_can_sort_person() throws Exception {
        ResultActions perform = mockMvc.perform(get("/people").param("nameSource", "huang lizhen, dou qingqing"));
        perform.andDo(print());
        perform.andExpect(status().isOk());

        perform.andExpect(jsonPath("$[0].id").value(1));
        perform.andExpect(jsonPath("$[0].name").value("DOU,QINGQING"));

        perform.andExpect(jsonPath("$[1].id").value(2));
        perform.andExpect(jsonPath("$[1].name").value("HUANG,LIZHEN"));
    }

    @Test
    void should_throw_exception_when_input_is_null() throws Exception {
        ResultActions perform = mockMvc.perform(get("/people").param("nameSource", (String) null));
        perform.andDo(print());
        perform.andExpect(status().is4xxClientError());
    }

    @Test
    void should_throw_exception_when_input_is_empty() throws Exception {
        ResultActions perform = mockMvc.perform(get("/people").param("nameSource", ""));
        perform.andDo(print());
        perform.andExpect(status().is4xxClientError());
    }
}
