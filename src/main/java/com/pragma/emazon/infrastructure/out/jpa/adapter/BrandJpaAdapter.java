package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.spi.IBrandPersistencePort;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;


    @Override
    public Brand saveBrand(Brand brand) {
        BrandEntity brandEntity = brandRepository.save(brandEntityMapper.toEntity(brand));
        return brandEntityMapper.toBrand(brandEntity);
    }
}
