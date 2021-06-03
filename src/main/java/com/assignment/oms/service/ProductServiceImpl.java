package com.assignment.oms.service;

import com.assignment.oms.domain.Product;
import com.assignment.oms.dto.ProductDetails;
import com.assignment.oms.exception.NotFoundException;
import com.assignment.oms.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public List<ProductDetails> getAllProducts() {
        List<ProductDetails> response = null;

        try {
            List<Product> products = (List<Product>) productRepository.findAll();
            response = products.stream()
                    .map(product -> mapper.convertValue(product, ProductDetails.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public ProductDetails getProductDetails(Long productId) {
        ProductDetails response = null;

        try {
            Product product = productRepository
                    .findById(productId)
                    .orElseThrow(() -> new NotFoundException("product not found."));

            response = mapper.convertValue(product, ProductDetails.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Long save(ProductDetails product) {
        Product request = null;
        Long response = 0L;

        try {
            request = mapper.convertValue(product, Product.class);
            response = productRepository.save(request).getProductId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
