// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

// Referenced classes of package android.view:
//            KeyEvent, MotionEvent, InputChannel, InputEvent

public abstract class InputEventSender
{

    public InputEventSender(InputChannel inputchannel, Looper looper)
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
            mSenderPtr = nativeInit(new WeakReference(this), inputchannel, mMessageQueue);
            mCloseGuard.open("dispose");
            return;
        }
    }

    private void dispatchInputEventFinished(int i, boolean flag)
    {
        onInputEventFinished(i, flag);
    }

    private void dispose(boolean flag)
    {
        if(mCloseGuard != null)
        {
            if(flag)
                mCloseGuard.warnIfOpen();
            mCloseGuard.close();
        }
        if(mSenderPtr != 0L)
        {
            nativeDispose(mSenderPtr);
            mSenderPtr = 0L;
        }
        mInputChannel = null;
        mMessageQueue = null;
    }

    private static native void nativeDispose(long l);

    private static native long nativeInit(WeakReference weakreference, InputChannel inputchannel, MessageQueue messagequeue);

    private static native boolean nativeSendKeyEvent(long l, int i, KeyEvent keyevent);

    private static native boolean nativeSendMotionEvent(long l, int i, MotionEvent motionevent);

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

    public void onInputEventFinished(int i, boolean flag)
    {
    }

    public final boolean sendInputEvent(int i, InputEvent inputevent)
    {
        if(inputevent == null)
            throw new IllegalArgumentException("event must not be null");
        if(mSenderPtr == 0L)
        {
            Log.w("InputEventSender", "Attempted to send an input event but the input event sender has already been disposed.");
            return false;
        }
        if(inputevent instanceof KeyEvent)
            return nativeSendKeyEvent(mSenderPtr, i, (KeyEvent)inputevent);
        else
            return nativeSendMotionEvent(mSenderPtr, i, (MotionEvent)inputevent);
    }

    private static final String TAG = "InputEventSender";
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private InputChannel mInputChannel;
    private MessageQueue mMessageQueue;
    private long mSenderPtr;
}
