package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.infrastructure.util.ListUtil;
import lombok.Builder;
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
public interface CategoryEntityMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryEntity toEntity(Category category);

    default Category toCategory(CategoryEntity categoryEntity){
        Item [] items = ListUtil.toArray(categoryEntity.getItems().stream()
                .map(itemEntity -> {
                    Item.Builder builder = new Item.Builder();
                    builder.id(itemEntity.getId())
                            .name(itemEntity.getName())
                            .description(itemEntity.getDescription())
                            .price(itemEntity.getPrice())
                            .quantity(itemEntity.getQuantity());
                    return builder.build();
                })
                .toList());

        return new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription(), items);
    }

    default Page<Category> toCategoriesPage(Page<CategoryEntity> categoryEntities){
        List<Category> categories = categoryEntities
                .map(this::toCategory)
                .getContent();
        return new PageImpl<>(categories, categoryEntities.getPageable(), categoryEntities.getTotalElements());
    }
}
