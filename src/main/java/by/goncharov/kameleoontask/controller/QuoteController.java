package by.goncharov.kameleoontask.controller;

import by.goncharov.kameleoontask.dto.QuoteDTO;
import by.goncharov.kameleoontask.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public QuoteDTO createQuote(@Valid @RequestBody QuoteDTO quoteDTO) {
        return quoteService.createQuote(quoteDTO);
    }

    @PutMapping("/{id}")
    public QuoteDTO updateQuote(@PathVariable Long id, @Valid @RequestBody QuoteDTO quoteDTO) {
        return quoteService.updateQuote(id, quoteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public QuoteDTO getQuote(@PathVariable Long id) {
        return quoteService.getQuote(id);
    }

    @GetMapping("/random")
    public QuoteDTO getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @GetMapping("/top")
    public List<QuoteDTO> getTopTenQuotes() {
        return quoteService.getTopTenQuotes();
    }

    @GetMapping("/worst")
    public List<QuoteDTO> getWorstTenQuotes() {
        return quoteService.getWorstTenQuotes();
    }

    @PostMapping("/{id}/vote")
    public QuoteDTO voteForQuote(@PathVariable Long id, @RequestParam boolean isUpvote) {
        return quoteService.voteForQuote(id, isUpvote);
    }
}
