package com.graphql.app.controller;

import com.graphql.app.model.Book;
import com.graphql.app.repository.BookRepository;
import com.graphql.app.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {

    private final BookRepository repository;

    private final GraphQLService graphQLService;

    public BookController(BookRepository repository, GraphQLService graphQLService) {
        this.repository = repository;
        this.graphQLService = graphQLService;
    }

    @GetMapping("/loadall")
    public String addBook(){

        List<Book> books= Arrays.asList(
                new Book(null,"book1","auther1","date1"),
                new Book(null,"book2","auther2","date2"),
                new Book(null,"book3","auther3","date3"),
                new Book(null,"book4","auther4","date4"),
                new Book(null,"book5","auther5","date5"),
                new Book(null,"book6","auther6","date6"),
                new Book(null,"book7","auther7","date7"),
                new Book(null,"book8","auther8","date8"),
                new Book(null,"book9","auther9","date9"),
                new Book(null,"book10","auther10","date10")

        );
        repository.saveAll(books);

        return "Dummy Data added to h2: use /getall or use /rungraph for testing";
    }


    @GetMapping("/getall")
    public List<Book> getAllBooks(){
        return repository.findAll();
    }


    @GetMapping("/rungraph")
    public ResponseEntity<Object> graphQLrun(@RequestBody String query){
        ExecutionResult result = graphQLService.getGraphQL().execute(query);

        return ResponseEntity.ok(result);


        // result contains details so you can manually get details using .getData and map to Object according to needs...
//        Map<String,List<Book>> finalResult = result.getData();
//
//       return  finalResult.get("allBooks");

    }

}
