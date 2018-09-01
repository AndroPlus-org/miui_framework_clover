// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Looper;
import android.os.MessageQueue;
import com.android.internal.util.VirtualRefBasePtr;
import java.lang.ref.WeakReference;

// Referenced classes of package android.view:
//            FrameMetrics, Window

public class FrameMetricsObserver
{

    FrameMetricsObserver(Window window, Looper looper, Window.OnFrameMetricsAvailableListener onframemetricsavailablelistener)
    {
        if(looper == null)
            throw new NullPointerException("looper cannot be null");
        mMessageQueue = looper.getQueue();
        if(mMessageQueue == null)
        {
            throw new IllegalStateException("invalid looper, null message queue\n");
        } else
        {
            mFrameMetrics = new FrameMetrics();
            mWindow = new WeakReference(window);
            mListener = onframemetricsavailablelistener;
            return;
        }
    }

    private void notifyDataAvailable(int i)
    {
        Window window = (Window)mWindow.get();
        if(window != null)
            mListener.onFrameMetricsAvailable(window, mFrameMetrics, i);
    }

    private FrameMetrics mFrameMetrics;
    Window.OnFrameMetricsAvailableListener mListener;
    private MessageQueue mMessageQueue;
    VirtualRefBasePtr mNative;
    private WeakReference mWindow;
}
