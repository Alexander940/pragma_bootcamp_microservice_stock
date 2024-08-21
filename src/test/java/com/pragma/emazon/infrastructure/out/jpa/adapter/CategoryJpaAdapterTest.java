package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.CategoryEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryJpaAdapterTest {

    private CategoryJpaAdapter categoryJpaAdapter;
    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryJpaAdapter = new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Test
    void when_saveCategory_is_called_and_returns_a_category() {
        Category category = new Category(1L, "name", "description");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "name", "description");

        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Category categoryResponse = categoryJpaAdapter.saveCategory(category);

        assertEquals(category, categoryResponse);
    }

    @Test
    void when_findCategoryByName_is_called_and_returns_a_category() {
        Category category = new Category(1L, "name", "description");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "name", "description");

        when(categoryRepository.findByName("name")).thenReturn(java.util.Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Category categoryResponse = categoryJpaAdapter.findCategoryByName("name");

        assertEquals(category, categoryResponse);
    }
}