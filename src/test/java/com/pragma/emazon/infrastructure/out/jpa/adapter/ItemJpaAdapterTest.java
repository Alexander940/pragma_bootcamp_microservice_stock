package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.ItemEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.mapper.PageAdapterMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ItemJpaAdapterTest {

    private ItemJpaAdapter itemJpaAdapter;
    @Mock
    private IItemRepository itemRepository;
    @Mock
    private ItemEntityMapper itemEntityMapper;
    @Mock
    private PageAdapterMapper pageAdapterMapper;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            itemJpaAdapter = new ItemJpaAdapter(itemRepository, itemEntityMapper, pageAdapterMapper);
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
                .categoriesName(new String[]{})
                .brandName("brand1");
        Item item = builder.build();
        ItemEntity itemEntity = new ItemEntity(1L, "name", "description", 10,1.0, null, null);

        when(itemEntityMapper.toItemEntity(item)).thenReturn(itemEntity);
        when(itemRepository.save(itemEntity)).thenReturn(itemEntity);
        when(itemEntityMapper.toItem(itemEntity)).thenReturn(item);

        Item itemResponse = itemJpaAdapter.saveItem(item);

        assertEquals(item, itemResponse);
    }
}