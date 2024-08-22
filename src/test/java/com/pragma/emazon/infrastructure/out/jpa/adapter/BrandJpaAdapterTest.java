package com.pragma.emazon.infrastructure.out.jpa.adapter;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.model.PageModel;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.infrastructure.out.jpa.mapper.BrandEntityMapper;
import com.pragma.emazon.infrastructure.out.jpa.mapper.PageAdapterMapper;
import com.pragma.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BrandJpaAdapterTest {

    private BrandJpaAdapter brandJpaAdapter;
    @Mock
    private IBrandRepository brandRepository;
    @Mock
    private BrandEntityMapper brandEntityMapper;
    @Mock
    private PageAdapterMapper pageAdapterMapper;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            brandJpaAdapter = new BrandJpaAdapter(brandRepository, brandEntityMapper, pageAdapterMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_saveBrand_method_is_called_and_returns_a_BrandEntity() {
        Brand brand = new Brand(1L, "name", "description", new Item[]{});
        BrandEntity brandEntity = new BrandEntity(1L, "name", "description", Set.of());

        when(brandRepository.save(brandEntityMapper.toEntity(brand))).thenReturn(brandEntity);
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Brand savedBrand = brandJpaAdapter.saveBrand(brand);

        assertEquals(brand, savedBrand);
    }

    @Test
    void when_findBrandByName_is_called_and_returns_a_BrandEntity() {
        Brand brand = new Brand(1L, "name", "description", new Item[]{});
        BrandEntity brandEntity = new BrandEntity(1L, "name", "description", Set.of());

        when(brandRepository.findByName("name")).thenReturn(java.util.Optional.of(brandEntity));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Brand foundBrand = brandJpaAdapter.findBrandByName("name");

        assertEquals(brand, foundBrand);
    }

    @Test
    void when_findAllBrands_is_called_and_returns_a_PageModel() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Page<BrandEntity> brandEntityPage = Page.empty();
        Page<Brand> brandPage = Page.empty();
        PageModel<Brand> pageModel = new PageModel.Builder<Brand>().build();

        when(brandRepository.findAll(pageable)).thenReturn(brandEntityPage);
        when(brandEntityMapper.toBrandsPage(brandEntityPage)).thenReturn(brandPage);
        when(pageAdapterMapper.toPageModel(brandPage)).thenReturn(pageModel);

        PageModel<Brand> pageModelResponse = brandJpaAdapter.findAllBrands(0, 10, "asc");

        assertEquals(pageModel, pageModelResponse);
    }
}