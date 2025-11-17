import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import ProfileMenu from "./ProfileMenu";
import "./Header.css"; // styling from main css


// Top Nav bar :- yeh component har page ke uper dikhega-like app logo , icons


export default function Header() {
  const totalQty = useSelector((state) => state.cart.totalQty);
  const [openProfile, setOpenProfile] = useState(false);

  return (
    <header className="header">
      <Link to="/" className="logo">🛍️ MyShop</Link>

      <nav className="icons">
        <Link to="/cart" className="cart-icon" title="Cart">
          🛒
          {totalQty > 0 && <span className="badge">{totalQty}</span>}
        </Link>

        <button className="icon-btn" title="Notifications">🔔</button>

        <div className="profile-wrapper">
          <button className="icon-btn" onClick={() => setOpenProfile((s) => !s)} title="Profile">👤</button>
          {openProfile && <ProfileMenu onClose={() => setOpenProfile(false)} />}
        </div>
      </nav>
    </header>
  );
}