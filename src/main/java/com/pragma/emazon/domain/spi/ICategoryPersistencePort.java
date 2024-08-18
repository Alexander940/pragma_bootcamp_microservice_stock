package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Category;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    Category findCategoryByName(String name);
}
