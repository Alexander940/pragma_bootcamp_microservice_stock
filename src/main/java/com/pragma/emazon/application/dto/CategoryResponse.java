package com.pragma.emazon.application.dto;

import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.io.Serializable;

public record CategoryResponse(
        @Size(max = 50)
        String name,
        @NonNull
        @Size(max = 90)
        String description,
        ItemResponse [] items
) implements Serializable {
}
