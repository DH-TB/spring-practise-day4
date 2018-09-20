package com.thoughtworks.grads;

import com.thoughtworks.grads.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class UserControllerTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(new UserController()).build();
    }

    @Test
    void should_get_users() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0]['id']").value(1))
                .andExpect(jsonPath("$[1]['id']").value(2))
                .andExpect(jsonPath("$[0]['name']").value("huanglizhen"))
                .andExpect(jsonPath("$[1]['name']").value("douqingqing"));
    }

    
}
