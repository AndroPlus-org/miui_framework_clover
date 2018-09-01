// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Bundle;
import android.util.ArraySet;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

// Referenced classes of package android.telecom:
//            Conferenceable, Connection, Log, DisconnectCause, 
//            AudioState, CallAudioState, PhoneAccountHandle, StatusHints

public abstract class Conference extends Conferenceable
{
    public static abstract class Listener
    {

        public void onConferenceableConnectionsChanged(Conference conference, List list)
        {
        }

        public void onConnectionAdded(Conference conference, Connection connection)
        {
        }

        public void onConnectionCapabilitiesChanged(Conference conference, int i)
        {
        }

        public void onConnectionPropertiesChanged(Conference conference, int i)
        {
        }

        public void onConnectionRemoved(Conference conference, Connection connection)
        {
        }

        public void onDestroyed(Conference conference)
        {
        }

        public void onDisconnected(Conference conference, DisconnectCause disconnectcause)
        {
        }

        public void onExtrasChanged(Conference conference, Bundle bundle)
        {
        }

        public void onExtrasRemoved(Conference conference, List list)
        {
        }

        public void onStateChanged(Conference conference, int i, int j)
        {
        }

        public void onStatusHintsChanged(Conference conference, StatusHints statushints)
        {
        }

        public void onVideoProviderChanged(Conference conference, Connection.VideoProvider videoprovider)
        {
        }

        public void onVideoStateChanged(Conference conference, int i)
        {
        }

        public Listener()
        {
        }
    }


    static List _2D_get0(Conference conference)
    {
        return conference.mConferenceableConnections;
    }

    static void _2D_wrap0(Conference conference)
    {
        conference.fireOnConferenceableConnectionsChanged();
    }

    public Conference(PhoneAccountHandle phoneaccounthandle)
    {
        mUnmodifiableChildConnections = Collections.unmodifiableList(mChildConnections);
        mUnmodifiableConferenceableConnections = Collections.unmodifiableList(mConferenceableConnections);
        mState = 1;
        mConnectTimeMillis = 0L;
        mConnectElapsedTimeMillis = 0L;
        mPhoneAccount = phoneaccounthandle;
    }

    public static boolean can(int i, int j)
    {
        boolean flag = false;
        if((i & j) != 0)
            flag = true;
        return flag;
    }

    private final void clearConferenceableList()
    {
        for(Iterator iterator = mConferenceableConnections.iterator(); iterator.hasNext(); ((Connection)iterator.next()).removeConnectionListener(mConnectionDeathListener));
        mConferenceableConnections.clear();
    }

