# app/main.py
from fastapi import FastAPI, UploadFile, File, HTTPException, Query
from fastapi.responses import StreamingResponse
import pandas as pd
from io import BytesIO, StringIO
import csv
from prisma import Prisma
from typing import Optional
from pydantic import BaseModel

app = FastAPI(title="Product Manager - Assignment 3")
prisma = Prisma()  # async client (matches interface = "asyncio")

class ProductIn(BaseModel):
    name: str
    sku: Optional[str] = None
    description: Optional[str] = None
    price: float
    quantity: int

@app.on_event("startup")
async def startup():
    await prisma.connect()

@app.on_event("shutdown")
async def shutdown():
    await prisma.disconnect()

@app.post("/upload-csv")
async def upload_csv(file: UploadFile = File(...)):
    # expect CSV with columns: name,sku,description,price,quantity
    if file.content_type not in ("text/csv", "application/vnd.ms-excel", "text/plain"):
        raise HTTPException(status_code=400, detail="Please upload a CSV file")
    content = await file.read()
    try:
        df = pd.read_csv(StringIO(content.decode('utf-8')))
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"CSV parse error: {e}")

    # Validate columns
    expected = {"name", "price", "quantity"}
    if not expected.issubset(set(df.columns)):
        raise HTTPException(status_code=400, detail=f"CSV must contain columns: {expected}")

    created = []
    for idx, row in df.iterrows():
        # convert types safely
        try:
            price = float(row.get('price', 0))
            quantity = int(row.get('quantity', 0))
        except Exception:
            continue
        prod = await prisma.product.create(
            data={
                "name": str(row.get('name')),
                "sku": row.get('sku') if 'sku' in df.columns else None,
                "description": row.get('description') if 'description' in df.columns else None,
                "price": price,
                "quantity": quantity
            }
        )
        created.append({"id": prod.id, "name": prod.name})
    return {"inserted": len(created), "examples": created[:5]}

@app.post("/products")
async def add_product(payload: ProductIn):
    prod = await prisma.product.create(
        data={
            "name": payload.name,
            "sku": payload.sku,
            "description": payload.description,
            "price": payload.price,
            "quantity": payload.quantity
        }
    )
    return {"id": prod.id, "name": prod.name}

@app.get("/export")
async def export_data(format: str = Query("csv", regex="^(csv|xlsx|xls)$"), limit: Optional[int] = None):
    # fetch from DB
    q = {}
    if limit:
        products = await prisma.product.find_many(take=limit, order={"id": "asc"})
    else:
        products = await prisma.product.find_many(order={"id": "asc"})

    # Convert to dataframe
    rows = []
    for p in products:
        rows.append({
            "id": p.id,
            "name": p.name,
            "sku": p.sku,
            "description": p.description,
            "price": p.price,
            "quantity": p.quantity,
            "created_at": p.created_at.isoformat() if p.created_at else None
        })
    df = pd.DataFrame(rows)

    if format == "csv":
        buf = StringIO()
        df.to_csv(buf, index=False)
        buf.seek(0)
        return StreamingResponse(iter([buf.getvalue().encode("utf-8")]),
                                 media_type="text/csv",
                                 headers={"Content-Disposition": "attachment; filename=products.csv"})
    else:
        buf = BytesIO()
        # pandas uses openpyxl for xlsx and xlwt for xls (we installed openpyxl & xlwt)
        writer = pd.ExcelWriter(buf, engine="openpyxl" if format=="xlsx" else "xlwt")
        df.to_excel(writer, index=False, sheet_name="Products")
        writer.save()
        buf.seek(0)
        ext = "xlsx" if format=="xlsx" else "xls"
        return StreamingResponse(buf,
                                 media_type="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" if ext=="xlsx" else "application/vnd.ms-excel",
                                 headers={"Content-Disposition": f"attachment; filename=products.{ext}"})
