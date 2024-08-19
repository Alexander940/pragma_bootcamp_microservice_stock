package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BrandJpaAdapterTest {

    private BrandJpaAdapter brandJpaAdapter;
    @Mock
    private IBrandRepository brandRepository;
    @Mock
    private BrandEntityMapper brandEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandJpaAdapter = new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Test
    void when_saveBrand_method_is_called_and_returns_an_CategoryEntity() {
        Brand brand = new Brand(1L, "name", "description", new Item[]{});
        BrandEntity brandEntity = new BrandEntity(1L, "name", "description", Set.of());

        when(brandRepository.save(brandEntityMapper.toEntity(brand))).thenReturn(brandEntity);
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Brand savedBrand = brandJpaAdapter.saveBrand(brand);

        assertEquals(brand, savedBrand);
    }

    @Test
    void when_findBrandByName_is_called_and_returns_an_CategoryEntity() {
        Brand brand = new Brand(1L, "name", "description", new Item[]{});
        BrandEntity brandEntity = new BrandEntity(1L, "name", "description", Set.of());

        when(brandRepository.findByName("name")).thenReturn(java.util.Optional.of(brandEntity));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Brand foundBrand = brandJpaAdapter.findBrandByName("name");

        assertEquals(brand, foundBrand);
    }
}