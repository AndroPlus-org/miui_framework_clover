// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.LongSparseArray;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

// Referenced classes of package android.view:
//            KeyEvent, MotionEvent, InputEvent

public final class InputQueue
{
    private final class ActiveInputEvent
    {

        public void recycle()
        {
            mToken = null;
            mCallback = null;
        }

        public FinishedInputEventCallback mCallback;
        public Object mToken;
        final InputQueue this$0;

        private ActiveInputEvent()
        {
            this$0 = InputQueue.this;
            super();
        }

        ActiveInputEvent(ActiveInputEvent activeinputevent)
        {
            this();
        }
    }

    public static interface Callback
    {

        public abstract void onInputQueueCreated(InputQueue inputqueue);

        public abstract void onInputQueueDestroyed(InputQueue inputqueue);
    }

    public static interface FinishedInputEventCallback
    {

        public abstract void onFinishedInputEvent(Object obj, boolean flag);
    }


    public InputQueue()
    {
        mPtr = nativeInit(new WeakReference(this), Looper.myQueue());
        mCloseGuard.open("dispose");
    }

    private void finishInputEvent(long l, boolean flag)
    {
        int i = mActiveEventArray.indexOfKey(l);
        if(i >= 0)
        {
            ActiveInputEvent activeinputevent = (ActiveInputEvent)mActiveEventArray.valueAt(i);
            mActiveEventArray.removeAt(i);
            activeinputevent.mCallback.onFinishedInputEvent(activeinputevent.mToken, flag);
            recycleActiveInputEvent(activeinputevent);
        }
    }

    private static native void nativeDispose(long l);

    private static native long nativeInit(WeakReference weakreference, MessageQueue messagequeue);

    private static native long nativeSendKeyEvent(long l, KeyEvent keyevent, boolean flag);

    private static native long nativeSendMotionEvent(long l, MotionEvent motionevent);

    private ActiveInputEvent obtainActiveInputEvent(Object obj, FinishedInputEventCallback finishedinputeventcallback)
    {
        ActiveInputEvent activeinputevent = (ActiveInputEvent)mActiveInputEventPool.acquire();
        ActiveInputEvent activeinputevent1 = activeinputevent;
        if(activeinputevent == null)
            activeinputevent1 = new ActiveInputEvent(null);
        activeinputevent1.mToken = obj;
        activeinputevent1.mCallback = finishedinputeventcallback;
        return activeinputevent1;
    }

    private void recycleActiveInputEvent(ActiveInputEvent activeinputevent)
    {
        activeinputevent.recycle();
        mActiveInputEventPool.release(activeinputevent);
    }

    public void dispose()
    {
        dispose(false);
    }

    public void dispose(boolean flag)
    {
        if(mCloseGuard != null)
        {
            if(flag)
                mCloseGuard.warnIfOpen();
            mCloseGuard.close();
        }
        if(mPtr != 0L)
        {
            nativeDispose(mPtr);
            mPtr = 0L;
        }
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

    public long getNativePtr()
    {
        return mPtr;
    }

    public void sendInputEvent(InputEvent inputevent, Object obj, boolean flag, FinishedInputEventCallback finishedinputeventcallback)
    {
        obj = obtainActiveInputEvent(obj, finishedinputeventcallback);
        long l;
        if(inputevent instanceof KeyEvent)
            l = nativeSendKeyEvent(mPtr, (KeyEvent)inputevent, flag);
        else
            l = nativeSendMotionEvent(mPtr, (MotionEvent)inputevent);
        mActiveEventArray.put(l, obj);
    }

    private final LongSparseArray mActiveEventArray = new LongSparseArray(20);
    private final android.util.Pools.Pool mActiveInputEventPool = new android.util.Pools.SimplePool(20);
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private long mPtr;
}
