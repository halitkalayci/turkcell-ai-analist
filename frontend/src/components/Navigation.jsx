import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Navigation.css';

const Navigation = () => {
  const location = useLocation();

  return (
    <nav className="navigation">
      <div className="nav-container">
        <div className="nav-brand">
          <h1>Turkcell E-Ticaret</h1>
        </div>
        <div className="nav-links">
          <Link 
            to="/" 
            className={location.pathname === '/' ? 'nav-link active' : 'nav-link'}
          >
            Ürünler
          </Link>
          <Link 
            to="/add-product" 
            className={location.pathname === '/add-product' ? 'nav-link active' : 'nav-link'}
          >
            Yeni Ürün Ekle
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navigation;
