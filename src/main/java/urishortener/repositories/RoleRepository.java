package urishortener.repositories;

import org.springframework.data.repository.CrudRepository;
import urishortener.entities.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
