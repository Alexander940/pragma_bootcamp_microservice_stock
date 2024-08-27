package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ItemEntityMapper {

    default ItemEntity toItemEntity(Item item){
        if (item == null) return null;

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setQuantity(item.getQuantity());

        BrandEntity brandEntity = new BrandEntity();

        if (item.getBrandId() != null){
            brandEntity.setId(item.getBrandId());

            itemEntity.setBrand(brandEntity);
        }

        List<CategoryEntity> categoryEntities = Arrays.stream(item.getCategoriesId()).map(categoryId -> {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(categoryId);
            return categoryEntity;
        }).toList();
        itemEntity.setCategories(categoryEntities);

        return itemEntity;
    }

    default Item toItem(ItemEntity itemEntity){
        if (itemEntity == null) return null;

        Item.Builder builder = new Item.Builder();

        builder.id(itemEntity.getId())
                .name(itemEntity.getName())
                .description(itemEntity.getDescription())
                .price(itemEntity.getPrice())
                .quantity(itemEntity.getQuantity())
                .categories(itemEntity.getCategories().stream()
                        .map(categoryEntity -> new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription(), new Item[]{}))
                        .toList()
                        .toArray(new Category[0]))
                .brand(new Brand(itemEntity.getBrand().getId(), itemEntity.getBrand().getName(), itemEntity.getBrand().getDescription(), new Item[]{}));

        return builder.build();
    }
}
