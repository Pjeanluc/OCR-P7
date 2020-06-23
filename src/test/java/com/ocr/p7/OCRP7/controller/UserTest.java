package com.ocr.p7.OCRP7.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ocr.p7.OCRP7.domain.User;
import com.ocr.p7.OCRP7.repositories.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getAllUserControllerTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/user/list").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postValidateUserTest() throws Exception {

        // GIVEN
        User user = new User();
        user.setUsername("TestUserName");
        user.setPassword("TestPassword@1");
        user.setFullname("TestFullName");
        user.setRole("USER");
        Optional<User> userMock = Optional.of(user);

        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                post("/user/validate").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("password", user.getPassword()).param("fullname", user.getFullname())
                        .param("role", user.getRole()).param("username", user.getUsername())

        ).andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void postValidateUserWithErrorTest() throws Exception {

        // GIVEN
        User user = new User();
        user.setUsername("TestUserName");
        user.setPassword("TestPassworderror");
        user.setFullname("TestFullName");
        user.setRole("USER");
        Optional<User> userMock = Optional.of(user);

        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/user/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("password", user.getPassword())
                        .param("fullname", user.getFullname()).param("role", user.getRole())
                        .param("username", user.getUsername()))
                .andDo(print()).andExpect(status().isOk()).andExpect(view().name("user/add"));
    }

    @Test
    public void postUpdateUserTest() throws Exception {

        // GIVEN
        User user = new User();
        user.setUsername("TestUserName");
        user.setPassword("TestPassword@1");
        user.setFullname("TestFullName");
        user.setRole("USER");
        Optional<User> userMock = Optional.of(user);

        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                post("/user/update/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("password", user.getPassword()).param("fullname", user.getFullname())
                        .param("role", user.getRole()).param("username", user.getUsername())

        ).andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void postUpdateUserWithWrongParametersTest() throws Exception {

        // GIVEN
        User user = new User();
        user.setUsername("TestUserName");
        user.setPassword("TestPasswordWrong");
        user.setFullname("TestFullName");
        user.setRole("USER");
        Optional<User> userMock = Optional.of(user);

        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/user/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("password", user.getPassword())
                        .param("fullname", user.getFullname()).param("role", user.getRole())
                        .param("username", user.getUsername()))
                .andExpect(MockMvcResultMatchers.redirectedUrl(null)).andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    @Test
    public void getUserAddTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/user/add").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUpdateUserTest() throws Exception {

        // GIVEN
        User user = new User();
        user.setUsername("TestUserName");
        user.setPassword("TestPassword@1");
        user.setFullname("TestFullName");
        user.setRole("USER");
        Optional<User> userMock = Optional.of(user);

        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/user/update/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getDeleteUserTest() throws Exception {

        // GIVEN
        User user = new User();
        user.setUsername("TestUserName");
        user.setPassword("TestPassword@1");
        user.setFullname("TestFullName");
        user.setRole("USER");
        Optional<User> userMock = Optional.of(user);

        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/user/delete/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/list")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void getDeleteUserNoExistingTest() throws Exception {

        // GIVEN
        User user = null;
        Optional<User> userMock = Optional.ofNullable(user);

        Mockito.when(userRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        try {
            this.mockMvc.perform(
                    get("/user/delete/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertThat(e).hasMessageContaining("Invalid user Id:1");
        }
    }

}
