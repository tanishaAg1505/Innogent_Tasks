import { createSlice } from "@reduxjs/toolkit";

const saved = localStorage.getItem("cart_v1");
const initialState = saved ? JSON.parse(saved) : {
  items: [], // { id, title, price, image, qty }
  totalQty: 0,
  totalAmount: 0,
};

const recalc = (state) => {
  state.totalQty = state.items.reduce((s, it) => s + it.qty, 0);
  state.totalAmount = state.items.reduce((s, it) => s + it.qty * it.price, 0);
  localStorage.setItem("cart_v1", JSON.stringify(state));
};

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addToCart(state, action) {
      const p = action.payload;
      const existing = state.items.find((it) => it.id === p.id);
      if (existing) {
        existing.qty += p.qty ?? 1;
      } else {
        state.items.push({ ...p, qty: p.qty ?? 1 });
      }
      recalc(state);
    },
    removeFromCart(state, action) {
      const id = action.payload;
      state.items = state.items.filter((it) => it.id !== id);
      recalc(state);
    },
    updateQty(state, action) {
      const { id, qty } = action.payload;
      const item = state.items.find((it) => it.id === id);
      if (item) item.qty = Math.max(1, qty);
      recalc(state);
    },
    clearCart(state) {
      state.items = [];
      recalc(state);
    },
  },
});

export const { addToCart, removeFromCart, updateQty, clearCart } = cartSlice.actions;
export default cartSlice.reducer;
