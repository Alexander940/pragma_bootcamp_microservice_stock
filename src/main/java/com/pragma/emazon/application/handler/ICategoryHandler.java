package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;

public interface ICategoryHandler {
    CategoryResponse saveCategory(CategoryRequest category);
}
