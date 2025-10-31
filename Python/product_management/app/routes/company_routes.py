from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from app.database import get_db
from app.schemas.company_schema import CompanyCreate, CompanyResponse
from app.crud.company_crud import (
    create_company,
    get_all_companies,
    get_company_by_id,
    update_company,
    delete_company
)

router = APIRouter(prefix="/companies", tags=["Companies"])


#  Create company
@router.post("/", response_model=CompanyResponse)
def create_new_company(company: CompanyCreate, db: Session = Depends(get_db)):
    return create_company(db, company)


#  Read all companies
@router.get("/", response_model=list[CompanyResponse])
def read_companies(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    return get_all_companies(db, skip, limit)


#  Read one company
@router.get("/{company_id}", response_model=CompanyResponse)
def read_company(company_id: int, db: Session = Depends(get_db)):
    db_company = get_company_by_id(db, company_id)
    if not db_company:
        raise HTTPException(status_code=404, detail="Company not found")
    return db_company


#  Update company
@router.put("/{company_id}", response_model=CompanyResponse)
def update_existing_company(company_id: int, company: CompanyCreate, db: Session = Depends(get_db)):
    updated = update_company(db, company_id, company)
    if not updated:
        raise HTTPException(status_code=404, detail="Company not found")
    return updated


#  Delete company
@router.delete("/{company_id}")
def remove_company(company_id: int, db: Session = Depends(get_db)):
    if not delete_company(db, company_id):
        raise HTTPException(status_code=404, detail="Company not found")
    return {"message": "Company deleted successfully"}
