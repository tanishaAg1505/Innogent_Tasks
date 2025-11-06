import React from "react";
import "./App.css";
import Header from "./components/Header";
import Home from "./pages/Home";
import ProductDetails from "./pages/ProductDetails"; // import product details page
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    
      <div className="App">
        <Header /> {/* Yeh har page par dikhenga */}
        <Routes>
          <Route path="/" element={<Home />} /> {/* Home Page */}
          <Route path="/product/:id" element={<ProductDetails />} /> {/* Product Detail Page */}
        </Routes>
      </div>

  );
}

export default App;

