package com.example.usersapi.repositories;

import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User firstUser = new User(
                "user_name",
                "some first name",
                "some last name"
        );

        User secondUser = new User(
                "second_user",
                "some other first name",
                "some other last name"
        );

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllUsers() {
        Iterable<User> usersFromDb = userRepository.findAll();
        long size = usersFromDb.spliterator().getExactSizeIfKnown();
        assertThat(size, is(2L));
    }


}