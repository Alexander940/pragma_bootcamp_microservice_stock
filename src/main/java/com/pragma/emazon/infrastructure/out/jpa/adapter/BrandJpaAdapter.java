package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.PageModel;
import com.pragma.emazon.domain.spi.IBrandPersistencePort;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.mapper.PageAdapterMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final PageAdapterMapper pageAdapterMapper;

    @Override
    public Brand saveBrand(Brand brand) {
        BrandEntity brandEntity = brandRepository.save(brandEntityMapper.toEntity(brand));
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public Brand findBrandByName(String name) {
        return brandRepository.findByName(name)
                .map(brandEntityMapper::toBrand)
                .orElse(null);
    }

    @Override
    public PageModel<Brand> findAllBrands(int page, int size, String sort) {
        Sort.Direction sortDirection = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        return pageAdapterMapper.toPageModel(brandEntityMapper.toBrandsPage(brandRepository.findAll(pageable)));
    }
}
