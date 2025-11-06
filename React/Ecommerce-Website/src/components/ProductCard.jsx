import React from "react";
import { Link } from "react-router-dom";
import "./ProductCard.css";

export default function ProductCard({ product }) {
  return (
    <Link to={`/product/${product.id}`} className="product-card">
      <div className="pc-image"><img src={product.image} alt={product.title} /></div>
      <div className="pc-body">
        <h4 className="pc-title">{product.title}</h4>
        <p className="pc-price">₹ {product.price}</p>
      </div>
    </Link>
  );
}