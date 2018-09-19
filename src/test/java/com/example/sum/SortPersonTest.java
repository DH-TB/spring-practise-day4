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

class SortPersonTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(new PersonController()).build();
    }

    @Test
    void should_can_sort_person_when_input_string_names() throws Exception {
        ResultActions perform = mockMvc.perform(get("/persons")
                .param("name", "dou qingqing,huang lizhen"));
        perform.andDo(print());
        perform.andExpect(status().isOk());

        perform.andExpect(jsonPath("$[0].id").value("1"));
        perform.andExpect(jsonPath("$[0].name").value("DOU,QINGQING"));

        perform.andExpect(jsonPath("$[1].id").value("2"));
        perform.andExpect(jsonPath("$[1].name").value("HUANG,LIZHEN"));
    }

    @Test
    void should_throw_exception_when_input_is_null() throws Exception {
        ResultActions perform = mockMvc.perform(get("/persons")
                .param("name", (String) null));
        perform.andDo(print());
        perform.andExpect(status().is4xxClientError());
    }

    @Test
    void should_throw_exception_when_input_is_empty() throws Exception {
        ResultActions perform = mockMvc.perform(get("/persons")
                .param("name", ""));
        perform.andDo(print());
        perform.andExpect(status().is4xxClientError());
    }


}
