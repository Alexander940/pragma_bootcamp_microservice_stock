package com.pragma.emazon.domain.api;

import com.pragma.emazon.domain.model.Category;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}
