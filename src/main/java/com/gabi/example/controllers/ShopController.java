package com.gabi.example.controllers;

import com.gabi.example.repositories.ItemRepository;
import com.gabi.example.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {

    PeopleRepository peopleRepository;
    ItemRepository itemRepository;

    @Autowired
    public ShopController(PeopleRepository peopleRepository, ItemRepository itemRepository){
        this.peopleRepository = peopleRepository;
        this.itemRepository = itemRepository;
    }

    @RequestMapping("hi")
    String hello(Model model){
        return "Hello";
    }
}
