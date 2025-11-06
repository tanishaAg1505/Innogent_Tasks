
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProductById } from "../services/api";
import "./ProductDetails.css"; // styling

function ProductDetails() {
  const { id } = useParams(); // URL se product id milegi
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadProduct() {
      try {
        const data = await fetchProductById(id);
        setProduct(data);
      } catch (error) {
        console.error("Error fetching product:", error);
      } finally {
        setLoading(false);
      }
    }
    loadProduct();
  }, [id]);

  if (loading) return <h2 style={{ textAlign: "center" }}>Loading...</h2>;
  if (!product) return <h2 style={{ textAlign: "center" }}>Product not found</h2>;

  return (
    <div className="product-details">
      <img src={product.image} alt={product.title} className="details-image" />
      <div className="details-info">
        <h2>{product.title}</h2>
        <p className="category">Category: {product.category}</p>
        <p className="description">{product.description}</p>
        <h3>💲{product.price}</h3>
        <button className="add-to-cart">Add to Cart 🛒</button>
      </div>
    </div>
  );
}

export default ProductDetails;
