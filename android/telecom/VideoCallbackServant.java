// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.*;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.IVideoCallback;

// Referenced classes of package android.telecom:
//            VideoProfile

final class VideoCallbackServant
{

    static IVideoCallback _2D_get0(VideoCallbackServant videocallbackservant)
    {
        return videocallbackservant.mDelegate;
    }

    static Handler _2D_get1(VideoCallbackServant videocallbackservant)
    {
        return videocallbackservant.mHandler;
    }

    public VideoCallbackServant(IVideoCallback ivideocallback)
    {
        mDelegate = ivideocallback;
    }

    public IVideoCallback getStub()
    {
        return mStub;
    }

    private static final int MSG_CHANGE_CALL_DATA_USAGE = 4;
    private static final int MSG_CHANGE_CAMERA_CAPABILITIES = 5;
    private static final int MSG_CHANGE_PEER_DIMENSIONS = 3;
    private static final int MSG_CHANGE_VIDEO_QUALITY = 6;
    private static final int MSG_HANDLE_CALL_SESSION_EVENT = 2;
    private static final int MSG_RECEIVE_SESSION_MODIFY_REQUEST = 0;
    private static final int MSG_RECEIVE_SESSION_MODIFY_RESPONSE = 1;
    private final IVideoCallback mDelegate;
    private final Handler mHandler = new Handler() {

        private void internalHandleMessage(Message message)
            throws RemoteException
        {
            message.what;
            JVM INSTR tableswitch 0 6: default 48
        //                       0 49
        //                       1 71
        //                       2 123
        //                       3 161
        //                       4 203
        //                       5 247
        //                       6 269;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            return;
_L2:
            VideoCallbackServant._2D_get0(VideoCallbackServant.this).receiveSessionModifyRequest((VideoProfile)message.obj);
            continue; /* Loop/switch isn't completed */
_L3:
            message = (SomeArgs)message.obj;
            VideoCallbackServant._2D_get0(VideoCallbackServant.this).receiveSessionModifyResponse(((SomeArgs) (message)).argi1, (VideoProfile)((SomeArgs) (message)).arg1, (VideoProfile)((SomeArgs) (message)).arg2);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            message.recycle();
            throw exception;
_L4:
            SomeArgs someargs = (SomeArgs)message.obj;
            VideoCallbackServant._2D_get0(VideoCallbackServant.this).handleCallSessionEvent(someargs.argi1);
            someargs.recycle();
            continue; /* Loop/switch isn't completed */
            message;
            someargs.recycle();
            throw message;
_L5:
            someargs = (SomeArgs)message.obj;
            VideoCallbackServant._2D_get0(VideoCallbackServant.this).changePeerDimensions(someargs.argi1, someargs.argi2);
            someargs.recycle();
            continue; /* Loop/switch isn't completed */
            message;
            someargs.recycle();
            throw message;
_L6:
            someargs = (SomeArgs)message.obj;
            VideoCallbackServant._2D_get0(VideoCallbackServant.this).changeCallDataUsage(((Long)someargs.arg1).longValue());
            someargs.recycle();
            continue; /* Loop/switch isn't completed */
            message;
            someargs.recycle();
            throw message;
_L7:
            VideoCallbackServant._2D_get0(VideoCallbackServant.this).changeCameraCapabilities((VideoProfile.CameraCapabilities)message.obj);
            continue; /* Loop/switch isn't completed */
_L8:
            VideoCallbackServant._2D_get0(VideoCallbackServant.this).changeVideoQuality(message.arg1);
            if(true) goto _L1; else goto _L9
_L9:
        }

        public void handleMessage(Message message)
        {
            internalHandleMessage(message);
_L2:
            return;
            message;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final VideoCallbackServant this$0;

            
            {
                this$0 = VideoCallbackServant.this;
                super();
            }
    }
;
    private final IVideoCallback mStub = new com.android.internal.telecom.IVideoCallback.Stub() {

        public void changeCallDataUsage(long l)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = Long.valueOf(l);
            VideoCallbackServant._2D_get1(VideoCallbackServant.this).obtainMessage(4, someargs).sendToTarget();
        }

        public void changeCameraCapabilities(VideoProfile.CameraCapabilities cameracapabilities)
            throws RemoteException
        {
            VideoCallbackServant._2D_get1(VideoCallbackServant.this).obtainMessage(5, cameracapabilities).sendToTarget();
        }

        public void changePeerDimensions(int i, int j)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.argi1 = i;
            someargs.argi2 = j;
            VideoCallbackServant._2D_get1(VideoCallbackServant.this).obtainMessage(3, someargs).sendToTarget();
        }

        public void changeVideoQuality(int i)
            throws RemoteException
        {
            VideoCallbackServant._2D_get1(VideoCallbackServant.this).obtainMessage(6, i, 0).sendToTarget();
        }

        public void handleCallSessionEvent(int i)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.argi1 = i;
            VideoCallbackServant._2D_get1(VideoCallbackServant.this).obtainMessage(2, someargs).sendToTarget();
        }

        public void receiveSessionModifyRequest(VideoProfile videoprofile)
            throws RemoteException
        {
            VideoCallbackServant._2D_get1(VideoCallbackServant.this).obtainMessage(0, videoprofile).sendToTarget();
        }

        public void receiveSessionModifyResponse(int i, VideoProfile videoprofile, VideoProfile videoprofile1)
            throws RemoteException
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.argi1 = i;
            someargs.arg1 = videoprofile;
            someargs.arg2 = videoprofile1;
            VideoCallbackServant._2D_get1(VideoCallbackServant.this).obtainMessage(1, someargs).sendToTarget();
        }

        final VideoCallbackServant this$0;

            
            {
                this$0 = VideoCallbackServant.this;
                super();
            }
    }
;
}
