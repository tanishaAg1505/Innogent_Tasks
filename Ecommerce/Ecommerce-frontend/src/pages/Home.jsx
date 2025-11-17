// src/pages/Home.jsx
import React, { useEffect, useState } from "react";
import ProductList from "../components/ProductList";
import { fetchAllProducts } from "../services/api";

export default function Home() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("all");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let mounted = true;
    setLoading(true);
    fetchAllProducts()
      .then(data => {
        if (!mounted) return;
        setProducts(data);
        setCategories(Array.from(new Set(data.map(p => p.category))));
      })
      .catch(console.error)
      .finally(() => mounted && setLoading(false));
    return () => (mounted = false);
  }, []);

  const q = search.trim().toLowerCase();
  const filtered = products.filter(p => {
    const matchCategory = category === "all" || p.category === category;
    const matchSearch = q === "" || p.title.toLowerCase().includes(q) || p.description.toLowerCase().includes(q);
    return matchCategory && matchSearch;
  });

  return (
    <div className="container">
      <div className="controls">
        <input className="search-input" placeholder="Search products..." value={search} onChange={e => setSearch(e.target.value)} />
        <select className="select-input" value={category} onChange={e => setCategory(e.target.value)}>
          <option value="all">All Categories</option>
          {categories.map(c => <option key={c} value={c}>{c}</option>)}
        </select>
      </div>

      {loading ? <p>Loading products...</p> : (
        category === "all" ? categories.map(cat => {
          const list = filtered.filter(p => p.category === cat);
          if (list.length === 0) return null;
          return (
            <section key={cat} className="category-block">
              <h3 className="category-title">{cat}</h3>
              <ProductList products={list} />
            </section>
          );
        }) : (
          <section className="category-block">
            <h3 className="category-title">{category}</h3>
            <ProductList products={filtered} />
          </section>
        )
      )}
    </div>
  );
}
