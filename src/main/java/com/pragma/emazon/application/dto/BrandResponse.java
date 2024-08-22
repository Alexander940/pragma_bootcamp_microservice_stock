package com.pragma.emazon.application.dto;

import com.pragma.emazon.domain.model.Item;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

public record BrandResponse(
        @Size(max = 50)
        String name,
        @NonNull
        @Size(max = 120)
        String description,
        List<Item> items
) implements Serializable {
}
