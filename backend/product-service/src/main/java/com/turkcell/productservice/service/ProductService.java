package com.turkcell.productservice.service;

import com.turkcell.productservice.dto.CreateProductRequest;
import com.turkcell.productservice.dto.PagedProductResponse;
import com.turkcell.productservice.dto.ProductResponse;
import com.turkcell.productservice.exception.DuplicateProductException;
import com.turkcell.productservice.exception.ProductNotFoundException;
import com.turkcell.productservice.model.Product;
import com.turkcell.productservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public PagedProductResponse listProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> productResponses = productPage.getContent().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getCurrency()
                ))
                .collect(Collectors.toList());

        return new PagedProductResponse(
                productResponses,
                productPage.getNumber(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                productPage.getSize()
        );
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Geçersiz UUID formatı");
        }

        Product product = productRepository.findById(uuid.toString())
                .orElseThrow(() -> new ProductNotFoundException("Ürün bulunamadı"));

        return new ProductResponse(
                product.getId().toString(),
                product.getName(),
                product.getPrice(),
                product.getCurrency()
        );
    }

}
