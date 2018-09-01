// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;
import android.view.Surface;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.IVideoProvider;

// Referenced classes of package android.telecom:
//            VideoProfile, Log

public class VideoCallImpl extends InCallService.VideoCall
{
    private final class MessageHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(VideoCallImpl._2D_get0(VideoCallImpl.this) == null)
                return;
            message.what;
            JVM INSTR tableswitch 1 7: default 56
        //                       1 57
        //                       2 77
        //                       3 143
        //                       4 166
        //                       5 226
        //                       6 249
        //                       7 269;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            return;
_L2:
            VideoCallImpl._2D_get0(VideoCallImpl.this).onSessionModifyRequestReceived((VideoProfile)message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            message = (SomeArgs)message.obj;
            int i = ((Integer)((SomeArgs) (message)).arg1).intValue();
            VideoProfile videoprofile = (VideoProfile)((SomeArgs) (message)).arg2;
            VideoProfile videoprofile1 = (VideoProfile)((SomeArgs) (message)).arg3;
            VideoCallImpl._2D_get0(VideoCallImpl.this).onSessionModifyResponseReceived(i, videoprofile, videoprofile1);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            message.recycle();
            throw exception;
_L4:
            VideoCallImpl._2D_get0(VideoCallImpl.this).onCallSessionEvent(((Integer)message.obj).intValue());
            continue; /* Loop/switch isn't completed */
_L5:
            message = (SomeArgs)message.obj;
            int k = ((Integer)((SomeArgs) (message)).arg1).intValue();
            int j = ((Integer)((SomeArgs) (message)).arg2).intValue();
            VideoCallImpl._2D_get0(VideoCallImpl.this).onPeerDimensionsChanged(k, j);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            exception;
            message.recycle();
            throw exception;
_L6:
            VideoCallImpl._2D_get0(VideoCallImpl.this).onCallDataUsageChanged(((Long)message.obj).longValue());
            continue; /* Loop/switch isn't completed */
_L7:
            VideoCallImpl._2D_get0(VideoCallImpl.this).onCameraCapabilitiesChanged((VideoProfile.CameraCapabilities)message.obj);
            continue; /* Loop/switch isn't completed */
_L8:
            VideoCallImpl._2D_set0(VideoCallImpl.this, message.arg1);
            VideoCallImpl._2D_get0(VideoCallImpl.this).onVideoQualityChanged(message.arg1);
            if(true) goto _L1; else goto _L9
_L9:
        }

        private static final int MSG_CHANGE_CALL_DATA_USAGE = 5;
        private static final int MSG_CHANGE_CAMERA_CAPABILITIES = 6;
        private static final int MSG_CHANGE_PEER_DIMENSIONS = 4;
        private static final int MSG_CHANGE_VIDEO_QUALITY = 7;
        private static final int MSG_HANDLE_CALL_SESSION_EVENT = 3;
        private static final int MSG_RECEIVE_SESSION_MODIFY_REQUEST = 1;
        private static final int MSG_RECEIVE_SESSION_MODIFY_RESPONSE = 2;
        final VideoCallImpl this$0;

