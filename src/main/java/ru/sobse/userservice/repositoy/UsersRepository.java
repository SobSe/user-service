package ru.sobse.userservice.repositoy;

import org.springframework.data.repository.CrudRepository;
import ru.sobse.userservice.entity.ServiceUser;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<ServiceUser, Integer> {
    Optional<ServiceUser> getUsersByName(String login);

    List<ServiceUser> getAllBy();
}
