package com.pragma.emazon.infrastructure.input.rest;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.application.handler.IBrandHandler;
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
}
