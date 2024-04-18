package ru.sobse.userservice.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.sobse.userservice.entity.Role;
import ru.sobse.userservice.entity.ServiceUser;
import ru.sobse.userservice.repositoy.UsersRepository;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/sql/users_roles.sql")
public class UserRepositoryIT {
    @Autowired
    UsersRepository usersRepository;

    @Test
    public void getUsersTest() {
        //arrange
        List<ServiceUser> expectedUsers = Arrays.asList(
                new ServiceUser(1, "admin", "{noop}password", Arrays.asList(
                        new Role(1, "ROLE_ADMIN")
                )),
                new ServiceUser(2, "user", "{noop}password", Arrays.asList(
                        new Role(2, "ROLE_USER")
                )),
                new ServiceUser(3, "usertest", "{noop}password", Arrays.asList(
                        new Role(2, "ROLE_USER")
                ))
        );
        //act
        List<ServiceUser> actualUsers = usersRepository.getAllBy();
        //assert
        Assertions.assertEquals(expectedUsers, actualUsers);
    }
}
