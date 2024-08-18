package com.pragma.emazon.domain.api;

import com.pragma.emazon.domain.model.Category;

public interface ICategoryServicePort {
    Category saveCategory(Category category);
    Category findCategoryByName(String name);
}
