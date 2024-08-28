package com.pragma.emazon.application.mapper;

import com.pragma.emazon.application.dto.ItemResponse;
import com.pragma.emazon.domain.model.Item;
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
public interface ItemResponseMapper {

    ItemResponse toItemResponse(Item item);

    default Page<ItemResponse> toItemResponsePage(PageModel<Item> items){
        List<ItemResponse> itemResponses = items
                .getContent()
                .stream()
                .map(this::toItemResponse)
                .toList();
        return new PageImpl<>(itemResponses, PageRequest.of(items.getPageNumber(), items.getPageSize()), items.getTotalElements());
    }
}
