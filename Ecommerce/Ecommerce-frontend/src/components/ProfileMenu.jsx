import React from "react";
import { Link } from "react-router-dom";
//import "./ProfileMenu.css";

export default function ProfileMenu({ onClose }) {
  return (
    <div className="profile-menu">
      <ul>
        <li><Link to="/profile" onClick={onClose}>Profile</Link></li>
        <li><Link to="/orders" onClick={onClose}>My Orders</Link></li>
        <li><button onClick={onClose}>Logout</button></li>
      </ul>
    </div>
  );
}
