package com.pragma.emazon.application.usecase;

import com.pragma.emazon.application.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.application.util.CategoryUtil;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
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
            throw new CategoryAlreadyExistsException();
        }

        //This exception is thrown if the category description is empty
        if(category.getDescription().isEmpty()){
            throw new MandatoryParameterException();
        }

        //This exception is thrown if the category name or description is too long
        if(CategoryUtil.isCategoryNameTooLong(category.getName()) || CategoryUtil.isCategoryDescriptionTooLong(category.getDescription())){
            throw new StringTooLongException();
        }

        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryPersistencePort.findCategoryByName(name);
    }
}
