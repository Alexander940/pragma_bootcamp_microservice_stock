package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.ItemRequest;
import com.pragma.emazon.application.dto.ItemResponse;

public interface IItemHandler {
    ItemResponse saveItem(ItemRequest itemRequest);
}
