// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;
import com.android.internal.telecom.IConnectionServiceAdapter;
import com.android.internal.telecom.RemoteServiceCallback;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.telecom:
//            Log, CallAudioState, ParcelableConference, ParcelableConnection, 
//            ConnectionRequest, DisconnectCause, StatusHints

final class ConnectionServiceAdapter
    implements android.os.IBinder.DeathRecipient
{

    ConnectionServiceAdapter()
    {
    }

    void addAdapter(IConnectionServiceAdapter iconnectionserviceadapter)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
            if(((IConnectionServiceAdapter)iterator.next()).asBinder() == iconnectionserviceadapter.asBinder())
            {
                Log.w(this, "Ignoring duplicate adapter addition.", new Object[0]);
                return;
            }

        if(!mAdapters.add(iconnectionserviceadapter))
            break MISSING_BLOCK_LABEL_79;
        iconnectionserviceadapter.asBinder().linkToDeath(this, 0);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        mAdapters.remove(iconnectionserviceadapter);
          goto _L1
    }

    void addConferenceCall(String s, ParcelableConference parcelableconference)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.addConferenceCall(s, parcelableconference, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void addExistingConnection(String s, ParcelableConnection parcelableconnection)
    {
        Log.v(this, "addExistingConnection: %s", new Object[] {
            s
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.addExistingConnection(s, parcelableconnection, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    public void binderDied()
    {
        Iterator iterator = mAdapters.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            if(!iconnectionserviceadapter.asBinder().isBinderAlive())
            {
                iterator.remove();
                iconnectionserviceadapter.asBinder().unlinkToDeath(this, 0);
            }
        } while(true);
    }

    void handleCreateConnectionComplete(String s, ConnectionRequest connectionrequest, ParcelableConnection parcelableconnection)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.handleCreateConnectionComplete(s, connectionrequest, parcelableconnection, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onConferenceMergeFailed(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                Log.d(this, "merge failed for call %s", new Object[] {
                    s
                });
                iconnectionserviceadapter.setConferenceMergeFailed(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onConnectionEvent(String s, String s1, Bundle bundle)
    {
        Log.v(this, "onConnectionEvent: %s", new Object[] {
            s1
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.onConnectionEvent(s, s1, bundle, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onPostDialChar(String s, char c)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.onPostDialChar(s, c, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onPostDialWait(String s, String s1)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.onPostDialWait(s, s1, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onRemoteRttRequest(String s)
    {
        Log.v(this, "onRemoteRttRequest: %s", new Object[] {
            s
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.onRemoteRttRequest(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onRttInitiationFailure(String s, int i)
    {
        Log.v(this, "onRttInitiationFailure: %s", new Object[] {
            s
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.onRttInitiationFailure(s, i, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onRttInitiationSuccess(String s)
    {
        Log.v(this, "onRttInitiationSuccess: %s", new Object[] {
            s
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.onRttInitiationSuccess(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void onRttSessionRemotelyTerminated(String s)
    {
        Log.v(this, "onRttSessionRemotelyTerminated: %s", new Object[] {
            s
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.onRttSessionRemotelyTerminated(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void putExtra(String s, String s1, int i)
    {
        Log.v(this, "putExtra: %s %s=%d", new Object[] {
            s, s1, Integer.valueOf(i)
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                Bundle bundle = JVM INSTR new #174 <Class Bundle>;
                bundle.Bundle();
                bundle.putInt(s1, i);
                iconnectionserviceadapter.putExtras(s, bundle, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void putExtra(String s, String s1, String s2)
    {
        Log.v(this, "putExtra: %s %s=%s", new Object[] {
            s, s1, s2
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                Bundle bundle = JVM INSTR new #174 <Class Bundle>;
                bundle.Bundle();
                bundle.putString(s1, s2);
                iconnectionserviceadapter.putExtras(s, bundle, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void putExtra(String s, String s1, boolean flag)
    {
        Log.v(this, "putExtra: %s %s=%b", new Object[] {
            s, s1, Boolean.valueOf(flag)
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                Bundle bundle = JVM INSTR new #174 <Class Bundle>;
                bundle.Bundle();
                bundle.putBoolean(s1, flag);
                iconnectionserviceadapter.putExtras(s, bundle, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void putExtras(String s, Bundle bundle)
    {
        Log.v(this, "putExtras: %s", new Object[] {
            s
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.putExtras(s, bundle, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void queryRemoteConnectionServices(RemoteServiceCallback remoteservicecallback)
    {
        if(mAdapters.size() != 1)
            break MISSING_BLOCK_LABEL_39;
        ((IConnectionServiceAdapter)mAdapters.iterator().next()).queryRemoteConnectionServices(remoteservicecallback, Log.getExternalSession());
_L1:
        return;
        remoteservicecallback;
        Log.e(this, remoteservicecallback, "Exception trying to query for remote CSs", new Object[0]);
          goto _L1
    }

    void removeAdapter(IConnectionServiceAdapter iconnectionserviceadapter)
    {
label0:
        {
            if(iconnectionserviceadapter == null)
                break label0;
            Iterator iterator = mAdapters.iterator();
            IConnectionServiceAdapter iconnectionserviceadapter1;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                iconnectionserviceadapter1 = (IConnectionServiceAdapter)iterator.next();
            } while(iconnectionserviceadapter1.asBinder() != iconnectionserviceadapter.asBinder() || !mAdapters.remove(iconnectionserviceadapter1));
            iconnectionserviceadapter.asBinder().unlinkToDeath(this, 0);
        }
    }

    void removeCall(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.removeCall(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void removeExtras(String s, List list)
    {
        Log.v(this, "removeExtras: %s %s", new Object[] {
            s, list
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.removeExtras(s, list, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void resetCdmaConnectionTime(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.resetCdmaConnectionTime(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setActive(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setActive(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setAddress(String s, Uri uri, int i)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setAddress(s, uri, i, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setAudioRoute(String s, int i)
    {
        Log.v(this, "setAudioRoute: %s %s", new Object[] {
            s, CallAudioState.audioRouteToString(i)
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setAudioRoute(s, i, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setCallerDisplayName(String s, String s1, int i)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setCallerDisplayName(s, s1, i, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setConferenceableConnections(String s, List list)
    {
        Log.v(this, "setConferenceableConnections: %s, %s", new Object[] {
            s, list
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setConferenceableConnections(s, list, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setConnectionCapabilities(String s, int i)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setConnectionCapabilities(s, i, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setConnectionProperties(String s, int i)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setConnectionProperties(s, i, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setDialing(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setDialing(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setDisconnected(String s, DisconnectCause disconnectcause)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setDisconnected(s, disconnectcause, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setIsConferenced(String s, String s1)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                Log.d(this, "sending connection %s with conference %s", new Object[] {
                    s, s1
                });
                iconnectionserviceadapter.setIsConferenced(s, s1, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setIsVoipAudioMode(String s, boolean flag)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setIsVoipAudioMode(s, flag, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setOnHold(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setOnHold(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setPulling(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setPulling(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setRingbackRequested(String s, boolean flag)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setRingbackRequested(s, flag, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setRinging(String s)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setRinging(s, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setStatusHints(String s, StatusHints statushints)
    {
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setStatusHints(s, statushints, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    void setVideoProvider(String s, Connection.VideoProvider videoprovider)
    {
        Iterator iterator = mAdapters.iterator();
_L7:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
        if(videoprovider != null) goto _L4; else goto _L3
_L3:
        Object obj = null;
_L5:
        try
        {
            iconnectionserviceadapter.setVideoProvider(s, ((com.android.internal.telecom.IVideoProvider) (obj)), Log.getExternalSession());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
        continue; /* Loop/switch isn't completed */
_L4:
        obj = videoprovider.getInterface();
        if(true) goto _L5; else goto _L2
_L2:
        return;
        if(true) goto _L7; else goto _L6
_L6:
    }

    void setVideoState(String s, int i)
    {
        Log.v(this, "setVideoState: %d", new Object[] {
            Integer.valueOf(i)
        });
        for(Iterator iterator = mAdapters.iterator(); iterator.hasNext();)
        {
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)iterator.next();
            try
            {
                iconnectionserviceadapter.setVideoState(s, i, Log.getExternalSession());
            }
            catch(RemoteException remoteexception) { }
        }

    }

    private final Set mAdapters = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9F, 1));
}
