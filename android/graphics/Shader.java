// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.graphics:
//            Matrix

public class Shader
{
    private static class NoImagePreloadHolder
    {

        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(android/graphics/Shader.getClassLoader(), Shader._2D_wrap0(), 50L);


        private NoImagePreloadHolder()
        {
        }
    }

    public static final class TileMode extends Enum
    {

        public static TileMode valueOf(String s)
        {
            return (TileMode)Enum.valueOf(android/graphics/Shader$TileMode, s);
        }

        public static TileMode[] values()
        {
            return $VALUES;
        }

        private static final TileMode $VALUES[];
        public static final TileMode CLAMP;
        public static final TileMode MIRROR;
        public static final TileMode REPEAT;
        final int nativeInt;

        static 
        {
            CLAMP = new TileMode("CLAMP", 0, 0);
            REPEAT = new TileMode("REPEAT", 1, 1);
            MIRROR = new TileMode("MIRROR", 2, 2);
            $VALUES = (new TileMode[] {
                CLAMP, REPEAT, MIRROR
            });
        }

        private TileMode(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    static long _2D_wrap0()
    {
        return nativeGetFinalizer();
    }

    public Shader()
    {
    }

    private static native long nativeGetFinalizer();

    protected Shader copy()
    {
        Shader shader = new Shader();
        copyLocalMatrix(shader);
        return shader;
    }

    protected void copyLocalMatrix(Shader shader)
    {
        shader.mLocalMatrix.set(mLocalMatrix);
    }

    long createNativeInstance(long l)
    {
        return 0L;
    }

    protected final void discardNativeInstance()
    {
        if(mNativeInstance != 0L)
        {
            mCleaner.run();
            mCleaner = null;
            mNativeInstance = 0L;
        }
    }

    public boolean getLocalMatrix(Matrix matrix)
    {
        if(mLocalMatrix != null)
        {
            matrix.set(mLocalMatrix);
            return true;
        } else
        {
            return false;
        }
    }

    public final long getNativeInstance()
    {
        verifyNativeInstance();
        if(mNativeInstance == 0L)
        {
            long l;
            if(mLocalMatrix == null)
                l = 0L;
            else
                l = mLocalMatrix.native_instance;
            mNativeInstance = createNativeInstance(l);
            if(mNativeInstance != 0L)
                mCleaner = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, mNativeInstance);
        }
        return mNativeInstance;
    }

    public void setLocalMatrix(Matrix matrix)
    {
        if(matrix != null && !matrix.isIdentity()) goto _L2; else goto _L1
_L1:
        if(mLocalMatrix != null)
        {
            mLocalMatrix = null;
            discardNativeInstance();
        }
_L4:
        return;
_L2:
        if(mLocalMatrix == null)
        {
            mLocalMatrix = new Matrix(matrix);
            discardNativeInstance();
        } else
        if(!mLocalMatrix.equals(matrix))
        {
            mLocalMatrix.set(matrix);
            discardNativeInstance();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void verifyNativeInstance()
    {
    }

    private Runnable mCleaner;
    private Matrix mLocalMatrix;
    private long mNativeInstance;
}
