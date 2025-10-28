
LOW_STOCK = 5
products = [
            {"id" :1 , "name": "Soap", "stock": 3,"price": 40.0, "location": "shelf-1","tags": {"grocery", "clearance"} },
            { "id": 2, "name": "Rice Bag", "stock": 10, "price": 600.0, "location": "shelf-2","tags": {"grocery"}},
            {"id": 3, "name": "Toothpaste", "stock": 4, "price": 70.0, "location": "shelf-3", "tags": {"grocery", "clearance"}}
            ]
# Function to show low stock warnings
def list_products():
    if not products :
        print("Products not found. ")
    else :
        print("\n --Products List--")
        for p in products:
            print(f"ID: {p['id']} , Name: {p['name']} , Stock: {p['stock']}, Price: {p['price']}, Location: {p['location']}, Tags: {p['tags']}")

# Function to show low stock warnings
def low_stock():
    print("\n--- Low Stock Products ---")
    found = False
    for p in products:
        if p["stock"]<LOW_STOCK:
            print(f"{p['name']} has low stock {p['stock']}")
            found=True
            if not found:
                print("No low stock products ")        

# Function to add a new product
def add_product(next_id):
    name = input("Enter product name: ")
    stock = int(input("Enter stock: "))
    price = float(input("Enter price: "))
    location = input("Enter location: ")
    tags_input = input("Enter tags : ")   

    tags = set(tag.strip().lower() for tag in tags_input.split(",") if tag.strip())
    product = {"id" : next_id , "name":name , "stock":stock , "price" : price, "location":location , "tags": tags}
    products.append(product)
    print("Product added successfully ! ")
    return next_id+1 #return next id for next product

# Function to update stock
def update_stock():
  pid = int(input("Enter product ID to update stock : "))
  for p in products :
      if p["id"]==pid:
          new_stock = int(input(f"Enter the new stock for {p['name']}"))
          p["stock"] = new_stock
          print("Stock added successfully")

# Function to delete product
def delete_product():
    pid = int(input("Enter the id of product you want to delete :"))
    for p in products:
       if p["id"]==pid :
           products.remove(p)
           print("Product deleted successfully")
           return 
       print("Product not found\n")

#Function to calculate total value
def total_value():
    total=0
    for p in products:
        total+=p["price"]*p["stock"]
        print(f"Total value of all stock : {total}\n")       

def apply_discount():
    print("\n--- Applying 50% discount to 'clearance' items ---")
    for p in products:
        if "clearance" in p["tags"]:
            new_price = p["price"] * 0.5
            print(f"{p['name']} | Old Price: {p['price']} | New Price: {new_price}")


def main():

    print("----Welcome to the Product Inventory---")    
    next_id = len(products) + 1  # continue ID numbering from product        
    while True:
        print("""
Choose an option:
1. List all products
2. Low stock warnings
3. Add product
4. Update stock
5. Delete product
6. Total value of all products
7. Apply discount by tag (clearance -> 50%)
8. Exit
""")
        choice = input("Enter your choice (1-8): ")

        if choice == "1":
           list_products()
        elif choice == "2":
            low_stock()
        elif choice == "3":
            next_id = add_product(next_id)
        elif choice == "4":
            update_stock()
        elif choice == "5":
            delete_product()
        elif choice == "6":
            total_value()
        elif choice == "7":
            apply_discount()
        elif choice == "8":
            print("Exiting....")
            break
        else:
            print(" Invalid choice, please try again.\n")

# Run the program
main()