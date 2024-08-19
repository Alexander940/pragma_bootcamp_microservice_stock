package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.BrandResponse;
import com.pragma.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandResponseMapper {

    BrandResponse toBrandResponse(Brand brand);

    Brand toBrand(BrandResponse brandResponse);

}
