package com.pragma.emazon.domain.api;

import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryServicePort {
    Category saveCategory(Category category);
    Category findCategoryByName(String name);
    PageModel<Category> findAllCategories(int page, int size, String sort);
}
