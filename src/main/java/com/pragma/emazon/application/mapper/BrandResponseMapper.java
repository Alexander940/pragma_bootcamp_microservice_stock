package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.PageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandResponseMapper {

    BrandResponse toBrandResponse(Brand brand);

    default Page<BrandResponse> toBrandResponsePage(PageModel<Brand> brands){
        List<BrandResponse> brandResponses = brands
                .getContent()
                .stream()
                .map(this::toBrandResponse)
                .toList();
        return new PageImpl<>(brandResponses, PageRequest.of(brands.getPageNumber(), brands.getPageSize()), brands.getTotalElements());
    }
}
