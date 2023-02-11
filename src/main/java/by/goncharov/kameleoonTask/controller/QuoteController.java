package by.goncharov.kameleoonTask.controller;

import by.goncharov.kameleoonTask.dto.QuoteDTO;
import by.goncharov.kameleoonTask.dto.VoteDTO;
import by.goncharov.kameleoonTask.entity.Quote;
import by.goncharov.kameleoonTask.entity.Vote;
import by.goncharov.kameleoonTask.service.QuoteService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        List<Quote> quotes = quoteService.getAllQuotes();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        Quote quote = quoteService.getQuoteById(id);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @GetMapping("/quotes/random")
    public ResponseEntity<Quote> getRandomQuote() {
        Quote quote = quoteService.getRandomQuote();
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @PostMapping("/quotes")
    public ResponseEntity<Quote> createQuote(@RequestBody QuoteDTO quoteDTO) {
        Quote quote = quoteService.createQuote(quoteDTO);
        return new ResponseEntity<>(quote, HttpStatus.CREATED);
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody QuoteDTO quoteDTO) {
        Quote quote = quoteService.updateQuote(id, quoteDTO);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/quotes/{id}/vote")
    public ResponseEntity<Quote> voteOnQuote(@PathVariable Long id, @RequestBody VoteDTO voteDTO) {
        Quote quote = quoteService.voteOnQuote(id, voteDTO);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @PostMapping("/quotes/{id}/downvote")
    public ResponseEntity<Quote> downvoteQuote(@PathVariable Long id, @RequestBody VoteDTO voteDTO) {
        Quote quote = quoteService.downvoteQuote(id, voteDTO);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @GetMapping("/quotes/top10")
    public ResponseEntity<List<Quote>> getTop10Quotes() {
        List<Quote> quotes = quoteService.getTop10Quotes();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/worst10")
    public ResponseEntity<List<Quote>> getWorst10Quotes() {
        List<Quote> quotes = quoteService.getWorst10Quotes();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }
}
