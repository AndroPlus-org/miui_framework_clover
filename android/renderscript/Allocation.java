// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.*;
import android.os.Trace;
import android.util.Log;
import android.view.Surface;
import dalvik.system.CloseGuard;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.HashMap;

// Referenced classes of package android.renderscript:
//            BaseObj, RSIllegalArgumentException, Type, Element, 
//            RenderScript, RSRuntimeException, RSInvalidStateException, AllocationAdapter, 
//            FieldPacker

public class Allocation extends BaseObj
{
    public static final class MipmapControl extends Enum
    {

        public static MipmapControl valueOf(String s)
        {
            return (MipmapControl)Enum.valueOf(android/renderscript/Allocation$MipmapControl, s);
        }

        public static MipmapControl[] values()
        {
            return $VALUES;
        }

        private static final MipmapControl $VALUES[];
        public static final MipmapControl MIPMAP_FULL;
        public static final MipmapControl MIPMAP_NONE;
        public static final MipmapControl MIPMAP_ON_SYNC_TO_TEXTURE;
        int mID;

        static 
        {
            MIPMAP_NONE = new MipmapControl("MIPMAP_NONE", 0, 0);
            MIPMAP_FULL = new MipmapControl("MIPMAP_FULL", 1, 1);
            MIPMAP_ON_SYNC_TO_TEXTURE = new MipmapControl("MIPMAP_ON_SYNC_TO_TEXTURE", 2, 2);
            $VALUES = (new MipmapControl[] {
                MIPMAP_NONE, MIPMAP_FULL, MIPMAP_ON_SYNC_TO_TEXTURE
            });
        }

