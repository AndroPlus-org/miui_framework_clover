// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.*;
import com.android.internal.telecom.*;
import java.util.*;

// Referenced classes of package android.telecom:
//            RemoteConnection, ConnectionRequest, RemoteConference, ConnectionServiceAdapterServant, 
//            Log, DisconnectCause, ConnectionService, PhoneAccountHandle, 
//            ParcelableConference, ParcelableConnection, StatusHints

final class RemoteConnectionService
{

    static RemoteConference _2D_get0()
    {
        return NULL_CONFERENCE;
    }

    static RemoteConnection _2D_get1()
    {
        return NULL_CONNECTION;
    }

    static Map _2D_get2(RemoteConnectionService remoteconnectionservice)
    {
        return remoteconnectionservice.mConferenceById;
    }

    static Map _2D_get3(RemoteConnectionService remoteconnectionservice)
    {
        return remoteconnectionservice.mConnectionById;
    }

    static android.os.IBinder.DeathRecipient _2D_get4(RemoteConnectionService remoteconnectionservice)
    {
        return remoteconnectionservice.mDeathRecipient;
    }

    static ConnectionService _2D_get5(RemoteConnectionService remoteconnectionservice)
    {
        return remoteconnectionservice.mOurConnectionServiceImpl;
    }

    static IConnectionService _2D_get6(RemoteConnectionService remoteconnectionservice)
    {
        return remoteconnectionservice.mOutgoingConnectionServiceRpc;
    }

    static Set _2D_get7(RemoteConnectionService remoteconnectionservice)
    {
        return remoteconnectionservice.mPendingConnections;
    }

    static RemoteConference _2D_wrap0(RemoteConnectionService remoteconnectionservice, String s, String s1)
    {
        return remoteconnectionservice.findConferenceForAction(s, s1);
    }

    static RemoteConnection _2D_wrap1(RemoteConnectionService remoteconnectionservice, String s, String s1)
    {
        return remoteconnectionservice.findConnectionForAction(s, s1);
    }

    static boolean _2D_wrap2(RemoteConnectionService remoteconnectionservice, String s)
    {
        return remoteconnectionservice.hasConnection(s);
    }

    static void _2D_wrap3(RemoteConnectionService remoteconnectionservice)
    {
        remoteconnectionservice.maybeDisconnectAdapter();
    }

    RemoteConnectionService(IConnectionService iconnectionservice, ConnectionService connectionservice)
        throws RemoteException
    {
        mServant = new ConnectionServiceAdapterServant(mServantDelegate);
        mOutgoingConnectionServiceRpc = iconnectionservice;
        mOutgoingConnectionServiceRpc.asBinder().linkToDeath(mDeathRecipient, 0);
        mOurConnectionServiceImpl = connectionservice;
    }

    private RemoteConference findConferenceForAction(String s, String s1)
    {
        if(mConferenceById.containsKey(s))
        {
            return (RemoteConference)mConferenceById.get(s);
        } else
        {
            Log.w(this, "%s - Cannot find Conference %s", new Object[] {
                s1, s
            });
            return NULL_CONFERENCE;
        }
    }

    private RemoteConnection findConnectionForAction(String s, String s1)
    {
        if(mConnectionById.containsKey(s))
        {
            return (RemoteConnection)mConnectionById.get(s);
        } else
        {
            Log.w(this, "%s - Cannot find Connection %s", new Object[] {
                s1, s
            });
            return NULL_CONNECTION;
        }
    }

    private boolean hasConnection(String s)
    {
        return mConnectionById.containsKey(s);
    }

