package com.pragma.emazon.application.usecase;

import com.pragma.emazon.application.exception.BrandAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BrandUseCaseTest {
    
    private BrandUseCase brandUseCase;
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandUseCase = new BrandUseCase(brandPersistencePort);
    }

    @Test
    void when_saveBrand_useCase_is_called_and_returns_an_Brand() {
        Brand brand = new Brand(1L, "name", "description", new Item[]{});

        when(brandPersistencePort.saveBrand(brand)).thenReturn(brand);

        Brand brandResponse = brandUseCase.saveBrand(brand);

        assertEquals(brand.getName(), brandResponse.getName());
    }

    @Test
    void when_saveBrand_useCase_is_called_and_name_is_too_long() {
        String name = "a".repeat(51);
        Brand brand = new Brand(1L, name,"description", new Item[]{});

        assertThrows(StringTooLongException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void when_saveBrand_useCase_is_called_and_description_is_too_long() {
        String description = "a".repeat(121);
        Brand brand = new Brand(1L, "name", description, new Item[]{});

        assertThrows(StringTooLongException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void when_saveBrand_useCase_is_called_and_description_is_missing() {
        Brand brand = new Brand(1L, "name", "", new Item[]{});

        assertThrows(MandatoryParameterException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void when_saveBrand_useCase_is_called_and_the_brand_name_already_exists() {
        Brand brand = new Brand(1L,"name", "description", new Item[]{});

        when(brandPersistencePort.findBrandByName(brand.getName())).thenReturn(brand);

        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void when_findBrandByName_method_is_called_and_returns_a_Brand() {
        Brand brand = new Brand(1L, "name", "description", new Item[]{});

        when(brandPersistencePort.findBrandByName("name")).thenReturn(brand);

        Brand foundBrand = brandUseCase.findBrandByName("name");

        assertEquals(brand, foundBrand);
    }
}