package com.pragma.emazon.application.dto;

import java.io.Serializable;

public record ItemRequest(
        String name,
        String description,
        int quantity,
        double price,
        Long [] categoriesId,
        Long brandId
) implements Serializable {
}
