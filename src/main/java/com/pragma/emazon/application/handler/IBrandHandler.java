package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.application.dto.BrandResponse;
import org.springframework.data.domain.Page;

public interface IBrandHandler {
    BrandResponse saveBrand(BrandRequest brandRequest);
    Page<BrandResponse> findAllBrands(int page, int size, String sort);
}
