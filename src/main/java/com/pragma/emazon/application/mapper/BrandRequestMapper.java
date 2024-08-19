package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.BrandRequest;
import com.pragma.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {

    BrandRequest toBrandRequest(Brand brand);

    Brand toBrand(BrandRequest brandRequest);

}
