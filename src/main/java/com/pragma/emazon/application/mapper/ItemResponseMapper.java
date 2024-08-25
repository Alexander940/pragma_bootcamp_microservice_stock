package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.ItemResponse;
import com.pragma.emazon.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ItemResponseMapper {

    ItemResponse toItemResponse(Item item);

}
