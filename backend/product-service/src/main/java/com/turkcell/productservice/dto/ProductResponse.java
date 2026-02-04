package com.turkcell.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ürün yanıt modeli")
public class ProductResponse {

    @Schema(description = "Ürün benzersiz kimliği", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "Ürün adı", example = "iPhone 15 Pro")
    private String name;

    @Schema(description = "Ürün fiyatı", example = "45999.99")
    private Double price;

    @Schema(description = "Para birimi", example = "₺")
    private String currency;

    public ProductResponse() {
    }

    public ProductResponse(String id, String name, Double price, String currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
