package by.goncharov.kameleoonTask.service;

import by.goncharov.kameleoonTask.dto.QuoteDTO;
import by.goncharov.kameleoonTask.dto.VoteDTO;
import by.goncharov.kameleoonTask.entity.Quote;
import by.goncharov.kameleoonTask.entity.User;
import by.goncharov.kameleoonTask.entity.Vote;

import java.util.List;

public interface QuoteService {
    Quote createQuote(QuoteDTO quoteDTO);
    List<Quote> getAllQuotes();
    List<Quote> getTop10Quotes();
    List<Quote> getWorst10Quotes();
    Quote getRandomQuote();
    Quote updateQuote(Long id, QuoteDTO quoteDTO);
    void deleteQuote(Long id);
    Vote upvoteQuote(Long quoteId, User user);
    Vote downvoteQuote(Long quoteId, User user);
    
    Quote getQuoteById(Long id);

    Quote voteOnQuote(Long id, VoteDTO voteDTO);
}
