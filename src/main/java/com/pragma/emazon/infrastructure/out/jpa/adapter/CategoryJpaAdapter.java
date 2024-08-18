package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        return categoryEntityMapper.toCategory(categoryRepository.save(categoryEntity));
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryEntityMapper::toCategory)
                .orElse(null);
    }

    @Override
    public Page<Category> findAllCategories(Pageable pageable) {
        return categoryEntityMapper.toCategoriesPage(categoryRepository.findAll(pageable));
    }
}
