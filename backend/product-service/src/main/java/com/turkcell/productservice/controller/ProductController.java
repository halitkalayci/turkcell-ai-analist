package com.turkcell.productservice.controller;

import com.turkcell.productservice.dto.CreateProductRequest;
import com.turkcell.productservice.dto.ErrorResponse;
import com.turkcell.productservice.dto.ProductResponse;
import com.turkcell.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Ürün yönetimi API")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(
            summary = "Yeni ürün oluştur",
            description = """
                    Yeni bir ürün oluşturur.
                    
                    **Kurallar:**
                    - İsim 2-150 karakter arasında olmalıdır
                    - İsim sadece boşluklardan oluşamaz
                    - İsim başında ve sonunda boşluk olamaz
                    - Aynı isimde başka bir ürün zaten mevcut olmamalıdır
                    - Fiyat 0 veya daha büyük olmalıdır
                    - Para birimi sadece ₺ veya $ olabilir
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ürün başarıyla oluşturuldu",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class),
                            examples = @ExampleObject(
                                    name = "createdProduct",
                                    summary = "Oluşturulan ürün",
                                    value = """
                                            {
                                              "id": "123e4567-e89b-12d3-a456-426614174000",
                                              "name": "iPhone 15 Pro",
                                              "price": 45999.99,
                                              "currency": "₺"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Geçersiz istek - Validasyon hatası",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "invalidName",
                                            summary = "Geçersiz isim",
                                            value = """
                                                    {
                                                      "timestamp": "2026-02-04T10:30:00Z",
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "İsim 2-150 karakter arasında olmalıdır",
                                                      "path": "/api/v1/products"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "nameWithSpaces",
                                            summary = "Sadece boşluklardan oluşan isim",
                                            value = """
                                                    {
                                                      "timestamp": "2026-02-04T10:30:00Z",
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "İsim sadece boşluklardan oluşamaz",
                                                      "path": "/api/v1/products"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "invalidPrice",
                                            summary = "Negatif fiyat",
                                            value = """
                                                    {
                                                      "timestamp": "2026-02-04T10:30:00Z",
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "Fiyat 0 veya daha büyük olmalıdır",
                                                      "path": "/api/v1/products"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "invalidCurrency",
                                            summary = "Geçersiz para birimi",
                                            value = """
                                                    {
                                                      "timestamp": "2026-02-04T10:30:00Z",
                                                      "status": 400,
                                                      "error": "Bad Request",
                                                      "message": "Para birimi sadece ₺ veya $ olabilir",
                                                      "path": "/api/v1/products"
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Çakışma - Aynı isimde ürün zaten mevcut",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "duplicateName",
                                    summary = "Duplicate ürün ismi",
                                    value = """
                                            {
                                              "timestamp": "2026-02-04T10:30:00Z",
                                              "status": 409,
                                              "error": "Conflict",
                                              "message": "Bu isimde bir ürün zaten mevcut",
                                              "path": "/api/v1/products"
                                            }
                                            """
                            )
                    )
            )
    })
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
