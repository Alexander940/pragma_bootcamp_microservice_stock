package com.pragma.emazon.application.usecase;

import com.pragma.emazon.application.exception.ObjectAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.application.util.CategoryUtil;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;

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

        //This exception is thrown if the category description is empty
        if(category.getDescription().isEmpty()){
            throw new MandatoryParameterException("description");
        }

        //This exception is thrown if the category name long is up to 50 characters
        if(CategoryUtil.isCategoryNameTooLong(category.getName())){
            throw new StringTooLongException(category.getName(), 50);
        }

        //This exception is thrown if the category description long is up to 90 characters
        if(CategoryUtil.isCategoryDescriptionTooLong(category.getDescription())){
            throw new StringTooLongException(category.getDescription(), 90);
        }

        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryPersistencePort.findCategoryByName(name);
    }
}
