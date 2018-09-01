// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.*;
import com.android.internal.telecom.IConnectionService;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

// Referenced classes of package android.telecom:
//            CallbackRecord, RemoteConnection, CallAudioState, Connection, 
//            Log, DisconnectCause, AudioState

public final class RemoteConference
{
    public static abstract class Callback
    {

        public void onConferenceableConnectionsChanged(RemoteConference remoteconference, List list)
        {
        }

        public void onConnectionAdded(RemoteConference remoteconference, RemoteConnection remoteconnection)
        {
        }

        public void onConnectionCapabilitiesChanged(RemoteConference remoteconference, int i)
        {
        }

        public void onConnectionPropertiesChanged(RemoteConference remoteconference, int i)
        {
        }

        public void onConnectionRemoved(RemoteConference remoteconference, RemoteConnection remoteconnection)
        {
        }

        public void onDestroyed(RemoteConference remoteconference)
        {
        }

        public void onDisconnected(RemoteConference remoteconference, DisconnectCause disconnectcause)
        {
        }

        public void onExtrasChanged(RemoteConference remoteconference, Bundle bundle)
        {
        }

        public void onStateChanged(RemoteConference remoteconference, int i, int j)
        {
        }

        public Callback()
        {
        }
    }


    static int _2D_get0(RemoteConference remoteconference)
    {
        return remoteconference.mConnectionCapabilities;
    }

    static int _2D_get1(RemoteConference remoteconference)
    {
        return remoteconference.mConnectionProperties;
    }

    static Bundle _2D_get2(RemoteConference remoteconference)
    {
        return remoteconference.mExtras;
    }

    static List _2D_get3(RemoteConference remoteconference)
    {
        return remoteconference.mUnmodifiableConferenceableConnections;
    }

    RemoteConference(String s, IConnectionService iconnectionservice)
    {
        mUnmodifiableChildConnections = Collections.unmodifiableList(mChildConnections);
        mUnmodifiableConferenceableConnections = Collections.unmodifiableList(mConferenceableConnections);
        mState = 1;
        mId = s;
        mConnectionService = iconnectionservice;
    }

