// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;
import android.util.ArraySet;
import android.view.Surface;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.IVideoCallback;
import com.android.internal.telecom.IVideoProvider;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.telecom:
//            Conferenceable, Log, Conference, DisconnectCause, 
//            AudioState, ConnectionService, CallAudioState, StatusHints, 
//            VideoProfile

public abstract class Connection extends Conferenceable
{
    private static class FailureSignalingConnection extends Connection
    {

        public void checkImmutable()
        {
            if(mImmutable)
                throw new UnsupportedOperationException("Connection is immutable");
            else
                return;
        }

        private boolean mImmutable;

        public FailureSignalingConnection(DisconnectCause disconnectcause)
        {
            mImmutable = false;
            setDisconnected(disconnectcause);
            mImmutable = true;
        }
    }

    public static abstract class Listener
    {

        public void onAddressChanged(Connection connection, Uri uri, int i)
        {
        }

        public void onAudioModeIsVoipChanged(Connection connection, boolean flag)
        {
        }

        public void onAudioRouteChanged(Connection connection, int i)
        {
        }

        public void onCallerDisplayNameChanged(Connection connection, String s, int i)
        {
        }

        public void onCdmaConnectionTimeReset(Connection connection)
        {
        }

        public void onConferenceChanged(Connection connection, Conference conference)
        {
        }

        public void onConferenceMergeFailed(Connection connection)
        {
        }

        public void onConferenceParticipantsChanged(Connection connection, List list)
        {
        }

        public void onConferenceStarted()
        {
        }

        public void onConferenceSupportedChanged(Connection connection, boolean flag)
        {
        }

        public void onConferenceablesChanged(Connection connection, List list)
        {
        }

        public void onConnectionCapabilitiesChanged(Connection connection, int i)
        {
        }

        public void onConnectionEvent(Connection connection, String s, Bundle bundle)
        {
        }

        public void onConnectionPropertiesChanged(Connection connection, int i)
        {
        }

        public void onDestroyed(Connection connection)
        {
        }

        public void onDisconnected(Connection connection, DisconnectCause disconnectcause)
        {
        }

        public void onExtrasChanged(Connection connection, Bundle bundle)
        {
        }

        public void onExtrasRemoved(Connection connection, List list)
        {
        }

        public void onPostDialChar(Connection connection, char c)
        {
        }

        public void onPostDialWait(Connection connection, String s)
        {
        }

        public void onRemoteRttRequest(Connection connection)
        {
        }

        public void onRingbackRequested(Connection connection, boolean flag)
        {
        }

        public void onRttInitiationFailure(Connection connection, int i)
        {
        }

        public void onRttInitiationSuccess(Connection connection)
        {
        }

        public void onRttSessionRemotelyTerminated(Connection connection)
        {
        }

        public void onStateChanged(Connection connection, int i)
        {
        }

        public void onStatusHintsChanged(Connection connection, StatusHints statushints)
        {
        }

        public void onSupportedAudioRoutesChanged(Connection connection, int i)
        {
        }

        public void onVideoProviderChanged(Connection connection, VideoProvider videoprovider)
        {
        }

        public void onVideoStateChanged(Connection connection, int i)
        {
        }

        public Listener()
        {
        }
    }

    public static final class RttModifyStatus
    {

        public static final int SESSION_MODIFY_REQUEST_FAIL = 2;
        public static final int SESSION_MODIFY_REQUEST_INVALID = 3;
        public static final int SESSION_MODIFY_REQUEST_REJECTED_BY_REMOTE = 5;
        public static final int SESSION_MODIFY_REQUEST_SUCCESS = 1;
        public static final int SESSION_MODIFY_REQUEST_TIMED_OUT = 4;

        private RttModifyStatus()
        {
        }
    }

    public static final class RttTextStream
    {

        public ParcelFileDescriptor getFdFromInCall()
        {
            return mFdFromInCall;
        }

        public ParcelFileDescriptor getFdToInCall()
        {
            return mFdToInCall;
        }

        public String read()
            throws IOException
        {
            int i = mPipeFromInCall.read(mReadBuffer, 0, 1000);
            if(i < 0)
                return null;
            else
                return new String(mReadBuffer, 0, i);
        }

        public String readImmediately()
            throws IOException
        {
            if(mPipeFromInCall.ready())
                return read();
            else
                return null;
        }

        public void write(String s)
            throws IOException
        {
            mPipeToInCall.write(s);
            mPipeToInCall.flush();
        }

        private static final int READ_BUFFER_SIZE = 1000;
        private final ParcelFileDescriptor mFdFromInCall;
        private final ParcelFileDescriptor mFdToInCall;
        private final InputStreamReader mPipeFromInCall;
        private final OutputStreamWriter mPipeToInCall;
        private char mReadBuffer[];

        public RttTextStream(ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1)
        {
            mReadBuffer = new char[1000];
            mFdFromInCall = parcelfiledescriptor1;
            mFdToInCall = parcelfiledescriptor;
            mPipeFromInCall = new InputStreamReader(new android.os.ParcelFileDescriptor.AutoCloseInputStream(parcelfiledescriptor1));
            mPipeToInCall = new OutputStreamWriter(new android.os.ParcelFileDescriptor.AutoCloseOutputStream(parcelfiledescriptor));
        }
    }

    public static abstract class VideoProvider
    {

        static VideoProviderHandler _2D_get0(VideoProvider videoprovider)
        {
            return videoprovider.mMessageHandler;
        }

        static ConcurrentHashMap _2D_get1(VideoProvider videoprovider)
        {
            return videoprovider.mVideoCallbacks;
        }

        public static String sessionEventToString(int i)
        {
            switch(i)
            {
            default:
                return (new StringBuilder()).append("UNKNOWN ").append(i).toString();

            case 5: // '\005'
                return "CAMERA_FAIL";

            case 6: // '\006'
                return "CAMERA_READY";

            case 1: // '\001'
                return "RX_PAUSE";

            case 2: // '\002'
                return "RX_RESUME";

            case 3: // '\003'
                return "TX_START";

            case 4: // '\004'
                return "TX_STOP";

            case 7: // '\007'
                return "CAMERA_PERMISSION_ERROR";
            }
        }

        public void changeCallDataUsage(long l)
        {
            setCallDataUsage(l);
        }

