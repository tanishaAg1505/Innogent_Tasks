# app/routes/company_routes.py

"""
Company Routes
---------------
In Java: similar to a Controller class that handles Company-related requests.
Uses Prisma ORM for database operations.
"""

from fastapi import APIRouter, HTTPException
from prisma import Prisma
from app.schemas.company_schema import CompanyCreate, CompanyResponse

router = APIRouter(prefix="/companies", tags=["Companies"])
prisma = Prisma()

# Create a new company
@router.post("/", response_model=CompanyResponse)
async def create_company(company: CompanyCreate):
    await prisma.connect()
    existing = await prisma.company.find_first(where={"name": company.name})
    if existing:
        await prisma.disconnect()
        raise HTTPException(status_code=400, detail="Company already exists.")
    new_company = await prisma.company.create(data=company.dict())
    await prisma.disconnect()
    return new_company

# Get all companies
@router.get("/", response_model=list[CompanyResponse])
async def get_companies():
    await prisma.connect()
    companies = await prisma.company.find_many()
    await prisma.disconnect()
    return companies

# Get company by ID
@router.get("/{company_id}", response_model=CompanyResponse)
async def get_company(company_id: int):
    await prisma.connect()
    company = await prisma.company.find_unique(where={"id": company_id})
    await prisma.disconnect()
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")
    return company

# Delete a company
@router.delete("/{company_id}")
async def delete_company(company_id: int):
    await prisma.connect()
    company = await prisma.company.find_unique(where={"id": company_id})
    if not company:
        await prisma.disconnect()
        raise HTTPException(status_code=404, detail="Company not found")
    await prisma.company.delete(where={"id": company_id})
    await prisma.disconnect()
    return {"message": "Company deleted successfully"}