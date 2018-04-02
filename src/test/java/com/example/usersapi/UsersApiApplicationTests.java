package com.example.usersapi;

import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersApiApplicationTests {

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
	public void shouldAllowFullCrudForAUser() throws Exception{
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

		when()
				.get("http://localhost:8080/users")
				.then()
				.statusCode(is(200))
				.and().body(containsString("someone"))
				.and().body(containsString("Else"));
	}


}
