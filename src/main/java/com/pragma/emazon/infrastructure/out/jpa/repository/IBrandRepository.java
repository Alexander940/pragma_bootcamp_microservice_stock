package com.pragma.emazon.infrastructure.out.jpa.repository;

import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    @NonNull
    BrandEntity save(@NonNull BrandEntity brandEntity);

    Optional<BrandEntity> findByName(String name);

    @NonNull
    Page<BrandEntity> findAll(@NonNull  Pageable pageable);
}
