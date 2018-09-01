// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.telecom.Logging.Runnable;
import android.telecom.Logging.Session;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.IConnectionServiceAdapter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.telecom:
//            Log, RemoteConnectionManager, ConnectionServiceAdapter, Connection, 
//            Conference, PhoneAccountHandle, DisconnectCause, ConnectionRequest, 
//            ParcelableConnection, Conferenceable, ParcelableConference, CallAudioState, 
//            RemoteConference, RemoteConnection, StatusHints

public abstract class ConnectionService extends Service
{

    static ConnectionServiceAdapter _2D_get0(ConnectionService connectionservice)
    {
        return connectionservice.mAdapter;
    }

    static boolean _2D_get1(ConnectionService connectionservice)
    {
        return connectionservice.mAreAccountsInitialized;
    }

    static Handler _2D_get2(ConnectionService connectionservice)
    {
        return connectionservice.mHandler;
    }

    static Map _2D_get3(ConnectionService connectionservice)
    {
        return connectionservice.mIdByConference;
    }

    static Map _2D_get4(ConnectionService connectionservice)
    {
        return connectionservice.mIdByConnection;
    }

    static List _2D_get5(ConnectionService connectionservice)
    {
        return connectionservice.mPreInitializationConnectionRequests;
    }

    static RemoteConnectionManager _2D_get6(ConnectionService connectionservice)
    {
        return connectionservice.mRemoteConnectionManager;
    }

    static boolean _2D_set0(ConnectionService connectionservice, boolean flag)
    {
        connectionservice.mAreAccountsInitialized = flag;
        return flag;
    }

    static List _2D_wrap0(ConnectionService connectionservice, List list)
    {
        return connectionservice.createConnectionIdList(list);
    }

    static List _2D_wrap1(ConnectionService connectionservice, List list)
    {
        return connectionservice.createIdList(list);
    }

    static void _2D_wrap10(ConnectionService connectionservice, String s, Bundle bundle)
    {
        connectionservice.handleExtrasChanged(s, bundle);
    }

    static void _2D_wrap11(ConnectionService connectionservice, String s, Connection.RttTextStream rtttextstream)
    {
        connectionservice.handleRttUpgradeResponse(s, rtttextstream);
    }

    static void _2D_wrap12(ConnectionService connectionservice, String s)
    {
        connectionservice.hold(s);
    }

    static void _2D_wrap13(ConnectionService connectionservice, String s)
    {
        connectionservice.mergeConference(s);
    }

    static void _2D_wrap14(ConnectionService connectionservice, String s)
    {
        connectionservice.notifyCreateConnectionComplete(s);
    }

    static void _2D_wrap15(ConnectionService connectionservice)
    {
        connectionservice.onAccountsInitialized();
    }

    static void _2D_wrap16(ConnectionService connectionservice)
    {
        connectionservice.onAdapterAttached();
    }

    static void _2D_wrap17(ConnectionService connectionservice, String s, CallAudioState callaudiostate)
    {
        connectionservice.onCallAudioStateChanged(s, callaudiostate);
    }

    static void _2D_wrap18(ConnectionService connectionservice, String s, boolean flag)
    {
        connectionservice.onPostDialContinue(s, flag);
    }

    static void _2D_wrap19(ConnectionService connectionservice, String s, char c)
    {
        connectionservice.playDtmfTone(s, c);
    }

    static void _2D_wrap2(ConnectionService connectionservice, String s)
    {
        connectionservice.abort(s);
    }

    static void _2D_wrap20(ConnectionService connectionservice, String s)
    {
        connectionservice.pullExternalCall(s);
    }

    static void _2D_wrap21(ConnectionService connectionservice, String s)
    {
        connectionservice.reject(s);
    }

    static void _2D_wrap22(ConnectionService connectionservice, String s, String s1)
    {
        connectionservice.reject(s, s1);
    }

    static void _2D_wrap23(ConnectionService connectionservice, Conference conference1)
    {
        connectionservice.removeConference(conference1);
    }

    static void _2D_wrap24(ConnectionService connectionservice, String s, String s1, Bundle bundle)
    {
        connectionservice.sendCallEvent(s, s1, bundle);
    }

    static void _2D_wrap25(ConnectionService connectionservice, String s)
    {
        connectionservice.silence(s);
    }

    static void _2D_wrap26(ConnectionService connectionservice, String s)
    {
        connectionservice.splitFromConference(s);
    }

    static void _2D_wrap27(ConnectionService connectionservice, String s, Connection.RttTextStream rtttextstream)
    {
        connectionservice.startRtt(s, rtttextstream);
    }

    static void _2D_wrap28(ConnectionService connectionservice, String s)
    {
        connectionservice.stopDtmfTone(s);
    }

    static void _2D_wrap29(ConnectionService connectionservice, String s)
    {
        connectionservice.stopRtt(s);
    }

    static void _2D_wrap3(ConnectionService connectionservice, String s, String s1)
    {
        connectionservice.addParticipantWithConference(s, s1);
    }

    static void _2D_wrap30(ConnectionService connectionservice, String s)
    {
        connectionservice.swapConference(s);
    }

    static void _2D_wrap31(ConnectionService connectionservice, String s)
    {
        connectionservice.unhold(s);
    }

    static void _2D_wrap4(ConnectionService connectionservice, String s, int i)
    {
        connectionservice.answerVideo(s, i);
    }

    static void _2D_wrap5(ConnectionService connectionservice, String s)
    {
        connectionservice.answer(s);
    }

    static void _2D_wrap6(ConnectionService connectionservice, String s, String s1)
    {
        connectionservice.conference(s, s1);
    }

    static void _2D_wrap7(ConnectionService connectionservice, PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag)
    {
        connectionservice.createConnectionFailed(phoneaccounthandle, s, connectionrequest, flag);
    }

    static void _2D_wrap8(ConnectionService connectionservice, PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, boolean flag1)
    {
        connectionservice.createConnection(phoneaccounthandle, s, connectionrequest, flag, flag1);
    }

    static void _2D_wrap9(ConnectionService connectionservice, String s)
    {
        connectionservice.disconnect(s);
    }

    public ConnectionService()
    {
        mAreAccountsInitialized = false;
        mIdSyncRoot = new Object();
        mId = 0;
        mPrevPhoneConnection = null;
    }

    private void abort(String s)
    {
        Log.d(this, "abort %s", new Object[] {
            s
        });
        findConnectionForAction(s, "abort").onAbort();
    }

    private String addConferenceInternal(Conference conference1)
    {
        Object obj = null;
        String s = obj;
        if(conference1.getExtras() != null)
        {
            s = obj;
            if(conference1.getExtras().containsKey("android.telecom.extra.ORIGINAL_CONNECTION_ID"))
            {
                s = conference1.getExtras().getString("android.telecom.extra.ORIGINAL_CONNECTION_ID");
                Log.d(this, "addConferenceInternal: conf %s reusing original id %s", new Object[] {
                    conference1.getTelecomCallId(), s
                });
            }
        }
        if(mIdByConference.containsKey(conference1))
            Log.w(this, "Re-adding an existing conference: %s.", new Object[] {
                conference1
            });
        else
        if(conference1 != null)
        {
            if(s == null)
                s = UUID.randomUUID().toString();
            mConferenceById.put(s, conference1);
            mIdByConference.put(conference1, s);
            conference1.addListener(mConferenceListener);
            return s;
        }
        return null;
    }

    private void addConnection(String s, Connection connection)
    {
        connection.setTelecomCallId(s);
        mConnectionById.put(s, connection);
        mIdByConnection.put(connection, s);
        connection.addConnectionListener(mConnectionListener);
        connection.setConnectionService(this);
    }

    private String addExistingConnectionInternal(PhoneAccountHandle phoneaccounthandle, Connection connection)
    {
        if(connection.getExtras() != null && connection.getExtras().containsKey("android.telecom.extra.ORIGINAL_CONNECTION_ID"))
        {
            phoneaccounthandle = connection.getExtras().getString("android.telecom.extra.ORIGINAL_CONNECTION_ID");
            Log.d(this, "addExistingConnectionInternal - conn %s reusing original id %s", new Object[] {
                connection.getTelecomCallId(), phoneaccounthandle
            });
        } else
        if(phoneaccounthandle == null)
            phoneaccounthandle = UUID.randomUUID().toString();
        else
            phoneaccounthandle = (new StringBuilder()).append(phoneaccounthandle.getComponentName().getClassName()).append("@").append(getNextCallId()).toString();
        addConnection(phoneaccounthandle, connection);
        return phoneaccounthandle;
    }