        public void changeCameraCapabilities(VideoProfile.CameraCapabilities cameracapabilities)
        {
            if(mVideoCallbacks != null)
            {
                for(Iterator iterator = mVideoCallbacks.values().iterator(); iterator.hasNext();)
                {
                    IVideoCallback ivideocallback = (IVideoCallback)iterator.next();
                    try
                    {
                        ivideocallback.changeCameraCapabilities(cameracapabilities);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w(this, "changeCameraCapabilities callback failed", new Object[] {
                            remoteexception
                        });
                    }
                }

            }
        }

        public void changePeerDimensions(int i, int j)
        {
            if(mVideoCallbacks != null)
            {
                for(Iterator iterator = mVideoCallbacks.values().iterator(); iterator.hasNext();)
                {
                    IVideoCallback ivideocallback = (IVideoCallback)iterator.next();
                    try
                    {
                        ivideocallback.changePeerDimensions(i, j);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w(this, "changePeerDimensions callback failed", new Object[] {
                            remoteexception
                        });
                    }
                }

            }
        }

        public void changeVideoQuality(int i)
        {
            if(mVideoCallbacks != null)
            {
                for(Iterator iterator = mVideoCallbacks.values().iterator(); iterator.hasNext();)
                {
                    IVideoCallback ivideocallback = (IVideoCallback)iterator.next();
                    try
                    {
                        ivideocallback.changeVideoQuality(i);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w(this, "changeVideoQuality callback failed", new Object[] {
                            remoteexception
                        });
                    }
                }

            }
        }

        public final IVideoProvider getInterface()
        {
            return mBinder;
        }

        public void handleCallSessionEvent(int i)
        {
            if(mVideoCallbacks != null)
            {
                for(Iterator iterator = mVideoCallbacks.values().iterator(); iterator.hasNext();)
                {
                    IVideoCallback ivideocallback = (IVideoCallback)iterator.next();
                    try
                    {
                        ivideocallback.handleCallSessionEvent(i);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w(this, "handleCallSessionEvent callback failed", new Object[] {
                            remoteexception
                        });
                    }
                }

            }
        }

        public abstract void onRequestCameraCapabilities();

        public abstract void onRequestConnectionDataUsage();

        public abstract void onSendSessionModifyRequest(VideoProfile videoprofile, VideoProfile videoprofile1);

        public abstract void onSendSessionModifyResponse(VideoProfile videoprofile);

        public abstract void onSetCamera(String s);

        public void onSetCamera(String s, String s1, int i, int j, int k)
        {
        }

        public abstract void onSetDeviceOrientation(int i);

        public abstract void onSetDisplaySurface(Surface surface);

        public abstract void onSetPauseImage(Uri uri);

        public abstract void onSetPreviewSurface(Surface surface);

        public abstract void onSetZoom(float f);

        public void receiveSessionModifyRequest(VideoProfile videoprofile)
        {
            if(mVideoCallbacks != null)
            {
                for(Iterator iterator = mVideoCallbacks.values().iterator(); iterator.hasNext();)
                {
                    IVideoCallback ivideocallback = (IVideoCallback)iterator.next();
                    try
                    {
                        ivideocallback.receiveSessionModifyRequest(videoprofile);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w(this, "receiveSessionModifyRequest callback failed", new Object[] {
                            remoteexception
                        });
                    }
                }

            }
        }

        public void receiveSessionModifyResponse(int i, VideoProfile videoprofile, VideoProfile videoprofile1)
        {
            if(mVideoCallbacks != null)
            {
                for(Iterator iterator = mVideoCallbacks.values().iterator(); iterator.hasNext();)
                {
                    IVideoCallback ivideocallback = (IVideoCallback)iterator.next();
                    try
                    {
                        ivideocallback.receiveSessionModifyResponse(i, videoprofile, videoprofile1);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w(this, "receiveSessionModifyResponse callback failed", new Object[] {
                            remoteexception
                        });
                    }
                }

            }
        }

        public void setCallDataUsage(long l)
        {
            if(mVideoCallbacks != null)
            {
                for(Iterator iterator = mVideoCallbacks.values().iterator(); iterator.hasNext();)
                {
                    IVideoCallback ivideocallback = (IVideoCallback)iterator.next();
                    try
                    {
                        ivideocallback.changeCallDataUsage(l);
                    }
                    catch(RemoteException remoteexception)
                    {
                        Log.w(this, "setCallDataUsage callback failed", new Object[] {
                            remoteexception
                        });
                    }
                }

            }
        }

        private static final int MSG_ADD_VIDEO_CALLBACK = 1;
        private static final int MSG_REMOVE_VIDEO_CALLBACK = 12;
        private static final int MSG_REQUEST_CAMERA_CAPABILITIES = 9;
        private static final int MSG_REQUEST_CONNECTION_DATA_USAGE = 10;
        private static final int MSG_SEND_SESSION_MODIFY_REQUEST = 7;
        private static final int MSG_SEND_SESSION_MODIFY_RESPONSE = 8;
        private static final int MSG_SET_CAMERA = 2;
        private static final int MSG_SET_DEVICE_ORIENTATION = 5;
        private static final int MSG_SET_DISPLAY_SURFACE = 4;
        private static final int MSG_SET_PAUSE_IMAGE = 11;
        private static final int MSG_SET_PREVIEW_SURFACE = 3;
        private static final int MSG_SET_ZOOM = 6;
        public static final int SESSION_EVENT_CAMERA_FAILURE = 5;
        private static final String SESSION_EVENT_CAMERA_FAILURE_STR = "CAMERA_FAIL";
        public static final int SESSION_EVENT_CAMERA_PERMISSION_ERROR = 7;
        private static final String SESSION_EVENT_CAMERA_PERMISSION_ERROR_STR = "CAMERA_PERMISSION_ERROR";
        public static final int SESSION_EVENT_CAMERA_READY = 6;
        private static final String SESSION_EVENT_CAMERA_READY_STR = "CAMERA_READY";
        public static final int SESSION_EVENT_RX_PAUSE = 1;
        private static final String SESSION_EVENT_RX_PAUSE_STR = "RX_PAUSE";
        public static final int SESSION_EVENT_RX_RESUME = 2;
        private static final String SESSION_EVENT_RX_RESUME_STR = "RX_RESUME";
        public static final int SESSION_EVENT_TX_START = 3;
        private static final String SESSION_EVENT_TX_START_STR = "TX_START";
        public static final int SESSION_EVENT_TX_STOP = 4;
        private static final String SESSION_EVENT_TX_STOP_STR = "TX_STOP";
        private static final String SESSION_EVENT_UNKNOWN_STR = "UNKNOWN";
        public static final int SESSION_MODIFY_REQUEST_FAIL = 2;
        public static final int SESSION_MODIFY_REQUEST_INVALID = 3;
        public static final int SESSION_MODIFY_REQUEST_REJECTED_BY_REMOTE = 5;
        public static final int SESSION_MODIFY_REQUEST_SUCCESS = 1;
        public static final int SESSION_MODIFY_REQUEST_TIMED_OUT = 4;
        private final VideoProviderBinder mBinder;
        private VideoProviderHandler mMessageHandler;
        private ConcurrentHashMap mVideoCallbacks;

