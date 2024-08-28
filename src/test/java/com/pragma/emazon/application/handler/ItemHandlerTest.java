package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.application.dto.ItemRequest;
import com.pragma.emazon.application.dto.ItemResponse;
import com.pragma.emazon.application.mapper.ItemRequestMapper;
import com.pragma.emazon.application.mapper.ItemResponseMapper;
import com.pragma.emazon.domain.api.IItemServicePort;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.model.PageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ItemHandlerTest {

    private ItemHandler itemHandler;
    @Mock
    private ItemRequestMapper itemRequestMapper;
    @Mock
    private ItemResponseMapper itemResponseMapper;
    @Mock
    private IItemServicePort itemServicePort;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            itemHandler = new ItemHandler(itemServicePort, itemRequestMapper, itemResponseMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_saveItem_method_is_called_and_returns_an_ItemResponse() {
        Item.Builder builder = new Item.Builder();
        builder.name("name")
                .description("description")
                .price(10.0)
                .quantity(10);
        Item item = builder.build();
        ItemRequest itemRequest = new ItemRequest("name", "description", 10, 10, List.of(), "brand");
        ItemResponse itemResponse = new ItemResponse("name", "description", 10, 10, List.of(), new BrandResponse("", "", List.of()));

        when(itemRequestMapper.toItem(itemRequest)).thenReturn(item);
        when(itemServicePort.saveItem(item)).thenReturn(item);
        when(itemResponseMapper.toItemResponse(item)).thenReturn(itemResponse);

        ItemResponse itemResponses = itemHandler.saveItem(itemRequest);

        assertEquals(itemResponse, itemResponses);
    }

    @Test
    void when_findAllItems_method_is_called_and_returns_a_Page() {
        Page<ItemResponse> itemResponsePage = new PageImpl<>(List.of(new ItemResponse("name", "description", 10, 10, List.of(), new BrandResponse("", "", List.of()))));
        PageModel<Item> itemPageModel = new PageModel.Builder<Item>().build();

        when(itemServicePort.findAllItems(0, 10, "asc", "name")).thenReturn(itemPageModel);
        when(itemResponseMapper.toItemResponsePage(itemPageModel)).thenReturn(itemResponsePage);

        Page<ItemResponse> itemResponses = itemHandler.findAllItems(0, 10, "asc", "name");

        assertEquals(itemResponsePage, itemResponses);
    }
}