package com.gabi.example.controllers;

import com.gabi.example.models.Category;
import com.gabi.example.models.Item;
import com.gabi.example.repositories.CategoryRepository;
import com.gabi.example.repositories.ItemRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public boolean addItem(@RequestBody Item item){
        try{
            itemRepository.save(item);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @PostMapping("/addStick")
    public List<Item> addItem(){
        Category newCategory = new Category();
        newCategory.setName("Ubrania");
        categoryRepository.save(newCategory);
        Item newItem = new Item();
        newItem.setCategory(newCategory);
        newItem.setName("Koszulka");
        newItem.setDescription("Koszulka, rozmiar do wyboru dalej");
        itemRepository.save(newItem);
        return itemRepository.findAll();

    }

    @PostMapping(value = "/addImage/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("image") MultipartFile image, @PathVariable int id){
        String IMAGES_HOME_FOLDER = "C:\\xampp\\htdocs\\images\\";
        String IMAGES_HOME_SERVER = "http://127.0.0.1/images/";
        Optional<Item> optionalItem = itemRepository.findById(id);
        Item item;
        if(!optionalItem.isPresent())
            return new ResponseEntity<>("Error, bad id", HttpStatus.BAD_REQUEST);

        item = optionalItem.get();
        if(image.isEmpty())
            return new ResponseEntity<>("Error, bad id", HttpStatus.BAD_REQUEST);

        try {
            String pathFile;
            File convertFile;
            if(item.getImageLocation().equals("")){
                pathFile = item.hashCode()+"."+ FilenameUtils.getExtension(image.getOriginalFilename());
                convertFile = new File(IMAGES_HOME_FOLDER+pathFile);

                item.setImageLocation(IMAGES_HOME_SERVER+pathFile);
                itemRepository.save(item);
            }else{
                pathFile=item.getImageLocation();
                convertFile = new File(pathFile);
            }

            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(image.getBytes());
            fout.close();

        }catch (Exception ex){ex.printStackTrace();}
        return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
    }

    @GetMapping("/itemsWithCategoryId/{id}")
    public List<Item> getItemsWithCategoryId(@PathVariable int id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            return itemRepository.findByCategory(optionalCategory.get());
        }
        return null;
    }

}
