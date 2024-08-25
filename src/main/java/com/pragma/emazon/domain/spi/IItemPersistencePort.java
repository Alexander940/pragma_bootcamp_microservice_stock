package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Item;

public interface IItemPersistencePort {
    Item saveItem(Item item);
}
