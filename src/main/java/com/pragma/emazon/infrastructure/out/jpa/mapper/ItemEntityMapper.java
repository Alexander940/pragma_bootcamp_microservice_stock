package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;

public interface ItemEntityMapper {

    ItemEntity toItemEntity(Item item);

    Item toItem(ItemEntity itemEntity);
}
