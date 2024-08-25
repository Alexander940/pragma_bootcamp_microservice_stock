package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.ItemRequest;
import com.pragma.emazon.application.dto.ItemResponse;
import com.pragma.emazon.application.mapper.ItemRequestMapper;
import com.pragma.emazon.application.mapper.ItemResponseMapper;
import com.pragma.emazon.domain.api.IItemServicePort;
import com.pragma.emazon.domain.model.Item;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemHandler implements IItemHandler{

    private final IItemServicePort itemServicePort;
    private final ItemRequestMapper itemRequestMapper;
    private final ItemResponseMapper itemResponseMapper;

    @Override
    public ItemResponse saveItem(ItemRequest itemRequest) {
        Item item = itemServicePort.saveItem(itemRequestMapper.toItem(itemRequest));
        return itemResponseMapper.toItemResponse(item);
    }
}
