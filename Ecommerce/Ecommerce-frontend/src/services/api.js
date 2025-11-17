// src/services/api.js
const BASE = "https://fakestoreapi.com";

export async function fetchAllProducts() {
  const res = await fetch(`${BASE}/products`);
  if (!res.ok) throw new Error("Failed to fetch products");
  return res.json();
}

export async function fetchProductById(id) {
  const res = await fetch(`${BASE}/products/${id}`);
  if (!res.ok) throw new Error("Failed to fetch product");
  return res.json();
}

// Mock promo validation
const promos = [
  { code: "SAVE10", type: "percent", amount: 10, validUntil: "2099-12-31" },
  { code: "FLAT50", type: "flat", amount: 50, validUntil: "2099-12-31" },
];

export async function validatePromo(code) {
  // simulate network
  await new Promise((r) => setTimeout(r, 500));
  const p = promos.find((x) => x.code === code.toUpperCase());
  if (!p) throw new Error("Invalid promo code");
  return p;
}