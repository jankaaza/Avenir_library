package library.avenir.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import library.avenir.test.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUserNameOrEmail(String username, String email);
    Optional<User> findByUserName(String username);
}