    private void notifyExtrasChanged()
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onExtrasChanged(conference, RemoteConference._2D_get2(RemoteConference.this));
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    void addConnection(final RemoteConnection connection)
    {
        if(!mChildConnections.contains(connection))
        {
            mChildConnections.add(connection);
            connection.setConference(this);
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionAdded(conference, connection);
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;
        final RemoteConnection val$connection;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                connection = remoteconnection;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = (Callback)callbackrecord.getCallback();
            }

        }
    }

    public void disconnect()
    {
        mConnectionService.disconnect(mId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public List getConferenceableConnections()
    {
        return mUnmodifiableConferenceableConnections;
    }

    public final int getConnectionCapabilities()
    {
        return mConnectionCapabilities;
    }

    public final int getConnectionProperties()
    {
        return mConnectionProperties;
    }

    public final List getConnections()
    {
        return mUnmodifiableChildConnections;
    }

    public DisconnectCause getDisconnectCause()
    {
        return mDisconnectCause;
    }

    public final Bundle getExtras()
    {
        return mExtras;
    }

    String getId()
    {
        return mId;
    }

    public final int getState()
    {
        return mState;
    }

    public void hold()
    {
        mConnectionService.hold(mId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void merge()
    {
        mConnectionService.mergeConference(mId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void playDtmfTone(char c)
    {
        mConnectionService.playDtmfTone(mId, c, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void putExtras(Bundle bundle)
    {
        if(bundle == null)
            return;
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putAll(bundle);
        notifyExtrasChanged();
    }

    public final void registerCallback(Callback callback)
    {
        registerCallback(callback, new Handler());
    }

    public final void registerCallback(Callback callback, Handler handler)
    {
        unregisterCallback(callback);
        if(callback != null && handler != null)
            mCallbackRecords.add(new CallbackRecord(callback, handler));
    }

    void removeConnection(final RemoteConnection connection)
    {
        if(mChildConnections.contains(connection))
        {
            mChildConnections.remove(connection);
            connection.setConference(null);
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionRemoved(conference, connection);
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;
        final RemoteConnection val$connection;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                connection = remoteconnection;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = (Callback)callbackrecord.getCallback();
            }

        }
    }

    void removeExtras(List list)
    {
        while(mExtras == null || list == null || list.isEmpty()) 
            return;
        String s;
        for(list = list.iterator(); list.hasNext(); mExtras.remove(s))
            s = (String)list.next();

        notifyExtrasChanged();
    }

    public void separate(RemoteConnection remoteconnection)
    {
        if(!mChildConnections.contains(remoteconnection))
            break MISSING_BLOCK_LABEL_27;
        mConnectionService.splitFromConference(remoteconnection.getId(), null);
_L2:
        return;
        remoteconnection;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setAudioState(AudioState audiostate)
    {
        setCallAudioState(new CallAudioState(audiostate));
    }

    public void setCallAudioState(CallAudioState callaudiostate)
    {
        mConnectionService.onCallAudioStateChanged(mId, callaudiostate, null);
_L2:
        return;
        callaudiostate;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void setConferenceableConnections(final List callback)
    {
        mConferenceableConnections.clear();
        mConferenceableConnections.addAll(callback);
        CallbackRecord callbackrecord;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConferenceableConnectionsChanged(conference, RemoteConference._2D_get3(RemoteConference.this));
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    void setConnectionCapabilities(int i)
    {
        if(mConnectionCapabilities != i)
        {
            mConnectionCapabilities = i;
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionCapabilitiesChanged(conference, RemoteConference._2D_get0(RemoteConference.this));
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = (Callback)callbackrecord.getCallback();
            }

        }
    }

    void setConnectionProperties(int i)
    {
        if(mConnectionProperties != i)
        {
            mConnectionProperties = i;
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionPropertiesChanged(conference, RemoteConference._2D_get1(RemoteConference.this));
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = (Callback)callbackrecord.getCallback();
            }

        }
    }

    void setDestroyed()
    {
        for(Iterator iterator = mChildConnections.iterator(); iterator.hasNext(); ((RemoteConnection)iterator.next()).setConference(null));
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator1 = mCallbackRecords.iterator(); iterator1.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onDestroyed(conference);
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator1.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    void setDisconnected(final DisconnectCause disconnectCause)
    {
        if(mState != 6)
        {
            mDisconnectCause = disconnectCause;
            setState(6);
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onDisconnected(conference, disconnectCause);
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;
        final DisconnectCause val$disconnectCause;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                disconnectCause = disconnectcause;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = (Callback)callbackrecord.getCallback();
            }

        }
    }

    void setState(final int newState)
    {
        if(newState != 4 && newState != 5 && newState != 6)
        {
            Log.w(this, "Unsupported state transition for Conference call.", new Object[] {
                Connection.stateToString(newState)
            });
            return;
        }
        if(mState != newState)
        {
            final int oldState = mState;
            mState = newState;
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onStateChanged(conference, oldState, newState);
        }

        final RemoteConference this$0;
        final Callback val$callback;
        final RemoteConference val$conference;
        final int val$newState;
        final int val$oldState;

            
            {
                this$0 = RemoteConference.this;
                callback = callback1;
                conference = remoteconference1;
                oldState = i;
                newState = j;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = (Callback)callbackrecord.getCallback();
            }

        }
    }

    public void stopDtmfTone()
    {
        mConnectionService.stopDtmfTone(mId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void swap()
    {
        mConnectionService.swapConference(mId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void unhold()
    {
        mConnectionService.unhold(mId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final void unregisterCallback(Callback callback)
    {
label0:
        {
            if(callback == null)
                break label0;
            Iterator iterator = mCallbackRecords.iterator();
            CallbackRecord callbackrecord;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                callbackrecord = (CallbackRecord)iterator.next();
            } while(callbackrecord.getCallback() != callback);
            mCallbackRecords.remove(callbackrecord);
        }
    }

    private final Set mCallbackRecords = new CopyOnWriteArraySet();
    private final List mChildConnections = new CopyOnWriteArrayList();
    private final List mConferenceableConnections = new ArrayList();
    private int mConnectionCapabilities;
    private int mConnectionProperties;
    private final IConnectionService mConnectionService;
    private DisconnectCause mDisconnectCause;
    private Bundle mExtras;
    private final String mId;
    private int mState;
    private final List mUnmodifiableChildConnections;
    private final List mUnmodifiableConferenceableConnections;
}
