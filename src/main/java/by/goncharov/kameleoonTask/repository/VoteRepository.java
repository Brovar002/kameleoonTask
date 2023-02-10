package by.goncharov.kameleoonTask.repository;

import by.goncharov.kameleoonTask.entity.Quote;
import by.goncharov.kameleoonTask.entity.User;
import by.goncharov.kameleoonTask.entity.Vote;
import by.goncharov.kameleoonTask.entity.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByQuoteAndVoteType(Quote quote, VoteType voteType);
    Vote findByQuoteAndUser(Quote quote, User user);
}