    private void maybeDisconnectAdapter()
    {
        if(!mConnectionById.isEmpty() || !mConferenceById.isEmpty())
            break MISSING_BLOCK_LABEL_41;
        mOutgoingConnectionServiceRpc.removeConnectionServiceAdapter(mServant.getStub(), null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    final RemoteConnection createRemoteConnection(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest, boolean flag)
    {
        String s = UUID.randomUUID().toString();
        ConnectionRequest connectionrequest1 = (new ConnectionRequest.Builder()).setAccountHandle(connectionrequest.getAccountHandle()).setAddress(connectionrequest.getAddress()).setExtras(connectionrequest.getExtras()).setVideoState(connectionrequest.getVideoState()).setRttPipeFromInCall(connectionrequest.getRttPipeFromInCall()).setRttPipeToInCall(connectionrequest.getRttPipeToInCall()).build();
        try
        {
            if(mConnectionById.isEmpty())
                mOutgoingConnectionServiceRpc.addConnectionServiceAdapter(mServant.getStub(), null);
            connectionrequest = JVM INSTR new #95  <Class RemoteConnection>;
            connectionrequest.RemoteConnection(s, mOutgoingConnectionServiceRpc, connectionrequest1);
            mPendingConnections.add(connectionrequest);
            mConnectionById.put(s, connectionrequest);
            mOutgoingConnectionServiceRpc.createConnection(phoneaccounthandle, s, connectionrequest1, flag, false, null);
            phoneaccounthandle = JVM INSTR new #14  <Class RemoteConnectionService$3>;
            phoneaccounthandle.this. _cls3();
            connectionrequest.registerCallback(phoneaccounthandle);
        }
        // Misplaced declaration of an exception variable
        catch(PhoneAccountHandle phoneaccounthandle)
        {
            return RemoteConnection.failure(new DisconnectCause(1, phoneaccounthandle.toString()));
        }
        return connectionrequest;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[RemoteCS - ").append(mOutgoingConnectionServiceRpc.asBinder().toString()).append("]").toString();
    }

    private static final RemoteConference NULL_CONFERENCE = new RemoteConference("NULL", null);
    private static final RemoteConnection NULL_CONNECTION = new RemoteConnection("NULL", null, (ConnectionRequest)null);
    private final Map mConferenceById = new HashMap();
    private final Map mConnectionById = new HashMap();
    private final android.os.IBinder.DeathRecipient mDeathRecipient = new android.os.IBinder.DeathRecipient() {

        public void binderDied()
        {
            for(Iterator iterator = RemoteConnectionService._2D_get3(RemoteConnectionService.this).values().iterator(); iterator.hasNext(); ((RemoteConnection)iterator.next()).setDestroyed());
            for(Iterator iterator1 = RemoteConnectionService._2D_get2(RemoteConnectionService.this).values().iterator(); iterator1.hasNext(); ((RemoteConference)iterator1.next()).setDestroyed());
            RemoteConnectionService._2D_get3(RemoteConnectionService.this).clear();
            RemoteConnectionService._2D_get2(RemoteConnectionService.this).clear();
            RemoteConnectionService._2D_get7(RemoteConnectionService.this).clear();
            RemoteConnectionService._2D_get6(RemoteConnectionService.this).asBinder().unlinkToDeath(RemoteConnectionService._2D_get4(RemoteConnectionService.this), 0);
        }

        final RemoteConnectionService this$0;

            
            {
                this$0 = RemoteConnectionService.this;
                super();
            }
    }
;
    private final ConnectionService mOurConnectionServiceImpl;
    private final IConnectionService mOutgoingConnectionServiceRpc;
    private final Set mPendingConnections = new HashSet();
    private final ConnectionServiceAdapterServant mServant;
    private final IConnectionServiceAdapter mServantDelegate = new IConnectionServiceAdapter() {

        public void addConferenceCall(String s, ParcelableConference parcelableconference, android.telecom.Logging.Session.Info info)
        {
            info = new RemoteConference(s, RemoteConnectionService._2D_get6(RemoteConnectionService.this));
            Iterator iterator = parcelableconference.getConnectionIds().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Object obj = (String)iterator.next();
                obj = (RemoteConnection)RemoteConnectionService._2D_get3(RemoteConnectionService.this).get(obj);
                if(obj != null)
                    info.addConnection(((RemoteConnection) (obj)));
            } while(true);
            if(info.getConnections().size() == 0)
            {
                Log.d(this, "addConferenceCall - skipping", new Object[0]);
                return;
            } else
            {
                info.setState(parcelableconference.getState());
                info.setConnectionCapabilities(parcelableconference.getConnectionCapabilities());
                info.setConnectionProperties(parcelableconference.getConnectionProperties());
                info.putExtras(parcelableconference.getExtras());
                RemoteConnectionService._2D_get2(RemoteConnectionService.this).put(s, info);
                parcelableconference = new Bundle();
                parcelableconference.putString("android.telecom.extra.ORIGINAL_CONNECTION_ID", s);
                info.putExtras(parcelableconference);
                info.registerCallback(s. new RemoteConference.Callback() {

                    public void onDestroyed(RemoteConference remoteconference)
                    {
                        RemoteConnectionService._2D_get2(_fld0).remove(callId);
                        RemoteConnectionService._2D_wrap3(_fld0);
                    }

                    final _cls1 this$1;
                    final String val$callId;

            
            {
                this$1 = final__pcls1;
                callId = String.this;
                super();
            }
                }
);
                RemoteConnectionService._2D_get5(RemoteConnectionService.this).addRemoteConference(info);
                return;
            }
        }

        public void addExistingConnection(String s, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
        {
            info = RemoteConnectionService._2D_get5(RemoteConnectionService.this).getApplicationContext().getOpPackageName();
            int i = RemoteConnectionService._2D_get5(RemoteConnectionService.this).getApplicationInfo().targetSdkVersion;
            parcelableconnection = new RemoteConnection(s, RemoteConnectionService._2D_get6(RemoteConnectionService.this), parcelableconnection, info, i);
            RemoteConnectionService._2D_get3(RemoteConnectionService.this).put(s, parcelableconnection);
            parcelableconnection.registerCallback(s. new RemoteConnection.Callback() {

                public void onDestroyed(RemoteConnection remoteconnection)
                {
                    RemoteConnectionService._2D_get3(_fld0).remove(callId);
                    RemoteConnectionService._2D_wrap3(_fld0);
                }

                final _cls1 this$1;
                final String val$callId;

            
            {
                this$1 = final__pcls1;
                callId = String.this;
                super();
            }
            }
);
            RemoteConnectionService._2D_get5(RemoteConnectionService.this).addRemoteExistingConnection(parcelableconnection);
        }

        public IBinder asBinder()
        {
            throw new UnsupportedOperationException();
        }

        public void handleCreateConnectionComplete(String s, ConnectionRequest connectionrequest, ParcelableConnection parcelableconnection, android.telecom.Logging.Session.Info info)
        {
            connectionrequest = RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "handleCreateConnectionSuccessful");
            if(connectionrequest != RemoteConnectionService._2D_get1() && RemoteConnectionService._2D_get7(RemoteConnectionService.this).contains(connectionrequest))
            {
                RemoteConnectionService._2D_get7(RemoteConnectionService.this).remove(connectionrequest);
                connectionrequest.setConnectionCapabilities(parcelableconnection.getConnectionCapabilities());
                connectionrequest.setConnectionProperties(parcelableconnection.getConnectionProperties());
                if(parcelableconnection.getHandle() != null || parcelableconnection.getState() != 6)
                    connectionrequest.setAddress(parcelableconnection.getHandle(), parcelableconnection.getHandlePresentation());
                if(parcelableconnection.getCallerDisplayName() != null || parcelableconnection.getState() != 6)
                    connectionrequest.setCallerDisplayName(parcelableconnection.getCallerDisplayName(), parcelableconnection.getCallerDisplayNamePresentation());
                ArrayList arraylist;
                if(parcelableconnection.getState() == 6)
                    connectionrequest.setDisconnected(parcelableconnection.getDisconnectCause());
                else
                    connectionrequest.setState(parcelableconnection.getState());
                arraylist = new ArrayList();
                info = parcelableconnection.getConferenceableConnectionIds().iterator();
                do
                {
                    if(!info.hasNext())
                        break;
                    s = (String)info.next();
                    if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                        arraylist.add((RemoteConnection)RemoteConnectionService._2D_get3(RemoteConnectionService.this).get(s));
                } while(true);
                connectionrequest.setConferenceableConnections(arraylist);
                connectionrequest.setVideoState(parcelableconnection.getVideoState());
                if(connectionrequest.getState() == 6)
                    connectionrequest.setDestroyed();
            }
        }

        public void onConnectionEvent(String s, String s1, Bundle bundle, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "onConnectionEvent").onConnectionEvent(s1, bundle);
        }

        public void onPostDialChar(String s, char c, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "onPostDialChar").onPostDialChar(c);
        }

