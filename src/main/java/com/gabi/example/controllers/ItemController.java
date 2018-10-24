package com.gabi.example.controllers;

import com.gabi.example.models.Category;
import com.gabi.example.models.Item;
import com.gabi.example.repositories.CategoryRepository;
import com.gabi.example.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/all")
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @GetMapping("/all/{pageNumber}")
    public Page<Item> getPage(@PathVariable int pageNumber){
        Page<Item> page = itemRepository.findAll(new PageRequest(pageNumber-1,9));

        return page;
    }

    @GetMapping("/promotedItems/{maxNumberOfPromotedItems}")
    public List<Item> getPromotedItems(@PathVariable int maxNumberOfPromotedItems){
        List<Item> promotedItems = itemRepository.findByIsPromoted(true);
        List<Item> newPromotedItems = new ArrayList<>();
        for (int i = 0; i<promotedItems.size()&&i<maxNumberOfPromotedItems; i++) {
            newPromotedItems.add(promotedItems.get(i));
        }
        return newPromotedItems;
    }

    @PostMapping("/add")
    public List<Item> addItem(/*@RequestBody Item item*/){
        Category newCategory = new Category();
        newCategory.setName("Ubrania");
        categoryRepository.save(newCategory);
        Item newItem = new Item();
        newItem.setCategory(newCategory);
        newItem.setName("Koszulka");
        newItem.setName("Koszulka, rozmiar do wyboru dalej");
        itemRepository.save(newItem);
        return itemRepository.findAll();

    }

}
