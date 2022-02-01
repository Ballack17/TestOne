package urishortener.repositories;


import org.springframework.data.repository.CrudRepository;
import urishortener.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);
}
