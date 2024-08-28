package com.pragma.emazon.infrastructure.input.rest;

import com.pragma.emazon.application.dto.*;
import com.pragma.emazon.application.handler.IItemHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Get all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item have been successfully obtained", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server internal error", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<Page<ItemResponse>> findAllBrands(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String sortD,
            @RequestParam(defaultValue = "name") String sortF
    ) {
        Page<ItemResponse> itemResponses = itemHandler.findAllItems(page, size, sortD, sortF);
        return new ResponseEntity<>(itemResponses, HttpStatus.OK);
    }
}
