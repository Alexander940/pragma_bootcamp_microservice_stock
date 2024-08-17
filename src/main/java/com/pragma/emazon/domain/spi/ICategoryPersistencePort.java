package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
}
