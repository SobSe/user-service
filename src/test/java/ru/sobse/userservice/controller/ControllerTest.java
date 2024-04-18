package ru.sobse.userservice.controller;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.util.Asserts;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.sobse.userservice.DTO.UserDto;
import ru.sobse.userservice.service.DefaultUserService;
import ru.sobse.userservice.service.UserService;

import java.util.Arrays;
import java.util.List;

public class ControllerTest {
    public UserService userService;

    @BeforeEach
    public void beforeEach() {
        userService = Mockito.mock(DefaultUserService.class);
    }

    @Test
    public void getUserTest() {
        //arrange
        List<UserDto> listOfUsers = Arrays.asList(
                new UserDto("admin"),
                new UserDto("user")
        );

        Controller controller = new Controller(userService);
        Mockito.when(userService.getUsers()).thenReturn(listOfUsers);
        //act
        List<UserDto> actual = controller.getUsers();
        //assert
        Assertions.assertEquals(listOfUsers, actual);
    }

    @Test
    public void adminPingTest() {
        //arrange
        Controller controller = new Controller(userService);
        String expect = "ADMIN OK";
        //act
        String actual = controller.adminPing();
        //assert
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void userPingTest() {
        //arrange
        Controller controller = new Controller(userService);
        String expect = "USER OK";
        //act
        String actual = controller.userPing();
        //assert
        Assertions.assertEquals(expect, actual);
    }
}
