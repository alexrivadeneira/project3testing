package com.example.usersapi.controllers;

import com.example.usersapi.controllers.UsersController;
import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository mockUserRepository;

    @Before
    public void setUp() {
        User firstUser = new User(
                "someone",
                "Ima",
                "Person"
        );

        User secondUser = new User(
                "someone_else",
                "Someone",
                "Else"
        );

        Iterable<User> mockUsers =
                Stream.of(firstUser, secondUser).collect(Collectors.toList());


        given(mockUserRepository.findAll()).willReturn(mockUsers);
        given(mockUserRepository.findById(1L)).willReturn(java.util.Optional.ofNullable(firstUser));
        given(mockUserRepository.findById(4L)).willReturn(null);

    }

    @Test
    public void findAllUsers_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_success_returnUserNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].userName", is("someone")));
    }

    @Test
    public void findAllUsers_success_returnFirstNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].firstName", is("Ima")));
    }

    @Test
    public void findAllUsers_success_returnLastNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].lastName", is("Person")));
    }

    @Test
    public void findUserById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserById_success_returnUserName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.userName", is("someone")));
    }

    @Test
    public void findUserById_success_returnFirstName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.firstName", is("Ima")));
    }

    @Test
    public void findUserById_success_returnLastName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.lastName", is("Person")));
    }

}