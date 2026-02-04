package com.turkcell.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;

@Schema(description = "Hata yanıt modeli")
public class ErrorResponse {

    @Schema(description = "Hatanın oluştuğu zaman (ISO 8601 formatında)", example = "2026-02-04T10:30:00Z")
    private String timestamp;

    @Schema(description = "HTTP durum kodu", example = "400")
    private Integer status;

    @Schema(description = "HTTP durum açıklaması", example = "Bad Request")
    private String error;

    @Schema(description = "Hata mesajı", example = "İsim 2-150 karakter arasında olmalıdır")
    private String message;

    @Schema(description = "İstek yapılan endpoint", example = "/api/v1/products")
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(String timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public static ErrorResponse of(Integer status, String error, String message, String path) {
        return new ErrorResponse(
                ZonedDateTime.now().toString(),
                status,
                error,
                message,
                path
        );
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
