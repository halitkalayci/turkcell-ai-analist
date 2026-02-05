import React, { useState, useEffect } from 'react';
import ProductCard from '../components/ProductCard';
import productService from '../services/productService';
import './ProductList.css';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [size] = useState(10);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchProducts(currentPage, size);
  }, [currentPage]);

  const fetchProducts = async (page, pageSize) => {
    setLoading(true);
    setError(null);
    try {
      const data = await productService.getProducts(page, pageSize);
      setProducts(data.products || []);
      setTotalPages(data.totalPages || 0);
      setTotalElements(data.totalElements || 0);
      setCurrentPage(data.currentPage || 0);
    } catch (err) {
      setError(err.message || 'Ürünler yüklenirken bir hata oluştu');
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (newPage) => {
    if (newPage >= 0 && newPage < totalPages) {
      setCurrentPage(newPage);
    }
  };

  return (
    <div className="product-list-container">
      <div className="product-list-header">
        <h2>Ürün Listesi</h2>
        <div className="product-count">
          Toplam {totalElements} ürün
        </div>
      </div>

      {error && (
        <div className="error-message">
          {error}
        </div>
      )}

      {loading ? (
        <div className="loading-spinner">Yükleniyor...</div>
      ) : products.length === 0 ? (
        <div className="empty-state">
          <p>Henüz ürün bulunmamaktadır.</p>
          <p>Yeni ürün eklemek için yukarıdaki menüden "Yeni Ürün Ekle" seçeneğine tıklayın.</p>
        </div>
      ) : (
        <>
          <div className="product-grid">
            {products.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))}
          </div>

          {totalPages > 1 && (
            <div className="pagination">
              <button
                className="pagination-button"
                onClick={() => handlePageChange(currentPage - 1)}
                disabled={currentPage === 0}
              >
                Önceki
              </button>
              
              <div className="pagination-info">
                Sayfa {currentPage + 1} / {totalPages}
              </div>

              <button
                className="pagination-button"
                onClick={() => handlePageChange(currentPage + 1)}
                disabled={currentPage === totalPages - 1}
              >
                Sonraki
              </button>
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default ProductList;
