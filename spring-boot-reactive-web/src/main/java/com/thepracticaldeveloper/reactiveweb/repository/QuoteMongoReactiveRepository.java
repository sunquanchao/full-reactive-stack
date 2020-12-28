package com.thepracticaldeveloper.reactiveweb.repository;

import com.thepracticaldeveloper.reactiveweb.domain.Quote;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import reactor.core.publisher.Flux;

public interface QuoteMongoReactiveRepository extends ReactiveSortingRepository<Quote, String> {

    Flux<Quote> findByContentOrderByIdAsc(final String content,final Pageable page);

	//@Query("{ 'content': ?0}")
    Flux<Quote> findByContent(final String content);

    @Query("{ 'content': ?0}")
    Flux<Quote> findByContentFuzzy(final String content);

    @Query("{ 'book': ?0, 'content': ?1}")
    Flux<Quote> findByContentAndBook(final String book,final String content);

    Flux<Quote> findAllByIdNotNullOrderByIdAsc(final Pageable page);


}
