package by.goncharov.kameleoontask.service.impl;

import by.goncharov.kameleoontask.dto.QuoteDTO;
import by.goncharov.kameleoontask.entity.Quote;
import by.goncharov.kameleoontask.entity.User;
import by.goncharov.kameleoontask.entity.Vote;
import by.goncharov.kameleoontask.exception.ResourceNotFoundException;
import by.goncharov.kameleoontask.repository.QuoteRepository;
import by.goncharov.kameleoontask.repository.UserRepository;
import by.goncharov.kameleoontask.repository.VoteRepository;
import by.goncharov.kameleoontask.service.QuoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public QuoteDTO createQuote(QuoteDTO quoteDTO) {
        User user = userRepository.findById(quoteDTO.getUserId()).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + quoteDTO.getUserId());
        }
        Quote quote = new Quote();
        quote.setContent(quoteDTO.getContent());
        quote.setCreatedAt(LocalDateTime.now());
        quote.setUser(user);
        quote = quoteRepository.save(quote);
        return mapQuoteToQuoteDTO(quote);
    }

    @Override
    public QuoteDTO updateQuote(Long id, QuoteDTO quoteDTO) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote == null) {
            throw new ResourceNotFoundException("Quote not found with id " + id);
        }
        quote.setContent(quoteDTO.getContent());
        quote.setUpdatedAt(LocalDateTime.now());
        User user = userRepository.findById(quoteDTO.getUserId()).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + quoteDTO.getUserId());
        }
        quote.setUser(user);
        quote = quoteRepository.save(quote);
        return mapQuoteToQuoteDTO(quote);
    }

    @Override
    public void deleteQuote(Long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote == null) {
            throw new ResourceNotFoundException("Quote not found with id " + id);
        }
        quoteRepository.delete(quote);
    }

    @Override
    public QuoteDTO getQuote(Long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote == null) {
            throw new ResourceNotFoundException("Quote not found with id " + id);
        }
        return mapQuoteToQuoteDTO(quote);
    }

    @Override
    public QuoteDTO getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        if (quotes.isEmpty()) {
            throw new ResourceNotFoundException("No quotes found");
        }
        int randomIndex = (int) (Math.random() * quotes.size());
        Quote quote = quotes.get(randomIndex);
        return mapQuoteToQuoteDTO(quote);
    }

    @Override
    public List<QuoteDTO> getTopTenQuotes() {
        List<Quote> quotes = quoteRepository.findTop10ByOrderByVotesDesc();
        return quotes.stream().map(this::mapQuoteToQuoteDTO).collect(Collectors.toList());
    }

    @Override
    public List<QuoteDTO> getWorstTenQuotes() {
        List<Quote> quotes = quoteRepository.findTop10ByOrderByVotesAsc();
        return quotes.stream().map(this::mapQuoteToQuoteDTO).collect(Collectors.toList());
    }

    @Override
    public QuoteDTO voteForQuote(Long id, boolean isUpvote) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote == null) {
            throw new ResourceNotFoundException("Quote not found with id " + id);
        }
        Vote vote = voteRepository.findByQuoteId(id).orElse(null);
        if (vote == null) {
            vote = new Vote();
            vote.setQuote(quote);
            vote.setDownvotes(0);
            vote.setUpvotes(0);
        }
        if (isUpvote) {
            vote.setUpvotes(vote.getUpvotes() + 1);
        } else {
            vote.setDownvotes(vote.getDownvotes() + 1);
        }
        vote = voteRepository.save(vote);
        quote.setVotes(vote.getUpvotes() - vote.getDownvotes());
        quote = quoteRepository.save(quote);
        return mapQuoteToQuoteDTO(quote);
    }

    private QuoteDTO mapQuoteToQuoteDTO (Quote quote) {
        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.setId(quote.getId());
        quoteDTO.setContent(quote.getContent());
        quoteDTO.setCreatedAt(quote.getCreatedAt());
        quoteDTO.setUpdatedAt(quote.getUpdatedAt());
        quoteDTO.setUserId(quote.getUser().getId());
        quoteDTO.setVotes(quote.getVotes());
        return quoteDTO;
    }
}
