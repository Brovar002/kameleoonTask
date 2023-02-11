package by.goncharov.kameleoontask.repository;

import by.goncharov.kameleoontask.entity.Quote;
import by.goncharov.kameleoontask.entity.User;
import by.goncharov.kameleoontask.entity.Vote;
import by.goncharov.kameleoontask.entity.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByQuoteAndVoteType(Quote quote, VoteType voteType);
    Vote findByQuoteAndUser(Quote quote, User user);
}
