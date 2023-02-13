package by.goncharov.kameleoontask.repository;

import by.goncharov.kameleoontask.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Optional<Quote> findById(Long id);
    List<Quote> findTop10ByOrderByVotesDesc();
    List<Quote> findTop10ByOrderByVotesAsc();
}
