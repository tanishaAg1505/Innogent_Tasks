from sqlalchemy.orm import Session
from app.models.company_model import Company
from app.schemas.company_schema import CompanyCreate

#  Create company
def create_company(db: Session, company: CompanyCreate):
    db_company = Company(**company.dict())
    db.add(db_company)
    db.commit()
    db.refresh(db_company)
    return db_company


#  Read all companies
def get_all_companies(db: Session, skip: int = 0, limit: int = 10):
    return db.query(Company).offset(skip).limit(limit).all()


#  Read company by ID
def get_company_by_id(db: Session, company_id: int):
    return db.query(Company).filter(Company.id == company_id).first()


#  Update company
def update_company(db: Session, company_id: int, company_data: CompanyCreate):
    db_company = get_company_by_id(db, company_id)
    if not db_company:
        return None
    for key, value in company_data.dict().items():
        setattr(db_company, key, value)
    db.commit()
    db.refresh(db_company)
    return db_company


#  Delete company
def delete_company(db: Session, company_id: int):
    db_company = get_company_by_id(db, company_id)
    if db_company:
        db.delete(db_company)
        db.commit()
        return True
    return False
