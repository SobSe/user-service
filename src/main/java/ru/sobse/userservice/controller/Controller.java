package ru.sobse.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobse.userservice.DTO.UserDto;
import ru.sobse.userservice.service.UserService;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class Controller {
    private final UserService service;

    @GetMapping("admin/get-users")
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @GetMapping("admin/ping")
    public String adminPing() {
        return "ADMIN OK";
    }

    @GetMapping("user/ping")
    public String userPing() {
        return "USER OK";
    }
}
