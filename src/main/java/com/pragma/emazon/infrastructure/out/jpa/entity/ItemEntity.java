package com.pragma.emazon.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UniqueID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "branduniqueid", nullable = false)
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(
        name = "item_category",
        joinColumns = @JoinColumn(name = "itemuniqueid"),
        inverseJoinColumns = @JoinColumn(name = "categoryuniqueid")
    )
    private List<CategoryEntity> categories;
}
