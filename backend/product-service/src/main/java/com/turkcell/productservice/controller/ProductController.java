package com.turkcell.productservice.controller;

import com.turkcell.productservice.dto.CreateProductRequest;
import com.turkcell.productservice.dto.ErrorResponse;
import com.turkcell.productservice.dto.PagedProductResponse;
import com.turkcell.productservice.dto.ProductResponse;
import com.turkcell.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Ürün yönetimi API")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(
            summary = "Ürünleri listele",
            description = """
                    Tüm ürünleri sayfalı şekilde listeler.
                    
                    **Kurallar:**
                    - Sayfa numarası (page) 0 veya daha büyük olmalıdır
                    - Sayfa boyutu (size) 1-100 arasında olmalıdır
                    - Varsayılan sayfa boyutu 10'dur
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ürünler başarıyla listelendi",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PagedProductResponse.class),
                            examples = @ExampleObject(
                                    name = "productList",
                                    summary = "Ürün listesi",
                                    value = """
                                            {
                                              "products": [
                                                {
                                                  "id": "123e4567-e89b-12d3-a456-426614174000",
                                                  "name": "iPhone 15 Pro",
                                                  "price": 45999.99,
                                                  "currency": "₺"
                                                },
                                                {
                                                  "id": "223e4567-e89b-12d3-a456-426614174001",
                                                  "name": "Samsung Galaxy S24",
                                                  "price": 39999.99,
                                                  "currency": "₺"
                                                }
                                              ],
                                              "currentPage": 0,
                                              "totalPages": 5,
                                              "totalElements": 50,
                                              "size": 10
                                            }
                                            """
                            )
                    )
            )
    })
    public ResponseEntity<PagedProductResponse> listProducts(
            @Parameter(description = "Sayfa numarası (0'dan başlar)", example = "0")
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @Parameter(description = "Sayfa başına ürün sayısı (1-100 arası)", example = "10")
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {
        PagedProductResponse response = productService.listProducts(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Ürün detayını getir",
            description = """
                    Belirtilen ID'ye sahip ürünün detayını getirir.
                    
                    **Kurallar:**
                    - ID geçerli bir UUID formatında olmalıdır
                    - Ürün sistemde mevcut olmalıdır
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ürün başarıyla getirildi",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class),
                            examples = @ExampleObject(
                                    name = "productDetail",
                                    summary = "Ürün detayı",
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
                    description = "Geçersiz istek - UUID formatı hatalı",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ürün bulunamadı",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<ProductResponse> getProductById(
            @Parameter(description = "Ürün UUID'si", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
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
