package com.assignment.oms.service;

import com.assignment.oms.dto.ProductDetails;

import java.util.List;

public interface ProductService {

    List<ProductDetails> getAllProducts();

    ProductDetails getProductDetails(Long productId);

    Long save(ProductDetails product);
}
