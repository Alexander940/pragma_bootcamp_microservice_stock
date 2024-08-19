package com.pragma.emazon.domain.api;

import com.pragma.emazon.domain.model.Brand;

public interface IBrandServicePort {
    Brand saveBrand(Brand brand);
    Brand findBrandByName(String name);
}
