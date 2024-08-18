package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CategoryResponseMapper {

    public abstract CategoryResponse toCategoryResponse(Category category);

    public Page<CategoryResponse> toCategoryResponsesPage(Page<Category> categories){
        List<CategoryResponse> categoryResponses = categories
                .map(this::toCategoryResponse)
                .getContent();
        return new PageImpl<>(categoryResponses, categories.getPageable(), categories.getTotalElements());
    }
}
