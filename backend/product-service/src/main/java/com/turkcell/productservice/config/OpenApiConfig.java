package com.turkcell.productservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product API")
                        .description("Product API sözleşmeleri bulunduran yml dosyası.\n\n" +
                                "Bu dosyadaki kalıplar product-service içerisinde kullanılmak zorundadır.\n" +
                                "Bu kalıpların dışına çıkılamaz.")
                        .version("1.0.0"))
                .servers(List.of(
                        new Server().url("http://localhost:8080/api/v1").description("Development Server"),
                        new Server().url("https://api.turkcell.com.tr").description("Production Server")
                ));
    }

}
