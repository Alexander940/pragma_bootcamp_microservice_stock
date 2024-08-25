package com.pragma.emazon.domain.model;

public class Item {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private Long[] categoriesId;
    private Long brandId;
    private Category [] categories;
    private Brand brand;

    private Item(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.categoriesId = builder.categoriesId;
        this.brandId = builder.brandId;
        this.categories = builder.categories;
        this.brand = builder.brand;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private int quantity;
        private double price;
        private Long[] categoriesId;
        private Long brandId;
        private Category [] categories;
        private Brand brand;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder categoriesId(Long[] categoriesId) {
            this.categoriesId = categoriesId;
            return this;
        }

        public Builder brandId(Long brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder categories(Category [] categories) {
            this.categories = categories;
            return this;
        }

        public Builder brand(Brand brand) {
            this.brand = brand;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long[] getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long[] categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Category [] getCategories() {
        return categories;
    }

    public void setCategories(Category [] categories) {
        this.categories = categories;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
