# app/routes/category_routes.py

"""
Category Routes
---------------
In Java: similar to a CategoryController with endpoints for CRUD operations.
"""

from fastapi import APIRouter, HTTPException
from prisma import Prisma
from app.schemas.category_schema import CategoryCreate, CategoryResponse

router = APIRouter(prefix="/categories", tags=["Categories"])
prisma = Prisma()

@router.post("/", response_model=CategoryResponse)
async def create_category(category: CategoryCreate):
    await prisma.connect()
    existing = await prisma.category.find_first(where={"name": category.name})
    if existing:
        await prisma.disconnect()
        raise HTTPException(status_code=400, detail="Category already exists.")
    new_category = await prisma.category.create(data=category.dict())
    await prisma.disconnect()
    return new_category

@router.get("/", response_model=list[CategoryResponse])
async def get_categories():
    await prisma.connect()
    categories = await prisma.category.find_many()
    await prisma.disconnect()
    return categories

@router.get("/{category_id}", response_model=CategoryResponse)
async def get_category(category_id: int):
    await prisma.connect()
    category = await prisma.category.find_unique(where={"id": category_id})
    await prisma.disconnect()
    if not category:
        raise HTTPException(status_code=404, detail="Category not found")
    return category

@router.delete("/{category_id}")
async def delete_category(category_id: int):
    await prisma.connect()
    category = await prisma.category.find_unique(where={"id": category_id})
    if not category:
        await prisma.disconnect()
        raise HTTPException(status_code=404, detail="Category not found")
    await prisma.category.delete(where={"id": category_id})
    await prisma.disconnect()
    return {"message": "Category deleted successfully"}