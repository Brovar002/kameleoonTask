package by.goncharov.kameleoontask.repository;

import by.goncharov.kameleoontask.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findById(Long id);

    Optional<Vote> findByQuoteId(Long id);
}
