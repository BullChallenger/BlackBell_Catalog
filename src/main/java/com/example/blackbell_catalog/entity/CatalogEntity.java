package com.example.blackbell_catalog.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "catalog")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Setter
    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer unitPrice;
}
