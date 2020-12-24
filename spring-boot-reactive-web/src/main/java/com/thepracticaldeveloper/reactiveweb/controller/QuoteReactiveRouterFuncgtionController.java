package com.thepracticaldeveloper.reactiveweb.controller;

import com.thepracticaldeveloper.reactiveweb.domain.Quote;
import com.thepracticaldeveloper.reactiveweb.repository.QuoteMongoReactiveRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.DispatcherHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/router")
public class QuoteReactiveRouterFuncgtionController {

    private static final int DELAY_PER_ITEM_MS = 100*10;

    private final QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    public QuoteReactiveRouterFuncgtionController(final QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @GetMapping("/quotes-reactive")
    public Flux<Quote> getQuoteFlux() {

		// DispatcherServlet /DispatcherHandler
        return quoteMongoReactiveRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

    @GetMapping("/quotes-reactive-paged")
    public Flux<Quote> getQuoteFlux(final @RequestParam(name = "page") int page,
                                    final @RequestParam(name = "size") int size) {
        return quoteMongoReactiveRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }


	@GetMapping("/quotes-reactive-paged-by-content")
	public Flux<Quote> getQuoteFlux(final @RequestParam(name = "content") String content,
									final @RequestParam(name = "page") int page,
									final @RequestParam(name = "size") int size) {
		return quoteMongoReactiveRepository.findByContentOrderByIdAsc(content,PageRequest.of(page, size))
				.delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
	}

	@GetMapping("/quotes-reactive-by-content")
	public Flux<Quote> getQuoteFlux(final @RequestParam(name = "content") String content) {
		return quoteMongoReactiveRepository.findByContent(content)
				.delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
	}

	@GetMapping("/quotes-reactive-by-book-content")
	public Flux<Quote> getQuoteFlux(
			final @RequestParam(name = "book") String book,
			final @RequestParam(name = "content") String content) {
		return quoteMongoReactiveRepository.findByContentAndBook(book,content)
				.delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
	}

	@PostMapping("/quotes-reactive-add")
	public Mono<Quote> addQuoteFlux(final @RequestParam(name = "book") String book,
									final @RequestParam(name = "content") String content) {
		Quote quote = new Quote();
		quote.setBook(book);
		quote.setContent(content);
//		Flow.Publisher
		return quoteMongoReactiveRepository.save(quote);
	}

}
