package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.application.mapper.CategoryRequestMapper;
import com.pragma.emazon.application.mapper.CategoryResponseMapper;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryServicePort.saveCategory(categoryRequestMapper.toCategory(categoryRequest));

        return categoryResponseMapper.toCategoryResponse(category);
    }
}
