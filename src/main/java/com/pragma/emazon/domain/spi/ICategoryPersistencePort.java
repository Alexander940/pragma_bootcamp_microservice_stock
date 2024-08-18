package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    Category findCategoryByName(String name);
    List<Category> findAllCategories();
}
