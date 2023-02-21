package com.graphql.app.controller;

import com.graphql.app.model.Book;
import com.graphql.app.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLBookController {

    private final BookRepository repository;


    public GraphQLBookController(BookRepository repository) {
        this.repository = repository;
    }

    //@SchemaMapping(typeName = "Query", value = "allBooks")
    @QueryMapping
    public List<Book> allBooks(){
        return this.repository.findAll();
    }


    @QueryMapping
    public Optional<Book> findById(@Argument Long id){
        return this.repository.findById(id);
    }





























}
