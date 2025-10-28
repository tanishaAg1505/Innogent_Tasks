class Product:
    def __init__(self, id, name, stock, price, location, tags):
        self.id = id
        self.name = name
        self.stock = stock
        self.price = price
        self.location = location
        self.tags = tags  # set of tags

    def value(self):
        """Return total value of product in stock."""
        return self.stock * self.price

    def describe(self):
        """Return product info string."""
        tags_str = ", ".join(self.tags)
        return f"{self.id}. {self.name} | Stock: {self.stock} | Price: ₹{self.price} | Location: {self.location} | Tags: {tags_str}"


# Inheritance Example
class FoodProduct(Product):
    def __init__(self, id, name, stock, price, location, tags, expiry_date):
        super().__init__(id, name, stock, price, location, tags)
        self.expiry_date = expiry_date

    # Polymorphism (method overriding)
    def describe(self):
        return super().describe() + f" | Expiry: {self.expiry_date}"
