package com.pragma.emazon.infrastructure.input.rest;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.application.handler.IBrandHandler;
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
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Add a new Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Brand name or description too long", content = @Content),
            @ApiResponse(responseCode = "400", description = "Brand already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<BrandResponse> saveCategory(@RequestBody BrandRequest brandRequest) {
        BrandResponse brandResponse = brandHandler.saveBrand(brandRequest);
        return new ResponseEntity<>(brandResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brands have been successfully obtained", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server internal error", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<Page<BrandResponse>> findAllBrands(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Page<BrandResponse> brandResponses = brandHandler.findAllBrands(page, size, sort);
        return ResponseEntity.ok(brandResponses);
    }
}
