from pydantic import BaseModel

class CategoryBase(BaseModel):
    name: str
    # price: float
    category_id: int
    # company_id: int

class CategoryCreate(CategoryBase):
    pass

class CategoryResponse(CategoryBase):
    id: int
    class Config:
        orm_mode = True