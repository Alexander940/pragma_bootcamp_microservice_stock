package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.application.mapper.BrandRequestMapper;
import com.pragma.emazon.application.mapper.BrandResponseMapper;
import com.pragma.emazon.domain.api.IBrandServicePort;
import com.pragma.emazon.domain.model.Brand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;
    private final IBrandServicePort brandServicePort;

    @Override
    public BrandResponse saveBrand(BrandRequest brandRequest) {
        Brand brand = brandServicePort.saveBrand(brandRequestMapper.toBrand(brandRequest));

        return brandResponseMapper.toBrandResponse(brand);
    }
}
