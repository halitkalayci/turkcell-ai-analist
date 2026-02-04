package com.turkcell.productservice.dto;

import com.turkcell.productservice.validation.ValidCurrency;
import com.turkcell.productservice.validation.ValidProductName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Yeni ürün oluşturma isteği")
public class CreateProductRequest {

    @NotNull(message = "İsim alanı zorunludur")
    @Size(min = 2, max = 150, message = "İsim 2-150 karakter arasında olmalıdır")
    @ValidProductName
    @Schema(description = "Ürün adı (2-150 karakter, sadece boşluklardan oluşamaz, başında ve sonunda boşluk olamaz)", example = "iPhone 15 Pro")
    private String name;

    @NotNull(message = "Fiyat alanı zorunludur")
    @DecimalMin(value = "0.0", inclusive = true, message = "Fiyat 0 veya daha büyük olmalıdır")
    @Schema(description = "Ürün fiyatı (0 veya daha büyük olmalıdır)", example = "45999.99")
    private Double price;

    @NotNull(message = "Para birimi alanı zorunludur")
    @ValidCurrency
    @Schema(description = "Para birimi (sadece ₺ veya $)", example = "₺", allowableValues = {"₺", "$"})
    private String currency;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name, Double price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
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
