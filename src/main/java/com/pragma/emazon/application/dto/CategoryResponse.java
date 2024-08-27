package com.pragma.emazon.application.dto;

import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

public record CategoryResponse(
        @Size(max = 50)
        String name,
        @Size(max = 90)
        String description,
        List<ItemResponse> items
) implements Serializable {
}
