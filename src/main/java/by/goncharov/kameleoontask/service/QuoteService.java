package by.goncharov.kameleoontask.service;

import by.goncharov.kameleoontask.dto.QuoteDTO;
import by.goncharov.kameleoontask.dto.VoteDTO;
import by.goncharov.kameleoontask.entity.Quote;

import java.util.List;

public interface QuoteService {
    Quote createQuote(QuoteDTO quoteDTO);
    List<Quote> getAllQuotes();
    List<Quote> getTop10Quotes();
    List<Quote> getWorst10Quotes();
    Quote getRandomQuote();
    Quote updateQuote(Long id, QuoteDTO quoteDTO);
    void deleteQuote(Long id);
    Quote downvoteQuote(Long id, VoteDTO voteDTO);
    
    Quote getQuoteById(Long id);

    Quote voteOnQuote(Long id, VoteDTO voteDTO);
}
