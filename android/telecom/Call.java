// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.telecom:
//            Phone, CallbackRecord, Log, InCallAdapter, 
//            ParcelableCall, VideoCallImpl, ParcelableRttCall, PhoneAccountHandle, 
//            DisconnectCause, GatewayInfo, StatusHints

public final class Call
{
    public static abstract class Callback
    {

        public void onCallDestroyed(Call call)
        {
        }

        public void onCannedTextResponsesLoaded(Call call, List list)
        {
        }

        public void onChildrenChanged(Call call, List list)
        {
        }

        public void onConferenceableCallsChanged(Call call, List list)
        {
        }

        public void onConnectionEvent(Call call, String s, Bundle bundle)
        {
        }

        public void onDetailsChanged(Call call, Details details)
        {
        }

        public void onParentChanged(Call call, Call call1)
        {
        }

        public void onPostDialWait(Call call, String s)
        {
        }

        public void onRttInitiationFailure(Call call, int i)
        {
        }

        public void onRttModeChanged(Call call, int i)
        {
        }

        public void onRttRequest(Call call, int i)
        {
        }

        public void onRttStatusChanged(Call call, boolean flag, RttCall rttcall)
        {
        }

        public void onStateChanged(Call call, int i)
        {
        }

        public void onVideoCallChanged(Call call, InCallService.VideoCall videocall)
        {
        }

        public Callback()
        {
        }
    }

