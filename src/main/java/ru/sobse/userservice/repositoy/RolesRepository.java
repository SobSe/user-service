package ru.sobse.userservice.repositoy;

import org.springframework.data.repository.CrudRepository;
import ru.sobse.userservice.entity.Role;

public interface RolesRepository extends CrudRepository<Role, Integer>{
}
