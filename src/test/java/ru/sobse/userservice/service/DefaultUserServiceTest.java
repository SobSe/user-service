package ru.sobse.userservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sobse.userservice.DTO.UserDto;
import ru.sobse.userservice.entity.Role;
import ru.sobse.userservice.entity.ServiceUser;
import ru.sobse.userservice.repositoy.UsersRepository;

import java.util.Arrays;
import java.util.List;

public class DefaultUserServiceTest {
    public UsersRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = Mockito.mock(UsersRepository.class);
    }

    @Test
    public void getUsersTest() {
        //arrange
        List<ServiceUser> listOfUsers = Arrays.asList(
                new ServiceUser(1,
                        "admin",
                        "password",
                        Arrays.asList(new Role(1, "ADMIN"))),
                new ServiceUser(2,
                        "user",
                        "password",
                        Arrays.asList(new Role(1, "USER")))
        );
        List<UserDto> listOfUsersDto = Arrays.asList(
                new UserDto("admin"),
                new UserDto("user")
        );
        DefaultUserService userService = new DefaultUserService(repository);
        Mockito.when(repository.getAllBy()).thenReturn(listOfUsers);
        //act
        List<UserDto> actual = userService.getUsers();
        //assert
        Assertions.assertEquals(listOfUsersDto, actual);
    }
}
