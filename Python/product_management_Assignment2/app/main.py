# main.py
from fastapi import FastAPI
from prisma import Prisma
from app.routes import product_routes, company_routes, category_routes


# Create FastAPI app instance

# In Java it is similar to creating a Spring Boot application class
app = FastAPI(title="Product Management API - Assignment 2 (Prisma Version)")


# Initializing Prisma client

# In Java it is similar to creating a Hibernate SessionFactory or JDBC connection)
prisma = Prisma()


# Startup and Shutdown events

# These run automatically when the API starts or stops
@app.on_event("startup")
async def startup():
    await prisma.connect()
    print("Database connected successfully.")

@app.on_event("shutdown")
async def shutdown():
    await prisma.disconnect()
    print(" Database connection closed.")

# ----------------------------------------------
# Include all routers (like Controllers in Java)
# ----------------------------------------------
app.include_router(company_routes.router)
app.include_router(category_routes.router)
app.include_router(product_routes.router)

# ----------------------------------------------
# Root endpoint (simple test route)
# ----------------------------------------------
@app.get("/")
def root():
    return {"message": "Product Management API with Prisma is running successfully!"}