package com.pragma.emazon.infrastructure.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryEntity {
    @Id
    private Long id;
    @Column(unique = true)
    @Size(max = 50)
    private String name;
    @Size(max = 90)
    @NonNull
    private String description;
}
