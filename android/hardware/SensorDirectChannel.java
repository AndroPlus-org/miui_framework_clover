// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.MemoryFile;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.hardware:
//            SensorManager, Sensor

public final class SensorDirectChannel
    implements Channel
{

    SensorDirectChannel(SensorManager sensormanager, int i, int j, long l)
    {
        mManager = sensormanager;
        mNativeHandle = i;
        mType = j;
        mSize = l;
        mCloseGuard.open("SensorDirectChannel");
    }

    static long[] encodeData(MemoryFile memoryfile)
    {
        int i;
        try
        {
            i = memoryfile.getFileDescriptor().getInt$();
        }
        // Misplaced declaration of an exception variable
        catch(MemoryFile memoryfile)
        {
            i = -1;
        }
        return (new long[] {
            1L, 0L, (long)i
        });
    }

    public void close()
    {
        if(mClosed.compareAndSet(false, true))
        {
            mCloseGuard.close();
            mManager.destroyDirectChannel(this);
        }
    }

    public int configure(Sensor sensor, int i)
    {
        return mManager.configureDirectChannelImpl(this, sensor, i);
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    int getNativeHandle()
    {
        return mNativeHandle;
    }

    public boolean isOpen()
    {
        return mClosed.get() ^ true;
    }

    public boolean isValid()
    {
        return isOpen();
    }

    public static final int RATE_FAST = 2;
    public static final int RATE_NORMAL = 1;
    public static final int RATE_STOP = 0;
    public static final int RATE_VERY_FAST = 3;
    public static final int TYPE_HARDWARE_BUFFER = 2;
    public static final int TYPE_MEMORY_FILE = 1;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final AtomicBoolean mClosed = new AtomicBoolean();
    private final SensorManager mManager;
    private final int mNativeHandle;
    private final long mSize;
    private final int mType;
}
