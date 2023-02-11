package by.goncharov.kameleoontask.repository;

import by.goncharov.kameleoontask.entity.Quote;
import by.goncharov.kameleoontask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findAllByOrderByCreatedAtDesc();
    List<Quote> findTop10ByOrderByCreatedAtDesc();
    List<Quote> findTop10ByOrderByCreatedAtAsc();
    List<Quote> findByUser(User user);
}
