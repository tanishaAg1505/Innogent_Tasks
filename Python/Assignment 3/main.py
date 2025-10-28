
from Service.services import (
    list_products, low_stock, add_product,
    update_stock, delete_product, total_value,
    apply_discount
)
from Service.stats_services import stats_report   # ✅ new import
from Model.models import Product, FoodProduct

def main():
    # Preloaded products
    products = [
        Product(1, "Soap", 3, 40, "shelf-1", {"grocery", "clearance"}),
        FoodProduct(2, "Bread", 2, 50, "shelf-2", {"grocery"}, "2025-11-05"),
        Product(3, "Rice Bag", 10, 600, "shelf-3", {"grocery"}),
        Product(4, "Toothpaste", 4, 70, "shelf-4", {"clearance"}),
    ]
    next_id = len(products) + 1

    while True:
        print("""
====== Product Tracker (With NumPy Stats) ======
1. List all products
2. Low stock warning
3. Add product
4. Update stock
5. Delete product
6. Total stock value
7. Apply discount (clearance)
8. Stats report (NumPy)
9. Exit
================================================
""")
        choice = input("Enter choice (1–9): ")

        if choice == "1": list_products(products)
        elif choice == "2": low_stock(products)
        elif choice == "3": next_id = add_product(products, next_id)
        elif choice == "4": update_stock(products)
        elif choice == "5": delete_product(products)
        elif choice == "6": total_value(products)
        elif choice == "7": apply_discount(products)
        elif choice == "8": stats_report(products)
        elif choice == "9":
            print("Exiting program. Goodbye!")
            break
        else:
            print(" Invalid choice, try again.\n")

if __name__ == "__main__":
    main()