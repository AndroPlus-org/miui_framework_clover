// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.os.*;
import android.util.Log;

public abstract class Filter
{
    public static interface Delayer
    {

        public abstract long getPostingDelay(CharSequence charsequence);
    }

    public static interface FilterListener
    {

        public abstract void onFilterComplete(int i);
    }

    protected static class FilterResults
    {

        public int count;
        public Object values;

        public FilterResults()
        {
        }
    }

    private static class RequestArguments
    {

        CharSequence constraint;
        FilterListener listener;
        FilterResults results;

        private RequestArguments()
        {
        }

        RequestArguments(RequestArguments requestarguments)
        {
            this();
        }
    }

    private class RequestHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            int i = message.what;
            i;
            JVM INSTR lookupswitch 2: default 32
        //                       -791613427: 33
        //                       -559038737: 214;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            message = (RequestArguments)message.obj;
            message.results = performFiltering(((RequestArguments) (message)).constraint);
            Message message1 = Filter._2D_get1(Filter.this).obtainMessage(i);
            message1.obj = message;
            message1.sendToTarget();
_L4:
            Object obj = Filter._2D_get0(Filter.this);
            obj;
            JVM INSTR monitorenter ;
            message = ((Message) (obj));
            if(Filter._2D_get2(Filter.this) == null)
                break MISSING_BLOCK_LABEL_129;
            message = Filter._2D_get2(Filter.this).obtainMessage(0xdeadbeef);
            Filter._2D_get2(Filter.this).sendMessageDelayed(message, 3000L);
            message = ((Message) (obj));
_L6:
            message;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Object obj1;
            obj1;
            obj = JVM INSTR new #73  <Class Filter$FilterResults>;
            ((FilterResults) (obj)).FilterResults();
            message.results = ((FilterResults) (obj));
            Log.w("Filter", "An exception occured during performFiltering()!", ((Throwable) (obj1)));
            obj = Filter._2D_get1(Filter.this).obtainMessage(i);
            obj.obj = message;
            ((Message) (obj)).sendToTarget();
              goto _L4
            obj1;
            obj = Filter._2D_get1(Filter.this).obtainMessage(i);
            obj.obj = message;
            ((Message) (obj)).sendToTarget();
            throw obj1;
            message;
            throw message;
_L3:
            obj = Filter._2D_get0(Filter.this);
            obj;
            JVM INSTR monitorenter ;
            message = ((Message) (obj));
            if(Filter._2D_get2(Filter.this) == null) goto _L6; else goto _L5
_L5:
            Filter._2D_get2(Filter.this).getLooper().quit();
            Filter._2D_set0(Filter.this, null);
            message = ((Message) (obj));
              goto _L6
            message;
            throw message;
            if(true) goto _L1; else goto _L7
_L7:
        }

        final Filter this$0;

        public RequestHandler(Looper looper)
        {
            this$0 = Filter.this;
            super(looper);
        }
    }

    private class ResultsHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message = (RequestArguments)message.obj;
            publishResults(((RequestArguments) (message)).constraint, ((RequestArguments) (message)).results);
            if(((RequestArguments) (message)).listener != null)
            {
                int i;
                if(((RequestArguments) (message)).results != null)
                    i = ((RequestArguments) (message)).results.count;
                else
                    i = -1;
                ((RequestArguments) (message)).listener.onFilterComplete(i);
            }
        }

        final Filter this$0;

        private ResultsHandler()
        {
            this$0 = Filter.this;
            super();
        }

        ResultsHandler(ResultsHandler resultshandler)
        {
            this();
        }
    }


    static Object _2D_get0(Filter filter1)
    {
        return filter1.mLock;
    }

    static Handler _2D_get1(Filter filter1)
    {
        return filter1.mResultHandler;
    }

    static Handler _2D_get2(Filter filter1)
    {
        return filter1.mThreadHandler;
    }

    static Handler _2D_set0(Filter filter1, Handler handler)
    {
        filter1.mThreadHandler = handler;
        return handler;
    }

    public Filter()
    {
        mResultHandler = new ResultsHandler(null);
    }

    public CharSequence convertResultToString(Object obj)
    {
        if(obj == null)
            obj = "";
        else
            obj = obj.toString();
        return ((CharSequence) (obj));
    }

    public final void filter(CharSequence charsequence)
    {
        filter(charsequence, null);
    }

    public final void filter(CharSequence charsequence, FilterListener filterlistener)
    {
        String s = null;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mThreadHandler == null)
        {
            HandlerThread handlerthread = JVM INSTR new #75  <Class HandlerThread>;
            handlerthread.HandlerThread("Filter", 10);
            handlerthread.start();
            RequestHandler requesthandler = JVM INSTR new #18  <Class Filter$RequestHandler>;
            requesthandler.this. RequestHandler(handlerthread.getLooper());
            mThreadHandler = requesthandler;
        }
        if(mDelayer != null) goto _L2; else goto _L1
_L1:
        long l = 0L;
_L4:
        RequestArguments requestarguments;
        Message message;
        message = mThreadHandler.obtainMessage(0xd0d0f00d);
        requestarguments = JVM INSTR new #15  <Class Filter$RequestArguments>;
        requestarguments.RequestArguments(null);
        if(charsequence == null)
            break MISSING_BLOCK_LABEL_102;
        s = charsequence.toString();
        requestarguments.constraint = s;
        requestarguments.listener = filterlistener;
        message.obj = requestarguments;
        mThreadHandler.removeMessages(0xd0d0f00d);
        mThreadHandler.removeMessages(0xdeadbeef);
        mThreadHandler.sendMessageDelayed(message, l);
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        l = mDelayer.getPostingDelay(charsequence);
        if(true) goto _L4; else goto _L3
_L3:
        charsequence;
        throw charsequence;
    }

    protected abstract FilterResults performFiltering(CharSequence charsequence);

    protected abstract void publishResults(CharSequence charsequence, FilterResults filterresults);

    public void setDelayer(Delayer delayer)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mDelayer = delayer;
        obj;
        JVM INSTR monitorexit ;
        return;
        delayer;
        throw delayer;
    }

    private static final int FILTER_TOKEN = 0xd0d0f00d;
    private static final int FINISH_TOKEN = 0xdeadbeef;
    private static final String LOG_TAG = "Filter";
    private static final String THREAD_NAME = "Filter";
    private Delayer mDelayer;
    private final Object mLock = new Object();
    private Handler mResultHandler;
    private Handler mThreadHandler;
}
