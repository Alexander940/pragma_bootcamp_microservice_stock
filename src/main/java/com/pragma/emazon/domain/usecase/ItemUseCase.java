package com.pragma.emazon.domain.usecase;

import com.pragma.emazon.domain.api.IItemServicePort;
import com.pragma.emazon.domain.exception.AssociatedAttributesNumberException;
import com.pragma.emazon.domain.exception.RepeatedAttributeException;
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
        if(item.getCategoriesName().length < 1 || item.getCategoriesName().length > 3){
            throw new AssociatedAttributesNumberException("categories", "1 to 3");
        }

        if(ArrayUtil.hasDuplicates(item.getCategoriesName())){
            throw new RepeatedAttributeException("categories");
        }

        return itemPersistencePort.saveItem(item);
    }
}
