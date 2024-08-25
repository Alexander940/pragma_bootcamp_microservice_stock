package com.pragma.emazon.domain.model;

public class Category {
    private Long id;
    private String name;
    private String description;
    private Long [] itemsId;
    private Item [] items;

    public Category(Long id, String name, String description, Item[] items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long[] getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long[] itemsId) {
        this.itemsId = itemsId;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
