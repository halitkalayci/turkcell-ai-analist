package com.turkcell.productservice.dto;

import java.util.List;

public class PagedProductResponse {

    private List<ProductResponse> products;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;

    public PagedProductResponse() {
    }

    public PagedProductResponse(List<ProductResponse> products, int currentPage, int totalPages, long totalElements, int size) {
        this.products = products;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
