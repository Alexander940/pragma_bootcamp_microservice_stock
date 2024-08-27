package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.spi.IItemPersistencePort;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.ItemEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemJpaAdapter implements IItemPersistencePort {

    private final IItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;

    public Item saveItem(Item item) {
        ItemEntity itemEntity = itemEntityMapper.toItemEntity(item);
        return itemEntityMapper.toItem(itemRepository.save(itemEntity));
    }
}
