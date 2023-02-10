package by.goncharov.kameleoonTask.service.impl;

import by.goncharov.kameleoonTask.dto.QuoteDTO;
import by.goncharov.kameleoonTask.dto.VoteDTO;
import by.goncharov.kameleoonTask.entity.Quote;
import by.goncharov.kameleoonTask.entity.User;
import by.goncharov.kameleoonTask.entity.Vote;
import by.goncharov.kameleoonTask.entity.VoteType;
import by.goncharov.kameleoonTask.exception.BadRequestException;
import by.goncharov.kameleoonTask.exception.ResourceNotFoundException;
import by.goncharov.kameleoonTask.repository.QuoteRepository;
import by.goncharov.kameleoonTask.repository.UserRepository;
import by.goncharov.kameleoonTask.repository.VoteRepository;
import by.goncharov.kameleoonTask.service.QuoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public Vote upvoteQuote(Long quoteId, User user) {
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + quoteId));
        Vote vote = voteRepository.findByQuoteAndUser(quote, user);
        if (vote != null) {
            if (vote.getVoteType().equals(VoteType.UPVOTE)) {
                throw new BadRequestException("You have already upvoted this quote");
            }

            vote.setVoteType(VoteType.UPVOTE);

            return voteRepository.save(vote);
        }

        vote = new Vote();
        vote.setQuote(quote);
        vote.setUser(user);
        vote.setVoteType(VoteType.UPVOTE);

        return voteRepository.save(vote);
    }

    @Override
    public Vote downvoteQuote(Long quoteId, User user) {
        Quote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new ResourceNotFoundException("Quote not found with id " + quoteId));
        Vote vote = voteRepository.findByQuoteAndUser(quote, user);
        if (vote != null) {
            if (vote.getVoteType().equals(VoteType.DOWNVOTE)) {
                throw new BadRequestException("You have already downvoted this quote");
            }

            vote.setVoteType(VoteType.DOWNVOTE);

            return voteRepository.save(vote);
        }

        vote = new Vote();
        vote.setQuote(quote);
        vote.setUser(user);
        vote.setVoteType(VoteType.DOWNVOTE);

        return voteRepository.save(vote);
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