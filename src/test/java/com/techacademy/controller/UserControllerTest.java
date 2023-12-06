package com.techacademy.controller;

import com.techacademy.entity.User;
import com.techacademy.repository.UserRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    UserControllerTest(WebApplicationContext context) {
        this.webApplicationContext = context;
    }

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }

    @Test
    @DisplayName("User一覧画面")
    @WithMockUser
    void testGetList() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userlist"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("user/list"))
                .andReturn();

         List<User> userList = (List<User>) result.getModelAndView().getModel().get("userlist");
         assertEquals(userList.size(), 3);

         User user1 = userList.get(0);
         assertEquals(user1.getId(), 1);
         assertEquals(user1.getName(), "キラメキ太郎");

        User user2 = userList.get(1);
        assertEquals(user2.getId(), 2);
        assertEquals(user2.getName(), "キラメキ次郎");

        User user3 = userList.get(2);
        assertEquals(user3.getId(), 3);
        assertEquals(user3.getName(), "キラメキ花子");
    }

    @Test
    @DisplayName("User更新画面")
    @WithMockUser
    void testGetUser() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/update/1/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("user/update"))
                .andReturn();

        User user = (User)result.getModelAndView().getModel().get("user");
        assertEquals(user.getId(), 1);
        assertEquals(user.getName(), "キラメキ太郎");
    }
}
