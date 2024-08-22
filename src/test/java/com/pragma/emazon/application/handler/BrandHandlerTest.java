package com.pragma.emazon.application.handler;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.application.mapper.BrandRequestMapper;
import com.pragma.emazon.application.mapper.BrandResponseMapper;
import com.pragma.emazon.domain.api.IBrandServicePort;
import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.domain.model.PageModel;
import com.pragma.emazon.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
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
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            brandHandler = new BrandHandler(brandRequestMapper, brandResponseMapper, brandServicePort);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void when_saveBrand_useCase_is_called_and_return_an_BrandResponse() {
        Brand brand = new Brand(1L,"name", "description", new Item[]{});
        BrandRequest brandRequest = new BrandRequest("name", "description");

        when(brandRequestMapper.toBrand(brandRequest)).thenReturn(brand);
        when(brandServicePort.saveBrand(brand)).thenReturn(brand);
        when(brandResponseMapper.toBrandResponse(brand)).thenReturn(new BrandResponse("name", "description", List.of()));

        BrandResponse brandResponse = brandHandler.saveBrand(brandRequest);

        assertEquals(brandRequest.name(), brandResponse.name());
    }

    @Test
    void when_findAllBrands_method_is_called_and_returns_a_BrandResponse() {
        Page<BrandResponse> brandResponsePage = new PageImpl<>(List.of(new BrandResponse("name", "description", List.of())));
        PageModel<Brand> brandPageModel = new PageModel.Builder<Brand>().build();

        when(brandServicePort.findAllBrands(0, 10, "name")).thenReturn(brandPageModel);
        when(brandResponseMapper.toBrandResponsePage(brandPageModel)).thenReturn(brandResponsePage);

        Page<BrandResponse> brandResponses = brandHandler.findAllBrands(0, 10, "name");

        assertEquals(brandResponsePage, brandResponses);
    }
}