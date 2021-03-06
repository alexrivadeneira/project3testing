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

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.error.ShouldHave.shouldHave;

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

        userRepository.save(firstUser);
        Long firstUserId = firstUser.getId();

        User secondUser = new User(
                "someone_else",
                "Someone",
                "Else"
        );

        secondUser = userRepository.save(secondUser);
        Long secondUserId = secondUser.getId();

//        Stream.of(firstUser, secondUser)
//                .forEach(user -> {
//                    userRepository.save(user);
//                });

        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:3000");

        $$("[data-user-display]").shouldHave(size(2));

        //test adding a user
        $("#new-user-userName").val("SonnyJim");
        $("#new-user-firstName").val("Sonny");
        $("#new-user-lastName").val("Jim");
        $("#new-user-submit").click();

        $$("[data-user-display").shouldHave(size(3));

        // test deleting a user
        $("#user-" + firstUserId).should(exist);
        $("#delete-user-" + firstUserId).click();
        $("#user-" + firstUserId).shouldNot(exist);
        $$("[data-user-display]").shouldHave(size(2));
    }

//    @Test
//    public void checkReactStarting(){
//        open("http://localhost:3000");
//        $("body").shouldHave(text("React"));
//    }


}
