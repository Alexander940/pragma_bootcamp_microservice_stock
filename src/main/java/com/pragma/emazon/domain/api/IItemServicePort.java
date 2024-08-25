package com.pragma.emazon.domain.api;

import com.pragma.emazon.domain.model.Item;

public interface IItemServicePort {
    Item saveItem(Item item);
}