        public VideoProvider()
        {
            mVideoCallbacks = new ConcurrentHashMap(8, 0.9F, 1);
            mBinder = new VideoProviderBinder(null);
            mMessageHandler = new VideoProviderHandler(Looper.getMainLooper());
        }

        public VideoProvider(Looper looper)
        {
            mVideoCallbacks = new ConcurrentHashMap(8, 0.9F, 1);
            mBinder = new VideoProviderBinder(null);
            mMessageHandler = new VideoProviderHandler(looper);
        }
    }

    private final class VideoProvider.VideoProviderBinder extends com.android.internal.telecom.IVideoProvider.Stub
    {

        public void addVideoCallback(IBinder ibinder)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(1, ibinder).sendToTarget();
        }

        public void removeVideoCallback(IBinder ibinder)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(12, ibinder).sendToTarget();
        }

        public void requestCallDataUsage()
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(10).sendToTarget();
        }

        public void requestCameraCapabilities()
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(9).sendToTarget();
        }

        public void sendSessionModifyRequest(VideoProfile videoprofile, VideoProfile videoprofile1)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = videoprofile;
            someargs.arg2 = videoprofile1;
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(7, someargs).sendToTarget();
        }

        public void sendSessionModifyResponse(VideoProfile videoprofile)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(8, videoprofile).sendToTarget();
        }

        public void setCamera(String s, String s1, int i)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.arg2 = s1;
            someargs.argi1 = Binder.getCallingUid();
            someargs.argi2 = Binder.getCallingPid();
            someargs.argi3 = i;
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(2, someargs).sendToTarget();
        }

        public void setDeviceOrientation(int i)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(5, i, 0).sendToTarget();
        }

        public void setDisplaySurface(Surface surface)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(4, surface).sendToTarget();
        }

        public void setPauseImage(Uri uri)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(11, uri).sendToTarget();
        }

        public void setPreviewSurface(Surface surface)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(3, surface).sendToTarget();
        }

        public void setZoom(float f)
        {
            VideoProvider._2D_get0(VideoProvider.this).obtainMessage(6, Float.valueOf(f)).sendToTarget();
        }

        final VideoProvider this$1;

        private VideoProvider.VideoProviderBinder()
        {
            this$1 = VideoProvider.this;
            super();
        }

        VideoProvider.VideoProviderBinder(VideoProvider.VideoProviderBinder videoproviderbinder)
        {
            this();
        }
    }

    private final class VideoProvider.VideoProviderHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 12: default 68
        //                       1 69
        //                       2 209
        //                       3 278
        //                       4 295
        //                       5 312
        //                       6 326
        //                       7 346
        //                       8 389
        //                       9 406
        //                       10 416
        //                       11 426
        //                       12 148;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
            return;
_L2:
            IBinder ibinder = (IBinder)message.obj;
            message = com.android.internal.telecom.IVideoCallback.Stub.asInterface((IBinder)message.obj);
            if(message == null)
                Log.w(this, "addVideoProvider - skipped; callback is null.", new Object[0]);
            else
            if(VideoProvider._2D_get1(VideoProvider.this).containsKey(ibinder))
                Log.i(this, "addVideoProvider - skipped; already present.", new Object[0]);
            else
                VideoProvider._2D_get1(VideoProvider.this).put(ibinder, message);
            continue; /* Loop/switch isn't completed */
_L13:
            IBinder ibinder1 = (IBinder)message.obj;
            com.android.internal.telecom.IVideoCallback.Stub.asInterface((IBinder)message.obj);
            if(!VideoProvider._2D_get1(VideoProvider.this).containsKey(ibinder1))
                Log.i(this, "removeVideoProvider - skipped; not present.", new Object[0]);
            else
                VideoProvider._2D_get1(VideoProvider.this).remove(ibinder1);
            continue; /* Loop/switch isn't completed */
_L3:
            message = (SomeArgs)message.obj;
            onSetCamera((String)((SomeArgs) (message)).arg1);
            onSetCamera((String)((SomeArgs) (message)).arg1, (String)((SomeArgs) (message)).arg2, ((SomeArgs) (message)).argi1, ((SomeArgs) (message)).argi2, ((SomeArgs) (message)).argi3);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            message.recycle();
            throw exception;
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
            SomeArgs someargs = (SomeArgs)message.obj;
            onSendSessionModifyRequest((VideoProfile)someargs.arg1, (VideoProfile)someargs.arg2);
            someargs.recycle();
            continue; /* Loop/switch isn't completed */
            message;
            someargs.recycle();
            throw message;
_L9:
            onSendSessionModifyResponse((VideoProfile)message.obj);
            continue; /* Loop/switch isn't completed */
_L10:
            onRequestCameraCapabilities();
            continue; /* Loop/switch isn't completed */
_L11:
            onRequestConnectionDataUsage();
            continue; /* Loop/switch isn't completed */
_L12:
            onSetPauseImage((Uri)message.obj);
            if(true) goto _L1; else goto _L14
