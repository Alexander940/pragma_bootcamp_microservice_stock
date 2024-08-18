package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.application.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.application.mapper.CategoryRequestMapper;
import com.pragma.emazon.application.mapper.CategoryResponseMapper;
import com.pragma.emazon.application.util.CategoryHandlerUtil;
import com.pragma.emazon.domain.api.ICategoryServicePort;
import com.pragma.emazon.domain.model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) throws CategoryAlreadyExistsException, MandatoryParameterException, StringTooLongException{
        //This exception is thrown if the category name already exists
        if(categoryServicePort.findCategoryByName(categoryRequest.name()) != null){
            throw new CategoryAlreadyExistsException();
        }

        //This exception is thrown if the category description is empty
        if(categoryRequest.description().isEmpty()){
            throw new MandatoryParameterException();
        }

        //This exception is thrown if the category name or description is too long
        if(CategoryHandlerUtil.isCategoryNameTooLong(categoryRequest.name()) || CategoryHandlerUtil.isCategoryDescriptionTooLong(categoryRequest.description())){
            throw new StringTooLongException();
        }

        Category category = categoryServicePort.saveCategory(categoryRequestMapper.toCategory(categoryRequest));

        return categoryResponseMapper.toCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> findAllCategories(Pageable pageable) {
        return categoryResponseMapper.toCategoryResponsesPage(categoryServicePort.findAllCategories(pageable));
    }
}
