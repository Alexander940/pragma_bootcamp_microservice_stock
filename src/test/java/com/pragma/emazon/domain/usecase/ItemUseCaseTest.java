package com.pragma.emazon.domain.usecase;

import com.pragma.emazon.domain.exception.AssociatedAttributesNumberException;
import com.pragma.emazon.domain.exception.RepeatedAttributeException;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.spi.IItemPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ItemUseCaseTest {

    private ItemUseCase itemUseCase;
    @Mock
    private IItemPersistencePort itemPersistencePort;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            itemUseCase = new ItemUseCase(itemPersistencePort);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_saveItem_method_is_called_and_returns_an_Item() {
        Item.Builder builder = new Item.Builder();
        builder.name("name")
                .description("description")
                .price(1.0)
                .quantity(10)
                .categoriesId(new Long[]{1L})
                .brandId(1L);
        Item item = builder.build();

        when(itemPersistencePort.saveItem(item)).thenReturn(item);

        Item itemResponse = itemUseCase.saveItem(item);

        assertEquals(item, itemResponse);
    }

    @Test
    void when_saveItem_method_is_called_with_zero_category_ids_associated_and_throw_an_AssociatedAttributesNumberException() {
        Item.Builder builder = new Item.Builder();
        builder.name("name")
                .description("description")
                .price(1.0)
                .quantity(10)
                .categoriesId(new Long[]{})
                .brandId(1L);
        Item item = builder.build();

        assertThrows(AssociatedAttributesNumberException.class, () -> itemUseCase.saveItem(item));
    }

    @Test
    void when_saveItem_method_is_called_with_four_category_ids_associated_and_throw_an_AssociatedAttributesNumberException() {
        Item.Builder builder = new Item.Builder();
        builder.name("name")
                .description("description")
                .price(1.0)
                .quantity(10)
                .categoriesId(new Long[]{1L, 2L, 3L, 4L})
                .brandId(1L);
        Item item = builder.build();

        assertThrows(AssociatedAttributesNumberException.class, () -> itemUseCase.saveItem(item));
    }

    @Test
    void when_saveItem_method_is_called_with_category_ids_repeated_and_returns_a_RepeatedAttributeException() {
        Item.Builder builder = new Item.Builder();
        builder.name("name")
                .description("description")
                .price(1.0)
                .quantity(10)
                .categoriesId(new Long[]{1L, 2L, 2L})
                .brandId(1L);
        Item item = builder.build();

        assertThrows(RepeatedAttributeException.class, () -> itemUseCase.saveItem(item));
    }
}