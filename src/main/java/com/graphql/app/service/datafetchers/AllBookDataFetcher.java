package com.graphql.app.service.datafetchers;

import com.graphql.app.model.Book;
import com.graphql.app.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBookDataFetcher implements DataFetcher<List<Book>> {

   private BookRepository repository;

    public AllBookDataFetcher(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> get(DataFetchingEnvironment environment) {
        return repository.findAll();
    }
}
