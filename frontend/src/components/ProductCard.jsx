import React from 'react';
import { useNavigate } from 'react-router-dom';
import './ProductCard.css';

const ProductCard = ({ product }) => {
  const navigate = useNavigate();

  const handleViewDetails = () => {
    navigate(`/product/${product.id}`);
  };

  return (
    <div className="product-card">
      <h3 className="product-name">{product.name}</h3>
      <div className="product-price">
        <span className="price-amount">{product.price.toLocaleString('tr-TR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</span>
        <span className="price-currency">{product.currency}</span>
      </div>
      <div className="product-id">ID: {product.id}</div>
      <button className="view-details-button" onClick={handleViewDetails}>
        Detayları Gör
      </button>
    </div>
  );
};

export default ProductCard;
