import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { cancelOrder } from "../redux/orderSlice";

export default function Orders() {
  const orders = useSelector((s) => s.orders.list);
  const dispatch = useDispatch();

  if (!orders.length) return <div style={{ padding: 20 }}>No orders yet.</div>;

  return (
    <div className="container">
      <h2>My Orders</h2>
      <div className="orders-list">
        {orders.map((o) => (
          <div className="order-card" key={o.id}>
            <div className="order-main">
              <h3>Order #{o.id}</h3>
              <p>Status: <b>{o.status}</b></p>
              <p>Placed: {new Date(o.placedAt).toLocaleString()}</p>
              <p>Total: ₹{o.total.toFixed(2)}</p>
              <p>Address: {o.address?.line1}, {o.address?.city}</p>
            </div>
            <div className="order-items">
              {o.items.map((it) => (
                <div key={it.id} className="order-item">
                  <img src={it.image} alt={it.title} />
                  <div>
                    <p>{it.title}</p>
                    <p>Qty: {it.qty} • ₹{(it.qty * it.price).toFixed(2)}</p>
                  </div>
                </div>
              ))}
            </div>
            <div className="order-actions">
              {o.status === "Pending" && <button onClick={() => dispatch(cancelOrder(o.id))}>Cancel</button>}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
