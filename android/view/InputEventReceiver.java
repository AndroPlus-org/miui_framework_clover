// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.util.SparseIntArray;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

// Referenced classes of package android.view:
//            InputEvent, Choreographer, InputChannel

public abstract class InputEventReceiver
{
    public static interface Factory
    {

        public abstract InputEventReceiver createInputEventReceiver(InputChannel inputchannel, Looper looper);
    }


    public InputEventReceiver(InputChannel inputchannel, Looper looper)
    {
        if(inputchannel == null)
            throw new IllegalArgumentException("inputChannel must not be null");
        if(looper == null)
        {
            throw new IllegalArgumentException("looper must not be null");
        } else
        {
            mInputChannel = inputchannel;
            mMessageQueue = looper.getQueue();
            mReceiverPtr = nativeInit(new WeakReference(this), inputchannel, mMessageQueue);
            mCloseGuard.open("dispose");
            return;
        }
    }

    private void dispatchBatchedInputEventPending()
    {
        onBatchedInputEventPending();
    }

    private void dispatchInputEvent(int i, InputEvent inputevent, int j)
    {
        mSeqMap.put(inputevent.getSequenceNumber(), i);
        onInputEvent(inputevent, j);
    }

    private void dispatchMotionEventInfo(int i, int j)
    {
        if(mChoreographer == null)
            mChoreographer = Choreographer.getInstance();
        if(mChoreographer != null)
            mChoreographer.setMotionEventInfo(i, j);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("InputEventReceiver", "cannot invoke setMotionEventInfo.");
          goto _L1
    }

    private void dispatchMotionEventInfo_MIUI(int i)
    {
        if(mChoreographer == null)
            mChoreographer = Choreographer.getInstance();
        if(mChoreographer != null)
            mChoreographer.setMotionEventInfo_MIUI(i);
_L1:
        return;
        Exception exception;
        exception;
        Log.e("InputEventReceiver", "cannot invoke setMotionEventInfo.");
          goto _L1
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
        mInputChannel = null;
        mMessageQueue = null;
    }

    private static native boolean nativeConsumeBatchedInputEvents(long l, long l1);

    private static native void nativeDispose(long l);

    private static native void nativeFinishInputEvent(long l, int i, boolean flag);

    private static native long nativeInit(WeakReference weakreference, InputChannel inputchannel, MessageQueue messagequeue);

    public final boolean consumeBatchedInputEvents(long l)
    {
        if(mReceiverPtr == 0L)
        {
            Log.w("InputEventReceiver", "Attempted to consume batched input events but the input event receiver has already been disposed.");
            return false;
        } else
        {
            return nativeConsumeBatchedInputEvents(mReceiverPtr, l);
        }
    }

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

    public final void finishInputEvent(InputEvent inputevent, boolean flag)
    {
        if(inputevent == null)
            throw new IllegalArgumentException("event must not be null");
        if(mReceiverPtr == 0L)
        {
            Log.w("InputEventReceiver", "Attempted to finish an input event but the input event receiver has already been disposed.");
        } else
        {
            int i = mSeqMap.indexOfKey(inputevent.getSequenceNumber());
            if(i < 0)
            {
                Log.w("InputEventReceiver", "Attempted to finish an input event that is not in progress.");
            } else
            {
                int j = mSeqMap.valueAt(i);
                mSeqMap.removeAt(i);
                nativeFinishInputEvent(mReceiverPtr, j, flag);
            }
        }
        inputevent.recycleIfNeededAfterDispatch();
    }

    public void onBatchedInputEventPending()
    {
        consumeBatchedInputEvents(-1L);
    }

    public void onInputEvent(InputEvent inputevent, int i)
    {
        finishInputEvent(inputevent, false);
    }

    private static final String TAG = "InputEventReceiver";
    Choreographer mChoreographer;
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private InputChannel mInputChannel;
    private MessageQueue mMessageQueue;
    private long mReceiverPtr;
    private final SparseIntArray mSeqMap = new SparseIntArray();
}