    private void addParticipantWithConference(String s, String s1)
    {
        Conference conference1;
        Log.d(this, "ConnectionService addParticipantWithConference(%s, %s)", new Object[] {
            s1, s
        });
        conference1 = findConferenceForAction(s, "addParticipantWithConference");
        s = findConnectionForAction(s, "addParticipantWithConnection");
        if(s == getNullConnection()) goto _L2; else goto _L1
_L1:
        onAddParticipant(s, s1);
_L4:
        return;
_L2:
        if(conference1 != getNullConference())
            conference1.onAddParticipant(s1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void answer(String s)
    {
        Log.d(this, "answer %s", new Object[] {
            s
        });
        findConnectionForAction(s, "answer").onAnswer();
    }

    private void answerVideo(String s, int i)
    {
        Log.d(this, "answerVideo %s", new Object[] {
            s
        });
        findConnectionForAction(s, "answer").onAnswer(i);
    }

    private void conference(String s, String s1)
    {
        Log.d(this, "conference %s, %s", new Object[] {
            s, s1
        });
        Connection connection = findConnectionForAction(s1, "conference");
        Conference conference1 = getNullConference();
        if(connection == getNullConnection())
        {
            Conference conference2 = findConferenceForAction(s1, "conference");
            conference1 = conference2;
            if(conference2 == getNullConference())
            {
                Log.w(this, "Connection2 or Conference2 missing in conference request %s.", new Object[] {
                    s1
                });
                return;
            }
        }
        s1 = findConnectionForAction(s, "conference");
        if(s1 == getNullConnection())
        {
            s1 = findConferenceForAction(s, "addConnection");
            if(s1 == getNullConference())
                Log.w(this, "Connection1 or Conference1 missing in conference request %s.", new Object[] {
                    s
                });
            else
            if(connection != getNullConnection())
            {
                s1.onMerge(connection);
            } else
            {
                Log.wtf(this, "There can only be one conference and an attempt was made to merge two conferences.", new Object[0]);
                return;
            }
        } else
        if(conference1 != getNullConference())
            conference1.onMerge(s1);
        else
            onConference(s1, connection);
    }

    private void createConnection(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, boolean flag1)
    {
        Log.d(this, "createConnection, callManagerAccount: %s, callId: %s, request: %s, isIncoming: %b, isUnknown: %b", new Object[] {
            phoneaccounthandle, s, connectionrequest, Boolean.valueOf(flag), Boolean.valueOf(flag1)
        });
        Object obj;
        if(flag1)
            phoneaccounthandle = onCreateUnknownConnection(phoneaccounthandle, connectionrequest);
        else
        if(flag)
            phoneaccounthandle = onCreateIncomingConnection(phoneaccounthandle, connectionrequest);
        else
            phoneaccounthandle = onCreateOutgoingConnection(phoneaccounthandle, connectionrequest);
        Log.d(this, "createConnection, connection: %s", new Object[] {
            phoneaccounthandle
        });
        obj = phoneaccounthandle;
        if(phoneaccounthandle == null)
            obj = Connection.createFailedConnection(new DisconnectCause(1));
        ((Connection) (obj)).setTelecomCallId(s);
        if(mPrevPhoneConnection != null)
        {
            mPrevPhoneConnection = null;
            mAdapter.removeCall(s);
            return;
        }
        if(((Connection) (obj)).getState() != 6)
            addConnection(s, ((Connection) (obj)));
        phoneaccounthandle = ((Connection) (obj)).getAddress();
        ConnectionServiceAdapter connectionserviceadapter;
        PhoneAccountHandle phoneaccounthandle1;
        int i;
        int j;
        int k;
        int l;
        Uri uri;
        int i1;
        String s1;
        int j1;
        if(phoneaccounthandle == null)
            phoneaccounthandle = "null";
        else
            phoneaccounthandle = phoneaccounthandle.getSchemeSpecificPart();
        Log.v(this, "createConnection, number: %s, state: %s, capabilities: %s, properties: %s", new Object[] {
            Connection.toLogSafePhoneNumber(phoneaccounthandle), Connection.stateToString(((Connection) (obj)).getState()), Connection.capabilitiesToString(((Connection) (obj)).getConnectionCapabilities()), Connection.propertiesToString(((Connection) (obj)).getConnectionProperties())
        });
        Log.d(this, "createConnection, calling handleCreateConnectionSuccessful %s", new Object[] {
            s
        });
        connectionserviceadapter = mAdapter;
        phoneaccounthandle1 = connectionrequest.getAccountHandle();
        i = ((Connection) (obj)).getState();
        j = ((Connection) (obj)).getConnectionCapabilities();
        k = ((Connection) (obj)).getConnectionProperties();
        l = ((Connection) (obj)).getSupportedAudioRoutes();
        uri = ((Connection) (obj)).getAddress();
        i1 = ((Connection) (obj)).getAddressPresentation();
        s1 = ((Connection) (obj)).getCallerDisplayName();
        j1 = ((Connection) (obj)).getCallerDisplayNamePresentation();
        if(((Connection) (obj)).getVideoProvider() == null)
            phoneaccounthandle = null;
        else
            phoneaccounthandle = ((Connection) (obj)).getVideoProvider().getInterface();
        connectionserviceadapter.handleCreateConnectionComplete(s, connectionrequest, new ParcelableConnection(phoneaccounthandle1, i, j, k, l, uri, i1, s1, j1, phoneaccounthandle, ((Connection) (obj)).getVideoState(), ((Connection) (obj)).isRingbackRequested(), ((Connection) (obj)).getAudioModeIsVoip(), ((Connection) (obj)).getConnectTimeMillis(), ((Connection) (obj)).getConnectElapsedTimeMillis(), ((Connection) (obj)).getStatusHints(), ((Connection) (obj)).getDisconnectCause(), createIdList(((Connection) (obj)).getConferenceables()), ((Connection) (obj)).getExtras()));
        if(flag && connectionrequest.shouldShowIncomingCallUi() && (((Connection) (obj)).getConnectionProperties() & 0x80) == 128)
            ((Connection) (obj)).onShowIncomingCallUi();
        if(flag1)
            triggerConferenceRecalculate();
    }

    private void createConnectionFailed(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag)
    {
        Log.i(this, "createConnectionFailed %s", new Object[] {
            s
        });
        if(flag)
            onCreateIncomingConnectionFailed(phoneaccounthandle, connectionrequest);
        else
            onCreateOutgoingConnectionFailed(phoneaccounthandle, connectionrequest);
    }

    private List createConnectionIdList(List list)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            list = (Connection)iterator.next();
            if(mIdByConnection.containsKey(list))
                arraylist.add((String)mIdByConnection.get(list));
        } while(true);
        Collections.sort(arraylist);
        return arraylist;
    }

    private List createIdList(List list)
    {
        ArrayList arraylist = new ArrayList();
        list = list.iterator();
        do
        {
            if(!list.hasNext())
                break;
            Object obj = (Conferenceable)list.next();
            if(obj instanceof Connection)
            {
                obj = (Connection)obj;
                if(mIdByConnection.containsKey(obj))
                    arraylist.add((String)mIdByConnection.get(obj));
            } else
            if(obj instanceof Conference)
            {
                obj = (Conference)obj;
                if(mIdByConference.containsKey(obj))
                    arraylist.add((String)mIdByConference.get(obj));
            }
        } while(true);
        Collections.sort(arraylist);
        return arraylist;
    }

    private void disconnect(String s)
    {
        Log.d(this, "disconnect %s", new Object[] {
            s
        });
        if(mConnectionById.containsKey(s))
            findConnectionForAction(s, "disconnect").onDisconnect();
        else
            findConferenceForAction(s, "disconnect").onDisconnect();
    }

