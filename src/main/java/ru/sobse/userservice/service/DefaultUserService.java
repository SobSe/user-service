package ru.sobse.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobse.userservice.DTO.UserDto;
import ru.sobse.userservice.entity.ServiceUser;
import ru.sobse.userservice.repositoy.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService{
    private final UsersRepository usersRepository;

    @Override
    public List<UserDto> getUsers() {
        return usersRepository.getAllBy()
                .stream()
                .map(ServiceUser::getName)
                .map(UserDto::new)
                .toList();
    }
}
