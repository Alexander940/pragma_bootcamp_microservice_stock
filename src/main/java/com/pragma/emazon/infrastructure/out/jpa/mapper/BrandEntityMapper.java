package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.application.util.ListUtil;
import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Item;
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

    default Brand toBrand(BrandEntity brandEntity){
        Long id = brandEntity.getId();
        String name = brandEntity.getName();
        String description = brandEntity.getDescription();
        Item [] items = ListUtil.toArray(brandEntity.getItems().stream()
                .map(itemEntity -> {
                    Item.Builder builder = new Item.Builder();
                    builder.id(itemEntity.getId())
                            .name(itemEntity.getName())
                            .description(itemEntity.getDescription())
                            .price(itemEntity.getPrice())
                            .quantity(itemEntity.getQuantity());
                    return builder.build();
                })
                .toList());

        return new Brand(id, name, description, items);
    }

    default Page<Brand> toBrandsPage(Page<BrandEntity> brandEntities){
        List<Brand> brands = brandEntities
                .map(this::toBrand)
                .getContent();
        return new PageImpl<>(brands, brandEntities.getPageable(), brandEntities.getTotalElements());
    }
}
