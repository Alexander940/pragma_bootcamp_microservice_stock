package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.application.mapper.CategoryResponseMapper;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.infrastructure.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public CategoryResponse saveCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        CategoryEntity categoryEntity = categoryRepository.save(categoryEntityMapper.toEntity(category));

        return categoryResponseMapper.toCategoryResponse(categoryEntityMapper.toCategory(categoryEntity));
    }
}
