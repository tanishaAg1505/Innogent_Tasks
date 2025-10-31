from fastapi import FastAPI
from app.database import Base, engine
from app.models import product_model, company_model, category_model
from app.routes import product_routes, company_routes, category_routes

# Create all tables in DB

app = FastAPI(title="Product Management API (MySQL)")

Base.metadata.create_all(bind=engine)
# Include routes
app.include_router(product_routes.router)
app.include_router(company_routes.router)
app.include_router(category_routes.router)

@app.get("/")
def root():
    return {"message": "Welcome to Product Management API"}