    private void endAllConnections()
    {
        Iterator iterator = mIdByConnection.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Connection connection = (Connection)iterator.next();
            if(connection.getConference() == null)
                connection.onDisconnect();
        } while(true);
        for(Iterator iterator1 = mIdByConference.keySet().iterator(); iterator1.hasNext(); ((Conference)iterator1.next()).onDisconnect());
    }

    private Conference findConferenceForAction(String s, String s1)
    {
        if(mConferenceById.containsKey(s))
        {
            return (Conference)mConferenceById.get(s);
        } else
        {
            Log.w(this, "%s - Cannot find conference %s", new Object[] {
                s1, s
            });
            return getNullConference();
        }
    }

    private Connection findConnectionForAction(String s, String s1)
    {
        if(s != null && mConnectionById.containsKey(s))
        {
            return (Connection)mConnectionById.get(s);
        } else
        {
            Log.w(this, "%s - Cannot find Connection %s", new Object[] {
                s1, s
            });
            return getNullConnection();
        }
    }

    private int getNextCallId()
    {
        Object obj = mIdSyncRoot;
        obj;
        JVM INSTR monitorenter ;
        int i;
        i = mId + 1;
        mId = i;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    private Conference getNullConference()
    {
        if(sNullConference == null)
            sNullConference = new Conference(null) {

                final ConnectionService this$0;

            
            {
                this$0 = ConnectionService.this;
                super(phoneaccounthandle);
            }
            }
;
        return sNullConference;
    }

    static Connection getNullConnection()
    {
        android/telecom/ConnectionService;
        JVM INSTR monitorenter ;
        Connection connection1;
        if(sNullConnection == null)
        {
            Connection connection = JVM INSTR new #26  <Class ConnectionService$6>;
            connection._cls6();
            sNullConnection = connection;
        }
        connection1 = sNullConnection;
        android/telecom/ConnectionService;
        JVM INSTR monitorexit ;
        return connection1;
        Exception exception;
        exception;
        throw exception;
    }

    private void handleExtrasChanged(String s, Bundle bundle)
    {
        Log.d(this, "handleExtrasChanged(%s, %s)", new Object[] {
            s, bundle
        });
        if(!mConnectionById.containsKey(s)) goto _L2; else goto _L1
_L1:
        findConnectionForAction(s, "handleExtrasChanged").handleExtrasChanged(bundle);
_L4:
        return;
_L2:
        if(mConferenceById.containsKey(s))
            findConferenceForAction(s, "handleExtrasChanged").handleExtrasChanged(bundle);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void handleRttUpgradeResponse(String s, Connection.RttTextStream rtttextstream)
    {
        boolean flag;
        if(rtttextstream == null)
            flag = true;
        else
            flag = false;
        Log.d(this, "handleRttUpgradeResponse(%s, %s)", new Object[] {
            s, Boolean.valueOf(flag)
        });
        if(!mConnectionById.containsKey(s)) goto _L2; else goto _L1
_L1:
        findConnectionForAction(s, "handleRttUpgradeResponse").handleRttUpgradeResponse(rtttextstream);
_L4:
        return;
_L2:
        if(mConferenceById.containsKey(s))
            Log.w(this, "handleRttUpgradeResponse called on a conference.", new Object[0]);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void hold(String s)
    {
        Log.d(this, "hold %s", new Object[] {
            s
        });
        if(mConnectionById.containsKey(s))
            findConnectionForAction(s, "hold").onHold();
        else
            findConferenceForAction(s, "hold").onHold();
    }

    private void mergeConference(String s)
    {
        Log.d(this, "mergeConference(%s)", new Object[] {
            s
        });
        s = findConferenceForAction(s, "mergeConference");
        if(s != null)
            s.onMerge();
    }

    private void notifyCreateConnectionComplete(String s)
    {
        Log.i(this, "notifyCreateConnectionComplete %s", new Object[] {
            s
        });
        if(s == null)
        {
            Log.w(this, "notifyCreateConnectionComplete: callId is null.", new Object[0]);
            return;
        } else
        {
            onCreateConnectionComplete(findConnectionForAction(s, "notifyCreateConnectionComplete"));
            return;
        }
    }

    private void onAccountsInitialized()
    {
        mAreAccountsInitialized = true;
        for(Iterator iterator = mPreInitializationConnectionRequests.iterator(); iterator.hasNext(); ((Runnable)iterator.next()).run());
        mPreInitializationConnectionRequests.clear();
    }

    private void onAdapterAttached()
    {
        if(mAreAccountsInitialized)
        {
            return;
        } else
        {
            mAdapter.queryRemoteConnectionServices(new com.android.internal.telecom.RemoteServiceCallback.Stub() {

                public void onError()
                {
                    ConnectionService._2D_get2(ConnectionService.this).post((new Runnable("oAA.qRCS.oE", null) {

                        public void loggedRun()
                        {
                            ConnectionService._2D_set0(_fld0, true);
                        }

                        final _cls5 this$1;

            
            {
                this$1 = _cls5.this;
                super(s, obj);
            }
                    }
).prepare());
                }

                public void onResult(List list, List list1)
                {
                    ConnectionService._2D_get2(ConnectionService.this).post((null. new Runnable(list, list1) {

                        public void loggedRun()
                        {
                            for(int i = 0; i < componentNames.size() && i < services.size(); i++)
                                ConnectionService._2D_get6(_fld0).addConnectionService((ComponentName)componentNames.get(i), com.android.internal.telecom.IConnectionService.Stub.asInterface((IBinder)services.get(i)));

                            ConnectionService._2D_wrap15(_fld0);
                            Log.d(this, (new StringBuilder()).append("remote connection services found: ").append(services).toString(), new Object[0]);
                        }

                        final _cls5 this$1;
                        final List val$componentNames;
                        final List val$services;

            
            {
                this$1 = final__pcls5;
                componentNames = list;
                services = list1;
                super(final_s, Object.this);
            }
                    }
).prepare());
                }

                final ConnectionService this$0;

            
            {
                this$0 = ConnectionService.this;
                super();
            }
            }
);
            return;
        }
    }

    private void onCallAudioStateChanged(String s, CallAudioState callaudiostate)
    {
        Log.d(this, "onAudioStateChanged %s %s", new Object[] {
            s, callaudiostate
        });
        if(mConnectionById.containsKey(s))
            findConnectionForAction(s, "onCallAudioStateChanged").setCallAudioState(callaudiostate);
        else
            findConferenceForAction(s, "onCallAudioStateChanged").setCallAudioState(callaudiostate);
    }

    private void onPostDialContinue(String s, boolean flag)
    {
        Log.d(this, "onPostDialContinue(%s)", new Object[] {
            s
        });
        findConnectionForAction(s, "stopDtmfTone").onPostDialContinue(flag);
    }

    private void playDtmfTone(String s, char c)
    {
        Log.d(this, "playDtmfTone %s %c", new Object[] {
            s, Character.valueOf(c)
        });
        if(mConnectionById.containsKey(s))
            findConnectionForAction(s, "playDtmfTone").onPlayDtmfTone(c);
        else
            findConferenceForAction(s, "playDtmfTone").onPlayDtmfTone(c);
    }

    private void pullExternalCall(String s)
    {
        Log.d(this, "pullExternalCall(%s)", new Object[] {
            s
        });
        s = findConnectionForAction(s, "pullExternalCall");
        if(s != null)
            s.onPullExternalCall();
    }

    private void reject(String s)
    {
        Log.d(this, "reject %s", new Object[] {
            s
        });
        findConnectionForAction(s, "reject").onReject();
    }

    private void reject(String s, String s1)
    {
        Log.d(this, "reject %s with message", new Object[] {
            s
        });
        findConnectionForAction(s, "reject").onReject(s1);
    }

    private void removeConference(Conference conference1)
    {
        if(mIdByConference.containsKey(conference1))
        {
            conference1.removeListener(mConferenceListener);
            String s = (String)mIdByConference.get(conference1);
            mConferenceById.remove(s);
            mIdByConference.remove(conference1);
            mAdapter.removeCall(s);
        }
    }

    private void sendCallEvent(String s, String s1, Bundle bundle)
    {
        Log.d(this, "sendCallEvent(%s, %s)", new Object[] {
            s, s1
        });
        s = findConnectionForAction(s, "sendCallEvent");
        if(s != null)
            s.onCallEvent(s1, bundle);
    }

    private void silence(String s)
    {
        Log.d(this, "silence %s", new Object[] {
            s
        });
        findConnectionForAction(s, "silence").onSilence();
    }

    private void splitFromConference(String s)
    {
        Log.d(this, "splitFromConference(%s)", new Object[] {
            s
        });
        Connection connection = findConnectionForAction(s, "splitFromConference");
        if(connection == getNullConnection())
        {
            Log.w(this, "Connection missing in conference request %s.", new Object[] {
                s
            });
            return;
        }
        s = connection.getConference();
        if(s != null)
            s.onSeparate(connection);
    }

    private void startRtt(String s, Connection.RttTextStream rtttextstream)
    {
        Log.d(this, "startRtt(%s)", new Object[] {
            s
        });
        if(!mConnectionById.containsKey(s)) goto _L2; else goto _L1
_L1:
        findConnectionForAction(s, "startRtt").onStartRtt(rtttextstream);
_L4:
        return;
_L2:
        if(mConferenceById.containsKey(s))
            Log.w(this, "startRtt called on a conference.", new Object[0]);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void stopDtmfTone(String s)
    {
        Log.d(this, "stopDtmfTone %s", new Object[] {
            s
        });
        if(mConnectionById.containsKey(s))
            findConnectionForAction(s, "stopDtmfTone").onStopDtmfTone();
        else
            findConferenceForAction(s, "stopDtmfTone").onStopDtmfTone();
    }

    private void stopRtt(String s)
    {
        Log.d(this, "stopRtt(%s)", new Object[] {
            s
        });
        if(!mConnectionById.containsKey(s)) goto _L2; else goto _L1
_L1:
        findConnectionForAction(s, "stopRtt").onStopRtt();
        findConnectionForAction(s, "stopRtt").unsetRttProperty();
_L4:
        return;
_L2:
        if(mConferenceById.containsKey(s))
            Log.w(this, "stopRtt called on a conference.", new Object[0]);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void swapConference(String s)
    {
        Log.d(this, "swapConference(%s)", new Object[] {
            s
        });
        s = findConferenceForAction(s, "swapConference");
        if(s != null)
            s.onSwap();
    }

    private void unhold(String s)
    {
        Log.d(this, "unhold %s", new Object[] {
            s
        });
        if(mConnectionById.containsKey(s))
            findConnectionForAction(s, "unhold").onUnhold();
        else
            findConferenceForAction(s, "unhold").onUnhold();
    }

    public final void addConference(Conference conference1)
    {
        Log.d(this, "addConference: conference=%s", new Object[] {
            conference1
        });
        String s = addConferenceInternal(conference1);
        if(s != null)
        {
            ArrayList arraylist = new ArrayList(2);
            Object obj = conference1.getConnections().iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                Connection connection = (Connection)((Iterator) (obj)).next();
                if(mIdByConnection.containsKey(connection))
                    arraylist.add((String)mIdByConnection.get(connection));
            } while(true);
            conference1.setTelecomCallId(s);
            PhoneAccountHandle phoneaccounthandle = conference1.getPhoneAccountHandle();
            int i = conference1.getState();
            int j = conference1.getConnectionCapabilities();
            int k = conference1.getConnectionProperties();
            if(conference1.getVideoProvider() == null)
                obj = null;
            else
                obj = conference1.getVideoProvider().getInterface();
            obj = new ParcelableConference(phoneaccounthandle, i, j, k, arraylist, ((com.android.internal.telecom.IVideoProvider) (obj)), conference1.getVideoState(), conference1.getConnectTimeMillis(), conference1.getConnectElapsedTime(), conference1.getStatusHints(), conference1.getExtras());
            mAdapter.addConferenceCall(s, ((ParcelableConference) (obj)));
            mAdapter.setVideoProvider(s, conference1.getVideoProvider());
            mAdapter.setVideoState(s, conference1.getVideoState());
            conference1 = conference1.getConnections().iterator();
            do
            {
                if(!conference1.hasNext())
                    break;
                obj = (Connection)conference1.next();
                obj = (String)mIdByConnection.get(obj);
                if(obj != null)
                    mAdapter.setIsConferenced(((String) (obj)), s);
            } while(true);
        }
    }

    public final void addExistingConnection(PhoneAccountHandle phoneaccounthandle, Connection connection)
    {
        addExistingConnection(phoneaccounthandle, connection, null);
    }

    public final void addExistingConnection(PhoneAccountHandle phoneaccounthandle, Connection connection, Conference conference1)
    {
        String s = addExistingConnectionInternal(phoneaccounthandle, connection);
        if(s != null)
        {
            ArrayList arraylist = new ArrayList(0);
            String s1 = null;
            if(conference1 != null)
                s1 = (String)mIdByConference.get(conference1);
            int i = connection.getState();
            int j = connection.getConnectionCapabilities();
            int k = connection.getConnectionProperties();
            int l = connection.getSupportedAudioRoutes();
            Uri uri = connection.getAddress();
            int i1 = connection.getAddressPresentation();
            String s2 = connection.getCallerDisplayName();
            int j1 = connection.getCallerDisplayNamePresentation();
            if(connection.getVideoProvider() == null)
                conference1 = null;
            else
                conference1 = connection.getVideoProvider().getInterface();
            phoneaccounthandle = new ParcelableConnection(phoneaccounthandle, i, j, k, l, uri, i1, s2, j1, conference1, connection.getVideoState(), connection.isRingbackRequested(), connection.getAudioModeIsVoip(), connection.getConnectTimeMillis(), connection.getConnectElapsedTimeMillis(), connection.getStatusHints(), connection.getDisconnectCause(), arraylist, connection.getExtras(), s1);
            mAdapter.addExistingConnection(s, phoneaccounthandle);
        }
    }

    void addRemoteConference(RemoteConference remoteconference)
    {
        onRemoteConferenceAdded(remoteconference);
    }

    void addRemoteExistingConnection(RemoteConnection remoteconnection)
    {
        onRemoteExistingConnectionAdded(remoteconnection);
    }

    public final void conferenceRemoteConnections(RemoteConnection remoteconnection, RemoteConnection remoteconnection1)
    {
        mRemoteConnectionManager.conferenceRemoteConnections(remoteconnection, remoteconnection1);
    }

    public boolean containsConference(Conference conference1)
    {
        return mIdByConference.containsKey(conference1);
    }

    public final RemoteConnection createRemoteIncomingConnection(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest)
    {
        return mRemoteConnectionManager.createRemoteConnection(phoneaccounthandle, connectionrequest, true);
    }

    public final RemoteConnection createRemoteOutgoingConnection(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest)
    {
        return mRemoteConnectionManager.createRemoteConnection(phoneaccounthandle, connectionrequest, false);
    }

    public final Collection getAllConferences()
    {
        return mConferenceById.values();
    }

    public final Collection getAllConnections()
    {
        return mConnectionById.values();
    }

    public void onAddParticipant(Connection connection, String s)
    {
    }

    public final IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    public void onConference(Connection connection, Connection connection1)
    {
    }

    public void onCreateConnectionComplete(Connection connection)
    {
    }

    public Connection onCreateIncomingConnection(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest)
    {
        return null;
    }

    public void onCreateIncomingConnectionFailed(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest)
    {
    }

    public Connection onCreateOutgoingConnection(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest)
    {
        return null;
    }

    public void onCreateOutgoingConnectionFailed(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest)
    {
    }

    public Connection onCreateUnknownConnection(PhoneAccountHandle phoneaccounthandle, ConnectionRequest connectionrequest)
    {
        return null;
    }

    public void onRemoteConferenceAdded(RemoteConference remoteconference)
    {
    }

    public void onRemoteExistingConnectionAdded(RemoteConnection remoteconnection)
    {
    }

    public boolean onUnbind(Intent intent)
    {
        endAllConnections();
        return super.onUnbind(intent);
    }

    protected void removeConnection(Connection connection)
    {
        connection.unsetConnectionService(this);
        connection.removeConnectionListener(mConnectionListener);
        String s = (String)mIdByConnection.get(connection);
        if(s != null)
        {
            mConnectionById.remove(s);
            mIdByConnection.remove(connection);
            mAdapter.removeCall(s);
        }
    }

    public void triggerConferenceRecalculate()
    {
    }

    protected void updateConnection(Connection connection, Connection connection1)
    {
        Log.d(this, (new StringBuilder()).append("updateConnection: ").append(connection1).toString(), new Object[0]);
        if(mIdByConnection.containsKey(connection))
        {
            String s = (String)mIdByConnection.get(connection);
            if(mConnectionById.containsKey(s))
            {
                Log.d(this, "Replacing old connection with new connection", new Object[0]);
                mConnectionById.put(s, connection1);
                mIdByConnection.remove(connection);
                mIdByConnection.put(connection1, s);
                connection1.addConnectionListener(mConnectionListener);
                connection1.setConnectionService(this);
            }
        } else
        {
            Log.d(this, "Connection not found", new Object[0]);
        }
    }

    public static final String EXTRA_IS_HANDOVER = "android.telecom.extra.IS_HANDOVER";
    private static final int MSG_ABORT = 3;
    private static final int MSG_ADD_CONNECTION_SERVICE_ADAPTER = 1;
    private static final int MSG_ADD_PARTICIPANT_WITH_CONFERENCE = 40;
    private static final int MSG_ANSWER = 4;
    private static final int MSG_ANSWER_VIDEO = 17;
    private static final int MSG_CONFERENCE = 12;
    private static final int MSG_CREATE_CONNECTION = 2;
    private static final int MSG_CREATE_CONNECTION_COMPLETE = 29;
    private static final int MSG_CREATE_CONNECTION_FAILED = 25;
    private static final int MSG_DISCONNECT = 6;
    private static final int MSG_HOLD = 7;
    private static final int MSG_MERGE_CONFERENCE = 18;
    private static final int MSG_ON_CALL_AUDIO_STATE_CHANGED = 9;
    private static final int MSG_ON_EXTRAS_CHANGED = 24;
    private static final int MSG_ON_POST_DIAL_CONTINUE = 14;
    private static final int MSG_ON_START_RTT = 26;
    private static final int MSG_ON_STOP_RTT = 27;
    private static final int MSG_PLAY_DTMF_TONE = 10;
    private static final int MSG_PULL_EXTERNAL_CALL = 22;
    private static final int MSG_REJECT = 5;
    private static final int MSG_REJECT_WITH_MESSAGE = 20;
    private static final int MSG_REMOVE_CONNECTION_SERVICE_ADAPTER = 16;
    private static final int MSG_RTT_UPGRADE_RESPONSE = 28;
    private static final int MSG_SEND_CALL_EVENT = 23;
    private static final int MSG_SILENCE = 21;
    private static final int MSG_SPLIT_FROM_CONFERENCE = 13;
    private static final int MSG_STOP_DTMF_TONE = 11;
    private static final int MSG_SWAP_CONFERENCE = 19;
    private static final int MSG_UNHOLD = 8;
    private static final boolean PII_DEBUG = Log.isLoggable(3);
    public static final String SERVICE_INTERFACE = "android.telecom.ConnectionService";
    private static final String SESSION_ABORT = "CS.ab";
    private static final String SESSION_ADD_CS_ADAPTER = "CS.aCSA";
    private static final String SESSION_ANSWER = "CS.an";
    private static final String SESSION_ANSWER_VIDEO = "CS.anV";
    private static final String SESSION_CALL_AUDIO_SC = "CS.cASC";
    private static final String SESSION_CONFERENCE = "CS.c";
    private static final String SESSION_CREATE_CONN = "CS.crCo";
    private static final String SESSION_CREATE_CONN_COMPLETE = "CS.crCoC";
    private static final String SESSION_CREATE_CONN_FAILED = "CS.crCoF";
    private static final String SESSION_DISCONNECT = "CS.d";
    private static final String SESSION_EXTRAS_CHANGED = "CS.oEC";
    private static final String SESSION_HANDLER = "H.";
    private static final String SESSION_HOLD = "CS.h";
    private static final String SESSION_MERGE_CONFERENCE = "CS.mC";
    private static final String SESSION_PLAY_DTMF = "CS.pDT";
    private static final String SESSION_POST_DIAL_CONT = "CS.oPDC";
    private static final String SESSION_PULL_EXTERNAL_CALL = "CS.pEC";
    private static final String SESSION_REJECT = "CS.r";
    private static final String SESSION_REJECT_MESSAGE = "CS.rWM";
    private static final String SESSION_REMOVE_CS_ADAPTER = "CS.rCSA";
    private static final String SESSION_RTT_UPGRADE_RESPONSE = "CS.rTRUR";
    private static final String SESSION_SEND_CALL_EVENT = "CS.sCE";
    private static final String SESSION_SILENCE = "CS.s";
    private static final String SESSION_SPLIT_CONFERENCE = "CS.sFC";
    private static final String SESSION_START_RTT = "CS.+RTT";
    private static final String SESSION_STOP_DTMF = "CS.sDT";
    private static final String SESSION_STOP_RTT = "CS.-RTT";
    private static final String SESSION_SWAP_CONFERENCE = "CS.sC";
    private static final String SESSION_UNHOLD = "CS.u";
    private static Connection sNullConnection;
    private final ConnectionServiceAdapter mAdapter = new ConnectionServiceAdapter();
    private boolean mAreAccountsInitialized;
    private final IBinder mBinder = new com.android.internal.telecom.IConnectionService.Stub() {

        public void abort(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.ab");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(3, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void addConnectionServiceAdapter(IConnectionServiceAdapter iconnectionserviceadapter, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.aCSA");
            info = SomeArgs.obtain();
            info.arg1 = iconnectionserviceadapter;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(1, info).sendToTarget();
            Log.endSession();
            return;
            iconnectionserviceadapter;
            Log.endSession();
            throw iconnectionserviceadapter;
        }

        public void addParticipantWithConference(String s, String s1)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.arg2 = s1;
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(40, someargs).sendToTarget();
        }

        public void answer(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.an");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(4, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void answerVideo(String s, int i, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.anV");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            info.argi1 = i;
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(17, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void conference(String s, String s1, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.c");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = s1;
            info.arg3 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(12, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void createConnection(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, boolean flag1, android.telecom.Logging.Session.Info info)
        {
            boolean flag2;
            flag2 = true;
            Log.startSession(info, "CS.crCo");
            info = SomeArgs.obtain();
            info.arg1 = phoneaccounthandle;
            info.arg2 = s;
            info.arg3 = connectionrequest;
            info.arg4 = Log.createSubsession();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            info.argi1 = i;
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            info.argi2 = i;
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(2, info).sendToTarget();
            Log.endSession();
            return;
            phoneaccounthandle;
            Log.endSession();
            throw phoneaccounthandle;
        }

        public void createConnectionComplete(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.crCoC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(29, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void createConnectionFailed(PhoneAccountHandle phoneaccounthandle, String s, ConnectionRequest connectionrequest, boolean flag, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.crCoF");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = connectionrequest;
            info.arg3 = Log.createSubsession();
            info.arg4 = phoneaccounthandle;
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            info.argi1 = i;
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(25, info).sendToTarget();
            Log.endSession();
            return;
            phoneaccounthandle;
            Log.endSession();
            throw phoneaccounthandle;
        }

        public void disconnect(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.d");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(6, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void hold(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.h");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(7, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void mergeConference(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.mC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(18, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void onCallAudioStateChanged(String s, CallAudioState callaudiostate, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.cASC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = callaudiostate;
            info.arg3 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(9, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void onExtrasChanged(String s, Bundle bundle, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.oEC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = bundle;
            info.arg3 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(24, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void onPostDialContinue(String s, boolean flag, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.oPDC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            info.argi1 = i;
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(14, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void playDtmfTone(String s, char c, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.pDT");
            info = SomeArgs.obtain();
            info.arg1 = Character.valueOf(c);
            info.arg2 = s;
            info.arg3 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(10, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void pullExternalCall(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.pEC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(22, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void reject(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.r");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(5, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void rejectWithMessage(String s, String s1, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.rWM");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = s1;
            info.arg3 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(20, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void removeConnectionServiceAdapter(IConnectionServiceAdapter iconnectionserviceadapter, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.rCSA");
            info = SomeArgs.obtain();
            info.arg1 = iconnectionserviceadapter;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(16, info).sendToTarget();
            Log.endSession();
            return;
            iconnectionserviceadapter;
            Log.endSession();
            throw iconnectionserviceadapter;
        }

        public void respondToRttUpgradeRequest(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Log.startSession(info, "CS.rTRUR");
            info = SomeArgs.obtain();
            info.arg1 = s;
            if(parcelfiledescriptor1 != null && parcelfiledescriptor != null)
                break MISSING_BLOCK_LABEL_61;
            info.arg2 = null;
_L1:
            info.arg3 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(28, info).sendToTarget();
            Log.endSession();
            return;
            s = JVM INSTR new #153 <Class Connection$RttTextStream>;
            s.Connection.RttTextStream(parcelfiledescriptor1, parcelfiledescriptor);
            info.arg2 = s;
              goto _L1
            s;
            Log.endSession();
            throw s;
        }

        public void sendCallEvent(String s, String s1, Bundle bundle, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.sCE");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = s1;
            info.arg3 = bundle;
            info.arg4 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(23, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void silence(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.s");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(21, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void splitFromConference(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.sFC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(13, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void startRtt(String s, ParcelFileDescriptor parcelfiledescriptor, ParcelFileDescriptor parcelfiledescriptor1, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Log.startSession(info, "CS.+RTT");
            info = SomeArgs.obtain();
            info.arg1 = s;
            s = JVM INSTR new #153 <Class Connection$RttTextStream>;
            s.Connection.RttTextStream(parcelfiledescriptor1, parcelfiledescriptor);
            info.arg2 = s;
            info.arg3 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(26, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void stopDtmfTone(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.sDT");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(11, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void stopRtt(String s, android.telecom.Logging.Session.Info info)
            throws RemoteException
        {
            Log.startSession(info, "CS.-RTT");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(27, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void swapConference(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.sC");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(19, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        public void unhold(String s, android.telecom.Logging.Session.Info info)
        {
            Log.startSession(info, "CS.u");
            info = SomeArgs.obtain();
            info.arg1 = s;
            info.arg2 = Log.createSubsession();
            ConnectionService._2D_get2(ConnectionService.this).obtainMessage(8, info).sendToTarget();
            Log.endSession();
            return;
            s;
            Log.endSession();
            throw s;
        }

        final ConnectionService this$0;

            
            {
                this$0 = ConnectionService.this;
                super();
            }
    }
;
    private final Map mConferenceById = new ConcurrentHashMap();
    private final Conference.Listener mConferenceListener = new Conference.Listener() {

        public void onConferenceableConnectionsChanged(Conference conference1, List list)
        {
            ConnectionService._2D_get0(ConnectionService.this).setConferenceableConnections((String)ConnectionService._2D_get3(ConnectionService.this).get(conference1), ConnectionService._2D_wrap0(ConnectionService.this, list));
        }

        public void onConnectionAdded(Conference conference1, Connection connection)
        {
        }

        public void onConnectionCapabilitiesChanged(Conference conference1, int i)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            Log.d(this, "call capabilities: conference: %s", new Object[] {
                Connection.capabilitiesToString(i)
            });
            ConnectionService._2D_get0(ConnectionService.this).setConnectionCapabilities(conference1, i);
        }

        public void onConnectionPropertiesChanged(Conference conference1, int i)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            Log.d(this, "call capabilities: conference: %s", new Object[] {
                Connection.propertiesToString(i)
            });
            ConnectionService._2D_get0(ConnectionService.this).setConnectionProperties(conference1, i);
        }

        public void onConnectionRemoved(Conference conference1, Connection connection)
        {
        }

        public void onDestroyed(Conference conference1)
        {
            ConnectionService._2D_wrap23(ConnectionService.this, conference1);
        }

        public void onDisconnected(Conference conference1, DisconnectCause disconnectcause)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            ConnectionService._2D_get0(ConnectionService.this).setDisconnected(conference1, disconnectcause);
        }

        public void onExtrasChanged(Conference conference1, Bundle bundle)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            if(conference1 != null)
                ConnectionService._2D_get0(ConnectionService.this).putExtras(conference1, bundle);
        }

        public void onExtrasRemoved(Conference conference1, List list)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            if(conference1 != null)
                ConnectionService._2D_get0(ConnectionService.this).removeExtras(conference1, list);
        }

        public void onStateChanged(Conference conference1, int i, int j)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            j;
            JVM INSTR tableswitch 4 6: default 44
        //                       4 45
        //                       5 59
        //                       6 44;
               goto _L1 _L2 _L3 _L1
_L1:
            return;
_L2:
            ConnectionService._2D_get0(ConnectionService.this).setActive(conference1);
            continue; /* Loop/switch isn't completed */
_L3:
            ConnectionService._2D_get0(ConnectionService.this).setOnHold(conference1);
            if(true) goto _L1; else goto _L4
_L4:
        }

        public void onStatusHintsChanged(Conference conference1, StatusHints statushints)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            if(conference1 != null)
                ConnectionService._2D_get0(ConnectionService.this).setStatusHints(conference1, statushints);
        }

        public void onVideoProviderChanged(Conference conference1, Connection.VideoProvider videoprovider)
        {
            String s = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            Log.d(this, "onVideoProviderChanged: Connection: %s, VideoProvider: %s", new Object[] {
                conference1, videoprovider
            });
            ConnectionService._2D_get0(ConnectionService.this).setVideoProvider(s, videoprovider);
        }

        public void onVideoStateChanged(Conference conference1, int i)
        {
            conference1 = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
            Log.d(this, "onVideoStateChanged set video state %d", new Object[] {
                Integer.valueOf(i)
            });
            ConnectionService._2D_get0(ConnectionService.this).setVideoState(conference1, i);
        }

        final ConnectionService this$0;

            
            {
                this$0 = ConnectionService.this;
                super();
            }
    }
;
    private final Map mConnectionById = new ConcurrentHashMap();
    private final Connection.Listener mConnectionListener = new Connection.Listener() {

        public void onAddressChanged(Connection connection, Uri uri, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            ConnectionService._2D_get0(ConnectionService.this).setAddress(connection, uri, i);
        }

        public void onAudioModeIsVoipChanged(Connection connection, boolean flag)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            ConnectionService._2D_get0(ConnectionService.this).setIsVoipAudioMode(connection, flag);
        }

        public void onAudioRouteChanged(Connection connection, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).setAudioRoute(connection, i);
        }

        public void onCallerDisplayNameChanged(Connection connection, String s, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            ConnectionService._2D_get0(ConnectionService.this).setCallerDisplayName(connection, s, i);
        }

        public void onCdmaConnectionTimeReset(Connection connection)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            ConnectionService._2D_get0(ConnectionService.this).resetCdmaConnectionTime(connection);
        }

        public void onConferenceChanged(Connection connection, Conference conference1)
        {
            String s = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(s != null)
            {
                connection = null;
                if(conference1 != null)
                    connection = (String)ConnectionService._2D_get3(ConnectionService.this).get(conference1);
                ConnectionService._2D_get0(ConnectionService.this).setIsConferenced(s, connection);
            }
        }

        public void onConferenceMergeFailed(Connection connection)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).onConferenceMergeFailed(connection);
        }

        public void onConferenceablesChanged(Connection connection, List list)
        {
            ConnectionService._2D_get0(ConnectionService.this).setConferenceableConnections((String)ConnectionService._2D_get4(ConnectionService.this).get(connection), ConnectionService._2D_wrap1(ConnectionService.this, list));
        }

        public void onConnectionCapabilitiesChanged(Connection connection, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "capabilities: parcelableconnection: %s", new Object[] {
                Connection.capabilitiesToString(i)
            });
            ConnectionService._2D_get0(ConnectionService.this).setConnectionCapabilities(connection, i);
        }

        public void onConnectionEvent(Connection connection, String s, Bundle bundle)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).onConnectionEvent(connection, s, bundle);
        }

        public void onConnectionPropertiesChanged(Connection connection, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "properties: parcelableconnection: %s", new Object[] {
                Connection.propertiesToString(i)
            });
            ConnectionService._2D_get0(ConnectionService.this).setConnectionProperties(connection, i);
        }

        public void onDestroyed(Connection connection)
        {
            removeConnection(connection);
        }

        public void onDisconnected(Connection connection, DisconnectCause disconnectcause)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "Adapter set disconnected %s", new Object[] {
                disconnectcause
            });
            ConnectionService._2D_get0(ConnectionService.this).setDisconnected(connection, disconnectcause);
        }

        public void onExtrasChanged(Connection connection, Bundle bundle)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).putExtras(connection, bundle);
        }

        public void onExtrasRemoved(Connection connection, List list)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).removeExtras(connection, list);
        }

        public void onPostDialChar(Connection connection, char c)
        {
            String s = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "Adapter onPostDialChar %s, %s", new Object[] {
                connection, Character.valueOf(c)
            });
            ConnectionService._2D_get0(ConnectionService.this).onPostDialChar(s, c);
        }

        public void onPostDialWait(Connection connection, String s)
        {
            String s1 = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "Adapter onPostDialWait %s, %s", new Object[] {
                connection, s
            });
            ConnectionService._2D_get0(ConnectionService.this).onPostDialWait(s1, s);
        }

        public void onRemoteRttRequest(Connection connection)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).onRemoteRttRequest(connection);
        }

        public void onRingbackRequested(Connection connection, boolean flag)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "Adapter onRingback %b", new Object[] {
                Boolean.valueOf(flag)
            });
            ConnectionService._2D_get0(ConnectionService.this).setRingbackRequested(connection, flag);
        }

        public void onRttInitiationFailure(Connection connection, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).onRttInitiationFailure(connection, i);
        }

        public void onRttInitiationSuccess(Connection connection)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).onRttInitiationSuccess(connection);
        }

        public void onRttSessionRemotelyTerminated(Connection connection)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            if(connection != null)
                ConnectionService._2D_get0(ConnectionService.this).onRttSessionRemotelyTerminated(connection);
        }

        public void onStateChanged(Connection connection, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "Adapter set state %s %s", new Object[] {
                connection, Connection.stateToString(i)
            });
            i;
            JVM INSTR tableswitch 1 7: default 80
        //                       1 80
        //                       2 137
        //                       3 95
        //                       4 81
        //                       5 123
        //                       6 80
        //                       7 109;
               goto _L1 _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L1:
            return;
