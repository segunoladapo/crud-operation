package com.cognizant.crud.Crud.Operation.controller;

import com.cognizant.crud.Crud.Operation.Controller.UserController;
import com.cognizant.crud.Crud.Operation.CrudOperationApplication;
import com.cognizant.crud.Crud.Operation.model.User;
import com.cognizant.crud.Crud.Operation.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ContextConfiguration(classes = CrudOperationApplication.class)
//@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserRepository mockRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void createUser() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setPassword("somesecrets");
        mockUser.setPassword("joe_bob@mail.com");
        User user = new User();
        user.setPassword("somesecrets");
        user.setEmail("joe_bob@mail.com");
        Mockito.when(mockRepository.save(user)).thenReturn(mockUser);
        this.mvc.perform(post("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
