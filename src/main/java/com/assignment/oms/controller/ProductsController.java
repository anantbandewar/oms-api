package com.assignment.oms.controller;

import com.assignment.oms.dto.ProductDetails;
import com.assignment.oms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products/")
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDetails>> getProducts() {
        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetails> getProductDetails(@PathVariable Long productId) {
        return ResponseEntity.ok(
                productService.getProductDetails(productId)
        );
    }
}
