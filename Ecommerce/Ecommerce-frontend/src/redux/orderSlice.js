import { createSlice, nanoid } from "@reduxjs/toolkit";

/*
orders state:
{
  list: [
    { id, items: [], address: {...}, promo: {code, discount}, total, status, placedAt }
  ]
}
*/

const savedOrders = localStorage.getItem("orders_v1");
const initialState = savedOrders ? JSON.parse(savedOrders) : { list: [] };

const persist = (state) => {
  localStorage.setItem("orders_v1", JSON.stringify(state));
};

const orderSlice = createSlice({
  name: "orders",
  initialState,
  reducers: {
    placeOrder(state, action) {
      // payload: { items, address, promo, total }
      const payload = action.payload;
      const id = nanoid();
      const placedAt = Date.now();
      const order = { id, ...payload, status: "Pending", placedAt };
      state.list.unshift(order);
      persist(state);

      // auto-mark delivered after a delay (6 hours in ms)
      const DELAY = 6 * 60 * 60 * 1000;
      // For development/test change to 10000 (10s)
      //const DEV_DELAY = 10000;
      const useDelay = DELAY; // change to DELAY for production

      setTimeout(() => {
        // This runs outside Redux store, so we dispatch via window (we'll handle in UI)
        // Use a custom event to notify the app to update the Redux state.
        window.dispatchEvent(new CustomEvent("autoDeliver", { detail: { id } }));
      }, useDelay);
    },
    markDelivered(state, action) {
      const id = action.payload;
      const o = state.list.find((x) => x.id === id);
      if (o) o.status = "Delivered";
      persist(state);
    },
    cancelOrder(state, action) {
      const id = action.payload;
      const o = state.list.find((x) => x.id === id);
      if (o) o.status = "Cancelled";
      persist(state);
    },
  },
});

export const { placeOrder, markDelivered, cancelOrder } = orderSlice.actions;
export default orderSlice.reducer;
