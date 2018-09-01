// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.net.Uri;
import android.os.*;
import android.telecom.VideoProfile;
import android.view.Surface;
import com.android.internal.os.SomeArgs;

// Referenced classes of package com.android.ims.internal:
//            IImsVideoCallCallback, IImsVideoCallProvider

public abstract class ImsVideoCallProvider
{
    private final class ImsVideoCallProviderBinder extends IImsVideoCallProvider.Stub
    {

        public void requestCallDataUsage()
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(10).sendToTarget();
        }

        public void requestCameraCapabilities()
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(9).sendToTarget();
        }

        public void sendSessionModifyRequest(VideoProfile videoprofile, VideoProfile videoprofile1)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = videoprofile;
            someargs.arg2 = videoprofile1;
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(7, someargs).sendToTarget();
        }

        public void sendSessionModifyResponse(VideoProfile videoprofile)
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(8, videoprofile).sendToTarget();
        }

        public void setCallback(IImsVideoCallCallback iimsvideocallcallback)
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(1, iimsvideocallcallback).sendToTarget();
        }

        public void setCamera(String s, int i)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.argi1 = i;
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(2, someargs).sendToTarget();
        }

        public void setDeviceOrientation(int i)
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(5, i, 0).sendToTarget();
        }

        public void setDisplaySurface(Surface surface)
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(4, surface).sendToTarget();
        }

        public void setPauseImage(Uri uri)
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(11, uri).sendToTarget();
        }

        public void setPreviewSurface(Surface surface)
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(3, surface).sendToTarget();
        }

        public void setZoom(float f)
        {
            ImsVideoCallProvider._2D_get0(ImsVideoCallProvider.this).obtainMessage(6, Float.valueOf(f)).sendToTarget();
        }

        final ImsVideoCallProvider this$0;

        private ImsVideoCallProviderBinder()
        {
            this$0 = ImsVideoCallProvider.this;
            super();
        }

        ImsVideoCallProviderBinder(ImsVideoCallProviderBinder imsvideocallproviderbinder)
        {
            this();
        }
    }


    static Handler _2D_get0(ImsVideoCallProvider imsvideocallprovider)
    {
        return imsvideocallprovider.mProviderHandler;
    }

    static IImsVideoCallCallback _2D_set0(ImsVideoCallProvider imsvideocallprovider, IImsVideoCallCallback iimsvideocallcallback)
    {
        imsvideocallprovider.mCallback = iimsvideocallcallback;
        return iimsvideocallcallback;
    }

    public ImsVideoCallProvider()
    {
    }

    public void changeCallDataUsage(long l)
    {
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_17;
        mCallback.changeCallDataUsage(l);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void changeCameraCapabilities(android.telecom.VideoProfile.CameraCapabilities cameracapabilities)
    {
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_17;
        mCallback.changeCameraCapabilities(cameracapabilities);
_L2:
        return;
        cameracapabilities;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void changePeerDimensions(int i, int j)
    {
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_18;
        mCallback.changePeerDimensions(i, j);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void changeVideoQuality(int i)
    {
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_17;
        mCallback.changeVideoQuality(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final IImsVideoCallProvider getInterface()
    {
        return mBinder;
    }

    public void handleCallSessionEvent(int i)
    {
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_17;
        mCallback.handleCallSessionEvent(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public abstract void onRequestCallDataUsage();

    public abstract void onRequestCameraCapabilities();

    public abstract void onSendSessionModifyRequest(VideoProfile videoprofile, VideoProfile videoprofile1);

    public abstract void onSendSessionModifyResponse(VideoProfile videoprofile);

    public abstract void onSetCamera(String s);

    public void onSetCamera(String s, int i)
    {
    }

    public abstract void onSetDeviceOrientation(int i);

    public abstract void onSetDisplaySurface(Surface surface);

    public abstract void onSetPauseImage(Uri uri);

    public abstract void onSetPreviewSurface(Surface surface);

    public abstract void onSetZoom(float f);

    public void receiveSessionModifyRequest(VideoProfile videoprofile)
    {
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_17;
        mCallback.receiveSessionModifyRequest(videoprofile);
_L2:
        return;
        videoprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void receiveSessionModifyResponse(int i, VideoProfile videoprofile, VideoProfile videoprofile1)
    {
        if(mCallback == null)
            break MISSING_BLOCK_LABEL_19;
        mCallback.receiveSessionModifyResponse(i, videoprofile, videoprofile1);
_L2:
        return;
        videoprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final int MSG_REQUEST_CALL_DATA_USAGE = 10;
    private static final int MSG_REQUEST_CAMERA_CAPABILITIES = 9;
    private static final int MSG_SEND_SESSION_MODIFY_REQUEST = 7;
    private static final int MSG_SEND_SESSION_MODIFY_RESPONSE = 8;
    private static final int MSG_SET_CALLBACK = 1;
    private static final int MSG_SET_CAMERA = 2;
    private static final int MSG_SET_DEVICE_ORIENTATION = 5;
    private static final int MSG_SET_DISPLAY_SURFACE = 4;
    private static final int MSG_SET_PAUSE_IMAGE = 11;
    private static final int MSG_SET_PREVIEW_SURFACE = 3;
    private static final int MSG_SET_ZOOM = 6;
    private final ImsVideoCallProviderBinder mBinder = new ImsVideoCallProviderBinder(null);
    private IImsVideoCallCallback mCallback;
    private final Handler mProviderHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 11: default 64
        //                       1 65
        //                       2 83
        //                       3 137
        //                       4 154
        //                       5 171
        //                       6 185
        //                       7 205
        //                       8 252
        //                       9 269
        //                       10 279
        //                       11 289;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
            return;
_L2:
            ImsVideoCallProvider._2D_set0(ImsVideoCallProvider.this, (IImsVideoCallCallback)message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            SomeArgs someargs = (SomeArgs)message.obj;
            onSetCamera((String)someargs.arg1);
            onSetCamera((String)someargs.arg1, someargs.argi1);
            someargs.recycle();
            continue; /* Loop/switch isn't completed */
            message;
            someargs.recycle();
            throw message;
_L4:
            onSetPreviewSurface((Surface)message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            onSetDisplaySurface((Surface)message.obj);
            continue; /* Loop/switch isn't completed */
_L6:
            onSetDeviceOrientation(message.arg1);
            continue; /* Loop/switch isn't completed */
_L7:
            onSetZoom(((Float)message.obj).floatValue());
            continue; /* Loop/switch isn't completed */
_L8:
            message = (SomeArgs)message.obj;
            VideoProfile videoprofile1 = (VideoProfile)((SomeArgs) (message)).arg1;
            VideoProfile videoprofile = (VideoProfile)((SomeArgs) (message)).arg2;
            onSendSessionModifyRequest(videoprofile1, videoprofile);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            message.recycle();
            throw exception;
_L9:
            onSendSessionModifyResponse((VideoProfile)message.obj);
            continue; /* Loop/switch isn't completed */
_L10:
            onRequestCameraCapabilities();
            continue; /* Loop/switch isn't completed */
_L11:
            onRequestCallDataUsage();
            continue; /* Loop/switch isn't completed */
_L12:
            onSetPauseImage((Uri)message.obj);
            if(true) goto _L1; else goto _L13
_L13:
        }

        final ImsVideoCallProvider this$0;

            
            {
                this$0 = ImsVideoCallProvider.this;
                super(looper);
            }
    }
;
}
