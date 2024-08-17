package com.pragma.emazon.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniqueID")
    private Long id;

    @Size(max = 50)
    @Column(name = "name", unique = true)
    private String name;

    @Size(max = 90)
    @NonNull
    @Column(name = "description")
    private String description;
}
