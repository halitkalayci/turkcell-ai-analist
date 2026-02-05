import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import productService from '../services/productService';
import './ProductDetail.css';

const ProductDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        setLoading(true);
        setError(null);
        const data = await productService.getProductById(id);
        setProduct(data);
      } catch (err) {
        setError(err.message || 'Ürün detayı alınamadı');
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  if (loading) {
    return (
      <div className="product-detail-container">
        <div className="loading">Yükleniyor...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="product-detail-container">
        <div className="error-message">{error}</div>
        <button className="back-button" onClick={() => navigate('/')}>
          Geri Dön
        </button>
      </div>
    );
  }

  if (!product) {
    return (
      <div className="product-detail-container">
        <div className="error-message">Ürün bulunamadı</div>
        <button className="back-button" onClick={() => navigate('/')}>
          Geri Dön
        </button>
      </div>
    );
  }

  return (
    <div className="product-detail-container">
      <div className="product-detail-card">
        <h1 className="detail-title">Ürün Detayı</h1>
        
        <div className="detail-section">
          <label className="detail-label">Ürün Adı</label>
          <div className="detail-value">{product.name}</div>
        </div>

        <div className="detail-section">
          <label className="detail-label">Fiyat</label>
          <div className="detail-value price">
            <span className="price-amount">
              {product.price.toLocaleString('tr-TR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
            </span>
            <span className="price-currency">{product.currency}</span>
          </div>
        </div>

        <div className="detail-section">
          <label className="detail-label">Ürün ID</label>
          <div className="detail-value id">{product.id}</div>
        </div>

        <button className="back-button" onClick={() => navigate('/')}>
          Geri Dön
        </button>
      </div>
    </div>
  );
};

export default ProductDetail;
