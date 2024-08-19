package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.application.dto.BrandResponse;

public interface IBrandHandler {
    BrandResponse createBrand(BrandRequest brandRequest);
}
