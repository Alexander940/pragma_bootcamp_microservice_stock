package com.pragma.emazon.infrastructure.out.jpa.repository;

import com.pragma.emazon.infrastructure.out.jpa.entity.CategoryEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @NonNull
    Page<CategoryEntity> findAll(@NonNull Pageable pageable);

    Optional<CategoryEntity> findByName(String name);

    @NonNull
    CategoryEntity save(@NonNull CategoryEntity categoryEntity);

}
