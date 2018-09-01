// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            BaseObj, RSInvalidStateException, Element, RenderScript, 
//            RSIllegalArgumentException

public class Type extends BaseObj
{
    public static class Builder
    {

        public Type create()
        {
            if(mDimZ > 0)
            {
                if(mDimX < 1 || mDimY < 1)
                    throw new RSInvalidStateException("Both X and Y dimension required when Z is present.");
                if(mDimFaces)
                    throw new RSInvalidStateException("Cube maps not supported with 3D types.");
            }
            if(mDimY > 0 && mDimX < 1)
                throw new RSInvalidStateException("X dimension required when Y is present.");
            if(mDimFaces && mDimY < 1)
                throw new RSInvalidStateException("Cube maps require 2D Types.");
            if(mYuv != 0 && (mDimZ != 0 || mDimFaces || mDimMipmaps))
                throw new RSInvalidStateException("YUV only supports basic 2D.");
            int ai[] = null;
            for(int i = 3; i >= 0;)
            {
                int ai1[] = ai;
                if(mArray[i] != 0)
                {
                    ai1 = ai;
                    if(ai == null)
                        ai1 = new int[i];
                }
                if(mArray[i] == 0 && ai1 != null)
                    throw new RSInvalidStateException("Array dimensions must be contigous from 0.");
                i--;
                ai = ai1;
            }

            Type type = new Type(mRS.nTypeCreate(mElement.getID(mRS), mDimX, mDimY, mDimZ, mDimMipmaps, mDimFaces, mYuv), mRS);
            type.mElement = mElement;
            type.mDimX = mDimX;
            type.mDimY = mDimY;
            type.mDimZ = mDimZ;
            type.mDimMipmaps = mDimMipmaps;
            type.mDimFaces = mDimFaces;
            type.mDimYuv = mYuv;
            type.mArrays = ai;
            type.calcElementCount();
            return type;
        }

        public Builder setArray(int i, int j)
        {
            if(i < 0 || i >= 4)
            {
                throw new RSIllegalArgumentException("Array dimension out of range.");
            } else
            {
                mArray[i] = j;
                return this;
            }
        }

        public Builder setFaces(boolean flag)
        {
            mDimFaces = flag;
            return this;
        }

        public Builder setMipmaps(boolean flag)
        {
            mDimMipmaps = flag;
            return this;
        }

        public Builder setX(int i)
        {
            if(i < 1)
            {
                throw new RSIllegalArgumentException("Values of less than 1 for Dimension X are not valid.");
            } else
            {
                mDimX = i;
                return this;
            }
        }

        public Builder setY(int i)
        {
            if(i < 1)
            {
                throw new RSIllegalArgumentException("Values of less than 1 for Dimension Y are not valid.");
            } else
            {
                mDimY = i;
                return this;
            }
        }

        public Builder setYuvFormat(int i)
        {
            switch(i)
            {
            default:
                throw new RSIllegalArgumentException("Only ImageFormat.NV21, .YV12, and .YUV_420_888 are supported..");

            case 17: // '\021'
            case 35: // '#'
            case 842094169: 
                mYuv = i;
                break;
            }
            return this;
        }

        public Builder setZ(int i)
        {
            if(i < 1)
            {
                throw new RSIllegalArgumentException("Values of less than 1 for Dimension Z are not valid.");
            } else
            {
                mDimZ = i;
                return this;
            }
        }

        int mArray[];
        boolean mDimFaces;
        boolean mDimMipmaps;
        int mDimX;
        int mDimY;
        int mDimZ;
        Element mElement;
        RenderScript mRS;
        int mYuv;

        public Builder(RenderScript renderscript, Element element)
        {
            mDimX = 1;
            mArray = new int[4];
            element.checkValid();
            mRS = renderscript;
            mElement = element;
        }
    }

    public static final class CubemapFace extends Enum
    {

        public static CubemapFace valueOf(String s)
        {
            return (CubemapFace)Enum.valueOf(android/renderscript/Type$CubemapFace, s);
        }

        public static CubemapFace[] values()
        {
            return $VALUES;
        }

        private static final CubemapFace $VALUES[];
        public static final CubemapFace NEGATIVE_X;
        public static final CubemapFace NEGATIVE_Y;
        public static final CubemapFace NEGATIVE_Z;
        public static final CubemapFace POSITIVE_X;
        public static final CubemapFace POSITIVE_Y;
        public static final CubemapFace POSITIVE_Z;
        public static final CubemapFace POSITVE_X;
        public static final CubemapFace POSITVE_Y;
        public static final CubemapFace POSITVE_Z;
        int mID;

        static 
        {
            POSITIVE_X = new CubemapFace("POSITIVE_X", 0, 0);
            NEGATIVE_X = new CubemapFace("NEGATIVE_X", 1, 1);
            POSITIVE_Y = new CubemapFace("POSITIVE_Y", 2, 2);
            NEGATIVE_Y = new CubemapFace("NEGATIVE_Y", 3, 3);
            POSITIVE_Z = new CubemapFace("POSITIVE_Z", 4, 4);
            NEGATIVE_Z = new CubemapFace("NEGATIVE_Z", 5, 5);
            POSITVE_X = new CubemapFace("POSITVE_X", 6, 0);
            POSITVE_Y = new CubemapFace("POSITVE_Y", 7, 2);
            POSITVE_Z = new CubemapFace("POSITVE_Z", 8, 4);
            $VALUES = (new CubemapFace[] {
                POSITIVE_X, NEGATIVE_X, POSITIVE_Y, NEGATIVE_Y, POSITIVE_Z, NEGATIVE_Z, POSITVE_X, POSITVE_Y, POSITVE_Z
            });
        }

