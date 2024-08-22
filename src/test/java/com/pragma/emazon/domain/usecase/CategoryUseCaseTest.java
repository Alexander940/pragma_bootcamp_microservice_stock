package com.pragma.emazon.domain.usecase;

import com.pragma.emazon.domain.exception.ObjectAlreadyExistsException;
import com.pragma.emazon.domain.exception.MandatoryParameterException;
import com.pragma.emazon.domain.exception.StringTooLongException;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.PageModel;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        Category category = new Category(1L, "name", "description");

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
    void when_saveCategory_useCase_is_called_and_the_category_name_already_exists() {
        Category category = new Category(1L,"name", "description");

        when(categoryPersistencePort.findCategoryByName(category.getName())).thenReturn(category);

        assertThrows(ObjectAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void when_findAllCategories_is_called_and_returns_a_PageModel() {
        when(categoryPersistencePort.findAllCategories(0, 10, "asc")).thenReturn(new PageModel.Builder<Category>().build());

        PageModel<Category> pageModelResponse = categoryUseCase.findAllCategories(0, 10, "asc");

        assertNotNull(pageModelResponse);
    }
}