_L4:
            ConnectionService._2D_get0(ConnectionService.this).setActive(connection);
            continue; /* Loop/switch isn't completed */
_L3:
            ConnectionService._2D_get0(ConnectionService.this).setDialing(connection);
            continue; /* Loop/switch isn't completed */
_L6:
            ConnectionService._2D_get0(ConnectionService.this).setPulling(connection);
            continue; /* Loop/switch isn't completed */
_L5:
            ConnectionService._2D_get0(ConnectionService.this).setOnHold(connection);
            continue; /* Loop/switch isn't completed */
_L2:
            ConnectionService._2D_get0(ConnectionService.this).setRinging(connection);
            if(true) goto _L1; else goto _L7
_L7:
        }

        public void onStatusHintsChanged(Connection connection, StatusHints statushints)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            ConnectionService._2D_get0(ConnectionService.this).setStatusHints(connection, statushints);
        }

        public void onVideoProviderChanged(Connection connection, Connection.VideoProvider videoprovider)
        {
            String s = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "onVideoProviderChanged: Connection: %s, VideoProvider: %s", new Object[] {
                connection, videoprovider
            });
            ConnectionService._2D_get0(ConnectionService.this).setVideoProvider(s, videoprovider);
        }

        public void onVideoStateChanged(Connection connection, int i)
        {
            connection = (String)ConnectionService._2D_get4(ConnectionService.this).get(connection);
            Log.d(this, "Adapter set video state %d", new Object[] {
                Integer.valueOf(i)
            });
            ConnectionService._2D_get0(ConnectionService.this).setVideoState(connection, i);
        }

        final ConnectionService this$0;

            
            {
                this$0 = ConnectionService.this;
                super();
            }
    }
