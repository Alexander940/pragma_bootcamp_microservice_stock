package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.ItemRequest;
import com.pragma.emazon.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ItemRequestMapper {

    default Item toItem(ItemRequest itemRequest) {
        Item.Builder builder = new Item.Builder();

        builder.name(itemRequest.name())
                .description(itemRequest.description())
                .price(itemRequest.price())
                .quantity(itemRequest.quantity())
                .categoriesId(itemRequest.categoriesId().toArray(new Long[0]))
                .brandId(itemRequest.brandId());

        return builder.build();
    }
}
