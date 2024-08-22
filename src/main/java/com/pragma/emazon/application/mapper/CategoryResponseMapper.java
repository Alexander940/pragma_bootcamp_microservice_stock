package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.PageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryResponseMapper {

    CategoryResponse toCategoryResponse(Category category);

    default Page<CategoryResponse> toCategoryResponsePage(Page<Category> categories){
        List<CategoryResponse> categoryResponses = categories
                .map(this::toCategoryResponse)
                .getContent();
        return new PageImpl<>(categoryResponses, categories.getPageable(), categories.getTotalElements());
    }

    default Page<CategoryResponse> toCategoryResponsePage(PageModel<Category> pageModel){
        List<CategoryResponse> categoryResponses = pageModel
                .getContent()
                .stream()
                .map(this::toCategoryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(categoryResponses, PageRequest.of(pageModel.getPageNumber(), pageModel.getPageSize()), pageModel.getTotalElements());
    }
}