_L14:
        }

        final VideoProvider this$1;

        public VideoProvider.VideoProviderHandler()
        {
            this$1 = VideoProvider.this;
            super();
        }

        public VideoProvider.VideoProviderHandler(Looper looper)
        {
            this$1 = VideoProvider.this;
            super(looper);
        }
    }


    static List _2D_get0(Connection connection)
    {
        return connection.mConferenceables;
    }

    static void _2D_wrap0(Connection connection)
    {
        connection.fireOnConferenceableConnectionsChanged();
    }

    public Connection()
    {
        mUnmodifiableConferenceables = Collections.unmodifiableList(mConferenceables);
        mState = 1;
        mRingbackRequested = false;
        mSupportedAudioRoutes = 15;
        mConnectTimeMillis = 0L;
        mConnectElapsedTimeMillis = 0L;
    }

    public static boolean can(int i, int j)
    {
        boolean flag;
        if((i & j) == j)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String capabilitiesToString(int i)
    {
        return capabilitiesToStringInternal(i, true);
    }

    private static String capabilitiesToStringInternal(int i, boolean flag)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        if(flag)
            stringbuilder.append("Capabilities:");
        String s;
        if(can(i, 1))
        {
            if(flag)
                s = " CAPABILITY_HOLD";
            else
                s = " hld";
            stringbuilder.append(s);
        }
        if(can(i, 2))
        {
            if(flag)
                s = " CAPABILITY_SUPPORT_HOLD";
            else
                s = " sup_hld";
            stringbuilder.append(s);
        }
        if(can(i, 4))
        {
            if(flag)
                s = " CAPABILITY_MERGE_CONFERENCE";
            else
                s = " mrg_cnf";
            stringbuilder.append(s);
        }
        if(can(i, 8))
        {
            if(flag)
                s = " CAPABILITY_SWAP_CONFERENCE";
            else
                s = " swp_cnf";
            stringbuilder.append(s);
        }
        if(can(i, 32))
        {
            if(flag)
                s = " CAPABILITY_RESPOND_VIA_TEXT";
            else
                s = " txt";
            stringbuilder.append(s);
        }
        if(can(i, 64))
        {
            if(flag)
                s = " CAPABILITY_MUTE";
            else
                s = " mut";
            stringbuilder.append(s);
        }
        if(can(i, 128))
        {
            if(flag)
                s = " CAPABILITY_MANAGE_CONFERENCE";
            else
                s = " mng_cnf";
            stringbuilder.append(s);
        }
        if(can(i, 256))
        {
            if(flag)
                s = " CAPABILITY_SUPPORTS_VT_LOCAL_RX";
            else
                s = " VTlrx";
            stringbuilder.append(s);
        }
        if(can(i, 512))
        {
            if(flag)
                s = " CAPABILITY_SUPPORTS_VT_LOCAL_TX";
            else
                s = " VTltx";
            stringbuilder.append(s);
        }
        if(can(i, 768))
        {
            if(flag)
                s = " CAPABILITY_SUPPORTS_VT_LOCAL_BIDIRECTIONAL";
            else
                s = " VTlbi";
            stringbuilder.append(s);
        }
        if(can(i, 1024))
        {
            if(flag)
                s = " CAPABILITY_SUPPORTS_VT_REMOTE_RX";
            else
                s = " VTrrx";
            stringbuilder.append(s);
        }
        if(can(i, 2048))
        {
            if(flag)
                s = " CAPABILITY_SUPPORTS_VT_REMOTE_TX";
            else
                s = " VTrtx";
            stringbuilder.append(s);
        }
        if(can(i, 3072))
        {
            if(flag)
                s = " CAPABILITY_SUPPORTS_VT_REMOTE_BIDIRECTIONAL";
            else
                s = " VTrbi";
            stringbuilder.append(s);
        }
        if(can(i, 0x800000))
        {
            if(flag)
                s = " CAPABILITY_CANNOT_DOWNGRADE_VIDEO_TO_AUDIO";
            else
                s = " !v2a";
            stringbuilder.append(s);
        }
        if(can(i, 0x40000))
        {
            if(flag)
                s = " CAPABILITY_SPEED_UP_MT_AUDIO";
            else
                s = " spd_aud";
            stringbuilder.append(s);
        }
        if(can(i, 0x80000))
        {
            if(flag)
                s = " CAPABILITY_CAN_UPGRADE_TO_VIDEO";
            else
                s = " a2v";
            stringbuilder.append(s);
        }
        if(can(i, 0x100000))
        {
            if(flag)
                s = " CAPABILITY_CAN_PAUSE_VIDEO";
            else
                s = " paus_VT";
            stringbuilder.append(s);
        }
        if(can(i, 0x200000))
        {
            if(flag)
                s = " CAPABILITY_SINGLE_PARTY_CONFERENCE";
            else
                s = " 1p_cnf";
            stringbuilder.append(s);
        }
        if(can(i, 0x400000))
        {
            if(flag)
                s = " CAPABILITY_CAN_SEND_RESPONSE_VIA_CONNECTION";
            else
                s = " rsp_by_con";
            stringbuilder.append(s);
        }
        if(can(i, 0x1000000))
        {
            if(flag)
                s = " CAPABILITY_CAN_PULL_CALL";
            else
                s = " pull";
            stringbuilder.append(s);
        }
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public static String capabilitiesToStringShort(int i)
    {
        return capabilitiesToStringInternal(i, false);
    }

    private final void clearConferenceableList()
    {
        Iterator iterator = mConferenceables.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Conferenceable conferenceable = (Conferenceable)iterator.next();
            if(conferenceable instanceof Connection)
                ((Connection)conferenceable).removeConnectionListener(mConnectionDeathListener);
            else
            if(conferenceable instanceof Conference)
                ((Conference)conferenceable).removeListener(mConferenceDeathListener);
        } while(true);
        mConferenceables.clear();
    }

    public static Connection createCanceledConnection()
    {
        return new FailureSignalingConnection(new DisconnectCause(4));
    }

    public static Connection createFailedConnection(DisconnectCause disconnectcause)
    {
        return new FailureSignalingConnection(disconnectcause);
    }

    private final void fireConferenceChanged()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConferenceChanged(this, mConference));
    }

    private final void fireOnConferenceableConnectionsChanged()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConferenceablesChanged(this, getConferenceables()));
    }

    public static String propertiesToString(int i)
    {
        return propertiesToStringInternal(i, true);
    }

    private static String propertiesToStringInternal(int i, boolean flag)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        if(flag)
            stringbuilder.append("Properties:");
        String s;
        if(can(i, 128))
        {
            if(flag)
                s = " PROPERTY_SELF_MANAGED";
            else
                s = " self_mng";
            stringbuilder.append(s);
        }
        if(can(i, 1))
        {
            if(flag)
                s = " PROPERTY_EMERGENCY_CALLBACK_MODE";
            else
                s = " ecbm";
            stringbuilder.append(s);
        }
        if(can(i, 4))
        {
            if(flag)
                s = " PROPERTY_HIGH_DEF_AUDIO";
            else
                s = " HD";
            stringbuilder.append(s);
        }
        if(can(i, 8))
        {
            if(flag)
                s = " PROPERTY_WIFI";
            else
                s = " wifi";
            stringbuilder.append(s);
        }
        if(can(i, 2))
        {
            if(flag)
                s = " PROPERTY_GENERIC_CONFERENCE";
            else
                s = " gen_conf";
            stringbuilder.append(s);
        }
        if(can(i, 16))
        {
            if(flag)
                s = " PROPERTY_IS_EXTERNAL_CALL";
            else
                s = " xtrnl";
            stringbuilder.append(s);
        }
        if(can(i, 32))
        {
            if(flag)
                s = " PROPERTY_HAS_CDMA_VOICE_PRIVACY";
            else
                s = " priv";
            stringbuilder.append(s);
        }
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public static String propertiesToStringShort(int i)
    {
        return propertiesToStringInternal(i, false);
    }

    private void setState(int i)
    {
        checkImmutable();
        if(mState == 6 && mState != i)
        {
            Log.d(this, "Connection already DISCONNECTED; cannot transition out of this state.", new Object[0]);
            return;
        }
        if(mState != i)
        {
            Log.d(this, "setState: %s", new Object[] {
                stateToString(i)
            });
            mState = i;
            onStateChanged(i);
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onStateChanged(this, i));
        }
    }

    public static String stateToString(int i)
    {
        switch(i)
        {
        default:
            Log.wtf(android/telecom/Connection, "Unknown state %d", new Object[] {
                Integer.valueOf(i)
            });
            return "UNKNOWN";

        case 0: // '\0'
            return "INITIALIZING";

        case 1: // '\001'
            return "NEW";

        case 2: // '\002'
            return "RINGING";

        case 3: // '\003'
            return "DIALING";

        case 7: // '\007'
            return "PULLING_CALL";

        case 4: // '\004'
            return "ACTIVE";

        case 5: // '\005'
            return "HOLDING";

        case 6: // '\006'
            return "DISCONNECTED";
        }
    }

    static String toLogSafePhoneNumber(String s)
    {
        StringBuilder stringbuilder;
        int i;
        if(s == null)
            return "";
        if(PII_DEBUG)
            return s;
        stringbuilder = new StringBuilder();
        i = 0;
_L2:
        char c;
        if(i >= s.length())
            break MISSING_BLOCK_LABEL_80;
        c = s.charAt(i);
        break MISSING_BLOCK_LABEL_40;
        if(c == '-' || c == '@' || c == '.')
            stringbuilder.append(c);
        else
            stringbuilder.append('x');
        i++;
        continue; /* Loop/switch isn't completed */
        return stringbuilder.toString();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void addCapability(int i)
    {
        mConnectionCapabilities = mConnectionCapabilities | i;
    }

    public final Connection addConnectionListener(Listener listener)
    {
        mListeners.add(listener);
        return this;
    }

    public boolean can(int i)
    {
        return can(mConnectionCapabilities, i);
    }

    public void checkImmutable()
    {
    }

    public final void destroy()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onDestroyed(this));
    }

    public final Uri getAddress()
    {
        return mAddress;
    }

    public final int getAddressPresentation()
    {
        return mAddressPresentation;
    }

    public final boolean getAudioModeIsVoip()
    {
        return mAudioModeIsVoip;
    }

    public final AudioState getAudioState()
    {
        if(mCallAudioState == null)
            return null;
        else
            return new AudioState(mCallAudioState);
    }

    public final CallAudioState getCallAudioState()
    {
        return mCallAudioState;
    }

    public final String getCallerDisplayName()
    {
        return mCallerDisplayName;
    }

    public final int getCallerDisplayNamePresentation()
    {
        return mCallerDisplayNamePresentation;
    }

    public final Conference getConference()
    {
        return mConference;
    }

    public final List getConferenceables()
    {
        return mUnmodifiableConferenceables;
    }

    public final long getConnectElapsedTimeMillis()
    {
        return mConnectElapsedTimeMillis;
    }

    public final long getConnectTimeMillis()
    {
        return mConnectTimeMillis;
    }

    public final int getConnectionCapabilities()
    {
        return mConnectionCapabilities;
    }

    public final int getConnectionProperties()
    {
        return mConnectionProperties;
    }

    public final ConnectionService getConnectionService()
    {
        return mConnectionService;
    }

    public final DisconnectCause getDisconnectCause()
    {
        return mDisconnectCause;
    }

    public final Bundle getExtras()
    {
        Bundle bundle = null;
        Object obj = mExtrasLock;
        obj;
        JVM INSTR monitorenter ;
        if(mExtras != null)
            bundle = new Bundle(mExtras);
        obj;
        JVM INSTR monitorexit ;
        return bundle;
        Exception exception;
        exception;
        throw exception;
    }

    public final int getState()
    {
        return mState;
    }

    public final StatusHints getStatusHints()
    {
        return mStatusHints;
    }

    public final int getSupportedAudioRoutes()
    {
        return mSupportedAudioRoutes;
    }

    public final String getTelecomCallId()
    {
        return mTelecomCallId;
    }

    public final VideoProvider getVideoProvider()
    {
        return mVideoProvider;
    }

    public final int getVideoState()
    {
        return mVideoState;
    }

    final void handleExtrasChanged(Bundle bundle)
    {
        Object obj = null;
        Object obj1 = mExtrasLock;
        obj1;
        JVM INSTR monitorenter ;
        mExtras = bundle;
        bundle = obj;
        if(mExtras != null)
            bundle = new Bundle(mExtras);
        obj1;
        JVM INSTR monitorexit ;
        onExtrasChanged(bundle);
        return;
        bundle;
        throw bundle;
    }

    public void handleRttUpgradeResponse(RttTextStream rtttextstream)
    {
    }

    public final boolean isRingbackRequested()
    {
        return mRingbackRequested;
    }

    void lambda$_2D_android_telecom_Connection_103878(Listener listener)
    {
        listener.onRttInitiationSuccess(this);
    }

    void lambda$_2D_android_telecom_Connection_104487(int i, Listener listener)
    {
        listener.onRttInitiationFailure(this, i);
    }

    void lambda$_2D_android_telecom_Connection_104801(Listener listener)
    {
        listener.onRttSessionRemotelyTerminated(this);
    }

    void lambda$_2D_android_telecom_Connection_105112(Listener listener)
    {
        listener.onRemoteRttRequest(this);
    }

    protected final void notifyConferenceMergeFailed()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConferenceMergeFailed(this));
    }

    protected void notifyConferenceStarted()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConferenceStarted());
    }

    protected void notifyConferenceSupportedChanged(boolean flag)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConferenceSupportedChanged(this, flag));
    }

    public void onAbort()
    {
    }

    public void onAnswer()
    {
        onAnswer(0);
    }

    public void onAnswer(int i)
    {
    }

    public void onAudioStateChanged(AudioState audiostate)
    {
    }

    public void onCallAudioStateChanged(CallAudioState callaudiostate)
    {
    }

    public void onCallEvent(String s, Bundle bundle)
    {
    }

    public void onDisconnect()
    {
    }

    public void onDisconnectConferenceParticipant(Uri uri)
    {
    }

    public void onExtrasChanged(Bundle bundle)
    {
    }

    public void onHold()
    {
    }

    public void onPlayDtmfTone(char c)
    {
    }

    public void onPostDialContinue(boolean flag)
    {
    }

    public void onPullExternalCall()
    {
    }

    public void onReject()
    {
    }

    public void onReject(String s)
    {
    }

    public void onSeparate()
    {
    }

    public void onShowIncomingCallUi()
    {
    }

    public void onSilence()
    {
    }

    public void onStartRtt(RttTextStream rtttextstream)
    {
    }

    public void onStateChanged(int i)
    {
    }

    public void onStopDtmfTone()
    {
    }

    public void onStopRtt()
    {
    }

    public void onUnhold()
    {
    }

    public final void putExtra(String s, int i)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(s, i);
        putExtras(bundle);
    }

    public final void putExtra(String s, String s1)
    {
        Bundle bundle = new Bundle();
        bundle.putString(s, s1);
        putExtras(bundle);
    }

    public final void putExtra(String s, boolean flag)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean(s, flag);
        putExtras(bundle);
    }

    public final void putExtras(Bundle bundle)
    {
        checkImmutable();
        if(bundle == null)
            return;
        Object obj = mExtrasLock;
        obj;
        JVM INSTR monitorenter ;
        if(mExtras == null)
        {
            Bundle bundle1 = JVM INSTR new #612 <Class Bundle>;
            bundle1.Bundle();
            mExtras = bundle1;
        }
        mExtras.putAll(bundle);
        bundle = new Bundle(mExtras);
        obj;
        JVM INSTR monitorexit ;
        for(obj = mListeners.iterator(); ((Iterator) (obj)).hasNext(); ((Listener)((Iterator) (obj)).next()).onExtrasChanged(this, new Bundle(bundle)));
        break MISSING_BLOCK_LABEL_106;
        bundle;
        throw bundle;
    }

    public void removeCapability(int i)
    {
        mConnectionCapabilities = mConnectionCapabilities & i;
    }

    public final Connection removeConnectionListener(Listener listener)
    {
        if(listener != null)
            mListeners.remove(listener);
        return this;
    }

    public final void removeExtras(List list)
    {
        Object obj = mExtrasLock;
        obj;
        JVM INSTR monitorenter ;
        if(mExtras != null)
        {
            String s;
            for(Iterator iterator1 = list.iterator(); iterator1.hasNext(); mExtras.remove(s))
                s = (String)iterator1.next();

        }
        break MISSING_BLOCK_LABEL_58;
        list;
        throw list;
        obj;
        JVM INSTR monitorexit ;
        list = Collections.unmodifiableList(list);
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onExtrasRemoved(this, list));
        return;
    }

    public final transient void removeExtras(String as[])
    {
        removeExtras(Arrays.asList(as));
    }

    public final void resetCdmaConnectionTime()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onCdmaConnectionTimeReset(this));
    }

    public final void resetConference()
    {
        if(mConference != null)
        {
            Log.d(this, "Conference reset", new Object[0]);
            mConference = null;
            fireConferenceChanged();
        }
    }

    public void sendConnectionEvent(String s, Bundle bundle)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConnectionEvent(this, s, bundle));
    }

    public final void sendRemoteRttRequest()
    {
        mListeners.forEach(new _.Lambda._cls4SVh5muPQdDUeBsBoEG9OejHF_s((byte)0, this));
    }

    public final void sendRttInitiationFailure(int i)
    {
        unsetRttProperty();
        mListeners.forEach(new _.Lambda._cls4SVh5muPQdDUeBsBoEG9OejHF_s._cls1(i, this));
    }

    public final void sendRttInitiationSuccess()
    {
        setRttProperty();
        mListeners.forEach(new _.Lambda._cls4SVh5muPQdDUeBsBoEG9OejHF_s((byte)1, this));
    }

    public final void sendRttSessionRemotelyTerminated()
    {
        mListeners.forEach(new _.Lambda._cls4SVh5muPQdDUeBsBoEG9OejHF_s((byte)2, this));
    }

    public final void setActive()
    {
        checkImmutable();
        setRingbackRequested(false);
        setState(4);
    }

    public final void setAddress(Uri uri, int i)
    {
        checkImmutable();
        Log.d(this, "setAddress %s", new Object[] {
            uri
        });
        mAddress = uri;
        mAddressPresentation = i;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onAddressChanged(this, uri, i));
    }

    public final void setAudioModeIsVoip(boolean flag)
    {
        checkImmutable();
        mAudioModeIsVoip = flag;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onAudioModeIsVoipChanged(this, flag));
    }

    public final void setAudioRoute(int i)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onAudioRouteChanged(this, i));
    }

    final void setCallAudioState(CallAudioState callaudiostate)
    {
        checkImmutable();
        Log.d(this, "setAudioState %s", new Object[] {
            callaudiostate
        });
        mCallAudioState = callaudiostate;
        onAudioStateChanged(getAudioState());
        onCallAudioStateChanged(callaudiostate);
    }

    public final void setCallerDisplayName(String s, int i)
    {
        checkImmutable();
        Log.d(this, "setCallerDisplayName %s", new Object[] {
            s
        });
        mCallerDisplayName = s;
        mCallerDisplayNamePresentation = i;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onCallerDisplayNameChanged(this, s, i));
    }

    public final boolean setConference(Conference conference)
    {
        checkImmutable();
        if(mConference == null)
        {
            mConference = conference;
            if(mConnectionService != null && mConnectionService.containsConference(conference))
                fireConferenceChanged();
            return true;
        } else
        {
            return false;
        }
    }

    public final void setConferenceableConnections(List list)
    {
        checkImmutable();
        clearConferenceableList();
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            Connection connection = (Connection)list.next();
            if(!mConferenceables.contains(connection))
            {
                connection.addConnectionListener(mConnectionDeathListener);
                mConferenceables.add(connection);
            }
        } while(true);
        fireOnConferenceableConnectionsChanged();
    }

    public final void setConferenceables(List list)
    {
        clearConferenceableList();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            list = (Conferenceable)iterator.next();
            if(!mConferenceables.contains(list))
            {
                if(list instanceof Connection)
                    ((Connection)list).addConnectionListener(mConnectionDeathListener);
                else
                if(list instanceof Conference)
                    ((Conference)list).addListener(mConferenceDeathListener);
                mConferenceables.add(list);
            }
        }

        fireOnConferenceableConnectionsChanged();
    }

    public final void setConnectElapsedTimeMillis(long l)
    {
        mConnectElapsedTimeMillis = l;
    }

    public final void setConnectTimeMillis(long l)
    {
        mConnectTimeMillis = l;
    }

    public final void setConnectionCapabilities(int i)
    {
        checkImmutable();
        if(mConnectionCapabilities != i)
        {
            mConnectionCapabilities = i;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConnectionCapabilitiesChanged(this, mConnectionCapabilities));
        }
    }

    public final void setConnectionProperties(int i)
    {
        checkImmutable();
        if(mConnectionProperties != i)
        {
            mConnectionProperties = i;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConnectionPropertiesChanged(this, mConnectionProperties));
        }
    }

    public final void setConnectionService(ConnectionService connectionservice)
    {
        checkImmutable();
        if(mConnectionService != null)
            Log.e(this, new Exception(), "Trying to set ConnectionService on a connection which is already associated with another ConnectionService.", new Object[0]);
        else
            mConnectionService = connectionservice;
    }

    public final void setDialing()
    {
        checkImmutable();
        setState(3);
    }

    public final void setDisconnected(DisconnectCause disconnectcause)
    {
        checkImmutable();
        mDisconnectCause = disconnectcause;
        setState(6);
        Log.d(this, "Disconnected with cause %s", new Object[] {
            disconnectcause
        });
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onDisconnected(this, disconnectcause));
    }

    public final void setExtras(Bundle bundle)
    {
        checkImmutable();
        putExtras(bundle);
        if(mPreviousExtraKeys != null)
        {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = mPreviousExtraKeys.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s = (String)iterator.next();
                if(bundle == null || bundle.containsKey(s) ^ true)
                    arraylist.add(s);
            } while(true);
            if(!arraylist.isEmpty())
                removeExtras(arraylist);
        }
        if(mPreviousExtraKeys == null)
            mPreviousExtraKeys = new ArraySet();
        mPreviousExtraKeys.clear();
        if(bundle != null)
            mPreviousExtraKeys.addAll(bundle.keySet());
    }

    public final void setInitialized()
    {
        checkImmutable();
        setState(1);
    }

    public final void setInitializing()
    {
        checkImmutable();
        setState(0);
    }

    public final void setNextPostDialChar(char c)
    {
        checkImmutable();
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onPostDialChar(this, c));
    }

    public final void setOnHold()
    {
        checkImmutable();
        setState(5);
    }

    public final void setPostDialWait(String s)
    {
        checkImmutable();
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onPostDialWait(this, s));
    }

    public final void setPulling()
    {
        checkImmutable();
        setState(7);
    }

    public final void setRingbackRequested(boolean flag)
    {
        checkImmutable();
        if(mRingbackRequested != flag)
        {
            mRingbackRequested = flag;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onRingbackRequested(this, flag));
        }
    }

    public final void setRinging()
    {
        checkImmutable();
        setState(2);
    }

    void setRttProperty()
    {
        setConnectionProperties(getConnectionProperties() | 0x100);
    }

    public final void setStatusHints(StatusHints statushints)
    {
        checkImmutable();
        mStatusHints = statushints;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onStatusHintsChanged(this, statushints));
    }

    public final void setSupportedAudioRoutes(int i)
    {
        if((i & 9) == 0)
            throw new IllegalArgumentException("supported audio routes must include either speaker or earpiece");
        if(mSupportedAudioRoutes != i)
        {
            mSupportedAudioRoutes = i;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onSupportedAudioRoutesChanged(this, mSupportedAudioRoutes));
        }
    }

    public void setTelecomCallId(String s)
    {
        mTelecomCallId = s;
    }

    public final void setVideoProvider(VideoProvider videoprovider)
    {
        checkImmutable();
        mVideoProvider = videoprovider;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onVideoProviderChanged(this, videoprovider));
    }

    public final void setVideoState(int i)
    {
        checkImmutable();
        Log.d(this, "setVideoState %d", new Object[] {
            Integer.valueOf(i)
        });
        mVideoState = i;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onVideoStateChanged(this, mVideoState));
    }

    public final void unsetConnectionService(ConnectionService connectionservice)
    {
        if(mConnectionService != connectionservice)
            Log.e(this, new Exception(), "Trying to remove ConnectionService from a Connection that does not belong to the ConnectionService.", new Object[0]);
        else
            mConnectionService = null;
    }

    void unsetRttProperty()
    {
        setConnectionProperties(getConnectionProperties() & 0xfffffeff);
    }

    protected final void updateConferenceParticipants(List list)
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConferenceParticipantsChanged(this, list));
    }

    public static final int CAPABILITY_ADD_PARTICIPANT = 0x2000000;
    public static final int CAPABILITY_CANNOT_DOWNGRADE_VIDEO_TO_AUDIO = 0x800000;
    public static final int CAPABILITY_CAN_PAUSE_VIDEO = 0x100000;
    public static final int CAPABILITY_CAN_PULL_CALL = 0x1000000;
    public static final int CAPABILITY_CAN_SEND_RESPONSE_VIA_CONNECTION = 0x400000;
    public static final int CAPABILITY_CAN_UPGRADE_TO_VIDEO = 0x80000;
    public static final int CAPABILITY_CONFERENCE_HAS_NO_CHILDREN = 0x200000;
    public static final int CAPABILITY_DISCONNECT_FROM_CONFERENCE = 8192;
    public static final int CAPABILITY_HOLD = 1;
    public static final int CAPABILITY_MANAGE_CONFERENCE = 128;
    public static final int CAPABILITY_MERGE_CONFERENCE = 4;
    public static final int CAPABILITY_MUTE = 64;
    public static final int CAPABILITY_RESPOND_VIA_TEXT = 32;
    public static final int CAPABILITY_SEPARATE_FROM_CONFERENCE = 4096;
    public static final int CAPABILITY_SPEED_UP_MT_AUDIO = 0x40000;
    public static final int CAPABILITY_SUPPORTS_VT_LOCAL_BIDIRECTIONAL = 768;
    public static final int CAPABILITY_SUPPORTS_VT_LOCAL_RX = 256;
    public static final int CAPABILITY_SUPPORTS_VT_LOCAL_TX = 512;
    public static final int CAPABILITY_SUPPORTS_VT_REMOTE_BIDIRECTIONAL = 3072;
    public static final int CAPABILITY_SUPPORTS_VT_REMOTE_RX = 1024;
    public static final int CAPABILITY_SUPPORTS_VT_REMOTE_TX = 2048;
    public static final int CAPABILITY_SUPPORT_HOLD = 2;
    public static final int CAPABILITY_SWAP_CONFERENCE = 8;
    public static final int CAPABILITY_UNUSED = 16;
    public static final int CAPABILITY_UNUSED_2 = 16384;
    public static final int CAPABILITY_UNUSED_3 = 32768;
    public static final int CAPABILITY_UNUSED_4 = 0x10000;
    public static final int CAPABILITY_UNUSED_5 = 0x20000;
    public static final String EVENT_CALL_MERGE_FAILED = "android.telecom.event.CALL_MERGE_FAILED";
    public static final String EVENT_CALL_PULL_FAILED = "android.telecom.event.CALL_PULL_FAILED";
    public static final String EVENT_CALL_REMOTELY_HELD = "android.telecom.event.CALL_REMOTELY_HELD";
    public static final String EVENT_CALL_REMOTELY_UNHELD = "android.telecom.event.CALL_REMOTELY_UNHELD";
    public static final String EVENT_HANDOVER_COMPLETE = "android.telecom.event.HANDOVER_COMPLETE";
    public static final String EVENT_HANDOVER_FAILED = "android.telecom.event.HANDOVER_FAILED";
    public static final String EVENT_MERGE_COMPLETE = "android.telecom.event.MERGE_COMPLETE";
    public static final String EVENT_MERGE_START = "android.telecom.event.MERGE_START";
    public static final String EVENT_ON_HOLD_TONE_END = "android.telecom.event.ON_HOLD_TONE_END";
    public static final String EVENT_ON_HOLD_TONE_START = "android.telecom.event.ON_HOLD_TONE_START";
    public static final String EXTRA_ANSWERING_DROPS_FG_CALL = "android.telecom.extra.ANSWERING_DROPS_FG_CALL";
    public static final String EXTRA_ANSWERING_DROPS_FG_CALL_APP_NAME = "android.telecom.extra.ANSWERING_DROPS_FG_CALL_APP_NAME";
    public static final String EXTRA_CALL_SUBJECT = "android.telecom.extra.CALL_SUBJECT";
    public static final String EXTRA_CHILD_ADDRESS = "android.telecom.extra.CHILD_ADDRESS";
    public static final String EXTRA_DISABLE_ADD_CALL = "android.telecom.extra.DISABLE_ADD_CALL";
    public static final String EXTRA_LAST_FORWARDED_NUMBER = "android.telecom.extra.LAST_FORWARDED_NUMBER";
    public static final String EXTRA_ORIGINAL_CONNECTION_ID = "android.telecom.extra.ORIGINAL_CONNECTION_ID";
    private static final boolean PII_DEBUG = Log.isLoggable(3);
    public static final int PROPERTY_EMERGENCY_CALLBACK_MODE = 1;
    public static final int PROPERTY_GENERIC_CONFERENCE = 2;
    public static final int PROPERTY_HAS_CDMA_VOICE_PRIVACY = 32;
    public static final int PROPERTY_HIGH_DEF_AUDIO = 4;
    public static final int PROPERTY_IS_DOWNGRADED_CONFERENCE = 64;
    public static final int PROPERTY_IS_EXTERNAL_CALL = 16;
    public static final int PROPERTY_IS_RTT = 256;
    public static final int PROPERTY_SELF_MANAGED = 128;
    public static final int PROPERTY_WIFI = 8;
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_DIALING = 3;
    public static final int STATE_DISCONNECTED = 6;
    public static final int STATE_HOLDING = 5;
    public static final int STATE_INITIALIZING = 0;
    public static final int STATE_NEW = 1;
    public static final int STATE_PULLING_CALL = 7;
    public static final int STATE_RINGING = 2;
    private Uri mAddress;
    private int mAddressPresentation;
    private boolean mAudioModeIsVoip;
    private CallAudioState mCallAudioState;
    private String mCallerDisplayName;
    private int mCallerDisplayNamePresentation;
    private Conference mConference;
    private final Conference.Listener mConferenceDeathListener = new Conference.Listener() {

        public void onDestroyed(Conference conference)
        {
            if(Connection._2D_get0(Connection.this).remove(conference))
                Connection._2D_wrap0(Connection.this);
        }

        final Connection this$0;

            
            {
                this$0 = Connection.this;
                super();
            }
    }
;
    private final List mConferenceables = new ArrayList();
    private long mConnectElapsedTimeMillis;
    private long mConnectTimeMillis;
    private int mConnectionCapabilities;
    private final Listener mConnectionDeathListener = new Listener() {

        public void onDestroyed(Connection connection)
        {
            if(Connection._2D_get0(Connection.this).remove(connection))
                Connection._2D_wrap0(Connection.this);
        }

        final Connection this$0;

            
            {
                this$0 = Connection.this;
                super();
            }
    }
;
    private int mConnectionProperties;
    private ConnectionService mConnectionService;
    private DisconnectCause mDisconnectCause;
    private Bundle mExtras;
    private final Object mExtrasLock = new Object();
    private final Set mListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9F, 1));
    private Set mPreviousExtraKeys;
    private boolean mRingbackRequested;
    private int mState;
    private StatusHints mStatusHints;
    private int mSupportedAudioRoutes;
    private String mTelecomCallId;
    private final List mUnmodifiableConferenceables;
    private VideoProvider mVideoProvider;
    private int mVideoState;

}
