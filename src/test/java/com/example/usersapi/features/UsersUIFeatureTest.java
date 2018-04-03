package com.example.usersapi.features;

import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersUIFeatureTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        userRepository.deleteAll();
    }

    @After
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {
        System.setProperty("selenide.browser", "Chrome");

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

        Stream.of(firstUser, secondUser)
                .forEach(user -> {
                    userRepository.save(user);
                });

    }

    @Test
    public void checkReactStarting(){
        open("http://localhost:3000");
        $("body").shouldHave(text("react"));
    }
}
