package com.pragma.emazon.infrastructure.out.jpa.repository;

import com.pragma.emazon.infrastructure.out.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
}
