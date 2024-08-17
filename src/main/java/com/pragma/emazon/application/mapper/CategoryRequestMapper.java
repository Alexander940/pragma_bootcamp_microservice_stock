package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {
    Category toCategory(CategoryRequest category);
}
