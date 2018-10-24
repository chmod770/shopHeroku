package com.gabi.example.controllers;

import com.gabi.example.models.Item;
import com.gabi.example.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/all/{pageNumber}")
    public Page<Item> getPage(@PathVariable int pageNumber){
        Page<Item> page = itemRepository.findAll(new PageRequest(pageNumber-1,9));

        return page;
    }

}
