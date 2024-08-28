package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.ItemRequest;
import com.pragma.emazon.application.dto.ItemResponse;
import org.springframework.data.domain.Page;

public interface IItemHandler {
    ItemResponse saveItem(ItemRequest itemRequest);
    Page<ItemResponse> findAllItems(int page, int size, String sortDirection, String sortField);
}
