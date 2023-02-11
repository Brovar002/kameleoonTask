package by.goncharov.kameleoontask.service.impl;

import by.goncharov.kameleoontask.dto.QuoteDTO;
import by.goncharov.kameleoontask.dto.VoteDTO;
import by.goncharov.kameleoontask.entity.Quote;
import by.goncharov.kameleoontask.entity.User;
import by.goncharov.kameleoontask.exception.ResourceNotFoundException;
import by.goncharov.kameleoontask.repository.QuoteRepository;
import by.goncharov.kameleoontask.repository.UserRepository;
import by.goncharov.kameleoontask.service.QuoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + id));
    }

    @Override
    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        int randomIndex = (int) (Math.random() * quotes.size());
        return quotes.get(randomIndex);
    }

    @Override
    public Quote createQuote(QuoteDTO quoteDTO) {
        User user = userRepository.findById(quoteDTO.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + quoteDTO.getUser().getId()));
        Quote quote = new Quote();
        quote.setContent(quoteDTO.getContent());
        quote.setUser(user);
        quote.setCreatedAt(LocalDateTime.now());
        quote.setUpdatedAt(LocalDateTime.now());
        return quoteRepository.save(quote);
    }

    @Override
    public Quote updateQuote(Long id, QuoteDTO quoteDTO) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + id));
        quote.setContent(quoteDTO.getContent());
        quote.setUpdatedAt(LocalDateTime.now());
        return quoteRepository.save(quote);
    }
    @Override

    public void deleteQuote(Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + id));
        quoteRepository.delete(quote);
    }

    @Override
    public Quote voteOnQuote(Long id, VoteDTO voteDTO) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + id));
        int vote = voteDTO.getVote();
        quote.setVotes(quote.getVotes() + vote);
        return quoteRepository.save(quote);
    }

    public Quote downvoteQuote(Long id, VoteDTO voteDTO) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + id));
        int vote = voteDTO.getVote();
        quote.setVotes(quote.getVotes() - vote);
        return quoteRepository.save(quote);
    }

    @Override
    public List<Quote> getTop10Quotes() {
        return quoteRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Override
    public List<Quote> getWorst10Quotes() {
        return quoteRepository.findTop10ByOrderByCreatedAtAsc();
    }

}