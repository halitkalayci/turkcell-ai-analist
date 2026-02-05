import React from 'react';
import './ProductCard.css';

const ProductCard = ({ product }) => {
  return (
    <div className="product-card">
      <h3 className="product-name">{product.name}</h3>
      <div className="product-price">
        <span className="price-amount">{product.price.toLocaleString('tr-TR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</span>
        <span className="price-currency">{product.currency}</span>
      </div>
      <div className="product-id">ID: {product.id}</div>
    </div>
  );
};

export default ProductCard;
