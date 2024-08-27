package com.pragma.emazon.application.dto;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.Item;

import java.io.Serializable;
import java.util.List;

public record ItemResponse(
        String name,
        String description,
        int quantity,
        double price,
        List<CategoryResponse> categories,
        BrandResponse brand
) implements Serializable {
}
