from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from app.database import get_db
from app.schemas.category_schema import CategoryCreate, CategoryResponse
from app.crud.category_crud import (
    create_category,
    get_all_categories,
    get_category_by_id,
    update_category,
    delete_category
)

router = APIRouter(prefix="/categories", tags=["Categories"])


#  Create category
@router.post("/", response_model=CategoryResponse)
def create_new_category(category: CategoryCreate, db: Session = Depends(get_db)):
    return create_category(db, category)


#  Read all categories
@router.get("/", response_model=list[CategoryResponse])
def read_categories(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    return get_all_categories(db, skip, limit)


#  Read one category
@router.get("/{category_id}", response_model=CategoryResponse)
def read_category(category_id: int, db: Session = Depends(get_db)):
    db_category = get_category_by_id(db, category_id)
    if not db_category:
        raise HTTPException(status_code=404, detail="Category not found")
    return db_category


#  Update category
@router.put("/{category_id}", response_model=CategoryResponse)
def update_existing_category(category_id: int, category: CategoryCreate, db: Session = Depends(get_db)):
    updated = update_category(db, category_id, category)
    if not updated:
        raise HTTPException(status_code=404, detail="Category not found")
    return updated


# Delete category
@router.delete("/{category_id}")
def remove_category(category_id: int, db: Session = Depends(get_db)):
    if not delete_category(db, category_id):
        raise HTTPException(status_code=404, detail="Category not found")
    return {"message": "Category deleted successfully"}