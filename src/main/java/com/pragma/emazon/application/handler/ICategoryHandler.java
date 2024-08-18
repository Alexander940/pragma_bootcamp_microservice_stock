package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {
    CategoryResponse saveCategory(CategoryRequest category);
    List<CategoryResponse> findAllCategories();
}
