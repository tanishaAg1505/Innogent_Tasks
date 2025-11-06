import React from 'react';
import { Link } from 'react-router-dom';
import "./Header.css"; // styling from main css


// Top Nav bar :- yeh component har page ke uper dikhega-like app logo , icons


function Header() {
  return (
  
    // Ye HTML <header> tag hai jisme humara nav bar bana rahe hain.

    <header className="header">
      {/* Left Side → Logo */}
      <Link  className="logo">🛍️ E-Shopify</Link>

      {/* Right Side → Icons */}
      <nav className="icons">
        <span title="Cart">🛒</span>
        <span title="Notifications">🔔</span>
        <span title="Profile">👤</span>
      </nav>
    </header>
  );
}

export default Header;
