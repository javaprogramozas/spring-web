package hu.bearmaster.springtutorial.web.repository;

import hu.bearmaster.springtutorial.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