        private MipmapControl(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    public static interface OnBufferAvailableListener
    {

        public abstract void onBufferAvailable(Allocation allocation);
    }


    private static int[] _2D_getandroid_2D_graphics_2D_Bitmap$ConfigSwitchesValues()
    {
        if(_2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues != null)
            return _2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues;
        int ai[] = new int[android.graphics.Bitmap.Config.values().length];
        try
        {
            ai[android.graphics.Bitmap.Config.ALPHA_8.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[android.graphics.Bitmap.Config.ARGB_4444.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[android.graphics.Bitmap.Config.ARGB_8888.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[android.graphics.Bitmap.Config.HARDWARE.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[android.graphics.Bitmap.Config.RGBA_F16.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[android.graphics.Bitmap.Config.RGB_565.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues = ai;
        return ai;
    }

    Allocation(long l, RenderScript renderscript, Type type, int i)
    {
        super(l, renderscript);
        mOwningType = false;
        mTimeStamp = -1L;
        mReadAllowed = true;
        mWriteAllowed = true;
        mAutoPadding = false;
        mSelectedFace = Type.CubemapFace.POSITIVE_X;
        mGetSurfaceSurface = null;
        mByteBuffer = null;
        mByteBufferStride = -1L;
        if((i & 0xffffff00) != 0)
            throw new RSIllegalArgumentException("Unknown usage specified.");
        if((i & 0x20) != 0)
        {
            mWriteAllowed = false;
            if((i & 0xffffffdc) != 0)
                throw new RSIllegalArgumentException("Invalid usage combination.");
        }
        mType = type;
        mUsage = i;
        if(type != null)
        {
            mSize = mType.getCount() * mType.getElement().getBytesSize();
            updateCacheInfo(type);
        }
        try
        {
            RenderScript.registerNativeAllocation.invoke(RenderScript.sRuntime, new Object[] {
                Integer.valueOf(mSize)
            });
        }
        // Misplaced declaration of an exception variable
        catch(RenderScript renderscript)
        {
            Log.e("RenderScript_jni", (new StringBuilder()).append("Couldn't invoke registerNativeAllocation:").append(renderscript).toString());
            throw new RSRuntimeException((new StringBuilder()).append("Couldn't invoke registerNativeAllocation:").append(renderscript).toString());
        }
        guard.open("destroy");
    }

    Allocation(long l, RenderScript renderscript, Type type, boolean flag, int i, MipmapControl mipmapcontrol)
    {
        this(l, renderscript, type, i);
        mOwningType = flag;
        mMipmapControl = mipmapcontrol;
    }

    private void copy1DRangeFromUnchecked(int i, int j, Object obj, Element.DataType datatype, int k)
    {
        int l;
        Trace.traceBegin(32768L, "copy1DRangeFromUnchecked");
        l = mType.mElement.getBytesSize() * j;
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(!mAutoPadding)
            break MISSING_BLOCK_LABEL_58;
        flag1 = flag;
        if(mType.getElement().getVectorSize() == 3)
            flag1 = true;
        data1DChecks(i, j, k * datatype.mSize, l, flag1);
        mRS.nAllocationData1D(getIDSafe(), i, mSelectedLOD, j, obj, l, datatype, mType.mElement.mType.mSize, flag1);
        Trace.traceEnd(32768L);
        return;
        obj;
        Trace.traceEnd(32768L);
        throw obj;
    }

    private void copy1DRangeToUnchecked(int i, int j, Object obj, Element.DataType datatype, int k)
    {
        int l;
        Trace.traceBegin(32768L, "copy1DRangeToUnchecked");
        l = mType.mElement.getBytesSize() * j;
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(!mAutoPadding)
            break MISSING_BLOCK_LABEL_58;
        flag1 = flag;
        if(mType.getElement().getVectorSize() == 3)
            flag1 = true;
        data1DChecks(i, j, k * datatype.mSize, l, flag1);
        mRS.nAllocationRead1D(getIDSafe(), i, mSelectedLOD, j, obj, l, datatype, mType.mElement.mType.mSize, flag1);
        Trace.traceEnd(32768L);
        return;
        obj;
        Trace.traceEnd(32768L);
        throw obj;
    }

    private void copy3DRangeFromUnchecked(int i, int j, int k, int l, int i1, int j1, Object obj, 
            Element.DataType datatype, int k1)
    {
        int l1;
        Trace.traceBegin(32768L, "copy3DRangeFromUnchecked");
        mRS.validate();
        validate3DRange(i, j, k, l, i1, j1);
        l1 = mType.mElement.getBytesSize() * l * i1 * j1;
        boolean flag = false;
        int i2 = k1 * datatype.mSize;
        if(!mAutoPadding || mType.getElement().getVectorSize() != 3) goto _L2; else goto _L1
_L1:
        if((l1 / 4) * 3 > i2)
        {
            obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
            throw obj;
        }
          goto _L3
        obj;
        Trace.traceEnd(32768L);
        throw obj;
_L3:
        flag = true;
        k1 = l1;
_L5:
        mRS.nAllocationData3D(getIDSafe(), i, j, k, mSelectedLOD, l, i1, j1, obj, k1, datatype, mType.mElement.mType.mSize, flag);
        Trace.traceEnd(32768L);
        return;
_L2:
        k1 = i2;
        if(l1 <= i2) goto _L5; else goto _L4
_L4:
        obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
        ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
        throw obj;
    }

    private void copy3DRangeToUnchecked(int i, int j, int k, int l, int i1, int j1, Object obj, 
            Element.DataType datatype, int k1)
    {
        int l1;
        Trace.traceBegin(32768L, "copy3DRangeToUnchecked");
        mRS.validate();
        validate3DRange(i, j, k, l, i1, j1);
        l1 = mType.mElement.getBytesSize() * l * i1 * j1;
        boolean flag = false;
        int i2 = k1 * datatype.mSize;
        if(!mAutoPadding || mType.getElement().getVectorSize() != 3) goto _L2; else goto _L1
_L1:
        if((l1 / 4) * 3 > i2)
        {
            obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
            throw obj;
        }
          goto _L3
        obj;
        Trace.traceEnd(32768L);
        throw obj;
_L3:
        flag = true;
        k1 = l1;
_L5:
        mRS.nAllocationRead3D(getIDSafe(), i, j, k, mSelectedLOD, l, i1, j1, obj, k1, datatype, mType.mElement.mType.mSize, flag);
        Trace.traceEnd(32768L);
        return;
_L2:
        k1 = i2;
        if(l1 <= i2) goto _L5; else goto _L4
_L4:
        obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
        ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
        throw obj;
    }

    private void copyFromUnchecked(Object obj, Element.DataType datatype, int i)
    {
        Trace.traceBegin(32768L, "copyFromUnchecked");
        mRS.validate();
        if(mCurrentDimZ <= 0) goto _L2; else goto _L1
_L1:
        copy3DRangeFromUnchecked(0, 0, 0, mCurrentDimX, mCurrentDimY, mCurrentDimZ, obj, datatype, i);
_L3:
        Trace.traceEnd(32768L);
        return;
_L2:
        if(mCurrentDimY <= 0)
            break MISSING_BLOCK_LABEL_88;
        copy2DRangeFromUnchecked(0, 0, mCurrentDimX, mCurrentDimY, obj, datatype, i);
          goto _L3
        obj;
        Trace.traceEnd(32768L);
        throw obj;
        copy1DRangeFromUnchecked(0, mCurrentCount, obj, datatype, i);
          goto _L3
    }

    private void copyTo(Object obj, Element.DataType datatype, int i)
    {
        Trace.traceBegin(32768L, "copyTo");
        mRS.validate();
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(!mAutoPadding)
            break MISSING_BLOCK_LABEL_51;
        flag1 = flag;
        if(mType.getElement().getVectorSize() == 3)
            flag1 = true;
        if(!flag1)
            break MISSING_BLOCK_LABEL_95;
        if(datatype.mSize * i < (mSize / 4) * 3)
        {
            obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Size of output array cannot be smaller than size of allocation.");
            throw obj;
        }
        break MISSING_BLOCK_LABEL_121;
        obj;
        Trace.traceEnd(32768L);
        throw obj;
        if(datatype.mSize * i < mSize)
        {
            obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Size of output array cannot be smaller than size of allocation.");
            throw obj;
        }
        mRS.nAllocationRead(getID(mRS), obj, datatype, mType.mElement.mType.mSize, flag1);
        Trace.traceEnd(32768L);
        return;
    }

    public static Allocation[] createAllocations(RenderScript renderscript, Type type, int i, int j)
    {
        Trace.traceBegin(32768L, "createAllocations");
        renderscript.validate();
        if(type.getID(renderscript) == 0L)
        {
            renderscript = JVM INSTR new #363 <Class RSInvalidStateException>;
            renderscript.RSInvalidStateException("Bad Type");
            throw renderscript;
        }
        break MISSING_BLOCK_LABEL_45;
        renderscript;
        Trace.traceEnd(32768L);
        throw renderscript;
        Allocation aallocation[];
        aallocation = new Allocation[j];
        aallocation[0] = createTyped(renderscript, type, i);
        if((i & 0x20) == 0)
            break MISSING_BLOCK_LABEL_102;
        if(j <= 16)
            break MISSING_BLOCK_LABEL_94;
        aallocation[0].destroy();
        renderscript = JVM INSTR new #156 <Class RSIllegalArgumentException>;
        renderscript.RSIllegalArgumentException("Exceeds the max number of Allocations allowed: 16");
        throw renderscript;
        aallocation[0].setupBufferQueue(j);
        i = 1;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        aallocation[i] = createFromAllocation(renderscript, aallocation[0]);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        Trace.traceEnd(32768L);
        return aallocation;
    }

    public static Allocation createCubemapFromBitmap(RenderScript renderscript, Bitmap bitmap)
    {
        return createCubemapFromBitmap(renderscript, bitmap, MipmapControl.MIPMAP_NONE, 2);
    }

    public static Allocation createCubemapFromBitmap(RenderScript renderscript, Bitmap bitmap, MipmapControl mipmapcontrol, int i)
    {
        renderscript.validate();
        int j = bitmap.getHeight();
        int k = bitmap.getWidth();
        if(k % 6 != 0)
            throw new RSIllegalArgumentException("Cubemap height must be multiple of 6");
        if(k / 6 != j)
            throw new RSIllegalArgumentException("Only square cube map faces supported");
        boolean flag;
        if((j - 1 & j) == 0)
            flag = true;
        else
            flag = false;
        if(!flag)
            throw new RSIllegalArgumentException("Only power of 2 cube faces supported");
        Element element = elementFromBitmap(renderscript, bitmap);
        Object obj = new Type.Builder(renderscript, element);
        ((Type.Builder) (obj)).setX(j);
        ((Type.Builder) (obj)).setY(j);
        ((Type.Builder) (obj)).setFaces(true);
        boolean flag1;
        long l;
        if(mipmapcontrol == MipmapControl.MIPMAP_FULL)
            flag1 = true;
        else
            flag1 = false;
        ((Type.Builder) (obj)).setMipmaps(flag1);
        obj = ((Type.Builder) (obj)).create();
        l = renderscript.nAllocationCubeCreateFromBitmap(((Type) (obj)).getID(renderscript), mipmapcontrol.mID, bitmap, i);
        if(l == 0L)
            throw new RSRuntimeException((new StringBuilder()).append("Load failed for bitmap ").append(bitmap).append(" element ").append(element).toString());
        else
            return new Allocation(l, renderscript, ((Type) (obj)), true, i, mipmapcontrol);
    }

    public static Allocation createCubemapFromCubeFaces(RenderScript renderscript, Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5)
    {
        return createCubemapFromCubeFaces(renderscript, bitmap, bitmap1, bitmap2, bitmap3, bitmap4, bitmap5, MipmapControl.MIPMAP_NONE, 2);
    }

    public static Allocation createCubemapFromCubeFaces(RenderScript renderscript, Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, MipmapControl mipmapcontrol, 
            int i)
    {
        int j;
        for(j = bitmap.getHeight(); bitmap.getWidth() != j || bitmap1.getWidth() != j || bitmap1.getHeight() != j || bitmap2.getWidth() != j || bitmap2.getHeight() != j || bitmap3.getWidth() != j || bitmap3.getHeight() != j || bitmap4.getWidth() != j || bitmap4.getHeight() != j || bitmap5.getWidth() != j || bitmap5.getHeight() != j;)
            throw new RSIllegalArgumentException("Only square cube map faces supported");

        boolean flag;
        if((j - 1 & j) == 0)
            flag = true;
        else
            flag = false;
        if(!flag)
            throw new RSIllegalArgumentException("Only power of 2 cube faces supported");
        Type.Builder builder = new Type.Builder(renderscript, elementFromBitmap(renderscript, bitmap));
        builder.setX(j);
        builder.setY(j);
        builder.setFaces(true);
        boolean flag1;
        if(mipmapcontrol == MipmapControl.MIPMAP_FULL)
            flag1 = true;
        else
            flag1 = false;
        builder.setMipmaps(flag1);
        mipmapcontrol = createTyped(renderscript, builder.create(), mipmapcontrol, i);
        renderscript = AllocationAdapter.create2D(renderscript, mipmapcontrol);
        renderscript.setFace(Type.CubemapFace.POSITIVE_X);
        renderscript.copyFrom(bitmap);
        renderscript.setFace(Type.CubemapFace.NEGATIVE_X);
        renderscript.copyFrom(bitmap1);
        renderscript.setFace(Type.CubemapFace.POSITIVE_Y);
        renderscript.copyFrom(bitmap2);
        renderscript.setFace(Type.CubemapFace.NEGATIVE_Y);
        renderscript.copyFrom(bitmap3);
        renderscript.setFace(Type.CubemapFace.POSITIVE_Z);
        renderscript.copyFrom(bitmap4);
        renderscript.setFace(Type.CubemapFace.NEGATIVE_Z);
        renderscript.copyFrom(bitmap5);
        return mipmapcontrol;
    }

    static Allocation createFromAllocation(RenderScript renderscript, Allocation allocation)
    {
        Trace.traceBegin(32768L, "createFromAllcation");
        renderscript.validate();
        if(allocation.getID(renderscript) == 0L)
        {
            renderscript = JVM INSTR new #363 <Class RSInvalidStateException>;
            renderscript.RSInvalidStateException("Bad input Allocation");
            throw renderscript;
        }
        break MISSING_BLOCK_LABEL_45;
        renderscript;
        Trace.traceEnd(32768L);
        throw renderscript;
        Type type;
        int i;
        MipmapControl mipmapcontrol;
        long l;
        type = allocation.getType();
        i = allocation.getUsage();
        mipmapcontrol = allocation.getMipmap();
        l = renderscript.nAllocationCreateTyped(type.getID(renderscript), mipmapcontrol.mID, i, 0L);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_99;
        renderscript = JVM INSTR new #248 <Class RSRuntimeException>;
        renderscript.RSRuntimeException("Allocation creation failed.");
        throw renderscript;
        Allocation allocation1;
        allocation1 = JVM INSTR new #2   <Class Allocation>;
        allocation1.Allocation(l, renderscript, type, false, i, mipmapcontrol);
        if((i & 0x20) == 0)
            break MISSING_BLOCK_LABEL_130;
        allocation1.shareBufferQueue(allocation);
        Trace.traceEnd(32768L);
        return allocation1;
    }

    public static Allocation createFromBitmap(RenderScript renderscript, Bitmap bitmap)
    {
        if(renderscript.getApplicationContext().getApplicationInfo().targetSdkVersion >= 18)
            return createFromBitmap(renderscript, bitmap, MipmapControl.MIPMAP_NONE, 131);
        else
            return createFromBitmap(renderscript, bitmap, MipmapControl.MIPMAP_NONE, 2);
    }

    public static Allocation createFromBitmap(RenderScript renderscript, Bitmap bitmap, MipmapControl mipmapcontrol, int i)
    {
        Trace.traceBegin(32768L, "createFromBitmap");
        renderscript.validate();
        if(bitmap.getConfig() != null)
            break MISSING_BLOCK_LABEL_104;
        if((i & 0x80) == 0)
            break MISSING_BLOCK_LABEL_50;
        renderscript = JVM INSTR new #156 <Class RSIllegalArgumentException>;
        renderscript.RSIllegalArgumentException("USAGE_SHARED cannot be used with a Bitmap that has a null config.");
        throw renderscript;
        renderscript;
        Trace.traceEnd(32768L);
        throw renderscript;
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = JVM INSTR new #539 <Class Canvas>;
        canvas.Canvas(bitmap1);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
        renderscript = createFromBitmap(renderscript, bitmap1, mipmapcontrol, i);
        Trace.traceEnd(32768L);
        return renderscript;
        Type type = typeFromBitmap(renderscript, bitmap, mipmapcontrol);
        if(mipmapcontrol != MipmapControl.MIPMAP_NONE || !type.getElement().isCompatible(Element.RGBA_8888(renderscript)) || i != 131)
            break MISSING_BLOCK_LABEL_212;
        long l = renderscript.nAllocationCreateBitmapBackedAllocation(type.getID(renderscript), mipmapcontrol.mID, bitmap, i);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_179;
        renderscript = JVM INSTR new #248 <Class RSRuntimeException>;
        renderscript.RSRuntimeException("Load failed.");
        throw renderscript;
        Allocation allocation;
        allocation = JVM INSTR new #2   <Class Allocation>;
        allocation.Allocation(l, renderscript, type, true, i, mipmapcontrol);
        allocation.setBitmap(bitmap);
        Trace.traceEnd(32768L);
        return allocation;
        l = renderscript.nAllocationCreateFromBitmap(type.getID(renderscript), mipmapcontrol.mID, bitmap, i);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_250;
        renderscript = JVM INSTR new #248 <Class RSRuntimeException>;
        renderscript.RSRuntimeException("Load failed.");
        throw renderscript;
        renderscript = new Allocation(l, renderscript, type, true, i, mipmapcontrol);
        Trace.traceEnd(32768L);
        return renderscript;
    }

    public static Allocation createFromBitmapResource(RenderScript renderscript, Resources resources, int i)
    {
        if(renderscript.getApplicationContext().getApplicationInfo().targetSdkVersion >= 18)
            return createFromBitmapResource(renderscript, resources, i, MipmapControl.MIPMAP_NONE, 3);
        else
            return createFromBitmapResource(renderscript, resources, i, MipmapControl.MIPMAP_NONE, 2);
    }

    public static Allocation createFromBitmapResource(RenderScript renderscript, Resources resources, int i, MipmapControl mipmapcontrol, int j)
    {
        renderscript.validate();
        if((j & 0xe0) != 0)
        {
            throw new RSIllegalArgumentException("Unsupported usage specified.");
        } else
        {
            resources = BitmapFactory.decodeResource(resources, i);
            renderscript = createFromBitmap(renderscript, resources, mipmapcontrol, j);
            resources.recycle();
            return renderscript;
        }
    }

    public static Allocation createFromString(RenderScript renderscript, String s, int i)
    {
        renderscript.validate();
        try
        {
            s = s.getBytes("UTF-8");
            renderscript = createSized(renderscript, Element.U8(renderscript), s.length, i);
            renderscript.copyFrom(s);
        }
        // Misplaced declaration of an exception variable
        catch(RenderScript renderscript)
        {
            throw new RSRuntimeException("Could not convert string to utf-8.");
        }
        return renderscript;
    }

    public static Allocation createSized(RenderScript renderscript, Element element, int i)
    {
        return createSized(renderscript, element, i, 1);
    }

    public static Allocation createSized(RenderScript renderscript, Element element, int i, int j)
    {
        long l;
        Trace.traceBegin(32768L, "createSized");
        renderscript.validate();
        Type.Builder builder = JVM INSTR new #410 <Class Type$Builder>;
        builder.Type.Builder(renderscript, element);
        builder.setX(i);
        element = builder.create();
        l = renderscript.nAllocationCreateTyped(element.getID(renderscript), MipmapControl.MIPMAP_NONE.mID, j, 0L);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_86;
        renderscript = JVM INSTR new #248 <Class RSRuntimeException>;
        renderscript.RSRuntimeException("Allocation creation failed.");
        throw renderscript;
        renderscript;
        Trace.traceEnd(32768L);
        throw renderscript;
        renderscript = new Allocation(l, renderscript, element, true, j, MipmapControl.MIPMAP_NONE);
        Trace.traceEnd(32768L);
        return renderscript;
    }

    public static Allocation createTyped(RenderScript renderscript, Type type)
    {
        return createTyped(renderscript, type, MipmapControl.MIPMAP_NONE, 1);
    }

    public static Allocation createTyped(RenderScript renderscript, Type type, int i)
    {
        return createTyped(renderscript, type, MipmapControl.MIPMAP_NONE, i);
    }

    public static Allocation createTyped(RenderScript renderscript, Type type, MipmapControl mipmapcontrol, int i)
    {
        Trace.traceBegin(32768L, "createTyped");
        renderscript.validate();
        if(type.getID(renderscript) == 0L)
        {
            renderscript = JVM INSTR new #363 <Class RSInvalidStateException>;
            renderscript.RSInvalidStateException("Bad Type");
            throw renderscript;
        }
        break MISSING_BLOCK_LABEL_45;
        renderscript;
        Trace.traceEnd(32768L);
        throw renderscript;
        long l = renderscript.nAllocationCreateTyped(type.getID(renderscript), mipmapcontrol.mID, i, 0L);
        if(l != 0L)
            break MISSING_BLOCK_LABEL_82;
        renderscript = JVM INSTR new #248 <Class RSRuntimeException>;
        renderscript.RSRuntimeException("Allocation creation failed.");
        throw renderscript;
        renderscript = new Allocation(l, renderscript, type, false, i, mipmapcontrol);
        Trace.traceEnd(32768L);
        return renderscript;
    }

    private void data1DChecks(int i, int j, int k, int l, boolean flag)
    {
        mRS.validate();
        if(i < 0)
            throw new RSIllegalArgumentException("Offset must be >= 0.");
        if(j < 1)
            throw new RSIllegalArgumentException("Count must be >= 1.");
        if(i + j > mCurrentCount)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Overflow, Available count ").append(mCurrentCount).append(", got ").append(j).append(" at offset ").append(i).append(".").toString());
        if(flag)
        {
            if(k < (l / 4) * 3)
                throw new RSIllegalArgumentException("Array too small for allocation type.");
        } else
        if(k < l)
            throw new RSIllegalArgumentException("Array too small for allocation type.");
    }

    static Element elementFromBitmap(RenderScript renderscript, Bitmap bitmap)
    {
        bitmap = bitmap.getConfig();
        if(bitmap == android.graphics.Bitmap.Config.ALPHA_8)
            return Element.A_8(renderscript);
        if(bitmap == android.graphics.Bitmap.Config.ARGB_4444)
            return Element.RGBA_4444(renderscript);
        if(bitmap == android.graphics.Bitmap.Config.ARGB_8888)
            return Element.RGBA_8888(renderscript);
        if(bitmap == android.graphics.Bitmap.Config.RGB_565)
            return Element.RGB_565(renderscript);
        else
            throw new RSInvalidStateException((new StringBuilder()).append("Bad bitmap type: ").append(bitmap).toString());
    }

    private long getIDSafe()
    {
        if(mAdaptedAllocation != null)
            return mAdaptedAllocation.getID(mRS);
        else
            return getID(mRS);
    }

    static void sendBufferNotification(long l)
    {
        HashMap hashmap = mAllocationMap;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj;
        HashMap hashmap1 = mAllocationMap;
        obj = JVM INSTR new #640 <Class Long>;
        ((Long) (obj)).Long(l);
        obj = (Allocation)hashmap1.get(obj);
        if(obj == null)
            break MISSING_BLOCK_LABEL_57;
        if(((Allocation) (obj)).mBufferNotifier != null)
            ((Allocation) (obj)).mBufferNotifier.onBufferAvailable(((Allocation) (obj)));
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void setBitmap(Bitmap bitmap)
    {
        mBitmap = bitmap;
    }

    static Type typeFromBitmap(RenderScript renderscript, Bitmap bitmap, MipmapControl mipmapcontrol)
    {
        renderscript = new Type.Builder(renderscript, elementFromBitmap(renderscript, bitmap));
        renderscript.setX(bitmap.getWidth());
        renderscript.setY(bitmap.getHeight());
        boolean flag;
        if(mipmapcontrol == MipmapControl.MIPMAP_FULL)
            flag = true;
        else
            flag = false;
        renderscript.setMipmaps(flag);
        return renderscript.create();
    }

    private void updateCacheInfo(Type type)
    {
        mCurrentDimX = type.getX();
        mCurrentDimY = type.getY();
        mCurrentDimZ = type.getZ();
        mCurrentCount = mCurrentDimX;
        if(mCurrentDimY > 1)
            mCurrentCount = mCurrentCount * mCurrentDimY;
        if(mCurrentDimZ > 1)
            mCurrentCount = mCurrentCount * mCurrentDimZ;
    }

    private void validate2DRange(int i, int j, int k, int l)
    {
        if(mAdaptedAllocation == null)
        {
            if(i < 0 || j < 0)
                throw new RSIllegalArgumentException("Offset cannot be negative.");
            if(l < 0 || k < 0)
                throw new RSIllegalArgumentException("Height or width cannot be negative.");
            if(i + k > mCurrentDimX || j + l > mCurrentDimY)
                throw new RSIllegalArgumentException("Updated region larger than allocation.");
        }
    }

    private void validate3DRange(int i, int j, int k, int l, int i1, int j1)
    {
        if(mAdaptedAllocation == null)
        {
            while(i < 0 || j < 0 || k < 0) 
                throw new RSIllegalArgumentException("Offset cannot be negative.");
            while(i1 < 0 || l < 0 || j1 < 0) 
                throw new RSIllegalArgumentException("Height or width cannot be negative.");
            while(i + l > mCurrentDimX || j + i1 > mCurrentDimY || k + j1 > mCurrentDimZ) 
                throw new RSIllegalArgumentException("Updated region larger than allocation.");
        }
    }

    private void validateBitmapFormat(Bitmap bitmap)
    {
        bitmap = bitmap.getConfig();
        if(bitmap == null)
            throw new RSIllegalArgumentException("Bitmap has an unsupported format for this operation");
        _2D_getandroid_2D_graphics_2D_Bitmap$ConfigSwitchesValues()[bitmap.ordinal()];
        JVM INSTR tableswitch 1 4: default 60
    //                   1 61
    //                   2 392
    //                   3 162
    //                   4 277;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        if(mType.getElement().mKind != Element.DataKind.PIXEL_A)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Allocation kind is ").append(mType.getElement().mKind).append(", type ").append(mType.getElement().mType).append(" of ").append(mType.getElement().getBytesSize()).append(" bytes, passed bitmap was ").append(bitmap).toString());
        continue; /* Loop/switch isn't completed */
_L4:
        if(mType.getElement().mKind != Element.DataKind.PIXEL_RGBA || mType.getElement().getBytesSize() != 4)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Allocation kind is ").append(mType.getElement().mKind).append(", type ").append(mType.getElement().mType).append(" of ").append(mType.getElement().getBytesSize()).append(" bytes, passed bitmap was ").append(bitmap).toString());
        continue; /* Loop/switch isn't completed */
_L5:
        if(mType.getElement().mKind != Element.DataKind.PIXEL_RGB || mType.getElement().getBytesSize() != 2)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Allocation kind is ").append(mType.getElement().mKind).append(", type ").append(mType.getElement().mType).append(" of ").append(mType.getElement().getBytesSize()).append(" bytes, passed bitmap was ").append(bitmap).toString());
        continue; /* Loop/switch isn't completed */
_L3:
        if(mType.getElement().mKind != Element.DataKind.PIXEL_RGBA || mType.getElement().getBytesSize() != 2)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Allocation kind is ").append(mType.getElement().mKind).append(", type ").append(mType.getElement().mType).append(" of ").append(mType.getElement().getBytesSize()).append(" bytes, passed bitmap was ").append(bitmap).toString());
        if(true) goto _L1; else goto _L6
_L6:
    }

    private void validateBitmapSize(Bitmap bitmap)
    {
        if(mCurrentDimX != bitmap.getWidth() || mCurrentDimY != bitmap.getHeight())
            throw new RSIllegalArgumentException("Cannot update allocation from bitmap, sizes mismatch");
        else
            return;
    }

    private void validateIsFloat32()
    {
        if(mType.mElement.mType == Element.DataType.FLOAT_32)
            return;
        else
            throw new RSIllegalArgumentException((new StringBuilder()).append("32 bit float source does not match allocation type ").append(mType.mElement.mType).toString());
    }

    private void validateIsFloat64()
    {
        if(mType.mElement.mType == Element.DataType.FLOAT_64)
            return;
        else
            throw new RSIllegalArgumentException((new StringBuilder()).append("64 bit float source does not match allocation type ").append(mType.mElement.mType).toString());
    }

    private void validateIsInt16OrFloat16()
    {
        while(mType.mElement.mType == Element.DataType.SIGNED_16 || mType.mElement.mType == Element.DataType.UNSIGNED_16 || mType.mElement.mType == Element.DataType.FLOAT_16) 
            return;
        throw new RSIllegalArgumentException((new StringBuilder()).append("16 bit integer source does not match allocation type ").append(mType.mElement.mType).toString());
    }

    private void validateIsInt32()
    {
        if(mType.mElement.mType == Element.DataType.SIGNED_32 || mType.mElement.mType == Element.DataType.UNSIGNED_32)
            return;
        else
            throw new RSIllegalArgumentException((new StringBuilder()).append("32 bit integer source does not match allocation type ").append(mType.mElement.mType).toString());
    }

    private void validateIsInt64()
    {
        if(mType.mElement.mType == Element.DataType.SIGNED_64 || mType.mElement.mType == Element.DataType.UNSIGNED_64)
            return;
        else
            throw new RSIllegalArgumentException((new StringBuilder()).append("64 bit integer source does not match allocation type ").append(mType.mElement.mType).toString());
    }

    private void validateIsInt8()
    {
        if(mType.mElement.mType == Element.DataType.SIGNED_8 || mType.mElement.mType == Element.DataType.UNSIGNED_8)
            return;
        else
            throw new RSIllegalArgumentException((new StringBuilder()).append("8 bit integer source does not match allocation type ").append(mType.mElement.mType).toString());
    }

    private void validateIsObject()
    {
        while(mType.mElement.mType == Element.DataType.RS_ELEMENT || mType.mElement.mType == Element.DataType.RS_TYPE || mType.mElement.mType == Element.DataType.RS_ALLOCATION || mType.mElement.mType == Element.DataType.RS_SAMPLER || mType.mElement.mType == Element.DataType.RS_SCRIPT || mType.mElement.mType == Element.DataType.RS_MESH || mType.mElement.mType == Element.DataType.RS_PROGRAM_FRAGMENT || mType.mElement.mType == Element.DataType.RS_PROGRAM_VERTEX || mType.mElement.mType == Element.DataType.RS_PROGRAM_RASTER || mType.mElement.mType == Element.DataType.RS_PROGRAM_STORE) 
            return;
        throw new RSIllegalArgumentException((new StringBuilder()).append("Object source does not match allocation type ").append(mType.mElement.mType).toString());
    }

    private Element.DataType validateObjectIsPrimitiveArray(Object obj, boolean flag)
    {
        obj = obj.getClass();
        if(!((Class) (obj)).isArray())
            throw new RSIllegalArgumentException("Object passed is not an array of primitives.");
        obj = ((Class) (obj)).getComponentType();
        if(!((Class) (obj)).isPrimitive())
            throw new RSIllegalArgumentException("Object passed is not an Array of primitives.");
        if(obj == Long.TYPE)
            if(flag)
            {
                validateIsInt64();
                return mType.mElement.mType;
            } else
            {
                return Element.DataType.SIGNED_64;
            }
        if(obj == Integer.TYPE)
            if(flag)
            {
                validateIsInt32();
                return mType.mElement.mType;
            } else
            {
                return Element.DataType.SIGNED_32;
            }
        if(obj == Short.TYPE)
            if(flag)
            {
                validateIsInt16OrFloat16();
                return mType.mElement.mType;
            } else
            {
                return Element.DataType.SIGNED_16;
            }
        if(obj == Byte.TYPE)
            if(flag)
            {
                validateIsInt8();
                return mType.mElement.mType;
            } else
            {
                return Element.DataType.SIGNED_8;
            }
        if(obj == Float.TYPE)
        {
            if(flag)
                validateIsFloat32();
            return Element.DataType.FLOAT_32;
        }
        if(obj == Double.TYPE)
        {
            if(flag)
                validateIsFloat64();
            return Element.DataType.FLOAT_64;
        } else
        {
            throw new RSIllegalArgumentException((new StringBuilder()).append("Parameter of type ").append(((Class) (obj)).getSimpleName()).append("[] is not compatible with data type ").append(mType.mElement.mType.name()).append(" of allocation").toString());
        }
    }

    public void copy1DRangeFrom(int i, int j, Allocation allocation, int k)
    {
        Trace.traceBegin(32768L, "copy1DRangeFrom");
        mRS.nAllocationData2D(getIDSafe(), i, 0, mSelectedLOD, mSelectedFace.mID, j, 1, allocation.getID(mRS), k, 0, allocation.mSelectedLOD, allocation.mSelectedFace.mID);
        Trace.traceEnd(32768L);
    }

    public void copy1DRangeFrom(int i, int j, Object obj)
    {
        copy1DRangeFromUnchecked(i, j, obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
    }

    public void copy1DRangeFrom(int i, int j, byte abyte0[])
    {
        validateIsInt8();
        copy1DRangeFromUnchecked(i, j, abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copy1DRangeFrom(int i, int j, float af[])
    {
        validateIsFloat32();
        copy1DRangeFromUnchecked(i, j, af, Element.DataType.FLOAT_32, af.length);
    }

    public void copy1DRangeFrom(int i, int j, int ai[])
    {
        validateIsInt32();
        copy1DRangeFromUnchecked(i, j, ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copy1DRangeFrom(int i, int j, short aword0[])
    {
        validateIsInt16OrFloat16();
        copy1DRangeFromUnchecked(i, j, aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copy1DRangeFromUnchecked(int i, int j, Object obj)
    {
        copy1DRangeFromUnchecked(i, j, obj, validateObjectIsPrimitiveArray(obj, false), Array.getLength(obj));
    }

    public void copy1DRangeFromUnchecked(int i, int j, byte abyte0[])
    {
        copy1DRangeFromUnchecked(i, j, abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copy1DRangeFromUnchecked(int i, int j, float af[])
    {
        copy1DRangeFromUnchecked(i, j, af, Element.DataType.FLOAT_32, af.length);
    }

    public void copy1DRangeFromUnchecked(int i, int j, int ai[])
    {
        copy1DRangeFromUnchecked(i, j, ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copy1DRangeFromUnchecked(int i, int j, short aword0[])
    {
        copy1DRangeFromUnchecked(i, j, aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copy1DRangeTo(int i, int j, Object obj)
    {
        copy1DRangeToUnchecked(i, j, obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
    }

    public void copy1DRangeTo(int i, int j, byte abyte0[])
    {
        validateIsInt8();
        copy1DRangeToUnchecked(i, j, abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copy1DRangeTo(int i, int j, float af[])
    {
        validateIsFloat32();
        copy1DRangeToUnchecked(i, j, af, Element.DataType.FLOAT_32, af.length);
    }

    public void copy1DRangeTo(int i, int j, int ai[])
    {
        validateIsInt32();
        copy1DRangeToUnchecked(i, j, ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copy1DRangeTo(int i, int j, short aword0[])
    {
        validateIsInt16OrFloat16();
        copy1DRangeToUnchecked(i, j, aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copy1DRangeToUnchecked(int i, int j, Object obj)
    {
        copy1DRangeToUnchecked(i, j, obj, validateObjectIsPrimitiveArray(obj, false), Array.getLength(obj));
    }

    public void copy1DRangeToUnchecked(int i, int j, byte abyte0[])
    {
        copy1DRangeToUnchecked(i, j, abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copy1DRangeToUnchecked(int i, int j, float af[])
    {
        copy1DRangeToUnchecked(i, j, af, Element.DataType.FLOAT_32, af.length);
    }

    public void copy1DRangeToUnchecked(int i, int j, int ai[])
    {
        copy1DRangeToUnchecked(i, j, ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copy1DRangeToUnchecked(int i, int j, short aword0[])
    {
        copy1DRangeToUnchecked(i, j, aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, Allocation allocation, int i1, int j1)
    {
        Trace.traceBegin(32768L, "copy2DRangeFrom");
        mRS.validate();
        validate2DRange(i, j, k, l);
        mRS.nAllocationData2D(getIDSafe(), i, j, mSelectedLOD, mSelectedFace.mID, k, l, allocation.getID(mRS), i1, j1, allocation.mSelectedLOD, allocation.mSelectedFace.mID);
        Trace.traceEnd(32768L);
        return;
        allocation;
        Trace.traceEnd(32768L);
        throw allocation;
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, Object obj)
    {
        Trace.traceBegin(32768L, "copy2DRangeFrom");
        copy2DRangeFromUnchecked(i, j, k, l, obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
        Trace.traceEnd(32768L);
        return;
        obj;
        Trace.traceEnd(32768L);
        throw obj;
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, byte abyte0[])
    {
        validateIsInt8();
        copy2DRangeFromUnchecked(i, j, k, l, abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, float af[])
    {
        validateIsFloat32();
        copy2DRangeFromUnchecked(i, j, k, l, af, Element.DataType.FLOAT_32, af.length);
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, int ai[])
    {
        validateIsInt32();
        copy2DRangeFromUnchecked(i, j, k, l, ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copy2DRangeFrom(int i, int j, int k, int l, short aword0[])
    {
        validateIsInt16OrFloat16();
        copy2DRangeFromUnchecked(i, j, k, l, aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copy2DRangeFrom(int i, int j, Bitmap bitmap)
    {
        Trace.traceBegin(32768L, "copy2DRangeFrom");
        mRS.validate();
        if(bitmap.getConfig() != null)
            break MISSING_BLOCK_LABEL_75;
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = JVM INSTR new #539 <Class Canvas>;
        canvas.Canvas(bitmap1);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
        copy2DRangeFrom(i, j, bitmap1);
        Trace.traceEnd(32768L);
        return;
        validateBitmapFormat(bitmap);
        validate2DRange(i, j, bitmap.getWidth(), bitmap.getHeight());
        mRS.nAllocationData2D(getIDSafe(), i, j, mSelectedLOD, mSelectedFace.mID, bitmap);
        Trace.traceEnd(32768L);
        return;
        bitmap;
        Trace.traceEnd(32768L);
        throw bitmap;
    }

    void copy2DRangeFromUnchecked(int i, int j, int k, int l, Object obj, Element.DataType datatype, int i1)
    {
        int j1;
        Trace.traceBegin(32768L, "copy2DRangeFromUnchecked");
        mRS.validate();
        validate2DRange(i, j, k, l);
        j1 = mType.mElement.getBytesSize() * k * l;
        boolean flag = false;
        int k1 = i1 * datatype.mSize;
        if(!mAutoPadding || mType.getElement().getVectorSize() != 3) goto _L2; else goto _L1
_L1:
        if((j1 / 4) * 3 > k1)
        {
            obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
            throw obj;
        }
          goto _L3
        obj;
        Trace.traceEnd(32768L);
        throw obj;
_L3:
        flag = true;
        i1 = j1;
_L5:
        mRS.nAllocationData2D(getIDSafe(), i, j, mSelectedLOD, mSelectedFace.mID, k, l, obj, i1, datatype, mType.mElement.mType.mSize, flag);
        Trace.traceEnd(32768L);
        return;
_L2:
        i1 = k1;
        if(j1 <= k1) goto _L5; else goto _L4
_L4:
        obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
        ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
        throw obj;
    }

    public void copy2DRangeTo(int i, int j, int k, int l, Object obj)
    {
        copy2DRangeToUnchecked(i, j, k, l, obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
    }

    public void copy2DRangeTo(int i, int j, int k, int l, byte abyte0[])
    {
        validateIsInt8();
        copy2DRangeToUnchecked(i, j, k, l, abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copy2DRangeTo(int i, int j, int k, int l, float af[])
    {
        validateIsFloat32();
        copy2DRangeToUnchecked(i, j, k, l, af, Element.DataType.FLOAT_32, af.length);
    }

    public void copy2DRangeTo(int i, int j, int k, int l, int ai[])
    {
        validateIsInt32();
        copy2DRangeToUnchecked(i, j, k, l, ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copy2DRangeTo(int i, int j, int k, int l, short aword0[])
    {
        validateIsInt16OrFloat16();
        copy2DRangeToUnchecked(i, j, k, l, aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    void copy2DRangeToUnchecked(int i, int j, int k, int l, Object obj, Element.DataType datatype, int i1)
    {
        int j1;
        Trace.traceBegin(32768L, "copy2DRangeToUnchecked");
        mRS.validate();
        validate2DRange(i, j, k, l);
        j1 = mType.mElement.getBytesSize() * k * l;
        boolean flag = false;
        int k1 = i1 * datatype.mSize;
        if(!mAutoPadding || mType.getElement().getVectorSize() != 3) goto _L2; else goto _L1
_L1:
        if((j1 / 4) * 3 > k1)
        {
            obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
            throw obj;
        }
          goto _L3
        obj;
        Trace.traceEnd(32768L);
        throw obj;
_L3:
        flag = true;
        i1 = j1;
_L5:
        mRS.nAllocationRead2D(getIDSafe(), i, j, mSelectedLOD, mSelectedFace.mID, k, l, obj, i1, datatype, mType.mElement.mType.mSize, flag);
        Trace.traceEnd(32768L);
        return;
_L2:
        i1 = k1;
        if(j1 <= k1) goto _L5; else goto _L4
_L4:
        obj = JVM INSTR new #156 <Class RSIllegalArgumentException>;
        ((RSIllegalArgumentException) (obj)).RSIllegalArgumentException("Array too small for allocation type.");
        throw obj;
    }

    public void copy3DRangeFrom(int i, int j, int k, int l, int i1, int j1, Allocation allocation, 
            int k1, int l1, int i2)
    {
        mRS.validate();
        validate3DRange(i, j, k, l, i1, j1);
        mRS.nAllocationData3D(getIDSafe(), i, j, k, mSelectedLOD, l, i1, j1, allocation.getID(mRS), k1, l1, i2, allocation.mSelectedLOD);
    }

    public void copy3DRangeFrom(int i, int j, int k, int l, int i1, int j1, Object obj)
    {
        Trace.traceBegin(32768L, "copy3DRangeFrom");
        copy3DRangeFromUnchecked(i, j, k, l, i1, j1, obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
        Trace.traceEnd(32768L);
        return;
        obj;
        Trace.traceEnd(32768L);
        throw obj;
    }

    public void copy3DRangeTo(int i, int j, int k, int l, int i1, int j1, Object obj)
    {
        copy3DRangeToUnchecked(i, j, k, l, i1, j1, obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
    }

    public void copyFrom(Bitmap bitmap)
    {
        Trace.traceBegin(32768L, "copyFrom");
        mRS.validate();
        if(bitmap.getConfig() != null)
            break MISSING_BLOCK_LABEL_67;
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = JVM INSTR new #539 <Class Canvas>;
        canvas.Canvas(bitmap1);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
        copyFrom(bitmap1);
        Trace.traceEnd(32768L);
        return;
        validateBitmapSize(bitmap);
        validateBitmapFormat(bitmap);
        mRS.nAllocationCopyFromBitmap(getID(mRS), bitmap);
        Trace.traceEnd(32768L);
        return;
        bitmap;
        Trace.traceEnd(32768L);
        throw bitmap;
    }

    public void copyFrom(Allocation allocation)
    {
        Trace.traceBegin(32768L, "copyFrom");
        mRS.validate();
        if(!mType.equals(allocation.getType()))
        {
            allocation = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            allocation.RSIllegalArgumentException("Types of allocations must match.");
            throw allocation;
        }
        break MISSING_BLOCK_LABEL_52;
        allocation;
        Trace.traceEnd(32768L);
        throw allocation;
        copy2DRangeFrom(0, 0, mCurrentDimX, mCurrentDimY, allocation, 0, 0);
        Trace.traceEnd(32768L);
        return;
    }

    public void copyFrom(Object obj)
    {
        Trace.traceBegin(32768L, "copyFrom");
        copyFromUnchecked(obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
        Trace.traceEnd(32768L);
        return;
        obj;
        Trace.traceEnd(32768L);
        throw obj;
    }

    public void copyFrom(byte abyte0[])
    {
        validateIsInt8();
        copyFromUnchecked(abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copyFrom(float af[])
    {
        validateIsFloat32();
        copyFromUnchecked(af, Element.DataType.FLOAT_32, af.length);
    }

    public void copyFrom(int ai[])
    {
        validateIsInt32();
        copyFromUnchecked(ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copyFrom(BaseObj abaseobj[])
    {
        Trace.traceBegin(32768L, "copyFrom");
        mRS.validate();
        validateIsObject();
        if(abaseobj.length != mCurrentCount)
        {
            RSIllegalArgumentException rsillegalargumentexception = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #226 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            rsillegalargumentexception.RSIllegalArgumentException(stringbuilder.append("Array size mismatch, allocation sizeX = ").append(mCurrentCount).append(", array length = ").append(abaseobj.length).toString());
            throw rsillegalargumentexception;
        }
        break MISSING_BLOCK_LABEL_84;
        abaseobj;
        Trace.traceEnd(32768L);
        throw abaseobj;
        if(RenderScript.sPointerSize != 8) goto _L2; else goto _L1
_L1:
        long al[] = new long[abaseobj.length * 4];
        int i = 0;
_L4:
        if(i >= abaseobj.length)
            break; /* Loop/switch isn't completed */
        al[i * 4] = abaseobj[i].getID(mRS);
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        copy1DRangeFromUnchecked(0, mCurrentCount, al);
_L7:
        Trace.traceEnd(32768L);
        return;
_L2:
        al = new int[abaseobj.length];
        i = 0;
_L6:
        if(i >= abaseobj.length)
            break; /* Loop/switch isn't completed */
        al[i] = (int)abaseobj[i].getID(mRS);
        i++;
        if(true) goto _L6; else goto _L5
_L5:
        copy1DRangeFromUnchecked(0, mCurrentCount, al);
          goto _L7
    }

    public void copyFrom(short aword0[])
    {
        validateIsInt16OrFloat16();
        copyFromUnchecked(aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copyFromUnchecked(Object obj)
    {
        Trace.traceBegin(32768L, "copyFromUnchecked");
        copyFromUnchecked(obj, validateObjectIsPrimitiveArray(obj, false), Array.getLength(obj));
        Trace.traceEnd(32768L);
        return;
        obj;
        Trace.traceEnd(32768L);
        throw obj;
    }

    public void copyFromUnchecked(byte abyte0[])
    {
        copyFromUnchecked(abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copyFromUnchecked(float af[])
    {
        copyFromUnchecked(af, Element.DataType.FLOAT_32, af.length);
    }

    public void copyFromUnchecked(int ai[])
    {
        copyFromUnchecked(ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copyFromUnchecked(short aword0[])
    {
        copyFromUnchecked(aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copyTo(Bitmap bitmap)
    {
        Trace.traceBegin(32768L, "copyTo");
        mRS.validate();
        validateBitmapFormat(bitmap);
        validateBitmapSize(bitmap);
        mRS.nAllocationCopyToBitmap(getID(mRS), bitmap);
        Trace.traceEnd(32768L);
        return;
        bitmap;
        Trace.traceEnd(32768L);
        throw bitmap;
    }

    public void copyTo(Object obj)
    {
        copyTo(obj, validateObjectIsPrimitiveArray(obj, true), Array.getLength(obj));
    }

    public void copyTo(byte abyte0[])
    {
        validateIsInt8();
        copyTo(abyte0, Element.DataType.SIGNED_8, abyte0.length);
    }

    public void copyTo(float af[])
    {
        validateIsFloat32();
        copyTo(af, Element.DataType.FLOAT_32, af.length);
    }

    public void copyTo(int ai[])
    {
        validateIsInt32();
        copyTo(ai, Element.DataType.SIGNED_32, ai.length);
    }

    public void copyTo(short aword0[])
    {
        validateIsInt16OrFloat16();
        copyTo(aword0, Element.DataType.SIGNED_16, aword0.length);
    }

    public void copyToFieldPacker(int i, int j, int k, int l, FieldPacker fieldpacker)
    {
        mRS.validate();
        if(l >= mType.mElement.mElements.length)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Component_number ").append(l).append(" out of range.").toString());
        if(i < 0)
            throw new RSIllegalArgumentException("Offset x must be >= 0.");
        if(j < 0)
            throw new RSIllegalArgumentException("Offset y must be >= 0.");
        if(k < 0)
            throw new RSIllegalArgumentException("Offset z must be >= 0.");
        fieldpacker = fieldpacker.getData();
        int i1 = fieldpacker.length;
        int j1 = mType.mElement.mElements[l].getBytesSize() * mType.mElement.mArraySizes[l];
        if(i1 != j1)
        {
            throw new RSIllegalArgumentException((new StringBuilder()).append("Field packer sizelength ").append(i1).append(" does not match component size ").append(j1).append(".").toString());
        } else
        {
            mRS.nAllocationElementRead(getIDSafe(), i, j, k, mSelectedLOD, l, fieldpacker, i1);
            return;
        }
    }

    public void destroy()
    {
        if((mUsage & 0x40) != 0)
            setSurface(null);
        if(mType != null && mOwningType)
            mType.destroy();
        super.destroy();
    }

    protected void finalize()
        throws Throwable
    {
        RenderScript.registerNativeFree.invoke(RenderScript.sRuntime, new Object[] {
            Integer.valueOf(mSize)
        });
        super.finalize();
    }

    public void generateMipmaps()
    {
        mRS.nAllocationGenerateMipmaps(getID(mRS));
    }

    public ByteBuffer getByteBuffer()
    {
        if(mType.hasFaces())
            throw new RSInvalidStateException("Cubemap is not supported for getByteBuffer().");
        while(mType.getYuv() == 17 || mType.getYuv() == 0x32315659 || mType.getYuv() == 35) 
            throw new RSInvalidStateException("YUV format is not supported for getByteBuffer().");
        if(mByteBuffer == null || (mUsage & 0x20) != 0)
        {
            int i = mType.getX();
            int j = mType.getElement().getBytesSize();
            long al[] = new long[1];
            mByteBuffer = mRS.nAllocationGetByteBuffer(getID(mRS), al, i * j, mType.getY(), mType.getZ());
            mByteBufferStride = al[0];
        }
        if((mUsage & 0x20) != 0)
            return mByteBuffer.asReadOnlyBuffer();
        else
            return mByteBuffer;
    }

    public int getBytesSize()
    {
        if(mType.mDimYuv != 0)
            return (int)Math.ceil((double)(mType.getCount() * mType.getElement().getBytesSize()) * 1.5D);
        else
            return mType.getCount() * mType.getElement().getBytesSize();
    }

    public Element getElement()
    {
        return mType.getElement();
    }

    public MipmapControl getMipmap()
    {
        return mMipmapControl;
    }

    public long getStride()
    {
        if(mByteBufferStride == -1L)
            getByteBuffer();
        return mByteBufferStride;
    }

    public Surface getSurface()
    {
        if((mUsage & 0x20) == 0)
            throw new RSInvalidStateException("Allocation is not a surface texture.");
        if(mGetSurfaceSurface == null)
            mGetSurfaceSurface = mRS.nAllocationGetSurface(getID(mRS));
        return mGetSurfaceSurface;
    }

    public long getTimeStamp()
    {
        return mTimeStamp;
    }

    public Type getType()
    {
        return mType;
    }

    public int getUsage()
    {
        return mUsage;
    }

    public void ioReceive()
    {
        Trace.traceBegin(32768L, "ioReceive");
        if((mUsage & 0x20) == 0)
        {
            RSIllegalArgumentException rsillegalargumentexception = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            rsillegalargumentexception.RSIllegalArgumentException("Can only receive if IO_INPUT usage specified.");
            throw rsillegalargumentexception;
        }
        break MISSING_BLOCK_LABEL_41;
        Exception exception;
        exception;
        Trace.traceEnd(32768L);
        throw exception;
        mRS.validate();
        mTimeStamp = mRS.nAllocationIoReceive(getID(mRS));
        Trace.traceEnd(32768L);
        return;
    }

    public void ioSend()
    {
        Trace.traceBegin(32768L, "ioSend");
        if((mUsage & 0x40) == 0)
        {
            RSIllegalArgumentException rsillegalargumentexception = JVM INSTR new #156 <Class RSIllegalArgumentException>;
            rsillegalargumentexception.RSIllegalArgumentException("Can only send buffer if IO_OUTPUT usage specified.");
            throw rsillegalargumentexception;
        }
        break MISSING_BLOCK_LABEL_41;
        Exception exception;
        exception;
        Trace.traceEnd(32768L);
        throw exception;
        mRS.validate();
        mRS.nAllocationIoSend(getID(mRS));
        Trace.traceEnd(32768L);
        return;
    }

    public void resize(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(mRS.getApplicationContext().getApplicationInfo().targetSdkVersion >= 21)
        {
            RSRuntimeException rsruntimeexception = JVM INSTR new #248 <Class RSRuntimeException>;
            rsruntimeexception.RSRuntimeException("Resize is not allowed in API 21+.");
            throw rsruntimeexception;
        }
        break MISSING_BLOCK_LABEL_38;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        while(mType.getY() > 0 || mType.getZ() > 0 || mType.hasFaces() || mType.hasMipmaps()) 
        {
            RSInvalidStateException rsinvalidstateexception = JVM INSTR new #363 <Class RSInvalidStateException>;
            rsinvalidstateexception.RSInvalidStateException("Resize only support for 1D allocations at this time.");
            throw rsinvalidstateexception;
        }
        mRS.nAllocationResize1D(getID(mRS), i);
        mRS.finish();
        long l = mRS.nAllocationGetType(getID(mRS));
        mType.setID(0L);
        Type type = JVM INSTR new #169 <Class Type>;
        type.Type(l, mRS);
        mType = type;
        mType.updateFromNative();
        updateCacheInfo(mType);
        this;
        JVM INSTR monitorexit ;
    }

    public void setAutoPadding(boolean flag)
    {
        mAutoPadding = flag;
    }

    public void setFromFieldPacker(int i, int j, int k, int l, FieldPacker fieldpacker)
    {
        mRS.validate();
        if(l >= mType.mElement.mElements.length)
            throw new RSIllegalArgumentException((new StringBuilder()).append("Component_number ").append(l).append(" out of range.").toString());
        if(i < 0)
            throw new RSIllegalArgumentException("Offset x must be >= 0.");
        if(j < 0)
            throw new RSIllegalArgumentException("Offset y must be >= 0.");
        if(k < 0)
            throw new RSIllegalArgumentException("Offset z must be >= 0.");
        byte abyte0[] = fieldpacker.getData();
        int i1 = fieldpacker.getPos();
        int j1 = mType.mElement.mElements[l].getBytesSize() * mType.mElement.mArraySizes[l];
        if(i1 != j1)
        {
            throw new RSIllegalArgumentException((new StringBuilder()).append("Field packer sizelength ").append(i1).append(" does not match component size ").append(j1).append(".").toString());
        } else
        {
            mRS.nAllocationElementData(getIDSafe(), i, j, k, mSelectedLOD, l, abyte0, i1);
            return;
        }
    }

    public void setFromFieldPacker(int i, int j, FieldPacker fieldpacker)
    {
        setFromFieldPacker(i, 0, 0, j, fieldpacker);
    }

    public void setFromFieldPacker(int i, FieldPacker fieldpacker)
    {
        mRS.validate();
        int j = mType.mElement.getBytesSize();
        byte abyte0[] = fieldpacker.getData();
        int k = fieldpacker.getPos();
        int l = k / j;
        if(j * l != k)
        {
            throw new RSIllegalArgumentException((new StringBuilder()).append("Field packer length ").append(k).append(" not divisible by element size ").append(j).append(".").toString());
        } else
        {
            copy1DRangeFromUnchecked(i, l, abyte0);
            return;
        }
    }

    public void setOnBufferAvailableListener(OnBufferAvailableListener onbufferavailablelistener)
    {
        HashMap hashmap = mAllocationMap;
        hashmap;
        JVM INSTR monitorenter ;
        HashMap hashmap1 = mAllocationMap;
        Long long1 = JVM INSTR new #640 <Class Long>;
        long1.Long(getID(mRS));
        hashmap1.put(long1, this);
        mBufferNotifier = onbufferavailablelistener;
        hashmap;
        JVM INSTR monitorexit ;
        return;
        onbufferavailablelistener;
        throw onbufferavailablelistener;
    }

    public void setSurface(Surface surface)
    {
        mRS.validate();
        if((mUsage & 0x40) == 0)
        {
            throw new RSInvalidStateException("Allocation is not USAGE_IO_OUTPUT.");
        } else
        {
            mRS.nAllocationSetSurface(getID(mRS), surface);
            return;
        }
    }

    void setupBufferQueue(int i)
    {
        mRS.validate();
        if((mUsage & 0x20) == 0)
        {
            throw new RSInvalidStateException("Allocation is not USAGE_IO_INPUT.");
        } else
        {
            mRS.nAllocationSetupBufferQueue(getID(mRS), i);
            return;
        }
    }

    void shareBufferQueue(Allocation allocation)
    {
        mRS.validate();
        if((mUsage & 0x20) == 0)
        {
            throw new RSInvalidStateException("Allocation is not USAGE_IO_INPUT.");
        } else
        {
            mGetSurfaceSurface = allocation.getSurface();
            mRS.nAllocationShareBufferQueue(getID(mRS), allocation.getID(mRS));
            return;
        }
    }

    public void syncAll(int i)
    {
        Trace.traceBegin(32768L, "syncAll");
        i;
        JVM INSTR lookupswitch 5: default 60
    //                   1: 82
    //                   2: 82
    //                   4: 101
    //                   8: 101
    //                   128: 127;
           goto _L1 _L2 _L2 _L3 _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_101;
_L1:
        RSIllegalArgumentException rsillegalargumentexception = JVM INSTR new #156 <Class RSIllegalArgumentException>;
        rsillegalargumentexception.RSIllegalArgumentException("Source must be exactly one usage type.");
        throw rsillegalargumentexception;
        Exception exception;
        exception;
        Trace.traceEnd(32768L);
        throw exception;
_L2:
        if((mUsage & 0x80) != 0)
            copyFrom(mBitmap);
_L6:
        mRS.validate();
        mRS.nAllocationSyncAll(getIDSafe(), i);
        Trace.traceEnd(32768L);
        return;
_L4:
        if((mUsage & 0x80) == 0) goto _L6; else goto _L5
_L5:
        copyTo(mBitmap);
          goto _L6
    }

    void updateFromNative()
    {
        super.updateFromNative();
        long l = mRS.nAllocationGetType(getID(mRS));
        if(l != 0L)
        {
            mType = new Type(l, mRS);
            mType.updateFromNative();
            updateCacheInfo(mType);
        }
    }

    private static final int _2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues[];
    private static final int MAX_NUMBER_IO_INPUT_ALLOC = 16;
    public static final int USAGE_GRAPHICS_CONSTANTS = 8;
    public static final int USAGE_GRAPHICS_RENDER_TARGET = 16;
    public static final int USAGE_GRAPHICS_TEXTURE = 2;
    public static final int USAGE_GRAPHICS_VERTEX = 4;
    public static final int USAGE_IO_INPUT = 32;
    public static final int USAGE_IO_OUTPUT = 64;
    public static final int USAGE_SCRIPT = 1;
    public static final int USAGE_SHARED = 128;
    static HashMap mAllocationMap = new HashMap();
    static android.graphics.BitmapFactory.Options mBitmapOptions;
    Allocation mAdaptedAllocation;
    boolean mAutoPadding;
    Bitmap mBitmap;
    OnBufferAvailableListener mBufferNotifier;
    private ByteBuffer mByteBuffer;
    private long mByteBufferStride;
    int mCurrentCount;
    int mCurrentDimX;
    int mCurrentDimY;
    int mCurrentDimZ;
    private Surface mGetSurfaceSurface;
    MipmapControl mMipmapControl;
    boolean mOwningType;
    boolean mReadAllowed;
    int mSelectedArray[];
    Type.CubemapFace mSelectedFace;
    int mSelectedLOD;
    int mSelectedX;
    int mSelectedY;
    int mSelectedZ;
    int mSize;
    long mTimeStamp;
    Type mType;
    int mUsage;
    boolean mWriteAllowed;

    static 
    {
        mBitmapOptions = new android.graphics.BitmapFactory.Options();
        mBitmapOptions.inScaled = false;
    }
}
