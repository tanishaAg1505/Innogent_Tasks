from pydantic import BaseModel

class CompanyBase(BaseModel):
    name: str
    # price: float
    # category_id: int
    company_id: int

class CompanyCreate(CompanyBase):
    pass

class CompanyResponse(CompanyBase):
    id: int
    class Config:
        orm_mode = True