;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 40: default 180
        //                       1 181
        //                       2 304
        //                       3 790
        //                       4 844
        //                       5 962
        //                       6 1077
        //                       7 1185
        //                       8 1239
        //                       9 1293
        //                       10 1375
        //                       11 1439
        //                       12 1542
        //                       13 1609
        //                       14 1772
        //                       15 180
        //                       16 247
        //                       17 898
        //                       18 1663
        //                       19 1717
        //                       20 1016
        //                       21 1131
        //                       22 1848
        //                       23 1903
        //                       24 1982
        //                       25 611
        //                       26 2050
        //                       27 2118
        //                       28 2175
        //                       29 491
        //                       30 180
        //                       31 180
        //                       32 180
        //                       33 180
        //                       34 180
        //                       35 180
        //                       36 180
        //                       37 180
        //                       38 180
        //                       39 180
        //                       40 1493;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L1 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L30
_L1:
            return;
_L2:
            message = (SomeArgs)message.obj;
            IConnectionServiceAdapter iconnectionserviceadapter = (IConnectionServiceAdapter)((SomeArgs) (message)).arg1;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.aCSA");
            ConnectionService._2D_get0(ConnectionService.this).addAdapter(iconnectionserviceadapter);
            ConnectionService._2D_wrap16(ConnectionService.this);
            message.recycle();
            Log.endSession();
              goto _L1
            Exception exception;
            exception;
            message.recycle();
            Log.endSession();
            throw exception;
