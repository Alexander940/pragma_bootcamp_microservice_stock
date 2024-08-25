package com.pragma.emazon.infrastructure.input.rest;

import com.pragma.emazon.application.dto.CategoryRequest;
import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.application.dto.ItemRequest;
import com.pragma.emazon.application.dto.ItemResponse;
import com.pragma.emazon.application.handler.IItemHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemRestController {

    private final IItemHandler itemHandler;

    @Operation(summary = "Add a new item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Item name or description too long", content = @Content),
            @ApiResponse(responseCode = "400", description = "Item already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ItemResponse> saveCategory(@RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemHandler.saveItem(itemRequest);
        return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
    }
}
