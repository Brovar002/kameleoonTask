package by.goncharov.kameleoontask.service;

import by.goncharov.kameleoontask.dto.QuoteDTO;

import java.util.List;

public interface QuoteService {

    QuoteDTO createQuote(QuoteDTO quoteDTO);

    QuoteDTO updateQuote(Long id, QuoteDTO quoteDTO);

    void deleteQuote(Long id);

    QuoteDTO getQuote(Long id);

    QuoteDTO getRandomQuote();

    List<QuoteDTO> getTopTenQuotes();

    List<QuoteDTO> getWorstTenQuotes();

    QuoteDTO voteForQuote(Long id, boolean isUpvote);
}
