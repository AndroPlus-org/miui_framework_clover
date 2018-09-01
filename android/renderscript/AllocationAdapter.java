// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            Allocation, RenderScript, Type, RSInvalidStateException, 
//            Element, RSRuntimeException, RSIllegalArgumentException

public class AllocationAdapter extends Allocation
{

    AllocationAdapter(long l, RenderScript renderscript, Allocation allocation, Type type)
    {
        super(l, renderscript, allocation.mType, allocation.mUsage);
        mAdaptedAllocation = allocation;
        mWindow = type;
    }

    public static AllocationAdapter create1D(RenderScript renderscript, Allocation allocation)
    {
        renderscript.validate();
        return createTyped(renderscript, allocation, Type.createX(renderscript, allocation.getElement(), allocation.getType().getX()));
    }

    public static AllocationAdapter create2D(RenderScript renderscript, Allocation allocation)
    {
        renderscript.validate();
        return createTyped(renderscript, allocation, Type.createXY(renderscript, allocation.getElement(), allocation.getType().getX(), allocation.getType().getY()));
    }

    public static AllocationAdapter createTyped(RenderScript renderscript, Allocation allocation, Type type)
    {
        renderscript.validate();
        if(allocation.mAdaptedAllocation != null)
            throw new RSInvalidStateException("Adapters cannot be nested.");
        if(!allocation.getType().getElement().equals(type.getElement()))
            throw new RSInvalidStateException("Element must match Allocation type.");
        if(type.hasFaces() || type.hasMipmaps())
            throw new RSInvalidStateException("Adapters do not support window types with Mipmaps or Faces.");
        Type type1;
        for(type1 = allocation.getType(); type.getX() > type1.getX() || type.getY() > type1.getY() || type.getZ() > type1.getZ() || type.getArrayCount() > type1.getArrayCount();)
            throw new RSInvalidStateException("Type cannot have dimension larger than the source allocation.");

        if(type.getArrayCount() > 0)
        {
            for(int i = 0; i < type.getArray(i); i++)
                if(type.getArray(i) > type1.getArray(i))
                    throw new RSInvalidStateException("Type cannot have dimension larger than the source allocation.");

        }
        long l = renderscript.nAllocationAdapterCreate(allocation.getID(renderscript), type.getID(renderscript));
        if(l == 0L)
            throw new RSRuntimeException("AllocationAdapter creation failed.");
        else
            return new AllocationAdapter(l, renderscript, allocation, type);
    }

    private void updateOffsets()
    {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        int k1 = ((flag) ? 1 : 0);
        if(mSelectedArray != null)
        {
            if(mSelectedArray.length > 0)
                j = mSelectedArray[0];
            if(mSelectedArray.length > 1)
                l = mSelectedArray[2];
            if(mSelectedArray.length > 2)
                j1 = mSelectedArray[2];
            i = j;
            k = l;
            i1 = j1;
            k1 = ((flag) ? 1 : 0);
            if(mSelectedArray.length > 3)
            {
                k1 = mSelectedArray[3];
                i1 = j1;
                k = l;
                i = j;
            }
        }
        mRS.nAllocationAdapterOffset(getID(mRS), mSelectedX, mSelectedY, mSelectedZ, mSelectedLOD, mSelectedFace.mID, i, k, i1, k1);
    }

    void initLOD(int i)
    {
        if(i < 0)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Attempting to set negative lod (").append(i).append(").").toString());
        int j = mAdaptedAllocation.mType.getX();
        int k = mAdaptedAllocation.mType.getY();
        int l = mAdaptedAllocation.mType.getZ();
        for(int i1 = 0; i1 < i;)
        {
            if(j == 1 && k == 1 && l == 1)
                throw new RSIllegalArgumentException((new StringBuilder()).append("Attempting to set lod (").append(i).append(") out of range.").toString());
            int j1 = j;
            if(j > 1)
                j1 = j >> 1;
            int k1 = k;
            if(k > 1)
                k1 = k >> 1;
            int l1 = l;
            if(l > 1)
                l1 = l >> 1;
            i1++;
            j = j1;
            k = k1;
            l = l1;
        }

        mCurrentDimX = j;
        mCurrentDimY = k;
        mCurrentDimZ = l;
        mCurrentCount = mCurrentDimX;
        if(mCurrentDimY > 1)
            mCurrentCount = mCurrentCount * mCurrentDimY;
        if(mCurrentDimZ > 1)
            mCurrentCount = mCurrentCount * mCurrentDimZ;
        mSelectedY = 0;
        mSelectedZ = 0;
    }

