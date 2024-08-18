package com.pragma.emazon.domain.usecase;

import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {
    private ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryPersistencePort.findCategoryByName(name);
    }


}
