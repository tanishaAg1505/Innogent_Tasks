# app/config/database.py


from prisma import Prisma
from typing import Generator

# Creating Prisma client instance (singleton for the app)
# In Java it is like "private static final SessionFactory sessionFactory = ..."
prisma = Prisma()


async def connect_db() -> None:
    """
    Connect Prisma client to the database.
    This should be called during application startup (FastAPI @app.on_event("startup")).

    Java comparison:
    - Similar to opening a pooled DataSource or initializing Hibernate's SessionFactory.
    """
    await prisma.connect()
    
    print("Prisma client connected to DB.")


async def disconnect_db() -> None:
    """
    Disconnect Prisma client from the database.
    This should be called during application shutdown (FastAPI @app.on_event("shutdown")).

    Java comparison:
    - Similar to closing DataSource connections or destroying the SessionFactory at shutdown.
    """
    await prisma.disconnect()
    print(" Prisma client disconnected from DB.")


def get_db() -> Prisma:
    """
    Dependency to return the Prisma client.
    Use this in FastAPI routes like: db: Prisma = Depends(get_db)

    Notes:
    - Prisma client is asynchronous but can be used from sync routes as well through the generated
      sync wrappers (depending on the prisma version). For simplicity we return the client directly.
    - If you need per-request transactional sessions, we will create functions to begin/commit/rollback
      transactions in service layer (we'll add that later if required).
    """
    return prisma