    public void resize(int i)
    {
        this;
        JVM INSTR monitorenter ;
        RSInvalidStateException rsinvalidstateexception = JVM INSTR new #66  <Class RSInvalidStateException>;
        rsinvalidstateexception.RSInvalidStateException("Resize not allowed for Adapters.");
        throw rsinvalidstateexception;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setArray(int i, int j)
    {
        if(mAdaptedAllocation.getType().getArray(i) == 0)
            throw new RSInvalidStateException("Cannot set arrayNum when the allocation type does not include arrayNum dim.");
        if(mAdaptedAllocation.getType().getArray(i) <= j)
            throw new RSInvalidStateException("Cannot set arrayNum greater than dimension of allocation.");
        if(mWindow.getArray(i) == mAdaptedAllocation.getType().getArray(i))
            throw new RSInvalidStateException("Cannot set arrayNum when the adapter includes arrayNum.");
        if(mWindow.getArray(i) + j >= mAdaptedAllocation.getType().getArray(i))
        {
            throw new RSInvalidStateException("Cannot set (arrayNum + window) which would be larger than dimension of allocation.");
        } else
        {
            mSelectedArray[i] = j;
            updateOffsets();
            return;
        }
    }

    public void setFace(Type.CubemapFace cubemapface)
    {
        if(!mAdaptedAllocation.getType().hasFaces())
            throw new RSInvalidStateException("Cannot set Face when the allocation type does not include faces.");
        if(mWindow.hasFaces())
            throw new RSInvalidStateException("Cannot set face when the adapter includes faces.");
        if(cubemapface == null)
        {
            throw new RSIllegalArgumentException("Cannot set null face.");
        } else
        {
            mSelectedFace = cubemapface;
            updateOffsets();
            return;
        }
    }

    public void setLOD(int i)
    {
        if(!mAdaptedAllocation.getType().hasMipmaps())
            throw new RSInvalidStateException("Cannot set LOD when the allocation type does not include mipmaps.");
        if(mWindow.hasMipmaps())
        {
            throw new RSInvalidStateException("Cannot set LOD when the adapter includes mipmaps.");
        } else
        {
            initLOD(i);
            mSelectedLOD = i;
            updateOffsets();
            return;
        }
    }

    public void setX(int i)
    {
        if(mAdaptedAllocation.getType().getX() <= i)
            throw new RSInvalidStateException("Cannot set X greater than dimension of allocation.");
        if(mWindow.getX() == mAdaptedAllocation.getType().getX())
            throw new RSInvalidStateException("Cannot set X when the adapter includes X.");
        if(mWindow.getX() + i >= mAdaptedAllocation.getType().getX())
        {
            throw new RSInvalidStateException("Cannot set (X + window) which would be larger than dimension of allocation.");
        } else
        {
            mSelectedX = i;
            updateOffsets();
            return;
        }
    }

    public void setY(int i)
    {
        if(mAdaptedAllocation.getType().getY() == 0)
            throw new RSInvalidStateException("Cannot set Y when the allocation type does not include Y dim.");
        if(mAdaptedAllocation.getType().getY() <= i)
            throw new RSInvalidStateException("Cannot set Y greater than dimension of allocation.");
        if(mWindow.getY() == mAdaptedAllocation.getType().getY())
            throw new RSInvalidStateException("Cannot set Y when the adapter includes Y.");
        if(mWindow.getY() + i >= mAdaptedAllocation.getType().getY())
        {
            throw new RSInvalidStateException("Cannot set (Y + window) which would be larger than dimension of allocation.");
        } else
        {
            mSelectedY = i;
            updateOffsets();
            return;
        }
    }

    public void setZ(int i)
    {
        if(mAdaptedAllocation.getType().getZ() == 0)
            throw new RSInvalidStateException("Cannot set Z when the allocation type does not include Z dim.");
        if(mAdaptedAllocation.getType().getZ() <= i)
            throw new RSInvalidStateException("Cannot set Z greater than dimension of allocation.");
        if(mWindow.getZ() == mAdaptedAllocation.getType().getZ())
            throw new RSInvalidStateException("Cannot set Z when the adapter includes Z.");
        if(mWindow.getZ() + i >= mAdaptedAllocation.getType().getZ())
        {
            throw new RSInvalidStateException("Cannot set (Z + window) which would be larger than dimension of allocation.");
        } else
        {
            mSelectedZ = i;
            updateOffsets();
            return;
        }
    }

    Type mWindow;
}