    private final void fireOnConferenceableConnectionsChanged()
    {
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConferenceableConnectionsChanged(this, getConferenceableConnections()));
    }

    private void setState(int i)
    {
        if(i != 4 && i != 5 && i != 3 && i != 6)
        {
            Log.w(this, "Unsupported state transition for Conference call.", new Object[] {
                Connection.stateToString(i)
            });
            return;
        }
        if(mState != i)
        {
            int j = mState;
            mState = i;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onStateChanged(this, j, i));
        }
    }

    public void addCapability(int i)
    {
        setConnectionCapabilities(mConnectionCapabilities | i);
    }

    public final boolean addConnection(Connection connection)
    {
        Log.d(this, "Connection=%s, connection=", new Object[] {
            connection
        });
        if(connection != null && mChildConnections.contains(connection) ^ true && connection.setConference(this))
        {
            mChildConnections.add(connection);
            onConnectionAdded(connection);
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConnectionAdded(this, connection));
            return true;
        } else
        {
            return false;
        }
    }

    public final Conference addListener(Listener listener)
    {
        mListeners.add(listener);
        return this;
    }

    public boolean can(int i)
    {
        return can(mConnectionCapabilities, i);
    }

    public final void destroy()
    {
        Log.d(this, "destroying conference : %s", new Object[] {
            this
        });
        Connection connection;
        for(Iterator iterator = mChildConnections.iterator(); iterator.hasNext(); removeConnection(connection))
        {
            connection = (Connection)iterator.next();
            Log.d(this, "removing connection %s", new Object[] {
                connection
            });
        }

        if(mState != 6)
        {
            Log.d(this, "setting to disconnected", new Object[0]);
            setDisconnected(new DisconnectCause(2));
        }
        for(Iterator iterator1 = mListeners.iterator(); iterator1.hasNext(); ((Listener)iterator1.next()).onDestroyed(this));
    }

    public final AudioState getAudioState()
    {
        return new AudioState(mCallAudioState);
    }

    public final CallAudioState getCallAudioState()
    {
        return mCallAudioState;
    }

    public final List getConferenceableConnections()
    {
        return mUnmodifiableConferenceableConnections;
    }

    public final long getConnectElapsedTime()
    {
        return mConnectElapsedTimeMillis;
    }

    public final long getConnectTimeMillis()
    {
        return getConnectionTime();
    }

    public final int getConnectionCapabilities()
    {
        return mConnectionCapabilities;
    }

    public final int getConnectionProperties()
    {
        return mConnectionProperties;
    }

    public final long getConnectionTime()
    {
        return mConnectTimeMillis;
    }

    public final List getConnections()
    {
        return mUnmodifiableChildConnections;
    }

    public final DisconnectCause getDisconnectCause()
    {
        return mDisconnectCause;
    }

    public final Bundle getExtras()
    {
        return mExtras;
    }

    public final PhoneAccountHandle getPhoneAccountHandle()
    {
        return mPhoneAccount;
    }

    public Connection getPrimaryConnection()
    {
        if(mUnmodifiableChildConnections == null || mUnmodifiableChildConnections.isEmpty())
            return null;
        else
            return (Connection)mUnmodifiableChildConnections.get(0);
    }

    public final int getState()
    {
        return mState;
    }

    public final StatusHints getStatusHints()
    {
        return mStatusHints;
    }

    public final String getTelecomCallId()
    {
        return mTelecomCallId;
    }

    public Connection.VideoProvider getVideoProvider()
    {
        return null;
    }

    public int getVideoState()
    {
        return 0;
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

    public void onAddParticipant(String s)
    {
    }

    public void onAudioStateChanged(AudioState audiostate)
    {
    }

    public void onCallAudioStateChanged(CallAudioState callaudiostate)
    {
    }

    public void onConnectionAdded(Connection connection)
    {
    }

    public void onDisconnect()
    {
    }

    public void onExtrasChanged(Bundle bundle)
    {
    }

    public void onHold()
    {
    }

    public void onMerge()
    {
    }

    public void onMerge(Connection connection)
    {
    }

    public void onPlayDtmfTone(char c)
    {
    }

    public void onSeparate(Connection connection)
    {
    }

    public void onStopDtmfTone()
    {
    }

    public void onSwap()
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
        if(bundle == null)
            return;
        Object obj = mExtrasLock;
        obj;
        JVM INSTR monitorenter ;
        if(mExtras == null)
        {
            Bundle bundle1 = JVM INSTR new #280 <Class Bundle>;
            bundle1.Bundle();
            mExtras = bundle1;
        }
        mExtras.putAll(bundle);
        bundle = new Bundle(mExtras);
        obj;
        JVM INSTR monitorexit ;
        for(obj = mListeners.iterator(); ((Iterator) (obj)).hasNext(); ((Listener)((Iterator) (obj)).next()).onExtrasChanged(this, new Bundle(bundle)));
        break MISSING_BLOCK_LABEL_102;
        bundle;
        throw bundle;
    }

    public void removeCapability(int i)
    {
        setConnectionCapabilities(mConnectionCapabilities & i);
    }

    public final void removeConnection(Connection connection)
    {
        Log.d(this, "removing %s from %s", new Object[] {
            connection, mChildConnections
        });
        if(connection != null && mChildConnections.remove(connection))
        {
            connection.resetConference();
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConnectionRemoved(this, connection));
        }
    }

    public final void removeExtras(List list)
    {
        if(list == null || list.isEmpty())
            return;
        Object obj = mExtrasLock;
        obj;
        JVM INSTR monitorenter ;
        if(mExtras != null)
        {
            String s;
            for(Iterator iterator1 = list.iterator(); iterator1.hasNext(); mExtras.remove(s))
                s = (String)iterator1.next();

        }
        break MISSING_BLOCK_LABEL_72;
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

    public final Conference removeListener(Listener listener)
    {
        mListeners.remove(listener);
        return this;
    }

    public final void setActive()
    {
        setState(4);
    }

    final void setCallAudioState(CallAudioState callaudiostate)
    {
        Log.d(this, "setCallAudioState %s", new Object[] {
            callaudiostate
        });
        mCallAudioState = callaudiostate;
        onAudioStateChanged(getAudioState());
        onCallAudioStateChanged(callaudiostate);
    }

    public final void setConferenceableConnections(List list)
    {
        clearConferenceableList();
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            Connection connection = (Connection)list.next();
            if(!mConferenceableConnections.contains(connection))
            {
                connection.addConnectionListener(mConnectionDeathListener);
                mConferenceableConnections.add(connection);
            }
        } while(true);
        fireOnConferenceableConnectionsChanged();
    }

    public final void setConnectTimeMillis(long l)
    {
        setConnectionTime(l);
    }

    public final void setConnectionCapabilities(int i)
    {
        if(i != mConnectionCapabilities)
        {
            mConnectionCapabilities = i;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConnectionCapabilitiesChanged(this, mConnectionCapabilities));
        }
    }

    public final void setConnectionElapsedTime(long l)
    {
        mConnectElapsedTimeMillis = l;
    }

    public final void setConnectionProperties(int i)
    {
        if(i != mConnectionProperties)
        {
            mConnectionProperties = i;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onConnectionPropertiesChanged(this, mConnectionProperties));
        }
    }

    public final void setConnectionTime(long l)
    {
        mConnectTimeMillis = l;
    }

    public final void setDialing()
    {
        setState(3);
    }

    public final void setDisconnected(DisconnectCause disconnectcause)
    {
        mDisconnectCause = disconnectcause;
        setState(6);
        for(disconnectcause = mListeners.iterator(); disconnectcause.hasNext(); ((Listener)disconnectcause.next()).onDisconnected(this, mDisconnectCause));
    }

    public final void setExtras(Bundle bundle)
    {
        Object obj = mExtrasLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        Iterator iterator;
        putExtras(bundle);
        if(mPreviousExtraKeys == null)
            break MISSING_BLOCK_LABEL_106;
        arraylist = JVM INSTR new #82  <Class ArrayList>;
        arraylist.ArrayList();
        iterator = mPreviousExtraKeys.iterator();
_L3:
        String s;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_92;
        s = (String)iterator.next();
        if(bundle == null) goto _L2; else goto _L1
_L1:
        if(!(bundle.containsKey(s) ^ true)) goto _L3; else goto _L2
_L2:
        arraylist.add(s);
          goto _L3
        bundle;
        throw bundle;
        if(!arraylist.isEmpty())
            removeExtras(arraylist);
        if(mPreviousExtraKeys == null)
        {
            ArraySet arrayset = JVM INSTR new #402 <Class ArraySet>;
            arrayset.ArraySet();
            mPreviousExtraKeys = arrayset;
        }
        mPreviousExtraKeys.clear();
        if(bundle == null)
            break MISSING_BLOCK_LABEL_153;
        mPreviousExtraKeys.addAll(bundle.keySet());
        obj;
        JVM INSTR monitorexit ;
    }

    public final void setOnHold()
    {
        setState(5);
    }

    public final void setStatusHints(StatusHints statushints)
    {
        mStatusHints = statushints;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onStatusHintsChanged(this, statushints));
    }

    public final void setTelecomCallId(String s)
    {
        mTelecomCallId = s;
    }

    public final void setVideoProvider(Connection connection, Connection.VideoProvider videoprovider)
    {
        Log.d(this, "setVideoProvider Conference: %s Connection: %s VideoState: %s", new Object[] {
            this, connection, videoprovider
        });
        for(connection = mListeners.iterator(); connection.hasNext(); ((Listener)connection.next()).onVideoProviderChanged(this, videoprovider));
    }

    public final void setVideoState(Connection connection, int i)
    {
        Log.d(this, "setVideoState Conference: %s Connection: %s VideoState: %s", new Object[] {
            this, connection, Integer.valueOf(i)
        });
        for(connection = mListeners.iterator(); connection.hasNext(); ((Listener)connection.next()).onVideoStateChanged(this, i));
    }

    public String toString()
    {
        return String.format(Locale.US, "[State: %s,Capabilites: %s, VideoState: %s, VideoProvider: %s, ThisObject %s]", new Object[] {
            Connection.stateToString(mState), Call.Details.capabilitiesToString(mConnectionCapabilities), Integer.valueOf(getVideoState()), getVideoProvider(), super.toString()
        });
    }

    public static final long CONNECT_TIME_NOT_SPECIFIED = 0L;
    private CallAudioState mCallAudioState;
    private final List mChildConnections = new CopyOnWriteArrayList();
    private final List mConferenceableConnections = new ArrayList();
    private long mConnectElapsedTimeMillis;
    private long mConnectTimeMillis;
    private int mConnectionCapabilities;
    private final Connection.Listener mConnectionDeathListener = new Connection.Listener() {

        public void onDestroyed(Connection connection)
        {
            if(Conference._2D_get0(Conference.this).remove(connection))
                Conference._2D_wrap0(Conference.this);
        }

        final Conference this$0;

            
            {
                this$0 = Conference.this;
                super();
            }
    }
;
    private int mConnectionProperties;
    private DisconnectCause mDisconnectCause;
    private String mDisconnectMessage;
    private Bundle mExtras;
    private final Object mExtrasLock = new Object();
    private final Set mListeners = new CopyOnWriteArraySet();
    private PhoneAccountHandle mPhoneAccount;
    private Set mPreviousExtraKeys;
    private int mState;
    private StatusHints mStatusHints;
    private String mTelecomCallId;
    private final List mUnmodifiableChildConnections;
    private final List mUnmodifiableConferenceableConnections;
}