        public void onPostDialWait(String s, String s1, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "onPostDialWait").setPostDialWait(s1);
        }

        public void onRemoteRttRequest(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            if(RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "onRemoteRttRequest").onRemoteRttRequest();
            else
                Log.w(this, "onRemoteRttRequest called on a remote conference", new Object[0]);
        }

        public void onRttInitiationFailure(String s, int i, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            if(RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "onRttInitiationFailure").onRttInitiationFailure(i);
            else
                Log.w(this, "onRttInitiationFailure called on a remote conference", new Object[0]);
        }

        public void onRttInitiationSuccess(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            if(RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "onRttInitiationSuccess").onRttInitiationSuccess();
            else
                Log.w(this, "onRttInitiationSuccess called on a remote conference", new Object[0]);
        }

        public void onRttSessionRemotelyTerminated(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            if(RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "onRttSessionRemotelyTerminated").onRttSessionRemotelyTerminated();
            else
                Log.w(this, "onRttSessionRemotelyTerminated called on a remote conference", new Object[0]);
        }

        public void putExtras(String s, Bundle bundle, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "putExtras").putExtras(bundle);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "putExtras").putExtras(bundle);
        }

        public void queryRemoteConnectionServices(RemoteServiceCallback remoteservicecallback, android.telecom.Logging.Session.Info info)
        {
        }

        public void removeCall(String s, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "removeCall").setDestroyed();
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "removeCall").setDestroyed();
        }

        public void removeExtras(String s, List list, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "removeExtra").removeExtras(list);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "removeExtra").removeExtras(list);
        }

        public void resetCdmaConnectionTime(String s, android.telecom.Logging.Session.Info info)
        {
        }

        public void setActive(String s, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setActive").setState(4);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "setActive").setState(4);
        }

        public void setAddress(String s, Uri uri, int i, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setAddress").setAddress(uri, i);
        }

        public void setAudioRoute(String s, int i, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s);
        }

        public void setCallerDisplayName(String s, String s1, int i, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setCallerDisplayName").setCallerDisplayName(s1, i);
        }

        public void setConferenceMergeFailed(String s, android.telecom.Logging.Session.Info info)
        {
        }

        public final void setConferenceableConnections(String s, List list, android.telecom.Logging.Session.Info info)
        {
            info = new ArrayList();
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                list = (String)iterator.next();
                if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(list))
                    info.add((RemoteConnection)RemoteConnectionService._2D_get3(RemoteConnectionService.this).get(list));
            } while(true);
            if(RemoteConnectionService._2D_wrap2(RemoteConnectionService.this, s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setConferenceableConnections").setConferenceableConnections(info);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "setConferenceableConnections").setConferenceableConnections(info);
        }

        public void setConnectionCapabilities(String s, int i, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setConnectionCapabilities").setConnectionCapabilities(i);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "setConnectionCapabilities").setConnectionCapabilities(i);
        }

        public void setConnectionProperties(String s, int i, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setConnectionProperties").setConnectionProperties(i);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "setConnectionProperties").setConnectionProperties(i);
        }

        public void setDialing(String s, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setDialing").setState(3);
        }

        public void setDisconnected(String s, DisconnectCause disconnectcause, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setDisconnected").setDisconnected(disconnectcause);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "setDisconnected").setDisconnected(disconnectcause);
        }

        public void setIsConferenced(String s, String s1, android.telecom.Logging.Session.Info info)
        {
            s = RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setIsConferenced");
            if(s == RemoteConnectionService._2D_get1()) goto _L2; else goto _L1
_L1:
            if(s1 != null) goto _L4; else goto _L3
_L3:
            if(s.getConference() != null)
                s.getConference().removeConnection(s);
_L2:
            return;
_L4:
            s1 = RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s1, "setIsConferenced");
            if(s1 != RemoteConnectionService._2D_get0())
                s1.addConnection(s);
            if(true) goto _L2; else goto _L5
_L5:
        }

        public void setIsVoipAudioMode(String s, boolean flag, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setIsVoipAudioMode").setIsVoipAudioMode(flag);
        }

        public void setOnHold(String s, android.telecom.Logging.Session.Info info)
        {
            if(RemoteConnectionService._2D_get3(RemoteConnectionService.this).containsKey(s))
                RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setOnHold").setState(5);
            else
                RemoteConnectionService._2D_wrap0(RemoteConnectionService.this, s, "setOnHold").setState(5);
        }

        public void setPulling(String s, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setPulling").setState(7);
        }

        public void setRingbackRequested(String s, boolean flag, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setRingbackRequested").setRingbackRequested(flag);
        }

        public void setRinging(String s, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setRinging").setState(2);
        }

        public void setStatusHints(String s, StatusHints statushints, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setStatusHints").setStatusHints(statushints);
        }

        public void setVideoProvider(String s, IVideoProvider ivideoprovider, android.telecom.Logging.Session.Info info)
        {
            String s1 = RemoteConnectionService._2D_get5(RemoteConnectionService.this).getApplicationContext().getOpPackageName();
            int i = RemoteConnectionService._2D_get5(RemoteConnectionService.this).getApplicationInfo().targetSdkVersion;
            info = null;
            if(ivideoprovider != null)
                info = new RemoteConnection.VideoProvider(ivideoprovider, s1, i);
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setVideoProvider").setVideoProvider(info);
        }

        public void setVideoState(String s, int i, android.telecom.Logging.Session.Info info)
        {
            RemoteConnectionService._2D_wrap1(RemoteConnectionService.this, s, "setVideoState").setVideoState(i);
        }

        final RemoteConnectionService this$0;

            
            {
                this$0 = RemoteConnectionService.this;
                super();
            }
    }
;


    // Unreferenced inner class android/telecom/RemoteConnectionService$3

/* anonymous class */
    class _cls3 extends RemoteConnection.Callback
    {

        public void onDestroyed(RemoteConnection remoteconnection)
        {
            RemoteConnectionService._2D_get3(RemoteConnectionService.this).remove(id);
            RemoteConnectionService._2D_wrap3(RemoteConnectionService.this);
        }

        final RemoteConnectionService this$0;
        final String val$id;

            
            {
                this$0 = RemoteConnectionService.this;
                id = s;
                super();
            }
    }

}
