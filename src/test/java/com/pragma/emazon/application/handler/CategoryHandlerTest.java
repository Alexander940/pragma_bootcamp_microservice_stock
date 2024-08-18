package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.application.mapper.CategoryRequestMapper;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CategoryHandlerTest {

    private CategoryHandler categoryHandler;
    @Mock
    private CategoryRequestMapper categoryRequestMapper;
    @Mock
    private ICategoryServicePort categoryServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryHandler = new CategoryHandler(categoryRequestMapper, categoryServicePort);
    }

    @Test
    void when_saveCategory_handler_is_called_and_return_an_CategoryResponse() {
        Category category = new Category(1L,"name", "description");
        CategoryRequest categoryRequest = new CategoryRequest("name", "description");

        when(categoryRequestMapper.toCategory(eq(categoryRequest))).thenReturn(category);
        when(categoryServicePort.saveCategory(eq(category))).thenReturn(new CategoryResponse("name", "description"));

        CategoryResponse categoryResponse = categoryHandler.saveCategory(categoryRequest);

        assertEquals(categoryRequest.name(), categoryResponse.name());

    }
}