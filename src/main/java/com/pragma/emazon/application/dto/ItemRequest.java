package com.pragma.emazon.application.dto;

import java.io.Serializable;
import java.util.List;

public record ItemRequest(
        String name,
        String description,
        int quantity,
        double price,
        List<Long> categoriesId,
        Long brandId
) implements Serializable {
}
