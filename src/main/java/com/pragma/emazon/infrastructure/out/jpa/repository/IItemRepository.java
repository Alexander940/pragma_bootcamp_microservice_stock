package com.pragma.emazon.infrastructure.out.jpa.repository;

import com.pragma.emazon.domain.model.Item;
import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, Long> {

    @NonNull
    Item save(@NonNull ItemEntity item);
}