    public static class Details
    {

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
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[Capabilities:");
            if(can(i, 1))
                stringbuilder.append(" CAPABILITY_HOLD");
            if(can(i, 2))
                stringbuilder.append(" CAPABILITY_SUPPORT_HOLD");
            if(can(i, 4))
                stringbuilder.append(" CAPABILITY_MERGE_CONFERENCE");
            if(can(i, 8))
                stringbuilder.append(" CAPABILITY_SWAP_CONFERENCE");
            if(can(i, 32))
                stringbuilder.append(" CAPABILITY_RESPOND_VIA_TEXT");
            if(can(i, 64))
                stringbuilder.append(" CAPABILITY_MUTE");
            if(can(i, 128))
                stringbuilder.append(" CAPABILITY_MANAGE_CONFERENCE");
            if(can(i, 256))
                stringbuilder.append(" CAPABILITY_SUPPORTS_VT_LOCAL_RX");
            if(can(i, 512))
                stringbuilder.append(" CAPABILITY_SUPPORTS_VT_LOCAL_TX");
            if(can(i, 768))
                stringbuilder.append(" CAPABILITY_SUPPORTS_VT_LOCAL_BIDIRECTIONAL");
            if(can(i, 1024))
                stringbuilder.append(" CAPABILITY_SUPPORTS_VT_REMOTE_RX");
            if(can(i, 2048))
                stringbuilder.append(" CAPABILITY_SUPPORTS_VT_REMOTE_TX");
            if(can(i, 0x400000))
                stringbuilder.append(" CAPABILITY_CANNOT_DOWNGRADE_VIDEO_TO_AUDIO");
            if(can(i, 3072))
                stringbuilder.append(" CAPABILITY_SUPPORTS_VT_REMOTE_BIDIRECTIONAL");
            if(can(i, 0x40000))
                stringbuilder.append(" CAPABILITY_SPEED_UP_MT_AUDIO");
            if(can(i, 0x80000))
                stringbuilder.append(" CAPABILITY_CAN_UPGRADE_TO_VIDEO");
            if(can(i, 0x100000))
                stringbuilder.append(" CAPABILITY_CAN_PAUSE_VIDEO");
            if(can(i, 0x800000))
                stringbuilder.append(" CAPABILITY_CAN_PULL_CALL");
            if(can(i, 0x1000000))
                stringbuilder.append(" CAPABILITY_ADD_PARTICIPANT");
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public static Details createFromParcelableCall(ParcelableCall parcelablecall)
        {
            return new Details(parcelablecall.getId(), parcelablecall.getHandle(), parcelablecall.getHandlePresentation(), parcelablecall.getCallerDisplayName(), parcelablecall.getCallerDisplayNamePresentation(), parcelablecall.getAccountHandle(), parcelablecall.getCapabilities(), parcelablecall.getProperties(), parcelablecall.getDisconnectCause(), parcelablecall.getConnectTimeMillis(), parcelablecall.getGatewayInfo(), parcelablecall.getVideoState(), parcelablecall.getStatusHints(), parcelablecall.getExtras(), parcelablecall.getIntentExtras(), parcelablecall.getCreationTimeMillis());
        }

        public static boolean hasProperty(int i, int j)
        {
            boolean flag;
            if((i & j) == j)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public static String propertiesToString(int i)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[Properties:");
            if(hasProperty(i, 1))
                stringbuilder.append(" PROPERTY_CONFERENCE");
            if(hasProperty(i, 2))
                stringbuilder.append(" PROPERTY_GENERIC_CONFERENCE");
            if(hasProperty(i, 8))
                stringbuilder.append(" PROPERTY_WIFI");
            if(hasProperty(i, 16))
                stringbuilder.append(" PROPERTY_HIGH_DEF_AUDIO");
            if(hasProperty(i, 4))
                stringbuilder.append(" PROPERTY_EMERGENCY_CALLBACK_MODE");
            if(hasProperty(i, 64))
                stringbuilder.append(" PROPERTY_IS_EXTERNAL_CALL");
            if(hasProperty(i, 128))
                stringbuilder.append(" PROPERTY_HAS_CDMA_VOICE_PRIVACY");
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public boolean can(int i)
        {
            return can(mCallCapabilities, i);
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(obj instanceof Details)
            {
                obj = (Details)obj;
                boolean flag1 = flag;
                if(Objects.equals(mHandle, ((Details) (obj)).mHandle))
                {
                    flag1 = flag;
                    if(Objects.equals(Integer.valueOf(mHandlePresentation), Integer.valueOf(((Details) (obj)).mHandlePresentation)))
                    {
                        flag1 = flag;
                        if(Objects.equals(mCallerDisplayName, ((Details) (obj)).mCallerDisplayName))
                        {
                            flag1 = flag;
                            if(Objects.equals(Integer.valueOf(mCallerDisplayNamePresentation), Integer.valueOf(((Details) (obj)).mCallerDisplayNamePresentation)))
                            {
                                flag1 = flag;
                                if(Objects.equals(mAccountHandle, ((Details) (obj)).mAccountHandle))
                                {
                                    flag1 = flag;
                                    if(Objects.equals(Integer.valueOf(mCallCapabilities), Integer.valueOf(((Details) (obj)).mCallCapabilities)))
                                    {
                                        flag1 = flag;
                                        if(Objects.equals(Integer.valueOf(mCallProperties), Integer.valueOf(((Details) (obj)).mCallProperties)))
                                        {
                                            flag1 = flag;
                                            if(Objects.equals(mDisconnectCause, ((Details) (obj)).mDisconnectCause))
                                            {
                                                flag1 = flag;
                                                if(Objects.equals(Long.valueOf(mConnectTimeMillis), Long.valueOf(((Details) (obj)).mConnectTimeMillis)))
                                                {
                                                    flag1 = flag;
                                                    if(Objects.equals(mGatewayInfo, ((Details) (obj)).mGatewayInfo))
                                                    {
                                                        flag1 = flag;
                                                        if(Objects.equals(Integer.valueOf(mVideoState), Integer.valueOf(((Details) (obj)).mVideoState)))
                                                        {
                                                            flag1 = flag;
                                                            if(Objects.equals(mStatusHints, ((Details) (obj)).mStatusHints))
                                                            {
                                                                flag1 = flag;
                                                                if(Call._2D_wrap0(mExtras, ((Details) (obj)).mExtras))
                                                                {
                                                                    flag1 = flag;
                                                                    if(Call._2D_wrap0(mIntentExtras, ((Details) (obj)).mIntentExtras))
                                                                        flag1 = Objects.equals(Long.valueOf(mCreationTimeMillis), Long.valueOf(((Details) (obj)).mCreationTimeMillis));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return flag1;
            } else
            {
                return false;
            }
        }

        public PhoneAccountHandle getAccountHandle()
        {
            return mAccountHandle;
        }

        public int getCallCapabilities()
        {
            return mCallCapabilities;
        }

        public int getCallProperties()
        {
            return mCallProperties;
        }

        public String getCallerDisplayName()
        {
            return mCallerDisplayName;
        }

        public int getCallerDisplayNamePresentation()
        {
            return mCallerDisplayNamePresentation;
        }

        public final long getConnectTimeMillis()
        {
            return mConnectTimeMillis;
        }

        public long getCreationTimeMillis()
        {
            return mCreationTimeMillis;
        }

        public DisconnectCause getDisconnectCause()
        {
            return mDisconnectCause;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public GatewayInfo getGatewayInfo()
        {
            return mGatewayInfo;
        }

        public Uri getHandle()
        {
            return mHandle;
        }

        public int getHandlePresentation()
        {
            return mHandlePresentation;
        }

        public Bundle getIntentExtras()
        {
            return mIntentExtras;
        }

        public StatusHints getStatusHints()
        {
            return mStatusHints;
        }

        public int getSupportedAudioRoutes()
        {
            return 15;
        }

        public String getTelecomCallId()
        {
            return mTelecomCallId;
        }

        public int getVideoState()
        {
            return mVideoState;
        }

        public boolean hasProperty(int i)
        {
            return hasProperty(mCallProperties, i);
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                mHandle, Integer.valueOf(mHandlePresentation), mCallerDisplayName, Integer.valueOf(mCallerDisplayNamePresentation), mAccountHandle, Integer.valueOf(mCallCapabilities), Integer.valueOf(mCallProperties), mDisconnectCause, Long.valueOf(mConnectTimeMillis), mGatewayInfo, 
                Integer.valueOf(mVideoState), mStatusHints, mExtras, mIntentExtras, Long.valueOf(mCreationTimeMillis)
            });
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[pa: ");
            stringbuilder.append(mAccountHandle);
            stringbuilder.append(", hdl: ");
            stringbuilder.append(Log.pii(mHandle));
            stringbuilder.append(", caps: ");
            stringbuilder.append(capabilitiesToString(mCallCapabilities));
            stringbuilder.append(", props: ");
            stringbuilder.append(propertiesToString(mCallProperties));
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public static final int CAPABILITY_ADD_PARTICIPANT = 0x1000000;
        public static final int CAPABILITY_CANNOT_DOWNGRADE_VIDEO_TO_AUDIO = 0x400000;
        public static final int CAPABILITY_CAN_PAUSE_VIDEO = 0x100000;
        public static final int CAPABILITY_CAN_PULL_CALL = 0x800000;
        public static final int CAPABILITY_CAN_SEND_RESPONSE_VIA_CONNECTION = 0x200000;
        public static final int CAPABILITY_CAN_UPGRADE_TO_VIDEO = 0x80000;
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
        public static final int CAPABILITY_UNUSED_1 = 16;
        public static final int PROPERTY_CONFERENCE = 1;
        public static final int PROPERTY_EMERGENCY_CALLBACK_MODE = 4;
        public static final int PROPERTY_ENTERPRISE_CALL = 32;
        public static final int PROPERTY_GENERIC_CONFERENCE = 2;
        public static final int PROPERTY_HAS_CDMA_VOICE_PRIVACY = 128;
        public static final int PROPERTY_HIGH_DEF_AUDIO = 16;
        public static final int PROPERTY_IS_EXTERNAL_CALL = 64;
        public static final int PROPERTY_SELF_MANAGED = 256;
        public static final int PROPERTY_WIFI = 8;
        private final PhoneAccountHandle mAccountHandle;
        private final int mCallCapabilities;
        private final int mCallProperties;
        private final String mCallerDisplayName;
        private final int mCallerDisplayNamePresentation;
        private final long mConnectTimeMillis;
        private final long mCreationTimeMillis;
        private final DisconnectCause mDisconnectCause;
        private final Bundle mExtras;
        private final GatewayInfo mGatewayInfo;
        private final Uri mHandle;
        private final int mHandlePresentation;
        private final Bundle mIntentExtras;
        private final StatusHints mStatusHints;
        private final int mSupportedAudioRoutes = 15;
        private final String mTelecomCallId;
        private final int mVideoState;

        public Details(String s, Uri uri, int i, String s1, int j, PhoneAccountHandle phoneaccounthandle, int k, 
                int l, DisconnectCause disconnectcause, long l1, GatewayInfo gatewayinfo, int i1, StatusHints statushints, 
                Bundle bundle, Bundle bundle1, long l2)
        {
            mTelecomCallId = s;
            mHandle = uri;
            mHandlePresentation = i;
            mCallerDisplayName = s1;
            mCallerDisplayNamePresentation = j;
            mAccountHandle = phoneaccounthandle;
            mCallCapabilities = k;
            mCallProperties = l;
            mDisconnectCause = disconnectcause;
            mConnectTimeMillis = l1;
            mGatewayInfo = gatewayinfo;
            mVideoState = i1;
            mStatusHints = statushints;
            mExtras = bundle;
            mIntentExtras = bundle1;
            mCreationTimeMillis = l2;
        }
    }

    public static abstract class Listener extends Callback
    {

        public Listener()
        {
        }
    }

    public static final class RttCall
    {

        public int getRttAudioMode()
        {
            return mRttMode;
        }

        public String read()
        {
            int i;
            String s;
            try
            {
                i = mReceiveStream.read(mReadBuffer, 0, 1000);
            }
            catch(IOException ioexception)
            {
                Log.w(this, "Exception encountered when reading from InputStreamReader: %s", new Object[] {
                    ioexception
                });
                return null;
            }
            if(i < 0)
                return null;
            s = new String(mReadBuffer, 0, i);
            return s;
        }

        public String readImmediately()
            throws IOException
        {
            if(mReceiveStream.ready())
            {
                int i = mReceiveStream.read(mReadBuffer, 0, 1000);
                if(i < 0)
                    return null;
                else
                    return new String(mReadBuffer, 0, i);
            } else
            {
                return null;
            }
        }

        public void setRttMode(int i)
        {
            mInCallAdapter.setRttMode(mTelecomCallId, i);
        }

        public void write(String s)
            throws IOException
        {
            mTransmitStream.write(s);
            mTransmitStream.flush();
        }

        private static final int READ_BUFFER_SIZE = 1000;
        public static final int RTT_MODE_FULL = 1;
        public static final int RTT_MODE_HCO = 2;
        public static final int RTT_MODE_INVALID = 0;
        public static final int RTT_MODE_VCO = 3;
        private final InCallAdapter mInCallAdapter;
        private char mReadBuffer[];
        private InputStreamReader mReceiveStream;
        private int mRttMode;
        private final String mTelecomCallId;
        private OutputStreamWriter mTransmitStream;

        public RttCall(String s, InputStreamReader inputstreamreader, OutputStreamWriter outputstreamwriter, int i, InCallAdapter incalladapter)
        {
            mReadBuffer = new char[1000];
            mTelecomCallId = s;
            mReceiveStream = inputstreamreader;
            mTransmitStream = outputstreamwriter;
            mRttMode = i;
            mInCallAdapter = incalladapter;
        }
    }


    static List _2D_get0(Call call)
    {
        return call.mCallbackRecords;
    }

    static Phone _2D_get1(Call call)
    {
        return call.mPhone;
    }

    static List _2D_get2(Call call)
    {
        return call.mUnmodifiableConferenceableCalls;
    }

    static boolean _2D_wrap0(Bundle bundle, Bundle bundle1)
    {
        return areBundlesEqual(bundle, bundle1);
    }

    Call(Phone phone, String s, InCallAdapter incalladapter, int i, String s1, int j)
    {
        mChildrenIds = new ArrayList();
        mChildren = new ArrayList();
        mUnmodifiableChildren = Collections.unmodifiableList(mChildren);
        mCallbackRecords = new CopyOnWriteArrayList();
        mConferenceableCalls = new ArrayList();
        mUnmodifiableConferenceableCalls = Collections.unmodifiableList(mConferenceableCalls);
        mParentId = null;
        mCannedTextResponses = null;
        mPhone = phone;
        mTelecomCallId = s;
        mInCallAdapter = incalladapter;
        mState = i;
        mCallingPackage = s1;
        mTargetSdkVersion = j;
    }

    Call(Phone phone, String s, InCallAdapter incalladapter, String s1, int i)
    {
        mChildrenIds = new ArrayList();
        mChildren = new ArrayList();
        mUnmodifiableChildren = Collections.unmodifiableList(mChildren);
        mCallbackRecords = new CopyOnWriteArrayList();
        mConferenceableCalls = new ArrayList();
        mUnmodifiableConferenceableCalls = Collections.unmodifiableList(mConferenceableCalls);
        mParentId = null;
        mCannedTextResponses = null;
        mPhone = phone;
        mTelecomCallId = s;
        mInCallAdapter = incalladapter;
        mState = 0;
        mCallingPackage = s1;
        mTargetSdkVersion = i;
    }

    private static boolean areBundlesEqual(Bundle bundle, Bundle bundle1)
    {
        boolean flag = true;
        if(bundle == null || bundle1 == null)
        {
            if(bundle != bundle1)
                flag = false;
            return flag;
        }
        if(bundle.size() != bundle1.size())
            return false;
        for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if(s != null && !Objects.equals(bundle.get(s), bundle1.get(s)))
                return false;
        }

        return true;
    }

    private void fireCallDestroyed()
    {
        if(mCallbackRecords.isEmpty())
            mPhone.internalRemoveCall(this);
        final CallbackRecord record;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); record.getHandler().post(new Runnable() {

        public void run()
        {
            Call call1;
            boolean flag = false;
            RuntimeException runtimeexception = null;
            boolean flag1;
            try
            {
                callback.onCallDestroyed(call);
            }
            // Misplaced declaration of an exception variable
            catch(RuntimeException runtimeexception) { }
            call1 = Call.this;
            call1;
            JVM INSTR monitorenter ;
            Call._2D_get0(Call.this).remove(record);
            flag1 = Call._2D_get0(Call.this).isEmpty();
            if(flag1)
                flag = true;
            call1;
            JVM INSTR monitorexit ;
            if(flag)
                Call._2D_get1(Call.this).internalRemoveCall(call);
            Exception exception;
            if(runtimeexception != null)
                throw runtimeexception;
            else
                return;
            exception;
            throw exception;
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final CallbackRecord val$record;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                record = callbackrecord;
                super();
            }
    }
))
        {
            record = (CallbackRecord)iterator.next();
            callback = (Callback)record.getCallback();
        }

    }

    private void fireCannedTextResponsesLoaded(final List cannedTextResponses)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onCannedTextResponsesLoaded(call, cannedTextResponses);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final List val$cannedTextResponses;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                cannedTextResponses = list;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireChildrenChanged(final List children)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onChildrenChanged(call, children);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final List val$children;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                children = list;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireConferenceableCallsChanged()
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConferenceableCallsChanged(call, Call._2D_get2(Call.this));
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireDetailsChanged(final Details details)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onDetailsChanged(call, details);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final Details val$details;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                details = details1;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireOnConnectionEvent(final String event, final Bundle extras)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onConnectionEvent(call, event, extras);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final String val$event;
        final Bundle val$extras;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                event = s;
                extras = bundle;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireOnIsRttChanged(boolean flag, RttCall rttcall)
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda.C1mff0scl0rlO_JIsUmJ5H_4cmo(flag, callback, this, rttcall)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireOnRttModeChanged(int i)
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda._cls35vMRSYyUGqYojJbIVkJttSja_M((byte)0, i, callback, this)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireParentChanged(final Call newParent)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onParentChanged(call, newParent);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final Call val$newParent;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                newParent = call3;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void firePostDialWait(final String remainingPostDialSequence)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onPostDialWait(call, remainingPostDialSequence);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final String val$remainingPostDialSequence;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                remainingPostDialSequence = s;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireStateChanged(final int newState)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onStateChanged(call, newState);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final int val$newState;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                newState = i;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    private void fireVideoCallChanged(final InCallService.VideoCall videoCall)
    {
        CallbackRecord callbackrecord;
        final Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new Runnable() {

        public void run()
        {
            callback.onVideoCallChanged(call, videoCall);
        }

        final Call this$0;
        final Call val$call;
        final Callback val$callback;
        final InCallService.VideoCall val$videoCall;

            
            {
                this$0 = Call.this;
                callback = callback1;
                call = call2;
                videoCall = videocall;
                super();
            }
    }
))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    static void lambda$_2D_android_telecom_Call_73393(Callback callback, Call call, int i)
    {
        callback.onRttRequest(call, i);
    }

    static void lambda$_2D_android_telecom_Call_73734(Callback callback, Call call, int i)
    {
        callback.onRttInitiationFailure(call, i);
    }

    static void lambda$_2D_android_telecom_Call_80443(Callback callback, Call call, boolean flag, RttCall rttcall)
    {
        callback.onRttStatusChanged(call, flag, rttcall);
    }

    static void lambda$_2D_android_telecom_Call_80882(Callback callback, Call call, int i)
    {
        callback.onRttModeChanged(call, i);
    }

    private static String stateToString(int i)
    {
        switch(i)
        {
        case 5: // '\005'
        case 6: // '\006'
        default:
            Log.w(android/telecom/Call, "Unknown state %d", new Object[] {
                Integer.valueOf(i)
            });
            return "UNKNOWN";

        case 0: // '\0'
            return "NEW";

        case 2: // '\002'
            return "RINGING";

        case 1: // '\001'
            return "DIALING";

        case 4: // '\004'
            return "ACTIVE";

        case 3: // '\003'
            return "HOLDING";

        case 7: // '\007'
            return "DISCONNECTED";

        case 9: // '\t'
            return "CONNECTING";

        case 10: // '\n'
            return "DISCONNECTING";

        case 8: // '\b'
            return "SELECT_PHONE_ACCOUNT";
        }
    }

    public void addListener(Listener listener)
    {
        registerCallback(listener);
    }

    public void answer(int i)
    {
        mInCallAdapter.answerCall(mTelecomCallId, i);
    }

    public void conference(Call call)
    {
        if(call != null)
            mInCallAdapter.conference(mTelecomCallId, call.mTelecomCallId);
    }

    public void disconnect()
    {
        mInCallAdapter.disconnectCall(mTelecomCallId);
    }

    public List getCannedTextResponses()
    {
        return mCannedTextResponses;
    }

    public List getChildren()
    {
        if(!mChildrenCached)
        {
            mChildrenCached = true;
            mChildren.clear();
            for(Iterator iterator = mChildrenIds.iterator(); iterator.hasNext();)
            {
                Object obj = (String)iterator.next();
                obj = mPhone.internalGetCallByTelecomId(((String) (obj)));
                if(obj == null)
                    mChildrenCached = false;
                else
                    mChildren.add(obj);
            }

        }
        return mUnmodifiableChildren;
    }

    public List getConferenceableCalls()
    {
        return mUnmodifiableConferenceableCalls;
    }

    public Details getDetails()
    {
        return mDetails;
    }

    public Call getParent()
    {
        if(mParentId != null)
            return mPhone.internalGetCallByTelecomId(mParentId);
        else
            return null;
    }

    public String getRemainingPostDialSequence()
    {
        return mRemainingPostDialSequence;
    }

    public RttCall getRttCall()
    {
        return mRttCall;
    }

    public int getState()
    {
        return mState;
    }

    public InCallService.VideoCall getVideoCall()
    {
        return mVideoCallImpl;
    }

    public void hold()
    {
        mInCallAdapter.holdCall(mTelecomCallId);
    }

    final String internalGetCallId()
    {
        return mTelecomCallId;
    }

    final void internalOnConnectionEvent(String s, Bundle bundle)
    {
        fireOnConnectionEvent(s, bundle);
    }

    final void internalOnRttInitiationFailure(int i)
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda._cls35vMRSYyUGqYojJbIVkJttSja_M((byte)1, i, callback, this)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    final void internalOnRttUpgradeRequest(int i)
    {
        CallbackRecord callbackrecord;
        Callback callback;
        for(Iterator iterator = mCallbackRecords.iterator(); iterator.hasNext(); callbackrecord.getHandler().post(new _.Lambda._cls35vMRSYyUGqYojJbIVkJttSja_M((byte)2, i, callback, this)))
        {
            callbackrecord = (CallbackRecord)iterator.next();
            callback = (Callback)callbackrecord.getCallback();
        }

    }

    final void internalSetDisconnected()
    {
        if(mState != 7)
        {
            mState = 7;
            fireStateChanged(mState);
            fireCallDestroyed();
        }
    }

    final void internalSetPostDialWait(String s)
    {
        mRemainingPostDialSequence = s;
        firePostDialWait(mRemainingPostDialSequence);
    }

    final void internalUpdate(ParcelableCall parcelablecall, Map map)
    {
        Object obj = Details.createFromParcelableCall(parcelablecall);
        boolean flag = Objects.equals(mDetails, obj) ^ true;
        if(flag)
            mDetails = ((Details) (obj));
        int i = 0;
        boolean flag2 = i;
        if(mCannedTextResponses == null)
        {
            flag2 = i;
            if(parcelablecall.getCannedSmsResponses() != null)
            {
                flag2 = i;
                if(parcelablecall.getCannedSmsResponses().isEmpty() ^ true)
                {
                    mCannedTextResponses = Collections.unmodifiableList(parcelablecall.getCannedSmsResponses());
                    flag2 = true;
                }
            }
        }
        obj = parcelablecall.getVideoCallImpl(mCallingPackage, mTargetSdkVersion);
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        Object obj1;
        if(parcelablecall.isVideoCallProviderChanged())
            flag3 = Objects.equals(mVideoCallImpl, obj) ^ true;
        else
            flag3 = false;
        if(flag3)
            mVideoCallImpl = ((VideoCallImpl) (obj));
        if(mVideoCallImpl != null)
            mVideoCallImpl.setVideoState(getDetails().getVideoState());
        i = parcelablecall.getState();
        if(mState != i)
            flag4 = true;
        else
            flag4 = false;
        if(flag4)
            mState = i;
        obj = parcelablecall.getParentCallId();
        flag5 = Objects.equals(mParentId, obj) ^ true;
        if(flag5)
            mParentId = ((String) (obj));
        flag6 = Objects.equals(parcelablecall.getChildCallIds(), mChildrenIds) ^ true;
        if(flag6)
        {
            mChildrenIds.clear();
            mChildrenIds.addAll(parcelablecall.getChildCallIds());
            mChildrenCached = false;
        }
        obj1 = parcelablecall.getConferenceableCallIds();
        obj = new ArrayList(((List) (obj1)).size());
        obj1 = ((Iterable) (obj1)).iterator();
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break;
            String s = (String)((Iterator) (obj1)).next();
            if(map.containsKey(s))
                ((List) (obj)).add((Call)map.get(s));
        } while(true);
        if(!Objects.equals(mConferenceableCalls, obj))
        {
            mConferenceableCalls.clear();
            mConferenceableCalls.addAll(((java.util.Collection) (obj)));
            fireConferenceableCallsChanged();
        }
        boolean flag7 = false;
        boolean flag8 = false;
        boolean flag9 = false;
        boolean flag10 = false;
        boolean flag1;
        if(parcelablecall.getParcelableRttCall() != null && parcelablecall.getIsRttCallChanged())
        {
            map = parcelablecall.getParcelableRttCall();
            InputStreamReader inputstreamreader = new InputStreamReader(new android.os.ParcelFileDescriptor.AutoCloseInputStream(map.getReceiveStream()), StandardCharsets.UTF_8);
            parcelablecall = new OutputStreamWriter(new android.os.ParcelFileDescriptor.AutoCloseOutputStream(map.getTransmitStream()), StandardCharsets.UTF_8);
            parcelablecall = new RttCall(mTelecomCallId, inputstreamreader, parcelablecall, map.getRttMode(), mInCallAdapter);
            if(mRttCall == null)
            {
                flag1 = true;
            } else
            {
                flag1 = flag8;
                if(mRttCall.getRttAudioMode() != parcelablecall.getRttAudioMode())
                {
                    flag10 = true;
                    flag1 = flag8;
                }
            }
            mRttCall = parcelablecall;
        } else
        {
            flag1 = flag7;
            flag10 = flag9;
            if(mRttCall != null)
            {
                flag1 = flag7;
                flag10 = flag9;
                if(parcelablecall.getParcelableRttCall() == null)
                {
                    flag1 = flag7;
                    flag10 = flag9;
                    if(parcelablecall.getIsRttCallChanged())
                    {
                        flag1 = true;
                        mRttCall = null;
                        flag10 = flag9;
                    }
                }
            }
        }
        if(flag4)
            fireStateChanged(mState);
        if(flag)
            fireDetailsChanged(mDetails);
        if(flag2)
            fireCannedTextResponsesLoaded(mCannedTextResponses);
        if(flag3)
            fireVideoCallChanged(mVideoCallImpl);
        if(flag5)
            fireParentChanged(getParent());
        if(flag6)
            fireChildrenChanged(getChildren());
        if(flag1)
        {
            boolean flag11;
            if(mRttCall != null)
                flag11 = true;
            else
                flag11 = false;
            fireOnIsRttChanged(flag11, mRttCall);
        }
        if(flag10)
            fireOnRttModeChanged(mRttCall.getRttAudioMode());
        if(mState == 7)
            fireCallDestroyed();
    }

    public boolean isRttActive()
    {
        boolean flag;
        if(mRttCall != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void mergeConference()
    {
        mInCallAdapter.mergeConference(mTelecomCallId);
    }

    public void phoneAccountSelected(PhoneAccountHandle phoneaccounthandle, boolean flag)
    {
        mInCallAdapter.phoneAccountSelected(mTelecomCallId, phoneaccounthandle, flag);
    }

    public void playDtmfTone(char c)
    {
        mInCallAdapter.playDtmfTone(mTelecomCallId, c);
    }

    public void postDialContinue(boolean flag)
    {
        mInCallAdapter.postDialContinue(mTelecomCallId, flag);
    }

    public void pullExternalCall()
    {
        if(!mDetails.hasProperty(64))
        {
            return;
        } else
        {
            mInCallAdapter.pullExternalCall(mTelecomCallId);
            return;
        }
    }

    public final void putExtra(String s, int i)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putInt(s, i);
        mInCallAdapter.putExtra(mTelecomCallId, s, i);
    }

    public final void putExtra(String s, String s1)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putString(s, s1);
        mInCallAdapter.putExtra(mTelecomCallId, s, s1);
    }

    public final void putExtra(String s, boolean flag)
    {
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putBoolean(s, flag);
        mInCallAdapter.putExtra(mTelecomCallId, s, flag);
    }

    public final void putExtras(Bundle bundle)
    {
        if(bundle == null)
            return;
        if(mExtras == null)
            mExtras = new Bundle();
        mExtras.putAll(bundle);
        mInCallAdapter.putExtras(mTelecomCallId, bundle);
    }

    public void registerCallback(Callback callback)
    {
        registerCallback(callback, new Handler());
    }

    public void registerCallback(Callback callback, Handler handler)
    {
        unregisterCallback(callback);
        if(callback != null && handler != null && mState != 7)
            mCallbackRecords.add(new CallbackRecord(callback, handler));
    }

    public void reject(boolean flag, String s)
    {
        mInCallAdapter.rejectCall(mTelecomCallId, flag, s);
    }

    public final void removeExtras(List list)
    {
        if(mExtras != null)
        {
            String s;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); mExtras.remove(s))
                s = (String)iterator.next();

            if(mExtras.size() == 0)
                mExtras = null;
        }
        mInCallAdapter.removeExtras(mTelecomCallId, list);
    }

    public final transient void removeExtras(String as[])
    {
        removeExtras(Arrays.asList(as));
    }

    public void removeListener(Listener listener)
    {
        unregisterCallback(listener);
    }

    public void respondToRttRequest(int i, boolean flag)
    {
        mInCallAdapter.respondToRttRequest(mTelecomCallId, i, flag);
    }

    public void sendCallEvent(String s, Bundle bundle)
    {
        mInCallAdapter.sendCallEvent(mTelecomCallId, s, bundle);
    }

    public void sendRttRequest()
    {
        mInCallAdapter.sendRttRequest(mTelecomCallId);
    }

    public void splitFromConference()
    {
        mInCallAdapter.splitFromConference(mTelecomCallId);
    }

    public void stopDtmfTone()
    {
        mInCallAdapter.stopDtmfTone(mTelecomCallId);
    }

    public void stopRtt()
    {
        mInCallAdapter.stopRtt(mTelecomCallId);
    }

    public void swapConference()
    {
        mInCallAdapter.swapConference(mTelecomCallId);
    }

    public String toString()
    {
        return (new StringBuilder()).append("Call [id: ").append(mTelecomCallId).append(", state: ").append(stateToString(mState)).append(", details: ").append(mDetails).append("]").toString();
    }

    public void unhold()
    {
        mInCallAdapter.unholdCall(mTelecomCallId);
    }

    public void unregisterCallback(Callback callback)
    {
label0:
        {
            if(callback == null || mState == 7)
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

    public static final String AVAILABLE_PHONE_ACCOUNTS = "selectPhoneAccountAccounts";
    public static final String EVENT_HANDOVER_COMPLETE = "android.telecom.event.HANDOVER_COMPLETE";
    public static final String EVENT_HANDOVER_FAILED = "android.telecom.event.HANDOVER_FAILED";
    public static final String EVENT_HANDOVER_SOURCE_DISCONNECTED = "android.telecom.event.HANDOVER_SOURCE_DISCONNECTED";
    public static final String EVENT_REQUEST_HANDOVER = "android.telecom.event.REQUEST_HANDOVER";
    public static final String EXTRA_HANDOVER_EXTRAS = "android.telecom.extra.HANDOVER_EXTRAS";
    public static final String EXTRA_HANDOVER_PHONE_ACCOUNT_HANDLE = "android.telecom.extra.HANDOVER_PHONE_ACCOUNT_HANDLE";
    public static final String EXTRA_HANDOVER_VIDEO_STATE = "android.telecom.extra.HANDOVER_VIDEO_STATE";
    public static final String EXTRA_LAST_EMERGENCY_CALLBACK_TIME_MILLIS = "android.telecom.extra.LAST_EMERGENCY_CALLBACK_TIME_MILLIS";
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_CONNECTING = 9;
    public static final int STATE_DIALING = 1;
    public static final int STATE_DISCONNECTED = 7;
    public static final int STATE_DISCONNECTING = 10;
    public static final int STATE_HOLDING = 3;
    public static final int STATE_NEW = 0;
    public static final int STATE_PRE_DIAL_WAIT = 8;
    public static final int STATE_PULLING_CALL = 11;
    public static final int STATE_RINGING = 2;
    public static final int STATE_SELECT_PHONE_ACCOUNT = 8;
    private final List mCallbackRecords;
    private String mCallingPackage;
    private List mCannedTextResponses;
    private final List mChildren;
    private boolean mChildrenCached;
    private final List mChildrenIds;
    private final List mConferenceableCalls;
    private Details mDetails;
    private Bundle mExtras;
    private final InCallAdapter mInCallAdapter;
    private String mParentId;
    private final Phone mPhone;
    private String mRemainingPostDialSequence;
    private RttCall mRttCall;
    private int mState;
    private int mTargetSdkVersion;
    private final String mTelecomCallId;
    private final List mUnmodifiableChildren;
    private final List mUnmodifiableConferenceableCalls;
    private VideoCallImpl mVideoCallImpl;
}
