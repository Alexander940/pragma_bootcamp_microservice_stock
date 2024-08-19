package com.pragma.emazon.infrastructure.out.jpa.repository;

import com.pragma.emazon.infrastructure.out.jpa.entity.BrandEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    @NonNull
    BrandEntity save(BrandEntity brandEntity);
}
