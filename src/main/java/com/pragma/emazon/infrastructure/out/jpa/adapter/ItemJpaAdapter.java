package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.model.PageModel;
import com.pragma.emazon.domain.spi.IItemPersistencePort;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.ItemEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.mapper.PageAdapterMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class ItemJpaAdapter implements IItemPersistencePort {

    private final IItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;
    private final PageAdapterMapper pageAdapterMapper;

    @Override
    public Item saveItem(Item item) {
        ItemEntity itemEntity = itemEntityMapper.toItemEntity(item);
        return itemEntityMapper.toItem(itemRepository.save(itemEntity));
    }

    @Override
    public PageModel<Item> findAllItems(int page, int size, String sortDirection, String sortField) {
        Sort.Direction sort = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort, sortField));
        return pageAdapterMapper.toPageModel(itemEntityMapper.toItemsPage(itemRepository.findAll(pageable)));
    }
}
