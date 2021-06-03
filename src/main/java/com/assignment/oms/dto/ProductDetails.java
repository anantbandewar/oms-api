package com.assignment.oms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("quantity")
    private Float quantity;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("barcode")
    private String barcode;
}