_L16:
            Object obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg2, "H.CS.rCSA");
            ConnectionService._2D_get0(ConnectionService.this).removeAdapter((IConnectionServiceAdapter)((SomeArgs) (obj)).arg1);
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L3:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg4, "H.CS.crCo");
            PhoneAccountHandle phoneaccounthandle;
            Object obj1;
            Object obj2;
            phoneaccounthandle = (PhoneAccountHandle)((SomeArgs) (message)).arg1;
            obj1 = (String)((SomeArgs) (message)).arg2;
            obj2 = (ConnectionRequest)((SomeArgs) (message)).arg3;
            boolean flag;
            boolean flag1;
            if(((SomeArgs) (message)).argi1 == 1)
                flag = true;
            else
                flag = false;
            if(((SomeArgs) (message)).argi2 == 1)
                flag1 = true;
            else
                flag1 = false;
            if(ConnectionService._2D_get1(ConnectionService.this)) goto _L32; else goto _L31
_L31:
            Log.d(this, "Enqueueing pre-init request %s", new Object[] {
                obj1
            });
            obj = ConnectionService._2D_get5(ConnectionService.this);
            Runnable runnable = JVM INSTR new #8   <Class ConnectionService$2$1>;
            ((_cls1) (obj1)).obj2. _cls1(flag, flag1);
            ((List) (obj)).add(runnable.prepare());
