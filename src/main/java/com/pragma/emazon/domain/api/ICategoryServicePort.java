package com.pragma.emazon.domain.api;

import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.domain.model.Category;

public interface ICategoryServicePort {
    CategoryResponse saveCategory(Category category);
}
