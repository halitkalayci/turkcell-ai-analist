import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import productService from '../services/productService';
import './AddProduct.css';

const AddProduct = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    price: '',
    currency: '₺'
  });
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [apiError, setApiError] = useState(null);

  const validateForm = () => {
    const newErrors = {};

    if (!formData.name.trim()) {
      newErrors.name = 'Ürün adı zorunludur';
    } else if (formData.name.length < 2 || formData.name.length > 150) {
      newErrors.name = 'İsim 2-150 karakter arasında olmalıdır';
    } else if (!/^(?!\s*$)\S.*\S$|^\S$/.test(formData.name)) {
      newErrors.name = 'İsim sadece boşluklardan oluşamaz ve başında/sonunda boşluk olamaz';
    }

    if (!formData.price) {
      newErrors.price = 'Fiyat zorunludur';
    } else if (isNaN(formData.price) || parseFloat(formData.price) < 0) {
      newErrors.price = 'Fiyat 0 veya daha büyük olmalıdır';
    }

    if (!['₺', '$'].includes(formData.currency)) {
      newErrors.currency = 'Para birimi sadece ₺ veya $ olabilir';
    }

    return newErrors;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    
    if (errors[name]) {
      setErrors(prev => ({
        ...prev,
        [name]: null
      }));
    }
    
    if (apiError) {
      setApiError(null);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const validationErrors = validateForm();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    setIsSubmitting(true);
    setApiError(null);

    try {
      const productData = {
        name: formData.name.trim(),
        price: parseFloat(formData.price),
        currency: formData.currency
      };

      await productService.createProduct(productData);
      navigate('/');
    } catch (error) {
      setApiError(error.message || 'Ürün eklenirken bir hata oluştu');
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleCancel = () => {
    navigate('/');
  };

  return (
    <div className="add-product-container">
      <div className="add-product-card">
        <h2>Yeni Ürün Ekle</h2>
        
        {apiError && (
          <div className="error-alert">
            {apiError}
          </div>
        )}

        <form onSubmit={handleSubmit} className="product-form">
          <div className="form-group">
            <label htmlFor="name">Ürün Adı *</label>
            <input
              type="text"
              id="name"
              name="name"
              value={formData.name}
              onChange={handleChange}
              className={errors.name ? 'input-error' : ''}
              placeholder="Örn: iPhone 15 Pro"
              disabled={isSubmitting}
            />
            {errors.name && <span className="error-text">{errors.name}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="price">Fiyat *</label>
            <input
              type="number"
              id="price"
              name="price"
              value={formData.price}
              onChange={handleChange}
              className={errors.price ? 'input-error' : ''}
              placeholder="0.00"
              step="0.01"
              min="0"
              disabled={isSubmitting}
            />
            {errors.price && <span className="error-text">{errors.price}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="currency">Para Birimi *</label>
            <select
              id="currency"
              name="currency"
              value={formData.currency}
              onChange={handleChange}
              className={errors.currency ? 'input-error' : ''}
              disabled={isSubmitting}
            >
              <option value="₺">₺ (Türk Lirası)</option>
              <option value="$">$ (Dolar)</option>
            </select>
            {errors.currency && <span className="error-text">{errors.currency}</span>}
          </div>

          <div className="form-actions">
            <button
              type="button"
              onClick={handleCancel}
              className="btn-cancel"
              disabled={isSubmitting}
            >
              İptal
            </button>
            <button
              type="submit"
              className="btn-submit"
              disabled={isSubmitting}
            >
              {isSubmitting ? 'Kaydediliyor...' : 'Kaydet'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddProduct;
