// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.*;
import java.util.List;

// Referenced classes of package android.telecom:
//            ConnectionRequest, ParcelableConnection, DisconnectCause, ParcelableConference, 
//            StatusHints

final class ConnectionServiceAdapterServant
{

    static IConnectionServiceAdapter _2D_get0(ConnectionServiceAdapterServant connectionserviceadapterservant)
    {
        return connectionserviceadapterservant.mDelegate;
    }

    static Handler _2D_get1(ConnectionServiceAdapterServant connectionserviceadapterservant)
    {
        return connectionserviceadapterservant.mHandler;
    }

    public ConnectionServiceAdapterServant(IConnectionServiceAdapter iconnectionserviceadapter)
    {
        mDelegate = iconnectionserviceadapter;
    }

    public IConnectionServiceAdapter getStub()
    {
        return mStub;
    }

    private static final int MSG_ADD_CONFERENCE_CALL = 10;
    private static final int MSG_ADD_EXISTING_CONNECTION = 21;
    private static final int MSG_HANDLE_CREATE_CONNECTION_COMPLETE = 1;
    private static final int MSG_ON_CONNECTION_EVENT = 26;
    private static final int MSG_ON_POST_DIAL_CHAR = 22;
    private static final int MSG_ON_POST_DIAL_WAIT = 12;
    private static final int MSG_ON_RTT_INITIATION_FAILURE = 31;
    private static final int MSG_ON_RTT_INITIATION_SUCCESS = 30;
    private static final int MSG_ON_RTT_REMOTELY_TERMINATED = 32;
    private static final int MSG_ON_RTT_UPGRADE_REQUEST = 33;
    private static final int MSG_PUT_EXTRAS = 24;
    private static final int MSG_QUERY_REMOTE_CALL_SERVICES = 13;
    private static final int MSG_REMOVE_CALL = 11;
    private static final int MSG_REMOVE_EXTRAS = 25;
    private static final int MSG_SET_ACTIVE = 2;
    private static final int MSG_SET_ADDRESS = 18;
    private static final int MSG_SET_AUDIO_ROUTE = 29;
    private static final int MSG_SET_CALLER_DISPLAY_NAME = 19;
    private static final int MSG_SET_CONFERENCEABLE_CONNECTIONS = 20;
    private static final int MSG_SET_CONFERENCE_MERGE_FAILED = 23;
    private static final int MSG_SET_CONNECTION_CAPABILITIES = 8;
    private static final int MSG_SET_CONNECTION_PROPERTIES = 27;
    private static final int MSG_SET_DIALING = 4;
    private static final int MSG_SET_DISCONNECTED = 5;
    private static final int MSG_SET_IS_CONFERENCED = 9;
    private static final int MSG_SET_IS_VOIP_AUDIO_MODE = 16;
    private static final int MSG_SET_ON_HOLD = 6;
    private static final int MSG_SET_PULLING = 28;
    private static final int MSG_SET_RINGBACK_REQUESTED = 7;
    private static final int MSG_SET_RINGING = 3;
    private static final int MSG_SET_STATUS_HINTS = 17;
    private static final int MSG_SET_VIDEO_CALL_PROVIDER = 15;
    private static final int MSG_SET_VIDEO_STATE = 14;
    private final IConnectionServiceAdapter mDelegate;
    private final Handler mHandler = new Handler() {

        private void internalHandleMessage(Message message)
            throws RemoteException
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = true;
            message.what;
            JVM INSTR tableswitch 1 33: default 156
        //                       1 157
        //                       2 215
        //                       3 238
        //                       4 261
        //                       5 307
        //                       6 361
        //                       7 384
        //                       8 429
        //                       9 483
        //                       10 534
        //                       11 588
        //                       12 611
        //                       13 714
        //                       14 737
        //                       15 764
        //                       16 815
        //                       17 862
        //                       18 913
        //                       19 968
        //                       20 1027
        //                       21 1078
        //                       22 665
        //                       23 1132
        //                       24 1176
        //                       25 1230
        //                       26 1284
        //                       27 456
        //                       28 284
        //                       29 1346
        //                       30 1400
        //                       31 1423
        //                       32 1450
        //                       33 1473;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34
_L1:
            return;
_L2:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).handleCreateConnectionComplete((String)((SomeArgs) (message)).arg1, (ConnectionRequest)((SomeArgs) (message)).arg2, (ParcelableConnection)((SomeArgs) (message)).arg3, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            message.recycle();
            throw exception;
_L3:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setActive((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L4:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setRinging((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L5:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setDialing((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L29:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setPulling((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L6:
            Object obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setDisconnected((String)((SomeArgs) (obj)).arg1, (DisconnectCause)((SomeArgs) (obj)).arg2, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L7:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setOnHold((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L8:
            obj = ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this);
            String s = (String)message.obj;
            if(message.arg1 != 1)
                flag1 = false;
            ((IConnectionServiceAdapter) (obj)).setRingbackRequested(s, flag1, null);
            continue; /* Loop/switch isn't completed */
_L9:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setConnectionCapabilities((String)message.obj, message.arg1, null);
            continue; /* Loop/switch isn't completed */
_L28:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setConnectionProperties((String)message.obj, message.arg1, null);
            continue; /* Loop/switch isn't completed */
_L10:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setIsConferenced((String)((SomeArgs) (message)).arg1, (String)((SomeArgs) (message)).arg2, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L11:
            obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).addConferenceCall((String)((SomeArgs) (obj)).arg1, (ParcelableConference)((SomeArgs) (obj)).arg2, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L12:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).removeCall((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L13:
            obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).onPostDialWait((String)((SomeArgs) (obj)).arg1, (String)((SomeArgs) (obj)).arg2, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L23:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).onPostDialChar((String)((SomeArgs) (message)).arg1, (char)((SomeArgs) (message)).argi1, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L14:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).queryRemoteConnectionServices((RemoteServiceCallback)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L15:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setVideoState((String)message.obj, message.arg1, null);
            continue; /* Loop/switch isn't completed */
_L16:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setVideoProvider((String)((SomeArgs) (message)).arg1, (IVideoProvider)((SomeArgs) (message)).arg2, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L17:
            IConnectionServiceAdapter iconnectionserviceadapter1 = ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this);
            obj = (String)message.obj;
            boolean flag2;
            if(message.arg1 == 1)
                flag2 = flag;
            else
                flag2 = false;
            iconnectionserviceadapter1.setIsVoipAudioMode(((String) (obj)), flag2, null);
            continue; /* Loop/switch isn't completed */
_L18:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setStatusHints((String)((SomeArgs) (message)).arg1, (StatusHints)((SomeArgs) (message)).arg2, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L19:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setAddress((String)((SomeArgs) (message)).arg1, (Uri)((SomeArgs) (message)).arg2, ((SomeArgs) (message)).argi1, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L20:
            obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setCallerDisplayName((String)((SomeArgs) (obj)).arg1, (String)((SomeArgs) (obj)).arg2, ((SomeArgs) (obj)).argi1, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L21:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setConferenceableConnections((String)((SomeArgs) (message)).arg1, (List)((SomeArgs) (message)).arg2, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L22:
            obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).addExistingConnection((String)((SomeArgs) (obj)).arg1, (ParcelableConnection)((SomeArgs) (obj)).arg2, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L24:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setConferenceMergeFailed((String)((SomeArgs) (message)).arg1, null);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L25:
            obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).putExtras((String)((SomeArgs) (obj)).arg1, (Bundle)((SomeArgs) (obj)).arg2, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L26:
            obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).removeExtras((String)((SomeArgs) (obj)).arg1, (List)((SomeArgs) (obj)).arg2, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L27:
            obj = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).onConnectionEvent((String)((SomeArgs) (obj)).arg1, (String)((SomeArgs) (obj)).arg2, (Bundle)((SomeArgs) (obj)).arg3, null);
            ((SomeArgs) (obj)).recycle();
            continue; /* Loop/switch isn't completed */
            message;
            ((SomeArgs) (obj)).recycle();
            throw message;
_L30:
            message = (SomeArgs)message.obj;
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).setAudioRoute((String)((SomeArgs) (message)).arg1, ((SomeArgs) (message)).argi1, (android.telecom.Logging.Session.Info)((SomeArgs) (message)).arg2);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            obj;
            message.recycle();
            throw obj;
_L31:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).onRttInitiationSuccess((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L32:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).onRttInitiationFailure((String)message.obj, message.arg1, null);
            continue; /* Loop/switch isn't completed */
_L33:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).onRttSessionRemotelyTerminated((String)message.obj, null);
            continue; /* Loop/switch isn't completed */
_L34:
            ConnectionServiceAdapterServant._2D_get0(ConnectionServiceAdapterServant.this).onRemoteRttRequest((String)message.obj, null);
            if(true) goto _L1; else goto _L35
_L35:
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

        final ConnectionServiceAdapterServant this$0;

            
            {
                this$0 = ConnectionServiceAdapterServant.this;
                super();
            }
    }
;
    private final IConnectionServiceAdapter mStub = new com.android.internal.telecom.IConnectionServiceAdapter.Stub() {

        public void addConferenceCall(String s, ParcelableConference parcelableconference, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = parcelableconference;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(10, info).sendToTarget();
        }

        public final void addExistingConnection(String s, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = parcelableconnection;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(21, info).sendToTarget();
        }

        public void handleCreateConnectionComplete(String s, ConnectionRequest connectionrequest, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = connectionrequest;
            info.arg3 = parcelableconnection;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(1, info).sendToTarget();
        }

        public final void onConnectionEvent(String s, String s1, Bundle bundle, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = s1;
            info.arg3 = bundle;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(26, info).sendToTarget();
        }

        public void onPostDialChar(String s, char c, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.argi1 = c;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(22, info).sendToTarget();
        }

        public void onPostDialWait(String s, String s1, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = s1;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(12, info).sendToTarget();
        }

        public void onRemoteRttRequest(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(33, s).sendToTarget();
        }

        public void onRttInitiationFailure(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(31, i, 0, s).sendToTarget();
        }

        public void onRttInitiationSuccess(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(30, s).sendToTarget();
        }

        public void onRttSessionRemotelyTerminated(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(32, s).sendToTarget();
        }

        public final void putExtras(String s, Bundle bundle, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = bundle;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(24, info).sendToTarget();
        }

        public void queryRemoteConnectionServices(RemoteServiceCallback remoteservicecallback, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(13, remoteservicecallback).sendToTarget();
        }

        public void removeCall(String s, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(11, s).sendToTarget();
        }

        public final void removeExtras(String s, List list, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = list;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(25, info).sendToTarget();
        }

        public void resetCdmaConnectionTime(String s, android.telecom.Logging.Session.Info info)
        {
        }

        public void setActive(String s, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(2, s).sendToTarget();
        }

        public final void setAddress(String s, Uri uri, int i, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = uri;
            info.argi1 = i;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(18, info).sendToTarget();
        }

        public final void setAudioRoute(String s, int i, android.telecom.Logging.Session.Info info)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.argi1 = i;
            someargs.arg2 = info;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(29, someargs).sendToTarget();
        }

        public final void setCallerDisplayName(String s, String s1, int i, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = s1;
            info.argi1 = i;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(19, info).sendToTarget();
        }

        public void setConferenceMergeFailed(String s, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(23, info).sendToTarget();
        }

        public final void setConferenceableConnections(String s, List list, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = list;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(20, info).sendToTarget();
        }

        public void setConnectionCapabilities(String s, int i, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(8, i, 0, s).sendToTarget();
        }

        public void setConnectionProperties(String s, int i, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(27, i, 0, s).sendToTarget();
        }

        public void setDialing(String s, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(4, s).sendToTarget();
        }

        public void setDisconnected(String s, DisconnectCause disconnectcause, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = disconnectcause;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(5, info).sendToTarget();
        }

        public void setIsConferenced(String s, String s1, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = s1;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(9, info).sendToTarget();
        }

        public final void setIsVoipAudioMode(String s, boolean flag, android.telecom.Logging.Session.Info info)
        {
            info = ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            info.obtainMessage(16, i, 0, s).sendToTarget();
        }

        public void setOnHold(String s, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(6, s).sendToTarget();
        }

        public void setPulling(String s, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(28, s).sendToTarget();
        }

        public void setRingbackRequested(String s, boolean flag, android.telecom.Logging.Session.Info info)
        {
            info = ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            info.obtainMessage(7, i, 0, s).sendToTarget();
        }

        public void setRinging(String s, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(3, s).sendToTarget();
        }

        public final void setStatusHints(String s, StatusHints statushints, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = statushints;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(17, info).sendToTarget();
        }

        public void setVideoProvider(String s, IVideoProvider ivideoprovider, android.telecom.Logging.Session.Info info)
        {
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = ivideoprovider;
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(15, info).sendToTarget();
        }

        public void setVideoState(String s, int i, android.telecom.Logging.Session.Info info)
        {
            ConnectionServiceAdapterServant._2D_get1(ConnectionServiceAdapterServant.this).obtainMessage(14, i, 0, s).sendToTarget();
        }

        final ConnectionServiceAdapterServant this$0;

            
            {
                this$0 = ConnectionServiceAdapterServant.this;
                super();
            }
    }
;
}
