package com.pragma.emazon.domain.spi;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.PageModel;

public interface IBrandPersistencePort {
    Brand saveBrand(Brand brand);
    Brand findBrandByName(String name);
    PageModel<Brand> findAllBrands(int page, int size, String sort);
}
