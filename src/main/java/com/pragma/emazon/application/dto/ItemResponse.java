package com.pragma.emazon.application.dto;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Category;

import java.io.Serializable;

public record ItemResponse(
        String name,
        String description,
        int quantity,
        double price,
        Category [] categories,
        Brand brand
) implements Serializable {
}
