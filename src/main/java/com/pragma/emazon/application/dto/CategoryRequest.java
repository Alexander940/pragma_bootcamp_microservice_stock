package com.pragma.emazon.application.dto;

import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.io.Serializable;

public record CategoryRequest(
        @Size(max = 50)
        String name,
        @NonNull
        @Size(max = 90)
        String description
) implements Serializable {
}
