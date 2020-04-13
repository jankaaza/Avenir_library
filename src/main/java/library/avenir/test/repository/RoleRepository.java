package library.avenir.test.repository;

import library.avenir.test.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import library.avenir.test.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}