// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Handler;
import android.view.Surface;
import dalvik.system.CloseGuard;

public final class RemoteDisplay
{
    public static interface Listener
    {

        public abstract void onDisplayConnected(Surface surface, int i, int j, int k, int l);

        public abstract void onDisplayDisconnected();

        public abstract void onDisplayError(int i);
    }


    static Listener _2D_get0(RemoteDisplay remotedisplay)
    {
        return remotedisplay.mListener;
    }

    private RemoteDisplay(Listener listener, Handler handler, String s)
    {
        mListener = listener;
        mHandler = handler;
        mOpPackageName = s;
    }

    private void dispose(boolean flag)
    {
        if(mPtr != 0L)
        {
            if(mGuard != null)
                if(flag)
                    mGuard.warnIfOpen();
                else
                    mGuard.close();
            nativeDispose(mPtr);
            mPtr = 0L;
        }
    }

    public static RemoteDisplay listen(String s, Listener listener, Handler handler, String s1)
    {
        if(s == null)
            throw new IllegalArgumentException("iface must not be null");
        if(listener == null)
            throw new IllegalArgumentException("listener must not be null");
        if(handler == null)
        {
            throw new IllegalArgumentException("handler must not be null");
        } else
        {
            listener = new RemoteDisplay(listener, handler, s1);
            listener.startListening(s);
            return listener;
        }
    }

    private native void nativeDispose(long l);

    private native long nativeListen(String s, String s1);

    private native void nativePause(long l);

    private native void nativeResume(long l);

    private void notifyDisplayConnected(final Surface surface, final int width, final int height, final int flags, final int session)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                RemoteDisplay._2D_get0(RemoteDisplay.this).onDisplayConnected(surface, width, height, flags, session);
            }

            final RemoteDisplay this$0;
            final int val$flags;
            final int val$height;
            final int val$session;
            final Surface val$surface;
            final int val$width;

            
            {
                this$0 = RemoteDisplay.this;
                surface = surface1;
                width = i;
                height = j;
                flags = k;
                session = l;
                super();
            }
        }
);
    }

    private void notifyDisplayDisconnected()
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                RemoteDisplay._2D_get0(RemoteDisplay.this).onDisplayDisconnected();
            }

            final RemoteDisplay this$0;

            
            {
                this$0 = RemoteDisplay.this;
                super();
            }
        }
);
    }

    private void notifyDisplayError(final int error)
    {
        mHandler.post(new Runnable() {

            public void run()
            {
                RemoteDisplay._2D_get0(RemoteDisplay.this).onDisplayError(error);
            }

            final RemoteDisplay this$0;
            final int val$error;

            
            {
                this$0 = RemoteDisplay.this;
                error = i;
                super();
            }
        }
);
    }

    private void startListening(String s)
    {
        mPtr = nativeListen(s, mOpPackageName);
        if(mPtr == 0L)
        {
            throw new IllegalStateException((new StringBuilder()).append("Could not start listening for remote display connection on \"").append(s).append("\"").toString());
        } else
        {
            mGuard.open("dispose");
            return;
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

    public void pause()
    {
        nativePause(mPtr);
    }

    public void resume()
    {
        nativeResume(mPtr);
    }

    public static final int DISPLAY_ERROR_CONNECTION_DROPPED = 2;
    public static final int DISPLAY_ERROR_UNKOWN = 1;
    public static final int DISPLAY_FLAG_SECURE = 1;
    private final CloseGuard mGuard = CloseGuard.get();
    private final Handler mHandler;
    private final Listener mListener;
    private final String mOpPackageName;
    private long mPtr;
}
