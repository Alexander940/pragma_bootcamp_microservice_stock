package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.PageModel;
import org.springframework.data.domain.Pageable;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    Category findCategoryByName(String name);
    PageModel<Category> findAllCategories(int page, int size, String sort);
}
