// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.projection;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.ArrayMap;
import android.util.Log;
import java.util.Map;

// Referenced classes of package android.media.projection:
//            IMediaProjectionManager, MediaProjection, MediaProjectionInfo

public final class MediaProjectionManager
{
    public static abstract class Callback
    {

        public abstract void onStart(MediaProjectionInfo mediaprojectioninfo);

        public abstract void onStop(MediaProjectionInfo mediaprojectioninfo);

        public Callback()
        {
        }
    }

    private static final class CallbackDelegate extends IMediaProjectionWatcherCallback.Stub
    {

        static Callback _2D_get0(CallbackDelegate callbackdelegate)
        {
            return callbackdelegate.mCallback;
        }

        public void onStart(MediaProjectionInfo mediaprojectioninfo)
        {
            mHandler.post(mediaprojectioninfo. new Runnable() {

                public void run()
                {
                    CallbackDelegate._2D_get0(CallbackDelegate.this).onStart(info);
                }

                final CallbackDelegate this$1;
                final MediaProjectionInfo val$info;

            
            {
                this$1 = final_callbackdelegate;
                info = MediaProjectionInfo.this;
                super();
            }
            }
);
        }

        public void onStop(MediaProjectionInfo mediaprojectioninfo)
        {
            mHandler.post(mediaprojectioninfo. new Runnable() {

                public void run()
                {
                    CallbackDelegate._2D_get0(CallbackDelegate.this).onStop(info);
                }

                final CallbackDelegate this$1;
                final MediaProjectionInfo val$info;

            
            {
                this$1 = final_callbackdelegate;
                info = MediaProjectionInfo.this;
                super();
            }
            }
);
        }

        private Callback mCallback;
        private Handler mHandler;

        public CallbackDelegate(Callback callback, Handler handler)
        {
            mCallback = callback;
            callback = handler;
            if(handler == null)
                callback = new Handler();
            mHandler = callback;
        }
    }


    public MediaProjectionManager(Context context)
    {
        mContext = context;
        mService = IMediaProjectionManager.Stub.asInterface(ServiceManager.getService("media_projection"));
        mCallbacks = new ArrayMap();
    }

    public void addCallback(Callback callback, Handler handler)
    {
        if(callback == null)
            throw new IllegalArgumentException("callback must not be null");
        handler = new CallbackDelegate(callback, handler);
        mCallbacks.put(callback, handler);
        mService.addCallback(handler);
_L1:
        return;
        callback;
        Log.e("MediaProjectionManager", "Unable to add callbacks to MediaProjection service", callback);
          goto _L1
    }

    public Intent createScreenCaptureIntent()
    {
        Intent intent = new Intent();
        intent.setClassName("com.android.systemui", "com.android.systemui.media.MediaProjectionPermissionActivity");
        return intent;
    }

    public MediaProjectionInfo getActiveProjectionInfo()
    {
        MediaProjectionInfo mediaprojectioninfo;
        try
        {
            mediaprojectioninfo = mService.getActiveProjectionInfo();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("MediaProjectionManager", "Unable to get the active projection info", remoteexception);
            return null;
        }
        return mediaprojectioninfo;
    }

    public MediaProjection getMediaProjection(int i, Intent intent)
    {
        if(i != -1 || intent == null)
            return null;
        intent = intent.getIBinderExtra("android.media.projection.extra.EXTRA_MEDIA_PROJECTION");
        if(intent == null)
            return null;
        else
            return new MediaProjection(mContext, IMediaProjection.Stub.asInterface(intent));
    }

    public void removeCallback(Callback callback)
    {
        if(callback == null)
            throw new IllegalArgumentException("callback must not be null");
        callback = (CallbackDelegate)mCallbacks.remove(callback);
        if(callback == null)
            break MISSING_BLOCK_LABEL_42;
        mService.removeCallback(callback);
_L1:
        return;
        callback;
        Log.e("MediaProjectionManager", "Unable to add callbacks to MediaProjection service", callback);
          goto _L1
    }

    public void stopActiveProjection()
    {
        mService.stopActiveProjection();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MediaProjectionManager", "Unable to stop the currently active media projection", remoteexception);
          goto _L1
    }

    public static final String EXTRA_APP_TOKEN = "android.media.projection.extra.EXTRA_APP_TOKEN";
    public static final String EXTRA_MEDIA_PROJECTION = "android.media.projection.extra.EXTRA_MEDIA_PROJECTION";
    private static final String TAG = "MediaProjectionManager";
    public static final int TYPE_MIRRORING = 1;
    public static final int TYPE_PRESENTATION = 2;
    public static final int TYPE_SCREEN_CAPTURE = 0;
    private Map mCallbacks;
    private Context mContext;
    private IMediaProjectionManager mService;
}
