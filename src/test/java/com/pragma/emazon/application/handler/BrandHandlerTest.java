package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.application.mapper.BrandRequestMapper;
import com.pragma.emazon.application.mapper.BrandResponseMapper;
import com.pragma.emazon.domain.api.IBrandServicePort;
import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BrandHandlerTest {

    private BrandHandler brandHandler;

    @Mock
    private BrandRequestMapper brandRequestMapper;
    @Mock
    private BrandResponseMapper brandResponseMapper;
    @Mock
    private IBrandServicePort brandServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandHandler = new BrandHandler(brandRequestMapper, brandResponseMapper, brandServicePort);
    }

    @Test
    void when_saveBrand_useCase_is_called_and_return_an_BrandResponse() {
        Brand brand = new Brand(1L,"name", "description", new Item[]{});
        BrandRequest brandRequest = new BrandRequest("name", "description");

        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);
        when(brandServicePort.saveBrand(brand)).thenReturn(brand);
        when(brandResponseMapper.toBrandResponse(brand)).thenReturn(new BrandResponse("name", "description", Set.of()));

        BrandResponse brandResponse = brandHandler.saveBrand(brandRequest);

        assertEquals(brandRequest.name(), brandResponse.name());
    }
}