        private CubemapFace(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }


    Type(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static Type createX(RenderScript renderscript, Element element, int i)
    {
        if(i < 1)
        {
            throw new RSInvalidStateException("Dimension must be >= 1.");
        } else
        {
            renderscript = new Type(renderscript.nTypeCreate(element.getID(renderscript), i, 0, 0, false, false, 0), renderscript);
            renderscript.mElement = element;
            renderscript.mDimX = i;
            renderscript.calcElementCount();
            return renderscript;
        }
    }

    public static Type createXY(RenderScript renderscript, Element element, int i, int j)
    {
        if(i < 1 || j < 1)
        {
            throw new RSInvalidStateException("Dimension must be >= 1.");
        } else
        {
            renderscript = new Type(renderscript.nTypeCreate(element.getID(renderscript), i, j, 0, false, false, 0), renderscript);
            renderscript.mElement = element;
            renderscript.mDimX = i;
            renderscript.mDimY = j;
            renderscript.calcElementCount();
            return renderscript;
        }
    }

    public static Type createXYZ(RenderScript renderscript, Element element, int i, int j, int k)
    {
        while(i < 1 || j < 1 || k < 1) 
            throw new RSInvalidStateException("Dimension must be >= 1.");
        renderscript = new Type(renderscript.nTypeCreate(element.getID(renderscript), i, j, k, false, false, 0), renderscript);
        renderscript.mElement = element;
        renderscript.mDimX = i;
        renderscript.mDimY = j;
        renderscript.mDimZ = k;
        renderscript.calcElementCount();
        return renderscript;
    }

    void calcElementCount()
    {
        boolean flag = hasMipmaps();
        int i = getX();
        int l = getY();
        int i1 = getZ();
        byte byte0 = 1;
        if(hasFaces())
            byte0 = 6;
        int j1 = i;
        if(i == 0)
            j1 = 1;
        i = l;
        if(l == 0)
            i = 1;
        l = i1;
        if(i1 == 0)
            l = 1;
        int k1 = j1 * i * l * byte0;
        i1 = i;
        int i2 = j1;
        j1 = k1;
        int j2;
        for(; flag && (i2 > 1 || i1 > 1 || l > 1); l = j2)
        {
            int j = i2;
            if(i2 > 1)
                j = i2 >> 1;
            int l1 = i1;
            if(i1 > 1)
                l1 = i1 >> 1;
            j2 = l;
            if(l > 1)
                j2 = l >> 1;
            j1 += j * l1 * j2 * byte0;
            i2 = j;
            i1 = l1;
        }

        l = j1;
        if(mArrays != null)
        {
            int k = 0;
            do
            {
                l = j1;
                if(k >= mArrays.length)
                    break;
                j1 *= mArrays[k];
                k++;
            } while(true);
        }
        mElementCount = l;
    }

    public int getArray(int i)
    {
        if(i < 0 || i >= 4)
            throw new RSIllegalArgumentException("Array dimension out of range.");
        if(mArrays == null || i >= mArrays.length)
            return 0;
        else
            return mArrays[i];
    }

    public int getArrayCount()
    {
        if(mArrays != null)
            return mArrays.length;
        else
            return 0;
    }

    public int getCount()
    {
        return mElementCount;
    }

    public Element getElement()
    {
        return mElement;
    }

    public int getX()
    {
        return mDimX;
    }

    public int getY()
    {
        return mDimY;
    }

    public int getYuv()
    {
        return mDimYuv;
    }

    public int getZ()
    {
        return mDimZ;
    }

    public boolean hasFaces()
    {
        return mDimFaces;
    }

    public boolean hasMipmaps()
    {
        return mDimMipmaps;
    }

    void updateFromNative()
    {
        boolean flag = true;
        long al[] = new long[6];
        mRS.nTypeGetNativeData(getID(mRS), al);
        mDimX = (int)al[0];
        mDimY = (int)al[1];
        mDimZ = (int)al[2];
        boolean flag1;
        long l;
        if(al[3] == 1L)
            flag1 = true;
        else
            flag1 = false;
        mDimMipmaps = flag1;
        if(al[4] == 1L)
            flag1 = flag;
        else
            flag1 = false;
        mDimFaces = flag1;
        l = al[5];
        if(l != 0L)
        {
            mElement = new Element(l, mRS);
            mElement.updateFromNative();
        }
        calcElementCount();
    }

    static final int mMaxArrays = 4;
    int mArrays[];
    boolean mDimFaces;
    boolean mDimMipmaps;
    int mDimX;
    int mDimY;
    int mDimYuv;
    int mDimZ;
    Element mElement;
    int mElementCount;
}
