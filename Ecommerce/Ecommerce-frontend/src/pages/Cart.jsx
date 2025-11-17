import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import CartItem from "../components/CartItem";
import { clearCart } from "../redux/cartSlice";
import { useNavigate } from "react-router-dom";

export default function Cart() {
  const { items, totalAmount } = useSelector((s) => s.cart);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [promoCode, setPromoCode] = useState("");
  const [discount, setDiscount] = useState(0);
  const [promoMsg, setPromoMsg] = useState("");

  const applyPromo = async () => {
    try {
      setPromoMsg("Applying...");
      const { validatePromo } = await import("../services/api");
      const promo = await validatePromo(promoCode);
      let d = 0;
      if (promo.type === "percent") d = (promo.amount / 100) * totalAmount;
      else d = promo.amount;
      setDiscount(d);
      setPromoMsg(`Promo applied: -₹${Math.round(d)}`);
    } catch (err) {
      setDiscount(0);
      setPromoMsg(err.message);
    }
  };

  const checkout = () => {
    navigate("/checkout", { state: { discount } });
  };

  if (!items.length) return <div style={{ padding: 20 }}>Your cart is empty.</div>;

  return (
    <div className="container">
      <h2>Cart</h2>
      <div className="cart-grid">
        <div className="cart-items">
          {items.map((it) => <CartItem key={it.id} item={it} />)}
        </div>
        <aside className="cart-summary">
          <h3>Order Summary</h3>
          <p>Subtotal: ₹{totalAmount.toFixed(2)}</p>
          <div className="promo">
            <input value={promoCode} onChange={(e) => setPromoCode(e.target.value)} placeholder="Promo code" />
            <button onClick={applyPromo}>Apply</button>
            <div>{promoMsg}</div>
          </div>
          <p>Discount: -₹{discount.toFixed(2)}</p>
          <p><b>Grand Total: ₹{(totalAmount - discount).toFixed(2)}</b></p>
          <button onClick={checkout} className="btn primary">Proceed to Checkout</button>
          <button onClick={() => dispatch(clearCart())} className="btn">Clear Cart</button>
        </aside>
      </div>
    </div>
  );
}
