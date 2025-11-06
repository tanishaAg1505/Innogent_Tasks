import React from "react";
import ProductCard from "./ProductCard";

export default function ProductList({ products }) {
  if (!products || products.length === 0) return <p>No products found.</p>;
  return (
    <div className="product-grid">
      {products.map(p => <ProductCard key={p.id} product={p} />)}
    </div>
  );
}