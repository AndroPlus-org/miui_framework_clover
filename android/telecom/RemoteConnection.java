// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;
import android.view.Surface;
import com.android.internal.telecom.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.telecom:
//            ParcelableConnection, Log, CallAudioState, DisconnectCause, 
//            RemoteConference, StatusHints, ConnectionRequest, AudioState, 
//            VideoCallbackServant, VideoProfile

public final class RemoteConnection
{
    public static abstract class Callback
    {

        public void onAddressChanged(RemoteConnection remoteconnection, Uri uri, int i)
        {
        }

        public void onCallerDisplayNameChanged(RemoteConnection remoteconnection, String s, int i)
        {
        }

        public void onConferenceChanged(RemoteConnection remoteconnection, RemoteConference remoteconference)
        {
        }

        public void onConferenceableConnectionsChanged(RemoteConnection remoteconnection, List list)
        {
        }

        public void onConnectionCapabilitiesChanged(RemoteConnection remoteconnection, int i)
        {
        }

        public void onConnectionEvent(RemoteConnection remoteconnection, String s, Bundle bundle)
        {
        }

        public void onConnectionPropertiesChanged(RemoteConnection remoteconnection, int i)
        {
        }

        public void onDestroyed(RemoteConnection remoteconnection)
        {
        }

        public void onDisconnected(RemoteConnection remoteconnection, DisconnectCause disconnectcause)
        {
        }

        public void onExtrasChanged(RemoteConnection remoteconnection, Bundle bundle)
        {
        }

        public void onPostDialChar(RemoteConnection remoteconnection, char c)
        {
        }

        public void onPostDialWait(RemoteConnection remoteconnection, String s)
        {
        }

        public void onRemoteRttRequest(RemoteConnection remoteconnection)
        {
        }

        public void onRingbackRequested(RemoteConnection remoteconnection, boolean flag)
        {
        }

        public void onRttInitiationFailure(RemoteConnection remoteconnection, int i)
        {
        }

        public void onRttInitiationSuccess(RemoteConnection remoteconnection)
        {
        }

        public void onRttSessionRemotelyTerminated(RemoteConnection remoteconnection)
        {
        }

        public void onStateChanged(RemoteConnection remoteconnection, int i)
        {
        }

        public void onStatusHintsChanged(RemoteConnection remoteconnection, StatusHints statushints)
        {
        }

        public void onVideoProviderChanged(RemoteConnection remoteconnection, VideoProvider videoprovider)
        {
        }

        public void onVideoStateChanged(RemoteConnection remoteconnection, int i)
        {
        }

        public void onVoipAudioChanged(RemoteConnection remoteconnection, boolean flag)
        {
        }

        public Callback()
        {
        }
    }

    private static final class CallbackRecord extends Callback
    {

        public Callback getCallback()
        {
            return mCallback;
        }

        public Handler getHandler()
        {
            return mHandler;
        }

        private final Callback mCallback;
        private final Handler mHandler;

        public CallbackRecord(Callback callback, Handler handler)
        {
            mCallback = callback;
            mHandler = handler;
        }
    }

    public static class VideoProvider
    {

        static Set _2D_get0(VideoProvider videoprovider)
        {
            return videoprovider.mCallbacks;
        }

        public void registerCallback(Callback callback)
        {
            mCallbacks.add(callback);
        }

