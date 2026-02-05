import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navigation from './components/Navigation';
import ProductList from './pages/ProductList';
import AddProduct from './pages/AddProduct';
import './App.css';

function App() {
  return (
    <Router>
      <div className="app">
        <Navigation />
        <Routes>
          <Route path="/" element={<ProductList />} />
          <Route path="/add-product" element={<AddProduct />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
