package com.pragma.emazon.domain.usecase;

import com.pragma.emazon.domain.exception.ObjectAlreadyExistsException;
import com.pragma.emazon.domain.exception.MandatoryParameterException;
import com.pragma.emazon.domain.exception.StringTooLongException;
import com.pragma.emazon.application.util.StringUtil;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.PageModel;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
        //This exception is thrown if the category name already exists
        if(findCategoryByName(category.getName()) != null){
            throw new ObjectAlreadyExistsException(category, "name");
        }

        //This exception is thrown if the category description is empty or null
        if(category.getDescription().isEmpty() || category.getDescription() == null){
            throw new MandatoryParameterException("description");
        }

        //This exception is thrown if the category name long is up to 50 characters
        if(StringUtil.assessHigherLength(category.getName(), 50)){
            throw new StringTooLongException(category.getName(), 50);
        }

        //This exception is thrown if the category description long is up to 90 characters
        if(StringUtil.assessHigherLength(category.getDescription(), 90)){
            throw new StringTooLongException(category.getDescription(), 90);
        }

        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryPersistencePort.findCategoryByName(name);
    }

    @Override
    public PageModel<Category> findAllCategories(int page, int size, String sort) {
        return categoryPersistencePort.findAllCategories(page, size, sort);
    }
}
