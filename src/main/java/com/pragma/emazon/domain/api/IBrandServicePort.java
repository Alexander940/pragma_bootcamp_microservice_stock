package com.pragma.emazon.domain.api;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.PageModel;

public interface IBrandServicePort {
    Brand saveBrand(Brand brand);
    Brand findBrandByName(String name);
    PageModel<Brand> findAllBrands(int page, int size, String sort);
}
