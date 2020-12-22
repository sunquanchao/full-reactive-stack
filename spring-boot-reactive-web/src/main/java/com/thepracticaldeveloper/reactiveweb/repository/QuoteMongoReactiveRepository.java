package com.thepracticaldeveloper.reactiveweb.repository;

import com.thepracticaldeveloper.reactiveweb.domain.Quote;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import reactor.core.publisher.Flux;

public interface QuoteMongoReactiveRepository extends ReactiveSortingRepository<Quote, String> {

    Flux<Quote> findByContentOrderByIdAsc(final String content,final Pageable page);

    Flux<Quote> findByContent(final String content);


    Flux<Quote> findAllByIdNotNullOrderByIdAsc(final Pageable page);


}
