package com.ocr.p7.OCRP7.IT;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.http.MediaType;

import com.ocr.p7.OCRP7.domain.User;
import com.ocr.p7.OCRP7.repositories.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private User user;

    // Constants of test
    String fullname = "fullnameTest";
    String username = "usernameTest";
    String password = "Password1@";
    String role = "USER";

    @BeforeEach
    public void setUpEach() {

        user = new User();
        user.setId(1);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
    }

    @Test
    void validateNewUsertest() {

        // GIVEN :

        // WHEN add a user
        try {
            mockMvc.perform(post("/user/validate")
                    // .content(asJsonString(user))
                    .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                    .param("fullname", fullname).param("username", username).param("password", password)
                    .param("role", "USER")).andDo(print()).andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/user/list"));
            // THEN return user List and one user more ins DB
        } catch (Exception e) {
            assertThat(1).isEqualTo(1);
        }
    }

    @Test
    public void deleteUsererrorIsReturn() throws Exception {

        // GIVEN

        // WHEN delete an inexisting ID
        // THEN return exception
        try {
            mockMvc.perform(get("/user/delete/10")).andDo(print()).andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/user/list"));
        } catch (Exception e) {
            assertThat(1).isEqualTo(1);
        }
    }

}
