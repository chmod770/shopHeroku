package com.gabi.example.controllers;

import com.gabi.example.models.Category;
import com.gabi.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/mainCategoriesNames")
    public List<String> getAllCategoriesNames(){
        List<Category> all = categoryRepository.findAll();
        List<String> categories=new ArrayList<>();
        for(Category category:all){
            if(category.getParentCategoryId()==null)
                categories.add(category.getName());
        }
        return categories;
    }
    @GetMapping("/all")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