        public MessageHandler(Looper looper)
        {
            this$0 = VideoCallImpl.this;
            super(looper);
        }
    }

    private final class VideoCallListenerBinder extends com.android.internal.telecom.IVideoCallback.Stub
    {

        public void changeCallDataUsage(long l)
        {
            if(VideoCallImpl._2D_get1(VideoCallImpl.this) == null)
            {
                return;
            } else
            {
                VideoCallImpl._2D_get1(VideoCallImpl.this).obtainMessage(5, Long.valueOf(l)).sendToTarget();
                return;
            }
        }

        public void changeCameraCapabilities(VideoProfile.CameraCapabilities cameracapabilities)
        {
            if(VideoCallImpl._2D_get1(VideoCallImpl.this) == null)
            {
                return;
            } else
            {
                VideoCallImpl._2D_get1(VideoCallImpl.this).obtainMessage(6, cameracapabilities).sendToTarget();
                return;
            }
        }

        public void changePeerDimensions(int i, int j)
        {
            if(VideoCallImpl._2D_get1(VideoCallImpl.this) == null)
            {
                return;
            } else
            {
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = Integer.valueOf(i);
                someargs.arg2 = Integer.valueOf(j);
                VideoCallImpl._2D_get1(VideoCallImpl.this).obtainMessage(4, someargs).sendToTarget();
                return;
            }
        }

        public void changeVideoQuality(int i)
        {
            if(VideoCallImpl._2D_get1(VideoCallImpl.this) == null)
            {
                return;
            } else
            {
                VideoCallImpl._2D_get1(VideoCallImpl.this).obtainMessage(7, i, 0).sendToTarget();
                return;
            }
        }

        public void handleCallSessionEvent(int i)
        {
            if(VideoCallImpl._2D_get1(VideoCallImpl.this) == null)
            {
                return;
            } else
            {
                VideoCallImpl._2D_get1(VideoCallImpl.this).obtainMessage(3, Integer.valueOf(i)).sendToTarget();
                return;
            }
        }

        public void receiveSessionModifyRequest(VideoProfile videoprofile)
        {
            if(VideoCallImpl._2D_get1(VideoCallImpl.this) == null)
            {
                return;
            } else
            {
                VideoCallImpl._2D_get1(VideoCallImpl.this).obtainMessage(1, videoprofile).sendToTarget();
                return;
            }
        }

        public void receiveSessionModifyResponse(int i, VideoProfile videoprofile, VideoProfile videoprofile1)
        {
            if(VideoCallImpl._2D_get1(VideoCallImpl.this) == null)
            {
                return;
            } else
            {
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = Integer.valueOf(i);
                someargs.arg2 = videoprofile;
                someargs.arg3 = videoprofile1;
                VideoCallImpl._2D_get1(VideoCallImpl.this).obtainMessage(2, someargs).sendToTarget();
                return;
            }
        }

        final VideoCallImpl this$0;

        private VideoCallListenerBinder()
        {
            this$0 = VideoCallImpl.this;
            super();
        }

        VideoCallListenerBinder(VideoCallListenerBinder videocalllistenerbinder)
        {
            this();
        }
    }


    static InCallService.VideoCall.Callback _2D_get0(VideoCallImpl videocallimpl)
    {
        return videocallimpl.mCallback;
    }

    static Handler _2D_get1(VideoCallImpl videocallimpl)
    {
        return videocallimpl.mHandler;
    }

    static int _2D_set0(VideoCallImpl videocallimpl, int i)
    {
        videocallimpl.mVideoQuality = i;
        return i;
    }

    VideoCallImpl(IVideoProvider ivideoprovider, String s, int i)
        throws RemoteException
    {
        mVideoQuality = 0;
        mVideoState = 0;
        mVideoProvider = ivideoprovider;
        mVideoProvider.addVideoCallback(mBinder);
        mCallingPackageName = s;
        setTargetSdkVersion(i);
    }

    public void destroy()
    {
        unregisterCallback(mCallback);
    }

    public void registerCallback(InCallService.VideoCall.Callback callback)
    {
        registerCallback(callback, null);
    }

    public void registerCallback(InCallService.VideoCall.Callback callback, Handler handler)
    {
        mCallback = callback;
        if(handler == null)
            mHandler = new MessageHandler(Looper.getMainLooper());
        else
            mHandler = new MessageHandler(handler.getLooper());
    }

    public void requestCallDataUsage()
    {
        mVideoProvider.requestCallDataUsage();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void requestCameraCapabilities()
    {
        mVideoProvider.requestCameraCapabilities();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendSessionModifyRequest(VideoProfile videoprofile)
    {
        VideoProfile videoprofile1 = JVM INSTR new #99  <Class VideoProfile>;
        videoprofile1.VideoProfile(mVideoState, mVideoQuality);
        mVideoProvider.sendSessionModifyRequest(videoprofile1, videoprofile);
_L2:
        return;
        videoprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void sendSessionModifyResponse(VideoProfile videoprofile)
    {
        mVideoProvider.sendSessionModifyResponse(videoprofile);
_L2:
        return;
        videoprofile;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setCamera(String s)
    {
        Log.w(this, "setCamera: cameraId=%s, calling=%s", new Object[] {
            s, mCallingPackageName
        });
        mVideoProvider.setCamera(s, mCallingPackageName, mTargetSdkVersion);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setDeviceOrientation(int i)
    {
        mVideoProvider.setDeviceOrientation(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setDisplaySurface(Surface surface)
    {
        mVideoProvider.setDisplaySurface(surface);
_L2:
        return;
        surface;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setPauseImage(Uri uri)
    {
        mVideoProvider.setPauseImage(uri);
_L2:
        return;
        uri;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setPreviewSurface(Surface surface)
    {
        mVideoProvider.setPreviewSurface(surface);
_L2:
        return;
        surface;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setTargetSdkVersion(int i)
    {
        mTargetSdkVersion = i;
    }

    public void setVideoState(int i)
    {
        mVideoState = i;
    }

    public void setZoom(float f)
    {
        mVideoProvider.setZoom(f);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void unregisterCallback(InCallService.VideoCall.Callback callback)
    {
        if(callback != mCallback)
            return;
        mCallback = null;
        if(mVideoProvider != null)
            mVideoProvider.removeVideoCallback(mBinder);
_L2:
        return;
        callback;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final VideoCallListenerBinder mBinder = new VideoCallListenerBinder(null);
    private InCallService.VideoCall.Callback mCallback;
    private final String mCallingPackageName;
    private Handler mHandler;
    private int mTargetSdkVersion;
    private final IVideoProvider mVideoProvider;
    private int mVideoQuality;
    private int mVideoState;
}
