package com.graphql.app.service;

import com.graphql.app.service.datafetchers.AllBookDataFetcher;
import com.graphql.app.service.datafetchers.SingleBookFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class GraphQLService {
    @Value("graphql/schema.graphqls")
    private ClassPathResource classPathResource;


    private GraphQL graphQL;

    private AllBookDataFetcher allBooksDataFetcher;

    private SingleBookFetcher singleBookFetcher;

    public GraphQLService(AllBookDataFetcher allBooksDataFetcher, SingleBookFetcher singleBookFetcher) {
        this.allBooksDataFetcher = allBooksDataFetcher;
        this.singleBookFetcher = singleBookFetcher;
    }

    @PostConstruct
    private void init() throws IOException{

        InputStream st = classPathResource.getInputStream();
        Reader targetReader = new InputStreamReader(st);

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(targetReader);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);

        graphQL = GraphQL.newGraphQL(schema).build();


    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring->typeWiring
                            .dataFetcher("allBooks", allBooksDataFetcher)
                            .dataFetcher("findById",singleBookFetcher)
                )
                .build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }


}
