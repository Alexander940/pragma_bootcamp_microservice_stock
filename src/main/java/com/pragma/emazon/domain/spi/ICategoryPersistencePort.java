package com.pragma.emazon.domain.spi;

import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.domain.model.Category;

public interface ICategoryPersistencePort {
    CategoryResponse saveCategory(Category category);
}
