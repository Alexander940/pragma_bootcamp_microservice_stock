package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.PageModel;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.mapper.PageAdapterMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PageAdapterMapper pageAdapterMapper;

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
    public PageModel<Category> findAllCategories(int page, int size, String sort) {
        Sort.Direction sortDirection = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        return pageAdapterMapper.toPageModel(categoryEntityMapper.toCategoriesPage(categoryRepository.findAll(pageable)));
    }
}
