import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { placeOrder } from "../redux/orderSlice"; // action creator
import { clearCart } from "../redux/cartSlice";
import { useLocation, useNavigate } from "react-router-dom";

export default function Checkout() {
  const { items, totalAmount } = useSelector((s) => s.cart);
  const dispatch = useDispatch();
  const location = useLocation();
  const navigate = useNavigate();
  const discount = (location.state && location.state.discount) || 0;

  const [address, setAddress] = useState({
    name: "",
    line1: "",
    city: "",
    postal: "",
    phone: "",
  });

  const handleChange = (e) => setAddress({ ...address, [e.target.name]: e.target.value });

  const place = () => {
    const total = totalAmount - discount;
    const payload = {
      items,
      address,
      promo: discount > 0 ? { code: location.state?.promoCode || "APPLIED" } : null,
      total,
    };
    dispatch({ type: "orders/placeOrder", payload }); // using createSlice action name
    dispatch(clearCart());
    navigate("/orders");
  };

  return (
    <div className="container">
      <h2>Checkout</h2>
      <div className="checkout-grid">
        <form className="address-form" onSubmit={(e) => { e.preventDefault(); place(); }}>
          <input name="name" placeholder="Full Name" value={address.name} onChange={handleChange} required />
          <input name="line1" placeholder="Address line 1" value={address.line1} onChange={handleChange} required />
          <input name="city" placeholder="City" value={address.city} onChange={handleChange} required />
          <input name="postal" placeholder="Postal Code" value={address.postal} onChange={handleChange} required />
          <input name="phone" placeholder="Phone" value={address.phone} onChange={handleChange} required />
          <button className="btn primary" type="submit">Place Order</button>
        </form>

        <aside className="order-review">
          <h3>Order Review</h3>
          <p>Items: {items.length}</p>
          <p>Subtotal: ₹{totalAmount.toFixed(2)}</p>
          <p>Discount: -₹{discount.toFixed(2)}</p>
          <p><b>Total: ₹{(totalAmount - discount).toFixed(2)}</b></p>
        </aside>
      </div>
    </div>
  );
}
