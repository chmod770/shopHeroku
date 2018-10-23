package com.gabi.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {

    PeopleRepository peopleRepository;

    @Autowired
    public ShopController(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    @RequestMapping("hi")
    String hello(Model model){
        return "Hello";
    }
}
