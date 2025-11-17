//yeh file humare pure app ko html ke root element mai inject karti hai
import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import { Provider } from "react-redux";
import App from "./App";
import store from "./redux/store";
import "./index.css";

window.addEventListener("autoDeliver", (e) => {
  const { id } = e.detail;
  store.dispatch({ type: "orders/markDelivered", payload: id });
});

ReactDOM.createRoot(document.getElementById('root')).render(
  //BrowserRouter imp hai so that we can use routes of react router (for navigating between pages)
   <React.StrictMode>
    {/* Provider makes the redux store available to all components */}
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
);