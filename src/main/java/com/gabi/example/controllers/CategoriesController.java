package com.gabi.example.controllers;

import com.gabi.example.models.Category;
import com.gabi.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = {"http://86.111.102.219","https://cryptic-escarpment-38413.herokuapp.com"},allowedHeaders = "*")//no access without this
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
    @PostMapping("/add/{nameCategory}")
    public Category addCategory(@PathVariable String nameCategory){
        Category category = new Category();
        category.setName(nameCategory);
        categoryRepository.save(category);

        return category;
    }
}
