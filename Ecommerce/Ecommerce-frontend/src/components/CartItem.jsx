import React from "react";
import { useDispatch } from "react-redux";
import { removeFromCart, updateQty } from "../redux/cartSlice";
//import "./CartItem.css";

export default function CartItem({ item }) {
  const dispatch = useDispatch();
  return (
    <div className="cart-item">
      <img src={item.image} alt={item.title} />
      <div className="ci-body">
        <h4>{item.title}</h4>
        <p>₹ {item.price}</p>
        <div className="ci-controls">
          <button onClick={() => dispatch(updateQty({ id: item.id, qty: item.qty - 1 }))}>-</button>
          <span>{item.qty}</span>
          <button onClick={() => dispatch(updateQty({ id: item.id, qty: item.qty + 1 }))}>+</button>
          <button className="ci-remove" onClick={() => dispatch(removeFromCart(item.id))}>Remove</button>
        </div>
      </div>
      <div className="ci-total">₹ {item.qty * item.price}</div>
    </div>
  );
}
