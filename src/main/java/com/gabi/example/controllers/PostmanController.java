package com.gabi.example.controllers;

import com.gabi.example.models.Item;
import com.gabi.example.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/postman/items")
public class PostmanController {

    @Autowired
    ItemRepository itemRepository;

    @DeleteMapping("/delete/{id}")
    public boolean deleteItemWithId(@PathVariable int id){
        try{
            itemRepository.delete(itemRepository.findOneById(id));
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @PostMapping("/add")
    public boolean addItem(@RequestBody Item item){
        try{
            itemRepository.save(item);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

}