        public void requestCallDataUsage()
        {
            mVideoProviderBinder.requestCallDataUsage();
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void requestCameraCapabilities()
        {
            mVideoProviderBinder.requestCameraCapabilities();
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void sendSessionModifyRequest(VideoProfile videoprofile, VideoProfile videoprofile1)
        {
            mVideoProviderBinder.sendSessionModifyRequest(videoprofile, videoprofile1);
_L2:
            return;
            videoprofile;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void sendSessionModifyResponse(VideoProfile videoprofile)
        {
            mVideoProviderBinder.sendSessionModifyResponse(videoprofile);
_L2:
            return;
            videoprofile;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void setCamera(String s)
        {
            mVideoProviderBinder.setCamera(s, mCallingPackage, mTargetSdkVersion);
_L2:
            return;
            s;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void setDeviceOrientation(int i)
        {
            mVideoProviderBinder.setDeviceOrientation(i);
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void setDisplaySurface(Surface surface)
        {
            mVideoProviderBinder.setDisplaySurface(surface);
_L2:
            return;
            surface;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void setPauseImage(Uri uri)
        {
            mVideoProviderBinder.setPauseImage(uri);
_L2:
            return;
            uri;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void setPreviewSurface(Surface surface)
        {
            mVideoProviderBinder.setPreviewSurface(surface);
_L2:
            return;
            surface;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void setZoom(float f)
        {
            mVideoProviderBinder.setZoom(f);
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void unregisterCallback(Callback callback)
        {
            mCallbacks.remove(callback);
        }

        private final Set mCallbacks;
        private final String mCallingPackage;
        private final int mTargetSdkVersion;
        private final IVideoCallback mVideoCallbackDelegate;
        private final VideoCallbackServant mVideoCallbackServant;
        private final IVideoProvider mVideoProviderBinder;

        VideoProvider(IVideoProvider ivideoprovider, String s, int i)
        {
            mVideoCallbackDelegate = new _cls1();
            mVideoCallbackServant = new VideoCallbackServant(mVideoCallbackDelegate);
            mCallbacks = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9F, 1));
            mVideoProviderBinder = ivideoprovider;
            mCallingPackage = s;
            mTargetSdkVersion = i;
            mVideoProviderBinder.addVideoCallback(mVideoCallbackServant.getStub().asBinder());
_L2:
            return;
            ivideoprovider;
            if(true) goto _L2; else goto _L1
_L1:
        }
    }

    public static abstract class VideoProvider.Callback
    {

        public void onCallDataUsageChanged(VideoProvider videoprovider, long l)
        {
        }

        public void onCallSessionEvent(VideoProvider videoprovider, int i)
        {
        }

        public void onCameraCapabilitiesChanged(VideoProvider videoprovider, VideoProfile.CameraCapabilities cameracapabilities)
        {
        }

        public void onPeerDimensionsChanged(VideoProvider videoprovider, int i, int j)
        {
        }

        public void onSessionModifyRequestReceived(VideoProvider videoprovider, VideoProfile videoprofile)
        {
        }

        public void onSessionModifyResponseReceived(VideoProvider videoprovider, int i, VideoProfile videoprofile, VideoProfile videoprofile1)
        {
        }

        public void onVideoQualityChanged(VideoProvider videoprovider, int i)
        {
        }

        public VideoProvider.Callback()
        {
        }
    }


    static Bundle _2D_get0(RemoteConnection remoteconnection)
    {
        return remoteconnection.mExtras;
    }

    static List _2D_get1(RemoteConnection remoteconnection)
    {
        return remoteconnection.mUnmodifiableconferenceableConnections;
    }

    RemoteConnection(DisconnectCause disconnectcause)
    {
        mCallbackRecords = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9F, 1));
        mConferenceableConnections = new ArrayList();
        mUnmodifiableconferenceableConnections = Collections.unmodifiableList(mConferenceableConnections);
        mState = 1;
        mConnectionId = "NULL";
        mConnected = false;
        mState = 6;
        mDisconnectCause = disconnectcause;
    }

    RemoteConnection(String s, IConnectionService iconnectionservice, ConnectionRequest connectionrequest)
    {
        mCallbackRecords = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9F, 1));
        mConferenceableConnections = new ArrayList();
        mUnmodifiableconferenceableConnections = Collections.unmodifiableList(mConferenceableConnections);
        mState = 1;
        mConnectionId = s;
        mConnectionService = iconnectionservice;
        mConnected = true;
        mState = 0;
    }

    RemoteConnection(String s, IConnectionService iconnectionservice, ParcelableConnection parcelableconnection, String s1, int i)
    {
        mCallbackRecords = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9F, 1));
        mConferenceableConnections = new ArrayList();
        mUnmodifiableconferenceableConnections = Collections.unmodifiableList(mConferenceableConnections);
        mState = 1;
        mConnectionId = s;
        mConnectionService = iconnectionservice;
        mConnected = true;
        mState = parcelableconnection.getState();
        mDisconnectCause = parcelableconnection.getDisconnectCause();
        mRingbackRequested = parcelableconnection.isRingbackRequested();
        mConnectionCapabilities = parcelableconnection.getConnectionCapabilities();
        mConnectionProperties = parcelableconnection.getConnectionProperties();
        mVideoState = parcelableconnection.getVideoState();
        iconnectionservice = parcelableconnection.getVideoProvider();
        if(iconnectionservice != null)
            mVideoProvider = new VideoProvider(iconnectionservice, s1, i);
        else
            mVideoProvider = null;
        mIsVoipAudioMode = parcelableconnection.getIsVoipAudioMode();
        mStatusHints = parcelableconnection.getStatusHints();
        mAddress = parcelableconnection.getHandle();
        mAddressPresentation = parcelableconnection.getHandlePresentation();
        mCallerDisplayName = parcelableconnection.getCallerDisplayName();
        mCallerDisplayNamePresentation = parcelableconnection.getCallerDisplayNamePresentation();
        mConference = null;
        putExtras(parcelableconnection.getExtras());
        iconnectionservice = new Bundle();
        iconnectionservice.putString("android.telecom.extra.ORIGINAL_CONNECTION_ID", s);
        putExtras(iconnectionservice);
    }

    public static RemoteConnection failure(DisconnectCause disconnectcause)
    {
        return new RemoteConnection(disconnectcause);
    }

    static void lambda$_2D_android_telecom_RemoteConnection_57723(Callback callback, RemoteConnection remoteconnection)
    {
        callback.onRttInitiationSuccess(remoteconnection);
    }

    static void lambda$_2D_android_telecom_RemoteConnection_58084(Callback callback, RemoteConnection remoteconnection, int i)
    {
        callback.onRttInitiationFailure(remoteconnection, i);
    }

    static void lambda$_2D_android_telecom_RemoteConnection_58451(Callback callback, RemoteConnection remoteconnection)
    {
        callback.onRttSessionRemotelyTerminated(remoteconnection);
    }

    static void lambda$_2D_android_telecom_RemoteConnection_58806(Callback callback, RemoteConnection remoteconnection)
    {
        callback.onRemoteRttRequest(remoteconnection);
    }

    private void notifyExtrasChanged()
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onExtrasChanged(connection, RemoteConnection._2D_get0(RemoteConnection.this));
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    public void abort()
    {
        if(mConnected)
            mConnectionService.abort(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void answer()
    {
        if(mConnected)
            mConnectionService.answer(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void answer(int i)
    {
        if(mConnected)
            mConnectionService.answerVideo(mConnectionId, i, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void disconnect()
    {
        if(mConnected)
            mConnectionService.disconnect(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Uri getAddress()
    {
        return mAddress;
    }

    public int getAddressPresentation()
    {
        return mAddressPresentation;
    }

    public CharSequence getCallerDisplayName()
    {
        return mCallerDisplayName;
    }

    public int getCallerDisplayNamePresentation()
    {
        return mCallerDisplayNamePresentation;
    }

    public RemoteConference getConference()
    {
        return mConference;
    }

    public List getConferenceableConnections()
    {
        return mUnmodifiableconferenceableConnections;
    }

    public int getConnectionCapabilities()
    {
        return mConnectionCapabilities;
    }

    public int getConnectionProperties()
    {
        return mConnectionProperties;
    }

    IConnectionService getConnectionService()
    {
        return mConnectionService;
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
        return mConnectionId;
    }

    public int getState()
    {
        return mState;
    }

    public StatusHints getStatusHints()
    {
        return mStatusHints;
    }

    public final VideoProvider getVideoProvider()
    {
        return mVideoProvider;
    }

    public int getVideoState()
    {
        return mVideoState;
    }

    public void hold()
    {
        if(mConnected)
            mConnectionService.hold(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isRingbackRequested()
    {
        return mRingbackRequested;
    }

    public boolean isVoipAudioMode()
    {
        return mIsVoipAudioMode;
    }

    void onConnectionEvent(final String event, final Bundle extras)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionEvent(connection, event, extras);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final String val$event;
        final Bundle val$extras;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                event = s;
                extras = bundle;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void onPostDialChar(final char final_c)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onPostDialChar(connection, nextChar);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final char val$nextChar;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                nextChar = final_c;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void onRemoteRttRequest()
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda.jt22v7S6acXavRq__4QCBipXsH8((byte)0, callback, this)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void onRttInitiationFailure(int i)
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda._cls35vMRSYyUGqYojJbIVkJttSja_M((byte)3, i, callback, this)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void onRttInitiationSuccess()
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda.jt22v7S6acXavRq__4QCBipXsH8((byte)1, callback, this)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void onRttSessionRemotelyTerminated()
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda.jt22v7S6acXavRq__4QCBipXsH8((byte)2, callback, this)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    public void playDtmfTone(char c)
    {
        if(mConnected)
            mConnectionService.playDtmfTone(mConnectionId, c, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void postDialContinue(boolean flag)
    {
        if(mConnected)
            mConnectionService.onPostDialContinue(mConnectionId, flag, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void pullExternalCall()
    {
        if(mConnected)
            mConnectionService.pullExternalCall(mConnectionId, null);
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
        try
        {
            mExtras.putAll(bundle);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            Log.w(this, (new StringBuilder()).append("putExtras: could not unmarshal extras; exception = ").append(bundle).toString(), new Object[0]);
        }
        notifyExtrasChanged();
    }

    public void registerCallback(Callback callback)
    {
        registerCallback(callback, new Handler());
    }

    public void registerCallback(Callback callback, Handler handler)
    {
        unregisterCallback(callback);
        if(callback != null && handler != null)
            mCallbackRecords.add(new CallbackRecord(callback, handler));
    }

    public void reject()
    {
        if(mConnected)
            mConnectionService.reject(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void removeExtras(List list)
    {
        while(mExtras == null || list == null || list.isEmpty()) 
            return;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); mExtras.remove(list))
            list = (String)iterator.next();

        notifyExtrasChanged();
    }

    public void sendRttUpgradeResponse(Connection.RttTextStream rtttextstream)
    {
        if(!mConnected)
            break MISSING_BLOCK_LABEL_27;
        if(rtttextstream != null)
            break MISSING_BLOCK_LABEL_28;
        mConnectionService.respondToRttUpgradeRequest(mConnectionId, null, null, null);
_L1:
        return;
        try
        {
            mConnectionService.respondToRttUpgradeRequest(mConnectionId, rtttextstream.getFdFromInCall(), rtttextstream.getFdToInCall(), null);
        }
        // Misplaced declaration of an exception variable
        catch(Connection.RttTextStream rtttextstream) { }
          goto _L1
    }

    void setAddress(final Uri address, final int presentation)
    {
        mAddress = address;
        mAddressPresentation = presentation;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onAddressChanged(connection, address, presentation);
        }

        final RemoteConnection this$0;
        final Uri val$address;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final int val$presentation;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                address = uri;
                presentation = i;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    public void setAudioState(AudioState audiostate)
    {
        setCallAudioState(new CallAudioState(audiostate));
    }

    public void setCallAudioState(CallAudioState callaudiostate)
    {
        if(mConnected)
            mConnectionService.onCallAudioStateChanged(mConnectionId, callaudiostate, null);
_L2:
        return;
        callaudiostate;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void setCallerDisplayName(final String callerDisplayName, final int presentation)
    {
        mCallerDisplayName = callerDisplayName;
        mCallerDisplayNamePresentation = presentation;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onCallerDisplayNameChanged(connection, callerDisplayName, presentation);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final String val$callerDisplayName;
        final RemoteConnection val$connection;
        final int val$presentation;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                callerDisplayName = s;
                presentation = i;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setConference(final RemoteConference conference)
    {
        if(mConference != conference)
        {
            mConference = conference;
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConferenceChanged(connection, conference);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConference val$conference;
        final RemoteConnection val$connection;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                conference = remoteconference;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = callbackrecord.getCallback();
            }

        }
    }

    void setConferenceableConnections(final List callback)
    {
        mConferenceableConnections.clear();
        mConferenceableConnections.addAll(callback);
        CallbackRecord callbackrecord;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConferenceableConnectionsChanged(connection, RemoteConnection._2D_get1(RemoteConnection.this));
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setConnectionCapabilities(final int connectionCapabilities)
    {
        mConnectionCapabilities = connectionCapabilities;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionCapabilitiesChanged(connection, connectionCapabilities);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final int val$connectionCapabilities;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                connectionCapabilities = i;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setConnectionProperties(final int connectionProperties)
    {
        mConnectionProperties = connectionProperties;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionPropertiesChanged(connection, connectionProperties);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final int val$connectionProperties;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                connectionProperties = i;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setDestroyed()
    {
        if(!mCallbackRecords.isEmpty())
        {
            if(mState != 6)
                setDisconnected(new DisconnectCause(1, "Connection destroyed."));
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onDestroyed(connection);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = callbackrecord.getCallback();
            }

            mCallbackRecords.clear();
            mConnected = false;
        }
    }

    void setDisconnected(final DisconnectCause disconnectCause)
    {
        if(mState != 6)
        {
            mState = 6;
            mDisconnectCause = disconnectCause;
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onDisconnected(connection, disconnectCause);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final DisconnectCause val$disconnectCause;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                disconnectCause = disconnectcause;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = callbackrecord.getCallback();
            }

        }
    }

    void setIsVoipAudioMode(final boolean isVoip)
    {
        mIsVoipAudioMode = isVoip;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onVoipAudioChanged(connection, isVoip);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final boolean val$isVoip;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                isVoip = flag;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setPostDialWait(final String remainingDigits)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onPostDialWait(connection, remainingDigits);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final String val$remainingDigits;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                remainingDigits = s;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setRingbackRequested(final boolean ringback)
    {
        if(mRingbackRequested != ringback)
        {
            mRingbackRequested = ringback;
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onRingbackRequested(connection, ringback);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final boolean val$ringback;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                ringback = flag;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = callbackrecord.getCallback();
            }

        }
    }

    void setState(final int state)
    {
        if(mState != state)
        {
            mState = state;
            CallbackRecord callbackrecord;
            final Callback callback;
            for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onStateChanged(connection, state);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final int val$state;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                state = i;
                super();
            }
    }
))
            {
                callbackrecord = (CallbackRecord)iterator.next();
                callback = callbackrecord.getCallback();
            }

        }
    }

    void setStatusHints(final StatusHints statusHints)
    {
        mStatusHints = statusHints;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onStatusHintsChanged(connection, statusHints);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final StatusHints val$statusHints;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                statusHints = statushints;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setVideoProvider(final VideoProvider videoProvider)
    {
        mVideoProvider = videoProvider;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onVideoProviderChanged(connection, videoProvider);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final VideoProvider val$videoProvider;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                videoProvider = videoprovider;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    void setVideoState(final int videoState)
    {
        mVideoState = videoState;
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onVideoStateChanged(connection, videoState);
        }

        final RemoteConnection this$0;
        final Callback val$callback;
        final RemoteConnection val$connection;
        final int val$videoState;

            
            {
                this$0 = RemoteConnection.this;
                callback = callback1;
                connection = remoteconnection1;
                videoState = i;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = callbackrecord.getCallback();
        }

    }

    public void startRtt(Connection.RttTextStream rtttextstream)
    {
        if(mConnected)
            mConnectionService.startRtt(mConnectionId, rtttextstream.getFdFromInCall(), rtttextstream.getFdToInCall(), null);
_L2:
        return;
        rtttextstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopDtmfTone()
    {
        if(mConnected)
            mConnectionService.stopDtmfTone(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void stopRtt()
    {
        if(mConnected)
            mConnectionService.stopRtt(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void unhold()
    {
        if(mConnected)
            mConnectionService.unhold(mConnectionId, null);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void unregisterCallback(Callback callback)
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

    private Uri mAddress;
    private int mAddressPresentation;
    private final Set mCallbackRecords;
    private String mCallerDisplayName;
    private int mCallerDisplayNamePresentation;
    private RemoteConference mConference;
    private final List mConferenceableConnections;
    private boolean mConnected;
    private int mConnectionCapabilities;
    private final String mConnectionId;
    private int mConnectionProperties;
    private IConnectionService mConnectionService;
    private DisconnectCause mDisconnectCause;
    private Bundle mExtras;
    private boolean mIsVoipAudioMode;
    private boolean mRingbackRequested;
    private int mState;
    private StatusHints mStatusHints;
    private final List mUnmodifiableconferenceableConnections;
    private VideoProvider mVideoProvider;
    private int mVideoState;

    // Unreferenced inner class android/telecom/RemoteConnection$VideoProvider$1

/* anonymous class */
    class VideoProvider._cls1
        implements IVideoCallback
    {

        public IBinder asBinder()
        {
            return null;
        }

        public void changeCallDataUsage(long l)
        {
            for(Iterator iterator = VideoProvider._2D_get0(VideoProvider.this).iterator(); iterator.hasNext(); ((VideoProvider.Callback)iterator.next()).onCallDataUsageChanged(VideoProvider.this, l));
        }

        public void changeCameraCapabilities(VideoProfile.CameraCapabilities cameracapabilities)
        {
            for(Iterator iterator = VideoProvider._2D_get0(VideoProvider.this).iterator(); iterator.hasNext(); ((VideoProvider.Callback)iterator.next()).onCameraCapabilitiesChanged(VideoProvider.this, cameracapabilities));
        }

        public void changePeerDimensions(int i, int j)
        {
            for(Iterator iterator = VideoProvider._2D_get0(VideoProvider.this).iterator(); iterator.hasNext(); ((VideoProvider.Callback)iterator.next()).onPeerDimensionsChanged(VideoProvider.this, i, j));
        }

        public void changeVideoQuality(int i)
        {
            for(Iterator iterator = VideoProvider._2D_get0(VideoProvider.this).iterator(); iterator.hasNext(); ((VideoProvider.Callback)iterator.next()).onVideoQualityChanged(VideoProvider.this, i));
        }

        public void handleCallSessionEvent(int i)
        {
            for(Iterator iterator = VideoProvider._2D_get0(VideoProvider.this).iterator(); iterator.hasNext(); ((VideoProvider.Callback)iterator.next()).onCallSessionEvent(VideoProvider.this, i));
        }

        public void receiveSessionModifyRequest(VideoProfile videoprofile)
        {
            for(Iterator iterator = VideoProvider._2D_get0(VideoProvider.this).iterator(); iterator.hasNext(); ((VideoProvider.Callback)iterator.next()).onSessionModifyRequestReceived(VideoProvider.this, videoprofile));
        }

        public void receiveSessionModifyResponse(int i, VideoProfile videoprofile, VideoProfile videoprofile1)
        {
            for(Iterator iterator = VideoProvider._2D_get0(VideoProvider.this).iterator(); iterator.hasNext(); ((VideoProvider.Callback)iterator.next()).onSessionModifyResponseReceived(VideoProvider.this, i, videoprofile, videoprofile1));
        }

        final VideoProvider this$1;

            
            {
                this$1 = VideoProvider.this;
                super();
            }
    }

}
