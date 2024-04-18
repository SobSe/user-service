package ru.sobse.userservice.service;

import ru.sobse.userservice.DTO.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> getUsers();
}
