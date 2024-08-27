package com.pragma.emazon.infrastructure.out.jpa.mapper;

import com.pragma.emazon.domain.model.Brand;
import com.pragma.emazon.domain.model.Category;
import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import com.pragma.emazon.infrastructure.out.jpa.repository.IBrandRepository;
import com.pragma.emazon.infrastructure.out.jpa.repository.ICategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ItemEntityMapperDecorator implements ItemEntityMapper{

    private final ICategoryRepository categoryRepository;
    private final IBrandRepository brandRepository;

    public ItemEntityMapperDecorator(ICategoryRepository categoryRepository, IBrandRepository brandRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public ItemEntity toItemEntity(Item item) {
        if (item == null) return null;

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setId(item.getId());
        itemEntity.setName(item.getName());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setQuantity(item.getQuantity());

        if (item.getBrandName() != null){
            Optional<BrandEntity> brandEntity = brandRepository.findByName(item.getBrandName());

            brandEntity.ifPresent(itemEntity::setBrand);
        }

        List<CategoryEntity> categoryEntities = Arrays.stream(item.getCategoriesName()).map(categoryName -> {
            Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(categoryName);
            return categoryEntity.orElse(null);
        }).toList();
        itemEntity.setCategories(categoryEntities);

        return itemEntity;
    }

    @Override
    public Item toItem(ItemEntity itemEntity) {
        if (itemEntity == null) return null;

        Item.Builder builder = new Item.Builder();

        builder.id(itemEntity.getId())
                .name(itemEntity.getName())
                .description(itemEntity.getDescription())
                .price(itemEntity.getPrice())
                .quantity(itemEntity.getQuantity())
                .categories(itemEntity.getCategories().stream()
                        .map(categoryEntity -> new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription(), new Item[]{}))
                        .toList()
                        .toArray(new Category[0]))
                .brand(new Brand(itemEntity.getBrand().getId(), itemEntity.getBrand().getName(), itemEntity.getBrand().getDescription(), new Item[]{}));

        return builder.build();
    }
}
