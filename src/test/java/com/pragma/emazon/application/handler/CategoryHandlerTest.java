package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.application.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.application.mapper.CategoryRequestMapper;
import com.pragma.emazon.application.mapper.CategoryResponseMapper;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CategoryHandlerTest {

    private CategoryHandler categoryHandler;
    @Mock
    private CategoryRequestMapper categoryRequestMapper;
    @Mock
    private CategoryResponseMapper categoryResponseMapper;
    @Mock
    private ICategoryServicePort categoryServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryHandler = new CategoryHandler(categoryRequestMapper, categoryResponseMapper, categoryServicePort);
    }

    @Test
    void when_saveCategory_handler_is_called_and_return_an_CategoryResponse() {
        Category category = new Category(1L,"name", "description");
        CategoryRequest categoryRequest = new CategoryRequest("name", "description");

        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        when(categoryServicePort.saveCategory(category)).thenReturn(category);
        when(categoryResponseMapper.toCategoryResponse(category)).thenReturn(new CategoryResponse("name", "description"));

        CategoryResponse categoryResponse = categoryHandler.saveCategory(categoryRequest);

        assertEquals(categoryRequest.name(), categoryResponse.name());
    }

    @Test
    void when_saveCategory_handler_is_called_and_name_is_too_long() {
        String name = "a".repeat(51);
        CategoryRequest categoryRequest = new CategoryRequest(name, "description");

        assertThrows(StringTooLongException.class, () -> categoryHandler.saveCategory(categoryRequest));
    }

    @Test
    void when_saveCategory_handler_is_called_and_description_is_too_long() {
        String description = "a".repeat(91);
        CategoryRequest categoryRequest = new CategoryRequest("name", description);

        assertThrows(StringTooLongException.class, () -> categoryHandler.saveCategory(categoryRequest));
    }

    @Test
    void when_saveCategory_handler_is_called_and_description_is_missing() {
        CategoryRequest categoryRequest = new CategoryRequest("name", "");

        assertThrows(MandatoryParameterException.class, () -> categoryHandler.saveCategory(categoryRequest));
    }

    @Test
    void when_saveCategory_handler_is_called_and_the_category_name_already_exists() {
        Category category = new Category(1L,"name", "description");
        CategoryRequest categoryRequest = new CategoryRequest("name", "description");

        when(categoryServicePort.findCategoryByName(categoryRequest.name())).thenReturn(category);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryHandler.saveCategory(categoryRequest));
    }

    @Test
    void when_findAllCategories_is_called_and_its_paginated_in_page_size_one() {
        Pageable pageable = PageRequest.of(0, 1);
        Category category = new Category(1L, "name", "description");
        Page<Category> categoryPage = new PageImpl<>(Collections.singletonList(category));

        CategoryResponse categoryResponse = new CategoryResponse("name", "description");
        Page<CategoryResponse> categoryResponsePage = new PageImpl<>(Collections.singletonList(categoryResponse));

        when(categoryServicePort.findAllCategories(pageable)).thenReturn(categoryPage);
        when(categoryResponseMapper.toCategoryResponsesPage(categoryPage)).thenReturn(categoryResponsePage);

        Page<CategoryResponse> result = categoryHandler.findAllCategories(pageable);
        
        assertEquals(categoryResponsePage, result);
    }

    @Test
    void when_findAllCategories_is_called_and_categories_are_sort_asc_by_name() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "name"));
        Category category1 = new Category(1L, "name1", "description1");
        Category category2 = new Category(2L, "name2", "description2");
        Page<Category> categoryPage = new PageImpl<>(Arrays.asList(category1, category2));

        CategoryResponse categoryResponse1 = new CategoryResponse("name1", "description1");
        CategoryResponse categoryResponse2 = new CategoryResponse("name2", "description2");
        Page<CategoryResponse> categoryResponsePage = new PageImpl<>(Arrays.asList(categoryResponse1, categoryResponse2));

        when(categoryServicePort.findAllCategories(pageable)).thenReturn(categoryPage);
        when(categoryResponseMapper.toCategoryResponsesPage(categoryPage)).thenReturn(categoryResponsePage);

        Page<CategoryResponse> result = categoryHandler.findAllCategories(pageable);

        assertEquals(categoryResponsePage, result);
    }
}