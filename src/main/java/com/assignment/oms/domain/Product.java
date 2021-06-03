package com.assignment.oms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProductId", unique = true, nullable = false, updatable = false)
    private Long productId;

    @NotNull(message = "Product name is required.")
    @Column(name = "Name")
    private String name;

    @Column(name = "Height", nullable = false)
    private Double height;

    @Column(name = "Weight", nullable = false)
    private Double weight;

    @NotNull(message = "Product price is required.")
    @Column(name = "Price")
    private Double price;

    @Column(name = "ImageUrl")
    private String imageUrl;

    @Column(name = "Quantity", nullable = false)
    private Float quantity;

    @Column(name = "SKU", nullable = false)
    private String sku;

    @Column(name = "Barcode", nullable = false)
    private String barcode;
}
