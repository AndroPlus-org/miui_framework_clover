// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            BaseObj, RenderScript, RSIllegalArgumentException

public class Element extends BaseObj
{
    public static class Builder
    {

        public Builder add(Element element, String s)
        {
            return add(element, s, 1);
        }

        public Builder add(Element element, String s, int i)
        {
            if(i < 1)
                throw new RSIllegalArgumentException("Array size cannot be less than 1.");
            if(mSkipPadding != 0 && s.startsWith("#padding_"))
            {
                mSkipPadding = 0;
                return this;
            }
            if(element.mVectorSize == 3)
                mSkipPadding = 1;
            else
                mSkipPadding = 0;
            if(mCount == mElements.length)
            {
                Element aelement[] = new Element[mCount + 8];
                String as[] = new String[mCount + 8];
                int ai[] = new int[mCount + 8];
                System.arraycopy(mElements, 0, aelement, 0, mCount);
                System.arraycopy(mElementNames, 0, as, 0, mCount);
                System.arraycopy(mArraySizes, 0, ai, 0, mCount);
                mElements = aelement;
                mElementNames = as;
                mArraySizes = ai;
            }
            mElements[mCount] = element;
            mElementNames[mCount] = s;
            mArraySizes[mCount] = i;
            mCount = mCount + 1;
            return this;
        }

        public Element create()
        {
            mRS.validate();
            Element aelement[] = new Element[mCount];
            String as[] = new String[mCount];
            int ai[] = new int[mCount];
            System.arraycopy(mElements, 0, aelement, 0, mCount);
            System.arraycopy(mElementNames, 0, as, 0, mCount);
            System.arraycopy(mArraySizes, 0, ai, 0, mCount);
            long al[] = new long[aelement.length];
            for(int i = 0; i < aelement.length; i++)
                al[i] = aelement[i].getID(mRS);

            return new Element(mRS.nElementCreate2(al, as, ai), mRS, aelement, as, ai);
        }

        int mArraySizes[];
        int mCount;
        String mElementNames[];
        Element mElements[];
        RenderScript mRS;
        int mSkipPadding;

        public Builder(RenderScript renderscript)
        {
            mRS = renderscript;
            mCount = 0;
            mElements = new Element[8];
            mElementNames = new String[8];
            mArraySizes = new int[8];
        }
    }

    public static final class DataKind extends Enum
    {

        public static DataKind valueOf(String s)
        {
            return (DataKind)Enum.valueOf(android/renderscript/Element$DataKind, s);
        }

        public static DataKind[] values()
        {
            return $VALUES;
        }

        private static final DataKind $VALUES[];
        public static final DataKind PIXEL_A;
        public static final DataKind PIXEL_DEPTH;
        public static final DataKind PIXEL_L;
        public static final DataKind PIXEL_LA;
        public static final DataKind PIXEL_RGB;
        public static final DataKind PIXEL_RGBA;
        public static final DataKind PIXEL_YUV;
        public static final DataKind USER;
        int mID;

        static 
        {
            USER = new DataKind("USER", 0, 0);
            PIXEL_L = new DataKind("PIXEL_L", 1, 7);
            PIXEL_A = new DataKind("PIXEL_A", 2, 8);
            PIXEL_LA = new DataKind("PIXEL_LA", 3, 9);
            PIXEL_RGB = new DataKind("PIXEL_RGB", 4, 10);
            PIXEL_RGBA = new DataKind("PIXEL_RGBA", 5, 11);
            PIXEL_DEPTH = new DataKind("PIXEL_DEPTH", 6, 12);
            PIXEL_YUV = new DataKind("PIXEL_YUV", 7, 13);
            $VALUES = (new DataKind[] {
                USER, PIXEL_L, PIXEL_A, PIXEL_LA, PIXEL_RGB, PIXEL_RGBA, PIXEL_DEPTH, PIXEL_YUV
            });
        }

