package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import com.pragma.emazon.infrastructure.util.ListUtil;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ItemEntityMapper {

    ItemEntity toItemEntity(Item item);

    default Item toItem(ItemEntity itemEntity){
        Item.Builder builder = new Item.Builder();

        builder.id(itemEntity.getId())
                .name(itemEntity.getName())
                .description(itemEntity.getDescription())
                .price(itemEntity.getPrice())
                .quantity(itemEntity.getQuantity())
                .categories(ListUtil.toArray(itemEntity.getCategories().stream()
                        .map(categoryEntity -> new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription(), new Item[]{}))
                        .toList()))
                .brand(new Brand(itemEntity.getBrand().getId(), itemEntity.getBrand().getName(), itemEntity.getBrand().getDescription(), new Item[]{}));

        return builder.build();
    }
}
