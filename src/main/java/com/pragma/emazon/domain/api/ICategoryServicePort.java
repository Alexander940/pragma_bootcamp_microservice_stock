package com.pragma.emazon.domain.api;

import com.pragma.emazon.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    Category saveCategory(Category category);
    Category findCategoryByName(String name);
    List<Category> findAllCategories();
}
