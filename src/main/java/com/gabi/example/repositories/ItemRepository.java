package com.gabi.example.repositories;

import com.gabi.example.models.Category;
import com.gabi.example.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>{
    List<Item> findByIsPromoted(boolean isPromoted);
    List<Item> findByCategory(Category category);
    Page<Item>  findAllByCategory(Category category, Pageable pageable);
    Item findOneById(int id);
    boolean deleteItemById(int id);
}
