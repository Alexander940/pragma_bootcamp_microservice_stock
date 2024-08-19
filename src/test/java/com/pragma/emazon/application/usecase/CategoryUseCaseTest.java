package com.pragma.emazon.application.usecase;

import com.pragma.emazon.application.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryUseCaseTest {

    private CategoryUseCase categoryUseCase;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @Test
    void when_saveCategory_useCase_is_called_and_return_an_CategoryResponse() {
        Category category = new Category(1L,"name", "description");

        when(categoryPersistencePort.saveCategory(category)).thenReturn(category);

        Category categoryResponse = categoryUseCase.saveCategory(category);

        assertEquals(category.getName(), categoryResponse.getName());
    }

    @Test
    void when_saveCategory_useCase_is_called_and_name_is_too_long() {
        String name = "a".repeat(51);
        Category category = new Category(1L, name,"description");

        assertThrows(StringTooLongException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void when_saveCategory_useCase_is_called_and_description_is_too_long() {
        String description = "a".repeat(91);
        Category category = new Category(1L, "name", description);

        assertThrows(StringTooLongException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void when_saveCategory_useCase_is_called_and_description_is_missing() {
        Category category = new Category(1L, "name", "");

        assertThrows(MandatoryParameterException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void when_saveCategory_handler_is_called_and_the_category_name_already_exists() {
        Category category = new Category(1L,"name", "description");

        when(categoryPersistencePort.findCategoryByName(category.getName())).thenReturn(category);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void when_findAllCategories_is_called_and_its_paginated_in_page_size_one() {
        Pageable pageable = PageRequest.of(0, 1);
        Category category = new Category(1L, "name", "description");
        Page<Category> categoryPage = new PageImpl<>(Collections.singletonList(category));

        when(categoryPersistencePort.findAllCategories(pageable)).thenReturn(categoryPage);

        Page<Category> result = categoryUseCase.findAllCategories(pageable);

        assertEquals(categoryPage, result);
    }

    @Test
    void when_findAllCategories_is_called_and_categories_are_sort_asc_by_name() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "name"));
        Category category1 = new Category(1L, "name1", "description1");
        Category category2 = new Category(2L, "name2", "description2");
        Page<Category> categoryPage = new PageImpl<>(Arrays.asList(category1, category2));

        when(categoryPersistencePort.findAllCategories(pageable)).thenReturn(categoryPage);

        Page<Category> result = categoryUseCase.findAllCategories(pageable);

        assertEquals(categoryPage, result);
    }
}