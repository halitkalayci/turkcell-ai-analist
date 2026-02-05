import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/v1';

const productService = {
  /**
   * Ürünleri sayfalı şekilde getirir
   * @param {number} page - Sayfa numarası (0'dan başlar)
   * @param {number} size - Sayfa başına ürün sayısı (1-100 arası)
   * @returns {Promise} PagedProductResponse
   */
  getProducts: async (page = 0, size = 10) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/products`, {
        params: { page, size }
      });
      return response.data;
    } catch (error) {
      throw error.response?.data || error;
    }
  },

  /**
   * Yeni ürün oluşturur
   * @param {Object} productData - CreateProductRequest
   * @param {string} productData.name - Ürün adı (2-150 karakter)
   * @param {number} productData.price - Ürün fiyatı (0 veya daha büyük)
   * @param {string} productData.currency - Para birimi (₺ veya $)
   * @returns {Promise} ProductResponse
   */
  createProduct: async (productData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/products`, productData);
      return response.data;
    } catch (error) {
      throw error.response?.data || error;
    }
  }
};

export default productService;
