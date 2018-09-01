// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.Parcel;
import android.os.Parcelable;
import dalvik.system.CloseGuard;
import libcore.util.NativeAllocationRegistry;

public final class HardwareBuffer
    implements Parcelable, AutoCloseable
{

    static long _2D_wrap0(Parcel parcel)
    {
        return nReadHardwareBufferFromParcel(parcel);
    }

    private HardwareBuffer(long l)
    {
        mCloseGuard = CloseGuard.get();
        mNativeObject = l;
        mCleaner = (new NativeAllocationRegistry(android/hardware/HardwareBuffer.getClassLoader(), nGetNativeFinalizer(), 232L)).registerNativeAllocation(this, mNativeObject);
        mCloseGuard.open("close");
    }

    HardwareBuffer(long l, HardwareBuffer hardwarebuffer)
    {
        this(l);
    }

    public static HardwareBuffer create(int i, int j, int k, int l, long l1)
    {
        if(!isSupportedFormat(k))
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid pixel format ").append(k).toString());
        if(i <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid width ").append(i).toString());
        if(j <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid height ").append(j).toString());
        if(l <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid layer count ").append(l).toString());
        if(k == 33 && j != 1)
            throw new IllegalArgumentException("Height must be 1 when using the BLOB format");
        l1 = nCreateHardwareBuffer(i, j, k, l, l1);
        if(l1 == 0L)
            throw new IllegalArgumentException("Unable to create a HardwareBuffer, either the dimensions passed were too large, too many image layers were requested, or an invalid set of usage flags was passed");
        else
            return new HardwareBuffer(l1);
    }

    private static boolean isSupportedFormat(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 22: // '\026'
        case 33: // '!'
        case 43: // '+'
            return true;
        }
    }

    private static native long nCreateHardwareBuffer(int i, int j, int k, int l, long l1);

    private static native int nGetFormat(long l);

    private static native int nGetHeight(long l);

    private static native int nGetLayers(long l);

    private static native long nGetNativeFinalizer();

    private static native long nGetUsage(long l);

    private static native int nGetWidth(long l);

    private static native long nReadHardwareBufferFromParcel(Parcel parcel);

    private static native void nWriteHardwareBufferToParcel(long l, Parcel parcel);

    public void close()
    {
        if(!isClosed())
        {
            mCloseGuard.close();
            mNativeObject = 0L;
            mCleaner.run();
            mCleaner = null;
        }
    }

    public int describeContents()
    {
        return 1;
    }

    public void destroy()
    {
        close();
    }

    protected void finalize()
        throws Throwable
    {
        mCloseGuard.warnIfOpen();
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getFormat()
    {
        if(isClosed())
            throw new IllegalStateException("This HardwareBuffer has been closed and its format cannot be obtained.");
        else
            return nGetFormat(mNativeObject);
    }

    public int getHeight()
    {
        if(isClosed())
            throw new IllegalStateException("This HardwareBuffer has been closed and its height cannot be obtained.");
        else
            return nGetHeight(mNativeObject);
    }

    public int getLayers()
    {
        if(isClosed())
            throw new IllegalStateException("This HardwareBuffer has been closed and its layer count cannot be obtained.");
        else
            return nGetLayers(mNativeObject);
    }

    public long getUsage()
    {
        if(isClosed())
            throw new IllegalStateException("This HardwareBuffer has been closed and its usage cannot be obtained.");
        else
            return nGetUsage(mNativeObject);
    }

    public int getWidth()
    {
        if(isClosed())
            throw new IllegalStateException("This HardwareBuffer has been closed and its width cannot be obtained.");
        else
            return nGetWidth(mNativeObject);
    }

    public boolean isClosed()
    {
        boolean flag;
        if(mNativeObject == 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDestroyed()
    {
        return isClosed();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(isClosed())
        {
            throw new IllegalStateException("This HardwareBuffer has been closed and cannot be written to a parcel.");
        } else
        {
            nWriteHardwareBufferToParcel(mNativeObject, parcel);
            return;
        }
    }

    public static final int BLOB = 33;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public HardwareBuffer createFromParcel(Parcel parcel)
        {
            long l = HardwareBuffer._2D_wrap0(parcel);
            if(l != 0L)
                return new HardwareBuffer(l, null);
            else
                return null;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public HardwareBuffer[] newArray(int i)
        {
            return new HardwareBuffer[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final long NATIVE_HARDWARE_BUFFER_SIZE = 232L;
    public static final int RGBA_1010102 = 43;
    public static final int RGBA_8888 = 1;
    public static final int RGBA_FP16 = 22;
    public static final int RGBX_8888 = 2;
    public static final int RGB_565 = 4;
    public static final int RGB_888 = 3;
    public static final long USAGE_CPU_READ_OFTEN = 3L;
    public static final long USAGE_CPU_READ_RARELY = 2L;
    public static final long USAGE_CPU_WRITE_OFTEN = 48L;
    public static final long USAGE_CPU_WRITE_RARELY = 32L;
    public static final long USAGE_GPU_COLOR_OUTPUT = 512L;
    public static final long USAGE_GPU_DATA_BUFFER = 0x1000000L;
    public static final long USAGE_GPU_SAMPLED_IMAGE = 256L;
    public static final long USAGE_PROTECTED_CONTENT = 16384L;
    public static final long USAGE_SENSOR_DIRECT_DATA = 0x800000L;
    public static final long USAGE_VIDEO_ENCODE = 0x10000L;
    private Runnable mCleaner;
    private final CloseGuard mCloseGuard;
    private long mNativeObject;

}
