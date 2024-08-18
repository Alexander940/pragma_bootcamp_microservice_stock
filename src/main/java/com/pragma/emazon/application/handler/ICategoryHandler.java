package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryHandler {
    CategoryResponse saveCategory(CategoryRequest category);
    Page<CategoryResponse> findAllCategories(Pageable pageable);
}
