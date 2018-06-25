package com.example.search.controller;

import com.example.search.component.SearchComponent;
import com.example.search.component.SearchQuery;
import com.example.search.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchComponent searchComponent;

    @Autowired
    public SearchController(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    List<Flight> search(@RequestBody SearchQuery query) {
        System.out.println("Input : " + query);
        return searchComponent.search(query);
    }
}
