package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Brand;

public interface IBrandPersistencePort {
    Brand saveBrand(Brand brand);
}
