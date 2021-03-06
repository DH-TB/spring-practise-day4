package com.thoughtworks.grads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grads.controller.UserController;
import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class UserControllerTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(new UserController()).build();
        UserStorage.clear();
    }

    @Test
    void should_get_users() throws Exception {
        UserStorage.add(new User(1, "huanglizhen"), new User(2, "douqingqing"));

        mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0]['id']").value(1))
                .andExpect(jsonPath("$[1]['id']").value(2))

                .andExpect(jsonPath("$[0]['name']").value("huanglizhen"))
                .andExpect(jsonPath("$[1]['name']").value("douqingqing"));
    }

    @Test
    void should_create_users() throws Exception {
        User user = new User(3, "huanglizhen");
        int originSize = UserStorage.getSize();

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("huanglizhen"));

        int currentSize = UserStorage.getSize();

        assertEquals(originSize + 1, currentSize);
    }

    @Test
    void should_update_users() throws Exception {
        User user = new User(1, "huanglizhen");
        UserStorage.add(user);

        User updatedUser = new User("douqing");

        mockMvc.perform(put("/api/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedUser)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("douqing"));

        User storageUser = UserStorage.getUserById(user.getId());

        assertEquals(storageUser.getName(), updatedUser.getName());
    }

    @Test
    void should_delete_user_by_id() throws Exception{
        User user = new User(1, "huanglizhen");
        UserStorage.add(user);

        int originSize = UserStorage.getSize();

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        int currentSize = UserStorage.getSize();

        assertEquals(originSize - 1, currentSize);
        assertNull(UserStorage.getUserById(user.getId()));
    }
}