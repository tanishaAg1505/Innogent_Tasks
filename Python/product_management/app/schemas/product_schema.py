from pydantic import BaseModel

class ProductBase(BaseModel):
    name: str
    price: float
    category_id: int
    company_id: int

class ProductCreate(ProductBase):
    pass

class ProductResponse(ProductBase):
    id: int
    class Config:
        orm_mode = True