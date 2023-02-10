package by.goncharov.kameleoonTask.repository;

import by.goncharov.kameleoonTask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
