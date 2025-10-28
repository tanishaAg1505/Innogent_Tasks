# services.py
from Model.models import Product, FoodProduct

LOW_STOCK = 5

def list_products(products):
    if not products:
        print("\nNo products found.\n")
        return
    print("\nAll Products:")
    for p in products:
        print(p.describe())
    print()

def low_stock(products):
    print("\nLow Stock Products (≤ 5):")
    found = False
    for p in products:
        if p.stock <= LOW_STOCK:
            print(p.describe())
            found = True
    if not found:
        print("All products have sufficient stock.\n")

def add_product(products, next_id):
    print("\nAdd New Product:")
    name = input("Name: ")
    stock = int(input("Stock: "))
    price = float(input("Price: "))
    location = input("Location: ")
    tags = set(input("Tags (comma separated): ").lower().split(","))
    is_food = input("Is this a Food Product? (y/n): ").lower()

    if is_food == "y":
        expiry = input("Enter expiry date (e.g., 2025-12-01): ")
        prod = FoodProduct(next_id, name, stock, price, location, tags, expiry)
    else:
        prod = Product(next_id, name, stock, price, location, tags)

    products.append(prod)
    print("Product added successfully!\n")
    return next_id + 1

def update_stock(products):
    pid = int(input("Enter product ID: "))
    for p in products:
        if p.id == pid:
            p.stock = int(input("Enter new stock: "))
            print(" Stock updated!\n")
            return
    print(" Product not found!\n")

def delete_product(products):
    pid = int(input("Enter product ID to delete: "))
    for p in products:
        if p.id == pid:
            products.remove(p)
            print("🗑 Product deleted!\n")
            return
    print(" Product not found!\n")

def total_value(products):
    total = sum(p.value() for p in products)
    print(f"\n Total value of all products: ₹{total}\n")

def apply_discount(products):
    print("\nApplying 50% discount on 'clearance' items:")
    found = False
    for p in products:
        if "clearance" in p.tags:
            new_price = p.price * 0.5
            print(f"- {p.name}: Old ₹{p.price} → New ₹{new_price}")
            found = True
    if not found:
        print("No clearance products found.\n")
