import React from "react";
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addToCart } from "../redux/cartSlice";
import "./ProductCard.css";

export default function ProductCard({ product }) {
  const dispatch = useDispatch();

  return (
    <div className="card">

      {/* Product Details Page Link */}
      <Link to={`/product/${product.id}`} className="card-link">
        <img src={product.image} alt={product.title} className="card-image" />

        <h3>{product.title}</h3>
        <p>₹ {product.price}</p>
      </Link>

      {/* Add to Cart */}
      <div style={{ marginTop: 8 }}>
        <button
          className="btn"
          onClick={() =>
            dispatch(
              addToCart({
                id: product.id,
                title: product.title,
                price: product.price,
                image: product.image,
              })
            )
          }
        >
          Add to Cart
        </button>
      </div>

    </div>
  );
}