        private DataKind(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    public static final class DataType extends Enum
    {

        public static DataType valueOf(String s)
        {
            return (DataType)Enum.valueOf(android/renderscript/Element$DataType, s);
        }

        public static DataType[] values()
        {
            return $VALUES;
        }

        private static final DataType $VALUES[];
        public static final DataType BOOLEAN;
        public static final DataType FLOAT_16;
        public static final DataType FLOAT_32;
        public static final DataType FLOAT_64;
        public static final DataType MATRIX_2X2;
        public static final DataType MATRIX_3X3;
        public static final DataType MATRIX_4X4;
        public static final DataType NONE;
        public static final DataType RS_ALLOCATION;
        public static final DataType RS_ELEMENT;
        public static final DataType RS_FONT;
        public static final DataType RS_MESH;
        public static final DataType RS_PROGRAM_FRAGMENT;
        public static final DataType RS_PROGRAM_RASTER;
        public static final DataType RS_PROGRAM_STORE;
        public static final DataType RS_PROGRAM_VERTEX;
        public static final DataType RS_SAMPLER;
        public static final DataType RS_SCRIPT;
        public static final DataType RS_TYPE;
        public static final DataType SIGNED_16;
        public static final DataType SIGNED_32;
        public static final DataType SIGNED_64;
        public static final DataType SIGNED_8;
        public static final DataType UNSIGNED_16;
        public static final DataType UNSIGNED_32;
        public static final DataType UNSIGNED_4_4_4_4;
        public static final DataType UNSIGNED_5_5_5_1;
        public static final DataType UNSIGNED_5_6_5;
        public static final DataType UNSIGNED_64;
        public static final DataType UNSIGNED_8;
        int mID;
        int mSize;

        static 
        {
            NONE = new DataType("NONE", 0, 0, 0);
            FLOAT_16 = new DataType("FLOAT_16", 1, 1, 2);
            FLOAT_32 = new DataType("FLOAT_32", 2, 2, 4);
            FLOAT_64 = new DataType("FLOAT_64", 3, 3, 8);
            SIGNED_8 = new DataType("SIGNED_8", 4, 4, 1);
            SIGNED_16 = new DataType("SIGNED_16", 5, 5, 2);
            SIGNED_32 = new DataType("SIGNED_32", 6, 6, 4);
            SIGNED_64 = new DataType("SIGNED_64", 7, 7, 8);
            UNSIGNED_8 = new DataType("UNSIGNED_8", 8, 8, 1);
            UNSIGNED_16 = new DataType("UNSIGNED_16", 9, 9, 2);
            UNSIGNED_32 = new DataType("UNSIGNED_32", 10, 10, 4);
            UNSIGNED_64 = new DataType("UNSIGNED_64", 11, 11, 8);
            BOOLEAN = new DataType("BOOLEAN", 12, 12, 1);
            UNSIGNED_5_6_5 = new DataType("UNSIGNED_5_6_5", 13, 13, 2);
            UNSIGNED_5_5_5_1 = new DataType("UNSIGNED_5_5_5_1", 14, 14, 2);
            UNSIGNED_4_4_4_4 = new DataType("UNSIGNED_4_4_4_4", 15, 15, 2);
            MATRIX_4X4 = new DataType("MATRIX_4X4", 16, 16, 64);
            MATRIX_3X3 = new DataType("MATRIX_3X3", 17, 17, 36);
            MATRIX_2X2 = new DataType("MATRIX_2X2", 18, 18, 16);
            RS_ELEMENT = new DataType("RS_ELEMENT", 19, 1000);
            RS_TYPE = new DataType("RS_TYPE", 20, 1001);
            RS_ALLOCATION = new DataType("RS_ALLOCATION", 21, 1002);
            RS_SAMPLER = new DataType("RS_SAMPLER", 22, 1003);
            RS_SCRIPT = new DataType("RS_SCRIPT", 23, 1004);
            RS_MESH = new DataType("RS_MESH", 24, 1005);
            RS_PROGRAM_FRAGMENT = new DataType("RS_PROGRAM_FRAGMENT", 25, 1006);
            RS_PROGRAM_VERTEX = new DataType("RS_PROGRAM_VERTEX", 26, 1007);
            RS_PROGRAM_RASTER = new DataType("RS_PROGRAM_RASTER", 27, 1008);
            RS_PROGRAM_STORE = new DataType("RS_PROGRAM_STORE", 28, 1009);
            RS_FONT = new DataType("RS_FONT", 29, 1010);
            $VALUES = (new DataType[] {
                NONE, FLOAT_16, FLOAT_32, FLOAT_64, SIGNED_8, SIGNED_16, SIGNED_32, SIGNED_64, UNSIGNED_8, UNSIGNED_16, 
                UNSIGNED_32, UNSIGNED_64, BOOLEAN, UNSIGNED_5_6_5, UNSIGNED_5_5_5_1, UNSIGNED_4_4_4_4, MATRIX_4X4, MATRIX_3X3, MATRIX_2X2, RS_ELEMENT, 
                RS_TYPE, RS_ALLOCATION, RS_SAMPLER, RS_SCRIPT, RS_MESH, RS_PROGRAM_FRAGMENT, RS_PROGRAM_VERTEX, RS_PROGRAM_RASTER, RS_PROGRAM_STORE, RS_FONT
            });
        }

        private DataType(String s, int i, int j)
        {
            super(s, i);
            mID = j;
            mSize = 4;
            if(RenderScript.sPointerSize == 8)
                mSize = 32;
        }

        private DataType(String s, int i, int j, int k)
        {
            super(s, i);
            mID = j;
            mSize = k;
        }
    }


    private static int[] _2D_getandroid_2D_renderscript_2D_Element$DataKindSwitchesValues()
    {
        if(_2D_android_2D_renderscript_2D_Element$DataKindSwitchesValues != null)
            return _2D_android_2D_renderscript_2D_Element$DataKindSwitchesValues;
        int ai[] = new int[DataKind.values().length];
        try
        {
            ai[DataKind.PIXEL_A.ordinal()] = 17;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[DataKind.PIXEL_DEPTH.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[DataKind.PIXEL_L.ordinal()] = 18;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[DataKind.PIXEL_LA.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[DataKind.PIXEL_RGB.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[DataKind.PIXEL_RGBA.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[DataKind.PIXEL_YUV.ordinal()] = 19;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[DataKind.USER.ordinal()] = 20;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_renderscript_2D_Element$DataKindSwitchesValues = ai;
        return ai;
    }

    private static int[] _2D_getandroid_2D_renderscript_2D_Element$DataTypeSwitchesValues()
    {
        if(_2D_android_2D_renderscript_2D_Element$DataTypeSwitchesValues != null)
            return _2D_android_2D_renderscript_2D_Element$DataTypeSwitchesValues;
        int ai[] = new int[DataType.values().length];
        try
        {
            ai[DataType.BOOLEAN.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror29) { }
        try
        {
            ai[DataType.FLOAT_16.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror28) { }
        try
        {
            ai[DataType.FLOAT_32.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror27) { }
        try
        {
            ai[DataType.FLOAT_64.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror26) { }
        try
        {
            ai[DataType.MATRIX_2X2.ordinal()] = 17;
        }
        catch(NoSuchFieldError nosuchfielderror25) { }
        try
        {
            ai[DataType.MATRIX_3X3.ordinal()] = 18;
        }
        catch(NoSuchFieldError nosuchfielderror24) { }
        try
        {
            ai[DataType.MATRIX_4X4.ordinal()] = 19;
        }
        catch(NoSuchFieldError nosuchfielderror23) { }
        try
        {
            ai[DataType.NONE.ordinal()] = 20;
        }
        catch(NoSuchFieldError nosuchfielderror22) { }
        try
        {
            ai[DataType.RS_ALLOCATION.ordinal()] = 21;
        }
        catch(NoSuchFieldError nosuchfielderror21) { }
        try
        {
            ai[DataType.RS_ELEMENT.ordinal()] = 22;
        }
        catch(NoSuchFieldError nosuchfielderror20) { }
        try
        {
            ai[DataType.RS_FONT.ordinal()] = 23;
        }
        catch(NoSuchFieldError nosuchfielderror19) { }
        try
        {
            ai[DataType.RS_MESH.ordinal()] = 24;
        }
        catch(NoSuchFieldError nosuchfielderror18) { }
        try
        {
            ai[DataType.RS_PROGRAM_FRAGMENT.ordinal()] = 25;
        }
        catch(NoSuchFieldError nosuchfielderror17) { }
        try
        {
            ai[DataType.RS_PROGRAM_RASTER.ordinal()] = 26;
        }
        catch(NoSuchFieldError nosuchfielderror16) { }
        try
        {
            ai[DataType.RS_PROGRAM_STORE.ordinal()] = 27;
        }
        catch(NoSuchFieldError nosuchfielderror15) { }
        try
        {
            ai[DataType.RS_PROGRAM_VERTEX.ordinal()] = 28;
        }
        catch(NoSuchFieldError nosuchfielderror14) { }
        try
        {
            ai[DataType.RS_SAMPLER.ordinal()] = 29;
        }
        catch(NoSuchFieldError nosuchfielderror13) { }
        try
        {
            ai[DataType.RS_SCRIPT.ordinal()] = 30;
        }
        catch(NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai[DataType.RS_TYPE.ordinal()] = 31;
        }
        catch(NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai[DataType.SIGNED_16.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai[DataType.SIGNED_32.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[DataType.SIGNED_64.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[DataType.SIGNED_8.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[DataType.UNSIGNED_16.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[DataType.UNSIGNED_32.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[DataType.UNSIGNED_4_4_4_4.ordinal()] = 32;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[DataType.UNSIGNED_5_5_5_1.ordinal()] = 33;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[DataType.UNSIGNED_5_6_5.ordinal()] = 34;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[DataType.UNSIGNED_64.ordinal()] = 11;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[DataType.UNSIGNED_8.ordinal()] = 12;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_renderscript_2D_Element$DataTypeSwitchesValues = ai;
        return ai;
    }

    Element(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    Element(long l, RenderScript renderscript, DataType datatype, DataKind datakind, boolean flag, int i)
    {
        super(l, renderscript);
        if(datatype != DataType.UNSIGNED_5_6_5 && datatype != DataType.UNSIGNED_4_4_4_4 && datatype != DataType.UNSIGNED_5_5_5_1)
        {
            if(i == 3)
                mSize = datatype.mSize * 4;
            else
                mSize = datatype.mSize * i;
        } else
        {
            mSize = datatype.mSize;
        }
        mType = datatype;
        mKind = datakind;
        mNormalized = flag;
        mVectorSize = i;
    }

    Element(long l, RenderScript renderscript, Element aelement[], String as[], int ai[])
    {
        super(l, renderscript);
        mSize = 0;
        mVectorSize = 1;
        mElements = aelement;
        mElementNames = as;
        mArraySizes = ai;
        mType = DataType.NONE;
        mKind = DataKind.USER;
        mOffsetInBytes = new int[mElements.length];
        for(int i = 0; i < mElements.length; i++)
        {
            mOffsetInBytes[i] = mSize;
            mSize = mSize + mElements[i].mSize * mArraySizes[i];
        }

        updateVisibleSubElements();
    }

    public static Element ALLOCATION(RenderScript renderscript)
    {
        if(renderscript.mElement_ALLOCATION != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_ALLOCATION == null)
            renderscript.mElement_ALLOCATION = createUser(renderscript, DataType.RS_ALLOCATION);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_ALLOCATION;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element A_8(RenderScript renderscript)
    {
        if(renderscript.mElement_A_8 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_A_8 == null)
            renderscript.mElement_A_8 = createPixel(renderscript, DataType.UNSIGNED_8, DataKind.PIXEL_A);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_A_8;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element BOOLEAN(RenderScript renderscript)
    {
        if(renderscript.mElement_BOOLEAN != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_BOOLEAN == null)
            renderscript.mElement_BOOLEAN = createUser(renderscript, DataType.BOOLEAN);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_BOOLEAN;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element ELEMENT(RenderScript renderscript)
    {
        if(renderscript.mElement_ELEMENT != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_ELEMENT == null)
            renderscript.mElement_ELEMENT = createUser(renderscript, DataType.RS_ELEMENT);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_ELEMENT;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F16(RenderScript renderscript)
    {
        if(renderscript.mElement_F16 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_F16 == null)
            renderscript.mElement_F16 = createUser(renderscript, DataType.FLOAT_16);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_F16;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F16_2(RenderScript renderscript)
    {
        if(renderscript.mElement_HALF_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_HALF_2 == null)
            renderscript.mElement_HALF_2 = createVector(renderscript, DataType.FLOAT_16, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_HALF_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F16_3(RenderScript renderscript)
    {
        if(renderscript.mElement_HALF_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_HALF_3 == null)
            renderscript.mElement_HALF_3 = createVector(renderscript, DataType.FLOAT_16, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_HALF_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F16_4(RenderScript renderscript)
    {
        if(renderscript.mElement_HALF_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_HALF_4 == null)
            renderscript.mElement_HALF_4 = createVector(renderscript, DataType.FLOAT_16, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_HALF_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F32(RenderScript renderscript)
    {
        if(renderscript.mElement_F32 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_F32 == null)
            renderscript.mElement_F32 = createUser(renderscript, DataType.FLOAT_32);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_F32;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F32_2(RenderScript renderscript)
    {
        if(renderscript.mElement_FLOAT_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_FLOAT_2 == null)
            renderscript.mElement_FLOAT_2 = createVector(renderscript, DataType.FLOAT_32, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_FLOAT_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F32_3(RenderScript renderscript)
    {
        if(renderscript.mElement_FLOAT_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_FLOAT_3 == null)
            renderscript.mElement_FLOAT_3 = createVector(renderscript, DataType.FLOAT_32, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_FLOAT_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F32_4(RenderScript renderscript)
    {
        if(renderscript.mElement_FLOAT_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_FLOAT_4 == null)
            renderscript.mElement_FLOAT_4 = createVector(renderscript, DataType.FLOAT_32, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_FLOAT_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F64(RenderScript renderscript)
    {
        if(renderscript.mElement_F64 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_F64 == null)
            renderscript.mElement_F64 = createUser(renderscript, DataType.FLOAT_64);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_F64;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F64_2(RenderScript renderscript)
    {
        if(renderscript.mElement_DOUBLE_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_DOUBLE_2 == null)
            renderscript.mElement_DOUBLE_2 = createVector(renderscript, DataType.FLOAT_64, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_DOUBLE_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F64_3(RenderScript renderscript)
    {
        if(renderscript.mElement_DOUBLE_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_DOUBLE_3 == null)
            renderscript.mElement_DOUBLE_3 = createVector(renderscript, DataType.FLOAT_64, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_DOUBLE_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element F64_4(RenderScript renderscript)
    {
        if(renderscript.mElement_DOUBLE_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_DOUBLE_4 == null)
            renderscript.mElement_DOUBLE_4 = createVector(renderscript, DataType.FLOAT_64, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_DOUBLE_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element FONT(RenderScript renderscript)
    {
        if(renderscript.mElement_FONT != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_FONT == null)
            renderscript.mElement_FONT = createUser(renderscript, DataType.RS_FONT);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_FONT;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I16(RenderScript renderscript)
    {
        if(renderscript.mElement_I16 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_I16 == null)
            renderscript.mElement_I16 = createUser(renderscript, DataType.SIGNED_16);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_I16;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I16_2(RenderScript renderscript)
    {
        if(renderscript.mElement_SHORT_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_SHORT_2 == null)
            renderscript.mElement_SHORT_2 = createVector(renderscript, DataType.SIGNED_16, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_SHORT_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I16_3(RenderScript renderscript)
    {
        if(renderscript.mElement_SHORT_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_SHORT_3 == null)
            renderscript.mElement_SHORT_3 = createVector(renderscript, DataType.SIGNED_16, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_SHORT_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I16_4(RenderScript renderscript)
    {
        if(renderscript.mElement_SHORT_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_SHORT_4 == null)
            renderscript.mElement_SHORT_4 = createVector(renderscript, DataType.SIGNED_16, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_SHORT_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I32(RenderScript renderscript)
    {
        if(renderscript.mElement_I32 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_I32 == null)
            renderscript.mElement_I32 = createUser(renderscript, DataType.SIGNED_32);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_I32;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I32_2(RenderScript renderscript)
    {
        if(renderscript.mElement_INT_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_INT_2 == null)
            renderscript.mElement_INT_2 = createVector(renderscript, DataType.SIGNED_32, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_INT_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I32_3(RenderScript renderscript)
    {
        if(renderscript.mElement_INT_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_INT_3 == null)
            renderscript.mElement_INT_3 = createVector(renderscript, DataType.SIGNED_32, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_INT_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I32_4(RenderScript renderscript)
    {
        if(renderscript.mElement_INT_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_INT_4 == null)
            renderscript.mElement_INT_4 = createVector(renderscript, DataType.SIGNED_32, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_INT_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I64(RenderScript renderscript)
    {
        if(renderscript.mElement_I64 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_I64 == null)
            renderscript.mElement_I64 = createUser(renderscript, DataType.SIGNED_64);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_I64;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I64_2(RenderScript renderscript)
    {
        if(renderscript.mElement_LONG_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_LONG_2 == null)
            renderscript.mElement_LONG_2 = createVector(renderscript, DataType.SIGNED_64, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_LONG_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I64_3(RenderScript renderscript)
    {
        if(renderscript.mElement_LONG_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_LONG_3 == null)
            renderscript.mElement_LONG_3 = createVector(renderscript, DataType.SIGNED_64, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_LONG_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I64_4(RenderScript renderscript)
    {
        if(renderscript.mElement_LONG_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_LONG_4 == null)
            renderscript.mElement_LONG_4 = createVector(renderscript, DataType.SIGNED_64, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_LONG_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I8(RenderScript renderscript)
    {
        if(renderscript.mElement_I8 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_I8 == null)
            renderscript.mElement_I8 = createUser(renderscript, DataType.SIGNED_8);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_I8;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I8_2(RenderScript renderscript)
    {
        if(renderscript.mElement_CHAR_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_CHAR_2 == null)
            renderscript.mElement_CHAR_2 = createVector(renderscript, DataType.SIGNED_8, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_CHAR_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I8_3(RenderScript renderscript)
    {
        if(renderscript.mElement_CHAR_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_CHAR_3 == null)
            renderscript.mElement_CHAR_3 = createVector(renderscript, DataType.SIGNED_8, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_CHAR_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element I8_4(RenderScript renderscript)
    {
        if(renderscript.mElement_CHAR_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_CHAR_4 == null)
            renderscript.mElement_CHAR_4 = createVector(renderscript, DataType.SIGNED_8, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_CHAR_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element MATRIX4X4(RenderScript renderscript)
    {
        return MATRIX_4X4(renderscript);
    }

    public static Element MATRIX_2X2(RenderScript renderscript)
    {
        if(renderscript.mElement_MATRIX_2X2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_MATRIX_2X2 == null)
            renderscript.mElement_MATRIX_2X2 = createUser(renderscript, DataType.MATRIX_2X2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_MATRIX_2X2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element MATRIX_3X3(RenderScript renderscript)
    {
        if(renderscript.mElement_MATRIX_3X3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_MATRIX_3X3 == null)
            renderscript.mElement_MATRIX_3X3 = createUser(renderscript, DataType.MATRIX_3X3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_MATRIX_3X3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element MATRIX_4X4(RenderScript renderscript)
    {
        if(renderscript.mElement_MATRIX_4X4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_MATRIX_4X4 == null)
            renderscript.mElement_MATRIX_4X4 = createUser(renderscript, DataType.MATRIX_4X4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_MATRIX_4X4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element MESH(RenderScript renderscript)
    {
        if(renderscript.mElement_MESH != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_MESH == null)
            renderscript.mElement_MESH = createUser(renderscript, DataType.RS_MESH);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_MESH;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element PROGRAM_FRAGMENT(RenderScript renderscript)
    {
        if(renderscript.mElement_PROGRAM_FRAGMENT != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_PROGRAM_FRAGMENT == null)
            renderscript.mElement_PROGRAM_FRAGMENT = createUser(renderscript, DataType.RS_PROGRAM_FRAGMENT);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_PROGRAM_FRAGMENT;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element PROGRAM_RASTER(RenderScript renderscript)
    {
        if(renderscript.mElement_PROGRAM_RASTER != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_PROGRAM_RASTER == null)
            renderscript.mElement_PROGRAM_RASTER = createUser(renderscript, DataType.RS_PROGRAM_RASTER);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_PROGRAM_RASTER;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element PROGRAM_STORE(RenderScript renderscript)
    {
        if(renderscript.mElement_PROGRAM_STORE != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_PROGRAM_STORE == null)
            renderscript.mElement_PROGRAM_STORE = createUser(renderscript, DataType.RS_PROGRAM_STORE);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_PROGRAM_STORE;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element PROGRAM_VERTEX(RenderScript renderscript)
    {
        if(renderscript.mElement_PROGRAM_VERTEX != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_PROGRAM_VERTEX == null)
            renderscript.mElement_PROGRAM_VERTEX = createUser(renderscript, DataType.RS_PROGRAM_VERTEX);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_PROGRAM_VERTEX;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element RGBA_4444(RenderScript renderscript)
    {
        if(renderscript.mElement_RGBA_4444 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_RGBA_4444 == null)
            renderscript.mElement_RGBA_4444 = createPixel(renderscript, DataType.UNSIGNED_4_4_4_4, DataKind.PIXEL_RGBA);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_RGBA_4444;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element RGBA_5551(RenderScript renderscript)
    {
        if(renderscript.mElement_RGBA_5551 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_RGBA_5551 == null)
            renderscript.mElement_RGBA_5551 = createPixel(renderscript, DataType.UNSIGNED_5_5_5_1, DataKind.PIXEL_RGBA);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_RGBA_5551;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element RGBA_8888(RenderScript renderscript)
    {
        if(renderscript.mElement_RGBA_8888 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_RGBA_8888 == null)
            renderscript.mElement_RGBA_8888 = createPixel(renderscript, DataType.UNSIGNED_8, DataKind.PIXEL_RGBA);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_RGBA_8888;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element RGB_565(RenderScript renderscript)
    {
        if(renderscript.mElement_RGB_565 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_RGB_565 == null)
            renderscript.mElement_RGB_565 = createPixel(renderscript, DataType.UNSIGNED_5_6_5, DataKind.PIXEL_RGB);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_RGB_565;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element RGB_888(RenderScript renderscript)
    {
        if(renderscript.mElement_RGB_888 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_RGB_888 == null)
            renderscript.mElement_RGB_888 = createPixel(renderscript, DataType.UNSIGNED_8, DataKind.PIXEL_RGB);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_RGB_888;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element SAMPLER(RenderScript renderscript)
    {
        if(renderscript.mElement_SAMPLER != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_SAMPLER == null)
            renderscript.mElement_SAMPLER = createUser(renderscript, DataType.RS_SAMPLER);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_SAMPLER;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element SCRIPT(RenderScript renderscript)
    {
        if(renderscript.mElement_SCRIPT != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_SCRIPT == null)
            renderscript.mElement_SCRIPT = createUser(renderscript, DataType.RS_SCRIPT);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_SCRIPT;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element TYPE(RenderScript renderscript)
    {
        if(renderscript.mElement_TYPE != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_TYPE == null)
            renderscript.mElement_TYPE = createUser(renderscript, DataType.RS_TYPE);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_TYPE;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U16(RenderScript renderscript)
    {
        if(renderscript.mElement_U16 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_U16 == null)
            renderscript.mElement_U16 = createUser(renderscript, DataType.UNSIGNED_16);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_U16;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U16_2(RenderScript renderscript)
    {
        if(renderscript.mElement_USHORT_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_USHORT_2 == null)
            renderscript.mElement_USHORT_2 = createVector(renderscript, DataType.UNSIGNED_16, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_USHORT_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U16_3(RenderScript renderscript)
    {
        if(renderscript.mElement_USHORT_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_USHORT_3 == null)
            renderscript.mElement_USHORT_3 = createVector(renderscript, DataType.UNSIGNED_16, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_USHORT_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U16_4(RenderScript renderscript)
    {
        if(renderscript.mElement_USHORT_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_USHORT_4 == null)
            renderscript.mElement_USHORT_4 = createVector(renderscript, DataType.UNSIGNED_16, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_USHORT_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U32(RenderScript renderscript)
    {
        if(renderscript.mElement_U32 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_U32 == null)
            renderscript.mElement_U32 = createUser(renderscript, DataType.UNSIGNED_32);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_U32;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U32_2(RenderScript renderscript)
    {
        if(renderscript.mElement_UINT_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_UINT_2 == null)
            renderscript.mElement_UINT_2 = createVector(renderscript, DataType.UNSIGNED_32, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_UINT_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U32_3(RenderScript renderscript)
    {
        if(renderscript.mElement_UINT_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_UINT_3 == null)
            renderscript.mElement_UINT_3 = createVector(renderscript, DataType.UNSIGNED_32, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_UINT_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U32_4(RenderScript renderscript)
    {
        if(renderscript.mElement_UINT_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_UINT_4 == null)
            renderscript.mElement_UINT_4 = createVector(renderscript, DataType.UNSIGNED_32, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_UINT_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U64(RenderScript renderscript)
    {
        if(renderscript.mElement_U64 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_U64 == null)
            renderscript.mElement_U64 = createUser(renderscript, DataType.UNSIGNED_64);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_U64;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U64_2(RenderScript renderscript)
    {
        if(renderscript.mElement_ULONG_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_ULONG_2 == null)
            renderscript.mElement_ULONG_2 = createVector(renderscript, DataType.UNSIGNED_64, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_ULONG_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U64_3(RenderScript renderscript)
    {
        if(renderscript.mElement_ULONG_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_ULONG_3 == null)
            renderscript.mElement_ULONG_3 = createVector(renderscript, DataType.UNSIGNED_64, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_ULONG_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U64_4(RenderScript renderscript)
    {
        if(renderscript.mElement_ULONG_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_ULONG_4 == null)
            renderscript.mElement_ULONG_4 = createVector(renderscript, DataType.UNSIGNED_64, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_ULONG_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U8(RenderScript renderscript)
    {
        if(renderscript.mElement_U8 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_U8 == null)
            renderscript.mElement_U8 = createUser(renderscript, DataType.UNSIGNED_8);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_U8;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U8_2(RenderScript renderscript)
    {
        if(renderscript.mElement_UCHAR_2 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_UCHAR_2 == null)
            renderscript.mElement_UCHAR_2 = createVector(renderscript, DataType.UNSIGNED_8, 2);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_UCHAR_2;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U8_3(RenderScript renderscript)
    {
        if(renderscript.mElement_UCHAR_3 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_UCHAR_3 == null)
            renderscript.mElement_UCHAR_3 = createVector(renderscript, DataType.UNSIGNED_8, 3);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_UCHAR_3;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element U8_4(RenderScript renderscript)
    {
        if(renderscript.mElement_UCHAR_4 != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_UCHAR_4 == null)
            renderscript.mElement_UCHAR_4 = createVector(renderscript, DataType.UNSIGNED_8, 4);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_UCHAR_4;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element YUV(RenderScript renderscript)
    {
        if(renderscript.mElement_YUV != null) goto _L2; else goto _L1
_L1:
        renderscript;
        JVM INSTR monitorenter ;
        if(renderscript.mElement_YUV == null)
            renderscript.mElement_YUV = createPixel(renderscript, DataType.UNSIGNED_8, DataKind.PIXEL_YUV);
        renderscript;
        JVM INSTR monitorexit ;
_L2:
        return renderscript.mElement_YUV;
        Exception exception;
        exception;
        throw exception;
    }

    public static Element createPixel(RenderScript renderscript, DataType datatype, DataKind datakind)
    {
        int i;
        if(datakind != DataKind.PIXEL_L && datakind != DataKind.PIXEL_A && datakind != DataKind.PIXEL_LA && datakind != DataKind.PIXEL_RGB && datakind != DataKind.PIXEL_RGBA && datakind != DataKind.PIXEL_DEPTH && datakind != DataKind.PIXEL_YUV)
            throw new RSIllegalArgumentException("Unsupported DataKind");
        if(datatype != DataType.UNSIGNED_8 && datatype != DataType.UNSIGNED_16 && datatype != DataType.UNSIGNED_5_6_5 && datatype != DataType.UNSIGNED_4_4_4_4 && datatype != DataType.UNSIGNED_5_5_5_1)
            throw new RSIllegalArgumentException("Unsupported DataType");
        if(datatype == DataType.UNSIGNED_5_6_5 && datakind != DataKind.PIXEL_RGB)
            throw new RSIllegalArgumentException("Bad kind and type combo");
        if(datatype == DataType.UNSIGNED_5_5_5_1 && datakind != DataKind.PIXEL_RGBA)
            throw new RSIllegalArgumentException("Bad kind and type combo");
        if(datatype == DataType.UNSIGNED_4_4_4_4 && datakind != DataKind.PIXEL_RGBA)
            throw new RSIllegalArgumentException("Bad kind and type combo");
        if(datatype == DataType.UNSIGNED_16 && datakind != DataKind.PIXEL_DEPTH)
            throw new RSIllegalArgumentException("Bad kind and type combo");
        i = 1;
        _2D_getandroid_2D_renderscript_2D_Element$DataKindSwitchesValues()[datakind.ordinal()];
        JVM INSTR tableswitch 1 4: default 248
    //                   1 291
    //                   2 276
    //                   3 281
    //                   4 286;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return new Element(renderscript.nElementCreate(datatype.mID, datakind.mID, true, i), renderscript, datatype, datakind, true, i);
_L3:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 3;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 4;
        continue; /* Loop/switch isn't completed */
_L2:
        i = 2;
        if(true) goto _L1; else goto _L6
_L6:
    }

    static Element createUser(RenderScript renderscript, DataType datatype)
    {
        DataKind datakind = DataKind.USER;
        return new Element(renderscript.nElementCreate(datatype.mID, datakind.mID, false, 1), renderscript, datatype, datakind, false, 1);
    }

    public static Element createVector(RenderScript renderscript, DataType datatype, int i)
    {
        if(i < 2 || i > 4)
            throw new RSIllegalArgumentException("Vector size out of range 2-4.");
        DataKind datakind;
        switch(_2D_getandroid_2D_renderscript_2D_Element$DataTypeSwitchesValues()[datatype.ordinal()])
        {
        default:
            throw new RSIllegalArgumentException("Cannot create vector of non-primitive type.");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
            datakind = DataKind.USER;
            break;
        }
        return new Element(renderscript.nElementCreate(datatype.mID, datakind.mID, false, i), renderscript, datatype, datakind, false, i);
    }

    private void updateVisibleSubElements()
    {
        if(mElements == null)
            return;
        int i = 0;
        int j = mElementNames.length;
        for(int k = 0; k < j;)
        {
            int i1 = i;
            if(mElementNames[k].charAt(0) != '#')
                i1 = i + 1;
            k++;
            i = i1;
        }

        mVisibleElementMap = new int[i];
        i = 0;
        int l = 0;
        for(; i < j; i++)
            if(mElementNames[i].charAt(0) != '#')
            {
                int ai[] = mVisibleElementMap;
                int j1 = l + 1;
                ai[l] = i;
                l = j1;
            }

    }

    public int getBytesSize()
    {
        return mSize;
    }

    public DataKind getDataKind()
    {
        return mKind;
    }

    public DataType getDataType()
    {
        return mType;
    }

    public Element getSubElement(int i)
    {
        if(mVisibleElementMap == null)
            throw new RSIllegalArgumentException("Element contains no sub-elements");
        if(i < 0 || i >= mVisibleElementMap.length)
            throw new RSIllegalArgumentException("Illegal sub-element index");
        else
            return mElements[mVisibleElementMap[i]];
    }

    public int getSubElementArraySize(int i)
    {
        if(mVisibleElementMap == null)
            throw new RSIllegalArgumentException("Element contains no sub-elements");
        if(i < 0 || i >= mVisibleElementMap.length)
            throw new RSIllegalArgumentException("Illegal sub-element index");
        else
            return mArraySizes[mVisibleElementMap[i]];
    }

    public int getSubElementCount()
    {
        if(mVisibleElementMap == null)
            return 0;
        else
            return mVisibleElementMap.length;
    }

    public String getSubElementName(int i)
    {
        if(mVisibleElementMap == null)
            throw new RSIllegalArgumentException("Element contains no sub-elements");
        if(i < 0 || i >= mVisibleElementMap.length)
            throw new RSIllegalArgumentException("Illegal sub-element index");
        else
            return mElementNames[mVisibleElementMap[i]];
    }

    public int getSubElementOffsetBytes(int i)
    {
        if(mVisibleElementMap == null)
            throw new RSIllegalArgumentException("Element contains no sub-elements");
        if(i < 0 || i >= mVisibleElementMap.length)
            throw new RSIllegalArgumentException("Illegal sub-element index");
        else
            return mOffsetInBytes[mVisibleElementMap[i]];
    }

    public int getVectorSize()
    {
        return mVectorSize;
    }

    public boolean isCompatible(Element element)
    {
        boolean flag = true;
        if(equals(element))
            return true;
        if(mSize == element.mSize && mType != DataType.NONE && mType == element.mType)
        {
            if(mVectorSize != element.mVectorSize)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean isComplex()
    {
        if(mElements == null)
            return false;
        for(int i = 0; i < mElements.length; i++)
            if(mElements[i].mElements != null)
                return true;

        return false;
    }

    void updateFromNative()
    {
        int i = 0;
        super.updateFromNative();
        int ai[] = new int[5];
        mRS.nElementGetNativeData(getID(mRS), ai);
        boolean flag;
        DataType adatatype[];
        int j;
        if(ai[2] == 1)
            flag = true;
        else
            flag = false;
        mNormalized = flag;
        mVectorSize = ai[3];
        mSize = 0;
        adatatype = DataType.values();
        j = adatatype.length;
        for(int k = 0; k < j; k++)
        {
            DataType datatype = adatatype[k];
            if(datatype.mID == ai[0])
            {
                mType = datatype;
                mSize = mType.mSize * mVectorSize;
            }
        }

        DataKind adatakind[] = DataKind.values();
        j = adatakind.length;
        for(int l = i; l < j; l++)
        {
            DataKind datakind = adatakind[l];
            if(datakind.mID == ai[1])
                mKind = datakind;
        }

        i = ai[4];
        if(i > 0)
        {
            mElements = new Element[i];
            mElementNames = new String[i];
            mArraySizes = new int[i];
            mOffsetInBytes = new int[i];
            long al[] = new long[i];
            mRS.nElementGetSubElements(getID(mRS), al, mElementNames, mArraySizes);
            for(int i1 = 0; i1 < i; i1++)
            {
                mElements[i1] = new Element(al[i1], mRS);
                mElements[i1].updateFromNative();
                mOffsetInBytes[i1] = mSize;
                mSize = mSize + mElements[i1].mSize * mArraySizes[i1];
            }

        }
        updateVisibleSubElements();
    }

    private static final int _2D_android_2D_renderscript_2D_Element$DataKindSwitchesValues[];
    private static final int _2D_android_2D_renderscript_2D_Element$DataTypeSwitchesValues[];
    int mArraySizes[];
    String mElementNames[];
    Element mElements[];
    DataKind mKind;
    boolean mNormalized;
    int mOffsetInBytes[];
    int mSize;
    DataType mType;
    int mVectorSize;
    int mVisibleElementMap[];
}
