package com.gabi.example.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    Integer parentCategoryId;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Item> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public List<Item> getItems() {
        return items;
    }*/

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }
}
