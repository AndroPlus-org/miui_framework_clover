// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.projection;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Surface;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package android.media.projection:
//            IMediaProjection

public final class MediaProjection
{
    public static abstract class Callback
    {

        public void onStop()
        {
        }

        public Callback()
        {
        }
    }

    private static final class CallbackRecord
    {

        static Callback _2D_get0(CallbackRecord callbackrecord)
        {
            return callbackrecord.mCallback;
        }

        public void onStop()
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    CallbackRecord._2D_get0(CallbackRecord.this).onStop();
                }

                final CallbackRecord this$1;

            
            {
                this$1 = CallbackRecord.this;
                super();
            }
            }
);
        }

        private final Callback mCallback;
        private final Handler mHandler;

        public CallbackRecord(Callback callback, Handler handler)
        {
            mCallback = callback;
            mHandler = handler;
        }
    }

    private final class MediaProjectionCallback extends IMediaProjectionCallback.Stub
    {

        public void onStop()
        {
            for(Iterator iterator = MediaProjection._2D_get0(MediaProjection.this).values().iterator(); iterator.hasNext(); ((CallbackRecord)iterator.next()).onStop());
        }

        final MediaProjection this$0;

        private MediaProjectionCallback()
        {
            this$0 = MediaProjection.this;
            super();
        }

        MediaProjectionCallback(MediaProjectionCallback mediaprojectioncallback)
        {
            this();
        }
    }


    static Map _2D_get0(MediaProjection mediaprojection)
    {
        return mediaprojection.mCallbacks;
    }

    public MediaProjection(Context context, IMediaProjection imediaprojection)
    {
        mContext = context;
        mImpl = imediaprojection;
        try
        {
            context = mImpl;
            imediaprojection = JVM INSTR new #14  <Class MediaProjection$MediaProjectionCallback>;
            imediaprojection.this. MediaProjectionCallback(null);
            context.start(imediaprojection);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Failed to start media projection", context);
        }
    }

    public AudioRecord createAudioRecord(int i, int j, int k, int l)
    {
        return null;
    }

    public VirtualDisplay createVirtualDisplay(String s, int i, int j, int k, int l, Surface surface, android.hardware.display.VirtualDisplay.Callback callback, 
            Handler handler)
    {
        return ((DisplayManager)mContext.getSystemService("display")).createVirtualDisplay(this, s, i, j, k, surface, l, callback, handler, null);
    }

    public VirtualDisplay createVirtualDisplay(String s, int i, int j, int k, boolean flag, Surface surface, android.hardware.display.VirtualDisplay.Callback callback, 
            Handler handler)
    {
        DisplayManager displaymanager = (DisplayManager)mContext.getSystemService("display");
        byte byte0;
        if(flag)
            byte0 = 4;
        else
            byte0 = 0;
        return displaymanager.createVirtualDisplay(this, s, i, j, k, surface, byte0 | 0x10 | 2, callback, handler, null);
    }

    public IMediaProjection getProjection()
    {
        return mImpl;
    }

    public void registerCallback(Callback callback, Handler handler)
    {
        if(callback == null)
            throw new IllegalArgumentException("callback should not be null");
        Handler handler1 = handler;
        if(handler == null)
            handler1 = new Handler();
        mCallbacks.put(callback, new CallbackRecord(callback, handler1));
    }

    public void stop()
    {
        mImpl.stop();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MediaProjection", "Unable to stop projection", remoteexception);
          goto _L1
    }

    public void unregisterCallback(Callback callback)
    {
        if(callback == null)
        {
            throw new IllegalArgumentException("callback should not be null");
        } else
        {
            mCallbacks.remove(callback);
            return;
        }
    }

    private static final String TAG = "MediaProjection";
    private final Map mCallbacks = new ArrayMap();
    private final Context mContext;
    private final IMediaProjection mImpl;
}
