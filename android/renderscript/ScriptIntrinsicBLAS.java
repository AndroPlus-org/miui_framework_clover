// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Element, RenderScript, RSRuntimeException, 
//            Allocation, Type, Float2, Double2

public final class ScriptIntrinsicBLAS extends ScriptIntrinsic
{

    private ScriptIntrinsicBLAS(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static ScriptIntrinsicBLAS create(RenderScript renderscript)
    {
        return new ScriptIntrinsicBLAS(renderscript.nScriptIntrinsicCreate(13, Element.U32(renderscript).getID(renderscript)), renderscript);
    }

    static void validateConjTranspose(int i)
    {
        if(i != 111 && i != 113)
            throw new RSRuntimeException("Invalid transpose passed to BLAS");
        else
            return;
    }

    static void validateDiag(int i)
    {
        if(i != 131 && i != 132)
            throw new RSRuntimeException("Invalid diag passed to BLAS");
        else
            return;
    }

    static void validateGEMV(Element element, int i, Allocation allocation, Allocation allocation1, int j, Allocation allocation2, int k)
    {
        validateTranspose(i);
        int l = allocation.getType().getY();
        int i1 = allocation.getType().getX();
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true || allocation2.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation1.getType().getY() > 1 || allocation2.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(j <= 0 || k <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(i == 111)
        {
            i = (i1 - 1) * j + 1;
            j = (l - 1) * k + 1;
        } else
        {
            i = (l - 1) * j + 1;
            j = (i1 - 1) * k + 1;
        }
        if(allocation1.getType().getX() != i || allocation2.getType().getX() != j)
            throw new RSRuntimeException("Incorrect vector dimensions for GEMV");
        else
            return;
    }

    static void validateGER(Element element, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        if(!allocation2.getType().getElement().isCompatible(element) || allocation.getType().getElement().isCompatible(element) ^ true || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation.getType().getY() > 1 || allocation1.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        if(l < 1 || k < 1)
            throw new RSRuntimeException("M and N must be 1 or greater for GER");
        if(i <= 0 || j <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation.getType().getX() != (k - 1) * i + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for GER");
        if(allocation1.getType().getX() != (l - 1) * j + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for GER");
        else
            return;
    }

    static void validateGERU(Element element, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        if(!allocation2.getType().getElement().isCompatible(element) || allocation.getType().getElement().isCompatible(element) ^ true || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation.getType().getY() > 1 || allocation1.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        if(i <= 0 || j <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation.getType().getX() != (k - 1) * i + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for GERU");
        if(allocation1.getType().getX() != (l - 1) * j + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for GERU");
        else
            return;
    }

    static void validateHEMM(Element element, int i, Allocation allocation, Allocation allocation1, Allocation allocation2)
    {
        validateSide(i);
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true || allocation2.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        int j = allocation.getType().getX();
        if(j != allocation.getType().getY())
            throw new RSRuntimeException("Called HEMM with non-square A");
        while(i == 141 && j != allocation1.getType().getY() || i == 142 && j != allocation1.getType().getX()) 
            throw new RSRuntimeException("Called HEMM with invalid B");
        if(allocation1.getType().getX() != allocation2.getType().getX() || allocation1.getType().getY() != allocation2.getType().getY())
            throw new RSRuntimeException("Called HEMM with mismatched B and C");
        else
            return;
    }

    static void validateHER2K(Element element, int i, Allocation allocation, Allocation allocation1, Allocation allocation2)
    {
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true || allocation2.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        validateConjTranspose(i);
        int j = allocation2.getType().getX();
        if(j != allocation2.getType().getY())
            throw new RSRuntimeException("Called HER2K with non-square C");
        if(i == 111)
        {
            if(allocation.getType().getY() != j)
                throw new RSRuntimeException("Called HER2K with invalid matrices");
        } else
        if(allocation.getType().getX() != j)
            throw new RSRuntimeException("Called HER2K with invalid matrices");
        if(allocation.getType().getX() != allocation1.getType().getX() || allocation.getType().getY() != allocation1.getType().getY())
            throw new RSRuntimeException("Called HER2K with invalid A and B matrices");
        else
            return;
    }

    static void validateHERK(Element element, int i, Allocation allocation, Allocation allocation1)
    {
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        validateConjTranspose(i);
        int j = allocation1.getType().getX();
        if(j != allocation1.getType().getY())
            throw new RSRuntimeException("Called HERK with non-square C");
        if(i == 111)
        {
            if(j != allocation.getType().getY())
                throw new RSRuntimeException("Called HERK with invalid A");
        } else
        if(j != allocation.getType().getX())
            throw new RSRuntimeException("Called HERK with invalid A");
    }

    static void validateL3(Element element, int i, int j, int k, Allocation allocation, Allocation allocation1, Allocation allocation2)
    {
        int l;
        int i1;
        byte byte0;
        int j1;
        int k1;
        int l1;
        l = -1;
        i1 = -1;
        byte0 = -1;
        j1 = -1;
        if(allocation != null && allocation.getType().getElement().isCompatible(element) ^ true || allocation1 != null && allocation1.getType().getElement().isCompatible(element) ^ true || allocation2 != null && allocation2.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation2 == null)
            throw new RSRuntimeException("Allocation C cannot be null");
        k1 = allocation2.getType().getY();
        l1 = allocation2.getType().getX();
        if(k != 142) goto _L2; else goto _L1
_L1:
        int i2;
        while(allocation == null && allocation1 != null || allocation != null && allocation1 == null) 
            throw new RSRuntimeException("Provided Matrix A without Matrix B, or vice versa");
        k = byte0;
        j = j1;
        if(allocation1 != null)
        {
            k = allocation.getType().getY();
            j = allocation.getType().getX();
        }
        i2 = i1;
        i = k;
        j1 = j;
        if(allocation != null)
        {
            l = allocation1.getType().getY();
            i2 = allocation1.getType().getX();
            j1 = j;
            i = k;
        }
_L8:
        if(allocation != null && allocation1 != null && allocation2 != null)
            break MISSING_BLOCK_LABEL_246;
        if(allocation == null || allocation2 == null)
            continue; /* Loop/switch isn't completed */
        if(k1 != l1)
            throw new RSRuntimeException("Matrix C is not symmetric");
          goto _L3
_L2:
        k = l;
        if(allocation != null)
            if(i == 112 || i == 113)
            {
                i1 = allocation.getType().getY();
                k = allocation.getType().getX();
            } else
            {
                k = allocation.getType().getY();
                i1 = allocation.getType().getX();
            }
        l = k;
        i2 = i1;
        i = byte0;
        if(allocation1 != null)
            if(j == 112 || j == 113)
            {
                j1 = allocation1.getType().getY();
                i = allocation1.getType().getX();
                l = k;
                i2 = i1;
            } else
            {
                i = allocation1.getType().getY();
                j1 = allocation1.getType().getX();
                l = k;
                i2 = i1;
            }
        continue; /* Loop/switch isn't completed */
        if(i2 != i || l != k1 || j1 != l1)
            throw new RSRuntimeException("Called BLAS with invalid dimensions");
_L5:
        return;
_L3:
        if(l == k1) goto _L5; else goto _L4
_L4:
        throw new RSRuntimeException("Called BLAS with invalid dimensions");
        if(allocation == null || allocation1 == null || i2 == i) goto _L5; else goto _L6
_L6:
        throw new RSRuntimeException("Called BLAS with invalid dimensions");
        if(true) goto _L8; else goto _L7
_L7:
    }

    static int validateSPMV(Element element, int i, Allocation allocation, Allocation allocation1, int j, Allocation allocation2, int k)
    {
        validateUplo(i);
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true || allocation2.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation1.getType().getY() > 1 || allocation2.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(allocation.getType().getY() > 1)
            throw new RSRuntimeException("Ap must have a Y dimension of 0 or 1");
        i = (int)Math.sqrt((double)allocation.getType().getX() * 2D);
        if(allocation.getType().getX() != ((i + 1) * i) / 2)
            throw new RSRuntimeException("Invalid dimension for Ap");
        if(j <= 0 || k <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation1.getType().getX() != (i - 1) * j + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SPMV");
        if(allocation2.getType().getX() != (i - 1) * k + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SPMV");
        else
            return i;
    }

    static int validateSPR(Element element, int i, Allocation allocation, int j, Allocation allocation1)
    {
        validateUplo(i);
        if(!allocation1.getType().getElement().isCompatible(element) || allocation.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(allocation1.getType().getY() > 1)
            throw new RSRuntimeException("Ap must have a Y dimension of 0 or 1");
        i = (int)Math.sqrt((double)allocation1.getType().getX() * 2D);
        if(allocation1.getType().getX() != ((i + 1) * i) / 2)
            throw new RSRuntimeException("Invalid dimension for Ap");
        if(j <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation.getType().getX() != (i - 1) * j + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SPR");
        else
            return i;
    }

    static int validateSPR2(Element element, int i, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        validateUplo(i);
        if(!allocation2.getType().getElement().isCompatible(element) || allocation.getType().getElement().isCompatible(element) ^ true || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation.getType().getY() > 1 || allocation1.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(allocation2.getType().getY() > 1)
            throw new RSRuntimeException("Ap must have a Y dimension of 0 or 1");
        i = (int)Math.sqrt((double)allocation2.getType().getX() * 2D);
        if(allocation2.getType().getX() != ((i + 1) * i) / 2)
            throw new RSRuntimeException("Invalid dimension for Ap");
        if(j <= 0 || k <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation.getType().getX() != (i - 1) * j + 1 || allocation1.getType().getX() != (i - 1) * k + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SPR2");
        else
            return i;
    }

    static int validateSYMV(Element element, int i, Allocation allocation, Allocation allocation1, Allocation allocation2, int j, int k)
    {
        validateUplo(i);
        i = allocation.getType().getY();
        if(allocation.getType().getX() != i)
            throw new RSRuntimeException("A must be a square matrix for SYMV");
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true || allocation2.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation1.getType().getY() > 1 || allocation2.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(j <= 0 || k <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation1.getType().getX() != (i - 1) * j + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SYMV");
        if(allocation2.getType().getX() != (i - 1) * k + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SYMV");
        else
            return i;
    }

    static int validateSYR(Element element, int i, Allocation allocation, int j, Allocation allocation1)
    {
        validateUplo(i);
        if(!allocation1.getType().getElement().isCompatible(element) || allocation.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        i = allocation1.getType().getX();
        if(allocation.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(i != allocation1.getType().getY())
            throw new RSRuntimeException("A must be a symmetric matrix");
        if(j <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation.getType().getX() != (i - 1) * j + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SYR");
        else
            return i;
    }

    static int validateSYR2(Element element, int i, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        validateUplo(i);
        if(!allocation2.getType().getElement().isCompatible(element) || allocation.getType().getElement().isCompatible(element) ^ true || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation.getType().getY() > 1 || allocation1.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        i = allocation2.getType().getX();
        if(i != allocation2.getType().getY())
            throw new RSRuntimeException("A must be a symmetric matrix");
        if(j <= 0 || k <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation.getType().getX() != (i - 1) * j + 1 || allocation1.getType().getX() != (i - 1) * k + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for SYR");
        else
            return i;
    }

    static void validateSYR2K(Element element, int i, Allocation allocation, Allocation allocation1, Allocation allocation2)
    {
        validateTranspose(i);
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true || allocation2.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(i == 112)
            i = allocation.getType().getX();
        else
            i = allocation.getType().getY();
        if(allocation2.getType().getX() != i || allocation2.getType().getY() != i)
            throw new RSRuntimeException("Invalid symmetric matrix in SYR2K");
        if(allocation.getType().getX() != allocation1.getType().getX() || allocation.getType().getY() != allocation1.getType().getY())
            throw new RSRuntimeException("Invalid A and B in SYR2K");
        else
            return;
    }

    static void validateSide(int i)
    {
        if(i != 141 && i != 142)
            throw new RSRuntimeException("Invalid side passed to BLAS");
        else
            return;
    }

    static int validateTPMV(Element element, int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTranspose(j);
        validateUplo(i);
        validateDiag(k);
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation1.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(allocation.getType().getY() > 1)
            throw new RSRuntimeException("Ap must have a Y dimension of 0 or 1");
        i = (int)Math.sqrt((double)allocation.getType().getX() * 2D);
        if(allocation.getType().getX() != ((i + 1) * i) / 2)
            throw new RSRuntimeException("Invalid dimension for Ap");
        if(l <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation1.getType().getX() != (i - 1) * l + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for TPMV");
        else
            return i;
    }

    static void validateTRMM(Element element, int i, int j, Allocation allocation, Allocation allocation1)
    {
        validateSide(i);
        validateTranspose(j);
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        int k = allocation.getType().getY();
        int l = allocation.getType().getX();
        if(k != l)
            throw new RSRuntimeException("Called TRMM with a non-symmetric matrix A");
        j = allocation1.getType().getY();
        int i1 = allocation1.getType().getX();
        if(i == 141)
        {
            if(l != j)
                throw new RSRuntimeException("Called TRMM with invalid matrices");
        } else
        if(i1 != k)
            throw new RSRuntimeException("Called TRMM with invalid matrices");
    }

    static void validateTRMV(Element element, int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTranspose(j);
        validateUplo(i);
        validateDiag(k);
        i = allocation.getType().getY();
        if(allocation.getType().getX() != i)
            throw new RSRuntimeException("A must be a square matrix for TRMV");
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        if(allocation1.getType().getY() > 1)
            throw new RSRuntimeException("BLAS vectors must have Y dimension of 0 or 1");
        if(l <= 0)
            throw new RSRuntimeException("Vector increments must be greater than 0");
        if(allocation1.getType().getX() != (i - 1) * l + 1)
            throw new RSRuntimeException("Incorrect vector dimensions for TRMV");
        else
            return;
    }

    static void validateTRSM(Element element, int i, int j, Allocation allocation, Allocation allocation1)
    {
        validateSide(i);
        validateTranspose(j);
        if(!allocation.getType().getElement().isCompatible(element) || allocation1.getType().getElement().isCompatible(element) ^ true)
            throw new RSRuntimeException("Called BLAS with wrong Element type");
        int k = allocation.getType().getX();
        if(k != allocation.getType().getY())
            throw new RSRuntimeException("Called TRSM with a non-symmetric matrix A");
        int l = allocation1.getType().getY();
        j = allocation1.getType().getX();
        if(i == 141)
        {
            if(k != l)
                throw new RSRuntimeException("Called TRSM with invalid matrix dimensions");
        } else
        if(k != j)
            throw new RSRuntimeException("Called TRSM with invalid matrix dimensions");
    }

    static void validateTranspose(int i)
    {
        if(i != 111 && i != 112 && i != 113)
            throw new RSRuntimeException("Invalid transpose passed to BLAS");
        else
            return;
    }

    static void validateUplo(int i)
    {
        if(i != 121 && i != 122)
            throw new RSRuntimeException("Invalid uplo passed to BLAS");
        else
            return;
    }

    public void BNNM(Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2, int k, int l)
    {
        validateL3(Element.U8(mRS), 111, 112, 0, allocation, allocation1, allocation2);
        if(i < 0 || i > 255)
            throw new RSRuntimeException("Invalid a_offset passed to BNNM");
        if(j < 0 || j > 255)
        {
            throw new RSRuntimeException("Invalid b_offset passed to BNNM");
        } else
        {
            int i1 = allocation.getType().getY();
            int j1 = allocation1.getType().getY();
            int k1 = allocation.getType().getX();
            mRS.nScriptIntrinsicBLAS_BNNM(getID(mRS), i1, j1, k1, allocation.getID(mRS), i, allocation1.getID(mRS), j, allocation2.getID(mRS), k, l);
            return;
        }
    }

    public void CGBMV(int i, int j, int k, Float2 float2, Allocation allocation, Allocation allocation1, int l, 
            Float2 float2_1, Allocation allocation2, int i1)
    {
        validateGEMV(Element.F32_2(mRS), i, allocation, allocation1, l, allocation2, i1);
        if(j < 0 || k < 0)
        {
            throw new RSRuntimeException("KL and KU must be greater than or equal to 0");
        } else
        {
            int j1 = allocation.getType().getY();
            int k1 = allocation.getType().getX();
            mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 64, i, 0, 0, 0, 0, j1, k1, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), l, i1, j, k);
            return;
        }
    }

    public void CGEMM(int i, int j, Float2 float2, Allocation allocation, Allocation allocation1, Float2 float2_1, Allocation allocation2)
    {
        validateTranspose(i);
        validateTranspose(j);
        validateL3(Element.F32_2(mRS), i, j, 0, allocation, allocation1, allocation2);
        int k;
        int l;
        int i1;
        if(i != 111)
        {
            k = allocation.getType().getX();
            l = allocation.getType().getY();
        } else
        {
            k = allocation.getType().getY();
            l = allocation.getType().getX();
        }
        if(j != 111)
            i1 = allocation1.getType().getY();
        else
            i1 = allocation1.getType().getX();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 125, i, j, 0, 0, 0, k, i1, l, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void CGEMV(int i, Float2 float2, Allocation allocation, Allocation allocation1, int j, Float2 float2_1, Allocation allocation2, 
            int k)
    {
        validateGEMV(Element.F32_2(mRS), i, allocation, allocation1, j, allocation2, k);
        int l = allocation.getType().getY();
        int i1 = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 63, i, 0, 0, 0, 0, l, i1, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void CGERC(Float2 float2, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        validateGERU(Element.F32_2(mRS), allocation, i, allocation1, j, allocation2);
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 99, 0, 0, 0, 0, 0, k, l, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, allocation2.getID(mRS), i, j, 0, 0);
    }

    public void CGERU(Float2 float2, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        validateGERU(Element.F32_2(mRS), allocation, i, allocation1, j, allocation2);
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 98, 0, 0, 0, 0, 0, k, l, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, allocation2.getID(mRS), i, j, 0, 0);
    }

    public void CHBMV(int i, int j, Float2 float2, Allocation allocation, Allocation allocation1, int k, Float2 float2_1, 
            Allocation allocation2, int l)
    {
        int i1 = validateSYR2(Element.F32_2(mRS), i, allocation1, k, allocation2, l, allocation);
        if(j < 0)
        {
            throw new RSRuntimeException("K must be 0 or greater for HBMV");
        } else
        {
            mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 96, 0, 0, 0, i, 0, 0, i1, j, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), k, l, 0, 0);
            return;
        }
    }

    public void CHEMM(int i, int j, Float2 float2, Allocation allocation, Allocation allocation1, Float2 float2_1, Allocation allocation2)
    {
        validateUplo(j);
        validateHEMM(Element.F32_2(mRS), i, allocation, allocation1, allocation2);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 137, 0, 0, i, j, 0, allocation2.getType().getY(), allocation2.getType().getX(), 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void CHEMV(int i, Float2 float2, Allocation allocation, Allocation allocation1, int j, Float2 float2_1, Allocation allocation2, 
            int k)
    {
        int l = validateSYR2(Element.F32_2(mRS), i, allocation1, j, allocation2, k, allocation);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 95, 0, 0, 0, i, 0, 0, l, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void CHER(int i, float f, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSYR(Element.F32_2(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 100, 0, 0, 0, i, 0, 0, k, 0, f, 0.0F, allocation.getID(mRS), 0L, 0.0F, 0.0F, allocation1.getID(mRS), j, 0, 0, 0);
    }

    public void CHER2(int i, Float2 float2, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        int l = validateSYR2(Element.F32_2(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 102, 0, 0, 0, i, 0, 0, l, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void CHER2K(int i, int j, Float2 float2, Allocation allocation, Allocation allocation1, float f, Allocation allocation2)
    {
        validateUplo(i);
        validateHER2K(Element.F32_2(mRS), j, allocation, allocation1, allocation2);
        int k;
        if(j == 111)
            k = allocation.getType().getX();
        else
            k = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 139, j, 0, 0, i, 0, 0, allocation2.getType().getX(), k, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), f, 0.0F, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void CHERK(int i, int j, float f, Allocation allocation, float f1, Allocation allocation1)
    {
        validateUplo(i);
        validateHERK(Element.F32_2(mRS), j, allocation, allocation1);
        int k;
        if(j == 113)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 138, j, 0, 0, i, 0, 0, allocation1.getType().getX(), k, f, 0.0F, allocation.getID(mRS), 0L, f1, 0.0F, allocation1.getID(mRS), 0, 0, 0, 0);
    }

    public void CHPMV(int i, Float2 float2, Allocation allocation, Allocation allocation1, int j, Float2 float2_1, Allocation allocation2, 
            int k)
    {
        int l = validateSPR2(Element.F32_2(mRS), i, allocation1, j, allocation2, k, allocation);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 97, 0, 0, 0, i, 0, 0, l, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void CHPR(int i, float f, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSPR(Element.F32_2(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 101, 0, 0, 0, i, 0, 0, k, 0, f, 0.0F, allocation.getID(mRS), 0L, 0.0F, 0.0F, allocation1.getID(mRS), j, 0, 0, 0);
    }

    public void CHPR2(int i, Float2 float2, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        int l = validateSPR2(Element.F32_2(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 103, 0, 0, 0, i, 0, 0, l, 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void CSYMM(int i, int j, Float2 float2, Allocation allocation, Allocation allocation1, Float2 float2_1, Allocation allocation2)
    {
        validateSide(i);
        validateUplo(j);
        if(allocation.getType().getX() != allocation.getType().getY())
        {
            throw new RSRuntimeException("Matrix A is not symmetric");
        } else
        {
            validateL3(Element.F32_2(mRS), 0, 0, i, allocation, allocation1, allocation2);
            mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 126, 0, 0, i, j, 0, allocation2.getType().getY(), allocation2.getType().getX(), 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
            return;
        }
    }

    public void CSYR2K(int i, int j, Float2 float2, Allocation allocation, Allocation allocation1, Float2 float2_1, Allocation allocation2)
    {
        validateUplo(i);
        validateSYR2K(Element.F32_2(mRS), j, allocation, allocation1, allocation2);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 128, j, 0, 0, i, 0, 0, allocation2.getType().getX(), k, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), float2_1.x, float2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void CSYRK(int i, int j, Float2 float2, Allocation allocation, Float2 float2_1, Allocation allocation1)
    {
        validateTranspose(j);
        validateUplo(i);
        validateL3(Element.F32_2(mRS), j, 0, 0, allocation, null, allocation1);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 127, j, 0, 0, i, 0, 0, allocation1.getType().getX(), k, float2.x, float2.y, allocation.getID(mRS), 0L, float2_1.x, float2_1.y, allocation1.getID(mRS), 0, 0, 0, 0);
    }

    public void CTBMV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        if(l < 0)
        {
            throw new RSRuntimeException("K must be greater than or equal to 0");
        } else
        {
            validateTRMV(Element.F32_2(mRS), i, j, k, allocation, allocation1, i1);
            int j1 = allocation.getType().getY();
            mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 66, j, 0, 0, i, k, 0, j1, l, 0.0F, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void CTBSV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        validateTRMV(Element.F32_2(mRS), i, j, k, allocation, allocation1, i1);
        int j1 = allocation.getType().getY();
        if(l < 0)
        {
            throw new RSRuntimeException("Number of diagonals must be positive");
        } else
        {
            mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 69, j, 0, 0, i, k, 0, j1, l, 0.0F, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void CTPMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F32_2(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 67, j, 0, 0, i, k, 0, i1, 0, 0.0F, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, l, 0, 0, 0);
    }

    public void CTPSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F32_2(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 70, j, 0, 0, i, k, 0, i1, 0, 0.0F, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, l, 0, 0, 0);
    }

    public void CTRMM(int i, int j, int k, int l, Float2 float2, Allocation allocation, Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRMM(Element.F32_2(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 129, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, 0, 0, 0, 0);
    }

    public void CTRMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F32_2(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 65, j, 0, 0, i, k, 0, i1, 0, 0.0F, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, l, 0, 0, 0);
    }

    public void CTRSM(int i, int j, int k, int l, Float2 float2, Allocation allocation, Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRSM(Element.F32_2(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 130, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, float2.x, float2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, 0, 0, 0, 0);
    }

    public void CTRSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F32_2(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Complex(getID(mRS), 68, j, 0, 0, i, k, 0, i1, 0, 0.0F, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0.0F, 0L, l, 0, 0, 0);
    }

    public void DGBMV(int i, int j, int k, double d, Allocation allocation, Allocation allocation1, 
            int l, double d1, Allocation allocation2, int i1)
    {
        validateGEMV(Element.F64(mRS), i, allocation, allocation1, l, allocation2, i1);
        if(j < 0 || k < 0)
        {
            throw new RSRuntimeException("KL and KU must be greater than or equal to 0");
        } else
        {
            int j1 = allocation.getType().getY();
            int k1 = allocation.getType().getX();
            mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 56, i, 0, 0, 0, 0, j1, k1, 0, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), l, i1, j, k);
            return;
        }
    }

    public void DGEMM(int i, int j, double d, Allocation allocation, Allocation allocation1, double d1, Allocation allocation2)
    {
        validateTranspose(i);
        validateTranspose(j);
        validateL3(Element.F64(mRS), i, j, 0, allocation, allocation1, allocation2);
        int k;
        int l;
        int i1;
        if(i != 111)
        {
            k = allocation.getType().getX();
            l = allocation.getType().getY();
        } else
        {
            k = allocation.getType().getY();
            l = allocation.getType().getX();
        }
        if(j != 111)
            i1 = allocation1.getType().getY();
        else
            i1 = allocation1.getType().getX();
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 119, i, j, 0, 0, 0, k, i1, l, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void DGEMV(int i, double d, Allocation allocation, Allocation allocation1, int j, double d1, Allocation allocation2, int k)
    {
        validateGEMV(Element.F64(mRS), i, allocation, allocation1, j, allocation2, k);
        int l = allocation.getType().getY();
        int i1 = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 55, i, 0, 0, 0, 0, l, i1, 0, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void DGER(double d, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        validateGER(Element.F64(mRS), allocation, i, allocation1, j, allocation2);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 90, 0, 0, 0, 0, 0, k, l, 0, d, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, allocation2.getID(mRS), i, j, 0, 0);
    }

    public void DSBMV(int i, int j, double d, Allocation allocation, Allocation allocation1, int k, 
            double d1, Allocation allocation2, int l)
    {
        if(j < 0)
        {
            throw new RSRuntimeException("K must be greater than or equal to 0");
        } else
        {
            int i1 = validateSYMV(Element.F64(mRS), i, allocation, allocation1, allocation2, k, l);
            mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 88, 0, 0, 0, i, 0, 0, i1, j, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), k, l, 0, 0);
            return;
        }
    }

    public void DSPMV(int i, double d, Allocation allocation, Allocation allocation1, int j, double d1, Allocation allocation2, int k)
    {
        int l = validateSPMV(Element.F64(mRS), i, allocation, allocation1, j, allocation2, k);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 89, 0, 0, 0, i, 0, 0, l, 0, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void DSPR(int i, double d, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSPR(Element.F64(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 92, 0, 0, 0, i, 0, 0, k, 0, d, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, j, 0, 0, 0);
    }

    public void DSPR2(int i, double d, Allocation allocation, int j, Allocation allocation1, int k, 
            Allocation allocation2)
    {
        int l = validateSPR2(Element.F64(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 94, 0, 0, 0, i, 0, 0, l, 0, d, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void DSYMM(int i, int j, double d, Allocation allocation, Allocation allocation1, double d1, Allocation allocation2)
    {
        validateSide(i);
        validateUplo(j);
        if(allocation.getType().getX() != allocation.getType().getY())
        {
            throw new RSRuntimeException("Matrix A is not symmetric");
        } else
        {
            validateL3(Element.F64(mRS), 0, 0, i, allocation, allocation1, allocation2);
            mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 120, 0, 0, i, j, 0, allocation2.getType().getY(), allocation2.getType().getX(), 0, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), 0, 0, 0, 0);
            return;
        }
    }

    public void DSYMV(int i, double d, Allocation allocation, Allocation allocation1, int j, double d1, Allocation allocation2, int k)
    {
        int l = validateSYMV(Element.F64(mRS), i, allocation, allocation1, allocation2, j, k);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 87, 0, 0, 0, i, 0, 0, l, 0, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void DSYR(int i, double d, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSYR(Element.F64(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 91, 0, 0, 0, i, 0, 0, k, 0, d, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, j, 0, 0, 0);
    }

    public void DSYR2(int i, double d, Allocation allocation, int j, Allocation allocation1, int k, 
            Allocation allocation2)
    {
        int l = validateSYR2(Element.F64(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 93, 0, 0, 0, i, 0, 0, l, 0, d, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void DSYR2K(int i, int j, double d, Allocation allocation, Allocation allocation1, double d1, Allocation allocation2)
    {
        validateUplo(i);
        validateSYR2K(Element.F64(mRS), j, allocation, allocation1, allocation2);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 122, j, 0, 0, i, 0, 0, allocation2.getType().getX(), k, d, allocation.getID(mRS), allocation1.getID(mRS), d1, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void DSYRK(int i, int j, double d, Allocation allocation, double d1, 
            Allocation allocation1)
    {
        validateTranspose(j);
        validateUplo(i);
        validateL3(Element.F64(mRS), j, 0, 0, allocation, null, allocation1);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 121, j, 0, 0, i, 0, 0, allocation1.getType().getX(), k, d, allocation.getID(mRS), 0L, d1, allocation1.getID(mRS), 0, 0, 0, 0);
    }

    public void DTBMV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        if(l < 0)
        {
            throw new RSRuntimeException("K must be greater than or equal to 0");
        } else
        {
            validateTRMV(Element.F64(mRS), i, j, k, allocation, allocation1, i1);
            int j1 = allocation.getType().getY();
            mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 58, j, 0, 0, i, k, 0, j1, l, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void DTBSV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        validateTRMV(Element.F64(mRS), i, j, k, allocation, allocation1, i1);
        int j1 = allocation.getType().getY();
        if(l < 0)
        {
            throw new RSRuntimeException("Number of diagonals must be positive");
        } else
        {
            mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 61, j, 0, 0, i, k, 0, j1, l, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void DTPMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F64(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 59, j, 0, 0, i, k, 0, i1, 0, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, l, 0, 0, 0);
    }

    public void DTPSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F64(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 62, j, 0, 0, i, k, 0, i1, 0, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, l, 0, 0, 0);
    }

    public void DTRMM(int i, int j, int k, int l, double d, Allocation allocation, 
            Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRMM(Element.F64(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 123, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, d, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, 0, 0, 0, 0);
    }

    public void DTRMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F64(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 57, j, 0, 0, i, k, 0, i1, 0, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, l, 0, 0, 0);
    }

    public void DTRSM(int i, int j, int k, int l, double d, Allocation allocation, 
            Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRSM(Element.F64(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 124, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, d, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, 0, 0, 0, 0);
    }

    public void DTRSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F64(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Double(getID(mRS), 60, j, 0, 0, i, k, 0, i1, 0, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0L, l, 0, 0, 0);
    }

    public void SGBMV(int i, int j, int k, float f, Allocation allocation, Allocation allocation1, int l, 
            float f1, Allocation allocation2, int i1)
    {
        validateGEMV(Element.F32(mRS), i, allocation, allocation1, l, allocation2, i1);
        if(j < 0 || k < 0)
        {
            throw new RSRuntimeException("KL and KU must be greater than or equal to 0");
        } else
        {
            int j1 = allocation.getType().getY();
            int k1 = allocation.getType().getX();
            mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 48, i, 0, 0, 0, 0, j1, k1, 0, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), l, i1, j, k);
            return;
        }
    }

    public void SGEMM(int i, int j, float f, Allocation allocation, Allocation allocation1, float f1, Allocation allocation2)
    {
        validateTranspose(i);
        validateTranspose(j);
        validateL3(Element.F32(mRS), i, j, 0, allocation, allocation1, allocation2);
        int k;
        int l;
        int i1;
        if(i != 111)
        {
            k = allocation.getType().getX();
            l = allocation.getType().getY();
        } else
        {
            k = allocation.getType().getY();
            l = allocation.getType().getX();
        }
        if(j != 111)
            i1 = allocation1.getType().getY();
        else
            i1 = allocation1.getType().getX();
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 113, i, j, 0, 0, 0, k, i1, l, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void SGEMV(int i, float f, Allocation allocation, Allocation allocation1, int j, float f1, Allocation allocation2, 
            int k)
    {
        validateGEMV(Element.F32(mRS), i, allocation, allocation1, j, allocation2, k);
        int l = allocation.getType().getY();
        int i1 = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 47, i, 0, 0, 0, 0, l, i1, 0, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void SGER(float f, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        validateGER(Element.F32(mRS), allocation, i, allocation1, j, allocation2);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 82, 0, 0, 0, 0, 0, k, l, 0, f, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, allocation2.getID(mRS), i, j, 0, 0);
    }

    public void SSBMV(int i, int j, float f, Allocation allocation, Allocation allocation1, int k, float f1, 
            Allocation allocation2, int l)
    {
        if(j < 0)
        {
            throw new RSRuntimeException("K must be greater than or equal to 0");
        } else
        {
            int i1 = validateSYMV(Element.F32(mRS), i, allocation, allocation1, allocation2, k, l);
            mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 80, 0, 0, 0, i, 0, 0, i1, j, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), k, l, 0, 0);
            return;
        }
    }

    public void SSPMV(int i, float f, Allocation allocation, Allocation allocation1, int j, float f1, Allocation allocation2, 
            int k)
    {
        int l = validateSPMV(Element.F32(mRS), i, allocation, allocation1, j, allocation2, k);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 81, 0, 0, 0, i, 0, 0, l, 0, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void SSPR(int i, float f, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSPR(Element.F32(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 84, 0, 0, 0, i, 0, 0, k, 0, f, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, j, 0, 0, 0);
    }

    public void SSPR2(int i, float f, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        int l = validateSPR2(Element.F32(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 86, 0, 0, 0, i, 0, 0, l, 0, f, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void SSYMM(int i, int j, float f, Allocation allocation, Allocation allocation1, float f1, Allocation allocation2)
    {
        validateSide(i);
        validateUplo(j);
        if(allocation.getType().getX() != allocation.getType().getY())
        {
            throw new RSRuntimeException("Matrix A is not symmetric");
        } else
        {
            validateL3(Element.F32(mRS), 0, 0, i, allocation, allocation1, allocation2);
            mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 114, 0, 0, i, j, 0, allocation2.getType().getY(), allocation2.getType().getX(), 0, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), 0, 0, 0, 0);
            return;
        }
    }

    public void SSYMV(int i, float f, Allocation allocation, Allocation allocation1, int j, float f1, Allocation allocation2, 
            int k)
    {
        int l = validateSYMV(Element.F32(mRS), i, allocation, allocation1, allocation2, j, k);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 79, 0, 0, 0, i, 0, 0, l, 0, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void SSYR(int i, float f, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSYR(Element.F32(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 83, 0, 0, 0, i, 0, 0, k, 0, f, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, j, 0, 0, 0);
    }

    public void SSYR2(int i, float f, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        int l = validateSYR2(Element.F32(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 85, 0, 0, 0, i, 0, 0, l, 0, f, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void SSYR2K(int i, int j, float f, Allocation allocation, Allocation allocation1, float f1, Allocation allocation2)
    {
        validateUplo(i);
        validateSYR2K(Element.F32(mRS), j, allocation, allocation1, allocation2);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 116, j, 0, 0, i, 0, 0, allocation2.getType().getX(), k, f, allocation.getID(mRS), allocation1.getID(mRS), f1, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void SSYRK(int i, int j, float f, Allocation allocation, float f1, Allocation allocation1)
    {
        validateTranspose(j);
        validateUplo(i);
        validateL3(Element.F32(mRS), j, 0, 0, allocation, null, allocation1);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 115, j, 0, 0, i, 0, 0, allocation1.getType().getX(), k, f, allocation.getID(mRS), 0L, f1, allocation1.getID(mRS), 0, 0, 0, 0);
    }

    public void STBMV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        if(l < 0)
        {
            throw new RSRuntimeException("K must be greater than or equal to 0");
        } else
        {
            validateTRMV(Element.F32(mRS), i, j, k, allocation, allocation1, i1);
            int j1 = allocation.getType().getY();
            mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 50, j, 0, 0, i, k, 0, j1, l, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void STBSV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        validateTRMV(Element.F32(mRS), i, j, k, allocation, allocation1, i1);
        int j1 = allocation.getType().getY();
        if(l < 0)
        {
            throw new RSRuntimeException("Number of diagonals must be positive");
        } else
        {
            mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 53, j, 0, 0, i, k, 0, j1, l, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void STPMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F32(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 51, j, 0, 0, i, k, 0, i1, 0, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, l, 0, 0, 0);
    }

    public void STPSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F32(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 54, j, 0, 0, i, k, 0, i1, 0, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, l, 0, 0, 0);
    }

    public void STRMM(int i, int j, int k, int l, float f, Allocation allocation, Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRMM(Element.F32(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 117, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, f, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, 0, 0, 0, 0);
    }

    public void STRMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F32(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 49, j, 0, 0, i, k, 0, i1, 0, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, l, 0, 0, 0);
    }

    public void STRSM(int i, int j, int k, int l, float f, Allocation allocation, Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRSM(Element.F32(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 118, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, f, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, 0, 0, 0, 0);
    }

    public void STRSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F32(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Single(getID(mRS), 52, j, 0, 0, i, k, 0, i1, 0, 0.0F, allocation.getID(mRS), allocation1.getID(mRS), 0.0F, 0L, l, 0, 0, 0);
    }

    public void ZGBMV(int i, int j, int k, Double2 double2, Allocation allocation, Allocation allocation1, int l, 
            Double2 double2_1, Allocation allocation2, int i1)
    {
        validateGEMV(Element.F64_2(mRS), i, allocation, allocation1, l, allocation2, i1);
        if(j < 0 || k < 0)
        {
            throw new RSRuntimeException("KL and KU must be greater than or equal to 0");
        } else
        {
            int j1 = allocation.getType().getY();
            int k1 = allocation.getType().getX();
            mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 72, i, 0, 0, 0, 0, j1, k1, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), l, i1, j, k);
            return;
        }
    }

    public void ZGEMM(int i, int j, Double2 double2, Allocation allocation, Allocation allocation1, Double2 double2_1, Allocation allocation2)
    {
        validateTranspose(i);
        validateTranspose(j);
        validateL3(Element.F64_2(mRS), i, j, 0, allocation, allocation1, allocation2);
        int k;
        int l;
        int i1;
        if(i != 111)
        {
            k = allocation.getType().getX();
            l = allocation.getType().getY();
        } else
        {
            k = allocation.getType().getY();
            l = allocation.getType().getX();
        }
        if(j != 111)
            i1 = allocation1.getType().getY();
        else
            i1 = allocation1.getType().getX();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 131, i, j, 0, 0, 0, k, i1, l, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void ZGEMV(int i, Double2 double2, Allocation allocation, Allocation allocation1, int j, Double2 double2_1, Allocation allocation2, 
            int k)
    {
        validateGEMV(Element.F64_2(mRS), i, allocation, allocation1, j, allocation2, k);
        int l = allocation.getType().getY();
        int i1 = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 71, i, 0, 0, 0, 0, l, i1, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void ZGERC(Double2 double2, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        validateGERU(Element.F64_2(mRS), allocation, i, allocation1, j, allocation2);
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 108, 0, 0, 0, 0, 0, k, l, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, allocation2.getID(mRS), i, j, 0, 0);
    }

    public void ZGERU(Double2 double2, Allocation allocation, int i, Allocation allocation1, int j, Allocation allocation2)
    {
        validateGERU(Element.F64_2(mRS), allocation, i, allocation1, j, allocation2);
        int k = allocation2.getType().getY();
        int l = allocation2.getType().getX();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 107, 0, 0, 0, 0, 0, k, l, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, allocation2.getID(mRS), i, j, 0, 0);
    }

    public void ZHBMV(int i, int j, Double2 double2, Allocation allocation, Allocation allocation1, int k, Double2 double2_1, 
            Allocation allocation2, int l)
    {
        int i1 = validateSYR2(Element.F64_2(mRS), i, allocation1, k, allocation2, l, allocation);
        if(j < 0)
        {
            throw new RSRuntimeException("K must be 0 or greater for HBMV");
        } else
        {
            mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 105, 0, 0, 0, i, 0, 0, i1, j, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), k, l, 0, 0);
            return;
        }
    }

    public void ZHEMM(int i, int j, Double2 double2, Allocation allocation, Allocation allocation1, Double2 double2_1, Allocation allocation2)
    {
        validateUplo(j);
        validateHEMM(Element.F64_2(mRS), i, allocation, allocation1, allocation2);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 140, 0, 0, i, j, 0, allocation2.getType().getY(), allocation2.getType().getX(), 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void ZHEMV(int i, Double2 double2, Allocation allocation, Allocation allocation1, int j, Double2 double2_1, Allocation allocation2, 
            int k)
    {
        int l = validateSYR2(Element.F64_2(mRS), i, allocation1, j, allocation2, k, allocation);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 104, 0, 0, 0, i, 0, 0, l, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void ZHER(int i, double d, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSYR(Element.F64_2(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 109, 0, 0, 0, i, 0, 0, k, 0, d, 0.0D, allocation.getID(mRS), 0L, 0.0D, 0.0D, allocation1.getID(mRS), j, 0, 0, 0);
    }

    public void ZHER2(int i, Double2 double2, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        int l = validateSYR2(Element.F64_2(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 111, 0, 0, 0, i, 0, 0, l, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void ZHER2K(int i, int j, Double2 double2, Allocation allocation, Allocation allocation1, double d, 
            Allocation allocation2)
    {
        validateUplo(i);
        validateHER2K(Element.F64_2(mRS), j, allocation, allocation1, allocation2);
        int k;
        if(j == 111)
            k = allocation.getType().getX();
        else
            k = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 142, j, 0, 0, i, 0, 0, allocation2.getType().getX(), k, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), d, 0.0D, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void ZHERK(int i, int j, double d, Allocation allocation, double d1, 
            Allocation allocation1)
    {
        validateUplo(i);
        validateHERK(Element.F64_2(mRS), j, allocation, allocation1);
        int k;
        if(j == 113)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 141, j, 0, 0, i, 0, 0, allocation1.getType().getX(), k, d, 0.0D, allocation.getID(mRS), 0L, d1, 0.0D, allocation1.getID(mRS), 0, 0, 0, 0);
    }

    public void ZHPMV(int i, Double2 double2, Allocation allocation, Allocation allocation1, int j, Double2 double2_1, Allocation allocation2, 
            int k)
    {
        int l = validateSPR2(Element.F64_2(mRS), i, allocation1, j, allocation2, k, allocation);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 106, 0, 0, 0, i, 0, 0, l, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void ZHPR(int i, double d, Allocation allocation, int j, Allocation allocation1)
    {
        int k = validateSPR(Element.F64_2(mRS), i, allocation, j, allocation1);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 110, 0, 0, 0, i, 0, 0, k, 0, d, 0.0D, allocation.getID(mRS), 0L, 0.0D, 0.0D, allocation1.getID(mRS), j, 0, 0, 0);
    }

    public void ZHPR2(int i, Double2 double2, Allocation allocation, int j, Allocation allocation1, int k, Allocation allocation2)
    {
        int l = validateSPR2(Element.F64_2(mRS), i, allocation, j, allocation1, k, allocation2);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 112, 0, 0, 0, i, 0, 0, l, 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, allocation2.getID(mRS), j, k, 0, 0);
    }

    public void ZSYMM(int i, int j, Double2 double2, Allocation allocation, Allocation allocation1, Double2 double2_1, Allocation allocation2)
    {
        validateSide(i);
        validateUplo(j);
        if(allocation.getType().getX() != allocation.getType().getY())
        {
            throw new RSRuntimeException("Matrix A is not symmetric");
        } else
        {
            validateL3(Element.F64_2(mRS), 0, 0, i, allocation, allocation1, allocation2);
            mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 132, 0, 0, i, j, 0, allocation2.getType().getY(), allocation2.getType().getX(), 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
            return;
        }
    }

    public void ZSYR2K(int i, int j, Double2 double2, Allocation allocation, Allocation allocation1, Double2 double2_1, Allocation allocation2)
    {
        validateUplo(i);
        validateSYR2K(Element.F64_2(mRS), j, allocation, allocation1, allocation2);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 134, j, 0, 0, i, 0, 0, allocation2.getType().getX(), k, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), double2_1.x, double2_1.y, allocation2.getID(mRS), 0, 0, 0, 0);
    }

    public void ZSYRK(int i, int j, Double2 double2, Allocation allocation, Double2 double2_1, Allocation allocation1)
    {
        validateTranspose(j);
        validateUplo(i);
        validateL3(Element.F64_2(mRS), j, 0, 0, allocation, null, allocation1);
        int k;
        if(j != 111)
            k = allocation.getType().getY();
        else
            k = allocation.getType().getX();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 133, j, 0, 0, i, 0, 0, allocation1.getType().getX(), k, double2.x, double2.y, allocation.getID(mRS), 0L, double2_1.x, double2_1.y, allocation1.getID(mRS), 0, 0, 0, 0);
    }

    public void ZTBMV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        if(l < 0)
        {
            throw new RSRuntimeException("K must be greater than or equal to 0");
        } else
        {
            validateTRMV(Element.F64_2(mRS), i, j, k, allocation, allocation1, i1);
            int j1 = allocation.getType().getY();
            mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 74, j, 0, 0, i, k, 0, j1, l, 0.0D, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void ZTBSV(int i, int j, int k, int l, Allocation allocation, Allocation allocation1, int i1)
    {
        validateTRMV(Element.F64_2(mRS), i, j, k, allocation, allocation1, i1);
        int j1 = allocation.getType().getY();
        if(l < 0)
        {
            throw new RSRuntimeException("Number of diagonals must be positive");
        } else
        {
            mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 77, j, 0, 0, i, k, 0, j1, l, 0.0D, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, i1, 0, 0, 0);
            return;
        }
    }

    public void ZTPMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F64_2(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 75, j, 0, 0, i, k, 0, i1, 0, 0.0D, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, l, 0, 0, 0);
    }

    public void ZTPSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        int i1 = validateTPMV(Element.F64_2(mRS), i, j, k, allocation, allocation1, l);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 78, j, 0, 0, i, k, 0, i1, 0, 0.0D, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, l, 0, 0, 0);
    }

    public void ZTRMM(int i, int j, int k, int l, Double2 double2, Allocation allocation, Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRMM(Element.F64_2(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 135, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, 0, 0, 0, 0);
    }

    public void ZTRMV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F64_2(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 73, j, 0, 0, i, k, 0, i1, 0, 0.0D, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, l, 0, 0, 0);
    }

    public void ZTRSM(int i, int j, int k, int l, Double2 double2, Allocation allocation, Allocation allocation1)
    {
        validateUplo(j);
        validateDiag(l);
        validateTRSM(Element.F64_2(mRS), i, k, allocation, allocation1);
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 136, k, 0, i, j, l, allocation1.getType().getY(), allocation1.getType().getX(), 0, double2.x, double2.y, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, 0, 0, 0, 0);
    }

    public void ZTRSV(int i, int j, int k, Allocation allocation, Allocation allocation1, int l)
    {
        validateTRMV(Element.F64_2(mRS), i, j, k, allocation, allocation1, l);
        int i1 = allocation.getType().getY();
        mRS.nScriptIntrinsicBLAS_Z(getID(mRS), 76, j, 0, 0, i, k, 0, i1, 0, 0.0D, 0.0D, allocation.getID(mRS), allocation1.getID(mRS), 0.0D, 0.0D, 0L, l, 0, 0, 0);
    }

    public static final int CONJ_TRANSPOSE = 113;
    public static final int LEFT = 141;
    public static final int LOWER = 122;
    public static final int NON_UNIT = 131;
    public static final int NO_TRANSPOSE = 111;
    public static final int RIGHT = 142;
    private static final int RsBlas_bnnm = 1000;
    private static final int RsBlas_caxpy = 29;
    private static final int RsBlas_ccopy = 28;
    private static final int RsBlas_cdotc_sub = 6;
    private static final int RsBlas_cdotu_sub = 5;
    private static final int RsBlas_cgbmv = 64;
    private static final int RsBlas_cgemm = 125;
    private static final int RsBlas_cgemv = 63;
    private static final int RsBlas_cgerc = 99;
    private static final int RsBlas_cgeru = 98;
    private static final int RsBlas_chbmv = 96;
    private static final int RsBlas_chemm = 137;
    private static final int RsBlas_chemv = 95;
    private static final int RsBlas_cher = 100;
    private static final int RsBlas_cher2 = 102;
    private static final int RsBlas_cher2k = 139;
    private static final int RsBlas_cherk = 138;
    private static final int RsBlas_chpmv = 97;
    private static final int RsBlas_chpr = 101;
    private static final int RsBlas_chpr2 = 103;
    private static final int RsBlas_cscal = 43;
    private static final int RsBlas_csscal = 45;
    private static final int RsBlas_cswap = 27;
    private static final int RsBlas_csymm = 126;
    private static final int RsBlas_csyr2k = 128;
    private static final int RsBlas_csyrk = 127;
    private static final int RsBlas_ctbmv = 66;
    private static final int RsBlas_ctbsv = 69;
    private static final int RsBlas_ctpmv = 67;
    private static final int RsBlas_ctpsv = 70;
    private static final int RsBlas_ctrmm = 129;
    private static final int RsBlas_ctrmv = 65;
    private static final int RsBlas_ctrsm = 130;
    private static final int RsBlas_ctrsv = 68;
    private static final int RsBlas_dasum = 12;
    private static final int RsBlas_daxpy = 26;
    private static final int RsBlas_dcopy = 25;
    private static final int RsBlas_ddot = 4;
    private static final int RsBlas_dgbmv = 56;
    private static final int RsBlas_dgemm = 119;
    private static final int RsBlas_dgemv = 55;
    private static final int RsBlas_dger = 90;
    private static final int RsBlas_dnrm2 = 11;
    private static final int RsBlas_drot = 39;
    private static final int RsBlas_drotg = 37;
    private static final int RsBlas_drotm = 40;
    private static final int RsBlas_drotmg = 38;
    private static final int RsBlas_dsbmv = 88;
    private static final int RsBlas_dscal = 42;
    private static final int RsBlas_dsdot = 2;
    private static final int RsBlas_dspmv = 89;
    private static final int RsBlas_dspr = 92;
    private static final int RsBlas_dspr2 = 94;
    private static final int RsBlas_dswap = 24;
    private static final int RsBlas_dsymm = 120;
    private static final int RsBlas_dsymv = 87;
    private static final int RsBlas_dsyr = 91;
    private static final int RsBlas_dsyr2 = 93;
    private static final int RsBlas_dsyr2k = 122;
    private static final int RsBlas_dsyrk = 121;
    private static final int RsBlas_dtbmv = 58;
    private static final int RsBlas_dtbsv = 61;
    private static final int RsBlas_dtpmv = 59;
    private static final int RsBlas_dtpsv = 62;
    private static final int RsBlas_dtrmm = 123;
    private static final int RsBlas_dtrmv = 57;
    private static final int RsBlas_dtrsm = 124;
    private static final int RsBlas_dtrsv = 60;
    private static final int RsBlas_dzasum = 16;
    private static final int RsBlas_dznrm2 = 15;
    private static final int RsBlas_icamax = 19;
    private static final int RsBlas_idamax = 18;
    private static final int RsBlas_isamax = 17;
    private static final int RsBlas_izamax = 20;
    private static final int RsBlas_sasum = 10;
    private static final int RsBlas_saxpy = 23;
    private static final int RsBlas_scasum = 14;
    private static final int RsBlas_scnrm2 = 13;
    private static final int RsBlas_scopy = 22;
    private static final int RsBlas_sdot = 3;
    private static final int RsBlas_sdsdot = 1;
    private static final int RsBlas_sgbmv = 48;
    private static final int RsBlas_sgemm = 113;
    private static final int RsBlas_sgemv = 47;
    private static final int RsBlas_sger = 82;
    private static final int RsBlas_snrm2 = 9;
    private static final int RsBlas_srot = 35;
    private static final int RsBlas_srotg = 33;
    private static final int RsBlas_srotm = 36;
    private static final int RsBlas_srotmg = 34;
    private static final int RsBlas_ssbmv = 80;
    private static final int RsBlas_sscal = 41;
    private static final int RsBlas_sspmv = 81;
    private static final int RsBlas_sspr = 84;
    private static final int RsBlas_sspr2 = 86;
    private static final int RsBlas_sswap = 21;
    private static final int RsBlas_ssymm = 114;
    private static final int RsBlas_ssymv = 79;
    private static final int RsBlas_ssyr = 83;
    private static final int RsBlas_ssyr2 = 85;
    private static final int RsBlas_ssyr2k = 116;
    private static final int RsBlas_ssyrk = 115;
    private static final int RsBlas_stbmv = 50;
    private static final int RsBlas_stbsv = 53;
    private static final int RsBlas_stpmv = 51;
    private static final int RsBlas_stpsv = 54;
    private static final int RsBlas_strmm = 117;
    private static final int RsBlas_strmv = 49;
    private static final int RsBlas_strsm = 118;
    private static final int RsBlas_strsv = 52;
    private static final int RsBlas_zaxpy = 32;
    private static final int RsBlas_zcopy = 31;
    private static final int RsBlas_zdotc_sub = 8;
    private static final int RsBlas_zdotu_sub = 7;
    private static final int RsBlas_zdscal = 46;
    private static final int RsBlas_zgbmv = 72;
    private static final int RsBlas_zgemm = 131;
    private static final int RsBlas_zgemv = 71;
    private static final int RsBlas_zgerc = 108;
    private static final int RsBlas_zgeru = 107;
    private static final int RsBlas_zhbmv = 105;
    private static final int RsBlas_zhemm = 140;
    private static final int RsBlas_zhemv = 104;
    private static final int RsBlas_zher = 109;
    private static final int RsBlas_zher2 = 111;
    private static final int RsBlas_zher2k = 142;
    private static final int RsBlas_zherk = 141;
    private static final int RsBlas_zhpmv = 106;
    private static final int RsBlas_zhpr = 110;
    private static final int RsBlas_zhpr2 = 112;
    private static final int RsBlas_zscal = 44;
    private static final int RsBlas_zswap = 30;
    private static final int RsBlas_zsymm = 132;
    private static final int RsBlas_zsyr2k = 134;
    private static final int RsBlas_zsyrk = 133;
    private static final int RsBlas_ztbmv = 74;
    private static final int RsBlas_ztbsv = 77;
    private static final int RsBlas_ztpmv = 75;
    private static final int RsBlas_ztpsv = 78;
    private static final int RsBlas_ztrmm = 135;
    private static final int RsBlas_ztrmv = 73;
    private static final int RsBlas_ztrsm = 136;
    private static final int RsBlas_ztrsv = 76;
    public static final int TRANSPOSE = 112;
    public static final int UNIT = 132;
    public static final int UPPER = 121;
    private Allocation mLUT;
}
