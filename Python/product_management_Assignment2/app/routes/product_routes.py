# app/routes/product_routes.py

"""
Product Routes
---------------
In Java: similar to ProductController or Service layer using Hibernate Repository.
Includes CRUD + Search + Pagination.
"""

from fastapi import APIRouter, HTTPException, Query
from prisma import Prisma
from app.schemas.product_schema import ProductCreate, ProductResponse



router = APIRouter(prefix="/products", tags=["Products"])
prisma = Prisma()

# Create Product
@router.post("/", response_model=ProductResponse)
async def create_product(product: ProductCreate):
    await prisma.connect()
    # check duplicate
    existing = await prisma.product.find_first(where={"name": product.name})
    if existing:
        await prisma.disconnect()
        raise HTTPException(status_code=400, detail="Product already exists.")
    new_product = await prisma.product.create(data=product.dict())
    await prisma.disconnect()
    return new_product

# Get all products with company and category names
@router.get("/", response_model=list[ProductResponse])
async def get_products(skip: int = 0, limit: int = 10):
    await prisma.connect()
    products = await prisma.product.find_many(
        skip=skip,
        take=limit,
        include={           # this will also fetch related company and category
            "company": True,
            "category": True
        }
    )
    await prisma.disconnect()
    return products


# Get single product by ID (with company and category names)
@router.get("/{product_id}", response_model=ProductResponse)
async def get_product(product_id: int):
    await prisma.connect()
    product = await prisma.product.find_unique(
        where={"id": product_id},
        include={           # also fetch company and category details
            "company": True,
            "category": True
        }
    )
    await prisma.disconnect()
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    return product


# Delete product
@router.delete("/{product_id}")
async def delete_product(product_id: int):
    await prisma.connect()
    product = await prisma.product.find_unique(where={"id": product_id})
    if not product:
        await prisma.disconnect()
        raise HTTPException(status_code=404, detail="Product not found")
    await prisma.product.delete(where={"id": product_id})
    await prisma.disconnect()
    return {"message": "Product deleted successfully"}

# Search products by name, category, price, or company
@router.get("/search/", response_model=list[ProductResponse])
async def search_products(
    q: str = Query("", description="Search keyword"),
    company_id: int | None = None,
    skip: int = 0,
    limit: int = 10
):
    await prisma.connect()
    where_clause = {
        "OR": [
            {"name": {"contains": q, "mode": "insensitive"}},
            {"description": {"contains": q, "mode": "insensitive"}}
        ]
    }
    if company_id:
        where_clause["company_id"] = company_id
    products = await prisma.product.find_many(where=where_clause, skip=skip, take=limit)
    await prisma.disconnect()
    return products