_L33:
            message.recycle();
            Log.endSession();
              goto _L1
_L32:
            ConnectionService._2D_wrap8(ConnectionService.this, phoneaccounthandle, ((String) (obj1)), ((ConnectionRequest) (obj2)), flag, flag1);
              goto _L33
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L29:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.crCoC");
            obj = (String)((SomeArgs) (message)).arg1;
            if(ConnectionService._2D_get1(ConnectionService.this)) goto _L35; else goto _L34
_L34:
            Log.d(this, "Enqueueing pre-init request %s", new Object[] {
                obj
            });
            obj2 = ConnectionService._2D_get5(ConnectionService.this);
            Runnable runnable1 = JVM INSTR new #10  <Class ConnectionService$2$2>;
            "H.CS.crCoC.pICR". super(null, ((String) (obj)));
            ((List) (obj2)).add(runnable1.prepare());
_L36:
            message.recycle();
            Log.endSession();
              goto _L1
_L35:
            ConnectionService._2D_wrap14(ConnectionService.this, ((String) (obj)));
              goto _L36
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L25:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg3, "H.CS.crCoF");
            obj = (String)((SomeArgs) (message)).arg1;
            obj1 = (ConnectionRequest)((SomeArgs) (message)).arg2;
            if(((SomeArgs) (message)).argi1 == 1)
                flag = true;
            else
                flag = false;
            obj2 = (PhoneAccountHandle)((SomeArgs) (message)).arg4;
            if(ConnectionService._2D_get1(ConnectionService.this)) goto _L38; else goto _L37
_L37:
            Log.d(this, "Enqueueing pre-init request %s", new Object[] {
                obj
            });
            List list = ConnectionService._2D_get5(ConnectionService.this);
            Runnable runnable2 = JVM INSTR new #12  <Class ConnectionService$2$3>;
            ((_cls3) (obj2)).obj. _cls3(((ConnectionRequest) (obj1)), flag);
            list.add(runnable2.prepare());
_L39:
            message.recycle();
            Log.endSession();
              goto _L1
