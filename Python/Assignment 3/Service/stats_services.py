import numpy as np
from Model.models import Product, FoodProduct
def stats_report(products):
    if not products:
        print("\nNo products to analyze.\n")
        return

    prices = np.array([p.price for p in products])
    stocks = np.array([p.stock for p in products])
    values = np.array([p.price * p.stock for p in products])

    print("\n====== Inventory Statistics ======")
    print(f"Average price of items: ₹{np.mean(prices):.2f}")
    print(f"Most expensive item price: ₹{np.max(prices):.2f}")
    print(f"Total count of all items in stock: {np.sum(stocks)}")
    print("-----------------------------------")

    print("\nTotal inventory value for each product:")
    for p in products:
        print(f"- {p.name}: ₹{p.price * p.stock}")

    print("-----------------------------------")
    tag = input("\nEnter tag to filter stats (e.g., 'clearance'): ").lower()
    tagged_products = [p for p in products if tag in p.tags]

    if tagged_products:
        tag_prices = np.array([p.price for p in tagged_products])
        tag_values = np.array([p.price * p.stock for p in tagged_products])
        print(f"\nStats for tag '{tag}':")
        print(f"Average price: ₹{np.mean(tag_prices):.2f}")
        print(f"Total value: ₹{np.sum(tag_values):.2f}")
    else:
        print(f"\nNo products found with tag '{tag}'.")
    print("===================================\n")