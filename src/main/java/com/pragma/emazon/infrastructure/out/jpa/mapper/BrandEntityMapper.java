package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

}
