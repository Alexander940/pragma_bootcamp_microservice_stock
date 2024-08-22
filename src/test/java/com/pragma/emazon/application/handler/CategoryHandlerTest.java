package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.application.mapper.CategoryRequestMapper;
import com.pragma.emazon.application.mapper.CategoryResponseMapper;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.PageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void when_findAllCategories_handler_is_called_and_returns_a_Page() {
        Page<CategoryResponse> categoryResponses = new PageImpl<>(List.of(), PageRequest.of(0, 10), 0);
        PageModel<Category> categoryPageModel = new PageModel.Builder<Category>().build();

        when(categoryServicePort.findAllCategories(0, 10, "asc")).thenReturn(categoryPageModel);
        when(categoryResponseMapper.toCategoryResponsePage(categoryPageModel)).thenReturn(categoryResponses);

        Page<CategoryResponse> categoryResponsesResponse = categoryHandler.findAllCategories(0, 10, "asc");

        assertEquals(categoryResponses, categoryResponsesResponse);
    }
}