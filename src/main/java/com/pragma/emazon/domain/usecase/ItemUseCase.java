package com.pragma.emazon.domain.usecase;

import com.pragma.emazon.domain.api.IItemServicePort;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.spi.IItemPersistencePort;
import com.pragma.emazon.domain.util.ArrayUtil;

public class ItemUseCase implements IItemServicePort {

    private final IItemPersistencePort itemPersistencePort;

    public ItemUseCase(IItemPersistencePort itemPersistencePort) {
        this.itemPersistencePort = itemPersistencePort;
    }

    @Override
    public Item saveItem(Item item) {
        if(item.getCategoriesId().length < 1 || item.getCategoriesId().length > 3){
            throw new IllegalArgumentException("Item must have at least one category");
        }

        if(ArrayUtil.assessEqualContent(item.getCategoriesId())){
            throw new IllegalArgumentException("Item cannot have the same category");
        }

        return itemPersistencePort.saveItem(item);
    }
}
