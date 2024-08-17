package com.pragma.emazon.application.dto;

import java.io.Serializable;

public record CategoryResponse(
        String name,
        String description) implements Serializable {
}
