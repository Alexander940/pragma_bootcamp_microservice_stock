package com.pragma.emazon.infrastructure.out.jpa.repository;

import com.pragma.emazon.application.dto.CategoryResponse;
import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);

    CategoryResponse save(CategoryEntity categoryEntity);

}
