package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryResponseMapper {

    Category toCategory(CategoryResponse categoryResponse);

    CategoryResponse toCategoryResponse(Category category);
}
