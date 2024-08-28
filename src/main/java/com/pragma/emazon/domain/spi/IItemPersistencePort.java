package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.model.PageModel;

public interface IItemPersistencePort {
    Item saveItem(Item item);
    PageModel<Item> findAllItems(int page, int size, String sortDirection, String sortField);
}
