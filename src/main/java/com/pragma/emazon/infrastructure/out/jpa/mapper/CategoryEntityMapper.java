package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CategoryEntityMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    public abstract CategoryEntity toEntity(Category category);

    public abstract Category toCategory(CategoryEntity categoryEntity);

    public Page<Category> toCategoriesPage(Page<CategoryEntity> categoryEntities){
        List<Category> categories = categoryEntities
                .map(this::toCategory)
                .getContent();
        return new PageImpl<>(categories, PageRequest.of(categoryEntities.getNumber(), categoryEntities.getSize()), categoryEntities.getTotalElements());
    }
}
