package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    Category findCategoryByName(String name);
    Page<Category> findAllCategories(Pageable pageable);
}
