package com.turkcell.productservice.service;

import com.turkcell.productservice.dto.CreateProductRequest;
import com.turkcell.productservice.dto.ProductResponse;
import com.turkcell.productservice.exception.DuplicateProductException;
import com.turkcell.productservice.model.Product;
import com.turkcell.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new DuplicateProductException("Bu isimde bir ürün zaten mevcut");
        }

        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getCurrency()
        );

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getCurrency()
        );
    }

}
