package urishortener.repositories;



import org.springframework.data.repository.CrudRepository;
import urishortener.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);
}