_L38:
            Log.i(this, "createConnectionFailed %s", new Object[] {
                obj
            });
            ConnectionService._2D_wrap7(ConnectionService.this, ((PhoneAccountHandle) (obj2)), ((String) (obj)), ((ConnectionRequest) (obj1)), flag);
              goto _L39
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L4:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.ab");
            ConnectionService._2D_wrap2(ConnectionService.this, (String)((SomeArgs) (message)).arg1);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L5:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.an");
            ConnectionService._2D_wrap5(ConnectionService.this, (String)((SomeArgs) (message)).arg1);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L17:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.anV");
            obj = (String)((SomeArgs) (message)).arg1;
            int i = ((SomeArgs) (message)).argi1;
            ConnectionService._2D_wrap4(ConnectionService.this, ((String) (obj)), i);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L6:
            obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg2, "H.CS.r");
            ConnectionService._2D_wrap21(ConnectionService.this, (String)((SomeArgs) (obj)).arg1);
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L20:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg3, "H.CS.rWM");
            ConnectionService._2D_wrap22(ConnectionService.this, (String)((SomeArgs) (message)).arg1, (String)((SomeArgs) (message)).arg2);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L7:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.d");
            ConnectionService._2D_wrap9(ConnectionService.this, (String)((SomeArgs) (message)).arg1);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L21:
            obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg2, "H.CS.s");
            ConnectionService._2D_wrap25(ConnectionService.this, (String)((SomeArgs) (obj)).arg1);
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L8:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.r");
            ConnectionService._2D_wrap12(ConnectionService.this, (String)((SomeArgs) (message)).arg1);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L9:
            obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg2, "H.CS.u");
            ConnectionService._2D_wrap31(ConnectionService.this, (String)((SomeArgs) (obj)).arg1);
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L10:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg3, "H.CS.cASC");
            String s4 = (String)((SomeArgs) (message)).arg1;
            CallAudioState callaudiostate = (CallAudioState)((SomeArgs) (message)).arg2;
            ConnectionService connectionservice = ConnectionService.this;
            obj = JVM INSTR new #213 <Class CallAudioState>;
            ((CallAudioState) (obj)).CallAudioState(callaudiostate);
            ConnectionService._2D_wrap17(connectionservice, s4, ((CallAudioState) (obj)));
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L11:
            obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg3, "H.CS.pDT");
            ConnectionService._2D_wrap19(ConnectionService.this, (String)((SomeArgs) (obj)).arg2, ((Character)((SomeArgs) (obj)).arg1).charValue());
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L12:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.sDT");
            ConnectionService._2D_wrap28(ConnectionService.this, (String)((SomeArgs) (message)).arg1);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L30:
            message = (SomeArgs)message.obj;
            String s5 = (String)((SomeArgs) (message)).arg1;
            obj = (String)((SomeArgs) (message)).arg2;
            ConnectionService._2D_wrap3(ConnectionService.this, s5, ((String) (obj)));
            message.recycle();
              goto _L1
            obj;
            message.recycle();
            throw obj;
_L13:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg3, "H.CS.c");
            obj = (String)((SomeArgs) (message)).arg1;
            String s6 = (String)((SomeArgs) (message)).arg2;
            ConnectionService._2D_wrap6(ConnectionService.this, ((String) (obj)), s6);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L14:
            obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg2, "H.CS.sFC");
            ConnectionService._2D_wrap26(ConnectionService.this, (String)((SomeArgs) (obj)).arg1);
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L18:
            obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg2, "H.CS.mC");
            ConnectionService._2D_wrap13(ConnectionService.this, (String)((SomeArgs) (obj)).arg1);
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L19:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.sC");
            ConnectionService._2D_wrap30(ConnectionService.this, (String)((SomeArgs) (message)).arg1);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L15:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.oPDC");
            obj = (String)((SomeArgs) (message)).arg1;
            if(((SomeArgs) (message)).argi1 == 1)
                flag = true;
            else
                flag = false;
            ConnectionService._2D_wrap18(ConnectionService.this, ((String) (obj)), flag);
            message.recycle();
            Log.endSession();
              goto _L1
            obj;
            message.recycle();
            Log.endSession();
            throw obj;
_L22:
            obj = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (obj)).arg2, "H.CS.pEC");
            ConnectionService._2D_wrap20(ConnectionService.this, (String)((SomeArgs) (obj)).arg1);
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
              goto _L1
            message;
            ((SomeArgs) (obj)).recycle();
            Log.endSession();
            throw message;
_L23:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg4, "H.CS.sCE");
            String s7 = (String)((SomeArgs) (message)).arg1;
            String s = (String)((SomeArgs) (message)).arg2;
            Bundle bundle1 = (Bundle)((SomeArgs) (message)).arg3;
            ConnectionService._2D_wrap24(ConnectionService.this, s7, s, bundle1);
            message.recycle();
            Log.endSession();
              goto _L1
            s;
            message.recycle();
            Log.endSession();
            throw s;
_L24:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg3, "H.CS.oEC");
            String s8 = (String)((SomeArgs) (message)).arg1;
            Bundle bundle = (Bundle)((SomeArgs) (message)).arg2;
            ConnectionService._2D_wrap10(ConnectionService.this, s8, bundle);
            message.recycle();
            Log.endSession();
              goto _L1
            bundle;
            message.recycle();
            Log.endSession();
            throw bundle;
_L26:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg3, "H.CS.+RTT");
            String s1 = (String)((SomeArgs) (message)).arg1;
            Connection.RttTextStream rtttextstream = (Connection.RttTextStream)((SomeArgs) (message)).arg2;
            ConnectionService._2D_wrap27(ConnectionService.this, s1, rtttextstream);
            message.recycle();
            Log.endSession();
              goto _L1
            s1;
            message.recycle();
            Log.endSession();
            throw s1;
_L27:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg2, "H.CS.-RTT");
            String s2 = (String)((SomeArgs) (message)).arg1;
            ConnectionService._2D_wrap29(ConnectionService.this, s2);
            message.recycle();
            Log.endSession();
              goto _L1
            s2;
            message.recycle();
            Log.endSession();
            throw s2;
_L28:
            message = (SomeArgs)message.obj;
            Log.continueSession((Session)((SomeArgs) (message)).arg3, "H.CS.rTRUR");
            String s3 = (String)((SomeArgs) (message)).arg1;
            Connection.RttTextStream rtttextstream1 = (Connection.RttTextStream)((SomeArgs) (message)).arg2;
            ConnectionService._2D_wrap11(ConnectionService.this, s3, rtttextstream1);
            message.recycle();
            Log.endSession();
              goto _L1
            s3;
            message.recycle();
            Log.endSession();
            throw s3;
        }

        final ConnectionService this$0;

            
            {
                this$0 = ConnectionService.this;
                super(looper);
            }
    }
;
    private int mId;
    private final Map mIdByConference = new ConcurrentHashMap();
    private final Map mIdByConnection = new ConcurrentHashMap();
    private Object mIdSyncRoot;
    private final List mPreInitializationConnectionRequests = new ArrayList();
    protected Connection mPrevPhoneConnection;
    private final RemoteConnectionManager mRemoteConnectionManager = new RemoteConnectionManager(this);
    private Conference sNullConference;


    // Unreferenced inner class android/telecom/ConnectionService$2$1

/* anonymous class */
    class _cls1 extends Runnable
    {

        public void loggedRun()
        {
            ConnectionService._2D_wrap8(_fld0, connectionManagerPhoneAccount, id, request, isIncoming, isUnknown);
        }

        final _cls2 this$1;
        final PhoneAccountHandle val$connectionManagerPhoneAccount;
        final String val$id;
        final boolean val$isIncoming;
        final boolean val$isUnknown;
        final ConnectionRequest val$request;

            
            {
                this$1 = final__pcls2;
                connectionManagerPhoneAccount = phoneaccounthandle;
                id = s1;
                request = ConnectionRequest.this;
                isIncoming = flag;
                isUnknown = flag1;
                super(final_s, final_obj);
            }
    }


    // Unreferenced inner class android/telecom/ConnectionService$2$2

/* anonymous class */
    class _cls2 extends Runnable
    {

        public void loggedRun()
        {
            ConnectionService._2D_wrap14(_fld0, id);
        }

        final _cls2 this$1;
        final String val$id;

            
            {
                this$1 = final__pcls2;
                id = s1;
                super(String.this, obj);
            }
    }


    // Unreferenced inner class android/telecom/ConnectionService$2$3

/* anonymous class */
    class _cls3 extends Runnable
    {

        public void loggedRun()
        {
            ConnectionService._2D_wrap7(_fld0, connectionMgrPhoneAccount, id, request, isIncoming);
        }

        final _cls2 this$1;
        final PhoneAccountHandle val$connectionMgrPhoneAccount;
        final String val$id;
        final boolean val$isIncoming;
        final ConnectionRequest val$request;

            
            {
                this$1 = final__pcls2;
                connectionMgrPhoneAccount = phoneaccounthandle;
                id = String.this;
                request = connectionrequest;
                isIncoming = flag;
                super(final_s, final_obj);
            }
    }


    // Unreferenced inner class android/telecom/ConnectionService$6

/* anonymous class */
    static final class _cls6 extends Connection
    {

    }

}
