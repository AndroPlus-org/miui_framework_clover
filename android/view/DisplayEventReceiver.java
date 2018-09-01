// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

public abstract class DisplayEventReceiver
{

    public DisplayEventReceiver(Looper looper)
    {
        this(looper, 0);
    }

    public DisplayEventReceiver(Looper looper, int i)
    {
        mCloseGuard = CloseGuard.get();
        if(looper == null)
        {
            throw new IllegalArgumentException("looper must not be null");
        } else
        {
            mMessageQueue = looper.getQueue();
            mReceiverPtr = nativeInit(new WeakReference(this), mMessageQueue, i);
            mCloseGuard.open("dispose");
            return;
        }
    }

    private void dispatchHotplug(long l, int i, boolean flag)
    {
        onHotplug(l, i, flag);
    }

    private void dispatchVsync(long l, int i, int j)
    {
        onVsync(l, i, j);
    }

    private void dispose(boolean flag)
    {
        if(mCloseGuard != null)
        {
            if(flag)
                mCloseGuard.warnIfOpen();
            mCloseGuard.close();
        }
        if(mReceiverPtr != 0L)
        {
            nativeDispose(mReceiverPtr);
            mReceiverPtr = 0L;
        }
        mMessageQueue = null;
    }

    private static native void nativeDispose(long l);

    private static native long nativeInit(WeakReference weakreference, MessageQueue messagequeue, int i);

    private static native void nativeScheduleVsync(long l);

    public void dispose()
    {
        dispose(false);
    }

    protected void finalize()
        throws Throwable
    {
        dispose(true);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void onHotplug(long l, int i, boolean flag)
    {
    }

    public void onVsync(long l, int i, int j)
    {
    }

    public void scheduleVsync()
    {
        if(mReceiverPtr == 0L)
            Log.w("DisplayEventReceiver", "Attempted to schedule a vertical sync pulse but the display event receiver has already been disposed.");
        else
            nativeScheduleVsync(mReceiverPtr);
    }

    private static final String TAG = "DisplayEventReceiver";
    public static final int VSYNC_SOURCE_APP = 0;
    public static final int VSYNC_SOURCE_SURFACE_FLINGER = 1;
    private final CloseGuard mCloseGuard;
    private MessageQueue mMessageQueue;
    private long mReceiverPtr;
}
