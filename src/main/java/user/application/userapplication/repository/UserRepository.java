package user.application.userapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.application.userapplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}