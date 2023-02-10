package by.goncharov.kameleoonTask.repository;

import by.goncharov.kameleoonTask.entity.Quote;
import by.goncharov.kameleoonTask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findAllByOrderByCreatedAtDesc();
    List<Quote> findTop10ByOrderByCreatedAtDesc();
    List<Quote> findTop10ByOrderByCreatedAtAsc();
    List<Quote> findByUser(User user);
}
