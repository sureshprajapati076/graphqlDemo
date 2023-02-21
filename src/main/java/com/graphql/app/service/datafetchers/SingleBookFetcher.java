package com.graphql.app.service.datafetchers;

import com.graphql.app.model.Book;
import com.graphql.app.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class SingleBookFetcher implements DataFetcher<Book> {

    private BookRepository repository;

    public SingleBookFetcher(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book get(DataFetchingEnvironment environment) {
        Long id = Long.valueOf(environment.getArgument("id"));
        return repository.findById(id).get();
    }
}
