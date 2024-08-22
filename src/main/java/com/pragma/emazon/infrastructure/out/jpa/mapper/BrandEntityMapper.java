package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandEntityMapper {

    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

    default Page<Brand> toBrandsPage(Page<BrandEntity> brandEntities){
        List<Brand> brands = brandEntities
                .map(this::toBrand)
                .getContent();
        return new PageImpl<>(brands, brandEntities.getPageable(), brandEntities.getTotalElements());
    }
}
