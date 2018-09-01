// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;
import android.os.Handler;
import android.os.Looper;

public class CallbackFilter extends Filter
{
    private class CallbackRunnable
        implements Runnable
    {

        public void run()
        {
            mListener.onFrameReceived(mFilter, mFrame, mUserData);
            mFrame.release();
        }

        private Filter mFilter;
        private Frame mFrame;
        private android.filterfw.core.FilterContext.OnFrameReceivedListener mListener;
        private Object mUserData;
        final CallbackFilter this$0;

        public CallbackRunnable(android.filterfw.core.FilterContext.OnFrameReceivedListener onframereceivedlistener, Filter filter, Frame frame, Object obj)
        {
            this$0 = CallbackFilter.this;
            super();
            mListener = onframereceivedlistener;
            mFilter = filter;
            mFrame = frame;
            mUserData = obj;
        }
    }


    public CallbackFilter(String s)
    {
        super(s);
        mCallbacksOnUiThread = true;
    }

    public void prepare(FilterContext filtercontext)
    {
        if(mCallbacksOnUiThread)
            mUiThreadHandler = new Handler(Looper.getMainLooper());
    }

    public void process(FilterContext filtercontext)
    {
        filtercontext = pullInput("frame");
        if(mListener != null)
        {
            if(mCallbacksOnUiThread)
            {
                filtercontext.retain();
                filtercontext = new CallbackRunnable(mListener, this, filtercontext, mUserData);
                if(!mUiThreadHandler.post(filtercontext))
                    throw new RuntimeException("Unable to send callback to UI thread!");
            } else
            {
                mListener.onFrameReceived(this, filtercontext, mUserData);
            }
            return;
        } else
        {
            throw new RuntimeException("CallbackFilter received frame, but no listener set!");
        }
    }

    public void setupPorts()
    {
        addInputPort("frame");
    }

    private boolean mCallbacksOnUiThread;
    private android.filterfw.core.FilterContext.OnFrameReceivedListener mListener;
    private Handler mUiThreadHandler;
    private Object mUserData;
}
